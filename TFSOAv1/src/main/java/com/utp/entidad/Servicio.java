package com.utp.entidad;

import java.io.InputStream;

public class Servicio {
    private int idservicio;
    private String nomserv;
    private String descripcion;
    private String duracion;
    private double precio;
    private String turno;
    private int idtipo;
    private int id_especialidad;
    private int estadoserv; 
    private InputStream imagen;

    public Servicio(){        
    }

    public Servicio(int idservicio, String nomserv, String descripcion, String duracion, double precio, String turno, int idtipo, int id_especialidad, int estadoserv, InputStream imagen) {
        this.idservicio = idservicio;
        this.nomserv = nomserv;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.precio = precio;
        this.turno = turno;
        this.idtipo = idtipo;
        this.id_especialidad = id_especialidad;
        this.estadoserv = estadoserv;
        this.imagen = imagen;
    }

    public int getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(int idservicio) {
        this.idservicio = idservicio;
    }

    public String getNomserv() {
        return nomserv;
    }

    public void setNomserv(String nomserv) {
        this.nomserv = nomserv;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getIdtipo() {
        return idtipo;
    }

    public void setIdtipo(int idtipo) {
        this.idtipo = idtipo;
    }

    public int getId_especialidad() {
        return id_especialidad;
    }

    public void setId_especialidad(int id_especialidad) {
        this.id_especialidad = id_especialidad;
    }

    public int getEstadoserv() {
        return estadoserv;
    }

    public void setEstadoserv(int estadoserv) {
        this.estadoserv = estadoserv;
    }

    public InputStream getImagen() {
        return imagen;
    }

    public void setImagen(InputStream imagen) {
        this.imagen = imagen;
    }

   
}
