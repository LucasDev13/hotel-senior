package br.com.hotel_senior.cadastro_hospedes.exceptions;

import java.time.Instant;

public record StandardError(
        Instant timestamp,
        Integer status,
        String error,
        String message,
        String path
) {
}
