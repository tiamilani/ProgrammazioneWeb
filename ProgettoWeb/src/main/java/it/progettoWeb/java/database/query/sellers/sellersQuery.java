/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.query.sellers;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author mattia
 */
public class sellersQuery {
    public static String hello() {
        return "Hello from" + sellersQuery.class.toString();
    }
    
    /*--- LAST UPDATE -> 2017-09-17 ---*/
    
    
    /**
     * @author fbrug
     * Ottenere la lista dei propri negozi
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista dei negozi
     */
    public static String selectShopsBySellerID(int idVenditore)
    {
        return "SELECT * FROM Negozio WHERE idVenditore = " + idVenditore + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista degli ordini ricevuti
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista degli ordini
     */
    public static String selectOrdersBySellerID(int idVenditore)
    {
        return "SELECT * FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id WHERE idVenditore=" + idVenditore + ";";
    }
          
    /**
     * @author fbrug
     * Ottenere la lista degli ordini ricevuti dal più nuovo al più vecchio
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista degli ordini
     */
    public static String selectNewestToOldestOrdersBySellerID(int idVenditore)
    {
        return "SELECT * FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id WHERE idVenditore=" + idVenditore + " ORDER BY dataOrdine DESC;";
    }
    
    
    
    /**
     * @author fbrug
     * Ottenere la lista degli ordini in base al loro stato
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @param stato: intero rappresentante lo stato dell'ordine (0 = nel carrello, 1 = pagato, 2 = in lavorazione, 3 = spedito, 4 = consegnato = nella lista desideri)
     * @return String: lista degli ordini
     */
    public static String selectOrderBySellerIDAndStatus(int idVenditore, int stato)
    {
        return "SELECT * FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id WHERE idVenditore=" + idVenditore + " AND stato=" + stato + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista degli ordini ricevuti in un determinato giorno
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @param dataOrdine: data usata per trovare ordini
     * @return String: lista degli ordini
     */
    public static String selectOrderByDate(int idVenditore, Timestamp dataOrdine)
    {
        return "SELECT * FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id WHERE idVenditore=" + idVenditore + " AND dataOrdine=" + dataOrdine + ";";
    }
          
    /**
     * @author fbrug
     * Ottenere la lista degli ordini ricevuti nella settimana corrente
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @param dataOrdine: data usata per trovare ordini
     * @return String: lista degli ordini
     */
    public static String selectOrderByWeek(int idVenditore, Timestamp dataOrdine)
    {
        return "SELECT * FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id WHERE idVenditore=" + idVenditore + " AND WEEK(dataOrdine)=WEEK(" + dataOrdine + ");";
    }
          
    /**
     * @author fbrug
     * Ottenere la lista degli ordini ricevuti nel mese corrente
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @param dataOrdine: data usata per trovare ordini
     * @return String: lista degli ordini
     */
    public static String selectOrderByMonth(int idVenditore, Timestamp dataOrdine)
    {
        return "SELECT * FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id WHERE idVenditore=" + idVenditore + " AND MONTH(dataOrdine)=MONTH(" + dataOrdine + ");";
    }
          
    /**
     * @author fbrug
     * Ottenere la lista degli ordini ricevuti nell'anno corrente
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @param dataOrdine: data usata per trovare ordini
     * @return String: lista degli ordini
     */
    public static String selectOrderByYear(int idVenditore, Timestamp dataOrdine)
    {
        return "SELECT * FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id WHERE idVenditore=" + idVenditore + " AND YEAR(dataOrdine)=YEAR(" + dataOrdine + ");";
    }
          
    /**
     * @author fbrug
     * Ottenere la lista dei propri negozi con anche il numero di vendite
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista dei negozi
     */
    public static String selectShopAndSalesNumberBySellerID(int idVenditore)
    {
        return "SELECT idNegozio, COUNT(idOrdine) AS numeroVendite "
                + "FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id "
                + "WHERE idVenditore=" + idVenditore + " GROUP BY idNegozio;";
    }
          
    /**
     * @author fbrug
     * Ottenere la lista dei propri negozi ordinati per vendite maggiori
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista dei negozi
     */
    public static String selectShopWithHigherSalesBySellerID(int idVenditore)
    {
        return "SELECT idNegozio, COUNT(idOrdine) AS numeroVendite "
                + "FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id "
                + "WHERE idVenditore=" + idVenditore
                + " GROUP BY idNegozio "
                + "ORDER BY COUNT(idOrdine) DESC;";
    }
          
    /**
     * @author fbrug
     * Ottenere la lista dei propri negozi per vendite minori
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista dei negozi
     */
    public static String selectShopWithLowestSalesBySellerID(int idVenditore)
    {
        return "SELECT idNegozio, COUNT(idOrdine) AS numeroVendite "
                + "FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id "
                + "WHERE idVenditore=" + idVenditore
                + " GROUP BY idNegozio "
                + "ORDER BY COUNT(idOrdine) ASC;";
    }
          
    /**
     * @author fbrug
     * Ottenere la lista dei propri negozi con vendite inferiori ad un certo valore
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @param valore: intero rappresentante il numero di vendite del negozio
     * @return String: lista dei negozi
     */
    public static String selectShopWithLowerSalesThanBySellerID(int idVenditore, int valore)
    {
        return "SELECT idNegozio, COUNT(idOrdine) AS numeroVendite "
                + "FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id "
                + "WHERE idVenditore=" + idVenditore
                + " GROUP BY idNegozio"
                + " HAVING COUNT(idOrdine)<" + valore + ";";
    }
          
    /**
     * @author fbrug
     * Ottenere la lista dei propri negozi con vendite superiori ad un certo valore
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @param valore: intero rappresentante il numero di vendite del negozio
     * @return String: lista dei negozi
     */
    public static String selectShopWithHigherSalesThanBySellerID(int idVenditore, int valore)
    {
        return "SELECT idNegozio, COUNT(idOrdine) AS numeroVendite "
                + "FROM Ordine JOIN Negozio ON Ordine.idNegozio=Negozio.id "
                + "WHERE idVenditore=" + idVenditore
                + " GROUP BY idNegozio"
                + " HAVING COUNT(idOrdine)>" + valore + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista dei negozi che vendono prodotti di una certa categoria
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @param attivo: intero rappresentante la situazione del negozio (0 = chiuso, 1 = in attività)
     * @param idCategoria: intero rappresentante l'ID della categoria
     * @return String: lista dei negozi
     */
    public static String selectShopByCategory(int idVenditore, int attivo, int idCategoria)
    {
        return "SELECT * "
                + "FROM ((Oggetto JOIN Categoria ON oggetto.categoria=Categoria.id) "
                + "JOIN Negozio ON Negozio.id=Oggetto.idNegozio) "
                + "JOIN Utente ON Utente.id=Negozio.idVenditore "
                + "WHERE idVenditore=" + idVenditore + " AND attivo=" + attivo + " AND Categoria.id=" + idCategoria + ";";
    }
          
    /**
     * @author fbrug
     * Ottenere la lista dei negozi che vendono prodotti di una certa categoria ordinate da quello con più vendite
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @param attivo: intero rappresentante la situazione del negozio (0 = chiuso, 1 = in attività)
     * @param idCategoria: intero rappresentante l'ID della categoria
     * @param categoriaOggetto: intero rappresentante la cetegoria dell'oggetto ricercato
     * @return String: lista dei negozi
     */
    public static String selectShopWithHigherSalesByCategory(int idVenditore, int attivo, int idCategoria, int categoriaOggetto)
    {
        return "SELECT Negozio.*, COUNT(Ordine.idOrdine) "
                + "FROM ((Oggetto JOIN Negozio ON Negozio.id = Oggetto.idNegozio) "
                + "JOIN Utente ON Utente.id = Negozio.idVenditore) "
                + "JOIN Ordine ON Ordine.idNegozio = Negozio.id "
                + "WHERE idVenditore = " + idVenditore + " AND attivo = " + attivo 
                + " AND Categoria.id = " + idCategoria + " AND Oggetto.categoria = " + categoriaOggetto
                + " AND Ordine.stato BETWEEN 1 AND 4 GROUP BY Negozio.id "
                + "ORDER BY COUNT(Ordine.idOrdine) DESC;";
    }
          
    /**
     * @author fbrug
     * Ottenere la lista dei negozi che vendono prodotti di una certa categoria ordinate da quello con meno vendite
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @param attivo: intero rappresentante la situazione del negozio (0 = chiuso, 1 = in attività)
     * @param idCategoria: intero rappresentante l'ID della categoria
     * @param categoriaOggetto: intero rappresentante la cetegoria dell'oggetto ricercato
     * @return String: lista dei negozi
     */
    public static String selectShopWithLowerSalesByCategory(int idVenditore, int attivo, int idCategoria, int categoriaOggetto)
    {
        return "SELECT Negozio.*, COUNT(Ordine.idOrdine) "
                + "FROM ((Oggetto JOIN Negozio ON Negozio.id = Oggetto.idNegozio) "
                + "JOIN Utente ON Utente.id = Negozio.idVenditore) "
                + "JOIN Ordine ON Ordine.idNegozio = Negozio.id "
                + "WHERE idVenditore = " + idVenditore + " AND attivo = " + attivo 
                + " AND Categoria.id = " + idCategoria + " AND Oggetto.categoria = " + categoriaOggetto
                + " AND Ordine.stato BETWEEN 1 AND 4 GROUP BY Negozio.id "
                + "ORDER BY COUNT(Ordine.idOrdine) DESC;";
    }
          
    /**
     * @author fbrug
     * Ottenere la lista dei propri negozi ordinati per data di apertura
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista dei negozi
     */
    public static String selectShopByOpenDate(int idVenditore)
    {
        return "SELECT * FROM Negozio WHERE idVenditore=" + idVenditore + " ORDER BY dataApertura DESC;";
    }
          
    /**
     * @author fbrug
     * Ottenere la lista dei propri negozi ordinati per fatturato
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista dei negozi
     */
    public static String selectShopByRevenue(int idVenditore)
    {
        return "SELECT SUM(Ordine.quantita * Oggetto.prezzo) AS fatturato, Ordine.idNegozio, Negozio.nomeNegozio "
                + "FROM (Oggetto JOIN Ordine ON Oggetto.id = Ordine.idOggetto) "
                + "JOIN Negozio ON Negozio.id = Ordine.idNegozio "
                + "WHERE Negozio.idVenditore = " + idVenditore
                + " AND Ordine.stato BETWEEN 1 AND 4 "
                + "GROUP BY Negozio.id ORDER BY fatturato DESC;";
    }
          
    /**
     * @author fbrug
     * Ottenere i dati di un negozio
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @param idNegozio: intero rappresentante l'ID del negozio
     * @return String: lista dei negozi
     */
    public static String selectShopBySellerIDAndShopID(int idVenditore, int idNegozio)
    {
        return "SELECT * FROM Negozio WHERE idVenditore=" + idVenditore + " AND id=" + idNegozio + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere i dati delle vendite di un determinato negozio
     * @param idNegozio: intero rappresentante l'ID del negozio
     * @return String: lista degli ordini
     */
    public static String selectOrderByShopID(int idNegozio)
    {
        return "SELECT * FROM Ordine WHERE Ordine.idNegozio = " + idNegozio + "AND Ordine.stato BETWEEN 1 AND 4;";
    }
          
    /**
     * @author fbrug
     * Ottenere i dati di vendita di un determinato negozio in una determinata categoria
     * @param idNegozio: intero rappresentante l'ID del negozio
     * @param idCategoria: intero rappresentante l'ID della categoria
     * @return String: lista degli ordini
     */
    public static String selectOrderByCategory(int idNegozio, int idCategoria)
    {
        return "SELECT Ordine.* FROM Ordine JOIN Oggetto ON Oggetto.id = Ordine.idOggetto"
                + "WHERE Ordine.idNegozio = " + idNegozio
                + " AND Ordine.stato BETWEEN 1 AND 4 AND Oggetto.categoria = " + idCategoria + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere le richieste di assistenza in cui si è stati citati
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista delle richieste di assistenza
     */
    public static String selectServiceRequestBySellerID(int idVenditore)
    {
        return "SELECT * FROM Utente JOIN Assistenza ON Utente.id=Assistenza.idVenditore "
                + "WHERE idVenditore=" + idVenditore + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere la lista dei prodotti venduti raggruppati per categoria e negozio
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista dei prodotti
     */
    public static String selectObjectSaledGroupByCategoryAndShop(int idVenditore)
    {
        return "SELECT * FROM (Oggetto JOIN Categoria ON Oggetto.categoria=Categoria.id) "
                + "JOIN Negozio ON Negozio.id=Oggetto.idNegozio "
                + "WHERE idVenditore=" + idVenditore + " AND stato=4 "
                + "GROUP BY idNegozio ASC, Categoria.nome ASC;";
    }
          
    /**
     * @author fbrug
     * Ottenere la lista dei prodotti venduti in una determinata categoria raggruppati per negozio
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @param nomeCategoria: rappresenta il nome della categoria da ricercare
     * @return String: lista dei prodotti
     */
    public static String selectObjectSaledGroupByShop(int idVenditore, String nomeCategoria)
    {
        return "SELECT * FROM (Oggetto JOIN Categoria ON Oggetto.categoria=Categoria.id) "
                + "JOIN Negozio ON Negozio.id=Oggetto.idNegozio "
                + "WHERE idVenditore=" + idVenditore + " AND stato=4 AND Categoria.nome=" + nomeCategoria
                + " GROUP BY idNegozio ASC;";
    }
          
    /**
     * @author fbrug
     * Ottenere la lista dei prodotti venduti ordinati per valutazioni
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista dei prodotti
     */
    public static String selectObjectSaledOrderedByRating(int idVenditore)
    {
        return "SELECT * FROM (Ordine JOIN RecensioneOggetto ON Ordine.idOggetto=RecensioneOggetto.idOggetto) "
                + "JOIN Negozio ON Ordine.idNegozio=Negozio.id "
                + "WHERE idVenditore=" + idVenditore + " AND stato=4 "
                + "ORDER BY AVG(RecensioneOggetto.valutazione) DESC;";
    }
          
    /**
     * @author fbrug
     * Ottenere la lista dei propri negozi ordinati per recensioni
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista dei negozi
     */
    public static String selectShopOrderedByRating(int idVenditore)
    {
        return "SELECT * FROM Negozio WHERE idVenditore=" + idVenditore + " ORDER BY valutazione DESC;";
    }
          
    /**
     * @author fbrug
     * Ottenere la lista dei proprio prodotti in sconto raggruppati per categoria e negozio
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista dei prodotti
     */
    public static String selectObjectDiscountedGroupByShopAndCategory(int idVenditore)
    {
        return "SELECT * FROM (Oggetto JOIN Categoria ON Oggetto.categoria=Categoria.id) "
                + "JOIN Negozio ON Negozio.id=Oggetto.idNegozio "
                + "WHERE idVenditore=" + idVenditore + " AND sconto>0 "
                + "GROUP BY Categoria.nome ASC, idNegozio ASC;";
    }
          
    /**
     * @author fbrug
     * Ottenere la lista dei proprio prodotti in sconto raggruppati per categoria e negozio ordinati per scadenza più vicina dello sconto
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista dei prodotti
     */
    public static String selectObjectsDiscountedGrupByShopAndCategoryOrderedByDeadline(int idVenditore)
    {
        return "SELECT * FROM (Oggetto JOIN Categoria ON Oggetto.categoria=Categoria.id) "
                + "JOIN Negozio ON Negozio.id=Oggetto.idNegozio "
                + "WHERE idVenditore=" + idVenditore + " AND sconto>0 "
                + "GROUP BY Categoria.nome ASC, idNegozio ASC "
                + "ORDER BY dataFineSconto ASC;";
    }
          
    /**
     * @author fbrug
     * Ottenere la lista delle recensioni ricevute
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista delle recensioni
     */
    public static String selectSellerReview(int idVenditore)
    {
        return "SELECT * FROM RecensioneVenditore WHERE idVenditore=" + idVenditore + ";";
    }
    
    /**
     * @author fbrug
     * Aggiungere un proprio negozio
     * @param idVenditore: intero rappresentante l'ID del venditore, proprietario del nuovo negozio
     * @param nomeNegozio: nome del nuovo negozio da inserire
     * @param valutazioneNegozio: valutazione iniziale del nuovo negozio
     * @param idI: intero rappresentante l'ID dell'indirizzo del nuovo negozio
     * @return String: conferma avvenuta operazione
     */
    public static String insertShop(int idVenditore, String nomeNegozio, double valutazioneNegozio, int idI)
    {
        return "INSERT INTO Negozio (idVenditore, nomeNegozio, valutazione, attivo, idI, dataApertura) "
                + "VALUES (" + idVenditore + "," +  nomeNegozio + "," + valutazioneNegozio + ", '1' ," + idI + ", CURRENT_TIMESTAMP);";
    }
    
    /**
     * @author fbrug
     * Chiudere un proprio negozio (cambiare il suo stato)
     * @param idNegozio: intero rappresentante l'ID del negozio a cui cambiare lo stato
     * @param attivo: nuovo stato del negozio (0 = chiuso, 1 = in attività)
     * @return String: conferma avvenuta operazione
     */
    public static String updateShopStatus(int idNegozio, int attivo)
    {
        return "UPDATE Negozio SET attivo=" + attivo + " WHERE id=" + idNegozio + ";";
    }
    
    /**
     * @author fbrug
     * Modificare lo stato di un ordine
     * @param nuovoStato: nuovo stato dell'ordine (0 = nel carrello, 1 = pagato, 2 = in lavorazione, 3 = spedito, 4 = consegnato = nella lista desideri)
     * @param idOrdine: intero rappresentante l'ID dell'ordine da ricercare
     * @return String: conferma avvenuta operazione
     */
    public static String updateOrderStatus(int idOrdine , int nuovoStato)
    {
        return "UPDATE Ordine SET stato=" + nuovoStato + " WHERE idOrdine=" + idOrdine + ";";
    }
    
    /**
     * @author fbrug
     * Aggiungere ad un ordine spedito il codice di tracking
     * @param codiceTracking: nuovo codice usato per il tracking dell'ordine
     * @param idOrdine: intero rappresentante l'ID dell'ordine da ricercare
     * @return String: conferma avvenuta operazione
     */
    public static String updateOrderTracking(int idOrdine, String codiceTracking)
    {
        return "UPDATE Ordine SET codiceTracking=" + codiceTracking + " WHERE idOrdine=" + idOrdine + ";";
    }
    
    
    ////////////////////////
    
    /**
     * @author fbrug
     * Aggiungere un prodotto (OGGETTO) ad un proprio negozio
     * @param idNegozio
     * @param nomeOggetto
     * @param prezzoOggetto
     * @param descrizioneOggetto
     * @param ritiroInNegozio
     * @param disponibilita
     * @param statoDisponibilita
     * @param sconto
     * @param dataFineSconto
     * @param idCategoria
     * @return String: conferma avvenuta operazione
     */
    public static String insertObject(int idNegozio, String nomeOggetto, double prezzoOggetto, 
            String descrizioneOggetto, int ritiroInNegozio, int disponibilita, int statoDisponibilita, 
            double sconto, Date dataFineSconto, int idCategoria)
    {
        return "INSERT INTO Oggetto "
                + "(idNegozio, nome, prezzo, descrizione, ritiroInNegozio, disponibilita, "
                + "statoDisponibilita, sconto, variazione, dataFineSconto, categoria) "
                + "VALUES ("
                + idNegozio
                + ", " + nomeOggetto
                + ", " + prezzoOggetto
                + ", " + descrizioneOggetto
                + ", " + ritiroInNegozio
                + ", " + disponibilita
                + ", " + statoDisponibilita
                + ", " + sconto
                + ", " + dataFineSconto
                + ", " + idCategoria
                + ");";
    }
    
    /**
     * @author fbrug
     * Rimuovere un oggetto da un proprio negozio
     * @param idNegozio
     * @return String: conferma avvenuta operazione
     */
    public static String deleteObject(int idNegozio)
    {
        return "DELETE FROM Oggetto WHERE id=" + idNegozio + ";";
    }
    
    /**
     * @author fbrug
     * Modificare il prezzo di un oggetto di un proprio negozio
     * @param idOggetti
     * @param prezzoOggetto
     * @return String: conferma avvenuta operazione
     */
    public static String updateObjectPrice(int idOggetti, double prezzoOggetto)
    {
        return "UPDATE Oggetto SET prezzo=" + prezzoOggetto + " WHERE id=" + idOggetti + ";";
    }
    
    /**
     * @author fbrug
     * Modificare lo sconto di un oggetto
     * @param idOggetti
     * @param sconto
     * @return String: conferma avvenuta operazione
     */
    public static String updateObjectDiscount(int idOggetti, double sconto)
    {
        return "UPDATE Oggetto SET sconto=" + sconto + " WHERE id=" + idOggetti + ";";
    }
    
    /*??? 
    
    
    
    -- aggiungere una foto ad un prodotto
    -- aggiungere una foto ad un negozio
    -- rimuovere una foto di un prodotto
    -- rimuovere una foto di un negozio
    */
}
