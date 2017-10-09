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

source assistenza.sql;

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

source carrello.sql;

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

source categoria.sql;

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
('http://localhost:8080/progettoWeb/jspFile/Finale/Img/square.png', 1),
('http://localhost:8080/progettoWeb/jspFile/Finale/Img/square.png', 2),
('http://localhost:8080/progettoWeb/jspFile/Finale/Img/square.png', 3);

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
('http://localhost:8080/progettoWeb/jspFile/Finale/Img/square.png', 1);

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
('http://localhost:8080/progettoWeb/jspFile/Finale/Img/square.png', 3),
('http://localhost:8080/progettoWeb/jspFile/Finale/Img/square.png', 4);

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

source indirizzo.sql;

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

source indirizzoUtente.sql;

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

source negozio.sql;

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

source oggetto.sql;

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

source ordine.sql;

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

--
-- Dump dei dati per la tabella `ordiniRicvuti`
--

INSERT INTO `ordiniRIcevuti` (`idO`, `idV`, `data`) VALUES
(1, 2, '2017-10-01 19:13:20');

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
(1, 1, 4, 'Bel negozio, personale qualificato', 5, '2017-09-14 00:00:00', NULL),
(2, 2, 3, 'Bel negozio, alla moda, consigliato!', 5, '2017-09-11 00:00:00', NULL),
(3, 3, 4, 'tutto molto bello', 4, '2017-08-19 00:00:00', 0);

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

source recensioneOggetto.sql;

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
(1, 2, 4, 'tutto arrivato in tempo, imballo fatto con cazzi di gomma', 4, '2017-07-19 00:00:00', 4);

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
  `idN` int(11) NOT NULL,
  `Nome` varchar(50) NOT NULL,
  `Prezzo` double NOT NULL,
  `Corriere` varchar(50) NOT NULL,
  `tempoRichiesto` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `tipoSpedizione`
--

INSERT INTO `tipoSpedizione` (`idS`, `idN`, `Nome`, `Prezzo`, `Corriere`, `tempoRichiesto`) VALUES
(1, 1, 'Spedizione Standard', 8, 'Bartolini', 3),
(2, 1, 'Spedizione Veloce', 15, 'GLS', 3),
(3, 1, 'Spedizione su Appuntamento', 65, 'DHL', 3),
(4, 2, 'Spedizione Standard', 6, 'SGA', 10),
(5, 2, 'Spedizione Veloce', 5, 'DHL', 10),
(7, 3, 'Spedizione Standard', 7, 'Non specificato', 2),
(8, 3, 'Spedizione Veloce', 15, 'Non specificato', 2),
(9, 3, 'Spedizione Express', 45, 'Non specificato', 2),
(10, 3, 'Spedizione su Appuntamento', 60, 'SGA', 2);

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

source utente.sql;

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
 ADD PRIMARY KEY (`idS`,`idN`), ADD KEY `negozioSpedizione` (`idN`);

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
ADD CONSTRAINT `negozioSpedizione` FOREIGN KEY (`idN`) REFERENCES `Negozio` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

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