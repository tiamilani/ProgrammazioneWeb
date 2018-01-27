/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.query.objectsMarkets;
/**
 * classe utilizzata per gestire le query di tipo objectMarkets
 * @author Mattia
 */
public class objectMarketsQuery {
    public static String hello() {
        return "Hello from" + objectMarketsQuery.class.toString();

    }



    /**
     * @author Mattia
     * ottenere la lista di oggetti di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopId(int idS, int ritiroInNegozio ) {
        return "SELECT * FROM Oggetto WHERE Oggetto.idNegozio = "+ idS +" AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";
    }


    /**
     * @author Mattia
     * ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificName(int idS, String name, int ritiroInNegozio ) {
        return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND nomeDownCase LIKE '%"+ name + "%' AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";
    }


    /**
     * @author Mattia
     * ottenere la lista di oggetti di una categoria di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategory(int idS, int category, int ritiroInNegozio ) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND Oggetto.categoria="+ category +" AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";
    }


    /**
     * @author Mattia
     * ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificName(int idS, int category, String name, int ritiroInNegozio ) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND Oggetto.categoria="+ category +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";
        }


    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificMinPrice(int idS, double min, int ritiroInNegozio ) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND Oggetto.prezzo >= "+ min +" AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificMaxPrice(int idS, double max, int ritiroInNegozio ) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND Oggetto.prezzo <= "+ max +" AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificBetweenPrice(int idS, double min, double max, int ritiroInNegozio ) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND Oggetto.prezzo BETWEEN "+ min +" AND "+ max +" AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificMinPrice(int idS, int category, double min, int ritiroInNegozio ) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND Oggetto.categoria="+ category +" AND Oggetto.prezzo >= "+ min +" AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificMaxPrice(int idS, int category, double max, int ritiroInNegozio ) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND Oggetto.categoria="+ category +" AND Oggetto.prezzo <= "+ max +" AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificBetweenPrice(int idS, int category, double min, double max, int ritiroInNegozio ) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND Oggetto.categoria="+ category +" AND Oggetto.prezzo BETWEEN "+ min +" AND "+ max +" AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificNameAndSpecificMinPrice(int idS, String name, double min, int ritiroInNegozio ) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND Oggetto.prezzo >= "+ min +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificNameAndSpecificMaxPrice(int idS, String name, double max, int ritiroInNegozio ) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND Oggetto.prezzo <= "+ max +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificNameAndSpecificBetweenPrice(int idS, String name, double min, double max, int ritiroInNegozio ) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND Oggetto.prezzo BETWEEN "+ min +" AND "+ max +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMinPrice(int idS, int category, String name, double min, int ritiroInNegozio ) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND Oggetto.categoria="+ category +" AND Oggetto.prezzo >= "+ min +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMaxPrice(int idS, int category, String name, double max, int ritiroInNegozio ) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND Oggetto.categoria="+ category +" AND Oggetto.prezzo <= "+ max +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificBetweenPrice(int idS, int category, String name, double min, double max, int ritiroInNegozio ) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND Oggetto.categoria="+ category +" AND Oggetto.prezzo BETWEEN "+ min +" AND "+ max +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }


    /**
     * @author Mattia
     * ottenere la lista di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @return lista di negozi
     */
    public static String selectShopWithlatitudeAndLongitude(double raggio, double longitudine, double latitudine) {
            return "SET @latitudine = "+ latitudine +"; "
                    + "SET @longitudine = "+ longitudine +"; "
                    + "SET @raggio = "+ raggio +"; "
                    + "SELECT Negozio.* "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE @raggio >= 111.111 *DEGREES(ACOS(COS(RADIANS(@latitudine))"
                    + "* COS(RADIANS(Indirizzo.latitudine))"
                    + "* COS(RADIANS(@longitudine - Indirizzo.longitudine))"
                    + "+ SIN(RADIANS(@latitudine))"
                    + "* SIN(RADIANS(Indirizzo.latitudine))));";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti in negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU,  int ritiroInNegozio ) {
        return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE "+ raggio +">= 111.111 *DEGREES(ACOS(COS(RADIANS("+ latitudine +"))"
                + "* COS(RADIANS(Indirizzo.latitudine))"
                + "* COS(RADIANS("+ longitudine +" - Indirizzo.longitudine))"
                + "+ SIN(RADIANS("+ latitudine +"))* SIN(RADIANS(Indirizzo.latitudine))));"
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                + "WHERE Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti che contengono una stringa nel nome, nei negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificNameWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU,  String name, int ritiroInNegozio ) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) "
                    + "* COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + "SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                    + "WHERE Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti di una categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU,  int category, int ritiroInNegozio ) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) "
                    + "* COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) + SIN(RADIANS("+ latitudine +")) "
                    + "* SIN(RADIANS(Indirizzo.latitudine)))); "
                    + "SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                    + "WHERE Oggetto.categoria="+ category +" AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti di una categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU,  int category, String name, int ritiroInNegozio ) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + "SELECT Oggetto.* FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                    + "WHERE Oggetto.categoria="+ category +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificMinPriceWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU,  double min, int ritiroInNegozio ) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + "SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                    + "WHERE Oggetto.prezzo >= "+ min +" AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo vdi negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificMaxPriceWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU,  double max, int ritiroInNegozio ) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + "SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                    + "WHERE Oggetto.prezzo <= "+ max +" AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificBetweenPriceWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU,  double min, double max, int ritiroInNegozio ) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + "SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                    + "WHERE Oggetto.prezzo BETWEEN "+ min +" AND "+ max +" AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificMinPriceWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU,  int category, double min, int ritiroInNegozio ) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + "SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                    + "WHERE Oggetto.prezzo >= "+ min +" AND Oggetto.categoria="+ category +" AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificMaxPriceWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU,  int category, double max, int ritiroInNegozio ) {
        return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                + "WHERE Oggetto.prezzo <= "+ max +" AND Oggetto.categoria="+ category +" AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +"; ";

    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificBetweenPriceWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU,  int category, double min, double max, int ritiroInNegozio ) {
        return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                + "WHERE Oggetto.prezzo BETWEEN "+ min +" AND "+ max +" AND Oggetto.categoria="+ category +" AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificNameAndSpecificMinPriceWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU,  String name, double min, int ritiroInNegozio ) {
        return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                + "WHERE Oggetto.prezzo >= "+ min +" and Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificNameAndSpecificMaxPriceWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU,  String name, double max, int ritiroInNegozio ) {
        return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                + "WHERE Oggetto.prezzo <= "+ max +" and Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificNameAndSpecificBetweenPriceWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU,  String name, double min, double max, int ritiroInNegozio ) {
        return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                + "WHERE Oggetto.prezzo BETWEEN "+ min +" AND "+ max +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMinPriceWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU,  int category, String name, double min, int ritiroInNegozio ) {
        return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                + "WHERE Oggetto.prezzo >= "+ min +" AND Oggetto.categoria="+ category +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMaxPriceWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU,  int category, String name, double max, int ritiroInNegozio ) {
        return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                + "WHERE Oggetto.prezzo <= "+ max +" AND Oggetto.categoria="+ category +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificBetweenPriceWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU,  int category, String name, double min, double max, int ritiroInNegozio ) {
        return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                + "WHERE Oggetto.prezzo BETWEEN "+ min +" AND "+ max +" AND Oggetto.categoria="+ category +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @return lista di oggetti
     */
    public static String selectObjectShopIdWithDiscount(int idS) {
            return "SELECT * FROM Oggetto WHERE Oggetto.idNegozio = '"+ idS +"' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificNameWithDiscount(int idS,String name) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti di una categoria di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryWithDiscount(int idS,int category) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND Oggetto.categoria="+ category +" AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameWithDiscount(int idS, int category, String name) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND Oggetto.categoria="+ category +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificMinPriceWithDiscount(int idS, double min) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND Oggetto.prezzo >= "+ min +" AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificMaxPriceWithDiscount(int idS, double max) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND Oggetto.prezzo <= "+ max +" AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificBetweenPriceWithDiscount(int idS, double min, double max) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND Oggetto.prezzo BETWEEN "+ min +" AND "+ max +" AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificMinPriceWithDiscount(int idS,int category, double min) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND Oggetto.categoria="+ category +" AND Oggetto.prezzo >= "+ min +" AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificMaxPriceWithDiscount(int idS, int category, double max) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND Oggetto.categoria="+ category +" AND Oggetto.prezzo <= "+ max +" AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificBetweenPriceWithDiscount(int idS,int category, double min, double max) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND Oggetto.categoria="+ category +" AND Oggetto.prezzo BETWEEN "+ min +" AND "+ max +" AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificNameAndSpecificMinPriceWithDiscount(int idS,String name, double min) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND Oggetto.prezzo >= "+ min +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificNameAndSpecificMaxPriceWithDiscount(int idS, String name, double max) {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio="+ idS +"  AND Oggetto.prezzo <= "+ max +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificNameAndSpecificBetweenPriceWithDiscount(int idS,String name, double min, double max) {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio="+ idS +"  AND Oggetto.prezzo BETWEEN "+ min +" AND "+ max +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMinPriceWithDiscount(int idS, int category, String name, double min) {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio="+ idS +"  AND Oggetto.categoria="+ category +" AND Oggetto.prezzo >= "+ min +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMaxPriceWithDiscount(int idS, int category, String name, double max) {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio="+ idS +"  AND Oggetto.categoria="+ category +" AND Oggetto.prezzo <= "+ max +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificBetweenPriceWithDiscount(int idS, int category, String name, double min, double max) {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio="+ idS +"  AND Oggetto.categoria="+ category +" AND Oggetto.prezzo BETWEEN "+ min +" AND "+ max +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }


    /**
     * @author Mattia
     * ottenere la lista di oggetti in negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @return lista di oggetti
     */
    public static String selectObjectShopWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + "SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                    + "WHERE Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti che contengono una stringa nel nome, nei negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificNameWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU,  String name) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                    + "WHERE Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti di una categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU,  int category) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                    + "WHERE Oggetto.categoria="+ category +" AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti di una categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU,  int category, String name) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                    + "WHERE Oggetto.categoria="+ category +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificMinPriceWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU,  double min) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* "
                    + " FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                    + " WHERE Oggetto.prezzo >= "+ min +" AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo vdi negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificMaxPriceWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU,  double max) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                    + "WHERE Oggetto.prezzo <= "+ max +" AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU,  double min, double max) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                    + "WHERE Oggetto.prezzo BETWEEN "+ min +" AND "+ max +" AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificMinPriceWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU,  int category, double min) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.*"
                    + " FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id)"
                    + " WHERE Oggetto.prezzo >= "+ min +" AND Oggetto.categoria="+ category +" AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificMaxPriceWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU,  int category, double max ) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.*"
                    + " FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                    + "WHERE Oggetto.prezzo <= "+ max +" AND Oggetto.categoria="+ category +" AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU,  int category, double min, double max) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                    + "WHERE Oggetto.prezzo BETWEEN "+ min +" AND "+ max +" AND Oggetto.categoria="+ category +" AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }


    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificNameAndSpecificMinPriceWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU,  String name, double min) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                    + "WHERE Oggetto.prezzo >= "+ min +" and Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificNameAndSpecificMaxPriceWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU,  String name, double max) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                    + "WHERE Oggetto.prezzo <= "+ max +" and Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificNameAndSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU,  String name, double min, double max) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                    + "WHERE Oggetto.prezzo BETWEEN "+ min +" AND "+ max +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMinPriceWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU,  int category, String name, double min) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                    + "WHERE Oggetto.prezzo >= "+ min +" AND Oggetto.categoria="+ category +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMaxPriceWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU,  int category, String name, double max) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                    + "WHERE Oggetto.prezzo <= "+ max +" AND Oggetto.categoria="+ category +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU,  int category, String name, double min, double max) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine)))); "
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                    + "WHERE Oggetto.prezzo BETWEEN "+ min +" AND "+ max +" AND Oggetto.categoria="+ category +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL;";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti di un determinato negozio
     *
     * @param idS variabile contenente l'id dello shop
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdWithDiscountAndPickupInStore(int idS, int ritiroInNegozio ) {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE Oggetto.idNegozio = '"+ idS +"' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio
     *
     * @param idS variabile contenente l'id dello shop
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificNameWithDiscountAndPickupInStore(int idS,String name, int ritiroInNegozio ) {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio="+ idS +"  AND nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti di una categoria di un determinato negozio
     *
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryWithDiscountAndPickupInStore(int idS,int category, int ritiroInNegozio ) {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio="+ idS +"  AND Oggetto.categoria="+ category +" AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio
     *
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameWithDiscountAndPickupInStore(int idS, int category, String name, int ritiroInNegozio ) {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio="+ idS +"  AND Oggetto.categoria="+ category +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio
     *
     * @param idS variabile contenente l'id dello shop
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificMinPriceWithDiscountAndPickupInStore(int idS, double min, int ritiroInNegozio ) {
            return "SELECT * FROM Oggetto WHERE idNegozio="+ idS +"  AND Oggetto.prezzo >= "+ min +" AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio
     *
     * @param idS variabile contenente l'id dello shop
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificMaxPriceWithDiscountAndPickupInStore(int idS, double max, int ritiroInNegozio ) {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio="+ idS +"  AND Oggetto.prezzo <= "+ max +" AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio
     *
     * @param idS variabile contenente l'id dello shop
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificBetweenPriceWithDiscountAndPickupInStore(int idS, double min, double max, int ritiroInNegozio ) {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio="+ idS +"  AND Oggetto.prezzo BETWEEN "+ min +" AND "+ max +" AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio
     *
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificMinPriceWithDiscountAndPickupInStore(int idS,int category, double min, int ritiroInNegozio ) {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio="+ idS +"  AND Oggetto.categoria="+ category +" AND Oggetto.prezzo >= "+ min +" AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio
     *
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificMaxPriceWithDiscountAndPickupInStore(int idS, int category, double max, int ritiroInNegozio ) {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio="+ idS +"  AND Oggetto.categoria="+ category +" AND Oggetto.prezzo <= "+ max +" AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio
     *
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificBetweenPriceWithDiscountAndPickupInStore(int idS,int category, double min, double max, int ritiroInNegozio ) {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio="+ idS +"  AND Oggetto.categoria="+ category +" AND Oggetto.prezzo BETWEEN "+ min +" AND "+ max +" AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio
     *
     * @param idS variabile contenente l'id dello shop
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificNameAndSpecificMinPriceWithDiscountAndPickupInStore(int idS,String name, double min, int ritiroInNegozio ) {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio="+ idS +"  AND Oggetto.prezzo >= "+ min +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
     *
     * @param idS variabile contenente l'id dello shop
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificNameAndSpecificMaxPriceWithDiscountAndPickupInStore(int idS, String name, double max, int ritiroInNegozio ) {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio="+ idS +"  AND Oggetto.prezzo <= "+ max +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
     *
     * @param idS variabile contenente l'id dello shop
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificNameAndSpecificBetweenPriceWithDiscountAndPickupInStore(int idS,String name, double min, double max, int ritiroInNegozio ) {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio="+ idS +"  AND Oggetto.prezzo BETWEEN "+ min +" AND "+ max +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     *
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMinPriceWithDiscountAndPickupInStore(int idS, int category, String name, double min, int ritiroInNegozio ) {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio="+ idS +"  AND Oggetto.categoria="+ category +" AND Oggetto.prezzo >= "+ min +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     *
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMaxPriceWithDiscountAndPickupInStore(int idS, int category, String name, double max, int ritiroInNegozio ) {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio="+ idS +"  AND Oggetto.categoria="+ category +" AND Oggetto.prezzo <= "+ max +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     *
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificBetweenPriceWithDiscountAndPickupInStore(int idS, int category, String name, double min, double max, int ritiroInNegozio ) {
            return "SELECT * "
                    + "FROM Oggetto "
                    + "WHERE idNegozio="+ idS +"  AND Oggetto.categoria="+ category +" AND Oggetto.prezzo BETWEEN "+ min +" AND "+ max +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }


    /**
     * @author Mattia
     * ottenere la lista di oggetti in negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     *
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double latitudine, double longitudine, int idU, int ritiroInNegozio ) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine))));"
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                    + "WHERE Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti che contengono una stringa nel nome, nei negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     *
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificNameWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double latitudine, double longitudine, int idU, String name, int ritiroInNegozio ) {
            return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                    + "SELECT Negozio.id "
                    + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                    + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                    + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                    + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine))));"
                    + " SELECT Oggetto.* "
                    + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                    + "WHERE Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

        }

    /**
     * @author Mattia
     * ottenere la lista di oggetti di una categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     *
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double latitudine, double longitudine, int idU, int category, int ritiroInNegozio ) {
        return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine))));"
                + " SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                + "WHERE Oggetto.categoria="+ category +" AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti di una categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     *
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double latitudine, double longitudine, int idU, int category, String name, int ritiroInNegozio ) {
        return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine))));"
                + " SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                + "WHERE Oggetto.categoria="+ category +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     *
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificMinPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double latitudine, double longitudine, int idU, double min, int ritiroInNegozio ) {
        return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine))));"
                + " SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                + "WHERE Oggetto.prezzo >= "+ min +" AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo vdi negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     *
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificMaxPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double latitudine, double longitudine, int idU, double max, int ritiroInNegozio ) {
        return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine))));"
                + " SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                + "WHERE Oggetto.prezzo <= "+ max +" AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     *
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double latitudine, double longitudine, int idU, double min, double max, int ritiroInNegozio ) {
        return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine))));"
                + " SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                + "WHERE Oggetto.prezzo BETWEEN "+ min +" AND "+ max +" AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     *
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificMinPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double latitudine, double longitudine, int idU, int category, double min, int ritiroInNegozio ) {
        return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine))));"
                + " SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                + "WHERE Oggetto.prezzo >= "+ min +" AND Oggetto.categoria="+ category +" AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     *
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificMaxPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double latitudine, double longitudine, int idU, int category, double max, int ritiroInNegozio ) {
        return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine))));"
                + " SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                + "WHERE Oggetto.prezzo <= "+ max +" AND Oggetto.categoria="+ category +" AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     *
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double latitudine, double longitudine, int idU, int category, double min, double max, int ritiroInNegozio ) {
        return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine))));"
                + " SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                + "WHERE Oggetto.prezzo BETWEEN "+ min +" AND "+ max +" AND Oggetto.categoria="+ category +" AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     *
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificNameAndSpecificMinPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double latitudine, double longitudine, int idU, String name, double min, int ritiroInNegozio ) {
        return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine))));"
                + " SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                + "WHERE Oggetto.prezzo >= "+ min +" and Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     *
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificNameAndSpecificMaxPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double latitudine, double longitudine, int idU, String name, double max, int ritiroInNegozio ) {
        return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine))));"
                + " SELECT Oggetto.* FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) WHERE Oggetto.prezzo <= "+ max +" and Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     *
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificNameAndSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double latitudine, double longitudine, int idU, String name, double min, double max, int ritiroInNegozio ) {
        return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine))));"
                + " SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                + "WHERE Oggetto.prezzo BETWEEN "+ min +" AND "+ max +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     *
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMinPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double latitudine, double longitudine, int idU, int category, String name, double min, int ritiroInNegozio ) {
        return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine))));"
                + " SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                + "WHERE Oggetto.prezzo >= "+ min +" AND Oggetto.categoria="+ category +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     *
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMaxPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double latitudine, double longitudine, int idU, int category, String name, double max, int ritiroInNegozio ) {
        return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine))));"
                + " SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                + "WHERE Oggetto.prezzo <= "+ max +" AND Oggetto.categoria="+ category +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +";";

    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     *
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return lista di oggetti
     */
    public static String selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double latitudine, double longitudine, int idU, int category, String name, double min, double max, int ritiroInNegozio ) {
        return "Create OR REPLACE View NegoziDistanza_"+ idU +" as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE "+ raggio +">= 111.111 * DEGREES(ACOS(COS(RADIANS("+ latitudine +")) "
                + "* COS(RADIANS(Indirizzo.latitudine)) * COS(RADIANS("+ longitudine +" - Indirizzo.longitudine)) "
                + "+ SIN(RADIANS("+ latitudine +")) * SIN(RADIANS(Indirizzo.latitudine))));"
                + " SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziDistanza_"+ idU +" ON (Oggetto.idNegozio = NegoziDistanza_"+ idU +".id) "
                + "WHERE Oggetto.prezzo BETWEEN "+ min +" AND "+ max +" AND Oggetto.categoria="+ category +" AND Oggetto.nomeDownCase LIKE '%"+ name + "%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = "+ ritiroInNegozio +"";
    }
}
