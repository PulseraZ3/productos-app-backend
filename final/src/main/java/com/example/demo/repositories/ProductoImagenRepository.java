package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ProductoImagen;

public interface ProductoImagenRepository extends JpaRepository<ProductoImagen, Integer> {
    List<ProductoImagen> findByProductoIdproducto(int productoId);
}
