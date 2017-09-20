/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */     
package it.progettoWeb.java.database.query.objectsMarkets;
/**
 *
 * @author mattia
 */
public class objectMarketsQuery {
    public static String hello() {
        return "Hello from" + objectMarketsQuery.class.toString();
        
    }

    // QUERY OGGETTI IN NEGOZI
    // ottenere la lista di oggetti di un determinato negozio

    public static String hello() {
        return "SELECT * FROM Oggetto WHERE Oggetto.idNegozio = 'idN' AND Oggetto.ritiroInNegozio = 1;";
    }
        // ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio

    public static String hello() {
        return "SELECT * FROM Oggetto WHERE idNegozio=ID AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;";
    }
        // ottenere la lista di oggetti di una categoria di un determinato negozio

    public static String hello() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.ritiroInNegozio = 1;";
    }
        // ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio

    public static String hello() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;";
        }
    
        // ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio
    public static String hello() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.prezzo >= MINIMO AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio
    public static String hello() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio
    public static String hello() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio
    public static String hello() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio
    public static String hello() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio
    public static String hello() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio
    public static String hello() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
    public static String hello() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
    public static String hello() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio
    public static String hello() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
    public static String hello() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
    public static String hello() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
    
        // BASSANO DEL GRAPPA
    public static String hello() {
            return "SET @latitudine = 45.7665600; "
                    + "SET @longitudine = 11.727390; "
                    + "SET @raggio = 50; "
                    + "SELECT Negozio.* "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE @raggio >= 111.111 *DEGREES(ACOS(COS(RADIANS(@latitudine))"
                    + "* COS(RADIANS(Indirizzo.latitudine))"
                    + "* COS(RADIANS(@longitudine - Indirizzo.longitudine))"
                    + "+ SIN(RADIANS(@latitudine))"
                    + "* SIN(RADIANS(Indirizzo.latitudine))));";

        }
        // ottenere la lista di oggetti in negozi data una determinata longitudine, latitudine ed un raggio di ricerca
    
        public static String hello() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 *DEGREES(ACOS(COS(RADIANS(45.7665600))"
                    + "* COS(RADIANS(Indirizzo.latitudine))"
                    + "* COS(RADIANS(11.727390 - Indirizzo.longitudine))"
                    + "+ SIN(RADIANS(45.7665600))* SIN(RADIANS(Indirizzo.latitudine))));"
                    + "SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti che contengono una stringa nel nome, nei negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    public static String hello() {
            return "Create OR REPLACE View NegoziNellaDistanza as

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

    ";

        }
        // ottenere la lista di oggetti di una categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.categoria=CATEGORIA AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti di una categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo >= MINIMO AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo vdi negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo >= MINIMO and Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo <= MASSIMO and Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti di un determinato negozio

    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE Oggetto.idNegozio = 'idN' AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio

    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti di una categoria di un determinato negozio

    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio

    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio
    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio
    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio
    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio
    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio
    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio
    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio
    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio
    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
    ";

        }
        // BASSANO DEL GRAPPA
    SET @latitudine = 45.7665600;
    SET @longitudine = 11.727390;
    SET @raggio = 50;

    public static String hello() {
            return "SELECT Negozio.*
    FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
    WHERE @raggio >= 111.111 *
               DEGREES(ACOS(COS(RADIANS(@latitudine))
                     * COS(RADIANS(Indirizzo.latitudine))
                     * COS(RADIANS(@longitudine - Indirizzo.longitudine))
                     + SIN(RADIANS(@latitudine))
                     * SIN(RADIANS(Indirizzo.latitudine))));

    ";

        }
        // ottenere la lista di oggetti in negozi data una determinata longitudine, latitudine ed un raggio di ricerca
    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.`dataFineSconto` IS NOT NULL;


    ";

        }
        // ottenere la lista di oggetti che contengono una stringa nel nome, nei negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti di una categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti di una categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo vdi negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo >= MINIMO and Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo <= MASSIMO and Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti di un determinato negozio

    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE Oggetto.idNegozio = 'idN' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio

    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti di una categoria di un determinato negozio

    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio

    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio
    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio
    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio
    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio
    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio
    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio
    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio
    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio
    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
    public static String hello() {
            return "SELECT *
    FROM Oggetto
    WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
    ";

        }
        // BASSANO DEL GRAPPA
    SET @latitudine = 45.7665600;
    SET @longitudine = 11.727390;
    SET @raggio = 50;

    public static String hello() {
            return "SELECT Negozio.*
    FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
    WHERE @raggio >= 111.111 *
               DEGREES(ACOS(COS(RADIANS(@latitudine))
                     * COS(RADIANS(Indirizzo.latitudine))
                     * COS(RADIANS(@longitudine - Indirizzo.longitudine))
                     + SIN(RADIANS(@latitudine))
                     * SIN(RADIANS(Indirizzo.latitudine))));

    ";

        }
        // ottenere la lista di oggetti in negozi data una determinata longitudine, latitudine ed un raggio di ricerca
    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;


    ";

        }
        // ottenere la lista di oggetti che contengono una stringa nel nome, nei negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti di una categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti di una categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo vdi negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo >= MINIMO and Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo <= MASSIMO and Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

    ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    Create OR REPLACE View NegoziNellaDistanza as

        public static String hello() {
            return "SELECT Negozio.id
            FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)
            WHERE 50>= 111.111 *
                               DEGREES(ACOS(COS(RADIANS(45.7665600))
                                             * COS(RADIANS(Indirizzo.latitudine))
                                             * COS(RADIANS(11.727390 - Indirizzo.longitudine))
                                             + SIN(RADIANS(45.7665600))
                                             * SIN(RADIANS(Indirizzo.latitudine))));

    public static String hello() {
            return "SELECT Oggetto.*
    FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)
    WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;

}
