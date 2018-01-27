/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.query.objects;
/**
 *
 * @author mattia
 */
public class objectsQuery {
    public static String hello() {
        return "Hello from" + objectsQuery.class.toString();
    }
    
    /*---LAST UPDATE - 2017-09-20---*/
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti che contengono una stringa nel nome
     * @param nomeDownCase
     * @return String: elenco oggetti
     */
    public static String selectObjectById(String id)
    {
        return "SELECT * FROM oggetto WHERE id = '" + id + "';";
    }
    
    /**
     * @author andrea
     * Ottenere Recensione, utente
     * @param idO Una stringa che rappresenta l'identificativo dell'oggetto preso in considerazione
     * @return elenco recensioni e utente
     */
    public static String selectReviewUserByObject(String idO)
    {
        return "SELECT recensioneOggetto.*, utente.* FROM "
                + "recensioneOggetto JOIN utente ON recensioneOggetto.idUtente=utente.id "
                + "WHERE recensioneOggetto.idOggetto='" + idO + "';";
    }
    
    /**
     * @author andrea
     * Ottenere Immagini
     * @param idR Un intero che rappresenta l'identificativo della recensione presa in considerazione
     * @return elenco immagini
     */
    public static String selectImagesByObject(int idR)
    {
        return "SELECT imageRecensione.* FROM imageRecensione JOIN recensioneOggetto ON "
                + "imageRecensione.idR=recensioneOggetto.id WHERE "
                + "recensioneOggetto.id=" + idR + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti che contengono una stringa nel nome
     * @param nomeDownCase
     * @return String: elenco oggetti
     */
    public static String selectObjectByName(String nomeDownCase)
    {
        return "SELECT * FROM oggetto WHERE nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti di una categoria
     * @param idcategoria
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategory(int idcategoria)
    {
        return "SELECT * FROM oggetto WHERE categoria = " + idcategoria + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti di una categoria con una certa stringa nel nome
     * @param idcategoria
     * @param nomeDownCase
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndName(int idcategoria, String nomeDownCase)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo
     * @param prezzoMin
     * @return String: elenco oggetti
     */
    public static String selectObjectHigherThanPrice(double prezzoMin)
    {
        return "SELECT * FROM oggetto WHERE prezzo >= " + prezzoMin + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo massimo
     * @param prezzoMax
     * @return String: elenco oggetti
     */
    public static String selectObjectLowerThanPrice(double prezzoMax)
    {
        return "SELECT * FROM oggetto WHERE prezzo <= " + prezzoMax + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo
     * @param prezzoMin
     * @param prezzoMax
     * @return String: elenco oggetti
     */
    public static String selectObjectBetweenPrices(double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM oggetto WHERE prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria
     * @param idcategoria
     * @param prezzoMin
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndHigherThanPrice(int idcategoria, double prezzoMin)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND prezzo >= " + prezzoMin + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria
     * @param idcategoria
     * @param prezzoMax
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndLowerThanPrice(int idcategoria, double prezzoMax)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND prezzo <= " + prezzoMax + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria
     * @param idcategoria
     * @param prezzoMin
     * @param prezzoMax
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndBetweenPrices(int idcategoria, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome
     * @param nomeDownCase
     * @param prezzoMin
     * @return String: elenco oggetti
     */
    public static String selectObjectByNameAndHigherThanPrice(String nomeDownCase, double prezzoMin)
    {
        return "SELECT * FROM oggetto WHERE prezzo >= " + prezzoMin + " AND nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome
     * @param nomeDownCase
     * @param prezzoMax
     * @return String: elenco oggetti
     */
    public static String selectObjectByNameAndLowerThanPrice(String nomeDownCase, double prezzoMax)
    {
        return "SELECT * FROM oggetto WHERE prezzo <= " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome
     * @param nomeDownCase
     * @param prezzoMin
     * @param prezzoMax
     * @return String: elenco oggetti
     */
    public static String selectObjectByNameAndBetweenPrices(String nomeDownCase, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM oggetto WHERE prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome
     * @param idcategoria
     * @param nomeDownCase
     * @param prezzoMin
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndNameAndHigherThanPrice(int idcategoria, String nomeDownCase, double prezzoMin)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND prezzo >= " + prezzoMin + " AND nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome
     * @param idcategoria
     * @param nomeDownCase
     * @param prezzoMax
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndNameAndLowerThanPrice(int idcategoria, String nomeDownCase, double prezzoMax)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND prezzo <= " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome
     * @param idcategoria
     * @param nomeDownCase
     * @param prezzoMin
     * @param prezzoMax
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndNameAndBetweenPrices(int idcategoria, String nomeDownCase, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti di un determinato negozio
     * @param idNegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByShop(int idNegozio)
    {
        return "SELECT * FROM oggetto WHERE oggetto.idNegozio = " + idNegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio
     * @param idNegozio
     * @param nomeDownCase
     * @return String: elenco oggetti
     */
    public static String selectObjectByShopAndName(int idNegozio, String nomeDownCase)
    {
        return "SELECT * FROM oggetto WHERE idNegozio=" + idNegozio + " AND nomeDownCase LIKE '%" + nomeDownCase + "%'";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti di una categoria di un determinato negozio
     * @param idcategoria
     * @param idNegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndShop(int idcategoria, int idNegozio)
    {
        return "SELECT * FROM oggetto WHERE idNegozio=" + idNegozio + " AND oggetto.categoria=" + idcategoria + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio
     * @param idcategoria
     * @param idNegozio
     * @param nomeDownCase
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndShopAndName(int idcategoria, int idNegozio, String nomeDownCase)
    {
        return "SELECT * FROM oggetto WHERE idNegozio=" + idNegozio + " AND oggetto.categoria=" + idcategoria + " AND oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio
     * @param idNegozio
     * @param prezzoMin
     * @return String: elenco oggetti
     */
    public static String selectObjectByShopAndHigherThanPrice(int idNegozio, double prezzoMin)
    {
        return "SELECT * FROM oggetto WHERE idNegozio=" + idNegozio + " AND oggetto.prezzo >= " + prezzoMin + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio
     * @param idNegozio
     * @param prezzoMax
     * @return String: elenco oggetti
     */
    public static String selectObjectByShopAndLowerThanPrice(int idNegozio, double prezzoMax)
    {
        return "SELECT * FROM oggetto WHERE idNegozio=" + idNegozio + " AND oggetto.prezzo <= " + prezzoMax + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio
     * @param idNegozio
     * @param prezzoMin
     * @param prezzoMax
     * @return String: elenco oggetti
     */
    public static String selectObjectByShopAndBetweenPrices(int idNegozio, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM oggetto WHERE idNegozio=" + idNegozio + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio
     * @param idcategoria
     * @param idNegozio
     * @param prezzoMin
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndShopAndHigherThanPrice(int idcategoria, int idNegozio, double prezzoMin)
    {
        return "SELECT * FROM oggetto WHERE idNegozio=" + idNegozio + " AND categoria=" + idcategoria + " AND prezzo >= " + prezzoMin + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio
     * @param idcategoria
     * @param idNegozio
     * @param prezzoMax
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndShopAndLowerThanPrice(int idcategoria, int idNegozio, double prezzoMax)
    {
        return "SELECT * FROM oggetto WHERE idNegozio=" + idNegozio + " AND categoria=" + idcategoria + " AND prezzo <= " + prezzoMax + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio
     * @param idcategoria
     * @param idNegozio
     * @param prezzoMin
     * @param prezzoMax
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndShopAndBetweenPrices(int idcategoria, int idNegozio, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM oggetto WHERE idNegozio=" + idNegozio + " AND categoria=" + idcategoria + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio
     * @param idNegozio
     * @param nomeDownCase
     * @param prezzoMin
     * @return String: elenco oggetti
     */
    public static String selectObjectByShopAndNameAndHigherThanPrice(int idNegozio, String nomeDownCase, double prezzoMin)
    {
        return "SELECT * FROM oggetto WHERE idNegozio=" + idNegozio + " AND oggetto.prezzo >= " + prezzoMin + " AND oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
     * @param idNegozio
     * @param nomeDownCase
     * @param prezzoMax
     * @return String: elenco oggetti
     */
    public static String selectObjectByShopAndNameAndLowerThanPrice(int idNegozio, String nomeDownCase, double prezzoMax)
    {
        return "SELECT * FROM oggetto WHERE idNegozio=" + idNegozio + " AND oggetto.prezzo <= " + prezzoMax + " AND oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
     * @param idNegozio
     * @param nomeDownCase
     * @param prezzoMin
     * @param prezzoMax
     * @return String: elenco oggetti
     */
    public static String selectObjectByShopAndNameAndBetweenPrices(int idNegozio, String nomeDownCase, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM oggetto WHERE idNegozio=" + idNegozio + " AND oggetto.prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     * @param idNegozio
     * @param idcategoria
     * @param nomeDownCase
     * @param prezzoMin
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndShopAndNameAndHigherThanPrice(int idNegozio, int idcategoria, String nomeDownCase, double prezzoMin)
    {
        return "SELECT * FROM oggetto WHERE idNegozio=" + idNegozio + "  AND categoria=" + idcategoria + " AND oggetto.prezzo >= " + prezzoMin + " AND oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     * @param idNegozio
     * @param idcategoria
     * @param nomeDownCase
     * @param prezzoMax
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndShopAndNameAndLowerThanPrice(int idNegozio, int idcategoria, String nomeDownCase, double prezzoMax)
    {
        return "SELECT * FROM oggetto WHERE idNegozio=" + idNegozio + " AND categoria=" + idcategoria + " AND oggetto.prezzo <= " + prezzoMax + " AND oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     * @param idNegozio
     * @param idcategoria
     * @param nomeDownCase
     * @param prezzoMin
     * @param prezzoMax
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndShopAndNameAndBetweenPrices(int idNegozio, int idcategoria, String nomeDownCase, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM oggetto WHERE idNegozio=" + idNegozio + " AND categoria=" + idcategoria + " AND oggetto.prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    
    /*---1*/    /*---QUERY BRUGI---*/
    /**
     * @author fbrug
     * Ottenere la lista di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente
     * @param lat
     * @param lon
     * @param r
     * @return String: lista di negozi nell'area specificata
     */
    private static String LLR(int idUtente, double lat, double lon, double r)   //LLR = Latitude - Longitude - Radius
    {
        return "Create OR REPLACE View NegoziDistanza_" + idUtente + " as"
                + "SET @latitudine = " + lat + ";"
                + "SET @longitudine = " + lon + ";"
                + "SET @raggio = " + r + ";"
                
                + "SELECT negozio.id"
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI)"
                + "WHERE @raggio >= 111.111"
                + "* DEGREES(ACOS(COS(RADIANS(@latitudine))"
                + "* COS(RADIANS(indirizzo.latitudine))"
                + "* COS(RADIANS(@longitudine - indirizzo.longitudine))"
                + "+ SIN(RADIANS(@latitudine))"
                + "* SIN(RADIANS(indirizzo.latitudine))));";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente
     * @param lat
     * @param lon
     * @param r
     * @return String: lista di negozi
     */
    public static String selectShopByLLR(int idUtente, double lat, double lon, double r)
    {
        return LLR(idUtente, lat, lon, r);
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti in negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente
     * @param lat
     * @param lon
     * @param r
     * @return String: lista di oggetti
     */
    public static String selectObjectByLLR(int idUtente, double lat, double lon, double r)
    {
        return LLR(idUtente, lat, lon, r) 
                + "SELECT oggetto.* FROM oggetto "
                + "INNER JOIN NegoziDistanza_"+idUtente+" ON (oggetto.idNegozio = NegoziDistanza_"+idUtente+".id);";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti che contengono una stringa nel nome, nei negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente
     * @param lat
     * @param lon
     * @param r
     * @param nomeDownCase
     * @return String: lista di oggetti
     */
    public static String selectObjectByNameAndLLR(int idUtente, double lat, double lon, double r, String nomeDownCase)
    {
        return LLR(idUtente, lat, lon, r)
                + "SELECT oggetto.* FROM oggetto "
                + "INNER JOIN NegoziDistanza_"+idUtente+" ON (oggetto.idNegozio = NegoziDistanza_"+idUtente+".id) "
                + "WHERE oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti di una categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente
     * @param lat
     * @param lon
     * @param r
     * @param idcategoria
     * @return String: lista di oggetti 
     */
    public static String selectObjectByCategoryAndLLR(int idUtente, double lat, double lon, double r, int idcategoria)
    {
        return LLR(idUtente, lat, lon, r)
                + "SELECT oggetto.* FROM oggetto "
                + "INNER JOIN NegoziDistanza_"+idUtente+" ON (oggetto.idNegozio = NegoziDistanza_"+idUtente+".id) "
                + "WHERE oggetto.categoria=" + idcategoria + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti di una categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente
     * @param lat
     * @param lon
     * @param r
     * @param idcategoria
     * @param nomeDownCase
     * @return String: lista di oggetti 
     */
    public static String selectObjectByCategoryAndNameAndLLR(int idUtente, double lat, double lon, double r, int idcategoria, String nomeDownCase)
    {
        return LLR(idUtente, lat, lon, r)
                + "SELECT oggetto.* FROM oggetto "
                + "INNER JOIN NegoziDistanza_"+idUtente+" ON (oggetto.idNegozio = NegoziDistanza_"+idUtente+".id) "
                + "WHERE oggetto.categoria=" + idcategoria + " AND oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente
     * @param lat
     * @param lon
     * @param r
     * @param prezzoMin
     * @return String: lista di oggetti 
     */
    public static String selectObjectByLLRAndHigherThanPrice(int idUtente, double lat, double lon, double r, double prezzoMin)
    {
        return LLR(idUtente, lat, lon, r)
                + "SELECT oggetto.* FROM oggetto "
                + "INNER JOIN NegoziDistanza_"+idUtente+" ON (oggetto.idNegozio = NegoziDistanza_"+idUtente+".id) "
                + "WHERE oggetto.prezzo >= " + prezzoMin + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo massimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente
     * @param lat
     * @param lon
     * @param r
     * @param prezzoMax
     * @return String: lista di oggetti 
     */
    public static String selectObjectByLLRAndLowerThanPrice(int idUtente, double lat, double lon, double r, double prezzoMax)
    {
        return LLR(idUtente, lat, lon, r)
                + "SELECT oggetto.* FROM oggetto "
                + "INNER JOIN NegoziDistanza_"+idUtente+" ON (oggetto.idNegozio = NegoziDistanza_"+idUtente+".id) "
                + "WHERE oggetto.prezzo <= " + prezzoMax + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente
     * @param lat
     * @param lon
     * @param r
     * @param prezzoMin
     * @param prezzoMax
     * @return String: lista di oggetti 
     */
    public static String selectObjectByLLRAndBetweenPrices(int idUtente, double lat, double lon, double r, double prezzoMin, double prezzoMax)
    {
        return LLR(idUtente, lat, lon, r)
                + "SELECT oggetto.* FROM oggetto "
                + "INNER JOIN NegoziDistanza_"+idUtente+" ON (oggetto.idNegozio = NegoziDistanza_"+idUtente+".id) "
                + "WHERE oggetto.prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente
     * @param lat
     * @param lon
     * @param r
     * @param idcategoria
     * @param prezzoMin
     * @return String: lista di oggetti 
     */
    public static String selectObjectByCategoryAndLLRAndHigherThanPrice(int idUtente, double lat, double lon, double r, int idcategoria, double prezzoMin)
    {
        return LLR(idUtente, lat, lon, r)
                + "SELECT oggetto.* FROM oggetto "
                + "INNER JOIN NegoziDistanza_"+idUtente+" ON (oggetto.idNegozio = NegoziDistanza_"+idUtente+".id) "
                + "WHERE oggetto.prezzo >= " + prezzoMin + " AND oggetto.categoria=" + idcategoria + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente
     * @param lat
     * @param lon
     * @param r
     * @param idcategoria
     * @param prezzoMax
     * @return String: lista di oggetti 
     */
    public static String selectObjectByCategoryAndLLRLowerThanPrice(int idUtente, double lat, double lon, double r, int idcategoria, double prezzoMax)
    {
        return LLR(idUtente, lat, lon, r)
                + "SELECT oggetto.* FROM oggetto "
                + "INNER JOIN NegoziDistanza_"+idUtente+" ON (oggetto.idNegozio = NegoziDistanza_"+idUtente+".id) "
                + "WHERE oggetto.prezzo <= " + prezzoMax + " AND oggetto.categoria=" + idcategoria + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente
     * @param lat
     * @param lon
     * @param r
     * @param idcategoria
     * @param prezzoMin
     * @param prezzoMax
     * @return String: lista di oggetti 
     */
    public static String selectObjectByCategoryAndLLRAndBetweenPrices(int idUtente, double lat, double lon, double r, int idcategoria, double prezzoMin, double prezzoMax)
    {
        return LLR(idUtente, lat, lon, r)
                + "SELECT oggetto.* FROM oggetto "
                + "INNER JOIN NegoziDistanza_"+idUtente+" ON (oggetto.idNegozio = NegoziDistanza_"+idUtente+".id) "
                + "WHERE oggetto.prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND oggetto.categoria=" + idcategoria + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente
     * @param lat
     * @param lon
     * @param r
     * @param prezzoMin
     * @param nomeDownCase
     * @return String: lista di oggetti 
     */
    public static String selectObjectByNameAndLLRAndHigherThanPrice(int idUtente, double lat, double lon, double r, double prezzoMin, String nomeDownCase)
    {
        return LLR(idUtente, lat, lon, r)
                + "SELECT oggetto.* FROM oggetto "
                + "INNER JOIN NegoziDistanza_"+idUtente+" ON (oggetto.idNegozio = NegoziDistanza_"+idUtente+".id) "
                + "WHERE oggetto.prezzo >= " + prezzoMin
                + " AND oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente
     * @param lat
     * @param lon
     * @param r
     * @param prezzoMax
     * @param nomeDownCase
     * @return String: lista di oggetti 
     */
    public static String selectObjectByNameAndLLRLowerThanPrice(int idUtente, double lat, double lon, double r, double prezzoMax, String nomeDownCase)
    {
        return LLR(idUtente, lat, lon, r)
                + "SELECT oggetto.* FROM oggetto "
                + "INNER JOIN NegoziDistanza_"+idUtente+" ON (oggetto.idNegozio = NegoziDistanza_"+idUtente+".id) "
                + "WHERE oggetto.prezzo <= " + prezzoMax
                + " AND oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente
     * @param lat
     * @param lon
     * @param r
     * @param prezzoMin
     * @param prezzoMax
     * @param nomeDownCase
     * @return String: lista di oggetti 
     */
    public static String selectObjectByNameAndLLRAndBetweenPrices(int idUtente, double lat, double lon, double r, double prezzoMin, double prezzoMax, String nomeDownCase)
    {
        return LLR(idUtente, lat, lon, r)
                + "SELECT oggetto.* FROM oggetto "
                + "INNER JOIN NegoziDistanza_"+idUtente+" ON (oggetto.idNegozio = NegoziDistanza_"+idUtente+".id) "
                + "WHERE oggetto.prezzo BETWEEN " + prezzoMin
                + " AND oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente
     * @param lat
     * @param lon
     * @param r
     * @param idcategoria
     * @param prezzoMin
     * @param nomeDownCase
     * @return String: lista di oggetti 
     */
    public static String selectObjectByCategoryAndNameAndLLRAndHigherThanPrice(int idUtente, double lat, double lon, double r, int idcategoria, double prezzoMin, String nomeDownCase)
    {
        return LLR(idUtente, lat, lon, r)
                + "SELECT oggetto.* FROM oggetto "
                + "INNER JOIN NegoziDistanza_"+idUtente+" ON (oggetto.idNegozio = NegoziDistanza_"+idUtente+".id) "
                + "WHERE oggetto.prezzo >= " + prezzoMin + " AND oggetto.categoria=" + idcategoria
                + " AND oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente
     * @param lat
     * @param lon
     * @param r
     * @param idcategoria
     * @param prezzoMax
     * @param nomeDownCase
     * @return String: lista di oggetti 
     */
    public static String selectObjectByCategoryAndNameAndLLRLowerThanPrice(int idUtente, double lat, double lon, double r, int idcategoria, double prezzoMax, String nomeDownCase)
    {
        return LLR(idUtente, lat, lon, r)
                + "SELECT oggetto.* FROM oggetto "
                + "INNER JOIN NegoziDistanza_"+idUtente+" ON (oggetto.idNegozio = NegoziDistanza_"+idUtente+".id) "
                + "WHERE oggetto.prezzo <= " + prezzoMax + " AND oggetto.categoria=" + idcategoria
                + " AND oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente
     * @param lat
     * @param lon
     * @param r
     * @param idcategoria
     * @param prezzoMin
     * @param prezzoMax
     * @param nomeDownCase
     * @return String: lista di oggetti 
     */
    public static String selectObjectByCategoryAndNameAndLLRAndBetweenPrices(int idUtente, double lat, double lon, double r, int idcategoria, double prezzoMin, double prezzoMax, String nomeDownCase)
    {
        return LLR(idUtente, lat, lon, r)
                + "SELECT oggetto.* FROM oggetto "
                + "INNER JOIN NegoziDistanza_"+idUtente+" ON (oggetto.idNegozio = NegoziDistanza_"+idUtente+".id) "
                + "WHERE oggetto.prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND oggetto.categoria=" + idcategoria
                + " AND oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /*---2*/
    /**
     * @author fbrug
     * Ottenere la lista di oggetti che contengono una stringa nel nome
     * @param nomeDownCase
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByNameAndPIS(String nomeDownCase, int ritiroInnegozio)  //PIS = Pickup In Store
    {
        return "SELECT * FROM oggetto WHERE nomeDownCase LIKE '%" + nomeDownCase + "%' AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti di una categoria
     * @param idcategoria
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndPIS(int idcategoria, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE categoria = " + idcategoria + " AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti di una categoria con una certa stringa nel nome
     * @param idcategoria
     * @param nomeDownCase
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndNameAndPIS(int idcategoria, String nomeDownCase, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo
     * @param prezzoMin
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectHigherThanPriceAndPIS(double prezzoMin, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE prezzo >= " + prezzoMin + " AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo massimo
     * @param prezzoMax
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectLowerThanPriceAndPIS(double prezzoMax, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE prezzo <= " + prezzoMax + " AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo
     * @param prezzoMin
     * @param prezzoMax
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectBetweenPricesAndPIS(double prezzoMin, double prezzoMax, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria
     * @param idcategoria
     * @param prezzoMin
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndHigherThanPriceAndPIS(int idcategoria, double prezzoMin, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND prezzo >= " + prezzoMin + " AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria
     * @param idcategoria
     * @param prezzoMax
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndLowerThanPriceAndPIS(int idcategoria, double prezzoMax, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND prezzo <= " + prezzoMax + " AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria
     * @param idcategoria
     * @param prezzoMin
     * @param prezzoMax
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndBetweenPricesAndPIS(int idcategoria, double prezzoMin, double prezzoMax, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome
     * @param nomeDownCase
     * @param prezzoMin
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByNameAndHigherThanPriceAndPIS(String nomeDownCase, double prezzoMin, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE prezzo >= " + prezzoMin + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome
     * @param nomeDownCase
     * @param prezzoMax
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByNameAndLowerThanPriceAndPIS(String nomeDownCase, double prezzoMax, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE prezzo <= " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome
     * @param nomeDownCase
     * @param prezzoMin
     * @param prezzoMax
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByNameAndBetweenPricesAndPIS(String nomeDownCase, double prezzoMin, double prezzoMax, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome
     * @param idcategoria
     * @param nomeDownCase
     * @param prezzoMin
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndNameAndHigherThanPriceAndPIS(int idcategoria, String nomeDownCase, double prezzoMin, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND prezzo >= " + prezzoMin + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome
     * @param idcategoria
     * @param nomeDownCase
     * @param prezzoMax
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndNameAndLowerThanPriceAndPIS(int idcategoria, String nomeDownCase, double prezzoMax, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND prezzo <= " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome
     * @param idcategoria
     * @param nomeDownCase
     * @param prezzoMin
     * @param prezzoMax
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndNameAndBetweenPricesAndPIS(int idcategoria, String nomeDownCase, double prezzoMin, double prezzoMax, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    
    
    
    /*---3*/
    /**
     * @author fbrug
     * Ottenere la lista di oggetti che contengono una stringa nel nome
     * @param nomeDownCase
     * @return String: elenco oggetti
     */
    public static String selectObjectByNameAndEndDiscount(String nomeDownCase)
    {
        return "SELECT * FROM oggetto WHERE nomeDownCase LIKE '%" + nomeDownCase + "%' AND oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti di una categoria
     * @param idcategoria
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndEndDiscount(int idcategoria)
    {
        return "SELECT * FROM oggetto WHERE categoria = " + idcategoria + " AND oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti di una categoria con una certa stringa nel nome
     * @param idcategoria
     * @param nomeDownCase
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndNameAndEndDiscount(int idcategoria, String nomeDownCase)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo
     * @param prezzoMin
     * @return String: elenco oggetti
     */
    public static String selectObjectHigherThanPriceAndEndDiscount(double prezzoMin)
    {
        return "SELECT * FROM oggetto WHERE prezzo >= " + prezzoMin + " AND oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo massimo
     * @param prezzoMax
     * @return String: elenco oggetti
     */
    public static String selectObjectLowerThanPriceAndEndDiscount(double prezzoMax)
    {
        return "SELECT * FROM oggetto WHERE prezzo <= " + prezzoMax + " AND oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo
     * @param prezzoMin
     * @param prezzoMax
     * @return String: elenco oggetti
     */
    public static String selectObjectBetweenPricesAndEndDiscount(double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM oggetto WHERE prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria
     * @param idcategoria
     * @param prezzoMin
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndHigherThanPriceAndEndDiscount(int idcategoria, double prezzoMin)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND prezzo >= " + prezzoMin + " AND oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria
     * @param idcategoria
     * @param prezzoMax
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndLowerThanPriceAndEndDiscount(int idcategoria, double prezzoMax)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND prezzo <= " + prezzoMax + " AND oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria
     * @param idcategoria
     * @param prezzoMin
     * @param prezzoMax
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndBetweenPricesAndEndDiscount(int idcategoria, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome
     * @param nomeDownCase
     * @param prezzoMin
     * @return String: elenco oggetti
     */
    public static String selectObjectByNameAndHigherThanPriceAndEndDiscount(String nomeDownCase, double prezzoMin)
    {
        return "SELECT * FROM oggetto WHERE prezzo >= " + prezzoMin + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome
     * @param nomeDownCase
     * @param prezzoMax
     * @return String: elenco oggetti
     */
    public static String selectObjectByNameAndLowerThanPriceAndEndDiscount(String nomeDownCase, double prezzoMax)
    {
        return "SELECT * FROM oggetto WHERE prezzo <= " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome
     * @param nomeDownCase
     * @param prezzoMin
     * @param prezzoMax
     * @return String: elenco oggetti
     */
    public static String selectObjectByNameAndBetweenPricesAndEndDiscount(String nomeDownCase, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM oggetto WHERE prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome
     * @param idcategoria
     * @param nomeDownCase
     * @param prezzoMin
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndNameAndHigherThanPriceAndEndDiscount(int idcategoria, String nomeDownCase, double prezzoMin)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND prezzo >= " + prezzoMin + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome
     * @param idcategoria
     * @param nomeDownCase
     * @param prezzoMax
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndNameAndLowerThanPriceAndEndDiscount(int idcategoria, String nomeDownCase, double prezzoMax)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND prezzo <= " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome
     * @param idcategoria
     * @param nomeDownCase
     * @param prezzoMin
     * @param prezzoMax
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndNameAndBetweenPricesAndEndDiscount(int idcategoria, String nomeDownCase, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /*---4*/
    /**
     * @author fbrug
     * Ottenere la lista di oggetti che contengono una stringa nel nome
     * @param nomeDownCase
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByNameAndPISAndEndDiscount(String nomeDownCase, int ritiroInnegozio)  //PIS = Pickup In Store
    {
        return "SELECT * FROM oggetto WHERE nomeDownCase LIKE '%" + nomeDownCase + "%' AND oggetto.`dataFineSconto` IS NOT NULL AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti di una categoria
     * @param idcategoria
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndPISAndEndDiscount(int idcategoria, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE categoria = " + idcategoria + " AND oggetto.`dataFineSconto` IS NOT NULL AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti di una categoria con una certa stringa nel nome
     * @param idcategoria
     * @param nomeDownCase
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndNameAndPISAndEndDiscount(int idcategoria, String nomeDownCase, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND oggetto.`dataFineSconto` IS NOT NULL AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo
     * @param prezzoMin
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectHigherThanPriceAndPISAndEndDiscount(double prezzoMin, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE prezzo >= " + prezzoMin + " AND oggetto.`dataFineSconto` IS NOT NULL AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo massimo
     * @param prezzoMax
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectLowerThanPriceAndPISAndEndDiscount(double prezzoMax, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE prezzo <= " + prezzoMax + " AND oggetto.`dataFineSconto` IS NOT NULL AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo
     * @param prezzoMin
     * @param prezzoMax
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectBetweenPricesAndPISAndEndDiscount(double prezzoMin, double prezzoMax, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND oggetto.`dataFineSconto` IS NOT NULL AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria
     * @param idcategoria
     * @param prezzoMin
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndHigherThanPriceAndPISAndEndDiscount(int idcategoria, double prezzoMin, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND prezzo >= " + prezzoMin + " AND oggetto.`dataFineSconto` IS NOT NULL AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria
     * @param idcategoria
     * @param prezzoMax
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndLowerThanPriceAndPISAndEndDiscount(int idcategoria, double prezzoMax, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND prezzo <= " + prezzoMax + " AND oggetto.`dataFineSconto` IS NOT NULL AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria
     * @param idcategoria
     * @param prezzoMin
     * @param prezzoMax
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndBetweenPricesAndPISAndEndDiscount(int idcategoria, double prezzoMin, double prezzoMax, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND oggetto.`dataFineSconto` IS NOT NULL AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome
     * @param nomeDownCase
     * @param prezzoMin
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByNameAndHigherThanPriceAndPISAndEndDiscount(String nomeDownCase, double prezzoMin, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE prezzo >= " + prezzoMin + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND oggetto.`dataFineSconto` IS NOT NULL AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome
     * @param nomeDownCase
     * @param prezzoMax
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByNameAndLowerThanPriceAndPISAndEndDiscount(String nomeDownCase, double prezzoMax, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE prezzo <= " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND oggetto.`dataFineSconto` IS NOT NULL AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome
     * @param nomeDownCase
     * @param prezzoMin
     * @param prezzoMax
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByNameAndBetweenPricesAndPISAndEndDiscount(String nomeDownCase, double prezzoMin, double prezzoMax, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND oggetto.`dataFineSconto` IS NOT NULL AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome
     * @param idcategoria
     * @param nomeDownCase
     * @param prezzoMin
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndNameAndHigherThanPriceAndPISAndEndDiscount(int idcategoria, String nomeDownCase, double prezzoMin, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND prezzo >= " + prezzoMin + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND oggetto.`dataFineSconto` IS NOT NULL AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome
     * @param idcategoria
     * @param nomeDownCase
     * @param prezzoMax
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndNameAndLowerThanPriceAndPISAndEndDiscount(int idcategoria, String nomeDownCase, double prezzoMax, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND prezzo <= " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND oggetto.`dataFineSconto` IS NOT NULL AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome
     * @param idcategoria
     * @param nomeDownCase
     * @param prezzoMin
     * @param prezzoMax
     * @param ritiroInnegozio
     * @return String: elenco oggetti
     */
    public static String selectObjectByCategoryAndNameAndBetweenPricesAndPISAndEndDiscount(int idcategoria, String nomeDownCase, double prezzoMin, double prezzoMax, int ritiroInnegozio)
    {
        return "SELECT * FROM oggetto WHERE categoria=" + idcategoria + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND oggetto.`dataFineSconto` IS NOT NULL AND oggetto.ritiroInnegozio = " + ritiroInnegozio + ";";
    }
}
