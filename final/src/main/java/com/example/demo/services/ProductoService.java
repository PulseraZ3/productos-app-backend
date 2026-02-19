package com.example.demo.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ProductoDto;
import com.example.demo.dto.ProductoPorCategoriaDto;
import com.example.demo.dto.ProductoPorUsuarioDto;
import com.example.demo.model.ProductoImagen;
import com.example.demo.model.Productos;
import com.example.demo.repositories.ProductoImagenRepository;
import com.example.demo.repositories.ProductoRepository;
import com.example.demo.util.ProductoMapper;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository repo;
    @Autowired
    private ProductoImagenRepository repoImagen;

    @Override
    public List<ProductoDto> listarTodo() {
        return ProductoMapper.toDoList(
                repo.findAll());
    }

    private final Path uploadDir = Paths.get("uploads").toAbsolutePath();

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

    public String guardarImagen(MultipartFile file) throws IOException {
        if (!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        // Nombre único
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();

        // Guardar archivo
        Path filePath = uploadDir.resolve(filename);
        Files.write(filePath, file.getBytes());

        // Retornar URL pública
        return "/uploads/" + filename;
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
