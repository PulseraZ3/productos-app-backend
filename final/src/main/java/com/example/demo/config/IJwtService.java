package com.example.demo.config;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.example.demo.model.Usuario;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

public interface IJwtService {
    String generarToken(Usuario usuario,
			List<GrantedAuthority> authorities);
	Claims obtenerClaims(String token);
	boolean tokenValido(String token);
	String extraerToken(HttpServletRequest request);
	void generarAutenticacion(Claims claims);
}
