package com.example.sales_system.client.controller;

import com.example.sales_system.client.dto.ClientResponseDto;
import com.example.sales_system.client.dto.CreateClientDto;
import com.example.sales_system.client.service.interfaces.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public List<ClientResponseDto> list() {
        return clientService.listClients();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@Valid @RequestBody CreateClientDto dto) {
        return clientService.create(dto);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @Valid @RequestBody CreateClientDto dto) {
        clientService.update(id, dto);
    }
}
