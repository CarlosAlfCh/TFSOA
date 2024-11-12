package com.utp.entidad;

public abstract class Persona {
    private int codigo;
    private String nombres;
    private String apelpat;
    private String apelmat;
    private String dni;
    private String correo;
    private String telefono;
    private String contrasena;
    private int estado;
    private int rol;   
    private String Direccion;
    private int Distrito;

    public Persona() {        
    }

    public Persona(int codigo, String nombres, String apelpat, String apelmat, String dni, String correo, String telefono, String contrasena, int estado, int rol, String Direccion, int Distrito) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apelpat = apelpat;
        this.apelmat = apelmat;
        this.dni = dni;
        this.correo = correo;
        this.telefono = telefono;
        this.contrasena = contrasena;
        this.estado = estado;
        this.rol = rol;
        this.Direccion = Direccion;
        this.Distrito = Distrito;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public int getDistrito() {
        return Distrito;
    }

    public void setDistrito(int Distrito) {
        this.Distrito = Distrito;
    }
    
    
}
