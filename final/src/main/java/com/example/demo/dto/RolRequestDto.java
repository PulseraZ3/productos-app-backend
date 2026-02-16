package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RolRequestDto {
    private Integer idRol;
    private String nombre;
}
