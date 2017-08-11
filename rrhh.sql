-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-08-2017 a las 14:35:18
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
  `hora` datetime NOT NULL,
  `accion` text NOT NULL,
  `usuario_idUsuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

--
-- Volcado de datos para la tabla `capacitacion`
--

INSERT INTO `capacitacion` (`idCapacitacion`, `instructor`, `tipo`, `nombreEmpleado`, `fechaInicio`, `fechaCulminacion`, `duracion`, `empleado_cedula`) VALUES
(9, 'Pedro', '1', 'Juan', '2017-08-10', '2017-08-11', '23', '123');

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
  `salario` double NOT NULL,
  `empleado_cedula` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  `status` text NOT NULL,
  `registroSS` varchar(45) NOT NULL,
  `horaInicio` datetime DEFAULT NULL,
  `horasTrabajadas` int(11) NOT NULL DEFAULT '0',
  `statusLaborando` tinyint(1) DEFAULT '0',
  `diasLaborados` int(11) NOT NULL,
  `FK_id_Usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`cedula`, `nombreEmpleado`, `fechaNacimiento`, `direccion`, `cargo`, `status`, `registroSS`, `horaInicio`, `horasTrabajadas`, `statusLaborando`, `diasLaborados`, `FK_id_Usuario`) VALUES
('123', 'Juan', '2017-08-10', 'Santa Rita', 'Aseo', '1', '1242', '2017-08-11 08:02:11', 2, 1, 11, 23);

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

--
-- Volcado de datos para la tabla `estructura_organizativa`
--

INSERT INTO `estructura_organizativa` (`idEstrutura`, `departamento`, `funcion`, `jefeInmediato`, `usuario_idUsuario`) VALUES
(2, 'Ventas', 'Gerente', 'Juan', 23);

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
  `prestamo` double NOT NULL,
  `diasNoLaborados` int(11) NOT NULL DEFAULT '0',
  `empleado_cedula` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `nomina`
--

INSERT INTO `nomina` (`idNomina`, `nombreEmpleado`, `diasHabiles`, `diasDescanso`, `bonoNocturno`, `diasFeriados`, `prestamo`, `diasNoLaborados`, `empleado_cedula`) VALUES
(2, 'Juan', 9, 4, 2, 2, 1000, 2, '123');

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
(23, 'Ivans', '1234', 'Ivans@gmail.com', 1, 'Vacion', 'si', 'Vacion', 'No', '2017-08-10', '123', 1);

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
  `fecha` date DEFAULT NULL,
  `fk_id_usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `valores`
--

INSERT INTO `valores` (`idvalores`, `salario`, `precioUnidadTributaria`, `paroForzoso`, `IVSS`, `FAO`, `fecha`, `fk_id_usuario`) VALUES
(8, 54000, '300', '1', '4', '0.5', '2017-08-10', 23);

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
  ADD PRIMARY KEY (`idvalores`),
  ADD KEY `Fk_id_usuario_idx` (`fk_id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `auditoria`
--
ALTER TABLE `auditoria`
  MODIFY `idAuditoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT de la tabla `capacitacion`
--
ALTER TABLE `capacitacion`
  MODIFY `idCapacitacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT de la tabla `contratacion`
--
ALTER TABLE `contratacion`
  MODIFY `idContratacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT de la tabla `estructura_organizativa`
--
ALTER TABLE `estructura_organizativa`
  MODIFY `idEstrutura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `nomina`
--
ALTER TABLE `nomina`
  MODIFY `idNomina` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `pago_nomina`
--
ALTER TABLE `pago_nomina`
  MODIFY `idNominaPago` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `seleccion_personal`
--
ALTER TABLE `seleccion_personal`
  MODIFY `idSeleccion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;
--
-- AUTO_INCREMENT de la tabla `valores`
--
ALTER TABLE `valores`
  MODIFY `idvalores` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
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
  ADD CONSTRAINT `FK_id_usuario` FOREIGN KEY (`fk_id_usuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
