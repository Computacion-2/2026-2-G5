package com.compunet.springboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compunet.springboot.model.Curso;
import com.compunet.springboot.model.Estudiante;
import com.compunet.springboot.model.EstudianteCurso;
import com.compunet.springboot.model.Permiso;
import com.compunet.springboot.model.Profesor;
import com.compunet.springboot.model.Usuario;
import com.compunet.springboot.service.CursoService;
import com.compunet.springboot.service.EstudianteService;
import com.compunet.springboot.service.MatriculaService;
import com.compunet.springboot.service.PermisoService;
import com.compunet.springboot.service.ProfesorService;
import com.compunet.springboot.service.UsuarioService;

@RestController
public class Controller {

    private final UsuarioService userService;
    private final ProfesorService profesorService;
    private final CursoService cursoService;
    private final EstudianteService estudianteService;
    private final MatriculaService matriculaService;
    private final PermisoService permisoService;

    @Autowired
    public Controller(UsuarioService userService,
                      ProfesorService profesorService,
                      CursoService cursoService,
                      EstudianteService estudianteService,
                      MatriculaService matriculaService,
                      PermisoService permisoService) {
        this.userService = userService;
        this.profesorService = profesorService;
        this.cursoService = cursoService;
        this.estudianteService = estudianteService;
        this.matriculaService = matriculaService;
        this.permisoService = permisoService;
    }

    @GetMapping("/")
    public String home() {
        return "Proyecto spring boot funcionando correctamente";
    }

    // ==========================================
    // Consultas Generales Base
    // ==========================================

    @GetMapping("/profesor")
    public List<Profesor> getProfesores() {
        return profesorService.findAll();
    }

    @GetMapping("/profesor/depto")
    public List<Profesor> getProfesoresDepto() {
        return profesorService.profesoresPorDepto("Computación y sistemas inteligentes");
    }

    @GetMapping("/curso")
    public List<Curso> getCursos() {
        return cursoService.findAll();
    }

    @GetMapping("/estudiante")
    public List<Estudiante> getEstudiantes() {
        return estudianteService.findAll();
    }

    @GetMapping("/estudiante_curso")
    public List<EstudianteCurso> getEstudianteCurso() {
        return matriculaService.findAll();
    }

    @GetMapping("/usuarios")
    public List<Usuario> getUsuarios() {
        return userService.findAll();
    }

    @GetMapping("/permisos")
    public List<Permiso> getPermisos() {
        return permisoService.findAll();
    }

    // ==========================================
    // Ejercicios Asignación Query Methods
    // ==========================================

    // Ejercicio 1: Buscar usuario por correo institucional exacto
    @GetMapping("/ejercicio1")
    public Optional<Usuario> ejercicio1BuscarUsuarioPorCorreo() {
        String correo = "juan.perez@icesi.edu.co";
        return userService.porCorreoInstitucional(correo);
    }

    @GetMapping("/usuario/correo")
    public Optional<Usuario> getUsuarioPorCorreoInstitucional() {
        return userService.porCorreoInstitucional("juan.perez@icesi.edu.co");
    }

    // Ejercicio 2: Verificar existencia de correo institucional (retorna boolean)
    @GetMapping("/ejercicio2")
    public boolean ejercicio2VerificarExistenciaCorreo() {
        String correo = "maria.gomez@icesi.edu.co";
        return userService.existePorCorreoInstitucional(correo);
    }

    @GetMapping("/usuario/existe/correo")
    public boolean existeUsuarioPorCorreoInstitucional() {
        return userService.existePorCorreoInstitucional("maria.gomez@icesi.edu.co");
    }

    // Ejercicio 3: Profesores activos por departamento (sin distinguir mayúsculas/minúsculas)
    @GetMapping("/ejercicio3")
    public List<Profesor> ejercicio3ProfesoresActivosPorDepto() {
        String depto = "computación y sistemas inteligentes";
        return profesorService.listarProfesoresActivos(depto);
    }

    @GetMapping("/profesor/depto/active")
    public List<Profesor> getProfesoresDeptoActive() {
        return profesorService.listarProfesoresActivos("computación y sistemas inteligentes");
    }

    // Ejercicio 4: Cursos por rango de créditos (min y max)
    @GetMapping("/ejercicio4")
    public List<Curso> ejercicio4CursosPorRangoCreditos() {
        int min = 2;
        int max = 4;
        return cursoService.cursosPorRangoCreditos(min, max);
    }

    @GetMapping("/curso/rango-creditos")
    public List<Curso> getCursosPorRangoCreditos() {
        return cursoService.cursosPorRangoCreditos(2, 4);
    }

    // Ejercicio 5: Buscar cursos por coincidencia en el nombre (sin distinguir mayúsculas/minúsculas)
    @GetMapping("/ejercicio5")
    public List<Curso> ejercicio5BuscarCursosPorNombre() {
        String texto = "internet";
        return cursoService.buscarCursosPorNombre(texto);
    }

    @GetMapping("/curso/buscar/nombre")
    public List<Curso> buscarCursosPorNombre() {
        return cursoService.buscarCursosPorNombre("internet");
    }

    // Ejercicio 6: Profesores por especialidad ordenados por apellido ascendente (sin distinguir mayúsculas)
    @GetMapping("/ejercicio6")
    public List<Profesor> ejercicio6ProfesoresPorEspecialidadOrdenados() {
        String especialidad = "telematica";
        return profesorService.profesoresPorEspecialidadOrdenados(especialidad);
    }

