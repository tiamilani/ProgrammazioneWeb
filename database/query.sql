-- piccola guida sui valori utilizzati:
-- tipo di utenti: 	0 -> utenti normali
-- 					1 -> utenti venditori
-- 					2 -> utenti amministratori
-- 					3 -> utenti che non hanno ancora confermato l'account
-- 					4 -> super administrator (Utente di test che può modificare qualsiasi valore)

-- tipo di ordini:	0 -> ordine nel carrello
-- 					1 -> ordine pagato
-- 					2 -> ordine in lavorazione
-- 					3 -> ordine spedito
-- 					4 -> ordine arrivato
--					5 -> ordine nella lista dei desideri

-- query generali:
-- selezionare tutti gli utenti normali

SELECT *
FROM Utente
WHERE UtenteType = 0;

-- selezionare tutti gli utenti venditori

SELECT *
FROM Utente
WHERE UtenteType = 1;

-- selezionare tutti gli utenti amministratori

SELECT *
FROM Utente
WHERE UtenteType = 2;

-- selezionare un utente specificando email e password

SELECT *
FROM Utente
WHERE mail = 'mail' AND password = 'password';

-- ottenere tutti gli utenti con un certo nome

SELECT *
FROM Utente
WHERE nome = 'nome';

-- ottenere tutti gli utenti con un certo nome e cognome

SELECT *
FROM Utente
WHERE nome = 'nome' AND cognome = 'cognome';

-- ottenere i dati un utente specificando l'id

SELECT *
FROM Utente
WHERE id = id;

-- ottenere indirizzi di un utente avendo l'id

select Indirizzo.*
from Indirizzo INNER JOIN IndirizzoUtente ON (Indirizzo.idI = IndirizzoUtente.idI AND IndirizzoUtente.idU = 1);

-- ottenere i dati di un utente e l'indirizzo avendo email e password

select Utente.*, Indirizzo.*
from Utente INNER JOIN IndirizzoUtente INNER JOIN Indirizzo
	ON (Utente.mail = 'abc@def.ghi' AND Utente.password = 'ciao' AND Indirizzo.idI = IndirizzoUtente.idI AND IndirizzoUtente.idU = Utente.id);

-- ottenere i dati di un utente e l'indirizzo avendo l'id

select Utente.*, Indirizzo.*
from Utente INNER JOIN IndirizzoUtente INNER JOIN Indirizzo
	ON (Utente.id = id AND Indirizzo.idI = IndirizzoUtente.idI AND IndirizzoUtente.idU = Utente.id);

-- ottenere l'immagine di un utente

SELECT src
FROM imageUtente
WHERE idU = 'idU';

-- ottener i dati di un utente, l'indirizzo ed l'immagine a cui fa riferimento

select Utente.*, Indirizzo.*, imageUtente.src
from Utente INNER JOIN IndirizzoUtente INNER JOIN Indirizzo ON (Utente.mail = 'abc@def.ghi' AND Utente.password = 'ciao' AND Indirizzo.idI = IndirizzoUtente.idI AND IndirizzoUtente.idU = Utente.id)
	LEFT JOIN imageUtente ON (imageUtente.idU = Utente.id);


-- ottenere gli utenti amministratori ordinati per numero di richieste

SELECT DISTINCT COUNT(A2.idAmministratore) as contatore, A1.idAmministratore as id
FROM Assistenza A1
LEFT JOIN Assistenza A2 ON (A1.idAmministratore = A2.idAmministratore)
GROUP BY A1.id
ORDER BY contatore DESC;

-- modificare l'immagine del profilo di un utente con un determinato id

UPDATE imageUtente
	SET imageUtente.src = 'NewSrc'
	WHERE imageUtente.idU = 'idU'

-- modificare la propria mail

UPDATE Utente
	SET Utente.mail = 'NewMail'
	WHERE Utente.id = 'id';

-- modificare la propria password

UPDATE Utente
	SET Utente.password = 'newPassword'
	WHERE Utente.id = 'id';

-- modificare uno dei propri indirizzo, suppongo l'utente abbia scelto un indirizzo dalla lista dei propri indirizzi quindi avrò l'id dell'indirizzo

UPDATE Indirizzo
	SET Indirizzo.citta = 'NewCitta'
		Indirizzo.interno = 'NewCitta'
		Indirizzo.latitudine = 'NewCitta'
		Indirizzo.longitudine = 'NewCitta'
		Indirizzo.nCivico = 'NewCitta'
		Indirizzo.provincia = 'NewCitta'
		Indirizzo.regione = 'NewCitta'
		Indirizzo.stato = 'NewCitta'
		Indirizzo.via = 'NewCitta'
	WHERE Indirizzo.idI = 'idI';

