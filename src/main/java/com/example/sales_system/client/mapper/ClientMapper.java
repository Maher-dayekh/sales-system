package com.example.sales_system.client.mapper;

import com.example.sales_system.client.dto.ClientResponseDto;
import com.example.sales_system.client.dto.CreateClientDto;
import com.example.sales_system.client.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client toEntity(CreateClientDto dto) {
        if (dto == null) return null;
        return Client.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .mobile(dto.getMobile())
                .build();
    }

    public ClientResponseDto toResponseDto(Client c) {
        if (c == null) return null;
        return ClientResponseDto.builder()
                .id(c.getId())
                .firstName(c.getFirstName())
                .lastName(c.getLastName())
                .mobile(c.getMobile())
                .createdAt(c.getCreatedAt())
                .build();
    }

    public void updateEntity(Client c, CreateClientDto dto) {
        if (c == null || dto == null) return;
        c.setFirstName(dto.getFirstName());
        c.setLastName(dto.getLastName());
        c.setMobile(dto.getMobile());
    }
}
