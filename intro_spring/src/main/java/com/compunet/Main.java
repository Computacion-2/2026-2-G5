package com.compunet;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.compunet.config.AppConfig;
import com.compunet.service.LiquidacionMatriculaService;

public class Main {

    public static void main(String[] args) {
        System.out.println("Iniciando contenedor Spring con JavaConfig y SpEL...\n");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        LiquidacionMatriculaService liquidacionService = context.getBean(LiquidacionMatriculaService.class);

        liquidacionService.imprimirReporteLiquidacion();

        context.close();
    }

}
