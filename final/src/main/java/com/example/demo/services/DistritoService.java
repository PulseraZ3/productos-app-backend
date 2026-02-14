package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.DistritoDto;
import com.example.demo.repositories.DistritoRepository;
import com.example.demo.util.DistritoMapper;

@Service
public class DistritoService implements IDistritoService{
    private final DistritoRepository repo;

    public DistritoService(DistritoRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<DistritoDto> listarTodo() {
        return DistritoMapper.toDtoList(repo.findAll());
    }
}
