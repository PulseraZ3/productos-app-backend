package com.example.demo.util;

import com.example.demo.dto.FacturaDto;
import com.example.demo.model.Factura;

import java.util.stream.Collectors;

public class FacturaMapper {

    public static FacturaDto toDto(Factura factura) {
        FacturaDto dto = new FacturaDto();
        dto.setIdFactura(factura.getIdFactura());
        dto.setFecha(factura.getFecha());
        dto.setCliente(factura.getCliente());
        dto.setMetodoPago(factura.getMetodoPago());
        dto.setTotal(factura.getTotal());
        if (factura.getDetalles() != null) {
            dto.setDetalles(
                    factura.getDetalles().stream()
                            .map(DetalleFacturaMapper::toDto)
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }

    public static Factura toEntity(FacturaDto dto) {
        Factura factura = new Factura();
        factura.setCliente(dto.getCliente());
        factura.setMetodoPago(dto.getMetodoPago());
        factura.setFecha(dto.getFecha());
        factura.setTotal(dto.getTotal());
        return factura;
    }
}
