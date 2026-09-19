package com.compunet.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compunet.springboot.model.Profesor;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    
    public List<Profesor> findByDepartamento(String depto);

    // Ejercicio 3: Obtener los profesores activos (active = true) que pertenezcan a un departamento específico, sin distinguir mayúsculas de minúsculas
    List<Profesor> findByDepartamentoIgnoreCaseAndActiveTrue(String depto);

    // Ejercicio 6: Obtener los profesores de una especialidad dada (sin distinguir mayúsculas) ordenados alfabéticamente por su apellido de forma ascendente
    List<Profesor> findByEspecialidadIgnoreCaseOrderByApellidoAsc(String especialidad);

    // Ejercicio 1 preparcial: Reporte docente por créditos dictados y departamento
    List<Profesor> findDistinctByActiveTrueAndDepartamentoIgnoreCaseAndCursos_CreditosGreaterThanEqualOrderByApellidoAscNombreAsc(
        String departamento,
        int minCreditos
    );
}

