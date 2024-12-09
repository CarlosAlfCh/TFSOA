package com.utp.controlador;

import com.itextpdf.text.*;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;
import com.utp.modelo.InformeHabitacionDAO;
import com.utp.entidad.InformeHabitacion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet("/GenerarInformeHabitacionesServlet")
public class GenerarInformeHabitacionesServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final InformeHabitacionDAO informeHabitacionDAO = new InformeHabitacionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/InformesHabitaciones.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Generar el PDF con los datos del informe de ocupación de habitaciones
        generarInformePDF(response);
    }

    private void generarInformePDF(HttpServletResponse response) throws IOException, ServletException {
        try {
            // Obtener todos los datos del informe de ocupación de habitaciones
            List<InformeHabitacion> informes = informeHabitacionDAO.obtenerOcupacionHabitaciones();

            // Configurar la respuesta para la descarga de PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=InformeOcupacionHabitaciones.pdf");
            try (OutputStream out = response.getOutputStream()) {
                Document document = new Document();
                PdfWriter.getInstance(document, out);
                document.open();

                // Añadir título
                Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
                Paragraph titleParagraph = new Paragraph("Informe de Ocupación de Habitaciones", fontTitle);
                titleParagraph.setAlignment(Element.ALIGN_CENTER);
                titleParagraph.setSpacingAfter(20);
                document.add(titleParagraph);

                // Crear tabla para el informe
                PdfPTable table = new PdfPTable(4); // Número de columnas según tus necesidades
                table.setWidthPercentage(100);
                table.setSpacingBefore(10f);
                table.setSpacingAfter(10f);

                // Añadir encabezados de la tabla
                Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
                PdfPCell header1 = new PdfPCell(new Phrase("Tipo de Habitación", fontHeader));
                header1.setBackgroundColor(BaseColor.DARK_GRAY);
                table.addCell(header1);
                PdfPCell header2 = new PdfPCell(new Phrase("Cantidad", fontHeader));
                header2.setBackgroundColor(BaseColor.DARK_GRAY);
                table.addCell(header2);
                PdfPCell header3 = new PdfPCell(new Phrase("Monto Total", fontHeader));
                header3.setBackgroundColor(BaseColor.DARK_GRAY);
                table.addCell(header3);
                PdfPCell header4 = new PdfPCell(new Phrase("Número de Huéspedes", fontHeader));
                header4.setBackgroundColor(BaseColor.DARK_GRAY);
                table.addCell(header4);

                // Añadir datos a la tabla
                for (InformeHabitacion inf : informes) {
                    table.addCell(inf.getTipoHabitacion());
                    table.addCell(String.valueOf(inf.getCantidad()));
                    table.addCell(String.valueOf(inf.getMontoTotal()));
                    table.addCell(String.valueOf(inf.getNumeroHuespedes()));
                }

                document.add(table);
                document.close();
            } catch (DocumentException e) {
                throw new ServletException("Error al generar el PDF.", e);
            }
        } catch (Exception e) {
            // Log de errores y mensaje para el usuario
            e.printStackTrace();
            throw new ServletException("Ocurrió un error al generar el informe.", e);
        }
    }
}
