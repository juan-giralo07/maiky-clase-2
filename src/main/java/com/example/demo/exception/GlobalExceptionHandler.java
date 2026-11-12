package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice 
public class GlobalExceptionHandler {
    
    @ExceptionHandler (RecursoNoEncontradoException.class)
    public ResponseEntity<String> recursoNoEncontrado(RecursoNoEncontradoException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

     @ExceptionHandler (RecursoYaExistenteException.class)
    public ResponseEntity<String> recursoYaExistente(RecursoYaExistenteException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

     @ExceptionHandler (StockInsuficienteException.class)
    public ResponseEntity<String> stockInsuficinete(StockInsuficienteException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
