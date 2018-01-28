-- QUERY UTENTI

-- ottenere la lista dei negozi di un venditore specificando l'id del venditore

SELECT *
FROM Negozio
WHERE idVenditore = 'idV';

-- ottenere la lista dei negozi di un venditore avendo nome e cognome del venditore

SELECT Negozio.*
FROM Negozio INNER JOIN Utente ON (Utente.nome = 'nome' AND Utente.cognome = 'cognome' AND Utente.UtenteType = 1 AND Negozio.idVenditore = Utente.id);

-- ottenre la lista dei negozi di un venditore avendo l'id e l'immagine del negozio

SELECT Negozio.*, imageNegozio.src
FROM Negozio INNER JOIN imageNegozio ON (Negozio.idVenditore = 'idV' AND Negozio.id = imageNegozio.idN);

-- ottenere la lista dei negozi di un venditore avendo nome e cognome e la prima immagine del negozio

SELECT Negozio.*, imageNegozio.src
FROM Negozio INNER JOIN Utente ON (Utente.nome = 'Carlo' AND Utente.cognome = 'Cracco' AND Utente.UtenteType = 1 AND Negozio.idVenditore = Utente.id)
	LEFT JOIN imageNegozio ON (Negozio.id = imageNegozio.idN);

-- ottenere la lista degli ordini effettuati

SELECT Ordine.*
FROM Ordine INNER JOIN Utente ON (Utente.id = 'idU' AND Ordine.idUtente = Utente.id AND Ordine.stato <> 0);

-- ottenre la lista dei negozi di un venditore avendo l'id e SOLO la prima immagine del negozio

SELECT Negozio.*, imageNegozio.src
FROM Negozio INNER JOIN imageNegozio ON (Negozio.idVenditore = 6 AND Negozio.id = imageNegozio.idN)
GROUP BY Negozio.id;

-- ottenere la lista degli ordini

SELECT *
FROM Ordine
WHERE Ordine.idUtente = 1
ORDER BY Ordine.dataOrdine DESC;

-- ottenere la lista degli ordini effettuati e portati a termine

SELECT *
FROM Ordine
WHERE Ordine.idUtente = 1 AND Ordine.stato = 4
ORDER BY Ordine.dataOrdine DESC;

-- ottenere la lista degli ordini spediti

SELECT *
FROM Ordine
WHERE Ordine.idUtente = 1 AND Ordine.stato = 3
ORDER BY Ordine.dataOrdine DESC;

-- ottenere la lista degli ordini effettuati ed in lavorazione

SELECT *
FROM Ordine
WHERE Ordine.idUtente = 1 AND Ordine.stato = 2
ORDER BY Ordine.dataOrdine DESC;

-- ottenere la lista degli ordini effettuati ma non ancora in lavorazione

SELECT *
FROM Ordine
WHERE Ordine.idUtente = 1 AND Ordine.stato = 1
ORDER BY Ordine.dataOrdine DESC;

-- ottenere la lsita dei prodotti all'interno dell'ordine della lista dei desideri

SELECT *
FROM Ordine
WHERE Ordine.idUtente = 1 AND Ordine.stato = 5
ORDER BY Ordine.dataOrdine DESC;

-- ottenere la lista dei negozi da cui ho acquistato

SELECT Negozio.*
FROM Negozio INNER JOIN Ordine ON (Ordine.idUtente = 1 AND Negozio.id = Ordine.idNegozio AND Ordine.stato <> 0)
GROUP BY Negozio.id

-- ottenere la lsita dei negozi da cui ho acquistato e la loro prima foto

SELECT Negozio.*, imageNegozio.src
FROM Negozio INNER JOIN Ordine ON (Ordine.idUtente = 1 AND Negozio.id = Ordine.idNegozio AND Ordine.stato <> 0) LEFT JOIN imageNegozio ON (Negozio.id = imageNegozio.idN)
GROUP BY Negozio.id

