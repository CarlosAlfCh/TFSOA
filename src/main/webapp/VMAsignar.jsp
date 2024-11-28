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
                                            ${r.nomTecnico} ${r.estado}
                                        </td>
                                        <td>
                                            <c:if test="${r.nomTecnico ne 'No asignado'}"> 
                                                <a href="ServletAsignar?accion=borrar&idreserva=${r.idreserva}" class="btn btn-secondary btn-circle">
                                                    <i class="fas fa-minus"></i>
                                                </a>
                                            </c:if> 
                                            <c:if test="${r.nomTecnico eq 'No asignado'}"> 
                                                <a class="btn btn-warning btn-circle" data-toggle="modal" data-target="#modal${r.idreserva}">
                                                    <i class="fas fa-clock"></i>
                                                </a>
                                            </c:if>
                                        </td>
                                    </tr>

                                    <!-- Generar cupon Modal-->

                                <div class="modal fade" id="modal${r.idreserva}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Asignar Tecnico a tratamiento</h5>
                                                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">×</span>
                                                </button>
                                            </div>
                                            <form action="ServletAsignar" method="post">
                                                <div class="modal-body">
                                                    <div class="card-header">
                                                        <label style="color: #4c3d3d">Seleccione tecnico para atender tratamiento de reserva ${r.idpago}</label>
                                                    </div>
                                                    <div class="card-body">
                                                        <!-- Campo de entrada para la búsqueda -->
                                                        <div class="input-group">
                                                            <input type="text" id="busqueda${r.idreserva}" class="form-control bg-light border-0 small" placeholder="Buscar..." aria-label="Search" aria-describedby="basic-addon2">
                                                            <div class="input-group-append">
                                                                <div class="btn btn-info">
                                                                    <i class="fas fa-search fa-sm"></i>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="my-2"></div>

                                                        <!-- ComboBox con opciones preestablecidas -->
                                                        <select id="resultadosCombo${r.idreserva}" class="form-control" size="2">
                                                            <c:forEach var="t" items="${tecnicos}" varStatus="status">
                                                                <c:if test="${t.rol == 3 && t.estado == 1}">
                                                                    <option value="1" data-idcliente="${t.codigo}" data-correo="${t.correo}">${t.nombres} ${t.apelpat} ${t.apelmat}</option>
                                                                </c:if>
                                                            </c:forEach>
                                                        </select>

                                                        <div class="my-2"></div>

                                                        <!-- Retorno de id a input -->
                                                        <div class="input-group">
                                                            <div class="input-group-prepend">
                                                                <div class="btn btn-secondary">ID</div>
                                                            </div>
                                                            <input type="text" id="idcliente${r.idreserva}" class="form-control bg-light border-0 small" placeholder="Id del cliente" name="txtidtecnico" readonly>
                                                        </div>
                                                        <div class="my-2"></div>

                                                        <!-- Retorno de correo a input -->
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
                                                </div>
                                                <div class="card-footer">
                                                    <button class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
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
                // Iterar sobre todos los modales
                $('div.modal').each(function () {
                    var modalId = $(this).attr('id');  // Obtener el id único del modal (ej. modal1, modal2, ...)
                    var idReserva = modalId.replace('modal', ''); // Extraer el idreserva de cada modal

                    // Obtener los campos dentro de este modal específico
                    var $busqueda = $('#busqueda' + idReserva);
                    var $comboBox = $('#resultadosCombo' + idReserva);
                    var $idCliente = $('#idcliente' + idReserva);
                    var $correo = $('#correo' + idReserva);

                    // Guardar las opciones originales en el ComboBox para restaurarlas luego si es necesario
                    var opcionesOriginales = $comboBox.find('option').clone();

                    // Evento para escuchar cada vez que el usuario escribe en el campo de búsqueda
                    $busqueda.on('keyup', function () {
                        var textoBusqueda = $(this).val().toLowerCase();  // Convertir a minúsculas para búsqueda insensible a mayúsculas

                        // Filtrar las opciones en base a la búsqueda
                        var opcionesFiltradas = opcionesOriginales.filter(function () {
                            return $(this).text().toLowerCase().includes(textoBusqueda);
                        });

                        // Limpiar el ComboBox y agregar las opciones filtradas
                        $comboBox.empty();

                        if (opcionesFiltradas.length === 0) {
                            // Si no hay coincidencias, mostrar un mensaje de "no hay resultados"
                            $comboBox.append('<option disabled>No hay coincidencias</option>');
                        } else {
                            // Agregar las opciones filtradas
                            $comboBox.append(opcionesFiltradas);
                        }
                    });

                    // Evento para escuchar cuando el usuario selecciona una opción en el ComboBox
                    $comboBox.on('change', function () {
                        var optionSeleccionada = $(this).find('option:selected');

                        // Obtener los datos de la opción seleccionada (idcliente y correo)
                        var idCliente = optionSeleccionada.data('idcliente');
                        var correo = optionSeleccionada.data('correo');

                        // Llenar los campos de idcliente y correo
                        $idCliente.val(idCliente);
                        $correo.val(correo);
                    });
                });
            });
        </script>


    </body>
    <jsp:include page="includes/sidebarfoot.jsp"></jsp:include>
</html>
