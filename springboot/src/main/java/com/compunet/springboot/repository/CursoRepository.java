package com.compunet.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compunet.springboot.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    // Ejercicio 4: Obtener los cursos cuya cantidad de créditos se encuentre dentro de un rango (min y max)
    List<Curso> findByCreditosBetween(int min, int max);

    // Ejercicio 5: Buscar cursos cuyo nombre contenga un texto dado, sin distinguir mayúsculas de minúsculas
    List<Curso> findByNombreIgnoreCaseContaining(String texto);

    // Ejercicio 9: Obtener todos los cursos que dicta un profesor a partir del id del profesor
    List<Curso> findByProfesor_Id(Long id);

    // Ejercicio 10: Obtener los cursos cuyo profesor pertenezca a un departamento determinado, sin distinguir mayúsculas
    List<Curso> findByProfesor_DepartamentoIgnoreCase(String depto);

    // Ejercicio 13: Obtener los cursos con créditos mayores o iguales a un valor mínimo, ordenados por créditos de mayor a menor
    List<Curso> findByCreditosGreaterThanEqualOrderByCreditosDesc(int creditosMinimos);

}


