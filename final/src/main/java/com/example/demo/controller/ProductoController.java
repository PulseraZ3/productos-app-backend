package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.GenericResponseDto;
import com.example.demo.dto.ProductoDto;
import com.example.demo.dto.ProductoPorCategoriaDto;
import com.example.demo.dto.ProductoPorUsuarioDto;
import com.example.demo.services.ProductoService;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/productos")
@PreAuthorize("hasRole('ADMIN')")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<GenericResponseDto<List<ProductoDto>>> getAllProductos() {
        List<ProductoDto> dto = productoService.listarTodo();
        if (dto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        GenericResponseDto<List<ProductoDto>> response = new GenericResponseDto<>();
        response.setResponse(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/categoria")
    public ResponseEntity<GenericResponseDto<List<ProductoPorCategoriaDto>>> getProductosByCategoria() {
        List<ProductoPorCategoriaDto> dto = productoService.listarPorCategoria();
        if (dto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        GenericResponseDto<List<ProductoPorCategoriaDto>> response = new GenericResponseDto<>();
        response.setResponse(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/usuario")
    public ResponseEntity<GenericResponseDto<List<ProductoPorUsuarioDto>>> getProductosByUsuario() {
        List<ProductoPorUsuarioDto> dto = productoService.listarPorUsuario();
        if (dto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        GenericResponseDto<List<ProductoPorUsuarioDto>> response = new GenericResponseDto<>();
        response.setResponse(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{estado}")
    public ResponseEntity<GenericResponseDto<List<ProductoDto>>> getEstadoProductos(@PathVariable boolean estado) {
        List<ProductoDto> dto = productoService.listarEstado(estado);
        if (dto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        GenericResponseDto<List<ProductoDto>> response = new GenericResponseDto<>();
        response.setResponse(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductoDto> createProducto(@RequestBody ProductoDto dto) {
        ProductoDto nuevo = productoService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ProductoDto> actualizarProducto(@PathVariable Integer id, @RequestBody ProductoDto dto) {
        ProductoDto actualiazado = productoService.editar(id, dto);
        if (actualiazado != null) {
            return ResponseEntity.ok(actualiazado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PutMapping("/cambiarEstado/{id}")
    public ResponseEntity<String> cambiarEstado(@PathVariable Integer id) {
        boolean estadoCambiado = productoService.cambiarEstadoProducto(id);
        if (estadoCambiado) {
            return ResponseEntity.ok("Estado del producto cambiado exitosamente.");
        } else {
            return ResponseEntity.status(404).body("Producto no encontrado o no se pudo cambiar el estado.");
        }
    }

}