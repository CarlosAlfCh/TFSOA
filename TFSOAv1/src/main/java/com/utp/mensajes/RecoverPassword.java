package com.utp.mensajes;

import com.utp.util.CodeGenerator;
import com.utp.modelo.AutenticacionDAO;
import javax.mail.MessagingException;

public class RecoverPassword {

    private final AutenticacionDAO user = new AutenticacionDAO();

    public int enviarCorreoRecuperacion(String correo) throws MessagingException {
        int r;
        String codigoRecuperacion = CodeGenerator.generateCode();

        r = user.actualizarCodigoRecuperacion(correo, codigoRecuperacion);

        CorreoService correoService = new CorreoService();
        correoService.enviarCorreoRecuperacion(correo, codigoRecuperacion);

        return r;
    }

    public int actualizarContrasena(String clave, String nuevaContrasena) {
        
        return user.actualizarContrasena(clave, nuevaContrasena);
        
    }
}
