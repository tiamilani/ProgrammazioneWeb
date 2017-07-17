-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Lug 16, 2017 alle 13:42
-- Versione del server: 5.5.55-0+deb8u1
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
  `soluzione` varchar(2500) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Svuota la tabella prima dell'inserimento `Assistenza`
--

TRUNCATE TABLE `Assistenza`;
--
-- Dump dei dati per la tabella `Assistenza`
--

INSERT INTO `Assistenza` (`id`, `idUtente`, `idVenditore`, `idAmministratore`, `idOrdine`, `idOggetto`, `stato`, `soluzione`) VALUES
(1, 1, NULL, 10, 1, NULL, 0, NULL);

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
-- Svuota la tabella prima dell'inserimento `Carrello`
--

TRUNCATE TABLE `Carrello`;
--
-- Dump dei dati per la tabella `Carrello`
--

INSERT INTO `Carrello` (`idUtente`, `idOrdine`, `subTotal`) VALUES
(1, 1, 0),
(2, 3, 250);

-- --------------------------------------------------------

--
-- Struttura della tabella `Categoria`
--

DROP TABLE IF EXISTS `Categoria`;
CREATE TABLE IF NOT EXISTS `Categoria` (
`id` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `descrizione` varchar(500) NOT NULL,
  `oggettiPresenti` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Svuota la tabella prima dell'inserimento `Categoria`
--

TRUNCATE TABLE `Categoria`;
--
-- Dump dei dati per la tabella `Categoria`
--

INSERT INTO `Categoria` (`id`, `nome`, `descrizione`, `oggettiPresenti`) VALUES
(1, 'Elettronica', 'Materiale Elettronico', 3),
(2, 'Casa', 'Materiale per Casa e Cucina', 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `image`
--

DROP TABLE IF EXISTS `image`;
CREATE TABLE IF NOT EXISTS `image` (
`idI` int(11) NOT NULL,
  `src` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Svuota la tabella prima dell'inserimento `image`
--

TRUNCATE TABLE `image`;
--
-- Dump dei dati per la tabella `image`
--

INSERT INTO `image` (`idI`, `src`) VALUES
(1, 'img1'),
(2, 'img2'),
(3, 'img3'),
(4, 'img4'),
(5, 'img5'),
(6, 'img6'),
(7, 'img7'),
(8, 'img8'),
(9, 'img9'),
(10, 'img10');

-- --------------------------------------------------------

--
-- Struttura della tabella `imageNegozio`
--

DROP TABLE IF EXISTS `imageNegozio`;
CREATE TABLE IF NOT EXISTS `imageNegozio` (
  `idI` int(11) NOT NULL DEFAULT '0',
  `idN` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Svuota la tabella prima dell'inserimento `imageNegozio`
--

TRUNCATE TABLE `imageNegozio`;
--
-- Dump dei dati per la tabella `imageNegozio`
--

INSERT INTO `imageNegozio` (`idI`, `idN`) VALUES
(1, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `imageOggetto`
--

DROP TABLE IF EXISTS `imageOggetto`;
CREATE TABLE IF NOT EXISTS `imageOggetto` (
  `idI` int(11) NOT NULL,
  `idO` varchar(32) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Svuota la tabella prima dell'inserimento `imageOggetto`
--

TRUNCATE TABLE `imageOggetto`;
--
-- Dump dei dati per la tabella `imageOggetto`
--

INSERT INTO `imageOggetto` (`idI`, `idO`) VALUES
(3, 1),
(4, 2);

-- --------------------------------------------------------

--
-- Struttura della tabella `imageRecensione`
--

DROP TABLE IF EXISTS `imageRecensione`;
CREATE TABLE IF NOT EXISTS `imageRecensione` (
  `idI` int(11) NOT NULL DEFAULT '0',
  `idR` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Svuota la tabella prima dell'inserimento `imageRecensione`
--

TRUNCATE TABLE `imageRecensione`;
--
-- Dump dei dati per la tabella `imageRecensione`
--

INSERT INTO `imageRecensione` (`idI`, `idR`) VALUES
(5, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `imageUtente`
--

DROP TABLE IF EXISTS `imageUtente`;
CREATE TABLE IF NOT EXISTS `imageUtente` (
  `idI` int(11) DEFAULT NULL,
  `idU` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Svuota la tabella prima dell'inserimento `imageUtente`
--

TRUNCATE TABLE `imageUtente`;
--
-- Dump dei dati per la tabella `imageUtente`
--

INSERT INTO `imageUtente` (`idI`, `idU`) VALUES
(6, 1),
(7, 2),
(8, 3),
(9, 10),
(10, 7);

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

--
-- Svuota la tabella prima dell'inserimento `Indirizzo`
--

TRUNCATE TABLE `Indirizzo`;
--
-- Dump dei dati per la tabella `Indirizzo`
--

INSERT INTO `Indirizzo` (`idI`, `stato`, `regione`, `provincia`, `citta`, `via`, `nCivico`, `interno`, `latitudine`, `longitudine`) VALUES
(1, 'Italia', 'Veneto', 'Treviso', 'Paese', 'Via Paese', 4, NULL, NULL, NULL),
(2, 'Italia', 'Veneto', 'Vicenza', 'Bassano del Grappa', 'Via roma', 4, NULL, NULL, NULL),
(3, 'Italia', 'Veneto', 'Padova', 'Cittadella', 'Via einaudi', 4, NULL, NULL, NULL),
(4, 'Italia', 'Lombardia', 'Milano', 'Milano', 'Via vittorio emanuele', 4, NULL, NULL, NULL),
(5, 'Italia', 'Lombardia', 'Brescia', 'Brescia', 'Via cristo', 4, NULL, NULL, NULL),
(6, 'Italia', 'Lazio', 'Roma', 'Roma', 'Via garibaldi', 4, NULL, NULL, NULL),
(7, 'Italia', 'Piemonte', 'Torino', 'Torino', 'Via Matteo', 4, NULL, NULL, NULL),
(8, 'Italia', 'Trentino Alto Adige', 'Trento', 'Trento', 'Via Roma', 4, NULL, NULL, NULL),
(9, 'Italia', 'Trentino Alto Adige', 'Trento', 'Povo', 'Via Sommarive', 4, NULL, NULL, NULL),
(10, 'Italia', 'Lombardia', 'Bergamo', 'Bergamo', 'Via Rose', 4, NULL, NULL, NULL),
(11, 'Italia', 'Veneto', 'Treviso', 'Paese', 'Via Paese', 5, NULL, NULL, NULL),
(12, 'Italia', 'Veneto', 'Vicenza', 'Bassano del Grappa', 'Via roma', 5, NULL, NULL, NULL),
(13, 'Italia', 'Veneto', 'Padova', 'Cittadella', 'Via einaudi', 5, NULL, NULL, NULL),
(14, 'Italia', 'Lombardia', 'Milano', 'Milano', 'Via vittorio emanuele', 5, NULL, NULL, NULL),
(15, 'Italia', 'Lombardia', 'Brescia', 'Brescia', 'Via cristo', 5, NULL, NULL, NULL),
(16, 'Italia', 'Lazio', 'Roma', 'Roma', 'Via garibaldi', 5, NULL, NULL, NULL),
(17, 'Italia', 'Piemonte', 'Torino', 'Torino', 'Via Matteo', 5, NULL, NULL, NULL),
(18, 'Italia', 'Trentino Alto Adige', 'Trento', 'Trento', 'Via Roma', 5, NULL, NULL, NULL),
(19, 'Italia', 'Trentino Alto Adige', 'Trento', 'Povo', 'Via Sommarive', 5, NULL, NULL, NULL),
(20, 'Italia', 'Lombardia', 'Bergamo', 'Bergamo', 'Via Rose', 5, NULL, NULL, NULL);

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
-- Svuota la tabella prima dell'inserimento `IndirizzoUtente`
--

TRUNCATE TABLE `IndirizzoUtente`;
--
-- Dump dei dati per la tabella `IndirizzoUtente`
--

INSERT INTO `IndirizzoUtente` (`idI`, `idU`) VALUES
(1, 1),
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
  `foto` varchar(500) DEFAULT NULL,
  `valutazione` double DEFAULT NULL,
  `attivo` tinyint(1) NOT NULL DEFAULT '1',
  `idI` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Svuota la tabella prima dell'inserimento `Negozio`
--

TRUNCATE TABLE `Negozio`;
--
-- Dump dei dati per la tabella `Negozio`
--

INSERT INTO `Negozio` (`id`, `idVenditore`, `nomeNegozio`, `foto`, `valutazione`, `attivo`, `idI`) VALUES
(1, 6, 'Mediaworld', NULL, NULL, 1, 11),
(2, 7, 'Trony', NULL, NULL, 1, 11),
(3, 8, 'Unieuro', NULL, NULL, 1, 12),
(4, 9, 'Kasanova', NULL, NULL, 1, 13);

-- --------------------------------------------------------

--
-- Struttura della tabella `Oggetto`
--

DROP TABLE IF EXISTS `Oggetto`;
CREATE TABLE IF NOT EXISTS `Oggetto` (
  `id` varchar(32) NOT NULL,
  `idNegozio` int(11) NOT NULL,
  `nome` varchar(500) NOT NULL,
  `prezzo` double NOT NULL,
  `descrizione` varchar(2500) NOT NULL,
  `ritiroInNegozio` tinyint(1) DEFAULT '0',
  `disponibilita` int(11) NOT NULL,
  `sconto` double DEFAULT '0',
  `variazione` double DEFAULT '100',
  `dataFineSconto` datetime DEFAULT NULL,
  `categoria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Svuota la tabella prima dell'inserimento `Oggetto`
--

TRUNCATE TABLE `Oggetto`;
--
-- Dump dei dati per la tabella `Oggetto`
--

INSERT INTO `Oggetto` (`id`, `idNegozio`, `nome`, `prezzo`, `descrizione`, `ritiroInNegozio`, `disponibilita`, `sconto`, `variazione`, `dataFineSconto`, `categoria`) VALUES
(1, 1, 'iPhone SE', 500, 'iPhone SE 16GB Bianco', 0, 5, 0, 100, NULL, 1),
(2, 2, 'HTC U11', 450, 'HTC U11 32GB Bianco', 0, 6, 0, 100, NULL, 1),
(3, 3, 'UAUEI XYZ', 100, 'UAUEI XYZ Telefono inutile', 0, 1, 0, 100, NULL, 1),
(4, 4, 'Pentola Acciaio Inossidabile', 50, 'Pentola Acciaio Inossidabile, insdistrutibile!', 1, 45, 10, 100, NULL, 2);

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
  `quantita` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Svuota la tabella prima dell'inserimento `Ordine`
--

TRUNCATE TABLE `Ordine`;
--
-- Dump dei dati per la tabella `Ordine`
--

INSERT INTO `Ordine` (`idOrdine`, `idOggetto`, `idNegozio`, `idUtente`, `stato`, `quantita`) VALUES
(1, 1, 1, 1, 0, 1),
(2, 2, 2, 1, 0, 1),
(3, 3, 3, 2, 0, 1);

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Svuota la tabella prima dell'inserimento `RecensioneNegozio`
--

TRUNCATE TABLE `RecensioneNegozio`;
--
-- Dump dei dati per la tabella `RecensioneNegozio`
--

INSERT INTO `RecensioneNegozio` (`id`, `idNegozio`, `idUtente`, `testo`, `valutazione`, `data`, `utilita`) VALUES
(1, 1, 1, 'Bel negozio, personale qualificato', 5, '2017-07-14 00:00:00', NULL),
(2, 4, 2, 'Bel negozio, alla moda, consigliato!', 5, '2017-07-11 00:00:00', NULL);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Svuota la tabella prima dell'inserimento `RecensioneOggetto`
--

TRUNCATE TABLE `RecensioneOggetto`;
--
-- Dump dei dati per la tabella `RecensioneOggetto`
--

INSERT INTO `RecensioneOggetto` (`id`, `idOggetto`, `idUtente`, `testo`, `valutazione`, `data`, `utilita`) VALUES
(1, 1, 1, 'Bene ma non benissimo, si vede che è una cinesata', 2, '2017-07-14 00:00:00', NULL);

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Svuota la tabella prima dell'inserimento `RecensioneVenditore`
--

TRUNCATE TABLE `RecensioneVenditore`;
--
-- Dump dei dati per la tabella `RecensioneVenditore`
--

INSERT INTO `RecensioneVenditore` (`id`, `idVenditore`, `idUtente`, `testo`, `valutazione`, `data`, `utilita`) VALUES
(1, 6, 1, 'Spedizione troppo lenta', 1, '2017-07-14 00:00:00', NULL);

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

--
-- Svuota la tabella prima dell'inserimento `RispostaNegozio`
--

TRUNCATE TABLE `RispostaNegozio`;
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
-- Svuota la tabella prima dell'inserimento `RispostaOggetto`
--

TRUNCATE TABLE `RispostaOggetto`;
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

--
-- Svuota la tabella prima dell'inserimento `RispostaVenditore`
--

TRUNCATE TABLE `RispostaVenditore`;
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
  `UtenteType` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Svuota la tabella prima dell'inserimento `Utente`
--

TRUNCATE TABLE `Utente`;
--
-- Dump dei dati per la tabella `Utente`
--

INSERT INTO `Utente` (`id`, `nome`, `cognome`, `mail`, `password`, `avatar`, `valutazione`, `UtenteType`) VALUES
(1, 'Andrea', 'Fadi', 'abc@def.ghi', 'ciao', NULL, NULL, 0),
(2, 'Federico', 'Brugiolo', 'def@def.ghi', 'ciao', NULL, NULL, 0),
(3, 'Mattia', 'Milani', 'ghi@def.ghi', 'ciao', NULL, NULL, 0),
(4, 'Damiano', 'Sartori', 'lmn@def.ghi', 'ciao', NULL, NULL, 0),
(5, 'Ciuffo', 'Rosso', 'opq@def.ghi', 'ciao', NULL, NULL, 0),
(6, 'Carlo', 'Cracco', 'a@def.ghi', 'ciao', NULL, NULL, 1),
(7, 'Coso', 'Canavacciulo', 'b@def.ghi', 'ciao', NULL, NULL, 1),
(8, 'Bill', 'Gate', 'c@def.ghi', 'ciao', NULL, NULL, 1),
(9, 'Open', 'Close', 'd@def.ghi', 'ciao', NULL, NULL, 1),
(10, 'Admin', 'Supremo', 'root@root.com', 'ciao', NULL, NULL, 2);

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
-- Indexes for table `image`
--
ALTER TABLE `image`
 ADD PRIMARY KEY (`idI`);

--
-- Indexes for table `imageNegozio`
--
ALTER TABLE `imageNegozio`
 ADD PRIMARY KEY (`idI`,`idN`), ADD KEY `idN` (`idN`);

--
-- Indexes for table `imageOggetto`
--
ALTER TABLE `imageOggetto`
 ADD PRIMARY KEY (`idI`,`idO`), ADD KEY `idO` (`idO`);

--
-- Indexes for table `imageRecensione`
--
ALTER TABLE `imageRecensione`
 ADD PRIMARY KEY (`idI`,`idR`), ADD KEY `idR` (`idR`);

--
-- Indexes for table `imageUtente`
--
ALTER TABLE `imageUtente`
 ADD KEY `idI` (`idI`), ADD KEY `idU` (`idU`);

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
 ADD PRIMARY KEY (`id`), ADD KEY `idI` (`idI`), ADD KEY `idNegozioIndex` (`idVenditore`) USING HASH;

--
-- Indexes for table `Oggetto`
--
ALTER TABLE `Oggetto`
 ADD PRIMARY KEY (`id`), ADD KEY `idNegozio` (`idNegozio`), ADD KEY `idOggettoIndex` (`categoria`) USING HASH;

--
-- Indexes for table `Ordine`
--
ALTER TABLE `Ordine`
 ADD PRIMARY KEY (`idOrdine`,`idOggetto`,`idUtente`), ADD KEY `idOggetto` (`idOggetto`), ADD KEY `idNegozio` (`idNegozio`), ADD KEY `idUtente` (`idUtente`);

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
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `Categoria`
--
ALTER TABLE `Categoria`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `image`
--
ALTER TABLE `image`
MODIFY `idI` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `Indirizzo`
--
ALTER TABLE `Indirizzo`
MODIFY `idI` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `Negozio`
--
ALTER TABLE `Negozio`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `Ordine`
--
ALTER TABLE `Ordine`
MODIFY `idOrdine` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `RecensioneOggetto`
--
ALTER TABLE `RecensioneOggetto`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `Utente`
--
ALTER TABLE `Utente`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=12;
--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `Assistenza`
--
ALTER TABLE `Assistenza`
ADD CONSTRAINT `Assistenza_ibfk_5` FOREIGN KEY (`idOggetto`) REFERENCES `Oggetto` (`id`) ON DELETE NO ACTION,
ADD CONSTRAINT `Assistenza_ibfk_1` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`id`) ON DELETE NO ACTION,
ADD CONSTRAINT `Assistenza_ibfk_2` FOREIGN KEY (`idVenditore`) REFERENCES `Utente` (`id`) ON DELETE NO ACTION,
ADD CONSTRAINT `Assistenza_ibfk_3` FOREIGN KEY (`idAmministratore`) REFERENCES `Utente` (`id`) ON DELETE NO ACTION,
ADD CONSTRAINT `Assistenza_ibfk_4` FOREIGN KEY (`idOrdine`) REFERENCES `Ordine` (`idOrdine`) ON DELETE NO ACTION;

--
-- Limiti per la tabella `Carrello`
--
ALTER TABLE `Carrello`
ADD CONSTRAINT `Carrello_ibfk_1` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `Carrello_ibfk_2` FOREIGN KEY (`idOrdine`) REFERENCES `Ordine` (`idOrdine`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `imageNegozio`
--
ALTER TABLE `imageNegozio`
ADD CONSTRAINT `imageNegozio_ibfk_1` FOREIGN KEY (`idI`) REFERENCES `image` (`idI`),
ADD CONSTRAINT `imageNegozio_ibfk_2` FOREIGN KEY (`idN`) REFERENCES `Negozio` (`id`);

--
-- Limiti per la tabella `imageOggetto`
--
ALTER TABLE `imageOggetto`
ADD CONSTRAINT `imageOggetto_ibfk_1` FOREIGN KEY (`idI`) REFERENCES `image` (`idI`),
ADD CONSTRAINT `imageOggetto_ibfk_2` FOREIGN KEY (`idO`) REFERENCES `Oggetto` (`id`);

--
-- Limiti per la tabella `imageRecensione`
--
ALTER TABLE `imageRecensione`
ADD CONSTRAINT `imageRecensione_ibfk_1` FOREIGN KEY (`idI`) REFERENCES `image` (`idI`),
ADD CONSTRAINT `imageRecensione_ibfk_2` FOREIGN KEY (`idR`) REFERENCES `RecensioneOggetto` (`id`);

--
-- Limiti per la tabella `imageUtente`
--
ALTER TABLE `imageUtente`
ADD CONSTRAINT `imageUtente_ibfk_1` FOREIGN KEY (`idI`) REFERENCES `image` (`idI`),
ADD CONSTRAINT `imageUtente_ibfk_2` FOREIGN KEY (`idU`) REFERENCES `Utente` (`id`);

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
ADD CONSTRAINT `Ordine_ibfk_1` FOREIGN KEY (`idOggetto`) REFERENCES `Oggetto` (`id`) ON DELETE NO ACTION,
ADD CONSTRAINT `Ordine_ibfk_2` FOREIGN KEY (`idNegozio`) REFERENCES `Negozio` (`id`) ON DELETE NO ACTION,
ADD CONSTRAINT `Ordine_ibfk_3` FOREIGN KEY (`idUtente`) REFERENCES `Utente` (`id`) ON DELETE CASCADE;

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

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
