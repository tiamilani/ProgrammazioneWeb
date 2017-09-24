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

    public static String selectObjectShopId() {
        return "SELECT * FROM Oggetto WHERE Oggetto.idNegozio = 'idN' AND Oggetto.ritiroInNegozio = 1;";
    }
        // ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio

    public static String selectObjectShopIdSpecificName() {
        return "SELECT * FROM Oggetto WHERE idNegozio=ID AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;";
    }
        // ottenere la lista di oggetti di una categoria di un determinato negozio

    public static String selectObjectShopIdSpecificCategory() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.ritiroInNegozio = 1;";
    }
        // ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio

    public static String selectObjectShopIdSpecificCategoryAndSpecificName() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;";
        }
    
        // ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio
    public static String selectObjectShopIdSpecificMinPrice() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.prezzo >= MINIMO AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio
    public static String selectObjectShopIdSpecificMaxPrice() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio
    public static String selectObjectShopIdSpecificBetweenPrice() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio
    public static String selectObjectShopIdSpecificCategoryAndSpecificMinPrice() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio
    public static String selectObjectShopIdSpecificCategoryAndSpecificMaxPrice() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio
    public static String selectObjectShopIdSpecificCategoryAndSpecificBetweenPrice() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio
    public static String selectObjectShopIdSpecificNameAndSpecificMinPrice() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
    public static String selectObjectShopIdSpecificNameAndSpecificMaxPrice() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
    public static String selectObjectShopIdSpecificNameAndSpecificBetweenPrice() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMinPrice() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMaxPrice() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificBetweenPrice() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;";

        }
    
        // ottenere la lista di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
    
        // BASSANO DEL GRAPPA
    public static String selectShopWithlatitudeAndLongitude() {
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
    
        public static String selectObjectShopWithLatitudeAndLongitude() {
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

    public static String selectObjectShopIdSpecificNameWithLatitudeAndLongitude() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) "
                    + "* COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + "SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti di una categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    public static String selectObjectShopIdSpecificCategoryWithLatitudeAndLongitude() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) "
                    + "* COS(RADIANS(11.727390 - Indirizzo.longitudine)) + SIN(RADIANS(45.7665600)) "
                    + "* SIN(RADIANS(Indirizzo.latitudine)))); "
                    + "SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.categoria=CATEGORIA AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti di una categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    public static String selectObjectShopIdSpecificCategoryAndSpecificNameWithLatitudeAndLongitude() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + "SELECT Oggetto.* FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) WHERE Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    public static String selectObjectShopIdSpecificMinPriceWithLatitudeAndLongitude() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + "SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo >= MINIMO AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo vdi negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    public static String selectObjectShopIdSpecificMaxPriceWithLatitudeAndLongitude() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + "SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    public static String selectObjectShopIdSpecificBetweenPriceWithLatitudeAndLongitude() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + "SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    public static String selectObjectShopIdSpecificCategoryAndSpecificMinPriceWithLatitudeAndLongitude() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + "SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

        public static String selectObjectShopIdSpecificCategoryAndSpecificMaxPriceWithLatitudeAndLongitude() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + "SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.ritiroInNegozio = 1; ";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

        public static String selectObjectShopIdSpecificCategoryAndSpecificBetweenPriceWithLatitudeAndLongitude() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + "SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

        public static String selectObjectShopIdSpecificNameAndSpecificMinPriceWithLatitudeAndLongitude() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + "SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo >= MINIMO and Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

        public static String selectObjectShopIdSpecificNameAndSpecificMaxPriceWithLatitudeAndLongitude() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + "SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo <= MASSIMO and Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

        public static String selectObjectShopIdSpecificNameAndSpecificBetweenPriceWithLatitudeAndLongitude() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + "SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

        public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMinPriceWithLatitudeAndLongitude() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + "SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

        public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMaxPriceWithLatitudeAndLongitude() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + "SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

        public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificBetweenPriceWithLatitudeAndLongitude() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + "SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti di un determinato negozio

    public static String selectObjectShopIdWithDiscount() {
            return "SELECT * FROM Oggetto WHERE Oggetto.idNegozio = 'idN' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio

    public static String selectObjectShopIdSpecificNameWithDiscount() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti di una categoria di un determinato negozio

    public static String selectObjectShopIdSpecificCategoryWithDiscount() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio

    public static String selectObjectShopIdSpecificCategoryAndSpecificNameWithDiscount() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio
    public static String selectObjectShopIdSpecificMinPriceWithDiscount() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio
    public static String selectObjectShopIdSpecificMaxPriceWithDiscount() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio
    public static String selectObjectShopIdSpecificBetweenPriceWithDiscount() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio
    public static String selectObjectShopIdSpecificCategoryAndSpecificMinPriceWithDiscount() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio
    public static String selectObjectShopIdSpecificCategoryAndSpecificMaxPriceWithDiscount() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio
    public static String selectObjectShopIdSpecificCategoryAndSpecificBetweenPriceWithDiscount() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio
    public static String selectObjectShopIdSpecificNameAndSpecificMinPriceWithDiscount() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
    public static String selectObjectShopIdSpecificNameAndSpecificMaxPriceWithDiscount() {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio=ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
    public static String selectObjectShopIdSpecificNameAndSpecificBetweenPriceWithDiscount() {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio=ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMinPriceWithDiscount() {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMaxPriceWithDiscount() {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificBetweenPriceWithDiscount() {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
    
        // ottenere la lista di oggetti in negozi data una determinata longitudine, latitudine ed un raggio di ricerca
    public static String selectObjectShopWithLatitudeAndLongitudeWithDiscount() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + "SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti che contengono una stringa nel nome, nei negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    public static String selectObjectShopIdSpecificNameWithLatitudeAndLongitudeWithDiscount() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti di una categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    public static String selectObjectShopIdSpecificCategoryWithLatitudeAndLongitudeWithDiscount() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti di una categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    public static String selectObjectShopIdSpecificCategoryAndSpecificNameWithLatitudeAndLongitudeWithDiscount() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    public static String selectObjectShopIdSpecificMinPriceWithLatitudeAndLongitudeWithDiscount() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* "
                    + " FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + " WHERE Oggetto.prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo vdi negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    public static String selectObjectShopIdSpecificMaxPriceWithLatitudeAndLongitudeWithDiscount() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    public static String selectObjectShopIdSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscount() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    public static String selectObjectShopIdSpecificCategoryAndSpecificMinPriceWithLatitudeAndLongitudeWithDiscount() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.*"
                    + " FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id)"
                    + " WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    public static String selectObjectShopIdSpecificCategoryAndSpecificMaxPriceWithLatitudeAndLongitudeWithDiscount() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.*"
                    + " FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    public static String selectObjectShopIdSpecificCategoryAndSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscount() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    public static String selectObjectShopIdSpecificNameAndSpecificMinPriceWithLatitudeAndLongitudeWithDiscount() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo >= MINIMO and Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    public static String selectObjectShopIdSpecificNameAndSpecificMaxPriceWithLatitudeAndLongitudeWithDiscount() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo <= MASSIMO and Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    public static String selectObjectShopIdSpecificNameAndSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscount() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMinPriceWithLatitudeAndLongitudeWithDiscount() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMaxPriceWithLatitudeAndLongitudeWithDiscount() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscount() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti di un determinato negozio

    public static String selectObjectShopIdWithDiscountAndPickupInStore() {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE Oggetto.idNegozio = 'idN' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio

    public static String selectObjectShopIdSpecificNameWithDiscountAndPickupInStore() {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio=ID AND nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti di una categoria di un determinato negozio

    public static String selectObjectShopIdSpecificCategoryWithDiscountAndPickupInStore() {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio

    public static String selectObjectShopIdSpecificCategoryAndSpecificNameWithDiscountAndPickupInStore() {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio
    public static String selectObjectShopIdSpecificMinPriceWithDiscountAndPickupInStore() {
            return "SELECT * FROM Oggetto WHERE idNegozio=ID AND Oggetto.prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio
    public static String selectObjectShopIdSpecificMaxPriceWithDiscountAndPickupInStore() {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio=ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio
    public static String selectObjectShopIdSpecificBetweenPriceWithDiscountAndPickupInStore() {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio=ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio
    public static String selectObjectShopIdSpecificCategoryAndSpecificMinPriceWithDiscountAndPickupInStore() {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio
    public static String selectObjectShopIdSpecificCategoryAndSpecificMaxPriceWithDiscountAndPickupInStore() {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio
    public static String selectObjectShopIdSpecificCategoryAndSpecificBetweenPriceWithDiscountAndPickupInStore() {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio
    public static String selectObjectShopIdSpecificNameAndSpecificMinPriceWithDiscountAndPickupInStore() {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio=ID AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
    public static String selectObjectShopIdSpecificNameAndSpecificMaxPriceWithDiscountAndPickupInStore() {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio=ID AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
    public static String selectObjectShopIdSpecificNameAndSpecificBetweenPriceWithDiscountAndPickupInStore() {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio=ID AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMinPriceWithDiscountAndPickupInStore() {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo >= MINIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMaxPriceWithDiscountAndPickupInStore() {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo <= MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificBetweenPriceWithDiscountAndPickupInStore() {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio=ID AND Oggetto.categoria=CATEGORIA AND Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
    
        // ottenere la lista di oggetti in negozi data una determinata longitudine, latitudine ed un raggio di ricerca
    public static String selectObjectShopWithLatitudeAndLongitudeWithDiscountAndPickupInStore() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine))));"
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti che contengono una stringa nel nome, nei negozi data una determinata longitudine, latitudine ed un raggio di ricerca

    public static String selectObjectShopIdSpecificNameWithLatitudeAndLongitudeWithDiscountAndPickupInStore() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine))));"
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti di una categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

        public static String selectObjectShopIdSpecificCategoryWithLatitudeAndLongitudeWithDiscountAndPickupInStore() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine))));"
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti di una categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

        public static String selectObjectShopIdSpecificCategoryAndSpecificNameWithLatitudeAndLongitudeWithDiscountAndPickupInStore() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine))));"
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

        public static String selectObjectShopIdSpecificMinPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine))));"
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo >= MINIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo vdi negozi data una determinata longitudine, latitudine ed un raggio di ricerca

        public static String selectObjectShopIdSpecificMaxPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine))));"
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

        public static String selectObjectShopIdSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine))));"
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

        public static String selectObjectShopIdSpecificCategoryAndSpecificMinPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine))));"
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

        public static String selectObjectShopIdSpecificCategoryAndSpecificMaxPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine))));"
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

        public static String selectObjectShopIdSpecificCategoryAndSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine))));"
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

        public static String selectObjectShopIdSpecificNameAndSpecificMinPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine))));"
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo >= MINIMO and Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

        public static String selectObjectShopIdSpecificNameAndSpecificMaxPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine))));"
                    + " SELECT Oggetto.* FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) WHERE Oggetto.prezzo <= MASSIMO and Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

        public static String selectObjectShopIdSpecificNameAndSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine))));"
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

        public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMinPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine))));"
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo >= MINIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

        public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMaxPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine))));"
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo <= MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1;";

        }
        // ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca

        public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore() {
            return "Create OR REPLACE View NegoziNellaDistanza as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE 50>= 111.111 * DEGREES(ACOS(COS(RADIANS(45.7665600)) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS(11.727390 - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS(45.7665600)) * SIN(RADIANS(Indirizzo.latitudine))));"
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                    + "WHERE Oggetto.prezzo BETWEEN MINIMO AND MASSIMO AND Oggetto.categoria=CATEGORIA AND Oggetto.nomeDownCase LIKE '%STRINGA%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = 1";
        }
}
