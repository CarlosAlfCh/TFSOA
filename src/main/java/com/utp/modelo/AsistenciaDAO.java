package com.utp.modelo;

import com.utp.entidad.info.Asistencia;
import com.utp.util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AsistenciaDAO {

    Conexion cn = new Conexion();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public int verificarRegistro(int idUsuario) {
        LocalDate fechaActual = LocalDate.now();
        String consultaExiste = "SELECT COUNT(*) FROM asistencia WHERE id_tecnico = ? AND fecha = ?";
        String insertarNuevo = "INSERT INTO asistencia (id_tecnico, fecha, hora_ingreso) VALUES (?, ?, ?)";

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(consultaExiste);
            ps.setInt(1, idUsuario);
            ps.setDate(2, java.sql.Date.valueOf(fechaActual));
            rs = ps.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                return 0; // Ya tiene registro para hoy
            }

            // Si no tiene registro, se crea uno nuevo
            ps = conn.prepareStatement(insertarNuevo);
            ps.setInt(1, idUsuario);
            ps.setDate(2, java.sql.Date.valueOf(fechaActual));
            ps.setTime(3, java.sql.Time.valueOf(LocalTime.now()));

            if (ps.executeUpdate() > 0) {
                return 1; // Registro creado exitosamente
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Error
    }

    public int registrarAsistencia(int idUsuario, LocalDate fecha, LocalTime horaentrada, LocalTime horasalida) {
        String insertarNuevo = "INSERT INTO asistencia (id_tecnico, fecha, hora_ingreso, hora_salida) VALUES (?, ?, ?, ?)";

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(insertarNuevo);
            ps.setInt(1, idUsuario);
            ps.setDate(2, java.sql.Date.valueOf(fecha));
            ps.setTime(3, java.sql.Time.valueOf(horaentrada));
            ps.setTime(4, java.sql.Time.valueOf(horasalida));

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

        try {
            conn = cn.conectar();

            // Obtener la hora de ingreso
            ps = conn.prepareStatement(obtenerHoraIngreso);
            ps.setInt(1, idUsuario);
            ps.setDate(2, java.sql.Date.valueOf(fechaActual));
            rs = ps.executeQuery();

            if (rs.next()) {
                LocalTime horaIngreso = rs.getTime("hora_ingreso").toLocalTime();
                LocalTime horaSalida = LocalTime.now();

                // Calcular las horas trabajadas
                long horasTrabajadas = java.time.Duration.between(horaIngreso, horaSalida).toHours();

                // Actualizar hora de salida y nhoras
                ps = conn.prepareStatement(actualizarSalidaYHoras);
                ps.setTime(1, java.sql.Time.valueOf(horaSalida));
                ps.setInt(2, (int) horasTrabajadas); // Convertir a int para nhoras
                ps.setInt(3, idUsuario);
                ps.setDate(4, java.sql.Date.valueOf(fechaActual));

                if (ps.executeUpdate() > 0) {
                    return 0; // Actualización exitosa
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Error
    }

    public List<Asistencia> obtenerAsistencias() {
        List<Asistencia> asistencias = new ArrayList<>();
        String sql = "SELECT id, id_tecnico, fecha, hora_ingreso, hora_salida, nhoras FROM asistencia";

        try (Connection conn = cn.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int idTecnico = rs.getInt("id_tecnico");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                LocalTime horaIngreso = rs.getTime("hora_ingreso").toLocalTime();
                LocalTime horaSalida = rs.getTime("hora_salida") != null ? rs.getTime("hora_salida").toLocalTime() : null;
                int nhoras = rs.getInt("nhoras");

                Asistencia asistencia = new Asistencia(id, idTecnico, fecha, horaIngreso, horaSalida, nhoras);
                asistencias.add(asistencia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return asistencias;
    }
}
