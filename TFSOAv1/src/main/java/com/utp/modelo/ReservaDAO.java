package com.utp.modelo;

import com.utp.entidad.pagos.Reserva;
import com.utp.entidad.info.DetalleReserva;
import com.utp.util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    Conexion cn = new Conexion();
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int r = 0;

    public int GenerarCita(Reserva cita) {
        int idreserva = 0;
        String sql = "insert into reserva(id_cliente, id_pago, f_reserva, monto, estado) values(?,?,?,?,?)";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cita.getIdcliente());
            ps.setInt(2, cita.getIdpago());
            ps.setString(3, cita.getFecha());
            ps.setDouble(4, cita.getMonto());
            ps.setInt(5, 0);
            r = ps.executeUpdate();

            sql = "select id_reserva from reserva where id_pago=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, cita.getIdpago());
            rs = ps.executeQuery();
            while (rs.next()) {
                idreserva = rs.getInt("id_reserva");
            }

            for (DetalleReserva detalle : cita.getDetallereserva()) {
                sql = "insert into detallereserva(id_reserva, id_servicio, id_habitacion, npersonas, subtotal, total)values(?,?,?,?,?)";

                ps = conn.prepareStatement(sql);
                ps.setInt(1, idreserva);
                ps.setInt(2, detalle.getIdservicio());
                ps.setInt(3, detalle.getIdhabitacion());
                ps.setInt(4, detalle.getNpersonas());
                ps.setDouble(5, detalle.getSubtotal());
                ps.setDouble(6, detalle.getTotal());
                r = ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return r;
    }

    public List<Reserva> listares() {
        ArrayList<Reserva> list = new ArrayList<>();
        String sql = "select * from reserva";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Reserva c = new Reserva();
                c.setIdreserva(rs.getInt("id_reserva"));
                c.setIdcliente(rs.getInt("id_cliente"));
                c.setIdtecnico(rs.getInt("id_tecnico"));
                c.setIdpago(rs.getInt("id_pago"));
                c.setFecha(rs.getString("f_reserva"));
                c.setMonto(rs.getDouble("monto"));
                c.setEstado(rs.getInt("estado"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Reserva> listasign(int codigo) {
        ArrayList<Reserva> list = new ArrayList<>();
        String sql = "select * from reserva where id_tecnico=" + codigo;
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Reserva c = new Reserva();
                c.setIdreserva(rs.getInt("id_reserva"));
                c.setIdcliente(rs.getInt("id_cliente"));
                c.setIdtecnico(rs.getInt("id_tecnico"));
                c.setIdpago(rs.getInt("id_pago"));
                c.setFecha(rs.getString("f_reserva"));
                c.setMonto(rs.getDouble("monto"));
                c.setEstado(rs.getInt("estado"));

                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Reserva seleccionado(int codigo) {
        Reserva c = new Reserva();
        String sql = "select * from reserva where id_reserva=?";
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                c.setIdreserva(rs.getInt("id_reserva"));
                c.setFecha(rs.getString("f_reserva"));
                c.setMonto(rs.getDouble("monto"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return c;
    }

    public int asignar(int idreserva, int idtecnico) {
        String sql = "UPDATE reserva SET id_tecnico=? WHERE id_reserva=" + idreserva;
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, idtecnico);

            r = ps.executeUpdate();
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return r;
    }

    public int quitar(int idreserva) {
        String sql = "UPDATE reserva SET id_tecnico=? WHERE id_reserva=" + idreserva;
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, 0);

            r = ps.executeUpdate();
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return r;
    }

    public int marcar(int idreserva) {
        String sql = "UPDATE reserva SET estado=? WHERE id_reserva=" + idreserva;
        try {
            conn = cn.conectar();
            ps = conn.prepareStatement(sql);

            ps.setInt(1, 1);

            r = ps.executeUpdate();
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return r;
    }
}
