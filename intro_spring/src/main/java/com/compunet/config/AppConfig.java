package com.compunet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.compunet.repository.EstudianteRepository;
import com.compunet.service.EstudianteServiceSetterImpl;

@Configuration
@ComponentScan("com.compunet")
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    public String nombreAplicacion() {
        return "Sistema de Gestión de Estudiantes";
    }
    
    // Ejemplo de instanciación de un bean que tiene inyección de dependecias por metodo setter
    @Bean()
    public EstudianteServiceSetterImpl estudianteServiceSetterImpl(EstudianteRepository estudianteRepository){
        EstudianteServiceSetterImpl service = new EstudianteServiceSetterImpl();
        service.setEstudianteRepository(estudianteRepository);
        return service;
    }
}
