package com.utp.controlador;

import com.google.gson.Gson;
import com.utp.modelo.AsignacionDAO;
import com.utp.modelo.AsistenciaDAO;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletInicio", urlPatterns = {"/ServletInicio"})
public class ServletInicio extends HttpServlet {

    AsignacionDAO asigDAO = new AsignacionDAO();
    AsistenciaDAO asisDAO = new AsistenciaDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion"); // Para identificar la acción específica
        int id;
        try {
            switch (menu) {
                case "principal":
                    id = Integer.parseInt(request.getParameter("id"));
                    if ("verificar".equals(accion)) {
                        verificarAsistencia(request, id);
                    } else if ("actualizar".equals(accion)) {
                        actualizarAsistencia(request, id);
                    } 

                    // Obtener asignaciones del técnico y enviarlas como JSON
                    List<HashMap<String, Object>> eventos = asigDAO.asignacionesTecnico(id);
                    String eventosJson = new Gson().toJson(eventos);
                    request.setAttribute("eventosJson", eventosJson);
                    request.getRequestDispatcher("VMInicio.jsp").forward(request, response);
                    break;
            }
        } catch (IOException | NumberFormatException | ServletException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en el procesamiento");
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

    private void verificarAsistencia(HttpServletRequest request, int id) {
        int resp = asisDAO.verificarRegistro(id);
        request.setAttribute("marca", resp);
    }

    private void actualizarAsistencia(HttpServletRequest request, int id) {
        int resp = asisDAO.actualizarHoraSalida(id);
        
        request.getSession().setAttribute("marca", resp);
    }

    private void registrarAsistencia(HttpServletRequest request, int id) {

    }
}
