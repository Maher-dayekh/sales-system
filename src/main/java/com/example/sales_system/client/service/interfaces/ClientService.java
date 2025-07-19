package com.example.sales_system.client.service.interfaces;

import com.example.sales_system.client.dto.ClientResponseDto;
import com.example.sales_system.client.dto.CreateClientDto;

import java.util.List;

public interface ClientService {
    List<ClientResponseDto> listClients();
    Long create(CreateClientDto dto);
    void update(Long id, CreateClientDto dto);
}
