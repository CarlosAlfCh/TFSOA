package com.utp.entidad.pagos;

import com.utp.entidad.info.DetalleReserva;
import java.util.List;

public class Reserva {
    private int idreserva;//idreserva
    private int idcliente;    
    private int idtecnico; 
    private int idpago; 
    private String fecha;
    private double monto;
    private int estado;
    
    private List<DetalleReserva> detallereserva;

    public Reserva() {
    }

    public Reserva(int idreserva, int idcliente, int idtecnico, int idpago, String fecha, double monto, int estado, List<DetalleReserva> detallereserva) {
        this.idreserva = idreserva;
        this.idcliente = idcliente;
        this.idtecnico = idtecnico;
        this.idpago = idpago;
        this.fecha = fecha;
        this.monto = monto;
        this.estado = estado;
        this.detallereserva = detallereserva;
    }

    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdtecnico() {
        return idtecnico;
    }

    public void setIdtecnico(int idtecnico) {
        this.idtecnico = idtecnico;
    }

    public int getIdpago() {
        return idpago;
    }

    public void setIdpago(int idpago) {
        this.idpago = idpago;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public List<DetalleReserva> getDetallereserva() {
        return detallereserva;
    }

    public void setDetallereserva(List<DetalleReserva> detallereserva) {
        this.detallereserva = detallereserva;
    }
    
}
