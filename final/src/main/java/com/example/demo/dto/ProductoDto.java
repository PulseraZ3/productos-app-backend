package com.example.demo.dto;

import java.time.LocalDate;


public class ProductoDto {
    private Integer idproducto;
    private String nombre;
    private String descripcion;
    private float precio;
    private Integer stock;
    private float peso;
    private LocalDate fvencimiento;
    private boolean estado;
    private Integer id_usuario;
    private Integer idcategoria;


    public ProductoDto(Integer idproducto, String nombre, String descripcion, float precio, Integer stock, float peso,
            LocalDate fvencimiento, boolean estado, Integer id_usuario, Integer idcategoria) {
        this.idproducto = idproducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.peso = peso;
        this.fvencimiento = fvencimiento;
        this.estado = estado;
        this.id_usuario = id_usuario;
        this.idcategoria = idcategoria;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
    }

    public Integer getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Integer idproducto) {
        this.idproducto = idproducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public LocalDate getFvencimiento() {
        return fvencimiento;
    }

    public void setFvencimiento(LocalDate fvencimiento) {
        this.fvencimiento = fvencimiento;
    }


    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }


}
