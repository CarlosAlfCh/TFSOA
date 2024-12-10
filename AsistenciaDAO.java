package com.utp.modelo;

import com.utp.entidad.info.Asistencia;
import com.utp.entidad.info.AsistenciaDetalle;
import com.utp.util.Conexion;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AsistenciaDAO {

    public int verificarRegistro(int idUsuario) {
        LocalDate fechaActual = LocalDate.now();
        String consultaExiste = "SELECT COUNT(*) FROM asistencia WHERE id_tecnico = ? AND fecha = ?";
        String insertarNuevo = "INSERT INTO asistencia (id_tecnico, fecha, hora_ingreso) VALUES (?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement psConsulta = conn.prepareStatement(consultaExiste)) {

            psConsulta.setInt(1, idUsuario);
            psConsulta.setDate(2, java.sql.Date.valueOf(fechaActual));

            try (ResultSet rs = psConsulta.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return 0; // Ya tiene registro para hoy
                }
            }

            // Si no tiene registro, se crea uno nuevo
            try (PreparedStatement psInsertar = conn.prepareStatement(insertarNuevo)) {
                psInsertar.setInt(1, idUsuario);
                psInsertar.setDate(2, java.sql.Date.valueOf(fechaActual));
                psInsertar.setTime(3, java.sql.Time.valueOf(LocalTime.now()));

                if (psInsertar.executeUpdate() > 0) {
                    return 1; // Registro creado exitosamente
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Error
    }

    public int registrarAsistencia(int idUsuario, LocalDate fecha, LocalTime horaEntrada, LocalTime horaSalida) {
        String insertarNuevo = "INSERT INTO asistencia (id_tecnico, fecha, hora_ingreso, hora_salida) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(insertarNuevo)) {

            ps.setInt(1, idUsuario);
            ps.setDate(2, java.sql.Date.valueOf(fecha));
            ps.setTime(3, java.sql.Time.valueOf(horaEntrada));
            ps.setTime(4, java.sql.Time.valueOf(horaSalida));

            if (ps.executeUpdate() > 0) {
                return 2; // Registro creado exitosamente
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Error
    }

    public int actualizarHoraSalida(int idUsuario) {
        LocalDate fechaActual = LocalDate.now();
        String obtenerHoraIngreso = "SELECT hora_ingreso FROM asistencia WHERE id_tecnico = ? AND fecha = ?";
        String actualizarSalidaYHoras = "UPDATE asistencia SET hora_salida = ?, nhoras = ? WHERE id_tecnico = ? AND fecha = ?";

        try (Connection conn = Conexion.getConnection()) {

            // Obtener la hora de ingreso
            try (PreparedStatement psIngreso = conn.prepareStatement(obtenerHoraIngreso)) {
                psIngreso.setInt(1, idUsuario);
                psIngreso.setDate(2, java.sql.Date.valueOf(fechaActual));

                try (ResultSet rs = psIngreso.executeQuery()) {
                    if (rs.next()) {
                        LocalTime horaIngreso = rs.getTime("hora_ingreso").toLocalTime();
                        LocalTime horaSalida = LocalTime.now();

                        // Calcular las horas trabajadas
                        long horasTrabajadas = java.time.Duration.between(horaIngreso, horaSalida).toHours();

                        // Actualizar hora de salida y nhoras
                        try (PreparedStatement psActualizar = conn.prepareStatement(actualizarSalidaYHoras)) {
                            psActualizar.setTime(1, java.sql.Time.valueOf(horaSalida));
                            psActualizar.setInt(2, (int) horasTrabajadas); // Convertir a int para nhoras
                            psActualizar.setInt(3, idUsuario);
                            psActualizar.setDate(4, java.sql.Date.valueOf(fechaActual));

                            if (psActualizar.executeUpdate() > 0) {
                                return 0; // Actualización exitosa
                            }
                        }
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Error
    }

    public List<AsistenciaDetalle> obtenerAsistencias() {
    List<AsistenciaDetalle> asistencias = new ArrayList<>();
    String sql = "SELECT a.id, a.id_tecnico, p.nombres AS nombre_tecnico, a.fecha, a.hora_ingreso, a.hora_salida, a.nhoras " +
                 "FROM asistencia a " +
                 "JOIN persona p ON a.id_tecnico = p.codigo " +
                 "WHERE p.idrol = 3"; // Asumiendo que el rol 3 es el de técnico

    try (Connection conn = Conexion.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            int id = rs.getInt("id");
            int idTecnico = rs.getInt("id_tecnico");
            String nombreTecnico = rs.getString("nombre_tecnico");
            LocalDate fecha = rs.getDate("fecha").toLocalDate();
            LocalTime horaIngreso = rs.getTime("hora_ingreso").toLocalTime();
            LocalTime horaSalida = rs.getTime("hora_salida") != null ? rs.getTime("hora_salida").toLocalTime() : null;
            int nhoras = rs.getInt("nhoras");

            AsistenciaDetalle asistenciaDetalle = new AsistenciaDetalle(id, idTecnico, nombreTecnico, fecha, horaIngreso, horaSalida, nhoras);
            asistencias.add(asistenciaDetalle);
        }
    } catch (SQLException e) {
        // Mejor manejo de errores
        System.err.println("Error al obtener las asistencias: " + e.getMessage());
        // Opcionalmente, podrías agregar el uso de un Logger en lugar de System.err
    }
    return asistencias;
}

}
