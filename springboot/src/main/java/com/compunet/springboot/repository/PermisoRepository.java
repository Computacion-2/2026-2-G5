package com.compunet.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compunet.springboot.model.Permiso;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Long> {

    // Ejercicio 14: Obtener todos los permisos asignados a un rol buscando por el nombre del rol, sin distinguir mayúsculas
    List<Permiso> findByRoles_NombreIgnoreCase(String nombreRol);

}

