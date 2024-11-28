package com.utp.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.utp.entidad.info.DetalleReserva;
import com.utp.entidad.pagos.Reserva;
import com.utp.modelo.ReservaClienteDAO;
import java.time.LocalDate;

@WebServlet(name = "ServletServiceCliente", urlPatterns = {"/ServletServiceCliente"})
public class ServletServiceCliente extends HttpServlet {

    Reserva reserva = new Reserva();
    List<DetalleReserva> listaServices = new ArrayList<>();
    ReservaClienteDAO consultarreserva = new ReservaClienteDAO();

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
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

//        // Lista de eventos simulados
//        List<HashMap<String, String>> eventos = new ArrayList<>();
//        
//        // Crear evento con rango de fechas
//        HashMap<String, String> evento1 = new HashMap<>();
//        evento1.put("id", "1");
//        evento1.put("title", "Reserva de Sala");
//        evento1.put("start", LocalDate.of(2024, 11, 20).toString()); // Fecha de inicio
//        evento1.put("end", LocalDate.of(2024, 11, 25).toString());   // Fecha de fin
//        eventos.add(evento1);
//
//        HashMap<String, String> evento2 = new HashMap<>();
//        evento2.put("id", "2");
//        evento2.put("title", "Evento Especial");
//        evento2.put("start", LocalDate.of(2024, 11, 26).toString());
//        evento2.put("end", LocalDate.of(2024, 11, 30).toString());
//        eventos.add(evento2);
//
//        // Convertir lista a JSON
//        String eventosJson = new Gson().toJson(eventos);
//        request.setAttribute("eventosJson", eventosJson);
//        // Redirigimos a la página JSP
//        request.getRequestDispatcher("Cita.jsp").forward(request, response);
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
        
        descuento = (int) ((montodescuento/subtotal)*100);

        totalPagar = subtotal - montodescuento;

        request.setAttribute("subtotal", subtotal);
        request.setAttribute("porcentaje", descuento);
        request.setAttribute("descuento", montodescuento);
        request.setAttribute("totalpagar", totalPagar);
        
        request.setAttribute("reserva", reserva);
        request.setAttribute("detalle", listaServices);

        request.getRequestDispatcher("Cita.jsp").forward(request, response);

    }

}
