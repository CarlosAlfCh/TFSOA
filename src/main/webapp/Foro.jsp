<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            .comment-card {
                margin-bottom: 1rem;
                transition: transform 0.2s;
            }
            .comment-card:hover {
                transform: translateY(-2px);
            }
            .rating-select {
                display: flex;
                flex-direction: row;
                justify-content: flex-start;
                gap: 5px;
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
            /* Estilos para el botón flotante */
            .floating-button {
                position: fixed;
                bottom: 2rem;
                right: 2rem;
                z-index: 1000;
                box-shadow: 0 4px 12px rgba(0,0,0,0.15);
                border-radius: 50%;
                width: 60px;
                height: 60px;
                display: flex;
                align-items: center;
                justify-content: center;
                transition: transform 0.3s, box-shadow 0.3s;
            }
            .floating-button:hover {
                transform: translateY(-3px);
                box-shadow: 0 6px 16px rgba(0,0,0,0.2);
            }
            .floating-button i {
                font-size: 1.5rem;
            }
        </style>
    </head>
    <%@include file="includes/header.jsp"%>
    <body>
        <%@include file="includes/navbar.jsp"%>
        <hr class="sidebar-divider">

        <div class="container">

            <div class="container py-5">
                <h2 class="mb-4">Comentarios de nuestros clientes usuarios</h2>

                <!-- Lista de comentarios -->
                <div class="comments-list">

                    <!-- Comentaris -->
                    <c:forEach var="co" items="${comentarios}" varStatus="status">
                        <div class="card comment-card">
                            <div class="card-body">
                                <div class="d-flex justify-content-between align-items-center mb-2">
                                    <h5 class="card-title mb-0">${co.cliente}</h5>
                                    <div class="star-rating">
                                        <c:forEach begin="1" end="5" var="i">
                                            <i class="fas fa-star ${i <= co.nestrellas ? 'checked' : ''}"></i>
                                        </c:forEach>
                                    </div>
                                </div>
                                <p class="card-text">${co.comentario}</p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <!-- Botón flotante -->
            <button class="btn btn-info floating-button" data-bs-toggle="modal" data-bs-target="#addCommentModal">
                <i class="fas fa-plus"></i>
            </button>

            <!-- Modal para agregar comentario -->
            <div class="modal fade" id="addCommentModal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Agregar nuevo comentario</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <form action="ServletServiceCliente" method="post">
                            <div class="modal-body">
                                <input type="hidden" name="txtidcliente" value="${cliente.getCodigo()}">

                                <div class="mb-3">
                                    <label class="form-label" style="color: #4c3d3d" >Calificación</label>
                                    <div class="rating-select" style="color: #4c3d3d">
                                        <i class="fas fa-star" data-rating="1"></i>
                                        <i class="fas fa-star" data-rating="2"></i>
                                        <i class="fas fa-star" data-rating="3"></i>
                                        <i class="fas fa-star" data-rating="4"></i>
                                        <i class="fas fa-star" data-rating="5"></i>
                                    </div>
                                    <input type="hidden" id="rating" name="txtestrellas" required>
                                </div>
                                <div class="mb-3">
                                    <label for="commentText" class="form-label" style="color: #4c3d3d">Comentario</label>
                                    <textarea class="form-control" name="txtcomentario" rows="3" required></textarea>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                <button type="submit" class="btn btn-primary" name="accion" value="comentar">Publicar comentario</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.2/js/bootstrap.bundle.min.js"></script>
        <script>
// Manejo de la selección de estrellas
            document.querySelectorAll('.rating-select .fa-star').forEach(star => {
                // Evento al pasar el mouse
                star.addEventListener('mouseover', function () {
                    const rating = this.dataset.rating;
                    highlightStars(rating, 'hover');
                });

                // Evento al quitar el mouse
                star.addEventListener('mouseout', function () {
                    const selectedRating = document.querySelector('input[name="txtestrellas"]').value;
                    removeHighlight('hover');
                    if (selectedRating) {
                        highlightStars(selectedRating, 'checked');
                    }
                });

                // Evento al hacer clic
                star.addEventListener('click', function () {
                    const rating = this.dataset.rating;
                    document.querySelector('input[name="txtestrellas"]').value = rating;
                    removeHighlight('checked');
                    highlightStars(rating, 'checked');
                });
            });

// Función para resaltar estrellas
            function highlightStars(rating, className) {
                const stars = document.querySelectorAll('.rating-select .fa-star');
                for (let i = 0; i < stars.length; i++) {
                    if (i < rating) {
                        stars[i].classList.add(className);
                    } else {
                        stars[i].classList.remove(className);
                    }
                }
            }

// Función para quitar el resaltado
            function removeHighlight(className) {
                document.querySelectorAll('.rating-select .fa-star').forEach(star => {
                    star.classList.remove(className);
                });
            }

// Validación del formulario
            document.querySelector('form[action="ServletServiceCliente"]').addEventListener('submit', function (e) {
                const estrellas = document.querySelector('input[name="txtestrellas"]').value;
                const comentario = document.querySelector('textarea[name="txtcomentario"]').value;

                if (!estrellas || !comentario) {
                    e.preventDefault();
                    alert('Por favor, complete todos los campos requeridos');
                }
            });
        </script>

        <%@include file="includes/footer.jsp"%>
    </body>
</html>
