CREATE DATABASE  IF NOT EXISTS `progettoweb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `progettoweb`;
-- MySQL dump 10.13  Distrib 5.7.21, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: progettoweb
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.17.10.1

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
-- Table structure for table `Assistenza`
--

DROP TABLE IF EXISTS `Assistenza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Assistenza` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idUtente` int(11) NOT NULL,
  `idVenditore` int(11) DEFAULT NULL,
  `idAmministratore` int(11) NOT NULL,
  `idOrdine` int(11) DEFAULT NULL,
  `idOggetto` varchar(32) DEFAULT NULL,
  `stato` int(11) DEFAULT '0',
  `soluzione` varchar(2500) DEFAULT NULL,
  `dataApertura` datetime NOT NULL,
  `dataChiusura` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idUtente` (`idUtente`),
  KEY `idVenditore` (`idVenditore`),
  KEY `idAmministratore` (`idAmministratore`),
  KEY `idOrdine` (`idOrdine`),
  KEY `idOggetto` (`idOggetto`),
  CONSTRAINT `Assistenza_ibfk_1` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `Assistenza_ibfk_2` FOREIGN KEY (`idVenditore`) REFERENCES `Utente` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `Assistenza_ibfk_3` FOREIGN KEY (`idAmministratore`) REFERENCES `Utente` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `Assistenza_ibfk_4` FOREIGN KEY (`idOrdine`) REFERENCES `Ordine` (`idOrdine`) ON DELETE SET NULL,
  CONSTRAINT `Assistenza_ibfk_5` FOREIGN KEY (`idOggetto`) REFERENCES `Oggetto` (`id`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Assistenza`
--

