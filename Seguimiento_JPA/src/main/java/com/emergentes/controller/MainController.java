package com.emergentes.controller;

import com.emergentes.bean.BeanSeguimiento;
import com.emergentes.entidades.Estudiante;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("ESTAMOS EN EL SERVLET");
        String action = request.getParameter("action");
        if (action == null) {
            action = "listar";
        }

        switch (action) {
            case "nuevo":
                request.setAttribute("estudiante", new Estudiante());
                request.getRequestDispatcher("frmestudiante.jsp").forward(request, response);
                break;
            case "editar":
                Long id = Long.parseLong(request.getParameter("id"));
                BeanSeguimiento dao = new BeanSeguimiento();
                Estudiante estudiante = dao.buscar(id);
                request.setAttribute("estudiante", estudiante);
                request.getRequestDispatcher("frmestudiante.jsp").forward(request, response);
                break;
            case "eliminar":
                eliminar(request, response);
                break;
            default:
                mostrar(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        String fechaNacimientoStr = request.getParameter("fechaNacimiento");

        // Convertimos la cadena de texto a un objeto DateD
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaNacimiento = null;
        try {
            fechaNacimiento = dateFormat.parse(fechaNacimientoStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Estudiante estudiante = new Estudiante();
        estudiante.setId(id);
        estudiante.setNombre(nombre);
        estudiante.setApellidos(apellidos);
        estudiante.setEmail(email);
        estudiante.setFechaNacimiento(fechaNacimiento);

        BeanSeguimiento dao = new BeanSeguimiento();
        if (id == 0) {
            dao.insertar(estudiante);
        } else {
            dao.editar(estudiante);
        }

        response.sendRedirect("MainController");
    }

    private void mostrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BeanSeguimiento dao = new BeanSeguimiento();
        List<Estudiante> lista = dao.listarTodos();
        request.setAttribute("estudiantes", lista);
        request.getRequestDispatcher("estudiantes.jsp").forward(request, response);
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        BeanSeguimiento dao = new BeanSeguimiento();
        dao.eliminar(id);
        response.sendRedirect("MainController");
    }

    private void nuevo() {
        BeanSeguimiento dao = new BeanSeguimiento();
        Estudiante e = new Estudiante();
        e.setNombre("Maria Antonieta");
        e.setApellidos("Perez");
        e.setEmail("maria@gmail.com");
        e.setFechaNacimiento(java.sql.Date.valueOf("1990-02-01"));

        dao.insertar(e);
    }

    private void editar() {
        BeanSeguimiento dao = new BeanSeguimiento();
        //Id para editar
        Long id = 2L;
        Estudiante e = dao.buscar(id);
        e.setNombre("Jhoselin Lucia Huaycho Laura");
        e.setEmail("jho.jlhl@gmail.com");
        dao.editar(e);
    }
}
