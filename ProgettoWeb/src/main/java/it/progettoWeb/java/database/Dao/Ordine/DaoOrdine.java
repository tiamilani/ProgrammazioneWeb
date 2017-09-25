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
            e.printStackTrace();
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
     * @param stato: intero rappresentante lo stato dell'ordine (non in carico, in lavorazione, spedito, concluso)
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
    
    
    ///////////////////////////////
}
