package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.DetalleFactura;

public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura,Integer>{

}
