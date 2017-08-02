-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-07-2017 a las 11:15:14
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
  `FK_idUsuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `capacitacion`
--

CREATE TABLE `capacitacion` (
  `idCapacitacion` int(11) NOT NULL,
  `intructor` text NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `cedulaEmpledo` varchar(45) NOT NULL,
  `nombreEmpleado` text NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaCulminacion` date NOT NULL,
  `duracion` varchar(45) NOT NULL,
  `FK_cedula_empleado` varchar(45) DEFAULT NULL
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
  `salario` double NOT NULL,
  `FK_cedula_empleado` varchar(45) DEFAULT NULL
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
  `FK_id_Usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estructura_organizativa`
--

CREATE TABLE `estructura_organizativa` (
  `idEstrutura` int(11) NOT NULL,
  `departamento` varchar(45) NOT NULL,
  `funcion` varchar(45) NOT NULL,
  `jefeInmediato` varchar(45) NOT NULL,
  `FK_idUsuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nomina`
--

CREATE TABLE `nomina` (
  `idNomina` int(11) NOT NULL,
  `nombreEmpleado` text NOT NULL,
  `diasHabiles` int(11) NOT NULL,
  `diasDescanso` int(11) NOT NULL,
  `bonoNocturno` double NOT NULL,
  `bonoLealtad` double NOT NULL,
  `diasFeriados` int(11) NOT NULL,
  `totalAsignaciones` double NOT NULL,
  `faov` double NOT NULL,
  `ivss` double NOT NULL,
  `paroForzoso` double NOT NULL,
  `prestamo` double NOT NULL,
  `diasNoLaborados` int(11) NOT NULL,
  `totalDeducciones` double NOT NULL,
  `salarioTotal` double NOT NULL,
  `cedula` varchar(45) NOT NULL,
  `FK_cedula_empleado` varchar(45) DEFAULT NULL
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
  `disponibilidad` int(11) NOT NULL,
  `FK_idUsuario` int(11) DEFAULT NULL
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
  `cedula` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `auditoria`
--
ALTER TABLE `auditoria`
  ADD PRIMARY KEY (`idAuditoria`),
  ADD KEY `FK_idUsuario` (`FK_idUsuario`);

--
-- Indices de la tabla `capacitacion`
--
ALTER TABLE `capacitacion`
  ADD PRIMARY KEY (`idCapacitacion`),
  ADD KEY `FK_cedula_empleado` (`FK_cedula_empleado`);

--
-- Indices de la tabla `contratacion`
--
ALTER TABLE `contratacion`
  ADD PRIMARY KEY (`idContratacion`),
  ADD KEY `FK_cedula_empleado` (`FK_cedula_empleado`);

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
  ADD PRIMARY KEY (`idEstrutura`),
  ADD KEY `FK_idUsuario` (`FK_idUsuario`);

--
-- Indices de la tabla `nomina`
--
ALTER TABLE `nomina`
  ADD PRIMARY KEY (`idNomina`),
  ADD KEY `FK_cedula_empleado` (`FK_cedula_empleado`);

--
-- Indices de la tabla `seleccion_personal`
--
ALTER TABLE `seleccion_personal`
  ADD PRIMARY KEY (`idSeleccion`),
  ADD KEY `FK_idUsuario` (`FK_idUsuario`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `auditoria`
--
ALTER TABLE `auditoria`
  MODIFY `idAuditoria` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `capacitacion`
--
ALTER TABLE `capacitacion`
  MODIFY `idCapacitacion` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `contratacion`
--
ALTER TABLE `contratacion`
  MODIFY `idContratacion` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `estructura_organizativa`
--
ALTER TABLE `estructura_organizativa`
  MODIFY `idEstrutura` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `nomina`
--
ALTER TABLE `nomina`
  MODIFY `idNomina` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `seleccion_personal`
--
ALTER TABLE `seleccion_personal`
  MODIFY `idSeleccion` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `auditoria`
--
ALTER TABLE `auditoria`
  ADD CONSTRAINT `FK_idUsuario` FOREIGN KEY (`FK_idUsuario`) REFERENCES `usuario` (`idUsuario`);

--
-- Filtros para la tabla `capacitacion`
--
ALTER TABLE `capacitacion`
  ADD CONSTRAINT `capacitacion_ibfk_1` FOREIGN KEY (`FK_cedula_empleado`) REFERENCES `empleado` (`cedula`);

--
-- Filtros para la tabla `contratacion`
--
ALTER TABLE `contratacion`
  ADD CONSTRAINT `contratacion_ibfk_1` FOREIGN KEY (`FK_cedula_empleado`) REFERENCES `empleado` (`cedula`);

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`FK_id_Usuario`) REFERENCES `usuario` (`idUsuario`);

--
-- Filtros para la tabla `estructura_organizativa`
--
ALTER TABLE `estructura_organizativa`
  ADD CONSTRAINT `estructura_organizativa_ibfk_1` FOREIGN KEY (`FK_idUsuario`) REFERENCES `usuario` (`idUsuario`);

--
-- Filtros para la tabla `nomina`
--
ALTER TABLE `nomina`
  ADD CONSTRAINT `FK_cedula_empleado` FOREIGN KEY (`FK_cedula_empleado`) REFERENCES `empleado` (`cedula`);

--
-- Filtros para la tabla `seleccion_personal`
--
ALTER TABLE `seleccion_personal`
  ADD CONSTRAINT `seleccion_personal_ibfk_1` FOREIGN KEY (`FK_idUsuario`) REFERENCES `usuario` (`idUsuario`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
