<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Horario del tecnico</title>
        <link href="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.css" rel="stylesheet">

    </head>
    <jsp:include page="includes/sidebarhead.jsp"></jsp:include>
        <body>
            <div class="container-fluid">
                <div class="card mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary"></h6>

                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-primary font-weight-bold text-primary text-uppercase mb-1">
                                    Horario del tecnico
                                </div>
                            </div>
                            <div class="col-auto">
                                <a class="btn btn-secondary btn-circle" href="ServletAsignar?accion=listar">
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

                    // Parsear eventos del backend
                    var eventos = ${eventosJson};

                    // Configurar el calendario
                    var calendar = new FullCalendar.Calendar(calendarEl, {
                        locale: 'es', // Configurar idioma español
                        initialView: 'dayGridMonth', // Vista inicial
                        headerToolbar: {
                            left: 'prev,next today', // Controles de navegación
                            center: 'title', // Título del mes
                            right: 'dayGridMonth,timeGridWeek,timeGridDay' // Vistas disponibles
                        },
                        events: eventos // Cargar eventos desde el backend
                    });

                    calendar.render();
                });
        </script>
    </body>
    <jsp:include page="includes/sidebarfoot.jsp"></jsp:include>
</html>

