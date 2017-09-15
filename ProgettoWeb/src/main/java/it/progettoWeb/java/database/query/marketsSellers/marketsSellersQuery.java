/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.query.marketsSellers;
/**
 *
 * @author mattia
 */
public class marketsSellersQuery {
    /**
     * @author Damiano
     * Metodo di debug che ritorna il path della classe corrente
     * @return La stringa corrispondente al path della classe
     */
    public static String hello() {
        return "Hello from" + marketsSellersQuery.class.toString();
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista di negozi di un determinato venditore
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando i negozi
     * @return La stringa corrispondete alla query
     */
    public static String shopBySeller(int id){
        return "SELECT * FROM Negozio WHERE idVenditore = " + id + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti presenti nei negozi di un determinato venditore
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti presenti nei suoi negozi
     * @return La stringa corrispondete alla query
     */
    public static String itemsInShopBySeller(int id){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + " AND Oggetto.ritiroInNegozio = 1;";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti che contengono una determinata stringa nel nome
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti presenti nei suoi negozi
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query
     */
    public static String ItemsWithStringInShopBySeller(int id, String pattern){
        return "SELECT Oggetto.*"
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)"
                + "WHERE Negozio.idVenditore = " + id + " AND Oggetto.nomeDownCase LIKE '%" + pattern + "%';";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti che contengono una determinata stringa nel nome con una
     * determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti presenti nei suoi negozi
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query
     */
    public static String ItemsWithStringInShopBySellerSpecific(int id, String pattern, int ritiro){
        return "SELECT Oggetto.*"
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)"
                + "WHERE Negozio.idVenditore = " + id + " AND Oggetto.nomeDownCase LIKE '%" + pattern + "%'"
                + "AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista di oggetti presenti in una determinata categoria
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @return La stringa corrispondete alla query
     */
    public static String ItemsInCategoryInShopBySeller(int id, int categoria){
        return "SELECT Oggetto.*"
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)"
                + "WHERE Negozio.idVenditore = " + id + " AND Oggetto.categoria = " + categoria + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista di oggetti presenti in una determinata categoria con una 
     * determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondente alla query
     */
    public static String ItemsInCategoryInShopBySellerSpecific(int id, int categoria, int ritiro){
        return "SELECT Oggetto.*"
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)"
                + "WHERE Negozio.idVenditore = " + id + " AND Oggetto.categoria = " + categoria + ""
                + "AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /*
    public static String ItemsInCategoryWithStringInShopBySeller(){
        
    }
    */
    
}
/*
-- ottenere la lista di oggetti di una categoria con una certa stringa nel nome,nei negozi di un determinato venditore
SELECT Oggetto.*
FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)
WHERE Negozio.idVenditore = ID AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

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
*/
