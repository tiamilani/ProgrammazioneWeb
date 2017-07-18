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
FROM Negozio INNER JOIN imageNegozio ON (Negozio.idVenditore = 'idV' AND Negozio.id = imageNegozio.idN);
GROUP BY Negozio.id -- da errore qui

-- ottenere la lista degli ordini effettuati e portati a termine

SELECT
FROM
WHERE

-- ottenere la lista degli ordini effettuati ed in lavorazione

SELECT
FROM
WHERE

-- ottenere la lista degli ordini effettuati ma non ancora in lavorazione

SELECT
FROM
WHERE

-- ottenere la lsita dei prodotti all'interno dell'ordine della lista dei desideri

SELECT
FROM
WHERE

-- ottenere la lista dei negozi da cui ho acquistato

SELECT
FROM
WHERE

-- ottenere il carrello

SELECT
FROM
WHERE

-- ottenere le proprie recensioni

SELECT
FROM
WHERE

-- ottenere le proprie recensioni di oggetti

SELECT
FROM
WHERE

-- ottenere le proprie recensioni di negozi

SELECT
FROM
WHERE

-- ottenere le proprie recensioni di venditori

SELECT
FROM
WHERE

-- ottenere le risposte alle proprie recensioni

SELECT
FROM
WHERE

-- ottenere le risposte alle proprie recensioni di oggetti

SELECT
FROM
WHERE

-- ottenere le risposte alle proprie recensioni di negozi

SELECT
FROM
WHERE

-- ottenere le risposte alle proprie recensioni di venditori

SELECT
FROM
WHERE

-- ottenere tutte le recensioni di un oggetto 

SELECT
FROM
WHERE

-- ottenere tutte le recensioni di un negozio

SELECT
FROM
WHERE

-- ottenere tutte le recensioni di un venditore

SELECT
FROM
WHERE

-- non specificato se con ritiro a mano o scontati:
-- ottenere la lista di oggetti che contengono una stringa nel nome
-- ottenere la lista di oggetti di una categoria
-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome
-- ottenere la lista di oggetti con un certo prezzo minimo
-- ottenere la lista di oggetti con un certo prezzo massimo
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria
-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome
-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome
-- ottenere la lista di oggetti di un determinato negozio
-- ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio
-- ottenere la lista di oggetti di una categoria di un determinato negozio
-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio
-- ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio
-- ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio
-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio
-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
-- ottenere la lista di negozi data una determinata longitudine, latitudine ed un raggio di ricerca 
-- ottenere la lista di oggetti in negozi data una determinata longitudine, latitudine ed un raggio di ricerca
-- ottenere la lista di oggetti che contengono una stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca 
-- ottenere la lista di oggetti di una categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca 
-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca 
-- ottenere la lista di oggetti con un certo prezzo minimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca 
-- ottenere la lista di oggetti con un certo prezzo massimo vdi negozi data una determinata longitudine, latitudine ed un raggio di ricerca 
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca 
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca 
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca 
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca 
-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca 
-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca 
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca 
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca 
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca 
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca 
-- ottenere la lista di negozi di un determinato venditore
-- ottenere la lista di oggetti di negozi di un determinato venditore
-- ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio di un determinato venditore
-- ottenere la lista di oggetti di una categoria di un determinato negozio di un determinato venditore
-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore
-- ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio di un determinato venditore
-- ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio di un determinato venditore
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio di un determinato venditore
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio di un determinato venditore
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio di un determinato venditore
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio di un determinato venditore
-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio di un determinato venditore
-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio di un determinato venditore
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio di un determinato venditore
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore
-- ottenere la lista di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
-- ottenere la lista di oggetti di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
-- ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
-- ottenere la lista di oggetti di una categoria di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
-- ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
-- ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca

