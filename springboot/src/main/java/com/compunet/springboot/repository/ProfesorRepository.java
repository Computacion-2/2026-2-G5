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

    // La decanatura necesita listar a los profesores activos (active = true) que pertenezcan a un departamento específico (sin distinguir
    // mayúsculas/minúsculas) y que se encuentren dictando asignaturas de alta exigencia académica (cursos con una cantidad de créditos mayor o igual a
    // un valor mínimo).
    // Para la entrega del reporte:
    // Cada profesor debe aparecer únicamente una vez (sin duplicados causados por dictar múltiples cursos).
    // La lista debe ordenarse alfabéticamente de forma ascendente por el apellido del profesor y, en caso de empate, por su nombre.

    List <Profesor> findDistinctByActiveTrueAndDepartamentoIgnoreCaseAndCursos_CreditosGreaterThanEqualOrderByApellidoAscNombreAsc(
        String depto,
        int creditos
    );



}

