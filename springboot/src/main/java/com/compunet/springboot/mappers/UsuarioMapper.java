package com.compunet.springboot.mappers;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.compunet.springboot.dtos.Usuarios.UsuarioRequestDTO;
import com.compunet.springboot.dtos.Usuarios.UsuarioResponseDTO;
import com.compunet.springboot.model.Rol;
import com.compunet.springboot.model.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    @Mapping (target = "roles", source = "roles" , qualifiedByName = "rolesToString")
    UsuarioResponseDTO toUsuarioResponseDTO(Usuario usuario);

    List<UsuarioResponseDTO> toListUsuarioResponseDTO(List<Usuario> users);

    @Named ("rolesToString")
    default Set<String> rolesToString(List<Rol> roles) {
        if (roles == null) {
            return Collections.emptySet();
        }

        return roles.stream()
                .map(Rol::getNombre)
                .collect(Collectors.toSet());
    }

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "active", ignore = true)
    Usuario toUsuario(UsuarioRequestDTO userRequestDTO);

}
