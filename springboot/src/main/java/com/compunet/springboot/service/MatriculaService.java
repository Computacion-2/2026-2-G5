package com.compunet.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.compunet.springboot.model.Curso;
import com.compunet.springboot.model.Estudiante;
import com.compunet.springboot.model.EstudianteCurso;
import com.compunet.springboot.model.EstudianteCursoId;
import com.compunet.springboot.repository.CursoRepository;
import com.compunet.springboot.repository.EstudianteCursoRepository;
import com.compunet.springboot.repository.EstudianteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class MatriculaService {

    private final EstudianteCursoRepository repository;
    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;

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


    public EstudianteCurso matricularEstudianteEnCurso(Long estudianteId, Long cursoId) throws Exception {
        Estudiante estudiante = estudianteRepository.findById(estudianteId)
            .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado con ID: " + estudianteId));

        if (!estudiante.isActive()) {
            throw new IllegalStateException("El estudiante está inactivo y no puede matricular cursos.");
        }

        Curso curso = cursoRepository.findById(cursoId)
            .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado con ID: " + cursoId));

        EstudianteCursoId idCompuesto = new EstudianteCursoId(estudianteId, cursoId);
        if (repository.existsById(idCompuesto)) {
            throw new IllegalStateException("El estudiante ya se encuentra matriculado en este curso.");
        }

        EstudianteCurso nuevaMatricula = new EstudianteCurso(estudiante, curso);
        EstudianteCurso guardado = repository.save(nuevaMatricula);

        return guardado;
    }
}

