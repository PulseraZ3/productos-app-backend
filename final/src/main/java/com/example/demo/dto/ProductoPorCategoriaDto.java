package com.example.demo.dto;

public class ProductoPorCategoriaDto {
    private String categoria;
    private Long cantidad;

    public ProductoPorCategoriaDto(String categoria, Long cantidad) {
        this.categoria = categoria;
        this.cantidad = cantidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }
}
