package com.utp.controlador;

import com.utp.entidad.info.DetalleReserva;
import com.utp.modelo.AsignacionDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletTecnico", urlPatterns = {"/ServletTecnico"})
public class ServletTecnico extends HttpServlet {

    AsignacionDAO asignaDAO = new AsignacionDAO();
    int idtecnico;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        try {
            switch (accion) {
                case "listar":
                    idtecnico = Integer.parseInt(request.getParameter("id"));
                    List listaAsig = asignaDAO.listaAsignada(idtecnico);
                    request.setAttribute("pendientes", listaAsig);
                    request.getRequestDispatcher("VTAsignacion.jsp").forward(request, response);
                    break;
                case "marcar":
                    marcarVB(request, response);
                    break;
                case "desmarcar":
                    desmarcarVB(request, response);
                    break;
                case "detalle":
                    verDetalle(request, response);
                    break;
                case "historial":
                    idtecnico = Integer.parseInt(request.getParameter("id"));
                    List listaHistorial = asignaDAO.listaAsignada(idtecnico);
                    request.setAttribute("realizados", listaHistorial);
                    request.getRequestDispatcher("VTHistorial.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            System.err.println(e.toString());
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

    private void marcarVB(HttpServletRequest request, HttpServletResponse response) throws Exception{
        int id = Integer.parseInt(request.getParameter("idreserva"));
        asignaDAO.marcar(id);
        request.getRequestDispatcher("ServletTecnico?accion=listar&id="+idtecnico).forward(request, response);
    }

    private void desmarcarVB(HttpServletRequest request, HttpServletResponse response) throws Exception{
        int id = Integer.parseInt(request.getParameter("idreserva"));
        asignaDAO.desmarcar(id);
        request.getRequestDispatcher("ServletTecnico?accion=listar&id="+idtecnico).forward(request, response);
    }

    private void verDetalle(HttpServletRequest request, HttpServletResponse response) throws Exception{
        int id = Integer.parseInt(request.getParameter("idreserva"));
        int idpago = Integer.parseInt(request.getParameter("idpago"));
        List<DetalleReserva> listaReserva = asignaDAO.detalle(id);
        double total=0;
        for (int i = 0; i < listaReserva.size(); i++) {
            total = total + listaReserva.get(i).getTotal();
        }
        
        request.setAttribute("detalle", listaReserva);
        request.setAttribute("total", total);
        request.setAttribute("idpago", idpago);
        
        request.getRequestDispatcher("VTView.jsp").forward(request, response);
    }

}
