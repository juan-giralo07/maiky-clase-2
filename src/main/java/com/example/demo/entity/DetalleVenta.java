package com.example.demo.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity 
@Data 
@Table (name = "detalle_venta")
public class DetalleVenta {
    
    @Id 
    @Column (name = "id")
    private Long id;

    @Column (name = "cantidad")
    private Integer cantidad;

    @Column (name = "precio_unitario")
    private Double precioUnitario;

    @Column (name = "subtotal")
    private Double subtotal;

    @ManyToOne 
    private Venta venta;

    @ManyToOne 
    private Producto producto;
}

