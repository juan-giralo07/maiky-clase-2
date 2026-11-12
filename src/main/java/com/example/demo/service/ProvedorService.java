package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ProvedorRequestDto;
import com.example.demo.dto.ProvedorResponseDto;
import com.example.demo.entity.Provedor;
import com.example.demo.exception.RecursoNoEncontradoException;
import com.example.demo.exception.RecursoYaExistenteException;
import com.example.demo.repository.ProvedorRepository;

@Service 
public class ProvedorService {
    
    private final ProvedorRepository provedorRepository;

    public ProvedorService(ProvedorRepository provedorRepository) {
        this.provedorRepository = provedorRepository;
    }

    public ProvedorResponseDto getProvedorById(Long id){

        Provedor provedor = provedorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Provedor no encontrado con id: " + id));

        ProvedorResponseDto provedorResponseDto = new ProvedorResponseDto();
        provedorResponseDto.setId(provedor.getId());
        provedorResponseDto.setNombre(provedor.getNombre());
        provedorResponseDto.setTelefono(provedor.getTelefono());
        provedorResponseDto.setCorreo(provedor.getCorreo());
        return provedorResponseDto;
    }

    public ProvedorResponseDto create (ProvedorRequestDto requestDto){

        provedorRepository.findByEmail(requestDto.getCorreo())
        .ifPresent((provedor) -> {
            throw new RecursoYaExistenteException("provedor ya existe");
        });

        Provedor provedor = new Provedor();
        provedor.setNombre(requestDto.getNombre());
        provedor.setTelefono(requestDto.getTelefono());
        provedor.setCorreo(requestDto.getCorreo());

        provedor = provedorRepository.save(provedor);

        ProvedorResponseDto provedorResponseDto = new ProvedorResponseDto();
        provedorResponseDto.setId(provedor.getId());
        provedorResponseDto.setNombre(provedor.getNombre());
        provedorResponseDto.setTelefono(provedor.getTelefono());
        provedorResponseDto.setCorreo(provedor.getCorreo());
        return provedorResponseDto;
    }

    public ProvedorResponseDto update(Long id, ProvedorRequestDto requestDto) {
        Provedor provedor = provedorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Provedor no encontrado con id: " + id));

        provedor.setNombre(requestDto.getNombre());
        provedor.setTelefono(requestDto.getTelefono());
        provedor.setCorreo(requestDto.getCorreo());

        provedor = provedorRepository.save(provedor);

        ProvedorResponseDto provedorResponseDto = new ProvedorResponseDto();
        provedorResponseDto.setId(provedor.getId());
        provedorResponseDto.setNombre(provedor.getNombre());
        provedorResponseDto.setTelefono(provedor.getTelefono());
        provedorResponseDto.setCorreo(provedor.getCorreo());
        return provedorResponseDto;
    }

    public String delete(Long id) {
        Provedor provedor = provedorRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Provedor no encontrado con id: " + id));

        provedorRepository.delete(provedor);
        return "Provedor eliminado con éxito";
    }

    public List<ProvedorResponseDto> getAll(){

        List<Provedor> provedores = provedorRepository.findAll();
        List<ProvedorResponseDto> provedorResponseDtos = provedores.stream().map(provedor -> {
            ProvedorResponseDto provedorResponseDto = new ProvedorResponseDto();
            provedorResponseDto.setId(provedor.getId());
            provedorResponseDto.setNombre(provedor.getNombre());
            provedorResponseDto.setTelefono(provedor.getTelefono());
            provedorResponseDto.setCorreo(provedor.getCorreo());
            return provedorResponseDto;
        }).toList();
        return provedorResponseDtos;
    }
    
}