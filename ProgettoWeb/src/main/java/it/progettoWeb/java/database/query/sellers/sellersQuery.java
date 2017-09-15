/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.query.sellers;
/**
 *
 * @author mattia
 */
public class sellersQuery {
    public static String hello() {
        return "Hello from" + sellersQuery.class.toString();
    }
    
    /*--- LAST UPDATE -> 2017-09-14 ---*/
    
    
    /**
     * @author Brugix
     * Ottenere la lista dei propri negozi
     * @param sellerId
     * @return String: lista dei negozi
     */
    public static String selectShopsBySellerID(int sellerId)
    {
        return "SELECT * FROM Negozio WHERE idVenditore = " + sellerId + ";";
    }
    
    /**
     * @author Brugix
     * Ottenere la lista degli ordini ricevuti
     * @param sellerId
     * @return String: lista degli ordini
     */
    public static String selectOrdersBySellerID(int sellerId)
    {
        return "SELECT * FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id WHERE idVenditore=" + sellerId + ";";
    }
          
    /**
     * @author Brugix
     * Ottenere la lista degli ordini ricevuti dal più nuovo al più vecchio
     * @param sellerId
     * @return String: lista degli ordini
     */
    public static String selectNewestToOldestOrdersBySellerID(int sellerId)
    {
        return "SELECT * FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id WHERE idVenditore=" + sellerId + " ORDER BY dataOrdine DESC;";
    }
          
    /**
     * @author Brugix
     * Ottenere la lista degli ordini non ancora in carico
     * @param sellerId
     * @return String: lista degli ordini
     */
    public static String selectOrderNotConfirmedBySellerID(int sellerId) //??? non ancora in carico -> Not Confirmed ???
    {
        return "SELECT * FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id WHERE idVenditore=" + sellerId + " AND stato=1;";
    }
          
    /**
     * @author Brugix
     * Ottenere la lista degli ordini in lavorazione
     * @param sellerId
     * @return String: lista degli ordini
     */
    public static String selectOrderConfirmedBySellerID(int sellerId) //??? In lavorazione -> Confirmed ???
    {
        return "SELECT * FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id WHERE idVenditore=" + sellerId + " AND stato=2;";
    }
          
    /**
     * @author Brugix
     * Ottenere la lista degli ordini già spediti
     * @param sellerId
     * @return String: lista degli ordini
     */
    public static String selectOrderShippedBySellerID(int sellerId)
    {
        return "SELECT * FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id WHERE idVenditore=" + sellerId + " AND stato=3;";
    }
          
    /**
     * @author Brugix
     * Ottenere la lista degli ordini conclusi
     * @param sellerId
     * @return String: lista degli ordini
     */
    public static String selectOrderClosedBySellerID(int sellerId)
    {
        return "SELECT * FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id WHERE idVenditore=" + sellerId + " AND stato=4;";
    }
          
    /**
     * @author Brugix
     * Ottenere la lista degli ordini ricevuti in un determinato giorno
     * @param sellerId
     * @param date
     * @return String: lista degli ordini
     */
    public static String selectOrderByDate(int sellerId, String date)
    {
        return "SELECT * FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id WHERE idVenditore=" + sellerId + " AND dataOrdine=" + date + ";";
    }
          
    /**
     * @author Brugix
     * Ottenere la lista degli ordini ricevuti nella settimana corrente
     * @param sellerId
     * @param date
     * @return String: lista degli ordini
     */
    public static String selectOrderByWeek(int sellerId, String date)
    {
        return "SELECT * FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id WHERE idVenditore=" + sellerId + " AND WEEK(dataOrdine)=WEEK(" + date + ");";
    }
          
    /**
     * @author Brugix
     * Ottenere la lista degli ordini ricevuti nel mese corrente
     * @param sellerId
     * @param date
     * @return String: lista degli ordini
     */
    public static String selectOrderByMonth(int sellerId, String date)
    {
        return "SELECT * FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id WHERE idVenditore=" + sellerId + " AND MONTH(dataOrdine)=MONTH(" + date + ");";
    }
          
