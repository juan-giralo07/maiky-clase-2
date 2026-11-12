package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity 
@Data 
@Table (name = "provedor")
public class Provedor {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;


    @Column (name = "nombre")
    private String nombre;

    @Column (name = "telefono")
    private String telefono;

    @Column (name = "correo")
    private String correo;

    @OneToMany (mappedBy = "provedor")
    private List<Producto> productos;

}
