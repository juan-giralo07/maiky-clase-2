package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

//creamos getter y setter
@Data 
public class ClienteRequestDto {
    
    @NotBlank ( message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank ( message = "El teléfono es obligatorio")
    private String telefono;
    
    @NotBlank ( message = "El documento es obligatorio")
    private String documento;

}
