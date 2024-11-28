package com.utp.entidad.info;

public class Consigna {
    private int idcita; // idreserva
    private int idpago;
    private String nombres;
    private String apelpat;
    private String apelmat;
    private String fecha;
    private double monto;
    private int estado;

    public Consigna() {
    }

    public Consigna(int idcita, int idpago, String nombres, String apelpat, String apelmat, String fecha, double monto, int estado) {
        this.idcita = idcita;
        this.idpago = idpago;
        this.nombres = nombres;
        this.apelpat = apelpat;
        this.apelmat = apelmat;
        this.fecha = fecha;
        this.monto = monto;
        this.estado = estado;
    }

    public int getIdcita() {
        return idcita;
    }

    public void setIdcita(int idcita) {
        this.idcita = idcita;
    }

    public int getIdpago() {
        return idpago;
    }

    public void setIdpago(int idpago) {
        this.idpago = idpago;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApelpat() {
        return apelpat;
    }

    public void setApelpat(String apelpat) {
        this.apelpat = apelpat;
    }

    public String getApelmat() {
        return apelmat;
    }

    public void setApelmat(String apelmat) {
        this.apelmat = apelmat;
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

    
    
}
