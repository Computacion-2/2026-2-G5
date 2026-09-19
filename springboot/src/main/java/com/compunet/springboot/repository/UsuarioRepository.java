package com.compunet.springboot.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compunet.springboot.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Ejercicio 1: Buscar usuario por su correoInstitucional exacto
    Optional<Usuario> findByCorreoInstitucional(String correoInstitucional);

    // Ejercicio 2: Comprobar si ya existe un usuario con un correoInstitucional determinado
    boolean existsByCorreoInstitucional(String correoInstitucional);

    // Ejercicio 11: Obtener todos los usuarios activos (active = true) que tengan asignado un rol con un nombre dado, sin distinguir mayúsculas
    List<Usuario> findByActiveTrueAndRoles_NombreIgnoreCase(String nombreRol);

    // Ejercicio 5 preparcial: Usuarios activos con al menos un rol de la lista y que tengan un permiso específico
    List<Usuario> findDistinctByActiveTrueAndRoles_NombreInAndRoles_Permisos_NombreIgnoreCaseOrderByApellidoAscNombreAsc(
        Collection<String> nombresRoles,
        String nombrePermiso
    );

}

