-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: CODE_N_BUGS
-- ------------------------------------------------------
-- Server version	5.7.22-0ubuntu0.17.10.1

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
-- Table structure for table `ACCION`
--

DROP TABLE IF EXISTS `ACCION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ACCION` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre_Usuario` varchar(30) NOT NULL,
  `Id_Proyecto` int(11) NOT NULL,
  `Id_Caso` int(11) DEFAULT NULL,
  `Id_Etapa` int(11) DEFAULT NULL,
  `Fase_Proyecto` varchar(30) NOT NULL,
  `Tipo_Accion` varchar(30) NOT NULL,
  `Comentario` varchar(80) NOT NULL,
  `Aprobado` tinyint(1) DEFAULT NULL,
  `Revisada` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_PROYECTO_IN_ID_PROYECTO_2` (`Id_Proyecto`),
  KEY `FK_CASO_IN_ID_CASO_2` (`Id_Caso`),
  KEY `FK_ETAPA_IN_ID_ETAPA_2` (`Id_Etapa`),
  KEY `FK_USUARIO_IN_NOMBRE_USUARIO_2` (`Nombre_Usuario`),
  CONSTRAINT `FK_CASO_IN_ID_CASO_2` FOREIGN KEY (`Id_Caso`) REFERENCES `CASO` (`Id`),
  CONSTRAINT `FK_ETAPA_IN_ID_ETAPA_2` FOREIGN KEY (`Id_Etapa`) REFERENCES `ETAPA` (`Id`),
  CONSTRAINT `FK_PROYECTO_IN_ID_PROYECTO_2` FOREIGN KEY (`Id_Proyecto`) REFERENCES `PROYECTO` (`Id`),
  CONSTRAINT `FK_USUARIO_IN_NOMBRE_USUARIO_2` FOREIGN KEY (`Nombre_Usuario`) REFERENCES `USUARIO` (`Usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ACCION`
--

LOCK TABLES `ACCION` WRITE;
/*!40000 ALTER TABLE `ACCION` DISABLE KEYS */;
INSERT INTO `ACCION` VALUES (1,'Desarrollador 1',1,1,1,'Etapa','Finalizacion','Termine',1,NULL),(2,'Desarrollador 1',6,2,3,'Etapa','Finalizacion','Termine',1,NULL),(3,'Desarrollador 1',11,3,5,'Etapa','Finalizacion','Termine',1,NULL),(4,'Desarrollador 1',11,4,7,'Etapa','Finalizacion','Termine',1,NULL),(5,'Desarrollador 1',1,5,9,'Etapa','Finalizacion','Termine',1,NULL),(6,'Desarrollador 1',1,1,2,'Etapa','Finalizacion','Termine',1,NULL),(7,'Desarrollador 1',6,2,4,'Etapa','Finalizacion','Termine',1,NULL),(8,'Desarrollador 1',11,3,6,'Etapa','Finalizacion','Termine',1,NULL),(9,'Desarrollador 1',11,4,8,'Etapa','Finalizacion','Termine',1,NULL),(10,'Desarrollador 1',1,5,10,'Etapa','Finalizacion','Termine',1,NULL),(11,'Desarrollador 1',1,6,11,'Etapa','Finalizacion','Termine',1,NULL),(12,'Desarrollador 1',1,7,13,'Etapa','Finalizacion','Termine',1,NULL),(13,'Desarrollador 1',1,8,15,'Etapa','Finalizacion','Termine',1,NULL),(14,'Desarrollador 1',6,9,17,'Etapa','Finalizacion','Termine',1,NULL),(15,'Desarrollador 1',6,10,19,'Etapa','Finalizacion','Termine',1,NULL),(16,'Desarrollador 1',6,11,21,'Etapa','Finalizacion','Termine',1,NULL),(17,'Desarrollador 1',6,12,23,'Etapa','Finalizacion','Termine',1,NULL),(18,'Desarrollador 1',11,13,25,'Etapa','Finalizacion','Termine',1,NULL),(19,'Desarrollador 1',11,14,27,'Etapa','Finalizacion','Termine',1,NULL),(20,'Desarrollador 1',11,15,29,'Etapa','Finalizacion','Termine',1,NULL),(21,'Desarrollador 1',1,6,12,'Etapa','Finalizacion','Termine',1,NULL),(22,'Desarrollador 1',1,7,14,'Etapa','Finalizacion','Termine',1,NULL),(23,'Desarrollador 1',1,8,16,'Etapa','Finalizacion','Termine',1,NULL),(24,'Desarrollador 1',6,9,18,'Etapa','Finalizacion','Termine',1,NULL),(25,'Desarrollador 1',6,10,20,'Etapa','Finalizacion','Termine',1,NULL),(26,'Desarrollador 1',6,11,22,'Etapa','Finalizacion','Termine',1,NULL),(27,'Desarrollador 1',6,12,24,'Etapa','Finalizacion','Termine',1,NULL),(28,'Desarrollador 1',11,13,26,'Etapa','Finalizacion','Termine',1,NULL),(29,'Desarrollador 1',11,14,28,'Etapa','Finalizacion','Termine',1,NULL),(30,'Desarrollador 1',11,15,30,'Etapa','Finalizacion','Termine',1,NULL),(31,'Desarrollador 10',2,16,31,'Etapa','Finalizacion','Termine',1,NULL),(32,'Desarrollador 10',2,17,33,'Etapa','Finalizacion','Termine',1,NULL),(33,'Desarrollador 10',2,18,35,'Etapa','Finalizacion','Termine',1,NULL),(34,'Desarrollador 10',2,19,37,'Etapa','Finalizacion','Termine',1,NULL),(35,'Desarrollador 10',2,20,39,'Etapa','Finalizacion','Termine',1,NULL),(36,'Desarrollador 10',7,21,41,'Etapa','Finalizacion','Termine',1,NULL),(37,'Desarrollador 10',7,22,43,'Etapa','Finalizacion','Termine',1,NULL),(38,'Desarrollador 10',7,23,45,'Etapa','Finalizacion','Termine',1,NULL),(39,'Desarrollador 10',7,24,47,'Etapa','Finalizacion','Termine',1,NULL),(40,'Desarrollador 10',7,25,49,'Etapa','Finalizacion','Termine',1,NULL),(41,'Desarrollador 10',2,16,32,'Etapa','Finalizacion','Termine',1,NULL),(42,'Desarrollador 10',2,17,34,'Etapa','Finalizacion','Termine',1,NULL),(43,'Desarrollador 10',2,18,36,'Etapa','Finalizacion','Termine',1,NULL),(44,'Desarrollador 10',2,19,38,'Etapa','Finalizacion','Termine',1,NULL),(45,'Desarrollador 10',2,20,40,'Etapa','Finalizacion','Termine',1,NULL),(46,'Desarrollador 10',7,21,42,'Etapa','Finalizacion','Termine',1,NULL),(47,'Desarrollador 10',7,22,44,'Etapa','Finalizacion','Termine',1,NULL),(48,'Desarrollador 10',7,23,46,'Etapa','Finalizacion','Termine',1,NULL),(49,'Desarrollador 10',7,24,48,'Etapa','Finalizacion','Termine',1,NULL),(50,'Desarrollador 10',7,25,50,'Etapa','Finalizacion','Termine',1,NULL),(51,'Desarrollador 11',12,26,51,'Etapa','Finalizacion','Termine',1,NULL),(52,'Desarrollador 11',12,27,53,'Etapa','Finalizacion','Termine',1,NULL),(53,'Desarrollador 11',12,28,55,'Etapa','Finalizacion','Termine',1,NULL),(54,'Desarrollador 11',12,29,57,'Etapa','Finalizacion','Termine',1,NULL),(55,'Desarrollador 11',12,30,59,'Etapa','Finalizacion','Termine',1,NULL),(56,'Desarrollador 12',3,31,61,'Etapa','Finalizacion','Termine',1,NULL),(57,'Desarrollador 12',3,32,63,'Etapa','Finalizacion','Termine',1,NULL),(58,'Desarrollador 12',3,33,65,'Etapa','Finalizacion','Termine',1,NULL),(59,'Desarrollador 12',3,34,67,'Etapa','Finalizacion','Termine',1,NULL),(60,'Desarrollador 12',3,35,69,'Etapa','Finalizacion','Termine',1,NULL),(61,'Desarrollador 12',8,36,71,'Etapa','Finalizacion','Termine',1,NULL),(62,'Desarrollador 12',8,37,73,'Etapa','Finalizacion','Termine',1,NULL),(63,'Desarrollador 12',8,38,75,'Etapa','Finalizacion','Termine',1,NULL),(64,'Desarrollador 12',8,39,77,'Etapa','Finalizacion','Termine',1,NULL),(65,'Desarrollador 12',8,40,79,'Etapa','Finalizacion','Termine',1,NULL),(66,'Desarrollador 12',13,41,81,'Etapa','Finalizacion','Termine',1,NULL),(67,'Desarrollador 12',13,42,83,'Etapa','Finalizacion','Termine',1,NULL),(68,'Desarrollador 12',13,43,85,'Etapa','Finalizacion','Termine',1,NULL),(69,'Desarrollador 12',13,44,87,'Etapa','Finalizacion','Termine',1,NULL),(70,'Desarrollador 12',13,45,89,'Etapa','Finalizacion','Termine',1,NULL),(71,'Desarrollador 12',4,46,91,'Etapa','Finalizacion','Termine',1,NULL),(72,'Desarrollador 12',4,47,93,'Etapa','Finalizacion','Termine',1,NULL),(73,'Desarrollador 12',4,48,95,'Etapa','Finalizacion','Termine',1,NULL),(74,'Desarrollador 12',4,49,97,'Etapa','Finalizacion','Termine',1,NULL),(75,'Desarrollador 12',4,50,99,'Etapa','Finalizacion','Termine',1,NULL),(76,'PROYECTO 5',5,76,NULL,'Caso','Cancelacion Caso','PORQUE SI',0,1),(77,'PROYECTO 5',5,77,NULL,'Caso','Cancelacion Caso','PORQUE SI',0,1),(78,'PROYECTO 5',5,78,NULL,'Caso','Cancelacion Caso','PORQUE SI',0,1),(79,'PROYECTO 5',5,79,NULL,'Caso','Cancelacion Caso','PORQUE SI',0,1),(80,'PROYECTO 5',5,80,NULL,'Caso','Cancelacion Caso','PORQUE SI',0,1),(81,'PROYECTO 5',10,81,NULL,'Caso','Cancelacion Caso','PORQUE SI ',0,1),(82,'PROYECTO 5',10,82,NULL,'Caso','Cancelacion Caso','PORQUE SI ',0,1),(83,'PROYECTO 5',10,83,NULL,'Caso','Cancelacion Caso','PORQUE SI ',0,1),(84,'PROYECTO 5',10,84,NULL,'Caso','Cancelacion Caso','PORQUE SI ',0,1),(85,'PROYECTO 5',10,85,NULL,'Caso','Cancelacion Caso','PORQUE SI ',0,1),(86,'PROYECTO 5',15,86,NULL,'Caso','Cancelacion Caso','PORQUE SI',0,1),(87,'PROYECTO 5',15,87,NULL,'Caso','Cancelacion Caso','PORQUE SI',0,1),(88,'PROYECTO 5',15,88,NULL,'Caso','Cancelacion Caso','PORQUE SI',0,1),(89,'PROYECTO 5',15,89,NULL,'Caso','Cancelacion Caso','PORQUE SI',0,1),(90,'PROYECTO 5',15,90,NULL,'Caso','Cancelacion Caso','PORQUE SI',0,1),(91,'PROYECTO 4',4,91,NULL,'Caso','Cancelacion Caso','PORQUE SI',0,1),(92,'PROYECTO 4',4,92,NULL,'Caso','Cancelacion Caso','PORQUE SI',0,1),(93,'PROYECTO 4',4,93,NULL,'Caso','Cancelacion Caso','PORQUE SI',0,1),(94,'PROYECTO 4',4,94,NULL,'Caso','Cancelacion Caso','PORQUE SI',0,1),(95,'PROYECTO 4',4,95,NULL,'Caso','Cancelacion Caso','PORQUE SI',0,1),(96,'PROYECTO 4',9,96,NULL,'Caso','Cancelacion Caso','PORQUE SI',0,1),(97,'PROYECTO 4',9,97,NULL,'Caso','Cancelacion Caso','PORQUE SI',0,1),(98,'PROYECTO 4',9,98,NULL,'Caso','Cancelacion Caso','PORQUE SI',0,1),(99,'PROYECTO 4',9,99,NULL,'Caso','Cancelacion Caso','PORQUE SI',0,1),(100,'PROYECTO 4',9,100,NULL,'Caso','Cancelacion Caso','PORQUE SI',0,1);
/*!40000 ALTER TABLE `ACCION` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CASO`
--

DROP TABLE IF EXISTS `CASO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CASO` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(30) NOT NULL,
  `Id_Proyecto` int(11) NOT NULL,
  `Fecha_Limite` date NOT NULL,
  `Porcentaje_Avance` int(11) DEFAULT NULL,
  `Activo` tinyint(1) DEFAULT NULL,
  `Tipo` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_PROYECTO_IN_ID_PROYECTO` (`Id_Proyecto`),
  CONSTRAINT `FK_PROYECTO_IN_ID_PROYECTO` FOREIGN KEY (`Id_Proyecto`) REFERENCES `PROYECTO` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CASO`
--

LOCK TABLES `CASO` WRITE;
/*!40000 ALTER TABLE `CASO` DISABLE KEYS */;
INSERT INTO `CASO` VALUES (1,'Caso 1',1,'2018-09-23',100,1,'Tipo 1'),(2,'Caso 2',6,'2018-09-23',100,1,'Tipo 1'),(3,'Caso 2',11,'2018-09-23',100,1,'Tipo 1'),(4,'Caso 3',11,'2018-09-23',100,1,'Tipo 1'),(5,'Caso 4',1,'2018-09-23',100,1,'Tipo 1'),(6,'CASO',1,'2018-09-23',100,1,'Tipo 1'),(7,'CASO',1,'2018-09-23',100,1,'Tipo 1'),(8,'CASO',1,'2018-09-23',100,1,'Tipo 1'),(9,'CASO',6,'2018-09-23',100,1,'Tipo 1'),(10,'CASO',6,'2018-09-23',100,1,'Tipo 1'),(11,'CASO',6,'2018-09-23',100,1,'Tipo 1'),(12,'CASO',6,'2018-09-23',100,1,'Tipo 1'),(13,'CASO',11,'2018-09-23',100,1,'Tipo 1'),(14,'CASO',11,'2018-09-23',100,1,'Tipo 1'),(15,'CASO',11,'2018-09-23',100,1,'Tipo 1'),(16,'CASO',2,'2018-09-23',100,1,'Tipo 1'),(17,'CASO',2,'2018-09-23',100,1,'Tipo 1'),(18,'CASO',2,'2018-09-23',100,1,'Tipo 1'),(19,'CASO',2,'2018-09-23',100,1,'Tipo 1'),(20,'CASO',2,'2018-09-23',100,1,'Tipo 1'),(21,'CASO',7,'2018-09-23',100,1,'Tipo 1'),(22,'CASO',7,'2018-09-23',100,1,'Tipo 1'),(23,'CASO',7,'2018-09-23',100,1,'Tipo 1'),(24,'CASO',7,'2018-09-23',100,1,'Tipo 1'),(25,'CASO',7,'2018-09-23',100,1,'Tipo 1'),(26,'CASO',12,'2018-09-23',50,1,'Tipo 1'),(27,'CASO',12,'2018-09-23',50,1,'Tipo 1'),(28,'CASO',12,'2018-09-23',50,1,'Tipo 1'),(29,'CASO',12,'2018-09-23',50,1,'Tipo 1'),(30,'CASO',12,'2018-09-23',50,1,'Tipo 1'),(31,'CASO',3,'2018-09-23',50,1,'Tipo 1'),(32,'CASO',3,'2018-09-23',50,1,'Tipo 1'),(33,'CASO',3,'2018-09-23',50,1,'Tipo 1'),(34,'CASO',3,'2018-09-23',50,1,'Tipo 1'),(35,'CASO',3,'2018-09-23',50,1,'Tipo 1'),(36,'CASO',8,'2018-09-23',50,1,'Tipo 1'),(37,'CASO',8,'2018-09-23',50,1,'Tipo 1'),(38,'CASO',8,'2018-09-23',50,1,'Tipo 1'),(39,'CASO',8,'2018-09-23',50,1,'Tipo 1'),(40,'CASO',8,'2018-09-23',50,1,'Tipo 1'),(41,'CASO',13,'2018-09-23',50,1,'Tipo 1'),(42,'CASO',13,'2018-09-23',50,1,'Tipo 1'),(43,'CASO',13,'2018-09-23',50,1,'Tipo 1'),(44,'CASO',13,'2018-09-23',50,1,'Tipo 1'),(45,'CASO',13,'2018-09-23',50,1,'Tipo 1'),(46,'CASO',4,'2018-09-23',50,1,'Tipo 1'),(47,'CASO',4,'2018-09-23',50,1,'Tipo 1'),(48,'CASO',4,'2018-09-23',50,1,'Tipo 1'),(49,'CASO',4,'2018-09-23',50,1,'Tipo 1'),(50,'CASO',4,'2018-09-23',50,1,'Tipo 1'),(51,'CASO',9,'2018-09-23',0,1,'Tipo 1'),(52,'CASO',9,'2018-09-23',0,1,'Tipo 1'),(53,'CASO',9,'2018-09-23',0,1,'Tipo 1'),(54,'CASO',9,'2018-09-23',0,1,'Tipo 1'),(55,'CASO',9,'2018-09-23',0,1,'Tipo 1'),(56,'CASO',14,'2018-09-23',0,1,'Tipo 1'),(57,'CASO',14,'2018-09-23',0,1,'Tipo 1'),(58,'CASO',14,'2018-09-23',0,1,'Tipo 1'),(59,'CASO',14,'2018-09-23',0,1,'Tipo 1'),(60,'CASO',14,'2018-09-23',0,1,'Tipo 1'),(61,'CASO',5,'2018-09-23',0,1,'Tipo 1'),(62,'CASO',5,'2018-09-23',0,1,'Tipo 1'),(63,'CASO',5,'2018-09-23',0,1,'Tipo 1'),(64,'CASO',5,'2018-09-23',0,1,'Tipo 1'),(65,'CASO',5,'2018-09-23',0,1,'Tipo 1'),(66,'CASO',10,'2018-09-23',0,1,'Tipo 1'),(67,'CASO',10,'2018-09-23',0,1,'Tipo 1'),(68,'CASO',10,'2018-09-23',0,1,'Tipo 1'),(69,'CASO',10,'2018-09-23',0,1,'Tipo 1'),(70,'CASO',10,'2018-09-23',0,1,'Tipo 1'),(71,'CASO',15,'2018-09-23',0,1,'Tipo 1'),(72,'CASO',15,'2018-09-23',0,1,'Tipo 1'),(73,'CASO',15,'2018-09-23',0,1,'Tipo 1'),(74,'CASO',15,'2018-09-23',0,1,'Tipo 1'),(75,'CASO',15,'2018-09-23',0,1,'Tipo 1'),(76,'CASO',5,'2018-09-23',0,0,'Tipo 1'),(77,'CASO',5,'2018-09-23',0,0,'Tipo 1'),(78,'CASO',5,'2018-09-23',0,0,'Tipo 1'),(79,'CASO',5,'2018-09-23',0,0,'Tipo 1'),(80,'CASO',5,'2018-09-23',0,0,'Tipo 1'),(81,'ETAPA',10,'2018-09-23',0,0,'Tipo 1'),(82,'CASO',10,'2018-09-23',0,0,'Tipo 1'),(83,'CASO',10,'2018-09-23',0,0,'Tipo 1'),(84,'CASO',10,'2018-09-23',0,0,'Tipo 1'),(85,'CASO',10,'2018-09-23',0,0,'Tipo 1'),(86,'CASO',15,'2018-09-23',0,0,'Tipo 1'),(87,'CASO',15,'2018-09-23',0,0,'Tipo 1'),(88,'CASO',15,'2018-09-23',0,0,'Tipo 1'),(89,'CASO',15,'2018-09-23',0,0,'Tipo 1'),(90,'CASO',15,'2018-09-23',0,0,'Tipo 1'),(91,'CASO',4,'2018-09-23',0,0,'Tipo 1'),(92,'CASO',4,'2018-09-23',0,0,'Tipo 1'),(93,'CASO',4,'2018-09-23',0,0,'Tipo 1'),(94,'CASO',4,'2018-09-23',0,0,'Tipo 1'),(95,'CASO',4,'2018-09-23',0,0,'Tipo 1'),(96,'CASO',9,'2018-09-23',0,0,'Tipo 1'),(97,'CASO',9,'2018-09-23',0,0,'Tipo 1'),(98,'CASO',9,'2018-09-23',0,0,'Tipo 1'),(99,'CASO',9,'2018-09-23',0,0,'Tipo 1'),(100,'CASO',9,'2018-09-23',0,0,'Tipo 1');
/*!40000 ALTER TABLE `CASO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ETAPA`
--

DROP TABLE IF EXISTS `ETAPA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ETAPA` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(30) NOT NULL,
  `Desarrollador_A_Cargo` varchar(30) DEFAULT NULL,
  `Activo` tinyint(1) DEFAULT NULL,
  `Tiempo_En_Horas` int(11) DEFAULT NULL,
  `Aprobado` tinyint(1) DEFAULT NULL,
  `Fecha_Limite` date DEFAULT NULL,
  `Id_Caso` int(11) NOT NULL,
  `Total` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_USUARIO_IN_DESARROLLADOR_A_CARGO` (`Desarrollador_A_Cargo`),
  KEY `FK_CASO_IN_ID_CASO` (`Id_Caso`),
  CONSTRAINT `FK_CASO_IN_ID_CASO` FOREIGN KEY (`Id_Caso`) REFERENCES `CASO` (`Id`),
  CONSTRAINT `FK_USUARIO_IN_DESARROLLADOR_A_CARGO` FOREIGN KEY (`Desarrollador_A_Cargo`) REFERENCES `USUARIO` (`Usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=201 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ETAPA`
--

LOCK TABLES `ETAPA` WRITE;
/*!40000 ALTER TABLE `ETAPA` DISABLE KEYS */;
INSERT INTO `ETAPA` VALUES (1,'Etapa1','Desarrollador 1',1,1,1,'2018-09-23',1,5),(2,'Etapa2','Desarrollador 1',1,1,1,'2018-09-23',1,5),(3,'Etapa3','Desarrollador 1',1,1,1,'2018-09-23',2,5),(4,'Etapa4','Desarrollador 1',1,1,1,'2018-09-23',2,5),(5,'Etapa 5','Desarrollador 1',1,1,1,'2018-09-23',3,5),(6,'Etapa6','Desarrollador 1',1,1,1,'2018-09-23',3,5),(7,'Etapa7','Desarrollador 1',1,1,1,'2018-09-23',4,5),(8,'Etapa8','Desarrollador 1',1,1,1,'2018-09-23',4,5),(9,'Etapa9','Desarrollador 1',1,1,1,'2018-09-23',5,5),(10,'Etapa10','Desarrollador 1',1,1,1,'2018-09-23',5,5),(11,'ETAPA','Desarrollador 1',1,1,1,'2018-09-23',6,5),(12,'ETAPA','Desarrollador 1',1,1,1,'2018-09-23',6,5),(13,'ETAPA','Desarrollador 1',1,1,1,'2018-09-23',7,5),(14,'ETAPA','Desarrollador 1',1,1,1,'2018-09-23',7,5),(15,'ETAPA','Desarrollador 1',1,1,1,'2018-09-23',8,5),(16,'ETAPA','Desarrollador 1',1,1,1,'2018-09-23',8,5),(17,'ETAPA','Desarrollador 1',1,1,1,'2018-09-23',9,5),(18,'ETAPA','Desarrollador 1',1,1,1,'2018-09-23',9,5),(19,'ETAPA','Desarrollador 1',1,1,1,'2018-09-23',10,5),(20,'ETAPA','Desarrollador 1',1,1,1,'2018-09-23',10,5),(21,'ETAPA','Desarrollador 1',1,1,1,'2018-09-23',11,5),(22,'ETAPA','Desarrollador 1',1,1,1,'2018-09-23',11,5),(23,'ETAPA','Desarrollador 1',1,1,1,'2018-09-23',12,5),(24,'ETAPA','Desarrollador 1',1,1,1,'2018-09-23',12,5),(25,'ETAPA','Desarrollador 1',1,1,1,'2018-09-23',13,5),(26,'ETAPA','Desarrollador 1',1,1,1,'2018-09-23',13,5),(27,'CASO','Desarrollador 1',1,1,1,'2018-09-23',14,5),(28,'ETAPA','Desarrollador 1',1,1,1,'2018-09-23',14,5),(29,'ETAPA','Desarrollador 1',1,1,1,'2018-09-23',15,5),(30,'ETAPA','Desarrollador 1',1,1,1,'2018-09-23',15,5),(31,'ETAPA','Desarrollador 10',1,1,1,'2018-09-23',16,10),(32,'ETAPA','Desarrollador 10',1,1,1,'2018-09-23',16,10),(33,'ETAPA','Desarrollador 10',1,1,1,'2018-09-23',17,10),(34,'ETAPA','Desarrollador 10',1,1,1,'2018-09-23',17,10),(35,'ETAPA','Desarrollador 10',1,1,1,'2018-09-23',18,10),(36,'ETAPA','Desarrollador 10',1,1,1,'2018-09-23',18,10),(37,'ETAPA','Desarrollador 10',1,1,1,'2018-09-23',19,10),(38,'ETAPA','Desarrollador 10',1,1,1,'2018-09-23',19,10),(39,'ETAPA','Desarrollador 10',1,1,1,'2018-09-23',20,10),(40,'ETAPA','Desarrollador 10',1,1,1,'2018-09-23',20,10),(41,'ETAPA','Desarrollador 10',1,1,1,'2018-09-23',21,10),(42,'ETAPA','Desarrollador 10',1,1,1,'2018-09-23',21,10),(43,'ETAPA','Desarrollador 10',1,1,1,'2018-09-23',22,10),(44,'ETAPA','Desarrollador 10',1,1,1,'2018-09-23',22,10),(45,'ETAPA','Desarrollador 10',1,1,1,'2018-09-23',23,10),(46,'ETAPA','Desarrollador 10',1,1,1,'2018-09-23',23,10),(47,'ETAPA','Desarrollador 10',1,1,1,'2018-09-23',24,10),(48,'ETAPA','Desarrollador 10',1,1,1,'2018-09-23',24,10),(49,'ETAPA','Desarrollador 10',1,1,1,'2018-09-23',25,10),(50,'ETAPA','Desarrollador 10',1,1,1,'2018-09-23',25,10),(51,'ETAPA','Desarrollador 11',1,1,1,'2018-09-23',26,11),(52,'ETAPA',NULL,NULL,NULL,NULL,NULL,26,NULL),(53,'ETAPA','Desarrollador 11',1,1,1,'2018-09-23',27,11),(54,'ETAPA','Desarrollador 11',1,NULL,NULL,'2018-09-23',27,NULL),(55,'ETAPA','Desarrollador 11',1,1,1,'2018-09-23',28,11),(56,'ETAPA','Desarrollador 11',1,NULL,NULL,'2018-09-23',28,NULL),(57,'ETAPA','Desarrollador 11',1,1,1,'2018-09-23',29,11),(58,'ETAPA','Desarrollador 11',1,NULL,NULL,'2018-09-23',29,NULL),(59,'ETAPA','Desarrollador 11',1,1,1,'2018-09-23',30,11),(60,'ETAPA','Desarrollador 11',1,NULL,NULL,'2018-09-23',30,NULL),(61,'ETAPA','Desarrollador 12',1,1,1,'2018-09-23',31,12),(62,'ETAPA','Desarrollador 12',1,NULL,NULL,'2018-09-23',31,NULL),(63,'ETAPA','Desarrollador 12',1,1,1,'2018-09-23',32,12),(64,'ETAPA','Desarrollador 12',1,NULL,NULL,'2018-09-23',32,NULL),(65,'ETAPA','Desarrollador 12',1,1,1,'2018-09-23',33,12),(66,'ETAPA','Desarrollador 12',1,NULL,NULL,'2018-09-23',33,NULL),(67,'ETAPA','Desarrollador 12',1,1,1,'2018-09-23',34,12),(68,'ETAPA','Desarrollador 12',1,NULL,NULL,'2018-09-23',34,NULL),(69,'ETAPA','Desarrollador 12',1,1,1,'2018-09-23',35,12),(70,'ETAPA','Desarrollador 12',1,NULL,NULL,'2018-09-23',35,NULL),(71,'ETAPA','Desarrollador 12',1,1,1,'2018-09-23',36,12),(72,'ETAPA','Desarrollador 12',1,NULL,NULL,'2018-09-23',36,NULL),(73,'ETAPA','Desarrollador 12',1,1,1,'2018-09-23',37,12),(74,'ETAPA','Desarrollador 12',1,NULL,NULL,'2018-09-23',37,NULL),(75,'ETAPA','Desarrollador 12',1,1,1,'2018-09-23',38,12),(76,'ETAPA','Desarrollador 12',1,NULL,NULL,'2018-09-23',38,NULL),(77,'ETAPA','Desarrollador 12',1,1,1,'2018-09-23',39,12),(78,'ETAPA','Desarrollador 12',1,NULL,NULL,'2018-09-23',39,NULL),(79,'ETAPA','Desarrollador 12',1,1,1,'2018-09-23',40,12),(80,'ETAPA','Desarrollador 12',1,NULL,NULL,'2018-09-23',40,NULL),(81,'ETAPA','Desarrollador 12',1,1,1,'2018-09-23',41,12),(82,'ETAPA','Desarrollador 12',1,NULL,NULL,'2018-09-23',41,NULL),(83,'ETAPA','Desarrollador 12',1,1,1,'2018-09-23',42,12),(84,'ETAPA','Desarrollador 12',1,NULL,NULL,'2018-09-23',42,NULL),(85,'ETAPA','Desarrollador 12',1,1,1,'2018-09-23',43,12),(86,'ETAPA','Desarrollador 12',1,NULL,NULL,'2018-09-23',43,NULL),(87,'ETAPA','Desarrollador 12',1,1,1,'2018-09-23',44,12),(88,'ETAPA','Desarrollador 12',1,NULL,NULL,'2018-09-23',44,NULL),(89,'ETAPA','Desarrollador 12',1,1,1,'2018-09-23',45,12),(90,'ETAPA','Desarrollador 12',1,NULL,NULL,'2018-09-23',45,NULL),(91,'ETAPA','Desarrollador 12',1,1,1,'2018-09-23',46,12),(92,'ETAPA','Desarrollador 12',1,NULL,NULL,'2018-09-23',46,NULL),(93,'ETAPA','Desarrollador 12',1,1,1,'2018-09-23',47,12),(94,'ETAPA','Desarrollador 12',1,NULL,NULL,'2018-09-23',47,NULL),(95,'ETAPA','Desarrollador 12',1,1,1,'2018-09-23',48,12),(96,'ETAPA','Desarrollador 12',1,NULL,NULL,'2018-09-23',48,NULL),(97,'ETAPA','Desarrollador 12',1,1,1,'2018-09-23',49,12),(98,'ETAPA','Desarrollador 12',1,NULL,NULL,'2018-09-23',49,NULL),(99,'ETAPA','Desarrollador 12',1,1,1,'2018-09-23',50,12),(100,'ETAPA','Desarrollador 12',1,NULL,NULL,'2018-09-23',50,NULL),(101,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',51,NULL),(102,'ETAPA',NULL,NULL,NULL,NULL,NULL,51,NULL),(103,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',52,NULL),(104,'ETAPA',NULL,NULL,NULL,NULL,NULL,52,NULL),(105,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',53,NULL),(106,'ETAPA',NULL,NULL,NULL,NULL,NULL,53,NULL),(107,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',54,NULL),(108,'ETAPA',NULL,NULL,NULL,NULL,NULL,54,NULL),(109,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',55,NULL),(110,'ETAPA',NULL,NULL,NULL,NULL,NULL,55,NULL),(111,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',56,NULL),(112,'ETAPA',NULL,NULL,NULL,NULL,NULL,56,NULL),(113,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',57,NULL),(114,'ETAPA',NULL,NULL,NULL,NULL,NULL,57,NULL),(115,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',58,NULL),(116,'ETAPA',NULL,NULL,NULL,NULL,NULL,58,NULL),(117,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',59,NULL),(118,'ETAPA',NULL,NULL,NULL,NULL,NULL,59,NULL),(119,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',60,NULL),(120,'ETAPA',NULL,NULL,NULL,NULL,NULL,60,NULL),(121,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',61,NULL),(122,'ETAPA',NULL,NULL,NULL,NULL,NULL,61,NULL),(123,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',62,NULL),(124,'ETAPA',NULL,NULL,NULL,NULL,NULL,62,NULL),(125,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',63,NULL),(126,'ETAPA',NULL,NULL,NULL,NULL,NULL,63,NULL),(127,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',64,NULL),(128,'ETAPA',NULL,NULL,NULL,NULL,NULL,64,NULL),(129,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',65,NULL),(130,'ETAPA',NULL,NULL,NULL,NULL,NULL,65,NULL),(131,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',66,NULL),(132,'ETAPA',NULL,NULL,NULL,NULL,NULL,66,NULL),(133,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',67,NULL),(134,'ETAPA',NULL,NULL,NULL,NULL,NULL,67,NULL),(135,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',68,NULL),(136,'ETAPA',NULL,NULL,NULL,NULL,NULL,68,NULL),(137,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',69,NULL),(138,'ETAPA',NULL,NULL,NULL,NULL,NULL,69,NULL),(139,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',70,NULL),(140,'ETAPA',NULL,NULL,NULL,NULL,NULL,70,NULL),(141,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',71,NULL),(142,'ETAPA',NULL,NULL,NULL,NULL,NULL,71,NULL),(143,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',72,NULL),(144,'ETAPA',NULL,NULL,NULL,NULL,NULL,72,NULL),(145,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',73,NULL),(146,'ETAPA',NULL,NULL,NULL,NULL,NULL,73,NULL),(147,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',74,NULL),(148,'ETAPA',NULL,NULL,NULL,NULL,NULL,74,NULL),(149,'ETAPA','Desarrollador 13',1,NULL,NULL,'2018-09-23',75,NULL),(150,'ETAPA',NULL,NULL,NULL,NULL,NULL,75,NULL),(151,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',76,NULL),(152,'ETAPA',NULL,NULL,NULL,NULL,NULL,76,NULL),(153,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',77,NULL),(154,'ETAPA',NULL,NULL,NULL,NULL,NULL,77,NULL),(155,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',78,NULL),(156,'ETAPA',NULL,NULL,NULL,NULL,NULL,78,NULL),(157,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',79,NULL),(158,'ETAPA',NULL,NULL,NULL,NULL,NULL,79,NULL),(159,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',80,NULL),(160,'ETAPA',NULL,NULL,NULL,NULL,NULL,80,NULL),(161,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',81,NULL),(162,'ETAPA',NULL,NULL,NULL,NULL,NULL,81,NULL),(163,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',82,NULL),(164,'ETAPA',NULL,NULL,NULL,NULL,NULL,82,NULL),(165,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',83,NULL),(166,'ETAPA',NULL,NULL,NULL,NULL,NULL,83,NULL),(167,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',84,NULL),(168,'ETAPA',NULL,NULL,NULL,NULL,NULL,84,NULL),(169,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',85,NULL),(170,'ETAPA',NULL,NULL,NULL,NULL,NULL,85,NULL),(171,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',86,NULL),(172,'ETAPA',NULL,NULL,NULL,NULL,NULL,86,NULL),(173,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',87,NULL),(174,'ETAPA',NULL,NULL,NULL,NULL,NULL,87,NULL),(175,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',88,NULL),(176,'ETAPA',NULL,NULL,NULL,NULL,NULL,88,NULL),(177,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',89,NULL),(178,'ETAPA',NULL,NULL,NULL,NULL,NULL,89,NULL),(179,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',90,NULL),(180,'ETAPA',NULL,NULL,NULL,NULL,NULL,90,NULL),(181,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',91,NULL),(182,'ETAPA',NULL,NULL,NULL,NULL,NULL,91,NULL),(183,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',92,NULL),(184,'ETAPA',NULL,NULL,NULL,NULL,NULL,92,NULL),(185,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',93,NULL),(186,'ETAPA',NULL,NULL,NULL,NULL,NULL,93,NULL),(187,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',94,NULL),(188,'ETAPA',NULL,NULL,NULL,NULL,NULL,94,NULL),(189,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',95,NULL),(190,'ETAPA',NULL,NULL,NULL,NULL,NULL,95,NULL),(191,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',96,NULL),(192,'ETAPA',NULL,NULL,NULL,NULL,NULL,96,NULL),(193,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',97,NULL),(194,'ETAPA',NULL,NULL,NULL,NULL,NULL,97,NULL),(195,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',98,NULL),(196,'ETAPA',NULL,NULL,NULL,NULL,NULL,98,NULL),(197,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',99,NULL),(198,'ETAPA',NULL,NULL,NULL,NULL,NULL,99,NULL),(199,'ETAPA','Desarrollador 14',1,NULL,NULL,'2018-09-23',100,NULL),(200,'ETAPA',NULL,NULL,NULL,NULL,NULL,100,NULL);
/*!40000 ALTER TABLE `ETAPA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PROYECTO`
--

DROP TABLE IF EXISTS `PROYECTO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PROYECTO` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(30) NOT NULL,
  `Fecha_Limite` date NOT NULL,
  `Activo` tinyint(1) NOT NULL,
  `Administrador_Proyecto` varchar(30) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FK_USUARIO_IN_ADMINISTRADOR_PROYECTO` (`Administrador_Proyecto`),
  CONSTRAINT `FK_USUARIO_IN_ADMINISTRADOR_PROYECTO` FOREIGN KEY (`Administrador_Proyecto`) REFERENCES `USUARIO` (`Usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PROYECTO`
--

LOCK TABLES `PROYECTO` WRITE;
/*!40000 ALTER TABLE `PROYECTO` DISABLE KEYS */;
INSERT INTO `PROYECTO` VALUES (1,'Proyecto 1','2018-09-10',1,'Proyecto 1'),(2,'Proyecto 2','2018-09-10',1,'Proyecto 2'),(3,'Proyecto 3','2018-09-10',1,'Proyecto 3'),(4,'Proyecto 4','2018-09-10',1,'Proyecto 4'),(5,'Proyecto 5','2018-09-10',1,'Proyecto 5'),(6,'Proyecto 6','2018-09-10',1,'Proyecto 1'),(7,'Proyecto 7','2018-09-10',1,'Proyecto 2'),(8,'Proyecto 8','2018-09-10',1,'Proyecto 3'),(9,'Proyecto 9','2018-09-10',1,'Proyecto 4'),(10,'Proyecto 10','2018-09-10',1,'Proyecto 5'),(11,'Proyecto 11','2018-09-10',1,'Proyecto 1'),(12,'Proyecto 12','2018-09-10',1,'Proyecto 2'),(13,'Proyecto 13','2018-09-10',1,'Proyecto 3'),(14,'Proyecto 14','2018-09-10',1,'Proyecto 4'),(15,'Proyecto 15','2018-09-10',1,'Proyecto 5'),(16,'Proyecto 16','2018-09-10',0,'Proyecto 1'),(17,'Proyecto 17','2018-09-10',0,'Proyecto 2'),(18,'Proyecto 18','2018-09-10',0,'Proyecto 3'),(19,'Proyecto 19','2018-09-10',0,'Proyecto 3'),(20,'Proyecto 20','2018-09-10',0,'Proyecto 5');
/*!40000 ALTER TABLE `PROYECTO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TIPO_CASO`
--

DROP TABLE IF EXISTS `TIPO_CASO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TIPO_CASO` (
  `Nombre` varchar(30) NOT NULL,
  `Etapas` int(11) NOT NULL,
  PRIMARY KEY (`Nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TIPO_CASO`
--

LOCK TABLES `TIPO_CASO` WRITE;
/*!40000 ALTER TABLE `TIPO_CASO` DISABLE KEYS */;
INSERT INTO `TIPO_CASO` VALUES ('Tipo 1',2),('Tipo 2',2),('Tipo 3',2),('Tipo 4',2),('Tipo 5',2),('Tipo 6',3),('Tipo 7',4),('Tipo 8',5);
/*!40000 ALTER TABLE `TIPO_CASO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USUARIO`
--

DROP TABLE IF EXISTS `USUARIO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USUARIO` (
  `Usuario` varchar(30) NOT NULL,
  `Contraseña` varchar(10) NOT NULL,
  `Nombre` varchar(30) NOT NULL,
  `Apellido` varchar(30) NOT NULL,
  `Dpi` varchar(15) NOT NULL,
  `Fecha_Ingreso` date NOT NULL,
  `Dinero_Hora` int(11) NOT NULL,
  `Tipo_Usuario` varchar(30) NOT NULL,
  PRIMARY KEY (`Usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USUARIO`
--

LOCK TABLES `USUARIO` WRITE;
/*!40000 ALTER TABLE `USUARIO` DISABLE KEYS */;
INSERT INTO `USUARIO` VALUES ('admin','1','administrador','base','123456789','2018-09-03',300,'administrador sistema'),('Desarrollador 1','1','Desarrollador 1','Desarrollador 1','1','2018-09-10',5,'desarrollador'),('Desarrollador 10','10','Desarrollador 10','Desarrollador 10','10','2018-09-10',10,'desarrollador'),('Desarrollador 11','11','Desarrollador 11','Desarrollador 11','11','2018-09-10',11,'desarrollador'),('Desarrollador 12','12','Desarrollador 12','Desarrollador 12','12','2018-09-10',12,'desarrollador'),('Desarrollador 13','13','Desarrollador 13','Desarrollador 13','13','2018-09-10',13,'desarrollador'),('Desarrollador 14','14','Desarrollador 14','Desarrollador 14','14','2018-09-10',14,'desarrollador'),('Desarrollador 15','15','Desarrollador 15','Desarrollador 15','15','2018-09-10',15,'desarrollador'),('Desarrollador 16','16','Desarrollador 16','Desarrollador 16','16','2018-09-10',16,'desarrollador'),('Desarrollador 17','17','Desarrollador 17','Desarrollador 17','17','2018-09-10',17,'desarrollador'),('Desarrollador 18','18','Desarrollador 18','Desarrollador 18','18','2018-09-10',18,'desarrollador'),('Desarrollador 19','19','Desarrollador 19','Desarrollador 19','19','2018-09-10',19,'desarrollador'),('Desarrollador 2','2','Desarrollador 2','Desarrollador 2','2','2018-09-10',5,'desarrollador'),('Desarrollador 20','20','Desarrollador 20','Desarrollador 20','20','2018-09-10',20,'desarrollador'),('Desarrollador 3','3','Desarrollador 3','Desarrollador 3','3','2018-09-10',5,'desarrollador'),('Desarrollador 4','4','Desarrollador 4','Desarrollador 4','4','2018-09-10',5,'desarrollador'),('Desarrollador 5','5','Desarrollador 5','Desarrollador 5','5','2018-09-10',5,'desarrollador'),('Desarrollador 6','6','Desarrollador 6','Desarrollador 6','6','2018-09-10',6,'desarrollador'),('Desarrollador 7','7','Desarrollador 7','Desarrollador 7','7','2018-09-10',7,'desarrollador'),('Desarrollador 8','8','Desarrollador 8','Desarrollador 8','8','2018-09-10',8,'desarrollador'),('Desarrollador 9','9','Desarrollador 9','Desarrollador 9','9','2018-09-10',9,'desarrollador'),('Proyecto 1','1','Proyecto 1','Proyecto 1','1','2018-09-10',1,'administrador proyecto'),('Proyecto 2','2','Proyecto 2','Proyecto 2','2','2018-09-10',2,'administrador proyecto'),('Proyecto 3','3','Proyecto 3','Proyecto 3','3','2018-09-10',3,'administrador proyecto'),('Proyecto 4','4','Proyecto 4','Proyecto 4','4','2018-09-10',4,'administrador proyecto'),('Proyecto 5','5','Proyecto 5','Proyecto 5','5','2018-09-10',5,'administrador proyecto');
/*!40000 ALTER TABLE `USUARIO` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-09-11  1:05:12
