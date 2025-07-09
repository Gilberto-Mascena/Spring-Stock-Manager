package br.com.mascenadev.springstockmanager.exception;

import java.time.LocalDateTime;

public record StandardError(
        LocalDateTime timestamp,
        Integer status,
        String error,
        Object errorDetails
) {
}
