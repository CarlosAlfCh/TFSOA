package com.utp.entidad;

public class BoletaReserva {
    private int idReserva;
    private String fechaReserva;
    private String fechaCheckIn;
    private String fechaCheckOut;
    private int idCliente;
    private String clienteNombre;
    private String clienteCorreo;
    private String clienteTelefono;
    private double monto;

    public BoletaReserva(int idReserva, String fechaReserva, String fechaCheckIn, String fechaCheckOut, int idCliente, String clienteNombre, String clienteCorreo, String clienteTelefono, double monto) {
        this.idReserva = idReserva;
        this.fechaReserva = fechaReserva;
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckOut;
        this.idCliente = idCliente;
        this.clienteNombre = clienteNombre;
        this.clienteCorreo = clienteCorreo;
        this.clienteTelefono = clienteTelefono;
        this.monto = monto;
    }

    // Getters y setters

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getFechaCheckIn() {
        return fechaCheckIn;
    }

    public void setFechaCheckIn(String fechaCheckIn) {
        this.fechaCheckIn = fechaCheckIn;
    }

    public String getFechaCheckOut() {
        return fechaCheckOut;
    }

    public void setFechaCheckOut(String fechaCheckOut) {
        this.fechaCheckOut = fechaCheckOut;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getClienteCorreo() {
        return clienteCorreo;
    }

    public void setClienteCorreo(String clienteCorreo) {
        this.clienteCorreo = clienteCorreo;
    }

    public String getClienteTelefono() {
        return clienteTelefono;
    }

    public void setClienteTelefono(String clienteTelefono) {
        this.clienteTelefono = clienteTelefono;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
}
