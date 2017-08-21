-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-08-2017 a las 13:50:20
-- Versión del servidor: 10.1.21-MariaDB
-- Versión de PHP: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `rrhh`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `auditoria`
--

CREATE TABLE `auditoria` (
  `idAuditoria` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `accion` text NOT NULL,
  `nombreUsuario` varchar(45) NOT NULL,
  `usuario_idUsuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `auditoria`
--

INSERT INTO `auditoria` (`idAuditoria`, `fecha`, `accion`, `nombreUsuario`, `usuario_idUsuario`) VALUES
(1, '2017-08-20', 'Registro usuario Ivans', 'Ivans', 27),
(2, '2017-08-20', 'Registro usuario Ivans', 'Ivans', 27),
(3, '2017-08-20', 'Registro usuario Ivans', 'Ivans', 27),
(4, '2017-08-20', 'Registro usuario Ivans', 'Ivans', 27);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `capacitacion`
--

CREATE TABLE `capacitacion` (
  `idCapacitacion` int(11) NOT NULL,
  `instructor` text NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `nombreEmpleado` text NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaCulminacion` date NOT NULL,
  `duracion` varchar(45) NOT NULL,
  `empleado_cedula` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contratacion`
--

CREATE TABLE `contratacion` (
  `idContratacion` int(11) NOT NULL,
  `cedula` varchar(45) NOT NULL,
  `nombre` text NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaCulminacion` date DEFAULT NULL,
  `cargo` varchar(45) NOT NULL,
  `salarioInicial` double NOT NULL,
  `salarioActual` double DEFAULT NULL,
  `empleado_cedula` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `contratacion`
--

INSERT INTO `contratacion` (`idContratacion`, `cedula`, `nombre`, `fechaInicio`, `fechaCulminacion`, `cargo`, `salarioInicial`, `salarioActual`, `empleado_cedula`) VALUES
(18, 'V-20897704', 'Ivans Del Pino', '2017-08-14', NULL, 'Aseo', 25000, 27000, '123'),
(19, '123', 'Juan', '2017-08-13', NULL, 'Aseo', 25000, 27000, 'V-1'),
(20, 'V-2348', 'Miguel', '2017-08-13', NULL, 'Gerente', 50000, 0, 'V-2'),
(21, 'V-340295', 'Pedro Mora', '2017-08-14', NULL, 'Profesor', 25000, 0, 'V-3'),
(22, 'V-341324', 'Ivans Del Pino', '2017-08-19', NULL, 'Sueldo', 25000, 0, 'V-4'),
(23, 'V-441', 'Ivans Del Pino', '2017-08-19', NULL, 'sufaoj', 25000, 0, 'V-5'),
(24, 'V-141', 'asdf', '2017-08-19', NULL, 'asdf', 25000, 0, 'V-6'),
(25, 'V-1232', 'asdf', '2017-08-19', NULL, 'asdf', 25000, 0, 'V-7'),
(26, 'V-34', 'asdf', '2017-08-19', NULL, 'asdf', 25000, 0, 'V-8'),
(27, 'V-1245', 'Ivans Del Pino', '2017-08-20', NULL, 'Barredor', 25000, 0, 'V-9'),
(28, 'V-2314', 'Ivans Del Pino', '2017-08-20', NULL, 'Bombero', 25000, 0, 'V-10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `cedula` varchar(45) NOT NULL,
  `nombreEmpleado` text NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `cargo` varchar(45) NOT NULL,
  `registroSS` varchar(45) NOT NULL,
  `horaInicio` datetime DEFAULT NULL,
  `horasTrabajadas` int(11) NOT NULL DEFAULT '0',
  `statusLaborando` tinyint(1) DEFAULT '0',
  `diasLaborados` int(11) NOT NULL DEFAULT '0',
  `FK_id_Usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`cedula`, `nombreEmpleado`, `fechaNacimiento`, `direccion`, `cargo`, `registroSS`, `horaInicio`, `horasTrabajadas`, `statusLaborando`, `diasLaborados`, `FK_id_Usuario`) VALUES
('123', 'Juan', '2017-08-12', 'Calle Santa Rita', 'Aseo', '123413', '2017-08-13 12:54:42', 0, 0, 0, 28),
('V-1', 'Ivans Del Pino', '1985-08-07', 'Calle Santa Rita', 'Sueldo', '41324', NULL, 0, 0, 0, 28),
('V-10', 'Ivans Del Pino', '1982-08-18', 'Calle Santa Rita', 'Barredor', '21335', NULL, 0, 0, 0, 28),
('V-2', 'Ivans Del Pino', '1993-08-11', 'sdfadf', 'sufaoj', '3123', '2017-08-20 06:28:48', 0, 0, 0, 27),
('V-3', 'sdfad', '1970-08-26', 'ssf', 'sdfadsfasdf', '1312', NULL, 0, 0, 0, 28),
('V-4', 'asdf', '1970-08-12', 'dfads', 'asdf', '321', NULL, 0, 0, 0, 28),
('V-5', 'Ivans Del Pino', '1980-08-19', 'Calle Santa Rita', 'Aseo', '124235', NULL, 0, 0, 0, 28),
('V-6', 'Ivans Del Pino', '1987-08-20', 'Calle Santa Rita', 'Bombero', '21334', NULL, 0, 0, 0, 28),
('V-7', 'asdf', '1981-08-12', 'sadf', 'sdf', '1123', NULL, 0, 0, 0, 28),
('V-8', 'asdf', '1987-08-12', 'asdfa', 'asdf', '1232', NULL, 0, 0, 0, 28),
('V-9', 'Pedro Mora', '1983-08-11', 'Calle Paez', 'Profesor', '34324', NULL, 0, 0, 0, 28);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estructura_organizativa`
--

CREATE TABLE `estructura_organizativa` (
  `idEstrutura` int(11) NOT NULL,
  `departamento` varchar(45) NOT NULL,
  `funcion` varchar(45) NOT NULL,
  `jefeInmediato` varchar(45) NOT NULL,
  `usuario_idUsuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nomina`
--

CREATE TABLE `nomina` (
  `idNomina` int(11) NOT NULL,
  `nombreEmpleado` text NOT NULL,
  `diasHabiles` int(11) NOT NULL DEFAULT '11',
  `diasDescanso` int(11) NOT NULL DEFAULT '4',
  `bonoNocturno` double NOT NULL DEFAULT '0',
  `diasFeriados` int(11) NOT NULL DEFAULT '0',
  `prestamo` double NOT NULL DEFAULT '0',
  `diasNoLaborados` int(11) NOT NULL DEFAULT '0',
  `empleado_cedula` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `nomina`
--

INSERT INTO `nomina` (`idNomina`, `nombreEmpleado`, `diasHabiles`, `diasDescanso`, `bonoNocturno`, `diasFeriados`, `prestamo`, `diasNoLaborados`, `empleado_cedula`) VALUES
(1, 'asdf', 0, 0, 0, 0, 0, 0, '123'),
(2, 'asdf', 0, 0, 0, 0, 0, 0, 'V-1'),
(3, 'Ivans Del Pino', 0, 0, 0, 0, 0, 0, 'V-2'),
(4, 'Ivans Del Pino', 0, 0, 0, 0, 0, 0, 'V-3'),
(5, 'Ivans Del Pino', 0, 0, 0, 0, 0, 0, 'V-4'),
(6, 'Del Pino', 11, 4, 0, 0, 0, 0, 'V-5'),
(7, 'Pedro', 11, 4, 0, 0, 0, 0, 'V-6'),
(8, 'Juan', 11, 4, 0, 0, 0, 0, 'V-7'),
(9, 'Rafael', 11, 4, 0, 0, 0, 0, 'V-8'),
(10, 'Fernando', 11, 4, 0, 0, 0, 0, 'V-9'),
(11, 'Juancho', 11, 4, 0, 0, 0, 0, 'V-10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pago_nomina`
--

CREATE TABLE `pago_nomina` (
  `idNominaPago` int(11) NOT NULL,
  `nombreEmpleado` text NOT NULL,
  `diasHabiles` int(11) NOT NULL DEFAULT '11',
  `diasDescanso` int(11) NOT NULL DEFAULT '4',
  `bonoNocturno` double NOT NULL DEFAULT '0',
  `diasFeriados` int(11) NOT NULL DEFAULT '0',
  `totalAsignaciones` double NOT NULL,
  `faov` double NOT NULL,
  `ivss` double NOT NULL,
  `paroForzoso` double NOT NULL,
  `prestamo` double NOT NULL,
  `diasNoLaborados` int(11) NOT NULL DEFAULT '0',
  `totalDeducciones` double NOT NULL,
  `salarioTotal` double NOT NULL,
  `fecha` date NOT NULL,
  `empleado_cedula` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seleccion_personal`
--

CREATE TABLE `seleccion_personal` (
  `idSeleccion` int(11) NOT NULL,
  `nombreCandidato` text NOT NULL,
  `cedula` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `puestoPostulacion` varchar(45) NOT NULL,
  `disponibilidad` varchar(45) NOT NULL,
  `usuario_idUsuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL,
  `nombreUsuario` varchar(45) NOT NULL,
  `clave` varchar(45) NOT NULL,
  `correo` varchar(45) NOT NULL,
  `nivelAcceso` int(11) NOT NULL,
  `pregunta1` varchar(45) NOT NULL,
  `respuesta1` varchar(45) NOT NULL,
  `pregunta2` varchar(45) NOT NULL,
  `respuesta2` varchar(45) NOT NULL,
  `fechaCreacion` date NOT NULL,
  `cedula` varchar(45) NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idUsuario`, `nombreUsuario`, `clave`, `correo`, `nivelAcceso`, `pregunta1`, `respuesta1`, `pregunta2`, `respuesta2`, `fechaCreacion`, `cedula`, `status`) VALUES
(27, 'Ivans', '1234', 'Ivans@gmail.com', 1, 'Lugar de nacimiento de la madre', 'Maracay', 'Profesor favorito', 'Aponte', '2017-08-12', '123', 1),
(28, 'Pedro', '1234', 'Ivans@gmail.com', 2, 'asdkf', 'asdf', 'asdjf', 'sdflkj', '2017-08-20', '1234', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valores`
--

CREATE TABLE `valores` (
  `idvalores` int(11) NOT NULL,
  `salario` double DEFAULT NULL,
  `precioUnidadTributaria` varchar(45) DEFAULT NULL,
  `paroForzoso` varchar(45) DEFAULT NULL,
  `IVSS` varchar(45) DEFAULT NULL,
  `FAO` varchar(45) DEFAULT NULL,
  `diasUtilidades` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `fk_id_usuario` int(11) DEFAULT NULL,
  `contratacion_idContratacion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `valores`
--

INSERT INTO `valores` (`idvalores`, `salario`, `precioUnidadTributaria`, `paroForzoso`, `IVSS`, `FAO`, `diasUtilidades`, `fecha`, `fk_id_usuario`, `contratacion_idContratacion`) VALUES
(10, 52000, '12314.0', '4.0', '1.0', '0.5', NULL, '2017-08-14', 27, 20),
(11, 25000, '300.0', '0.5', '1.0', '4.0', NULL, '2017-08-14', 27, 21),
(12, 25000, '300.0', '0.5', '1.0', '4.0', NULL, '2017-08-19', 27, 22),
(13, 25000, '300.0', '0.5', '1.0', '4.0', NULL, '2017-08-19', 27, 23),
(14, 25000, '300.0', '0.5', '1.0', '4.0', NULL, '2017-08-19', 27, 24),
(15, 25000, '300.0', '0.5', '1.0', '4.0', NULL, '2017-08-19', 27, 25),
(16, 25000, '300.0', '0.5', '1.0', '4.0', NULL, '2017-08-19', 27, 26),
(17, 25000, '300.0', '0.5', '1.0', '4.0', NULL, '2017-08-20', 27, 27),
(18, 25000, '300.0', '0.5', '1.0', '4.0', NULL, '2017-08-20', 27, 28);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `auditoria`
--
ALTER TABLE `auditoria`
  ADD PRIMARY KEY (`idAuditoria`,`usuario_idUsuario`),
  ADD KEY `fk_auditoria_usuario1_idx` (`usuario_idUsuario`);

--
-- Indices de la tabla `capacitacion`
--
ALTER TABLE `capacitacion`
  ADD PRIMARY KEY (`idCapacitacion`,`empleado_cedula`),
  ADD KEY `fk_capacitacion_empleado1_idx` (`empleado_cedula`);

--
-- Indices de la tabla `contratacion`
--
ALTER TABLE `contratacion`
  ADD PRIMARY KEY (`idContratacion`,`empleado_cedula`),
  ADD KEY `fk_contratacion_empleado1_idx` (`empleado_cedula`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`cedula`),
  ADD KEY `FK_id_Usuario` (`FK_id_Usuario`);

--
-- Indices de la tabla `estructura_organizativa`
--
ALTER TABLE `estructura_organizativa`
  ADD PRIMARY KEY (`idEstrutura`,`usuario_idUsuario`),
  ADD KEY `fk_estructura_organizativa_usuario1_idx` (`usuario_idUsuario`);

--
-- Indices de la tabla `nomina`
--
ALTER TABLE `nomina`
  ADD PRIMARY KEY (`idNomina`,`empleado_cedula`),
  ADD KEY `fk_nomina_empleado1_idx` (`empleado_cedula`);

--
-- Indices de la tabla `pago_nomina`
--
ALTER TABLE `pago_nomina`
  ADD PRIMARY KEY (`idNominaPago`,`empleado_cedula`),
  ADD KEY `fk_nomina_empleado1_idx` (`empleado_cedula`);

--
-- Indices de la tabla `seleccion_personal`
--
ALTER TABLE `seleccion_personal`
  ADD PRIMARY KEY (`idSeleccion`,`usuario_idUsuario`),
  ADD KEY `fk_seleccion_personal_usuario1_idx` (`usuario_idUsuario`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`);

--
-- Indices de la tabla `valores`
--
ALTER TABLE `valores`
  ADD PRIMARY KEY (`idvalores`,`contratacion_idContratacion`),
  ADD KEY `Fk_id_usuario_idx` (`fk_id_usuario`),
  ADD KEY `fk_valores_contratacion1_idx` (`contratacion_idContratacion`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `auditoria`
--
ALTER TABLE `auditoria`
  MODIFY `idAuditoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `capacitacion`
--
ALTER TABLE `capacitacion`
  MODIFY `idCapacitacion` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `contratacion`
--
ALTER TABLE `contratacion`
  MODIFY `idContratacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT de la tabla `estructura_organizativa`
--
ALTER TABLE `estructura_organizativa`
  MODIFY `idEstrutura` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `nomina`
--
ALTER TABLE `nomina`
  MODIFY `idNomina` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT de la tabla `pago_nomina`
--
ALTER TABLE `pago_nomina`
  MODIFY `idNominaPago` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `seleccion_personal`
--
ALTER TABLE `seleccion_personal`
  MODIFY `idSeleccion` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT de la tabla `valores`
--
ALTER TABLE `valores`
  MODIFY `idvalores` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `auditoria`
--
ALTER TABLE `auditoria`
  ADD CONSTRAINT `fk_auditoria_usuario1` FOREIGN KEY (`usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `capacitacion`
--
ALTER TABLE `capacitacion`
  ADD CONSTRAINT `fk_capacitacion_empleado1` FOREIGN KEY (`empleado_cedula`) REFERENCES `empleado` (`cedula`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `contratacion`
--
ALTER TABLE `contratacion`
  ADD CONSTRAINT `fk_contratacion_empleado1` FOREIGN KEY (`empleado_cedula`) REFERENCES `empleado` (`cedula`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`FK_id_Usuario`) REFERENCES `usuario` (`idUsuario`);

--
-- Filtros para la tabla `estructura_organizativa`
--
ALTER TABLE `estructura_organizativa`
  ADD CONSTRAINT `fk_estructura_organizativa_usuario1` FOREIGN KEY (`usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `nomina`
--
ALTER TABLE `nomina`
  ADD CONSTRAINT `fk_nomina_empleado1` FOREIGN KEY (`empleado_cedula`) REFERENCES `empleado` (`cedula`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `pago_nomina`
--
ALTER TABLE `pago_nomina`
  ADD CONSTRAINT `fk_nomina_empleado10` FOREIGN KEY (`empleado_cedula`) REFERENCES `empleado` (`cedula`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `seleccion_personal`
--
ALTER TABLE `seleccion_personal`
  ADD CONSTRAINT `fk_seleccion_personal_usuario1` FOREIGN KEY (`usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `valores`
--
ALTER TABLE `valores`
  ADD CONSTRAINT `FK_id_usuario` FOREIGN KEY (`fk_id_usuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_valores_contratacion1` FOREIGN KEY (`contratacion_idContratacion`) REFERENCES `contratacion` (`idContratacion`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