-- rimuovere l'immagine del profilo di un determinato utente
DELETE FROM imageUtente
	WHERE imageUtente.idU = 'idU';

-- aggiungere una immagine del profilo di un utente
INSERT INTO image
	(src,idU)
SELECT 'newSrc', Utente.id
FROM Utente
WHERE Utente.id = 'id';

-- per gli utenti normali:
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

-- non specificato se con ritiro a mano o scontati:
-- ottenere la lista di oggetti che contengono una stringa nel nome

SELECT *
FROM Oggetto
WHERE nomeDownCase LIKE '%STRINGA%'

-- ottenere la lista di oggetti di una categoria

SELECT *
FROM Oggetto
WHERE categoria = CATEGORIA

-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND nomeDownCase LIKE '%STRINGA%'

-- ottenere la lista di oggetti con un certo prezzo minimo

SELECT *
FROM Oggetto
WHERE prezzo >= MINIMO

-- ottenere la lista di oggetti con un certo prezzo massimo

SELECT *
FROM Oggetto
WHERE prezzo <= MASSIMO

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo

SELECT *
FROM Oggetto
WHERE prezzo BETWEEN MINIMO AND MASSIMO

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND prezzo >= MINIMO

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND prezzo <= MASSIMO

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND prezzo BETWEEN MINIMO AND MASSIMO

-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE prezzo >= MINIMO AND nomeDownCase LIKE '%STRINGA%'

-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE prezzo <= MASSIMO AND nomeDownCase LIKE '%STRINGA%'

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE prezzo BETWEEN MINIMO AND MASSIMO AND nomeDownCase LIKE '%STRINGA%'

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND prezzo >= MINIMO AND nomeDownCase LIKE '%STRINGA%'

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND prezzo <= MASSIMO AND nomeDownCase LIKE '%STRINGA%'

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND prezzo BETWEEN MINIMO AND MASSIMO AND nomeDownCase LIKE '%STRINGA%'

-- ottenere la lista di oggetti di un determinato negozio

SELECT *
FROM Oggetto
WHERE Oggetto.idNegozio = 'idN'

-- ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio

SELECT *
FROM Oggetto
WHERE idNegozio=ID AND nomeDownCase LIKE '%STRINGA%'

-- ottenere la lista di oggetti di una categoria di un determinato negozio

SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA

-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio

SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%'

-- ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.prezzo >= MINIMO

-- ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.prezzo <= MASSIMO

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO

-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%'

-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%'

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%'

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%'

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%'

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%'

-- ottenere la lista di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
-- BASSANO DEL GRAPPA
SET @latitudine = 45.7665600;
SET @longitudine = 11.727390;
SET @raggio = 50;

SELECT Negozio.*
FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
WHERE @raggio >= 111.111 *
           DEGREES(ACOS(COS(RADIANS(@latitudine))
                 * COS(RADIANS(Indirizzo.latitudine))
                 * COS(RADIANS(@longitudine - Indirizzo.longitudine))
                 + SIN(RADIANS(@latitudine))
                 * SIN(RADIANS(Indirizzo.latitudine))));
                 
-- ottenere la lista di oggetti in negozi data una determinata longitudine, latitudine ed un raggio di ricerca
Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id);


-- ottenere la lista di oggetti che contengono una stringa nel nome, nei negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.nomeDownCase LIKE '%STRINGA%';

-- ottenere la lista di oggetti di una categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.categoria=CATEGORIA;

-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%';

-- ottenere la lista di oggetti con un certo prezzo minimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO;

-- ottenere la lista di oggetti con un certo prezzo massimo vdi negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA;

-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO and Oggetto.nomeDownCase LIKE '%STRINGA%';

-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO and Oggetto.nomeDownCase LIKE '%STRINGA%';

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%';

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%';

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%';

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%';

-- ottenere la lista di negozi di un determinato venditore
SELECT *
FROM Negozio
WHERE idVenditore=ID

-- ottenere la lista di oggetti di negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID

-- ottenere la lista di oggetti che contengono una stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND Oggetto.nomeDownCase LIKE '%STRINGA%'

-- ottenere la lista di oggetti di una categoria nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND categoria=CATEGORIA

-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome,nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%'

