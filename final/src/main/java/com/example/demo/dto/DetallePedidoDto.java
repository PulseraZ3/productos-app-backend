package com.example.demo.dto;

import lombok.Data;

@Data
public class DetallePedidoDto {

    private String producto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
}
