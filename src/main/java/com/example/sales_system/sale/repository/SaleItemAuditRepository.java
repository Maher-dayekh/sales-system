package com.example.sales_system.sale.repository;

import com.example.sales_system.sale.audit.SaleItemAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemAuditRepository extends JpaRepository<SaleItemAudit, Long> { }
