package com.example.sales_system.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateProductDto {
    @NotBlank(message = "Product name is required")
    @Size(max=100, message = "product name must be less than 100 characters") private String name;
    private String description;
    private String category;
}
