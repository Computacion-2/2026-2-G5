package com.compunet.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.compunet.springboot.model.Estudiante;
import com.compunet.springboot.repository.EstudianteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstudianteService {

    private final EstudianteRepository estudianteRepo;

    public List<Estudiante> findAll() {
        return estudianteRepo.findAll();
    }

    // Ejercicio 7: Estudiantes por dominio de correo
    public List<Estudiante> estudiantesPorDominioCorreo(String dominio) {
        return estudianteRepo.findByCorreoInstitucionalEndingWithIgnoreCase(dominio);
    }

    // Ejercicio 8: Conteo de estudiantes activos
    public Long contarEstudiantesActivos() {
        return estudianteRepo.countByActiveTrue();
    }

    // Ejercicio 15: Estudiantes de un curso con @Query (JPQL)
    public List<Estudiante> estudiantesActivosPorCursoJPQL(Long cursoId) {
        return estudianteRepo.findActivosByCursoIdJPQL(cursoId);
    }

    // Ejercicio 15: Estudiantes de un curso con @Query (Native)
    public List<Estudiante> estudiantesActivosPorCursoNative(Long cursoId) {
        return estudianteRepo.findActivosByCursoIdNative(cursoId);
    }

}
