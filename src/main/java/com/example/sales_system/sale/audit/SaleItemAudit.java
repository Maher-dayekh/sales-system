package com.example.sales_system.sale.audit;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "sale_item_audit")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleItemAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long saleItemId;
    private Integer oldQty;
    private Integer newQty;
    private BigDecimal oldPrice;
    private BigDecimal newPrice;

    private LocalDateTime changedAt;
}
