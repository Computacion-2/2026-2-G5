package com.compunet.springboot.service.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.compunet.springboot.model.Rol;
import com.compunet.springboot.model.Usuario;
import com.compunet.springboot.repository.RolRepository;
import com.compunet.springboot.repository.UsuarioRepository;
import com.compunet.springboot.service.UsuarioService;

@SpringBootTest 
@ActiveProfiles ("test")
@DisplayName ("Usuario Service - Integation Tests")
public class UsuarioServiceIntegrationTest {
    
    @Autowired 
    private UsuarioService usuarioService;

    @Autowired 
    private RolRepository rolRepository;

    @Test
    @DisplayName ("Debe persistir en base de daros el usuario con su rol y poder consuarlo")
    void deberegistrarUsuarioYRolEnBaseDeDatos() {
        Rol rolAdmin = new Rol();
        rolAdmin.setNombre("ADMIN_TEST");
        rolRepository.save(rolAdmin);

        Usuario user = new Usuario();
        user.setNombre("Alejandro");
        user.setApellido( "Penaranda Agudelo");
        user.setCorreoInstitucional("apenaranda@icesi.edu.co");
        user.setPassword("segura123");


        Usuario usuarioGuardado = usuarioService.registrarUsuario(user,  "ADMIN_TEST");


        assertNotNull(usuarioGuardado.getId());
        assertTrue(usuarioGuardado.isActive());
        assertEquals("apenaranda@icesi.edu.co", usuarioGuardado.getCorreoInstitucional());

        List<Usuario> usuariosAdmin = usuarioService.usuariosActivosPorRol("ADMIN_TEST");
        assertFalse(usuariosAdmin.isEmpty(), "Debe encontrar al menos un user");
        assertTrue(usuariosAdmin.stream().anyMatch(u -> u.getCorreoInstitucional().equals("apenaranda@icesi.edu.co")));

    }
}
