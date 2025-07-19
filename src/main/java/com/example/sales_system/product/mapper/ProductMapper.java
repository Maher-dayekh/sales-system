package com.example.sales_system.product.mapper;

import com.example.sales_system.product.dto.CreateProductDto;
import com.example.sales_system.product.dto.ProductResponseDto;
import com.example.sales_system.product.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(CreateProductDto dto) {
        if (dto == null) return null;
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .build();
    }

    public ProductResponseDto toResponseDto(Product p) {
        if (p == null) return null;
        return ProductResponseDto.builder()
                .id(p.getId())
                .name(p.getName())
                .description(p.getDescription())
                .category(p.getCategory())
                .createdAt(p.getCreatedAt())
                .build();
    }

    public void updateEntity(Product p, CreateProductDto dto) {
        if (p == null || dto == null) return;
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setCategory(dto.getCategory());
    }
}
