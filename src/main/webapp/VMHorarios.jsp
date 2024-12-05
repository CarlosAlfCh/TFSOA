<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Horarios</title>
    </head>
    <body>
        <jsp:include page="includes/sidebarhead.jsp"></jsp:include>


            <div class="container-fluid">
                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800">Horarios de actividades</h1>

                <p class="mb-4">
                    A continuación se presenta lista de tecnicos y habitaciones
                </p>


                <div class="row">

                    <div class="col-lg-6">

                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Tecnicos</h6>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Rol</th>
                                                <th>Nombres y Apellidos</th>
                                                <th>Opciones</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="user" items="${tecnicos}" varStatus="status">
                                            <c:if test="${user.estado == 1}"> 
                                                <c:if test="${user.rol < 4}">
                                                    <tr>
                                                        <td>
                                                            <span class="badge bg-primary reservation-badge text-white">
                                                                <i class="fas fa-id-badge me-1"></i>
                                                                ${user.codigo}
                                                            </span>
                                                        </td>
                                                        <td>
                                                            <c:if test="${user.rol == 1}">                                                
                                                                <span class="badge bg-info reservation-badge text-white">
                                                                    <i class="fas fa-user me-1"></i>
                                                                    Administrador
                                                                </span>
                                                            </c:if> 
                                                            <c:if test="${user.rol == 2}">                                                
                                                                <span class="badge bg-secondary reservation-badge text-white">
                                                                    <i class="fas fa-user me-1"></i>
                                                                    Encargado
                                                                </span>
                                                            </c:if> 
                                                            <c:if test="${user.rol == 3}">                                                
                                                                <span class="badge bg-warning reservation-badge text-white">
                                                                    <i class="fas fa-user me-1"></i>
                                                                    Tecnico
                                                                </span>
                                                            </c:if>
                                                        </td>
                                                        <td>${user.nombres} ${user.apelpat} ${user.apelmat}</td>
                                                        <td>
                                                            <a href="ServletHorarios?accion=tecnico&id=${user.codigo}" class="btn btn-info btn-circle">
                                                                <i class="fas fa-calendar"></i>
                                                            </a>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </c:if>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="col-lg-6">

                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Habitaciones</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable2" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Tipo</th>
                                            <th>Descripción</th>
                                            <th>Opciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="r" items="${rooms}" varStatus="status">
                                            <tr>
                                                <td>
                                                    <span class="badge bg-primary reservation-badge text-white">
                                                        <i class="fas fa-id-badge me-1"></i>
                                                        ${r.idhabitacion}D${r.disHotel}P${r.piso}
                                                    </span>
                                                </td>
                                                <td>${r.tipo}</td>
                                                <td>Piso: ${r.piso} </br> Dirección: ${r.dirHotel} </br> ${r.descripcion}</td>
                                                <td>
                                                    <a href="ServletHorarios?accion=room&id=${r.idhabitacion}" class="btn btn-info btn-circle">
                                                        <i class="fas fa-calendar"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>

            </div>






        </div>




        <jsp:include page="includes/sidebarfoot.jsp"></jsp:include>
    </body>
</html>
