package com.example.demo.dto;


public class CategoriaDto {
    private Integer idcategoria;
    private String nombre;
    private String descripcion;

    public CategoriaDto(Integer idcategoria, String nombre, String descripcion) {
        this.idcategoria = idcategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;

    }
    public Integer getIdcategoria() {
        return idcategoria;
    }
    public void setIdcategoria(Integer idcategoria) {
        this.idcategoria = idcategoria;
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
    

    
}
