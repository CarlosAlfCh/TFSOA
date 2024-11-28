package com.utp.modelo;

import com.utp.entidad.info.DetalleReserva;
import com.utp.util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleServicioDAO {

    Conexion cn = new Conexion();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<DetalleReserva> listReservaRoom() {
        ArrayList<DetalleReserva> list = new ArrayList<>();
        String sql = "SELECT h.id_habitacion, h.tipo_habita AS nombre, h.piso, h.precioxnoche AS subtotal, "
                + " COUNT(dr.id_habitacion) AS item FROM habitacion h LEFT JOIN detalleroom dr "
                + " ON h.id_habitacion = dr.id_habitacion "
                + " GROUP BY h.id_habitacion, h.tipo_habita, h.piso, h.precioxnoche;";

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                DetalleReserva detalle = new DetalleReserva();

                detalle.setIdhabitacion(rs.getInt("id_habitacion")); // ID de la habitación.
                detalle.setNombre(rs.getString("nombre"));          // Tipo de habitación.
                detalle.setDescripcion("Piso: " + rs.getString("piso"));// Descripción de la habitación.
                detalle.setSubtotal(rs.getDouble("subtotal"));      // Precio por noche.
                detalle.setItem(rs.getInt("item"));                 // Veces ocupada (contador).

                list.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime el error para depuración.
        } 
        return list;
    }

    public List<DetalleReserva> getAsignacionesPorHabitacion(int idHabitacion) {
        ArrayList<DetalleReserva> list = new ArrayList<>();
        String sql = "SELECT dr.id_reserva, dr.id_habitacion, dr.f_inicio AS fechaIngreso, dr.f_salida AS fechaSalida, "
                + " dr.nnoches, dr.subtotal, dr.total FROM detalleroom dr WHERE dr.id_habitacion = ?;";

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idHabitacion); // Establecemos el ID de la habitación como parámetro.
            rs = ps.executeQuery();

            while (rs.next()) {
                DetalleReserva detalle = new DetalleReserva();

                detalle.setIdservicio(rs.getInt("id_reserva")); // ID de reserva.
                detalle.setIdhabitacion(rs.getInt("id_habitacion")); // ID de la habitación.
                detalle.setFechaIngreso(rs.getDate("fechaIngreso").toLocalDate()); // Fecha de inicio.
                detalle.setFechaSalida(rs.getDate("fechaSalida").toLocalDate());   // Fecha de salida.
                detalle.setNnoches(rs.getInt("nnoches"));       // Número de noches.
                detalle.setSubtotal(rs.getDouble("subtotal"));  // Subtotal.
                detalle.setTotal(rs.getDouble("total"));        // Total.

                list.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprimir errores para depuración.
        } 
        return list;
    }

    public List<DetalleReserva> listReservaServicio() {
        ArrayList<DetalleReserva> list = new ArrayList<>();
        String sql = "SELECT s.id_servicio, s.nom_serv AS nombre_servicio, s.descripcion, s.precio AS subtotal, "
                + " COUNT(ds.id_servicio) AS item FROM servicio s LEFT JOIN detalleservicios ds "
                + " ON s.id_servicio = ds.id_servicio "
                + " GROUP BY s.id_servicio, s.nom_serv, s.descripcion, s.precio;";

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                DetalleReserva detalle = new DetalleReserva();

                detalle.setIdservicio(rs.getInt("id_servicio")); // ID del servicio.
                detalle.setNombre(rs.getString("nombre_servicio")); // Nombre del servicio.
                detalle.setDescripcion(rs.getString("descripcion")); // Descripción del servicio.
                detalle.setSubtotal(rs.getDouble("subtotal")); // Precio del servicio.
                detalle.setItem(rs.getInt("item")); // Veces reservado (contador).

                list.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores.
        } 
        return list;
    }

    public List<DetalleReserva> listReservasPorServicio(int idServicio) {
        ArrayList<DetalleReserva> list = new ArrayList<>();
        String sql = "SELECT ds.id_detalle, ds.id_reserva, ds.f_atencion, ds.npersonas, ds.subtotal, ds.total "
                + " FROM detalleservicios ds WHERE ds.id_servicio = ?;";

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idServicio); // Establece el ID del servicio en la consulta.
            rs = ps.executeQuery();

            while (rs.next()) {
                DetalleReserva detalle = new DetalleReserva();

                detalle.setItem(rs.getInt("id_detalle")); // ID del detalle (utilizado como contador).
                detalle.setIdservicio(idServicio); // ID del servicio.
                detalle.setIdhabitacion(rs.getInt("id_reserva")); // ID de la reserva (en este contexto).
                detalle.setFechaServicio(rs.getDate("f_atencion").toLocalDate()); // Fecha de atención.
                detalle.setNpersonas(rs.getInt("npersonas")); // Número de personas.
                detalle.setSubtotal(rs.getDouble("subtotal")); // Subtotal de la reserva.
                detalle.setTotal(rs.getDouble("total")); // Total de la reserva.

                list.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de errores.
        }
        return list;
    }

}
