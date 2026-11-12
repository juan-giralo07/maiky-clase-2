package com.example.demo.dto;


import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.entity.DetalleVenta;

import lombok.Data;

@Data 
public class VentaResponse {
    
    private Long clienteId;
    private LocalDateTime fecha;
    private Double total;
    private List<DetalleVenta> detalles;

}
