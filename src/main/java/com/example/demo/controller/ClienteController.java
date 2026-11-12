package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ClienteRequestDto;
import com.example.demo.dto.ClienteResponseDto;
import com.example.demo.service.ClienteService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/cliente")
public class ClienteController {
    
    private final ClienteService clienteService;

    ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @PostMapping ("/create")
    public ResponseEntity<ClienteResponseDto> create(@Valid @RequestBody ClienteRequestDto requestDto){
        ClienteResponseDto responseDto = clienteService.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping ("/verPorId/{id}")
    public ResponseEntity<ClienteResponseDto> verPorId(@PathVariable Long id){
        ClienteResponseDto responseDto = clienteService.getClienteById(id);
        return  ResponseEntity.status(HttpStatus.OK).body(responseDto);
     }

     @GetMapping ("/verTodos")
     public ResponseEntity<List<ClienteResponseDto>> verTodos(){
        List<ClienteResponseDto> responseDtos = clienteService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(responseDtos);
     }

     @GetMapping ("/verPorDocumento/{documento}")
     public ResponseEntity<ClienteResponseDto> verPorDocumento(@PathVariable String documento){
        ClienteResponseDto responseDto = clienteService.verPorDocumneto(documento);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
     }

     @PutMapping ("/actualizar/{id}")
    public ResponseEntity<ClienteResponseDto> actualizar (@PathVariable Long id,@Valid @RequestBody ClienteRequestDto requestDto){
        ClienteResponseDto responseDto = clienteService.update(id,requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping ("/borrar/{id}")
    public ResponseEntity<String> borrar(@PathVariable  Long id){
        String response = clienteService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
