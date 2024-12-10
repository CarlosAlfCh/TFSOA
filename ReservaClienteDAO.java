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

    public List<Reserva> listares(int idCliente) {
        ArrayList<Reserva> list = new ArrayList<>();
        String sql = "SELECT id_reserva, id_cliente, id_tecnico, id_pago, f_reserva, monto, f_atencion, f_check_in, f_check_out, estado FROM reserva WHERE id_cliente = ? ORDER BY id_reserva DESC";

        // Usamos try-with-resources para gestionar la conexión y los recursos automáticamente
        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCliente);  // Establecemos el parámetro de forma segura
            try (ResultSet rs = ps.executeQuery()) {
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
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener reservas: " + e.getMessage());
        }

        return list;
    }

    public Reserva miReserva(int idReserva) {
        Reserva reserva = new Reserva();
        String sql = "SELECT id_reserva, id_cliente, id_tecnico, id_pago, f_reserva, monto, f_atencion, f_check_in, f_check_out, estado FROM reserva WHERE id_reserva = ?";

        // Usamos try-with-resources para garantizar el cierre de recursos automáticamente
        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idReserva);  // Establecemos el parámetro de manera segura

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {  // Si hay un resultado, asignamos los valores
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
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener la reserva: " + e.getMessage());
        }

        return reserva;
    }

    public List<DetalleReserva> detalle(int idReserva) {
        List<DetalleReserva> list = new ArrayList<>();

        String consultaroom = "SELECT habitacion.tipo_habita, habitacion.piso, habitacion.descripcion, detalleroom.id_habitacion, "
                + "detalleroom.f_inicio, detalleroom.f_salida, detalleroom.nnoches, detalleroom.subtotal, detalleroom.total "
                + "FROM habitacion "
                + "INNER JOIN detalleroom ON habitacion.id_habitacion = detalleroom.id_habitacion "
                + "WHERE detalleroom.id_reserva = ?";  // Usar parámetros en lugar de concatenar la variable directamente

        String consultaserv = "SELECT servicio.nom_serv, servicio.descripcion, detalleservicios.id_servicio, "
                + "detalleservicios.f_atencion, detalleservicios.npersonas, detalleservicios.subtotal, detalleservicios.total "
                + "FROM servicio "
                + "INNER JOIN detalleservicios ON servicio.id_servicio = detalleservicios.id_servicio "
                + "WHERE detalleservicios.id_reserva = ?";  // Usar parámetros también aquí

        try (Connection conn = Conexion.getConnection(); PreparedStatement psRoom = conn.prepareStatement(consultaroom); PreparedStatement psServ = conn.prepareStatement(consultaserv)) {

            // Establecer parámetros en las consultas
            psRoom.setInt(1, idReserva);
            psServ.setInt(1, idReserva);

            // Ejecutar y procesar los resultados de la consulta de habitaciones
            try (ResultSet rsRoom = psRoom.executeQuery()) {
                while (rsRoom.next()) {
                    DetalleReserva detalle = new DetalleReserva();
                    String tipo = rsRoom.getString(1);
                    String piso = rsRoom.getString(2);
                    String descripcion = rsRoom.getString(3);
                    descripcion = "<h4><i class=\"fas fa-bed me-2\"></i> Habitación: " + tipo + "</h4>"
                            + "<p class=\"card-text text-muted mb-0\">Piso: " + piso
                            + "<br/>Descripción: <br/>" + descripcion + "</p>";

                    detalle.setDescripcion(descripcion);
                    detalle.setIdhabitacion(rsRoom.getInt(4));
                    detalle.setFechaIngreso(rsRoom.getObject(5, LocalDate.class));
                    detalle.setFechaSalida(rsRoom.getObject(6, LocalDate.class));
                    detalle.setNnoches(rsRoom.getInt(7));
                    detalle.setSubtotal(rsRoom.getInt(8));
                    detalle.setTotal(rsRoom.getInt(9));

                    list.add(detalle);
                }
            }

            // Ejecutar y procesar los resultados de la consulta de servicios
            try (ResultSet rsServ = psServ.executeQuery()) {
                while (rsServ.next()) {
                    DetalleReserva detalle = new DetalleReserva();
                    detalle.setNombre(rsServ.getString(1));
                    detalle.setDescripcion(rsServ.getString(2));
                    detalle.setIdservicio(rsServ.getInt(3));
                    detalle.setFechaServicio(rsServ.getObject(4, LocalDate.class));
                    detalle.setNpersonas(rsServ.getInt(5));
                    detalle.setSubtotal(rsServ.getInt(6));
                    detalle.setTotal(rsServ.getInt(7));

                    list.add(detalle);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener los detalles de la reserva: " + e.getMessage());
        }

        return list;
    }

}
