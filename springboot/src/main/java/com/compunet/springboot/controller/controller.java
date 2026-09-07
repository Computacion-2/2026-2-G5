package com.compunet.springboot.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class controller {

    @GetMapping("/")
    public String home() {
        return new String("Proyecto spring boot funcionando correctamente");
    }
    

    
}
