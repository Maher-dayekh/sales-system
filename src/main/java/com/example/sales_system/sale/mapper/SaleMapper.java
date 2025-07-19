package com.example.sales_system.sale.mapper;

import com.example.sales_system.client.model.Client;
import com.example.sales_system.product.model.Product;
import com.example.sales_system.sale.dto.creational.CreateSaleDto;
import com.example.sales_system.sale.dto.creational.CreateSaleItemDto;
import com.example.sales_system.sale.dto.response.SaleItemResponseDto;
import com.example.sales_system.sale.dto.response.SaleResponseDto;
import com.example.sales_system.sale.model.Sale;
import com.example.sales_system.sale.model.SaleItem;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class SaleMapper {

    public Sale toEntity(CreateSaleDto dto, Client client, List<Product> products) {

        Sale sale = Sale.builder()
                .client(client)
                .seller(dto.getSeller())
                .items(new ArrayList<>())
                .build();

        Map<Long, Product> productById =
                products.stream()
                        .collect(Collectors.toMap(Product::getId, Function.identity()));

        List<SaleItem> items = dto.getItems()
                .stream()
                .map(itemDto -> toSaleItem(itemDto, sale, productById))
                .toList();

        sale.setItems(new ArrayList<>(items));

        BigDecimal total = items.stream()
                .map(SaleItem::getLineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        sale.setTotal(total);

        return sale;
    }

    private SaleItem toSaleItem(CreateSaleItemDto dto,
                                Sale sale,
                                Map<Long, Product> productById) {

        Product p = productById.get(dto.getProductId());

        BigDecimal lineTotal = dto.getUnitPrice()
                .multiply(BigDecimal.valueOf(dto.getQuantity()));

        return SaleItem.builder()
                .sale(sale)
                .product(p)
                .quantity(dto.getQuantity())
                .unitPrice(dto.getUnitPrice())
                .lineTotal(lineTotal)
                .build();
    }

    public SaleResponseDto toResponseDto(Sale s) {
        return SaleResponseDto.builder()
                .id(s.getId())
                .createdAt(s.getCreatedAt())
                .clientId(s.getClient().getId())
                .seller(s.getSeller())
                .total(s.getTotal())
                .items(
                        s.getItems().stream()
                                .map(this::toItemResponse)
                                .toList()
                )
                .build();
    }

    private SaleItemResponseDto toItemResponse(SaleItem si) {
        return SaleItemResponseDto.builder()
                .id(si.getId())
                .productId(si.getProduct().getId())
                .productName(si.getProduct().getName())
                .quantity(si.getQuantity())
                .unitPrice(si.getUnitPrice())
                .lineTotal(si.getLineTotal())
                .build();
    }
}
