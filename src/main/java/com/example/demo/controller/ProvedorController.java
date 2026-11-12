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


import com.example.demo.dto.ProvedorRequestDto;
import com.example.demo.dto.ProvedorResponseDto;
import com.example.demo.service.ProvedorService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/provedores")
public class ProvedorController {
    
    private final ProvedorService provedorService;

    public ProvedorController(ProvedorService provedorService) {
        this.provedorService = provedorService;
    }

    @PostMapping ("/create")
    public ResponseEntity<ProvedorResponseDto> createProvedor(@Valid @RequestBody ProvedorRequestDto requestDto) {
        ProvedorResponseDto responseDto = provedorService.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping ("/verPorid/{id}")
    public ResponseEntity<ProvedorResponseDto> getProvedorById(@PathVariable Long id) {
        ProvedorResponseDto responseDto = provedorService.getProvedorById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
    @PutMapping ("/actualizar/{id}")
    public ResponseEntity<ProvedorResponseDto> actualizar(@PathVariable Long id, @Valid @RequestBody ProvedorRequestDto requestDto){
        ProvedorResponseDto responseDto = provedorService.update(id,requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping ("/borrar/{id}")
    public ResponseEntity<String> borrar(@PathVariable Long id){
        String response = provedorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @GetMapping ("/verTodos")
    public ResponseEntity<List<ProvedorResponseDto>> verTodos(){
        List<ProvedorResponseDto> responseDtos = provedorService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(responseDtos);
    }

  
    }



