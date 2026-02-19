package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Factura;
import com.example.demo.model.Usuario;

public interface FacturaRepository extends JpaRepository<Factura, Integer> {
    List<Factura> findByUsuario_IdUsuario(Integer idUsuario);

    List<Factura> findByUsuario(Usuario usuario);
}
