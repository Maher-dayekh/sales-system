package com.example.sales_system.product.service.impl;

import com.example.sales_system.common.exception.DatabaseOperationException;
import com.example.sales_system.common.exception.ResourceNotFoundException;
import com.example.sales_system.product.dto.CreateProductDto;
import com.example.sales_system.product.dto.ProductResponseDto;
import com.example.sales_system.product.mapper.ProductMapper;
import com.example.sales_system.product.model.Product;
import com.example.sales_system.product.repository.ProductRepository;
import com.example.sales_system.product.service.interfaces.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;
    private final ProductMapper mapper;

    @Override
    public List<ProductResponseDto> listProducts() {
        try {
            return repo.findAll()
                    .stream()
                    .map(mapper::toResponseDto)
                    .toList();
        } catch (Exception ex) {
            throw new DatabaseOperationException("Failed to fetch products", ex);
        }
    }

    @Override
    public ProductResponseDto  create(CreateProductDto dto) {
        try {
            Product saved = repo.save(mapper.toEntity(dto));
            return mapper.toResponseDto(saved);
        } catch (Exception ex) {
            throw new DatabaseOperationException("Failed to create product", ex);
        }
    }

    @Override
    public ProductResponseDto update(Long id, CreateProductDto dto) {
        try {
            Product product = repo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product", id));
            mapper.updateEntity(product, dto);
            return mapper.toResponseDto(product);             // return updated object
        } catch (ResourceNotFoundException nf) {
            throw nf;
        } catch (Exception ex) {
            throw new DatabaseOperationException("Failed to update product", ex);
        }
    }
}
