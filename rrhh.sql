-- MySQL dump 10.16  Distrib 10.1.21-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: localhost
-- ------------------------------------------------------
-- Server version	10.1.21-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `auditoria`
--

DROP TABLE IF EXISTS `auditoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `auditoria` (
  `idAuditoria` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `hora` datetime NOT NULL,
  `accion` text NOT NULL,
  `nombreUsuario` varchar(45) NOT NULL,
  `usuario_idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idAuditoria`,`usuario_idUsuario`),
  KEY `fk_auditoria_usuario1_idx` (`usuario_idUsuario`),
  CONSTRAINT `fk_auditoria_usuario1` FOREIGN KEY (`usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditoria`
--

LOCK TABLES `auditoria` WRITE;
/*!40000 ALTER TABLE `auditoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `auditoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `capacitacion`
--

DROP TABLE IF EXISTS `capacitacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `capacitacion` (
  `idCapacitacion` int(11) NOT NULL AUTO_INCREMENT,
  `instructor` text NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `nombreEmpleado` text NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaCulminacion` date NOT NULL,
  `duracion` varchar(45) NOT NULL,
  `empleado_cedula` varchar(45) NOT NULL,
  PRIMARY KEY (`idCapacitacion`,`empleado_cedula`),
  KEY `fk_capacitacion_empleado1_idx` (`empleado_cedula`),
  CONSTRAINT `fk_capacitacion_empleado1` FOREIGN KEY (`empleado_cedula`) REFERENCES `empleado` (`cedula`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `capacitacion`
--

LOCK TABLES `capacitacion` WRITE;
/*!40000 ALTER TABLE `capacitacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `capacitacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contratacion`
--

DROP TABLE IF EXISTS `contratacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contratacion` (
  `idContratacion` int(11) NOT NULL AUTO_INCREMENT,
  `cedula` varchar(45) NOT NULL,
  `nombre` text NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaCulminacion` date DEFAULT NULL,
  `cargo` varchar(45) NOT NULL,
  `salarioInicial` double NOT NULL,
  `salarioActual` int(11) DEFAULT NULL,
  `empleado_cedula` varchar(45) NOT NULL,
  PRIMARY KEY (`idContratacion`,`empleado_cedula`),
  KEY `fk_contratacion_empleado1_idx` (`empleado_cedula`),
  CONSTRAINT `fk_contratacion_empleado1` FOREIGN KEY (`empleado_cedula`) REFERENCES `empleado` (`cedula`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contratacion`
--

LOCK TABLES `contratacion` WRITE;
/*!40000 ALTER TABLE `contratacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `contratacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `FK_id_Usuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`cedula`),
  KEY `FK_id_Usuario` (`FK_id_Usuario`),
  CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`FK_id_Usuario`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES ('123','Juan','2017-08-12','Calle Santa Rita','Ventas','123413','2017-08-13 12:54:42',0,0,0,27);
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estructura_organizativa`
--

DROP TABLE IF EXISTS `estructura_organizativa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estructura_organizativa` (
  `idEstrutura` int(11) NOT NULL AUTO_INCREMENT,
  `departamento` varchar(45) NOT NULL,
  `funcion` varchar(45) NOT NULL,
  `jefeInmediato` varchar(45) NOT NULL,
  `usuario_idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idEstrutura`,`usuario_idUsuario`),
  KEY `fk_estructura_organizativa_usuario1_idx` (`usuario_idUsuario`),
  CONSTRAINT `fk_estructura_organizativa_usuario1` FOREIGN KEY (`usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estructura_organizativa`
--

LOCK TABLES `estructura_organizativa` WRITE;
/*!40000 ALTER TABLE `estructura_organizativa` DISABLE KEYS */;
/*!40000 ALTER TABLE `estructura_organizativa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nomina`
--

DROP TABLE IF EXISTS `nomina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nomina` (
  `idNomina` int(11) NOT NULL AUTO_INCREMENT,
  `nombreEmpleado` text NOT NULL,
  `diasHabiles` int(11) NOT NULL DEFAULT '11',
  `diasDescanso` int(11) NOT NULL DEFAULT '4',
  `bonoNocturno` double NOT NULL DEFAULT '0',
  `diasFeriados` int(11) NOT NULL DEFAULT '0',
  `prestamo` double NOT NULL,
  `diasNoLaborados` int(11) NOT NULL DEFAULT '0',
  `empleado_cedula` varchar(45) NOT NULL,
  PRIMARY KEY (`idNomina`,`empleado_cedula`),
  KEY `fk_nomina_empleado1_idx` (`empleado_cedula`),
  CONSTRAINT `fk_nomina_empleado1` FOREIGN KEY (`empleado_cedula`) REFERENCES `empleado` (`cedula`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nomina`
--

LOCK TABLES `nomina` WRITE;
/*!40000 ALTER TABLE `nomina` DISABLE KEYS */;
/*!40000 ALTER TABLE `nomina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pago_nomina`
--

DROP TABLE IF EXISTS `pago_nomina`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pago_nomina` (
  `idNominaPago` int(11) NOT NULL AUTO_INCREMENT,
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
  `empleado_cedula` varchar(45) NOT NULL,
  PRIMARY KEY (`idNominaPago`,`empleado_cedula`),
  KEY `fk_nomina_empleado1_idx` (`empleado_cedula`),
  CONSTRAINT `fk_nomina_empleado10` FOREIGN KEY (`empleado_cedula`) REFERENCES `empleado` (`cedula`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pago_nomina`
--

LOCK TABLES `pago_nomina` WRITE;
/*!40000 ALTER TABLE `pago_nomina` DISABLE KEYS */;
/*!40000 ALTER TABLE `pago_nomina` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seleccion_personal`
--

DROP TABLE IF EXISTS `seleccion_personal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seleccion_personal` (
  `idSeleccion` int(11) NOT NULL AUTO_INCREMENT,
  `nombreCandidato` text NOT NULL,
  `cedula` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `puestoPostulacion` varchar(45) NOT NULL,
  `disponibilidad` varchar(45) NOT NULL,
  `usuario_idUsuario` int(11) NOT NULL,
  PRIMARY KEY (`idSeleccion`,`usuario_idUsuario`),
  KEY `fk_seleccion_personal_usuario1_idx` (`usuario_idUsuario`),
  CONSTRAINT `fk_seleccion_personal_usuario1` FOREIGN KEY (`usuario_idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seleccion_personal`
--

LOCK TABLES `seleccion_personal` WRITE;
/*!40000 ALTER TABLE `seleccion_personal` DISABLE KEYS */;
/*!40000 ALTER TABLE `seleccion_personal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
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
  `status` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (27,'Ivans','1234','Ivans@gmail.com',1,'Lugar de nacimiento de la madre','Maracay','Profesor favorito','Aponte','2017-08-12','123',1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `valores`
--

DROP TABLE IF EXISTS `valores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `valores` (
  `idvalores` int(11) NOT NULL AUTO_INCREMENT,
  `salario` double DEFAULT NULL,
  `precioUnidadTributaria` varchar(45) DEFAULT NULL,
  `paroForzoso` varchar(45) DEFAULT NULL,
  `IVSS` varchar(45) DEFAULT NULL,
  `FAO` varchar(45) DEFAULT NULL,
  `diasUtilidades` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `fk_id_usuario` int(11) DEFAULT NULL,
  `contratacion_idContratacion` int(11) NOT NULL,
  PRIMARY KEY (`idvalores`,`contratacion_idContratacion`),
  KEY `Fk_id_usuario_idx` (`fk_id_usuario`),
  KEY `fk_valores_contratacion1_idx` (`contratacion_idContratacion`),
  CONSTRAINT `FK_id_usuario` FOREIGN KEY (`fk_id_usuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_valores_contratacion1` FOREIGN KEY (`contratacion_idContratacion`) REFERENCES `contratacion` (`idContratacion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valores`
--

LOCK TABLES `valores` WRITE;
/*!40000 ALTER TABLE `valores` DISABLE KEYS */;
/*!40000 ALTER TABLE `valores` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-13 18:56:46