-- specificato ritiro a mano ma non se scontati
-- ottenere la lista di oggetti che contengono una stringa nel nome e con ritiro a mano
-- ottenere la lista di oggetti di una categoria e con ritiro a mano
-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo massimo e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome e con ritiro a mano
-- ottenere la lista di oggetti di un determinato negozio e con ritiro a mano
-- ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio e con ritiro a mano
-- ottenere la lista di oggetti di una categoria di un determinato negozio e con ritiro a mano
-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio e con ritiro a mano
-- ottenere la lista di negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e con ritiro a mano
-- ottenere la lista di oggetti in negozi data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano
-- ottenere la lista di oggetti che contengono una stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano 
-- ottenere la lista di oggetti di una categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e con ritiro a mano
-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano 
-- ottenere la lista di oggetti con un certo prezzo minimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo massimo vdi negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano 
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano 
-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano 
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano 
-- ottenere la lista di negozi di un determinato venditore e con ritiro a mano
-- ottenere la lista di oggetti di negozi di un determinato venditore e con ritiro a mano
-- ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio di un determinato venditore e con ritiro a mano
-- ottenere la lista di oggetti di una categoria di un determinato negozio di un determinato venditore e con ritiro a mano
-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio di un determinato venditore e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio di un determinato venditore e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio di un determinato venditore e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio di un determinato venditore e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio di un determinato venditore e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio di un determinato venditore e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio di un determinato venditore e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio di un determinato venditore e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio di un determinato venditore e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore e con ritiro a mano
-- ottenere la lista di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano
-- ottenere la lista di oggetti di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano
-- ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano
-- ottenere la lista di oggetti di una categoria di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano
-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano

-- specificato scontati ma non ritiro a mano
-- ottenere la lista di oggetti che contengono una stringa nel nome e scontato
-- ottenere la lista di oggetti di una categoria e scontato
-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome e scontato
-- ottenere la lista di oggetti di un determinato negozio e scontato
-- ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio e scontato
-- ottenere la lista di oggetti di una categoria di un determinato negozio e scontato
-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio e scontato
-- ottenere la lista di negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e scontato
-- ottenere la lista di oggetti in negozi data una determinata longitudine, latitudine ed un raggio di ricerca e scontato
-- ottenere la lista di oggetti che contengono una stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca e scontato 
-- ottenere la lista di oggetti di una categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e scontato
-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca e scontato 
-- ottenere la lista di oggetti con un certo prezzo minimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo vdi negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca e scontato 
-- ottenere la lista di negozi di un determinato venditore  e scontato
-- ottenere la lista di oggetti di negozi di un determinato venditore e scontato
-- ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio di un determinato venditore e scontato
-- ottenere la lista di oggetti di una categoria di un determinato negozio di un determinato venditore e scontato
-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio di un determinato venditore e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio di un determinato venditore e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio di un determinato venditore e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio di un determinato venditore e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio di un determinato venditore e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio di un determinato venditore e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio di un determinato venditore e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio di un determinato venditore e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio di un determinato venditore e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore e scontato
-- ottenere la lista di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e scontato
-- ottenere la lista di oggetti di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e scontato
-- ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e scontato
-- ottenere la lista di oggetti di una categoria di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e scontato
-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e scontato

-- specificato ritiro a mano e scontati
-- ottenere la lista di oggetti che contengono una stringa nel nome e con ritiro a mano e scontato
-- ottenere la lista di oggetti di una categoria e con ritiro a mano e scontato
-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome e con ritiro a mano e scontato
-- ottenere la lista di oggetti di un determinato negozio e con ritiro a mano e scontato
-- ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio e con ritiro a mano e scontato
-- ottenere la lista di oggetti di una categoria di un determinato negozio e con ritiro a mano e scontato
-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio e con ritiro a mano  e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio e con ritiro a mano e scontato
-- ottenere la lista di negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e con ritiro a mano e scontato
-- ottenere la lista di oggetti in negozi data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato
-- ottenere la lista di oggetti che contengono una stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato 
-- ottenere la lista di oggetti di una categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e con ritiro a mano e scontato
-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato 
-- ottenere la lista di oggetti con un certo prezzo minimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo vdi negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato 
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato 
-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato 
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca  e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato 
-- ottenere la lista di negozi di un determinato venditore e con ritiro a mano e scontato
-- ottenere la lista di oggetti di negozi di un determinato venditore e con ritiro a mano e scontato
-- ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio di un determinato venditore e con ritiro a mano e scontato
-- ottenere la lista di oggetti di una categoria di un determinato negozio di un determinato venditore e con ritiro a mano e scontato
-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio di un determinato venditore e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio di un determinato venditore e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio di un determinato venditore e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio di un determinato venditore e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio di un determinato venditore e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio di un determinato venditore e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio di un determinato venditore e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio di un determinato venditore e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio di un determinato venditore e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore e con ritiro a mano e scontato
-- ottenere la lista di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato
-- ottenere la lista di oggetti di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato
-- ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato
-- ottenere la lista di oggetti di una categoria di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato
-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato
-- ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca e con ritiro a mano e scontato

