package com.utp.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.utp.entidad.BoletaReserva;
import com.utp.util.Conexion;

public class BoletaReservaDAO {

    private static final Logger logger = Logger.getLogger(BoletaReservaDAO.class.getName());

    public BoletaReserva obtenerBoletaReserva(int idReserva) {
        logger.info("Iniciando método obtenerBoletaReserva");
        BoletaReserva boleta = null;
        String sql = "SELECT r.id_reserva AS idReserva, r.f_reserva AS fechaReserva, r.f_check_in AS fechaCheckIn, r.f_check_out AS fechaCheckOut, " +
                     "r.id_cliente AS idCliente, p.nombres AS nombres, p.apelpat AS apelpat, p.apelmat AS apelmat, p.correo AS correo, p.telefono AS telefono, r.monto AS monto " +
                     "FROM reserva r JOIN persona p ON r.id_cliente = p.codigo WHERE r.id_reserva = ?";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, idReserva);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idReservaDB = resultSet.getInt("idReserva");
                    String fechaReserva = resultSet.getString("fechaReserva");
                    String fechaCheckIn = resultSet.getString("fechaCheckIn");
                    String fechaCheckOut = resultSet.getString("fechaCheckOut");
                    int idCliente = resultSet.getInt("idCliente");
                    String clienteNombre = resultSet.getString("nombres") + " " + resultSet.getString("apelpat") + " " + resultSet.getString("apelmat");
                    String clienteCorreo = resultSet.getString("correo");
                    String clienteTelefono = resultSet.getString("telefono");
                    double monto = resultSet.getDouble("monto");

                    boleta = new BoletaReserva(idReservaDB, fechaReserva, fechaCheckIn, fechaCheckOut, idCliente, clienteNombre, clienteCorreo, clienteTelefono, monto);
                }
            }
            logger.info("Datos de la boleta obtenidos correctamente: " + boleta);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en el método obtenerBoletaReserva", e);
        }

        return boleta;
    }

    public List<BoletaReserva> obtenerTodasLasReservas() {
        logger.info("Iniciando método obtenerTodasLasReservas");
        List<BoletaReserva> reservas = new ArrayList<>();
        String sql = "SELECT r.id_reserva AS idReserva, r.f_reserva AS fechaReserva, r.f_check_in AS fechaCheckIn, r.f_check_out AS fechaCheckOut, " +
                     "r.id_cliente AS idCliente, p.nombres AS nombres, p.apelpat AS apelpat, p.apelmat AS apelmat, p.correo AS correo, p.telefono AS telefono, r.monto AS monto " +
                     "FROM reserva r JOIN persona p ON r.id_cliente = p.codigo";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int idReserva = resultSet.getInt("idReserva");
                String fechaReserva = resultSet.getString("fechaReserva");
                String fechaCheckIn = resultSet.getString("fechaCheckIn");
                String fechaCheckOut = resultSet.getString("fechaCheckOut");
                int idCliente = resultSet.getInt("idCliente");
                String clienteNombre = resultSet.getString("nombres") + " " + resultSet.getString("apelpat") + " " + resultSet.getString("apelmat");
                String clienteCorreo = resultSet.getString("correo");
                String clienteTelefono = resultSet.getString("telefono");
                double monto = resultSet.getDouble("monto");

                BoletaReserva reserva = new BoletaReserva(idReserva, fechaReserva, fechaCheckIn, fechaCheckOut, idCliente, clienteNombre, clienteCorreo, clienteTelefono, monto);
                reservas.add(reserva);
            }
            logger.info("Lista de reservas obtenida correctamente: " + reservas);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en el método obtenerTodasLasReservas", e);
        }

        return reservas;
    }
}
