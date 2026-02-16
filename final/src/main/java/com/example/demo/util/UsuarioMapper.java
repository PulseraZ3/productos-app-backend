package com.example.demo.util;

import java.util.List;

import com.example.demo.dto.RolRequestDto;
import com.example.demo.model.Rol;

public class UsuarioMapper {
    
    public static RolRequestDto toDto(Rol rol){
        return RolRequestDto.builder()
                .idRol(rol.getIdRol())
                .nombre(rol.getNombre())
                .build();
    }
    public static List<RolRequestDto> toDtoList(List<Rol> roles){
        return roles.stream()
        .map(UsuarioMapper::toDto)
        .toList();
    }

}
