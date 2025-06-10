package com.Classy.exception;

public class EntidadeDuplicadaException extends RuntimeException{
    public EntidadeDuplicadaException(String entidade, String campo, String valor){
        super(String.format("Já existe um(a) %s com %s '%s'.", entidade, campo, valor ));
    }
}
