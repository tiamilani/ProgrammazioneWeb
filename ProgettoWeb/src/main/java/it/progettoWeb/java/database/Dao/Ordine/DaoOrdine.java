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
import it.progettoWeb.java.database.query.users.usersQuery;

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
        }

        return Ordini;
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
     * @param idUtente Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param from Un intero che rappresenta lo stato degli ordini da selezionare
     * @param to Un intero che rappresenta lo stato da impostare agli ordini selezionati
     */
    public void changeOrderStatus(int idUtente, int from, int to) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(usersQuery.changeOrderStatus(idUtente, from, to));
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
}
