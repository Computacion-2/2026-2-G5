package com.compunet.springboot.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compunet.springboot.dtos.Usuarios.UsuarioResponseDTO;
import com.compunet.springboot.model.Rol;
import com.compunet.springboot.model.Usuario;
import com.compunet.springboot.service.UsuarioService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequestMapping ("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioRestController {

    private final UsuarioService usuarioService;


    @GetMapping
    public List<UsuarioResponseDTO> obtenerUsuarios(@RequestParam(name = "activo", required=false) Boolean active) {

        List<Usuario> users = usuarioService.listarUsuarios(active);
        return users.stream()
                .map(usuario -> UsuarioResponseDTO.builder()
                    .id(usuario.getId())
                    .nombre(usuario.getNombre())
                    .apellido(usuario.getApellido())
                    .correoInstitucional(usuario.getCorreoInstitucional())
                    .active(usuario.isActive())
                    .roles(usuario.getRoles().stream()
                        .map(Rol::getNombre)
                        .collect(Collectors.toSet()))
                    .build())
                .toList();
    }
    
    
}
