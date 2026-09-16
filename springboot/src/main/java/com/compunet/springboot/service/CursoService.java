package com.compunet.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.compunet.springboot.model.Curso;
import com.compunet.springboot.repository.CursoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository cursoRepo;

    public List<Curso> findAll() {
        return cursoRepo.findAll();
    }

    // Ejercicio 4: Cursos por rango de créditos
    public List<Curso> cursosPorRangoCreditos(int min, int max) {
        return cursoRepo.findByCreditosBetween(min, max);
    }

    // Ejercicio 5: Buscar cursos por coincidencia en el nombre
    public List<Curso> buscarCursosPorNombre(String texto) {
        return cursoRepo.findByNombreIgnoreCaseContaining(texto);
    }

    // Ejercicio 9: Cursos asignados a un profesor (ManyToOne)
    public List<Curso> cursosPorProfesorId(Long profesorId) {
        return cursoRepo.findByProfesor_Id(profesorId);
    }

    // Ejercicio 10: Cursos según el departamento del profesor
    public List<Curso> cursosPorDepartamentoProfesor(String depto) {
        return cursoRepo.findByProfesor_DepartamentoIgnoreCase(depto);
    }

    // Ejercicio 13: Cursos por créditos mínimos ordenados descendentemente
    public List<Curso> cursosPorCreditosMinimosOrdenados(int creditosMinimos) {
        return cursoRepo.findByCreditosGreaterThanEqualOrderByCreditosDesc(creditosMinimos);
    }

}
