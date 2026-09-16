package com.compunet.springboot.service;

import java.util.List;

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

}

