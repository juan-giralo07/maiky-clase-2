package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductoRequestDto;
import com.example.demo.dto.ProductoResponseDto;
import com.example.demo.entity.Producto;
import com.example.demo.exception.RecursoNoEncontradoException;
import com.example.demo.exception.RecursoYaExistenteException;
import com.example.demo.repository.ProductoRepository;




@Service  
public class ProductoService {
    
   private final ProductoRepository productoRepository;

   ProductoService(ProductoRepository productoRepository){
    this.productoRepository = productoRepository;
   }
  

    public ProductoResponseDto getProductoById(Long id) {
       
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado con id: " + id));

        ProductoResponseDto productoResponseDto = new ProductoResponseDto();
        productoResponseDto.setId(producto.getId());
        productoResponseDto.setNombre(producto.getNombre());
        productoResponseDto.setPrecio(producto.getPrecio());
        productoResponseDto.setStock(producto.getStock());

        return productoResponseDto;
    }

    public ProductoResponseDto createProducto(ProductoRequestDto productoRequestDto) {
        
     productoRepository.findByCodigoBarra(productoRequestDto.getCodigoBarra())
     .ifPresent((producto) -> {
        throw new RecursoYaExistenteException("El producto con código de barra " + productoRequestDto.getCodigoBarra() + " ya existe.");
     });
             
        Producto producto = new Producto();
        producto.setNombre(productoRequestDto.getNombre());
        producto.setPrecio(productoRequestDto.getPrecio());
        producto.setStock(productoRequestDto.getStock());

        Producto savedProducto = productoRepository.save(producto);

        ProductoResponseDto responseDto = new ProductoResponseDto();
        responseDto.setId(savedProducto.getId());
        responseDto.setNombre(savedProducto.getNombre());
        responseDto.setPrecio(savedProducto.getPrecio());
        responseDto.setStock(savedProducto.getStock());

        return responseDto;
    }

    public ProductoResponseDto updateProducto(Long id, ProductoRequestDto productoRequestDto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado con id: " + id));

        producto.setNombre(productoRequestDto.getNombre());
        producto.setPrecio(productoRequestDto.getPrecio());
        producto.setStock(productoRequestDto.getStock());

        Producto updatedProducto = productoRepository.save(producto);

        ProductoResponseDto responseDto = new ProductoResponseDto();
        responseDto.setId(updatedProducto.getId());
        responseDto.setNombre(updatedProducto.getNombre());
        responseDto.setPrecio(updatedProducto.getPrecio());
        responseDto.setStock(updatedProducto.getStock());

        return responseDto;
    }

    public String deleteProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto no encontrado con id: " + id));

        productoRepository.delete(producto);
        return "Producto eliminado con éxito";
    }

    public List<ProductoResponseDto> getAllProductos() {
        List<Producto> productos = productoRepository.findAll();
        List<ProductoResponseDto> responseDtos = new ArrayList<>();

        for (Producto producto : productos) {
            ProductoResponseDto responseDto = new ProductoResponseDto();
            responseDto.setId(producto.getId());
            responseDto.setNombre(producto.getNombre());
            responseDto.setPrecio(producto.getPrecio());
            responseDto.setStock(producto.getStock());
            responseDtos.add(responseDto);
        }

        return responseDtos;
    }

    public List<ProductoResponseDto> getProductosByCategoria(Long categoriaId) {
        List<Producto> productos = productoRepository.findByCategoriaId(categoriaId);
        List<ProductoResponseDto> responseDtos = new ArrayList<>();

        for (Producto producto : productos) {
            ProductoResponseDto responseDto = new ProductoResponseDto();
            responseDto.setId(producto.getId());
            responseDto.setNombre(producto.getNombre());
            responseDto.setPrecio(producto.getPrecio());
            responseDto.setStock(producto.getStock());
            responseDtos.add(responseDto);
        }

        return responseDtos;
    }

    public List<ProductoResponseDto> getProductosByNombre(String nombre) {
        List<Producto> productos = productoRepository.findByNombreContainingIgnoreCase(nombre);
        List<ProductoResponseDto> responseDtos = new ArrayList<>();

        for (Producto producto : productos) {
            ProductoResponseDto responseDto = new ProductoResponseDto();
            responseDto.setId(producto.getId());
            responseDto.setNombre(producto.getNombre());
            responseDto.setPrecio(producto.getPrecio());
            responseDto.setStock(producto.getStock());
            responseDtos.add(responseDto);
        }

        return responseDtos;
    }

    public List<ProductoResponseDto> getProductosConLimite(int limite) {
        List<Producto> productos = productoRepository.findAll().stream().limit(limite).toList();
        List<ProductoResponseDto> responseDtos = new ArrayList<>();

        for (Producto producto : productos) {
            ProductoResponseDto responseDto = new ProductoResponseDto();
            responseDto.setId(producto.getId());
            responseDto.setNombre(producto.getNombre());
            responseDto.setPrecio(producto.getPrecio());
            responseDto.setStock(producto.getStock());
            responseDtos.add(responseDto);
        }
        return responseDtos;
    }
}
