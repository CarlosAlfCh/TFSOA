-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-11-2024 a las 21:58:13
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbhotel`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `codigos_promocionales`
--

CREATE TABLE `codigos_promocionales` (
  `id_promo` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `estado` int(11) NOT NULL,
  `fecha_generacion` date NOT NULL,
  `fecha_expiracion` date NOT NULL,
  `fecha_uso` date DEFAULT NULL,
  `descuento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleroom`
--

CREATE TABLE `detalleroom` (
  `id_detalle` int(11) NOT NULL,
  `id_reserva` int(11) NOT NULL,
  `id_habitacion` int(11) NOT NULL,
  `f_inicio` date NOT NULL,
  `f_salida` date NOT NULL,
  `nnoches` int(11) NOT NULL,
  `subtotal` double NOT NULL,
  `total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalleroom`
--

INSERT INTO `detalleroom` (`id_detalle`, `id_reserva`, `id_habitacion`, `f_inicio`, `f_salida`, `nnoches`, `subtotal`, `total`) VALUES
(6, 18, 2, '2024-11-18', '2024-11-22', 4, 250, 1000),
(7, 20, 3, '2024-11-17', '2024-11-19', 2, 80, 160);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalleservicios`
--

CREATE TABLE `detalleservicios` (
  `id_detalle` int(11) NOT NULL,
  `id_reserva` int(11) NOT NULL,
  `id_servicio` int(11) DEFAULT NULL,
  `f_atencion` date NOT NULL,
  `npersonas` int(11) NOT NULL,
  `subtotal` double NOT NULL,
  `total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalleservicios`
--

INSERT INTO `detalleservicios` (`id_detalle`, `id_reserva`, `id_servicio`, `f_atencion`, `npersonas`, `subtotal`, `total`) VALUES
(16, 19, 1, '2024-11-21', 1, 50, 50),
(17, 19, 2, '2024-11-21', 2, 40, 80),
(18, 19, 3, '2024-11-21', 2, 35, 70),
(19, 20, 1, '2024-11-25', 1, 50, 50),
(20, 20, 2, '2024-11-25', 1, 40, 40);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `distrito`
--

CREATE TABLE `distrito` (
  `id_distrito` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `distrito`
--

INSERT INTO `distrito` (`id_distrito`, `nombre`) VALUES
(1, 'Alto Selva Alegre'),
(2, 'Arequipa'),
(3, 'Cayma'),
(4, 'Cerro Colorado'),
(5, 'Characato'),
(6, 'Chiguata'),
(7, 'Jacobo Hunter'),
(8, 'José Luis Bustamante y Rivero'),
(9, 'La Joya'),
(10, 'Mariano Melgar'),
(11, 'Miraflores'),
(12, 'Mollebaya'),
(13, 'Paucarpata'),
(14, 'Pocsi'),
(15, 'Polobaya'),
(16, 'Quequeña'),
(17, 'Sabandía'),
(18, 'Sachaca'),
(19, 'San Juan de Siguas'),
(20, 'San Juan de Tarucani'),
(21, 'Santa Isabel de Siguas'),
(22, 'Santa Rita de Siguas'),
(23, 'Socabaya'),
(24, 'Tiabaya'),
(25, 'Uchumayo'),
(26, 'Vitor'),
(27, 'Yanahuara'),
(28, 'Yarabamba'),
(29, 'Yura');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `especialidad`
--

CREATE TABLE `especialidad` (
  `idespecialidad` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `especialidad`
--

INSERT INTO `especialidad` (`idespecialidad`, `nombre`) VALUES
(1, 'S/C'),
(2, 'Masajista'),
(3, 'Manicurista'),
(4, 'Pedicurista'),
(5, 'Cosmetólogo'),
(6, 'Terapeuta de Spa'),
(7, 'Instructor de Fitness'),
(8, 'Nutricionista');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `habitacion`
--

CREATE TABLE `habitacion` (
  `id_habitacion` int(11) NOT NULL,
  `piso` varchar(5) NOT NULL,
  `tipo_habita` varchar(50) NOT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  `foto_habitacion` blob DEFAULT NULL,
  `precioxnoche` double NOT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `id_distrito` int(11) NOT NULL,
  `estado` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `habitacion`
--

INSERT INTO `habitacion` (`id_habitacion`, `piso`, `tipo_habita`, `descripcion`, `foto_habitacion`, `precioxnoche`, `direccion`, `id_distrito`, `estado`) VALUES
(1, '2', 'Doble', 'Vista a la chilina', NULL, 120, 'Calle Los Olivos 123', 1, 2),
(2, '3', 'Suite', 'Jacuzzi y vista panorámica', NULL, 250, 'Av. Principal 456', 2, 1),
(3, '1', 'Individual', 'Individual privado', NULL, 80, 'Calle La Luna 789', 3, 2),
(4, '4', 'Suite', 'Terraza y piscina privada', NULL, 400, 'Av. Maravillas 321', 4, 3),
(5, '2', 'Doble', 'Vista al Misti', NULL, 150, 'Calle Las Flores 101', 5, 1),
(6, '3', 'Matrimonial', 'Piscina', NULL, 120, 'Av. Aeropajita', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horario`
--

CREATE TABLE `horario` (
  `id_horario` int(11) NOT NULL,
  `id_habitacion` int(11) NOT NULL,
  `id_reserva` int(11) NOT NULL,
  `estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pago`
--

CREATE TABLE `pago` (
  `id_pago` int(11) NOT NULL,
  `n_operacion` varchar(50) NOT NULL,
  `fecha_pago` varchar(40) NOT NULL,
  `metodo` varchar(40) NOT NULL,
  `valido` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `pago`
--

INSERT INTO `pago` (`id_pago`, `n_operacion`, `fecha_pago`, `metodo`, `valido`) VALUES
(39, '18587', '12/11/2024', 'Yape', 0),
(40, '35851', '12/11/2024', 'Yape', 0),
(41, '49478', '12/11/2024', 'Plin', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `codigo` int(11) NOT NULL,
  `nombres` varchar(40) NOT NULL,
  `apelpat` varchar(50) NOT NULL,
  `apelmat` varchar(50) NOT NULL,
  `dni` varchar(9) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `contrasena` varchar(50) NOT NULL,
  `estado` int(11) NOT NULL,
  `idrol` int(11) NOT NULL,
  `idespecialidad` int(11) DEFAULT NULL,
  `direccion` varchar(50) DEFAULT NULL,
  `id_distrito` int(11) NOT NULL,
  `turno` varchar(40) DEFAULT NULL,
  `foto` blob DEFAULT NULL,
  `recuperar` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`codigo`, `nombres`, `apelpat`, `apelmat`, `dni`, `correo`, `telefono`, `contrasena`, `estado`, `idrol`, `idespecialidad`, `direccion`, `id_distrito`, `turno`, `foto`, `recuperar`) VALUES
(1, 'Carlos Alfred', 'Choquehuanca', 'Chuctaya', '71650006', 'che.carlos.aqp@gmail.com', '999999999', '1', 1, 1, 1, 'Av. Aquiles Castro', 3, 'MaÃ±ana', NULL, '52894'),
(3, 'Josh', 'Volgen', 'Dumkof', '12345678', 'jolgendum@gmail.com', '123456789', '12', 1, 2, 2, 'Asoc. Aeropajita', 4, 'Mañana', NULL, NULL),
(4, 'Pedro', 'Suarez', 'Vertiz', '12345678', 'pedro@gmail.com', '123456789', '12', 1, 3, 7, 'Asoc. Aeropajita', 4, 'Tarde', NULL, NULL),
(6, 'Lola', 'Chuctaya', 'Colca', '1234567', 'lola.aqp@gmail.com', '123456789', '12', 1, 4, 2, 'Asoc. Las Malvinas', 1, NULL, NULL, NULL),
(7, 'Alexander', 'Romanov', 'Perez', '12345678', 'alexan@gmail.com', '123456789', '12345678', 1, 4, 2, 'Av. Delfin', 1, NULL, NULL, NULL),
(8, 'Angel', 'Valdivia', 'Mendoza', '1234567', 'a@gmail.com', '123', '1234567', 1, 3, 2, '123 av', 1, 'Mañana', NULL, NULL),
(9, 'Andrew', 'Alvarez', 'Murillo', '123456', 'andrew@gmail.com', '123', '123456', 1, 2, 2, 'Av. La paz', 2, 'Tarde', NULL, NULL),
(10, 'Benito', 'Villa', 'Buena', '123', 'bbenito@gmail.com', '12345', 'ben', 1, 2, 2, 'Av. Jesus', 1, 'Tarde', NULL, NULL),
(11, 'Claude', 'Anatoli', 'Vegas', '12345', 'claudeia@gmail.com', '12345', '12', 1, 3, 2, 'Av. IArtifi', 3, 'Tarde', NULL, NULL),
(12, 'Isac', 'Ventura', 'Marcos', '12345678', 'isa@gmail.com', '1234', '123', 1, 4, 1, 'Av. Prueba ', 1, NULL, NULL, NULL),
(14, 'Max', 'Stell', 'Smith', '123', 'max@gmail.com', '12345', '123', 1, 3, 2, 'Av. Terminator', 2, 'Noche', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva`
--

CREATE TABLE `reserva` (
  `id_reserva` int(11) NOT NULL,
  `f_reserva` varchar(20) DEFAULT NULL,
  `estado` int(11) NOT NULL,
  `f_atencion` date DEFAULT NULL,
  `f_check_in` date DEFAULT NULL,
  `f_check_out` date DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `id_pago` int(11) DEFAULT NULL,
  `id_tecnico` int(11) DEFAULT NULL,
  `monto` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reserva`
--

INSERT INTO `reserva` (`id_reserva`, `f_reserva`, `estado`, `f_atencion`, `f_check_in`, `f_check_out`, `id_cliente`, `id_pago`, `id_tecnico`, `monto`) VALUES
(18, '12/11/2024', 0, NULL, '2024-11-18', '2024-11-22', 6, 39, NULL, 1000),
(19, '12/11/2024', 0, '2024-11-21', NULL, NULL, 6, 40, NULL, 200),
(20, '12/11/2024', 0, '2024-11-25', '2024-11-17', '2024-11-19', 6, 41, NULL, 250);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `idrol` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`idrol`, `nombre`) VALUES
(1, 'Administrador'),
(2, 'Encargado'),
(3, 'Técnico'),
(4, 'Cliente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicio`
--

CREATE TABLE `servicio` (
  `id_servicio` int(11) NOT NULL,
  `nom_serv` varchar(50) NOT NULL,
  `descripcion` varchar(300) NOT NULL,
  `duracion` varchar(20) NOT NULL,
  `precio` double NOT NULL,
  `turno` varchar(30) NOT NULL,
  `id_tipo` int(11) NOT NULL,
  `id_especialidad` int(11) NOT NULL,
  `estado` int(11) NOT NULL,
  `foto_servicio` blob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `servicio`
--

INSERT INTO `servicio` (`id_servicio`, `nom_serv`, `descripcion`, `duracion`, `precio`, `turno`, `id_tipo`, `id_especialidad`, `estado`, `foto_servicio`) VALUES
(1, 'Masaje Sueco', 'Un masaje relajante que utiliza movimientos largos y fluidos para aliviar la tension muscular', '01:20', 50, 'tarde', 1, 1, 1, NULL),
(2, 'Facial Hidratante', 'Tratamiento facial que hidrata profundamente la piel, dejándola suave y radiante.', '45', 40, 'Tarde', 2, 1, 1, NULL),
(3, 'Exfoliación Corporal', 'Un tratamiento que elimina las células muertas de la piel, mejorando su textura y apariencia.', '30', 35, 'Todo el día', 4, 1, 1, NULL),
(4, 'Acupuntura', 'Es una práctica que implica estimular partes específicas del cuerpo, generalmente, mediante la inserción de agujas muy finas', '01:00', 12.5, 'tarde', 1, 1, 1, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tiposervicio`
--

CREATE TABLE `tiposervicio` (
  `id_tipo` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tiposervicio`
--

INSERT INTO `tiposervicio` (`id_tipo`, `nombre`) VALUES
(1, 'Masaje'),
(2, 'Facial Hidratante'),
(3, 'Facial Antiedad'),
(4, 'Exfoliación Corporal'),
(5, 'Manicura'),
(6, 'Pedicura'),
(7, 'Terapia'),
(8, 'Hidroterapia'),
(9, 'Baño de Vapor'),
(10, 'Tratamiento Anti Celulitis'),
(11, 'Tratamiento Anti Estrés');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `codigos_promocionales`
--
ALTER TABLE `codigos_promocionales`
  ADD PRIMARY KEY (`id_promo`),
  ADD KEY `id_cliente` (`id_cliente`);

--
-- Indices de la tabla `detalleroom`
--
ALTER TABLE `detalleroom`
  ADD PRIMARY KEY (`id_detalle`),
  ADD KEY `id_habitacion` (`id_habitacion`),
  ADD KEY `id_reserva` (`id_reserva`);

--
-- Indices de la tabla `detalleservicios`
--
ALTER TABLE `detalleservicios`
  ADD PRIMARY KEY (`id_detalle`),
  ADD KEY `id_servicio` (`id_servicio`),
  ADD KEY `id_reserva` (`id_reserva`);

--
-- Indices de la tabla `distrito`
--
ALTER TABLE `distrito`
  ADD PRIMARY KEY (`id_distrito`);

--
-- Indices de la tabla `especialidad`
--
ALTER TABLE `especialidad`
  ADD PRIMARY KEY (`idespecialidad`);

--
-- Indices de la tabla `habitacion`
--
ALTER TABLE `habitacion`
  ADD PRIMARY KEY (`id_habitacion`),
  ADD KEY `id_distrito` (`id_distrito`);

--
-- Indices de la tabla `horario`
--
ALTER TABLE `horario`
  ADD PRIMARY KEY (`id_horario`),
  ADD KEY `id_habitacion` (`id_habitacion`),
  ADD KEY `id_reserva` (`id_reserva`);

--
-- Indices de la tabla `pago`
--
ALTER TABLE `pago`
  ADD PRIMARY KEY (`id_pago`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`codigo`),
  ADD KEY `persona_ibfk_1` (`idrol`),
  ADD KEY `persona_ibfk_2` (`idespecialidad`),
  ADD KEY `persona_ibfk_3` (`id_distrito`);

--
-- Indices de la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD PRIMARY KEY (`id_reserva`),
  ADD KEY `reserva_ibfk_1` (`id_cliente`),
  ADD KEY `reserva_ibfk_2` (`id_pago`),
  ADD KEY `reserva_ibfk_3` (`id_tecnico`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`idrol`);

--
-- Indices de la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD PRIMARY KEY (`id_servicio`),
  ADD KEY `id_especialidad` (`id_especialidad`),
  ADD KEY `id_tipo` (`id_tipo`);

--
-- Indices de la tabla `tiposervicio`
--
ALTER TABLE `tiposervicio`
  ADD PRIMARY KEY (`id_tipo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `codigos_promocionales`
--
ALTER TABLE `codigos_promocionales`
  MODIFY `id_promo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detalleroom`
--
ALTER TABLE `detalleroom`
  MODIFY `id_detalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `detalleservicios`
--
ALTER TABLE `detalleservicios`
  MODIFY `id_detalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `distrito`
--
ALTER TABLE `distrito`
  MODIFY `id_distrito` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- AUTO_INCREMENT de la tabla `especialidad`
--
ALTER TABLE `especialidad`
  MODIFY `idespecialidad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `habitacion`
--
ALTER TABLE `habitacion`
  MODIFY `id_habitacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `horario`
--
ALTER TABLE `horario`
  MODIFY `id_horario` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pago`
--
ALTER TABLE `pago`
  MODIFY `id_pago` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `codigo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `reserva`
--
ALTER TABLE `reserva`
  MODIFY `id_reserva` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `idrol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `servicio`
--
ALTER TABLE `servicio`
  MODIFY `id_servicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `tiposervicio`
--
ALTER TABLE `tiposervicio`
  MODIFY `id_tipo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `codigos_promocionales`
--
ALTER TABLE `codigos_promocionales`
  ADD CONSTRAINT `codigos_promocionales_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `persona` (`codigo`) ON DELETE CASCADE;

--
-- Filtros para la tabla `detalleroom`
--
ALTER TABLE `detalleroom`
  ADD CONSTRAINT `detalleroom_ibfk_1` FOREIGN KEY (`id_habitacion`) REFERENCES `habitacion` (`id_habitacion`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detalleroom_ibfk_2` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`id_reserva`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `detalleservicios`
--
ALTER TABLE `detalleservicios`
  ADD CONSTRAINT `detalleservicios_ibfk_1` FOREIGN KEY (`id_servicio`) REFERENCES `servicio` (`id_servicio`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detalleservicios_ibfk_2` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`id_reserva`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `habitacion`
--
ALTER TABLE `habitacion`
  ADD CONSTRAINT `habitacion_ibfk_1` FOREIGN KEY (`id_distrito`) REFERENCES `distrito` (`id_distrito`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `horario`
--
ALTER TABLE `horario`
  ADD CONSTRAINT `horario_ibfk_1` FOREIGN KEY (`id_habitacion`) REFERENCES `habitacion` (`id_habitacion`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `horario_ibfk_2` FOREIGN KEY (`id_reserva`) REFERENCES `reserva` (`id_reserva`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `persona`
--
ALTER TABLE `persona`
  ADD CONSTRAINT `persona_ibfk_1` FOREIGN KEY (`idrol`) REFERENCES `rol` (`idrol`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `persona_ibfk_2` FOREIGN KEY (`idespecialidad`) REFERENCES `especialidad` (`idespecialidad`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `persona_ibfk_3` FOREIGN KEY (`id_distrito`) REFERENCES `distrito` (`id_distrito`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `reserva_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `persona` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reserva_ibfk_2` FOREIGN KEY (`id_pago`) REFERENCES `pago` (`id_pago`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reserva_ibfk_3` FOREIGN KEY (`id_tecnico`) REFERENCES `persona` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `servicio`
--
ALTER TABLE `servicio`
  ADD CONSTRAINT `servicio_ibfk_1` FOREIGN KEY (`id_especialidad`) REFERENCES `especialidad` (`idespecialidad`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `servicio_ibfk_2` FOREIGN KEY (`id_tipo`) REFERENCES `tiposervicio` (`id_tipo`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
