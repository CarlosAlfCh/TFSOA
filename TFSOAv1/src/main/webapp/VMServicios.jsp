<%-- 
    Document   : VMServicios
    Created on : 10 jun. 2023, 22:52:13
    Author     : LENOVO
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Servicios</title>
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
                                    Agregar Servicio SPA
                                </div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-plus fa-2x text-gray-300"></i>
                            </div>
                        </div>

                    </a>

                    <div id="collap" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">

                            <form action="Controlador?menu=servicio" method="POST">

                                <table class="table table-light"  width="100%" cellspacing="0">
                                    <tr>
                                        <td>Nombre:<input class="form-control" type="text" name="txtnombre" required=""></td>
                                        <td>Duracion:<input class="form-control" type="time" name="txtduracion" required=""></td>
                                        <td>Precio:<input class="form-control" type="number" min="1" step="any" name="txtprecio" required=""></td>
                                    </tr>
                                    <tr>
                                        <td>Turno:
                                            <select name="txtturno" class="form-control" required=""> 
                                                <option value="NULL" selected>Seleccione Turno</option>
                                                <option value="mañana">Mañana</option>
                                                <option value="tarde">Tarde</option>
                                                <option value="noche">Noche</option>
                                            </select>
                                        </td>
                                        <td>Tipo:
                                            <select name="txtidtipo" class="form-control"> 
                                                <option value="0" selected>Seleccione tipo de servicio</option>
                                                <option value="1">Masaje</option>
                                                <option value="2">Exfoliación</option>
                                                <option value="3">Mascarilla</option>
                                                <option value="4">Terapia</option>
                                            </select>                                     
                                        </td> 
                                        <td>Especialidad:
                                            <select name="txtespecial" class="form-control"> 
                                                <option value="0" selected>Seleccione Especialidad</option>
                                                <option value="1">Masajista</option>
                                                <option value="2">Esteticista</option>
                                                <option value="3">Terapeuta</option>
                                                <option value="4">Fisioterapeuta</option>
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
                        <h6 class="m-0 font-weight-bold text-primary">Base de datos: Servicios SPA</h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th>Codigo</th>
                                        <th>Nombre</th>
                                        <th>Descripcion</th>
                                        <th>Turno</th>
                                        <th>Precio</th>
                                        <th>Duracion</th>
                                        <th>Estado</th>
                                        <th>Opciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="serv" items="${servicios}" varStatus="status">
                                    <tr>
                                        <td>${serv.idservicio}</td>
                                        <td>${serv.nomserv}</td>
                                        <td>${serv.descripcion}</td>
                                        <td>${serv.turno}</td>
                                        <td>${serv.precio}</td>
                                        <td>${serv.duracion}</td>
                                        <td>
                                            <c:if test="${serv.estadoserv == 1}">                        
                                                <span class="btn-sm btn-info">&nbsp;Activo&nbsp;</span>
                                            </c:if>
                                            <c:if test="${serv.estadoserv == 0}"> 
                                                <span class="btn-sm btn-danger">Inactivo</span>
                                            </c:if>
                                        </td>
                                        <td>
                                            <a href="Controlador?menu=servicio&accion=seleciona&ser=${serv.idservicio}" class="btn btn-warning btn-icon-split btn-sm">
                                                <span class="icon text-white-50">
                                                    <i class="fas fa-pencil-alt"></i>
                                                </span>
                                                <span class="text">Modificar</span>
                                            </a>
                                            <div class="my-2"></div>
                                            <c:if test="${serv.estadoserv == 1}">  
                                                <a href="Controlador?menu=servicio&accion=eliminar&delete=${serv.idservicio}" class="btn btn-danger btn-icon-split btn-sm">
                                                    <span class="icon text-white-50">
                                                        <i class="fas fa-eraser"></i>
                                                    </span>
                                                    <span class="text">Eliminar &nbsp;</span>
                                                </a>
                                            </c:if>
                                            <c:if test="${serv.estadoserv == 0}"> 
                                                <a href="Controlador?menu=servicio&accion=activar&active=${serv.idservicio}" class="btn btn-info btn-icon-split btn-sm">
                                                    <span class="icon text-white-50">
                                                        <i class="fas fa-recycle"></i>
                                                    </span>
                                                    <span class="text">Activar &nbsp;&nbsp;&nbsp;</span>
                                                </a>
                                            </c:if>
                                            -
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
