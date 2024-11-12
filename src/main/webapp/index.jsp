<%-- 
    Document   : index
    Created on : 10 jun. 2023, 10:27:23
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.utp.entidad.Servicio"%>
<%@page import="com.utp.entidad.Habitacion"%>
<%@page import="com.utp.modelo.ServicioDAO"%>
<%@page import="com.utp.modelo.HabitacionDAO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Relax&Wellness</title>
        <style>
            .image-container {
                height: 200px;
                overflow: hidden;
            }

            .image-container img {
                width: 100%;
                height: 100%;
                object-fit: cover;
            }

            /* Ajustes responsivos */
            @media (max-width: 767.98px) {
                .card-margin-1,
                .card-margin-2,
                .card-margin-3,
                .card-margin-4,
                .card-margin-5 {
                    margin-bottom: 1.5rem; /* Margen uniforme en móviles */
                }
            }
        </style>
    </head>
    <jsp:include page="includes/header.jsp"></jsp:include>
        <body>        
            <!-- Page Header Start --> 
        <c:if test="${cliente.estado != null}"> 
            <jsp:include page="includes/navbar.jsp"></jsp:include>
        </c:if>    
        <!-- Page Header End -->    

        <!-- Carrousel Nav Bar Start -->
        <c:if test="${cliente.estado == null}">
            <div id="carousel" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carousel" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel" data-slide-to="1"></li>
                    <li data-target="#carousel" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="src/img/recep.png" alt="Carousel Image">
                        <div class="carousel-caption">
                            <h1 class="animated fadeInLeft">Relax & Wellness</h1>
                            <p class="animated fadeInRight">Hotel y Spa</p>
                            <!--  <ul style="list-style-type:none;">
                                <li class="nav-item nav-link active" style="float:left;"><a class="btn animated fadeInUp" href="Vistas/atencion.jsp" >Consulte aqui</a></li>
                            </ul>-->
                        </div>
                    </div>
                    <div class="carousel-item">  
                        <img src="src/img/room.jpeg" alt="Carousel Image">
                        <div class="carousel-caption">
                            <h1 class="animated fadeInLeft">Habitaciones de gran confort.</h1>
                            <!--                            <p class="animated fadeInRight">Reserva aqui</p>-->
                            <ul style="list-style-type:none;">
                                <li class="nav-item nav-link active" style="float:left;"><a class="btn animated fadeInUp" href="Vistas/atencion.jsp" >Reserva aqui</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <img src="src/img/masaje4.jpg" alt="Carousel Image">
                        <div class="carousel-caption">
                            <h1 class="animated fadeInLeft">Confía en nuestras manos y déjate consentir</h1>
                            <ul style="list-style-type:none;">
                                <li class="nav-item nav-link active" style="float:left;"><a class="btn animated fadeInUp" href="Vistas/atencion.jsp" >Reserva tu cita aqui</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>            
        </c:if>
        <!-- Carrousel Nav Bar End --> 

        <!-- Nav Bar Start -->
        <div class="nav-bar">
            <div class="container-fluid">
                <nav class="navbar navbar-expand-lg bg-dark navbar-dark">
                    <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                        <div class="col-md-10">
                            <div class="row g-2">
                                <div class="col-md-4">
                                    <label>Buscar por tipo:</label>
                                    <select class="form-control">
                                        <option selected>Tipo de habitacion</option>
                                        <option value="1">Doble</option>
                                        <option value="2">Suite</option>
                                        <option value="3">Matrimonial</option>
                                    </select>
                                </div>
                                <div class="col-md-4">
                                    <label>Check-In</label>
                                    <input type="date" class="form-control">
                                </div>
                                <div class="col-md-4">
                                    <label>Check-Out</label>
                                    <input type="date" class="form-control">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <button class="btn btn-dark border-0 w-100 py-3" >Search</button>
                        </div>
                    </div>
                </nav>
            </div>
        </div> 
        <!-- Nav Bar End -->



        <!-- Servicios de Hotel -->
        <c:if test="${viewroom == null || viewroom == 0}">
        <div class="container py-3"> 
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 row-cols-lg-3 g-4"> 
                <%  HabitacionDAO roomdao = new HabitacionDAO();
                    List<Habitacion> listroom = roomdao.listar();
                    if (listroom != null) {
                        for (int i = 0; i < listroom.size(); i++) {
                            Habitacion room = listroom.get(i);
                            // Calcula el margen inferior basado en el índice
                            String marginClass = "card-margin-" + (i + 1);
                %>
                <div class="col">
                    <div class="card h-100 shadow-sm <%= marginClass%>" style="max-width: 400px;">
                        <!-- Imagen de la habitación -->
                        <div class="position-relative">
                            <div class="image-container">
                                <img src="src/img/habita.jpg" class="w-100 h-100 object-fit-cover" alt="Habitación de hotel">
                            </div>
                        </div>
                        <div class="card-body">
                            <!-- Encabezado con título y precio -->
                            <div class="d-flex justify-content-between align-items-start mb-3">
                                <div>
                                    <h5 class="card-title fs-4 fw-bold mb-1"><%= room.getTipo()%></h5>
                                    <p class="text-muted small mb-0"><%= room.getDescripcion()%></p>
                                </div>
                                <div class="text-end">
                                    <span class="fs-4 fw-bold" style="color: #000000">S/ <%= room.getPrecioNoche()%></span>
                                    <p class="text-muted small mb-0">por noche</p>
                                </div>
                            </div>
                            <!-- Características con iconos -->
                            <div class="row row-cols-2 g-3 mb-3">
                                <div class="col">
                                    <div class="d-flex align-items-center">
                                        <i class="fas fa-users text-secondary me-2"></i>
                                        <span class="small" style="color: #000000">&nbsp;&nbsp;2-4 personas</span>
                                    </div>
                                </div>
                                <div class="col">
                                    <div class="d-flex align-items-center">
                                        <i class="fas fa-bed text-secondary me-2"></i>
                                        <span class="small" style="color: #000000">&nbsp;&nbsp;2 camas</span>
                                    </div>
                                </div>
                            </div>
                            <!-- Botón de acción -->
                            <c:if test="${cliente.estado == null}">
                                <a class="btn btn-primary w-100" href="login.jsp">
                                    <i class="fas fa-calendar me-2"></i>
                                    Reservar
                                </a>
                            </c:if>
                            <c:if test="${cliente.estado != null}">
                                <a class="btn btn-primary w-100" href="ServletGeneral?menu=habitacion&idroom=<%= room.getIdhabitacion()%>">
                                    <i class="fas fa-calendar me-2"></i>
                                    Reservar
                                </a>
                            </c:if>
                        </div>
                    </div>
                </div>                               
                <%      }
                    }
                %>
            </div>
        </div>
