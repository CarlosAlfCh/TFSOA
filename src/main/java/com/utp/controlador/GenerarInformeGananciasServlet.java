package com.utp.controlador;

import com.utp.modelo.InformeGananciasDAO;
import com.utp.entidad.InformeGanancias;
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

@WebServlet("/GenerarInformeGananciasServlet")
public class GenerarInformeGananciasServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(GenerarInformeGananciasServlet.class.getName());
    private final InformeGananciasDAO informeGananciasDAO = new InformeGananciasDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Iniciando método doGet");
        try {
            // Obtener datos de ganancias por día, mes y año
            List<InformeGanancias> gananciasPorDia = informeGananciasDAO.obtenerGananciasPorDia();
            List<InformeGanancias> gananciasPorMes = informeGananciasDAO.obtenerGananciasPorMes();
            List<InformeGanancias> gananciasPorAno = informeGananciasDAO.obtenerGananciasPorAno();

            // Imprimir datos obtenidos en logs
            logger.info("Datos de ganancias por Día: " + gananciasPorDia);
            logger.info("Datos de ganancias por Mes: " + gananciasPorMes);
            logger.info("Datos de ganancias por Año: " + gananciasPorAno);

            // Pasar los datos al JSP
            request.setAttribute("gananciasPorDia", gananciasPorDia);
            request.setAttribute("gananciasPorMes", gananciasPorMes);
            request.setAttribute("gananciasPorAno", gananciasPorAno);

            logger.info("Datos obtenidos y establecidos en atributos de solicitud");
            request.getRequestDispatcher("/informeGanancias.jsp").forward(request, response);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en el método doGet", e);
            throw new ServletException("Error al obtener los datos del informe de ganancias", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Iniciando método doPost");
        try {
            // Obtener datos de ganancias por día, mes y año
            List<InformeGanancias> gananciasPorDia = informeGananciasDAO.obtenerGananciasPorDia();
            List<InformeGanancias> gananciasPorMes = informeGananciasDAO.obtenerGananciasPorMes();
            List<InformeGanancias> gananciasPorAno = informeGananciasDAO.obtenerGananciasPorAno();

            // Imprimir datos obtenidos en logs
            logger.info("Datos de ganancias por Día: " + gananciasPorDia);
            logger.info("Datos de ganancias por Mes: " + gananciasPorMes);
            logger.info("Datos de ganancias por Año: " + gananciasPorAno);

            // Generar el PDF con los datos del informe de ganancias
            generarInformePDF(response, gananciasPorDia, gananciasPorMes, gananciasPorAno);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error en el método doPost", e);
            throw new ServletException("Error al generar el PDF del informe de ganancias", e);
        }
    }

    private void generarInformePDF(HttpServletResponse response, List<InformeGanancias> gananciasPorDia, List<InformeGanancias> gananciasPorMes, List<InformeGanancias> gananciasPorAno) throws IOException, ServletException {
        logger.info("Iniciando método generarInformePDF");
        try {
            // Configurar la respuesta para la descarga de PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=InformeGanancias.pdf");
            try (OutputStream out = response.getOutputStream()) {
                Document document = new Document();
                PdfWriter.getInstance(document, out);
                document.open();

                // Añadir título
                Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
                Paragraph titleParagraph = new Paragraph("Informe de Ganancias", fontTitle);
                titleParagraph.setAlignment(Element.ALIGN_CENTER);
                titleParagraph.setSpacingAfter(20);
                document.add(titleParagraph);

                // Añadir fecha de generación
                Font fontFecha = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);
                Paragraph fechaParagraph = new Paragraph("Generado el: " + java.time.LocalDate.now(), fontFecha);
                fechaParagraph.setAlignment(Element.ALIGN_RIGHT);
                fechaParagraph.setSpacingAfter(20);
                document.add(fechaParagraph);

                // Crear tabla para el informe de ganancias por día
                document.add(new Paragraph("Ganancias por Día", fontTitle));
                PdfPTable tableDia = new PdfPTable(2); // Dos columnas: Periodo y Ganancias
                tableDia.setWidthPercentage(100);
                tableDia.setSpacingBefore(10f);
                tableDia.setSpacingAfter(10f);

                // Añadir encabezados de la tabla
                Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
                PdfPCell header1 = new PdfPCell(new Phrase("Periodo", fontHeader));
                header1.setBackgroundColor(BaseColor.DARK_GRAY);
                tableDia.addCell(header1);
                PdfPCell header2 = new PdfPCell(new Phrase("Ganancias", fontHeader));
                header2.setBackgroundColor(BaseColor.DARK_GRAY);
                tableDia.addCell(header2);

                // Añadir datos a la tabla
                for (InformeGanancias informe : gananciasPorDia) {
                    tableDia.addCell(informe.getPeriodo());
                    tableDia.addCell(String.valueOf(informe.getGanancias()));
                }

                document.add(tableDia);

                // Crear tabla para el informe de ganancias por mes
                document.add(new Paragraph("Ganancias por Mes", fontTitle));
                PdfPTable tableMes = new PdfPTable(2);
                tableMes.setWidthPercentage(100);
                tableMes.setSpacingBefore(10f);
                tableMes.setSpacingAfter(10f);

                // Añadir encabezados de la tabla
                tableMes.addCell(header1);
                tableMes.addCell(header2);

                // Añadir datos a la tabla
                for (InformeGanancias informe : gananciasPorMes) {
                    tableMes.addCell(informe.getPeriodo());
                    tableMes.addCell(String.valueOf(informe.getGanancias()));
                }

                document.add(tableMes);

                // Crear tabla para el informe de ganancias por año
                document.add(new Paragraph("Ganancias por Año", fontTitle));
                PdfPTable tableAno = new PdfPTable(2);
                tableAno.setWidthPercentage(100);
                tableAno.setSpacingBefore(10f);
                tableAno.setSpacingAfter(10f);

                // Añadir encabezados de la tabla
                tableAno.addCell(header1);
                tableAno.addCell(header2);

                // Añadir datos a la tabla
                for (InformeGanancias informe : gananciasPorAno) {
                    tableAno.addCell(informe.getPeriodo());
                    tableAno.addCell(String.valueOf(informe.getGanancias()));
                }

                document.add(tableAno);
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
