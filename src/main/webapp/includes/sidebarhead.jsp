<%@page import="com.utp.entidad.pagos.Reserva"%>
<%@page import="com.utp.modelo.ReservaDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.utp.entidad.pagos.Pago"%>
<%@page import="java.util.List"%>
<%@page import="com.utp.modelo.PagoDAO"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Atención</title>

        <!-- Custom fonts for this template-->
        <link href="src/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="src/css/sb-admin-2.min.css" rel="stylesheet">

        <!-- Custom styles for this page -->
        <link href="src/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

    </head>   

    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">

            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="VMInicio.jsp">
                    <div class="sidebar-brand-icon">
                        <i class="fas fa-laugh-wink"></i>
                    </div>
                    <div class="sidebar-brand-text mx-3">
                        <c:if test="${usuario.getRol() == 1}">
                            Administrador
                        </c:if>
                        <c:if test="${usuario.getRol() == 2}">
                            Encargado
                        </c:if>
                        <c:if test="${usuario.getRol() == 3}">
                            Tecnico
                        </c:if>
                    </div>
                </a>

                <!-- Divider -->
                <hr class="sidebar-divider my-0">

                <!-- Nav Item - Dashboard -->
                <li class="nav-item active">
                    <a class="nav-link" href="ServletInicio?menu=principal&id=${usuario.getCodigo()}">
                        <i class="fas fa-fw fa-tachometer-alt"></i>
                        <span>Inicio</span></a>
                </li>

                <!-- Divider -->
                <hr class="sidebar-divider">

                <c:if test="${usuario.getRol() != 3}"> 

                    <c:if test="${usuario.getRol() == 1}"> 

                        <!-- Heading -->
                        <div class="sidebar-heading">
                            Manteniemientos
                        </div>

                        <!-- Nav Item - Pages Collapse Menu -->
                        <!-- Enlaces para Mantenimientos Usuarios(CRUD) -->

                        <li class="nav-item">
                            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUsuarios"
                               aria-expanded="true" aria-controls="collapseUsuarios">
                                <i class="fas fa-fw fa-cog"></i>
                                <span>Usuarios</span>
                            </a>

                            <div id="collapseUsuarios" class="collapse" aria-labelledby="collapseUsuarios" data-parent="#accordionSidebar">
                                <div class="bg-white py-2 collapse-inner rounded">
                                    <h6 class="collapse-header">Elija opción:</h6>

                                    <a class="collapse-item" href="Controlador?menu=cliente&accion=listar">Clientes</a>
                                    <a class="collapse-item" href="Controlador?menu=usuario&accion=listar">Usuarios</a>
                                </div>
                            </div>

                        </li>

                        <!-- Nav Item - Pages Collapse Menu -->
                        <!-- Enlaces para Mantenimientos Servicios (CRUD) -->

                        <li class="nav-item">
                            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseServicios"
                               aria-expanded="true" aria-controls="collapseServicios">
                                <i class="fas fa-fw fa-cog"></i>
                                <span>Servicios</span>
                            </a>

                            <div id="collapseServicios" class="collapse" aria-labelledby="headingServicios" data-parent="#accordionSidebar">
                                <div class="bg-white py-2 collapse-inner rounded">
                                    <h6 class="collapse-header">Elija opción:</h6>

                                    <a class="collapse-item" href="Controlador?menu=room&accion=listar">Habitaciones</a>
                                    <a class="collapse-item" href="Controlador?menu=servicio&accion=listar">Servicios Spa</a>
                                </div>
                            </div>

                        </li>
                    </c:if>

                    <!-- Heading -->
                    <div class="sidebar-heading">
                        Programación
                    </div>

                    <!-- Nav Item - Pages Collapse Menu -->
                    <!-- Enlaces para Gestion (Atencion de servicios) -->
                    <li class="nav-item">
                        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePrograma"
                           aria-expanded="true" aria-controls="collapsePrograma">
                            <i class="fas fa-fw fa-calendar"></i>
                            <span>Asignaciónes</span>
                        </a>
                        <div id="collapsePrograma" class="collapse" aria-labelledby="headingPrograma"
                             data-parent="#accordionSidebar">
                            <div class="bg-white py-2 collapse-inner rounded">
                                <h6 class="collapse-header">Elija opción:</h6>

                                <a class="collapse-item" href="ServletAsignar?accion=listar">Asignar</a>
                                <a class="collapse-item" href="ServletHorarios?accion=listar">Horarios</a>
                            </div>
                        </div>
                    </li>

                    <!-- Divider -->
                    <hr class="sidebar-divider">

                    <!-- Heading -->
                    <div class="sidebar-heading">
                        Funciones
                    </div>

                    <!-- Nav Item - Pages Collapse Menu -->
                    <!-- Enlaces para Gestion (Pagos) -->
                    <li class="nav-item">
                        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePagos"
                           aria-expanded="true" aria-controls="collapsePagos">
                            <i class="fas fa-fw fa-calendar"></i>
                            <span>Pagos</span>
                        </a>
                        <div id="collapsePagos" class="collapse" aria-labelledby="collapsePagos"
                             data-parent="#accordionSidebar">
                            <div class="bg-white py-2 collapse-inner rounded">
                                <h6 class="collapse-header">Elija opción:</h6>

                                <a class="collapse-item" href="ServletCitas?menu=listar">Validacion</a>
                                <!--                                <a class="collapse-item" href="ServletCitas?menu=listar">Historial</a>-->
                                <a class="collapse-item" href="ServletTarifas?accion=listar">Promociones</a>
                            </div>
                        </div>
                    </li>   

                    <c:if test="${usuario.getRol() == 1}">

                        <!-- Nav Item - Pages Collapse Menu -->
                        <!-- Enlaces para Gestion (informes) -->
                        <li class="nav-item">
                            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages"
                               aria-expanded="true" aria-controls="collapsePages">
                                <i class="fas fa-fw fa-folder"></i>
                                <span>Informes</span>
                            </a>
                            <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                                <div class="bg-white py-2 collapse-inner rounded">
                                    <h6 class="collapse-header">Atencion de:</h6>
                                    <a class="collapse-item" href="GenerarInformeServlet">Spa</a>
                                    <a class="collapse-item" href="GenerarInformeHabitacionesServlet">Hotel</a>
                                    <a class="collapse-item" href="GenerarInformeGananciasServlet">Ganancias Totales</a>
                                    <a class="collapse-item" href="GenerarInformeAtencionPorTecnicoServlet">Atencion por tecnico</a>
                                    <a class="collapse-item" href="GenerarInformeHabitacionesEstadoServlet">Hotel por estado</a> 
                                    <a class="collapse-item" href="GenerarBoletaReservaServlet">Boletas</a> 
                                    <a class="collapse-item" href="GenerarInformeAsistenciaServlet">Asistencias</a> 
                                    <a class="collapse-item" href="GenerarAnalisisSatisfaccionServlet">Satisfaccion</a>
                                    
                                </div>
                            </div>
                        </li>

                        <hr class="sidebar-divider">

                        <div class="sidebar-heading">
                            Otras Funciones
                        </div>
                        <!-- Enlaces para Gestion (Extras) -->
                        <li class="nav-item">
                            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseExtra"
                               aria-expanded="true" aria-controls="collapsePages">
                                <i class="fas fa-fw fa-folder"></i>
                                <span>Otros</span>
                            </a>
                            <div id="collapseExtra" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                                <div class="bg-white py-2 collapse-inner rounded">
                                    <h6 class="collapse-header">Gestiones:</h6>
                                    <a class="collapse-item" href="ServletExtras?menu=especialidad&accion=listar">Especialidad</a>
                                    <h6 class="collapse-header">Control:</h6>
                                    <a class="collapse-item" href="ServletExtras?menu=comentarios&accion=listar">Comentarios</a>
                                </div>
                            </div>
                        </li>

                    </c:if>

                </c:if>                    

                <c:if test="${usuario.getRol() == 3}">

                    <!-- Heading -->
                    <div class="sidebar-heading">
                        Asignaciones
                    </div>

                    <!-- Nav Item - Charts -->
                    <li class="nav-item">
                        <a class="nav-link" href="ServletTecnico?accion=listar&id=${usuario.getCodigo()}">
                            <i class="fas fa-fw fa-calendar-week"></i>
                            <span>Asignaciones</span>
                        </a>
                    </li>

                    <!-- Nav Item - Charts -->
                    <li class="nav-item">
                        <a class="nav-link" href="ServletTecnico?accion=historial&id=${usuario.getCodigo()}">
                            <i class="fas fa-fw fa-calendar-week"></i>
                            <span>Historial</span>
                        </a>
                    </li>

                </c:if>


                <!-- Divider -->
                <hr class="sidebar-divider d-none d-md-block">

                <!-- Sidebar Toggler (Sidebar) -->
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>

            </ul>
            <!-- End of Sidebar -->

            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">

                <!-- Main Content -->
                <div id="content">

                    <!-- Topbar -->
                    <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                        <!-- Sidebar Toggle (Topbar) -->
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>

                        <!-- Topbar Navbar -->
                        <ul class="navbar-nav ml-auto">


                            <!-- Nav Item - Alerts -->
                            <c:if test="${usuario.getRol() != 3}">

                                <%
                                    PagoDAO dao = new PagoDAO();
                                    List<Pago> lista = dao.listarpagos();
                                %>   

                                <li class="nav-item dropdown no-arrow mx-1">
                                    <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="fas fa-bell fa-fw"></i>
                                        <% if (lista.size() != 0) {%>
                                        <!-- Counter - Alerts -->
                                        <span class="badge badge-danger badge-counter">

                                            <%
                                                int r = 0;
                                                for (Pago p : lista) {
                                                    if (p.getValido() == 0) {
                                                        r++;
                                                    }
                                                }
                                            %>
                                            
                                            <%= r %>

                                        </span>
                                        <% }%>
                                    </a>

                                    <!-- Dropdown - Alerts -->

                                    <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                         aria-labelledby="alertsDropdown">
                                        <h6 class="dropdown-header">
                                            Pagos entrantes
                                        </h6>
                                        <%
                                            if (lista != null) {
                                                for (Pago p : lista) {
                                                    if (p.getValido() == 0) {

                                        %>
                                        <a class="dropdown-item d-flex align-items-center" 
                                           href="ServletCitas?menu=listar" >

                                            <div class="mr-3">
                                                <div class="icon-circle bg-success">
                                                    <i class="fas fa-donate text-white"></i>
                                                </div>
                                            </div>

                                            <div>
                                                <div class="small text-gray-500"><%= p.getFechapago()%></div>
                                                Pago por: <%= p.getMetodo()%><br>
                                                N° de operacion: <%= p.getCodigo()%><br>
                                                Codigo: 00000<%= p.getIdpago()%>
                                            </div>

                                        </a>
                                        <%          }
                                                }
                                            }
                                        %>

                                        <a class="dropdown-item text-center small text-gray-500" href="#">Mostrar todos los pagos</a>

                                    </div>
                                </li>
                            </c:if>

                            <c:if test="${usuario.getRol() == 3}">
                                <!-- Nav Item - Messages -->
                                <li class="nav-item dropdown no-arrow mx-1">
                                    <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
                                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="fas fa-envelope fa-fw"></i>

                                        <!-- Counter - Messages -->
                                        <span class="badge badge-danger badge-counter">
                                            <c:set var="count" value="0" />
                                            <c:forEach var="a" items="${asig}">
                                                <c:if test="${a.estado == 1}">
                                                    <c:set var="count" value="${count + 1}" />
                                                </c:if>
                                            </c:forEach>

                                            ${count}
                                        </span>

                                    </a>

                                    <!-- Dropdown - Messages -->
                                    <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                         aria-labelledby="messagesDropdown">
                                        <h6 class="dropdown-header">
                                            Asignaciones
                                        </h6>

                                        <c:forEach var="a" items="${asig}" varStatus="status">
                                            <c:if test="${a.estado == 1}">

                                                <a class="dropdown-item d-flex align-items-center" href="#">
                                                    <div class="dropdown-list-image mr-3">
                                                        <img class="rounded-circle" src="src/img/undraw_profile_1.svg"
                                                             alt="...">
                                                    </div>
                                                    <div class="font-weight-bold">
                                                        <div class="text-truncate"> Nueva asignacion !! </div>
                                                        <div class="small text-gray-500">Codigo: 000${a.idpago} · Fecha: ${a.fecha}</div>
                                                    </div>
                                                </a>

                                            </c:if>
                                        </c:forEach>

                                        <a class="dropdown-item text-center small text-gray-500" href="ServletCitas?menu=asigna&accion=ver&idtecnic=${usuario.getCodigo()}">Ir a asignaciones</a>
                                    </div>
                                </li>
                            </c:if>

                            <c:if test="${usuario.getRol() != 3}">
                                <!-- Nav Item - Messages -->
                                <li class="nav-item dropdown no-arrow mx-1">

                                    <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
                                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="fas fa-envelope fa-fw"></i>

                                        <!-- Counter - Messages -->
                                        <span class="badge badge-danger badge-counter">
                                            
                                            <c:set var="iter" value="0" />
                                            <c:forEach var="a" items="${msj}">
                                                <c:if test="${a.estado == 0}">
                                                    <c:set var="iter" value="${iter + 1}" />
                                                </c:if>
                                            </c:forEach>

                                            ${iter}
                                            
                                        </span>

                                    </a>

                                    <!-- Dropdown - Messages -->
                                    <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                         aria-labelledby="messagesDropdown">
                                        <h6 class="dropdown-header">
                                            Mensaje
                                        </h6>
                                        <c:forEach var="a" items="${msj}" varStatus="status">
                                            <c:if test="${a.estado == 1}">

                                                <a class="dropdown-item d-flex align-items-center" href="#">
                                                    <div class="dropdown-list-image mr-3">
                                                        <img class="rounded-circle" src="src/img/undraw_profile_3.svg"
                                                             alt="...">
                                                    </div>
                                                    <div class="font-weight-bold">
                                                        <div class="text-truncate">Servicio realizado · Codigo: 000000${a.idpago}</div>
                                                        <div class="small text-gray-500">Id Tecnico ${a.idtecnico}</div>
                                                    </div>
                                                </a>

                                            </c:if>
                                        </c:forEach>

                                        <a class="dropdown-item text-center small text-gray-500" href="ServletAsignar?accion=listar">Asignaciones</a>

                                    </div>
                                </li>
                            </c:if>

                            <div class="topbar-divider d-none d-sm-block"></div>

                            <!-- Nav Item - User Information -->
                            <li class="nav-item dropdown no-arrow">

                                <a class="nav-link dropdown-toggle" id="userDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="mr-2 d-none d-lg-inline text-gray-600 small">${usuario.getNombres()}</span>
                                    <img class="img-profile rounded-circle"
                                         src="src/img/undraw_profile.svg">
                                </a>

                                <!-- Dropdown - User Information -->
                                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                     aria-labelledby="userDropdown">

                                    <a class="dropdown-item" >
                                        <i class="fas fa-envelope fa-sm fa-fw mr-2 text-gray-400"></i>
                                        ${usuario.getCorreo()}
                                    </a>

                                    <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                        Salir
                                    </a>

                                </div>

                            </li>
                        </ul>

                    </nav>
                    <!-- End of Topbar -->

                    <!-- Begin Page Content -->
