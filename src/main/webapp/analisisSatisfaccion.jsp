<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Análisis de Satisfacción del Cliente</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        body {
            padding: 20px;
        }
        .table-wrapper {
            margin: 20px 0;
        }
        /* Estilo para el marco alrededor del gráfico */
        .chart-container {
            border: 2px solid #333; /* Borde alrededor del gráfico */
            padding: 20px; /* Espacio entre el borde y el gráfico */
            margin-bottom: 30px; /* Espacio abajo del gráfico */
            background-color: #f9f9f9; /* Color de fondo del contenedor */
            border-radius: 8px; /* Bordes redondeados */
        }
    </style>
</head>
<jsp:include page="includes/sidebarhead.jsp"></jsp:include>
<body>
    <header class="mb-4">
        <h1 class="text-center">Análisis de Satisfacción del Cliente</h1>
    </header>

    <!-- Añadir un contenedor para el gráfico con un marco -->
    <div class="container">
        <div class="chart-container">
            <canvas id="satisfaccionChart" width="400" height="200"></canvas>
        </div>
    </div>

    <section>
        <div class="table-wrapper">
            <h2 class="text-center">Comentarios de 5 Estrellas</h2>
            <table class="table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Cliente</th>
                        <th>Comentario</th>
                        <th>N. Estrellas</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="comentario" items="${comentarios}">
                        <c:if test="${comentario.nestrellas == 5}">
                            <tr>
                                <td>${comentario.id}</td>
                                <td>${comentario.cliente}</td>
                                <td>${comentario.comentario}</td>
                                <td>${comentario.nestrellas}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="table-wrapper">
            <h2 class="text-center">Comentarios de 4 Estrellas</h2>
            <table class="table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Cliente</th>
                        <th>Comentario</th>
                        <th>N. Estrellas</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="comentario" items="${comentarios}">
                        <c:if test="${comentario.nestrellas == 4}">
                            <tr>
                                <td>${comentario.id}</td>
                                <td>${comentario.cliente}</td>
                                <td>${comentario.comentario}</td>
                                <td>${comentario.nestrellas}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="table-wrapper">
            <h2 class="text-center">Comentarios de 3 Estrellas</h2>
            <table class="table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Cliente</th>
                        <th>Comentario</th>
                        <th>N. Estrellas</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="comentario" items="${comentarios}">
                        <c:if test="${comentario.nestrellas == 3}">
                            <tr>
                                <td>${comentario.id}</td>
                                <td>${comentario.cliente}</td>
                                <td>${comentario.comentario}</td>
                                <td>${comentario.nestrellas}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="table-wrapper">
            <h2 class="text-center">Comentarios de 2 Estrellas</h2>
            <table class="table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Cliente</th>
                        <th>Comentario</th>
                        <th>N. Estrellas</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="comentario" items="${comentarios}">
                        <c:if test="${comentario.nestrellas == 2}">
                            <tr>
                                <td>${comentario.id}</td>
                                <td>${comentario.cliente}</td>
                                <td>${comentario.comentario}</td>
                                <td>${comentario.nestrellas}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="table-wrapper">
            <h2 class="text-center">Comentarios de 1 Estrella</h2>
            <table class="table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Cliente</th>
                        <th>Comentario</th>
                        <th>N. Estrellas</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="comentario" items="${comentarios}">
                        <c:if test="${comentario.nestrellas == 1}">
                            <tr>
                                <td>${comentario.id}</td>
                                <td>${comentario.cliente}</td>
                                <td>${comentario.comentario}</td>
                                <td>${comentario.nestrellas}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </section>
<jsp:include page="includes/sidebarfoot.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<!-- Añadir script para el gráfico -->
<script>
$(document).ready(function() {
    // Contar los comentarios por estrella
    const counts = { 1: 0, 2: 0, 3: 0, 4: 0, 5: 0 };
    <c:forEach var="comentario" items="${comentarios}">
        counts[${comentario.nestrellas}]++;
    </c:forEach>

    const ctx = document.getElementById('satisfaccionChart').getContext('2d');
    const satisfaccionChart = new Chart(ctx, {
        type: 'bar',  // Gráfico de barras
        data: {
            labels: ['1 Estrella', '2 Estrellas', '3 Estrellas', '4 Estrellas', '5 Estrellas'],
            datasets: [{
                label: 'Comentarios por Calificación',
                data: [counts[1], counts[2], counts[3], counts[4], counts[5]],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top',
                },
                tooltip: {
                    enabled: true
                }
            }
        }
    });
});
</script>
</body>
</html>
