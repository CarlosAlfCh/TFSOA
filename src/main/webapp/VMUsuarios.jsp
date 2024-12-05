<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.utp.entidad.Usuario"%>
<%@page import="com.utp.modelo.UsuarioDAO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios</title>        
    </head>
    <jsp:include page="includes/sidebarhead.jsp"></jsp:include>
        <div class="container-fluid">



            <!-- inicio Agregar Usuario -->

            <div class="card border-left-success shadow h-100 py-2 card shadow mb-4">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collap"
                   aria-expanded="true" aria-controls="collap">

                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-success font-weight-bold text-primary text-uppercase mb-1">
                                Agregar Usuario
                            </div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-plus fa-2x text-gray-300"></i>
                        </div>
                    </div>

                </a>

                <div id="collap" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">

                        <form action="Controlador?menu=usuario" method="POST">

                            <table class="table table-light"  width="100%" cellspacing="0">
                                <tr>
                                    <td>Nombres:<input class="form-control" type="text" name="txtnombres" required=""></td>
                                    <td>Apellido Paterno:<input class="form-control" type="text" name="txtapelpat" required=""></td>
                                    <td>Apellido Materno:<input class="form-control" type="text" name="txtapelmat" required=""></td>
                                    <td>DNI:<input class="form-control" type="text" name="txtdni" required=""></td>                                        
                                </tr>
                                <tr>
                                    <td>Correo:<input class="form-control" type="email" name="txtcorreo" required=""></td>
                                    <td>Telefono:<input class="form-control" type="text" name="txttelefono" required=""></td>
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
                                </tr>
                                <tr>
                                    <td>Especialidad:
                                        <select name="txtespecial" class="form-control"> 
                                            <option value="" selected>Seleccione Especialidad</option>
                                            <option value="2">Masajista</option>
                                            <option value="3">Esteticista</option>
                                            <option value="4">Terapeuta</option>
                                            <option value="5">Fisioterapeuta</option>
                                        </select>                                     
                                    </td>
                                    <td>Rol:
                                        <select name="txtrol" class="form-control"> 
                                            <option value="0" selected>Seleccione Rol</option>
                                            <option value="1">Administrador</option>
                                            <option value="2">Encargado</option>
                                            <option value="3">Tecnico</option>
                                        </select>                                     
                                    </td>
                                    <td>Turno:
                                        <select name="txtturno" class="form-control" required=""> 
                                            <option value="NULL" selected>Seleccione Turno</option>
                                            <option value="Mañana">Mañana</option>
                                            <option value="Tarde">Tarde</option>
                                            <option value="Noche">Noche</option>
                                        </select>
                                    </td>
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
                    <h6 class="m-0 font-weight-bold text-primary">Base de datos: Usuarios</h6>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>Codigo</th>
                                    <th>Nombres y Apellidos</th>
                                    <th>Dni</th>
                                    <th>Correo</th>
                                    <th>Telefono</th>
                                    <th>Direccion</th>
                                    <th>Estado</th>
                                    <th>Opciones</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="user" items="${usuarios}" varStatus="status">
                                <c:if test="${user.rol < 4}">
                                    <tr>
                                        <td>${user.codigo}</td>

                                        <td>${user.nombres} ${user.apelpat} ${user.apelmat}
                                            <div class="my-2"></div>
                                            <c:if test="${user.rol == 1}">                                                
                                                <span class="btn-sm btn-success">Administrador</span>                                                
                                            </c:if> 
                                            <c:if test="${user.rol == 2}">                                                
                                                <span class="btn-sm btn-secondary">Encargado</span>                                                
                                            </c:if> 
                                            <c:if test="${user.rol == 3}">                                                
                                                <span class="btn-sm btn-info">Tecnico</span>                                                
                                            </c:if>
                                        </td>
                                        <td>${user.dni}</td>
                                        <td>${user.correo}</td>
                                        <td>${user.telefono}</td>
                                        <td>${user.direccion}</td>

                                        <td>

                                            <c:if test="${user.estado == 1}">                        
                                                <span class="btn-sm btn-info">&nbsp;Activo&nbsp;</span>
                                            </c:if>
                                            <c:if test="${user.estado == 0}"> 
                                                <span class="btn-sm btn-danger">Inactivo</span>
                                            </c:if>                                           

                                        </td>
                                        <td>
                                            <a href="Controlador?menu=usuario&accion=seleciona&us=${user.codigo}" class="btn btn-warning btn-circle">
                                                <i class="fas fa-pencil-alt"></i>
                                            </a>
                                            <c:if test="${user.estado == 1}">  
                                                <a href="Controlador?menu=usuario&accion=desactivar&desactive=${user.codigo}" class="btn btn-secondary btn-circle">
                                                    <i class="fas fa-eraser"></i>
                                                </a>
                                            </c:if>
                                            <c:if test="${user.estado == 0}"> 
                                                <a href="Controlador?menu=usuario&accion=activar&active=${user.codigo}" class="btn btn-info btn-circle">
                                                    <i class="fas fa-recycle"></i>
                                                </a>
                                            </c:if>
                                            <a class="btn btn-danger btn-circle" data-toggle="modal" data-target="#modal${user.codigo}">
                                                <i class="fas fa-trash"></i>
                                            </a>
                                        </td>
                                    </tr>

                                    <!-- Modal para eliminar usuarios -->
                                <div class="modal fade" id="modal${user.codigo}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">¿Eliminar usuario?</h5>
                                                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">×</span>
                                                </button>
                                            </div>

                                            <div class="modal-body">
                                                <div class="card-header">
                                                    <label style="color: #4c3d3d">Se procedera a eliminar de manera permanente a: ${user.nombres} ${user.apelpat} ${user.apelmat}</label>
                                                </div>
                                            </div>

                                            <div class="card-footer">
                                                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                                                <a class="btn btn-success" href="Controlador?menu=usuario&accion=eliminar&delete=${user.codigo}">Eliminar</a>
                                            </div>  
                                            </form>
                                        </div>
                                    </div>
                                </div>

                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </div>
    <jsp:include page="includes/sidebarfoot.jsp"></jsp:include>
</html>
