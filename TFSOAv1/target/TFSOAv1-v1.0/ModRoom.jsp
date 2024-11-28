<%-- 
    Document   : ModRoom
    Created on : 14 oct. 2024, 21:00:05
    Author     : LENOVO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar</title>
    </head>
    <jsp:include page="includes/sidebarhead.jsp"></jsp:include>
        <body>
            <div class="container-fluid">
                <form action="Controlador?menu=room" method="POST">
                    <input type="hidden" name="txtid" value="${modif.getIdhabitacion()}">

                <div class="card border-left-warning shadow h-100 py-2">

                    <div class="nav-link" >

                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-success font-weight-bold text-warning text-uppercase mb-1">
                                    Modificando datos de la Habitacion</div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-user fa-2x text-gray-300"></i>
                            </div>
                        </div>

                    </div>

                    <table class="table table-light"  width="100%" cellspacing="0">
                        <tr>
                            <td>Dirección:<input class="form-control" type="text" name="txtdireccion" value="${modif.getDirHotel()}"></td>
                            <td>Distrito:
                                <select name="txtdistrito" class="form-control"> 
                                    <option value="${modif.getDisHotel()}" selected>Seleccione Distrito</option>
                                    <option value="1">Cayma</option>
                                    <option value="2">Cercado</option>
                                    <option value="3">Yanahuara</option>
                                    <option value="5">Selva Alegre</option>
                                    <option value="6">Mariano Melgar</option>
                                    <option value="8">Hunter</option>
                                    <option value="9">Cerro Colorado</option>
                                </select> 
                            </td>


                            <td>Precio por Noche:<input class="form-control" type="number" min="1" step="any" name="txtprecio" required="" value="${modif.getPrecioNoche()}"></td>
                        </tr>
                        <tr>
                            <td>Imagen:<input type="file" class="form-control" id="inputGroupFile01">                                           
                            </td>
                            <td>Piso:<input class="form-control" type="text" name="txtpiso" required="" value="${modif.getPiso()}"></td>

                            <td>Tipo de Habitacion:
                                <select name="txttipo" class="form-control" required=""> 
                                    <option value="${modif.getTipo()}" selected>${modif.getTipo()}</option>
                                    <option value="Normal">Normal</option>
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
                                <textarea class="form-control" name="txtdescripcion" rows="5" cols="10">${modif.getDescripcion()}</textarea>
                            </td>
                        </tr>
                        <tr>
                            <td align="center">
                                <button class="btn btn-warning btn-block text-uppercase mb-1 font-weight-bold" type="submit" name="accion" value="modificar">
                                    <span class="icon text-white-50">
                                        <i class="fas fa-wrench"></i>
                                    </span>
                                    <span class="text">Modificar</span>
                                </button>
                                <a href="Controlador?menu=room&accion=listar" class="btn btn-secondary btn-block text-uppercase mb-1 font-weight-bold">
                                    <span class="icon text-white-50">
                                        <i class="fas fa-arrow-right"></i>
                                    </span>
                                    <span class="text">Cancelar</span>    
                                </a>
                            </td>
                        </tr>
                    </table> 

                </div>            
            </form>
        </div>
    </body>
    <jsp:include page="includes/sidebarfoot.jsp"></jsp:include>
</html>
