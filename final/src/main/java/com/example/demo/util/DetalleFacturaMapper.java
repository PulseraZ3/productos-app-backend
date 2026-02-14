package com.example.demo.util;

import com.example.demo.dto.DetalleFacturaDto;
import com.example.demo.model.DetalleFactura;
import com.example.demo.model.Productos;

public class DetalleFacturaMapper {
    public static DetalleFacturaDto toDto(DetalleFactura detalle){
        DetalleFacturaDto dto = new DetalleFacturaDto();
        dto.setIdProducto(detalle.getProducto().getIdproducto());
        dto.setCantidad(detalle.getCantidad());
        dto.setPrecioUnitario(detalle.getPrecioUnitario());
        dto.setSubtotal(detalle.getSubtotal());
        return dto;
    }

     public static DetalleFactura toEntity(DetalleFacturaDto dto, Productos producto) {
        DetalleFactura detalle = new DetalleFactura();
        detalle.setProducto(producto);
        detalle.setCantidad(dto.getCantidad());
        detalle.setPrecioUnitario(dto.getPrecioUnitario());
        detalle.setSubtotal(dto.getSubtotal());
        return detalle;
    }
}
