package com.utp.entidad.info;

public class Notificacion {
    private int idpago;
    private int idreserva;
    private String mensaje;
    private int estado;

    public Notificacion() {
    }

    public Notificacion(int idpago, int idreserva, String mensaje, int estado) {
        this.idpago = idpago;
        this.idreserva = idreserva;
        this.mensaje = mensaje;
        this.estado = estado;
    }

    public int getIdpago() {
        return idpago;
    }

    public void setIdpago(int idpago) {
        this.idpago = idpago;
    }

    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    
}
