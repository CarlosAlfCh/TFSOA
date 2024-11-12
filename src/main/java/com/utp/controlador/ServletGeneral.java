package com.utp.controlador;

import com.utp.entidad.Habitacion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.utp.entidad.pagos.Reserva;
import com.utp.entidad.Servicio;
import com.utp.entidad.info.DetalleReserva;
import com.utp.modelo.HabitacionDAO;
import com.utp.modelo.ServicioDAO;
import com.utp.modelo.ReservaDAO;
import com.utp.modelo.PagoDAO;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ServletGeneral extends HttpServlet {

    DetalleReserva detalle;

    //temporales carrito
    int itemserv = 0;
    int itemroom = 0;
    
    double totalPagar = 0.0;
    int cantidad;
    int idpago;

    List<DetalleReserva> listaServices = new ArrayList<>();

    //Servicio
    Servicio serv = new Servicio();
    ServicioDAO servdao = new ServicioDAO();
    int npersonas = 1;
    int idserv;
    LocalDate fservicio;

    //Hotel
    Habitacion room = new Habitacion();
    HabitacionDAO roomdao = new HabitacionDAO();
    int nnoches = 1;
    int idroom;
    LocalDate fechaIngreso;
    LocalDate fechaSalida;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        request.setAttribute("fservicio", fservicio);
        request.setAttribute("viewserv", itemserv);
        request.setAttribute("viewroom", itemroom);
        try {
            if (menu != null) {
                switch (menu) {
                    case "pay":
                        pagar(request, response);
                        break;
                    case "buy":
                        System.out.println("in");
                        actuaFechaXServicio(request, response);
                        generarcita(request, response);
                        System.out.println("out");
                        break;
                    case "fecha":
                        agendar(request, response);
                        break;
                    case "habitacion":
                        habitacion(request, response);
                        break;
                    case "ocupar":
                        reservaRoom(request, response);
                        break;
                    case "usuario":
                        switch (accion) {
                            case "logout":
                                if (request.getSession().getAttribute("cliente") != null) {
                                    System.out.println(request.getSession().getAttribute("cliente"));

                                    request.getSession().removeAttribute("cliente");

                                    itemserv=0;
                                    itemroom=0;
                                    listaServices = new ArrayList<>();
                                    idpago = 0;
                                    fservicio = null;
                                    fechaIngreso = null;
                                    fechaSalida = null;
                                    
                                    request.setAttribute("codpago", idpago);
                                    request.setAttribute("cart", listaServices);
                                    response.sendRedirect("login.jsp");
                                } else {
                                    response.sendRedirect("index.jsp");
                                }
                                break;
                            default:
                                System.out.println("No se pudo usuario:C");
                        }
                        break;
                    case "carrito":
                        switch (accion) {
                            case "comprar":
                                reservaService(request, response);
                                break;
                            case "agregar":
                                agregarservicio(request, response);
                                System.out.println("Agrego al carrito??");
                                break;
                            case "ver":
                                vereserva(request, response);
                                break;
                            case "eliminarserv":
                                eliminarservicio(request, response);
                                System.out.println("Elimino serv??");
                                break;
                            case "eliminaroom":
                                eliminaroom(request, response);
                                System.out.println("Elimino room??");
                                break;
                            case "volver":
                                request.setAttribute("cont", listaServices.size());
                                request.getRequestDispatcher("index.jsp").forward(request, response);
                                break;
                            case "add":
                                mas(request, response);
                                System.out.println("+");
                                break;
                            case "quita":
                                menos(request, response);
                                System.out.println("-");
                                break;
                            default:
                                System.out.println("No se pudo la reserva :C");
                        }
                        break;
                    default:
                        System.out.println("Tiempo Agotado cita :C");
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

    private void reservaService(HttpServletRequest request, HttpServletResponse response) throws Exception {
        totalPagar = 0.0;

        // Valor por defecto
        idserv = 0;

        String idservParam = request.getParameter("id");

        itemserv = itemserv + 1;
        detalle = new DetalleReserva();

        System.out.println(idservParam + " " + itemserv);

        detalle.setItem(itemserv);

        if (idservParam != null && !idservParam.isEmpty()) {
            idserv = Integer.parseInt(idservParam);

            serv = servdao.seleccionado(idserv);

            detalle.setIdservicio(serv.getIdservicio());
            detalle.setNombre(serv.getNomserv());
            detalle.setDescripcion(serv.getDescripcion());
            detalle.setSubtotal(serv.getPrecio());
            detalle.setNpersonas(npersonas);
            detalle.setTotal(npersonas * serv.getPrecio());
            listaServices.add(detalle);

        }

        for (int i = 0; i < listaServices.size(); i++) {
            totalPagar = totalPagar + listaServices.get(i).getTotal();
        }

        request.setAttribute("codpago", idpago);
        request.setAttribute("totalpagar", totalPagar);
        request.setAttribute("cart", listaServices);
        request.setAttribute("cont", listaServices.size());
        request.setAttribute("viewserv", itemserv);
        
        request.getRequestDispatcher("orders.jsp").forward(request, response);
    }

    private void reservaRoom(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Valor por defecto
        totalPagar = 0.0;
        idroom = 0;

        String idroomParam = request.getParameter("idroom");
        String fechaIngresoParam = request.getParameter("fechaIngreso"); // Parámetro de fecha de ingreso
        String fechaSalidaParam = request.getParameter("fechaSalida");   // Parámetro de fecha de salida

        fechaIngreso = LocalDate.parse(fechaIngresoParam);
        fechaSalida = LocalDate.parse(fechaSalidaParam);

        itemroom = itemroom + 1;
        detalle = new DetalleReserva();

        detalle.setItem(itemroom);

        // Calcular número de noches y asignarlo
        nnoches = (int) ChronoUnit.DAYS.between(fechaIngreso, fechaSalida);
        detalle.setNnoches(nnoches);

        if (idroomParam != null && !idroomParam.isEmpty()) {
            idroom = Integer.parseInt(idroomParam);

            room = roomdao.seleccionado(idroom);

            detalle.setIdhabitacion(room.getIdhabitacion());
            detalle.setDescripcion(room.obtenerInfoConcatenada());
            detalle.setSubtotal(room.getPrecioNoche());

            // Asignar las fechas al objeto DetalleReserva
            detalle.setFechaIngreso(fechaIngreso);
            detalle.setFechaSalida(fechaSalida);

            System.out.println(nnoches + " " + fechaIngreso + " " + fechaSalida);

            detalle.setTotal(room.getPrecioNoche() * nnoches);

            listaServices.add(detalle);

        }

        for (int i = 0; i < listaServices.size(); i++) {
            totalPagar = totalPagar + listaServices.get(i).getTotal();
        }

        request.setAttribute("codpago", idpago);
        request.setAttribute("totalpagar", totalPagar);
        request.setAttribute("cart", listaServices);
        request.setAttribute("cont", listaServices.size());
        request.setAttribute("viewroom", itemroom);
        request.getRequestDispatcher("orders.jsp").forward(request, response);
    }

    private void agregarservicio(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int pos = 0;
        cantidad = 1;
        idserv = Integer.parseInt(request.getParameter("id"));
        serv = servdao.seleccionado(idserv);

        if (!listaServices.isEmpty()) {
            for (int i = 0; i < listaServices.size(); i++) {
                if (idserv == listaServices.get(i).getIdservicio()) {
                    pos = i;
                }
            }
            if (idserv == listaServices.get(pos).getIdservicio()) {
                cantidad = listaServices.get(pos).getNpersonas() + cantidad;
                double total = listaServices.get(pos).getSubtotal() * cantidad;
                listaServices.get(pos).setNpersonas(cantidad);
                listaServices.get(pos).setTotal(total);
            } else {
                itemserv = itemserv + 1;
                detalle = new DetalleReserva();
                detalle.setItem(itemserv);
                detalle.setIdservicio(serv.getIdservicio());
                detalle.setNombre(serv.getNomserv());
                detalle.setDescripcion(serv.getDescripcion());
                detalle.setSubtotal(serv.getPrecio());
                detalle.setNpersonas(npersonas);
                detalle.setTotal(npersonas * serv.getPrecio());
                listaServices.add(detalle);
            }
        } else {
            itemserv = itemserv + 1;
            detalle = new DetalleReserva();
            detalle.setItem(itemserv);
            detalle.setIdservicio(serv.getIdservicio());
            detalle.setNombre(serv.getNomserv());
            detalle.setDescripcion(serv.getDescripcion());
            detalle.setSubtotal(serv.getPrecio());
            detalle.setNpersonas(npersonas);
            detalle.setTotal(npersonas * serv.getPrecio());
            listaServices.add(detalle);
        }
        request.setAttribute("cont", listaServices.size());
        request.setAttribute("viewserv", itemserv);
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

    private void vereserva(HttpServletRequest request, HttpServletResponse response) throws Exception {
        totalPagar = 0.0;

        for (int i = 0; i < listaServices.size(); i++) {
            totalPagar = totalPagar + listaServices.get(i).getTotal();
        }

        request.setAttribute("cart", listaServices);
        request.setAttribute("totalpagar", totalPagar);
        request.setAttribute("codpago", idpago);
        request.getRequestDispatcher("orders.jsp").forward(request, response);

    }

    private void eliminarservicio(HttpServletRequest request, HttpServletResponse response) throws Exception {

        totalPagar = 0.0;

        //ELiminar servicio de spa
        int id = Integer.parseInt(request.getParameter("idserv"));
        
        System.out.println(id);

        for (int i = 0; i < listaServices.size(); i++) {
            if (listaServices.get(i).getIdservicio() == id) {
                listaServices.remove(i);
                itemserv=itemserv-1;
            }
        }

        for (DetalleReserva reserva : listaServices) {
            totalPagar += reserva.getTotal();
        }
        request.setAttribute("codpago", idpago);
        request.setAttribute("totalpagar", totalPagar);
        request.setAttribute("cart", listaServices);
        request.setAttribute("cont", listaServices.size());
        request.setAttribute("viewserv", itemserv);
        request.getRequestDispatcher("orders.jsp").forward(request, response);
    }

    private void eliminaroom(HttpServletRequest request, HttpServletResponse response) throws Exception {

        totalPagar = 0.0;

        //ELiminar habitacion
        int id = Integer.parseInt(request.getParameter("idroom"));

        for (int i = 0; i < listaServices.size(); i++) {
            if (listaServices.get(i).getIdhabitacion() == id) {
                listaServices.remove(i);
                itemroom=itemroom-1;
            }
        }

        for (DetalleReserva reserva : listaServices) {
            totalPagar += reserva.getTotal();
        }
        request.setAttribute("codpago", idpago);
        request.setAttribute("totalpagar", totalPagar);
        request.setAttribute("cart", listaServices);
        request.setAttribute("cont", listaServices.size());
        request.setAttribute("viewroom", itemroom);
        request.getRequestDispatcher("orders.jsp").forward(request, response);
    }

    private void mas(HttpServletRequest request, HttpServletResponse response) throws Exception {
        totalPagar = 0.0;

        int posicion = -1;
        cantidad = 1;

        int idServicio = Integer.parseInt(request.getParameter("id"));

        for (int i = 0; i < listaServices.size(); i++) {
            if (idServicio == listaServices.get(i).getIdservicio()) {
                posicion = i;
                break;  // Se encontró la coincidencia, se puede salir del bucle
            }
        }

        if (posicion != -1) {
            cantidad += listaServices.get(posicion).getNpersonas();
            double total = listaServices.get(posicion).getSubtotal() * cantidad;
            listaServices.get(posicion).setNpersonas(cantidad);
            listaServices.get(posicion).setTotal(total);
        }

        for (DetalleReserva reserva : listaServices) {
            totalPagar += reserva.getTotal();
        }

        request.setAttribute("codpago", idpago);
        request.setAttribute("cart", listaServices);
        request.setAttribute("totalpagar", totalPagar);
        request.setAttribute("cont", listaServices.size());

        request.getRequestDispatcher("orders.jsp").forward(request, response);
    }

    private void menos(HttpServletRequest request, HttpServletResponse response) throws Exception {
        totalPagar = 0.0;

        int posicion = -1;
        cantidad = 1;

        int idServicio = Integer.parseInt(request.getParameter("id"));

        for (int i = 0; i < listaServices.size(); i++) {
            if (idServicio == listaServices.get(i).getIdservicio()) {
                posicion = i;
                break;  // Se encontró la coincidencia, se puede salir del bucle
            }
        }

        if (posicion != -1) {
            cantidad = listaServices.get(posicion).getNpersonas() - cantidad;
            double total = listaServices.get(posicion).getTotal() - listaServices.get(posicion).getSubtotal();
            listaServices.get(posicion).setNpersonas(cantidad);
            listaServices.get(posicion).setTotal(total);
        }

        for (DetalleReserva reserva : listaServices) {
            totalPagar += reserva.getTotal();
        }

        request.setAttribute("codpago", idpago);
        request.setAttribute("cart", listaServices);
        request.setAttribute("totalpagar", totalPagar);
        request.setAttribute("cont", listaServices.size());

        request.getRequestDispatcher("orders.jsp").forward(request, response);
    }

    private void pagar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        PagoDAO pagoDAO = new PagoDAO();

        String cod = request.getParameter("codi");
        String metodo = request.getParameter("forma");
        String fecha = request.getParameter("factual");

        idpago = pagoDAO.insertpago(cod, metodo, fecha);

        request.setAttribute("codpago", idpago);
        request.setAttribute("cart", listaServices);
        request.setAttribute("totalpagar", totalPagar);
        request.setAttribute("cont", listaServices.size());
        request.getRequestDispatcher("orders.jsp").forward(request, response);
    }

    private void generarcita(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String fecha = request.getParameter("txtfecha");
        int userID = Integer.parseInt(request.getParameter("code"));

        System.out.println(fservicio);
        System.out.println(fechaIngreso);
        System.out.println(fechaSalida);

        System.out.println(fecha);

        Reserva reserva = new Reserva();

        reserva.setIdcliente(userID);
        reserva.setIdpago(idpago);
        reserva.setFecha(fecha);

        reserva.setFechaServicio(fservicio);
        reserva.setFechaIngreso(fechaIngreso);
        reserva.setFechaSalida(fechaSalida);

        reserva.setDetalleService(listaServices);
        reserva.setMonto(totalPagar);

        if (totalPagar > 0) {

            ReservaDAO reservaDAO = new ReservaDAO();

            int r = reservaDAO.GenerarReserva(reserva);

            System.out.println(r);

            listaServices = new ArrayList<>();
            idpago = 0;
            fservicio = null;
            fechaIngreso = null;
            fechaSalida = null;

            request.setAttribute("codpago", idpago);
            request.setAttribute("cart", listaServices);

            // Si la reserva se generó exitosamente o no, redirige al final
            if (r != 0 || totalPagar <= 0) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    private void agendar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String fechaservicio = request.getParameter("txtfechaservice");
        fservicio = LocalDate.parse(fechaservicio);

        for (int i = 0; i < listaServices.size(); i++) {
            if (listaServices.get(i).getIdservicio() != 0) {
                listaServices.get(i).setFechaServicio(fservicio);
                System.out.println(listaServices.get(i).getIdservicio() + " " + listaServices.get(i).getFechaServicio());
            }
        }

        request.setAttribute("fservicio", fservicio);
        request.setAttribute("codpago", idpago);
        request.setAttribute("cart", listaServices);
        request.setAttribute("totalpagar", totalPagar);
        request.setAttribute("cont", listaServices.size());
        request.getRequestDispatcher("orders.jsp").forward(request, response);
    }

    private void actuaFechaXServicio(HttpServletRequest request, HttpServletResponse response) throws Exception {

        for (int i = 0; i < listaServices.size(); i++) {
            if (listaServices.get(i).getIdservicio() != 0) {
                listaServices.get(i).setFechaServicio(fservicio);
                System.out.println(listaServices.get(i).getIdservicio() + " " + listaServices.get(i).getFechaServicio());
            }
        }        
    }

    private void habitacion(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String idroomParam = request.getParameter("idroom");

        idroom = Integer.parseInt(idroomParam);

        room = roomdao.seleccionado(idroom);

        request.setAttribute("room", room);
        request.setAttribute("cont", listaServices.size());
        request.getRequestDispatcher("habitacion.jsp").forward(request, response);
    }
}