-- ottenere la lista di oggetti con un certo prezzo minimo dei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND Oggetto.prezzo >= MINIMO

-- ottenere la lista di oggetti con un certo prezzo massimo nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND Oggetto.prezzo <= MASSIMO

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO

-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore=ID AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%'

-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore=ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%'

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore=ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%'

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore=ID AND categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%'

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore=ID AND categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%'

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore=ID AND categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%'

-- ottenere la lista di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

SET @latitudine = 45.7665600;
SET @longitudine = 11.727390;
SET @raggio = 50;

SELECT Negozio.*
FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
WHERE @raggio >= 111.111 *
           DEGREES(ACOS(COS(RADIANS(@latitudine))
                 * COS(RADIANS(Indirizzo.latitudine))
                 * COS(RADIANS(@longitudine - Indirizzo.longitudine))
                 + SIN(RADIANS(@latitudine))
                 * SIN(RADIANS(Indirizzo.latitudine))))
     AND Negozio.idVenditore = ID;

-- ottenere la lista di oggetti di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id);

-- ottenere la lista di oggetti che contengono una stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;
			
SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.nomeDownCase LIKE '%STRINGA%';

-- ottenere la lista di oggetti di una categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.categoria=CATEGORIA;

-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%';

-- ottenere la lista di oggetti con un certo prezzo minimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO;

-- ottenere la lista di oggetti con un certo prezzo massimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;
			
SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;
			
SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA;

-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%';

-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%';

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;
			
SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%';

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%';

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%';

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;
			
SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%';

-- specificato ritiro a mano ma non se scontati
-- TUtte le query presenti presuppongono che Sia richiesto ci sia il ritiro a mano, anche se 
-- nel titolo della query non è presente, ATTENZIONE !!!!!!!!!!!!!!!

-- ottenere la lista di oggetti che contengono una stringa nel nome

SELECT *
FROM Oggetto
WHERE nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti di una categoria

SELECT *
FROM Oggetto
WHERE categoria = CATEGORIA AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo

SELECT *
FROM Oggetto 
WHERE prezzo >= MINIMO AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo

SELECT *
FROM Oggetto
WHERE prezzo <= MASSIMO AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo

SELECT *
FROM Oggetto
WHERE prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND prezzo >= MINIMO AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND prezzo <= MASSIMO AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE prezzo >= MINIMO AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE prezzo <= MASSIMO AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE prezzo BETWEEN MINIMO AND MASSIMO AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND prezzo >= MINIMO AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND prezzo <= MASSIMO AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND prezzo BETWEEN MINIMO AND MASSIMO AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti di un determinato negozio

SELECT *
FROM Oggetto
WHERE Oggetto.idNegozio = 'idN' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio

SELECT *
FROM Oggetto
WHERE idNegozio=ID AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti di una categoria di un determinato negozio

SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio

SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.prezzo >= MINIMO AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
-- BASSANO DEL GRAPPA
SET @latitudine = 45.7665600;
SET @longitudine = 11.727390;
SET @raggio = 50;

SELECT Negozio.*
FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
WHERE @raggio >= 111.111 *
           DEGREES(ACOS(COS(RADIANS(@latitudine))
                 * COS(RADIANS(Indirizzo.latitudine))
                 * COS(RADIANS(@longitudine - Indirizzo.longitudine))
                 + SIN(RADIANS(@latitudine))
                 * SIN(RADIANS(Indirizzo.latitudine))));
                 
-- ottenere la lista di oggetti in negozi data una determinata longitudine, latitudine ed un raggio di ricerca
Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.ritiroInNegozio = 1;


-- ottenere la lista di oggetti che contengono una stringa nel nome, nei negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti di una categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.categoria=CATEGORIA AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo vdi negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO and Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO and Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di negozi di un determinato venditore
SELECT *
FROM Negozio
WHERE idVenditore=ID

-- ottenere la lista di oggetti di negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti che contengono una stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) 
WHERE Negozio.idVenditore = ID AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti di una categoria nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND categoria=CATEGORIA AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome,nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo dei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND Oggetto.prezzo >= MINIMO AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore=ID AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore=ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore=ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore=ID AND categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore=ID AND categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore=ID AND categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

SET @latitudine = 45.7665600;
SET @longitudine = 11.727390;
SET @raggio = 50;

