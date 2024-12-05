package com.utp.modelo;

import com.utp.entidad.pagos.Rewards;
import com.utp.util.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RewardsDAO {

    Conexion cn = new Conexion();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public List<Rewards> listarClientesPromo() {
        ArrayList<Rewards> list = new ArrayList<>();
        String sql = "SELECT persona.codigo , persona.nombres , persona.apelpat , persona.apelmat , persona.correo , "
                + "codigospromo.fecha_generacion , codigospromo.fecha_expiracion , codigospromo.fecha_uso , codigospromo.descuento , codigospromo.estado , codigospromo.codpromo "
                + "FROM persona INNER JOIN codigospromo on persona.codigo = codigospromo.id_cliente";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Rewards detalle = new Rewards();
                detalle.setIdcliente(rs.getInt(1));
                detalle.setNomCliente(rs.getString(2) + ", " + rs.getString(3) + " " + rs.getString(4));
                detalle.setCorreo(rs.getString(5));
                detalle.setfGeneracion(rs.getObject(6, LocalDate.class));
                detalle.setfExpiracion(rs.getObject(7, LocalDate.class));
                detalle.setfUso(rs.getObject(8, LocalDate.class));
                detalle.setDescuento(rs.getInt(9));
                detalle.setEstado(rs.getInt(10));
                detalle.setCodpromo(rs.getString(11));
                list.add(detalle);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public String insertarCodigoXCliente(int idCliente, int descuento, String codpromo) {
        String fechaExpiracionFormateada = "";
        String sql = "INSERT INTO codigospromo(id_cliente, fecha_generacion, fecha_expiracion, descuento, estado, codpromo) VALUES (?,?,?,?,?,?)";

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);

            LocalDate fechaGeneracion = LocalDate.now();
            LocalDate fechaExpiracion = fechaGeneracion.plusDays(7); // Fecha de expiración es 7 días después de la generación

            ps.setInt(1, idCliente);
            ps.setDate(2, Date.valueOf(fechaGeneracion));
            ps.setDate(3, Date.valueOf(fechaExpiracion));
            ps.setInt(4, descuento);
            ps.setInt(5, 1); // Estado activo del cupón
            ps.setString(6, codpromo);

            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                // Formatear la fecha de expiración si la inserción fue exitosa
                fechaExpiracionFormateada = fechaExpiracion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fechaExpiracionFormateada;
    }

    public String actualizarCodPromo(int idCliente, int descuento, String codpromo) {
        String fechaExpiracionFormateada = "";
        String sql = "UPDATE codigospromo SET fecha_generacion=?, fecha_expiracion=?, descuento=?, estado=?, codpromo=? WHERE id_cliente=?";

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);

            LocalDate fechaGeneracion = LocalDate.now();
            LocalDate fechaExpiracion = fechaGeneracion.plusDays(7); // Fecha de expiración es 7 días después de la generación

            ps.setDate(1, Date.valueOf(fechaGeneracion));
            ps.setDate(2, Date.valueOf(fechaExpiracion));
            ps.setInt(3, descuento);
            ps.setInt(4, 1); // Estado activo del cupón
            ps.setString(5, codpromo);
            ps.setInt(6, idCliente);

            int resultado = ps.executeUpdate();

            if (resultado > 0) {
                // Formatear la fecha de expiración si la actualización fue exitosa
                fechaExpiracionFormateada = fechaExpiracion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return fechaExpiracionFormateada;
    }

    public int invalidarCodPromo(int idCliente) {
        int r = 0;
        String sql = "DELETE FROM codigospromo WHERE id_cliente = ?";

        System.out.println(idCliente);
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);

            // Establecer el parámetro idCliente para la cláusula WHERE
            ps.setInt(1, idCliente);

            // Ejecutar la consulta DELETE
            r = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.toString() + " aqui");
        }
        return r;
    }

    public int canjearCodPromo(int idCliente, String codpromo) {
        
        int descuento = 0;
        String verificarSql = "SELECT fecha_expiracion, estado, descuento FROM codigospromo WHERE id_cliente=? AND codpromo=?";
        String canjearSql = "UPDATE codigospromo SET estado=?, fecha_uso=? WHERE id_cliente=? AND codpromo=?";

        try {
            conn = cn.conectar();

            // Verificar si el código sigue vigente y no ha sido usado 
            ps = conn.prepareStatement(verificarSql);
            ps.setInt(1, idCliente);
            ps.setString(2, codpromo);
            rs = ps.executeQuery();

            if (rs.next()) {
                LocalDate fechaExpiracion = rs.getObject("fecha_expiracion", LocalDate.class);
                int estado = rs.getInt("estado");

                // Si la fecha de expiración es posterior a la actual y el código no ha sido usado
                if (fechaExpiracion != null && fechaExpiracion.isAfter(LocalDate.now()) && estado == 1) {
                    descuento = rs.getInt("descuento"); // Obtener el valor del descuento

                    // Preparar la actualización del estado
                    ps = conn.prepareStatement(canjearSql);
                    ps.setInt(1, 0); // Cambiar el estado a 0 (usado)
                    ps.setDate(2, Date.valueOf(LocalDate.now())); // Registrar la fecha de uso
                    ps.setInt(3, idCliente);
                    ps.setString(4, codpromo);

                    ps.executeUpdate(); // Ejecuta la actualización
                } else {
                    System.out.println("Código expirado o ya usado.");
                }
            } else {
                System.out.println("Código no encontrado.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return descuento;
    }

}
