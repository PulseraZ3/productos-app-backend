package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProductoDto;
import com.example.demo.dto.ProductoPorCategoriaDto;
import com.example.demo.dto.ProductoPorUsuarioDto;
import com.example.demo.model.Productos;
import com.example.demo.repositories.ProductoRepository;
import com.example.demo.util.ProductoMapper;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository repo;

    @Override
    public List<ProductoDto> listarTodo() {
        return ProductoMapper.toDoList(
                repo.findAll());
    }

    @Override
    public List<ProductoDto> listarEstado(boolean estado) {
        return ProductoMapper.toDoList(
                repo.findByEstado(estado));
    }

    @Override
    public List<ProductoPorCategoriaDto> listarPorCategoria() {
        return repo.contarProductosPorCategoria();
    }
    
    public List<ProductoPorUsuarioDto> listarPorUsuario() {
        return repo.contarProductosPorUsuario();
    }

    @Override
    public ProductoDto guardar(ProductoDto dto) {
        Productos entity = ProductoMapper.toEntity(dto);
        Productos save = repo.save(entity);
        return ProductoMapper.toDto(save);
    }

    @Override
    public Productos buscarId(int idproducto) {
        return repo.findById(idproducto).orElse(null);
    }
    public List<ProductoDto> productosPorCategoria(Integer idcategoria) {
        return ProductoMapper.toDoList(repo.findByCategoriaIdcategoria(idcategoria));
    }
    @Override
    public boolean cambiarEstadoProducto(Integer idProducto) {
        return repo.findById(idProducto)
                .map(producto -> {
                    producto.setEstado(!producto.isEstado());
                    repo.save(producto);
                    return true;
                })
                .orElse(false);
    }

    public ProductoDto editar(Integer id, ProductoDto dto) {
        Optional<Productos> optProducto = repo.findById(id);
        if (optProducto.isPresent()) {
            Productos producto = optProducto.get();
            ProductoMapper.updateEntity(producto, dto);
            Productos actualizados = repo.save(producto);
            return ProductoMapper.toDto(actualizados);
        }
        return null;
    }

}
