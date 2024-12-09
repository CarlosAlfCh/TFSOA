package com.utp.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.utp.entidad.InformeAtencionTecnico;
import com.utp.util.Conexion;

public class InformeAtencionTecnicoDAO {

    private static final Logger logger = Logger.getLogger(InformeAtencionTecnicoDAO.class.getName());

    public List<InformeAtencionTecnico> obtenerAtencionesPorTecnico() {
        logger.info("Iniciando método obtenerAtencionesPorTecnico");
        List<InformeAtencionTecnico> informes = new ArrayList<>();
        String sql = "SELECT id_tecnico AS tecnico, COUNT(*) AS atenciones, SUM(monto) AS monto FROM reserva GROUP BY id_tecnico";

        try (Connection connection = new Conexion().conectar();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String tecnico = resultSet.getString("tecnico");
                int atenciones = resultSet.getInt("atenciones");
                double monto = resultSet.getDouble("monto");
                informes.add(new InformeAtencionTecnico(tecnico, atenciones, monto));
                logger.info("Datos obtenidos: Técnico=" + tecnico + ", Atenciones=" + atenciones + ", Monto=" + monto);
            }
            logger.info("Datos de atención por técnico obtenidos correctamente");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en el método obtenerAtencionesPorTecnico", e);
        }

        return informes;
    }
}
