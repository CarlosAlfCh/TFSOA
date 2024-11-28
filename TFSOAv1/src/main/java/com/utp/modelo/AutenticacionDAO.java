package com.utp.modelo;

import com.utp.util.Conexion;
import com.utp.entidad.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutenticacionDAO {

    Conexion cn = new Conexion();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public Usuario validar(String correo, String contrasena) {
        Usuario user = new Usuario();
        String sql = "select * from persona where correo=? and contrasena=?";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, contrasena);
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setCodigo(rs.getInt("codigo"));
                user.setCorreo(rs.getString("correo"));
                user.setContrasena(rs.getString("contrasena"));
                user.setNombres(rs.getString("nombres"));
                user.setApelpat(rs.getString("apelpat"));
                user.setApelmat(rs.getString("apelmat"));
                user.setRol(rs.getInt("idrol"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return user;
    }

    public int actualizarCodigoRecuperacion(String correo, String recupera) {
        int r = 0;
        String sql = "UPDATE persona SET recuperar=? WHERE correo=?";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, recupera);
            ps.setString(2, correo); // Removido el LIKE y el %
            r = ps.executeUpdate();
            System.out.println("Codigo de recuperacion actualizado: " + recupera + " para correo: " + correo);
            if (r == 0) {
                System.out.println("No se encontro ningun usuario con el correo: " + correo);
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar codigo de recuperacion: " + e.toString());
        }
        return r;
    }

    // Método actualizarContrasena corregido
    public int actualizarContrasena(String clave, String contra) {
        int r = 0;
        String sql = "UPDATE persona SET contrasena=? WHERE recuperar=?";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, contra);
            ps.setString(2, clave);
            r = ps.executeUpdate();
            System.out.println("Contrasena actualizada para clave de recuperacion: " + clave);
            if (r == 0) {
                System.out.println("No se encontro ningun usuario con la clave de recuperacion: " + clave);
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar contrasena: " + e.toString());
        }
        return r;
    }
}
