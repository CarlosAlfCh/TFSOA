<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>  
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reserva</title>
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
        <%@include file="includes/header.jsp"%>        
    </head>

    <%@include file="includes/navbar.jsp"%>
    <body>
        <%-- Obtener la fecha actual --%>
        <% Date fechaActual = new Date(); %>

        <%-- Formatear la fecha --%> 
        <% SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy"); %>
        <% String fechaFormateada = formatoFecha.format(fechaActual);%>
        <fmt:parseDate value="${fservicio}" pattern="yyyy-MM-dd" var="parsedDate" type="date"/>

        <div class="container">
            <div class="container mt-4">
                <div class="row">
                    <div class="col-md-8">
                        <h2 class="fw-bold">Reserva de Cita</h2>
                    </div>
                    <div class="col-md-4 text-end">
                        <c:if test="${codpago > 0}"><h4>N° 000000${codpago}</h4></c:if>
                        </div>
                    </div>

                    <div class="row mt-3">
                        <div class="col-md-8" style="color: #4c3d3d">
                            <strong>Cliente:</strong> ${cliente.getNombres()} ${cliente.getApelpat()} ${cliente.getApelmat()}
                    </div>
                    <div class="col-md-4" style="color: #4c3d3d">
                        <strong>Fecha Emisión:</strong> <%= fechaFormateada%>
                    </div>
                </div>

                <div class="row mt-3">
                    <div class="col-md-8" style="color: #4c3d3d">
                        <strong>Código:</strong> ${cliente.getCodigo()}
                    </div>
                    <c:if test="${fservicio != null}">
                        <div class="col-md-4" style="color: #4c3d3d">
                            <strong>Fecha servicio SPA:</strong> <fmt:formatDate value="${parsedDate}" pattern="dd/MM/yyyy"/>
                        </div>           
                    </c:if>

                </div>
            </div>

            <hr class="sidebar-divider">

            <div class="row">
                <div class="col-sm-8">
                    <c:if test="${viewroom!=0}">
                        <c:forEach var="car" items="${cart}">
                            <c:if test="${car.getNnoches() != 0}">
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
                                                                ${car.getDescripcion()}
                                                            </div>
                                                            <div class="text-end">
                                                                <c:if test="${codpago == 0}"> 
                                                                    <a class="btn btn-outline-danger" href="ServletGeneral?menu=carrito&accion=eliminaroom&idroom=${car.getIdhabitacion()}">Quitar</a>
                                                                </c:if>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <!-- Detalles básicos -->
                                                    <fmt:parseDate value="${car.getFechaIngreso()}" pattern="yyyy-MM-dd" var="parsedin" type="date"/>
                                                    <fmt:parseDate value="${car.getFechaSalida()}" pattern="yyyy-MM-dd" var="parsedout" type="date"/>

                                                    <div class="mb-4">
                                                        <div class="d-flex justify-content-between mb-2">
                                                            <span class="text-muted"><i class="fas fa-calendar-check text-muted me-2"></i> Fecha ingreso:</span>
                                                            <span class="fw-medium" style="color: #4c3d3d"><fmt:formatDate value="${parsedin}" pattern="dd/MM/yyyy"/> </span>
                                                        </div>
                                                        <div class="d-flex justify-content-between mb-2">
                                                            <span class="text-muted"><i class="fas fa-calendar-minus text-muted me-2"></i> Fecha salida:</span>
                                                            <span class="fw-medium" style="color: #4c3d3d"><fmt:formatDate value="${parsedout}" pattern="dd/MM/yyyy"/> </span>
                                                        </div>
                                                        <div class="d-flex justify-content-between mb-2">
                                                            <span class="text-muted"><i class="fas fa-moon text-muted me-2"></i> Numero de noches:</span>
                                                            <span class="fw-medium" style="color: #4c3d3d">${car.getNnoches()} </span>
                                                        </div>
                                                    </div>
                                                    <!-- Detalles de precio -->
                                                    <div class="price-detail">
                                                        <div class="d-flex justify-content-between mb-2">
                                                            <span class="text-muted">Precio por noche:</span>
                                                            <span class="fw-medium" style="color: #4c3d3d">S/ ${car.getSubtotal()}0</span>
                                                        </div>
                                                        <div class="d-flex justify-content-between mb-2">
                                                            <span class="text-muted">Precio por noche:</span>
                                                            <span class="fw-medium" style="color: #4c3d3d">S/ ${car.getSubtotal()}0</span>
                                                        </div>
                                                        <div class="d-flex justify-content-between mb-2">
                                                            <span class="text-muted">Precio:</span>
                                                            <span class="fw-medium" style="color: #4c3d3d">S/ ${car.getTotal()}0</span>
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
                    <c:if test="${viewroom==0 && codpago == 0}">
                        <a class="btn btn-outline-secondary btn-block" href="ServletGeneral?menu=carrito&accion=volver">Agregar Habitación</a>
                        <hr class="sidebar-divider">
                    </c:if>

                    <c:if test="${viewserv!=0}">
                        <table class="table table-bordered">
                            <thead class="card-header">
                                <tr>
                                    <th>Servicio</th>
                                    <th>Precio</th>
                                    <th>Personas</th>
                                    <th>Subtotal</th>
                                        <c:if test="${codpago == 0 && fservicio == null}">
                                        <th>Opciones</th>
                                        </c:if>
                                </tr>
                            </thead>
                            <tbody class="card-body"> 
                                <c:forEach var="car" items="${cart}">
                                    <c:if test="${car.getNpersonas() != 0}">
                                        <tr>
                                            <td data-toggle="tooltip" data-placement="bottom" title="${car.getDescripcion()} ">
                                                ${car.getNombre()} 
                                            </td>

                                            <td>S/${car.getSubtotal()}0</td>

                                            <td>
                                                <c:if test="${codpago==0}"> 
                                                    <div class="input-group mb-3">
                                                        <c:if test="${car.getNpersonas() != 1}">
                                                            <div class="input-group-prepend">
                                                                <a class="btn btn-outline-info" href="ServletGeneral?menu=carrito&accion=quita&id=${car.getIdservicio()}"><strong>-</strong></a>
                                                            </div>
                                                        </c:if>

                                                        <input type="text" name="cantidad" value="${car.getNpersonas()}" class="form-control" readonly="">

                                                        <div class="input-group-append">
                                                            <a class="btn btn-outline-info" href="ServletGeneral?menu=carrito&accion=add&id=${car.getIdservicio()}"><strong>+</strong></a>
                                                        </div>
                                                    </div>
                                                </c:if>
                                                <c:if test="${codpago!=0}">${car.getNpersonas()}</c:if>
                                                </td>

                                                <td>S/${car.getTotal()}0</td>
                                            <c:if test="${codpago == 0 && fservicio == null}"> 
                                                <td style="text-align: center;"> 
                                                    <a class="btn btn-outline-danger" href="ServletGeneral?menu=carrito&accion=eliminarserv&idserv=${car.getIdservicio()}">Quitar</a>
                                                </td>
                                            </c:if>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>

                    <c:if test="${viewserv==0 && codpago == 0}">
                        <a class="btn btn-outline-secondary btn-block" href="ServletGeneral?menu=carrito&accion=volver">Agregar Servicio de SPA</a>
                    </c:if>
                </div>

                <div class="col-sm-4">

                    <div class="card">
                        <!-- Pagos Modal-->
                        <div class="modal fade" id="pagos" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                             aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLabel">Pagar</h5>
                                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">×</span>
                                        </button>
                                    </div>
                                    <form action="ServletGeneral" method="post">                                    
                                        <div class="modal-body">
                                            <div class="card-header">
                                                <label style="color: #4c3d3d">Fecha: <%= fechaFormateada%><br>
                                                    Ingrese codigo y metodo usado para el pago<br> 
                                                    N° de cuenta: 00000000000000000000000<br>
                                                    N° telefono deposito: +51 000 000 000  
                                                </label>                                          
                                            </div>
                                            <div class="card-body">
                                                <label style="color: #4c3d3d">Metodos de pago</label>
                                                <select name="forma" class="form-control" required=""> 
                                                    <option value="Efectivo" selected>Seleccione medoto de pago</option>
                                                    <option value="Efectivo">Pago en efectivo</option>
                                                    <option value="Yape">Yape</option>
                                                    <option value="Plin">Plin</option>
                                                    <option value="Transferencia">Transaccion Bancaria</option>
                                                </select> 
                                                <br><label style="color: #4c3d3d">Digite el N° de Operacion o Deposito</label>
                                                <input type="text" name="codi" placeholder="Si es pago en efectivo deje vacio" class="form-control">
                                                <input type="hidden" name="factual" value="<%= fechaFormateada%>">
                                            </div>
                                        </div>
                                        <div class="card-footer">
                                            <button class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                            <button class="btn btn-primary" name="menu" value="pay">Pagar</button>
                                        </div>  
                                    </form>
                                </div>
                            </div>
                        </div>

                        <div class="card-header">
                            <h3>Costo de Reserva</h3>
                        </div>
                        <form action="ServletGeneral" method="post">
                            <div class="card-body">
                                <c:if test="${viewserv!=0}">
                                    <c:if test="${fservicio == null}">
                                        <form action="ServletGeneral" method="post">                        
                                            <label style="color: #4c3d3d">Ingrese fecha para agendar su servicio de SPA</label>
                                            <input type="date" name="txtfechaservice" class="form-control">
                                            <button class="btn btn-primary btn-block" name="menu" value="fecha" >Agendar</button>                        
                                        </form>                        
                                    </c:if>
                                </c:if>
                                <input type="hidden" name="code" value="${cliente.getCodigo()}">
                                <label style="color: #4c3d3d">Subtotal</label>
                                <input type="text" name="name" value="S/ ${subtotal}0" readonly="" class="form-control"><br>
                                <label style="color: #4c3d3d">Descuento</label>

                                <c:if test="${porcentaje == 0}">

                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small" value="S/ 0.00"
                                               aria-label="Search" aria-describedby="basic-addon2" readonly>

                                        <div class="input-group-append">
                                            <a class="btn btn-success" data-toggle="modal" data-target="#modal" >
                                                <i class="fas fa-plus fa-sm"></i>
                                            </a>
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
                                <c:if test="${codpago == 0}"> 
                                    <c:if test="${fservicio == null && viewserv!=0}">
                                        <label style="color: #4c3d3d">*Seleccione una fecha para su servicio</label>
                                    </c:if>
                                    <c:if test="${fservicio != null}">
                                        <label style="color: #4c3d3d">* Realize el pago para continuar</label>
                                        <a href="#" class="btn btn-success btn-block" data-toggle="modal" data-target="#pagos">Pagar</a>
                                    </c:if>
                                    <c:if test="${viewserv==0 && viewroom!=0}">
                                        <label style="color: #4c3d3d">* Realize el pago para continuar</label>
                                        <a href="#" class="btn btn-success btn-block" data-toggle="modal" data-target="#pagos">Pagar</a>
                                    </c:if>
                                </c:if>
                                <c:if test="${codpago > 0}">

                                    <input type="hidden" name="txtfecha" class="form-control" value="<%= fechaFormateada%>"/>

                                    <button class="btn btn-primary btn-block" name="menu" value="buy" onclick="print()">Procesar Reserva</button>
                                </c:if>                                
                            </div>

                        </form>
                    </div>

                </div>
            </div>
        </div>
                            
        <!-- Canjear Modal-->
        <div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Canjear codigo promocional</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <form action="ServletGeneral" method="post">                                    
                        <div class="modal-body">
                            <div class="card-header">
                                <label style="color: #4c3d3d">
                                    Revise en su correo si tiene algun codigo de descuento<br> 
                                    Enviado a correo: ${cliente.getCorreo()}
                                </label>                                          
                            </div>
                            <div class="card-body">
                                <input type="hidden" name="txtidcliente" value="${cliente.getCodigo()}">

                                <div class="input-group">
                                    <input type="text" class="form-control bg-light border-0 small" placeholder="Ingrese codigo de descuento..."
                                           aria-label="Search" aria-describedby="basic-addon2" name="txtcodpromo">
                                    <div class="input-group-addon">
                                        <div class="btn btn-info">
                                            <i class=" fas fa-envelope fa-sm"></i>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="card-footer">
                            <button class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                            <button class="btn btn-primary" name="menu" value="cupon">Canjear</button>
                        </div>  
                    </form>
                </div>
            </div>
        </div>
                                
    </body>

    <%@include file="includes/footer.jsp"%>
</html>
