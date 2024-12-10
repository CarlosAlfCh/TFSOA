package com.utp.modelo;

import com.utp.entidad.extras.Comentario;
import com.utp.util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDAO {

    
    public int agregar(Comentario comentario) {
        int resultado = 0;
        String sql = "INSERT INTO comentarios (id_persona, comentario, nestrellas) VALUES (?, ?, ?)";
        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, comentario.getIdPersona());
            ps.setString(2, comentario.getComentario());
            ps.setInt(3, comentario.getNestrellas());
            resultado = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al agregar comentario: " + e.getMessage());
        }
        return resultado;
    }

    public List<Comentario> listar() {
        List<Comentario> lista = new ArrayList<>();
        String sql = "SELECT c.id, c.id_persona, c.comentario, c.nestrellas, "
                + " CONCAT(p.nombres, ' ', p.apelpat, IFNULL(CONCAT(' ', p.apelmat), '')) AS cliente "
                + " FROM comentarios c INNER JOIN persona p ON c.id_persona = p.codigo ORDER BY c.id DESC;";
        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Comentario comentario = new Comentario();
                comentario.setId(rs.getInt("id"));
                comentario.setIdPersona(rs.getInt("id_persona"));
                comentario.setComentario(rs.getString("comentario"));
                comentario.setNestrellas(rs.getInt("nestrellas"));
                comentario.setCliente(rs.getString("cliente"));
                lista.add(comentario);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar comentarios: " + e.getMessage());
        }
        return lista;
    }

    public int eliminar(int id) {
        int resultado = 0;
        String sql = "DELETE FROM comentarios WHERE id = ?";
        try (Connection conn = Conexion.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error al eliminar comentario: " + e.getMessage());
        }
        return resultado;
    }

}
