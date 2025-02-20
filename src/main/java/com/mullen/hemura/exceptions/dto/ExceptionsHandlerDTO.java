package com.mullen.hemura.exceptions.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ExceptionsHandlerDTO(String path, HttpStatus status, String error, String message, LocalDateTime timestamp) {
}
