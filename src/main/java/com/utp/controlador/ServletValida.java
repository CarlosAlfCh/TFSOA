package com.utp.controlador;

import com.utp.entidad.Cliente;
import com.utp.entidad.Usuario;
import com.utp.modelo.ClienteDAO;
import com.utp.modelo.ReservaDAO;
import com.utp.modelo.UsuarioDAO;
import com.utp.modelo.AutenticacionDAO;
import com.utp.mensajes.RecoverPassword;

import java.io.IOException;

import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ServletValida", urlPatterns = {"/ServletValida"})
public class ServletValida extends HttpServlet {

    Usuario user = new Usuario();
    UsuarioDAO userdao = new UsuarioDAO();
    AutenticacionDAO autenticacionDAO = new AutenticacionDAO();
    Cliente cliente = new Cliente();
    ClienteDAO clienteDAO = new ClienteDAO();
    ReservaDAO reservadao = new ReservaDAO();
    RecoverPassword recoverPassword = new RecoverPassword();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String metodo = request.getParameter("metodo");

        try {
            if (metodo != null) {
                switch (metodo) {
                    case "loguear":
                        verificar(request, response);
                        break;
                    case "registrar":
                        registrarse(request, response);
                        break;
                    case "enviar":
                        envia(request, response);
                        break;
                    case "recuperar":
                        restablece(request, response);
                        break;
                    case "exit":
                        logout(request, response);
                        break;
                    default:
                        System.out.println("Tiempo agotado :C");
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

    private void verificar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String correo = request.getParameter("txtcorreo");
        String contrasena = request.getParameter("txtcontrasena");

        // Verificar si los parámetros son nulos o vacíos
        if (correo == null || contrasena == null || correo.isEmpty() || contrasena.isEmpty()) {
            request.setAttribute("msj", "Por favor ingrese su correo y contraseña.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

        // Validar usuario
        Usuario user = autenticacionDAO.validar(correo, contrasena);

        if (user.getRol() == 0) {
            // Usuario no encontrado o credenciales incorrectas
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

        // Configurar objetos auxiliares
        ReservaDAO reservaDAO = new ReservaDAO();

        if (user.getRol() != 4) {
            // Usuario no cliente
            List listres = reservaDAO.listasign(user.getCodigo());
            List listmensaje = reservaDAO.listares();

            request.getSession().setAttribute("usuario", user);
            request.setAttribute("asig", listres);
            request.setAttribute("msj", listmensaje);

            request.getRequestDispatcher("ServletInicio?menu=principal&id="+user.getCodigo()).forward(request, response);
        } else {
            // Usuario cliente
            request.getSession().setAttribute("cliente", user);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }

    private void registrarse(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession sesion;

        String nombres = request.getParameter("txtnombres");
        String apelpat = request.getParameter("txtapelpat");
        String apelmat = request.getParameter("txtapelmat");
        String dni = request.getParameter("txtdni");
        String telefono = request.getParameter("txttelefono");

        String direccion = request.getParameter("txtdireccion");
        int distrito = Integer.parseInt(request.getParameter("txtdistrito"));
        String correo = request.getParameter("txtcorreo");
        String contrasena = request.getParameter("txtcontrasena");

        cliente.setNombres(nombres);
        cliente.setApelpat(apelpat);
        cliente.setApelmat(apelmat);
        cliente.setDni(dni);
        cliente.setTelefono(telefono);
        cliente.setDireccion(direccion);
        cliente.setDistrito(distrito);
        cliente.setCorreo(correo);
        cliente.setContrasena(contrasena);

        int resp = clienteDAO.insertar(cliente);

        request.setAttribute("cliente", cliente);

        sesion = request.getSession();
        sesion.setAttribute("cliente", cliente);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("cliente") != null) {
            System.out.println(request.getSession().getAttribute("cliente"));
            request.getSession().removeAttribute("cliente");
            request.getSession().removeAttribute("msj");
            request.getSession().removeAttribute("marca");
            request.getSession().removeAttribute("asig");
            response.sendRedirect("login.jsp");
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    private void envia(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String correo = request.getParameter("correo");
        recoverPassword.enviarCorreoRecuperacion(correo);
        request.getRequestDispatcher("RContra.jsp").forward(request, response);
    }

    private void restablece(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String clave = request.getParameter("clave");
        String pass = request.getParameter("txtcontrasena");
        recoverPassword.actualizarContrasena(clave, pass);
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
