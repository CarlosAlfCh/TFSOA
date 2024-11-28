package com.utp.entidad;

import java.io.InputStream;

public class Habitacion {
    private int idhabitacion;
    private String piso;
    private String tipo;
    private String descripcion;
    private InputStream foto;
    private Double precioNoche;
    private String dirHotel; //direccion del hotel
    private int disHotel; //distrito del hotel
    private int estado;

    public Habitacion() {
    }

    public Habitacion(int idhabitacion, String piso, String tipo, String descripcion, InputStream foto, Double precioNoche, String dirHotel, int disHotel, int estado) {
        this.idhabitacion = idhabitacion;
        this.piso = piso;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.foto = foto;
        this.precioNoche = precioNoche;
        this.dirHotel = dirHotel;
        this.disHotel = disHotel;
        this.estado = estado;
    }

    public int getIdhabitacion() {
        return idhabitacion;
    }

    public void setIdhabitacion(int idhabitacion) {
        this.idhabitacion = idhabitacion;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }

    public Double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(Double precioNoche) {
        this.precioNoche = precioNoche;
    }

    public String getDirHotel() {
        return dirHotel;
    }

    public void setDirHotel(String dirHotel) {
        this.dirHotel = dirHotel;
    }

    public int getDisHotel() {
        return disHotel;
    }

    public void setDisHotel(int disHotel) {
        this.disHotel = disHotel;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    
    
}