-- ottenere la lista delle richieste di assistenza
-- ottenere i dettagli di una determinata richiesta di assistenza
-- ottenere i dati di un venditore
-- ottenere la lista dei prodotti nella stessa fascia di prezzo e categoria di quelli già acquistati
-- ottenere la lista di assistenze che hanno a che fare con un ordine
-- ottenere la lista delle proprie recensioni dalla più utile
-- ottenere la lista delle immagini di un oggetto
-- ottenere solo la prima immagine di un oggetto
-- ottenre il totale dagli ordini nel carrello
-- aggiungere un valore al subtotale del carrello
-- diminuire un valore dal subtotale del carrello
-- aggiungi un oggetto algli ordini nel carrello
-- rimuovere un ordine (oggetto) dal carrello
-- cambia lo stato degli ordini da nel carrello a pagati
-- aggiungi un oggetto agli ordini nella lista dei desideri
-- cambia lo stato di un ordine da nel carrello a lista dei desideri
-- cambia lo stato di un ordine da nella lista dei desideri al carrello (Se c'è già lo stesso oggetto anche nel carrello semplicmente ne aumento la quantità)
-- aggiungi una recensione ad un determinato venditore
-- aggiungi una recensione ad un determinato negozio
-- aggiungi una recensione ad un determinato oggetto
-- ottenere un boolean se si ha recensito oppure no un venditore
-- ottenere un boolean se si ha recensito oppure no un negozio
-- ottenere un boolean se si ha recensito oppure no un oggetto
-- aggiungere un proprio indirizzo
-- eliminaree un proprio indirizzo


-- per i venditori:
-- ottenere la lista dei propri negozi
-- ottenere la lista degli ordini ricevuti
-- ottenere la lista degli ordini ricevuti dal più nuovo al più vecchio
-- ottenere la lista degli ordini non ancora in carico
-- ottenere la lista degli ordini in lavorazione
-- ottenere la lista degli ordini già spediti
-- ottenere la lista degli ordini conclusi
-- ottenere la lista degli ordini ricevuti in un determinato giorno
-- ottenere la lista degli ordini ricevuti nella settimana corrente
-- ottenere la lista degli ordini ricevuti nel mese corrente
-- ottenere la lista degli ordini ricevuti nell'anno corrente
-- ottenere la lista dei propri negozi con anche il numero di vendite
-- ottenere la lista dei propri negozi ordinati per vendite maggiori
-- ottenere la lista dei propri negozi per vendite minori
-- ottenere la lista dei propri negozi con vendite inferiori ad un certo valore
-- ottenere la lista dei propri negozi con vendite superiori ad un certo valore
-- ottenere la lista dei negozi che vendono prodotti di una certa categoria
-- ottenere la lista dei negozi che vendono prodotti di una certa categoria ordinate da quello con più vendite
-- ottenere la lista dei negozi che vendono prodotti di una certa categoria ordinate da quello con meno vendite
-- ottenere la lista dei propri negozi ordinati per data di apertura
-- ottenere la lista dei propri negozi ordinati per fatturato
-- ottenere i dati di un negozio
-- ottenere i dati delle vendite di un determinato negozio
-- ottenere i dati di vendita di un determinato negozio in una determinata categoria
-- ottenere le richieste di assistenza in cui si è stati citati
-- ottenere la lista dei prodotti venduti raggruppati per categoria e negozio
-- ottenere la lista dei prodotti venduti in una determinata categoria raggruppati per negozio
-- ottenere la lista dei prodotti venduti ordinati per valutazioni
-- ottenere la lsita dei propri negozi ordinati per recensioni
-- ottenere la lista dei proprio prodotti in sconto raggruppati per categoria e negozio
-- ottenere la lista dei proprio prodotti in sconto raggruppati per categoria e negozio ordinati per scadenza più vicina dello sconto
-- ottenere la lista delle recensioni ricevute
-- aggiungere un proprio negozio
-- chiudere un proprio negozio (rimuoverlo)
-- modificare lo stato di un ordine da pagato a in lavorazione
-- modificare lo stato di un ordine da lavorazione a spedito
-- aggiungere ad un ordine spedito il codice di tracking
-- aggiungere un prodotto ad un proprio negozio
-- rimuovere un oggetto da un proprio negozio
-- modificare il prezzo di un oggetto di un proprio negozio
-- aggiungere uno sconto ad un proprio oggetto
-- rimuovere uno sconto dagli oggetti in sconto
-- aggiungere una foto ad un prodotto
-- aggiungere una foto ad un negozio
-- rimuovere una foto di un prodotto
-- rimuovere una foto di un negozio

-- per gli amministratori:
-- ottenere il numero di richieste di assistenza
-- ottenere il numero di richieste di assistenza in sospeso
-- ottenere il numero di richieste di assistenza risolte
-- aggiungere una soluzione ad una richiesta di assistenze
-- modificare lo stato di una richiesta di assistenza
