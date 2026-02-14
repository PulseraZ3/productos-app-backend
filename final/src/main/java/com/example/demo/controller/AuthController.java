package com.example.demo.controller;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.IJwtService;
import com.example.demo.dto.Login;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.UsuarioSecurity;
import com.example.demo.model.Usuario;
import com.example.demo.services.DetalleUsuarioService;
import com.example.demo.services.UsuarioService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {

    private final UsuarioService usuarioService;
    private final DetalleUsuarioService detalleUsuarioService;
    private final IJwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthController(UsuarioService usuarioService, DetalleUsuarioService detalleUsuarioService,
            IJwtService jwtService, AuthenticationManager authenticationManager) {
        this.usuarioService = usuarioService;
        this.detalleUsuarioService = detalleUsuarioService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioSecurity> login(@RequestBody Login login) {
        UsuarioSecurity userSecurity = new UsuarioSecurity();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
            Usuario usuario = usuarioService.obtenerUsuarioPorUsername(login.getUsername());
            String token = jwtService.generarToken(usuario,
                    detalleUsuarioService.obtenerAutorizacionPorRol(usuario.getRol()));
            userSecurity.setIdUsuario(usuario.getIdUsuario());
            userSecurity.setUsername(usuario.getUsername());
            userSecurity.setToken(token);
            return ResponseEntity.ok(userSecurity);

        } catch (AuthenticationException e) {
            userSecurity.setMensajeError("Usuario y/o password incorrecto");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(userSecurity);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        {
            Usuario usuario = usuarioService.registrar(request);
            return ResponseEntity.ok(usuario);
        }
    }
}
