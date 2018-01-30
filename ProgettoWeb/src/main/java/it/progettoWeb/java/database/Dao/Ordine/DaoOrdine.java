/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.Ordine;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella Ordine
 * nella tabella ordini la modifica del prezzo di un oggetto è automatica, se il prezzo varia allora varia anche nell'ordine che a sua volta farà variare il carrello
 * @author mattia
 */

import it.progettoWeb.java.database.Model.Ordine.ModelloOrdine;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;
import it.progettoWeb.java.database.query.sellers.sellersQuery;
import it.progettoWeb.java.database.query.users.usersQuery;
import java.sql.Timestamp;

public class DaoOrdine {

    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String IDORDINE="idOrdine";
    private static final String IDOGGETTO="idOggetto";
    private static final String IDNEGOZIO="idNegozio";
    private static final String IDUTENTE="idUtente";
    private static final String STATO="stato";
    private static final String QUANTITA="quantita";
    private static final String CODICETRACKING="codiceTracking";
    private static final String DATAARRIVOPRESUNTA="dataArrivoPresunta";
    private static final String DATAORDINE="dataOrdine";
    private static final String PREZZODIACQUISTO="prezzoDiAcquisto";
    private static final String IDS="idS";
    private static final String IDI="idI";

    /**
     * Variabile che gestisce la connessione con il db
     */
    private Connection connection;

    /**
     * Costruttore della classe, utilizzato per instaurare la connessione con il db
     */
    public DaoOrdine() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    private ModelloOrdine getModelloFromRs(ResultSet rs)
    {
        ModelloOrdine Ordine = new ModelloOrdine();

        try{
            Ordine.setIdOrdine(rs.getInt(IDORDINE));
            Ordine.setIdOggetto(rs.getString(IDOGGETTO));
            Ordine.setIdNegozio(rs.getInt(IDNEGOZIO));
            Ordine.setIdUtente(rs.getInt(IDUTENTE));
            Ordine.setStato(rs.getInt(STATO));
            Ordine.setQuantita(rs.getInt(QUANTITA));
            Ordine.setCodiceTracking(rs.getString(CODICETRACKING));
            Ordine.setDataArrivoPresunta(rs.getDate(DATAARRIVOPRESUNTA));
            Ordine.setDataOrdine(rs.getTimestamp(DATAORDINE));
            Ordine.setPrezzoDiAcquisto(rs.getDouble(PREZZODIACQUISTO));
            Ordine.setIdS(rs.getInt(IDS));
            Ordine.setIdI(rs.getInt(IDI));
        } catch (SQLException e) {
        }

        return Ordine;
    }

    /**
     * @author Mattia
     * Ottenere la lista degli ordini
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloOrdine> lista di ordini
     */
    public List<ModelloOrdine> selectOrders(int idU) {
        List<ModelloOrdine> Ordini = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectOrders(idU));
            while (rs.next()) {
                Ordini.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Ordini;
    }

    /**
     * @author Mattia
     * Ottenere la lista degli ordini con richieste specifiche per l'ordine
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param orderStatus Un intero che rappresenta l'identificativo dello stato dell'ordine
     * @return List<ModelloOrdine> lista di ordini
     */
    public List<ModelloOrdine> selectOrdersComplete(int idU, int orderStatus) {
        List<ModelloOrdine> Ordini = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectOrdersComplete(idU,orderStatus));
            while (rs.next()) {
                Ordini.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Ordini;
    }

    /**
     * @author Mattia
     * Ottenere il carrello (La lista degli ordini che sono nel carrello)
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloOrdine> lista di ordini
     */
    public List<ModelloOrdine> selectOrdersCart(int idU) {
        List<ModelloOrdine> Ordini = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectOrdersCart(idU));
            while (rs.next()) {
                Ordini.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Ordini;
    }

    /**
     * @author fbrug
     * Ottenere la lista degli ordini ricevuti
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista degli ordini
     */
    public List<ModelloOrdine> selectOrdersBySellerID(int idVenditore)
    {
        List<ModelloOrdine> ordini = new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectOrdersBySellerID(idVenditore));

