package com.example.sales_system.sale.model;

import com.example.sales_system.product.model.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "sale_items")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) private Sale sale;
    @ManyToOne(optional = false) private Product product;

    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal lineTotal;   // quantity × price
}