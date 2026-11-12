package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity 
@Data 
@Table (name = "producto")
public class Producto {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "nombre")
    private String nombre;

    @Column (name = "precio")
    private Double precio;

    @Column (name = "stock")
    private Integer stock;

    @Column (name = "codigo_barras")
    private String codigoBarras;

    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private Provedor provedor;
}
