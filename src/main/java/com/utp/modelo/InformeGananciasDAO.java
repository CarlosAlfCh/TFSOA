package com.utp.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.utp.entidad.InformeGanancias;
import com.utp.util.Conexion;

public class InformeGananciasDAO {

    private static final Logger logger = Logger.getLogger(InformeGananciasDAO.class.getName());

    public List<InformeGanancias> obtenerGananciasPorDia() {
        logger.info("Iniciando método obtenerGananciasPorDia");
        List<InformeGanancias> informes = new ArrayList<>();
        String sql = "SELECT DATE(f_atencion) AS periodo, SUM(monto) AS ganancias FROM reserva GROUP BY DATE(f_atencion)";

        try (Connection connection = new Conexion().conectar();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String periodo = resultSet.getString("periodo");
                double ganancias = resultSet.getDouble("ganancias");
                informes.add(new InformeGanancias(periodo, ganancias));
            }
            logger.info("Datos de ganancias por día obtenidos correctamente");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en el método obtenerGananciasPorDia", e);
        }

        return informes;
    }

    public List<InformeGanancias> obtenerGananciasPorMes() {
        logger.info("Iniciando método obtenerGananciasPorMes");
        List<InformeGanancias> informes = new ArrayList<>();
        String sql = "SELECT DATE_FORMAT(f_atencion, '%Y-%m') AS periodo, SUM(monto) AS ganancias FROM reserva GROUP BY DATE_FORMAT(f_atencion, '%Y-%m')";

        try (Connection connection = new Conexion().conectar();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String periodo = resultSet.getString("periodo");
                double ganancias = resultSet.getDouble("ganancias");
                informes.add(new InformeGanancias(periodo, ganancias));
            }
            logger.info("Datos de ganancias por mes obtenidos correctamente");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en el método obtenerGananciasPorMes", e);
        }

        return informes;
    }

    public List<InformeGanancias> obtenerGananciasPorAno() {
        logger.info("Iniciando método obtenerGananciasPorAno");
        List<InformeGanancias> informes = new ArrayList<>();
        String sql = "SELECT YEAR(f_atencion) AS periodo, SUM(monto) AS ganancias FROM reserva GROUP BY YEAR(f_atencion)";

        try (Connection connection = new Conexion().conectar();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String periodo = resultSet.getString("periodo");
                double ganancias = resultSet.getDouble("ganancias");
                informes.add(new InformeGanancias(periodo, ganancias));
            }
            logger.info("Datos de ganancias por año obtenidos correctamente");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en el método obtenerGananciasPorAno", e);
        }

        return informes;
    }
}
