package com.compunet.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compunet.springboot.model.Usuario;
import com.compunet.springboot.repository.UsuarioRepository;

@Service
public class UsuarioService {
  
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository userRepo){
        this.usuarioRepository = userRepo;
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    // Ejercicio 1: Buscar usuario por su correoInstitucional exacto
    public Optional<Usuario> porCorreoInstitucional(String correo) {
        return usuarioRepository.findByCorreoInstitucional(correo);
    }

    // Ejercicio 2: Comprobar si ya existe un usuario con un correoInstitucional determinado
    public boolean existePorCorreoInstitucional(String correo) {
        return usuarioRepository.existsByCorreoInstitucional(correo);
    }

    // Ejercicio 11: Obtener usuarios activos con un rol determinado (sin distinguir mayúsculas)
    public List<Usuario> usuariosActivosPorRol(String nombreRol) {
        return usuarioRepository.findByActiveTrueAndRoles_NombreIgnoreCase(nombreRol);
    }
}

