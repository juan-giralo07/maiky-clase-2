package com.example.demo.dto;

import lombok.Data;

@Data 
public class ProductoResponseDto {
    
    private Long id;
    private String nombre;
    private String codigoBarra;
    private Double precio;
    private Integer stock;
}
