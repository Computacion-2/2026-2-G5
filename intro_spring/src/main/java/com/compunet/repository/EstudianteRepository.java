package com.compunet.repository;

import java.util.List;

import com.compunet.model.Estudiante;

public interface EstudianteRepository {
    
    List<Estudiante> obtenerTodos();
    void registrarEstudiante(Estudiante estudiante);
}
