package com.utp.modelo;

import com.utp.entidad.info.Asignacion;
import com.utp.entidad.info.DetalleReserva;
import com.utp.util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AsignacionDAO {

    Conexion cn = new Conexion();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Asignacion> listasignacion() {
        ArrayList<Asignacion> list = new ArrayList<>();
        String sql = "SELECT r.id_reserva, r.id_pago, r.f_reserva, r.f_atencion, r.monto, r.estado, "
                + " c.nombres, c.apelpat, c.apelmat, t.nombres, t.apelpat, t.apelmat "
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

                // Concatenar nombre completo del cliente
                String nomCliente = rs.getString("c.nombres") + " "
                        + rs.getString("c.apelpat") + " "
                        + rs.getString("c.apelmat");
                asignacion.setNomCliente(nomCliente);

                // Concatenar nombre completo del técnico
                String nomTecnico = (rs.getString("t.nombres") != null ? rs.getString("t.nombres") : "") + " "
                        + (rs.getString("t.apelpat") != null ? rs.getString("t.apelpat") : "") + " "
                        + (rs.getString("t.apelmat") != null ? rs.getString("t.apelmat") : "");

                // Si no hay información del técnico, asignar "No asignado"
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

    public List<HashMap<String, Object>> asignacionesTecnico(int idtecnico) {
        List<HashMap<String, Object>> eventos = new ArrayList<>();
        String sql = "SELECT r.id_reserva, r.id_pago, r.f_reserva, r.f_atencion, r.monto, r.estado, "
                + " c.nombres AS cliente_nombre, c.apelpat AS cliente_apellido, "
                + " t.nombres AS tecnico_nombre, t.apelpat AS tecnico_apellido "
                + " FROM reserva r "
                + " INNER JOIN persona c ON r.id_cliente = c.codigo "
                + " INNER JOIN persona t ON r.id_tecnico = t.codigo "
                + " WHERE r.f_atencion IS NOT NULL AND t.codigo = ?";

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idtecnico);
            rs = ps.executeQuery();

            while (rs.next()) {
                HashMap<String, Object> evento = new HashMap<>();
                evento.put("id", rs.getInt("id_reserva"));
                evento.put("title", "Cliente: " + rs.getString("cliente_nombre") + " " + rs.getString("cliente_apellido")
                        + " | Técnico: " + rs.getString("tecnico_nombre") + " " + rs.getString("tecnico_apellido"));
                evento.put("start", rs.getDate("f_atencion").toLocalDate().toString());
                evento.put("end", rs.getDate("f_atencion").toLocalDate().plusDays(1).toString());

                HashMap<String, Object> extendedProps = new HashMap<>();
                extendedProps.put("monto", rs.getDouble("monto"));
                extendedProps.put("estado", rs.getInt("estado"));
                evento.put("extendedProps", extendedProps);

                eventos.add(evento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventos;
    }

    public List<HashMap<String, Object>> asignacionesRoom(int idroom) {
        List<HashMap<String, Object>> eventos = new ArrayList<>();
        String sql = "SELECT dr.id_reserva, dr.nnoches, dr.f_inicio, dr.f_salida, "
                + " r.id_pago, r.monto, "
                + " h.tipo_habita, h.piso, h.estado, "
                + " c.nombres AS nombre, c.apelpat AS apellido_paterno, c.apelmat AS apellido_materno "
                + " FROM detalleroom dr "
                + " INNER JOIN habitacion h ON dr.id_habitacion = h.id_habitacion "
                + " INNER JOIN reserva r ON dr.id_reserva = r.id_reserva "
                + " INNER JOIN persona c ON r.id_cliente = c.codigo "
                + " WHERE h.id_habitacion = ?";

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idroom);
            rs = ps.executeQuery();

            while (rs.next()) {
                HashMap<String, Object> evento = new HashMap<>();
                evento.put("id", rs.getInt("id_reserva"));
                evento.put("title", "N° Reserva 000000"+rs.getString("id_pago")+" Cliente: " + rs.getString("nombre") + " " + rs.getString("apellido_paterno") + " " + rs.getString("apellido_materno")
                +" Habitacion: " + rs.getString("tipo_habita") + " Piso: " +rs.getString("piso"));
                evento.put("start", rs.getDate("f_inicio").toLocalDate().toString());
                evento.put("end", rs.getDate("f_salida").toLocalDate().toString());

                HashMap<String, Object> extendedProps = new HashMap<>();
                extendedProps.put("monto", rs.getDouble("monto"));
                extendedProps.put("estado", rs.getInt("estado"));
                evento.put("extendedProps", extendedProps);

                eventos.add(evento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eventos;
    }

    public List<Asignacion> listaAsignada(int idtecnico) {

        ArrayList<Asignacion> list = new ArrayList<>();
        String sql = "SELECT r.id_reserva, r.id_pago, r.f_reserva, r.f_atencion, r.monto, r.estado, "
                + " c.nombres, c.apelpat, c.apelmat, "
                + " t.nombres, t.apelpat, t.apelmat, t.codigo "
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

                // Concatenar nombre completo del cliente
                String nomCliente = rs.getString("c.nombres") + " "
                        + rs.getString("c.apelpat") + " "
                        + rs.getString("c.apelmat");
                asignacion.setNomCliente(nomCliente);

                // Concatenar nombre completo del técnico
                String nomTecnico = (rs.getString("t.nombres") != null ? rs.getString("t.nombres") : "") + " "
                        + (rs.getString("t.apelpat") != null ? rs.getString("t.apelpat") : "") + " "
                        + (rs.getString("t.apelmat") != null ? rs.getString("t.apelmat") : "");

                // Si no hay información del técnico, asignar "No asignado"
                if (nomTecnico.trim().isEmpty()) {
                    nomTecnico = "No asignado";
                }
                asignacion.setNomTecnico(nomTecnico);
                asignacion.setIdtecnico(idtecnico);

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

    public int desmarcar(int idreserva) {
        int r = 0;
        String sql = "UPDATE reserva SET estado=? WHERE id_reserva=" + idreserva;
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, 1);

            r = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
        return r;
    }

    public List<DetalleReserva> detalle(int idreserva) {
        ArrayList<DetalleReserva> list = new ArrayList<>();

        String sql = "SELECT ds.f_atencion, ds.npersonas, ds.total, "
                + " s.nom_serv, s.descripcion FROM detalleservicios ds INNER JOIN servicio s "
                + " ON ds.id_servicio = s.id_servicio WHERE ds.id_reserva = ?;";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idreserva);
            rs = ps.executeQuery();
            while (rs.next()) {
                DetalleReserva detalle = new DetalleReserva();
                detalle.setFechaServicio(rs.getDate("f_atencion").toLocalDate());
                detalle.setNpersonas(rs.getInt("npersonas"));
                detalle.setTotal(rs.getInt("total"));
                detalle.setNombre(rs.getString("nom_serv"));
                detalle.setDescripcion(rs.getString("descripcion"));

                list.add(detalle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
