package com.compunet.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.compunet.springboot.model.Profesor;
import com.compunet.springboot.repository.ProfesorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfesorService {

    private final ProfesorRepository profeRepo;

    public List<Profesor> findAll() {
        return profeRepo.findAll();
    }

    public Optional<Profesor> obtenerPorId(Long id) {
        return profeRepo.findById(id);
    }

    public Optional<Profesor> porCorreoInstitucional(String correo) {
        return profeRepo.findByCorreoInstitucional(correo);
    }

    public boolean existePorCorreoInstitucional(String correo) {
        return profeRepo.existsByCorreoInstitucional(correo);
    }

    public Profesor registrarProfesor(Profesor profesor) {
        if (profeRepo.existsByCorreoInstitucional(profesor.getCorreoInstitucional())) {
            throw new IllegalArgumentException("El correo institucional ya se encuentra registrado: "
                    + profesor.getCorreoInstitucional());
        }
        profesor.setActive(true);
        return profeRepo.save(profesor);
    }

    public Profesor actualizarProfesor(Long id, Profesor profesorActualizado) {
        Profesor profesorDb = profeRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el profesor con ID: " + id));

        if (!profesorDb.getCorreoInstitucional().equalsIgnoreCase(profesorActualizado.getCorreoInstitucional())
                && profeRepo.existsByCorreoInstitucional(profesorActualizado.getCorreoInstitucional())) {
            throw new IllegalArgumentException("El nuevo correo institucional ya se encuentra registrado: "
                    + profesorActualizado.getCorreoInstitucional());
        }

        profesorDb.setNombre(profesorActualizado.getNombre());
        profesorDb.setApellido(profesorActualizado.getApellido());
        profesorDb.setCorreoInstitucional(profesorActualizado.getCorreoInstitucional());
        profesorDb.setDepartamento(profesorActualizado.getDepartamento());
        profesorDb.setEspecialidad(profesorActualizado.getEspecialidad());
        profesorDb.setActive(profesorActualizado.isActive());

        return profeRepo.save(profesorDb);
    }

    public Profesor alternarEstado(Long id) {
        Profesor profesor = profeRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el profesor con ID: " + id));
        profesor.setActive(!profesor.isActive());
        return profeRepo.save(profesor);
    }

    public List<Profesor> profesoresPorDepto(String depto) {
        return profeRepo.findByDepartamento(depto);
    }

    // Ejercicio 3: Obtener los profesores activos por departamento (sin distinguir mayúsculas)
    public List<Profesor> listarProfesoresActivos(String depto) {
        return profeRepo.findByDepartamentoIgnoreCaseAndActiveTrue(depto);
    }

    // Ejercicio 6: Obtener profesores de una especialidad ordenados alfabéticamente por apellido asc
    public List<Profesor> profesoresPorEspecialidadOrdenados(String especialidad) {
        return profeRepo.findByEspecialidadIgnoreCaseOrderByApellidoAsc(especialidad);
    }

    // Ejercicio 1 preparcial: Reporte docente por créditos dictados y departamento
    public List<Profesor> profesoresActivosPorDepartamentoYCreditosMinimos(String departamento, int minCreditos) {
        return profeRepo.findDistinctByActiveTrueAndDepartamentoIgnoreCaseAndCursos_CreditosGreaterThanEqualOrderByApellidoAscNombreAsc(departamento, minCreditos);
    }

}

