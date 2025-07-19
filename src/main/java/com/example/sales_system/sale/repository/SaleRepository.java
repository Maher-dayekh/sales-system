package com.example.sales_system.sale.repository;

import com.example.sales_system.sale.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {}
