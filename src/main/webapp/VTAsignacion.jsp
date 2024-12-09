<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html> 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Asignaciones</title>
    </head>
    <jsp:include page="includes/sidebarhead.jsp"></jsp:include>
        <body>    
            <div class="container-fluid">
                <!-- Page Heading -->
                <h1 class="h3 mb-2 text-gray-800">Valida tus actividades ${usuario.getNombres()}</h1>

            <p class="mb-4">
                A continuación se presenta las reserva a las que fue asignado
            </p>
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Reservas</h6>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>Codigo</th>
                                    <th>Cliente</th>
                                    <th>Fecha</th>
                                    <th>Monto</th>
                                    <th>Estado</th>
                                    <th>Opciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="p" items="${pendientes}" varStatus="status">
                                    <c:if test="${p.estado == 1}"> 
                                        <tr>
                                            <td>000000${p.idpago}</td>
                                            <td>${p.nomCliente}</td>
                                            <td>${p.fecha}</td>
                                            <td>${p.monto}</td>
                                            <td>
                                                <span class="badge bg-warning reservation-badge text-white">
                                                    <i class="fas fa-clock me-1"></i>
                                                    Pendiente
                                                </span>
                                            </td>
                                            <td>
                                                <a class="btn btn-success btn-circle" data-toggle="modal" data-target="#modal${p.idreserva}">
                                                    <i class="fas fa-check"></i>
                                                </a>
                                                <a href="ServletTecnico?accion=detalle&idreserva=${p.idreserva}&idpago=${p.idpago}" class="btn btn-info btn-circle">
                                                    <i class="fas fa-eye"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:if>

                                    <!-- Modal para marcar reserva -->
                                <div class="modal fade" id="modal${p.idreserva}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">¿Marcar como realizado?</h5>
                                                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">×</span>
                                                </button>
                                            </div>

                                            <div class="modal-body">
                                                <div class="card-header">
                                                    <label style="color: #4c3d3d">Se procedera a validar el cumpliento de la reserva N° 000000${p.idpago}</label>
                                                </div>
                                            </div>

                                            <div class="card-footer">
                                                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                                                <a class="btn btn-success" href="ServletTecnico?accion=marcar&idreserva=${p.idreserva}&idpago=${p.idpago}" >Validar</a>
                                            </div>  
                                        </div>
                                    </div>
                                </div>

                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <jsp:include page="includes/sidebarfoot.jsp"></jsp:include>
</html>