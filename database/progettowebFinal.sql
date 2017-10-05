-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Set 11, 2017 alle 15:26
-- Versione del server: 5.5.57-0+deb8u1
-- PHP Version: 5.6.30-0+deb8u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `progettoweb`
--
CREATE DATABASE IF NOT EXISTS `progettoweb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `progettoweb`;

-- --------------------------------------------------------

--
-- Struttura della tabella `Assistenza`
--

DROP TABLE IF EXISTS `Assistenza`;
CREATE TABLE IF NOT EXISTS `Assistenza` (
`id` int(11) NOT NULL,
  `idUtente` int(11) NOT NULL,
  `idVenditore` int(11) DEFAULT NULL,
  `idAmministratore` int(11) NOT NULL,
  `idOrdine` int(11) DEFAULT NULL,
  `idOggetto` varchar(32) DEFAULT NULL,
  `stato` int(11) DEFAULT '0',
  `soluzione` varchar(2500) DEFAULT NULL,
  `dataApertura` datetime NOT NULL,
  `dataChiusura` datetime DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `Assistenza`
--

INSERT INTO `Assistenza` (`id`, `idUtente`, `idVenditore`, `idAmministratore`, `idOrdine`, `idOggetto`, `stato`, `soluzione`, `dataApertura`, `dataChiusura`) VALUES
(1, 1, 6, 1, NULL, NULL, 0, NULL, '0000-00-00 00:00:00', NULL),
(2, 1, NULL, 2, NULL, NULL, 0, NULL, '0000-00-00 00:00:00', NULL),
(3, 1, NULL, 3, NULL, NULL, 0, NULL, '0000-00-00 00:00:00', NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `Carrello`
--

DROP TABLE IF EXISTS `Carrello`;
CREATE TABLE IF NOT EXISTS `Carrello` (
  `idUtente` int(11) NOT NULL,
  `idOrdine` int(11) NOT NULL,
  `subTotal` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `Carrello`
--

INSERT INTO `Carrello` (`idUtente`, `idOrdine`, `subTotal`) VALUES
(1, 1, 850),
(2, 3, 100),
(3, 3, 100);

-- --------------------------------------------------------

--
-- Struttura della tabella `Categoria`
--

DROP TABLE IF EXISTS `Categoria`;
CREATE TABLE IF NOT EXISTS `Categoria` (
`id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `sottoCategoria` varchar(50) DEFAULT NULL,
  `descrizione` varchar(500) NOT NULL,
  `oggettiPresenti` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `Categoria`
--

