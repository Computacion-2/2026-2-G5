package com.compunet.service;

import java.util.List;

import com.compunet.model.Estudiante;
import com.compunet.repository.EstudianteRepository;

public class EstudianteServiceSetterImpl implements EstudianteService {

    private EstudianteRepository estudianteRepository;

    public EstudianteServiceSetterImpl(){

    };

    public void setEstudianteRepository(EstudianteRepository repository){
        this.estudianteRepository = repository;
    }

    @Override
    public List<Estudiante> listarEstudiantes() {
        System.out.println("Soy el Bean de inyección por setter");
        return estudianteRepository.obtenerTodos();
    }

    @Override
    public void registrarEstudiante(Estudiante estudiante) {
        
        estudianteRepository.registrarEstudiante(estudiante);
    }

    
    
}
