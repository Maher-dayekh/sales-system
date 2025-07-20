package com.example.sales_system.sale.service.impl;

import com.example.sales_system.client.model.Client;
import com.example.sales_system.client.repository.ClientRepository;
import com.example.sales_system.common.exception.DatabaseOperationException;
import com.example.sales_system.common.exception.ResourceNotFoundException;
import com.example.sales_system.product.model.Product;
import com.example.sales_system.product.repository.ProductRepository;
import com.example.sales_system.sale.dto.creational.CreateSaleDto;
import com.example.sales_system.sale.dto.creational.CreateSaleItemDto;
import com.example.sales_system.sale.dto.creational.UpdateSaleDto;
import com.example.sales_system.sale.dto.creational.UpdateSaleItemDto;
import com.example.sales_system.sale.dto.response.SaleResponseDto;
import com.example.sales_system.sale.mapper.SaleMapper;
import com.example.sales_system.sale.model.Sale;
import com.example.sales_system.sale.model.SaleItem;
import com.example.sales_system.sale.repository.SaleRepository;
import com.example.sales_system.sale.service.interfaces.SaleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepo;
    private final ClientRepository clientRepo;
    private final ProductRepository productRepo;
    private final SaleMapper mapper;
    private final SaleAuditService auditService;


    @Override
    public List<SaleResponseDto> listSales() {
        try {
            return saleRepo.findAll()
                    .stream()
                    .map(mapper::toResponseDto)
                    .toList();
        } catch (Exception ex) {
            throw new DatabaseOperationException("Failed to fetch sales", ex);
        }
    }

    @Override
    public SaleResponseDto create(CreateSaleDto dto) {
        try {
            Client client = clientRepo.findById(dto.getClientId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Client", dto.getClientId()));

            List<Long> ids = dto.getItems().stream()
                    .map(CreateSaleItemDto::getProductId)
                    .distinct()
                    .toList();

            List<Product> products = productRepo.findAllById(ids);

            if (products.size() != ids.size())
                throw new ResourceNotFoundException("Product", "one of " + ids);

            Sale sale = mapper.toEntity(dto, client, products);
            Sale saved = saleRepo.save(sale);
            return mapper.toResponseDto(saved);

        } catch (ResourceNotFoundException rnfe) {
            throw rnfe;
        } catch (Exception ex) {
            throw new DatabaseOperationException("Failed to create sale", ex);
        }
    }


    @Override
    public SaleResponseDto update(Long id, UpdateSaleDto dto) {
        try {
            Sale sale = saleRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Sale", id));

            if (dto.getSeller() != null)
                sale.setSeller(dto.getSeller());

            // Extract all productIds from update DTO
            List<Long> incomingProductIds = dto.getItems()
                    .stream()
                    .map(UpdateSaleItemDto::getProductId)
                    .distinct()
                    .toList();

            // Validate products exist
            List<Product> foundProducts = productRepo.findAllById(incomingProductIds);
            if (foundProducts.size() != incomingProductIds.size()) {
                throw new ResourceNotFoundException("Product", "One or more product IDs do not exist: " + incomingProductIds);
            }

            // Validate that the sale contains those product IDs
            List<Long> productIdsInSale = sale.getItems()
                    .stream()
                    .map(i -> i.getProduct().getId())
                    .toList();

            for (Long incomingId : incomingProductIds) {
                if (!productIdsInSale.contains(incomingId)) {
                    throw new ResourceNotFoundException("SaleItem", "Product ID " + incomingId + " is not part of the current sale.");
                }
            }

            // Map updates by productId
            var updatesByProductId = dto.getItems()
                    .stream()
                    .collect(Collectors.toMap(UpdateSaleItemDto::getProductId, u -> u));

            BigDecimal newTotal = BigDecimal.ZERO;

            for (SaleItem item : sale.getItems()) {
                Long productId = item.getProduct().getId();
                UpdateSaleItemDto upd = updatesByProductId.get(productId);
                if (upd == null) continue;

                auditService.logChanges(item, upd);

                item.setQuantity(upd.getQuantity());
                item.setUnitPrice(upd.getUnitPrice());
                item.setLineTotal(
                        upd.getUnitPrice().multiply(BigDecimal.valueOf(upd.getQuantity()))
                );
                newTotal = newTotal.add(item.getLineTotal());
            }

            sale.setTotal(newTotal);
            return mapper.toResponseDto(saleRepo.save(sale));

        } catch (ResourceNotFoundException nf) {
            throw nf;
        } catch (Exception ex) {
            throw new DatabaseOperationException("Failed to update sale", ex);
        }
    }

}
