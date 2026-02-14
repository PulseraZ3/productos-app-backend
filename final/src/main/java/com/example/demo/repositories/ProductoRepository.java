package com.example.demo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.ProductoPorCategoriaDto;
import com.example.demo.dto.ProductoPorUsuarioDto;
import com.example.demo.model.Productos;
import com.example.demo.model.Usuario;

@Repository
public interface ProductoRepository extends JpaRepository<Productos, Integer> {

     List<Productos> findByCategoriaIdcategoria(Integer idcategoria);

     List<Productos> findByEstado(boolean estado);

     Optional<Productos> findByIdproductoAndUsuario(Integer idproducto, Usuario usuario);

     @Query("SELECT new com.example.demo.dto.ProductoPorCategoriaDto(p.categoria.nombre, COUNT(p)) " +
               "FROM Productos p GROUP BY p.categoria.nombre")
     List<ProductoPorCategoriaDto> contarProductosPorCategoria();

     @Query("SELECT new com.example.demo.dto.ProductoPorUsuarioDto(p.usuario.username, COUNT(p)) " +
               "FROM Productos p GROUP BY p.usuario.username")
     List<ProductoPorUsuarioDto> contarProductosPorUsuario();
}
