package com.utp.modelo;

import com.utp.entidad.info.Asignacion;
import com.utp.util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AsignacionDAO {

    Conexion cn = new Conexion();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Asignacion> listasignacion() {
        ArrayList<Asignacion> list = new ArrayList<>();
        String sql = "SELECT r.id_reserva, r.id_pago, r.f_reserva, r.f_atencion, r.monto, r.estado, "
                + " c.nombres AS nombre_cliente, c.apelpat AS apellido_paterno_cliente, c.apelmat AS apellido_materno_cliente, "
                + " t.nombres AS nombre_tecnico, t.apelpat AS apellido_paterno_tecnico, t.apelmat AS apellido_materno_tecnico "
                + " FROM reserva r "
                + " INNER JOIN persona c ON r.id_cliente = c.codigo "
                + " LEFT JOIN persona t ON r.id_tecnico = t.codigo "
                + " WHERE r.f_atencion IS NOT NULL";

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Asignacion asignacion = new Asignacion();

                asignacion.setIdreserva(rs.getInt("id_reserva"));
                asignacion.setIdpago(rs.getInt("id_pago"));
                asignacion.setFecha(rs.getString("f_reserva"));
                asignacion.setFechaServicio(rs.getDate("f_atencion").toLocalDate());
                asignacion.setMonto(rs.getDouble("monto"));
                asignacion.setEstado(rs.getInt("estado"));
                // Concatenar nombre y apellidos para cliente.
                asignacion.setNomCliente(rs.getString("nombre_cliente") + " "
                        + rs.getString("apellido_paterno_cliente") + " "
                        + rs.getString("apellido_materno_cliente"));

                // Concatenar nombre y apellidos para técnico.
                String nomTecnico = (rs.getString("nombre_tecnico") != null ? rs.getString("nombre_tecnico") : "") + " "
                        + (rs.getString("apellido_paterno_tecnico") != null ? rs.getString("apellido_paterno_tecnico") : "") + " "
                        + (rs.getString("apellido_materno_tecnico") != null ? rs.getString("apellido_materno_tecnico") : "");

// Si no se tiene ningún nombre o apellido, establecer "No asignado"
                if (nomTecnico.trim().isEmpty()) {
                    nomTecnico = "No asignado";
                }

                asignacion.setNomTecnico(nomTecnico);

                list.add(asignacion);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Mostrar errores en consola para depuración.
        }
        System.out.println(list.size());
        return list;
    }

    public List<Asignacion> asignacionesTecnico(int idtecnico) {

        ArrayList<Asignacion> list = new ArrayList<>();
        String sql = "SELECT r.id_reserva, r.id_pago, r.f_reserva, r.f_atencion, r.monto, r.estado, "
                + " c.nombres AS nombre_cliente, c.apelpat AS apellido_cliente, c.apelmat AS apellido_cliente, "
                + " t.nombres AS nombre_tecnico, t.apelpat AS apellido_tecnico, t.apelmat AS apellido_tecnico "
                + " FROM reserva r "
                + " INNER JOIN persona c ON r.id_cliente = c.codigo "
                + " INNER JOIN persona t ON r.id_tecnico = t.codigo "
                + " WHERE r.f_atencion IS NOT NULL AND t.codigo = ?";

        try {
            conn = cn.conectar(); // Asume que tienes una conexión válida.
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idtecnico); // Asignar el parámetro idtecnico en la consulta.
            rs = ps.executeQuery();

            while (rs.next()) {
                Asignacion asignacion = new Asignacion();

                asignacion.setIdreserva(rs.getInt("id_reserva"));
                asignacion.setIdpago(rs.getInt("id_pago"));
                asignacion.setFecha(rs.getString("f_reserva")); // Asumimos que fecha es un String.
                asignacion.setFechaServicio(rs.getDate("f_atencion").toLocalDate()); // Conversión a LocalDate.
                asignacion.setMonto(rs.getDouble("monto"));
                asignacion.setEstado(rs.getInt("estado"));

                // Concatenar nombre y apellidos para cliente.
                asignacion.setNomCliente(rs.getString("nombre_cliente") + " "
                        + rs.getString("apellido_cliente") + " "
                        + rs.getString("apellido_cliente"));

                // Concatenar nombre y apellidos para técnico.
                asignacion.setNomTecnico(rs.getString("nombre_tecnico") + " "
                        + rs.getString("apellido_tecnico") + " "
                        + rs.getString("apellido_tecnico"));

                list.add(asignacion);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Mostrar errores en consola para depuración.
        }
        return list;
    }

    public int asignarTecnico(int idreserva, int idtecnico) {
        int r = 0;
        String sql = "UPDATE reserva SET id_tecnico=? WHERE id_reserva=?;";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, idtecnico);
            ps.setInt(2, idreserva);

            r = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return r;
    }

    public int quitar(int idreserva) {
        int r = 0;
        String sql = "UPDATE reserva SET id_tecnico=? WHERE id_reserva=?;";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);

            ps.setNull(1, java.sql.Types.INTEGER);
            ps.setInt(2, idreserva);

            r = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return r;
    }

    public int marcar(int idreserva) {
        int r = 0;
        String sql = "UPDATE reserva SET estado=? WHERE id_reserva=" + idreserva;
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, 0);

            r = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return r;
    }

}
