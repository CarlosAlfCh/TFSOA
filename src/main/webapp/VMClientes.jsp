<%-- 
    Document   : VMClientes
    Created on : 10 jun. 2023, 22:52:03
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.utp.entidad.Cliente"%>
<%@page import="com.utp.modelo.ClienteDAO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Clientes</title>
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
                                    Agregar Cliente
                                </div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-plus fa-2x text-gray-300"></i>
                            </div>
                        </div>
                        
                    </a>
                    
                    <div id="collap" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">

                            <form action="Controlador?menu=cliente" method="POST">
                                   
                                <table class="table table-light"  width="100%" cellspacing="0">
                                    <tr>
                                        <td>Nombres:<input class="form-control" type="text" name="txtnombres" required=""></td>
                                        <td>Apellido Paterno:<input class="form-control" type="text" name="txtapelpat" required=""></td>
                                        <td>Apellido Materno:<input class="form-control" type="text" name="txtapelmat" required=""></td>
                                        
                                    </tr>
                                    <tr>
                                        <td>DNI:<input class="form-control" type="text" name="txtdni" required=""></td>
                                        <td>Telefono:<input class="form-control" type="text" name="txttelefono" required=""></td>
                                        <td>Dirección:<input class="form-control" type="text" name="txtdireccion"></td>
                                        
                                    </tr>
                                    <tr>
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
                                        <td>Correo:
                                            <input class="form-control" type="email" name="txtcorreo" required="">
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
                    <h6 class="m-0 font-weight-bold text-primary">Base de datos: Clientes</h6>
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
                                <c:forEach var="cl" items="${clientes}" varStatus="status">
                                    <c:if test="${cl.rol == 4}"> 
                                    <tr>
                                        <td>${cl.codigo}</td>
                                        <td>${cl.nombres} ${cl.apelpat} ${cl.apelmat}</td>
                                        <td>${cl.dni}</td>
                                        <td>${cl.correo}</td>
                                        <td>${cl.telefono}</td>
                                        <td>${cl.direccion}</td>
                                        <td>
                                            <c:if test="${cl.estado == 1}">                        
                                                <span class="btn-sm btn-info">&nbsp;Activo&nbsp;</span>
                                            </c:if>
                                            <c:if test="${cl.estado == 0}"> 
                                                <span class="btn-sm btn-danger">Inactivo</span>
                                            </c:if>
                                        </td>
                                        <td>
                                            <a href="Controlador?menu=cliente&accion=seleciona&cli=${cl.codigo}" class="btn btn-warning btn-icon-split btn-sm">
                                                <span class="icon text-white-50">
                                                    <i class="fas fa-pencil-alt"></i>
                                                </span>
                                                <span class="text">Modificar</span>
                                            </a>
                                            <div class="my-2"></div>
                                            <c:if test="${cl.estado == 1}">  
                                                <a href="Controlador?menu=cliente&accion=eliminar&delete=${cl.codigo}" class="btn btn-danger btn-icon-split btn-sm">
                                                    <span class="icon text-white-50">
                                                        <i class="fas fa-eraser"></i>
                                                    </span>
                                                    <span class="text">Eliminar &nbsp;</span>
                                                </a>
                                            </c:if>
                                            <c:if test="${cl.estado == 0}"> 
                                                <a href="Controlador?menu=cliente&accion=activar&active=${cl.codigo}" class="btn btn-info btn-icon-split btn-sm">
                                                    <span class="icon text-white-50">
                                                        <i class="fas fa-recycle"></i>
                                                    </span>
                                                    <span class="text">Activar &nbsp;&nbsp;&nbsp;</span>
                                                </a>
                                            </c:if>
                                        </td>
                                    </tr>
                                    </c:if>
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
