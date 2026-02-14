package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UsuarioSecurity {
    private Integer idUsuario;
    private String username;
    private String rol;
    private String token;
    private String mensajeError;
}
