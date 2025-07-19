package com.example.sales_system.sale.service.interfaces;

import com.example.sales_system.sale.dto.creational.CreateSaleDto;
import com.example.sales_system.sale.dto.creational.UpdateSaleDto;
import com.example.sales_system.sale.dto.response.SaleResponseDto;

import java.util.List;

public interface SaleService {
    List<SaleResponseDto> listSales();
    SaleResponseDto create(CreateSaleDto dto);
    SaleResponseDto update(Long id, UpdateSaleDto dto);
}
