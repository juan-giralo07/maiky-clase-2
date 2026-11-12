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
/*ponemos el nombre que tendra la table ya que si no lo hacemos
la db por defecto lo pondra todo feo*/
@Table (name = "categoria")
public class Categoria {

    
    @Id 
    //que se va autoicrementar
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    //simplemte le ponemos el nombre para que pase lo mismo con la db
    @Column (name = "id")
    private Long id;

    @Column (name = "nombre")
    private String nombre;

    @Column (name = "descripcion")
    private String descripcion;

    //la relacion con productos
    @OneToMany (mappedBy = "categoria")
    private List<Producto> productos;
}
