package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data 
public class CategoriaRequestDto {
    
    //una validacion que practicamente lo que hace es revisar si ese valor esta vacio o no
    @NotBlank ( message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank ( message = "La descripción es obligatoria")
    private String descripcion;
}
