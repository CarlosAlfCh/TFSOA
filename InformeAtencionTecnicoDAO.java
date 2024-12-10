package com.utp.modelo;

import com.utp.entidad.InformeAtencionTecnico;

import com.utp.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InformeAtencionTecnicoDAO {

    private static final Logger logger = Logger.getLogger(InformeAtencionTecnicoDAO.class.getName());

    public List<InformeAtencionTecnico> obtenerAtencionesPorTecnico() {
        logger.info("Iniciando método obtenerAtencionesPorTecnico");
        List<InformeAtencionTecnico> informes = new ArrayList<>();
        String sql = "SELECT r.id_tecnico AS tecnico, COUNT(*) AS atenciones, SUM(r.monto) AS monto, p.nombres AS nombre_tecnico " +
                     "FROM reserva r " +
                     "JOIN persona p ON r.id_tecnico = p.codigo " +
                     "WHERE p.idrol = 3 " +
                     "GROUP BY r.id_tecnico, p.nombres";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String tecnico = resultSet.getString("tecnico");
                int atenciones = resultSet.getInt("atenciones");
                double monto = resultSet.getDouble("monto");
                String nombreTecnico = resultSet.getString("nombre_tecnico");
                informes.add(new InformeAtencionTecnico(tecnico, atenciones, monto, nombreTecnico));
                logger.info("Datos obtenidos: Técnico=" + tecnico + ", Atenciones=" + atenciones + ", Monto=" + monto + ", Nombre Técnico=" + nombreTecnico);
            }
            logger.info("Datos de atención por técnico obtenidos correctamente");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en el método obtenerAtencionesPorTecnico", e);
        }

        return informes;
    }
}
