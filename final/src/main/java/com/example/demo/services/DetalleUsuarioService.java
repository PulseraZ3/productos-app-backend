package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Rol;
import com.example.demo.model.Usuario;
@Service
public class DetalleUsuarioService implements UserDetailsService {

    private final UsuarioService usuarioService;

    public DetalleUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioService.obtenerUsuarioPorUsername(username);

        return obtenerUsuarioPorUsername(
                usuario,
                obtenerAutorizacionPorRol(usuario.getRol()));
    }

    public List<GrantedAuthority> obtenerAutorizacionPorRol(Rol rol) {

        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(
                "ROLE_" + rol.getNombre()));

        return authorities;
    }

    public UserDetails obtenerUsuarioPorUsername(Usuario usuario, List<GrantedAuthority> authorities) {
        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getActivo(), true, true, true,
                authorities);
    }

}