SELECT Negozio.*
FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
WHERE @raggio >= 111.111 *
           DEGREES(ACOS(COS(RADIANS(@latitudine))
                 * COS(RADIANS(Indirizzo.latitudine))
                 * COS(RADIANS(@longitudine - Indirizzo.longitudine))
                 + SIN(RADIANS(@latitudine))
                 * SIN(RADIANS(Indirizzo.latitudine))))
     AND Negozio.idVenditore = ID;

-- ottenere la lista di oggetti di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti che contengono una stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;
			
SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti di una categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.categoria=CATEGORIA AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;
			
SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;
			
SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;
			
SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;
			
SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

-- specificato scontati ma non ritiro a mano
-- ATTENZIONE tutte le seguenti query sono da considerarsi con il filtro prodotti scontati, ATTENZIONE !!!!!!!

-- ottenere la lista di oggetti che contengono una stringa nel nome

SELECT *
FROM Oggetto
WHERE nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti di una categoria

SELECT *
FROM Oggetto
WHERE categoria = CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo

SELECT *
FROM Oggetto
WHERE prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo massimo

SELECT *
FROM Oggetto
WHERE prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo

SELECT *
FROM Oggetto
WHERE prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria

SELECT *
FROM Oggetto 
WHERE categoria=CATEGORIA AND prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE prezzo >= MINIMO AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE prezzo <= MASSIMO AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE prezzo BETWEEN MINIMO AND MASSIMO AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND prezzo >= MINIMO AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND prezzo <= MASSIMO AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND prezzo BETWEEN MINIMO AND MASSIMO AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti di un determinato negozio

SELECT *
FROM Oggetto
WHERE Oggetto.idNegozio = 'idN' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio

SELECT *
FROM Oggetto
WHERE idNegozio=ID AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti di una categoria di un determinato negozio

SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio

SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;
 
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio
SELECT *
FROM Oggetto 
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
-- BASSANO DEL GRAPPA
SET @latitudine = 45.7665600;
SET @longitudine = 11.727390;
SET @raggio = 50;

SELECT Negozio.*
FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
WHERE @raggio >= 111.111 *
           DEGREES(ACOS(COS(RADIANS(@latitudine))
                 * COS(RADIANS(Indirizzo.latitudine))
                 * COS(RADIANS(@longitudine - Indirizzo.longitudine))
                 + SIN(RADIANS(@latitudine))
                 * SIN(RADIANS(Indirizzo.latitudine))));
                 
-- ottenere la lista di oggetti in negozi data una determinata longitudine, latitudine ed un raggio di ricerca
Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.`dataFineSconto` IS NOT NULL;


-- ottenere la lista di oggetti che contengono una stringa nel nome, nei negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti di una categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo massimo vdi negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO and Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO and Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di negozi di un determinato venditore
SELECT *
FROM Negozio
WHERE idVenditore=ID

-- ottenere la lista di oggetti di negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti che contengono una stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti di una categoria nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome,nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo dei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND Oggetto.prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo massimo nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore=ID AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore=ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore=ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore=ID AND categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore=ID AND categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore=ID AND categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

SET @latitudine = 45.7665600;
SET @longitudine = 11.727390;
SET @raggio = 50;

SELECT Negozio.*
FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
WHERE @raggio >= 111.111 *
           DEGREES(ACOS(COS(RADIANS(@latitudine))
                 * COS(RADIANS(Indirizzo.latitudine))
                 * COS(RADIANS(@longitudine - Indirizzo.longitudine))
                 + SIN(RADIANS(@latitudine))
                 * SIN(RADIANS(Indirizzo.latitudine))))
     AND Negozio.idVenditore = ID;

-- ottenere la lista di oggetti di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti che contengono una stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;
			
SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti di una categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo massimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;
			
SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;
			
SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;
			
SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;
			
SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

-- specificato ritiro a mano e scontati
-- ATTNENZIONE, IL NOME NON CORRISPONDE MA TUTTE LE QUERY PREVEDONO RITIRO A MANO E SCONTI
-- ottenere la lista di oggetti che contengono una stringa nel nome

SELECT *
FROM Oggetto
WHERE nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti di una categoria

SELECT *
FROM Oggetto
WHERE categoria = CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo

SELECT *
FROM Oggetto
WHERE prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo

SELECT *
FROM Oggetto
WHERE prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo

SELECT *
FROM Oggetto
WHERE prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria

