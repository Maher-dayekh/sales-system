package com.example.sales_system.sale.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SaleResponseDto {
    private Long id;
    private LocalDateTime createdAt;
    private Long clientId;
    private String seller;
    private BigDecimal total;
    private List<SaleItemResponseDto> items;
}
