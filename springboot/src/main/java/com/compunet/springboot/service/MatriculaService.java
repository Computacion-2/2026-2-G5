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

    // Ejercicio 3 preparcial - Parte A: Verificar si existe estudiante activo con profesor y departamento
    public boolean existeEstudianteActivoEnCursoProfesorYDepartamento(String departamentoCurso, Long profesorId) {
        return repository.existsByEstudiante_ActiveTrueAndCurso_DepartamentoIgnoreCaseAndCurso_Profesor_Id(departamentoCurso, profesorId);
    }

    // Ejercicio 3 preparcial - Parte B: Contar total de matrículas por subcadena de nombre y rango de créditos
    public long contarMatriculasPorNombreCursoYCreditos(String subcadenaNombre, int minCreditos, int maxCreditos) {
        return repository.countByCurso_NombreIgnoreCaseContainingAndCurso_CreditosBetween(subcadenaNombre, minCreditos, maxCreditos);
    }

}

