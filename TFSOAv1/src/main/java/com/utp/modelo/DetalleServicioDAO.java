package com.utp.modelo;

import com.utp.entidad.info.Consigna;
import com.utp.entidad.pagos.DetalleServicio;
import com.utp.util.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DetalleServicioDAO {
    Conexion cn = new Conexion();
    Connection conn= null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public List<DetalleServicio> listar(int idreserva) {
        ArrayList<DetalleServicio>list=new ArrayList<>();
        String sql="SELECT servicio.nom_serv, servicio.descripcion , detallereserva.npersonas , detallereserva.subtotal , detallereserva.total FROM servicio INNER JOIN detallereserva on servicio.id_servicio=detallereserva.id_servicio WHERE detallereserva.id_reserva="+idreserva+";";
        try {
            conn=cn.conectar();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                DetalleServicio detalle = new DetalleServicio();
                detalle.setNomserv(rs.getString(1));
                detalle.setDescripcion(rs.getString(2));
                detalle.setNpersonas(rs.getInt(3));
                detalle.setSubtotal(rs.getDouble(4));
                detalle.setTotal(rs.getDouble(5));
                list.add(detalle);
            }
        } catch (SQLException e) {
        }
        return list;
    }
    
    public List<Consigna> listconsigna(int idtecnico) {
        ArrayList<Consigna>list=new ArrayList<>();
        String sql="SELECT reserva.id_reserva, persona.nombres, persona.apelpat, persona.apelmat, reserva.id_pago, reserva.f_reserva, reserva.monto, reserva.estado FROM reserva INNER JOIN persona ON reserva.id_cliente = persona.codigo WHERE reserva.id_tecnico ="+idtecnico+";";
        try {
            conn=cn.conectar();
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Consigna co = new Consigna();
                co.setIdcita(rs.getInt(1));
                co.setNombres(rs.getString(2));
                co.setApelpat(rs.getString(3));
                co.setApelmat(rs.getString(4));
                co.setIdpago(rs.getInt(5));
                co.setFecha(rs.getString(6));
                co.setMonto(rs.getDouble(7));
                co.setEstado(rs.getInt(8));
                
                list.add(co);
            }
        } catch (SQLException e) {
        }
        return list;
    }
}
