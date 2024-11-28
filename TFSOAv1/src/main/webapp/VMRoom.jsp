<%-- 
    Document   : VMRoom
    Created on : 14 oct. 2024, 20:59:45
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Habitaciones</title>
    </head>

    <jsp:include page="includes/sidebarhead.jsp"></jsp:include>
        <body>

            <!-- Begin Page Content -->
            <div class="container-fluid">



                <!-- inicio Agregar Cliente -->

                <div class="card border-left-success shadow h-100 py-2 card shadow mb-4">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collap"
                       aria-expanded="true" aria-controls="collap">

                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-success font-weight-bold text-primary text-uppercase mb-1">
                                    Agregar Habitaciones
                                </div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-plus fa-2x text-gray-300"></i>
                            </div>
                        </div>

                    </a>

                    <div id="collap" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">

                            <form action="Controlador?menu=room" method="POST">

                                <table class="table table-light"  width="100%" cellspacing="0">
                                    <tr>
                                        <td>Dirección:<input class="form-control" type="text" name="txtdireccion"></td>
                                        <td>Distrito:
                                            <select name="txtdistrito" class="form-control"> 
                                                <option value="NULL" selected>Seleccione Distrito</option>
                                                <option value="1">Cayma</option>
                                                <option value="2">Cercado</option>
                                                <option value="3">Yanahuara</option>
                                                <option value="5">Selva Alegre</option>
                                                <option value="6">Mariano Melgar</option>
                                                <option value="8">Hunter</option>
                                                <option value="9">Cerro Colorado</option>
                                            </select> 
                                        </td>


                                        <td>Precio por Noche:<input class="form-control" type="number" min="1" step="any" name="txtprecio" required=""></td>
                                    </tr>
                                    <tr>
                                        <td>Imagen:<input type="file" class="form-control" id="inputGroupFile01">                                           
                                        </td>
                                        <td>Piso:<input class="form-control" type="text" name="txtpiso" required=""></td>

                                        <td>Tipo de Habitacion:
                                            <select name="txttipo" class="form-control" required=""> 
                                                <option value="Normal" selected>Seleccione Tipo</option>
                                                <option value="Matrimonial">Matrimonial</option>
                                                <option value="Duplex">Duplex</option>
                                                <option value="Individual">Individual</option>
                                            </select>
                                        </td>
                                    </tr>

                                </table>
                                
                                <table class="table table-light"  width="100%" cellspacing="0">
                                    <tr>
                                        <td>
                                            Descripción:
                                            <textarea class="form-control" name="txtdescripcion" rows="5" cols="10"></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="center">
                                            <span class="text">&nbsp;</span>
                                            <button class="btn btn-success btn-block text-uppercase mb-1 font-weight-bold" type="submit" name="accion" value="agregar">
                                                <span class="text">Agregar</span>
                                            </button>
                                        </td>
                                    </tr>
                                </table>
                            </form>    

                        </div>
                    </div>

                </div> 

                <!-- /fin agragar cliente -->  

                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Base de datos: Habtaciones</h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th>Codigo</th>
                                        <th>Tipo</th>
                                        <th>Detalle de la habitación</th>
                                        <th>Precio</th>
                                        <th>Estado</th>
                                        <th>Opciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="r" items="${rooms}" varStatus="status">
                                    <tr>
                                        <td>${r.idhabitacion}D${r.disHotel}P${r.piso}</td>
                                        <td>${r.tipo}</td>
                                        <td>Piso: ${r.piso} </br> Dirección: ${r.dirHotel} </br> ${r.descripcion}</td>
                                        <td>${r.precioNoche}</td>
                                        <td>
                                            <div class="dropdown mb-4">
                                                <button type="button"
                                                        id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                                        aria-expanded="false" 
                                                    <c:if test="${r.estado == 1}">                        
                                                        class="btn btn-success dropdown-toggle">
                                                        Disponible &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    </c:if>
                                                    <c:if test="${r.estado == 2}"> 
                                                        class="btn btn-warning dropdown-toggle" >
                                                        Mantenimiento &nbsp;
                                                    </c:if>
                                                    <c:if test="${r.estado == 3}">
                                                        class="btn btn-secondary dropdown-toggle">
                                                        Ocupado &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    </c:if>
                                                </button>
                                                <div class="dropdown-menu animated--fade-in"
                                                     aria-labelledby="dropdownMenuButton">
                                                    <a class="dropdown-item" href="Controlador?menu=room&accion=cambio&id=${r.idhabitacion}&estado=1">Disponible</a>
                                                    <a class="dropdown-item" href="Controlador?menu=room&accion=cambio&id=${r.idhabitacion}&estado=2">Mantenimiento</a>
                                                    <a class="dropdown-item" href="Controlador?menu=room&accion=cambio&id=${r.idhabitacion}&estado=3">Ocupado</a>
                                                </div>
                                            </div>
                                        </td>
                                        <td>
                                            <a href="Controlador?menu=room&accion=seleciona&ser=${r.idhabitacion}" class="btn btn-warning btn-icon-split btn-sm">
                                                <span class="icon text-white-50">
                                                    <i class="fas fa-pencil-alt"></i>
                                                </span>
                                                <span class="text">Modificar</span>
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
        <!-- /.container-fluid -->

    </body>
    <jsp:include page="includes/sidebarfoot.jsp"></jsp:include>

</html>