INSERT INTO `Categoria` (`id`, `nome`, `sottoCategoria`, `descrizione`, `oggettiPresenti`) VALUES
(1, 'Elettronica', NULL, 'Materiale Elettronico', 3),
(2, 'Casa', NULL, 'Materiale per Casa e Cucina', 1),
(3, 'Abbigliamento', NULL, 'il mondo dei vestiti', 0),
(4, 'Alimentari', NULL, 'Ciò che soddisferà tutti i tuoi gusti', 0),
(5, 'Auto e Moto', NULL, 'Il mondo dei motori ai tuoi piedi', 0),
(6, 'Bellezza', NULL, 'Coccolati con questi prodotti', 0),
(7, 'Cancelleria e Ufficio', NULL, 'Tutto ciò che ti sarà utile avere sulla tua scrivania', 0),
(8, 'CD e Vinili', NULL, 'Lasciati trasportare in un mondo di note', 0),
(9, 'Fai da te', NULL, 'Costruisci ciò che ti circonda', 0),
(10, 'Film e TV', NULL, 'Non cambiare canale, resta sincronizzato sulle offerte', 0),
(11, 'Giardino e giardinaggio', NULL, 'Rendi il tuo giardino uno spettacolo', 0),
(12, 'Giochi e giocattoli', NULL, 'Divertiti come non mai', 0),
(13, 'Gioielli', NULL, 'Rendi prezioso il tuo corpo', 0),
(14, 'Fatto a mano', NULL, 'Guarda come potremo sorprenderti', 0),
(15, 'Illuminazione', NULL, 'Rendi luminosa la tua strada', 0),
(16, 'Industria e scienza', NULL, 'Esplora il mondo con un occhio scientifico', 0),
(17, 'Informatica', NULL, 'Gioca lavora o divertiti con tutte le ultime tecnologie uscite in commercio', 0),
(18, 'Libri', NULL, 'Entra in un nuovo mondo fatto di fantasia', 0),
(19, 'Moda', NULL, 'Fatti travolgere dalle ultime creazioni', 0),
(20, 'Orologi', NULL, 'Rendi ogni secondo speciale', 0),
(21, 'Prima infanzia', NULL, 'Il tuo bambino ha bisogno di te! usa solo prodotti di prima scelta', 0),
(22, 'Prodotti per animali', NULL, 'Fai gustare al tuo animale qualcosa creato appositamente per lui', 0),
(23, 'Salute', NULL, 'Prenditi cura di te stesso', 0),
(24, 'Scarpe e borse', NULL, 'L''accessorio giusto al momento giusto', 0),
(25, 'Software', NULL, 'Il meglio per il tuo computer', 0),
(26, 'Sport e tempo libero', NULL, 'Dai la giusta importanza ai tuoi hobby', 0),
(27, 'Strumenti musicali e dj', NULL, 'Solo il meglio per i musicisti di oggi e domani', 0),
(28, 'Valigeria', NULL, 'Non lasciare niente a casa ', 0),
(29, 'Videogiochi', NULL, 'Gustati i migliori titoli del momento', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `imageNegozio`
--

DROP TABLE IF EXISTS `imageNegozio`;
CREATE TABLE IF NOT EXISTS `imageNegozio` (
  `src` varchar(150) NOT NULL,
  `idN` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `imageNegozio`
--

INSERT INTO `imageNegozio` (`src`, `idN`) VALUES
('imgNeg1', 1),
('mediaworld2', 1),
('imgNeg2', 2),
('aliexpressimg1', 5);

-- --------------------------------------------------------

--
-- Struttura della tabella `imageOggetto`
--

DROP TABLE IF EXISTS `imageOggetto`;
CREATE TABLE IF NOT EXISTS `imageOggetto` (
  `src` varchar(150) NOT NULL,
  `idO` varchar(32) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `imageOggetto`
--

INSERT INTO `imageOggetto` (`src`, `idO`) VALUES
('imgOgg1', '1'),
('imgOgg2', '2');

-- --------------------------------------------------------

--
-- Struttura della tabella `imageRecensione`
--

DROP TABLE IF EXISTS `imageRecensione`;
CREATE TABLE IF NOT EXISTS `imageRecensione` (
  `src` varchar(150) NOT NULL,
  `idR` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `imageRecensione`
--

INSERT INTO `imageRecensione` (`src`, `idR`) VALUES
('imgRec1', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `imageUtente`
--

DROP TABLE IF EXISTS `imageUtente`;
CREATE TABLE IF NOT EXISTS `imageUtente` (
  `src` varchar(150) NOT NULL DEFAULT '',
  `idU` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `imageUtente`
--

INSERT INTO `imageUtente` (`src`, `idU`) VALUES
('newSrc', 1),
('imgUsr1', 2),
('imgUsr2', 3),
('imgUtente4', 4),
('imgUtente5', 5),
('imgUtente6', 6),
('imgUsr3', 7),
('imgUtente8', 8),
('imgUtente9', 9),
('imgUtente11', 11),
('imgUtente12', 12),
('imgUtente13', 13),
('imgUtente14', 14),
('imgUtente15', 15),
('imgUtente16', 16),
('imgUtente17', 17),
('imgUtente18', 18),
('imgUtente19', 19),
('imgUtente20', 20),
('imgUtente21', 21),
('imgUtente22', 22),
('imgUtente23', 23),
('imgUtente24', 24),
('imgUtente25', 25),
('imgUtente26', 26),
('imgUtente27', 27),
('imgUtente28', 28),
('imgUtente29', 29),
('imgUtente30', 30),
('imgUtente31', 31),
('imgUtente32', 32),
('imgUtente33', 33),
('imgUtente34', 34),
('imgUtente35', 35),
('imgUtente36', 36),
('imgUtente37', 37),
('imgUtente38', 38),
('imgUtente39', 39),
('imgUtente40', 40),
('imgUtente41', 41),
('imgUtente42', 42),
('imgUtente43', 43),
('imgUtente44', 44),
('imgUtente45', 45);

-- --------------------------------------------------------

--
-- Struttura della tabella `Indirizzo`
--

DROP TABLE IF EXISTS `Indirizzo`;
CREATE TABLE IF NOT EXISTS `Indirizzo` (
`idI` int(11) NOT NULL,
  `stato` varchar(255) NOT NULL,
  `regione` varchar(255) NOT NULL,
  `provincia` varchar(255) NOT NULL,
  `citta` varchar(255) NOT NULL,
  `via` varchar(255) NOT NULL,
  `nCivico` int(11) NOT NULL,
  `interno` int(11) DEFAULT NULL,
  `latitudine` double DEFAULT NULL,
  `longitudine` double DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `Indirizzo`
--

INSERT INTO `Indirizzo` (`idI`, `stato`, `regione`, `provincia`, `citta`, `via`, `nCivico`, `interno`, `latitudine`, `longitudine`) VALUES
(1, 'Italia', 'Veneto', 'Treviso', 'Paese', 'Via Pastrengo', 5, 1, 45.6699367, 12.17841889999997),
(2, 'Italia', 'Veneto', 'Vicenza', 'Bassano del Grappa', 'Via roma', 4, 2, 457665782, 11.73447020000032),
(3, 'Italia', 'Veneto', 'Padova', 'Cittadella', 'Via europa', 4, 3, 45.6501974, 11.78042479999992),
(4, 'Italia', 'Lombardia', 'Milano', 'Milano', 'Via vittorio emanuele orlando', 4, 4, 45.4181159, 9.174590399999943),
(5, 'Italia', 'Lombardia', 'Brescia', 'Brescia', 'Via cristo soldo', 4, 1, 45.5604776, 10.20214680000037),
(6, 'Italia', 'Lazio', 'Roma', 'Roma', 'Via garibaldi', 4, 2, 41.8911522999999, 12.467026499999974),
(7, 'Italia', 'Piemonte', 'Torino', 'Torino', 'Via Matteo pescatore', 4, 3, 45.0661486, 7.69552520000002),
(8, 'Italia', 'Trentino Alto Adige', 'Trento', 'Trento', 'Via Roma', 4, 4, 45.9164955, 11.065576100000044),
(9, 'Italia', 'Trentino Alto Adige', 'Trento', 'Povo', 'Via Sommarive', 4, 5, 46.06416429999999, 11.150366099999928),
(10, 'Italia', 'Lombardia', 'Bergamo', 'Bergamo', 'Via Vittorio Gasparini', 4, 6, 45.6871799, 9.693280200000004),
(11, 'Italia', 'Veneto', 'Treviso', 'Paese', 'Via Olimpia', 5, 1, 45.6782062, 12.160833400000001),
(12, 'Italia', 'Veneto', 'Vicenza', 'Bassano del Grappa', 'Viale Alcide De Gasperi', 4, 2, 45.762856, 11.732135599999992),
(13, 'Italia', 'Veneto', 'Padova', 'Cittadella', 'Via Angelo Gabrielli', 4, 3, 45.644099, 11.782445499999994),
(14, 'Italia', 'Lombardia', 'Milano', 'Milano', 'Via vittorio emanuele', 5, 4, 45.4642035, 9.189982),
(15, 'Italia', 'Lombardia', 'Brescia', 'Brescia', 'Via Tortona', 5, 5, 45.4537132, 9.168663899999956),
(16, 'Italia', 'Lazio', 'Roma', 'Roma', 'Via del corso', 5, 6, 41.9095265, 12.476847200000066),
(17, 'Italia', 'Piemonte', 'Torino', 'Torino', 'Via nizza', 5, 1, 45.0614125, 7.679637200000002),
(18, 'Italia', 'Trentino Alto Adige', 'Trento', 'Trento', 'Via del Brennero', 5, 3, 46.0754521, 11.124638900000036),
(19, 'Italia', 'Trentino Alto Adige', 'Trento', 'Povo', 'Via Della Resistenza', 5, 4, 46.0661954, 11.15495599999997),
(20, 'Italia', 'Lombardia', 'Bergamo', 'Bergamo', 'Via Borgo Palazzo', 5, 5, 45.6980742, 9.678067400000032),
(21, 'italia', 'veneto', 'vicenza', 'bassano del grappa', 'via Cristoforo Colombo', 5, 2, 45.7435524, 11.73428690000003),
(22, 'Italia', 'Veneto', 'Vicenza', 'Vicenza', 'Viale Venezia', 12, 1, 45.5415009, 11.544405500000039),
(23, 'Italia', 'Veneto', 'Vicenza', 'Vicenza', 'Via Aldo Moro', 46, 2, 45.542786, 11.586290700000063),
(24, 'Italia', 'Veneto', 'Vicenza', 'Vicenza', 'Via del Commercio', 34, 1, 45.5278482, 11.502893200000017),
(25, 'Italia', 'Veneto', 'Vicenza', 'Vicenza', 'Viale della pace', 6, 1, 45.5450869, 11.566271799999981),
(26, 'Italia', 'Veneto', 'Vicenza', 'Vicenza', 'Via Schio', 41, 2, 45.5452697, 11.555800200000022),
(27, 'Italia', 'Veneto', 'Vicenza', 'Bassano del Grappa', 'Via Capitelvecchio', 12, 1, 45.75286699999999, 11.750786000000062),
(28, 'Italia', 'Veneto', 'Vicenza', 'Bassano del Grappa', 'Via rovereto', 32, 1, 45.7648889, 11.725838100000033),
(29, 'Italia', 'Veneto', 'Vicenza', 'Bassano del Grappa', 'Viale Venezia', 15, 1, 45.7688253, 11.740909399999964),
(30, 'Italia', 'Veneto', 'Vicenza', 'Bassano del Grappa', 'Via Wagner', 17, 1, 45.75071, 11.731499999999983),
(31, 'Italia', 'Veneto', 'Vicenza', 'Bassano del Grappa', 'Via Cristoforo Colombo', 2, 1, 45.7433344, 11.734265400000027),
(32, 'Italia', 'Veneto', 'Vicenza', 'Breganze', 'Via torre', 1, NULL, NULL, NULL),
(33, 'Italia', 'Veneto', 'Vicenza', 'Breganze', 'Via fossa', 1, NULL, NULL, NULL),
(34, 'Italia', 'Veneto', 'Vicenza', 'Breganze', 'Via marittima', 2, NULL, NULL, NULL),
(35, 'Italia', 'Veneto', 'Vicenza', 'Breganze', 'Via Caltanissetta', 63, NULL, NULL, NULL),
(36, 'Italia', 'Veneto', 'Vicenza', 'Breganze', 'Via breganzina', 4, NULL, NULL, NULL),
(37, 'Italia', 'Veneto', 'Vicenza', 'Breganze', 'Piazza Alcide', 3, NULL, NULL, NULL),
(38, 'Italia', 'Veneto', 'Vicenza', 'Sandrigo', 'Via dei Colli', 52, NULL, NULL, NULL),
(39, 'Italia', 'Veneto', 'Vicenza', 'Sandrigo', 'Via Vicenza', 61, NULL, NULL, NULL),
(40, 'Italia', 'Veneto', 'Vicenza', 'Sandrigo', 'Via Brenta', 43, NULL, NULL, NULL),
(41, 'Italia', 'Veneto', 'Vicenza', 'Sandrigo', 'Via Storica', 1, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Struttura della tabella `IndirizzoUtente`
--

DROP TABLE IF EXISTS `IndirizzoUtente`;
CREATE TABLE IF NOT EXISTS `IndirizzoUtente` (
  `idI` int(11) NOT NULL,
  `idU` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `IndirizzoUtente`
--

INSERT INTO `IndirizzoUtente` (`idI`, `idU`) VALUES
(1, 1),
(21, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9);

-- --------------------------------------------------------

--
-- Struttura della tabella `Negozio`
--

DROP TABLE IF EXISTS `Negozio`;
CREATE TABLE IF NOT EXISTS `Negozio` (
`id` int(11) NOT NULL,
  `idVenditore` int(11) NOT NULL,
  `nomeNegozio` varchar(255) NOT NULL,
  `valutazione` double DEFAULT NULL,
  `attivo` tinyint(1) NOT NULL DEFAULT '1',
  `idI` int(11) NOT NULL,
  `dataApertura` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `linkSito` varchar(150) DEFAULT NULL,
  `orarioNegozio` varchar(400) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `Negozio`
--

INSERT INTO `Negozio` (`id`, `idVenditore`, `nomeNegozio`, `valutazione`, `attivo`, `idI`, `dataApertura`, `linkSito`, `orarioNegozio`) VALUES
(1, 6, 'Mediaworld', NULL, 1, 11, '0000-00-00 00:00:00', NULL, 'Lunedì: 8:00 - 19:00, Martedì: 8:00 - 19:00, Mercoledì: 8:00 - 19:00, Giovedì: 8:00 - 19:00, Venerdì: 8:00 - 19:00, Sabato: 8:00 - 19:00, Domenica: Chiuso'),
(2, 7, 'Trony', NULL, 1, 14, '0000-00-00 00:00:00', NULL, 'Lunedì: 8:00 - 19:00, Martedì: 8:00 - 19:00, Mercoledì: 8:00 - 19:00, Giovedì: 8:00 - 19:00, Venerdì: 8:00 - 19:00, Sabato: 8:00 - 19:00, Domenica: Chiuso'),
(3, 8, 'Unieuro', NULL, 1, 12, '0000-00-00 00:00:00', NULL, 'Lunedì: 8:00 - 19:00, Martedì: 8:00 - 19:00, Mercoledì: 8:00 - 19:00, Giovedì: 8:00 - 19:00, Venerdì: 8:00 - 19:00, Sabato: 8:00 - 19:00, Domenica: Chiuso'),
(4, 9, 'Kasanova', NULL, 1, 13, '0000-00-00 00:00:00', NULL, 'Lunedì: 8:00 - 19:00, Martedì: 8:00 - 19:00, Mercoledì: 8:00 - 19:00, Giovedì: 8:00 - 19:00, Venerdì: 8:00 - 19:00, Sabato: 8:00 - 19:00, Domenica: Chiuso'),
(5, 6, 'aliexpress', NULL, 1, 18, '2017-07-18 14:24:07', NULL, 'Lunedì: 8:00 - 19:00, Martedì: 8:00 - 19:00, Mercoledì: 8:00 - 19:00, Giovedì: 8:00 - 19:00, Venerdì: 8:00 - 19:00, Sabato: 8:00 - 19:00, Domenica: Chiuso'),
(6, 7, 'Ikea', 8, 0, 15, '2017-07-20 14:02:41', NULL, 'Lunedì: 8:00 - 19:00, Martedì: 8:00 - 19:00, Mercoledì: 8:00 - 19:00, Giovedì: 8:00 - 19:00, Venerdì: 8:00 - 19:00, Sabato: 8:00 - 19:00, Domenica: Chiuso');

-- --------------------------------------------------------

--
-- Struttura della tabella `Oggetto`
--

DROP TABLE IF EXISTS `Oggetto`;
CREATE TABLE IF NOT EXISTS `Oggetto` (
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
  `categoria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `Oggetto`
--

INSERT INTO `Oggetto` (`id`, `idNegozio`, `nome`, `nomeDownCase`, `prezzo`, `descrizione`, `ritiroInNegozio`, `disponibilita`, `statoDisponibilita`, `sconto`, `dataFineSconto`, `categoria`) VALUES
('1', 1, 'iPhone SE', 'iphone se', 400, 'iPhone SE 16GB Bianco', 0, 5, 0, 0, NULL, 1),
('2', 2, 'HTC U11', 'htc u11', 450, 'HTC U11 32GB Bianco', 0, 6, 0, 0, NULL, 1),
('3', 3, 'UAUEI XYZ', 'uauei xyz', 100, 'UAUEI XYZ Telefono inutile', 0, 1, 0, 0, NULL, 1),
('4', 4, 'Pentola Acciaio Inossidabile', 'pentola acciaio inossidabile', 50, 'Pentola Acciaio Inossidabile, insdistrutibile!', 1, 45, 0, 0, NULL, 2),
('5', 5, 'Earpods', 'earpods', 29, 'Auricolari wire apple', 1, 65, 1, 0, NULL, 1);

--
-- Trigger `Oggetto`
--
DROP TRIGGER IF EXISTS `AggiornamentoOrdiniSeVieneModificatoIlPrezzo`;
DELIMITER //
CREATE TRIGGER `AggiornamentoOrdiniSeVieneModificatoIlPrezzo` AFTER UPDATE ON `Oggetto`
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

END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `NomeSenzaLettereMaiuscole`;
DELIMITER //
CREATE TRIGGER `NomeSenzaLettereMaiuscole` AFTER INSERT ON `Oggetto`
 FOR EACH ROW BEGIN

UPDATE Oggetto
	SET nomeDownCase = LOWER(nome)
    WHERE Oggetto.id = new.id;

END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Struttura della tabella `Ordine`
--

DROP TABLE IF EXISTS `Ordine`;
CREATE TABLE IF NOT EXISTS `Ordine` (
`idOrdine` int(11) NOT NULL,
  `idOggetto` varchar(32) NOT NULL,
  `idNegozio` int(11) NOT NULL,
  `idUtente` int(11) NOT NULL,
  `stato` int(11) NOT NULL DEFAULT '0',
  `quantita` int(11) NOT NULL DEFAULT '1',
  `codiceTracking` varchar(40) DEFAULT NULL,
  `dataArrivoPresunta` date DEFAULT NULL,
  `dataOrdine` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `prezzoDiAcquisto` double DEFAULT NULL,
  `idS` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `Ordine`
--

INSERT INTO `Ordine` (`idOrdine`, `idOggetto`, `idNegozio`, `idUtente`, `stato`, `quantita`, `codiceTracking`, `dataArrivoPresunta`, `dataOrdine`, `prezzoDiAcquisto`, `idS`) VALUES
(1, '1', 1, 1, 0, 1, NULL, NULL, '0000-00-00 00:00:00', 400, NULL),
(1, '1', 1, 3, 1, 2, NULL, NULL, '2017-07-21 13:30:46', 200, NULL),
(1, '2', 2, 1, 0, 1, NULL, NULL, '0000-00-00 00:00:00', 450, NULL),
(2, '2', 2, 3, 1, 1, NULL, NULL, '2017-07-23 09:27:52', 200, NULL),
(3, '3', 3, 2, 0, 1, NULL, NULL, '0000-00-00 00:00:00', 100, NULL),
(3, '3', 3, 3, 0, 1, NULL, NULL, '2017-07-25 07:53:01', 100, NULL),
(3, '4', 4, 4, 5, 1, NULL, NULL, '2017-07-25 07:58:55', 50, NULL),
(4, '1', 1, 1, 2, 1, NULL, NULL, '2017-07-19 08:50:45', 500, NULL),
(4, '3', 1, 1, 2, 1, NULL, NULL, '2017-07-19 08:50:45', 450, NULL),
(4, '4', 2, 1, 2, 1, NULL, NULL, '2017-07-19 08:50:45', 150, NULL),
(5, '2', 2, 1, 3, 1, NULL, NULL, '2017-07-19 08:50:45', 320, NULL),
(5, '3', 3, 1, 3, 1, NULL, NULL, '2017-07-19 08:50:45', 350, NULL),
(6, '1', 1, 1, 3, 1, NULL, NULL, '2017-07-19 08:50:45', 300, NULL),
(7, '1', 3, 1, 4, 1, NULL, NULL, '2017-07-19 08:50:45', 250, NULL),
(7, '3', 3, 1, 4, 1, NULL, NULL, '2017-07-19 08:50:45', 200, NULL),
(8, '1', 1, 1, 4, 1, NULL, NULL, '2017-07-19 08:50:45', 150, NULL),
(9, '4', 3, 1, 5, 1, NULL, NULL, '2017-07-19 08:50:45', 100, NULL);

--
-- Trigger `Ordine`
--
DROP TRIGGER IF EXISTS `AggiornamentoCarrelloAggiuntaElemento`;
DELIMITER //
CREATE TRIGGER `AggiornamentoCarrelloAggiuntaElemento` AFTER INSERT ON `Ordine`
 FOR EACH ROW UPDATE Carrello c
	SET c.subTotal = c.subTotal + (new.prezzoDiAcquisto * new.quantita)
    WHERE c.idUtente = new.idUtente AND new.stato = 0
//
DELIMITER ;
DROP TRIGGER IF EXISTS `AggiornamentoCarrelloModificaPrezzoElemento`;
DELIMITER //
CREATE TRIGGER `AggiornamentoCarrelloModificaPrezzoElemento` AFTER UPDATE ON `Ordine`
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
END
//
DELIMITER ;
DROP TRIGGER IF EXISTS `AggiornamentoCarrelloRimozioneElemento`;
DELIMITER //
CREATE TRIGGER `AggiornamentoCarrelloRimozioneElemento` AFTER DELETE ON `Ordine`
 FOR EACH ROW UPDATE Carrello c
	SET c.subTotal = c.subTotal - (old.prezzoDiAcquisto * old.quantita)
    WHERE c.idUtente = old.idUtente AND old.stato = 0
//
DELIMITER ;
DROP TRIGGER IF EXISTS `CreoElementoCarrello`;
DELIMITER //
CREATE TRIGGER `CreoElementoCarrello` BEFORE INSERT ON `Ordine`
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
end
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Struttura della tabella `ordiniRicevuti`
--

DROP TABLE IF EXISTS `ordiniRicevuti`;
CREATE TABLE IF NOT EXISTS `ordiniRicevuti` (
  `idO` int(11) NOT NULL,
  `idV` int(11) NOT NULL,
  `data` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `RecensioneNegozio`
--

DROP TABLE IF EXISTS `RecensioneNegozio`;
CREATE TABLE IF NOT EXISTS `RecensioneNegozio` (
`id` int(11) NOT NULL,
  `idNegozio` int(11) NOT NULL,
  `idUtente` int(11) NOT NULL,
  `testo` varchar(2500) DEFAULT NULL,
  `valutazione` int(11) NOT NULL,
  `data` datetime NOT NULL,
  `utilita` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `RecensioneNegozio`
--

INSERT INTO `RecensioneNegozio` (`id`, `idNegozio`, `idUtente`, `testo`, `valutazione`, `data`, `utilita`) VALUES
(1, 1, 1, 'Bel negozio, personale qualificato', 5, '2017-07-14 00:00:00', NULL),
(2, 4, 2, 'Bel negozio, alla moda, consigliato!', 5, '2017-07-11 00:00:00', NULL),
(3, 3, 1, 'tutto molto bello', 4, '2017-07-19 00:00:00', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `RecensioneOggetto`
--

DROP TABLE IF EXISTS `RecensioneOggetto`;
CREATE TABLE IF NOT EXISTS `RecensioneOggetto` (
`id` int(11) NOT NULL,
  `idOggetto` varchar(32) NOT NULL,
  `idUtente` int(11) NOT NULL,
  `testo` varchar(2500) DEFAULT NULL,
  `valutazione` int(11) NOT NULL,
  `data` datetime NOT NULL,
  `utilita` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `RecensioneOggetto`
--

INSERT INTO `RecensioneOggetto` (`id`, `idOggetto`, `idUtente`, `testo`, `valutazione`, `data`, `utilita`) VALUES
(1, '1', 1, 'Bene ma non benissimo, si vede che è una cinesata', 2, '2017-07-14 00:00:00', 4),
(2, '2', 1, 'CAZZO FRA STA ROBA SPACCA, LA POLVERINA BIANCA MMMMM IL NASO FA FESTA', 5, '2017-07-25 08:21:56', 12),
(3, '1', 2, 'la droga.', 1, '2017-07-25 11:18:35', 3);

-- --------------------------------------------------------

--
-- Struttura della tabella `RecensioneVenditore`
--

DROP TABLE IF EXISTS `RecensioneVenditore`;
CREATE TABLE IF NOT EXISTS `RecensioneVenditore` (
`id` int(11) NOT NULL,
  `idVenditore` int(11) NOT NULL,
  `idUtente` int(11) NOT NULL,
  `testo` varchar(2500) DEFAULT NULL,
  `valutazione` int(11) NOT NULL,
  `data` datetime NOT NULL,
  `utilita` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `RecensioneVenditore`
--

INSERT INTO `RecensioneVenditore` (`id`, `idVenditore`, `idUtente`, `testo`, `valutazione`, `data`, `utilita`) VALUES
(1, 6, 1, 'tutto arrivato in tempo, imballo fatto con cazzi di gomma', 4, '2017-07-19 00:00:00', 4);

-- --------------------------------------------------------

--
-- Struttura della tabella `RispostaNegozio`
--

DROP TABLE IF EXISTS `RispostaNegozio`;
CREATE TABLE IF NOT EXISTS `RispostaNegozio` (
  `idRecensione` int(11) NOT NULL,
  `testo` varchar(2500) NOT NULL,
  `data` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `RispostaOggetto`
--

DROP TABLE IF EXISTS `RispostaOggetto`;
CREATE TABLE IF NOT EXISTS `RispostaOggetto` (
  `idRecensione` int(11) NOT NULL,
  `testo` varchar(2500) NOT NULL,
  `data` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `RispostaOggetto`
--

INSERT INTO `RispostaOggetto` (`idRecensione`, `testo`, `data`) VALUES
(1, 'Ci scusiamo, stiamo lavorando per assumere cinesi a basso costo che facciano cinesate che non sembrino cinesate, cordiali saluti, ToMare', '2017-07-30 00:00:00');

-- --------------------------------------------------------

--
-- Struttura della tabella `RispostaVenditore`
--

DROP TABLE IF EXISTS `RispostaVenditore`;
CREATE TABLE IF NOT EXISTS `RispostaVenditore` (
  `idRecensione` int(11) NOT NULL,
  `testo` varchar(2500) NOT NULL,
  `data` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `spedizioneOggetto`
--

DROP TABLE IF EXISTS `spedizioneOggetto`;
CREATE TABLE IF NOT EXISTS `spedizioneOggetto` (
  `idS` int(11) NOT NULL,
  `idO` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `tipoSpedizione`
--

DROP TABLE IF EXISTS `tipoSpedizione`;
CREATE TABLE IF NOT EXISTS `tipoSpedizione` (
`idS` int(11) NOT NULL,
  `idU` int(11) NOT NULL,
  `Nome` varchar(50) NOT NULL,
  `Prezzo` double NOT NULL,
  `Corriere` varchar(50) NOT NULL,
  `tempoRichiesto` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `tipoSpedizione`
--

INSERT INTO `tipoSpedizione` (`idS`, `idU`, `Nome`, `Prezzo`, `Corriere`, `tempoRichiesto`) VALUES
(1, 1, 'Spedizione standard 8$', 8, 'Non specificato', 3),
(2, 1, 'Spedizione standard pesante 13$', 13, 'Non specificato', 3),
(3, 1, 'Spedizione standard leggera 4$', 4, 'Non specificato', 3),
(4, 1, 'Spedizione lenta leggera 2$', 2, 'Non specificato', 10),
(5, 1, 'Spedizione lenta 5$', 5, 'Non specificato', 10),
(6, 1, 'Spedizione lenta pesante 10$', 10, 'Non specificato', 10),
(7, 1, 'Spedizione veloce leggera 7$', 7, 'Non specificato', 2),
(8, 1, 'Spedizione veloce 12$', 12, 'Non specificato', 2),
(9, 1, 'Spedizione veloce Pesante 18$', 18, 'Non specificato', 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `Utente`
--

DROP TABLE IF EXISTS `Utente`;
CREATE TABLE IF NOT EXISTS `Utente` (
`id` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `cognome` varchar(255) NOT NULL,
  `mail` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `valutazione` double DEFAULT NULL,
  `UtenteType` int(11) NOT NULL DEFAULT '0',
  `emailConfermata` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `Utente`
--

INSERT INTO `Utente` (`id`, `nome`, `cognome`, `mail`, `password`, `avatar`, `valutazione`, `UtenteType`, `emailConfermata`) VALUES
(1, 'Andrea', 'Fadi', 'abc@def.ghi', '6e6bc4e49dd477ebc98ef4046c067b5f', NULL, NULL, 2, b'1'),
(2, 'Federico', 'Brugiolo', 'def@def.ghi', '6e6bc4e49dd477ebc98ef4046c067b5f', NULL, NULL, 2, b'1'),
(3, 'Mattia', 'Milani', 'ghi@def.ghi', '6e6bc4e49dd477ebc98ef4046c067b5f', NULL, NULL, 2, b'1'),
(4, 'Damiano', 'Sartori', 'lmn@def.ghi', '6e6bc4e49dd477ebc98ef4046c067b5f', NULL, NULL, 2, b'1'),
(5, 'Ciuffo', 'Rosso', 'NewMail', '14a88b9d2f52c55b5fbcf9c5d9c11875', NULL, NULL, 1, b'1'),
(6, 'Carlo', 'Cracco', 'a@def.ghi', '6e6bc4e49dd477ebc98ef4046c067b5f', NULL, NULL, 1, b'1'),
(7, 'Coso', 'Canavacciulo', 'b@def.ghi', '6e6bc4e49dd477ebc98ef4046c067b5f', NULL, NULL, 1, b'1'),
(8, 'Bill', 'Gate', 'c@def.ghi', '6e6bc4e49dd477ebc98ef4046c067b5f', NULL, NULL, 1, b'1'),
(9, 'Open', 'Close', 'd@def.ghi', '6e6bc4e49dd477ebc98ef4046c067b5f', NULL, NULL, 1, b'1'),
(11, 'Mario', 'Rossi', 'marioRossi@gmail.com', '10776f3332b216b3f53d1f81c44aeeda', NULL, NULL, 1, b'1'),
(12, 'franco', 'baglioni', 'francobg@gmail.com', '10776f3332b216b3f53d1f81c44aeeda', NULL, NULL, 1, b'1'),
(13, 'piero', 'pelù', 'pelu@mail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 1, b'1'),
(14, 'Antonio', 'Tognazzi', 'togno@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 1, b'1'),
(15, 'Andrea', 'Andrelli', 'andrelli.andrea@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 1, b'1'),
(16, 'Giulio', 'Andreotti', 'andrelio@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 1, b'1'),
(17, 'Pietro', 'Pegoraro', 'pegoPietro@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 1, b'1'),
(18, 'Marco', 'Neri', 'neri.marco96@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 1, b'1'),
(19, 'Franco', 'Franchetti', 'lollo96happy@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 1, b'1'),
(20, 'Laura', 'Palmer', 'palme.laura23@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 1, b'1'),
(21, 'Federico', 'Spantelo', 'lollolollosorilollo02@maildivertente.it', 'e2a1715ac00b5e872a2191fb13f69a69', NULL, NULL, 1, b'1'),
(22, 'Mario', 'Grigi', 'grigio.mario@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 1, b'1'),
(23, 'Paolo', 'Bitta', 'uomochiamatocontratto@caffe.it', '886e011286278f1f10171adcc0e4e8aa', NULL, NULL, 1, b'1'),
(24, 'Luca', 'Nervi', 'sindacatoforpresident@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 1, b'1'),
(25, 'luigina', 'Brentola', 'brentolamail@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 1, b'1'),
(26, 'Marzia', 'Longhi', 'marzia.longhi@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 0, b'1'),
(27, 'Andrea', 'Rossi', 'andrea.rossi@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 0, b'1'),
(28, 'Marco', 'Furlan', 'furla.marco@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 0, b'1'),
(29, 'Lucia', 'Canticchi', 'cant.lucia@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 0, b'1'),
(30, 'Federico', 'Costenaro', 'coste.fede16@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 0, b'1'),
(31, 'Poldo', 'posturo', 'post.poldo@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 0, b'1'),
(32, 'Margherita', 'Torrevalle', 'marghelatorre@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 0, b'1'),
(33, 'Fiorella', 'Verduzzi', 'verd.fiore97@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 0, b'1'),
(34, 'Pietro', 'Di Stefano', 'distepietruzzo@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 0, b'1'),
(35, 'Pablo', 'Escobar', 'esco.nomaria@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 0, b'1'),
(36, 'Obama', 'baracche', 'barack.obama87@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 0, b'1'),
(37, 'Leonardo', 'diCapricorno', 'cpario.leo1@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 0, b'1'),
(38, 'Fiero', 'Becco', 'becco.fiero@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 0, b'1'),
(39, 'Gandalf', 'rossi', 'gandalf.ilrosso@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 0, b'1'),
(40, 'Filomena', 'menuzzi', 'filomenamenofila@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 0, b'1'),
(41, 'Willy', 'Belair', 'ilprincipe@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 0, b'1'),
(42, 'Thom', 'Hanks', 'tommy57@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 0, b'1'),
(43, 'Guglielmo', 'Marconi', 'marco.guglie@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 0, b'1'),
(44, 'Henrry', 'Wasley', 'potteriani@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 0, b'1'),
(45, 'Francesca', 'Lecresta', 'lecre.sca99@gmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL, NULL, 0, b'1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Assistenza`
--
ALTER TABLE `Assistenza`
 ADD PRIMARY KEY (`id`), ADD KEY `idUtente` (`idUtente`), ADD KEY `idVenditore` (`idVenditore`), ADD KEY `idAmministratore` (`idAmministratore`), ADD KEY `idOrdine` (`idOrdine`), ADD KEY `idOggetto` (`idOggetto`);

--
-- Indexes for table `Carrello`
--
ALTER TABLE `Carrello`
 ADD PRIMARY KEY (`idUtente`), ADD KEY `idOrdine` (`idOrdine`);

--
-- Indexes for table `Categoria`
--
ALTER TABLE `Categoria`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `imageNegozio`
--
ALTER TABLE `imageNegozio`
 ADD PRIMARY KEY (`src`,`idN`), ADD KEY `idN` (`idN`);

--
-- Indexes for table `imageOggetto`
--
ALTER TABLE `imageOggetto`
 ADD PRIMARY KEY (`src`,`idO`), ADD KEY `idO` (`idO`);

--
-- Indexes for table `imageRecensione`
--
ALTER TABLE `imageRecensione`
 ADD PRIMARY KEY (`src`,`idR`), ADD KEY `idR` (`idR`);

--
-- Indexes for table `imageUtente`
--
ALTER TABLE `imageUtente`
 ADD PRIMARY KEY (`src`,`idU`), ADD KEY `idI` (`src`), ADD KEY `idU` (`idU`);

--
-- Indexes for table `Indirizzo`
--
ALTER TABLE `Indirizzo`
 ADD PRIMARY KEY (`idI`);

--
-- Indexes for table `IndirizzoUtente`
--
ALTER TABLE `IndirizzoUtente`
 ADD PRIMARY KEY (`idI`,`idU`), ADD KEY `idU` (`idU`);

--
-- Indexes for table `Negozio`
--
ALTER TABLE `Negozio`
 ADD PRIMARY KEY (`id`,`idI`), ADD KEY `idI` (`idI`), ADD KEY `idNegozioIndex` (`idVenditore`) USING HASH;

--
-- Indexes for table `Oggetto`
--
ALTER TABLE `Oggetto`
 ADD PRIMARY KEY (`id`), ADD KEY `idNegozio` (`idNegozio`), ADD KEY `idOggettoIndex` (`categoria`) USING HASH;

--
-- Indexes for table `Ordine`
--
ALTER TABLE `Ordine`
 ADD PRIMARY KEY (`idOrdine`,`idOggetto`,`idUtente`), ADD UNIQUE KEY `idS` (`idS`), ADD KEY `idOggetto` (`idOggetto`), ADD KEY `idNegozio` (`idNegozio`), ADD KEY `idUtente` (`idUtente`);

--
-- Indexes for table `ordiniRicevuti`
--
ALTER TABLE `ordiniRicevuti`
 ADD PRIMARY KEY (`idO`,`idV`), ADD KEY `idV` (`idV`);

--
-- Indexes for table `RecensioneNegozio`
--
ALTER TABLE `RecensioneNegozio`
 ADD PRIMARY KEY (`id`), ADD KEY `idUtente` (`idUtente`), ADD KEY `idRecNegIndex` (`idNegozio`) USING HASH;

--
-- Indexes for table `RecensioneOggetto`
--
ALTER TABLE `RecensioneOggetto`
 ADD PRIMARY KEY (`id`), ADD KEY `idUtente` (`idUtente`), ADD KEY `idRecOggIndex` (`idOggetto`) USING HASH;

--
-- Indexes for table `RecensioneVenditore`
--
ALTER TABLE `RecensioneVenditore`
 ADD PRIMARY KEY (`id`), ADD KEY `idUtente` (`idUtente`), ADD KEY `idRecVenIndex` (`idVenditore`) USING HASH;

--
-- Indexes for table `RispostaNegozio`
--
ALTER TABLE `RispostaNegozio`
 ADD PRIMARY KEY (`idRecensione`);

--
-- Indexes for table `RispostaOggetto`
--
ALTER TABLE `RispostaOggetto`
 ADD PRIMARY KEY (`idRecensione`);

--
-- Indexes for table `RispostaVenditore`
--
ALTER TABLE `RispostaVenditore`
 ADD PRIMARY KEY (`idRecensione`);

--
-- Indexes for table `spedizioneOggetto`
--
ALTER TABLE `spedizioneOggetto`
 ADD PRIMARY KEY (`idS`,`idO`), ADD KEY `nomeSensato` (`idO`);

--
-- Indexes for table `tipoSpedizione`
--
ALTER TABLE `tipoSpedizione`
 ADD PRIMARY KEY (`idS`,`idU`), ADD KEY `utenteSpedizione` (`idU`);

--
-- Indexes for table `Utente`
--
ALTER TABLE `Utente`
 ADD PRIMARY KEY (`id`), ADD KEY `idUtenteIndex` (`id`,`UtenteType`) USING HASH;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Assistenza`
--
ALTER TABLE `Assistenza`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `Categoria`
--
ALTER TABLE `Categoria`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=30;
--
-- AUTO_INCREMENT for table `Indirizzo`
--
ALTER TABLE `Indirizzo`
MODIFY `idI` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=42;
--
-- AUTO_INCREMENT for table `Negozio`
--
ALTER TABLE `Negozio`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `Ordine`
--
ALTER TABLE `Ordine`
MODIFY `idOrdine` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `RecensioneNegozio`
--
ALTER TABLE `RecensioneNegozio`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `RecensioneOggetto`
--
ALTER TABLE `RecensioneOggetto`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `RecensioneVenditore`
--
ALTER TABLE `RecensioneVenditore`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `tipoSpedizione`
--
ALTER TABLE `tipoSpedizione`
MODIFY `idS` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `Utente`
--
ALTER TABLE `Utente`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=46;
--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `Assistenza`
--
ALTER TABLE `Assistenza`
ADD CONSTRAINT `Assistenza_ibfk_1` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`id`) ON DELETE NO ACTION,
ADD CONSTRAINT `Assistenza_ibfk_2` FOREIGN KEY (`idVenditore`) REFERENCES `Utente` (`id`) ON DELETE NO ACTION,
ADD CONSTRAINT `Assistenza_ibfk_3` FOREIGN KEY (`idAmministratore`) REFERENCES `Utente` (`id`) ON DELETE NO ACTION,
ADD CONSTRAINT `Assistenza_ibfk_4` FOREIGN KEY (`idOrdine`) REFERENCES `Ordine` (`idOrdine`) ON DELETE SET NULL,
ADD CONSTRAINT `Assistenza_ibfk_5` FOREIGN KEY (`idOggetto`) REFERENCES `Oggetto` (`id`) ON DELETE NO ACTION;

--
-- Limiti per la tabella `Carrello`
--
ALTER TABLE `Carrello`
ADD CONSTRAINT `Carrello_ibfk_1` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `imageNegozio`
--
ALTER TABLE `imageNegozio`
ADD CONSTRAINT `imageNegozio_ibfk_2` FOREIGN KEY (`idN`) REFERENCES `Negozio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `imageOggetto`
--
ALTER TABLE `imageOggetto`
ADD CONSTRAINT `imageOggetto_ibfk_2` FOREIGN KEY (`idO`) REFERENCES `Oggetto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `imageRecensione`
--
ALTER TABLE `imageRecensione`
ADD CONSTRAINT `imageRecensione_ibfk_2` FOREIGN KEY (`idR`) REFERENCES `RecensioneOggetto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `imageUtente`
--
ALTER TABLE `imageUtente`
ADD CONSTRAINT `imageUtente_ibfk_2` FOREIGN KEY (`idU`) REFERENCES `Utente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `IndirizzoUtente`
--
ALTER TABLE `IndirizzoUtente`
ADD CONSTRAINT `IndirizzoUtente_ibfk_1` FOREIGN KEY (`idU`) REFERENCES `Utente` (`id`) ON DELETE CASCADE,
ADD CONSTRAINT `IndirizzoUtente_ibfk_2` FOREIGN KEY (`idI`) REFERENCES `Indirizzo` (`idI`) ON DELETE CASCADE;

--
-- Limiti per la tabella `Negozio`
--
ALTER TABLE `Negozio`
ADD CONSTRAINT `Negozio_ibfk_1` FOREIGN KEY (`idVenditore`) REFERENCES `Utente` (`id`) ON DELETE NO ACTION,
ADD CONSTRAINT `Negozio_ibfk_2` FOREIGN KEY (`idI`) REFERENCES `Indirizzo` (`idI`) ON DELETE NO ACTION;

--
-- Limiti per la tabella `Oggetto`
--
ALTER TABLE `Oggetto`
ADD CONSTRAINT `Oggetto_ibfk_1` FOREIGN KEY (`idNegozio`) REFERENCES `Negozio` (`id`) ON DELETE NO ACTION,
ADD CONSTRAINT `Oggetto_ibfk_2` FOREIGN KEY (`categoria`) REFERENCES `Categoria` (`id`) ON DELETE NO ACTION;

--
-- Limiti per la tabella `Ordine`
--
ALTER TABLE `Ordine`
ADD CONSTRAINT `nomeSensato3` FOREIGN KEY (`idS`) REFERENCES `tipoSpedizione` (`idS`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `Ordine_ibfk_1` FOREIGN KEY (`idOggetto`) REFERENCES `Oggetto` (`id`) ON DELETE NO ACTION,
ADD CONSTRAINT `Ordine_ibfk_2` FOREIGN KEY (`idNegozio`) REFERENCES `Negozio` (`id`) ON DELETE NO ACTION,
ADD CONSTRAINT `Ordine_ibfk_3` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`id`) ON DELETE CASCADE;

--
-- Limiti per la tabella `ordiniRicevuti`
--
ALTER TABLE `ordiniRicevuti`
ADD CONSTRAINT `ordiniRicevuti_ibfk_1` FOREIGN KEY (`idO`) REFERENCES `Ordine` (`idOrdine`),
ADD CONSTRAINT `ordiniRicevuti_ibfk_2` FOREIGN KEY (`idV`) REFERENCES `Utente` (`id`);

--
-- Limiti per la tabella `RecensioneNegozio`
--
ALTER TABLE `RecensioneNegozio`
ADD CONSTRAINT `RecensioneNegozio_ibfk_1` FOREIGN KEY (`idNegozio`) REFERENCES `Negozio` (`id`) ON DELETE CASCADE,
ADD CONSTRAINT `RecensioneNegozio_ibfk_2` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`id`) ON DELETE NO ACTION;

--
-- Limiti per la tabella `RecensioneOggetto`
--
ALTER TABLE `RecensioneOggetto`
ADD CONSTRAINT `RecensioneOggetto_ibfk_1` FOREIGN KEY (`idOggetto`) REFERENCES `Oggetto` (`id`) ON DELETE CASCADE,
ADD CONSTRAINT `RecensioneOggetto_ibfk_2` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`id`) ON DELETE NO ACTION;

--
-- Limiti per la tabella `RecensioneVenditore`
--
ALTER TABLE `RecensioneVenditore`
ADD CONSTRAINT `RecensioneVenditore_ibfk_1` FOREIGN KEY (`idVenditore`) REFERENCES `Utente` (`id`) ON DELETE CASCADE,
ADD CONSTRAINT `RecensioneVenditore_ibfk_2` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`id`) ON DELETE NO ACTION;

--
-- Limiti per la tabella `RispostaNegozio`
--
ALTER TABLE `RispostaNegozio`
ADD CONSTRAINT `RispostaNegozio_ibfk_1` FOREIGN KEY (`idRecensione`) REFERENCES `RecensioneNegozio` (`id`) ON DELETE CASCADE;

--
-- Limiti per la tabella `RispostaOggetto`
--
ALTER TABLE `RispostaOggetto`
ADD CONSTRAINT `RispostaOggetto_ibfk_1` FOREIGN KEY (`idRecensione`) REFERENCES `RecensioneOggetto` (`id`) ON DELETE CASCADE;

--
-- Limiti per la tabella `RispostaVenditore`
--
ALTER TABLE `RispostaVenditore`
ADD CONSTRAINT `RispostaVenditore_ibfk_1` FOREIGN KEY (`idRecensione`) REFERENCES `RecensioneVenditore` (`id`) ON DELETE CASCADE;

--
-- Limiti per la tabella `spedizioneOggetto`
--
ALTER TABLE `spedizioneOggetto`
ADD CONSTRAINT `nomeSensato` FOREIGN KEY (`idO`) REFERENCES `Oggetto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `nomeSensatoAgain` FOREIGN KEY (`idS`) REFERENCES `tipoSpedizione` (`idS`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `tipoSpedizione`
--
ALTER TABLE `tipoSpedizione`
ADD CONSTRAINT `utenteSpedizione` FOREIGN KEY (`idU`) REFERENCES `Utente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

DELIMITER $$
--
-- Eventi
--
DROP EVENT IF EXISTS `ControlloScontiAttivi`$$
CREATE DEFINER=`progettoweb`@`%` EVENT `ControlloScontiAttivi` ON SCHEDULE EVERY 1 DAY STARTS '2017-07-22 00:01:00' ENDS '2018-07-22 00:01:00' ON COMPLETION PRESERVE ENABLE COMMENT 'Evento utilizzato per eliminare gli sconti terminati' DO UPDATE Oggetto SET Oggetto.`sconto` = 0, Oggetto.`dataFineSconto` = IF(Oggetto.`dataFineSconto`<CURDATE(), NULL,Oggetto.`dataFineSconto`)$$

DELIMITER ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
