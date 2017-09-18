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
    
    /**
     * Ottenere la lista di oggetti che contengono una stringa nel nome
     * @param nomeDownCase
     * @return 
     */
    public static String selectObjectByName(String nomeDownCase)
    {
        return "SELECT * FROM Oggetto WHERE nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * Ottenere la lista di oggetti di una categoria
     * @param idCategoria
     * @return 
     */
    public static String selectObjectByCategory(int idCategoria)
    {
        return "SELECT * FROM Oggetto WHERE categoria = " + idCategoria + ";";
    }
    
    /**
     * Ottenere la lista di oggetti di una categoria con una certa stringa nel nome
     * @param idCategoria
     * @param nomeDownCase
     * @return 
     */
    public static String selectObjectByCategoryAndName(int idCategoria, String nomeDownCase)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo minimo
     * @param prezzo
     * @return 
     */
    public static String selectObjectLowerThanPrice(double prezzo)
    {
        return "SELECT * FROM Oggetto WHERE prezzo >= " + prezzo + ";";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo massimo
     * @param prezzo
     * @return 
     */
    public static String selectObjectHigherThanPrice(double prezzo)
    {
        return "SELECT * FROM Oggetto WHERE prezzo <= " + prezzo + ";";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo
     * @param prezzoMin
     * @param prezzoMax
     * @return 
     */
    public static String selectObjectBetweenPrices(double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + ";";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria
     * @param idCategoria
     * @param prezzoMin
     * @return 
     */
    public static String selectObjectByCategoryAndHigherThanPrice(int idCategoria, double prezzoMin)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo >= " + prezzoMin + ";";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria
     * @param idCategoria
     * @param prezzoMax
     * @return 
     */
    public static String selectObjectByCategoryAndLowerThanPrice(int idCategoria, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo <= " + prezzoMax + ";";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria
     * @param idCategoria
     * @param prezzoMin
     * @param prezzoMax
     * @return 
     */
    public static String selectObjectByCategoryAndBetweenPrices(int idCategoria, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + ";";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome
     * @param nomeDownCase
     * @param prezzoMin
     * @return 
     */
    public static String selectObjectByNameAndHigherThanPrice(String nomeDownCase, double prezzoMin)
    {
        return "SELECT * FROM Oggetto WHERE prezzo >= " + prezzoMin + " AND nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome
     * @param nomeDownCase
     * @param prezzoMax
     * @return 
     */
    public static String selectObjectByNameAndLowerThanPrice(String nomeDownCase, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE prezzo <= " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome
     * @param nomeDownCase
     * @param prezzoMin
     * @param prezzoMax
     * @return 
     */
    public static String selectObjectByNameAndBetweenPrices(String nomeDownCase, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome
     * @param idCategoria
     * @param nomeDownCase
     * @param prezzoMin
     * @return 
     */
    public static String selectObjectByCategoryAndNameAndHigherThanPrice(int idCategoria, String nomeDownCase, double prezzoMin)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo >= " + prezzoMin + " AND nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome
     * @param idCategoria
     * @param nomeDownCase
     * @param prezzoMax
     * @return 
     */
    public static String selectObjectByCategoryAndNameAndLowerThanPrice(int idCategoria, String nomeDownCase, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo <= " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome
     * @param idCategoria
     * @param nomeDownCase
     * @param prezzoMin
     * @param prezzoMax
     * @return 
     */
    public static String selectObjectByCategoryAndNameAndBetweenPrices(int idCategoria, String nomeDownCase, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * Ottenere la lista di oggetti di un determinato negozio
     * @param idNegozio
     * @return 
     */
    public static String selectObjectByShop(int idNegozio)
    {
        return "SELECT * FROM Oggetto WHERE Oggetto.idNegozio = " + idNegozio + ";";
    }
    
    /**
     * Ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio
     * @param idNegozio
     * @param nomeDownCase
     * @return 
     */
    public static String selectObjectByShopAndName(int idNegozio, String nomeDownCase)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND nomeDownCase LIKE '%" + nomeDownCase + "%'";
    }
    
    /**
     * Ottenere la lista di oggetti di una categoria di un determinato negozio
     * @param idCategoria
     * @param idNegozio
     * @return 
     */
    public static String selectObjectByCategoryAndShop(int idCategoria, int idNegozio)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND Oggetto.categoria=" + idCategoria + ";";
    }
    
    /**
     * Ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio
     * @param idCategoria
     * @param idNegozio
     * @param nomeDownCase
     * @return 
     */
    public static String selectObjectByCategoryAndShopAndName(int idCategoria, int idNegozio, String nomeDownCase)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND Oggetto.categoria=" + idCategoria + " AND Oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio
     * @param idNegozio
     * @param prezzoMin
     * @return 
     */
    public static String selectObjectByShopAndHigherThanPrice(int idNegozio, double prezzoMin)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND Oggetto.prezzo >= " + prezzoMin + ";";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio
     * @param idNegozio
     * @param prezzoMax
     * @return 
     */
    public static String selectObjectByShopAndLowerThanPrice(int idNegozio, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND Oggetto.prezzo <= " + prezzoMax + ";";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio
     * @param idNegozio
     * @param prezzoMin
     * @param prezzoMax
     * @return 
     */
    public static String selectObjectByShopAndBetweenPrices(int idNegozio, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + ";";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio
     * @param idCategoria
     * @param idNegozio
     * @param prezzoMin
     * @return 
     */
    public static String selectObjectByCategoryAndShopAndHigherThanPrice(int idCategoria, int idNegozio, double prezzoMin)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND categoria=" + idCategoria + " AND prezzo >= " + prezzoMin + ";";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio
     * @param idCategoria
     * @param idNegozio
     * @param prezzoMax
     * @return 
     */
    public static String selectObjectByCategoryAndShopAndLowerThanPrice(int idCategoria, int idNegozio, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND categoria=" + idCategoria + " AND prezzo <= " + prezzoMax + ";";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio
     * @param idCategoria
     * @param idNegozio
     * @param prezzoMin
     * @param prezzoMax
     * @return 
     */
    public static String selectObjectByCategoryAndShopAndBetweenPrices(int idCategoria, int idNegozio, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND categoria=" + idCategoria + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + ";";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio
     * @param idNegozio
     * @param nomeDownCase
     * @param prezzoMin
     * @return 
     */
    public static String selectObjectByShopAndNameAndHigherThanPrice(int idNegozio, String nomeDownCase, double prezzoMin)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND Oggetto.prezzo >= " + prezzoMin + " AND Oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
     * @param idNegozio
     * @param nomeDownCase
     * @param prezzoMax
     * @return 
     */
    public static String selectObjectByShopAndNameAndLowerThanPrice(int idNegozio, String nomeDownCase, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND Oggetto.prezzo <= " + prezzoMax + " AND Oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
     * @param idNegozio
     * @param nomeDownCase
     * @param prezzoMin
     * @param prezzoMax
     * @return 
     */
    public static String selectObjectByShopAndNameAndBetweenPrices(int idNegozio, String nomeDownCase, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND Oggetto.prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND Oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     * @param idNegozio
     * @param idCategoria
     * @param nomeDownCase
     * @param prezzoMin
     * @return 
     */
    public static String selectObjectByCategoryAndShopAndNameAndHigherThanPrice(int idNegozio, int idCategoria, String nomeDownCase, double prezzoMin)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + "  AND categoria=" + idCategoria + " AND Oggetto.prezzo >= " + prezzoMin + " AND Oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     * @param idNegozio
     * @param idCategoria
     * @param nomeDownCase
     * @param prezzoMax
     * @return 
     */
    public static String selectObjectByCategoryAndShopAndNameAndLowerThanPrice(int idNegozio, int idCategoria, String nomeDownCase, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND categoria=" + idCategoria + " AND Oggetto.prezzo <= " + prezzoMax + " AND Oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     * @param idNegozio
     * @param idCategoria
     * @param nomeDownCase
     * @param prezzoMin
     * @param prezzoMax
     * @return 
     */
    public static String selectObjectByCategoryAndShopAndNameAndBetweenPrices(int idNegozio, int idCategoria, String nomeDownCase, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE idNegozio=" + idNegozio + " AND categoria=" + idCategoria + " AND Oggetto.prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND Oggetto.nomeDownCase LIKE '%" + nomeDownCase + "%';";
    }
    
    
    /*---1*/
    
    
    
    
    /*---2*/
    /**
     * Ottenere la lista di oggetti che contengono una stringa nel nome
     * @param nomeDownCase
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectByNameAndPIS(String nomeDownCase, int ritiroInNegozio)  //PIS = Pickup In Store
    {
        return "SELECT * FROM Oggetto WHERE nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * Ottenere la lista di oggetti di una categoria
     * @param idCategoria
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectByCategoryAndPIS(int idCategoria, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE categoria = " + idCategoria + " AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * Ottenere la lista di oggetti di una categoria con una certa stringa nel nome
     * @param idCategoria
     * @param nomeDownCase
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectByCategoryAndNameAndPIS(int idCategoria, String nomeDownCase, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo minimo
     * @param prezzo
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectLowerThanPriceAndPIS(double prezzo, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE prezzo >= " + prezzo + " AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * Ottenere la lista di oggetti con un certo prezzo massimo
     * @param prezzo
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectHigherThanPriceAndPIS(double prezzo, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE prezzo <= " + prezzo + " AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * 
     * @param prezzoMin
     * @param prezzoMax
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectBetweenPricesAndPIS(double prezzoMin, double prezzoMax, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * 
     * @param idCategoria
     * @param prezzoMin
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectByCategoryAndHigherThanPriceAndPIS(int idCategoria, double prezzoMin, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo >= " + prezzoMin + " AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * 
     * @param idCategoria
     * @param prezzoMax
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectByCategoryAndLowerThanPriceAndPIS(int idCategoria, double prezzoMax, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo <= " + prezzoMax + " AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * 
     * @param idCategoria
     * @param prezzoMin
     * @param prezzoMax
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectByCategoryAndBetweenPricesAndPIS(int idCategoria, double prezzoMin, double prezzoMax, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * 
     * @param nomeDownCase
     * @param prezzoMin
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectByNameAndHigherThanPriceAndPIS(String nomeDownCase, double prezzoMin, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE prezzo >= " + prezzoMin + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * 
     * @param nomeDownCase
     * @param prezzoMax
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectByNameAndLowerThanPriceAndPIS(String nomeDownCase, double prezzoMax, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE prezzo <= " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * 
     * @param nomeDownCase
     * @param prezzoMin
     * @param prezzoMax
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectByNameAndBetweenPricesAndPIS(String nomeDownCase, double prezzoMin, double prezzoMax, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * 
     * @param idCategoria
     * @param nomeDownCase
     * @param prezzoMin
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectByCategoryAndNameAndHigherThanPriceAndPIS(int idCategoria, String nomeDownCase, double prezzoMin, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo >= " + prezzoMin + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * 
     * @param idCategoria
     * @param nomeDownCase
     * @param prezzoMax
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectByCategoryAndNameAndLowerThanPriceAndPIS(int idCategoria, String nomeDownCase, double prezzoMax, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo <= " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * 
     * @param idCategoria
     * @param nomeDownCase
     * @param prezzoMin
     * @param prezzoMax
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectByCategoryAndNameAndBetweenPricesAndPIS(int idCategoria, String nomeDownCase, double prezzoMin, double prezzoMax, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    
    
    
    /*---3*/
    /**
     * 
     * @param nomeDownCase
     * @return 
     */
    public static String selectObjectByNameAndEndDiscount(String nomeDownCase)
    {
        return "SELECT * FROM Oggetto WHERE nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * 
     * @param idCategoria
     * @return 
     */
    public static String selectObjectByCategoryAndEndDiscount(int idCategoria)
    {
        return "SELECT * FROM Oggetto WHERE categoria = " + idCategoria + " AND Oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * 
     * @param idCategoria
     * @param nomeDownCase
     * @return 
     */
    public static String selectObjectByCategoryAndNameAndEndDiscount(int idCategoria, String nomeDownCase)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * 
     * @param prezzo
     * @return 
     */
    public static String selectObjectLowerThanPriceAndEndDiscount(double prezzo)
    {
        return "SELECT * FROM Oggetto WHERE prezzo >= " + prezzo + " AND Oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * 
     * @param prezzo
     * @return 
     */
    public static String selectObjectHigherThanPriceAndEndDiscount(double prezzo)
    {
        return "SELECT * FROM Oggetto WHERE prezzo <= " + prezzo + " AND Oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * 
     * @param prezzoMin
     * @param prezzoMax
     * @return 
     */
    public static String selectObjectBetweenPricesAndEndDiscount(double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND Oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * 
     * @param idCategoria
     * @param prezzoMin
     * @return 
     */
    public static String selectObjectByCategoryAndHigherThanPriceAndEndDiscount(int idCategoria, double prezzoMin)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo >= " + prezzoMin + " AND Oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * 
     * @param idCategoria
     * @param prezzoMax
     * @return 
     */
    public static String selectObjectByCategoryAndLowerThanPriceAndEndDiscount(int idCategoria, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo <= " + prezzoMax + " AND Oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * 
     * @param idCategoria
     * @param prezzoMin
     * @param prezzoMax
     * @return 
     */
    public static String selectObjectByCategoryAndBetweenPricesAndEndDiscount(int idCategoria, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND Oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * 
     * @param nomeDownCase
     * @param prezzoMin
     * @return 
     */
    public static String selectObjectByNameAndHigherThanPriceAndEndDiscount(String nomeDownCase, double prezzoMin)
    {
        return "SELECT * FROM Oggetto WHERE prezzo >= " + prezzoMin + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * 
     * @param nomeDownCase
     * @param prezzoMax
     * @return 
     */
    public static String selectObjectByNameAndLowerThanPriceAndEndDiscount(String nomeDownCase, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE prezzo <= " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * 
     * @param nomeDownCase
     * @param prezzoMin
     * @param prezzoMax
     * @return 
     */
    public static String selectObjectByNameAndBetweenPricesAndEndDiscount(String nomeDownCase, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * 
     * @param idCategoria
     * @param nomeDownCase
     * @param prezzoMin
     * @return 
     */
    public static String selectObjectByCategoryAndNameAndHigherThanPriceAndEndDiscount(int idCategoria, String nomeDownCase, double prezzoMin)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo >= " + prezzoMin + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * 
     * @param idCategoria
     * @param nomeDownCase
     * @param prezzoMax
     * @return 
     */
    public static String selectObjectByCategoryAndNameAndLowerThanPriceAndEndDiscount(int idCategoria, String nomeDownCase, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo <= " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /**
     * 
     * @param idCategoria
     * @param nomeDownCase
     * @param prezzoMin
     * @param prezzoMax
     * @return 
     */
    public static String selectObjectByCategoryAndNameAndBetweenPricesAndEndDiscount(int idCategoria, String nomeDownCase, double prezzoMin, double prezzoMax)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.`dataFineSconto` IS NOT NULL;";
    }
    
    /*---4*/
    /**
     * 
     * @param nomeDownCase
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectByNameAndPISAndEndDiscount(String nomeDownCase, int ritiroInNegozio)  //PIS = Pickup In Store
    {
        return "SELECT * FROM Oggetto WHERE nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * 
     * @param idCategoria
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectByCategoryAndPISAndEndDiscount(int idCategoria, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE categoria = " + idCategoria + " AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * 
     * @param idCategoria
     * @param nomeDownCase
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectByCategoryAndNameAndPISAndEndDiscount(int idCategoria, String nomeDownCase, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * 
     * @param prezzo
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectLowerThanPriceAndPISAndEndDiscount(double prezzo, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE prezzo >= " + prezzo + " AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * 
     * @param prezzo
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectHigherThanPriceAndPISAndEndDiscount(double prezzo, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE prezzo <= " + prezzo + " AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * 
     * @param prezzoMin
     * @param prezzoMax
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectBetweenPricesAndPISAndEndDiscount(double prezzoMin, double prezzoMax, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * 
     * @param idCategoria
     * @param prezzoMin
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectByCategoryAndHigherThanPriceAndPISAndEndDiscount(int idCategoria, double prezzoMin, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo >= " + prezzoMin + " AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * 
     * @param idCategoria
     * @param prezzoMax
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectByCategoryAndLowerThanPriceAndPISAndEndDiscount(int idCategoria, double prezzoMax, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo <= " + prezzoMax + " AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * 
     * @param idCategoria
     * @param prezzoMin
     * @param prezzoMax
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectByCategoryAndBetweenPricesAndPISAndEndDiscount(int idCategoria, double prezzoMin, double prezzoMax, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * 
     * @param nomeDownCase
     * @param prezzoMin
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectByNameAndHigherThanPriceAndPISAndEndDiscount(String nomeDownCase, double prezzoMin, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE prezzo >= " + prezzoMin + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * 
     * @param nomeDownCase
     * @param prezzoMax
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectByNameAndLowerThanPriceAndPISAndEndDiscount(String nomeDownCase, double prezzoMax, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE prezzo <= " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * 
     * @param nomeDownCase
     * @param prezzoMin
     * @param prezzoMax
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectByNameAndBetweenPricesAndPISAndEndDiscount(String nomeDownCase, double prezzoMin, double prezzoMax, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * 
     * @param idCategoria
     * @param nomeDownCase
     * @param prezzoMin
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectByCategoryAndNameAndHigherThanPriceAndPISAndEndDiscount(int idCategoria, String nomeDownCase, double prezzoMin, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo >= " + prezzoMin + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * 
     * @param idCategoria
     * @param nomeDownCase
     * @param prezzoMax
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectByCategoryAndNameAndLowerThanPriceAndPISAndEndDiscount(int idCategoria, String nomeDownCase, double prezzoMax, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo <= " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
    
    /**
     * 
     * @param idCategoria
     * @param nomeDownCase
     * @param prezzoMin
     * @param prezzoMax
     * @param ritiroInNegozio
     * @return 
     */
    public static String selectObjectByCategoryAndNameAndBetweenPricesAndPISAndEndDiscount(int idCategoria, String nomeDownCase, double prezzoMin, double prezzoMax, int ritiroInNegozio)
    {
        return "SELECT * FROM Oggetto WHERE categoria=" + idCategoria + " AND prezzo BETWEEN " + prezzoMin + " AND " + prezzoMax + " AND nomeDownCase LIKE '%" + nomeDownCase + "%' AND Oggetto.`dataFineSconto` IS NOT NULL AND Oggetto.ritiroInNegozio = " + ritiroInNegozio + ";";
    }
}
