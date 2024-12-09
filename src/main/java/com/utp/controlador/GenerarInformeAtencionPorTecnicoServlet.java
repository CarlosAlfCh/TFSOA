package com.utp.controlador;

import com.utp.modelo.InformeAtencionTecnicoDAO;
import com.utp.entidad.InformeAtencionTecnico;
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

@WebServlet("/GenerarInformeAtencionPorTecnicoServlet")
public class GenerarInformeAtencionPorTecnicoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(GenerarInformeAtencionPorTecnicoServlet.class.getName());
    private final InformeAtencionTecnicoDAO informeAtencionTecnicoDAO = new InformeAtencionTecnicoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Iniciando método doGet");
        try {
            // Obtener datos de atención por técnico
            List<InformeAtencionTecnico> atencionPorTecnico = informeAtencionTecnicoDAO.obtenerAtencionesPorTecnico();
            logger.info("Datos obtenidos en doGet: " + atencionPorTecnico);

            // Pasar los datos al JSP
            request.setAttribute("atencionPorTecnico", atencionPorTecnico);

            logger.info("Datos obtenidos y establecidos en atributos de solicitud");
            request.getRequestDispatcher("/informeAtencionPorTecnico.jsp").forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en el método doGet", e);
            throw new ServletException("Error al obtener los datos del informe de atención por técnico", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Iniciando método doPost");
        try {
            // Obtener datos de atención por técnico
            List<InformeAtencionTecnico> atencionPorTecnico = informeAtencionTecnicoDAO.obtenerAtencionesPorTecnico();
            logger.info("Datos obtenidos en doPost: " + atencionPorTecnico);

            // Generar el PDF con los datos del informe de atención por técnico
            generarInformePDF(response, atencionPorTecnico);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en el método doPost", e);
            throw new ServletException("Error al generar el PDF del informe de atención por técnico", e);
        }
    }

    private void generarInformePDF(HttpServletResponse response, List<InformeAtencionTecnico> atencionPorTecnico) throws IOException, ServletException {
        logger.info("Iniciando método generarInformePDF");
        try {
            // Configurar la respuesta para la descarga de PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=InformeAtencionPorTecnico.pdf");
            try (OutputStream out = response.getOutputStream()) {
                Document document = new Document();
                PdfWriter.getInstance(document, out);
                document.open();

                // Añadir título
                Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
                Paragraph titleParagraph = new Paragraph("Informe de Atención por Técnico", fontTitle);
                titleParagraph.setAlignment(Element.ALIGN_CENTER);
                titleParagraph.setSpacingAfter(20);
                document.add(titleParagraph);

                // Añadir fecha de generación
                Font fontFecha = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);
                Paragraph fechaParagraph = new Paragraph("Generado el: " + java.time.LocalDate.now(), fontFecha);
                fechaParagraph.setAlignment(Element.ALIGN_RIGHT);
                fechaParagraph.setSpacingAfter(20);
                document.add(fechaParagraph);

                // Crear tabla para el informe de atención por técnico
                PdfPTable table = new PdfPTable(3); // Tres columnas: Técnico, Atenciones, Monto
                table.setWidthPercentage(100);
                table.setSpacingBefore(10f);
                table.setSpacingAfter(10f);

                // Añadir encabezados de la tabla
                Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
                PdfPCell header1 = new PdfPCell(new Phrase("Técnico", fontHeader));
                header1.setBackgroundColor(BaseColor.DARK_GRAY);
                table.addCell(header1);
                PdfPCell header2 = new PdfPCell(new Phrase("Atenciones", fontHeader));
                header2.setBackgroundColor(BaseColor.DARK_GRAY);
                table.addCell(header2);
                PdfPCell header3 = new PdfPCell(new Phrase("Monto", fontHeader));
                header3.setBackgroundColor(BaseColor.DARK_GRAY);
                table.addCell(header3);

                // Añadir datos a la tabla
                for (InformeAtencionTecnico informe : atencionPorTecnico) {
                    table.addCell(informe.getTecnico());
                    table.addCell(String.valueOf(informe.getCantidadAtenciones()));
                    table.addCell(String.valueOf(informe.getMonto()));
                    logger.info("Añadiendo datos al PDF: Técnico=" + informe.getTecnico() + ", Atenciones=" + informe.getCantidadAtenciones() + ", Monto=" + informe.getMonto());
                }

                document.add(table);
                document.close();
                logger.info("PDF generado exitosamente");
            } catch (DocumentException e) {
                logger.log(Level.SEVERE, "Error al generar el PDF", e);
                throw new ServletException("Error al generar el PDF.", e);
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en el método generarInformePDF", e);
            throw new ServletException("Ocurrió un error al generar el informe.", e);
        }
    }
}

