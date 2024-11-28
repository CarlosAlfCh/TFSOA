package com.utp.mensajes;

import com.utp.modelo.RewardsDAO;
import com.utp.util.CodeGenerator;
import javax.mail.MessagingException;

public class DiscountPromotions {
    
    private final RewardsDAO rdao = new RewardsDAO();
    
    public void enviarCodigoPromocion(String correo, int idcliente, int descuento) throws MessagingException {
        String fexpira;
        
        String codpromo = CodeGenerator.generateCodeLetter();
        
        fexpira = rdao.insertarCodigoXCliente(idcliente, descuento, codpromo); //Para registrar a clientes en la lista promocional
        
        CorreoService correoService = new CorreoService();
        correoService.enviarCorreoPromocional(correo, codpromo, descuento, fexpira);
    }
    
    public void enviarNuevoCodigoPromocion(String correo, int idcliente, int descuento) throws MessagingException {
        String fexpira;
        
        String codpromo = CodeGenerator.generateCodeLetter();
        
        fexpira = rdao.actualizarCodPromo(idcliente, descuento, codpromo); //Actualizar su codigo de descuento
        
        CorreoService correoService = new CorreoService();
        correoService.enviarCorreoPromocional(correo, codpromo, descuento, fexpira);
    }
    
}
