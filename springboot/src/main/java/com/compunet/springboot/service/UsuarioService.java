package com.compunet.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compunet.springboot.model.Usuario;
import com.compunet.springboot.repository.UsuarioRepository;

// import lombok.RequiredArgsConstructor;

@Service
// @RequiredArgsConstructor Anotacion que me genera el constructor automaticamente.
public class UsuarioService {
  
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository userRepo){
        this.usuarioRepository = userRepo;
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> porCorreoInstitucional(String correo) {
        return usuarioRepository.findByCorreoInstitucional(correo);
    }

    public boolean existePorCorreoInstitucional(String correo) {
        return usuarioRepository.existsByCorreoInstitucional(correo);
    }
}
