package com.example.sales_system.sale.service.impl;

import com.example.sales_system.sale.dto.creational.UpdateSaleItemDto;
import com.example.sales_system.sale.model.SaleItem;
import com.example.sales_system.sale.model.SaleItemAudit;
import com.example.sales_system.sale.repository.SaleItemAuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SaleAuditService {

    private final SaleItemAuditRepository auditRepo;

    public void logChanges(SaleItem existing, UpdateSaleItemDto upd) {

        if (existing.getQuantity().equals(upd.getQuantity()) &&
                existing.getUnitPrice().compareTo(upd.getUnitPrice()) == 0) {
            return;      // nothing changed – skip audit row
        }

        SaleItemAudit audit = SaleItemAudit.builder()
                .saleItemId(existing.getId())
                .oldQty(existing.getQuantity())
                .newQty(upd.getQuantity())
                .oldPrice(existing.getUnitPrice())
                .newPrice(upd.getUnitPrice())
                .changedAt(LocalDateTime.now())
                .build();

        auditRepo.save(audit);
    }

}
