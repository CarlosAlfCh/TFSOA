package com.utp.modelo;

import com.utp.util.Conexion;
import com.utp.entidad.Habitacion;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAO implements CRUD<Habitacion> {

    @Override
    public List<Habitacion> listar() {
        ArrayList<Habitacion> listaHabitaciones = new ArrayList<>();
        String sql = "select * from habitacion";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Habitacion h = new Habitacion();
                h.setIdhabitacion(rs.getInt("id_habitacion"));
                h.setPiso(rs.getString("piso"));
                h.setTipo(rs.getString("tipo_habita"));
                h.setDescripcion(rs.getString("descripcion"));
                h.setDirHotel(rs.getString("direccion"));
                h.setDisHotel(rs.getInt("id_distrito"));
                h.setPrecioNoche(rs.getDouble("precioxnoche"));

                Blob fotoBlob = rs.getBlob("foto_habitacion");
                if (fotoBlob != null) {
                    h.setFoto(fotoBlob.getBinaryStream());
                } else {
                    h.setFoto(null);
                }

                h.setEstado(rs.getInt("estado"));

                listaHabitaciones.add(h);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return listaHabitaciones;
    }

    @Override
    public int insertar(Habitacion nuevo) {
        int r = 0;
        String sql = "INSERT INTO habitacion(piso, tipo_habita, descripcion, direccion, id_distrito, precioxnoche, foto_habitacion, estado) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nuevo.getPiso());
            ps.setString(2, nuevo.getTipo());
            ps.setString(3, nuevo.getDescripcion());
            ps.setString(4, nuevo.getDirHotel());
            ps.setInt(5, nuevo.getDisHotel());
            ps.setDouble(6, nuevo.getPrecioNoche());

            if (nuevo.getFoto() != null) {
                ps.setBlob(7, nuevo.getFoto());
            } else {
                ps.setNull(7, java.sql.Types.BLOB);
            }

            ps.setInt(8, 1);

            r = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return r;
    }

    @Override
    public int modificar(Habitacion modificado) {
        int r = 0;
        String sql = "UPDATE habitacion SET piso=?, tipo_habita=?, descripcion=?, direccion=?, id_distrito=?, precioxnoche=?, foto_habitacion=?, estado=? WHERE id_habitacion=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, modificado.getPiso());
            ps.setString(2, modificado.getTipo());
            ps.setString(3, modificado.getDescripcion());
            ps.setString(4, modificado.getDirHotel());
            ps.setInt(5, modificado.getDisHotel());
            ps.setDouble(6, modificado.getPrecioNoche());

            if (modificado.getFoto() != null) {
                ps.setBlob(7, modificado.getFoto());
            } else {
                ps.setNull(7, java.sql.Types.BLOB);
            }

            ps.setInt(8, 1);
            ps.setInt(9, modificado.getIdhabitacion());

            r = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return r;
    }

    @Override
    public int eliminar(int eliminado) {
        int resultado = 0;
        String sql = "DELETE FROM habitacion WHERE id_habitacion = ?";

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
    public int desactivar(int desactiva) {
        int r = 0;
        String sql = "UPDATE habitacion SET estado=? WHERE id_habitacion=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, 0);
            ps.setInt(2, desactiva);
            r = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return r;
    }

    @Override
    public int activar(int restaurado) {
        int r = 0;
        String sql = "UPDATE habitacion SET estado=? WHERE id_habitacion=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, 1);
            ps.setInt(2, restaurado);
            r = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return r;
    }

    public int estado(int id, int estado) {
        int r = 0;
        String sql = "UPDATE habitacion SET estado=? WHERE id_habitacion=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, estado);
            ps.setInt(2, id);
            r = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return r;
    }

    @Override
    public Habitacion seleccionado(int id) {
        Habitacion h = new Habitacion();
        String sql = "select * from habitacion where id_habitacion=?";
        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    h.setIdhabitacion(rs.getInt("id_habitacion"));
                    h.setPiso(rs.getString("piso"));
                    h.setTipo(rs.getString("tipo_habita"));
                    h.setDescripcion(rs.getString("descripcion"));
                    h.setDirHotel(rs.getString("direccion"));
                    h.setDisHotel(rs.getInt("id_distrito"));
                    h.setPrecioNoche(rs.getDouble("precioxnoche"));
                    h.setEstado(rs.getInt("estado"));

                    Blob fotoBlob = rs.getBlob("foto_habitacion");

                    if (fotoBlob != null) {
                        h.setFoto(fotoBlob.getBinaryStream());
                    } else {
                        h.setFoto(null);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return h;
    }
}
