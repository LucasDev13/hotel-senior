package br.com.hotel_senior.cadastro_hospedes.exceptions;

public class DataUniqueViolationException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public DataUniqueViolationException (String msg){
        super(msg);
    }
}
