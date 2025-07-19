package com.example.sales_system.product.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private String category;
    private LocalDateTime createdAt;
}