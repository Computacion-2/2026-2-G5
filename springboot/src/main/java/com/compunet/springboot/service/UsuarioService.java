package com.compunet.springboot.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compunet.springboot.model.Rol;
import com.compunet.springboot.model.Usuario;
import com.compunet.springboot.repository.RolRepository;
import com.compunet.springboot.repository.UsuarioRepository;

@Service
public class UsuarioService {
  
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    @Autowired
    public UsuarioService(UsuarioRepository userRepo, RolRepository rolRepo){
        this.usuarioRepository = userRepo;
        this.rolRepository = rolRepo;
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

    // Ejercicio 5 preparcial: Usuarios activos por roles y permiso
    public List<Usuario> usuariosActivosPorRolesYPermiso(Collection<String> nombresRoles, String nombrePermiso) {
        return usuarioRepository.findDistinctByActiveTrueAndRoles_NombreInAndRoles_Permisos_NombreIgnoreCaseOrderByApellidoAscNombreAsc(nombresRoles, nombrePermiso);
    }

    public Usuario registrarUsuario(Usuario usuario, String nombreRol) {
        if (usuarioRepository.existsByCorreoInstitucional(usuario.getCorreoInstitucional())) {
            throw new IllegalArgumentException("El correo institucional ya se encuentra registrado: "
            + usuario.getCorreoInstitucional());
        }

        Rol rol = rolRepository.findByNombre(nombreRol).orElseThrow(() -> 
            new IllegalStateException("El rol ingresado no existe"));

        if (usuario.getRoles() == null) {
            usuario.setRoles(new ArrayList<>());
        }

        usuario.getRoles().add(rol);
        usuario.setActive(true);

        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        Usuario usuarioDb = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el usuario con ID: " + id));

        // Regla de Negocio: Si cambia el correo institucional, validar que no esté ocupado por otro usuario
        if (!usuarioDb.getCorreoInstitucional().equalsIgnoreCase(usuarioActualizado.getCorreoInstitucional())
                && usuarioRepository.existsByCorreoInstitucional(usuarioActualizado.getCorreoInstitucional())) {
            throw new IllegalArgumentException("El nuevo correo institucional ya se encuentra registrado: "
                    + usuarioActualizado.getCorreoInstitucional());
        }

        usuarioDb.setNombre(usuarioActualizado.getNombre());
        usuarioDb.setApellido(usuarioActualizado.getApellido());
        usuarioDb.setCorreoInstitucional(usuarioActualizado.getCorreoInstitucional());

        if (usuarioActualizado.getPassword() != null && !usuarioActualizado.getPassword().isBlank()) {
            usuarioDb.setPassword(usuarioActualizado.getPassword());
        }

        usuarioDb.setActive(usuarioActualizado.isActive());

        return usuarioRepository.save(usuarioDb);
    }

    public Usuario alternarEstado(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el usuario con ID: " + id));
        usuario.setActive(!usuario.isActive());
        return usuarioRepository.save(usuario);
    }
}

