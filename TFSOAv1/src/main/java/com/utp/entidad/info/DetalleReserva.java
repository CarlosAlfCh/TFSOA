package com.utp.entidad.info;

public class DetalleReserva {
    private int item;
    private int idservicio;    
    private int idhabitacion;
    private String nombre;    
    private String descripcion;    
    private double subtotal;
    private int npersonas;
    private double total;

    public DetalleReserva(){

    }

    public DetalleReserva(int item, int idservicio, int idhabitacion, String nombre, String descripcion, double subtotal, int npersonas, double total) {
        this.item = item;
        this.idservicio = idservicio;
        this.idhabitacion = idhabitacion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.subtotal = subtotal;
        this.npersonas = npersonas;
        this.total = total;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(int idservicio) {
        this.idservicio = idservicio;
    }

    public int getIdhabitacion() {
        return idhabitacion;
    }

    public void setIdhabitacion(int idhabitacion) {
        this.idhabitacion = idhabitacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getNpersonas() {
        return npersonas;
    }

    public void setNpersonas(int npersonas) {
        this.npersonas = npersonas;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    
    
}
