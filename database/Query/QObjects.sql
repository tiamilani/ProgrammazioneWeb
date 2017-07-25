-- QUERY OGGETTI
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
