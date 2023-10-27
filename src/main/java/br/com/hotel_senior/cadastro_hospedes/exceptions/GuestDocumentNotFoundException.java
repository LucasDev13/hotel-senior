package br.com.hotel_senior.cadastro_hospedes.exceptions;

import java.io.Serial;

public class GuestDocumentNotFoundException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    public GuestDocumentNotFoundException(String msg) { super(msg); }
}
