package com.utp.modelo;

import com.utp.entidad.Usuario;
import com.utp.util.Conexion;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.sql.Types.NULL;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements CRUD<Usuario> {

    Conexion cn = new Conexion();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public List<Usuario> listar() {
        ArrayList<Usuario> list = new ArrayList<>();
        String sql = "select codigo, idrol, idespecialidad, nombres, apelpat, apelmat, dni, correo, telefono, estado, direccion, id_distrito, turno, foto from persona";

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                user.setCodigo(rs.getInt("codigo"));
                user.setRol(rs.getInt("idrol"));
                user.setIdespecialidad(rs.getInt("idespecialidad"));
                user.setNombres(rs.getString("nombres"));
                user.setApelpat(rs.getString("apelpat"));
                user.setApelmat(rs.getString("apelmat"));
                user.setDni(rs.getString("dni"));
                user.setCorreo(rs.getString("correo"));
                user.setTelefono(rs.getString("telefono"));
                user.setEstado(rs.getInt("estado"));
                user.setDireccion(rs.getString("direccion"));
                user.setDistrito(rs.getInt("id_distrito"));
                user.setTurno(rs.getString("turno"));
                Blob fotoBlob = rs.getBlob("foto");
                if (fotoBlob != null) {
                    user.setFoto(fotoBlob.getBinaryStream());
                } else {
                    user.setFoto(null);
                }
                list.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public int insertar(Usuario nuevo) {
        int r = 0;
        String sql = "INSERT INTO persona(nombres, apelpat, apelmat, dni, correo, telefono, contrasena, turno, foto, estado, idrol, direccion, id_distrito, idespecialidad) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, nuevo.getNombres());
            ps.setString(2, nuevo.getApelpat());
            ps.setString(3, nuevo.getApelmat());
            ps.setString(4, nuevo.getDni());
            ps.setString(5, nuevo.getCorreo());
            ps.setString(6, nuevo.getTelefono());
            String contrasena = nuevo.getContrasena();
            
            if (contrasena == null || contrasena.trim().isEmpty()) {
                ps.setString(7, nuevo.getDni()); // Asigna el DNI como contraseña
            } else {
                ps.setString(7, contrasena);
            }
            
            ps.setString(8, nuevo.getTurno());
            
            if (nuevo.getFoto() != null) {
                ps.setBlob(9, nuevo.getFoto());
            } else {
                ps.setNull(9, java.sql.Types.BLOB);
            }
            
            ps.setInt(10, 1); // Nuevo registro se inserta en estado activo=1
            ps.setInt(11, nuevo.getRol()); 
            ps.setString(12, nuevo.getDireccion());
            ps.setInt(13, nuevo.getDistrito());
            if(nuevo.getIdespecialidad()==4){
                ps.setInt(14, nuevo.getIdespecialidad());
            }

            r = ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e);
        } 
        return r;
    }

    @Override
    public int modificar(Usuario modificado) {
        int r = 0;
        String sql = "UPDATE persona SET nombres=?, apelpat=?, apelmat=?, dni=?, correo=?, telefono=?, turno=?, foto=?, estado=?, idrol=?, direccion=?, id_distrito=?, idespecialidad=? WHERE codigo=?";

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, modificado.getNombres());
            ps.setString(2, modificado.getApelpat());
            ps.setString(3, modificado.getApelmat());
            ps.setString(4, modificado.getDni());
            ps.setString(5, modificado.getCorreo());
            ps.setString(6, modificado.getTelefono());
            ps.setString(7, modificado.getTurno());

            if (modificado.getFoto() != null) {
                ps.setBlob(8, modificado.getFoto());
            } else {
                ps.setNull(8, java.sql.Types.BLOB);
            }

            ps.setInt(9, 1);
            ps.setInt(10, modificado.getRol());
            ps.setString(11, modificado.getDireccion());
            ps.setInt(12, modificado.getDistrito());
            ps.setInt(13, modificado.getIdespecialidad());

            ps.setInt(14, modificado.getCodigo());

            r = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.toString());
        } 
        return r;
    }

    @Override
    public int eliminar(int eliminado) {
        int r = 0;
        String sql = "UPDATE persona SET estado=? WHERE codigo=" + eliminado;

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 0);
            r = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } 
        return r;
    }

    @Override
    public int restaurar(int restaurado) {
        int r = 0;
        String sql = "UPDATE persona SET estado=? WHERE codigo=?";

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setInt(2, restaurado);
            r = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } 
        return r;
    }

    @Override
    public Usuario seleccionado(int id) {
        Usuario user = new Usuario();
        String sql = "SELECT codigo, idrol, nombres, apelpat, apelmat, dni, correo, telefono, direccion, id_distrito, idespecialidad, turno FROM persona WHERE codigo=?";

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                user.setCodigo(rs.getInt("codigo"));
                user.setRol(rs.getInt("idrol"));
                user.setNombres(rs.getString("nombres"));
                user.setApelpat(rs.getString("apelpat"));
                user.setApelmat(rs.getString("apelmat"));
                user.setDni(rs.getString("dni"));
                user.setCorreo(rs.getString("correo"));
                user.setTelefono(rs.getString("telefono"));
                user.setDireccion(rs.getString("direccion"));
                user.setDistrito(rs.getInt("id_distrito")); 
                user.setIdespecialidad(rs.getInt("idespecialidad"));
                user.setTurno(rs.getString("turno")); 
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } 

        return user;
    }
}