    /**
     * @author Brugix
     * Ottenere la lista degli ordini ricevuti nell'anno corrente
     * @param sellerId
     * @param date
     * @return String: lista degli ordini
     */
    public static String selectOrderByYear(int sellerId, String date)
    {
        return "SELECT * FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id WHERE idVenditore=" + sellerId + " AND YEAR(dataOrdine)=YEAR(" + date + ");";
    }
          
    /**
     * @author Brugix
     * Ottenere la lista dei propri negozi con anche il numero di vendite
     * @param sellerId
     * @return String: lista dei negozi
     */
    public static String selectShopAndSalesNumberBySellerID(int sellerId)
    {
        return "SELECT idNegozio, COUNT(idOrdine) AS numeroVendite "
                + "FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id "
                + "WHERE idVenditore=" + sellerId + " GROUP BY idNegozio;";
    }
          
    /**
     * @author Brugix
     * Ottenere la lista dei propri negozi ordinati per vendite maggiori
     * @param sellerId
     * @return String: lista dei negozi
     */
    public static String selectShopWithHigherSalesBySellerID(int sellerId)
    {
        return "SELECT idNegozio, COUNT(idOrdine) AS numeroVendite "
                + "FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id "
                + "WHERE idVenditore=" + sellerId
                + " GROUP BY idNegozio "
                + "ORDER BY COUNT(idOrdine) DESC;";
    }
          
    /**
     * @author Brugix
     * Ottenere la lista dei propri negozi per vendite minori
     * @param sellerId
     * @return String: lista dei negozi
     */
    public static String selectShopWithLowestSalesBySellerID(int sellerId)
    {
        return "SELECT idNegozio, COUNT(idOrdine) AS numeroVendite "
                + "FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id "
                + "WHERE idVenditore=" + sellerId
                + " GROUP BY idNegozio "
                + "ORDER BY COUNT(idOrdine) ASC;";
    }
          
    /**
     * @author Brugix
     * Ottenere la lista dei propri negozi con vendite inferiori ad un certo valore
     * @param sellerId
     * @param value
     * @return String: lista dei negozi
     */
    public static String selectShopWithLowerSalesThanBySellerID(int sellerId, int value)
    {
        return "SELECT idNegozio, COUNT(idOrdine) AS numeroVendite "
                + "FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id "
                + "WHERE idVenditore=" + sellerId
                + " GROUP BY idNegozio"
                + " HAVING COUNT(idOrdine)<" + value + ";";
    }
          
    /**
     * @author Brugix
     * Ottenere la lista dei propri negozi con vendite superiori ad un certo valore
     * @param sellerId
     * @param value
     * @return String: lista dei negozi
     */
    public static String selectShopWithHigherSalesThanBySellerID(int sellerId, int value)
    {
        return "SELECT idNegozio, COUNT(idOrdine) AS numeroVendite "
                + "FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id "
                + "WHERE idVenditore=" + sellerId
                + " GROUP BY idNegozio"
                + " HAVING COUNT(idOrdine)>" + value + ";";
    }
          
    
    /*--- DA QUI cominciano le query modificate ---*/
    /*
    
    public static String a()
    {
        return ;
    }
          
    
    public static String a()
    {
        return ;
    }
          
    
    public static String a()
    {
        return ;
    }
          
    
    public static String a()
    {
        return ;
    }
          
    
    public static String a()
    {
        return ;
    }
          
    
    public static String a()
    {
        return ;
    }
          
    
    public static String a()
    {
        return ;
    }
          
    
    public static String a()
    {
        return ;
    }
          
    
    public static String a()
    {
        return ;
    }
          
    
    public static String a()
    {
        return ;
    }
          
    
    public static String a()
    {
        return ;
    }
          
    
    public static String a()
    {
        return ;
    }
          
    
    public static String a()
    {
        return ;
    }
          
    
    public static String a()
    {
        return ;
    }
          
    
    public static String a()
    {
        return ;
    }
          
    
    public static String a()
    {
        return ;
    }
          
    */
    
}
