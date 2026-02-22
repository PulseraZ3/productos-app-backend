package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CategoriaDto;
import com.example.demo.model.Categorias;
import com.example.demo.repositories.CategoriaRepository;
import com.example.demo.util.CategoriaMapper;

@Service
public class CategoriaService implements ICategoriaService {

    private final CategoriaRepository repo;

	public CategoriaService(CategoriaRepository repo) {
		this.repo =repo;
	}

    @Override
    public List<CategoriaDto> listarTodo() {
        return CategoriaMapper.toDtoList(
				repo.findAll());
    }

    @Override
    public CategoriaDto guardar(CategoriaDto dto) {
        Categorias entity = CategoriaMapper.toEntity(dto);
        Categorias save =repo.save(entity);
        return CategoriaMapper.toDto(save); 
        
    }

    @Override
    public  CategoriaDto  buscarId(int id) {
        return CategoriaMapper.toDto(repo.findById(id).orElse(null));
        
    }


    public CategoriaDto editar(Integer id, CategoriaDto dto){
        Optional<Categorias> optCategoria = repo.findById(id);
        if(optCategoria.isPresent()){
            Categorias categorias = optCategoria.get();
            CategoriaMapper.updateEntity(categorias, dto);
            Categorias actualizados = repo.save(categorias);
            return CategoriaMapper.toDto(actualizados);
        }
        return null;
    }
    

}
