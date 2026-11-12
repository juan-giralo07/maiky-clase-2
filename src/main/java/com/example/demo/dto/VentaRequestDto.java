

 package com.example.demo.dto;

import java.time.Instant;
import java.util.List;

import lombok.Data;

@Data 
public class VentaRequestDto {

    
    private Long clienteId;
    private Instant fecha;
    private Double total;
    private List<DetallesVenta> detalles;

     public record DetallesVenta(
    Long id,
    Integer cantidad,
    Double precioUnitario,
    Double sudtotal
 ) {
 }
   
}


   
 


