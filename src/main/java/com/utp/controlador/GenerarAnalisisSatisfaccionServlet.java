package com.utp.controlador;

import com.utp.modelo.ComentarioDAO;
import com.utp.entidad.extras.Comentario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/GenerarAnalisisSatisfaccionServlet")
public class GenerarAnalisisSatisfaccionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(GenerarAnalisisSatisfaccionServlet.class.getName());
    private final ComentarioDAO comentarioDAO = new ComentarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Iniciando método processRequest");
        try {
            // Obtener datos de comentarios
            List<Comentario> comentarios = comentarioDAO.listar();
            logger.info("Datos obtenidos en processRequest: " + comentarios);

            // Pasar los datos al JSP
            request.setAttribute("comentarios", comentarios);
            request.getRequestDispatcher("/analisisSatisfaccion.jsp").forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en el método processRequest", e);
            throw new ServletException("Error al obtener los datos del análisis de satisfacción del cliente", e);
        }
    }
}
