package com.utp.entidad;

public class InformeAtencionTecnico {
    private String tecnico;
    private int cantidadAtenciones;
    private double monto;

    public InformeAtencionTecnico(String tecnico, int cantidadAtenciones, double monto) {
        this.tecnico = tecnico;
        this.cantidadAtenciones = cantidadAtenciones;
        this.monto = monto;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public int getCantidadAtenciones() {
        return cantidadAtenciones;
    }

    public void setCantidadAtenciones(int cantidadAtenciones) {
        this.cantidadAtenciones = cantidadAtenciones;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
