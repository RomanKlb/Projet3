CREATE DATABASE  IF NOT EXISTS `projet3` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `projet3`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: projet3
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id_admin` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_super_admin` bit(1) DEFAULT NULL,
  `role_id_role` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_admin`),
  KEY `FKpqo3dfrlnpvusd1ho01etijwx` (`role_id_role`),
  CONSTRAINT `FKpqo3dfrlnpvusd1ho01etijwx` FOREIGN KEY (`role_id_role`) REFERENCES `role` (`id_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `adresse`
--

DROP TABLE IF EXISTS `adresse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adresse` (
  `id_adresse` bigint(20) NOT NULL AUTO_INCREMENT,
  `code_postal` varchar(255) NOT NULL,
  `libelle` varchar(255) NOT NULL,
  `pays` varchar(255) NOT NULL,
  `rue` varchar(255) NOT NULL,
  `ville` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_adresse`),
  KEY `FKf80h974k8o1797mve80ktvw10` (`email`),
  CONSTRAINT `FKf80h974k8o1797mve80ktvw10` FOREIGN KEY (`email`) REFERENCES `utilisateur` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `appreciation`
--

DROP TABLE IF EXISTS `appreciation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appreciation` (
  `idAppreciation` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) NOT NULL,
  `note` int(11) NOT NULL,
  `id_projet` bigint(20) DEFAULT NULL,
  `id_role` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idAppreciation`),
  KEY `FK9hatjkupbbwoohgprkelstmwu` (`id_projet`),
  KEY `FKr5crdw38j63by043y05fg6g4s` (`id_role`),
  CONSTRAINT `FK9hatjkupbbwoohgprkelstmwu` FOREIGN KEY (`id_projet`) REFERENCES `projet` (`id_projet`),
  CONSTRAINT `FKr5crdw38j63by043y05fg6g4s` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorie` (
  `id_categorie` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_territoire` bigint(20) DEFAULT NULL,
  `id_type_projet` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_categorie`),
  KEY `FK2laq9m4c1cyq1m06icd4xken7` (`id_territoire`),
  KEY `FKf0bmey10iflb3eqj9qnxm7fu0` (`id_type_projet`),
  CONSTRAINT `FK2laq9m4c1cyq1m06icd4xken7` FOREIGN KEY (`id_territoire`) REFERENCES `territoire` (`id_territoire`),
  CONSTRAINT `FKf0bmey10iflb3eqj9qnxm7fu0` FOREIGN KEY (`id_type_projet`) REFERENCES `typeprojet` (`id_type_projet`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commentaire` (
  `id_commentaire` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) NOT NULL,
  `libelle` varchar(255) NOT NULL,
  `message` varchar(255) NOT NULL,
  `id_projet` bigint(20) DEFAULT NULL,
  `id_role` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_commentaire`),
  KEY `FKfi9e891rofhw5l1q19nmkvccp` (`id_projet`),
  KEY `FKt4tv9ggryvmw2fv3lycosp7p4` (`id_role`),
  CONSTRAINT `FKfi9e891rofhw5l1q19nmkvccp` FOREIGN KEY (`id_projet`) REFERENCES `projet` (`id_projet`),
  CONSTRAINT `FKt4tv9ggryvmw2fv3lycosp7p4` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `document` (
  `id_document` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) NOT NULL,
  `fichier` longblob NOT NULL,
  `libelle` varchar(255) NOT NULL,
  `typeDocument` varchar(255) NOT NULL,
  `id_projet` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_document`),
  KEY `FK7c6mt9wjvw5fpfpuc7cny3aoj` (`id_projet`),
  CONSTRAINT `FK7c6mt9wjvw5fpfpuc7cny3aoj` FOREIGN KEY (`id_projet`) REFERENCES `projet` (`id_projet`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `don`
--

DROP TABLE IF EXISTS `don`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `don` (
  `id_don` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) NOT NULL,
  `participationProjet_id_participation` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_don`),
  KEY `FK6cy3yqbrw3s1hpa6h14omvbxg` (`participationProjet_id_participation`),
  CONSTRAINT `FK6cy3yqbrw3s1hpa6h14omvbxg` FOREIGN KEY (`participationProjet_id_participation`) REFERENCES `participationprojet` (`id_participation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `donmateriel`
--

DROP TABLE IF EXISTS `donmateriel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donmateriel` (
  `libelle` varchar(255) NOT NULL,
  `montant` double NOT NULL,
  `quantite` bigint(20) NOT NULL,
  `id_don` bigint(20) NOT NULL,
  PRIMARY KEY (`id_don`),
  CONSTRAINT `FK5rthgywcy16u1060u28ebl4mc` FOREIGN KEY (`id_don`) REFERENCES `don` (`id_don`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `donmonetaire`
--

DROP TABLE IF EXISTS `donmonetaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `donmonetaire` (
  `montant` double NOT NULL,
  `id_don` bigint(20) NOT NULL,
  PRIMARY KEY (`id_don`),
  CONSTRAINT `FKg5imo0k3uanre5gomeedvec3o` FOREIGN KEY (`id_don`) REFERENCES `don` (`id_don`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dontemps`
--

DROP TABLE IF EXISTS `dontemps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dontemps` (
  `nb_heures` int(11) NOT NULL,
  `id_don` bigint(20) NOT NULL,
  PRIMARY KEY (`id_don`),
  CONSTRAINT `FK3op45lv2icbswi3pvxi1ht5k9` FOREIGN KEY (`id_don`) REFERENCES `don` (`id_don`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `facture`
--

DROP TABLE IF EXISTS `facture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facture` (
  `id_facture` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) NOT NULL,
  `libelle` varchar(255) NOT NULL,
  `participationProjet_id_participation` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_facture`),
  KEY `FK4lxrsfx16t32xpl6m9f7yk26v` (`participationProjet_id_participation`),
  CONSTRAINT `FK4lxrsfx16t32xpl6m9f7yk26v` FOREIGN KEY (`participationProjet_id_participation`) REFERENCES `participationprojet` (`id_participation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `favori`
--

DROP TABLE IF EXISTS `favori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `favori` (
  `id_favori` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) NOT NULL,
  `id_projet` bigint(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_favori`),
  KEY `FKa5m4q6gsv8irdrkncbxkwyqd4` (`id_projet`),
  KEY `FKc51ifkgtmdlnb65je78i2ruaq` (`email`),
  CONSTRAINT `FKa5m4q6gsv8irdrkncbxkwyqd4` FOREIGN KEY (`id_projet`) REFERENCES `projet` (`id_projet`),
  CONSTRAINT `FKc51ifkgtmdlnb65je78i2ruaq` FOREIGN KEY (`email`) REFERENCES `utilisateur` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `historique`
--

DROP TABLE IF EXISTS `historique`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historique` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_heure` datetime(6) NOT NULL,
  `etat_projet` varchar(255) NOT NULL,
  `evenement` varchar(255) NOT NULL,
  `libelle` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `notification_id_notification` bigint(20) DEFAULT NULL,
  `id_projet` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtpnydh91whvdgks08pkwgyjyg` (`email`),
  KEY `FKkw2vmiewkds0696lh54tdnm5x` (`notification_id_notification`),
  KEY `FKnr2d1mb5wehc2cjc8bacqot8l` (`id_projet`),
  CONSTRAINT `FKkw2vmiewkds0696lh54tdnm5x` FOREIGN KEY (`notification_id_notification`) REFERENCES `notification` (`id_notification`),
  CONSTRAINT `FKnr2d1mb5wehc2cjc8bacqot8l` FOREIGN KEY (`id_projet`) REFERENCES `projet` (`id_projet`),
  CONSTRAINT `FKtpnydh91whvdgks08pkwgyjyg` FOREIGN KEY (`email`) REFERENCES `utilisateur` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `messageinterne`
--

DROP TABLE IF EXISTS `messageinterne`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messageinterne` (
  `id_message_interne` bigint(20) NOT NULL AUTO_INCREMENT,
  `contenu` varchar(255) NOT NULL,
  `date` datetime(6) NOT NULL,
  `is_read` bit(1) DEFAULT NULL,
  `titre` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `notification_id_notification` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_message_interne`),
  KEY `FK89enft5x1j18d0kadhur8qp1v` (`email`),
  KEY `FKqvvfiyyos0lnpwtscahoj4ejc` (`notification_id_notification`),
  CONSTRAINT `FK89enft5x1j18d0kadhur8qp1v` FOREIGN KEY (`email`) REFERENCES `utilisateur` (`email`),
  CONSTRAINT `FKqvvfiyyos0lnpwtscahoj4ejc` FOREIGN KEY (`notification_id_notification`) REFERENCES `notification` (`id_notification`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `messageinterne_utilisateur`
--

DROP TABLE IF EXISTS `messageinterne_utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messageinterne_utilisateur` (
  `MessageInterne_id_message_interne` bigint(20) NOT NULL,
  `destinataire_email` varchar(255) NOT NULL,
  UNIQUE KEY `UK_nf26gbv8t5xfod590c4ohtuhr` (`destinataire_email`),
  KEY `FKp15gfrqdp798aofo2lh1sxy01` (`MessageInterne_id_message_interne`),
  CONSTRAINT `FK9arv0dei99tv4fokd6eq128c1` FOREIGN KEY (`destinataire_email`) REFERENCES `utilisateur` (`email`),
  CONSTRAINT `FKp15gfrqdp798aofo2lh1sxy01` FOREIGN KEY (`MessageInterne_id_message_interne`) REFERENCES `messageinterne` (`id_message_interne`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `id_notification` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) NOT NULL,
  `is_read` bit(1) DEFAULT NULL,
  `libelle` varchar(255) NOT NULL,
  PRIMARY KEY (`id_notification`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `participationprojet`
--

DROP TABLE IF EXISTS `participationprojet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participationprojet` (
  `id_participation` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) NOT NULL,
  `is_anonyme` bit(1) DEFAULT NULL,
  `statutDon` varchar(255) NOT NULL,
  `type_participation` varchar(255) NOT NULL,
  `facture_id_facture` bigint(20) DEFAULT NULL,
  `projet_id_projet` bigint(20) DEFAULT NULL,
  `role_id_role` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_participation`),
  KEY `FKfq77y770ug6tbpl73aoi91u0` (`facture_id_facture`),
  KEY `FKo7w2e2shf71cchgbcdi8gapwx` (`projet_id_projet`),
  KEY `FKoqyl1f57pcqtoq784br0kg29x` (`role_id_role`),
  CONSTRAINT `FKfq77y770ug6tbpl73aoi91u0` FOREIGN KEY (`facture_id_facture`) REFERENCES `facture` (`id_facture`),
  CONSTRAINT `FKo7w2e2shf71cchgbcdi8gapwx` FOREIGN KEY (`projet_id_projet`) REFERENCES `projet` (`id_projet`),
  CONSTRAINT `FKoqyl1f57pcqtoq784br0kg29x` FOREIGN KEY (`role_id_role`) REFERENCES `role` (`id_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `portefeuilleprojet`
--

DROP TABLE IF EXISTS `portefeuilleprojet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `portefeuilleprojet` (
  `id_portefeuille` bigint(20) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) NOT NULL,
  `porteurprojet_id_porteur_projet` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_portefeuille`),
  KEY `FKbrw2xlnr4elsdmi35hmxj8i9i` (`porteurprojet_id_porteur_projet`),
  CONSTRAINT `FKbrw2xlnr4elsdmi35hmxj8i9i` FOREIGN KEY (`porteurprojet_id_porteur_projet`) REFERENCES `porteurprojet` (`id_porteur_projet`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `porteurprojet`
--

DROP TABLE IF EXISTS `porteurprojet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `porteurprojet` (
  `id_porteur_projet` bigint(20) NOT NULL AUTO_INCREMENT,
  `iban` varchar(255) NOT NULL,
  `libelle` varchar(255) NOT NULL,
  `type_porteur_projet` varchar(255) NOT NULL,
  `role_id_role` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_porteur_projet`),
  KEY `FKrih0womhruyv2xf3v3b59fe7o` (`role_id_role`),
  CONSTRAINT `FKrih0womhruyv2xf3v3b59fe7o` FOREIGN KEY (`role_id_role`) REFERENCES `role` (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `projet`
--

DROP TABLE IF EXISTS `projet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projet` (
  `id_projet` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_fin` date NOT NULL,
  `date_maj` datetime(6) NOT NULL,
  `description_courte` varchar(255) NOT NULL,
  `description_longue` varchar(255) NOT NULL,
  `don_materiel` bit(1) DEFAULT NULL,
  `date_temps` bit(1) DEFAULT NULL,
  `montantAttendu` double NOT NULL,
  `montantCollecte` double NOT NULL,
  `statut_du_projet` varchar(255) NOT NULL,
  `titre` varchar(255) NOT NULL,
  `id_categorie` bigint(20) DEFAULT NULL,
  `id_portefeuille` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_projet`),
  KEY `FKe12wcca9j4bcx6ipjqbug0ijm` (`id_categorie`),
  KEY `FK185rhkwh4shrten1wdl7h4uq3` (`id_portefeuille`),
  CONSTRAINT `FK185rhkwh4shrten1wdl7h4uq3` FOREIGN KEY (`id_portefeuille`) REFERENCES `portefeuilleprojet` (`id_portefeuille`),
  CONSTRAINT `FKe12wcca9j4bcx6ipjqbug0ijm` FOREIGN KEY (`id_categorie`) REFERENCES `categorie` (`id_categorie`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id_role` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_role` varchar(255) NOT NULL,
  `utilisateur_email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_role`),
  KEY `FKeyoebhw1ywyquftx5ilr80ri0` (`utilisateur_email`),
  CONSTRAINT `FKeyoebhw1ywyquftx5ilr80ri0` FOREIGN KEY (`utilisateur_email`) REFERENCES `utilisateur` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `seuil`
--

DROP TABLE IF EXISTS `seuil`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `seuil` (
  `id_seuil` bigint(20) NOT NULL AUTO_INCREMENT,
  `montant` double NOT NULL,
  `quantite` bigint(20) NOT NULL,
  `typeParticipation` varchar(255) NOT NULL,
  PRIMARY KEY (`id_seuil`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `territoire`
--

DROP TABLE IF EXISTS `territoire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `territoire` (
  `id_territoire` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) NOT NULL,
  `libelle` varchar(255) NOT NULL,
  PRIMARY KEY (`id_territoire`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `typeprojet`
--

DROP TABLE IF EXISTS `typeprojet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `typeprojet` (
  `id_type_projet` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) NOT NULL,
  `libelle` varchar(255) NOT NULL,
  PRIMARY KEY (`id_type_projet`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilisateur` (
  `email` varchar(255) NOT NULL,
  `date_maj` datetime(6) NOT NULL,
  `mdp` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-01 16:45:10
