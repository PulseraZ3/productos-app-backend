package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Categorias;
@Repository
public interface CategoriaRepository extends JpaRepository<Categorias, Integer>{

}
