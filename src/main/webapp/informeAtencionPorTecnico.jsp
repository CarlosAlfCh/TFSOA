<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Informe de Atención por Técnico</title>
    <link rel="stylesheet" href="css/styles.css"> <!-- Enlace al archivo CSS -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script> <!-- Librería Chart.js -->
    <style>
        /* Estilos básicos */
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f1f3f5;
            color: #343a40;
        }

        header {
            background-color: #495057;
            color: white;
            padding: 20px;
            text-align: center;
        }

        section {
            margin: 20px;
            padding: 20px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        button {
            background-color: #28a745;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #218838;
        }

        h1, h2 {
            color: #212529;
        }

        canvas {
            max-width: 100%;
            margin-top: 20px;
        }

        /* Contenedor con borde alrededor del gráfico */
        .chart-wrapper {
            border: 2px solid #333; /* Borde alrededor del gráfico */
            padding: 20px; /* Espacio entre el borde y el gráfico */
            background-color: #f9f9f9; /* Fondo claro para el contenedor */
            border-radius: 8px; /* Bordes redondeados */
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* Sombra para el contenedor */
        }

        /* Responsividad */
        @media (max-width: 768px) {
            header {
                font-size: 18px;
                padding: 15px;
            }

            button {
                width: 100%;
                font-size: 14px;
            }

            section {
                margin: 10px;
                padding: 15px;
            }
        }
    </style>
</head>
<jsp:include page="includes/sidebarhead.jsp"></jsp:include>
<body>
    <header>
        <h1>Informe de Atención por Técnico</h1>
    </header>

    <section>
        <form action="GenerarInformeAtencionPorTecnicoServlet" method="POST">
            <button type="submit">Generar Informe PDF</button>
        </form>
    </section>

    <section>
        <h2>Datos Estadísticos</h2>
        <!-- Contenedor con el marco alrededor del gráfico -->
        <div class="chart-wrapper">
            <canvas id="atencionChart" width="400" height="200"></canvas>
        </div>
        <script>
            const ctx = document.getElementById('atencionChart').getContext('2d');
            
            const atencionChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: ['Técnico 1', 'Técnico 2', 'Técnico 3', 'Técnico 4'], // Aquí van los nombres de los técnicos
                    datasets: [
                        {
                            label: 'Número de Atenciones',
                            data: [10, 15, 20, 8], // Aquí van los números de atenciones
                            backgroundColor: 'rgba(255, 99, 132, 0.2)',
                            borderColor: 'rgba(255, 99, 132, 1)',
                            borderWidth: 1
                        },
                        {
                            label: 'Monto',
                            data: [100, 200, 150, 80], // Aquí van los montos correspondientes
                            backgroundColor: 'rgba(54, 162, 235, 0.2)',
                            borderColor: 'rgba(54, 162, 235, 1)',
                            borderWidth: 1
                        }
                    ]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        </script>
    </section>
</body>
<jsp:include page="includes/sidebarfoot.jsp"></jsp:include>
</html>