LOCK TABLES `Assistenza` WRITE;
/*!40000 ALTER TABLE `Assistenza` DISABLE KEYS */;
INSERT INTO `Assistenza` VALUES (1,3,1,5,1,'1',0,NULL,'2017-10-07 19:13:20',NULL),(2,4,NULL,5,NULL,'4',1,'I soldi sono stati restituiti al cliente','2017-09-21 12:10:01','2017-10-01 09:08:22'),(3,3,2,5,NULL,'5',1,'La descrizione dell\'articolo era corretta, nessun rimborso','2017-03-01 00:24:13','2017-03-05 14:51:12'),(4,3,NULL,5,NULL,'2',0,NULL,'2016-12-21 00:00:00',NULL);
/*!40000 ALTER TABLE `Assistenza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Carrello`
--

DROP TABLE IF EXISTS `Carrello`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Carrello` (
  `idUtente` int(11) NOT NULL,
  `idOrdine` int(11) NOT NULL,
  `subTotal` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`idUtente`),
  KEY `idOrdine` (`idOrdine`),
  CONSTRAINT `Carrello_ibfk_1` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Carrello`
--

LOCK TABLES `Carrello` WRITE;
/*!40000 ALTER TABLE `Carrello` DISABLE KEYS */;
INSERT INTO `Carrello` VALUES (2,0,499),(4,5,998);
/*!40000 ALTER TABLE `Carrello` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Categoria`
--

DROP TABLE IF EXISTS `Categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Categoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `sottoCategoria` varchar(50) DEFAULT NULL,
  `descrizione` varchar(500) NOT NULL,
  `oggettiPresenti` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Categoria`
--

LOCK TABLES `Categoria` WRITE;
/*!40000 ALTER TABLE `Categoria` DISABLE KEYS */;
INSERT INTO `Categoria` VALUES (1,'Elettronica',NULL,'Materiale Elettronico',8),(2,'Casa',NULL,'Materiale per Casa e Cucina',2),(3,'Abbigliamento',NULL,'il mondo dei vestiti',1),(4,'Alimentari',NULL,'Ne trovi per tutti i tuoi gusti',2),(5,'Auto e Moto',NULL,'Il mondo dei motori ai tuoi piedi',2),(6,'Bellezza',NULL,'Coccolati con questi prodotti',2),(7,'Cancelleria e Ufficio',NULL,'Tutto quello che vorrai avere sulla tua scrivania',0),(8,'CD e Vinili',NULL,'Lasciati trasportare in un mondo di note',0),(9,'Fai da te',NULL,'Costruisci il mondo intorno a te',1),(10,'Film e TV',NULL,'Non cambiare canale, resta sincronizzato sulle offerte',1),(11,'Giardino e giardinaggio',NULL,'Rendi il tuo giardino uno spettacolo',2),(12,'Giochi e giocattoli',NULL,'Divertiti come non mai',0),(13,'Gioielli',NULL,'Rendi prezioso il tuo corpo',0),(14,'Fatto a mano',NULL,'Guarda come potremo sorprenderti',0),(15,'Illuminazione',NULL,'Rendi luminosa la tua strada',0),(16,'Industria e scienza',NULL,'Esplora il mondo con un occhio scientifico',0),(17,'Informatica',NULL,'Gioca lavora o divertiti con tutte le ultime tecnologie uscite in commercio',0),(18,'Libri',NULL,'Entra in un nuovo mondo fatto di fantasia',0),(19,'Moda',NULL,'Fatti travolgere dalle ultime creazioni',0),(20,'Orologi',NULL,'Rendi ogni secondo speciale',0),(21,'Prima infanzia',NULL,'Il tuo bambino ha bisogno di te! Usa solo prodotti di prima scelta',0),(22,'Prodotti per animali',NULL,'Fai gustare al tuo animale qualcosa creato appositamente per lui',0),(23,'Salute',NULL,'Prenditi cura di te stesso',0),(24,'Scarpe e borse',NULL,'L\'accessorio giusto al momento giusto',0),(25,'Software',NULL,'Il meglio per il tuo computer',0),(26,'Sport e tempo libero',NULL,'Dai la giusta importanza ai tuoi hobby',0),(27,'Strumenti musicali e dj',NULL,'Solo il meglio per i musicisti di oggi e domani',0),(28,'Valigeria',NULL,'Non lasciare niente a casa ',0),(29,'Videogiochi',NULL,'Gustati i migliori titoli del momento',0);
/*!40000 ALTER TABLE `Categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Indirizzo`
--

DROP TABLE IF EXISTS `Indirizzo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Indirizzo` (
  `idI` int(11) NOT NULL AUTO_INCREMENT,
  `stato` varchar(255) NOT NULL,
  `regione` varchar(255) NOT NULL,
  `provincia` varchar(255) NOT NULL,
  `citta` varchar(255) NOT NULL,
  `via` varchar(255) NOT NULL,
  `nCivico` int(11) NOT NULL,
  `interno` int(11) DEFAULT NULL,
  `latitudine` double DEFAULT NULL,
  `longitudine` double DEFAULT NULL,
  PRIMARY KEY (`idI`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Indirizzo`
--

LOCK TABLES `Indirizzo` WRITE;
/*!40000 ALTER TABLE `Indirizzo` DISABLE KEYS */;
INSERT INTO `Indirizzo` VALUES (1,'Italia','Lombardia','Milano','Milano','Via Valassina',27,2,45.5005692,9.187734699999965),(2,'Italia','Lombardia','Milano','Milano','Via Orti',3,NULL,45.4549309,9.198682800000029),(3,'Italia','Trentino Alto Adige','Trento','Trento','Corso 3 Novembre 1918',102,15,46.0608004,11.124980899999969),(4,'Italia','Piemonte','Torino','Torino','Corso 3 Novembre 1918',102,15,45.07489349999999,7.652501499999971),(5,'Italia','Veneto','Treviso','Treviso','Piazza dei Signori',3,NULL,45.6654873,12.245583500000066),(6,'Italia','Trentino Alto Adige','Trento','Trento','Via Brennero',282,NULL,46.0884487,11.117878099999984),(7,'Italia','Piemonte','Torino','Collegno','Viale Svezia',1,NULL,45.097961,7.581563999999958),(8,'Italia','Veneto','Milano','Milano','Piazza del Duomo',1,NULL,45.465043,9.191806000000042),(14,'Italia','Veneto','Vicenza','Bassano del grappa','Via rovereto',26,-1,45.764622,11.725791999999956),(15,'Italia','Veneto','Vicenza','Bassano del grappa','Via rovereto',26,-1,45.764622,11.725791999999956),(16,'Italia','Veneto','Vicenza','Bassano del grappa','Via rovereto',12,-1,45.765,11.725549999999998),(27,'Italia','Veneto','Vicenza','Bassano del grappa','Via rovereto',12,-1,45.765,11.725549999999998),(28,'Italia','Veneto','Vicenza','Bassano del grappa','Via rovereto',33,-1,45.7648889,11.725838100000033),(29,'Italia','Veneto','VI','Bassano del grappa','via roma',30,-1,45.7659708,11.73488240000006),(30,'Italia','Veneto','Vicenza','Bassano del grappa','via roma',12,-1,45.7666101,11.734447899999964),(31,'Italia','Veneto','Vi','Bassano del grappa','Via rovereto',26,2,45.764622,11.725791999999956),(32,'Italia','Veneto','Vicenza','Bassano del grappa','Via rovereto',26,-1,45.764622,11.725791999999956);
/*!40000 ALTER TABLE `Indirizzo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `IndirizzoUtente`
--

DROP TABLE IF EXISTS `IndirizzoUtente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `IndirizzoUtente` (
  `idI` int(11) NOT NULL,
  `idU` int(11) NOT NULL,
  PRIMARY KEY (`idI`,`idU`),
  KEY `idU` (`idU`),
  CONSTRAINT `IndirizzoUtente_ibfk_1` FOREIGN KEY (`idU`) REFERENCES `Utente` (`id`) ON DELETE CASCADE,
  CONSTRAINT `IndirizzoUtente_ibfk_2` FOREIGN KEY (`idI`) REFERENCES `Indirizzo` (`idI`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `IndirizzoUtente`
--

LOCK TABLES `IndirizzoUtente` WRITE;
/*!40000 ALTER TABLE `IndirizzoUtente` DISABLE KEYS */;
INSERT INTO `IndirizzoUtente` VALUES (2,1),(4,2),(31,2),(5,3),(3,4),(1,5);
/*!40000 ALTER TABLE `IndirizzoUtente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Negozio`
--

DROP TABLE IF EXISTS `Negozio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Negozio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idVenditore` int(11) NOT NULL,
  `nomeNegozio` varchar(255) NOT NULL,
  `valutazione` double DEFAULT NULL,
  `attivo` tinyint(1) NOT NULL DEFAULT '1',
  `idI` int(11) NOT NULL,
  `dataApertura` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `linkSito` varchar(150) DEFAULT NULL,
  `orarioNegozio` varchar(400) DEFAULT NULL,
  PRIMARY KEY (`id`,`idI`),
  KEY `idI` (`idI`),
  KEY `idNegozioIndex` (`idVenditore`) USING HASH,
  CONSTRAINT `Negozio_ibfk_1` FOREIGN KEY (`idVenditore`) REFERENCES `Utente` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `Negozio_ibfk_2` FOREIGN KEY (`idI`) REFERENCES `Indirizzo` (`idI`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Negozio`
--

LOCK TABLES `Negozio` WRITE;
/*!40000 ALTER TABLE `Negozio` DISABLE KEYS */;
INSERT INTO `Negozio` VALUES (1,2,'Mediaworld',NULL,1,6,'2017-06-25 13:30:15',NULL,'Lunedi: 8:00 - 19:00, Martedi: 8:00 - 19:00, Mercoledi: 8:00 - 19:00, Giovedi: 8:00 - 19:00, Venerdi: 8:00 - 19:00, Sabato: 8:00 - 19:00, Domenica: Chiuso'),(2,2,'Unieuro',NULL,1,8,'2016-01-01 07:00:00',NULL,'Lunedi: 8:00 - 22:00, Martedi: 8:00 - 22:00, Mercoledi: 8:00 - 22:00, Giovedi: 8:00 - 22:00, Venerdi: 8:00 - 22:00, Sabato: 9:00 - 22:00, Domenica: 10:00 - 20:00'),(3,1,'Ikea',8,0,7,'2017-07-20 14:02:41',NULL,'Lunedi: 8:00 - 19:00, Martedi: 8:00 - 19:00, Mercoledi: 8:00 - 19:00, Giovedi: 8:00 - 19:00, Venerdi: 8:00 - 19:00, Sabato: 8:00 - 19:00, Domenica: Chiuso'),(4,2,'Negozio stravagante',NULL,1,4,'2017-12-28 10:12:34','www.negoziostrano.com','Lunedì: Chiuso Martedì: Chiuso Mercoledì: Chiuso Giovedì: Chiuso Venerdì: Chiuso Sabato: Chiuso Domenica: Chiuso '),(14,2,'bhobho',NULL,1,28,'2017-12-30 11:48:35','www.bhooooo.com','Lunedì: Chiuso , Martedì: Chiuso , Mercoledì: Chiuso , Giovedì: Chiuso , Venerdì: Chiuso , Sabato: Chiuso , Domenica: Chiuso '),(15,2,'Altro negozio di prova',NULL,0,29,'2018-01-08 21:06:07','www.altronegozio.it','Lunedì: Chiuso , Martedì: Chiuso , Mercoledì: Chiuso , Giovedì: Chiuso , Venerdì: Chiuso , Sabato: Chiuso , Domenica: Chiuso '),(16,2,'test finale aggiunta negozio',NULL,1,30,'2018-01-15 07:19:57','WWW.link.IT','Lunedì: Chiuso , Martedì: Chiuso , Mercoledì: Chiuso , Giovedì: Chiuso , Venerdì: Chiuso , Sabato: Chiuso , Domenica: Chiuso '),(17,2,'negozio di prova per url',NULL,1,32,'2018-01-17 14:33:03','http://www.linkDiProva.it','Lunedì: ciao - ciao , Martedì: Chiuso , Mercoledì: Chiuso , Giovedì: Chiuso , Venerdì: Chiuso , Sabato: Chiuso , Domenica: Chiuso ');
/*!40000 ALTER TABLE `Negozio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Oggetto`
--

DROP TABLE IF EXISTS `Oggetto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Oggetto` (
  `id` varchar(32) NOT NULL,
  `idNegozio` int(11) NOT NULL,
  `nome` varchar(500) NOT NULL,
  `nomeDownCase` varchar(500) NOT NULL,
  `prezzo` double NOT NULL,
  `descrizione` varchar(2500) NOT NULL,
  `ritiroInNegozio` tinyint(1) DEFAULT '0',
  `disponibilita` int(11) NOT NULL,
  `statoDisponibilita` int(11) NOT NULL DEFAULT '0',
  `sconto` double DEFAULT '0',
  `dataFineSconto` date DEFAULT NULL,
  `categoria` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idNegozio` (`idNegozio`),
  KEY `idOggettoIndex` (`categoria`) USING HASH,
  CONSTRAINT `Oggetto_ibfk_1` FOREIGN KEY (`idNegozio`) REFERENCES `Negozio` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `Oggetto_ibfk_2` FOREIGN KEY (`categoria`) REFERENCES `Categoria` (`id`) ON DELETE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Oggetto`
--

LOCK TABLES `Oggetto` WRITE;
/*!40000 ALTER TABLE `Oggetto` DISABLE KEYS */;
INSERT INTO `Oggetto` VALUES ('1',1,'iPhone SE','iphone se',259.99,'iPhone SE 16GB Bianco',0,23,0,15,'2017-12-30',1),('1be10aca451d221ba5de0253367a9e15',15,'oggetto tanto tanto brutto','oggetto tanto tanto brutto',1000,'questo Ã???Ã??Ã?Â¨ il mio miglior oggetto eheheh',1,20,0,50,'2018-01-24',5),('2',2,'HTC U11','htc u11',499,'HTC U11 32GB Bianco',0,6,0,0,NULL,1),('3',2,'Huawei P10 Lite','huawei p10 lite',260,'Huawei P10 Lite 32GB Nero',0,1,0,0,NULL,1),('3f6d28c6f3f3b2cafe5f2e3edd15549e',14,'oggetto scontato','oggetto scontato',124,'asdasdsad',1,10,0,25,'2018-01-19',1),('4',3,'Pentola Acciaio Inossidabile','pentola acciaio inossidabile',50,'Pentola Acciaio Inossidabile, insdistrutibile!',1,45,0,0,NULL,2),('42228bcaf44bf0148efcf2b8d1bb34c9',16,'secondo oggetto finale','secondo oggetto finale',280,'cavoli questo Ã?Â¨ un oggetto finale asdads',1,9,0,10,'2019-02-14',1),('43d478f6d437e62c5934639233409aec',15,'ammazza che brutto','ammazza che brutto',1987,'daoksldjlaskdnadja',1,9,0,10,'2018-01-23',1),('5',1,'Earpods','earpods',29,'Auricolari wire apple',1,65,1,0,NULL,1),('6',1,'Huawei P10 Lite','huawei p10 lite',289,'Huawei P10 Lite 32GB Nero',0,1,0,0,NULL,1),('6b52e737c308ee43a04f0499cd65cc59',14,'nome di un oggetto','nome di un oggetto',123,'questa Ã¨ la descrizione',1,120,0,0,NULL,1),('6db88cf5ed7ba32f94737ac8fbf5c35a',14,'oggetto di prova','oggetto di prova',999,'descrizione di prova',1,12,0,0,NULL,4),('7',1,'HTC U11','htc u11',469,'HTC U11 32GB Bianco',1,25,0,0,NULL,1),('726f8395b1a69285e0c052224b5b20e1',15,'oggettoooooooo','oggettoooooooo',124,'sdasdasdasds',1,10,0,0,NULL,5),('82a1a8d6d127d42889049af26c6da39d',14,'oggetto strano','oggetto strano',1234,'questo Ã¨ uno strano oggetto',1,12,0,20,'2018-01-12',1),('af39ab03be7a4124c0315c4292c7ec94',14,'asdasdasd','asdasdasd',123,'sweqweqwedsadasdasdasdqew',1,10,0,0,NULL,1),('ciao',14,'ciao','asdadkmasldnasjkndasdnasd',200,'dasjkdnasknm dasdbaskjdnalskndasd asd asldnasd  asijdkasjnd',1,1,0,0,NULL,1),('ciaociao',1,'nuovo nome oggetto','nuovo nome oggetto',987,'descrizione oggetto aggiornato',0,20,0,0,NULL,7),('dasdasda',1,'adasd','dasda',12,'gtgrfe',1,10,0,0,NULL,1),('dassadasddasda',1,'adasd','dasda',12,'gtgrfe',1,10,0,50,'2018-01-12',1),('e05ada816498ee538369327195ed87cc',14,'non ho un nome','non ho un nome',1234,'dasdasdads',1,123,0,0,NULL,6),('e2777a27bc774a720aba4d0745be9de2',16,'Oggetto test finale','oggetto test finale',180,'Descrizione di un oggetto test finale',1,10,1,0,NULL,3),('f3af9859dfa7ec25eeb8d8a8b8d31431',14,'nome casuale','nome casuale',989,'questa Ã¨ una descrizione completamente casuale',1,12,0,50,'2018-01-26',9);
/*!40000 ALTER TABLE `Oggetto` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `AggiornamentoOrdiniSeVieneModificatoIlPrezzo` AFTER UPDATE ON `Oggetto`
 FOR EACH ROW BEGIN

DECLARE curDate DATETIME DEFAULT NOW();


IF(curDate <= new.dataFineSconto)
	THEN
    	UPDATE Ordine
        	SET Ordine.prezzoDiAcquisto = new.prezzo - ((new.prezzo * new.sconto) / 100)
            WHERE Ordine.stato = 0 AND Ordine.idOggetto = new.id AND Ordine.idNegozio = new.idNegozio;
    ELSE
    UPDATE Ordine
        	SET Ordine.prezzoDiAcquisto = new.prezzo
            WHERE Ordine.stato = 0 AND Ordine.idOggetto = new.id AND Ordine.idNegozio = new.idNegozio;

END IF;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `Ordine`
--

DROP TABLE IF EXISTS `Ordine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Ordine` (
  `idOrdine` int(11) NOT NULL AUTO_INCREMENT,
  `idOggetto` varchar(32) NOT NULL,
  `idNegozio` int(11) NOT NULL,
  `idUtente` int(11) NOT NULL,
  `stato` int(11) NOT NULL DEFAULT '0',
  `quantita` int(11) NOT NULL DEFAULT '1',
  `codiceTracking` varchar(40) DEFAULT NULL,
  `dataArrivoPresunta` date DEFAULT NULL,
  `dataOrdine` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `prezzoDiAcquisto` double DEFAULT NULL,
  `idS` int(11) DEFAULT NULL,
  `idI` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idOrdine`,`idOggetto`,`idUtente`),
  KEY `idOggetto` (`idOggetto`),
  KEY `idNegozio` (`idNegozio`),
  KEY `idUtente` (`idUtente`),
  KEY `idS` (`idS`),
  CONSTRAINT `Ordine_ibfk_1` FOREIGN KEY (`idOggetto`) REFERENCES `Oggetto` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `Ordine_ibfk_2` FOREIGN KEY (`idNegozio`) REFERENCES `Negozio` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `Ordine_ibfk_3` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`id`) ON DELETE CASCADE,
  CONSTRAINT `nomeSensato3` FOREIGN KEY (`idS`) REFERENCES `tipoSpedizione` (`idS`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Ordine`
--

LOCK TABLES `Ordine` WRITE;
/*!40000 ALTER TABLE `Ordine` DISABLE KEYS */;
INSERT INTO `Ordine` VALUES (1,'1',1,3,1,1,NULL,NULL,'2017-10-01 19:13:20',300,NULL,1),(1,'6',1,3,1,2,'il codice di tracking: stocazzo','2018-01-15','2017-10-01 19:13:20',319,NULL,1),(1,'7',1,3,1,1,NULL,NULL,'2017-10-01 19:13:20',450,NULL,1),(2,'2',2,4,1,1,NULL,NULL,'2017-07-23 09:27:52',200,NULL,1),(3,'3',2,3,2,1,NULL,NULL,'2017-07-25 07:53:01',100,NULL,1),(4,'4',3,4,2,1,NULL,NULL,'2017-07-25 07:58:55',50,NULL,1),(5,'2',2,4,0,2,NULL,NULL,'2017-10-07 09:06:44',998,NULL,1),(6,'1',1,5,2,5,'htftxgfhjfrtxdhn','2018-01-18','2018-01-12 14:46:43',300,2,1),(7,'1',1,5,3,4,'Codice trackantissimo','2018-01-18','2018-01-15 07:39:27',300,2,1),(8,'43d478f6d437e62c5934639233409aec',15,2,1,1,NULL,NULL,'2018-01-22 10:30:40',1788.3,NULL,4),(8,'6db88cf5ed7ba32f94737ac8fbf5c35a',14,2,1,1,NULL,NULL,'2018-01-22 10:30:57',999,NULL,4),(9,'82a1a8d6d127d42889049af26c6da39d',14,2,1,3,NULL,NULL,'2018-01-22 20:43:23',987.2,NULL,31),(10,'2',2,2,0,1,NULL,NULL,'2018-01-23 07:47:04',499,NULL,1);
/*!40000 ALTER TABLE `Ordine` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `CreoElementoCarrello` BEFORE INSERT ON `Ordine`
 FOR EACH ROW BEGIN
declare x int default 0;
SET x = (SELECT COUNT(idUtente)
         FROM Carrello
         WHERE Carrello.idUtente = new.idUtente
         LIMIT 1
        );

IF(x < 1) THEN
	INSERT INTO Carrello
		(idOrdine,idUtente,subTotal)
    VALUES (new.idOrdine, new.idUtente, 0);
END IF;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `AggiornamentoCarrelloAggiuntaElemento` AFTER INSERT ON `Ordine`
 FOR EACH ROW UPDATE Carrello c
	SET c.subTotal = c.subTotal + (new.prezzoDiAcquisto * new.quantita)
    WHERE c.idUtente = new.idUtente AND new.stato = 0 */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `AggiornamentoCarrelloModificaPrezzoElemento` AFTER UPDATE ON `Ordine`
 FOR EACH ROW BEGIN
