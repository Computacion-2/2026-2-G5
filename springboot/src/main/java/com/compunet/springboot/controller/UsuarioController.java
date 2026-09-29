package com.compunet.springboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.compunet.springboot.model.Usuario;
import com.compunet.springboot.service.UsuarioService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor 
@RequestMapping ("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public String listarUsuarios (Model model){
        List<Usuario> users = usuarioService.findAll();
        model.addAttribute("titulo", "Sistema de gestión academica");
        model.addAttribute("usuarios", users);

        return "usuarios/lista";

    }
    
}
