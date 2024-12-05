package com.utp.controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.utp.entidad.pagos.Reserva;
import com.utp.modelo.DetalleServicioDAO;
import com.utp.modelo.ReservaDAO;
import com.utp.modelo.PagoDAO;
import com.utp.modelo.UsuarioDAO;
import com.utp.mensajes.VerifyPayment;

/**
 *
 * @author LENOVO
 */

public class ServletCitas extends HttpServlet {
    
    PagoDAO pagodao = new PagoDAO();
    ReservaDAO resedao = new ReservaDAO();
    
    UsuarioDAO userdao = new UsuarioDAO();
    
    Reserva cit = new Reserva();
    
    VerifyPayment payment = new VerifyPayment();
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        
        try {
            if(menu!=null){
                switch (menu) {
                    case "listar":
                        List listpago = pagodao.listarpagos();
                        request.setAttribute("pagos", listpago);
                        request.getRequestDispatcher("VMPagos.jsp").forward(request, response);
                        break;
                    case "validar":
                        validarpago(request, response);
                        break;
                    case "error":
                        errorvalidar(request, response);
                        break;
                    case "confirmar":
                        confirmar(request, response);
                        break;
                    default:
                        System.out.println("Tiempo Agotado :C");
                }
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

    private void validarpago(HttpServletRequest request, HttpServletResponse response)throws Exception{
        int idpago = Integer.parseInt(request.getParameter("idpago"));
        System.out.println(idpago);
        pagodao.pagovalido(idpago);
        request.getRequestDispatcher("ServletCitas?menu=listar").forward(request, response);
    }
    private void errorvalidar(HttpServletRequest request, HttpServletResponse response)throws Exception{
        int idpago = Integer.parseInt(request.getParameter("idpago"));
        System.out.println(idpago);
        pagodao.pagoinvalido(idpago);
        request.getRequestDispatcher("ServletCitas?menu=listar").forward(request, response);
    }
    private void eraser(HttpServletRequest request, HttpServletResponse response)throws Exception{
        int idreserva = Integer.parseInt(request.getParameter("id"));
        System.out.println(idreserva);
        //resedao.quitar(idreserva);
        request.getRequestDispatcher("VMAsignar.jsp").forward(request, response); 
    }
    private void seleccion(HttpServletRequest request, HttpServletResponse response)throws Exception{
        int idreserva = Integer.parseInt(request.getParameter("id"));
        System.out.println(idreserva);
        cit = resedao.seleccionado(idreserva);
        System.out.println(cit.getFecha());
        //List listdet = detdao.listar(idreserva);
        //request.setAttribute("detalles", listdet); 
        List listec = userdao.listar();        
        request.setAttribute("tecnicos", listec); 
        request.setAttribute("select", cit);
        request.getRequestDispatcher("ModAsignar.jsp").forward(request, response);
    }
    private void assing(HttpServletRequest request, HttpServletResponse response)throws Exception{
        int idreserva = Integer.parseInt(request.getParameter("idres"));
        int idtecnico = Integer.parseInt(request.getParameter("idtec"));
        System.out.println(idtecnico);
        //resedao.asignar(idreserva, idtecnico);
        request.getRequestDispatcher("VMAsignar.jsp").forward(request, response);
    }
    private void vereser(HttpServletRequest request, HttpServletResponse response)throws Exception{
        int idtecnico = Integer.parseInt(request.getParameter("idtecnic"));
        //List listres = detdao.listconsigna(idtecnico);
        //request.setAttribute("veres", listres);        
        request.getRequestDispatcher("VTAsignacion.jsp").forward(request, response);
    }
    private void inforeser(HttpServletRequest request, HttpServletResponse response)throws Exception{
        int idreserva = Integer.parseInt(request.getParameter("id"));
        System.out.println(idreserva);
        cit = resedao.seleccionado(idreserva);
        System.out.println(cit.getFecha());
        //List listdet = detdao.listar(idreserva);
        //request.setAttribute("detalles", listdet);  
        request.setAttribute("select", cit);
        request.getRequestDispatcher("VTView.jsp").forward(request, response);
    }
    private void okconsigna(HttpServletRequest request, HttpServletResponse response)throws Exception{
        int idtecnico = Integer.parseInt(request.getParameter("idtecnic"));
        int idreserva = Integer.parseInt(request.getParameter("id"));
        System.out.println(idreserva);
        //resedao.marcar(idreserva);
        //List listres = detdao.listconsigna(idtecnico);
        //request.setAttribute("veres", listres);
        request.getRequestDispatcher("VTAsignacion.jsp").forward(request, response);
    }
    
    private void confirmar(HttpServletRequest request, HttpServletResponse response)throws Exception{
        String correo = request.getParameter("correo");
        String idpago = request.getParameter("id"); 
        String metodo = request.getParameter("metodo");
        String fecha = request.getParameter("fecha");
        payment.confirmacionPago(correo, idpago, metodo, fecha);
        
        request.getRequestDispatcher("ServletCitas?menu=listar").forward(request, response);
    }
}
