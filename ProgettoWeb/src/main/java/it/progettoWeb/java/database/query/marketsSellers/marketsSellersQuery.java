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
        return "Hello from " + marketsSellersQuery.class.toString();
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
    public static String itemsWithStringInShopBySeller(int id, String pattern){
        return "SELECT Oggetto.*"
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
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
    public static String itemsWithStringInShopBySellerSpecific(int id, String pattern, int ritiro){
        return "SELECT Oggetto.*"
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + " AND Oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista di oggetti presenti in una determinata categoria
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @return La stringa corrispondete alla query
     */
    public static String itemsInCategoryInShopBySeller(int id, int categoria){
        return "SELECT Oggetto.*"
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
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
    public static String itemsInCategoryInShopBySellerSpecific(int id, int categoria, int ritiro){
        return "SELECT Oggetto.*"
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)"
                + "WHERE Negozio.idVenditore = " + id + " AND Oggetto.categoria = " + categoria + " "
                + "AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista di oggetti presenti in una deterinata categoria
     * che contengono una determinata stringa nel nome
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondente alla query
     */
    public static String itemsInCategoryWithStringInShopBySeller(int id, int categoria, String pattern){
        return "SELECT Oggetto.*"
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)"
                + "WHERE Negozio.idVenditore = " + id + "  AND Oggetto.categoria = " + categoria + " "
                + "AND Oggetto.nomeDownCase LIKE '%" + pattern + "%';";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista di oggetti presenti in una deterinata categoria
     * che contengono una determinata stringa nel nome e con una determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondente alla query
     */
    public static String itemsInCategoryWithStringInShopBySellerSpecific(int id, int categoria, String pattern, int ritiro){
        return "SELECT Oggetto.*"
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)"
                + "WHERE Negozio.idVenditore = " + id + " AND Oggetto.categoria = " + categoria + " "
                + "AND Oggetto.nomeDownCase LIKE '%" + pattern + "%' AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti di un determinato venditore con un prezzo
     * maggiore di una soglia minima fornita in input
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @return La stringa corrispondete alla query
     */
    public static String itemsWithPriceHigherThanBySeller(int id, double minimo){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + " AND Oggetto.prezzo >= " + minimo + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti di un determinato venditore con un prezzo
     * maggiore di una soglia minima fornita in input con una determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query
     */
    public static String itemsWithPriceHigherThanBySellerSpecific(int id, double minimo, int ritiro){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + " AND Oggetto.prezzo >= " + minimo + " "
                + "AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti di un determinato venditore con un prezzo
     * minore di una soglia massima fornita in input
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param massimo La soglia massima di prezzo
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceLowerThanBySeller(int id, double massimo){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + " AND Oggetto.prezzo <= " + massimo + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti di un determinato venditore con un prezzo
     * minore di una soglia massima fornita in input con una determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param massimo La soglia massima di prezzo
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceLowerThanBySellerSpecific(int id, double massimo, int ritiro){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + " AND Oggetto.prezzo <= " + massimo + " "
                + "AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti di un determinato venditore con un prezzo
     * compreso tra due valori
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceBetweenRangeBySeller(int id, double minimo, double massimo){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + " "
                + "AND Oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti di un determinato venditore con un prezzo
     * compreso tra due valori con una determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceBetweenRangeBySellerSpecific(int id, double minimo, double massimo, int ritiro){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + " "
                + "AND Oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in una determinata categoria 
     * di un determinato venditore con un prezzo maggiore di una soglia minima fornita in input
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @return La stringa corrispondete alla query
     */
    public static String itemsInCategoryWithPriceHigherThanBySeller(int id, double minimo, int categoria){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + "  AND categoria = " + categoria + " "
                + "AND Oggetto.prezzo >= " + minimo + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in una determianata categoria 
     * di un determinato venditore con un prezzo maggiore di una soglia minima fornita in input
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query
     */
    public static String itemsInCategoryWithPriceHigherThanBySellerSpecific(int id, double minimo, int categoria, int ritiro){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + "  AND categoria = " + categoria + " "
                + "AND Oggetto.prezzo >= " + minimo + "AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in una determianta categoria
     * di un determinato venditore con un prezzo minore di una soglia massima fornita in input
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param massimo La soglia massima di prezzo
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @return La stringa corrispondete alla query
     */
    public static String itemsInCategoryWithPriceLowerThanBySeller(int id, double massimo, int categoria){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + "  AND categoria = " + categoria + " "
                + "AND Oggetto.prezzo <= " + massimo + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in una determinata categoria 
     * di un determinato venditore con un prezzo maggiore di una soglia minima fornita in input
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param massimo La soglia massima di prezzo
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query
     */
    public static String itemsInCategoryWithPriceLowerThanBySellerSpecific(int id, double massimo, int categoria, int ritiro){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + "  AND categoria = " + categoria + " "
                + "AND Oggetto.prezzo <= " + massimo + "AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in una determinata categoria
     * di un determinato venditore con un prezzo compreso tra due valori con una determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono 
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategoryWithPriceBetweenRangeBySellerSpecific(int id, double minimo, double massimo, int categoria){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + " AND categoria = " + categoria + " "
                + "AND Oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in una determinata categoria
     * di un determinato venditore con un prezzo compreso tra due valori con una determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategoryWithPriceBetweenRangeBySellerSpecific(int id, double minimo, double massimo, int categoria, int ritiro){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + " AND categoria = " + categoria + " "
                + "AND Oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti di un determinato venditore con un prezzo
     * maggiore di una soglia minima e che contenga una determinata stringa nel nome
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query
     */
    public static String itemsWithPriceHigherThanWithStringBySeller(int id, double minimo, String pattern){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + " AND Oggetto.prezzo >= " + minimo + " "
                + "AND Oggetto.nomeDownCase LIKE '%" + pattern + "%';";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti di un determinato venditore con un prezzo
     * maggiore di una soglia minima e che contenga una determinata stringa nel nome e una determinata
     * modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceHigherThanWithStringBySellerSpecific(int id, double minimo, String pattern, int ritiro){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + " AND Oggetto.prezzo >= " + minimo + " "
                + "AND Oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti di un determinato venditore con un prezzo
     * minore di una soglia massima e che contenga una determinata stringa nel nome
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query
     */
    public static String itemsWithPriceLowerThanWithStringBySeller(int id, double massimo, String pattern){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + " AND Oggetto.prezzo <= " + massimo + " "
                + "AND Oggetto.nomeDownCase LIKE '%" + pattern + "%';";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti di un determinato venditore con un prezzo
     * minore di una soglia massima e che contenga una determinata stringa nel nome e una determinata
     * modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceLowerThanWithStringBySellerSpecific(int id, double massimo, String pattern, int ritiro){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + " AND Oggetto.prezzo <= " + massimo + " "
                + "AND Oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti di un determinato venditore con un prezzo
     * compreso tra due valori con una determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceBetweenRangeWithStringBySellerSpecific(int id, double minimo, double massimo, String pattern){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + " "
                + "AND Oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND Oggett.nomeDownCase LIKE '%" + pattern + "%';";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti di un determinato venditore con un prezzo
     * compreso tra due valori con una determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceBetweenRangeWithStringBySellerSpecific(int id, double minimo, double massimo, String pattern, int ritiro){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + " "
                + "AND Oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND Oggett.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in una categoria di un determinato venditore 
     * con un prezzo maggiore di un valore minimo e con una stringa nel nome
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param minimo La soglia minima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query  
     */
    public static String itemsInCategoryWithPriceHigherThanWithStringbySeller(int id, int categoria, double minimo, String pattern){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + " AND categoria = " + categoria + " "
                + "AND Oggetto.prezzo >= " + minimo + " AND Oggetto.nomeDownCase LIKE '%" + pattern + "%';";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in una categoria di un determinato venditore 
     * con un prezzo maggiore di un valore minimo, con una stringa nel nome e con una determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param minimo La soglia minima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query  
     */
    public static String itemsInCategoryWithPriceHigherThanWithStringbySellerSpecific(int id, int categoria, double minimo, String pattern, int ritiro){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + " AND categoria = " + categoria + " "
                + "AND Oggetto.prezzo >= " + minimo + " AND Oggetto.nomeDownCase LIKE '%" + pattern + "% "
                + "AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in una categoria di un determinato venditore 
     * con un prezzo minore di un valore massimo e con una stringa nel nome
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query  
     */
    public static String itemsInCategoryWithPriceLowerThanWithStringbySeller(int id, int categoria, double massimo, String pattern){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + " AND categoria = " + categoria + " "
                + "AND Oggetto.prezzo <= " + massimo + " AND Oggetto.nomeDownCase LIKE '%" + pattern + "%';";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in una categoria di un determinato venditore 
     * con un prezzo minore di un valore massimo, con una stringa nel nome e con una determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query  
     */
    public static String itemsInCategoryWithPriceLowerThanWithStringbySellerSpecific(int id, int categoria, double massimo, String pattern, int ritiro){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + " AND categoria = " + categoria + " "
                + "AND Oggetto.prezzo <= " + massimo + " AND Oggetto.nomeDownCase LIKE '%" + pattern + "% "
                + "AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in una categoria di un determinato venditore 
     * con un prezzo compreso tra due valori e con una stringa nel nome
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query  
     */
    public static String itemsInCategoryWithPriceBetweenRangeWithStringWithStringbySeller(int id, int categoria, double minimo, double massimo, String pattern){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + " AND categoria = " + categoria + " "
                + "AND Oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND Oggetto.nomeDownCase LIKE '%" + pattern + "%';";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in una categoria di un determinato venditore 
     * con un prezzo compreso tra due valori, con una stringa nel nome e con una determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query  
     */
    public static String itemsInCategoryWithPriceBetweenRangeWithStringbySellerSpecific(int id, int categoria, double minimo, double massimo, String pattern, int ritiro){
        return "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) "
                + "WHERE Negozio.idVenditore = " + id + " AND categoria = " + categoria + " "
                + "AND Oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND Oggetto.nomeDownCase LIKE '%" + pattern + "% "
                + "AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista di negozi di un determinato venditore a distanza minore o uguale al raggio
     * a partire da una latitudine e una longitudine
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @return La stringa corrispondete alla query 
     */
    public static String shopbySellerNearby(double lat, double lon, double radius, int id){
        return "SELECT Negozio.* "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "         AND Negozio.idVenditore = " + id + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti presenti nei negozi di un determinato venditore 
     * a distanza minore o uguale al raggio a partire da una latitudine e una longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @return La stringa corrispondete alla query 
     */
    public static String itemsbySellerNearby(int idU, double lat, double lon, double radius, int id){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id);";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti presenti nei negozi di un determinato venditore 
     * a distanza minore o uguale al raggio a partire da una latitudine e una longitudine e con una determinata
     * modalità di ritiro
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsbySellerNearbySpecific(int idU, double lat, double lon, double radius, int id, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti presenti nei negozi di un determinato venditore con una string nel 
     * nome a distanza minore o uguale al raggio a partire da una latitudine e una longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithStringbySellerNearby(int idU, double lat, double lon, double radius, int id, String pattern){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.nomeDownCase LIKE '%" + pattern + "%';";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti presenti nei negozi di un determinato venditore con una stringa nel
     * nome a distanza minore o uguale al raggio a partire da una latitudine e una longitudine e con una determinata
     * modalità di ritiro
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithStringbySellerNearbySpecific(int idU, double lat, double lon, double radius, int id, String pattern,  int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.nomeDownCase LIKE '%" + pattern + "%' AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in una categoria presenti nei negozi di un determinato venditore 
     * a distanza minore o uguale al raggio a partire da una latitudine e una longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategorybySellerNearby(int idU, double lat, double lon, double radius, int id, int categoria){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.categoria = " + categoria + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in una categoria presenti nei negozi di un determinato venditore 
     * a distanza minore o uguale al raggio a partire da una latitudine e una longitudine e con una determinata
     * modalità di ritiro
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategorybySellerNearbySpecific(int idU, double lat, double lon, double radius, int id, int categoria, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.categoria = " + categoria + " AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in una categoria presenti nei negozi di un determinato venditore 
     * con una string nel nome a distanza minore o uguale al raggio a partire da una latitudine e una longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategoryWithStringbySellerNearby(int idU, double lat, double lon, double radius, int id, int categoria, String pattern){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.categoria = " + categoria + "AND Oggetto.nomeDownCase LIKE '%" + pattern + "%';";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in una categoria presenti nei negozi di un determinato venditore 
     * con una stringa nel nome a distanza minore o uguale al raggio a partire da una latitudine e una longitudine 
     * e con una determinata modalità di ritiro
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategoryWithStringbySellerNearbySpecific(int idU, double lat, double lon, double radius, int id, int categoria, String pattern, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.categoria = " + categoria + "AND Oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti presenti nei negozi di un determinato venditore con un prezzo maggiore
     * di un certo minimo a distanza minore o uguale al raggio a partire da una latitudine e una longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceHigherThanbySellerNearby(int idU, double lat, double lon, double radius, int id, double minimo){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.prezzo >= " + minimo + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti presenti nei negozi di un determinato venditore con un prezzo maggiore
     * di un certo minimo a distanza minore o uguale al raggio a partire da una latitudine e una longitudine 
     * e con una determinata modalità di ritiro
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceHigherThanbySellerNearbySpecific(int idU, double lat, double lon, double radius, int id, double minimo, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.prezzo >= " + minimo + " AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti presenti nei negozi di un determinato venditore con un prezzo minore
     * di un certo massimo a distanza minore o uguale al raggio a partire da una latitudine e una longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param massimo La soglia massima di prezzo
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceLowerThanbySellerNearby(int idU, double lat, double lon, double radius, int id, double massimo){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.prezzo <= " + massimo + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti presenti nei negozi di un determinato venditore con un prezzo minore
     * di un certo massimo a distanza minore o uguale al raggio a partire da una latitudine e una longitudine e 
     * con una determinata modalità di ritiro
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param massimo La soglia massima di prezzo
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceLowerThanbySellerNearbySpecific(int idU, double lat, double lon, double radius, int id, double massimo, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.prezzo <= " + massimo + " AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti presenti nei negozi di un determinato venditore con un prezzo minore
     * di un certo massimo a distanza minore o uguale al raggio a partire da una latitudine e una longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceBetweenRangebySellerNearby(int idU, double lat, double lon, double radius, int id, double minimo, double massimo){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti presenti nei negozi di un determinato venditore con un prezzo minore
     * di un certo massimo a distanza minore o uguale al raggio a partire da una latitudine e una longitudine e 
     * con una determinata modalità di ritiro
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceBetweenRangebySellerNearbySpecific(int idU, double lat, double lon, double radius, int id, double minimo, double massimo, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in una categoria presenti nei negozi di un determinato venditore 
     * con un prezzo maggiore di un certo minimo a distanza minore o uguale al raggio a partire da una latitudine 
     * e una longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param minimo La soglia minima di prezzo
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategoryWithPriceHigherThanbySellerNearby(int idU, double lat, double lon, double radius, int id, int categoria, double minimo){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.categoria = " + categoria + " AND Oggetto.prezzo >= " + minimo + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in una categoria presenti nei negozi di un determinato venditore 
     * con un prezzo maggiore di un certo minimo a distanza minore o uguale al raggio a partire da una latitudine 
     * e una longitudine e con una determinata modalità di ritiro
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param minimo La soglia minima di prezzo
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategoryWithPriceHigherThanbySellerNearbySpecific(int idU, double lat, double lon, double radius, int id, int categoria, double minimo, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.categoria = " + categoria + "AND Oggetto.prezzo >= " + minimo + " "
                + "AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in una categoria presenti nei negozi di un determinato venditore 
     * con un prezzo minore di un certo massimo a distanza minore o uguale al raggio a partire da una latitudine 
     * e una longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param massimo La soglia massima di prezzo
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategoryWithPriceLowerThanbySellerNearby(int idU, double lat, double lon, double radius, int id, int categoria, double massimo){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.categoria = " + categoria + " AND Oggetto.prezzo <= " + massimo + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in una categoria presenti nei negozi di un determinato venditore 
     * con un prezzo minore di un certo massimo a distanza minore o uguale al raggio a partire da una latitudine 
     * e una longitudine e con una determinata modalità di ritiro
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param massimo La soglia massima di prezzo
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategoryWithPriceLowerThanbySellerNearbySpecific(int idU, double lat, double lon, double radius, int id, int categoria, double massimo, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.categoria = " + categoria + "AND Oggetto.prezzo <= " + massimo + " "
                + "AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in una categoria presenti nei negozi di un determinato venditore 
     * con un prezzo compreso tra due valori di un certo massimo a distanza minore o uguale al raggio a partire 
     * da una latitudine e una longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategoryWithPriceBetweenRangebySellerNearby(int idU, double lat, double lon, double radius, int id, int categoria, double minimo, double massimo){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.categoria = " + categoria + " AND Oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in una categoria presenti nei negozi di un determinato venditore 
     * con un prezzo compreso tra due valori a distanza minore o uguale al raggio a partire da una latitudine e 
     * una longitudine e con una determinata modalità di ritiro
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategoryWithPriceBetweenRangebySellerNearbySpecific(int idU, double lat, double lon, double radius, int id, int categoria, double minimo, double massimo, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.categoria = " + categoria + " "
                + "AND Oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti presenti nei negozi di un determinato venditore con un prezzo maggiore
     * di un certo minimo a distanza minore o uguale al raggio a partire da una latitudine e una longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceHigherThanWithStringbySellerNearby(int idU, double lat, double lon, double radius, int id, double minimo, String pattern){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.prezzo >= " + minimo + " AND Oggetto.nomeDownCase LIKE '%" + pattern + "%';";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti presenti nei negozi di un determinato venditore con un prezzo maggiore
     * di un certo minimo a distanza minore o uguale al raggio a partire da una latitudine e una longitudine 
     * e con una determinata modalità di ritiro
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceHigherThanWithStringbySellerNearbySpecific(int idU, double lat, double lon, double radius, int id, double minimo, String pattern, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.prezzo >= " + minimo + " AND Oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti presenti nei negozi di un determinato venditore con un prezzo minore
     * di un certo massimo a distanza minore o uguale al raggio a partire da una latitudine e una longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceLowerThanWithStringbySellerNearby(int idU, double lat, double lon, double radius, int id, double massimo, String pattern){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.prezzo <= " + massimo + " AND Oggetto.nomeDownCase LIKE '%" + pattern + "%';";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti presenti nei negozi di un determinato venditore con un prezzo minore
     * di un certo massimo a distanza minore o uguale al raggio a partire da una latitudine e una longitudine e 
     * con una determinata modalità di ritiro
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceLowerThanWithStringbySellerNearbySpecific(int idU, double lat, double lon, double radius, int id, double massimo, String pattern, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.prezzo <= " + massimo + "AND Oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti presenti nei negozi di un determinato venditore con un prezzo minore
     * di un certo massimo a distanza minore o uguale al raggio a partire da una latitudine e una longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceBetweenRangeWithStringbySellerNearby(int idU, double lat, double lon, double radius, int id, double minimo, double massimo, String pattern){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + ""
                + "AND Oggetto.nomeDownCase LIKE '%" + pattern + "%';";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti presenti nei negozi di un determinato venditore con un prezzo minore
     * di un certo massimo a distanza minore o uguale al raggio a partire da una latitudine e una longitudine e 
     * con una determinata modalità di ritiro
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceBetweenRangeWithStringbySellerNearbySpecific(int idU, double lat, double lon, double radius, int id, double minimo, double massimo, String pattern, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND Oggetto.nomeDownCase LIKE '%" + pattern + "%' AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in una categoria presenti nei negozi di un determinato venditore 
     * con una stringa nel nome, con un prezzo maggiore di un certo minimo, a distanza minore o uguale al raggio 
     * a partire da una latitudine e una longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param minimo La soglia minima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategoryWithPriceHigherThanWithStringbySellerNearby(int idU, double lat, double lon, double radius, int id, int categoria, double minimo, String pattern){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.categoria = " + categoria + " AND Oggetto.prezzo >= " + minimo + " "
                + "AND Oggetto.nomeDownCase LIKE '%" + pattern + "%';";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in una categoria presenti nei negozi di un determinato venditore 
     * con una stringa nel nome, con un prezzo maggiore di un certo minimo, a distanza minore o uguale al raggio 
     * a partire da una latitudine e una longitudine e con una determinata modalità di ritiro
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param minimo La soglia minima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategoryWithPriceHigherThanWithStringbySellerNearbySpecific(int idU, double lat, double lon, double radius, int id, int categoria, double minimo, String pattern, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.categoria = " + categoria + "AND Oggetto.prezzo >= " + minimo + " "
                + "AND Oggetto.nomeDownCase LIKE '%" + pattern + "%' AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in una categoria presenti nei negozi di un determinato venditore 
     * con una stringa nel nome, con un prezzo minore di un certo massimo, a distanza minore o uguale al raggio
     * a partire da una latitudine e una longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategoryWithPriceLowerThanWithStringbySellerNearby(int idU, double lat, double lon, double radius, int id, int categoria, double massimo, String pattern){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.categoria = " + categoria + " "
                + "AND Oggetto.nomeDownCase LIKE '%" + pattern + "%' AND Oggetto.prezzo <= " + massimo + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in una categoria presenti nei negozi di un determinato venditore 
     * con una stringa nel nome, con un prezzo minore di un certo massimo, a distanza minore o uguale al raggio 
     * a partire da una latitudine e una longitudine e con una determinata modalità di ritiro
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategoryWithPriceLowerThanWithStringbySellerNearbySpecific(int idU, double lat, double lon, double radius, int id, int categoria, double massimo, String pattern, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.categoria = " + categoria + "AND Oggetto.prezzo <= " + massimo + " "
                + "AND Oggetto.nomeDownCase LIKE '%" + pattern + "%' AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in una categoria presenti nei negozi di un determinato venditore 
     * con una stringa nel nome, con un prezzo compreso tra due valori, a distanza minore o uguale al raggio 
     * a partire da una latitudine e una longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategoryWithPriceBetweenRangeWithStringbySellerNearby(int idU, double lat, double lon, double radius, int id, int categoria, double minimo, double massimo, String pattern){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.categoria = " + categoria + " AND Oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND Oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in una categoria presenti nei negozi di un determinato venditore 
     * con una stringa nel nome, con un prezzo compreso tra due valori, a distanza minore o uguale al raggio 
     * a partire da una latitudine e una longitudine e con una determinata modalità di ritiro
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategoryWithPriceBetweenRangeWithStringbySellerNearbySpecific(int idU, double lat, double lon, double radius, int id, int categoria, double minimo, double massimo, String pattern, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT Negozio.id "
                + "FROM Negozio INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(Indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - Indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(Indirizzo.latitudine)))) "
                + "     AND Negozio.idVenditore = " + id + "; "
                + "SELECT Oggetto.* "
                + "FROM Oggetto INNER JOIN NegoziNellaDistanza ON (Oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE Oggetto.categoria = " + categoria + " AND Oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND Oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND Oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
}
/*-- ottenere la lista di negozi di un determinato venditore
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
