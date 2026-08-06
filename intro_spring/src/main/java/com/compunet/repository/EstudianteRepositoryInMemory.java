package com.compunet.repository;

import java.util.ArrayList;
import java.util.List;

import com.compunet.model.Estudiante;

public class EstudianteRepositoryInMemory implements EstudianteRepository{

    private List<Estudiante> InMemoryStudents;

    public EstudianteRepositoryInMemory(){
        InMemoryStudents = new ArrayList<>();
    };

    public void metodoInicial(){
        System.out.println("-> [LIFECYCLE] Inicializando Repositorio en Memoria <-");
        InMemoryStudents.add(new Estudiante("1", "pepito", "pepito@Icesi.edu.co"));
        InMemoryStudents.add(new Estudiante("2", "camilo", "camilo@Icesi.edu.co"));
        InMemoryStudents.add(new Estudiante("3", "roberto", "roberto@Icesi.edu.co"));
    }

    public void metodoFinal(){
        System.out.println("-> [LIFECYCLE] Finalizando Repositorio en Memoria <-");
    }
    
    @Override
    public List<Estudiante> obtenerTodos() {
        return InMemoryStudents;
    }

    @Override
    public void registrarEstudiante(Estudiante estudiante) {
        InMemoryStudents.add(estudiante);
    }
    
}
