package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.ProductoDto;
import com.example.demo.dto.ProductoPorCategoriaDto;
import com.example.demo.dto.ProductoPorUsuarioDto;
import com.example.demo.model.Productos;

public interface IProductoService {

    List<ProductoDto> listarTodo();
    List<ProductoDto> listarEstado(boolean estado);
    ProductoDto guardar(ProductoDto dto);
    Productos buscarId(int idproducto);
    boolean cambiarEstadoProducto(Integer idProducto);
    List<ProductoPorCategoriaDto> listarPorCategoria();
    List<ProductoPorUsuarioDto> listarPorUsuario();
}
