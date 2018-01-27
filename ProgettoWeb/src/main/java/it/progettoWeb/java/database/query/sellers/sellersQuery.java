/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.query.sellers;

import it.progettoWeb.java.database.Model.Negozio.ModelloNegozio;
import it.progettoWeb.java.database.Model.Oggetto.ModelloOggetto;
import it.progettoWeb.java.database.Model.Ordine.ModelloOrdine;
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

    public static String selectNumberOfOrderForStore(int idVenditore, int idNegozio){
        return "select COUNT(Ordine.idOggetto) as ordini "
                + "from Ordine INNER JOIN ordiniRicevuti ON (Ordine.idOrdine = ordiniRicevuti.idO) "
                + "where ordiniRicevuti.idV = "+idVenditore+" AND Ordine.idNegozio = "+idNegozio+";";
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
     * @author andrea
     * Ottenere Recensione, Venditore
     * @param idV Una stringa che rappresenta l'identificativo del venditore preso in considerazione
     * @return elenco recensioni e venditore
     */
    public static String selectReviewUserBySeller(int idV)
    {
        return "SELECT RecensioneVenditore.*, Utente.* FROM "
                + "RecensioneVenditore JOIN Utente ON RecensioneVenditore.idUtente=Utente.id "
                + "WHERE RecensioneVenditore.idVenditore='" + idV + "';";
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
        return "select Negozio.*, COUNT(idOrdine) AS numeroVendite "
                + "FROM Negozio JOIN Ordine ON (Ordine.idNegozio=Negozio.id) "
                + "where Negozio.idVenditore="+idVenditore+" "
                + "GROUP BY Negozio.id, Negozio.nomeNegozio, Negozio.valutazione, Negozio.attivo, Negozio.idI "
                + "ORDER BY numeroVendite DESC;";
    }

    /**
     * @author fbrug
     * Ottenere la lista dei propri negozi per vendite minori
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista dei negozi
     */
    public static String selectShopWithLowestSalesBySellerID(int idVenditore)
    {
        return "select Negozio.*, COUNT(idOrdine) AS numeroVendite "
                + "FROM Negozio JOIN Ordine ON (Ordine.idNegozio=Negozio.id) "
                + "where Negozio.idVenditore="+idVenditore+" "
                + "GROUP BY Negozio.id, Negozio.nomeNegozio, Negozio.valutazione, Negozio.attivo, Negozio.idI "
                + "ORDER BY numeroVendite ASC;";
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

    public static String selectShopByNameup(int idVenditore)
    {
        return "SELECT * FROM Negozio WHERE idVenditore=" + idVenditore + " ORDER BY nomeNegozio;";
    }

    public static String selectShopByNameDown(int idVenditore)
    {
        return "SELECT * FROM Negozio WHERE idVenditore=" + idVenditore + " ORDER BY nomeNegozio DESC;";
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
        return "SELECT * FROM Assistenza JOIN Utente ON Utente.id=Assistenza.idVenditore "
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
     * @param negozio: negozio da inserire
     * @return String: conferma avvenuta operazione
     */
    public static String insertShop(ModelloNegozio negozio)
    {
        return "INSERT INTO Negozio (idVenditore, nomeNegozio, valutazione, attivo, idI, dataApertura, linkSito, orarioNegozio) "
                + "VALUES ('" + negozio.getIdVenditore() + "','" +  negozio.getNomeNegozio() + "',NULL, '1' ,'" + negozio.getIdI() + "', CURRENT_TIMESTAMP,'"+negozio.getLinkSito()+"','"+negozio.getOrarioNegozio()+"');";
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
    
    public static String updateShopStars(int idNegozio, double value)
    {
        return "UPDATE Negozio SET valutazione=" + value + " WHERE id=" + idNegozio + ";";
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
     * @return String: conferma avvenuta operazione
     */
    public static String updateOrderTracking(ModelloOrdine ordine, String codiceTracking)
    {
        return "UPDATE Ordine SET codiceTracking='" + codiceTracking + "' WHERE idOrdine=" + ordine.getIdOrdine() + " AND idOggetto = '"+ordine.getIdOggetto()+"';";
    }

    /**
     * @author fbrug
     * Aggiungere un prodotto (OGGETTO) ad un proprio negozio
     * @param idNegozio: intero rapprensentante l'ID del negozio in cui inserire il nuovo oggetto
     * @param nomeOggetto: nome del nuovo oggetto da inserire
     * @param prezzoOggetto: prezzo del nuovo oggetto da inserire
     * @param descrizioneOggetto: descrizione del nuovo oggetto da inserire
     * @param ritiroInNegozio: disponibilità o meno del ritiro in negozio per l'oggetto (0 = no, 1 = si)
     * @param disponibilita: rappresenta la quantità dell'oggetto disponibile in negozio
     * @param statoDisponibilita: indica lo stato della disponibilità
     * @param sconto: indica lo sconto applicato all'oggetto
     * @param dataFineSconto: indica la data in cui terminerà lo sconto applicato all'oggetto
     * @param idCategoria: intero rappresentante l'ID della categoria di cui l'oggetto fa parte
     * @return String: conferma avvenuta operazione
     */
    public static String insertObject(String id,int idNegozio, String nomeOggetto,String nomeDownCase, double prezzoOggetto,
            String descrizioneOggetto, int ritiroInNegozio, int disponibilita, int statoDisponibilita,
            double sconto, Date dataFineSconto, int idCategoria)
    {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        if(dataFineSconto != null)
            return "INSERT INTO Oggetto "
                    + "(id, idNegozio, nome,nomeDownCase, prezzo, descrizione, ritiroInNegozio, disponibilita, "
                    + "statoDisponibilita, sconto, dataFineSconto, categoria) "
                    + "VALUES ("
                    + "'"+id+"'"
                    + ", " + idNegozio
                    + ", '" + nomeOggetto + "'"
                    + ", '" + nomeDownCase + "'"
                    + ", " + prezzoOggetto
                    + ", '" + descrizioneOggetto+"'"
                    + ", " + ritiroInNegozio
                    + ", " + disponibilita
                    + ", " + statoDisponibilita
                    + ", " + sconto
                    + ", '" + sdf.format(dataFineSconto) + "'"
                    + ", " + idCategoria
                    + ");";
        else
            return "INSERT INTO Oggetto "
                    + "(id, idNegozio, nome,nomeDownCase, prezzo, descrizione, ritiroInNegozio, disponibilita, "
                    + "statoDisponibilita, sconto, dataFineSconto, categoria) "
                    + "VALUES ("
                    + "'"+id+"'"
                    + ", " + idNegozio
                    + ", '" + nomeOggetto + "'"
                    + ", '" + nomeDownCase + "'"
                    + ", " + prezzoOggetto
                    + ", '" + descrizioneOggetto+"'"
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
     * Rimuovere un oggetto
     * @param idOggetto: stringa rapprensentante l'ID dell'oggetto da eliminare
     * @return String: query
     */
    public static String deleteObject(String idOggetto)
    {
        return "DELETE FROM Oggetto WHERE id='" + idOggetto + "';";
    }

    public static String updateObject(ModelloOggetto oggetto,String oldId){
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        if(oggetto.getDataFineSconto() != null)
            return "UPDATE Oggetto SET "
                    + "id='"+oggetto.getId()+"'"
                    + ",idNegozio=" + oggetto.getIdNegozio()
                    + ",nome='" + oggetto.getNome() + "'"
                    + ",nomeDownCase='" + oggetto.getNomeDownCase() + "'"
                    + ",prezzo=" + oggetto.getPrezzo()
                    + ",descrizione='" + oggetto.getDescrizione()+"'"
                    + ",ritiroInNegozio=" + oggetto.getRitiroInNegozio()
                    + ",disponibilita=" + oggetto.getDisponibilita()
                    + ",statoDisponibilita=" + oggetto.getStatoDisponibilita()
                    + ",sconto=" + oggetto.getSconto()
                    + ",dataFineSconto='" + sdf.format(oggetto.getDataFineSconto()) + "'"
                    + ",categoria=" + oggetto.getCategoria()
                    + " WHERE id='" + oldId + "';";
        else
            return "UPDATE Oggetto SET "
                    + "id='"+oggetto.getId()+"'"
                    + ",idNegozio=" + oggetto.getIdNegozio()
                    + ",nome='" + oggetto.getNome() + "'"
                    + ",nomeDownCase='" + oggetto.getNomeDownCase() + "'"
                    + ",prezzo=" + oggetto.getPrezzo()
                    + ",descrizione='" + oggetto.getDescrizione()+"'"
                    + ",ritiroInNegozio=" + oggetto.getRitiroInNegozio()
                    + ",disponibilita=" + oggetto.getDisponibilita()
                    + ",statoDisponibilita=" + oggetto.getStatoDisponibilita()
                    + ",sconto=" + oggetto.getSconto()
                    + ",dataFineSconto=" + oggetto.getDataFineSconto()
                    + ",categoria=" + oggetto.getCategoria()
                    + " WHERE id='" + oldId + "';";
    }

    /**
     * @author fbrug
     * Modificare il prezzo di un oggetto di un proprio negozio
     * @param idOggetto: stringa rappresentante l'ID dell'oggetto a cui modificare il prezzo
     * @param prezzoOggetto: il nuovo prezzo dell'oggetto
     * @return String: conferma avvenuta operazione
     */
    public static String updateObjectPrice(String idOggetto, double prezzoOggetto)
    {
        return "UPDATE Oggetto SET prezzo=" + prezzoOggetto + " WHERE id='" + idOggetto + "';";
    }

    /**
     * @author fbrug
     * Modificare lo sconto di un oggetto
     * @param idOggetto: stringa rappresentante l'ID dell'oggetto a cui modificare il prezzo
     * @param sconto: il nuovo sconto applicato all'oggetto
     * @return String: conferma avvenuta operazione
     */
    public static String updateObjectDiscount(String idOggetto, double sconto)
    {
        return "UPDATE Oggetto SET sconto=" + sconto + " WHERE id='" + idOggetto + "';";
    }

    /**
     * @author fbrug
     * Modificare la quantita' di un oggetto
     * @param idOggetto: stringa rappresentante l'ID dell'oggetto a cui modificare il prezzo
     * @param newQuantity: intero rappresentante la nuova quantita' disponibile per l'oggetto in questione
     * @return String: conferma avvenuta operazione
     */
    public static String updateObjectQuantity(String idOggetto, int newQuantity)
    {
        return "UPDATE Oggetto SET disponibilita="+ newQuantity + " WHERE id='" + idOggetto + "';";
    }
    
    /**
     * @author fbrug
     * Modificare la quantita' di un oggetto
     * @param idOggetto: stringa rappresentante l'ID dell'oggetto a cui modificare il prezzo
     * @param newState: intero rappresentante il nuovo stato di disponibilita' per l'oggetto in questione
     * @return String: conferma avvenuta operazione
     */
    public static String updateObjectStatus(String idOggetto, int newState)
    {
        return "UPDATE Oggetto SET statoDisponibilita=" + newState + " WHERE id='" + idOggetto + "';";
    }


    /**
     * @author fbrug
     * Aggiungere una immagine del profilo di un oggetto
     * @param idOggetto: stringa rappresentante l'ID dell'oggetto a cui inserire l'immagine
     * @param imagePath: il path della nuova immagine
     * @return String: conferma avvenuta operazione
     */
    public static String insertObjectImage(String idOggetto, String imagePath)
    {
        return "INSERT INTO imageOggetto (src, idO) "
                + "SELECT '" + imagePath + "', Oggetto.id FROM Oggetto WHERE Oggetto.id = '" + idOggetto + "';";
    }

    /**
     * @author fbrug
     * Modificare l'immagine del profilo di un oggetto con un determinato ID oggetto
     * @param idOggetto: stringa rappresentante l'ID dell'oggetto a cui cambiare l'immagine
     * @param oldImagePath: il path dell'immagine da modificare
     * @param newImagePath: il path della nuova immagine
     * @return String: conferma avvenuta operazione
     */
    public static String updateObjectImage(String idOggetto, String oldImagePath, String newImagePath)
    {
        return "UPDATE imageOggetto SET imageOggetto.src = " + newImagePath + " "
                + "WHERE imageOggetto.idO = '" + idOggetto + "' AND imageOggetto.src = " + oldImagePath + ";";
    }

    /**
     * @author fbrug
     * Rimuovere l'immagine di un determinato oggetto
     * @param idOggetto: stringa rappresentante l'ID dell'oggetto a cui rimuovere l'immagine
     * @param imagePath: il path dell'immagine da rimuovere
     * @return String: conferma avvenuta operazione
     */
    public static String deleteObjectImage(String idOggetto, String imagePath)
    {
        return "DELETE FROM imageOggetto WHERE imageOggetto.idO = " + idOggetto + " AND imageOggetto.src = " + imagePath + ";";
    }

    /**
     * @author fbrug
     * Aggiungere una immagine del profilo di un negozio
     * @param idNegozio: intero rappresentante l'ID del negozio a cui inserire l'immagine
     * @param imagePath: il path della nuova immagine
     * @return String: conferma avvenuta operazione
     */
    public static String insertShopImage(int idNegozio, String imagePath)
    {
        return "INSERT INTO imageNegozio (src, idN) values ('"+imagePath+"',"+idNegozio+");";
    }

    /**
     * @author fbrug
     * Modificare l'immagine del profilo di un negozio con un determinato ID negozio
     * @param idNegozio: intero rappresentante l'ID del negozio a cui cambiare l'immagine
     * @param oldImagePath: il path dell'immagine da modificare
     * @param newImagePath: il path della nuova immagine
     * @return String: conferma avvenuta operazione
     */
    public static String updateShopImage(int idNegozio, String oldImagePath, String newImagePath)
    {
        return "UPDATE imageNegozio SET imageNegozio.src = " + newImagePath + " "
                + "WHERE imageNegozio.idO = " + idNegozio + " AND imageNegozio.src = " + oldImagePath + ";";
    }

    /**
     * @author fbrug
     * Rimuovere l'immagine di un determinato negozio
     * @param idNegozio: intero rappresentante l'ID del negozio a cui rimuovere l'immagine
     * @param imagePath: il path dell'immagine da rimuovere
     * @return String: conferma avvenuta operazione
     */
    public static String deleteShopImage(int idNegozio, String imagePath)
    {
        return "DELETE FROM imageNegozio WHERE imageNegozio.idO = " + idNegozio + " AND imageNegozio.src = " + imagePath + ";";
    }



    /*---2017-11-20---*/

    /**
     * @author fbrug
     * Seleziona gli ordini con lo stesso tipo di spedizione (in base all'idS)
     * @param idS: intero rappresentante l'ID della spedzione associata all'ordine
     * @return String: lista degli ordini
     */
    public static String selectOrderByIdS(int idS)
    {
        return "SELECT * FROM Ordine WHERE Ordine.idS = " + idS + ";";
    }

    /**
     * @author fbrug
     * Aggiorna il valore dell'idS dell'ordine selezionato
     * @param idOrdine: intero rappresentante l'ID dell'ordine
     * @param idS: intero rappresentante l'ID della spedizione associata all'ordine
     * @return String: conferma avvenuta operazione
     */
    public static String updateOrderIdS(int idOrdine, int idS)
    {
        System.out.println("idS = " + idS);
        return "UPDATE Ordine SET idS = " + idS + " WHERE idOrdine = " + idOrdine + ";";
    }


    /*---2017-11-21---*/

    /**
     * @author fbrug
     * Seleziona gli ordini con lo stesso indirizzo spedizione (in base all'idI)
     * @param idI: intero rappresentante l'ID dell'indirizzo associato all'ordine
     * @return String: lista degli ordini
     */
    public static String selectOrderByIdI(int idI)
    {
        return "SELECT * FROM Ordine WHERE Ordine.idI = " + idI + ";";
    }

    /**
     * @author fbrug
     * Aggiorna il valore dell'idI dell'ordine selezionato
     * @param idOrdine: intero rappresentante l'ID dell'ordine
     * @param idI: intero rappresentante l'ID dell'indirizzo associato all'ordine
     * @return String: conferma avvenuta operazione
     */
    public static String updateOrderIdI(int idOrdine, int idI)
    {
        return "UPDATE Ordine SET idI = " + idI + " WHERE idOrdine = " + idOrdine + ";";
    }


    /*---2017-12-04---*/

    /**
     * @author fbrug
     * Seleziona gli ordini con lo stesso ID ordine
     * @param idOrdine: intero rappresentante l'ID dell'ordine
     * @return String: lista degli ordini
     */
    public static String selectOrdersByIdOrder(int idOrdine)
    {
        return "SELECT * FROM Ordine WHERE Ordine.idOrdine = " + idOrdine + ";";
    }

    /**
     * @author fbrug
     * @param idUtente: intero rappresentante l'ID dell'utente
     * @return String: lista dei carrelli
     */
    public static String selectCartByIdU(int idUtente)
    {
        return "SELECT * FROM Carrello WHERE Carrello.idUtente = " + idUtente + ";";
    }

    /**
     * @author fbrug
     * @param idordine: intero rappresentante l'ID dell'ordine
     * @return String: lista dei carrelli
     */
    public static String selectCartByIdOrder(int idordine)
    {
        return "SELECT * FROM Carrello WHERE Carrello.idOrdine = " + idordine + ";";
    }

    public static String increaseCategory(int categoria) {
        return "update Categoria set oggettiPresenti = oggettiPresenti+1 where id = "+categoria+";";
    }

    public static String decraseCategory(int categoria) {
        return "update Categoria set oggettiPresenti = oggettiPresenti-1 where id = "+categoria+";";
    }

    public static String selectNewestToOldestOrdersByShopId(int idNegozio) {
        return "SELECT * FROM Ordine WHERE Ordine.idNegozio = " + idNegozio + " ORDER BY dataOrdine DESC;";
    }

    public static String selectOrdersByIdOrderIdOggetto(String idOrdine, String idOggetto) {
        return "SELECT * FROM Ordine WHERE Ordine.idOrdine = " + idOrdine + " AND Ordine.idOggetto = '"+idOggetto+"';";
    }

    public static String selectOrderRecivedBySellerIdShopId(int idVenditore, int idNegozio) {
        return "select Ordine.* "
                + "from Ordine INNER JOIN ordiniRicevuti ON (Ordine.idOrdine = ordiniRicevuti.idO) "
                + "where ordiniRicevuti.idV = "+idVenditore+" AND Ordine.idNegozio = "+idNegozio+" ORDER BY Ordine.dataOrdine DESC;";
    }

    public static String updateOrderDataArrivoPrevista(ModelloOrdine ordine, String dt) {
        return "UPDATE Ordine SET dataArrivoPresunta = '" + dt + "' WHERE idOrdine = " + ordine.getIdOrdine() + " AND idOggetto = '"+ordine.getIdOggetto()+"';";
    }


    /*---2018-01-12---*/

    /**
     * @author fbrug
     * Aggiunge un elemento nella tabella OrdiniRicevuti
     * @param idOrdine: intero rappresentante l'ID dell'ordine da aggiungere
     * @param idVenditore: intero rappresentane l'ID del venditore (utente) da aggiungere
     * @return String: conferma avvenuta operazione
     */
    public static String addOrdineRicevuto(int idOrdine, int idVenditore)
    {
        return "INSERT INTO ordiniricevuti(idO, idV, data) VALUES (" + idOrdine + "," + idVenditore + ",CURRENT_TIMESTAMP)";
    }
}
