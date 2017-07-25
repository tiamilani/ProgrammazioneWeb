-- QUERY GENERALI

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

SELECT Indirizzo.*
from Indirizzo INNER JOIN IndirizzoUtente ON (Indirizzo.idI = IndirizzoUtente.idI AND IndirizzoUtente.idU = 1);

-- ottenere i dati di un utente e l'indirizzo avendo email e password

SELECT Utente.*, Indirizzo.*
from Utente INNER JOIN IndirizzoUtente INNER JOIN Indirizzo
	ON (Utente.mail = 'abc@def.ghi' AND Utente.password = 'ciao' AND Indirizzo.idI = IndirizzoUtente.idI AND IndirizzoUtente.idU = Utente.id);

-- ottenere i dati di un utente e l'indirizzo avendo l'id

SELECT Utente.*, Indirizzo.*
from Utente INNER JOIN IndirizzoUtente INNER JOIN Indirizzo
	ON (Utente.id = id AND Indirizzo.idI = IndirizzoUtente.idI AND IndirizzoUtente.idU = Utente.id);

-- ottenere l'immagine di un utente

SELECT src
FROM imageUtente
WHERE idU = 'idU';

-- ottener i dati di un utente, l'indirizzo ed l'immagine a cui fa riferimento

SELECT Utente.*, Indirizzo.*, imageUtente.src
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
