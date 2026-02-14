package com.example.demo.config;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FiltroJwtAuth  extends OncePerRequestFilter{
    private final IJwtService jwtService;
    public FiltroJwtAuth(IJwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try{
            String token = jwtService.extraerToken(request);
            if(token != null && jwtService.tokenValido(token)) {
                Claims claims = jwtService.obtenerClaims(token);
                jwtService.generarAutenticacion(claims);
            }else{
                SecurityContextHolder.clearContext();
            }
            filterChain.doFilter(request, response);
            
        }catch(JwtException e){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.sendError(HttpServletResponse.SC_FORBIDDEN, 
					e.getMessage());
        }
    }

}
