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

    Conexion cn = new Conexion();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int r = 0;

    public int insertpago(String cod, String metodo, String fecha) {
        String codigo = cod;
        String sql = "INSERT INTO pago(n_operacion, metodo, fecha_pago, valido) VALUES (?,?,?,?)";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            if (cod == null || cod.equals("")) {
                codigo = CodeGenerator.generateCode();
                ps.setString(1, codigo);
            } else {
                ps.setString(1, cod);
            }
            ps.setString(2, metodo);
            ps.setString(3, fecha);
            ps.setInt(4, 0);

            r = ps.executeUpdate();

            sql = "select id_pago from pago where n_operacion=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                r = rs.getInt("id_pago");
            }
            System.out.println(r+" pagoDAO");
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return r;
    }

    public List<Pago> listarpagos() {
        ArrayList<Pago> list = new ArrayList<>();
        String sql = "SELECT persona.nombres, persona.apelpat, persona.apelmat, persona.correo, pago.id_pago, "
                + " pago.n_operacion, pago.metodo, pago.fecha_pago, pago.valido "
                + " FROM persona INNER JOIN reserva ON persona.codigo=reserva.id_cliente INNER JOIN pago ON reserva.id_pago=pago.id_pago ORDER BY pago.id_pago DESC";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pago pa = new Pago();
                pa.setNombres(rs.getString(1));
                pa.setApelpat(rs.getString(2));
                pa.setApelmat(rs.getString(3));
                pa.setCorreo(rs.getString(4));
                pa.setIdpago(rs.getInt(5));
                pa.setCodigo(rs.getString(6));
                pa.setMetodo(rs.getString(7));
                pa.setFechapago(rs.getString(8));
                pa.setValido(rs.getInt(9));
                list.add(pa);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public int pagovalido(int idpago) {
        int r = 0;
        String sql = "UPDATE pago SET valido=? WHERE id_pago=" + idpago;

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 1);
            r = ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return r;
    }

    public int pagoinvalido(int idpago) {
        String sql = "UPDATE pago SET valido=? WHERE id_pago=" + idpago;

        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 0);
            r = ps.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        System.out.println("Valido?? " + idpago);
        return r;
    }
}
