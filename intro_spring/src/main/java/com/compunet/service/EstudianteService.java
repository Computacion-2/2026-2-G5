package com.compunet.service;

import java.util.List;

import com.compunet.model.Estudiante;

public interface EstudianteService {

    public List<Estudiante> listarEstudiantes();
    void registrarEstudiante(Estudiante estudiante);
    
}
