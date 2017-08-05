-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-08-2017 a las 14:20:31
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

--
-- Volcado de datos para la tabla `auditoria`
--

INSERT INTO `auditoria` (`idAuditoria`, `fecha`, `hora`, `accion`, `usuario_idUsuario`) VALUES
(9, '2017-07-08', '2017-07-30 14:47:26', '234', 14);

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
  `empleado_cedula` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `capacitacion`
--

INSERT INTO `capacitacion` (`idCapacitacion`, `intructor`, `tipo`, `cedulaEmpledo`, `nombreEmpleado`, `fechaInicio`, `fechaCulminacion`, `duracion`, `empleado_cedula`) VALUES
(1, 'sdljf', 'sljf', '1235', 'dfsjkldj', '0000-00-00', '0000-00-00', 'dflskj', '123');

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

--
-- Volcado de datos para la tabla `contratacion`
--

INSERT INTO `contratacion` (`idContratacion`, `cedula`, `nombre`, `fechaInicio`, `fechaCulminacion`, `cargo`, `salario`, `empleado_cedula`) VALUES
(1, '14', 'asdf', '0000-00-00', '0000-00-00', 'sdf', 0, '123'),
(13, '123', 'Juan', '2017-08-02', NULL, 'sdf', 1123, '3456');

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
  `FK_id_Usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`cedula`, `nombreEmpleado`, `fechaNacimiento`, `direccion`, `cargo`, `status`, `registroSS`, `horaInicio`, `horasTrabajadas`, `statusLaborando`, `FK_id_Usuario`) VALUES
('123', 'Ivans', '0000-00-00', 'sdfa', '34', 'sdf', '324', '2017-08-03 08:30:05', 0, 1, 14),
('3456', 'Ivans Del pino', '2017-08-21', 'Calle Santa Rita', 'Lavador', '0', '12324', NULL, 0, 0, 15),
('asdf', 'asdf', '2017-07-18', 'sadf', 'sdf', '0', 'asdf', NULL, 0, 0, 17),
('dfasd', 'sdf', '2017-08-08', 'asdas', 'sdf', '0', 'fasd', NULL, 0, 0, 19),
('gsgsrf', 'sdf', '2017-07-18', 'asdf', 'asdf', '0', 'sdf', '2017-08-03 06:40:08', 1, 1, 18);

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
(1, 'fsdf', 'sdf', 'sdfsdf', 14);

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
  `totalDeduciones` double NOT NULL,
  `salarioTotal` double NOT NULL,
  `cedula` varchar(45) NOT NULL,
  `empleado_cedula` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `nomina`
--

INSERT INTO `nomina` (`idNomina`, `nombreEmpleado`, `diasHabiles`, `diasDescanso`, `bonoNocturno`, `bonoLealtad`, `diasFeriados`, `totalAsignaciones`, `faov`, `ivss`, `paroForzoso`, `prestamo`, `diasNoLaborados`, `totalDeduciones`, `salarioTotal`, `cedula`, `empleado_cedula`) VALUES
(1, 'afsd', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 'sdf', '123');

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
  `usuario_idUsuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `seleccion_personal`
--

INSERT INTO `seleccion_personal` (`idSeleccion`, `nombreCandidato`, `cedula`, `direccion`, `telefono`, `puestoPostulacion`, `disponibilidad`, `usuario_idUsuario`) VALUES
(1, 'sdf', 'sdf', 'sdf', 'sdf', 'sdf', 0, 14);

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
(14, 'Ivans', '12345', 'sdlfkj', 0, 'sdfjkl', '1', 'sdñf', '2', '2017-07-31', 'dsjlfkj', 1),
(15, 'Juan', '12354', 'sdfsdf', 1, '', '', '', '', '2017-07-30', 'sdf', 3),
(17, 'sdf', 'sdf', 'asdf', 1, '', '', '', '', '2017-08-02', 'asdf', 0),
(18, 'JuanFran', '42ui244', 'sdfsdf', 1, '', '', '', '', '2017-08-02', '42ui244', 3),
(19, 'Pedro Juan', '123', 'sdfsdf', 1, '', '', '', '', '2017-08-02', 'sdf', 3),
(20, 'Maria', '12345', 'Maria@gmail.com', 1, '', '', '', '', '2017-08-04', '2048295', 3),
(21, 'Maria Petra', '204', 'Maria@gmail.com', 1, '', '', '', '', '2017-08-04', '2048295', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valores`
--

CREATE TABLE `valores` (
  `idvalores` int(11) NOT NULL,
  `salario` varchar(45) DEFAULT NULL,
  `precioUnidadTributaria` varchar(45) DEFAULT NULL,
  `paroForzoso` varchar(45) DEFAULT NULL,
  `IVSS` varchar(45) DEFAULT NULL,
  `FAO` varchar(45) DEFAULT NULL,
  `fk_id_usuario` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  MODIFY `idCapacitacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `contratacion`
--
ALTER TABLE `contratacion`
  MODIFY `idContratacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT de la tabla `estructura_organizativa`
--
ALTER TABLE `estructura_organizativa`
  MODIFY `idEstrutura` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `nomina`
--
ALTER TABLE `nomina`
  MODIFY `idNomina` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `seleccion_personal`
--
ALTER TABLE `seleccion_personal`
  MODIFY `idSeleccion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
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
