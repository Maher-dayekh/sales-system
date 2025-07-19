package com.example.sales_system.product.controller;

import com.example.sales_system.product.dto.CreateProductDto;
import com.example.sales_system.product.dto.ProductResponseDto;
import com.example.sales_system.product.service.interfaces.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductResponseDto> list() { return productService.listProducts(); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDto  create(@Valid @RequestBody CreateProductDto dto) {
        return productService.create(dto);
    }

    @PutMapping("/{id}")
    public ProductResponseDto  update(@PathVariable Long id, @Valid @RequestBody CreateProductDto dto) {
        return productService.update(id, dto);
    }
}