-- ottenere la lista dei negozi da cui ho acquistato con i dati dell'oggetto acquistato, la prima foto del negozio e la prima dell'oggetto

SELECT Negozio.*, imageNegozio.src AS imgNeg, Oggetto.id AS idOgg, Oggetto.nome AS nomeOgg, Oggetto.descrizione ,imageOggetto.src AS imgOgg
FROM Negozio INNER JOIN Ordine ON
	(Ordine.idUtente = 1 AND Negozio.id = Ordine.idNegozio AND Ordine.stato <> 0)
	LEFT JOIN imageNegozio ON (Negozio.id = imageNegozio.idN)
	LEFT JOIN Oggetto ON (Ordine.idOggetto = Oggetto.id)
    LEFT JOIN imageOggetto ON (Oggetto.id = imageOggetto.idO)
GROUP BY Oggetto.id

-- per ottenere i filtri sullo stato dell'ordine modificare la ricerca sullo stato per porlo = 1/2/3/4/5

-- ottenere il carrello (La lista degli ordini che sono nel carrello)

SELECT Ordine.*, Carrello.subtotale
FROM Carrello, Ordine
WHERE Carrello.idUtente = 'idU' AND Ordine.idOrdine = Carrello.idOrdine

-- ottenere le proprie recensioni di oggetti

SELECT *
FROM RecensioneOggetto
WHERE idUtente = 'idU'

-- ottenere le proprie recensioni di negozi

SELECT *
FROM RecensioneNegozio
WHERE idUtente = 'idU'

-- ottenere le proprie recensioni di venditori

SELECT *
FROM RecensioneVenditore
WHERE idUtente = 'idU'

-- ottenere le risposte alle proprie recensioni di oggetti

SELECT RispostaOggetto.*
FROM RispostaOggetto, RecensioneOggetto
WHERE RecensioneOggetto.idUtente = 'idU' AND RispostaOggetto.idRecensione = RecensioneOggetto.id

-- ottenere le risposte alle proprie recensioni di negozi

SELECT RispostaNegozio.*
FROM RispostaNegozio, RecensioneNegozio
WHERE RecensioneNegozio.idUtente = 'idU' AND RispostaNegozio.idRecensione = RecensioneNegozio.id

-- ottenere le risposte alle proprie recensioni di venditori

SELECT RispostaVenditore.*
FROM RispostaVenditore, RecensioneVenditore
WHERE RecensioneVenditore.idUtente = 'idU' AND RispostaVenditore.idRecensione = RecensioneVenditore.id

-- ottenere tutte le recensioni di un oggetto

SELECT *
FROM RecensioneOggetto
WHERE idOggetto = 'idO'

-- ottenere tutte le recensioni di un negozio

SELECT *
FROM RecensioneNegozio
WHERE idNegozio = 'idN'

-- ottenere tutte le recensioni di un venditore

SELECT *
FROM RecensioneVenditore
WHERE idVenditore = 'idV'

-- ottenere la lista delle richieste di assistenza fatte da uno specifico utente

SELECT *
FROM Assistenza
WHERE Assistenza.idUtente = 'IDU';

-- ottenere i dettagli di una determinata richiesta di assistenza

SELECT *
FROM Assistenza
WHERE Assistenza.id = 'ID';

-- ottenere i dati di un venditore

SELECT *
FROM Utente
WHERE Utente.id = 'IDV';

-- ottenere la lista dei prodotti nella stessa fascia di prezzo e categoria di quelli già acquistati

Create OR REPLACE View IDUInteressi as
	SELECT Oggetto.categoria, MAX(Oggetto.prezzo) AS prezzoMassimo, MIN(Oggetto.prezzo) AS prezzoMinimo
	FROM Oggetto INNER JOIN Ordine ON (Ordine.idOggetto = Oggetto.id)
	WHERE Ordine.idUtente = 1 AND Ordine.stato != 0
	GROUP BY Oggetto.categoria;

