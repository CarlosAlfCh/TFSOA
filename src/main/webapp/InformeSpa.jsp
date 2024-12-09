<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Informe de Servicios del SPA</title>
    <link rel="stylesheet" href="css/styles.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        /* Estilos básicos */
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
            color: #343a40;
        }

        header {
            background-color: #6c757d;
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
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #0056b3;
        }

        h1, h2 {
            color: #495057;
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
        <h1>Informe de Servicios del SPA</h1>
    </header>

    <section aria-labelledby="generar-informe">
        <h2 id="generar-informe">Generar Informe</h2>
        <form action="GenerarInformeServlet" method="POST">
            <button type="submit" aria-label="Generar informe en PDF">Generar Informe PDF</button>
        </form>
    </section>

    <section aria-labelledby="datos-estadisticos">
        <h2 id="datos-estadisticos">Datos Estadísticos</h2>
        <!-- Contenedor con el marco alrededor del gráfico -->
        <div class="chart-wrapper">
            <canvas id="myChart" width="400" height="200"></canvas>
        </div>
        <script>
            const ctx = document.getElementById('myChart').getContext('2d');
            const myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: ['Servicio 1', 'Servicio 2', 'Servicio 3', 'Servicio 4'], 
                    datasets: [{
                        label: 'Cantidad de Reservas',
                        data: [12, 19, 3, 5], 
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)',
                            'rgba(75, 192, 192, 0.2)'
                        ],
                        borderColor: [
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)'
                        ],
                        borderWidth: 1
                    }]
                },
                options: {
                    responsive: true,
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    },
                    plugins: {
                        legend: {
                            labels: {
                                color: '#495057'
                            }
                        }
                    }
                }
            });
        </script>
    </section>
</body>
<jsp:include page="includes/sidebarfoot.jsp"></jsp:include>
</html>
