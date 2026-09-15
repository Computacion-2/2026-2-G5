package com.compunet.springboot.controller;

import org.springframework.web.bind.annotation.RestController;

import com.compunet.springboot.model.Curso;
import com.compunet.springboot.model.EstudianteCurso;
import com.compunet.springboot.model.Profesor;
import com.compunet.springboot.model.Usuario;
import com.compunet.springboot.repository.CursoRepository;
import com.compunet.springboot.repository.EstudianteCursoRepository;
import com.compunet.springboot.repository.ProfesorRepository;
import com.compunet.springboot.repository.UsuarioRepository;
import com.compunet.springboot.service.ProfesorService;
import com.compunet.springboot.service.UsuarioService;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class Controller {

    private ProfesorRepository profeRepo;
    private CursoRepository cursoRepo;
    private EstudianteCursoRepository ecRepo;
    private UsuarioService userService;
    private ProfesorService profesorService;
    
    @Autowired
    public Controller (ProfesorRepository prepository, CursoRepository crepository,
                       EstudianteCursoRepository ecRepository, UsuarioService userService,
                        ProfesorService profeService) {
        this.profeRepo = prepository;
        this.cursoRepo = crepository;
        this.ecRepo = ecRepository;
        this.userService = userService;
        this.profesorService = profeService;
    }

    @GetMapping("/")
    public String home() {
        return new String("Proyecto spring boot funcionando correctamente");
    }
    

    @GetMapping("/profesor")
    public List<Profesor> getProfesores(){
        return profeRepo.findAll();

    }

    @GetMapping("/profesor/depto")
    public List<Profesor> getProfesoresDepto(){
        return profesorService.profesoresPorDepto("Computación y sistemas inteligentes");

    }

    @GetMapping("/profesor/depto/active")
    public List<Profesor> getProfesoresDeptoActive(){
        return profesorService.listarProfesoresActivos("Computación y sistemas inteligentes");
    }

    @GetMapping("/curso")
    public List<Curso> getCursos(){
        return cursoRepo.findAll();
        
    }

    @GetMapping("/estudiante_curso")
    public List<EstudianteCurso> getEstudianteCurso(){
        return ecRepo.findAll();
        
    }

    @GetMapping("/usuarios")
    public List <Usuario> getUsuarios(){
        return userService.findAll();
    }

    @GetMapping("/usuario/correo")
    public Optional<Usuario> getUsuarioPorCorreoInstitucional(){
        return userService.porCorreoInstitucional("juan.perez@icesi.edu.co");
    }

    @GetMapping("/usuario/existe/correo")
    public boolean existeUsuarioPorCorreoInstitucional(){
        return userService.existePorCorreoInstitucional("maria.gomez@icedsi.edu.co");
    }

    
}