SELECT Oggetto.*
FROM Oggetto, IDUInteressi
WHERE Oggetto.categoria = IDUInteressi.categoria AND (Oggetto.prezzo - (Oggetto.prezzo*Oggetto.sconto)/100) BETWEEN IDUInteressi.prezzoMassimo AND IDUInteressi.prezzoMinimo;

-- ottenere la lista di assistenze che hanno a che fare con un ordine

SELECT *
FROM Assistenza
WHERE Assistenza.idOrdine = IDORDINE;

-- ottenere la lista delle proprie recensioni Oggetti dalla più utile

SELECT *
FROM RecensioneOggetto
WHERE RecensioneOggetto.idUtente = IDU
ORDER BY RecensioneOggetto.utilita DESC;

-- ottenere la lista delle immagini di un oggetto

SELECT *
FROM imageOggetto
WHERE imageOggetto.idO = IDO;

-- ottenere solo la prima immagine di un oggetto

SELECT *
FROM imageOggetto
WHERE imageOggetto.idO = IDO
LIMIT 1;

-- aggiungi un oggetto algli ordini nel carrello

INSERT INTO `progettoweb`.`Ordine`
	(`idOrdine`, `idOggetto`, `idNegozio`, `idUtente`, `stato`, `quantita`, `codiceTracking`, `dataArrivoPresunta`, `dataOrdine`, `prezzoDiAcquisto`)
SELECT '3', Oggetto.id , Oggetto.idNegozio , 'IDU', '0', '1', NULL, NULL, CURRENT_TIMESTAMP, (Oggetto.prezzo - (Oggetto.prezzo*Oggetto.sconto)/100)
FROM Oggetto
WHERE Oggetto.id = IDO

-- rimuovere un ordine (oggetto) dal carrello

DELETE FROM Ordine
WHERE idOrdine = 'IDORDINE' AND idOggetto = 'IDOGGETTO' AND IDUTENTE = 'IDUTENTE'

-- cambia lo stato degli ordini da nel carrello a pagati

UPDATE `progettoweb`.`Ordine`
SET `stato` = '1'
WHERE `Ordine`.`stato` = 0 AND `Ordine`.`idUtente` = 'IDUTENTE';

-- aggiungi un oggetto agli ordini nella lista dei desideri

INSERT INTO `progettoweb`.`Ordine`
	(`idOrdine`, `idOggetto`, `idNegozio`, `idUtente`, `stato`, `quantita`, `codiceTracking`, `dataArrivoPresunta`, `dataOrdine`, `prezzoDiAcquisto`)
SELECT '3', Oggetto.id , Oggetto.idNegozio , 'IDU', '5', '1', NULL, NULL, CURRENT_TIMESTAMP, (Oggetto.prezzo - (Oggetto.prezzo*Oggetto.sconto)/100)
FROM Oggetto
WHERE Oggetto.id = IDO

-- cambia lo stato di un ordine da nel carrello a lista dei desideri

UPDATE `progettoweb`.`Ordine`
SET `stato` = '5'
WHERE `Ordine`.`idOrdine` = 'IDORDINE' AND `Ordine`.`idOggetto` = 'IDOGGETTO' AND `Ordine`.`idUtente` = 'IDUTENTE';

