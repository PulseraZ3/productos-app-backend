package com.example.demo.config;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    private SecurityUtils() {
        // Constructor privado para evitar instanciación
    }

    public static String obtenerUsername(){
        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();
                if(auth == null || !auth.isAuthenticated()){
                    return null;
                }
                return auth.getName();
    }
}

