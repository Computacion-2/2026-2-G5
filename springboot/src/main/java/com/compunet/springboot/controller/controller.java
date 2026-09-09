package com.compunet.springboot.controller;

import org.springframework.web.bind.annotation.RestController;

import com.compunet.springboot.model.Curso;
import com.compunet.springboot.model.EstudianteCurso;
import com.compunet.springboot.model.Profesor;
import com.compunet.springboot.repository.CursoRepository;
import com.compunet.springboot.repository.EstudianteCursoRepository;
import com.compunet.springboot.repository.ProfesorRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class Controller {

    private ProfesorRepository profeRepo;
    private CursoRepository cursoRepo;
    private EstudianteCursoRepository ecRepo;
    
    @Autowired
    public Controller (ProfesorRepository prepository, CursoRepository crepository, EstudianteCursoRepository ecRepository) {
        this.profeRepo = prepository;
        this.cursoRepo = crepository;
        this.ecRepo = ecRepository;
    }

    @GetMapping("/")
    public String home() {
        return new String("Proyecto spring boot funcionando correctamente");
    }
    

    @GetMapping("/profesor")
    public List<Profesor> getProfesores(){
        return profeRepo.findAll();

    }

    @GetMapping("/curso")
    public List<Curso> getCursos(){
        return cursoRepo.findAll();
        
    }

    @GetMapping("/estudiante_curso")
    public List<EstudianteCurso> getEstudianteCurso(){
        return ecRepo.findAll();
        
    }

    
}
