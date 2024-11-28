<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cita</title>
        <link href="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.11.3/main.min.js"></script>
        <style>
            .custom-container {
                width: 100%;
                padding-right: 0;
                padding-left: 0;
                margin-right: auto;
                margin-left: auto;
            }

            .card {
                margin: 0;
            }

            .card-image-wrapper {
                height: 100%;
                width: 100%;
                position: relative;
                overflow: hidden;
            }

            .card-image-wrapper img {
                height: 100%;
                width: 100%;
                object-fit: cover;
                position: absolute;
                top: 0;
                left: 0;
            }

            @media (max-width: 767.98px) {
                .card-image-wrapper {
                    height: 250px;
                    position: relative;
                }

                .card-image-wrapper img {
                    position: absolute;
                }
            }
        </style>
    </head>
    <jsp:include page="includes/header.jsp"></jsp:include>
        <body>

            <!-- Page Header Start -->
        <c:if test="${cliente.estado != null}"> 
            <jsp:include page="includes/navbar.jsp"></jsp:include>
        </c:if>    
        <!-- Page Header End --> 

        <fmt:parseDate value="${reserva.getFechaServicio()}" pattern="yyyy-MM-dd" var="fserv" type="date"/>
        
        <div class="container">
            <div class="container mt-4">
                <div class="row">
                    <div class="col-md-8">
                        <h2 class="fw-bold">Reserva de Cita</h2>
                    </div>
                    <div class="col-md-4 text-end">
                        <h4>N° 000000${reserva.getIdpago()}</h4>
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-md-8" style="color: #4c3d3d">
                        <strong>Cliente:</strong> ${cliente.getNombres()} ${cliente.getApelpat()} ${cliente.getApelmat()}
                    </div>
                    <div class="col-md-4" style="color: #4c3d3d">
                        <strong>Fecha Emisión: </strong> ${reserva.getFecha()}
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-md-8" style="color: #4c3d3d">
                        <strong>Código:</strong> ${cliente.getCodigo()}
                    </div>
                    <c:if test="${reserva.getFechaServicio() != null}">
                        <div class="col-md-4" style="color: #4c3d3d">
                            <strong>Fecha servicio SPA: </strong> <fmt:formatDate value="${fserv}" pattern="dd/MM/yyyy"/>
                        </div>           
                    </c:if>

                </div>
            </div>

            <hr class="sidebar-divider">

            <div class="row">
                <div class="col-sm-8">
                    <c:if test="${reserva.getFechaIngreso() != null}">
                        <c:forEach var="d" items="${detalle}">
                            <c:if test="${d.getNnoches() != 0}">
                                <div class="custom-container">
                                    <div class="card shadow w-100">
                                        <div class="row g-0">
                                            <!-- Imagen del lado izquierdo -->
                                            <div class="col-md-5">
                                                <div class="card-image-wrapper">
                                                    <img src="src/img/room.jpeg" 
                                                         class="img-fluid h-100 w-100" 
                                                         alt="Habitacion"
                                                         style="object-fit: cover;">
                                                </div>
                                            </div>
                                            <!-- Información del lado derecho -->
                                            <div class="col-md-7">
                                                <div class="card-body p-4">
                                                    <!-- Encabezado -->
                                                    <div class="mb-4">
                                                        <div class="d-flex justify-content-between align-items-start mb-3">
                                                            <div>
                                                                ${d.getDescripcion()}
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- Detalles básicos -->
                                                    <fmt:parseDate value="${d.getFechaIngreso()}" pattern="yyyy-MM-dd" var="parsedin" type="date"/>
                                                    <fmt:parseDate value="${d.getFechaSalida()}" pattern="yyyy-MM-dd" var="parsedout" type="date"/>
                                                    
                                                    <div class="mb-4">
                                                        <div class="d-flex justify-content-between mb-2">
                                                            <span class="text-muted"><i class="fas fa-calendar-check text-muted me-2"></i> Fecha ingreso:</span>
                                                            <span class="fw-medium" style="color: #4c3d3d"><fmt:formatDate value="${parsedin}" pattern="dd/MM/yyyy"/></span>
                                                        </div>
                                                        <div class="d-flex justify-content-between mb-2">
                                                            <span class="text-muted"><i class="fas fa-calendar-minus text-muted me-2"></i> Fecha salida:</span>
                                                            <span class="fw-medium" style="color: #4c3d3d"><fmt:formatDate value="${parsedout}" pattern="dd/MM/yyyy"/></span>
                                                        </div>
                                                        <div class="d-flex justify-content-between mb-2">
                                                            <span class="text-muted"><i class="fas fa-moon text-muted me-2"></i> Numero de noches:</span>
                                                            <span class="fw-medium" style="color: #4c3d3d">${d.getNnoches()}</span>
                                                        </div>
                                                    </div>
                                                    <!-- Detalles de precio -->
                                                    <div class="price-detail">
                                                        <div class="d-flex justify-content-between mb-2">
                                                            <span class="text-muted">Precio por noche:</span>
                                                            <span class="fw-medium" style="color: #4c3d3d">S/ ${d.getSubtotal()}0</span>
                                                        </div>
                                                        <div class="d-flex justify-content-between mb-2">
                                                            <span class="text-muted">Precio por noche:</span>
                                                            <span class="fw-medium" style="color: #4c3d3d">S/ ${d.getSubtotal()}0</span>
                                                        </div>
                                                        <div class="d-flex justify-content-between mb-2">
                                                            <span class="text-muted">Precio:</span>
                                                            <span class="fw-medium" style="color: #4c3d3d">S/ ${d.getTotal()}0</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                        <hr class="sidebar-divider">
                    </c:if>

                    <c:if test="${reserva.getFechaServicio() != null}">
                        <table class="table table-bordered">
                            <thead class="card-header">
                                <tr>
                                    <th>Servicio</th>
                                    <th>Precio</th>
                                    <th>Personas</th>
                                    <th>Subtotal</th>
                                </tr>
                            </thead>
                            <tbody class="card-body"> 
                                <c:forEach var="car" items="${detalle}">
                                    <c:if test="${car.getNpersonas() != 0}">
                                        <tr>
                                            <td data-toggle="tooltip" data-placement="bottom" title="${car.getDescripcion()} ">
                                                ${car.getNombre()} 
                                            </td>
                                            <td>S/${car.getSubtotal()}0</td>
                                            <td>
                                                ${car.getNpersonas()}
                                            </td>
                                            <td>
                                                S/${car.getTotal()}0
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>

                <div class="col-sm-4">

                    <div class="card">

                        <div class="card-header">
                            <h3>Costo de Reserva</h3>
                        </div>


                        <div class="card-body">
                            <label style="color: #4c3d3d">Subtotal</label>
                            <input type="text" name="name" value="S/ ${subtotal}0" readonly="" class="form-control"><br>
                            <label style="color: #4c3d3d">Descuento</label>

                            <c:if test="${porcentaje == 0}">

                                <div class="input-group">
                                    <input type="text" class="form-control bg-light border-0 small" value="S/ 0.00"
                                           aria-label="Search" aria-describedby="basic-addon2" readonly>

                                    <div class="input-group-append">
                                        <div class="btn btn-success">
                                            S/D
                                        </div>
                                    </div>

                                </div>

                            </c:if>

                            <c:if test="${porcentaje > 0}">

                                <div class="input-group">
                                    <input type="text" class="form-control bg-light border-0 small" value="S/ ${descuento}0"
                                           aria-label="Search" aria-describedby="basic-addon2" readonly>

                                    <div class="input-group-append">
                                        <div class="btn btn-success">
                                            ${porcentaje}%
                                        </div>
                                    </div>
                                </div>

                            </c:if>

                            <label style="color: #4c3d3d">Total a pagar</label>
                            <input type="text" name="name" value="S/ ${totalpagar}0" readonly="" class="form-control">
                        </div>


                        <div class="card-footer">
                            <button class="badge btn btn-outline-secondary" onclick="print()">Imprimir</button>
                        </div>


                    </div>

                </div>
            </div>
        </div>

    </body>
    <jsp:include page="includes/footer.jsp"></jsp:include>
</html>
