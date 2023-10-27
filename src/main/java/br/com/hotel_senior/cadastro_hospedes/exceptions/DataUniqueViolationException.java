package br.com.hotel_senior.cadastro_hospedes.exceptions;

import java.io.Serial;

public class DataUniqueViolationException extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;
    public DataUniqueViolationException (String msg){
        super(msg);
    }
}
