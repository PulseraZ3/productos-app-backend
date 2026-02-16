package com.example.demo.services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.RolRequestDto;
import com.example.demo.model.Distritos;
import com.example.demo.model.Rol;
import com.example.demo.model.Usuario;
import com.example.demo.repositories.DistritoRepository;
import com.example.demo.repositories.RolRepository;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.util.UsuarioMapper;
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final DistritoRepository distritoRepository;
    private PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository, DistritoRepository distritoRepository,@Lazy PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.distritoRepository = distritoRepository;
        this.passwordEncoder = passwordEncoder;
    } 

    public Usuario obtenerUsuarioPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }
    public List<RolRequestDto> listarRoles(){
        return UsuarioMapper.toDtoList(rolRepository.findAll());
    }
    
    public Usuario registrar(RegisterRequest request){
        Rol rol = rolRepository.findById(request.getIdRol())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        Distritos distrito = distritoRepository.findById(request.getIdDistrito())
                .orElseThrow(() -> new RuntimeException("Distrito no encontrado"));

        Usuario usuario = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .activo(true)
                .rol(rol)
                .distritos(distrito)
                .build();
                return usuarioRepository.save(usuario);
    }


   
}
