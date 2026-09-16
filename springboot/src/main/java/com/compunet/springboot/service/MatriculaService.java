package com.compunet.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.compunet.springboot.model.EstudianteCurso;
import com.compunet.springboot.repository.EstudianteCursoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class MatriculaService {

    private final EstudianteCursoRepository repository;

    public List<EstudianteCurso> findAll() {
        return repository.findAll();
    }

    // Ejercicio 12: Verificar matrícula en tabla intermedia
    public boolean estaMatriculado(Long estudianteId, Long cursoId) {
        return repository.existsByEstudiante_IdAndCurso_Id(estudianteId, cursoId);
    }

}

