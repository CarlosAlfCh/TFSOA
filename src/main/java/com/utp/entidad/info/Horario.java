package com.utp.entidad.info;

public class Horario {
    private int idhorario;
    private int idhabitacion;
    private int idreserva;
    private int estado;

    public Horario() {
    }

    public Horario(int idhorario, int idhabitacion, int idreserva, int estado) {
        this.idhorario = idhorario;
        this.idhabitacion = idhabitacion;
        this.idreserva = idreserva;
        this.estado = estado;
    }

    public int getIdhorario() {
        return idhorario;
    }

    public void setIdhorario(int idhorario) {
        this.idhorario = idhorario;
    }

    public int getIdhabitacion() {
        return idhabitacion;
    }

    public void setIdhabitacion(int idhabitacion) {
        this.idhabitacion = idhabitacion;
    }

    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    
    
}
