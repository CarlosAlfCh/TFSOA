<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detalles</title>
    </head>
    <jsp:include page="includes/sidebarhead.jsp"></jsp:include>
        <body>
            <div class="container-fluid">    
                
            
            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h3 class="m-0 font-weight-bold text-primary">N° de Reserva: 000000${idpago}</h3>

                <a href="ServletTecnico?accion=listar&id=${usuario.getCodigo()}" class="btn btn-secondary btn-icon-split">
                    <span class="icon text-white-50">
                        <i class="fas fa-arrow-left"></i>
                    </span>
                    <span class="text">Volver</span>
                </a>

            </div>

            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Detalle de la reserva</h6>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>Nombre del Servicio</th>
                                    <th>Descripcion</th>
                                    <th>Fecha</th>
                                    <th>N° Personas</th>
                                    <th>Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="de" items="${detalle}" varStatus="status">
                                    <tr>
                                        <td>${de.nombre}</td>
                                        <td>${de.descripcion}</td>
                                        <td>${de.fechaServicio}</td>
                                        <td>${de.npersonas}</td>
                                        <td>${de.total}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>Total:</td>
                                    <td>${total}</td>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <jsp:include page="includes/sidebarfoot.jsp"></jsp:include>
</html>
