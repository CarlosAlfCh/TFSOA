package com.utp.controlador;

import com.google.gson.Gson;
import com.utp.modelo.AsignacionDAO;
import com.utp.modelo.UsuarioDAO;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletAsignar", urlPatterns = {"/ServletAsignar"})
public class ServletAsignar extends HttpServlet {

    AsignacionDAO asignaDAO = new AsignacionDAO();
    UsuarioDAO userDAO = new UsuarioDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {
            switch (accion) {
                case "listar":
                    List listaReserva = asignaDAO.listasignacion();
                    List listatecnicos = userDAO.listar();
                    request.setAttribute("reservas", listaReserva);
                    request.setAttribute("tecnicos", listatecnicos);
                    request.getRequestDispatcher("VMAsignar.jsp").forward(request, response);
                    break;
                case "asignar":
                    asignarTecnico(request, response);
                    break;
                case "borrar":
                    quitarTecnico(request, response);
                    break;
                case "horario":
                    horarioTecnico(request, response);
                    break;
                case "eliminar":
                    //eliminaReserva(request, response);
                    break;
                default:
                    System.out.println("No se pudo Asignar :C");
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

    private void asignarTecnico(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int idTecnico = Integer.parseInt(request.getParameter("txtidtecnico"));
        int idReserva = Integer.parseInt(request.getParameter("idreserva"));
        asignaDAO.asignarTecnico(idReserva, idTecnico);
        request.getRequestDispatcher("ServletAsignar?accion=listar").forward(request, response);
    }

    private void quitarTecnico(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int idReserva = Integer.parseInt(request.getParameter("idreserva"));
        asignaDAO.quitar(idReserva);
        request.getRequestDispatcher("ServletAsignar?accion=listar").forward(request, response);
    }

    private void horarioTecnico(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            List<HashMap<String, Object>> eventos = asignaDAO.asignacionesTecnico(id);

            // Convertir la lista a JSON para FullCalendar
            String eventosJson = new Gson().toJson(eventos);

            // Pasar el JSON al JSP
            request.setAttribute("eventosJson", eventosJson);
            request.getRequestDispatcher("VMHorarioXTecnico.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El ID del técnico es inválido.");
        } catch (IOException | ServletException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error procesando el horario.");
        }
    }
}
