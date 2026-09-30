package com.compunet.springboot.controller;

import com.compunet.springboot.repository.UsuarioRepository;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.compunet.springboot.model.Usuario;
import com.compunet.springboot.service.UsuarioService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> users = usuarioService.findAll();
        model.addAttribute("titulo", "Sistema de gestión academica");
        model.addAttribute("usuarios", users);

        return "usuarios/lista";
    }

    // vista del formulario

    @GetMapping("/nuevo")
    public String obtenerVistaDeFormulario(Model model) {
        Usuario nuevoUsuario = new Usuario();
        model.addAttribute("usuario", nuevoUsuario);
        return "usuarios/formulario";
    }

    /// crear
    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario,
                                 @RequestParam(value = "nombreRol", defaultValue = "ESTUDIANTE") String nombreRol,
                                  RedirectAttributes redirectAttributes) {
    try {
        if (usuario.getId() == null) {
            usuarioService.registrarUsuario(usuario, nombreRol);
            redirectAttributes.addFlashAttribute("exito", "Usuario creado exitosamente");
        } else {
            usuarioService.actualizarUsuario(usuario.getId(), usuario);
            redirectAttributes.addFlashAttribute("exito", "Usuario actualizado exitosamente");
        }
        

    } catch (IllegalArgumentException | IllegalStateException e){
        redirectAttributes.addFlashAttribute("error", e.getMessage());        
    }
        return "redirect:/usuarios";
    }

    /// Editar
    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable Long id,
            Model model,
            RedirectAttributes redirectAttributes) {
        Usuario user = usuarioService.obtenerPorId(id).orElse(null);
        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "No se encontro un usuario el id ingresado");

        }
        model.addAttribute("usuario", user);
        return "usuarios/formulario";
    }

    /// Desactivar
    ///
    @GetMapping("/desactivar/{id}")
    public String desactivarUsuario(@PathVariable Long id, RedirectAttributes redirectAttributes) {

        Usuario u = usuarioService.alternarEstado(id);
        String estado = u.isActive() ? "activado" : "desactivado";
        redirectAttributes.addFlashAttribute("exito", "Usuario " + estado + "correctamente");

        return "redirect:/usuarios";
    }

}
