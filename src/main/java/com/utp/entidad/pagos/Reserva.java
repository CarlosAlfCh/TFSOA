package com.utp.entidad.pagos;

import com.utp.entidad.info.DetalleReserva;
import java.time.LocalDate;
import java.util.List;

public class Reserva {
    private int idreserva;//idreserva
    private int idcliente;    
    private int idtecnico; 
    private int idpago; 
    private String fecha;
    
    private LocalDate fechaServicio;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    
    private double monto;
    private int estado;
    
    private List<DetalleReserva> detalleService;

    public Reserva() {
    }

    public Reserva(int idreserva, int idcliente, int idtecnico, int idpago, String fecha, LocalDate fechaServicio, LocalDate fechaIngreso, LocalDate fechaSalida, double monto, int estado, List<DetalleReserva> detalleService) {
        this.idreserva = idreserva;
        this.idcliente = idcliente;
        this.idtecnico = idtecnico;
        this.idpago = idpago;
        this.fecha = fecha;
        this.fechaServicio = fechaServicio;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.monto = monto;
        this.estado = estado;
        this.detalleService = detalleService;
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

    public LocalDate getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(LocalDate fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
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

    public List<DetalleReserva> getDetalleService() {
        return detalleService;
    }

    public void setDetalleService(List<DetalleReserva> detalleService) {
        this.detalleService = detalleService;
    }

}