SELECT *
FROM Oggetto 
WHERE categoria=CATEGORIA AND prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE prezzo >= MINIMO AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE prezzo <= MASSIMO AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE prezzo BETWEEN MINIMO AND MASSIMO AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND prezzo >= MINIMO AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND prezzo <= MASSIMO AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome

SELECT *
FROM Oggetto
WHERE categoria=CATEGORIA AND prezzo BETWEEN MINIMO AND MASSIMO AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti di un determinato negozio

SELECT *
FROM Oggetto
WHERE Oggetto.idNegozio = 'idN' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio

SELECT *
FROM Oggetto
WHERE idNegozio=ID AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti di una categoria di un determinato negozio

SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio

SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;
 
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio
SELECT *
FROM Oggetto 
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
SELECT *
FROM Oggetto
WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
-- BASSANO DEL GRAPPA
SET @latitudine = 45.7665600;
SET @longitudine = 11.727390;
SET @raggio = 50;

SELECT Negozio.*
FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
WHERE @raggio >= 111.111 *
           DEGREES(ACOS(COS(RADIANS(@latitudine))
                 * COS(RADIANS(Indirizzo.latitudine))
                 * COS(RADIANS(@longitudine - Indirizzo.longitudine))
                 + SIN(RADIANS(@latitudine))
                 * SIN(RADIANS(Indirizzo.latitudine))));
                 
-- ottenere la lista di oggetti in negozi data una determinata longitudine, latitudine ed un raggio di ricerca
Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;


-- ottenere la lista di oggetti che contengono una stringa nel nome, nei negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti di una categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo vdi negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO and Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO and Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))));

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di negozi di un determinato venditore
SELECT *
FROM Negozio
WHERE idVenditore=ID

-- ottenere la lista di oggetti di negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti che contengono una stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti di una categoria nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome,nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo dei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND Oggetto.prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore=ID AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore=ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore=ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore=ID AND categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore=ID AND categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore=ID AND categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

SET @latitudine = 45.7665600;
SET @longitudine = 11.727390;
SET @raggio = 50;

SELECT Negozio.*
FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
WHERE @raggio >= 111.111 *
           DEGREES(ACOS(COS(RADIANS(@latitudine))
                 * COS(RADIANS(Indirizzo.latitudine))
                 * COS(RADIANS(@longitudine - Indirizzo.longitudine))
                 + SIN(RADIANS(@latitudine))
                 * SIN(RADIANS(Indirizzo.latitudine))))
     AND Negozio.idVenditore = ID;

-- ottenere la lista di oggetti di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti che contengono una stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;
			
SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti di una categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;
			
SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;
			
SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;
			
SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;

SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

Create OR REPLACE View NegoziNellaDistanza as

    SELECT Negozio.id
	FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
	WHERE 50>= 111.111 *
			   DEGREES(ACOS(COS(RADIANS(45.7665600))
					 * COS(RADIANS(Indirizzo.latitudine))
					 * COS(RADIANS(11.727390 - Indirizzo.longitudine))
					 + SIN(RADIANS(45.7665600))
					 * SIN(RADIANS(Indirizzo.latitudine))))
			AND Negozio.idVenditore = ID;
			
SELECT Oggetto.*
FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

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
-- aggiungi una recensione ad un determinato oggetto
-- ottenere un boolean se si ha recensito oppure no un venditore
-- ottenere un boolean se si ha recensito oppure no un negozio
-- ottenere un boolean se si ha recensito oppure no un oggetto
-- aggiungere un proprio indirizzo
-- eliminaree un proprio indirizzo


-- per i venditori:
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
SELECT idNegozio, SUM(idOrdine) AS numeroVendite
FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id
WHERE idVenditore=ID
GROUP BY idNegozio

-- ottenere la lista dei propri negozi ordinati per vendite maggiori
SELECT idNegozio, SUM(idOrdine) AS numeroVendite
FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id
WHERE idVenditore=ID
GROUP BY idNegozio
ORDER BY SUM(idOrdine) DESC

-- ottenere la lista dei propri negozi per vendite minori
SELECT idNegozio, SUM(idOrdine) AS numeroVendite
FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id
WHERE idVenditore=ID
GROUP BY idNegozio
ORDER BY SUM(idOrdine) ASC

-- ottenere la lista dei propri negozi con vendite inferiori ad un certo valore
SELECT idNegozio, SUM(idOrdine) AS numeroVendite
FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id
WHERE idVenditore=ID
GROUP BY idNegozio
HAVING SUM(idOrdine)<VALORE

