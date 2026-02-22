package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CategoriaDto;
import com.example.demo.dto.GenericResponseDto;
import com.example.demo.services.ICategoriaService;


@RestController
@RequestMapping("/api/categoria")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {
	private final ICategoriaService categoriaService;

	public CategoriaController(
			ICategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	@GetMapping
	public ResponseEntity<GenericResponseDto<List<CategoriaDto>>> getAllCategories() {
		List<CategoriaDto> dtos = categoriaService.listarTodo();
		if (dtos.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		GenericResponseDto<List<CategoriaDto>> response = new GenericResponseDto<>();
		response.setResponse(dtos);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<GenericResponseDto<CategoriaDto>> getCategoryById(@PathVariable Integer id) {
		CategoriaDto categoria = categoriaService.buscarId(id);
		if (categoria == null) {
			return ResponseEntity.notFound().build();
		}
		GenericResponseDto
		
		<CategoriaDto> response = new GenericResponseDto<>();
		response.setResponse(categoria);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<CategoriaDto> createCategoria(@RequestBody CategoriaDto dto) {
		CategoriaDto nuevo = categoriaService.guardar(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
	}
	
    @PostMapping("/{id}")
    public ResponseEntity<CategoriaDto> actualizarCategoria(@PathVariable Integer id, @RequestBody CategoriaDto dto) {
        CategoriaDto actualiazado = categoriaService.editar(id, dto);
        if (actualiazado != null) {
            return ResponseEntity.ok(actualiazado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
