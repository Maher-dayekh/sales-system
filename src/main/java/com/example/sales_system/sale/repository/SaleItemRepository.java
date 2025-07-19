package com.example.sales_system.sale.repository;

import com.example.sales_system.sale.model.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemRepository extends JpaRepository<SaleItem, Long> { }
