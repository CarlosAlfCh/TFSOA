package com.utp.modelo;

import com.utp.entidad.Cliente;
import com.utp.util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements CRUD<Cliente> {

    @Override
    public List<Cliente> listar() {
        ArrayList<Cliente> listaClientes = new ArrayList<>();
        String sql = "select * from persona";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodigo(rs.getInt("codigo"));
                cliente.setNombres(rs.getString("nombres"));
                cliente.setApelpat(rs.getString("apelpat"));
                cliente.setApelmat(rs.getString("apelmat"));
                cliente.setDni(rs.getString("dni"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setContrasena(rs.getString("contrasena"));
                cliente.setEstado(rs.getInt("estado"));
                cliente.setRol(rs.getInt("idrol"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setDistrito(rs.getInt("id_distrito"));

                listaClientes.add(cliente);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listaClientes;
    }

    @Override
    public int insertar(Cliente nuevo) {
        int r = 0;
        String sql = "insert into persona (nombres, apelpat, apelmat, dni, correo, telefono, contrasena, estado, idrol, direccion, id_distrito) values (?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

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

            ps.setInt(8, 1);
            ps.setInt(9, 4);
            ps.setString(10, nuevo.getDireccion());
            ps.setInt(11, nuevo.getDistrito());
            r = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return r;
    }

    @Override
    public Cliente seleccionado(int codigo) {
        Cliente cliente = new Cliente();
        String sql = "select * from persona where codigo=?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente.setCodigo(rs.getInt("codigo"));
                    cliente.setNombres(rs.getString("nombres"));
                    cliente.setApelpat(rs.getString("apelpat"));
                    cliente.setApelmat(rs.getString("apelmat"));
                    cliente.setDni(rs.getString("dni"));
                    cliente.setTelefono(rs.getString("telefono"));
                    cliente.setDireccion(rs.getString("direccion"));
                    cliente.setCorreo(rs.getString("correo"));
                    cliente.setDistrito(rs.getInt("id_distrito"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return cliente;
    }

    @Override
    public int modificar(Cliente modificado) {
        int r = 0;
        String sql = "UPDATE persona SET nombres=?, apelpat=?, apelmat=?, dni=?, correo=?, telefono=?, estado=?, idrol=?, direccion=?, id_distrito=? WHERE codigo=?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, modificado.getNombres());
            ps.setString(2, modificado.getApelpat());
            ps.setString(3, modificado.getApelmat());
            ps.setString(4, modificado.getDni());
            ps.setString(5, modificado.getCorreo());
            ps.setString(6, modificado.getTelefono());
            ps.setInt(7, 1);
            ps.setInt(8, 4);
            ps.setString(9, modificado.getDireccion());
            ps.setInt(10, modificado.getDistrito());
            ps.setInt(11, modificado.getCodigo());

            r = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return r;
    }

    @Override
    public int desactivar(int eliminado) {
        int r = 0;
        String sql = "UPDATE persona SET estado=? WHERE codigo=?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, 0);
            ps.setInt(2, eliminado);
            r = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return r;
    }

    @Override
    public int eliminar(int eliminado) {
        int resultado = 0;
        String sql = "DELETE FROM persona WHERE codigo=?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, eliminado);
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al intentar eliminar físicamente: " + e.getMessage());
        }
        return resultado;
    }

    @Override
    public int activar(int activado) {
        int r = 0;
        String sql = "UPDATE persona SET estado=? WHERE codigo=?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, 1);
            ps.setInt(2, activado);
            r = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return r;
    }
}
