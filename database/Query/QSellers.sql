-- QUERY VENDITORI

-- ottenere la lista dei propri negozi
SELECT *
FROM Negozio
WHERE idVenditore=ID

-- ottenere la lista degli ordini ricevuti
SELECT *
FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id
WHERE idVenditore=ID

-- ottenere la lista degli ordini ricevuti dal più nuovo al più vecchio
SELECT *
FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id
WHERE idVenditore=ID
ORDER BY dataOrdine DESC

-- ottenere la lista degli ordini non ancora in carico
SELECT *
FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id
WHERE idVenditore=ID AND stato=1

-- ottenere la lista degli ordini in lavorazione
SELECT *
FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id
WHERE idVenditore=ID AND stato=2

-- ottenere la lista degli ordini già spediti
SELECT *
FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id
WHERE idVenditore=ID AND stato=3

-- ottenere la lista degli ordini conclusi
SELECT *
FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id
WHERE idVenditore=ID AND stato=4

-- ottenere la lista degli ordini ricevuti in un determinato giorno
SELECT *
FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id
WHERE idVenditore=ID AND dataOrdine=DATA

-- ottenere la lista degli ordini ricevuti nella settimana corrente
SELECT *
FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id
WHERE idVenditore=ID AND WEEK(dataOrdine)=WEEK(DATA)

-- ottenere la lista degli ordini ricevuti nel mese corrente
SELECT *
FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id
WHERE idVenditore=ID AND MONTH(dataOrdine)=MONTH(DATA)

-- ottenere la lista degli ordini ricevuti nell'anno corrente
SELECT *
FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id
WHERE idVenditore=ID AND YEAR(dataOrdine)=YEAR(DATA)

-- ottenere la lista dei propri negozi con anche il numero di vendite
SELECT idNegozio, COUNT(idOrdine) AS numeroVendite
FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id
WHERE idVenditore=ID
GROUP BY idNegozio

-- ottenere la lista dei propri negozi ordinati per vendite maggiori
SELECT idNegozio, COUNT(idOrdine) AS numeroVendite
FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id
WHERE idVenditore=ID
GROUP BY idNegozio
ORDER BY COUNT(idOrdine) DESC

-- ottenere la lista dei propri negozi per vendite minori
SELECT idNegozio, COUNT(idOrdine) AS numeroVendite
FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id
WHERE idVenditore=ID
GROUP BY idNegozio
ORDER BY COUNT(idOrdine) ASC

-- ottenere la lista dei propri negozi con vendite inferiori ad un certo valore
SELECT idNegozio, COUNT(idOrdine) AS numeroVendite
FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id
WHERE idVenditore=ID
GROUP BY idNegozio
HAVING COUNT(idOrdine)<VALORE

-- ottenere la lista dei propri negozi con vendite superiori ad un certo valore
SELECT idNegozio, COUNT(idOrdine) AS numeroVendite
FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id
WHERE idVenditore=ID
GROUP BY idNegozio
HAVING COUNT(idOrdine)>VALORE

-- DA QUI cominciano le query modificate
-- ottenere la lista dei negozi che vendono prodotti di una certa categoria
SELECT *
FROM ((Oggetto JOIN Categoria ON Oggetto.categoria=Categoria.id) JOIN Negozio ON Negozio.id=Oggetto.idNegozio) JOIN Utente ON Utente.id=Negozio.idVenditore
WHERE idVenditore=ID AND attivo=1 AND Categoria.id=1

-- ottenere la lista dei negozi che vendono prodotti di una certa categoria ordinate da quello con più vendite

SELECT Negozio.*, COUNT(Ordine.idOrdine)
FROM ((Oggetto JOIN Negozio ON Negozio.id = Oggetto.idNegozio) JOIN Utente ON Utente.id = Negozio.idVenditore) JOIN Ordine ON Ordine.idNegozio = Negozio.id
WHERE idVenditore = ID AND attivo = 1 AND Categoria.id = 1 AND Oggetto.categoria = ID AND Ordine.stato BETWEEN 1 AND 4
GROUP BY Negozio.id
ORDER BY COUNT(Ordine.idOrdine) DESC;

-- ottenere la lista dei negozi che vendono prodotti di una certa categoria ordinate da quello con meno vendite

SELECT Negozio.*, COUNT(Ordine.idOrdine)
FROM ((Oggetto JOIN Negozio ON Negozio.id = Oggetto.idNegozio) JOIN Utente ON Utente.id = Negozio.idVenditore) JOIN Ordine ON Ordine.idNegozio = Negozio.id
WHERE idVenditore = ID AND attivo = 1 AND Categoria.id = 1 AND Oggetto.categoria = ID AND Ordine.stato BETWEEN 1 AND 4
GROUP BY Negozio.id
ORDER BY COUNT(Ordine.idOrdine) ASC;

-- ottenere la lista dei propri negozi ordinati per data di apertura
SELECT *
FROM Negozio
WHERE idVenditore=ID
ORDER BY dataApertura DESC;

-- ottenere la lista dei propri negozi ordinati per fatturato
SELECT SUM(Ordine.quantita * Oggetto.prezzo) AS fatturato, Ordine.idNegozio, Negozio.nomeNegozio
FROM (Oggetto JOIN Ordine ON Oggetto.id = Ordine.idOggetto) JOIN Negozio ON Negozio.id = Ordine.idNegozio
WHERE Negozio.idVenditore = ID AND Ordine.stato BETWEEN 1 AND 4
GROUP BY Negozio.id
ORDER BY fatturato DESC;

