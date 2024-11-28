package com.utp.entidad.info;

import java.time.LocalDate;

public class Asignacion {
    
    private int idreserva;
    private int idpago;
    private String nomCliente;
    private String nomTecnico;
    private String fecha;
    private LocalDate fechaServicio;
    private double monto;
    private int estado;

    public Asignacion() {
    }

    public Asignacion(int idreserva, int idpago, String nomCliente, String nomTecnico, String fecha, LocalDate fechaServicio, double monto, int estado) {
        this.idreserva = idreserva;
        this.idpago = idpago;
        this.nomCliente = nomCliente;
        this.nomTecnico = nomTecnico;
        this.fecha = fecha;
        this.fechaServicio = fechaServicio;
        this.monto = monto;
        this.estado = estado;
    }

    public int getIdreserva() {
        return idreserva;
    }

    public int getIdpago() {
        return idpago;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public String getNomTecnico() {
        return nomTecnico;
    }

    public String getFecha() {
        return fecha;
    }

    public LocalDate getFechaServicio() {
        return fechaServicio;
    }

    public double getMonto() {
        return monto;
    }

    public int getEstado() {
        return estado;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public void setIdpago(int idpago) {
        this.idpago = idpago;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public void setNomTecnico(String nomTecnico) {
        this.nomTecnico = nomTecnico;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setFechaServicio(LocalDate fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
