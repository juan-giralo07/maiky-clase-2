package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ClienteRequestDto;
import com.example.demo.dto.ClienteResponseDto;

import com.example.demo.entity.Cliente;
import com.example.demo.exception.RecursoNoEncontradoException;
import com.example.demo.exception.RecursoYaExistenteException;
import com.example.demo.repository.ClienteRepository;

@Service 
public class ClienteService {
    
    private final ClienteRepository clienteRepository;

    ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

     public ClienteResponseDto getClienteById(Long id){

        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("cliente no encontrado con id: " + id));

        ClienteResponseDto responseDto = new ClienteResponseDto();
        responseDto.setDocumento(cliente.getDocumento());  
        responseDto.setId(cliente.getId());
        responseDto.setNombre(cliente.getNombre());
        responseDto.setTelefono(cliente.getTelefono());
        return responseDto;
       
    }

    public ClienteResponseDto create (ClienteRequestDto requestDto){

        clienteRepository.findByDocumento(requestDto.getDocumento())
        .ifPresent((cliente) -> {
            throw new RecursoYaExistenteException("cliente ya existe");
        });

        Cliente cliente = new Cliente();
        cliente.setDocumento(requestDto.getDocumento());
        cliente.setNombre(requestDto.getNombre());
        cliente.setTelefono(requestDto.getTelefono());
        
        clienteRepository.save(cliente);

        ClienteResponseDto responseDto = new ClienteResponseDto();
        responseDto.setDocumento(cliente.getDocumento());
        responseDto.setNombre(cliente.getNombre());
        responseDto.setTelefono(cliente.getTelefono());
        responseDto.setId(cliente.getId());

        return responseDto;
    }

    public ClienteResponseDto update(Long id, ClienteRequestDto requestDto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("clinete no encontrado con id: " + id));

        cliente.setNombre(requestDto.getNombre());
        cliente.setTelefono(requestDto.getTelefono());
        cliente.setDocumento(requestDto.getDocumento());

        clienteRepository.save(cliente);

        ClienteResponseDto responseDto = new ClienteResponseDto();
        responseDto.setDocumento(cliente.getDocumento());
        responseDto.setId(cliente.getId());
        responseDto.setTelefono(cliente.getTelefono());
        responseDto.setNombre(cliente.getNombre());

        return responseDto;

        
    }

    public String delete(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("cliente no encontrado con id: " + id));

        clienteRepository.delete(cliente);
        return "cliente eliminado con éxito";
    }

    public List<ClienteResponseDto> getAll(){
        List<Cliente> clientes = clienteRepository.findAll();
        List<ClienteResponseDto> clienteResponseDtos = clientes.stream().map(cliente -> {
            ClienteResponseDto clienteResponseDto = new ClienteResponseDto();
            clienteResponseDto.setDocumento(cliente.getDocumento());
            clienteResponseDto.setId(cliente.getId());
            clienteResponseDto.setNombre(cliente.getNombre());
            clienteResponseDto.setTelefono(cliente.getTelefono());
            return clienteResponseDto;
        }).toList();
        return clienteResponseDtos;
        
    }

    public ClienteResponseDto verPorDocumneto(String documento){

        Cliente cliente = clienteRepository.findByDocumento(documento)
        .orElseThrow(() -> new RecursoYaExistenteException("cliente no existe"));

        ClienteResponseDto responseDto = new ClienteResponseDto();
        responseDto.setDocumento(cliente.getDocumento());
        responseDto.setId(cliente.getId());
        responseDto.setNombre(cliente.getNombre());
        responseDto.setTelefono(cliente.getTelefono());

        return  responseDto;
    }
}

 