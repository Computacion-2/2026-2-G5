package com.compunet.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compunet.springboot.model.EstudianteCurso;
import com.compunet.springboot.model.EstudianteCursoId;

@Repository
public interface EstudianteCursoRepository extends JpaRepository<EstudianteCurso, EstudianteCursoId> {

    // Ejercicio 12: Comprobar si existe un registro de matrícula para un estudianteId y un cursoId dados en EstudianteCurso
    boolean existsByEstudiante_IdAndCurso_Id(Long estudianteId, Long cursoId);

}

