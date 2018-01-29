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
        return "SELECT * FROM negozio WHERE idVenditore = " + id + ";";
    }
    
    /**
     * @author andrea
     * Ottenere Recensione, negozio
     * @param idN Una stringa che rappresenta l'identificativo del negozio preso in considerazione
     * @return elenco recensioni e negozio
     */
    public static String selectReviewUserByStore(int idN)
    {
        return "SELECT recensionenegozio.*, utente.* FROM "
                + "(recensionenegozio JOIN rispostanegozio ON "
                + "recensionenegozio.id=rispostanegozio.idRecensione ) JOIN utente "
                + "ON recensionenegozio.idUtente=utente.id WHERE "
                + "recensionenegozio.idNegozio=" + idN + ";";
    }
    
    public static String selectAnswerUserByStore(int idN)
    {
        return "SELECT rispostanegozio.*, utente.* FROM "
                + "(recensionenegozio JOIN rispostanegozio ON "
                + "recensionenegozio.id=rispostanegozio.idRecensione ) JOIN utente "
                + "ON recensionenegozio.idUtente=utente.id WHERE "
                + "recensionenegozio.idNegozio=" + idN + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti presenti nei negozi di un determinato venditore
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti presenti nei suoi negozi
     * @return La stringa corrispondete alla query
     */
    public static String itemsInShopBySeller(int id){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti presenti nei negozi di un determinato venditore
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti presenti nei suoi negozi
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query
     */
    public static String itemsInShopBySellerSpecific(int id, int ritiro){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto presenti nei negozi di un determinato venditore
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti presenti nei suoi negozi
     * @return La stringa corrispondete alla query
     */
    public static String itemsInShopBySellerOnDiscount(int id){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + "AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto presenti nei negozi di un determinato venditore
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti presenti nei suoi negozi
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query
     */
    public static String itemsInShopBySellerSpecificOnDiscount(int id, int ritiro){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.ritiroInNegozio = " + ritiro + " "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti che contengono una determinata stringa nel nome
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti presenti nei suoi negozi
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query
     */
    public static String itemsWithStringInShopBySeller(int id, String pattern){
        return "SELECT oggetto.*"
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.nomeDownCase LIKE '%" + pattern + "%';";
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
        return "SELECT oggetto.*"
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto che contengono una determinata stringa nel nome
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti presenti nei suoi negozi
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query
     */
    public static String itemsWithStringInShopBySellerOnDiscount(int id, String pattern){
        return "SELECT oggetto.*"
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto che contengono una determinata stringa 
     * nel nome con una determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti presenti nei suoi negozi
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query
     */
    public static String itemsWithStringInShopBySellerSpecificOnDiscount(int id, String pattern, int ritiro){
        return "SELECT oggetto.*"
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND oggetto.ritiroInNegozio = " + ritiro + " AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista di oggetti presenti in una determinata categoria
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @return La stringa corrispondete alla query
     */
    public static String itemsInCategoryInShopBySeller(int id, int categoria){
        return "SELECT oggetto.*"
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.categoria = " + categoria + ";";
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
        return "SELECT oggetto.*"
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id)"
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.categoria = " + categoria + " "
                + "AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista di oggetti in sconto presenti in una determinata categoria
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @return La stringa corrispondete alla query
     */
    public static String itemsInCategoryInShopBySellerOnDiscount(int id, int categoria){
        return "SELECT oggetto.*"
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.categoria = " + categoria + " "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista di oggetti in sconto presenti in una determinata categoria con una 
     * determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondente alla query
     */
    public static String itemsInCategoryInShopBySellerSpecificOnDiscount(int id, int categoria, int ritiro){
        return "SELECT oggetto.*"
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id)"
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.categoria = " + categoria + " "
                + "AND oggetto.ritiroInNegozio = " + ritiro + " AND oggetto.dataFineSconto IS NOT NULL;";
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
        return "SELECT oggetto.*"
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id)"
                + "WHERE negozio.idVenditore = " + id + "  AND oggetto.categoria = " + categoria + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%';";
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
        return "SELECT oggetto.*"
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id)"
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.categoria = " + categoria + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista di oggetti in sconto presenti in una deterinata categoria
     * che contengono una determinata stringa nel nome
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondente alla query
     */
    public static String itemsInCategoryWithStringInShopBySellerOnDiscount(int id, int categoria, String pattern){
        return "SELECT oggetto.*"
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id)"
                + "WHERE negozio.idVenditore = " + id + "  AND oggetto.categoria = " + categoria + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista di oggetti in sconto presenti in una deterinata categoria
     * che contengono una determinata stringa nel nome e con una determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondente alla query
     */
    public static String itemsInCategoryWithStringInShopBySellerSpecificOnDiscount(int id, int categoria, String pattern, int ritiro){
        return "SELECT oggetto.*"
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id)"
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.categoria = " + categoria + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' AND oggetto.ritiroInNegozio = " + ritiro + " "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
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
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.prezzo >= " + minimo + ";";
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
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.prezzo >= " + minimo + " "
                + "AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto di un determinato venditore con un prezzo
     * maggiore di una soglia minima fornita in input
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @return La stringa corrispondete alla query
     */
    public static String itemsWithPriceHigherThanBySellerOnDiscount(int id, double minimo){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.prezzo >= " + minimo + " "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto di un determinato venditore con un prezzo
     * maggiore di una soglia minima fornita in input con una determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query
     */
    public static String itemsWithPriceHigherThanBySellerSpecificOnDiscount(int id, double minimo, int ritiro){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.prezzo >= " + minimo + " "
                + "AND oggetto.ritiroInNegozio = " + ritiro + " AND oggetto.dataFineSconto IS NOT NULL;";
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
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.prezzo <= " + massimo + ";";
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
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.prezzo <= " + massimo + " "
                + "AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto di un determinato venditore con un prezzo
     * minore di una soglia massima fornita in input
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param massimo La soglia massima di prezzo
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceLowerThanBySellerOnDiscount(int id, double massimo){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.prezzo <= " + massimo + " "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto di un determinato venditore con un prezzo
     * minore di una soglia massima fornita in input con una determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param massimo La soglia massima di prezzo
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceLowerThanBySellerSpecificOnDiscount(int id, double massimo, int ritiro){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.prezzo <= " + massimo + " "
                + "AND oggetto.ritiroInNegozio = " + ritiro + " AND oggetto.dataFineSconto IS NOT NULL;";
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
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " "
                + "AND oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + ";";
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
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " "
                + "AND oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto di un determinato venditore con un prezzo
     * compreso tra due valori
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceBetweenRangeBySellerOnDiscount(int id, double minimo, double massimo){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " "
                + "AND oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto di un determinato venditore con un prezzo
     * compreso tra due valori con una determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceBetweenRangeBySellerSpecificOnDiscount(int id, double minimo, double massimo, int ritiro){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " "
                + "AND oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND oggetto.ritiroInNegozio = " + ritiro + " AND oggetto.dataFineSconto IS NOT NULL;";
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
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + "  AND categoria = " + categoria + " "
                + "AND oggetto.prezzo >= " + minimo + ";";
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
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + "  AND categoria = " + categoria + " "
                + "AND oggetto.prezzo >= " + minimo + "AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto in una determinata categoria 
     * di un determinato venditore con un prezzo maggiore di una soglia minima fornita in input
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @return La stringa corrispondete alla query
     */
    public static String itemsInCategoryWithPriceHigherThanBySellerOnDiscount(int id, double minimo, int categoria){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + "  AND categoria = " + categoria + " "
                + "AND oggetto.prezzo >= " + minimo + " AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto in una determianata categoria 
     * di un determinato venditore con un prezzo maggiore di una soglia minima fornita in input
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query
     */
    public static String itemsInCategoryWithPriceHigherThanBySellerSpecificOnDiscount(int id, double minimo, int categoria, int ritiro){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + "  AND categoria = " + categoria + " "
                + "AND oggetto.prezzo >= " + minimo + "AND oggetto.ritiroInNegozio = " + ritiro + " "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
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
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + "  AND categoria = " + categoria + " "
                + "AND oggetto.prezzo <= " + massimo + ";";
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
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + "  AND categoria = " + categoria + " "
                + "AND oggetto.prezzo <= " + massimo + "AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto in una determianta categoria
     * di un determinato venditore con un prezzo minore di una soglia massima fornita in input
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param massimo La soglia massima di prezzo
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @return La stringa corrispondete alla query
     */
    public static String itemsInCategoryWithPriceLowerThanBySellerOnDiscount(int id, double massimo, int categoria){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + "  AND categoria = " + categoria + " "
                + "AND oggetto.prezzo <= " + massimo + " AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto in una determinata categoria 
     * di un determinato venditore con un prezzo maggiore di una soglia minima fornita in input
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param massimo La soglia massima di prezzo
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query
     */
    public static String itemsInCategoryWithPriceLowerThanBySellerSpecificOnDiscount(int id, double massimo, int categoria, int ritiro){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + "  AND categoria = " + categoria + " "
                + "AND oggetto.prezzo <= " + massimo + "AND oggetto.ritiroInNegozio = " + ritiro + " "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
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
    public static String itemsInCategoryWithPriceBetweenRangeBySeller(int id, double minimo, double massimo, int categoria){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND categoria = " + categoria + " "
                + "AND oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + ";";
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
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND categoria = " + categoria + " "
                + "AND oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto in una determinata categoria
     * di un determinato venditore con un prezzo compreso tra due valori con una determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono 
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategoryWithPriceBetweenRangeBySellerOnDiscount(int id, double minimo, double massimo, int categoria){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND categoria = " + categoria + " "
                + "AND oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto in una determinata categoria
     * di un determinato venditore con un prezzo compreso tra due valori con una determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategoryWithPriceBetweenRangeBySellerSpecificOnDiscount(int id, double minimo, double massimo, int categoria, int ritiro){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND categoria = " + categoria + " "
                + "AND oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND oggetto.ritiroInNegozio = " + ritiro + " AND oggetto.dataFineSconto IS NOT NULL;";
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
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.prezzo >= " + minimo + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%';";
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
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.prezzo >= " + minimo + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto di un determinato venditore con un prezzo
     * maggiore di una soglia minima e che contenga una determinata stringa nel nome
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query
     */
    public static String itemsWithPriceHigherThanWithStringBySellerOnDiscount(int id, double minimo, String pattern){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.prezzo >= " + minimo + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto di un determinato venditore con un prezzo
     * maggiore di una soglia minima e che contenga una determinata stringa nel nome e una determinata
     * modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceHigherThanWithStringBySellerSpecificOnDiscount(int id, double minimo, String pattern, int ritiro){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.prezzo >= " + minimo + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND oggetto.ritiroInNegozio = " + ritiro + " AND oggetto.dataFineSconto IS NOT NULL;";
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
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.prezzo <= " + massimo + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%';";
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
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.prezzo <= " + massimo + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto di un determinato venditore con un prezzo
     * minore di una soglia massima e che contenga una determinata stringa nel nome
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query
     */
    public static String itemsWithPriceLowerThanWithStringBySellerOnDiscount(int id, double massimo, String pattern){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.prezzo <= " + massimo + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto di un determinato venditore con un prezzo
     * minore di una soglia massima e che contenga una determinata stringa nel nome e una determinata
     * modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceLowerThanWithStringBySellerSpecificOnDiscount(int id, double massimo, String pattern, int ritiro){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND oggetto.prezzo <= " + massimo + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND oggetto.ritiroInNegozio = " + ritiro + " AND oggetto.dataFineSconto IS NOT NULL;";
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
    public static String itemsWithPriceBetweenRangeWithStringBySeller(int id, double minimo, double massimo, String pattern){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " "
                + "AND oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
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
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " "
                + "AND oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND Oggett.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto di un determinato venditore con un prezzo
     * compreso tra due valori con una determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceBetweenRangeWithStringBySellerOnDiscount(int id, double minimo, double massimo, String pattern){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " "
                + "AND oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND Oggett.nomeDownCase LIKE '%" + pattern + "%' AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto di un determinato venditore con un prezzo
     * compreso tra due valori con una determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceBetweenRangeWithStringBySellerSpecificOnDiscount(int id, double minimo, double massimo, String pattern, int ritiro){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " "
                + "AND oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND Oggett.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND oggetto.ritiroInNegozio = " + ritiro + " AND oggetto.dataFineSconto IS NOT NULL;";
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
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND categoria = " + categoria + " "
                + "AND oggetto.prezzo >= " + minimo + " AND oggetto.nomeDownCase LIKE '%" + pattern + "%';";
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
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND categoria = " + categoria + " "
                + "AND oggetto.prezzo >= " + minimo + " AND oggetto.nomeDownCase LIKE '%" + pattern + "% "
                + "AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto in una categoria di un determinato venditore 
     * con un prezzo maggiore di un valore minimo e con una stringa nel nome
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param minimo La soglia minima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query  
     */
    public static String itemsInCategoryWithPriceHigherThanWithStringbySellerOnDiscount(int id, int categoria, double minimo, String pattern){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND categoria = " + categoria + " "
                + "AND oggetto.prezzo >= " + minimo + " AND oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto in una categoria di un determinato venditore 
     * con un prezzo maggiore di un valore minimo, con una stringa nel nome e con una determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param minimo La soglia minima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query  
     */
    public static String itemsInCategoryWithPriceHigherThanWithStringbySellerSpecificOnDiscount(int id, int categoria, double minimo, String pattern, int ritiro){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND categoria = " + categoria + " "
                + "AND oggetto.prezzo >= " + minimo + " AND oggetto.nomeDownCase LIKE '%" + pattern + "% "
                + "AND oggetto.ritiroInNegozio = " + ritiro + " AND oggetto.dataFineSconto IS NOT NULL;";
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
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND categoria = " + categoria + " "
                + "AND oggetto.prezzo <= " + massimo + " AND oggetto.nomeDownCase LIKE '%" + pattern + "%';";
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
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND categoria = " + categoria + " "
                + "AND oggetto.prezzo <= " + massimo + " AND oggetto.nomeDownCase LIKE '%" + pattern + "% "
                + "AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto in una categoria di un determinato venditore 
     * con un prezzo minore di un valore massimo e con una stringa nel nome
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query  
     */
    public static String itemsInCategoryWithPriceLowerThanWithStringbySellerOnDiscount(int id, int categoria, double massimo, String pattern){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND categoria = " + categoria + " "
                + "AND oggetto.prezzo <= " + massimo + " AND oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto in una categoria di un determinato venditore 
     * con un prezzo minore di un valore massimo, con una stringa nel nome e con una determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query  
     */
    public static String itemsInCategoryWithPriceLowerThanWithStringbySellerSpecificOnDiscount(int id, int categoria, double massimo, String pattern, int ritiro){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND categoria = " + categoria + " "
                + "AND oggetto.prezzo <= " + massimo + " AND oggetto.nomeDownCase LIKE '%" + pattern + "% "
                + "AND oggetto.ritiroInNegozio = " + ritiro + " AND oggetto.dataFineSconto IS NOT NULL;";
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
    public static String itemsInCategoryWithPriceBetweenRangeWithStringbySeller(int id, int categoria, double minimo, double massimo, String pattern){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND categoria = " + categoria + " "
                + "AND oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%';";
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
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND categoria = " + categoria + " "
                + "AND oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "% "
                + "AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto in una categoria di un determinato venditore 
     * con un prezzo compreso tra due valori e con una stringa nel nome
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query  
     */
    public static String itemsInCategoryWithPriceBetweenRangeWithStringbySellerOnDiscount(int id, int categoria, double minimo, double massimo, String pattern){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND categoria = " + categoria + " "
                + "AND oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che permette di ottenere la lista degli oggetti in sconto in una categoria di un determinato venditore 
     * con un prezzo compreso tra due valori, con una stringa nel nome e con una determinata modalità di ritiro
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query  
     */
    public static String itemsInCategoryWithPriceBetweenRangeWithStringbySellerSpecificOnDiscount(int id, int categoria, double minimo, double massimo, String pattern, int ritiro){
        return "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore = " + id + " AND categoria = " + categoria + " "
                + "AND oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "% "
                + "AND oggetto.ritiroInNegozio = " + ritiro + " AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista di negozi di un determinato venditore a distanza minore o uguale al raggio
     * a partire da una latitudine e una longitudine
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * 
     * @return La stringa corrispondete alla query 
     */
    public static String shopbySellerNearby(double lat, double lon, double radius, int id){
        return "SELECT negozio.* "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "         AND negozio.idVenditore = " + id + ";";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id);";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto presenti nei negozi di un determinato venditore 
     * a distanza minore o uguale al raggio a partire da una latitudine e una longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @return La stringa corrispondete alla query 
     */
    public static String itemsbySellerNearbyOnDiscount(int idU, double lat, double lon, double radius, int id){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto presenti nei negozi di un determinato venditore 
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
    public static String itemsbySellerNearbySpecificOnDiscount(int idU, double lat, double lon, double radius, int id, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.ritiroInNegozio = " + ritiro + " AND oggetto.dataFineSconto IS NOT NULL;";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.nomeDownCase LIKE '%" + pattern + "%';";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.nomeDownCase LIKE '%" + pattern + "%' AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto presenti nei negozi di un determinato venditore con una string nel 
     * nome a distanza minore o uguale al raggio a partire da una latitudine e una longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithStringbySellerNearbyOnDiscount(int idU, double lat, double lon, double radius, int id, String pattern){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.nomeDownCase LIKE '%" + pattern + "%' AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto presenti nei negozi di un determinato venditore con 
     * una stringa nel nome a distanza minore o uguale al raggio a partire da una latitudine e una longitudine e 
     * con una determinata modalità di ritiro
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithStringbySellerNearbySpecificOnDiscount(int idU, double lat, double lon, double radius, int id, String pattern,  int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.nomeDownCase LIKE '%" + pattern + "%' AND oggetto.ritiroInNegozio = " + ritiro + " "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + ";";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + " AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto in una categoria presenti nei negozi di un determinato 
     * venditore  a distanza minore o uguale al raggio a partire da una latitudine e una longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategorybySellerNearbyOnDiscount(int idU, double lat, double lon, double radius, int id, int categoria){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + " AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto in una categoria presenti nei negozi di un determinato 
     * venditore a distanza minore o uguale al raggio a partire da una latitudine e una longitudine e con una 
     * determinata modalità di ritiro
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategorybySellerNearbySpecificOnDiscount(int idU, double lat, double lon, double radius, int id, int categoria, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + " AND oggetto.ritiroInNegozio = " + ritiro + " "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%';";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto in una categoria presenti nei negozi di un determinato 
     * venditore con una string nel nome a distanza minore o uguale al raggio a partire da una latitudine e una 
     * longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategoryWithStringbySellerNearbyOnDiscount(int idU, double lat, double lon, double radius, int id, int categoria, String pattern){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto in una categoria presenti nei negozi di un determinato
     * venditore con una stringa nel nome a distanza minore o uguale al raggio a partire da una latitudine e 
     * una longitudine e con una determinata modalità di ritiro
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
    public static String itemsInCategoryWithStringbySellerNearbySpecificOnDiscount(int idU, double lat, double lon, double radius, int id, int categoria, String pattern, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND oggetto.ritiroInNegozio = " + ritiro + " AND oggetto.dataFineSconto IS NOT NULL;";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.prezzo >= " + minimo + ";";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.prezzo >= " + minimo + " AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto presenti nei negozi di un determinato venditore con 
     * un prezzo maggiore di un certo minimo a distanza minore o uguale al raggio a partire da una latitudine e una 
     * longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceHigherThanbySellerNearbyOnDiscount(int idU, double lat, double lon, double radius, int id, double minimo){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.prezzo >= " + minimo + " AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto presenti nei negozi di un determinato venditore con 
     * un prezzo maggiore di un certo minimo a distanza minore o uguale al raggio a partire da una latitudine e una 
     * longitudine e con una determinata modalità di ritiro
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceHigherThanbySellerNearbySpecificOnDiscount(int idU, double lat, double lon, double radius, int id, double minimo, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.prezzo >= " + minimo + " AND oggetto.ritiroInNegozio = " + ritiro + " "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.prezzo <= " + massimo + ";";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.prezzo <= " + massimo + " AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto presenti nei negozi di un determinato venditore con un 
     * prezzo minore di un certo massimo a distanza minore o uguale al raggio a partire da una latitudine e una 
     * longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param massimo La soglia massima di prezzo
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceLowerThanbySellerNearbyOnDiscount(int idU, double lat, double lon, double radius, int id, double massimo){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.prezzo <= " + massimo + " AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto presenti nei negozi di un determinato venditore con un 
     * prezzo minore di un certo massimo a distanza minore o uguale al raggio a partire da una latitudine e una 
     * longitudine e con una determinata modalità di ritiro
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param massimo La soglia massima di prezzo
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceLowerThanbySellerNearbySpecificOnDiscount(int idU, double lat, double lon, double radius, int id, double massimo, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.prezzo <= " + massimo + " AND oggetto.ritiroInNegozio = " + ritiro + " "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + ";";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto presenti nei negozi di un determinato venditore con un 
     * prezzo minore di un certo massimo a distanza minore o uguale al raggio a partire da una latitudine e una 
     * longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param massimo La soglia massima di prezzo
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceBetweenRangebySellerNearbyOnDiscount(int idU, double lat, double lon, double radius, int id, double minimo, double massimo){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto presenti nei negozi di un determinato venditore con 
     * un prezzo minore di un certo massimo a distanza minore o uguale al raggio a partire da una latitudine e una 
     * longitudine e con una determinata modalità di ritiro
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
    public static String itemsWithPriceBetweenRangebySellerNearbySpecificOnDiscount(int idU, double lat, double lon, double radius, int id, double minimo, double massimo, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND oggetto.ritiroInNegozio = " + ritiro + " AND oggetto.dataFineSconto IS NOT NULL;";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + " AND oggetto.prezzo >= " + minimo + ";";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + "AND oggetto.prezzo >= " + minimo + " "
                + "AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto in una categoria presenti nei negozi di un determinato 
     * venditore con un prezzo maggiore di un certo minimo a distanza minore o uguale al raggio a partire da una 
     * latitudine e una longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param minimo La soglia minima di prezzo
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategoryWithPriceHigherThanbySellerNearbyOnDiscount(int idU, double lat, double lon, double radius, int id, int categoria, double minimo){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + " AND oggetto.prezzo >= " + minimo + " "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto in una categoria presenti nei negozi di un determinato 
     * venditore con un prezzo maggiore di un certo minimo a distanza minore o uguale al raggio a partire da una 
     * latitudine e una longitudine e con una determinata modalità di ritiro
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
    public static String itemsInCategoryWithPriceHigherThanbySellerNearbySpecificOnDiscount(int idU, double lat, double lon, double radius, int id, int categoria, double minimo, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + "AND oggetto.prezzo >= " + minimo + " "
                + "AND oggetto.ritiroInNegozio = " + ritiro + " AND oggetto.dataFineSconto IS NOT NULL;";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + " AND oggetto.prezzo <= " + massimo + ";";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + "AND oggetto.prezzo <= " + massimo + " "
                + "AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto in una categoria presenti nei negozi di un determinato 
     * venditore con un prezzo minore di un certo massimo a distanza minore o uguale al raggio a partire da una 
     * latitudine e una longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param categoria Un intero che rappresenta la categoria a cui gli oggetti cercati appartengono
     * @param massimo La soglia massima di prezzo
     * @return La stringa corrispondete alla query 
     */
    public static String itemsInCategoryWithPriceLowerThanbySellerNearbyOnDiscount(int idU, double lat, double lon, double radius, int id, int categoria, double massimo){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + " AND oggetto.prezzo <= " + massimo + " "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto in una categoria presenti nei negozi di un determinato 
     * venditore con un prezzo minore di un certo massimo a distanza minore o uguale al raggio a partire da una 
     * latitudine e una longitudine e con una determinata modalità di ritiro
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
    public static String itemsInCategoryWithPriceLowerThanbySellerNearbySpecificOnDiscount(int idU, double lat, double lon, double radius, int id, int categoria, double massimo, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + "AND oggetto.prezzo <= " + massimo + " "
                + "AND oggetto.ritiroInNegozio = " + ritiro + " AND oggetto.dataFineSconto IS NOT NULL;";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + " AND oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + ";";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + " "
                + "AND oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto in una categoria presenti nei negozi di un determinato 
     * venditore con un prezzo compreso tra due valori di un certo massimo a distanza minore o uguale al raggio a 
     * partire da una latitudine e una longitudine
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
    public static String itemsInCategoryWithPriceBetweenRangebySellerNearbyOnDiscount(int idU, double lat, double lon, double radius, int id, int categoria, double minimo, double massimo){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + " AND oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto in una categoria presenti nei negozi di un determinato 
     * venditore con un prezzo compreso tra due valori a distanza minore o uguale al raggio a partire da una 
     * latitudine e una longitudine e con una determinata modalità di ritiro
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
    public static String itemsInCategoryWithPriceBetweenRangebySellerNearbySpecificOnDiscount(int idU, double lat, double lon, double radius, int id, int categoria, double minimo, double massimo, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + " "
                + "AND oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND oggetto.ritiroInNegozio = " + ritiro + " AND oggetto.dataFineSconto IS NOT NULL;";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.prezzo >= " + minimo + " AND oggetto.nomeDownCase LIKE '%" + pattern + "%';";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.prezzo >= " + minimo + " AND oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto presenti nei negozi di un determinato venditore con 
     * un prezzo maggiore di un certo minimo a distanza minore o uguale al raggio a partire da una latitudine e una 
     * longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param minimo La soglia minima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceHigherThanWithStringbySellerNearbyOnDiscount(int idU, double lat, double lon, double radius, int id, double minimo, String pattern){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.prezzo >= " + minimo + " AND oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto presenti nei negozi di un determinato venditore con un 
     * prezzo maggiore di un certo minimo a distanza minore o uguale al raggio a partire da una latitudine e una 
     * longitudine e con una determinata modalità di ritiro
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
    public static String itemsWithPriceHigherThanWithStringbySellerNearbySpecificOnDiscount(int idU, double lat, double lon, double radius, int id, double minimo, String pattern, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.prezzo >= " + minimo + " AND oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND oggetto.ritiroInNegozio = " + ritiro + " AND oggetto.dataFineSconto IS NOT NULL;";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.prezzo <= " + massimo + " AND oggetto.nomeDownCase LIKE '%" + pattern + "%';";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.prezzo <= " + massimo + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto presenti nei negozi di un determinato venditore con un 
     * prezzo minore di un certo massimo a distanza minore o uguale al raggio a partire da una latitudine e 
     * una longitudine
     * @param idU Un intero ceh rappresenta l'utente a cui si riferisce la view creata
     * @param lat Un double che rappresenta la latidudine desiderata
     * @param lon Un double che rappresenta la longitudine desiderata
     * @param radius Un double che rappresenta il raggio di ricerca
     * @param id Un intero che rappresenta il venditore di cui si stanno cercando gli oggetti
     * @param massimo La soglia massima di prezzo
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return La stringa corrispondete alla query 
     */
    public static String itemsWithPriceLowerThanWithStringbySellerNearbyOnDiscount(int idU, double lat, double lon, double radius, int id, double massimo, String pattern){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.prezzo <= " + massimo + " AND oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto presenti nei negozi di un determinato venditore con un 
     * prezzo minore di un certo massimo a distanza minore o uguale al raggio a partire da una latitudine e una 
     * longitudine e con una determinata modalità di ritiro
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
    public static String itemsWithPriceLowerThanWithStringbySellerNearbySpecificOnDiscount(int idU, double lat, double lon, double radius, int id, double massimo, String pattern, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.prezzo <= " + massimo + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND oggetto.ritiroInNegozio = " + ritiro + " AND oggetto.dataFineSconto IS NOT NULL;";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + ""
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%';";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto presenti nei negozi di un determinato venditore con un 
     * prezzo minore di un certo massimo a distanza minore o uguale al raggio a partire da una latitudine e una 
     * longitudine
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
    public static String itemsWithPriceBetweenRangeWithStringbySellerNearbyOnDiscount(int idU, double lat, double lon, double radius, int id, double minimo, double massimo, String pattern){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + ""
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto presenti nei negozi di un determinato venditore con un prezzo minore
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
    public static String itemsWithPriceBetweenRangeWithStringbySellerNearbySpecificOnDiscount(int idU, double lat, double lon, double radius, int id, double minimo, double massimo, String pattern, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' AND oggetto.ritiroInNegozio = " + ritiro + " "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + " AND oggetto.prezzo >= " + minimo + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%';";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + "AND oggetto.prezzo >= " + minimo + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto in una categoria presenti nei negozi di un determinato 
     * venditore con una stringa nel nome, con un prezzo maggiore di un certo minimo, a distanza minore o uguale 
     * al raggio a partire da una latitudine e una longitudine
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
    public static String itemsInCategoryWithPriceHigherThanWithStringbySellerNearbyOnDiscount(int idU, double lat, double lon, double radius, int id, int categoria, double minimo, String pattern){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + " AND oggetto.prezzo >= " + minimo + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto in una categoria presenti nei negozi di un determinato 
     * venditore con una stringa nel nome, con un prezzo maggiore di un certo minimo, a distanza minore o uguale al 
     * raggio a partire da una latitudine e una longitudine e con una determinata modalità di ritiro
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
    public static String itemsInCategoryWithPriceHigherThanWithStringbySellerNearbySpecificOnDiscount(int idU, double lat, double lon, double radius, int id, int categoria, double minimo, String pattern, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + "AND oggetto.prezzo >= " + minimo + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' AND oggetto.ritiroInNegozio = " + ritiro + " "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' AND oggetto.prezzo <= " + massimo + ";";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + "AND oggetto.prezzo <= " + massimo + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto in una categoria presenti nei negozi di un determinato 
     * venditore  con una stringa nel nome, con un prezzo minore di un certo massimo, a distanza minore o uguale al 
     * raggio a partire da una latitudine e una longitudine
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
    public static String itemsInCategoryWithPriceLowerThanWithStringbySellerNearbyOnDiscount(int idU, double lat, double lon, double radius, int id, int categoria, double massimo, String pattern){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' AND oggetto.prezzo <= " + massimo + " "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto in una categoria presenti nei negozi di un determinato 
     * venditore con una stringa nel nome, con un prezzo minore di un certo massimo, a distanza minore o uguale 
     * al raggio a partire da una latitudine e una longitudine e con una determinata modalità di ritiro
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
    public static String itemsInCategoryWithPriceLowerThanWithStringbySellerNearbySpecificOnDiscount(int idU, double lat, double lon, double radius, int id, int categoria, double massimo, String pattern, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + "AND oggetto.prezzo <= " + massimo + " "
                + "AND oggetto.nomeDownCase LIKE '%" + pattern + "%' AND oggetto.ritiroInNegozio = " + ritiro + " "
                + "AND oggetto.dataFineSconto IS NOT NULL;";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + " AND oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + ";";
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
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + " AND oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND oggetto.ritiroInNegozio = " + ritiro + ";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto in una categoria presenti nei negozi di un determinato
     * venditore con una stringa nel nome, con un prezzo compreso tra due valori, a distanza minore o uguale al raggio 
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
    public static String itemsInCategoryWithPriceBetweenRangeWithStringbySellerNearbyOnDiscount(int idU, double lat, double lon, double radius, int id, int categoria, double minimo, double massimo, String pattern){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + " AND oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " AND oggetto.dataFineSconto IS NOT NULL;";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la lista degli oggetti in sconto in una categoria presenti nei negozi di un determinato 
     * venditore con una stringa nel nome, con un prezzo compreso tra due valori, a distanza minore o uguale al raggio 
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
    public static String itemsInCategoryWithPriceBetweenRangeWithStringbySellerNearbySpecificOnDiscount(int idU, double lat, double lon, double radius, int id, int categoria, double minimo, double massimo, String pattern, int ritiro){
        return "Create OR REPLACE View NegoziNellaDistanza_" + idU + " as "
                + "SELECT negozio.id "
                + "FROM negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) "
                + "WHERE " + radius + " >= 111.111 * "
                + "         DEGREES(ACOS(COS(RADIANS(" + lat + ")) "
                + "             * COS(RADIANS(indirizzo.latitudine)) "
                + "             * COS(RADIANS(" + lon + " - indirizzo.longitudine)) "
                + "             + SIN(RADIANS(" + lat + ")) "
                + "             * SIN(RADIANS(indirizzo.latitudine)))) "
                + "     AND negozio.idVenditore = " + id + "; "
                + "SELECT oggetto.* "
                + "FROM oggetto INNER JOIN NegoziNellaDistanza ON (oggetto.idNegozio = NegoziNellaDistanza.id) "
                + "WHERE oggetto.categoria = " + categoria + " AND oggetto.nomeDownCase LIKE '%" + pattern + "%' "
                + "AND oggetto.prezzo BETWEEN " + minimo + " AND " + massimo + " "
                + "AND oggetto.ritiroInNegozio = " + ritiro + " AND oggetto.dataFineSconto IS NOT NULL;";
    }   
}