package com.compunet.springboot.service.unit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.h2.command.dml.MergeUsing.When;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.compunet.springboot.model.Rol;
import com.compunet.springboot.model.Usuario;
import com.compunet.springboot.repository.RolRepository;
import com.compunet.springboot.repository.UsuarioRepository;
import com.compunet.springboot.service.UsuarioService;

@ExtendWith (MockitoExtension.class)
@DisplayName("Pruebas Unitarias - UsuarioService - Mockito")
public class UsuarioServiceTest {

    @Mock 
    private UsuarioRepository usuarioRepository;

    @Mock
    private RolRepository rolRepository;

    @InjectMocks 
    private UsuarioService usuarioService;

    private Usuario usuarioEjemplo;
    private Rol rolEjemplo;

    @BeforeEach 
    void setUp(){
        usuarioEjemplo = new Usuario();
        usuarioEjemplo.setId(1L);
        usuarioEjemplo.setNombre("Alejandro");
        usuarioEjemplo.setApellido( "Penaranda Agudelo");
        usuarioEjemplo.setCorreoInstitucional("apenaranda@icesi.edu.co");
        usuarioEjemplo.setPassword("segura123");

        rolEjemplo = new Rol();
        rolEjemplo.setId(2L);
        rolEjemplo.setNombre("ESTUDIANTE");
    }

    @Test
    @DisplayName ("Debe registrar exitosamente cuando los datos y el rol son validos")
    void debeRegistrarUsuariosExistosamente(){

        //1. Arrange
        when(usuarioRepository.existsByCorreoInstitucional(usuarioEjemplo.getCorreoInstitucional())).thenReturn(false);
        when(rolRepository.findByNombre("ESTUDIANTE")).thenReturn(Optional.of(rolEjemplo));
        when(usuarioRepository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));
        //2. Act

        Usuario usuario = usuarioService.registrarUsuario(usuarioEjemplo, "ESTUDIANTE");

        //3. Assert

        assertNotNull(usuario);
        assertTrue(usuario.isActive());
        assertTrue(usuario.getRoles().contains(rolEjemplo));
        verify(usuarioRepository).save(any(Usuario.class));
    }

    @Test 
    @DisplayName ("Debe fallar a causa de duplicidad en correoInstitucional")
    void debeLanzarExcepcionCuandoCorreoYaExiste(){
        //1. Arrange
        when(usuarioRepository.existsByCorreoInstitucional(usuarioEjemplo.getCorreoInstitucional()))
            .thenReturn(true);
        //2. Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> 
            {usuarioService.registrarUsuario(usuarioEjemplo, "ESTUDIANTE");
        });

        //3. Assert
        assertTrue(exception.getMessage().contains("ya se encuentra registrado"));
        verify(rolRepository, never()).findByNombre(anyString());
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }

    @Test
    @DisplayName ("Verificar que el rol existe, delo contrario falla")
    void debeLanzarExcepcionCuandoElRolNoExiste(){
        //1. Arrange
        when(usuarioRepository.existsByCorreoInstitucional(usuarioEjemplo.getCorreoInstitucional())).thenReturn(false);
        when(rolRepository.findByNombre("ROL_INEXISTENTE")).thenReturn(Optional.empty());

        //2. Act

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            usuarioService.registrarUsuario(usuarioEjemplo, "ROL_INEXISTENTE");
        });

        //3. Assert
        assertTrue(exception.getMessage().contains("rol ingresado no existe"));
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }
    
}
