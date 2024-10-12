package com.scanai.api.infra.exceptions;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiErrorResponse {
    private String message;
    private String error;
    private LocalDateTime timestamp;

    public ApiErrorResponse(String message, String error, LocalDateTime now) {
        this.message = message;
        this.error = error;
        this.timestamp = now;
    }
}
