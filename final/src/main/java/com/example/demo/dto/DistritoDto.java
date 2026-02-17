package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DistritoDto {
    
    @JsonProperty("iddistrito")
    private Integer id_distrito;
    private String nombre;

    public DistritoDto(Integer id_distrito, String nombre) {
        this.id_distrito = id_distrito;
        this.nombre = nombre;
    }

    public Integer getIddistrito() {
        return id_distrito;
    }

    public void setIddistrito(Integer id_distrito) {
        this.id_distrito = id_distrito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
