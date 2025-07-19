package com.example.sales_system.common.exception;

import java.time.Instant;

public record ErrorResponse(
        int status,
        String message,
        String path,
        Instant timestamp
) {}
