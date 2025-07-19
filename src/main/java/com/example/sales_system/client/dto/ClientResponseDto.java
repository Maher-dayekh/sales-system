package com.example.sales_system.client.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ClientResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String mobile;
    private LocalDateTime createdAt;
}
