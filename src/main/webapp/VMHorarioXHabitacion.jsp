<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Horario de la habitacion</title>
        <link href="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="includes/sidebarhead.jsp"></jsp:include>


            <div class="container-fluid">

                <div class="card mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary"></h6>

                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-primary font-weight-bold text-primary text-uppercase mb-1">
                                    Horario de la habitacion
                                </div>
                            </div>
                            <div class="col-auto">
                                <a class="btn btn-secondary btn-circle" href="ServletHorarios?accion=listar">
                                    <i class="fas fa-arrow-left"></i>
                                </a>
                            </div>
                        </div>


                    </div>
                    <div class="card-body">
                        <div id="calendar"></div>
                    </div>
                </div>

            </div>



            <!-- FullCalendar JS -->
            <script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js"></script>

            <script>
                document.addEventListener('DOMContentLoaded', function () {
                    var calendarEl = document.getElementById('calendar');

                    // Parsear los eventos enviados desde el servlet
                    var eventos = ${eventosJson};

                    // Aplicar colores según el estado
                    eventos.forEach(function (evento) {
                        var estado = evento.extendedProps.estado; // Estado del evento
                        if (estado === 1) { // Ocupado
                            evento.color = '#ff4d4d'; // Rojo
                        } else if (estado === 2) { // Libre
                            evento.color = '#4dff4d'; // Verde
                        } else if (estado === 3) { // Mantenimiento
                            evento.color = '#4d4dff'; // Azul
                        }
                    });

                    // Configuración del calendario
                    var calendar = new FullCalendar.Calendar(calendarEl, {
                        locale: 'es',
                        initialView: 'dayGridMonth',
                        headerToolbar: {
                            left: 'prev,next today',
                            center: 'title',
                            right: 'dayGridMonth,timeGridWeek,timeGridDay'
                        },
                        events: eventos,
                        eventClick: function (info) {
                            var monto = info.event.extendedProps.monto;
                            var estado = info.event.extendedProps.estado;
                            var estadoTexto = estado === 1 ? 'Ocupado' : estado === 2 ? 'Libre' : 'Mantenimiento';
                            alert("Monto: " + monto + "\nEstado: " + estadoTexto);
                        }
                    });

                    calendar.render();
                });
        </script>

        <jsp:include page="includes/sidebarfoot.jsp"></jsp:include>
    </body>
</html>
