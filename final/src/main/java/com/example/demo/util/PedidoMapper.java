package com.example.demo.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.dto.DetallePedidoDto;
import com.example.demo.dto.PedidoResponseDto;
import com.example.demo.model.Factura;

@Component
public class PedidoMapper {

    public PedidoResponseDto convertirFacturaADto(Factura f) {

        PedidoResponseDto dto = new PedidoResponseDto();

        dto.setIdPedido(f.getIdFactura());
        dto.setFecha(java.sql.Date.valueOf(f.getFecha()));
        dto.setTotal(f.getTotal());
        dto.setEstado("GENERADO");
        dto.setNombreUsuario(f.getUsuario().getUsername());

        List<DetallePedidoDto> detalles = f.getDetalles()
                .stream()
                .map(d -> {

                    DetallePedidoDto det = new DetallePedidoDto();

                    det.setProducto(d.getProducto().getNombre());
                    det.setCantidad(d.getCantidad());
                    det.setPrecioUnitario(d.getPrecioUnitario());
                    det.setSubtotal(d.getSubtotal());

                    return det;
                }).toList();

        dto.setDetalles(detalles);

        return dto;
    }
}
