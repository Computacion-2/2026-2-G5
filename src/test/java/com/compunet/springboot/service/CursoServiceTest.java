package com.compunet.springboot.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.compunet.springboot.model.Curso;
import com.compunet.springboot.model.Profesor;
import com.compunet.springboot.repository.CursoRepository;
import com.compunet.springboot.repository.MatriculaRepository;
import com.compunet.springboot.repository.ProfesorRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas Unitarias - CursoService")
class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private ProfesorRepository profesorRepository;

    @Mock
    private MatriculaRepository matriculaRepository;

    @InjectMocks
    private CursoService cursoService;

    private Curso cursoEjemplo;
    private Profesor profesorEjemplo;

    @BeforeEach
    void setUp() {
        profesorEjemplo = new Profesor();
        profesorEjemplo.setId(10L);
        profesorEjemplo.setDepartamento("TIC");
        profesorEjemplo.setEspecialidad("Ingeniería de Software");

        cursoEjemplo = new Curso();
        cursoEjemplo.setId(1L);
        cursoEjemplo.setNombre("Programación Distribuida");
        cursoEjemplo.setCreditos(3);
        cursoEjemplo.setDepartamento("TIC");
        cursoEjemplo.setProfesor(profesorEjemplo);
    }

    @Test
    @DisplayName("Debe listar cursos filtrando por departamento insensible a mayúsculas")
    void debeListarCursosPorDepartamento() {
        // Arrange
        when(cursoRepository.findByDepartamentoIgnoreCase("tic"))
                .thenReturn(List.of(cursoEjemplo));

        // Act
        List<Curso> resultado = cursoService.listarPorDepartamento("tic");

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Programación Distribuida", resultado.get(0).getNombre());
        verify(cursoRepository, times(1)).findByDepartamentoIgnoreCase("tic");
    }

    @Test
    @DisplayName("Debe retornar todos los cursos si el departamento es nulo o vacío")
    void debeListarTodosSiDepartamentoEsVacio() {
        // Arrange
        when(cursoRepository.findAll()).thenReturn(List.of(cursoEjemplo));

        // Act
        List<Curso> resultado = cursoService.listarPorDepartamento("   ");

        // Assert
        assertEquals(1, resultado.size());
        verify(cursoRepository, times(1)).findAll();
        verify(cursoRepository, never()).findByDepartamentoIgnoreCase(anyString());
    }

    @Test
    @DisplayName("Debe listar cursos de un profesor cuando el profesor existe")
    void debeListarCursosPorProfesorIdExitoso() {
        // Arrange
        when(profesorRepository.existsById(10L)).thenReturn(true);
        when(cursoRepository.findByProfesorId(10L)).thenReturn(List.of(cursoEjemplo));

        // Act
        List<Curso> resultado = cursoService.listarPorProfesorId(10L);

        // Assert
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(10L, resultado.get(0).getProfesor().getId());
        verify(profesorRepository, times(1)).existsById(10L);
        verify(cursoRepository, times(1)).findByProfesorId(10L);
    }

    @Test
    @DisplayName("Debe lanzar NoSuchElementException al listar cursos de profesor inexistente")
    void debeLanzarExcepcionCuandoProfesorNoExiste() {
        // Arrange
        when(profesorRepository.existsById(999L)).thenReturn(false);

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> {
            cursoService.listarPorProfesorId(999L);
        });

        verify(cursoRepository, never()).findByProfesorId(anyLong());
    }

    @Test
    @DisplayName("Debe eliminar curso exitosamente cuando no tiene matrículas asociadas")
    void debeEliminarCursoCuandoNoTieneMatriculas() {
        // Arrange
        when(cursoRepository.existsById(1L)).thenReturn(true);
        when(matriculaRepository.existsByIdCursoId(1L)).thenReturn(false);

        // Act
        cursoService.eliminarCurso(1L);

        // Assert
        verify(cursoRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Debe lanzar IllegalStateException (409 Conflict) al eliminar curso con matrículas activas")
    void debeLanzarExcepcionAlEliminarCursoConMatriculas() {
        // Arrange
        when(cursoRepository.existsById(1L)).thenReturn(true);
        when(matriculaRepository.existsByIdCursoId(1L)).thenReturn(true);

        // Act & Assert
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            cursoService.eliminarCurso(1L);
        });

        assertTrue(ex.getMessage().contains("tiene estudiantes matriculados"));
        verify(cursoRepository, never()).deleteById(anyLong());
    }

    @Test
    @DisplayName("Debe lanzar NoSuchElementException al intentar eliminar curso inexistente")
    void debeLanzarExcepcionAlEliminarCursoInexistente() {
        // Arrange
        when(cursoRepository.existsById(999L)).thenReturn(false);

        // Act & Assert
        assertThrows(NoSuchElementException.class, () -> {
            cursoService.eliminarCurso(999L);
        });

        verify(matriculaRepository, never()).existsByIdCursoId(anyLong());
        verify(cursoRepository, never()).deleteById(anyLong());
    }
}
