<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Asignar</title>

        <!-- Incluir jQuery desde una CDN -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </head>

    <jsp:include page="includes/sidebarhead.jsp"></jsp:include>
    <body>
        <div class="container-fluid">
            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Asignar tecnicos</h6>
                </div>

                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>Reserva</th>
                                    <th>Cliente</th>
                                    <th>Fecha</th>
                                    <th>Monto</th>
                                    <th>Tecnico Asignado</th>
                                    <th>Opciones</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="r" items="${reservas}" varStatus="status">
                                <tr>
                                    <td>000000${r.idpago}</td>
                                    <td>${r.nomCliente}</td>
                                    <td>${r.fechaServicio}</td>
                                    <td>${r.monto}</td>
                                    <td>
                                        ${r.nomTecnico} 
                                    </td>
                                    <td>
                                        <c:if test="${r.nomTecnico ne 'No asignado'}"> 
                                            <c:if test="${r.estado == 0}"> 
                                                <span class="badge bg-success reservation-badge text-white">
                                                    <i class="fas fa-check-double me-1"></i>
                                                    Realizado
                                                </span>
                                            </c:if>
                                            
                                            <c:if test="${r.estado == 1}"> 
                                                <a href="ServletAsignar?accion=borrar&idreserva=${r.idreserva}" class="btn btn-secondary btn-circle">
                                                    <i class="fas fa-minus"></i>
                                                </a>
                                            </c:if>
                                        </c:if> 
                                        <c:if test="${r.nomTecnico eq 'No asignado'}"> 
                                            <a class="btn btn-warning btn-circle" data-toggle="modal" data-target="#modal${r.idreserva}">
                                                <i class="fas fa-clock"></i>
                                            </a>
                                        </c:if>
                                    </td>
                                </tr>

                                <!-- Modal para asignar técnico -->
                                <div class="modal fade" id="modal${r.idreserva}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Asignar Tecnico a tratamiento</h5>
                                                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">×</span>
                                                </button>
                                            </div>
                                            
                                            <!-- Formulario para asignar técnico -->
                                            <form action="ServletAsignar" method="post">
                                                <div class="modal-body">
                                                    <div class="card-header">
                                                        <label style="color: #4c3d3d">Seleccione técnico para atender tratamiento de reserva ${r.idpago}</label>
                                                    </div>
                                                    
                                                    <!-- Campo de búsqueda -->
                                                    <div class="input-group">
                                                        <input type="text" id="busqueda${r.idreserva}" class="form-control bg-light border-0 small" placeholder="Buscar..." aria-label="Search" aria-describedby="basic-addon2">
                                                        <div class="input-group-append">
                                                            <div class="btn btn-info">
                                                                <i class="fas fa-search fa-sm"></i>
                                                            </div>
                                                        </div>
                                                    </div>

                                                    <!-- ComboBox con opciones -->
                                                    <select id="resultadosCombo${r.idreserva}" class="form-control" size="2">
                                                        <c:forEach var="t" items="${tecnicos}" varStatus="status">
                                                            <c:if test="${t.rol == 3 && t.estado == 1}">
                                                                <option value="${t.codigo}" data-idcliente="${t.codigo}" data-correo="${t.correo}">${t.nombres} ${t.apelpat} ${t.apelmat}</option>
                                                            </c:if>
                                                        </c:forEach>
                                                    </select>

                                                    <div class="my-2"></div>

                                                    <!-- Campo de id del técnico -->
                                                    <div class="input-group">
                                                        <div class="input-group-prepend">
                                                            <div class="btn btn-secondary">
                                                                ID
                                                            </div>
                                                        </div>
                                                        <input type="text" id="idcliente${r.idreserva}" class="form-control bg-light border-0 small" placeholder="Id del técnico" name="txtidtecnico" readonly>
                                                    </div>

                                                    <div class="my-2"></div>

                                                    <!-- Campo de correo -->
                                                    <div class="input-group">
                                                        <div class="input-group-prepend">
                                                            <div class="btn btn-secondary">
                                                                <i class="fas fa-envelope fa-sm"></i>
                                                            </div>
                                                        </div>
                                                        <input type="email" id="correo${r.idreserva}" class="form-control bg-light border-0 small" placeholder="Correo" name="txtcorreo" readonly>
                                                    </div>

                                                    <div class="my-2"></div>

                                                    <!-- Input idreserva -->
                                                    <input type="hidden" name="idreserva" value="${r.idreserva}">
                                                </div>

                                                <div class="card-footer">
                                                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                                                    <button class="btn btn-success" type="submit" name="accion" value="asignar">Asignar</button>
                                                </div>  
                                            </form>
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

        <script>
            $(document).ready(function () {
                $('div.modal').each(function () {
                    var modalId = $(this).attr('id');
                    var idReserva = modalId.replace('modal', '');

                    var $busqueda = $('#busqueda' + idReserva);
                    var $comboBox = $('#resultadosCombo' + idReserva);
                    var $idCliente = $('#idcliente' + idReserva);
                    var $correo = $('#correo' + idReserva);

                    var opcionesOriginales = $comboBox.find('option').clone();

                    $busqueda.on('keyup', function () {
                        var textoBusqueda = $(this).val().toLowerCase();
                        var opcionesFiltradas = opcionesOriginales.filter(function () {
                            return $(this).text().toLowerCase().includes(textoBusqueda);
                        });

                        $comboBox.empty();
                        if (opcionesFiltradas.length === 0) {
                            $comboBox.append('<option disabled>No hay coincidencias</option>');
                        } else {
                            $comboBox.append(opcionesFiltradas);
                        }
                    });

                    $comboBox.on('change', function () {
                        var optionSeleccionada = $(this).find('option:selected');
                        var idCliente = optionSeleccionada.data('idcliente');
                        var correo = optionSeleccionada.data('correo');

                        $idCliente.val(idCliente);
                        $correo.val(correo);
                    });
                });
            });
        </script>

    </body>
    <jsp:include page="includes/sidebarfoot.jsp"></jsp:include>
</html>
