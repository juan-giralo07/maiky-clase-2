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

import com.example.demo.dto.CategoriaRequestDto;
import com.example.demo.dto.CategoriaResponseDto;
import com.example.demo.service.CategoriaService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/categorias")
public class CategoriaController {
    
    private final CategoriaService categoriaService;
    
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

   
    @PostMapping ("/crear")
    
    public ResponseEntity<CategoriaResponseDto> crearCategoria(@Valid @RequestBody CategoriaRequestDto categoriaRequestDto) {
        CategoriaResponseDto categoriaResponseDto = categoriaService.createCategoria(categoriaRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaResponseDto);
    }

    @GetMapping ("/ver/{id}")
    
    public ResponseEntity<CategoriaResponseDto> verCategoria(@PathVariable Long id) {
        CategoriaResponseDto categoriaResponseDto = categoriaService.getCategoriaById(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoriaResponseDto);
    }

    @GetMapping ("/verTodos")
    public ResponseEntity<List<CategoriaResponseDto>> verTodasLasCategorias() {
        List<CategoriaResponseDto> categoriaResponseDtos = categoriaService.getAllCategorias();
        return ResponseEntity.status(HttpStatus.OK).body(categoriaResponseDtos);
    }

    @DeleteMapping ("/eliminar/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id) {
        String response = categoriaService.deleteCategoria(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping ("/actualizar/{id}")
    public ResponseEntity<CategoriaResponseDto> actualizarCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaRequestDto categoriaRequestDto) {
        CategoriaResponseDto categoriaResponseDto = categoriaService.updateCategoria(id, categoriaRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(categoriaResponseDto);
    }
}
