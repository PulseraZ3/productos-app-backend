package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RolRequestDto;
import com.example.demo.services.UsuarioService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RequestMapping("/api/v1/rol")
@RestController
public class RolController {
    private final UsuarioService usuarioService;

    public RolController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<RolRequestDto> listarRoles() {
        return usuarioService.listarRoles();
    }

}
