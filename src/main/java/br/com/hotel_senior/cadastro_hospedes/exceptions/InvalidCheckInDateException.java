package br.com.hotel_senior.cadastro_hospedes.exceptions;

import java.io.Serial;

public class InvalidCheckInDateException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidCheckInDateException(String msg) { super(msg); }
}
