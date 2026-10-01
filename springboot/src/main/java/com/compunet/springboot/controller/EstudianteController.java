package com.compunet.springboot.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.compunet.springboot.model.Estudiante;
import com.compunet.springboot.service.EstudianteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    @GetMapping
    public String listarEstudiantes(Model model) {
        List<Estudiante> estudiantes = estudianteService.findAll();
        model.addAttribute("titulo", "Sistema de Gestión Académica - Lista de Estudiantes");
        model.addAttribute("estudiantes", estudiantes);
        return "estudiantes/lista";
    }

    @GetMapping("/desactivar/{id}")
    public String alternarEstadoEstudiante(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Estudiante e = estudianteService.alternarEstado(id);
            String estado = e.isActive() ? "activado" : "desactivado";
            redirectAttributes.addFlashAttribute("exito", "Estudiante " + estado + " correctamente");
        } catch (IllegalArgumentException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
        }
        return "redirect:/estudiantes";
    }
}
