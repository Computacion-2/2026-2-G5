package com.compunet.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.compunet.model.Estudiante;
import com.compunet.service.EstudianteService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/estudiante")
public class EstudianteServlet extends HttpServlet {

    private EstudianteService estudianteService;

    @Override
    public void init() throws ServletException {
        super.init();

        WebApplicationContext context = WebApplicationContextUtils
                .getRequiredWebApplicationContext(
                        getServletContext());

        this.estudianteService = context.getBean(EstudianteService.class);


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        List<Estudiante> estudiantes = estudianteService.listarEstudiantes();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>GESTION DE ESTUDIANTES - SPRING + SERVLETS</title></head><body>");
        out.println("<h1>Gestion de Estudiantes - spring </h1>");

        out.println("<h2> Registrar Nuevo Estudiante</h2>");
        out.println("<form action='/intro_spring/estudiante' method='POST'>");
        out.println("<label>Nombre: </label><br/>");
        out.println("<input type='text' name='nombre' required/><br/>");
        out.println("<label>Correo: </label><br/>");
        out.println("<input type='text' name='correo' required/><br/>");
        out.println("<button type='submit'>Guradar Estudiante</button>");
        out.println("</form>");

        out.println("<hr/>");
        out.println("<h2> Lista de Estudiantes</h2>");
        out.println("<ul>");
        for (Estudiante estudiante : estudiantes) {
            out.println("<li>" + estudiante.getNombre() + " - " + estudiante.getCorreo() + "</li>");
        }
        out.println("</ul>");
        out.println("</body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");

        String idGenerado = UUID.randomUUID().toString().substring(0, 8);
        Estudiante estudiante = new Estudiante(idGenerado, nombre, correo);

        estudianteService.registrarEstudiante(estudiante);
        response.sendRedirect(request.getContextPath() + "/estudiante");
    }

}
