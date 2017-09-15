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
          
    
    /*--- LAST UPDATE -> 2017-09-14 ---*/
    
    /**
     * @author Brugix
     * Ottenere la lista dei negozi che vendono prodotti di una certa categoria
     * @param sellerId
     * @param active
     * @param catId
     * @return String: lista dei negozi
     */
    public static String selectShopByCategory(int sellerId, int active, int catId)
    {
        return "SELECT * "
                + "FROM ((Oggetto JOIN Categoria ON oggetto.categoria=Categoria.id) "
                + "JOIN Negozio ON Negozio.id=Oggetto.idNegozio) "
                + "JOIN Utente ON Utente.id=Negozio.idVenditore "
                + "WHERE idVenditore=" + sellerId + " AND attivo=" + active + " AND Categoria.id=" + catId + ";";
    }
          
    /**
     * @author Brugix
     * Ottenere la lista dei negozi che vendono prodotti di una certa categoria ordinate da quello con più vendite
     * @param sellerId
     * @param active
     * @param catId
     * @param objCatId
     * @return String: lista dei negozi
     */
    public static String selectShopWithHigherSalesByCategory(int sellerId, int active, int catId, int objCatId)
    {
        return "SELECT Negozio.*, COUNT(Ordine.idOrdine) "
                + "FROM ((Oggetto JOIN Negozio ON Negozio.id = Oggetto.idNegozio) "
                + "JOIN Utente ON Utente.id = Negozio.idVenditore) "
                + "JOIN Ordine ON Ordine.idNegozio = Negozio.id "
                + "WHERE idVenditore = " + sellerId + " AND attivo = " + active 
                + " AND Categoria.id = " + catId + " AND Oggetto.categoria = " + objCatId
                + " AND Ordine.stato BETWEEN 1 AND 4 GROUP BY Negozio.id "
                + "ORDER BY COUNT(Ordine.idOrdine) DESC;";
    }
          
    /**
     * @author Brugix
     * Ottenere la lista dei negozi che vendono prodotti di una certa categoria ordinate da quello con meno vendite
     * @param sellerId
     * @param active
     * @param catId
     * @param objCatId
     * @return String: lista dei negozi
     */
    public static String selectShopWithLowerSalesByCategory(int sellerId, int active, int catId, int objCatId)
    {
        return "SELECT Negozio.*, COUNT(Ordine.idOrdine) "
                + "FROM ((Oggetto JOIN Negozio ON Negozio.id = Oggetto.idNegozio) "
                + "JOIN Utente ON Utente.id = Negozio.idVenditore) "
                + "JOIN Ordine ON Ordine.idNegozio = Negozio.id "
                + "WHERE idVenditore = " + sellerId + " AND attivo = " + active 
                + " AND Categoria.id = " + catId + " AND Oggetto.categoria = " + objCatId
                + " AND Ordine.stato BETWEEN 1 AND 4 GROUP BY Negozio.id "
                + "ORDER BY COUNT(Ordine.idOrdine) DESC;";
    }
          
    /**
     * @author Brugix
     * Ottenere la lista dei propri negozi ordinati per data di apertura
     * @param sellerId
     * @return String: lista dei negozi
     */
    public static String selectShopByOpenDate(int sellerId)
    {
        return "SELECT * FROM Negozio WHERE idVenditore=" + sellerId + " ORDER BY dataApertura DESC;";
    }
          
    /**
     * @author Brugix
     * Ottenere la lista dei propri negozi ordinati per fatturato
     * @param sellerId
     * @return String: lista dei negozi
     */
    public static String selectShopByRevenue(int sellerId)
    {
        return "SELECT SUM(Ordine.quantita * Oggetto.prezzo) AS fatturato, Ordine.idNegozio, Negozio.nomeNegozio "
                + "FROM (Oggetto JOIN Ordine ON Oggetto.id = Ordine.idOggetto) "
                + "JOIN Negozio ON Negozio.id = Ordine.idNegozio "
                + "WHERE Negozio.idVenditore = " + sellerId
                + " AND Ordine.stato BETWEEN 1 AND 4 "
                + "GROUP BY Negozio.id ORDER BY fatturato DESC;";
    }
          
    /**
     * @author Brugix
     * Ottenere i dati di un negozio
     * @param sellerId
     * @param shopId
     * @return String: lista dei negozi
     */
    public static String selectShopBySellerIDAndShopID(int sellerId, int shopId)
    {
        return "SELECT * FROM Negozio WHERE idVenditore=" + sellerId + " AND id=" + shopId + ";";
    }
          
    /**
     * @author Brugix
     * Ottenere i dati delle vendite di un determinato negozio
     * @param shopId
     * @return String: lista degli ordini
     */
    public static String selectOrderByShopID(int shopId)
    {
        return "SELECT * FROM Ordine WHERE Ordine.idNegozio = " + shopId + "AND Ordine.stato BETWEEN 1 AND 4;";
    }
          
    /**
     * @author Brugix
     * Ottenere i dati di vendita di un determinato negozio in una determinata categoria
     * @param shopId
     * @param catId
     * @return String: lista degli ordini
     */
    public static String selectOrderByCategory(int shopId, int catId)
    {
        return "SELECT Ordine.* FROM Ordine JOIN Oggetto ON Oggetto.id = Ordine.idOggetto"
                + "WHERE Ordine.idNegozio = " + shopId
                + " AND Ordine.stato BETWEEN 1 AND 4 AND Oggetto.categoria = " + catId + ";";
    }
          
    /**
     * @author Brugix
     * Ottenere le richieste di assistenza in cui si è stati citati
     * @param sellerId
     * @return String: lista delle richieste di assistenza
     */
    public static String selectServiceRequestBySellerID(int sellerId)
    {
        return "SELECT * FROM Utente JOIN Assistenza ON Utente.id=Assistenza.idVenditore "
                + "WHERE idVenditore=" + sellerId + ";";
    }
          
    /**
     * @author Brugix
     * Ottenere la lista dei prodotti venduti raggruppati per categoria e negozio
     * @param sellerId
     * @return String: lista dei prodotti
     */
    public static String selectProductSaledGroupByCategoryAndShop(int sellerId)
    {
        return "SELECT * FROM (Oggetto JOIN Categoria ON Oggetto.categoria=Categoria.id) "
                + "JOIN Negozio ON Negozio.id=Oggetto.idNegozio "
                + "WHERE idVenditore=" + sellerId + " AND stato=4 "
                + "GROUP BY idNegozio ASC, Categoria.nome ASC;";
    }
          
    /**
     * @author Brugix
     * Ottenere la lista dei prodotti venduti in una determinata categoria raggruppati per negozio
     * @param sellerId
     * @param cat
     * @return String: lista dei prodotti
     */
    public static String selectProductSaledGroupByShop(int sellerId, String cat)
    {
        return "SELECT * FROM (Oggetto JOIN Categoria ON Oggetto.categoria=Categoria.id) "
                + "JOIN Negozio ON Negozio.id=Oggetto.idNegozio "
                + "WHERE idVenditore=" + sellerId + " AND stato=4 AND Categoria.nome=" + cat
                + " GROUP BY idNegozio ASC;";
    }
          
    /**
     * @author Brugix
     * Ottenere la lista dei prodotti venduti ordinati per valutazioni
     * @param sellerId
     * @return String: lista dei prodotti
     */
    public static String selectProductSaledOrderedByRating(int sellerId)
    {
        return "SELECT * FROM (Ordine JOIN RecensioneOggetto ON Ordine.idOggetto=RecensioneOggetto.idOggetto) "
                + "JOIN Negozio ON Ordine.idNegozio=Negozio.id "
                + "WHERE idVenditore=" + sellerId + " AND stato=4 "
                + "ORDER BY AVG(RecensioneOggetto.valutazione) DESC;";
    }
          
    /**
     * @author Brugix
     * Ottenere la lsita dei propri negozi ordinati per recensioni
     * @param sellerId
     * @return String: lista dei negozi
     */
    public static String selectShopOrderedByRating(int sellerId)
    {
        return "SELECT * FROM Negozio WHERE idVenditore=" + sellerId + " ORDER BY valutazione DESC;";
    }
          
    /**
     * @author Brugix
     * Ottenere la lista dei proprio prodotti in sconto raggruppati per categoria e negozio
     * @param sellerId
     * @return String: lista dei prodotti
     */
    public static String selectProductDiscountedGroupByShopAndCategory(int sellerId)
    {
        return "SELECT * FROM (Oggetto JOIN Categoria ON Oggetto.categoria=Categoria.id) "
                + "JOIN Negozio ON Negozio.id=Oggetto.idNegozio "
                + "WHERE idVenditore=" + sellerId + " AND sconto>0 "
                + "GROUP BY Categoria.nome ASC, idNegozio ASC;";
    }
          
    /**
     * @author Brugix
     * Ottenere la lista dei proprio prodotti in sconto raggruppati per categoria e negozio ordinati per scadenza più vicina dello sconto
     * @param sellerId
     * @return String: lista dei prodotti
     */
    public static String selectProductsDiscountedGrupByShopAndCategoryOrderedByDeadline(int sellerId)
    {
        return "SELECT * FROM (Oggetto JOIN Categoria ON Oggetto.categoria=Categoria.id) "
                + "JOIN Negozio ON Negozio.id=Oggetto.idNegozio "
                + "WHERE idVenditore=" + sellerId + " AND sconto>0 "
                + "GROUP BY Categoria.nome ASC, idNegozio ASC "
                + "ORDER BY dataFineSconto ASC;";
    }
          
    /**
     * @author Brugix
     * Ottenere la lista delle recensioni ricevute
     * @param sellerId
     * @return String: lista delle recensioni
     */
    public static String selectSellerReview(int sellerId)
    {
        return "SELECT * FROM RecensioneVenditore WHERE idVenditore=" + sellerId + ";";
    }
    
    /**
     * @author Brugix
     * Aggiungere un proprio negozio
     * @param sellerId
     * @param shopName
     * @param rating
     * @param idI
     * @return String: conferma avvenuta operazione
     */
    public static String insertShop(int sellerId, String shopName, double rating, int idI)
    {
        return "INSERT INTO Negozio (idVenditore, nomeNegozio, valutazione, attivo, idI, dataApertura) "
                + "VALUES (" + sellerId + "," +  shopName + "," + rating + ", '1' ," + idI + ", CURRENT_TIMESTAMP);";
    }
    
    /**
     * @author Brugix
     * Chiudere un proprio negozio (rimuoverlo)
     * @param shopId
     * @return String: conferma avvenuta operazione
     */
    public static String updateShopStatus(int shopId)
    {
        return "UPDATE Negozio SET attivo=0 WHERE id=" + shopId + ";";
    }
    
    /**
     * @author Brugix
     * Modificare lo stato di un ordine
     * @param newStatus
     * @param orderId
     * @return String: conferma avvenuta operazione
     */
    public static String updateOrderStatus(int newStatus, int orderId)
    {
        return "UPDATE Ordine SET stato=" + newStatus + " WHERE idOrdine=" + orderId + ";";
    }
    
    /**
     * @author Brugix
     * Aggiungere ad un ordine spedito il codice di tracking
     * @param track
     * @param orderId
     * @return String: conferma avvenuta operazione
     */
    public static String updateOrderTracking(String track, int orderId)
    {
        return "UPDATE Ordine SET codiceTracking=" + track + " WHERE idOrdine=" + orderId + ";";
    }
    
    /**
     * @author Brugix
     * Aggiungere un prodotto ad un proprio negozio
     * @param shopId
     * @param prodName
     * @param prodPrice
     * @param prodDescr
     * @param pickupInStore
     * @param availability
     * @param availabilityStatus
     * @param discount
     * @param endDiscount
     * @param catId
     * @return String: conferma avvenuta operazione
     */
    public static String insertProduct(int shopId, String prodName, double prodPrice, 
            String prodDescr, int pickupInStore, int availability, int availabilityStatus, 
            double discount, /*??? VARIAZIONE*/ String endDiscount, int catId)
    {
        return "INSERT INTO Oggetto "
                + "(idNegozio, nome, prezzo, descrizione, ritiroInNegozio, disponibilita, "
                + "statoDisponibilita, sconto, variazione, dataFineSconto, categoria) "
                + "VALUES ("
                + shopId
                + ", " + prodName
                + ", " + prodPrice
                + ", " + prodDescr
                + ", " + pickupInStore
                + ", " + availability
                + ", " + availabilityStatus
                + ", " + discount
                /*+ ", VARIAZIONE"*/
                + ", " + endDiscount
                + ", " + catId
                + ");";
    }
    
    /**
     * @author Brugix
     * Rimuovere un oggetto da un proprio negozio
     * @param shopId
     * @return String: conferma avvenuta operazione
     */
    public static String deleteProduct(int shopId)
    {
        return "DELETE FROM Oggetto WHERE id=" + shopId + ";";
    }
    
    /**
     * @author Brugix
     * Modificare il prezzo di un oggetto di un proprio negozio
     * @param prodId
     * @param price
     * @return String: conferma avvenuta operazione
     */
    public static String updateProductPrice(int prodId, double price)
    {
        return "UPDATE Oggetto SET prezzo=" + price + " WHERE id=" + prodId + ";";
    }
    
    /**
     * @author Brugix
     * Modificare lo sconto di un oggetto
     * @param prodId
     * @param discount
     * @return String: conferma avvenuta operazione
     */
    public static String updateProductDiscount(int prodId, double discount)
    {
        return "UPDATE Oggetto SET sconto=" + discount + " WHERE id=" + prodId + ";";
    }
    
    /*??? 
    -- rimuovere uno sconto dagli oggetti in sconto
            UPDATE Oggetto SET prezzo=0 WHERE id=ID;
    
    
    
    -- aggiungere una foto ad un prodotto
    -- aggiungere una foto ad un negozio
    -- rimuovere una foto di un prodotto
    -- rimuovere una foto di un negozio
    */
}
