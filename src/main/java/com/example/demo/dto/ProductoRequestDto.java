package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data 
public class ProductoRequestDto {
    @NotBlank (message = "El nombre no puede ser nulo")
    private String nombre;

    @NotBlank (message = "El código de barra no puede ser nulo")
    private String codigoBarra;

    @Positive (message = "El precio debe ser un número positivo")
    private Double precio;

    @Positive (message = "El stock debe ser un número positivo")
    private Integer stock;
}
