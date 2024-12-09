package com.utp.entidad;

public class Comentario {
    private int id;
    private int idPersona;
    private String comentario;
    private int nEstrellas;

    public Comentario(int id, int idPersona, String comentario, int nEstrellas) {
        this.id = id;
        this.idPersona = idPersona;
        this.comentario = comentario;
        this.nEstrellas = nEstrellas;
    }

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getNEstrellas() {
        return nEstrellas;
    }

    public void setNEstrellas(int nEstrellas) {
        this.nEstrellas = nEstrellas;
    }
}