IF new.stato = 0
THEN
    UPDATE Carrello c
        SET c.subTotal = c.subTotal - (old.prezzoDiAcquisto * old.quantita) + (new.prezzoDiAcquisto * new.quantita)
        WHERE c.idUtente = old.idUtente AND old.stato = 0;
ELSE
    UPDATE Carrello c
        SET c.subTotal = c.subTotal - (old.prezzoDiAcquisto * old.quantita)
        WHERE c.idUtente = old.idUtente AND old.stato = 0;

    IF (SELECT subTotal
       	FROM Carrello
       	WHERE idUtente = old.idUtente) = 0
    THEN
    	DELETE FROM Carrello
        WHERE idUtente = old.idUtente;
    END IF;
END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `AggiornamentoCarrelloRimozioneElemento` AFTER DELETE ON `Ordine`
 FOR EACH ROW UPDATE Carrello c
	SET c.subTotal = c.subTotal - (old.prezzoDiAcquisto * old.quantita)
    WHERE c.idUtente = old.idUtente AND old.stato = 0 */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `RecensioneNegozio`
--

DROP TABLE IF EXISTS `RecensioneNegozio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RecensioneNegozio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idNegozio` int(11) NOT NULL,
  `idUtente` int(11) NOT NULL,
  `testo` varchar(2500) DEFAULT NULL,
  `valutazione` int(11) NOT NULL,
  `data` datetime NOT NULL,
  `utilita` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idUtente` (`idUtente`),
  KEY `idRecNegIndex` (`idNegozio`) USING HASH,
  CONSTRAINT `RecensioneNegozio_ibfk_1` FOREIGN KEY (`idNegozio`) REFERENCES `Negozio` (`id`) ON DELETE CASCADE,
  CONSTRAINT `RecensioneNegozio_ibfk_2` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`id`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RecensioneNegozio`