    @GetMapping("/profesor/especialidad")
    public List<Profesor> getProfesoresPorEspecialidad() {
        return profesorService.profesoresPorEspecialidadOrdenados("telematica");
    }

    // Ejercicio 7: Estudiantes por dominio de correo (ej. "@icesi.edu.co", sin distinguir mayúsculas)
    @GetMapping("/ejercicio7")
    public List<Estudiante> ejercicio7EstudiantesPorDominioCorreo() {
        String dominio = "@icesi.edu.co";
        return estudianteService.estudiantesPorDominioCorreo(dominio);
    }

    @GetMapping("/estudiante/dominio")
    public List<Estudiante> getEstudiantesPorDominio() {
        return estudianteService.estudiantesPorDominioCorreo("@icesi.edu.co");
    }

    // Ejercicio 8: Conteo de estudiantes activos (active = true)
    @GetMapping("/ejercicio8")
    public Long ejercicio8ConteoEstudiantesActivos() {
        return estudianteService.contarEstudiantesActivos();
    }

    @GetMapping("/estudiante/conteo/activos")
    public Long getConteoEstudiantesActivos() {
        return estudianteService.contarEstudiantesActivos();
    }

    // Ejercicio 9: Cursos asignados a un profesor (ManyToOne a partir del id)
    @GetMapping("/ejercicio9")
    public List<Curso> ejercicio9CursosPorProfesor() {
        Long profesorId = 3L;
        return cursoService.cursosPorProfesorId(profesorId);
    }

    @GetMapping("/curso/profesor")
    public List<Curso> getCursosPorProfesor() {
        return cursoService.cursosPorProfesorId(3L);
    }

    // Ejercicio 10: Cursos según el departamento del profesor (sin distinguir mayúsculas)
    @GetMapping("/ejercicio10")
    public List<Curso> ejercicio10CursosPorDeptoProfesor() {
        String depto = "computación y sistemas inteligentes";
        return cursoService.cursosPorDepartamentoProfesor(depto);
    }

    @GetMapping("/curso/profesor/depto")
    public List<Curso> getCursosPorDepartamentoProfesor() {
        return cursoService.cursosPorDepartamentoProfesor("computación y sistemas inteligentes");
    }

    // Ejercicio 11: Usuarios activos por nombre de rol (ManyToMany, sin distinguir mayúsculas)
    @GetMapping("/ejercicio11")
    public List<Usuario> ejercicio11UsuariosActivosPorRol() {
        String nombreRol = "docente";
        return userService.usuariosActivosPorRol(nombreRol);
    }

    @GetMapping("/usuario/activos/rol")
    public List<Usuario> getUsuariosActivosPorRol() {
        return userService.usuariosActivosPorRol("docente");
    }

    // Ejercicio 12: Verificar matrícula en tabla intermedia (retorna boolean)
    @GetMapping("/ejercicio12")
    public boolean ejercicio12VerificarMatricula() {
        Long estudianteId = 1L;
        Long cursoId = 1L;
        return matriculaService.estaMatriculado(estudianteId, cursoId);
    }

    @GetMapping("/matricula")
    public boolean estaMatriculado() {
        return matriculaService.estaMatriculado(1L, 1L);
    }

    // Ejercicio 13: Cursos con créditos mayores o iguales a un valor mínimo, ordenados por créditos desc
    @GetMapping("/ejercicio13")
    public List<Curso> ejercicio13CursosPorCreditosMinimos() {
        int creditosMinimos = 3;
        return cursoService.cursosPorCreditosMinimosOrdenados(creditosMinimos);
    }

    @GetMapping("/curso/creditos-minimos")
    public List<Curso> getCursosPorCreditosMinimos() {
        return cursoService.cursosPorCreditosMinimosOrdenados(3);
    }

    // Ejercicio 14: Permisos de un rol (ManyToMany inversa, sin distinguir mayúsculas)
    @GetMapping("/ejercicio14")
    public List<Permiso> ejercicio14PermisosPorRol() {
        String nombreRol = "admin";
        return permisoService.permisosPorNombreRol(nombreRol);
    }

    @GetMapping("/permiso/rol")
    public List<Permiso> getPermisosPorRol() {
        return permisoService.permisosPorNombreRol("admin");
    }

    // Ejercicio 15 (JPQL): Estudiantes activos matriculados en un curso ordenados por apellido asc
    @GetMapping("/ejercicio15/jpql")
    public List<Estudiante> ejercicio15EstudiantesCursoJPQL() {
        Long cursoId = 1L;
        return estudianteService.estudiantesActivosPorCursoJPQL(cursoId);
    }

    @GetMapping("/estudiante/curso/jpql")
    public List<Estudiante> getEstudiantesCursoJPQL() {
        return estudianteService.estudiantesActivosPorCursoJPQL(1L);
    }

    // Ejercicio 15 (Native SQL): Estudiantes activos matriculados en un curso ordenados por apellido asc
    @GetMapping("/ejercicio15/native")
    public List<Estudiante> ejercicio15EstudiantesCursoNative() {
        Long cursoId = 1L;
        return estudianteService.estudiantesActivosPorCursoNative(cursoId);
    }

    @GetMapping("/estudiante/curso/native")
    public List<Estudiante> getEstudiantesCursoNative() {
        return estudianteService.estudiantesActivosPorCursoNative(1L);
    }

}