</c:if>

        <!-- End -->        

        <!-- Servicios de SPA-->
        <div class="team">
            <div class="container"> 
                <div class="row"> 
                    <%  ServicioDAO serdao = new ServicioDAO();
                        List<Servicio> listser = serdao.listar();
                        if (listser != null) {
                            for (Servicio servicio : listser) {
                    %>
                    <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.4s">
                        <div class="team-item">
                            <div class="team-img">
                                <img src="src/img/masaje1.jpg" alt="Team Image" width="150" height="250">
                            </div>
                            <div class="team-text">
                                <h2><%= servicio.getNomserv()%></h2>
                                <p>Tiempo : <%= servicio.getDuracion()%> horas</p>
                                <p>Precio: S/ <%= servicio.getPrecio()%></p>
                            </div>  

                            <c:if test="${cliente.estado == null}"> 
                                <div class="team-social">
                                    <a class="social-in" href="login.jsp"><i class="fa fa-calendar"></i>&nbsp;&nbsp;&nbsp;Agregar a cita</a>
                                    <a class="social-in" href="login.jsp"><i class="fa fa-calendar"></i>&nbsp;&nbsp;Reservar ahora</a>
                                </div>
                            </c:if>

                            <c:if test="${cliente.estado != null}">
                                <div class="team-social">
                                    <a class="social-in" href="ServletGeneral?menu=carrito&accion=agregar&id=<%= servicio.getIdservicio()%>"><i class="fa fa-calendar"></i>&nbsp;&nbsp;&nbsp;Agregar a cita</a>
                                    <a class="social-in" href="ServletGeneral?menu=carrito&accion=comprar&id=<%= servicio.getIdservicio()%>"><i class="fa fa-calendar"></i>&nbsp;&nbsp;Reservar ahora</a>
                                </div>
                            </c:if>

                        </div>
                    </div>                                   
                    <%      }
                        }
                    %>

                </div>
            </div>
        </div>
        <!-- End -->


    </body>
    <jsp:include page="includes/footer.jsp"></jsp:include>
</html>
