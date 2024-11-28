<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Promociones</title>
        <!-- Incluir jQuery desde una CDN -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </head>
    <jsp:include page="includes/sidebarhead.jsp"></jsp:include>
        <body>

            <!-- Begin Page Content -->
            <div class="container-fluid">


            <!-- Page Heading -->
            <div class="d-sm-flex align-items-center justify-content-between mb-4">
                <h3 class="m-0 font-weight-bold text-primary">Enviar codigos promocionales</h3>

                <a class="btn btn-success btn-icon-split" data-toggle="modal" data-target="#modalpromo" >
                    <span class="icon text-white-50">
                        <i class="fas fa-percent fa-sm"></i>
                    </span>
                    <span class="text">Generar codigo</span>
                </a>

            </div>


            <div class="card shadow mb-4">
                <div class="card-header py-3">
                    <h6 class="m-0 font-weight-bold text-primary">Base de datos: Clientes</h6>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>Nombres y Apellidos</th>
                                    <th>Fecha Emisión - Expiración</th>
                                    <th>Fecha de uso</th>
                                    <th>% Descuento</th>
                                    <th>Estado</th>
                                    <th>Opciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="promo" items="${promocionesxcliente}" varStatus="status">
                                    <tr>
                                        <td>${promo.nomCliente}</td>
                                        <td>${promo.fGeneracion} - ${promo.fExpiracion}</td>
                                        <td>${promo.fUso}</td>
                                        <td>${promo.descuento}%</td>
                                        <td>
                                            <c:if test="${promo.estado == 1}">                        
                                                <span class="btn-sm btn-info">Activo</span>
                                            </c:if>
                                            <c:if test="${promo.estado == 0}"> 
                                                <span class="btn-sm btn-danger">Inactivo</span>
                                            </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${promo.estado == 0}">  
                                                <a data-toggle="modal" data-target="#modal${promo.idcliente}" class="btn btn-info btn-icon-split btn-sm">
                                                    <span class="icon text-white-50">
                                                        <i class="fas fa-recycle"></i>
                                                    </span>
                                                    <span class="text">Renovar</span>
                                                </a>
                                                <a href="ServletTarifas?accion=invalidar&idcliente=${promo.idcliente}" class="btn btn-danger btn-icon-split btn-sm">
                                                    <span class="icon text-white-50">
                                                        <i class="fas fa-eraser"></i>
                                                    </span>
                                                    <span class="text">Revocar</span>
                                                </a>
                                            </c:if>
                                            <c:if test="${promo.estado == 1}"> 
                                                <a href="ServletTarifas?accion=invalidar&idcliente=${promo.idcliente}" class="btn btn-danger btn-icon-split btn-sm">
                                                    <span class="icon text-white-50">
                                                        <i class="fas fa-eraser"></i>
                                                    </span>
                                                    <span class="text">Revocar</span>
                                                </a>
                                            </c:if>
                                        </td>
                                    </tr>

                                    <!-- Revocar Modal-->
                                <div class="modal fade" id="modal${promo.idcliente}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                                     aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Renovar codigo promocional</h5>
                                                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">×</span>
                                                </button>
                                            </div>
                                            <form action="ServletTarifas" method="post">                                    
                                                <div class="modal-body">
                                                    <div class="card-header">
                                                        <label style="color: #4c3d3d">
                                                            Cliente: ${promo.nomCliente}<br> 
                                                            Correo: ${promo.correo}
                                                        </label>                                          
                                                    </div>
                                                    <div class="card-body">
                                                        <input type="hidden" name="txtidcliente" value="${promo.idcliente}">
                                                        <input type="hidden" name="txtcorreo" value="${promo.correo}">

                                                        <div class="input-group">
                                                            <input type="text" class="form-control bg-light border-0 small" placeholder="Ingrese descuento..."
                                                                   aria-label="Search" aria-describedby="basic-addon2" name="txtdescuento">
                                                            <div class="input-group-addon">
                                                                <div class="btn btn-info">
                                                                    <i class=" fas fa-percent fa-sm"></i>
                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>
                                                <div class="card-footer">
                                                    <button class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                                    <button class="btn btn-primary" name="accion" value="actualizar">Enviar codigo promocional</button>
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




            <!-- Generar cupon Modal-->

            <div class="modal fade" id="modalpromo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Generar codigo promocional</h5>
                            <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">×</span>
                            </button>
                        </div>

                        <form action="ServletTarifas" method="post">                                    
                            <div class="modal-body">
                                <div class="card-header">
                                    <label style="color: #4c3d3d">
                                        Seleccione cliente para enviar codigo promocional
                                    </label>                                          
                                </div>

                                <div class="card-body">
                                    
                                    <!-- Campo de entrada para la búsqueda -->

                                    <div class="input-group">
                                        <input type="text" id="busqueda" class="form-control bg-light border-0 small" placeholder="Buscar..."
                                               aria-label="Search" aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <div class="btn btn-info">
                                                <i class=" fas fa-search fa-sm"></i>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="my-2"></div>

                                    <!-- ComboBox con opciones preestablecidas -->
                                    <select id="resultadosCombo" class="form-control" size="2">
                                    <c:forEach var="cl" items="${clientes}" varStatus="status">
                                        <c:if test="${cl.rol == 4 && cl.estado == 1}">
                                            <option value="1" data-idcliente="${cl.codigo}" data-correo="${cl.correo}">${cl.nombres} ${cl.apelpat} ${cl.apelmat}</option>
                                        </c:if>
                                    </c:forEach>
                                    </select>
                                    <div class="my-2"></div>
                                    
                                    <!-- Retorno de id a input -->
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <div class="btn btn-secondary">
                                                ID
                                            </div>
                                        </div>
                                        <input type="text" id="idcliente" class="form-control bg-light border-0 small" placeholder="Id del cliente"
                                               aria-label="Search" aria-describedby="basic-addon2" name="txtidcliente" readonly>
                                    </div>
                                    <div class="my-2"></div>
                                    <!-- Retorno de correo a input -->
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <div class="btn btn-secondary">
                                                <i class=" fas fa-envelope fa-sm"></i>
                                            </div>
                                        </div>
                                        <input type="email" id="correo" class="form-control bg-light border-0 small" placeholder="Correo"
                                               aria-label="Search" aria-describedby="basic-addon2" name="txtcorreo" readonly>
                                    </div>
                                    <div class="my-2"></div>
                                    
                                    <!-- Input de descuento -->
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small" placeholder="Ingrese descuento..."
                                               aria-label="Search" aria-describedby="basic-addon2" name="txtdescuento">
                                        <div class="input-group-addon">
                                            <div class="btn btn-info">
                                                <i class=" fas fa-percent fa-sm"></i>
                                            </div>
                                        </div>
                                    </div>

                                    

                                </div>
                            </div>
                            <div class="card-footer">
                                
                                <button class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                
                                <button class="btn btn-success" type="submit" name="accion" value="agregar">Enviar Promoción</button>
                                
                            </div>  
                        </form>
                    
                    </div>
                </div>
            </div>

        </div>

        <script>
            $(document).ready(function () {
                // Obtener el campo de entrada y el ComboBox
                var $busqueda = $('#busqueda');
                var $comboBox = $('#resultadosCombo');
                var $idCliente = $('#idcliente');
                var $correo = $('#correo');

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
                    $comboBox.empty().append(opcionesFiltradas);

                    // Mostrar un mensaje cuando no haya coincidencias
                    if (opcionesFiltradas.length === 0) {
                        $comboBox.append('<option disabled>No hay coincidencias</option>');
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
        </script>

    </body>
    <jsp:include page="includes/sidebarfoot.jsp"></jsp:include>
</html>
