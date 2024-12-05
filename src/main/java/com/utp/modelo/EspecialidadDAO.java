package com.utp.modelo;

import com.utp.entidad.extras.Especialidad;
import com.utp.util.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadDAO implements CRUD<Especialidad> {

    Conexion cn = new Conexion();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public List<Especialidad> listar() {
        ArrayList<Especialidad> list = new ArrayList<>();
        String sql = "SELECT * FROM especialidad";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Especialidad e = new Especialidad();
                e.setIdespecialidad(rs.getInt("idespecialidad"));
                e.setNombre(rs.getString("nombre"));
                e.setDescripcion(rs.getString("descripcion"));
                e.setSalario(rs.getDouble("salario_promedio"));
                e.setFechaactualizacion(rs.getDate("f_actualizacion").toLocalDate());
                e.setEstado(rs.getInt("estado"));
                list.add(e);
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar especialidades: " + ex.getMessage());
        }
        return list;
    }

    @Override
    public int insertar(Especialidad nuevo) {
        int resultado = 0;
        String sql = "INSERT INTO especialidad (nombre, descripcion, salario_promedio, f_actualizacion, estado) VALUES (?, ?, ?, ?, ?)";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, nuevo.getNombre());
            ps.setString(2, nuevo.getDescripcion());
            ps.setDouble(3, nuevo.getSalario());
            ps.setDate(4, Date.valueOf(nuevo.getFechaactualizacion()));
            ps.setInt(5, 1);
            resultado = ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al insertar especialidad: " + ex.getMessage());
        }
        return resultado;
    }

    @Override
    public int modificar(Especialidad modificado) {
        int resultado = 0;
        String sql = "UPDATE especialidad SET nombre = ?, descripcion = ?, salario_promedio = ?, f_actualizacion = ? WHERE idespecialidad = ?";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, modificado.getNombre());
            ps.setString(2, modificado.getDescripcion());
            ps.setDouble(3, modificado.getSalario());
            ps.setDate(4, Date.valueOf(modificado.getFechaactualizacion()));
            ps.setInt(5, modificado.getIdespecialidad());
            resultado = ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al modificar especialidad: " + ex.getMessage());
        }
        return resultado;
    }

    @Override
    public int eliminar(int eliminado) {
        int resultado = 0;
        String sql = "DELETE FROM especialidad WHERE idespecialidad = ?";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, eliminado);
            resultado = ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al eliminar especialidad: " + ex.getMessage());
        }
        return resultado;
    }

    @Override
    public int desactivar(int eliminado) {
        int resultado = 0;
        String sql = "UPDATE especialidad SET estado = 0, f_actualizacion = ? WHERE idespecialidad = ?";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(LocalDate.now())); // Establece la fecha actual
            ps.setInt(2, eliminado);
            resultado = ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al desactivar especialidad: " + ex.getMessage());
        } 
        return resultado;
    }

    @Override
    public int activar(int restaurado) {
        int resultado = 0;
        String sql = "UPDATE especialidad SET estado = 1, f_actualizacion = ? WHERE idespecialidad = ?";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setDate(1, java.sql.Date.valueOf(LocalDate.now())); // Establece la fecha actual
            ps.setInt(2, restaurado);
            resultado = ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error al activar especialidad: " + ex.getMessage());
        } 
        return resultado;
    }

    @Override
    public Especialidad seleccionado(int id) {
        Especialidad e = null;
        String sql = "SELECT * FROM especialidad WHERE idespecialidad = ?";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                e = new Especialidad();
                e.setIdespecialidad(rs.getInt("idespecialidad"));
                e.setNombre(rs.getString("nombre"));
                e.setDescripcion(rs.getString("descripcion"));
                e.setSalario(rs.getDouble("salario_promedio"));
                e.setFechaactualizacion(rs.getDate("f_actualizacion").toLocalDate());
            }
        } catch (SQLException ex) {
            System.err.println("Error al seleccionar especialidad: " + ex.getMessage());
        }
        return e;
    }
}
