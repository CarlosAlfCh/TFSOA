package com.utp.controlador;

import com.utp.mensajes.DiscountPromotions;
import com.utp.modelo.ClienteDAO;
import com.utp.modelo.RewardsDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletTarifas", urlPatterns = {"/ServletTarifas"})
public class ServletTarifas extends HttpServlet {

    RewardsDAO rewardsDAO = new RewardsDAO();
    ClienteDAO cliendao = new ClienteDAO();

    DiscountPromotions promotions = new DiscountPromotions();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {
            switch (accion) {
                case "listar":
                    List listaclientespromo = rewardsDAO.listarClientesPromo();
                    List listaclientes = cliendao.listar();
                    request.setAttribute("promocionesxcliente", listaclientespromo);
                    request.setAttribute("clientes", listaclientes);
                    request.getRequestDispatcher("VMPromociones.jsp").forward(request, response);
                    break;
                case "agregar":
                    addClientePromo(request, response);
                    break;
                case "actualizar":
                    updateCodigoPromo(request, response);
                    break;
                case "invalidar":
                    revokeCodigoPromo(request, response);
                    break;
                default:
                    System.out.println("-");
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

    private void addClientePromo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int idcliente = Integer.parseInt(request.getParameter("txtidcliente"));
        int descuento = Integer.parseInt(request.getParameter("txtdescuento"));
        String correo = request.getParameter("txtcorreo");

        promotions.enviarCodigoPromocion(correo, idcliente, descuento);
        request.getRequestDispatcher("ServletTarifas?accion=listar").forward(request, response);
    }

    private void updateCodigoPromo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int idcliente = Integer.parseInt(request.getParameter("txtidcliente"));
        int descuento = Integer.parseInt(request.getParameter("txtdescuento"));
        String correo = request.getParameter("txtcorreo");
        promotions.enviarNuevoCodigoPromocion(correo, idcliente, descuento);
        request.getRequestDispatcher("ServletTarifas?accion=listar").forward(request, response);
    }

    private void revokeCodigoPromo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int idcliente = Integer.parseInt(request.getParameter("idcliente"));
        rewardsDAO.invalidarCodPromo(idcliente);
        request.getRequestDispatcher("ServletTarifas?accion=listar").forward(request, response);
        
    }

}
