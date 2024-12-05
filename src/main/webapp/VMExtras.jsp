<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Especialidades</title>
    </head>
    <body>
        <jsp:include page="includes/sidebarhead.jsp"></jsp:include>

            <div class="container-fluid">


            <!-- inicio Agregar Especialidad -->

                <div class="card border-left-success shadow h-100 py-2 card shadow mb-4">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collap"
                       aria-expanded="true" aria-controls="collap">

                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-success font-weight-bold text-primary text-uppercase mb-1">
                                    Agregar Especialidad
                                </div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-plus fa-2x text-gray-300"></i>
                            </div>
                        </div>

                    </a>

                    <div id="collap" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">

                            <form action="ServletExtras?menu=especialidad" method="POST">

                                <table class="table table-light"  width="100%" cellspacing="0">
                                    <tr>
                                        <td>Nombre:<input class="form-control" type="text" name="txtnombre" required=""></td>
                                        <td>Salario Promedio:<input class="form-control" type="text" name="txtsalario" required=""></td>
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

            <!-- /fin agragar Especialidad -->  

            <!-- inicio Tabla Especialidad -->

                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Base de datos: Especialidad</h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Nombre</th>
                                        <th>Descripción</th>
                                        <th>Salario</th>
                                        <th>Ultimo cambio</th>
                                        <th>Opciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="e" items="${especiales}" varStatus="status">
                                    <tr>
                                        <td>${e.idespecialidad}</td>
                                        <td>${e.nombre}</td>
                                        <td>${e.descripcion}</td>
                                        <td>S/${e.salario}0</td>
                                        <td>${e.fechaactualizacion}</td>
                                        <td>

                                            <c:if test="${e.estado == 1}">  
                                                <a href="ServletExtras?menu=especialidad&accion=desactivar&id=${e.idespecialidad}" class="btn btn-secondary btn-circle">
                                                    <i class="fas fa-eraser"></i>
                                                </a>
                                            </c:if>

                                            <c:if test="${e.estado == 0}"> 
                                                <a href="ServletExtras?menu=especialidad&accion=activar&id=${e.idespecialidad}" class="btn btn-info btn-circle">
                                                    <i class="fas fa-recycle"></i>
                                                </a>
                                            </c:if>

                                            <a class="btn btn-warning btn-circle" data-toggle="modal" data-target="#modalmod${e.idespecialidad}">
                                                <i class="fas fa-pencil-alt"></i>
                                            </a>

                                            <a class="btn btn-danger btn-circle" data-toggle="modal" data-target="#modaldelete${e.idespecialidad}">
                                                <i class="fas fa-trash"></i>
                                            </a>
                                        </td>
                                    </tr>

                                    <!-- Modal para eliminar especialidad -->
                                <div class="modal fade" id="modaldelete${e.idespecialidad}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">¿Eliminar especialidad?</h5>
                                                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">×</span>
                                                </button>
                                            </div>

                                            <div class="modal-body">
                                                <div class="card-header">
                                                    <label style="color: #4c3d3d">Se procedera a eliminar de manera permanente la especialidad: ${e.nombre}</label>
                                                </div>
                                            </div>

                                            <div class="card-footer">
                                                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                                                <a class="btn btn-success" href="ServletExtras?menu=especialidad&accion=eliminar&id=${e.idespecialidad}">Eliminar</a>
                                            </div>  
                                        </div>
                                    </div>
                                </div>

                                <!-- Modal para modificar especialidad -->

                                <div class="modal fade" id="modalmod${e.idespecialidad}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
                                     aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">Modificar especialidad ${e.nombre}</h5>
                                                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">×</span>
                                                </button>
                                            </div>

                                            <form action="ServletExtras?menu=especialidad" method="post">                                    
                                                <div class="modal-body">
                                                    <div class="card-header">
                                                        <label style="color: #4c3d3d">
                                                            Edite sus cambios
                                                        </label>                                          
                                                    </div>

                                                    <div class="card-body">

                                                        <input type="hidden" name="id" value="${e.idespecialidad}">


                                                        <!-- Input de nombre especialidad -->
                                                        Nombre de la especialidad:
                                                        <div class="input-group">
                                                            <input type="text" class="form-control bg-light border-0 small" value="${e.nombre}"
                                                                   aria-label="Search" aria-describedby="basic-addon2" name="txtnombre">
                                                            <div class="input-group-addon">
                                                                <div class="btn btn-info">
                                                                    <i class=" fas fa-pencil-alt fa-sm"></i>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="my-2"></div>

                                                        <!-- Input de salario -->
                                                        Salario promedio:
                                                        <div class="input-group">
                                                            <input type="text" class="form-control bg-light border-0 small" value="${e.salario}"
                                                                   aria-label="Search" aria-describedby="basic-addon2" name="txtsalario">
                                                            <div class="input-group-addon">
                                                                <div class="btn btn-info">
                                                                    <i class=" fas fa-coins fa-sm"></i>
                                                                </div>
                                                            </div>
                                                        </div>

                                                        <div class="my-2"></div>

                                                        Descripción:
                                                        <textarea class="form-control" name="txtdescripcion" rows="5" cols="10">${e.descripcion}</textarea>

                                                    </div>
                                                </div>
                                                <div class="card-footer">

                                                    <button class="btn btn-secondary" data-dismiss="modal">Cancelar</button>

                                                    <button class="btn btn-success" type="submit" name="accion" value="modificar">Cambiar</button>

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

            <!-- /fin tabla Especialidad -->
        </div>

        <jsp:include page="includes/sidebarfoot.jsp"></jsp:include>
    </body>
</html>
