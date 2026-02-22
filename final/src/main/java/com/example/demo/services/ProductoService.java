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
import com.example.demo.model.Categorias;
import com.example.demo.model.ProductoImagen;
import com.example.demo.model.Productos;
import com.example.demo.model.Usuario;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.repositories.ProductoImagenRepository;
import com.example.demo.repositories.ProductoRepository;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.util.ProductoMapper;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository repo;
    @Autowired
    private ProductoImagenRepository repoImagen;
    @Autowired
    private UsuarioRepository repoUsuario;
    @Autowired
    private CategoriaRepository repoCategoria;
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
    public ProductoDto buscarId(int idproducto) {
        Productos producto = repo.findById(idproducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        return ProductoMapper.toDto(producto);
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

    public Productos buscarEntidadPorId(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public ProductoDto editar(Integer id, ProductoDto dto, Usuario usuarioLogueado) {
        Optional<Productos> optProducto = repo.findById(id);
        if (optProducto.isPresent()) {
            Productos producto = optProducto.get();

            // Actualiza los demás campos del DTO
            ProductoMapper.updateEntity(producto, dto);

            // Forzamos que registradoPor sea el usuario logueado
            producto.setUsuario(usuarioLogueado);

            // 🔹 Convertimos el idCategoria del DTO a una entidad Categorias existente
            if (dto.getIdcategoria() != null) {
                Categorias categoria = repoCategoria.findById(dto.getIdcategoria())
                        .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
                producto.setCategoria(categoria);
            }

            Productos actualizados = repo.save(producto);
            return ProductoMapper.toDto(actualizados);
        }
        return null;
    }

}
