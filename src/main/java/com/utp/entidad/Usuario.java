package com.utp.entidad;

import java.io.InputStream;

public class Usuario extends Persona{
    private String turno;
    private int idespecialidad;
    private InputStream foto;

    public Usuario() {
        super();        
    }

    public Usuario(String turno, String horario, int idespecialidad, InputStream foto, int codigo, String nombres, String apelpat, String apelmat, String dni, String correo, String telefono, String contrasena, int estado, int rol, String Direccion, int Distrito) {
        super(codigo, nombres, apelpat, apelmat, dni, correo, telefono, contrasena, estado, rol, Direccion, Distrito);
        this.turno = turno;
        this.idespecialidad = idespecialidad;
        this.foto = foto;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getIdespecialidad() {
        return idespecialidad;
    }

    public void setIdespecialidad(int idespecialidad) {
        this.idespecialidad = idespecialidad;
    }   

    public InputStream getFoto() {
        return foto;
    }

    public void setFoto(InputStream foto) {
        this.foto = foto;
    }       
}
