package com.utp.modelo;

import com.utp.entidad.pagos.Reserva;
import com.utp.entidad.info.DetalleReserva;
import com.utp.util.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    public int GenerarReserva(Reserva reserva) {
        int r = 0;
        String sql = "INSERT INTO reserva(id_cliente, id_pago, f_reserva, monto, estado, f_atencion, f_check_in, f_check_out) VALUES(?,?,?,?,?,?,?,?)";

        // Usamos try-with-resources para gestionar la conexión y los statements
        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            conn.setAutoCommit(false); // Iniciamos la transacción

            ps.setInt(1, reserva.getIdcliente());
            ps.setInt(2, reserva.getIdpago());
            ps.setString(3, reserva.getFecha());
            ps.setDouble(4, reserva.getMonto());
            ps.setInt(5, 1); // Estado activo de la reserva
            ps.setDate(6, reserva.getFechaServicio() != null ? Date.valueOf(reserva.getFechaServicio()) : null);
            ps.setDate(7, reserva.getFechaIngreso() != null ? Date.valueOf(reserva.getFechaIngreso()) : null);
            ps.setDate(8, reserva.getFechaSalida() != null ? Date.valueOf(reserva.getFechaSalida()) : null);

            r = ps.executeUpdate();

            if (r > 0) {
                // Obtener el id_reserva generado
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idreserva = rs.getInt(1);  // Recupera el id_reserva generado automáticamente
                        System.out.println("id reserva " + idreserva);

                        // Insertar detalles de servicio y habitación
                        for (DetalleReserva detalle : reserva.getDetalleService()) {
                            if (detalle.getIdservicio() != 0) {
                                sql = "INSERT INTO detalleservicios(id_reserva, id_servicio, f_atencion, npersonas, subtotal, total) VALUES(?,?,?,?,?,?)";
                                try (PreparedStatement psDetalle = conn.prepareStatement(sql)) {
                                    psDetalle.setInt(1, idreserva);
                                    psDetalle.setInt(2, detalle.getIdservicio());
                                    psDetalle.setDate(3, Date.valueOf(detalle.getFechaServicio()));
                                    psDetalle.setInt(4, detalle.getNpersonas());
                                    psDetalle.setDouble(5, detalle.getSubtotal());
                                    psDetalle.setDouble(6, detalle.getTotal());
                                    psDetalle.executeUpdate();
                                    System.out.println(detalle.getIdservicio() + " -s");
                                }
                            }
                            if (detalle.getIdhabitacion() != 0) {
                                sql = "INSERT INTO detalleroom(id_reserva, id_habitacion, f_inicio, f_salida, nnoches, subtotal, total) VALUES(?,?,?,?,?,?,?)";
                                try (PreparedStatement psRoom = conn.prepareStatement(sql)) {
                                    psRoom.setInt(1, idreserva);
                                    psRoom.setInt(2, detalle.getIdhabitacion());
                                    psRoom.setDate(3, Date.valueOf(detalle.getFechaIngreso()));
                                    psRoom.setDate(4, Date.valueOf(detalle.getFechaSalida()));
                                    psRoom.setInt(5, detalle.getNnoches());
                                    psRoom.setDouble(6, detalle.getSubtotal());
                                    psRoom.setDouble(7, detalle.getTotal());
                                    psRoom.executeUpdate();
                                    System.out.println(detalle.getIdhabitacion() + " -h");
                                }
                            }
                        }

                        conn.commit(); // Commit de la transacción si todo es exitoso
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e + " - Error al generar la reserva");

        }

        return r;
    }

    public List<Reserva> listares() {
        List<Reserva> list = new ArrayList<>();
        String sql = "SELECT id_reserva, id_cliente, id_tecnico, id_pago, f_reserva, monto, estado FROM reserva"; // Especificamos las columnas necesarias

        // Usamos try-with-resources para asegurar que los recursos se cierren automáticamente
        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Reserva c = new Reserva();
                c.setIdreserva(rs.getInt("id_reserva"));
                c.setIdcliente(rs.getInt("id_cliente"));
                c.setIdtecnico(rs.getInt("id_tecnico"));
                c.setIdpago(rs.getInt("id_pago"));
                c.setFecha(rs.getString("f_reserva"));
                c.setMonto(rs.getDouble("monto"));
                c.setEstado(rs.getInt("estado"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar reservas: " + e.getMessage());
        }

        return list;
    }

    public List<Reserva> listasign(int codigo) {
        List<Reserva> list = new ArrayList<>();
        String sql = "SELECT id_reserva, id_cliente, id_tecnico, id_pago, f_reserva, monto, estado FROM reserva WHERE id_tecnico = ?"; // Usamos parámetros

        // Usamos try-with-resources para asegurar que los recursos se cierren automáticamente
        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, codigo);  // Establecemos el valor del parámetro de forma segura
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Reserva c = new Reserva();
                    c.setIdreserva(rs.getInt("id_reserva"));
                    c.setIdcliente(rs.getInt("id_cliente"));
                    c.setIdtecnico(rs.getInt("id_tecnico"));
                    c.setIdpago(rs.getInt("id_pago"));
                    c.setFecha(rs.getString("f_reserva"));
                    c.setMonto(rs.getDouble("monto"));
                    c.setEstado(rs.getInt("estado"));

                    list.add(c);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener reservas: " + e.getMessage());
        }
        return list;
    }

    public Reserva seleccionado(int codigo) {
        Reserva c = new Reserva();
        String sql = "SELECT id_reserva, f_reserva, monto FROM reserva WHERE id_reserva = ?"; // Especificamos las columnas necesarias

        // Usamos try-with-resources para gestionar la conexión y los recursos automáticamente
        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, codigo);  // Establecemos el valor del parámetro de forma segura
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) { // Usamos if en lugar de while para obtener solo un resultado
                    c.setIdreserva(rs.getInt("id_reserva"));
                    c.setFecha(rs.getString("f_reserva"));
                    c.setMonto(rs.getDouble("monto"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener reserva: " + e.getMessage());
        }

        return c;
    }

}
