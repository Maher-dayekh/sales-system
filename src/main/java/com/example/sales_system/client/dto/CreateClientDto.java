package com.example.sales_system.client.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateClientDto {

    @NotBlank(message = "first name is required")
    @Size(max = 80, message = "first name must be less than 80 characters")
    private String firstName;

    @NotBlank(message = "last name is required")
    @Size(max = 80, message = "last name must be less than 80 characters")
    private String lastName;

    @NotBlank(message = "mobile number is required")
    @Pattern(regexp = "^[0-9]{8,20}$", message = "mobile number must follow the pattern")      // simple mobile check
    private String mobile;
}
