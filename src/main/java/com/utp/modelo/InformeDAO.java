package com.utp.modelo;

import com.utp.entidad.Informe;
import com.utp.util.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InformeDAO {

    private static final String QUERY_SERVICIOS = 
        "SELECT s.nom_serv AS descripcion, COUNT(ds.id_servicio) AS cantidad, SUM(ds.total) AS monto " +
        "FROM detalleservicios ds " +
        "JOIN servicio s ON ds.id_servicio = s.id_servicio " +
        "GROUP BY s.nom_serv";

    public List<Informe> obtenerInformeServicios() {
        List<Informe> informe = new ArrayList<>();

        Conexion conexion = new Conexion(); // Instanciar la conexión
        try (Connection conn = conexion.conectar();
             PreparedStatement preparedStatement = conn.prepareStatement(QUERY_SERVICIOS)) {

            // Ejecutar consulta
            ResultSet rs = preparedStatement.executeQuery();

            // Mapear resultados
            while (rs.next()) {
                Informe data = new Informe();
                data.setDescripcion(rs.getString("descripcion"));
                data.setCantidad(rs.getInt("cantidad"));
                data.setMonto(rs.getDouble("monto"));
                informe.add(data);
            }

        } catch (SQLException e) {
            System.out.println("Error en el DAO: " + e.getMessage());
        }

        return informe;
    }

    public List<Informe> obtenerOcupacionHabitaciones() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
