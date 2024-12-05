package com.utp.entidad.info;

import java.time.LocalDate;
import java.time.LocalTime;

public class Asistencia {
    private int id;
    private int idTecnico;
    private LocalDate fecha;
    private LocalTime horaIngreso;
    private LocalTime horaSalida;
    private int nhoras;
    
    // Constructor con parámetros
    public Asistencia(int id, int idTecnico, LocalDate fecha, LocalTime horaIngreso, LocalTime horaSalida, int nhoras) {
        this.id = id;
        this.idTecnico = idTecnico;
        this.fecha = fecha;
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
        this.nhoras = nhoras;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(LocalTime horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getNhoras() {
        return nhoras;
    }

    public void setNhoras(int nhoras) {
        this.nhoras = nhoras;
    }

    @Override
    public String toString() {
        return "Asistencia{" +
                "id=" + id +
                ", idTecnico=" + idTecnico +
                ", fecha=" + fecha +
                ", horaIngreso=" + horaIngreso +
                ", horaSalida=" + horaSalida +
                ", nhoras=" + nhoras +
                '}';
    }
}
