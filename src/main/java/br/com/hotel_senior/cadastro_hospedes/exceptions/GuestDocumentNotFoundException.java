package br.com.hotel_senior.cadastro_hospedes.exceptions;

public class GuestDocumentNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public GuestDocumentNotFoundException(String msg) { super(msg); }
}
