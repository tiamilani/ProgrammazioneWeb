-- QUERY OGGETTI DI VENDITORI

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
