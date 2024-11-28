package com.utp.entidad.pagos;

import java.time.LocalDate;

public class Rewards {
    private int idpromo;
    private int idcliente;
    
    private String nomCliente;
    private String correo;
    
    private String codpromo;
    
    private LocalDate fGeneracion;
    private LocalDate fExpiracion;
    private LocalDate fUso;
    
    private int descuento;
    
    private int estado;

    public Rewards() {
    }

    public Rewards(int idpromo, int idcliente, String nomCliente, String correo, String codpromo, LocalDate fGeneracion, LocalDate fExpiracion, LocalDate fUso, int descuento, int estado) {
        this.idpromo = idpromo;
        this.idcliente = idcliente;
        this.nomCliente = nomCliente;
        this.correo = correo;
        this.codpromo = codpromo;
        this.fGeneracion = fGeneracion;
        this.fExpiracion = fExpiracion;
        this.fUso = fUso;
        this.descuento = descuento;
        this.estado = estado;
    }

    public int getIdpromo() {
        return idpromo;
    }

    public void setIdpromo(int idpromo) {
        this.idpromo = idpromo;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public LocalDate getfGeneracion() {
        return fGeneracion;
    }

    public void setfGeneracion(LocalDate fGeneracion) {
        this.fGeneracion = fGeneracion;
    }

    public LocalDate getfExpiracion() {
        return fExpiracion;
    }

    public void setfExpiracion(LocalDate fExpiracion) {
        this.fExpiracion = fExpiracion;
    }

    public LocalDate getfUso() {
        return fUso;
    }

    public void setfUso(LocalDate fUso) {
        this.fUso = fUso;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getCodpromo() {
        return codpromo;
    }

    public void setCodpromo(String codpromo) {
        this.codpromo = codpromo;
    }
}
