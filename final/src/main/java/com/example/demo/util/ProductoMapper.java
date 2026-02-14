package com.example.demo.util;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.dto.ProductoDto;
import com.example.demo.model.Categorias;
import com.example.demo.model.Productos;
import com.example.demo.model.Usuario;

public class ProductoMapper {

    public static ProductoDto toDto(Productos producto) {
        return new ProductoDto(
                producto.getIdproducto(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getPrecio(),
                producto.getStock(),
                producto.getPeso(),
                producto.getFvencimiento(),
                producto.isEstado(),
                producto.getCategoria() != null ? producto.getCategoria().getIdcategoria() : null, // 👈 solo id
                producto.getUsuario() != null ? producto.getUsuario().getIdUsuario() : null // 👈 solo id
        );
    }

    public static List<ProductoDto> toDoList(List<Productos> productos) {
        return productos.stream()
                .map(ProductoMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Optional<ProductoDto> toDtoOptional(Productos producto) {
        return Optional.ofNullable(producto)
                .map(ProductoMapper::toDto);
    }

    public static Productos toEntity(ProductoDto dto) {
        Productos producto = new Productos();
        updateEntity(producto, dto);
        return producto;
    }

    public static void updateEntity(Productos producto, ProductoDto dto) {
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setPeso(dto.getPeso());
        producto.setFvencimiento(dto.getFvencimiento());
        producto.setEstado(dto.isEstado());

        // Usuario
        if (dto.getId_usuario() != null) {
            Usuario u = new Usuario();
            u.setIdUsuario(dto.getId_usuario());
            producto.setUsuario(u);
        }

        // Categoría
        if (dto.getIdcategoria() != null) {
            Categorias cat = new Categorias();
            cat.setIdcategoria(dto.getIdcategoria());
            producto.setCategoria(cat);
        }
    }

}
