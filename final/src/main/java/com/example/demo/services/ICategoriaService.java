package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.CategoriaDto;
import com.example.demo.model.Categorias;

public interface ICategoriaService {
    List<CategoriaDto> listarTodo();
    CategoriaDto guardar(CategoriaDto dto);
    Categorias buscarId(int idCategoria);
    CategoriaDto editar(Integer id, CategoriaDto dto);
}
