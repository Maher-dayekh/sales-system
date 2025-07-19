package com.example.sales_system.client.repository;

import com.example.sales_system.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByMobile(String mobile);
}
