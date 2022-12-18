CREATE DATABASE  IF NOT EXISTS `restaurant` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `restaurant`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: restaurant
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `id` binary(16) NOT NULL DEFAULT (uuid_to_bin(uuid())),
  `description` varchar(255) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `title` varchar(45) NOT NULL,
  `restaurant_id` binary(16) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rdvwhbcur6qgx08dw7dtx0xp6` (`title`),
  KEY `FKblwdtxevpl4mrds8a12q0ohu6` (`restaurant_id`),
  CONSTRAINT `FKblwdtxevpl4mrds8a12q0ohu6` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (_binary '	\�A(mLĜáyz��','adadasd','2022-12-22 00:00:00.000000','2022-12-06 00:00:00.000000','Naujass',_binary '��\��I��\�\�\�^\� '),(_binary ' \�*/=G\��Q�d\0\�l','adsadd','2023-01-19 00:00:00.000000','2022-12-05 00:00:00.000000','Naujas',_binary '�۹��>F´��\�!�'),(_binary ',�D\�\n=G榍P�\��\�','asdad','2022-12-09 00:00:00.000000','2022-12-05 00:00:00.000000','sdads',_binary 'Ԏ~8�=Hаq(V�\�m'),(_binary '��\�3LEǐ�&o\�؀&','adsad','2022-12-16 00:00:00.000000','2022-12-05 00:00:00.000000','asdadasdads',_binary 'Ԏ~8�=Hаq(V�\�m'),(_binary '�/Tp\�E�d/���','asdad','2022-12-30 00:00:00.000000','2022-12-06 00:00:00.000000','asdasda',_binary 'Ԏ~8�=Hаq(V�\�m'),(_binary '�\�jM�\�@�N\�4\�zK�','adadads','2022-12-20 00:00:00.000000','2022-12-08 00:00:00.000000','adadadad',_binary 'Ԏ~8�=Hаq(V�\�m'),(_binary '\�\Z\�KgK̡���T&�','asdad','2022-12-30 00:00:00.000000','2022-12-12 00:00:00.000000','asdad',_binary '��\��I��\�\�\�^\� '),(_binary '\�6Z\�d�K瘧\��y\�3','adsd','2022-12-09 00:00:00.000000','2022-12-05 00:00:00.000000','adssadad',_binary 'Ԏ~8�=Hаq(V�\�m'),(_binary '\�%\�\ZH�jP#	2\�','America menu','2023-10-05 00:00:00.000000','2022-10-05 00:00:00.000000','Senas',_binary '�۹��>F´��\�!�'),(_binary '\�\�}p�Hϯ�\�l���\�','asdad','2022-12-30 00:00:00.000000','2022-12-13 00:00:00.000000','adsasd',_binary '��\��I��\�\�\�^\� '),(_binary '��b\�H��� r	\�\��','Menu aprašymas','2022-12-23 00:00:00.000000','2022-12-14 00:00:00.000000','Titulas',_binary '��\��I��\�\�\�^\� ');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-18 22:46:42
