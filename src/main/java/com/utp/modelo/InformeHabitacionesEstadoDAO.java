package com.utp.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.utp.entidad.InformeHabitacionEstado;
import com.utp.util.Conexion;

public class InformeHabitacionesEstadoDAO {

    private static final Logger logger = Logger.getLogger(InformeHabitacionesEstadoDAO.class.getName());

    public List<InformeHabitacionEstado> obtenerHabitacionesPorEstado() {
        logger.info("Iniciando método obtenerHabitacionesPorEstado");
        List<InformeHabitacionEstado> informes = new ArrayList<>();
        String sql = "SELECT estado, COUNT(*) AS cantidad FROM habitacion GROUP BY estado";

        try (Connection connection = new Conexion().conectar();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int estado = resultSet.getInt("estado");
                int cantidad = resultSet.getInt("cantidad");
                informes.add(new InformeHabitacionEstado(estado, cantidad));
                logger.info("Datos obtenidos: Estado=" + estado + ", Cantidad=" + cantidad);
            }
            logger.info("Datos de habitaciones por estado obtenidos correctamente");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en el método obtenerHabitacionesPorEstado", e);
        }

        return informes;
    }
}
