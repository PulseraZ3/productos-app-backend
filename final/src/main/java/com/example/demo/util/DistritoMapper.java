package com.example.demo.util;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.dto.DistritoDto;
import com.example.demo.model.Distritos;

public class DistritoMapper {
    public static DistritoDto toDto(Distritos distrito) {
		return new DistritoDto(
				distrito.getIdDistrito(),
				distrito.getNombre());
	}

    public static List<DistritoDto> toDtoList(
        List<Distritos> distritos){
            return distritos.stream()
            .map(DistritoMapper::toDto)
            .collect(Collectors.toList());
    }

    public static Optional<DistritoDto> toDtoOptional(
			Distritos distrito) {
		return Optional.ofNullable(distrito)
				.map(DistritoMapper::toDto);
	}

    public static Distritos toEntity(DistritoDto dto){
		Distritos distritos = new Distritos();
		updateEntity(distritos, dto);
		return distritos;
	}
	public static void updateEntity(Distritos distritos,DistritoDto dto)
	{
		Distritos distrito = new Distritos();
		distrito.setNombre(dto.getNombre());
	}
}