-- ottenere la lista dei propri negozi con vendite superiori ad un certo valore
SELECT idNegozio, SUM(idOrdine) AS numeroVendite
FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id
WHERE idVenditore=ID
GROUP BY idNegozio
HAVING SUM(idOrdine)>VALORE

-- ottenere la lista dei negozi che vendono prodotti di una certa categoria
SELECT *
FROM ((Oggetto JOIN Categoria ON Oggetto.categoria=Categoria.id) JOIN Negozio ON Negozio.id=Oggetto.idNegozio) JOIN Utente ON Utente.id=Negozio.idVenditore
WHERE idVenditore=ID AND attivo=1 AND Categoria.nome="Elettronica"

-- ottenere la lista dei negozi che vendono prodotti di una certa categoria ordinate da quello con più vendite
-- ottenere la lista dei negozi che vendono prodotti di una certa categoria ordinate da quello con meno vendite
-- ottenere la lista dei propri negozi ordinati per data di apertura
SELECT *
FROM Negozio
WHERE idVenditore=ID
ORDER BY dataApertura DESC

-- ottenere la lista dei propri negozi ordinati per fatturato
-- ottenere i dati di un negozio
SELECT *
FROM Negozio
WHERE idVenditore=ID AND id=ID

-- ottenere i dati delle vendite di un determinato negozio
-- ottenere i dati di vendita di un determinato negozio in una determinata categoria
-- ottenere le richieste di assistenza in cui si è stati citati
SELECT *
FROM Utente JOIN Assistenza ON Utente.id=Assistenza.idVenditore
WHERE idVenditore=ID

-- ottenere la lista dei prodotti venduti raggruppati per categoria e negozio
SELECT *
FROM (Oggetto JOIN Categoria ON Oggetto.categoria=Categoria.id) JOIN Negozio ON Negozio.id=Oggetto.idNegozio
WHERE idVenditore=ID AND stato=4
GROUP BY idNegozio ASC, Categoria.nome ASC

-- ottenere la lista dei prodotti venduti in una determinata categoria raggruppati per negozio
SELECT *
FROM (Oggetto JOIN Categoria ON Oggetto.categoria=Categoria.id) JOIN Negozio ON Negozio.id=Oggetto.idNegozio
WHERE idVenditore=ID AND stato=4 AND Categoria.nome=CATEGORIA
GROUP BY idNegozio ASC

-- ottenere la lista dei prodotti venduti ordinati per valutazioni
SELECT *
FROM (Ordine JOIN RecensioneOggetto ON Ordine.idOggetto=RecensioneOggetto.idOggetto) JOIN Negozio ON Ordine.idNegozio=Negozio.id
WHERE idVenditore=ID AND stato=4
ORDER BY AVG(RecensioneOggetto.valutazione) DESC

-- ottenere la lsita dei propri negozi ordinati per recensioni
SELECT *
FROM Negozio
WHERE idVenditore=ID
ORDER BY valutazione DESC

-- ottenere la lista dei proprio prodotti in sconto raggruppati per categoria e negozio
SELECT *
FROM (Oggetto JOIN Categoria ON Oggetto.categoria=Categoria.id) JOIN Negozio ON Negozio.id=Oggetto.idNegozio
WHERE idVenditore=ID AND sconto>0
GROUP BY Categoria.nome ASC, idNegozio ASC

-- ottenere la lista dei proprio prodotti in sconto raggruppati per categoria e negozio ordinati per scadenza più vicina dello sconto
SELECT *
FROM (Oggetto JOIN Categoria ON Oggetto.categoria=Categoria.id) JOIN Negozio ON Negozio.id=Oggetto.idNegozio
WHERE idVenditore=ID AND sconto>0
GROUP BY Categoria.nome ASC, idNegozio ASC
ORDER BY dataFineSconto ASC

-- ottenere la lista delle recensioni ricevute
SELECT *
FROM RecensioneVenditore
WHERE idVenditore=ID

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

-- per gli amministratori:
-- ottenere il numero di richieste di assistenza
SELECT SUM(id)
FROM Assistenza

-- ottenere il numero di richieste di assistenza in sospeso
SELECT SUM(id)
FROM Assistenza
WHERE stato=0

-- ottenere il numero di richieste di assistenza risolte
SELECT SUM(id)
FROM Assistenza
WHERE stato=1

-- aggiungere una soluzione ad una richiesta di assistenze
-- modificare lo stato di una richiesta di assistenza