            while(rs.next())
                ordini.add(getModelloFromRs(rs));
        } catch(SQLException e) {}

        return ordini;
    }

    /**
     * @author fbrug
     * Ottenere la lista degli ordini ricevuti dal più nuovo al più vecchio
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista degli ordini
     */
    public List<ModelloOrdine> selectNewestToOldestOrdersBySellerID(int idVenditore)
    {
        List<ModelloOrdine> ordini = new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectNewestToOldestOrdersBySellerID(idVenditore));

            while(rs.next())
                ordini.add(getModelloFromRs(rs));
        } catch(SQLException e) {}

        return ordini;
    }

    /**
     * @author fbrug
     * Ottenere la lista degli ordini in base al loro stato
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @param stato: intero rappresentante lo stato dell'ordine (0 = nel carrello, 1 = pagato, 2 = in lavorazione, 3 = spedito, 4 = consegnato, 5 = nella lista desideri)
     * @return String: lista degli ordini
     */
    public List<ModelloOrdine> selectOrderBySellerIDAndStatus(int idVenditore, int stato)
    {
        List<ModelloOrdine> ordini = new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectOrderBySellerIDAndStatus(idVenditore, stato));

            while(rs.next())
                ordini.add(getModelloFromRs(rs));
        } catch(SQLException e) {}

        return ordini;
    }

    /**
     * @author fbrug
     * Ottenere la lista degli ordini ricevuti in un determinato giorno
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @param dataOrdine: data usata per trovare ordini
     * @return String: lista degli ordini
     */
    public List<ModelloOrdine> selectOrderByDate(int idVenditore, Timestamp dataOrdine)
    {
        List<ModelloOrdine> ordini = new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectOrderByDate(idVenditore, dataOrdine));

            while(rs.next())
                ordini.add(getModelloFromRs(rs));
        } catch(SQLException e) {}

        return ordini;
    }

    /**
     * @author fbrug
     * Ottenere la lista degli ordini ricevuti nella settimana corrente
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @param dataOrdine: data usata per trovare ordini
     * @return String: lista degli ordini
     */
    public List<ModelloOrdine> selectOrderByWeek(int idVenditore, Timestamp dataOrdine)
    {
        List<ModelloOrdine> ordini = new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectOrderByWeek(idVenditore, dataOrdine));

            while(rs.next())
                ordini.add(getModelloFromRs(rs));
        } catch(SQLException e) {}

        return ordini;
    }

    /**
     * @author fbrug
     * Ottenere la lista degli ordini ricevuti nel mese corrente
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @param dataOrdine: data usata per trovare ordini
     * @return String: lista degli ordini
     */
    public List<ModelloOrdine> selectOrderByMonth(int idVenditore, Timestamp dataOrdine)
    {
        List<ModelloOrdine> ordini = new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectOrderByMonth(idVenditore, dataOrdine));

            while(rs.next())
                ordini.add(getModelloFromRs(rs));
        } catch(SQLException e) {}

        return ordini;
    }

    /**
     * @author fbrug
     * Ottenere la lista degli ordini ricevuti nell'anno corrente
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @param dataOrdine: data usata per trovare ordini
     * @return String: lista degli ordini
     */
    public List<ModelloOrdine> selectOrderByYear(int idVenditore, Timestamp dataOrdine)
    {
        List<ModelloOrdine> ordini = new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectOrderByYear(idVenditore, dataOrdine));

            while(rs.next())
                ordini.add(getModelloFromRs(rs));
        } catch(SQLException e) {}

        return ordini;
    }

    /**
     * @author fbrug
     * Ottenere i dati delle vendite di un determinato negozio
     * @param idNegozio: intero rappresentante l'ID del negozio
     * @return String: lista degli ordini
     */
    public List<ModelloOrdine> selectOrderByShopID(int idNegozio)
    {
        List<ModelloOrdine> ordini = new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectOrderByShopID(idNegozio));

            while(rs.next())
                ordini.add(getModelloFromRs(rs));
        } catch(SQLException e) {}

        return ordini;
    }

    public List<ModelloOrdine> selectOrderRecivedBySellerIdShopIDNewstToOldes(int idVenditore,int idNegozio)
    {
        List<ModelloOrdine> ordini = new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectOrderRecivedBySellerIdShopId(idVenditore,idNegozio));

            while(rs.next())
                ordini.add(getModelloFromRs(rs));
        } catch(SQLException e) {}

        return ordini;
    }

    /**
     * @author fbrug
     * Ottenere i dati di vendita di un determinato negozio in una determinata categoria
     * @param idNegozio: intero rappresentante l'ID del negozio
     * @param idCategoria: intero rappresentante l'ID della categoria
     * @return String: lista degli ordini
     */
    public List<ModelloOrdine> selectOrderByCategory(int idNegozio, int idCategoria)
    {
        List<ModelloOrdine> ordini = new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectOrderByCategory(idNegozio, idCategoria));

            while(rs.next())
                ordini.add(getModelloFromRs(rs));
        } catch(SQLException e) {}

        return ordini;
    }

    /**
     * @author fbrug
     * Modificare lo stato di un ordine
     * @param nuovoStato: nuovo stato dell'ordine (0 = nel carrello, 1 = pagato, 2 = in lavorazione, 3 = spedito, 4 = consegnato = nella lista desideri)
     * @param idOrdine: intero rappresentante l'ID dell'ordine da ricercare
     */
    public void updateOrderStatus(int idOrdine, int nuovoStato)
    {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeQuery(sellersQuery.updateOrderStatus(idOrdine, nuovoStato));
        } catch(SQLException e) {}
    }

    /**
     * @author fbrug
     * Aggiungere ad un ordine spedito il codice di tracking
     * @param codiceTracking: nuovo codice usato per il tracking dell'ordine
     */
    public void updateOrderTracking(ModelloOrdine ordine, String codiceTracking)
    {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sellersQuery.updateOrderTracking(ordine, codiceTracking));
        } catch(SQLException e) {}
    }

    /**
     * @author Mattia
     * Aggiungi un oggetto agli ordini nel carrello
     * @param oggetto ModelloOrdine che rappresenta l'oggetto da inserire
     */
    public void insertObjectInCart(ModelloOrdine oggetto) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(usersQuery.insertObjectInCart(oggetto.getIdOrdine(), oggetto.getIdUtente(), oggetto.getQuantita(), oggetto.getIdOggetto()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }

    /**
     * @author Mattia
     * Rimuovere un ordine (oggetto) dal carrello
     * @param oggetto ModelloOrdine che rappresenta l'oggetto da inserire
     */
    public void removeObjectInCart(ModelloOrdine oggetto) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(usersQuery.removeObjectInCart(oggetto.getIdOrdine(), oggetto.getIdOggetto(), oggetto.getIdUtente()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }

    /**
     * @author Mattia
     * Cambia lo stato degli ordini da from a to
     * @param from Un intero che rappresenta lo stato degli ordini da selezionare
     * @param to Un intero che rappresenta lo stato da impostare agli ordini selezionati
     */
    public void changeOrderStatus(ModelloOrdine ordine, int from, int to) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(usersQuery.changeOrderStatus(ordine, from, to));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }

    /**
     * @author fbrug
     * @param idOr Un intero che rappresenta l'identificativo dell'ordine preso in considerazione
     * @param idOg Una stringa che rappresenta l'identificativo dell'oggetto preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param newQuantity Un intero rappresentate la nuova quantità dell'oggetto nell'ordine selezionato
     */
    public void changeOrderQuantity(int idOr, String idOg, int idU, int newQuantity)
    {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(usersQuery.changeOrderQuantity(idOr, idOg, idU, newQuantity));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }

    /**
     * @author Mattia
     * Aggiungi un oggetto agli ordini nella lista dei desideri
     * @param oggetto ModelloOrdine che rappresenta l'oggetto da inserire
     */
    public void insertObjectInWislist(ModelloOrdine oggetto) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(usersQuery.insertObjectInWislist(oggetto.getIdOrdine(), oggetto.getIdUtente(), oggetto.getQuantita(), oggetto.getIdOggetto()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }

    /*---2017-11-20---*/

    /**
     * @author fbrug
     * Seleziona gli ordini con lo stesso tipo di spedizione (in base all'idS)
     * @param idS: intero rappresentante l'ID della spedzione associata all'ordine
     * @return lista degli ordini
     */
    public List<ModelloOrdine> selectOrderByIdS(int idS)
    {
        List<ModelloOrdine> ordini = new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectOrderByIdS(idS));

            while(rs.next())
                ordini.add(getModelloFromRs(rs));
        } catch(SQLException e) {}

        return ordini;
    }

    /**
     * @author fbrug
     * Aggiorna il valore dell'idS dell'ordine selezionato
     * @param idOrdine: intero rappresentante l'ID dell'ordine
     * @param idS: intero rappresentante l'ID della spedizione associata all'ordine
     * @param idOggetto: intero rappresentante l'ID dell'oggetto associato all'ordine
     * @param idUtente: intero rappresentante l'ID dell'utente associato all'ordine
     */
    public void updateOrderIdS(int idOrdine, int idS, String idOggetto, int idUtente)
    {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sellersQuery.updateOrderIdS(idOrdine, idS, idOggetto, idUtente));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }


    /*---2017-11-21---*/

    /**
     * @author fbrug
     * Seleziona gli ordini con lo stesso indirizzo spedizione (in base all'idI)
     * @param idI: intero rappresentante l'ID dell'indirizzo associato all'ordine
     * @return lista degli ordini
     */
    public List<ModelloOrdine> selectOrderByIdI(int idI)
    {
        List<ModelloOrdine> ordini = new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectOrderByIdI(idI));

            while(rs.next())
                ordini.add(getModelloFromRs(rs));
        } catch(SQLException e) {}

        return ordini;
    }

    /**
     * @author fbrug
     * Aggiorna il valore dell'idI dell'ordine selezionato
     * @param idOrdine: intero rappresentante l'ID dell'ordine
     * @param idI: intero rappresentante l'ID dell'indirizzo associato all'ordine
     * @param idOggetto: intero rappresentante l'ID dell'oggetto associato all'ordine
     * @param idUtente: intero rappresentante l'ID dell'utente associato all'ordine
     */
    public void updateOrderIdI(int idOrdine, int idI, String idOggetto, int idUtente)
    {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(sellersQuery.updateOrderIdI(idOrdine, idI, idOggetto, idUtente));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }


    /*---2017-12-04---*/

    /**
     * @author fbrug
     * Seleziona gli ordini con lo stesso ID ordine
     * @param idOrdine: intero rappresentante l'ID dell'ordine
     * @return
     */
    public List<ModelloOrdine> selectOrdersByIdOrder(int idOrdine)
    {
        List<ModelloOrdine> ordini = new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectOrdersByIdOrder(idOrdine));

            while(rs.next())
                ordini.add(getModelloFromRs(rs));
        } catch(SQLException e) {}

        return ordini;
    }

    public ModelloOrdine selectOrdersByIdOrderIdOggetto(String idOrdine, String idOggetto) {
        ModelloOrdine ordine = new ModelloOrdine();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectOrdersByIdOrderIdOggetto(idOrdine,idOggetto));

            while(rs.next())
                ordine = getModelloFromRs(rs);
        } catch(SQLException e) {}

        return ordine;
    }

    public void updateOrderDataArrivoPresunta(ModelloOrdine ordine, String dt) {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sellersQuery.updateOrderDataArrivoPrevista(ordine, dt));
        } catch(SQLException e) {}
    }

    public String insertObjectInCartFirstTime(ModelloOrdine oggetto) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(usersQuery.insertObjectInCartFistTime(oggetto.getIdUtente(), oggetto.getQuantita(), oggetto.getIdOggetto()));
            preparedStatement.executeUpdate();
            return usersQuery.insertObjectInCartFistTime(oggetto.getIdUtente(), oggetto.getQuantita(), oggetto.getIdOggetto());
        } catch (SQLException e) {
            return usersQuery.insertObjectInCartFistTime(oggetto.getIdUtente(), oggetto.getQuantita(), oggetto.getIdOggetto());
        }
    }
}
