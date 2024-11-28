package com.utp.mensajes;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CorreoService {

    private final String username = "botrelaxwellness@gmail.com";
    private final String password = "srqgmjvksbulbxce";
    private final String host = "smtp.gmail.com";
    private final String port = "587";
    private final boolean starttls = true;
    private final boolean auth = true;

    public void enviarCorreoRecuperacion(String destinatario, String clave) throws MessagingException {
        String asunto = "Restablecer Contraseña - Relax & Wellness";
        String mensajeHTML = "<html>"
                + "<body>"
                + "<h2 style='color:#2E86C1;'>Restablecer su contraseña</h2>"
                + "<p>Hola,</p>"
                + "<p>Recibimos una solicitud para restablecer su contraseña. Use el siguiente código para restablecer su cuenta:</p>"
                + "<div style='background-color:#f0f0f0;padding:10px;border-radius:5px;text-align:center;'>"
                + "<h3 style='font-size:24px;'>" + clave + "</h3>"
                + "</div>"
                + "<p style='color:#7f8c8d;'>Este código es válido por 10 minutos.</p>"
                + "<p>Si no solicitó restablecer su contraseña, puede ignorar este mensaje. Su contraseña no será modificada.</p>"
                + "<br><p>Gracias,<br>El equipo de Relax & Wellness</p>"
                + "</body>"
                + "</html>";
        enviarCorreo(destinatario, asunto, mensajeHTML);
    }

    public void enviarCorreoPago(String destinatario, String idPago, String metodo, String fecha) throws MessagingException {
        String asunto = "Confirmación de pago - Relax & Wellness";
        String mensajeHTML = "<html>"
                + "<body>"
                + "<h2 style='color:#2E86C1;'>Confirmación de Pago</h2>"
                + "<p>Hola,</p>"
                + "<p>Gracias por su pago. Aquí están los detalles de la transacción:</p>"
                + "<table style='border-collapse:collapse;width:100%;'>"
                + "<tr><td style='padding:8px;border:1px solid #ddd;'>Código de pago:</td><td style='padding:8px;border:1px solid #ddd;'>00000" + idPago + "</td></tr>"
                + "<tr><td style='padding:8px;border:1px solid #ddd;'>Método de pago:</td><td style='padding:8px;border:1px solid #ddd;'>" + metodo + "</td></tr>"
                + "<tr><td style='padding:8px;border:1px solid #ddd;'>Fecha de pago:</td><td style='padding:8px;border:1px solid #ddd;'>" + fecha + "</td></tr>"
                + "</table>"
                + "<p>Si tiene alguna duda sobre su pago, no dude en ponerse en contacto con nosotros.</p>"
                + "<br><p>Gracias,<br>El equipo de Relax & Wellness</p>"
                + "</body>"
                + "</html>";
        enviarCorreo(destinatario, asunto, mensajeHTML);
    }
    
    public void enviarCorreoPromocional(String destinatario, String codpromo, int descuento, String fechaExpiracion) throws MessagingException {
    String asunto = "¡Tu Código Promocional de Relax & Wellness!";
    String mensajeHTML = "<html>"
            + "<body>"
            + "<h2 style='color:#2E86C1;'>¡Aprovecha tu Descuento Especial!</h2>"
            + "<p>Hola,</p>"
            + "<p>¡Gracias por ser parte de Relax & Wellness! Te hemos otorgado un código promocional para que disfrutes de un descuento especial del " + descuento + "% en nuestros servicios.</p>"
            + "<div style='background-color:#f0f0f0;padding:10px;border-radius:5px;text-align:center;'>"
            + "<h3 style='font-size:24px;'>Código: " + codpromo + "</h3>"
            + "</div>"
            + "<p style='color:#7f8c8d;'>Este código es válido hasta el " + fechaExpiracion + ".</p>"
            + "<p>¡No pierdas esta oportunidad de relajarte y revitalizarte con nosotros!</p>"
            + "<br><p>Gracias,<br>El equipo de Relax & Wellness</p>"
            + "</body>"
            + "</html>";
    
    enviarCorreo(destinatario, asunto, mensajeHTML);
}

    private void enviarCorreo(String destinatario, String asunto, String mensajeHTML) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", String.valueOf(auth));
        props.put("mail.smtp.starttls.enable", String.valueOf(starttls));
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props, new Authenticator() { 
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message email = new MimeMessage(session);
        email.setFrom(new InternetAddress(username));
        email.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
        email.setSubject(asunto);
        // Set the content type to HTML
        email.setContent(mensajeHTML, "text/html");

        Transport.send(email);
    }
}
