package com.compunet.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.compunet.springboot.model.Permiso;
import com.compunet.springboot.repository.PermisoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PermisoService {

    private final PermisoRepository permisoRepo;

    public List<Permiso> findAll() {
        return permisoRepo.findAll();
    }

    // Ejercicio 14: Permisos de un rol (ManyToMany inversa)
    public List<Permiso> permisosPorNombreRol(String nombreRol) {
        return permisoRepo.findByRoles_NombreIgnoreCase(nombreRol);
    }

}
