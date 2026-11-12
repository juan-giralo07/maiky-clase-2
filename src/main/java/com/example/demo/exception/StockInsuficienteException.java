package com.example.demo.exception;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String msj){
        super(msj);
    }
}
