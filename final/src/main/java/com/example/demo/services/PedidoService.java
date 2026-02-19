package com.example.demo.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.SecurityUtils;
import com.example.demo.dto.DetallePedidoDto;
import com.example.demo.dto.ItemPedidoDto;
import com.example.demo.dto.PedidoRequest;
import com.example.demo.dto.PedidoResponseDto;
import com.example.demo.model.DetalleFactura;
import com.example.demo.model.Factura;
import com.example.demo.model.Productos;
import com.example.demo.model.Usuario;
import com.example.demo.repositories.FacturaRepository;
import com.example.demo.repositories.ProductoRepository;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.util.PedidoMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PedidoService {

    private final UsuarioRepository usuarioRepository;
    private final FacturaRepository facturaRepository;
    private final ProductoRepository productoRepository;
    private final PedidoMapper pedidoMapper;

    public PedidoResponseDto crearPedido(PedidoRequest request) {

        Usuario usuario = obtenerUsuarioLogueado();

        Factura factura = new Factura();
        factura.setFecha(LocalDate.now());
        factura.setUsuario(usuario);
        factura.setMetodoPago(request.getMetodoPago());

        double total = 0;

        for (ItemPedidoDto item : request.getItems()) {

            Productos producto = productoRepository
                    .findById(item.getIdProducto())
                    .orElseThrow();

            DetalleFactura detalle = new DetalleFactura();

            detalle.setProducto(producto);
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario((double) producto.getPrecio());

            double subtotal = producto.getPrecio() * item.getCantidad();
            detalle.setSubtotal(subtotal);

            total += subtotal;

            factura.agregarDetalle(detalle);
        }

        factura.setTotal(total);

        Factura guardada = facturaRepository.save(factura);

        return pedidoMapper.convertirFacturaADto(guardada);
    }
    private Usuario obtenerUsuarioLogueado() {

        String username = SecurityUtils.obtenerUsername();

        return usuarioRepository.findByUsername(username);
    }

    public List<PedidoResponseDto> obtenerPedidosUsuario() {

    Usuario usuario = obtenerUsuarioLogueado();

    List<Factura> facturas =
            facturaRepository.findByUsuario(usuario);

    return facturas.stream()
            .map(pedidoMapper::convertirFacturaADto)
            .toList();
}
}
