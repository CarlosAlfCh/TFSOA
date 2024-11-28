<%-- 
    Document   : VMInicio
    Created on : 7 oct. 2024, 10:34:59
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.utp.entidad.Usuario"%>
<%@page import="com.utp.entidad.Servicio"%>
<%@page import="com.utp.modelo.UsuarioDAO"%>
<%@page import="com.utp.modelo.ServicioDAO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>        
    </head>
    
    <jsp:include page="includes/sidebarhead.jsp"></jsp:include>
    
    <body>

        <!-- Begin Page Content -->
        
        <div class="container-fluid">

                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">Bienvenido ${usuario.getNombres()}</h1>

                    <c:if test="${usuario.getRol() != 3}"> 
                    
                        <a href="#" class="btn btn-success btn-icon-split">
                            <span class="icon text-white-50">
                                <i class="fas fa-cart-arrow-down"></i>
                            </span>
                            <span class="text">Registrar reserva</span>
                        </a>
                        
                    </c:if>

                </div>
            
            <c:if test="${usuario.getRol() != 3}"> 
            
            <!-- Content Dashboard -->
            
            <div class="row">

                <!-- Earnings (Monthly) Card Example -->
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-left-primary shadow h-100 py-2">
                        <div class="card-body">
                            <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                    <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                        Ganancias (Mensual)</div>
                                    <div class="h5 mb-0 font-weight-bold text-gray-800">S/ 4,000</div>
                                </div>
                                <div class="col-auto">
                                    <i class="fas fa-calendar fa-2x text-gray-300"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Earnings (Monthly) Card Example -->
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-left-success shadow h-100 py-2">
                        <div class="card-body">
                            <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                    <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                        Ganacias (Anual)</div>
                                    <div class="h5 mb-0 font-weight-bold text-gray-800">S/ 215,000</div>
                                </div>
                                <div class="col-auto">
                                    <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Earnings (Monthly) Card Example -->
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-left-info shadow h-100 py-2">
                        <div class="card-body">
                            <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                    <div class="text-xs font-weight-bold text-info text-uppercase mb-1">% Habitaciones ocupadas
                                    </div>
                                    <div class="row no-gutters align-items-center">
                                        <div class="col-auto">
                                            <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">50%</div>
                                        </div>
                                        <div class="col">
                                            <div class="progress progress-sm mr-2">
                                                <div class="progress-bar bg-info" role="progressbar"
                                                     style="width: 50%" aria-valuenow="50" aria-valuemin="0"
                                                     aria-valuemax="100"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-auto">
                                    <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Pending Requests Card Example -->
                <div class="col-xl-3 col-md-6 mb-4">
                    <div class="card border-left-warning shadow h-100 py-2">
                        <div class="card-body">
                            <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                    <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                        N° Personal asignado</div>
                                    <div class="h5 mb-0 font-weight-bold text-gray-800">18</div>
                                </div>
                                <div class="col-auto">
                                    <i class="fas fa-comments fa-2x text-gray-300"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <c:if test="${usuario.getRol() == 1}"> 

            <div class="row">

                <!-- Area Grafico puntos -->
                <div class="col-xl-8 ">
                    <div class="card shadow mb-4">
                        
                        <!-- Card Header -->
                        <div
                            class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                            
                            <h6 class="m-0 font-weight-bold text-primary">Ganancias</h6>   
                            
                        </div>
                        
                        <!-- Grafico puntos -->
                        
                        <div class="card-body">
                            <div class="chart-area">
                                <canvas id="myAreaChart"></canvas>
                            </div>
                        </div>
                        
                    </div>
                </div>

                <!-- Area grafico circular-->
                <div class="col-xl-4 ">
                    <div class="card shadow mb-4">
                        <!-- Card Header - Dropdown -->
                        <div
                            class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                            
                            <h6 class="m-0 font-weight-bold text-primary">Metodos de pago</h6>
                            
                        </div>
                        <!-- Card Body -->
                        <div class="card-body">
                            
                            <!-- Grafico circular -->
                            <div class="chart-pie pt-4 pb-2">
                                <canvas id="myPieChart"></canvas>
                            </div>
                            
                            <div class="mt-4 text-center small">
                                <span class="mr-2">
                                    <i class="fas fa-circle text-primary"></i> Efectivo
                                </span>
                                <span class="mr-2">
                                    <i class="fas fa-circle text-success"></i> Yape
                                </span>
                                <span class="mr-2">
                                    <i class="fas fa-circle text-info"></i> Plin
                                </span>
                            </div>
                        </div>
                        
                    </div>
                </div>
                
            </div>            
            
            </c:if>
            
            </c:if>
            
            <!-- /Fin Dashboard -->
            
            <!-- inicio tabla -->
            
                <div class="card border-left-primary shadow h-100 py-2 card shadow mb-4">
                    <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collap"
                       aria-expanded="true" aria-controls="collap">
                        <h6 class="m-0 font-weight-bold text-primary">Consultar Usuarios/Clientes</h6>                            
                    </a>
                    <div id="collap" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                        <div class="bg-white py-2 collapse-inner rounded">

                            

                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                            <thead>
                                                <tr>
                                                    <th>Codigo</th>
                                                    <th>Nombres</th>
                                                    <th>Apellidos</th>
                                                    <th>Dni</th>
                                                    <th>Correo</th>
                                                    <th>Rol</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%  UsuarioDAO dao = new UsuarioDAO();
                                                    List<Usuario> lista = dao.listar();
                                                    if (lista != null) {
                                                        for (Usuario user : lista) {

                                                %>
                                                <tr>
                                                    <td><%= user.getCodigo()%></td>
                                                    <td><%= user.getNombres()%></td>
                                                    <td><%= user.getApelpat()%> <%= user.getApelmat()%></td>
                                                    <td><%= user.getDni()%></td>
                                                    <td><%= user.getCorreo()%></td>
                                                    <td><%if (user.getRol() == 1) {%>                        
                                                        Administrador
                                                        <%} else {
                                                            if (user.getRol() == 2) {%>
                                                        Encargado
                                                        <%  } else {
                                                            if (user.getRol() == 3) {%>
                                                        Tecnico
                                                        <%      } else {%>
                                                        Cliente
                                                        <%
                                                                    }
                                                                }
                                                            }
                                                        %>
                                                    </td>
                                                </tr>
                                                <%
                                                        }
                                                    }
                                                %>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                             

                        </div>
                    </div>
                </div> 
                                            
            <!-- /fin tabla -->            
            
        </div>
        
        <!-- /.container-fluid -->

    </body>
    
    <jsp:include page="includes/sidebarfoot.jsp"></jsp:include>
    
</html>
