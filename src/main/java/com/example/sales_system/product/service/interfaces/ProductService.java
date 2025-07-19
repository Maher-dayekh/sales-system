package com.example.sales_system.product.service.interfaces;

import com.example.sales_system.product.dto.CreateProductDto;
import com.example.sales_system.product.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    List<ProductResponseDto> listProducts();
    ProductResponseDto  create(CreateProductDto dto);
    ProductResponseDto  update(Long id, CreateProductDto dto);
}