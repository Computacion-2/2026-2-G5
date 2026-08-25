package com.compunet;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.compunet.config.AppConfig;
import com.compunet.model.Estudiante;
import com.compunet.service.EstudianteService;
import com.compunet.service.EstudianteServiceSetterImpl;

public class Main {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        

        String nombreApp = (String) context.getBean("nombreAplicacion");
        EstudianteServiceSetterImpl service = (EstudianteServiceSetterImpl) context.getBean("estudianteServiceSetterImpl");

        EstudianteService estudianteService = context.getBean("estudianteServiceImpl", EstudianteService.class);
        
        List<Estudiante> test = service.listarEstudiantes();
        List<Estudiante> estudiantes = estudianteService.listarEstudiantes();

        System.out.println("Nombre de mi aplicativo: " + nombreApp);

        for (Estudiante estudiante : estudiantes) {

            System.out.println("Estudiante registrado: " + "ID: " + estudiante.getId() + " nombre: " + estudiante.getNombre() + " Correo: " + estudiante.getCorreo());
            
        }

        context.close();
    }
    
}
