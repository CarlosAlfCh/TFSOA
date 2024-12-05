<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comentarios</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
        <style>
            .star-rating .fa-star.checked {
                color: #ffd700;
            }
            .star-rating .fa-star {
                color: #e4e5e9;
            }
            .rating-select .fa-star {
                cursor: pointer;
                font-size: 1.5em;
                color: #e4e5e9;
            }

            .rating-select .fa-star.checked,
            .rating-select .fa-star.hover {
                color: #ffd700;
            }
        </style>
    </head>
    <body>
        <jsp:include page="includes/sidebarhead.jsp"></jsp:include>

            <div class="container-fluid">

                <!-- inicio Tabla Especialidad -->

                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Comentarios</h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                    <tr>
                                        <th>ID Cliente</th>
                                        <th>Nombre Cliente</th>
                                        <th>Comentario</th>
                                        <th>N° Estrellas</th>
                                        <th>Opciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="e" items="${comentarios}" varStatus="status">
                                    <tr>
                                        <td>${e.idPersona}</td>
                                        <td>${e.cliente}</td>
                                        <td>${e.comentario}</td>
                                        <td>

                                            <div class="star-rating">
                                                <c:forEach begin="1" end="5" var="i">
                                                    <i class="fas fa-star ${i <= e.nestrellas ? 'checked' : ''}"></i>
                                                </c:forEach>
                                            </div>

                                        </td>
                                        <td>
                                            <a class="btn btn-danger btn-circle" data-toggle="modal" data-target="#modaldelete${e.id}">
                                                <i class="fas fa-trash"></i>
                                            </a>
                                        </td>
                                    </tr>

                                    <!-- Modal para eliminar comentario -->
                                <div class="modal fade" id="modaldelete${e.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel">¿Eliminar comentario?</h5>
                                                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">×</span>
                                                </button>
                                            </div>

                                            <div class="modal-body">
                                                <div class="card-header">
                                                    <label style="color: #4c3d3d">Se procedera a eliminar el comentario de ${e.cliente}</label>
                                                </div>
                                            </div>

                                            <div class="card-footer">
                                                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancelar</button>
                                                <a class="btn btn-success" href="ServletExtras?menu=comentarios&accion=eliminar&id=${e.id}">Eliminar</a>
                                            </div>  
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
