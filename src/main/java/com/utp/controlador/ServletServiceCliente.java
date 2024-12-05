package com.utp.controlador;

import com.utp.entidad.extras.Comentario;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.utp.entidad.info.DetalleReserva;
import com.utp.entidad.pagos.Reserva;
import com.utp.modelo.ComentarioDAO;
import com.utp.modelo.ReservaClienteDAO;

@WebServlet(name = "ServletServiceCliente", urlPatterns = {"/ServletServiceCliente"})
public class ServletServiceCliente extends HttpServlet {

    Reserva reserva = new Reserva();

    Comentario coment = new Comentario();

    List<DetalleReserva> listaServices = new ArrayList<>();
    List<Comentario> listaComents = new ArrayList<>();

    ReservaClienteDAO consultarreserva = new ReservaClienteDAO();
    ComentarioDAO comentsDAO = new ComentarioDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {
            switch (accion) {
                case "listar":
                    citas(request, response);
                    break;
                case "ver":
                    detalle(request, response);
                    break;
                case "comentarios":
                    listarComentarios(request, response);
                    break;
                case "comentar":
                    agregarComentario(request, response);
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

    private void citas(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int idcliente = Integer.parseInt(request.getParameter("idcliente"));
        List lista = consultarreserva.listares(idcliente);
        request.setAttribute("historial", lista);

        request.getRequestDispatcher("Historial.jsp").forward(request, response);
    }

    private void detalle(HttpServletRequest request, HttpServletResponse response) throws Exception {
        double montodescuento = 0.0;
        int descuento = 0;

        double subtotal = 0.0;

        double totalPagar = 0.0;

        int idreserva = Integer.parseInt(request.getParameter("idreserva"));
        reserva = consultarreserva.miReserva(idreserva);
        System.out.println(reserva.getFechaIngreso());
        System.out.println(reserva.getFechaSalida());
        listaServices = consultarreserva.detalle(idreserva);

        for (int i = 0; i < listaServices.size(); i++) {
            subtotal = subtotal + listaServices.get(i).getTotal();

        }

        System.out.println(subtotal);
        montodescuento = subtotal - reserva.getMonto();

        descuento = (int) ((montodescuento / subtotal) * 100);

        totalPagar = subtotal - montodescuento;

        request.setAttribute("subtotal", subtotal);
        request.setAttribute("porcentaje", descuento);
        request.setAttribute("descuento", montodescuento);
        request.setAttribute("totalpagar", totalPagar);

        request.setAttribute("reserva", reserva);
        request.setAttribute("detalle", listaServices);

        request.getRequestDispatcher("Cita.jsp").forward(request, response);

    }

    private void listarComentarios(HttpServletRequest request, HttpServletResponse response) throws Exception {
        listaComents = comentsDAO.listar();
        request.setAttribute("comentarios", listaComents);
        request.getRequestDispatcher("Foro.jsp").forward(request, response);
    }

    private void agregarComentario(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int idcliente = Integer.parseInt(request.getParameter("txtidcliente"));
        String comentario = request.getParameter("txtcomentario");
        int estrellas = Integer.parseInt(request.getParameter("txtestrellas"));
        coment.setIdPersona(idcliente);
        coment.setComentario(comentario);
        coment.setNestrellas(estrellas);
        comentsDAO.agregar(coment);
        response.sendRedirect("ServletServiceCliente?accion=comentarios");
    }

}
