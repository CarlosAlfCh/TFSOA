package com.utp.controlador;

import com.utp.entidad.extras.Especialidad;
import com.utp.modelo.ComentarioDAO;
import com.utp.modelo.EspecialidadDAO;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletExtras", urlPatterns = {"/ServletExtras"})
public class ServletExtras extends HttpServlet {

    Especialidad especialidad = new Especialidad();
    EspecialidadDAO especDAO = new EspecialidadDAO();
    ComentarioDAO comentDAO = new ComentarioDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        try {
            switch (menu) {
                case "especialidad":
                    switch (accion) {
                        case "listar":
                            List lista = especDAO.listar();
                            request.setAttribute("especiales", lista);
                            break;
                        case "agregar":
                            addespecial(request, response);
                            break;
                        case "modificar":
                            modespecial(request, response);
                            break;
                        case "eliminar":
                            deletespecial(request, response);
                            break;
                        case "activar":
                            activespecial(request, response);
                            break;
                        case "desactivar":
                            desactivespecial(request, response);
                            break;
                        default:
                            System.out.println("No se pudo :C");
                    }
                    request.getRequestDispatcher("VMExtras.jsp").forward(request, response);
                    break;
                case "comentarios":
                    switch (accion) {
                        case "listar":
                            List listacoment = comentDAO.listar();
                            request.setAttribute("comentarios", listacoment);
                        break;
                        case "eliminar":
                            deletecoment(request, response);
                        break;
                    }
                    request.getRequestDispatcher("VMComents.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void addespecial(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String nombre = request.getParameter("txtnombre");
        String descripcion = request.getParameter("txtdescripcion");
        double salario = Double.parseDouble(request.getParameter("txtsalario"));

        // Configura los datos de la nueva especialidad
        especialidad.setNombre(nombre);
        especialidad.setDescripcion(descripcion);
        especialidad.setSalario(salario);
        especialidad.setFechaactualizacion(LocalDate.now()); // Fecha de actualización = fecha actual

        int result = especDAO.insertar(especialidad);

        if (result > 0) {
            System.out.println("Especialidad agregada correctamente.");
        } else {
            System.out.println("Error al agregar especialidad.");
        }

        response.sendRedirect("ServletExtras?menu=especialidad&accion=listar");
    }

    private void modespecial(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("txtnombre");
        String descripcion = request.getParameter("txtdescripcion");
        double salario = Double.parseDouble(request.getParameter("txtsalario"));

        // Configura los datos actualizados
        especialidad.setIdespecialidad(id);
        especialidad.setNombre(nombre);
        especialidad.setDescripcion(descripcion);
        especialidad.setSalario(salario);
        especialidad.setFechaactualizacion(LocalDate.now()); // Fecha de actualización = fecha actual

        int result = especDAO.modificar(especialidad);

        if (result > 0) {
            System.out.println("Especialidad actualizada correctamente.");
        } else {
            System.out.println("Error al actualizar especialidad.");
        }

        response.sendRedirect("ServletExtras?menu=especialidad&accion=listar");
    }

    private void deletespecial(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));

        int result = especDAO.eliminar(id);

        if (result > 0) {
            System.out.println("Especialidad eliminada correctamente.");
        } else {
            System.out.println("Error al eliminar especialidad.");
        }

        response.sendRedirect("ServletExtras?menu=especialidad&accion=listar");
    }

    private void activespecial(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));

        int result = especDAO.activar(id);

        if (result > 0) {
            System.out.println("Especialidad activada correctamente.");
        } else {
            System.out.println("Error al activar especialidad.");
        }

        response.sendRedirect("ServletExtras?menu=especialidad&accion=listar");
    }

    private void desactivespecial(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));

        // Actualiza la fecha de actualización para reflejar el cambio
        especialidad.setFechaactualizacion(LocalDate.now());

        int result = especDAO.desactivar(id);

        if (result > 0) {
            System.out.println("Especialidad desactivada correctamente.");
        } else {
            System.out.println("Error al desactivar especialidad.");
        }

        response.sendRedirect("ServletExtras?menu=especialidad&accion=listar");
    }

    private void deletecoment(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        comentDAO.eliminar(id);
        response.sendRedirect("ServletExtras?menu=comentarios&accion=listar");
    }

}
