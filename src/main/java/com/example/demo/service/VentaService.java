package com.example.demo.service;

import com.example.demo.dto.VentaRequestDto;
import com.example.demo.dto.VentaResponse;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.DetalleVenta;
import com.example.demo.entity.Producto;
import com.example.demo.entity.Venta;
import com.example.demo.exception.RecursoNoEncontradoException;
import com.example.demo.exception.StockInsuficienteException;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.VentaRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;

    public VentaService(VentaRepository ventaRepository, 
                        ClienteRepository clienteRepository, 
                        ProductoRepository productoRepository) {
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
    }

    
    public VentaResponse registrarVenta(VentaRequestDto request) {
       
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado"));

        Venta venta = new Venta();
        venta.setCliente(cliente);
        venta.setFecha(LocalDateTime.now()); // Guardar con la fecha actual

        double totalVenta = 0.0;
        List<DetalleVenta> listaDetalles = new ArrayList<>();

       
        for (VentaRequestDto.DetallesVenta detalleDto : request.getDetalles()) {
            
          
            Producto producto = productoRepository.findById(detalleDto.id())
                    .orElseThrow(() -> new RecursoNoEncontradoException("Producto con ID " + detalleDto.id() + " no encontrado"));

          
            if (producto.getStock() < detalleDto.cantidad()) {
                throw new StockInsuficienteException("Stock insuficiente para el producto: " + producto.getNombre());
            }

           
            double precioUnitario = producto.getPrecio();

          
            double subtotal = detalleDto.cantidad() * precioUnitario;
            totalVenta += subtotal;

           
            producto.setStock(producto.getStock() - detalleDto.cantidad());
            productoRepository.save(producto);

           
            DetalleVenta detalleVenta = new DetalleVenta();
            detalleVenta.setProducto(producto);
            detalleVenta.setCantidad(detalleDto.cantidad());
            detalleVenta.setPrecioUnitario(precioUnitario);
            detalleVenta.setSubtotal(subtotal);
            detalleVenta.setVenta(venta); // Vincular con la venta principal

            listaDetalles.add(detalleVenta);
        }

        venta.setDetalles(listaDetalles);
        venta.setTotal(totalVenta);

       
        Venta ventaGuardada = ventaRepository.save(venta);

        VentaResponse response = new VentaResponse();
        response.setClienteId(ventaGuardada.getCliente().getId());
        response.setFecha(ventaGuardada.getFecha()); 
        response.setTotal(ventaGuardada.getTotal());
        response.setDetalles(ventaGuardada.getDetalles());

        return response;
    }

   
    public List<VentaResponse> listarTodas() {
        return ventaRepository.findAll().stream().map(venta -> {
            VentaResponse res = new VentaResponse();
            res.setClienteId(venta.getCliente().getId());
            res.setFecha(venta.getFecha());
            res.setTotal(venta.getTotal());
            res.setDetalles(venta.getDetalles());
            return res;
        }).toList();
    }

    
    public VentaResponse buscarPorId(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Venta no encontrada con ID: " + id));

        VentaResponse res = new VentaResponse();
        res.setClienteId(venta.getCliente().getId());
        res.setFecha(venta.getFecha());
        res.setTotal(venta.getTotal());
        res.setDetalles(venta.getDetalles());
        return res;
    }

   
    public List<VentaResponse> listarPorCliente(Long clienteId) {
        return ventaRepository.findByClienteId(clienteId).stream().map(venta -> {
            VentaResponse res = new VentaResponse();
            res.setClienteId(venta.getCliente().getId());
            res.setFecha(venta.getFecha());
            res.setTotal(venta.getTotal());
            res.setDetalles(venta.getDetalles());
            return res;
        }).toList();
    }

    
    public List<VentaResponse> listarPorRangoFechas(LocalDateTime inicio, LocalDateTime fin) {
        return ventaRepository.findByFechaBetween(inicio, fin).stream().map(venta -> {
            VentaResponse res = new VentaResponse();
            res.setClienteId(venta.getCliente().getId());
            res.setFecha(venta.getFecha());
            res.setTotal(venta.getTotal());
            res.setDetalles(venta.getDetalles());
            return res;
        }).toList();
    }

   
}