--

LOCK TABLES `RecensioneNegozio` WRITE;
/*!40000 ALTER TABLE `RecensioneNegozio` DISABLE KEYS */;
INSERT INTO `RecensioneNegozio` VALUES (1,1,4,'Bel negozio, personale qualificato',5,'2017-09-14 00:00:00',NULL),(2,2,3,'Bel negozio, alla moda, consigliato!',5,'2017-09-11 00:00:00',NULL),(3,3,4,'tutto molto bello',4,'2017-08-19 00:00:00',0);
/*!40000 ALTER TABLE `RecensioneNegozio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RecensioneOggetto`
--

DROP TABLE IF EXISTS `RecensioneOggetto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RecensioneOggetto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idOggetto` varchar(32) NOT NULL,
  `idUtente` int(11) NOT NULL,
  `testo` varchar(2500) DEFAULT NULL,
  `valutazione` int(11) NOT NULL,
  `data` datetime NOT NULL,
  `utilita` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idUtente` (`idUtente`),
  KEY `idRecOggIndex` (`idOggetto`) USING HASH,
  CONSTRAINT `RecensioneOggetto_ibfk_1` FOREIGN KEY (`idOggetto`) REFERENCES `Oggetto` (`id`) ON DELETE CASCADE,
  CONSTRAINT `RecensioneOggetto_ibfk_2` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`id`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RecensioneOggetto`
--

LOCK TABLES `RecensioneOggetto` WRITE;
/*!40000 ALTER TABLE `RecensioneOggetto` DISABLE KEYS */;
INSERT INTO `RecensioneOggetto` VALUES (1,'1',1,'Bene ma non benissimo, si vede che è una cinesata',2,'2017-07-14 00:00:00',4),(2,'5',1,'Ad Andrea Fadi piace questo elemento',5,'2017-07-25 08:21:56',12),(3,'3',2,'Era scritto motosega e invece è un telefono',1,'2017-07-25 11:18:35',3);
/*!40000 ALTER TABLE `RecensioneOggetto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RecensioneVenditore`
--

DROP TABLE IF EXISTS `RecensioneVenditore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RecensioneVenditore` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idVenditore` int(11) NOT NULL,
  `idUtente` int(11) NOT NULL,
  `testo` varchar(2500) DEFAULT NULL,
  `valutazione` int(11) NOT NULL,
  `data` datetime NOT NULL,
  `utilita` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idUtente` (`idUtente`),
  KEY `idRecVenIndex` (`idVenditore`) USING HASH,
  CONSTRAINT `RecensioneVenditore_ibfk_1` FOREIGN KEY (`idVenditore`) REFERENCES `Utente` (`id`) ON DELETE CASCADE,
  CONSTRAINT `RecensioneVenditore_ibfk_2` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`id`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RecensioneVenditore`
--

LOCK TABLES `RecensioneVenditore` WRITE;
/*!40000 ALTER TABLE `RecensioneVenditore` DISABLE KEYS */;
INSERT INTO `RecensioneVenditore` VALUES (1,2,4,'tutto arrivato in tempo, imballo fatto con cazzi di gomma',4,'2017-07-19 00:00:00',4);
/*!40000 ALTER TABLE `RecensioneVenditore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RispostaNegozio`
--

DROP TABLE IF EXISTS `RispostaNegozio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RispostaNegozio` (
  `idRecensione` int(11) NOT NULL,
  `testo` varchar(2500) NOT NULL,
  `data` datetime NOT NULL,
  PRIMARY KEY (`idRecensione`),
  CONSTRAINT `RispostaNegozio_ibfk_1` FOREIGN KEY (`idRecensione`) REFERENCES `RecensioneNegozio` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RispostaNegozio`
--

LOCK TABLES `RispostaNegozio` WRITE;
/*!40000 ALTER TABLE `RispostaNegozio` DISABLE KEYS */;
/*!40000 ALTER TABLE `RispostaNegozio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RispostaOggetto`
--

DROP TABLE IF EXISTS `RispostaOggetto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RispostaOggetto` (
  `idRecensione` int(11) NOT NULL,
  `testo` varchar(2500) NOT NULL,
  `data` datetime NOT NULL,
  PRIMARY KEY (`idRecensione`),
  CONSTRAINT `RispostaOggetto_ibfk_1` FOREIGN KEY (`idRecensione`) REFERENCES `RecensioneOggetto` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RispostaOggetto`
--

LOCK TABLES `RispostaOggetto` WRITE;
/*!40000 ALTER TABLE `RispostaOggetto` DISABLE KEYS */;
INSERT INTO `RispostaOggetto` VALUES (1,'Ci scusiamo, stiamo lavorando per assumere cinesi a basso costo che facciano cinesate che non sembrino cinesate, cordiali saluti, ToMare','2017-07-30 00:00:00');
/*!40000 ALTER TABLE `RispostaOggetto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RispostaVenditore`
--

DROP TABLE IF EXISTS `RispostaVenditore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RispostaVenditore` (
  `idRecensione` int(11) NOT NULL,
  `testo` varchar(2500) NOT NULL,
  `data` datetime NOT NULL,
  PRIMARY KEY (`idRecensione`),
  CONSTRAINT `RispostaVenditore_ibfk_1` FOREIGN KEY (`idRecensione`) REFERENCES `RecensioneVenditore` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RispostaVenditore`
--

LOCK TABLES `RispostaVenditore` WRITE;
/*!40000 ALTER TABLE `RispostaVenditore` DISABLE KEYS */;
/*!40000 ALTER TABLE `RispostaVenditore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Utente`
--

DROP TABLE IF EXISTS `Utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Utente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `cognome` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `valutazione` double DEFAULT NULL,
  `UtenteType` int(11) NOT NULL DEFAULT '0',
  `emailConfermata` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `idUtenteIndex` (`id`,`UtenteType`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Utente`
--

LOCK TABLES `Utente` WRITE;
/*!40000 ALTER TABLE `Utente` DISABLE KEYS */;
INSERT INTO `Utente` VALUES (1,'Paolo','Rossi','paolored@gmail.com','386e2e60f03df908eaf7a70b928685ef','http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/userImage.png',NULL,1,'\0'),(2,'Giorgio','Ugolini','giorgiougo@libero.it','cazzo','http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/userImage.png',NULL,1,'\0'),(3,'Andrea','Colombo','andrea.uccello@tiscali.it','3be747e3b8bffd2759019b0b337cc37e','http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/userImage.png',NULL,0,'\0'),(4,'Fabio','Basilio','fabiob75@outlook.com','d240445b5f0c9b2fd37b7b75f820d156','http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/userImage.png',NULL,0,'\0'),(5,'Luigi','Di Prima','luigilostesso@libero.it','a8778577393eed9e801cbeb1c5ca22b3','http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/userImage.png',NULL,2,'\0'),(6,'ugo','ciccio','ugo@ciccio.it','ciao','http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/userImage.png',0,0,'\0'),(7,'cavolo','banana','banana@cavolo.it','ciao','http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/userImage.png',0,0,'\0'),(8,'cavolo','ciccio','asdads@it','ciao','http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/userImage.png',0,0,'\0');
/*!40000 ALTER TABLE `Utente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `sottoCategoria` varchar(50) DEFAULT NULL,
  `descrizione` varchar(500) NOT NULL,
  `oggettiPresenti` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Elettronica',NULL,'Materiale Elettronico',3),(2,'Casa',NULL,'Materiale per Casa e Cucina',1),(3,'Abbigliamento',NULL,'il mondo dei vestiti',0),(4,'Alimentari',NULL,'Ne trovi per tutti i tuoi gusti',0),(5,'Auto e Moto',NULL,'Il mondo dei motori ai tuoi piedi',0),(6,'Bellezza',NULL,'Coccolati con questi prodotti',0),(7,'Cancelleria e Ufficio',NULL,'Tutto quello che vorrai avere sulla tua scrivania',0),(8,'CD e Vinili',NULL,'Lasciati trasportare in un mondo di note',0),(9,'Fai da te',NULL,'Costruisci il mondo intorno a te',0),(10,'Film e TV',NULL,'Non cambiare canale, resta sincronizzato sulle offerte',0),(11,'Giardino e giardinaggio',NULL,'Rendi il tuo giardino uno spettacolo',0),(12,'Giochi e giocattoli',NULL,'Divertiti come non mai',0),(13,'Gioielli',NULL,'Rendi prezioso il tuo corpo',0),(14,'Fatto a mano',NULL,'Guarda come potremo sorprenderti',0),(15,'Illuminazione',NULL,'Rendi luminosa la tua strada',0),(16,'Industria e scienza',NULL,'Esplora il mondo con un occhio scientifico',0),(17,'Informatica',NULL,'Gioca lavora o divertiti con tutte le ultime tecnologie uscite in commercio',0),(18,'Libri',NULL,'Entra in un nuovo mondo fatto di fantasia',0),(19,'Moda',NULL,'Fatti travolgere dalle ultime creazioni',0),(20,'Orologi',NULL,'Rendi ogni secondo speciale',0),(21,'Prima infanzia',NULL,'Il tuo bambino ha bisogno di te! Usa solo prodotti di prima scelta',0),(22,'Prodotti per animali',NULL,'Fai gustare al tuo animale qualcosa creato appositamente per lui',0),(23,'Salute',NULL,'Prenditi cura di te stesso',0),(24,'Scarpe e borse',NULL,'L\'accessorio giusto al momento giusto',0),(25,'Software',NULL,'Il meglio per il tuo computer',0),(26,'Sport e tempo libero',NULL,'Dai la giusta importanza ai tuoi hobby',0),(27,'Strumenti musicali e dj',NULL,'Solo il meglio per i musicisti di oggi e domani',0),(28,'Valigeria',NULL,'Non lasciare niente a casa ',0),(29,'Videogiochi',NULL,'Gustati i migliori titoli del momento',0);
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imageNegozio`
--

DROP TABLE IF EXISTS `imageNegozio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imageNegozio` (
  `src` varchar(150) NOT NULL,
  `idN` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`src`,`idN`),
  KEY `idN` (`idN`),
  CONSTRAINT `imageNegozio_ibfk_2` FOREIGN KEY (`idN`) REFERENCES `Negozio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imageNegozio`
--

LOCK TABLES `imageNegozio` WRITE;
/*!40000 ALTER TABLE `imageNegozio` DISABLE KEYS */;
INSERT INTO `imageNegozio` VALUES ('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/imageNegozio.png',1),('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/imageNegozio.png',2),('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/imageNegozio.png',3),('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/imageNegozio.png',4),('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/imageNegozio.png',14),('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/imageNegozio.png',15),('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/imageNegozio.png',16),('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/imageNegozio.png',17);
/*!40000 ALTER TABLE `imageNegozio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imageOggetto`
--

DROP TABLE IF EXISTS `imageOggetto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imageOggetto` (
  `src` varchar(150) NOT NULL,
  `idO` varchar(32) NOT NULL DEFAULT '0',
  PRIMARY KEY (`src`,`idO`),
  KEY `idO` (`idO`),
  CONSTRAINT `imageOggetto_ibfk_2` FOREIGN KEY (`idO`) REFERENCES `Oggetto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imageOggetto`
--

LOCK TABLES `imageOggetto` WRITE;
/*!40000 ALTER TABLE `imageOggetto` DISABLE KEYS */;
INSERT INTO `imageOggetto` VALUES ('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/objectImage.png','1'),('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/objectImage.png','1be10aca451d221ba5de0253367a9e15'),('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/objectImage.png','2'),('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/objectImage.png','3f6d28c6f3f3b2cafe5f2e3edd15549e'),('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/objectImage.png','42228bcaf44bf0148efcf2b8d1bb34c9'),('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/objectImage.png','43d478f6d437e62c5934639233409aec'),('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/objectImage.png','6db88cf5ed7ba32f94737ac8fbf5c35a'),('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/objectImage.png','726f8395b1a69285e0c052224b5b20e1'),('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/objectImage.png','82a1a8d6d127d42889049af26c6da39d'),('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/objectImage.png','ciao'),('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/objectImage.png','ciaociao'),('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/objectImage.png','e05ada816498ee538369327195ed87cc'),('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/objectImage.png','e2777a27bc774a720aba4d0745be9de2'),('http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/objectImage.png','f3af9859dfa7ec25eeb8d8a8b8d31431');
/*!40000 ALTER TABLE `imageOggetto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imageRecensione`
--

DROP TABLE IF EXISTS `imageRecensione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imageRecensione` (
  `src` varchar(150) NOT NULL,
  `idR` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`src`,`idR`),
  KEY `idR` (`idR`),
  CONSTRAINT `imageRecensione_ibfk_2` FOREIGN KEY (`idR`) REFERENCES `RecensioneOggetto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imageRecensione`
--

LOCK TABLES `imageRecensione` WRITE;
/*!40000 ALTER TABLE `imageRecensione` DISABLE KEYS */;
INSERT INTO `imageRecensione` VALUES ('http://localhost:8080/progettoWeb/jspFile/Finale/Img/square.png',1);
/*!40000 ALTER TABLE `imageRecensione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imageUtente`
--

DROP TABLE IF EXISTS `imageUtente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imageUtente` (
  `src` varchar(150) NOT NULL DEFAULT '',
  `idU` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`src`,`idU`),
  KEY `idI` (`src`),
  KEY `idU` (`idU`),
  CONSTRAINT `imageUtente_ibfk_2` FOREIGN KEY (`idU`) REFERENCES `Utente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imageUtente`
--

LOCK TABLES `imageUtente` WRITE;
/*!40000 ALTER TABLE `imageUtente` DISABLE KEYS */;
INSERT INTO `imageUtente` VALUES ('http://localhost:8080/progettoWeb/jspFile/Finale/Img/square.png',3),('http://localhost:8080/progettoWeb/jspFile/Finale/Img/square.png',4);
/*!40000 ALTER TABLE `imageUtente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `indirizzo`
--

DROP TABLE IF EXISTS `indirizzo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `indirizzo` (
  `idI` int(11) NOT NULL AUTO_INCREMENT,
  `stato` varchar(255) NOT NULL,
  `regione` varchar(255) NOT NULL,
  `provincia` varchar(255) NOT NULL,
  `citta` varchar(255) NOT NULL,
  `via` varchar(255) NOT NULL,
  `nCivico` int(11) NOT NULL,
  `interno` int(11) DEFAULT NULL,
  `latitudine` double DEFAULT NULL,
  `longitudine` double DEFAULT NULL,
  PRIMARY KEY (`idI`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indirizzo`
--

LOCK TABLES `indirizzo` WRITE;
/*!40000 ALTER TABLE `indirizzo` DISABLE KEYS */;
INSERT INTO `indirizzo` VALUES (1,'Italia','Lombardia','Milano','Milano','Via Valassina',27,2,45.5005692,9.187734699999965),(2,'Italia','Lombardia','Milano','Milano','Via Orti',3,NULL,45.4549309,9.198682800000029),(3,'Italia','Trentino Alto Adige','Trento','Trento','Corso 3 Novembre 1918',102,15,46.0608004,11.124980899999969),(4,'Italia','Piemonte','Torino','Torino','Corso 3 Novembre 1918',102,15,45.07489349999999,7.652501499999971),(5,'Italia','Veneto','Treviso','Treviso','Piazza dei Signori',3,NULL,45.6654873,12.245583500000066),(6,'Italia','Trentino Alto Adige','Trento','Trento','Via Brennero',282,NULL,46.0884487,11.117878099999984),(7,'Italia','Piemonte','Torino','Collegno','Viale Svezia',1,NULL,45.097961,7.581563999999958),(8,'Italia','Veneto','Milano','Milano','Piazza del Duomo',1,NULL,45.465043,9.191806000000042);
/*!40000 ALTER TABLE `indirizzo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordiniRicevuti`
--

DROP TABLE IF EXISTS `ordiniRicevuti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordiniRicevuti` (
  `idO` int(11) NOT NULL,
  `idV` int(11) NOT NULL,
  `data` datetime NOT NULL,
  PRIMARY KEY (`idO`,`idV`),
  KEY `idV` (`idV`),
  CONSTRAINT `ordiniRicevuti_ibfk_1` FOREIGN KEY (`idO`) REFERENCES `Ordine` (`idOrdine`),
  CONSTRAINT `ordiniRicevuti_ibfk_2` FOREIGN KEY (`idV`) REFERENCES `Utente` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordiniRicevuti`
--

LOCK TABLES `ordiniRicevuti` WRITE;
/*!40000 ALTER TABLE `ordiniRicevuti` DISABLE KEYS */;
INSERT INTO `ordiniRicevuti` VALUES (1,2,'2017-10-01 19:13:20'),(6,2,'2018-01-15 08:30:10'),(7,2,'2018-01-15 08:43:44');
/*!40000 ALTER TABLE `ordiniRicevuti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recuperopassword`
--

DROP TABLE IF EXISTS `recuperopassword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recuperopassword` (
  `token` varchar(512) NOT NULL,
  `chiave` varchar(512) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recuperopassword`
--

LOCK TABLES `recuperopassword` WRITE;
/*!40000 ALTER TABLE `recuperopassword` DISABLE KEYS */;
INSERT INTO `recuperopassword` VALUES ('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkc2FydG9yaTk2QGdtYWlsLmNvbSIsIm1haWxJRCI6ImRzYXJ0b3JpOTZAZ21haWwuY29tIiwiZXhwIjoxNTE1Njc4MTgzfQ.37X2Y-bHBteWbJvcBVVF1R_m-yq5DmDnGcHxbyIT8BJgFeZMX_dFHQ_axBoMXOgKqjPlDKFb1Rc6CePZ6GEmug','javax.crypto.spec.SecretKeySpec@fa77b841'),('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkc2FydG9yaTk2QGdtYWlsLmNvbSIsIm1haWxJRCI6ImRzYXJ0b3JpOTZAZ21haWwuY29tIiwiZXhwIjoxNTE1Njc4MzYzfQ.L0Amxv1zh-MyT_xuLWbFZKFUIYJopYeUHuc5TP-hI15tp1zwmMnw1Hnzy_kbrogmNEIkXLXzL62vOEBDw0iTmQ','javax.crypto.spec.SecretKeySpec@5882903'),('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkc2FydG9yaTk2QGdtYWlsLmNvbSIsIm1haWxJRCI6ImRzYXJ0b3JpOTZAZ21haWwuY29tIiwiZXhwIjoxNTE1Njc4NTAzfQ.sfmlNteVPujHZM8y08KVKRB1Ip9nShdVk2WJifQG1FLfbljBnGYcmWxt3OMvk96pjK1TaFsoWK2gAFLYhsJJvw','javax.crypto.spec.SecretKeySpec@fa77f3b6'),('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkc2FydG9yaUBnbWFpbC5jb20iLCJtYWlsSUQiOiJkc2FydG9yaUBnbWFpbC5jb20iLCJleHAiOjE1MTU2Nzg2MDd9.mnMxH6mN7q1hLNd427BVl4ZPavK-_cVBUd-Cv2DhepJHwOVRpbU8_c1JwQNSpDrULkPRL5Bjup7_i24U1A7_yA','javax.crypto.spec.SecretKeySpec@588364e'),('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkc2FydG9yaTk2QGdtYWlsLmNvbSIsIm1haWxJRCI6ImRzYXJ0b3JpOTZAZ21haWwuY29tIiwiZXhwIjoxNTE1Njc4NzQ4fQ.lu53bQApkCXILqwTRp9kzGm9b-Jeor2MEVGGVl9xOy65htKVSrIF_lcgJyKgtebKTH1ZxAgwZrPUV7L6Yh7L4A','javax.crypto.spec.SecretKeySpec@fa77941b'),('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkbnNqZmFkbmFkbXBhbWQiLCJtYWlsSUQiOiJkbnNqZmFkbmFkbXBhbWQiLCJleHAiOjE1MTU2ODI4NzF9.f_IewRFiVhyM5cyPVdu4_Tc9wFw_pK82EMs09JyckZOxW_OzuhcHWpVrCkPzCADe0hhw73uMo2lkrSm-8U24Vw','javax.crypto.spec.SecretKeySpec@fa77ca01'),('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkc2FydG9yaTk2QGdtYWlsLmNvbSIsIm1haWxJRCI6ImRzYXJ0b3JpOTZAZ21haWwuY29tIiwiZXhwIjoxNTE1NjgyOTMwfQ.FfZFyiCm3Js6hZ0vZ7uPON6bls2x8kKQA-tr638N7-5xn_HqezFh6jJtlYIkw93PQzktJBGJUfCzhT7ggcWJAg','javax.crypto.spec.SecretKeySpec@fa77e168'),('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkc2FydG9yaTk2QGdtYWlsLmNvbSIsIm1haWxJRCI6ImRzYXJ0b3JpOTZAZ21haWwuY29tIiwiZXhwIjoxNTE1Njg1ODgwfQ.mYlUVia9qWX50Von1ULoF1fqndUXeyxIjiCSh0PQ8Sdo24lynmACGNuMnpH90uz0SB6FpTzxAHc_Y49IHuFuFw','eFf6hJX3GQxBenRAQVKkouNqx2RiA8PEHuTZ3gAjGSygrPNleHPrz9Ud3Bo57AGHk/nJe2qk2yY3FGSsSLFybA=='),('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkc2FydG9yaTk2QGdtYWlsLmNvbSIsIm1haWxJRCI6ImRzYXJ0b3JpOTZAZ21haWwuY29tIiwiZXhwIjoxNTE1Njg2Mjk2fQ.9Kh6vPUSVaMFjAJ2z3l-WYSc05HeQcYUy6Djo8IzlmmKeXoaoqdRe79hIpNBKkSurD8h0KJpXaFXfBmrWm4kmw','U+aEVc0G5/8ghw5mn8av20Ne5jkmh2tkYmBVKGnh4IZz/RPRiS7GAcnJT5tP+5VB6REnf47mABdotuyLRjW7Uw=='),('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkc2FydG9yaTk2QGdtYWlsLmNvbSIsIm1haWxJRCI6ImRzYXJ0b3JpOTZAZ21haWwuY29tIiwiZXhwIjoxNTE1Njg4NzA4fQ.9yxFikgnrX5FDzC9Nh79MH9PlCEUsG9BsbWb0BVoROg8W1ezvtoOSKA2emLRAweNY0lftnXgV_jAC4azqXcZsQ','s4SNE9mayCZpIXx82A6EBuJ6tzFgsDE/nBk6mY6eucnF6tbQafMQY7Y4dIVf8OB99imRz2u7/MxBOMgO3YYuJw=='),('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkc2FydG9yaTk2QGdtYWlsLmNvbSIsIm1haWxJRCI6ImRzYXJ0b3JpOTZAZ21haWwuY29tIiwiZXhwIjoxNTE1Njg4ODMzfQ._j8fACBbV4b8zCqT9KFWftd62w8OAItwknNpCGPrsIxAPBfoFhtqZ4GI_Vh5310Ml1q8O_lChG90ArzJWU-FLg','e+nRMLRvzswR9DVmj9MojlkEqUI6jE5Fx5EqxtUxlZHw+fhaZkj3U/bBQ7yHo57lZ82rm78ywtoTkqdvStZGBg=='),('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkc2FydG9yaTk2QGdtYWlsLmNvbSIsIm1haWxJRCI6ImRzYXJ0b3JpOTZAZ21haWwuY29tIiwiZXhwIjoxNTE1Njg5MjUxfQ.5Xy5kWUxrVdRYpOdb8Hr3M_Ow-Q7UozFLE58Igk_x6gCFs2MCF_UcjdXVqoeON5-YxurYZZ__2R058UpYq4pbA','0hd0oNmQYDqZG1bXqRegfZ00ka5wJS62pGI36ReH7RvjMmUrqKfMXwTg79342m1enU5lppFzJtzorBtGY1WCfg=='),('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkc2FydG9yaTk2QGdtYWlsLmNvbSIsIm1haWxJRCI6ImRzYXJ0b3JpOTZAZ21haWwuY29tIiwiZXhwIjoxNTE1Njg5MzM3fQ.58ebv5CRXSs4y0l91NCQycPkbKzIu0VPITPku_VTMOQe5ui4gzQ7HODzzQ0YvYV0p6n4VCjQGBxan-pczbXTOw','aRPk7iB4X3LpMURis+c9+hhejbqdcpo3zsX0LlSsZlzpR6seK4eBo3HxvtLrLxhjjJqUH3ZyYPL0adIZ0+er1g=='),('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkc2FydG9yaTk2QGdtYWlsLmNvbSIsIm1haWxJRCI6ImRzYXJ0b3JpOTZAZ21haWwuY29tIiwiZXhwIjoxNTE1Njg5MzU3fQ.j8YJY-CF6hkpqTpUFUodDTBuAR14_-6t9jIdCs3iTJ_BvKJeZrQ6rkJgBjHzeTS-ZODlyMOlq-7-359-owKl-A','pL7BF3V7SXgJRCqGbEHqkuWDmgWA3uLCk2wRKfSPDdhX4UEiO4mgCTXhR2ioJqJ1JSOvLotNZMd+uU43OZBU/Q=='),('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkc2FydG9yaTk2QGdtYWlsLmNvbSIsIm1haWxJRCI6ImRzYXJ0b3JpOTZAZ21haWwuY29tIiwiZXhwIjoxNTE1NjkwMzcyfQ.btPzh-eSgvA_LwOob3KFVXL63EBrTc4Y7b8H2D7oR0vqooLFYpCIV1CjsM9Lp9iZFPkZ4yhdPWcCuvm4qT_iqQ','slIQMBPJoknJJlmp420keg9bWdrl+OzRl5fGs0KlsgHvznDSvVQkMiNe2gurrZom3wO++N93l4wca3UEdMSKlw=='),('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkc2FydG9yaTk2QGdtYWlsLmNvbSIsIm1haWxJRCI6ImRzYXJ0b3JpOTZAZ21haWwuY29tIiwiZXhwIjoxNTE1NjkxNzIwfQ.JrazvAM5mBV_A8U9vEP8tLZ8c1WDHiy1HK7MiJqRFv18t9ANyhgVb4HsXsTcp6t0IWkkGGqkkfPSmUOakoQwcQ','g6338SsY6CKaCksOblnbOhcXXXuv/znOS2WRehDbsYakqf9poV7i7Gf5ohilWHCK0RQt1cDOFxqabsbh4rMReA=='),('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkc2FydG9yaTk2QGdtYWlsLmNvbSIsIm1haWxJRCI6ImRzYXJ0b3JpOTZAZ21haWwuY29tIiwiZXhwIjoxNTE1Njk3ODIzfQ.9i8b2ZcsmaCUS908aP42_BO1Qlm8JuEmLScqydiQ_u0kdPz4LzQlEdVTg1bxS_YtGQTrUVCuTFs02i5v9C0XsQ','sRYpMMp2fIgbz0+G3NIbINZGajPPS8GndLrawVVc+Kecn4V8uDS3DGcoxNkCpLQzlJO+uqTVU58jlxV9R3cRAA=='),('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkc2FydG9yaTk2QGdtYWlsLmNvbSIsIm1haWxJRCI6ImRzYXJ0b3JpOTZAZ21haWwuY29tIiwiZXhwIjoxNTE1ODczNDg1fQ.zD3XaGeTxl94sdY5nNSI54SejghJTjT8NIPHmuBBnzRUvEMQ90hJzG0HRfrIy5TYB52wakOOFH6q3wh_rwXQJA','Xc4hQFFB+rfR9By7KCvayQ/2JB9Jy/u2f0ZaoopJT0ocxpQyIfaxoraG1TEvkohAHZ0le0jJTqXWGuOGCCQWoA=='),('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkc2FydG9yaTk2QGdtYWlsLmNvbSIsIm1haWxJRCI6ImRzYXJ0b3JpOTZAZ21haWwuY29tIiwiZXhwIjoxNTE1ODgxMjcxfQ.IXHIY4gmmevO7_fvQdCAC80Cn6ZipzZcHVoR-sOba7kPLloBLHCFBvQGsLASZPFESuZnrNlzwDa9I9rHDxWIvg','vfhMfD5q+VpNwW8qf9mfib30KSC3tDbcqgLE9TkXt/C2ox1d2UfvHYHPXzHEQoEQU119NY3Q+ByX5A7EXFy/RA=='),('eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkc2FydG9yaTk2QGdtYWlsLmNvbSIsIm1haWxJRCI6ImRzYXJ0b3JpOTZAZ21haWwuY29tIiwiZXhwIjoxNTE2NzExMTYyfQ.VGl9Ci0iMPgpF_DvkH1e1N7IZ3aBAqceUrsw0gnCYaM13KJW2cRyc2DiZNyMG-K1gP8M0cDIdd8aNHvRch3xoA','Q2wXoZxuSrqEVdY6LkhoUKIOuEKYKJcHra493INiPkNV9Ufj0ksy8n2XlGxgvcpsrmY59GA/k67UxJMxOewCIQ==');
/*!40000 ALTER TABLE `recuperopassword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spedizioneOggetto`
--

DROP TABLE IF EXISTS `spedizioneOggetto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spedizioneOggetto` (
  `idS` int(11) NOT NULL,
  `idO` varchar(32) NOT NULL,
  `idOrdine` int(11) NOT NULL,
  PRIMARY KEY (`idS`,`idO`,`idOrdine`),
  KEY `nomeSensato` (`idO`),
  CONSTRAINT `nomeSensato` FOREIGN KEY (`idO`) REFERENCES `Oggetto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `nomeSensatoAgain` FOREIGN KEY (`idS`) REFERENCES `tipoSpedizione` (`idS`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spedizioneOggetto`
--

LOCK TABLES `spedizioneOggetto` WRITE;
/*!40000 ALTER TABLE `spedizioneOggetto` DISABLE KEYS */;
/*!40000 ALTER TABLE `spedizioneOggetto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoSpedizione`
--

DROP TABLE IF EXISTS `tipoSpedizione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipoSpedizione` (
  `idS` int(11) NOT NULL AUTO_INCREMENT,
  `idN` int(11) NOT NULL,
  `Nome` varchar(50) NOT NULL,
  `Prezzo` double NOT NULL,
  `Corriere` varchar(50) NOT NULL,
  `tempoRichiesto` int(11) NOT NULL,
  `numeroMassimo` int(11) NOT NULL DEFAULT '5',
  PRIMARY KEY (`idS`,`idN`),
  KEY `negozioSpedizione` (`idN`),
  CONSTRAINT `negozioSpedizione` FOREIGN KEY (`idN`) REFERENCES `Negozio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoSpedizione`
--

LOCK TABLES `tipoSpedizione` WRITE;
/*!40000 ALTER TABLE `tipoSpedizione` DISABLE KEYS */;
INSERT INTO `tipoSpedizione` VALUES (2,1,'Spedizione Veloce',15,'GLS',3,5),(3,1,'Spedizione su Appuntamento',65,'DHL',3,5),(4,2,'Spedizione Standard',6,'SGA',10,5),(5,2,'Spedizione Veloce',5,'DHL',10,5),(7,3,'Spedizione Standard',7,'Non specificato',2,5),(8,3,'Spedizione Veloce',15,'Non specificato',2,5),(9,3,'Spedizione Express',45,'Non specificato',2,5),(10,3,'Spedizione su Appuntamento',60,'SGA',2,5),(11,1,'Spedizione standard',8,'Bartolini',4,5),(12,1,'SPedizionasda',12,'brt',5,5),(13,16,'spedizione poco sicura',2,'poste super italiane',4,5);
/*!40000 ALTER TABLE `tipoSpedizione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `cognome` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `valutazione` double DEFAULT NULL,
  `UtenteType` int(11) NOT NULL DEFAULT '0',
  `emailConfermata` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `idUtenteIndex` (`id`,`UtenteType`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,'Paolo','Rossi','paolored@gmail.com','386e2e60f03df908eaf7a70b928685ef','http://localhost:8080/progettoWeb/jspFile/Finale/Img/square.png',NULL,1,'\0'),(2,'Giorgio','Ugolini','giorgiougo@libero.it','5775592936944611ab1c1270ff7eecf9','http://localhost:8080/progettoWeb/jspFile/Finale/Img/square.png',NULL,1,'\0'),(3,'Andrea','Colombo','andrea.uccello@tiscali.it','3be747e3b8bffd2759019b0b337cc37e','http://localhost:8080/progettoWeb/jspFile/Finale/Img/square.png',NULL,0,'\0'),(4,'Fabio','Basilio','fabiob75@outlook.com','d240445b5f0c9b2fd37b7b75f820d156','http://localhost:8080/progettoWeb/jspFile/Finale/Img/square.png',NULL,0,'\0'),(5,'Luigi','Di Prima','luigilostesso@libero.it','a8778577393eed9e801cbeb1c5ca22b3','http://localhost:8080/progettoWeb/jspFile/Finale/Img/square.png',NULL,2,'\0'),(8,'Damiano','Sartori','dsartori96@gmail.com','5f4dcc3b5aa765d61d8327deb882cf99','http://localhost:8080/progettoWeb/jspFile/Finale/Img/square.png',NULL,1,'\0'),(9,'Virginia','Pieropan','virginia.pieropan@gmail.com','password','http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/square.png',0,0,'\0');
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'progettoweb'
--
/*!50106 SET @save_time_zone= @@TIME_ZONE */ ;
/*!50106 DROP EVENT IF EXISTS `ControlloScontiAttivi` */;
DELIMITER ;;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;;
/*!50003 SET character_set_client  = utf8 */ ;;
/*!50003 SET character_set_results = utf8 */ ;;
/*!50003 SET collation_connection  = utf8_general_ci */ ;;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;;
/*!50003 SET @saved_time_zone      = @@time_zone */ ;;
/*!50003 SET time_zone             = '+00:00' */ ;;
/*!50106 CREATE*/ /*!50117 DEFINER=`progettoweb`@`%`*/ /*!50106 EVENT `ControlloScontiAttivi` ON SCHEDULE EVERY 1 DAY STARTS '2017-07-22 00:01:00' ENDS '2018-07-22 00:01:00' ON COMPLETION PRESERVE ENABLE COMMENT 'Evento utilizzato per eliminare gli sconti terminati' DO UPDATE Oggetto SET Oggetto.`sconto` = 0, Oggetto.`dataFineSconto` = IF(Oggetto.`dataFineSconto`<CURDATE(), NULL,Oggetto.`dataFineSconto`) */ ;;
/*!50003 SET time_zone             = @saved_time_zone */ ;;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;;
/*!50003 SET character_set_client  = @saved_cs_client */ ;;
/*!50003 SET character_set_results = @saved_cs_results */ ;;
/*!50003 SET collation_connection  = @saved_col_connection */ ;;
DELIMITER ;
/*!50106 SET TIME_ZONE= @save_time_zone */ ;

--
-- Dumping routines for database 'progettoweb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-23 13:49:46