-- ottenere i dati di un negozio
SELECT *
FROM Negozio
WHERE idVenditore=ID AND id=ID;

-- ottenere i dati delle vendite di un determinato negozio
SELECT *
FROM Ordine
WHERE Ordine.idNegozio = ID AND Ordine.stato BETWEEN 1 AND 4;

-- ottenere i dati di vendita di un determinato negozio in una determinata categoria
SELECT Ordine.*
FROM Ordine JOIN Oggetto ON Oggetto.id = Ordine.idOggetto
WHERE Ordine.idNegozio = IDn AND Ordine.stato BETWEEN 1 AND 4 AND Oggetto.categoria = IDc;

-- ottenere le richieste di assistenza in cui si è stati citati
SELECT *
FROM Utente JOIN Assistenza ON Utente.id=Assistenza.idVenditore
WHERE idVenditore=ID;

-- ottenere la lista dei prodotti venduti raggruppati per categoria e negozio
SELECT *
FROM (Oggetto JOIN Categoria ON Oggetto.categoria=Categoria.id) JOIN Negozio ON Negozio.id=Oggetto.idNegozio
WHERE idVenditore=ID AND stato=4
GROUP BY idNegozio ASC, Categoria.nome ASC;

-- ottenere la lista dei prodotti venduti in una determinata categoria raggruppati per negozio
SELECT *
FROM (Oggetto JOIN Categoria ON Oggetto.categoria=Categoria.id) JOIN Negozio ON Negozio.id=Oggetto.idNegozio
WHERE idVenditore=ID AND stato=4 AND Categoria.nome=CATEGORIA
GROUP BY idNegozio ASC;

-- ottenere la lista dei prodotti venduti ordinati per valutazioni
SELECT *
FROM (Ordine JOIN RecensioneOggetto ON Ordine.idOggetto=RecensioneOggetto.idOggetto) JOIN Negozio ON Ordine.idNegozio=Negozio.id
WHERE idVenditore=ID AND stato=4
ORDER BY AVG(RecensioneOggetto.valutazione) DESC;

-- ottenere la lsita dei propri negozi ordinati per recensioni
SELECT *
FROM Negozio
WHERE idVenditore=ID
ORDER BY valutazione DESC;

-- ottenere la lista dei proprio prodotti in sconto raggruppati per categoria e negozio
SELECT *
FROM (Oggetto JOIN Categoria ON Oggetto.categoria=Categoria.id) JOIN Negozio ON Negozio.id=Oggetto.idNegozio
WHERE idVenditore=ID AND sconto>0
GROUP BY Categoria.nome ASC, idNegozio ASC;

-- ottenere la lista dei proprio prodotti in sconto raggruppati per categoria e negozio ordinati per scadenza più vicina dello sconto
SELECT *
FROM (Oggetto JOIN Categoria ON Oggetto.categoria=Categoria.id) JOIN Negozio ON Negozio.id=Oggetto.idNegozio
WHERE idVenditore=ID AND sconto>0
GROUP BY Categoria.nome ASC, idNegozio ASC
ORDER BY dataFineSconto ASC;

-- ottenere la lista delle recensioni ricevute
SELECT *
FROM RecensioneVenditore
WHERE idVenditore=ID;

-- aggiungere un proprio negozio
INSERT INTO Negozio (idVenditore, nomeNegozio, valutazione, attivo, idI, dataApertura) VALUES (ID, NOME, VALUTAZIONE, '1', IDINDIRIZZO, CURRENT_TIMESTAMP);

-- chiudere un proprio negozio (rimuoverlo)
UPDATE Negozio SET attivo=0 WHERE id=ID;

-- modificare lo stato di un ordine da pagato a in lavorazione
UPDATE Ordine SET stato=1 WHERE idOrdine=ID;

-- modificare lo stato di un ordine da lavorazione a spedito
UPDATE Ordine SET stato=2 WHERE idOrdine=ID;

-- aggiungere ad un ordine spedito il codice di tracking
UPDATE Ordine SET codiceTracking=TRACKING WHERE idOrdine=ID;

-- aggiungere un prodotto ad un proprio negozio
INSERT INTO Oggetto (idNegozio, nome, prezzo, descrizione, ritiroInNegozio, disponibilita, statoDisponibilita, sconto, variazione, dataFineSconto, categoria) VALUES (IDNEGOZIO, NOME, PREZZO, DESCRIZIONE, RITIRONEGOZIO, DISPONIBILITA, STATODISPO, SCONTO, VARIAZIONE, FINESCONTO, CATEGORIA);

-- rimuovere un oggetto da un proprio negozio
DELETE FROM Oggetto WHERE id=ID;

-- modificare il prezzo di un oggetto di un proprio negozio
UPDATE Oggetto SET prezzo=PREZZO WHERE id=ID;

-- aggiungere uno sconto ad un proprio oggetto
UPDATE Oggetto SET sconto=SCONTO WHERE id=ID;

-- rimuovere uno sconto dagli oggetti in sconto
UPDATE Oggetto SET prezzo=0 WHERE id=ID;

-- aggiungere una foto ad un prodotto
-- aggiungere una foto ad un negozio
-- rimuovere una foto di un prodotto
-- rimuovere una foto di un negozio
