package com.example.sales_system.sale.dto.creational;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class UpdateSaleDto {
    @Size(max = 100, message = "seller must be less than 100 characters")
    private String seller;
    @NotNull(message = "items are required")
    @NotEmpty(message = "items must not be empty")
    @Valid
    private List<UpdateSaleItemDto> items;   // required by spec
}
