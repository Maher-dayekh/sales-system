package com.example.sales_system.client.service.impl;

import com.example.sales_system.common.exception.DatabaseOperationException;
import com.example.sales_system.common.exception.ResourceNotFoundException;
import com.example.sales_system.client.dto.ClientResponseDto;
import com.example.sales_system.client.dto.CreateClientDto;
import com.example.sales_system.client.mapper.ClientMapper;
import com.example.sales_system.client.model.Client;
import com.example.sales_system.client.repository.ClientRepository;
import com.example.sales_system.client.service.interfaces.ClientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repo;
    private final ClientMapper mapper;

    @Override
    public List<ClientResponseDto> listClients() {
        try {
            return repo.findAll()
                    .stream()
                    .map(mapper::toResponseDto)
                    .toList();
        } catch (Exception ex) {
            throw new DatabaseOperationException("Failed to fetch clients", ex);
        }
    }

    @Override
    public Long create(CreateClientDto dto) {
        try {
            if (repo.existsByMobile(dto.getMobile()))
                throw new IllegalArgumentException("Mobile already exists");

            Client entity = mapper.toEntity(dto);
            return repo.save(entity).getId();
        } catch (IllegalArgumentException iae) {
            throw iae; // handled globally
        } catch (Exception ex) {
            throw new DatabaseOperationException("Failed to create client", ex);
        }
    }

    @Override
    public void update(Long id, CreateClientDto dto) {
        try {
            Client client = repo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Client", id));
            mapper.updateEntity(client, dto);
        } catch (ResourceNotFoundException nf) {
            throw nf;
        } catch (Exception ex) {
            throw new DatabaseOperationException("Failed to update client", ex);
        }
    }
}
