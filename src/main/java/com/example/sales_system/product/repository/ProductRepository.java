package com.example.sales_system.product.repository;

import com.example.sales_system.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
