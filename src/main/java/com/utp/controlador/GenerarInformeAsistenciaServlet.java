package com.utp.controlador;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.utp.modelo.AsistenciaDAO;
import com.utp.entidad.info.Asistencia;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@WebServlet("/GenerarInformeAsistenciaServlet")
public class GenerarInformeAsistenciaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final AsistenciaDAO asistenciaDAO = new AsistenciaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Llama al método que genera el PDF
        request.getRequestDispatcher("/generarInformeAsistencia.jsp").forward(request, response);

        
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Generar el PDF con los datos del informe de servicios
        generarInformePDF(response);
    }
    private void generarInformePDF(HttpServletResponse response) throws IOException, ServletException {
        try {
            // Obtener los datos de asistencia
            List<Asistencia> asistencias = asistenciaDAO.obtenerAsistencias();

            // Configurar la respuesta para la descarga de PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=Informe_Asistencia.pdf");
            try (OutputStream out = response.getOutputStream()) {
                Document document = new Document();
                PdfWriter.getInstance(document, out);
                document.open();

                // Añadir título
                Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
                Paragraph titleParagraph = new Paragraph("Informe de Asistencia", fontTitle);
                titleParagraph.setAlignment(Element.ALIGN_CENTER);
                titleParagraph.setSpacingAfter(20);
                document.add(titleParagraph);

                // Crear tabla para el informe
                PdfPTable table = new PdfPTable(6); // Número de columnas según la entidad Asistencia
                table.setWidthPercentage(100);
                table.setSpacingBefore(10f);
                table.setSpacingAfter(10f);

                // Añadir encabezados de la tabla
                Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE);
                PdfPCell header1 = new PdfPCell(new Phrase("ID", fontHeader));
                header1.setBackgroundColor(BaseColor.DARK_GRAY);
                table.addCell(header1);
                PdfPCell header2 = new PdfPCell(new Phrase("Técnico", fontHeader));
                header2.setBackgroundColor(BaseColor.DARK_GRAY);
                table.addCell(header2);
                PdfPCell header3 = new PdfPCell(new Phrase("Fecha", fontHeader));
                header3.setBackgroundColor(BaseColor.DARK_GRAY);
                table.addCell(header3);
                PdfPCell header4 = new PdfPCell(new Phrase("Hora Ingreso", fontHeader));
                header4.setBackgroundColor(BaseColor.DARK_GRAY);
                table.addCell(header4);
                PdfPCell header5 = new PdfPCell(new Phrase("Hora Salida", fontHeader));
                header5.setBackgroundColor(BaseColor.DARK_GRAY);
                table.addCell(header5);
                PdfPCell header6 = new PdfPCell(new Phrase("N. Horas", fontHeader));
                header6.setBackgroundColor(BaseColor.DARK_GRAY);
                table.addCell(header6);

                // Añadir datos a la tabla
                for (Asistencia asistencia : asistencias) {
                    table.addCell(String.valueOf(asistencia.getId()));
                    table.addCell(String.valueOf(asistencia.getIdTecnico()));
                    table.addCell(asistencia.getFecha().toString());
                    table.addCell(asistencia.getHoraIngreso().toString());
                    table.addCell(asistencia.getHoraSalida() != null ? asistencia.getHoraSalida().toString() : "N/A");
                    table.addCell(String.valueOf(asistencia.getNhoras()));
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
