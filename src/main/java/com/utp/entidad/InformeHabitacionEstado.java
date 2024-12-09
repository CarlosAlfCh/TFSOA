package com.utp.entidad;

public class InformeHabitacionEstado {
    private int estado;
    private int cantidad;

    public InformeHabitacionEstado(int estado, int cantidad) {
        this.estado = estado;
        this.cantidad = cantidad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
