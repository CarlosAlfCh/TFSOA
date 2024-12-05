<%-- 
    Document   : VMInicio
    Created on : 7 oct. 2024, 10:34:59
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.utp.entidad.Usuario"%>
<%@page import="com.utp.entidad.Servicio"%>
<%@page import="com.utp.modelo.UsuarioDAO"%>
<%@page import="com.utp.modelo.ServicioDAO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>       
        <link href="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.css" rel="stylesheet">
        <!-- Tu CSS adicional -->

    </head>

    <jsp:include page="includes/sidebarhead.jsp"></jsp:include>

        <body>

            <!-- Begin Page Content -->

            <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h3 class="m-0 font-weight-bold text-primary">Bienvenido ${usuario.getNombres()}</h3>


                <c:if test="${empty marca}">
                    <!-- Mostrar botón para "Marcar asistencia" si marca es null o 0 -->
                    <a href="ServletInicio?menu=principal&id=${usuario.getCodigo()}&accion=verificar" class="btn btn-success btn-icon-split">
                        <span class="icon text-white-50">
                            <i class="fas fa-clock"></i>
                        </span>
                        <span class="text">Marcar asistencia</span>
                    </a>
                </c:if>

                <c:if test="${marca == 1}">
                    <!-- Mostrar botón para "Marcar salida" si marca es 1 -->
                    <a href="ServletInicio?menu=principal&id=${usuario.getCodigo()}&accion=actualizar" class="btn btn-warning btn-icon-split">
                        <span class="icon text-white-50">
                            <i class="fas fa-sign-out-alt"></i>
                        </span>
                        <span class="text">Marcar salida</span>
                    </a>
                </c:if>
                    
            </div>

            <c:if test="${usuario.getRol() != 3}"> 

                <!-- Content Dashboard -->

                <div class="row">

                    <!-- Earnings (Monthly) Card Example -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-primary shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                            Ganancias (Mensual)</div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">S/ 4,000</div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-calendar fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Earnings (Monthly) Card Example -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-success shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                            Ganacias (Anual)</div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">S/ 215,000</div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Earnings (Monthly) Card Example -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-info shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-info text-uppercase mb-1">% Habitaciones ocupadas
                                        </div>
                                        <div class="row no-gutters align-items-center">
                                            <div class="col-auto">
                                                <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">50%</div>
                                            </div>
                                            <div class="col">
                                                <div class="progress progress-sm mr-2">
                                                    <div class="progress-bar bg-info" role="progressbar"
                                                         style="width: 50%" aria-valuenow="50" aria-valuemin="0"
                                                         aria-valuemax="100"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Pending Requests Card Example -->
                    <div class="col-xl-3 col-md-6 mb-4">
                        <div class="card border-left-warning shadow h-100 py-2">
                            <div class="card-body">
                                <div class="row no-gutters align-items-center">
                                    <div class="col mr-2">
                                        <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                            N° Personal asignado</div>
                                        <div class="h5 mb-0 font-weight-bold text-gray-800">18</div>
                                    </div>
                                    <div class="col-auto">
                                        <i class="fas fa-comments fa-2x text-gray-300"></i>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <c:if test="${usuario.getRol() == 1}"> 

                    <div class="row">

                        <!-- Area Grafico puntos -->
                        <div class="col-xl-8 ">
                            <div class="card shadow mb-4">

                                <!-- Card Header -->
                                <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">

                                    <h6 class="m-0 font-weight-bold text-primary">Ganancias</h6>   

                                </div>

                                <!-- Grafico puntos -->

                                <div class="card-body">
                                    <div class="chart-area">
                                        <canvas id="myAreaChart"></canvas>
                                    </div>
                                </div>

                            </div>
                        </div>

                        <!-- Area grafico circular-->
                        <div class="col-xl-4 ">
                            <div class="card shadow mb-4">
                                <!-- Card Header - Dropdown -->
                                <div
                                    class="card-header py-3 d-flex flex-row align-items-center justify-content-between">

                                    <h6 class="m-0 font-weight-bold text-primary">Metodos de pago</h6>

                                </div>
                                <!-- Card Body -->
                                <div class="card-body">

                                    <!-- Grafico circular -->
                                    <div class="chart-pie pt-4 pb-2">
                                        <canvas id="myPieChart"></canvas>
                                    </div>

                                    <div class="mt-4 text-center small">
                                        <span class="mr-2">
                                            <i class="fas fa-circle text-primary"></i> Efectivo
                                        </span>
                                        <span class="mr-2">
                                            <i class="fas fa-circle text-success"></i> Yape
                                        </span>
                                        <span class="mr-2">
                                            <i class="fas fa-circle text-info"></i> Plin
                                        </span>
                                    </div>
                                </div>

                            </div>
                        </div>

                    </div>            

                </c:if>

            </c:if>

            <!-- /Fin Dashboard -->

            <div class="my-2"></div>

            <!-- inicio calendario -->

            <div class="card border-left-info shadow h-100 py-2">

                <div class="nav-link" >

                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-success font-weight-bold text-info text-uppercase mb-1">
                                Mi horario</div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-calendar fa-2x text-gray-300"></i>
                        </div>
                    </div>

                </div>

                <div class="card-body">
                    <div id="calendar"></div>
                </div>

            </div>

            <!-- /fin calendario --> 

            <div class="my-2"></div>

            <!-- inicio tabla -->

            <div class="card border-left-primary shadow h-100 py-2 card shadow mb-4">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collap"
                   aria-expanded="true" aria-controls="collap">
                    <h6 class="m-0 font-weight-bold text-primary">Consultar Usuarios/Clientes</h6>                            
                </a>
                <div id="collap" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">



                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>Codigo</th>
                                            <th>Nombres</th>
                                            <th>Apellidos</th>
                                            <th>Dni</th>
                                            <th>Correo</th>
                                            <th>Rol</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%  UsuarioDAO dao = new UsuarioDAO();
                                            List<Usuario> lista = dao.listar();
                                            if (lista != null) {
                                                for (Usuario user : lista) {

                                        %>
                                        <tr>
                                            <td><%= user.getCodigo()%></td>
                                            <td><%= user.getNombres()%></td>
                                            <td><%= user.getApelpat()%> <%= user.getApelmat()%></td>
                                            <td><%= user.getDni()%></td>
                                            <td><%= user.getCorreo()%></td>
                                            <td><%if (user.getRol() == 1) {%>                        
                                                Administrador
                                                <%} else {
                                                    if (user.getRol() == 2) {%>
                                                Encargado
                                                <%  } else {
                                                    if (user.getRol() == 3) {%>
                                                Tecnico
                                                <%      } else {%>
                                                Cliente
                                                <%
                                                            }
                                                        }
                                                    }
                                                %>
                                            </td>
                                        </tr>
                                        <%
                                                }
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>


                    </div>
                </div>
            </div> 

            <!-- /fin tabla --> 


        </div>

        <!-- /.container-fluid -->
        <script src="https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/index.global.min.js"></script>

        <script>
            document.addEventListener('DOMContentLoaded', function () {
                var calendarEl = document.getElementById('calendar');

                // Parsear eventos del backend
                var eventos = ${eventosJson};

                // Asignar colores y estados según el estado del evento
                eventos = eventos.map(evento => {
                    if (evento.extendedProps.estado === 1) { // Sin realizar
                        evento.color = "orange"; // Color para eventos sin realizar
                    } else if (evento.extendedProps.estado === 0) { // Realizado
                        evento.color = "green"; // Color para eventos realizados
                    }
                    return evento;
                });

                // Configurar el calendario
                var calendar = new FullCalendar.Calendar(calendarEl, {
                    locale: 'es', // Configurar idioma español
                    initialView: 'dayGridMonth', // Vista inicial
                    headerToolbar: {
                        left: 'prev,next today', // Controles de navegación
                        center: 'title', // Título del mes
                        right: 'dayGridMonth,timeGridWeek,timeGridDay' // Vistas disponibles
                    },
                    events: eventos, // Cargar eventos desde el backend
                    eventClick: function (info) {
                        // Obtener propiedades extendidas
                        var monto = info.event.extendedProps.monto;
                        var estado = info.event.extendedProps.estado;

                        // Convertir estado a texto
                        var estadoTexto = estado === 1 ? "Sin realizar" : "Realizado";

                        // Mostrar información en un alerta
                        alert("Monto: " + monto + "\nEstado: " + estadoTexto);
                    }
                });

                calendar.render();
            });
        </script>



    </body>

    <jsp:include page="includes/sidebarfoot.jsp"></jsp:include>

</html>
