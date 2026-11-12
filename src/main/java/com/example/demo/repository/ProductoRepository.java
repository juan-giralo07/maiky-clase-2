package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Producto;

public interface  ProductoRepository extends JpaRepository<Producto, Long>  {
    Optional<Producto> findByCodigoBarra(String codigoBarra);
    List<Producto> findByCategoriaId(Long categoriaId);
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
}
