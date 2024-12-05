package com.utp.controlador;

import com.google.gson.Gson;
import com.utp.modelo.AsignacionDAO;
import com.utp.modelo.HabitacionDAO;
import com.utp.modelo.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletHorarios", urlPatterns = {"/ServletHorarios"})
public class ServletHorarios extends HttpServlet {

    AsignacionDAO asigDAO = new AsignacionDAO();
    UsuarioDAO userDAO = new UsuarioDAO();
    HabitacionDAO roomDAO = new HabitacionDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");

        try {
            switch (accion) {
                case "listar":
                    List listatec = userDAO.listar();
                    List listaroom = roomDAO.listar();
                    request.setAttribute("tecnicos", listatec);
                    request.setAttribute("rooms", listaroom);
                    request.getRequestDispatcher("VMHorarios.jsp").forward(request, response);
                    break;
                case "tecnico":
                    horarioTecnico(request, response);
                    break;
                case "room":
                    horarioRoom(request, response);
                    break;
            }
        } catch (Exception e) {
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

    private void horarioTecnico(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        List<HashMap<String, Object>> eventos = asigDAO.asignacionesTecnico(id);
        String eventosJson = new Gson().toJson(eventos);
        request.setAttribute("eventosJson", eventosJson);
        request.getRequestDispatcher("VMHorarioXTecnico.jsp").forward(request, response);
    }

    private void horarioRoom(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        List<HashMap<String, Object>> eventos = asigDAO.asignacionesRoom(id);
        String eventosJson = new Gson().toJson(eventos);
        request.setAttribute("eventosJson", eventosJson);
        request.getRequestDispatcher("VMHorarioXHabitacion.jsp").forward(request, response);
    }

}
