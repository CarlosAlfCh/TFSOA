package com.utp.controlador;

import com.utp.modelo.BoletaReservaDAO;
import com.utp.entidad.BoletaReserva;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/GenerarBoletaReservaServlet")
public class GenerarBoletaReservaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(GenerarBoletaReservaServlet.class.getName());
    private final BoletaReservaDAO boletaReservaDAO = new BoletaReservaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Iniciando método doGet");
        String idReservaStr = request.getParameter("idReserva");
        logger.info("Valor de idReservaStr: " + idReservaStr);

        // Verificar que el parámetro no sea nulo o vacío
        if (idReservaStr == null || idReservaStr.isEmpty()) {
            // Obtener la lista de reservas y pasarla al JSP
            List<BoletaReserva> reservas = boletaReservaDAO.obtenerTodasLasReservas();
            request.setAttribute("reservas", reservas);
            logger.info("Lista de reservas obtenida y pasada al JSP");

            request.getRequestDispatcher("/generarBoletaReserva.jsp").forward(request, response);
            return;
        }

        try {
            int idReserva = Integer.parseInt(idReservaStr);
            logger.info("ID de Reserva convertido a entero: " + idReserva);

            // Obtener datos de la reserva
            BoletaReserva boletaReserva = boletaReservaDAO.obtenerBoletaReserva(idReserva);
            logger.info("Datos obtenidos en doGet: " + boletaReserva);

            // Generar el PDF con los datos de la boleta de reserva
            generarBoletaPDF(response, boletaReserva);
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Error al convertir el parámetro idReserva a entero", e);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de reserva inválido");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en el método doGet", e);
            throw new ServletException("Error al generar la boleta de reserva", e);
        }
    }

    private void generarBoletaPDF(HttpServletResponse response, BoletaReserva boleta) throws IOException, ServletException {
        logger.info("Iniciando método generarBoletaPDF");
        try {
            // Configurar la respuesta para la descarga de PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=BoletaReserva_" + boleta.getIdReserva() + ".pdf");
            try (OutputStream out = response.getOutputStream()) {
                // Crear un documento con un tamaño de página personalizado (boleta estrecha)
                Document document = new Document(new Rectangle(216, 720)); // Tamaño en puntos (72 puntos por pulgada)
                PdfWriter.getInstance(document, out);
                document.open();

                // Añadir título
                Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);
                Paragraph titleParagraph = new Paragraph("Boleta de Reserva", fontTitle);
                titleParagraph.setAlignment(Element.ALIGN_CENTER);
                titleParagraph.setSpacingAfter(20);
                document.add(titleParagraph);

                // Añadir fecha de generación
                Font fontFecha = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
                Paragraph fechaParagraph = new Paragraph("Generado el: " + java.time.LocalDate.now(), fontFecha);
                fechaParagraph.setAlignment(Element.ALIGN_RIGHT);
                fechaParagraph.setSpacingAfter(10);
                document.add(fechaParagraph);

                // Añadir detalles de la reserva
                Font fontDetails = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);
                Paragraph detailsParagraph = new Paragraph();
                detailsParagraph.add(new Phrase("ID Reserva: " + boleta.getIdReserva() + "\n", fontDetails));
                detailsParagraph.add(new Phrase("Fecha de Reserva: " + boleta.getFechaReserva() + "\n", fontDetails));
                detailsParagraph.add(new Phrase("Fecha de Check-in: " + boleta.getFechaCheckIn() + "\n", fontDetails));
                detailsParagraph.add(new Phrase("Fecha de Check-out: " + boleta.getFechaCheckOut() + "\n", fontDetails));
                detailsParagraph.add(new Phrase("Cliente: " + boleta.getClienteNombre() + "\n", fontDetails));
                detailsParagraph.add(new Phrase("Correo: " + boleta.getClienteCorreo() + "\n", fontDetails));
                detailsParagraph.add(new Phrase("Teléfono: " + boleta.getClienteTelefono() + "\n", fontDetails));
                detailsParagraph.add(new Phrase("Monto: " + boleta.getMonto() + "\n", fontDetails));

                detailsParagraph.setSpacingAfter(20);
                document.add(detailsParagraph);

                document.close();
                logger.info("PDF generado exitosamente");
            } catch (DocumentException e) {
                logger.log(Level.SEVERE, "Error al generar el PDF", e);
                throw new ServletException("Error al generar el PDF.", e);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en el método generarBoletaPDF", e);
            throw new ServletException("Ocurrió un error al generar la boleta de reserva.", e);
        }
    }
}




