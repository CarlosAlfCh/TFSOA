package com.utp.mensajes;

import javax.mail.MessagingException;

public class VerifyPayment {

    public int confirmacionPago(String correo, String idpago, String metodo, String fecha) throws MessagingException {
        int r = 0;

        // Enviar el correo de recuperación
        CorreoService correoService = new CorreoService();
        correoService.enviarCorreoPago(correo, idpago, metodo, fecha);

        return r;
    }
}
