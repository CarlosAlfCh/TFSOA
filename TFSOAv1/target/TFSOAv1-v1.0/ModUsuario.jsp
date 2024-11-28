<%-- 
    Document   : ModUsuario
    Created on : 21 jun. 2023, 16:05:17
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar</title>
    </head>
    <jsp:include page="includes/sidebarhead.jsp"></jsp:include>
    <body>
        <!-- Begin Page Content -->
        <div class="container-fluid">
            <form action="Controlador?menu=usuario" method="POST">
                <input type="hidden" name="txtcodigo" value="${modif.getCodigo()}">
            
                <div class="card border-left-warning shadow h-100 py-2">
                        
                        <div class="nav-link" >
                            
                            <div class="row no-gutters align-items-center">
                                <div class="col mr-2">
                                    <div class="text-success font-weight-bold text-warning text-uppercase mb-1">
                                        Modificando datos del Usuario</div>
                                </div>
                                <div class="col-auto">
                                    <i class="fas fa-user fa-2x text-gray-300"></i>
                                </div>
                            </div>
                                                     
                        </div>

                        <table class="table table-light" width="100%" cellspacing="0">
                            <tr>
                                <td>Nombres:<input class="form-control" type="text" name="txtnombres" required="" value="${modif.getNombres()}"></td>
                                <td>Apellido Paterno:<input class="form-control" type="text" name="txtapelpat" required="" value="${modif.getApelpat()}"></td>
                                <td>Apellido Materno:<input class="form-control" type="text" name="txtapelmat" required="" value="${modif.getApelmat()}"></td>
                                <td>DNI:<input class="form-control" type="text" name="txtdni" required="" value="${modif.getDni()}"></td>
                            </tr>
                            <tr>
                                <td>Telefono:<input class="form-control" type="tel" name="txttelefono" required="" value="${modif.getTelefono()}"></td>
                                <td>Correo:<input class="form-control" type="email" name="txtcorreo" required="" value="${modif.getCorreo()}"></td>     
                                <td>Dirección:<input class="form-control" type="text" name="txtdireccion" required="" value="${modif.getDireccion()}"></td>
                                <td>Distrito:
                                    <select name="txtdistrito" class="form-control"> 
                                        <option value="${modif.getDistrito()}" selected>¿Cambiar?</option>
                                        <option value="1">Cayma</option>
                                        <option value="2">Cercado</option>
                                        <option value="3">Yanahuara</option>
                                        <option value="4">Selva Alegre</option>
                                        <option value="5">Mariano Melgar</option>
                                        <option value="6">Hunter</option>
                                        <option value="7">Cerro Colorado</option>
                                    </select> 
                                </td>
                            </tr>
                            <tr>
                                <td>Turno:
                                    <select name="txtturno" class="form-control" required=""> 
                                        <option value="${modif.getTurno()}" selected>${modif.getTurno()}</option>
                                        <option value="Mañana">Mañana</option>
                                        <option value="Tarde">Tarde</option>
                                        <option value="Noche">Noche</option>
                                    </select>
                                </td> 
                                <td>Rol:
                                    <select name="txtrol" class="form-control"> 
                                        <option value="${modif.getRol()}" selected>Seleccione Rol</option>
                                        <option value="1">Administrador</option>
                                        <option value="2">Encargado</option>
                                        <option value="3">Tecnico</option>
                                    </select>                                     
                                </td>
                                <td>Especialidad:
                                    <select name="txtespecial" class="form-control"> 
                                        <option value="${modif.getIdespecialidad()}" selected>Seleccione Especialidad ${modif.getIdespecialidad()}</option>
                                        <option value="1">Masajista</option>
                                        <option value="2">Esteticista</option>
                                        <option value="3">Terapeuta</option>
                                        <option value="4">Fisioterapeuta</option>
                                    </select>                                     
                                </td>
                                <td>
                                    <span class="text">&nbsp;</span>
                                    <button class="btn btn-warning btn-block text-uppercase mb-1 font-weight-bold" type="submit" name="accion" value="modificar">
                                        <span class="text-">Modificar</span>
                                    </button>
                                    <div class="my-2"></div>
                                    <a href="Controlador?menu=usuario&accion=listar" class="btn btn-secondary btn-block text-uppercase mb-1 font-weight-bold">
                                            <span class="text">Cancelar</span>
                                    </a>                                    
                                </td>
                            </tr>
                        </table>   

                    </div>            
            </form>
        </div>
        <!-- /.container-fluid -->
    </body>
    <jsp:include page="includes/sidebarfoot.jsp"></jsp:include>
</html>
