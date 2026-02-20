package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.ProductoImagen;
import com.example.demo.repositories.ProductoImagenRepository;

@Service
public class ImagenService {

    private final ProductoImagenRepository imagenRepository;

    public ImagenService(ProductoImagenRepository imagenRepository) {
        this.imagenRepository = imagenRepository;
    }

    public List<ProductoImagen> getImagenesByProductoId(int productoId) {
        return imagenRepository.findByProductoIdproducto(productoId);
    }

}