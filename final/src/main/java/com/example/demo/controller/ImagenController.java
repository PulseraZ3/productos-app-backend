package com.example.demo.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.ProductoImagen;
import com.example.demo.services.ImagenService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/imagenes")
public class ImagenController {

    private final String uploadDir = "/uploads"; // la carpeta donde se guardan tus imágenes
    private final ImagenService productoImagenService;

    public ImagenController(ImagenService productoImagenService) {
        this.productoImagenService = productoImagenService;
    }

    @GetMapping("/{id}/imagenes")
    public ResponseEntity<List<String>> getImagenesProducto(@PathVariable int id) {
        List<ProductoImagen> imagenes = productoImagenService.getImagenesByProductoId(id);

        // Suponiendo que tu backend está en localhost:8080
        List<String> urls = imagenes.stream()
                .map(img -> "http://localhost:8080/api/productos/" + id + "/imagenes" + img.getImagen_url())
                .collect(Collectors.toList());

        return ResponseEntity.ok(urls);
    }

    @GetMapping("/{id}/imagenes/{filename:.+}")
    public ResponseEntity<Resource> getImagen(
            @PathVariable int id,
            @PathVariable String filename) throws MalformedURLException {

        // Ruta física donde guardaste las imágenes
        Path filePath = Paths.get("uploads").resolve(filename); // ajusta la carpeta
        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        // Devuelve la imagen con su tipo correcto
        return ResponseEntity.ok()
                .contentType(MediaTypeFactory.getMediaType(resource)
                        .orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(resource);
    }
}
