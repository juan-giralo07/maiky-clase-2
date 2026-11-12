package com.example.demo.entity;


import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity 
@Data 
@Table (name = "venta")
public class Venta {
    
    @Id 
    @Column (name = "id")
    private Long id;

    @Column (name = "fecha")
    private LocalDateTime fecha;

    @Column (name = "total")
    private Double total;

    @ManyToOne 
    private Cliente cliente;

    @OneToMany (mappedBy = "DetalleVenta")
    private List<DetalleVenta> detalles;
}
