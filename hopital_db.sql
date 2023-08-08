CREATE DATABASE  IF NOT EXISTS `hopital` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hopital`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: hopital
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `acte`
--

DROP TABLE IF EXISTS `acte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `acte` (
  `idacte` int NOT NULL AUTO_INCREMENT,
  `libelle_acte` varchar(45) DEFAULT NULL,
  `num_acte` varchar(45) DEFAULT NULL,
  `examen` varchar(45) DEFAULT NULL,
  `prix` int DEFAULT NULL,
  `date_acte` varchar(45) DEFAULT NULL,
  `etat` varchar(45) DEFAULT 'Non Payer',
  `diagnostic` varchar(45) DEFAULT 'Non Fait',
  `id_patient` int DEFAULT NULL,
  `id_service` int DEFAULT NULL,
  `id_users` int DEFAULT NULL,
  `date_diag` varchar(45) DEFAULT NULL,
  `identite_patient` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idacte`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acte`
--

LOCK TABLES `acte` WRITE;
/*!40000 ALTER TABLE `acte` DISABLE KEYS */;
INSERT INTO `acte` VALUES (122,'Consultation','0','Pas d\'examen',2000,'31-07-2023','Payer','Non Fait',12,1,51,NULL,'TEST testament'),(123,'Consultation','1','Pas d\'examen',2000,'04-08-2023','Payer','Fait',11,1,51,'04-08-2023','TEST test'),(124,'Examen','2','Analyse cardio vasculaire',5000,'04-08-2023','Payer','Non Fait',11,1,51,NULL,'TEST test'),(125,'Consultation','3','Pas d\'examen',2000,'07-08-2023','Payer','Non Fait',13,1,51,NULL,'HHH hhhh'),(126,'Examen','4','Analyse cardio vasculaire',5000,'07-08-2023','Payer','Non Fait',13,1,51,NULL,'HHH hhhh');
/*!40000 ALTER TABLE `acte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dm`
--

DROP TABLE IF EXISTS `dm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dm` (
  `idDm` int NOT NULL AUTO_INCREMENT,
  `nume_acte` varchar(45) DEFAULT NULL,
  `dm_id_patient` int DEFAULT NULL,
  `dm_ser` int DEFAULT NULL,
  `infirmier` varchar(45) DEFAULT NULL,
  `adp` char(30) DEFAULT NULL,
  `mdv` char(30) DEFAULT NULL,
  `pp` varchar(25) DEFAULT NULL,
  `ta` varchar(15) DEFAULT NULL,
  `al` varchar(45) DEFAULT NULL,
  `Medecin` varchar(45) DEFAULT NULL,
  `traitement` varchar(45) DEFAULT 'Non traite',
  `date_traitement` varchar(45) DEFAULT 'Non traite',
  `tp` varchar(45) DEFAULT NULL,
  `pd` varchar(45) DEFAULT NULL,
  `pa` varchar(45) DEFAULT NULL,
  `identite` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idDm`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dm`
--

LOCK TABLES `dm` WRITE;
/*!40000 ALTER TABLE `dm` DISABLE KEYS */;
INSERT INTO `dm` VALUES (35,'1',11,1,'INF inf','test','test','test','test','test','HOUNDEGLE LOIC','a','04-08-2023','38','40','120/80','TEST test');
/*!40000 ALTER TABLE `dm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `idpatient` int NOT NULL AUTO_INCREMENT,
  `nom_patient` varchar(45) DEFAULT NULL,
  `prenom_patient` varchar(45) DEFAULT NULL,
  `date_naissance` varchar(45) DEFAULT NULL,
  `sexe` varchar(1) DEFAULT NULL,
  `telephone_patient` varchar(45) DEFAULT NULL,
  `personne` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idpatient`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (11,'TEST','test','07-05-1997','M','97388523','Test test'),(12,'TEST','testament','31-07-2023','M','97334422','test testament'),(13,'HHH','hhhh','07-08-2023','M','88888888','hhh hhhh');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recu_consultation`
--

DROP TABLE IF EXISTS `recu_consultation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recu_consultation` (
  `idrecu_consul` int NOT NULL AUTO_INCREMENT,
  `date_recu` varchar(45) DEFAULT NULL,
  `acte` varchar(45) DEFAULT NULL,
  `montant_net` varchar(45) DEFAULT NULL,
  `montant_verse` varchar(45) DEFAULT NULL,
  `reste` varchar(45) DEFAULT NULL,
  `caissier` varchar(45) DEFAULT NULL,
  `idservice` int DEFAULT NULL,
  `recu_id_acte` int DEFAULT NULL,
  `recu_id_patient` int DEFAULT NULL,
  `etat_recu` varchar(45) DEFAULT 'Payer',
  PRIMARY KEY (`idrecu_consul`),
  KEY `recu_consultation_serice_idx` (`idservice`),
  KEY `recu_id_patient_idx` (`recu_id_patient`),
  CONSTRAINT `recu_consultation_serice` FOREIGN KEY (`idservice`) REFERENCES `service` (`id`),
  CONSTRAINT `recu_id_patient` FOREIGN KEY (`recu_id_patient`) REFERENCES `patient` (`idpatient`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recu_consultation`
--

LOCK TABLES `recu_consultation` WRITE;
/*!40000 ALTER TABLE `recu_consultation` DISABLE KEYS */;
INSERT INTO `recu_consultation` VALUES (76,'31-07-2023','Consultation','2000','3000','1000','CAIS cai',1,0,12,'Payer'),(77,'04-08-2023','Consultation','2000','3000','1000','CAIS cai',1,1,11,'Payer'),(78,'04-08-2023','Examen','5000','10000','5000','CAIS cai',1,2,11,'Payer'),(79,'07-08-2023','Consultation','2000','3000','1000','CAIS cai',1,3,13,'Payer'),(80,'07-08-2023','Examen','5000','6000','1000','CAIS cai',1,4,13,'Payer');
/*!40000 ALTER TABLE `recu_consultation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `rle_id` int NOT NULL AUTO_INCREMENT,
  `role_libelle` char(30) NOT NULL,
  PRIMARY KEY (`rle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin'),(2,'Medecin'),(3,'Infirmier'),(4,'Secretaire'),(5,'Caissier'),(6,'Admission');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `id` int NOT NULL AUTO_INCREMENT,
  `service_libelle` char(30) NOT NULL,
  `Date_creation` varchar(45) DEFAULT NULL,
  `etat_service` varchar(45) DEFAULT 'actif',
  `Date_hor_service` varchar(45) DEFAULT 'Toujour actif',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'Cardiologie','27-06-2023','actif','Toujour actif'),(2,'Ophtamologie','27-06-2023','actif','Toujour actif'),(4,'Dermatologie','27-06-2023','actif','Toujour actif'),(5,'Caisse','27-06-2023','actif','Toujour actif'),(6,'Admission','08-07-2023','actif','Toujour actif'),(8,'Admin','27-06-2023','actif','Toujour actif'),(10,'General','27-06-2023','actif','Toujour actif'),(14,'Pediatrie','27-06-2023','Non Actif','29-06-2023'),(15,'HO','26-11-2023','non Actif','29-06-2023');
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `specialite`
--

DROP TABLE IF EXISTS `specialite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `specialite` (
  `id_specialite` int NOT NULL AUTO_INCREMENT,
  `specialite_libelle` varchar(45) NOT NULL,
  PRIMARY KEY (`id_specialite`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `specialite`
--

LOCK TABLES `specialite` WRITE;
/*!40000 ALTER TABLE `specialite` DISABLE KEYS */;
INSERT INTO `specialite` VALUES (1,'Dentiste'),(2,'Cardiologue'),(3,'Ophtamologue'),(4,'Dermatologue'),(5,'Admin'),(6,'Caisse');
/*!40000 ALTER TABLE `specialite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `idUser` int NOT NULL AUTO_INCREMENT,
  `Role_id` int NOT NULL,
  `Service_id` int DEFAULT NULL,
  `nom_users` char(30) DEFAULT NULL,
  `prenom_users` char(30) DEFAULT NULL,
  `telephone` varchar(25) DEFAULT NULL,
  `date` varchar(15) DEFAULT NULL,
  `identifiant` varchar(12) DEFAULT NULL,
  `mdp` varchar(12) DEFAULT NULL,
  `etat` varchar(45) DEFAULT 'En Service',
  `date_hs` varchar(45) DEFAULT 'Toujour en service',
  `inf_perso` varchar(45) DEFAULT 'defaut',
  `motif` varchar(45) DEFAULT 'Engagé',
  PRIMARY KEY (`idUser`),
  KEY `FK_role` (`Role_id`),
  KEY `FK_affecter` (`Service_id`),
  CONSTRAINT `FK_affecter` FOREIGN KEY (`Service_id`) REFERENCES `service` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_role` FOREIGN KEY (`Role_id`) REFERENCES `role` (`rle_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,1,8,'Dalmeida','Tillian','87665441',NULL,'admin','admin','En Service','Toujour en service','papa','Employé'),(31,2,1,'HOUNDEGLE','LOIC','98334466','02-06-2023','Med','Med','En Service','24-07-2023','defaut','Congés '),(44,2,1,'TESST','tessst','97388523','26-06-2023','MEDE9YKR','MEDEGHBQ','En Service','24-07-2023','defaut','Maladie'),(46,2,1,'TT','Mr','33221111','27-06-2023','MEDE93V8','MEDEAUND','En Service','Toujour en service','defaut','Employé'),(47,4,1,'SECRETAIRE','secretaire','22222222','29-06-2023','sec','sec','En Service','Toujour en service','defaut','Employé'),(48,5,5,'CAIS','cai','33443322','03-07-2023','cai','cai','En Service','Toujour en service','pedro','Employé'),(51,6,6,'ADMI','admi','12334554','08-07-2023','admi1234','admi','En Service','Toujour en service','afi','Employé'),(52,3,1,'INF','inf','99882223','18-07-2023','inf','inf','En Service','Toujour en service','defaut','Employé'),(53,3,2,'SOSSOU','carl','98334462','29-07-2023','INFIZIOm','INFIS7mQ','En Service','Toujour en service','poto','Engagé');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-07  9:32:16
