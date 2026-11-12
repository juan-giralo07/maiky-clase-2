package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data 
public class ProvedorRequestDto {
    
    @NotBlank (message = "noombre obligatorio")
    private String nombre;

    @NotBlank (message = "telifono oblagtorio")
    private String telefono;

    
    @Email (message = "formato email")
    private String correo;
}
