package com.utp.entidad.extras;

import java.time.LocalDate;

public class Especialidad {
    private int idespecialidad;
    private int estado;
    private String nombre;
    private String descripcion;
    private double salario;
    private LocalDate fechaactualizacion;

    public Especialidad() {
    }

    public Especialidad(int idespecialidad, int estado, String nombre, String descripcion, double salario, LocalDate fechaactualizacion) {
        this.idespecialidad = idespecialidad;
        this.estado = estado;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.salario = salario;
        this.fechaactualizacion = fechaactualizacion;
    }

    public int getIdespecialidad() {
        return idespecialidad;
    }

    public void setIdespecialidad(int idespecialidad) {
        this.idespecialidad = idespecialidad;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
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

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public LocalDate getFechaactualizacion() {
        return fechaactualizacion;
    }

    public void setFechaactualizacion(LocalDate fechaactualizacion) {
        this.fechaactualizacion = fechaactualizacion;
    }

        
}
