/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.recensioneVenditore;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella RecensioneVenditore
 * @author mattia
 */

import it.progettoWeb.java.database.Dao.Utente.DaoUtente;
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import it.progettoWeb.java.database.Model.recensioneVenditore.ModelloRecensioneVenditore;
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
import it.progettoWeb.java.utility.pair.pair;

public class DaoRecensioneVenditore {

    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String ID="id";
    private static final String IDVENDITORE="idVenditore";
    private static final String IDUTENTE="idUtente";
    private static final String TESTO="testo";
    private static final String VALUTAZIONE="valutazione";
    private static final String DATA="data";
    private static final String UTILITA="utilita";
    
    /**
     * Variabile che gestisce la connessione con il db
     */
    private Connection connection;
    
    /**
     * Costruttore della classe, utilizzato per instaurare la connessione con il db
     */
    public DaoRecensioneVenditore() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    public static ModelloRecensioneVenditore getModelloFromRs(ResultSet rs)
    {
        ModelloRecensioneVenditore RecensioneVenditore = new ModelloRecensioneVenditore();
        
        try{
            RecensioneVenditore.setId(rs.getInt(ID));
            RecensioneVenditore.setIdVenditore(rs.getInt(IDVENDITORE));
            RecensioneVenditore.setIdUtente(rs.getInt(IDUTENTE));
            RecensioneVenditore.setTesto(rs.getString(TESTO));
            RecensioneVenditore.setValutazione(rs.getInt(VALUTAZIONE));
            RecensioneVenditore.setData(rs.getDate(DATA));
            RecensioneVenditore.setUtilita(rs.getInt(UTILITA));
        } catch (SQLException e) {
        }
        
        return RecensioneVenditore;
    }
    
    /**
     * @author fbrug
     * Ottenere la lista delle recensioni ricevute
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista delle recensioni
     */
    public List<ModelloRecensioneVenditore> selectSellerReview(int idVenditore)
    {
        List<ModelloRecensioneVenditore> recensioniV = new ArrayList<>();
        
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectSellerReview(idVenditore));
            
            while(rs.next())
                recensioniV.add(getModelloFromRs(rs));
        } catch(SQLException e) {}
        
        return recensioniV;
    }
    
    /**
     * @author andrea
     * Ottenere pair Recensione, Venditore
     * @param idV Un intero che rappresenta l'identificativo del venditore preso in considerazione
     * @return pair<List<ModelloRecensioneVenditore>,List<ModelloUtente>> elenco recensioni e venditori
     */
    public pair<List<ModelloRecensioneVenditore>,List<ModelloUtente>> selectReviewUserBySeller(int idV) {
        pair<List<ModelloRecensioneVenditore>,List<ModelloUtente>> res;
        List<ModelloRecensioneVenditore> recensioni = new ArrayList<>();
        List<ModelloUtente> venditori = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectReviewUserBySeller(idV));
            
            while (rs.next()) {
                recensioni.add(DaoRecensioneVenditore.getModelloFromRs(rs));
                venditori.add(DaoUtente.getModelloFromRs(rs));
            }
        } catch (SQLException e) { }
        
        res = new pair(recensioni, venditori);
        return res;
    }
    
    /**
     * @author Mattia
     * Ottenere le proprie recensioni di negozi
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloRecensioneVenditore> lista di recensioni
     */
    public List<ModelloRecensioneVenditore> selectReviewsSellers(int idU) {
        List<ModelloRecensioneVenditore> recensioni = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectReviewsSellers(idU));
            while (rs.next()) {
                recensioni.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return recensioni;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista delle proprie recensioni Venditore dalla più utile
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloRecensioneVenditore> lista di recensioni
     */
    public List<ModelloRecensioneVenditore> selectSellerReviewsOrderUseful(int idU) {
        List<ModelloRecensioneVenditore> recensioni = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectSellerReviewsOrderUseful(idU));
            while (rs.next()) {
                recensioni.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return recensioni;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista delle proprie recensioni Venditore dalla meno utile
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloRecensioneVenditore> lista di recensioni
     */
    public List<ModelloRecensioneVenditore> selectSellerReviewsOrderUseless(int idU) {
        List<ModelloRecensioneVenditore> recensioni = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectSellerReviewsOrderUseless(idU));
            while (rs.next()) {
                recensioni.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return recensioni;
    }
    
    /**
     * @author Mattia
     * Aggiungi una recensione ad un determinato venditore
     * @param recensione oggetto recensione da inserire
     */
    public void addReviewToSeller(ModelloRecensioneVenditore recensione) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(usersQuery.addReviewToSeller(recensione.getIdVenditore(), recensione.getIdUtente(), recensione.getTesto(), recensione.getValutazione(), recensione.getUtilita()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }
    
    /**
     * @author Mattia
     * Ottenere un boolean se si ha recensito oppure no un venditore (se il count è 1 vuol dire di si)
     * @param idV Un intero identificativo del venditore preso in considerazione
     * @param idU Un intero identificativo dell'utente preso in considerazione
     * @return int NumRecensioni
     */
    public int reviewOrNotSeller(int idV, int idU) {
        int numRecensioni = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(usersQuery.reviewOrNotSeller(idV, idU));
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                numRecensioni = rs.getInt("counter");
            }
        } catch (SQLException e) {
        }

        return numRecensioni;
    }
}
