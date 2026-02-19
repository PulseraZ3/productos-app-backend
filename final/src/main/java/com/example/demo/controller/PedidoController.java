package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.PedidoRequest;
import com.example.demo.dto.PedidoResponseDto;
import com.example.demo.services.PedidoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponseDto> crearPedido(
            @RequestBody PedidoRequest request) {

        PedidoResponseDto response = pedidoService.crearPedido(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDto>> obtenerMisPedidos() {

        List<PedidoResponseDto> pedidos = pedidoService.obtenerPedidosUsuario();

        return ResponseEntity.ok(pedidos);
    }
}
