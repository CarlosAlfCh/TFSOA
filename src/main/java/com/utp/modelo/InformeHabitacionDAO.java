package com.utp.modelo;

import com.utp.entidad.InformeHabitacion;
import com.utp.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InformeHabitacionDAO {

    public List<InformeHabitacion> obtenerOcupacionHabitaciones() {
        List<InformeHabitacion> informes = new ArrayList<>();
        Conexion conexion = new Conexion();
        String sql = "SELECT h.tipo_habita, COUNT(d.id_habitacion) AS cantidad, SUM(d.total) AS montoTotal, SUM(d.nnoches) AS numeroHuespedes " +
                     "FROM detalleroom d " +
                     "JOIN habitacion h ON d.id_habitacion = h.id_habitacion " +
                     "GROUP BY h.tipo_habita";

        try (Connection conn = conexion.conectar();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                InformeHabitacion informe = new InformeHabitacion();
                informe.setTipoHabitacion(resultSet.getString("tipo_habita"));
                informe.setCantidad(resultSet.getInt("cantidad"));
                informe.setMontoTotal(resultSet.getDouble("montoTotal"));
                informe.setNumeroHuespedes(resultSet.getInt("numeroHuespedes"));
                informes.add(informe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return informes;
    }
}
