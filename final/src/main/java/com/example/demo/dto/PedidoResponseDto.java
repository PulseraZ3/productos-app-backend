package com.example.demo.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class PedidoResponseDto {

    private Integer idPedido;
    private Date fecha;
    private Double total;
    private String estado;
    private String nombreUsuario;
    private List<DetallePedidoDto> detalles;
}
