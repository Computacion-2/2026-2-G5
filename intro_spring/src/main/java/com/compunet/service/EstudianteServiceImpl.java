package com.compunet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.compunet.model.Estudiante;
import com.compunet.repository.EstudianteRepository;
@Service
@Primary
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteServiceImpl (EstudianteRepository repository){

        this.estudianteRepository = repository;
    }

    @Override
    public List<Estudiante> listarEstudiantes() {
        System.out.println("Soy el Bean de inyección por constructor");
        return estudianteRepository.obtenerTodos();
    }

    @Override
    public void registrarEstudiante(Estudiante estudiante) {

        estudianteRepository.registrarEstudiante(estudiante);
    }
    
}
