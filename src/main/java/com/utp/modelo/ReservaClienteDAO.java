package com.utp.modelo;

import com.utp.entidad.pagos.Reserva;
import com.utp.entidad.info.DetalleReserva;
import com.utp.util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaClienteDAO {

    Conexion cn = new Conexion();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int r = 0;

    public List<Reserva> listares(int idclienete) {
        ArrayList<Reserva> list = new ArrayList<>();
        String sql = "SELECT * FROM reserva WHERE id_cliente=" + idclienete + " ORDER BY id_reserva DESC";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setIdreserva(rs.getInt("id_reserva"));
                reserva.setIdcliente(rs.getInt("id_cliente"));
                reserva.setIdtecnico(rs.getInt("id_tecnico"));
                reserva.setIdpago(rs.getInt("id_pago"));
                reserva.setFecha(rs.getString("f_reserva"));
                reserva.setMonto(rs.getDouble("monto"));
                reserva.setFechaServicio(rs.getObject("f_atencion", LocalDate.class));
                reserva.setFechaIngreso(rs.getObject("f_check_in", LocalDate.class));
                reserva.setFechaSalida(rs.getObject("f_check_out", LocalDate.class));
                reserva.setEstado(rs.getInt("estado"));
                list.add(reserva);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Reserva miReserva(int idreserva) {
        Reserva reserva = new Reserva();
        String sql = "SELECT * FROM reserva WHERE id_reserva=" + idreserva;
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                reserva.setIdreserva(rs.getInt("id_reserva"));
                reserva.setIdcliente(rs.getInt("id_cliente"));
                reserva.setIdtecnico(rs.getInt("id_tecnico"));
                reserva.setIdpago(rs.getInt("id_pago"));
                reserva.setFecha(rs.getString("f_reserva"));
                reserva.setMonto(rs.getDouble("monto"));
                reserva.setFechaServicio(rs.getObject("f_atencion", LocalDate.class));
                reserva.setFechaIngreso(rs.getObject("f_check_in", LocalDate.class));
                reserva.setFechaSalida(rs.getObject("f_check_out", LocalDate.class));
                reserva.setEstado(rs.getInt("estado"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return reserva;
    }

    public List<DetalleReserva> detalle(int idreserva) {
        ArrayList<DetalleReserva> list = new ArrayList<>();
        String tipo, piso, descripcion;

        String consultaroom = "select habitacion.tipo_habita , habitacion.piso , habitacion.descripcion , detalleroom.id_habitacion , "
                + "detalleroom.f_inicio , detalleroom.f_salida , detalleroom.nnoches , detalleroom.subtotal , detalleroom.total "
                + " from habitacion INNER JOIN detalleroom ON habitacion.id_habitacion = detalleroom.id_habitacion "
                + " WHERE detalleroom.id_reserva=" + idreserva;
        
        String consultaserv = "SELECT servicio.nom_serv, servicio.descripcion , detalleservicios.id_servicio , "
                + "detalleservicios.f_atencion , detalleservicios.npersonas , detalleservicios.subtotal , detalleservicios.total FROM servicio "
                + " INNER JOIN detalleservicios on servicio.id_servicio = detalleservicios.id_servicio "
                + " WHERE detalleservicios.id_reserva=" + idreserva;
        try {
            conn = cn.conectar();

            ps = conn.prepareStatement(consultaroom);
            rs = ps.executeQuery();
            while (rs.next()) {
                DetalleReserva detalle = new DetalleReserva();
                tipo = rs.getString(1);
                piso = rs.getString(2);
                descripcion = rs.getString(3);
                descripcion = "<h4><i class=\"fas fa-bed me-2\"></i> Habitación: " + tipo + "</h4>"
                        + "<p class=\"card-text text-muted mb-0\">Piso: " + piso
                        + "<br/>Descripción: <br/>" + descripcion + "</p>";

                detalle.setDescripcion(descripcion);
                
                detalle.setIdhabitacion(rs.getInt(4));
                detalle.setFechaIngreso(rs.getObject(5, LocalDate.class));
                detalle.setFechaSalida(rs.getObject(6, LocalDate.class));
                detalle.setNnoches(rs.getInt(7));
                detalle.setSubtotal(rs.getInt(8));
                detalle.setTotal(rs.getInt(9));

                list.add(detalle);
            }

            ps = conn.prepareStatement(consultaserv);
            rs = ps.executeQuery();
            while (rs.next()) {
                DetalleReserva detalle = new DetalleReserva();
                detalle.setNombre(rs.getString(1));
                detalle.setDescripcion(rs.getString(2));
                detalle.setIdservicio(rs.getInt(3));
                detalle.setFechaServicio(rs.getObject(4, LocalDate.class));
                detalle.setNpersonas(rs.getInt(5));
                detalle.setSubtotal(rs.getInt(6));
                detalle.setTotal(rs.getInt(7));

                list.add(detalle);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
