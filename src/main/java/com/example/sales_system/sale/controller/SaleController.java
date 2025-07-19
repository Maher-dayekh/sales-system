package com.example.sales_system.sale.controller;

import com.example.sales_system.sale.dto.creational.CreateSaleDto;
import com.example.sales_system.sale.dto.creational.UpdateSaleDto;
import com.example.sales_system.sale.dto.response.SaleResponseDto;
import com.example.sales_system.sale.service.interfaces.SaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService service;

    @GetMapping
    public List<SaleResponseDto> list() {
        return service.listSales();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaleResponseDto create(@Valid @RequestBody CreateSaleDto dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public SaleResponseDto update(@PathVariable Long id,
                       @Valid @RequestBody UpdateSaleDto dto) {
        return service.update(id, dto);
    }
}
