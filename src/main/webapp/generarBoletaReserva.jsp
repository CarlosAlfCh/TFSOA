<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Generar Boleta de Reserva</title>
    <link rel="stylesheet" href="css/styles.css"> <!-- Enlace al archivo CSS -->
    
    <style>
        body {
            padding: 20px;
        }
        header {
            text-align: center;
            margin-bottom: 30px;
        }
        section {
            margin-bottom: 30px;
        }
        .table-wrapper {
            border: 2px solid #333; /* Borde alrededor de la tabla */
            padding: 20px; /* Espacio entre el borde y la tabla */
            background-color: #f9f9f9; /* Color de fondo del contenedor */
            border-radius: 8px; /* Bordes redondeados */
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
    
</head>
<jsp:include page="includes/sidebarhead.jsp"></jsp:include>
<body>
    <header>
        <h1>Generar Boleta de Reserva</h1>
    </header>

    <section>
        <form action="GenerarBoletaReservaServlet" method="get">
            <label for="idReserva">ID de la Reserva:</label>
            <input type="text" id="idReserva" name="idReserva" required>
            <button type="submit">Generar Boleta</button>
        </form>
    </section>

    <section>
        <h2>Reservas Disponibles</h2>
        <!-- Contenedor con el marco alrededor de la tabla -->
        <div class="table-wrapper">
            <table>
                <thead>
                    <tr>
                        <th>ID Reserva</th>
                        <th>Fecha de Reserva</th>
                        <th>Cliente</th>
                        <th>Monto</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="reserva" items="${reservas}">
                        <tr>
                            <td>${reserva.idReserva}</td>
                            <td>${reserva.fechaReserva}</td>
                            <td>${reserva.clienteNombre}</td>
                            <td>${reserva.monto}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </section>
</body>
<jsp:include page="includes/sidebarfoot.jsp"></jsp:include>
</html>
