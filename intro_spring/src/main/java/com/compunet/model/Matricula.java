package com.compunet.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Matricula {

    private Estudiante estudiante;
    private List<String> cursos;

    public Matricula() {

        cursos = new ArrayList<>();
        System.out.println("--- [Spring] Instanciando una nueva matricula (" + System.identityHashCode(this) + ") ---");

    }

    public void agregarEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public void agregarMateria(String materia){
        cursos.add(materia);
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public List<String> getCursos() {
        return cursos;
    }
    
}