-- cambia lo stato di un ordine da nella lista dei desideri al carrello (Se c'è già lo stesso oggetto anche nel carrello semplicmente ne aumento la quantità)

SET @oggettoPresente = 0;
SET @idOrdine = 0;
SET @IDU = 4;
SET @IDO = 4;
SET @idOrdineDesideri = 3;

SELECT @oggettoPresente:=COUNT(Ordine.idOggetto),@idOrdine := Ordine.idOrdine
FROM Ordine
WHERE Ordine.stato = 0 AND Ordine.idUtente = @IDU AND Ordine.idOggetto = @IDO;

SELECT @oggettoPresente AS Oggetto;
-- questo if deve essere fatto in jsp, controllo la query precedente e poi eseguo le seguenti
-- continua a darmi errore in sql dopo una select e non capisco perchè
IF @oggettoPresente = 1
THEN
	UPDATE Ordine
	SET quantita = quantita + 1
	WHERE idOrdine = @idOrdine AND idOggetto = @IDO AND idUtente = @IDU;

	DELETE FROM Ordine
	WHERE idOrdine = @idOrdineDesideri AND idOggetto = @IDO AND IDUTENTE = @IDU;

ELSE
	UPDATE `progettoweb`.`Ordine`
	SET `stato` = 0
	WHERE `Ordine`.`idOrdine` = @idOrdineDesideri AND `Ordine`.`idOggetto` = @IDO AND `Ordine`.`idUtente` = @IDU;
END IF;

-- aggiungi una recensione ad un determinato venditore

SET @idVenditore = 6;
SET @idUtente = 1;
SET @txt = "tutto molto bello";

INSERT INTO `progettoweb`.`RecensioneVenditore`
	(`id`, `idVenditore`, `idUtente`, `testo`, `valutazione`, `data`, `utilita`)
VALUES (NULL, @idVenditore, @idUtente, @txt, '4', '2017-07-19 00:00:00', '0');

-- aggiungi una recensione ad un determinato negozio

SET @idNegozio = 3;
SET @idUtente = 1;
SET @txt = "tutto molto bello";

INSERT INTO `progettoweb`.`RecensioneNegozio`
	(`id`, `idNegozio`, `idUtente`, `testo`, `valutazione`, `data`, `utilita`)
VALUES (NULL, @idVenditore, @idUtente, @txt, '4', '2017-07-19 00:00:00', '0');


-- aggiungi una recensione ad un determinato oggetto

SET @idOggetto = 3;
SET @idUtente = 2;
SET @txt = "tutto molto bello";

INSERT INTO `progettoweb`.`RecensioneOggetto`
	(`id`, `idOggetto`, `idUtente`, `testo`, `valutazione`, `data`, `utilita`)
VALUES (NULL, @idOggetto, @idUtente, @txt, '1', '2017-07-25 11:18:35', '3');

-- ottenere un boolean se si ha recensito oppure no un venditore (se il count è 1 vuol dire di si)

SELECT COUNT(idVenditore) AS counter
FROM RecensioneVenditore
WHERE RecensioneVenditore.idVenditore = 'idv' AND RecensioneVenditore.idUtente = 'idu';

-- ottenere un boolean se si ha recensito oppure no un negozio

SELECT COUNT(idNegozio) AS counter
FROM RecensioneNegozio
WHERE RecensioneNegozio.idVenditore = 'idv' AND RecensioneNegozio.idUtente = 'idu';

-- ottenere un boolean se si ha recensito oppure no un oggetto

SELECT COUNT(idOggetto) AS counter
FROM RecensioneVenditore
WHERE RecensioneVenditore.idVenditore = 'idv' AND RecensioneVenditore.idUtente = 'idu';

-- aggiungere un proprio indirizzo

INSERT INTO `progettoweb`.`Indirizzo`
	(`idI`, `stato`, `regione`, `provincia`, `citta`, `via`, `nCivico`, `interno`, `latitudine`, `longitudine`)
VALUES (NULL, 'italia', 'friuli', 'bho', 'doppio bho', 'via roma', '26', '2', NULL, NULL);

SET @IDI = 1;

SELECT @IDI:=idI
FROM Indirizzo
WHERE stato = 'italia' AND regione = 'friuli' AND provincia = 'bho' AND citta = 'doppio bho' AND via = 'via roma' AND nCivico = '26' AND interno = '2';

INSERT INTO `progettoweb`.`IndirizzoUtente`
	(`idI`, `idU`)
VALUES (@IDI, '1');

-- eliminaree un proprio indirizzo
-- verrà eliminato automaticamente anche dalla tabella IndirizzoUtente
DELETE FROM Indirizzo
WHERE idI = 'idI'
