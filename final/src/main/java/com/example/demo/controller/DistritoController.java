package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DistritoDto;
import com.example.demo.dto.GenericResponseDto;
import com.example.demo.services.DistritoService;

@RestController
@RequestMapping("/api/v1/distrito")

public class DistritoController {
    private final DistritoService distritoService;

    public DistritoController(DistritoService distritoService) {
        this.distritoService = distritoService;
    }

    @GetMapping
    public ResponseEntity<GenericResponseDto<List<DistritoDto>>> listarDistritos() {

        List<DistritoDto> dto = distritoService.listarTodo();

        if (dto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        GenericResponseDto<List<DistritoDto>> response = new GenericResponseDto<>();
        response.setResponse(dto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
