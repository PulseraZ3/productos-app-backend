package com.example.demo.util;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.dto.CategoriaDto;
import com.example.demo.model.Categorias;

public class CategoriaMapper {

	public static CategoriaDto toDto(Categorias categoria) {
		return new CategoriaDto(
				categoria.getIdcategoria(),
				categoria.getNombre(),
				categoria.getDescripcion());
	}

	public static List<CategoriaDto> toDtoList(
			List<Categorias> categories) {
		return categories.stream()
				.map(CategoriaMapper::toDto)
				.collect(Collectors.toList());
	}

	public static Optional<CategoriaDto> toDtoOptional(
			Categorias categoria) {
		return Optional.ofNullable(categoria)
				.map(CategoriaMapper::toDto);
	}

	public static Categorias toEntity(CategoriaDto dto){
		Categorias categorias = new Categorias();
		updateEntity(categorias, dto);
		return categorias;
	}
	public static void updateEntity(Categorias categorias,CategoriaDto dto)
	{
		Categorias categoria = new Categorias();
		categoria.setNombre(dto.getNombre());
		categoria.setDescripcion(dto.getDescripcion());
	}
}
