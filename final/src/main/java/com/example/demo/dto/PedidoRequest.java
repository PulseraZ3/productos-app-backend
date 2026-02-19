package com.example.demo.dto;

import java.util.List;

import lombok.Data;

@Data
public class PedidoRequest {

    private List<ItemPedidoDto> items;
    private String metodoPago;
}
