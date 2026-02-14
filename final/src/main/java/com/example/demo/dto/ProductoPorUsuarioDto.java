package com.example.demo.dto;

public class ProductoPorUsuarioDto {
    private String username;
    private Long cantidad;

    public ProductoPorUsuarioDto(String username, Long cantidad) {
        this.username = username;
        this.cantidad = cantidad;
    }

    public String getId_usuario() {
        return username;
    }

    public void setId_usuario(String username) {
        this.username = username;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

}
