package com.utp.modelo;

import com.utp.entidad.Usuario;
import com.utp.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutenticacionDAO {

    public Usuario validar(String correo, String contrasena) {
        String sql = "SELECT * FROM persona WHERE correo = ? AND contrasena = ?";
        Usuario user = null;

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, correo);
            ps.setString(2, contrasena);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new Usuario();
                    user.setCodigo(rs.getInt("codigo"));
                    user.setCorreo(rs.getString("correo"));
                    user.setContrasena(rs.getString("contrasena"));
                    user.setNombres(rs.getString("nombres"));
                    user.setApelpat(rs.getString("apelpat"));
                    user.setApelmat(rs.getString("apelmat"));
                    user.setRol(rs.getInt("idrol"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en la validación del usuario: " + e.getMessage());
        }

        return user;
    }

    public int actualizarCodigoRecuperacion(String correo, String recupera) {
        String sql = "UPDATE persona SET recuperar = ? WHERE correo = ?";
        int filasActualizadas = 0;

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, recupera);
            ps.setString(2, correo);
            filasActualizadas = ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar el código de recuperación: " + e.getMessage());
        }

        return filasActualizadas;
    }

    public int actualizarContrasena(String clave, String nuevaContrasena) {
        String sql = "UPDATE persona SET contrasena = ? WHERE recuperar = ?";
        int filasActualizadas = 0;

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nuevaContrasena);
            ps.setString(2, clave);
            filasActualizadas = ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar la contraseña: " + e.getMessage());
        }

        return filasActualizadas;
    }
}
