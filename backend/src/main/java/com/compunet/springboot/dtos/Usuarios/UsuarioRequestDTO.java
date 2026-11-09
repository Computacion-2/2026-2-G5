package com.compunet.springboot.dtos.Usuarios;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor 
@AllArgsConstructor 
public class UsuarioRequestDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private String password;
    private String correoInstitucional;
    private Set<String> roles;
    
}
