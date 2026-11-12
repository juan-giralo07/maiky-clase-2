package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CategoriaRequestDto;
import com.example.demo.dto.CategoriaResponseDto;
import com.example.demo.entity.Categoria;
import com.example.demo.exception.RecursoNoEncontradoException;
import com.example.demo.exception.RecursoYaExistenteException;
import com.example.demo.repository.CategoriaRepository;

@Service 
public class CategoriaService {
    
    private final CategoriaRepository categoriaRepository;

    //constructor del repo
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaResponseDto getCategoriaById(Long id){

       
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoria no encontrada con id: " + id));

        CategoriaResponseDto categoriaResponseDto = new CategoriaResponseDto();
        categoriaResponseDto.setId(categoria.getId());
        categoriaResponseDto.setNombre(categoria.getNombre());
        categoriaResponseDto.setDescripcion(categoria.getDescripcion());
        return categoriaResponseDto;
    }

    public CategoriaResponseDto createCategoria(CategoriaRequestDto categoriaRequestDto) {
        
       
        categoriaRepository.findByNombre(categoriaRequestDto.getNombre())
        .ifPresent((categoria) -> {
            throw new RecursoYaExistenteException("La categoria con nombre " + categoriaRequestDto.getNombre() + " ya existe.");
        });

        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaRequestDto.getNombre());
        categoria.setDescripcion(categoriaRequestDto.getDescripcion());

        Categoria savedCategoria = categoriaRepository.save(categoria);

        CategoriaResponseDto responseDto = new CategoriaResponseDto();
        responseDto.setId(savedCategoria.getId());
        responseDto.setNombre(savedCategoria.getNombre());
        responseDto.setDescripcion(savedCategoria.getDescripcion());

        return responseDto;
    }   

    public String deleteCategoria(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoria no encontrada con id: " + id));

        categoriaRepository.delete(categoria);
        return "Categoria eliminada con éxito";
    }

    public CategoriaResponseDto updateCategoria(Long id, CategoriaRequestDto categoriaRequestDto) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoria no encontrada con id: " + id));

        categoria.setNombre(categoriaRequestDto.getNombre());
        categoria.setDescripcion(categoriaRequestDto.getDescripcion());

        Categoria updatedCategoria = categoriaRepository.save(categoria);

        CategoriaResponseDto responseDto = new CategoriaResponseDto();
        responseDto.setId(updatedCategoria.getId());
        responseDto.setNombre(updatedCategoria.getNombre());
        responseDto.setDescripcion(updatedCategoria.getDescripcion());

        return responseDto;
    }

    public List<CategoriaResponseDto> getAllCategorias(){
        List<Categoria> categorias = categoriaRepository.findAll();
        List<CategoriaResponseDto> categoriaResponseDtos = categorias.stream().map(categoria -> {
            CategoriaResponseDto responseDto = new CategoriaResponseDto();
            responseDto.setId(categoria.getId());
            responseDto.setNombre(categoria.getNombre());
            responseDto.setDescripcion(categoria.getDescripcion());
            return responseDto;
        }).toList();

        return categoriaResponseDtos;
    }
    
}
