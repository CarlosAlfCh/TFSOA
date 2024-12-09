package com.utp.controlador;

import com.utp.modelo.InformeHabitacionesEstadoDAO;
import com.utp.entidad.InformeHabitacionEstado;
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

@WebServlet("/GenerarInformeHabitacionesEstadoServlet")
public class GenerarInformeHabitacionesEstadoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(GenerarInformeHabitacionesEstadoServlet.class.getName());
    private final InformeHabitacionesEstadoDAO informeHabitacionesEstadoDAO = new InformeHabitacionesEstadoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Iniciando método doGet");
        try {
            // Obtener datos de habitaciones por estado
            List<InformeHabitacionEstado> habitacionesPorEstado = informeHabitacionesEstadoDAO.obtenerHabitacionesPorEstado();
            logger.info("Datos obtenidos en doGet: " + habitacionesPorEstado);

            // Pasar los datos al JSP
            request.setAttribute("habitacionesPorEstado", habitacionesPorEstado);

            logger.info("Datos obtenidos y establecidos en atributos de solicitud");
            request.getRequestDispatcher("/informeHabitacionesEstado.jsp").forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en el método doGet", e);
            throw new ServletException("Error al obtener los datos del informe de habitaciones por estado", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Iniciando método doPost");
        try {
            // Obtener datos de habitaciones por estado
            List<InformeHabitacionEstado> habitacionesPorEstado = informeHabitacionesEstadoDAO.obtenerHabitacionesPorEstado();
            logger.info("Datos obtenidos en doPost: " + habitacionesPorEstado);

            // Generar el PDF con los datos del informe de habitaciones por estado
            generarInformePDF(response, habitacionesPorEstado);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en el método doPost", e);
            throw new ServletException("Error al generar el PDF del informe de habitaciones por estado", e);
        }
    }

    private void generarInformePDF(HttpServletResponse response, List<InformeHabitacionEstado> habitacionesPorEstado) throws IOException, ServletException {
        logger.info("Iniciando método generarInformePDF");
        try {
            // Configurar la respuesta para la descarga de PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=InformeHabitacionesPorEstado.pdf");
            try (OutputStream out = response.getOutputStream()) {
                Document document = new Document();
                PdfWriter.getInstance(document, out);
                document.open();

                // Añadir título
                Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
                Paragraph titleParagraph = new Paragraph("Informe de Habitaciones por Estado", fontTitle);
                titleParagraph.setAlignment(Element.ALIGN_CENTER);
                titleParagraph.setSpacingAfter(20);
                document.add(titleParagraph);

                // Añadir fecha de generación
                Font fontFecha = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);
                Paragraph fechaParagraph = new Paragraph("Generado el: " + java.time.LocalDate.now(), fontFecha);
                fechaParagraph.setAlignment(Element.ALIGN_RIGHT);
                fechaParagraph.setSpacingAfter(20);
                document.add(fechaParagraph);

                // Crear tabla para el informe de habitaciones por estado
                PdfPTable table = new PdfPTable(2); // Dos columnas: Estado, Cantidad
                table.setWidthPercentage(100);
                table.setSpacingBefore(10f);
                table.setSpacingAfter(10f);

                // Añadir encabezados de la tabla
                Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
                PdfPCell header1 = new PdfPCell(new Phrase("Estado", fontHeader));
                header1.setBackgroundColor(BaseColor.DARK_GRAY);
                table.addCell(header1);
                PdfPCell header2 = new PdfPCell(new Phrase("Cantidad", fontHeader));
                header2.setBackgroundColor(BaseColor.DARK_GRAY);
                table.addCell(header2);

                // Método para convertir el estado a su descripción
                for (InformeHabitacionEstado informe : habitacionesPorEstado) {
                    table.addCell(convertirEstado(informe.getEstado()));
                    table.addCell(String.valueOf(informe.getCantidad()));
                    logger.info("Añadiendo datos al PDF: Estado=" + convertirEstado(informe.getEstado()) + ", Cantidad=" + informe.getCantidad());
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

    // Método para convertir el estado a su descripción
    private String convertirEstado(int estado) {
        switch (estado) {
            case 1:
                return "Disponible";
            case 2:
                return "Mantenimiento";
            case 3:
                return "Ocupado";
            default:
                return "Desconocido";
        }
    }
}
