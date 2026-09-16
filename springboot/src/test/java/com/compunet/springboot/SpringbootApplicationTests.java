package com.compunet.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.compunet.springboot.model.Curso;
import com.compunet.springboot.model.Estudiante;
import com.compunet.springboot.model.Permiso;
import com.compunet.springboot.model.Profesor;
import com.compunet.springboot.model.Usuario;
import com.compunet.springboot.service.CursoService;
import com.compunet.springboot.service.EstudianteService;
import com.compunet.springboot.service.MatriculaService;
import com.compunet.springboot.service.PermisoService;
import com.compunet.springboot.service.ProfesorService;
import com.compunet.springboot.service.UsuarioService;

@SpringBootTest
class SpringbootApplicationTests {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private MatriculaService matriculaService;

    @Autowired
    private PermisoService permisoService;

    @Test
    void contextLoads() {
    }

    @Test
    void testEjercicio1_BuscarUsuarioPorCorreo() {
        Optional<Usuario> usuario = usuarioService.porCorreoInstitucional("juan.perez@icesi.edu.co");
        assertTrue(usuario.isPresent());
        assertEquals("Juan", usuario.get().getNombre());
    }

    @Test
    void testEjercicio2_VerificarExistenciaCorreo() {
        boolean existe = usuarioService.existePorCorreoInstitucional("maria.gomez@icesi.edu.co");
        assertTrue(existe);
        boolean noExiste = usuarioService.existePorCorreoInstitucional("noexiste@icesi.edu.co");
        assertFalse(noExiste);
    }

    @Test
    void testEjercicio3_ProfesoresActivosPorDepartamento() {
        List<Profesor> profesores = profesorService.listarProfesoresActivos("computación y sistemas inteligentes");
        assertEquals(3, profesores.size());
    }

    @Test
    void testEjercicio4_CursosPorRangoCreditos() {
        List<Curso> cursos = cursoService.cursosPorRangoCreditos(2, 4);
        assertEquals(3, cursos.size());
    }

    @Test
    void testEjercicio5_BuscarCursosPorNombre() {
        List<Curso> cursos = cursoService.buscarCursosPorNombre("internet");
        assertEquals(1, cursos.size());
        assertEquals("Computacion en internet 2", cursos.get(0).getNombre());
    }

    @Test
    void testEjercicio6_ProfesoresPorEspecialidadOrdenados() {
        List<Profesor> profesores = profesorService.profesoresPorEspecialidadOrdenados("telematica");
        assertEquals(1, profesores.size());
        assertEquals("Rincon", profesores.get(0).getApellido());
    }

    @Test
    void testEjercicio7_EstudiantesPorDominioCorreo() {
        List<Estudiante> estudiantes = estudianteService.estudiantesPorDominioCorreo("@icesi.edu.co");
        assertEquals(3, estudiantes.size());
    }

    @Test
    void testEjercicio8_ConteoEstudiantesActivos() {
        Long conteo = estudianteService.contarEstudiantesActivos();
        assertEquals(3L, conteo);
    }

    @Test
    void testEjercicio9_CursosPorProfesor() {
        List<Curso> cursos = cursoService.cursosPorProfesorId(3L);
        assertEquals(1, cursos.size());
        assertEquals("Computacion en internet 2", cursos.get(0).getNombre());
    }

    @Test
    void testEjercicio10_CursosPorDepartamentoProfesor() {
        List<Curso> cursos = cursoService.cursosPorDepartamentoProfesor("computación y sistemas inteligentes");
        assertEquals(3, cursos.size());
    }

    @Test
    void testEjercicio11_UsuariosActivosPorRol() {
        List<Usuario> usuarios = usuarioService.usuariosActivosPorRol("docente");
        assertEquals(2, usuarios.size());
    }

    @Test
    void testEjercicio12_VerificarMatricula() {
        boolean matriculado = matriculaService.estaMatriculado(1L, 1L);
        assertTrue(matriculado);
        boolean noMatriculado = matriculaService.estaMatriculado(1L, 999L);
        assertFalse(noMatriculado);
    }

    @Test
    void testEjercicio13_CursosPorCreditosMinimos() {
        List<Curso> cursos = cursoService.cursosPorCreditosMinimosOrdenados(3);
        assertEquals(3, cursos.size());
    }

    @Test
    void testEjercicio14_PermisosPorRol() {
        List<Permiso> permisosAdmin = permisoService.permisosPorNombreRol("admin");
        assertEquals(5, permisosAdmin.size());
        List<Permiso> permisosDocente = permisoService.permisosPorNombreRol("docente");
        assertEquals(2, permisosDocente.size());
    }

    @Test
    void testEjercicio15_EstudiantesCursoJPQLyNative() {
        List<Estudiante> jpql = estudianteService.estudiantesActivosPorCursoJPQL(1L);
        assertEquals(3, jpql.size());
        assertEquals("Marquez", jpql.get(0).getApellido());
        assertEquals("Penaranda", jpql.get(1).getApellido());
        assertEquals("Ramirez", jpql.get(2).getApellido());

        List<Estudiante> nativeSql = estudianteService.estudiantesActivosPorCursoNative(1L);
        assertEquals(3, nativeSql.size());
        assertEquals("Marquez", nativeSql.get(0).getApellido());
        assertEquals("Penaranda", nativeSql.get(1).getApellido());
        assertEquals("Ramirez", nativeSql.get(2).getApellido());
    }

}

