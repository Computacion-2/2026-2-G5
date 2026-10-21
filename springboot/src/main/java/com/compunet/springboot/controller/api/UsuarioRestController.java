package com.compunet.springboot.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.compunet.springboot.dtos.Usuarios.UsuarioRequestDTO;
import com.compunet.springboot.dtos.Usuarios.UsuarioResponseDTO;
import com.compunet.springboot.mappers.UsuarioMapper;
import com.compunet.springboot.model.Usuario;
import com.compunet.springboot.service.UsuarioService;

import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController 
@RequestMapping ("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioRestController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;


    @GetMapping
    public List<UsuarioResponseDTO> obtenerUsuarios(@RequestParam(name = "activo", required=false) Boolean active) {

        List<Usuario> users = usuarioService.listarUsuarios(active);
        // return users.stream()
        //         .map(usuario -> UsuarioResponseDTO.builder()
        //             .id(usuario.getId())
        //             .nombre(usuario.getNombre())
        //             .apellido(usuario.getApellido())
        //             .correoInstitucional(usuario.getCorreoInstitucional())
        //             .active(usuario.isActive())
        //             .roles(usuario.getRoles().stream()
        //                 .map(Rol::getNombre)
        //                 .collect(Collectors.toSet()))
        //             .build())
        //         .toList();
        return usuarioMapper.toListUsuarioResponseDTO(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtnerUsuarioPorId(@PathVariable Long id) {
        Optional<Usuario> user = usuarioService.obtenerPorId(id);

        if (user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        UsuarioResponseDTO response = usuarioMapper.toUsuarioResponseDTO(user.get());
        // return ResponseEntity.ok(response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping()
    public ResponseEntity<UsuarioResponseDTO> crearUsurio(@RequestBody UsuarioRequestDTO requestDTO) {

        try {

            Usuario usuario = usuarioMapper.toUsuario(requestDTO);

            Usuario creado = usuarioService.registrarUsuario(usuario, requestDTO.getRoles());
            
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(creado.getId())
                    .toUri();
            return ResponseEntity.created(location).body(usuarioMapper.toUsuarioResponseDTO(usuario));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuario(
            @PathVariable Long id,
            @RequestBody UsuarioRequestDTO requestDTO) {
        try {
            Usuario datosNuevos = usuarioMapper.toUsuario(requestDTO);
            Usuario actualizado = usuarioService.actualizarUsuario(id, datosNuevos, requestDTO.getRoles());
            return ResponseEntity.ok(usuarioMapper.toUsuarioResponseDTO(actualizado));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<UsuarioResponseDTO> alternarEstado(@PathVariable Long id) {
        try {
            Usuario usuarioModificado = usuarioService.alternarEstado(id);
            return ResponseEntity.ok(usuarioMapper.toUsuarioResponseDTO(usuarioModificado));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        try {
            usuarioService.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
