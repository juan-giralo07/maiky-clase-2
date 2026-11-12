package com.example.demo.exception;

public class RecursoYaExistenteException extends RuntimeException {
    public RecursoYaExistenteException(String msj){
        super(msj);
    }
}
