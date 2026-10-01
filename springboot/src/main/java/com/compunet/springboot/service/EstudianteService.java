package com.compunet.springboot.service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Estudiante> obtenerPorId(Long id) {
        return estudianteRepo.findById(id);
    }

    public Optional<Estudiante> porCorreoInstitucional(String correo) {
        return estudianteRepo.findByCorreoInstitucional(correo);
    }

    public boolean existePorCorreoInstitucional(String correo) {
        return estudianteRepo.existsByCorreoInstitucional(correo);
    }

    public Estudiante registrarEstudiante(Estudiante estudiante) {
        if (estudianteRepo.existsByCorreoInstitucional(estudiante.getCorreoInstitucional())) {
            throw new IllegalArgumentException("El correo institucional ya se encuentra registrado: "
                    + estudiante.getCorreoInstitucional());
        }
        estudiante.setActive(true);
        return estudianteRepo.save(estudiante);
    }

    public Estudiante actualizarEstudiante(Long id, Estudiante estudianteActualizado) {
        Estudiante estudianteDb = estudianteRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el estudiante con ID: " + id));

        if (!estudianteDb.getCorreoInstitucional().equalsIgnoreCase(estudianteActualizado.getCorreoInstitucional())
                && estudianteRepo.existsByCorreoInstitucional(estudianteActualizado.getCorreoInstitucional())) {
            throw new IllegalArgumentException("El nuevo correo institucional ya se encuentra registrado: "
                    + estudianteActualizado.getCorreoInstitucional());
        }

        estudianteDb.setNombre(estudianteActualizado.getNombre());
        estudianteDb.setApellido(estudianteActualizado.getApellido());
        estudianteDb.setCorreoInstitucional(estudianteActualizado.getCorreoInstitucional());
        estudianteDb.setColorFavorito(estudianteActualizado.getColorFavorito());
        estudianteDb.setActive(estudianteActualizado.isActive());

        return estudianteRepo.save(estudianteDb);
    }

    public Estudiante alternarEstado(Long id) {
        Estudiante estudiante = estudianteRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el estudiante con ID: " + id));
        estudiante.setActive(!estudiante.isActive());
        return estudianteRepo.save(estudiante);
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

    // Ejercicio 2 preparcial: Estudiantes activos por dominio y especialidad docente
    public List<Estudiante> estudiantesActivosPorDominioYEspecialidadProfesor(String dominioCorreo, String especialidad) {
        return estudianteRepo.findDistinctByActiveTrueAndCorreoInstitucionalEndingWithIgnoreCaseAndEstudianteCursos_Curso_Profesor_EspecialidadIgnoreCaseOrderByApellidoAscNombreAsc(dominioCorreo, especialidad);
    }

}
