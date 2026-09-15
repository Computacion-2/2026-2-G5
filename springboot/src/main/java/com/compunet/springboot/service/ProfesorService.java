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

    public List<Profesor> profesoresPorDepto(String depto) {
        return profeRepo.findByDepartamento(depto);
    }

    public List<Profesor> listarProfesoresActivos(String depto) {
        return profeRepo.findByDepartamentoAndActiveTrue(depto);
    }

}
