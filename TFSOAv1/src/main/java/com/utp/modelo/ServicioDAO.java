package com.utp.modelo;

import com.utp.entidad.Servicio;
import com.utp.util.Conexion;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioDAO implements CRUD<Servicio> {

    Conexion cn = new Conexion();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public List<Servicio> listar() {
        ArrayList<Servicio> listaServicios = new ArrayList<>();
        String sql = "select * from servicio";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Servicio servicio = new Servicio();
                servicio.setIdservicio(rs.getInt("id_servicio"));
                servicio.setNomserv(rs.getString("nom_serv"));
                servicio.setDescripcion(rs.getString("descripcion"));
                servicio.setDuracion(rs.getString("duracion"));
                servicio.setPrecio(rs.getDouble("precio"));
                servicio.setTurno(rs.getString("turno"));
                servicio.setIdtipo(rs.getInt("id_tipo"));
                servicio.setId_especialidad(rs.getInt("id_especialidad"));
                servicio.setEstadoserv(rs.getInt("estado"));

                Blob fotoBlob = rs.getBlob("foto_servicio");
                if (fotoBlob != null) {
                    servicio.setImagen(fotoBlob.getBinaryStream());
                } else {
                    servicio.setImagen(null);
                }

                listaServicios.add(servicio);
            }
        } catch (SQLException e) {
        }
        return listaServicios;
    }

    @Override
    public int insertar(Servicio nuevo) {
        int r = 0;
        String sql = "INSERT INTO servicio(nom_serv, descripcion, duracion, precio, turno, id_tipo, id_especialidad, estado, foto_servicio) VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);

            ps.setString(1, nuevo.getNomserv());
            ps.setString(2, nuevo.getDescripcion());
            ps.setString(3, nuevo.getDuracion());
            ps.setDouble(4, nuevo.getPrecio());
            ps.setString(5, nuevo.getTurno());
            ps.setInt(6, nuevo.getIdtipo());
            ps.setInt(7, nuevo.getId_especialidad());
            ps.setInt(8, 1);

            if (nuevo.getImagen() != null) {
                ps.setBlob(9, nuevo.getImagen());
            } else {
                ps.setNull(9, java.sql.Types.BLOB);
            }

            r = ps.executeUpdate();

        } catch (SQLException e) {
        } 
        return r;
    }

    @Override
    public int modificar(Servicio modificado) {
        int r = 0;
        String sql = "UPDATE servicio SET nom_serv=?, descripcion=?, duracion=?, precio=?, turno=?, id_tipo=?, id_especialidad=?, estado=?, foto_servicio=? WHERE id_servicio=?";

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, modificado.getNomserv());
            ps.setString(2, modificado.getDescripcion());
            ps.setString(3, modificado.getDuracion());
            ps.setDouble(4, modificado.getPrecio());
            ps.setString(5, modificado.getTurno());
            ps.setInt(6, modificado.getIdtipo());
            ps.setInt(7, modificado.getId_especialidad());
            ps.setInt(8, 1);

            if (modificado.getImagen() != null) {
                ps.setBlob(9, modificado.getImagen());
            } else {
                ps.setNull(9, java.sql.Types.BLOB);
            }

            ps.setInt(10, modificado.getIdservicio());

            r = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.toString());
        } 
        return r;
    }

    @Override
    public int eliminar(int eliminado) {
        int r = 0;
        String sql = "UPDATE servicio SET estado=? WHERE id_servicio=?";

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 0);
            ps.setInt(2, eliminado);
            r = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } 
        return r;
    }

    @Override
    public int restaurar(int restaurado) {
        int r = 0;
        String sql = "UPDATE servicio SET estado=? WHERE id_servicio=?";

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
    public Servicio seleccionado(int id) {
        Servicio serv = new Servicio();
        String sql = "select * from servicio where id_servicio=?";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                serv.setIdservicio(rs.getInt("id_servicio"));
                serv.setNomserv(rs.getString("nom_serv"));
                serv.setDescripcion(rs.getString("descripcion"));
                serv.setDuracion(rs.getString("duracion"));
                serv.setPrecio(rs.getDouble("precio"));
                serv.setTurno(rs.getString("turno"));
                serv.setIdtipo(rs.getInt("id_tipo"));
                serv.setId_especialidad(rs.getInt("id_especialidad"));
                Blob fotoBlob = rs.getBlob("foto_servicio");

                if (fotoBlob != null) {
                    serv.setImagen(fotoBlob.getBinaryStream());
                } else {
                    serv.setImagen(null);
                }

            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return serv;
    }

}