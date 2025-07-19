package com.example.sales_system.sale.dto.creational;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateSaleItemDto {
    @NotNull(message = "product id is required")
    private Long productId;

    @NotNull(message = "quantity is required")
    @Min(value = 1, message = "quantity must be greater than 0")
    private Integer quantity;

    @NotNull(message = "unit price is required")
    @DecimalMin(value = "0.01", message = "unit price mst be greater than 0")
    private BigDecimal unitPrice;
}
