<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">

        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>


        <style>
            .card-hover:hover {
                transform: translateY(-5px);
                transition: transform 0.3s ease;
            }
            .amenity-icon {
                width: 24px;
                height: 24px;
                display: inline-flex;
                align-items: center;
                justify-content: center;
                background-color: #e9ecef;
                border-radius: 50%;
                margin-right: 8px;
            }
            .table-hover tbody tr:hover {
                background-color: rgba(0,123,255,0.05) !important;
            }
            .reservation-badge {
                font-size: 0.85rem;
                padding: 0.35rem 0.65rem;
            }
            .table th {
                background-color: #f8f9fa;
                border-bottom: 2px solid #dee2e6;
                white-space: nowrap;
            }
            .date-cell {
                min-width: 200px;
            }
            .status-badge {
                width: 10px;
                height: 10px;
                display: inline-block;
                border-radius: 50%;
                margin-right: 5px;
            }
            .table td {
                vertical-align: middle;
            }
            .service-icon {
                width: 20px;
                height: 20px;
                margin-right: 5px;
            }
        </style>

    </head>
    <jsp:include page="includes/header.jsp"></jsp:include>
        <body>

            <!-- Page Header Start -->
        <c:if test="${cliente.estado != null}"> 
            <jsp:include page="includes/navbar.jsp"></jsp:include>
        </c:if>    




        <hr class="sidebar-divider">
        <div class="container">

            <c:forEach var="h" items="${historial}" varStatus="status">
                <c:if test="${h.estado == 1}">
                    <fmt:parseDate value="${h.fechaServicio}" pattern="yyyy-MM-dd" var="parseserv" type="date"/>
                    <fmt:parseDate value="${h.fechaIngreso}" pattern="yyyy-MM-dd" var="parsein" type="date"/>
                    <fmt:parseDate value="${h.fechaSalida}" pattern="yyyy-MM-dd" var="parseout" type="date"/>
                    <div class="card shadow card-hover" style="max-width: 1000px; margin: auto;">
                        <div class="row g-0">
                            <div class="col-md-4">
                                <img src="src/img/habita.jpg" class="img-fluid h-100 object-fit-cover" style="border-top-left-radius: 4px; border-bottom-left-radius: 4px;" alt="Habitación de lujo">
                            </div>
                            <div class="col-md-8">
                                <div class="card-body">
                                    <div class="d-flex justify-content-between align-items-start mb-3">
                                        <div>
                                            <c:choose>
                                                <c:when test="${not empty h.fechaServicio && not empty h.fechaIngreso}">
                                                    <h5 class="card-title text-info mb-1">
                                                        <i class="fas fa-bed me-1"></i>
                                                        <i class="fas fa-spa me-1"></i>
                                                        Reserva de Habitacion + Tratamiento SPA
                                                    </h5>
                                                </c:when>
                                                <c:when test="${not empty h.fechaServicio && empty h.fechaIngreso}">
                                                    <h4 class="card-title text-info mb-1">
                                                        <i class="fas fa-spa me-1"></i>
                                                        Reserva de Tratamiento SPA
                                                    </h4>
                                                </c:when>
                                                <c:when test="${empty h.fechaServicio && not empty h.fechaIngreso}">
                                                    <h4 class="card-title text-info mb-1">
                                                        <i class="fas fa-bed me-1"></i>
                                                        Reserva de Habitacion
                                                    </h4>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="small">
                                                        <i class="fas fa-exclamation-circle me-1 text-warning"></i>
                                                        No hay información disponible.
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>

                                            <p class="text-muted mb-0">
                                                <i class="fas fa-ticket-alt me-2"></i>Reserva #: 000000${h.idpago}
                                            </p>

                                        </div>
                                        <c:if test="${h.idtecnico != 0}">
                                            <span class="badge bg-success px-3 py-2">Confirmado</span>
                                        </c:if>
                                        <c:if test="${h.idtecnico == 0}">
                                            <span class="badge bg-warning px-3 py-2">Pendiente</span>
                                        </c:if>
                                    </div>

                                    <div class="row mb-4">

                                        <c:choose>
                                            <c:when test="${not empty h.fechaServicio && not empty h.fechaIngreso}">
                                                <div class="col-md-6">
                                                    <div class="p-3 bg-light rounded-3 mb-3">
                                                        <h6 class="mb-2"><i class="fas fa-bed text-info me-2"></i>Habitación</h6>
                                                        <div class="mb-1">
                                                            <div>
                                                                <i class="fas fa-sign-in-alt me-1 text-secondary"></i>
                                                                Check In: <fmt:formatDate value="${parsein}" pattern="dd/MM/yyyy" />
                                                            </div>
                                                            <div>
                                                                <i class="fas fa-sign-out-alt me-1 text-secondary"></i>
                                                                Check Out: <fmt:formatDate value="${parseout}" pattern="dd/MM/yyyy" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="p-3 bg-light rounded-3 mb-3">
                                                        <h6 class="mb-2"><i class="fas fa-spa text-info me-2"></i>Tratamiento de SPA</h6>
                                                        <div class="mb-1">
                                                            <div>
                                                                <i class="fas fa-calendar-alt me-1 text-secondary"></i>
                                                                Servicio: <fmt:formatDate value="${parseserv}" pattern="dd/MM/yyyy" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:when test="${not empty h.fechaServicio && empty h.fechaIngreso}">
                                                <div class="col-md-6">
                                                    <div class="p-3 bg-light rounded-3 mb-3">
                                                        <h6 class="mb-2"><i class="fas fa-spa text-info me-2"></i>Tratamiento de SPA</h6>
                                                        <div class="mb-1">
                                                            <div>
                                                                <i class="fas fa-calendar-alt me-1 text-secondary"></i>
                                                                Servicio: <fmt:formatDate value="${parseserv}" pattern="dd/MM/yyyy" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:when test="${empty h.fechaServicio && not empty h.fechaIngreso}">
                                                <div class="col-md-6">
                                                    <div class="p-3 bg-light rounded-3 mb-3">
                                                        <h6 class="mb-2"><i class="fas fa-bed text-info me-2"></i>Habitación</h6>
                                                        <div class="mb-1">
                                                            <div>
                                                                <i class="fas fa-sign-in-alt me-1 text-secondary"></i>
                                                                Check In: <fmt:formatDate value="${parsein}" pattern="dd/MM/yyyy" />
                                                            </div>
                                                            <div>
                                                                <i class="fas fa-sign-out-alt me-1 text-secondary"></i>
                                                                Check Out: <fmt:formatDate value="${parseout}" pattern="dd/MM/yyyy" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="small">
                                                    <i class="fas fa-exclamation-circle me-1 text-warning"></i>
                                                    No hay información disponible.
                                                </div>
                                            </c:otherwise>
                                        </c:choose>

                                    </div>

                                    <div class="d-flex justify-content-between align-items-center">
                                        <div>
                                            <small class="d-block text-muted">¿Cambios? Llama a:</small>
                                            <strong class="text-secondary">+51 900 123 456</strong>
                                        </div>
                                        <div class="text-end">
                                            <a href="ServletServiceCliente?accion=ver&idreserva=${h.idreserva}" 
                                               class="btn btn-sm btn-outline-info">
                                                <i class="fas fa-eye me-1"></i>
                                                Ver detalles
                                            </a>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                    <hr class="sidebar-divider">
                </c:if>
            </c:forEach>



            <!-- Page Header End --> 


            <div class="card shadow-sm">
                <div class="card-header bg-white py-3">
                    <h5 class="mb-0">Historial de Reservas</h5>
                </div>
                <div class="table-responsive">
                    <table class="table table-hover mb-0">
                        <thead>
                            <tr>
                                <th class="ps-3">
                                    <i class="fas fa-hashtag me-2 text-primary"></i>Código de reserva
                                </th>
                                <th>
                                    <i class="fas fa-calendar-check me-2 text-primary"></i>Fecha
                                </th>
                                <th>
                                    <i class="fas fa-concierge-bell me-2 text-primary"></i>Servicios
                                </th>
                                <th>
                                    <i class="fas fa-dollar-sign me-2 text-primary"></i>Monto
                                </th>
                                <th class="date-cell">
                                    <i class="fas fa-clock me-2 text-primary"></i>Fechas
                                </th>
                                <th class="text-center">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="h" items="${historial}" varStatus="status">
                                <c:if test="${h.estado == 0}">
                                    <fmt:parseDate value="${h.fechaServicio}" pattern="yyyy-MM-dd" var="parseserv" type="date"/>
                                    <fmt:parseDate value="${h.fechaIngreso}" pattern="yyyy-MM-dd" var="parsein" type="date"/>
                                    <fmt:parseDate value="${h.fechaSalida}" pattern="yyyy-MM-dd" var="parseout" type="date"/>
                                    <tr>
                                        <td>
                                            <span class="badge bg-light text-dark">
                                                <i class="fas fa-check-circle text-success me-1"></i>
                                                000000${h.idpago}
                                            </span>
                                        </td>
                                        <td>
                                            ${h.fecha}
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${h.fechaServicio != null && h.fechaIngreso != null}">
                                                    <!-- SPA + Habitación -->
                                                    <span class="badge bg-primary reservation-badge">
                                                        <i class="fas fa-bed me-1"></i>
                                                        <i class="fas fa-spa me-1"></i>
                                                        Habitación + SPA
                                                    </span>
                                                </c:when>
                                                <c:when test="${h.fechaServicio != null && h.fechaIngreso == null}">
                                                    <!-- Solo SPA -->
                                                    <span class="badge bg-info reservation-badge">
                                                        <i class="fas fa-spa me-1"></i>
                                                        Servicio SPA
                                                    </span>
                                                </c:when>
                                                <c:when test="${h.fechaServicio == null && h.fechaIngreso != null}">
                                                    <!-- Solo Habitación -->
                                                    <span class="badge bg-secondary reservation-badge">
                                                        <i class="fas fa-bed me-1"></i>
                                                        Habitación
                                                    </span>
                                                </c:when>
                                                <c:otherwise>
                                                    <!-- Ninguno -->
                                                    <span class="badge bg-warning reservation-badge">
                                                        <i class="fas fa-exclamation-circle me-1"></i>
                                                        Sin asignación
                                                    </span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <strong class="text-success">S/ ${h.monto}</strong>
                                        </td>
                                        <td class="date-cell">

                                            <c:choose>
                                                <c:when test="${not empty h.fechaServicio && not empty h.fechaIngreso}">
                                                    <div class="small">
                                                        <div class="mb-1">
                                                            <i class="fas fa-spa me-1 text-info"></i>
                                                            Servicio: <fmt:formatDate value="${parseserv}" pattern="dd/MM/yyyy" />
                                                        </div>
                                                        <div>
                                                            <i class="fas fa-sign-in-alt me-1 text-success"></i>
                                                            Check In: <fmt:formatDate value="${parsein}" pattern="dd/MM/yyyy" />
                                                        </div>
                                                        <div>
                                                            <i class="fas fa-sign-out-alt me-1 text-danger"></i>
                                                            Check Out: <fmt:formatDate value="${parseout}" pattern="dd/MM/yyyy" />
                                                        </div>
                                                    </div>
                                                </c:when>
                                                <c:when test="${not empty h.fechaServicio && empty h.fechaIngreso}">
                                                    <div class="small">
                                                        <i class="fas fa-spa me-1 text-info"></i>
                                                        Servicio: <fmt:formatDate value="${parseserv}" pattern="dd/MM/yyyy" />
                                                    </div>
                                                </c:when>
                                                <c:when test="${empty h.fechaServicio && not empty h.fechaIngreso}">
                                                    <div class="small">
                                                        <div>
                                                            <i class="fas fa-sign-in-alt me-1 text-success"></i>
                                                            Check In: <fmt:formatDate value="${parsein}" pattern="dd/MM/yyyy" />
                                                        </div>
                                                        <div>
                                                            <i class="fas fa-sign-out-alt me-1 text-danger"></i>
                                                            Check Out: <fmt:formatDate value="${parseout}" pattern="dd/MM/yyyy" />
                                                        </div>
                                                    </div>
                                                </c:when>
                                                <c:otherwise>
                                                    <div class="small">
                                                        <i class="fas fa-exclamation-circle me-1 text-warning"></i>
                                                        No hay información disponible.
                                                    </div>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td class="text-center">
                                            <a href="ServletServiceCliente?accion=ver&idreserva=${h.idreserva}" 
                                               class="btn btn-sm btn-outline-info">
                                                <i class="fas fa-eye me-1"></i>
                                                Ver detalles
                                            </a>
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </body>
    <%@include file="includes/footer.jsp"%>
</html>
