package com.utp.entidad.extras;

public class Comentario {
    private int id;
    private int idPersona;
    private String cliente;
    private String comentario;
    private int nestrellas;

    public Comentario() {
    }

    public Comentario(int id, int idPersona, String cliente, String comentario, int nestrellas) {
        this.id = id;
        this.idPersona = idPersona;
        this.cliente = cliente;
        this.comentario = comentario;
        this.nestrellas = nestrellas;
    }

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

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getNestrellas() {
        return nestrellas;
    }

    public void setNestrellas(int nestrellas) {
        this.nestrellas = nestrellas;
    }

    
    
    
}
