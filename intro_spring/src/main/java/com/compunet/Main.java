package com.compunet;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.compunet.model.Estudiante;
import com.compunet.service.EstudianteService;

public class Main {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        EstudianteService estudianteService = (EstudianteService) context.getBean("estudianteServiceSetterBean");
        
        List<Estudiante> estudiantes = estudianteService.listarEstudiantes();

        for (Estudiante estudiante : estudiantes) {

            System.out.println("Estudiante registrado: " + "ID: " + estudiante.getId() + " nombre: " + estudiante.getNombre() + " Correo: " + estudiante.getCorreo());
            
        }

        context.close();
    }
    
}
