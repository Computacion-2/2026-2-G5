package com.compunet.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.compunet.springboot.model.Estudiante;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    // Ejercicio 7: Obtener los estudiantes cuyo correoInstitucional termine con una cadena o dominio dado, ignorando mayúsculas/minúsculas
    List<Estudiante> findByCorreoInstitucionalEndingWithIgnoreCase(String dominio);

    // Ejercicio 8: Contar el total de estudiantes cuyo estado sea activo (active = true)
    Long countByActiveTrue();

    // Ejercicio 15 (JPQL): Obtener estudiantes activos matriculados en un curso, ordenados por apellido ascendente
    @Query("SELECT e FROM Estudiante e " + 
           "JOIN e.estudianteCursos ec " + 
           "WHERE ec.curso.id = :cursoId AND e.active = true ORDER BY e.apellido ASC")
    List<Estudiante> findActivosByCursoIdJPQL(@Param("cursoId") Long cursoId);

    // Ejercicio 15 (Native SQL): Obtener estudiantes activos matriculados en un curso, ordenados por apellido ascendente
    @Query(value = "SELECT e.* FROM Estudiante e INNER JOIN estudiante_curso ec ON e.id = ec.estudiante_id WHERE ec.curso_id = :cursoId AND e.active = true ORDER BY e.apellido ASC", nativeQuery = true)
    List<Estudiante> findActivosByCursoIdNative(@Param("cursoId") Long cursoId);

}