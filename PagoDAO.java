package com.utp.modelo;

import com.utp.entidad.pagos.Pago;
import com.utp.util.Conexion;
import com.utp.util.CodeGenerator;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PagoDAO {

    public int insertpago(String cod, String metodo, String fecha) {
        String codigo = (cod == null || cod.isEmpty()) ? CodeGenerator.generateCode() : cod; // Lógica simplificada
        String sqlInsert = "INSERT INTO pago(n_operacion, metodo, fecha_pago, valido) VALUES (?,?,?,?)";
        int idPago = -1;

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Establecer parámetros
            ps.setString(1, codigo);
            ps.setString(2, metodo);
            ps.setString(3, fecha);
            ps.setInt(4, 0); // Suponiendo que '0' representa "no válido" o similar

            // Ejecutar la inserción
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                // Obtener el id generado automáticamente
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        idPago = rs.getInt(1); // Obtener el ID generado
                    }
                }
            }

            System.out.println(idPago + " pagoDAO"); // Verificación para debug

        } catch (SQLException e) {
            System.out.println("Error al insertar el pago: " + e.getMessage());
        }

        return idPago;
    }

    public List<Pago> listarpagos() {
        List<Pago> list = new ArrayList<>();
        String sql = "SELECT persona.nombres, persona.apelpat, persona.apelmat, persona.correo, pago.id_pago, "
                + "pago.n_operacion, pago.metodo, pago.fecha_pago, pago.valido "
                + "FROM persona "
                + "INNER JOIN reserva ON persona.codigo = reserva.id_cliente "
                + "INNER JOIN pago ON reserva.id_pago = pago.id_pago "
                + "ORDER BY pago.id_pago DESC";

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pago pa = new Pago();
                pa.setNombres(rs.getString("nombres"));
                pa.setApelpat(rs.getString("apelpat"));
                pa.setApelmat(rs.getString("apelmat"));
                pa.setCorreo(rs.getString("correo"));
                pa.setIdpago(rs.getInt("id_pago"));
                pa.setCodigo(rs.getString("n_operacion"));
                pa.setMetodo(rs.getString("metodo"));
                pa.setFechapago(rs.getString("fecha_pago"));  // Considera usar LocalDate
                pa.setValido(rs.getInt("valido"));
                list.add(pa);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los pagos: " + e.getMessage());
        }

        return list;
    }

    public int pagovalido(int idpago) {
        int r = 0;
        String sql = "UPDATE pago SET valido=? WHERE id_pago=?";

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            // Parametrización de la consulta para evitar inyección SQL
            ps.setInt(1, 1);   // Establecemos el valor 'valido' a 1
            ps.setInt(2, idpago);  // Establecemos el id_pago como parámetro

            r = ps.executeUpdate(); // Ejecutamos la actualización
        } catch (SQLException e) {
            System.err.println("Error al actualizar el estado del pago: " + e.getMessage());
        }

        return r;
    }

    public int pagoinvalido(int idpago) {
        int r = 0;
        String sql = "UPDATE pago SET valido=? WHERE id_pago=?";

        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            // Parametrizamos la consulta para evitar inyección SQL
            ps.setInt(1, 0); // Establecemos el valor 'valido' a 0
            ps.setInt(2, idpago); // Establecemos el id_pago como parámetro

            r = ps.executeUpdate(); // Ejecutamos la actualización
        } catch (SQLException e) {
            System.err.println("Error al actualizar el estado del pago: " + e.getMessage());
        }

        System.out.println("Pago marcado como inválido. ID pago: " + idpago);
        return r;
    }

}
