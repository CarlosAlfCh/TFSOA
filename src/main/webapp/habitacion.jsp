<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>  
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reserva</title>
        <style>
            .card-image-container {
                height: 100%;
                min-height: 400px;
                position: relative;
                overflow: hidden;
            }

            .card-image {
                position: absolute;
                width: 100%;
                height: 100%;
                object-fit: cover;
            }

            @media (max-width: 767.98px) {
                .card-image-container {
                    min-height: 300px;
                }
            }
        </style>
        <%@include file="includes/header.jsp"%>        
    </head>

    <%@include file="includes/navbar.jsp"%>
    <body>

        <div class="container py-5">
            <div class="card shadow">
                <div class="row g-0">
                    <!-- Imagen del lado izquierdo -->
                    <div class="col-md-6">
                        <div class="card-image-container">
                            <img src="src/img/habita.jpg" 
                                 class="card-image" 
                                 alt="Habitación Deluxe">
                        </div>
                    </div>

                    <!-- Información del lado derecho -->
                    <div class="col-md-6">
                        <div class="card-body p-4">
                            <!-- Encabezado -->
                            <div class="mb-4">
                                <h3 class="card-title mb-2">
                                    <i class="fas fa-bed me-2"></i>
                                    Habitación ${room.getTipo()} 
                                </h3>
                                <p class="card-text text-muted">${room.getDescripcion()}</p>
                            </div>

                            <!-- Detalles de la habitación -->
                            <div class="row mb-4">
                                <div class="col-6">
                                    <div class="d-flex align-items-center">
                                        <i class="fas fa-users text-muted me-2"></i>
                                        <span style="color: #000000">&nbsp;&nbsp;2 personas</span>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="d-flex align-items-center">
                                        <i class="fas fa-dollar-sign text-muted me-2"></i>
                                        <span style="color: #000000">&nbsp;&nbsp;S/ ${room.getPrecioNoche()} x noche</span>
                                    </div>
                                </div>
                            </div>

                            <!-- Formulario de fechas -->
                            <form action="ServletGeneral" method="post">

                                <input type="hidden" name="idroom" value="${room.getIdhabitacion()}">
                                
                                <div class="mb-3">
                                    <label class="form-label d-flex align-items-center" style="color: #000000">
                                        <i class="fas fa-calendar me-2 text-muted"></i>
                                        &nbsp;&nbsp;Check-In
                                    </label>
                                    <input type="date" name="fechaIngreso" class="form-control" required>
                                </div>

                                <div class="mb-4">
                                    <label class="form-label d-flex align-items-center" style="color: #000000">
                                        <i class="fas fa-calendar me-2 text-muted"></i>
                                        &nbsp;&nbsp;Check-Out
                                    </label>
                                    <input type="date" name="fechaSalida" class="form-control" required>
                                </div>

                                <!-- Botones -->
                                <div class="d-flex gap-3">
                                    <a href="ServletGeneral?menu=carrito&accion=volver" class="btn btn-secondary flex-grow-1 py-2">
                                        <i class="fas fa-arrow-left me-2"></i>
                                        Regresar
                                    </a>
                                    <button type="submit" name="menu" value="ocupar" class="btn btn-primary flex-grow-1 py-2">
                                        <i class="fas fa-check me-2"></i>
                                        Procesar
                                    </button>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>

    <%@include file="includes/footer.jsp"%>
</html>
