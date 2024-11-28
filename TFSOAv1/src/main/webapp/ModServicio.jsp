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
                <form action="Controlador?menu=servicio" method="POST">
                    <input type="hidden" name="txtid" value="${modif.getIdservicio()}">

                <div class="card border-left-warning shadow h-100 py-2">

                    <div class="nav-link" >

                        <div class="row no-gutters align-items-center">
                            <div class="col mr-2">
                                <div class="text-success font-weight-bold text-warning text-uppercase mb-1">
                                    Modificando datos del Servicio de SPA</div>
                            </div>
                            <div class="col-auto">
                                <i class="fas fa-user fa-2x text-gray-300"></i>
                            </div>
                        </div>

                    </div>

                    <table class="table table-light"  width="100%" cellspacing="0">
                        <tr>
                            <td>Nombre:<input class="form-control" type="text" name="txtnombre" required="" value="${modif.getNomserv()}"></td>
                            <td>Duracion:<input class="form-control" type="time" name="txtduracion" required=""></td>
                            <td>Precio:<input class="form-control" type="number" min="1" step="any" name="txtprecio" required="" value="${modif.getPrecio()}"></td>
                        </tr>
                        <tr>
                            <td>Turno:
                                <select name="txtturno" class="form-control" required=""> 
                                    <option value="${modif.getTurno()}" selected>${modif.getTurno()}</option>
                                    <option value="mañana">Mañana</option>
                                    <option value="tarde">Tarde</option>
                                    <option value="noche">Noche</option>
                                </select>
                            </td>
                            <td>Tipo:
                                <select name="txtidtipo" class="form-control"> 
                                    <option value="${modif.getIdtipo()}" selected>Seleccione tipo de servicio</option>
                                    <option value="1">Masaje</option>
                                    <option value="2">Exfoliación</option>
                                    <option value="3">Mascarilla</option>
                                    <option value="4">Terapia</option>
                                </select>                                     
                            </td> 
                            <td>Especialidad:
                                <select name="txtespecial" class="form-control"> 
                                    <option value="${modif.getId_especialidad()}" selected>Seleccione Especialidad</option>
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
                                <a href="Controlador?menu=servicio&accion=listar" class="btn btn-secondary btn-block text-uppercase mb-1 font-weight-bold">
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
