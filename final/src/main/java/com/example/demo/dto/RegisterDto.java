package com.example.demo.dto;

public class RegisterDto {
    private Integer idUsuario;
    private String nombre;
    private String correo;
    private String contraseña;
    private Integer idRol;
    private Integer idDistrito;

    public RegisterDto(Integer idUsuario, String nombre, String correo, String contraseña, Integer idRol,
            Integer idDistrito) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.idRol = idRol;
        this.idDistrito = idDistrito;
    }

    public RegisterDto() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public Integer getIdDistrito() {
        return idDistrito;
    }

    public void setIdDistrito(Integer idDistrito) {
        this.idDistrito = idDistrito;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

}
