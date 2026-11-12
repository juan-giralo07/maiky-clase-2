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

import com.example.demo.dto.ProductoRequestDto;
import com.example.demo.dto.ProductoResponseDto;
import com.example.demo.service.ProductoService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping ("/api/productos")
public class ProductoController {


    private final ProductoService productoService;

    ProductoController(ProductoService productoService) {
        this.productoService = productoService; 
    }

    @PostMapping ("/crear")
    public ResponseEntity<ProductoResponseDto> crearProducto(@Valid @RequestBody ProductoRequestDto productoRequestDto) {
        ProductoResponseDto productoResponseDto = productoService.createProducto(productoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoResponseDto);
    }

    @GetMapping ("ver/{id}")
    public ResponseEntity<ProductoResponseDto> verProducto(@PathVariable Long id) {
        ProductoResponseDto productoResponseDto = productoService.getProductoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(productoResponseDto);
    }

    @GetMapping ("/verTodos")
    public ResponseEntity<List<ProductoResponseDto>> verTodosLosProductos() {
        List<ProductoResponseDto> productoResponseDtos = productoService.getAllProductos();
        return ResponseEntity.status(HttpStatus.OK).body(productoResponseDtos);
    }

    @PutMapping ("/actualizar/{id}")
    public ResponseEntity<ProductoResponseDto> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ProductoRequestDto productoRequestDto) {
        ProductoResponseDto productoResponseDto = productoService.updateProducto(id, productoRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(productoResponseDto);
    }

    @DeleteMapping ("/eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
        String response = productoService.deleteProducto(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping ("/buscarPorCategoria/{categoriaId}")
    public ResponseEntity<List<ProductoResponseDto>> buscarPorCategoria(@PathVariable Long categoriaId) {
        List<ProductoResponseDto> productoResponseDtos = productoService.getProductosByCategoria(categoriaId);
        return ResponseEntity.status(HttpStatus.OK).body(productoResponseDtos);
    }

    @GetMapping ("/buscarPorNombre/{nombre}")
    public ResponseEntity<List<ProductoResponseDto>> buscarPorNombre(@PathVariable String nombre) {
        List<ProductoResponseDto> productoResponseDtos = productoService.getProductosByNombre(nombre);
        return ResponseEntity.status(HttpStatus.OK).body(productoResponseDtos);
    }

    @GetMapping ("/limite/{limite}")
    public ResponseEntity<List<ProductoResponseDto>> verProductosConLimite(@PathVariable int limite) {
        List<ProductoResponseDto> productoResponseDtos = productoService.getProductosConLimite(limite);
        return ResponseEntity.status(HttpStatus.OK).body(productoResponseDtos);
    }
}
