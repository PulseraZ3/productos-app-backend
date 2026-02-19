package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "productos")
public class Productos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproducto")
    private Integer idproducto;
    private String nombre;
    private String descripcion;
    private float precio;
    private Integer stock;
    private float peso;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDate fvencimiento;

    @ManyToOne
    @JoinColumn(name = "idcategoria")
    private Categorias categoria;
    private boolean estado;

    @ManyToOne
    @JoinColumn(name = "registrado_por", nullable = false)
    private Usuario usuario;

}
