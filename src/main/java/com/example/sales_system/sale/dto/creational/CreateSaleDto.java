package com.example.sales_system.sale.dto.creational;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class CreateSaleDto {
    @NotNull(message = "client id is required")
    private Long clientId;
    @NotNull(message = "seller is required")
    @Size(max = 100, message = "seller must be less than 100 characters")
    private String seller;
    @NotNull(message = "items are required")
    @NotEmpty(message = "items must not be empty")
    @Valid
    private List<CreateSaleItemDto> items;
}
