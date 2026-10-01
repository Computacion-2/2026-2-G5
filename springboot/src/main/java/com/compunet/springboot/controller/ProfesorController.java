package com.compunet.springboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.compunet.springboot.model.Profesor;
import com.compunet.springboot.service.ProfesorService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    @GetMapping
    public String listarProfesores(Model model) {
        List<Profesor> profesores = profesorService.findAll();
        model.addAttribute("titulo", "Sistema de Gestión Académica - Lista de Profesores");
        model.addAttribute("profesores", profesores);
        return "profesores/lista";
    }

    @GetMapping("/desactivar/{id}")
    public String alternarEstadoProfesor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Profesor p = profesorService.alternarEstado(id);
            String estado = p.isActive() ? "activado" : "desactivado";
            redirectAttributes.addFlashAttribute("exito", "Profesor " + estado + " correctamente");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/profesores";
    }
}
