package com.compunet.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compunet.springboot.model.EstudianteCurso;
import com.compunet.springboot.model.EstudianteCursoId;

@Repository
public interface EstudianteCursoRepository extends JpaRepository<EstudianteCurso, EstudianteCursoId> {

    // Ejercicio 12: Comprobar si existe un registro de matrícula para un estudianteId y un cursoId dados en EstudianteCurso
    boolean existsByEstudiante_IdAndCurso_Id(Long estudianteId, Long cursoId);

    // Ejercicio 3 preparcial - Parte A: Validación booleana de estudiante activo con profesor específico y departamento de curso
    boolean existsByEstudiante_ActiveTrueAndCurso_DepartamentoIgnoreCaseAndCurso_Profesor_Id(String departamentoCurso, Long profesorId);

    // Ejercicio 3 preparcial - Parte B: Conteo de matrículas por fragmento de nombre de curso y rango inclusivo de créditos
    long countByCurso_NombreIgnoreCaseContainingAndCurso_CreditosBetween(String subcadenaNombre, int minCreditos, int maxCreditos);

}

