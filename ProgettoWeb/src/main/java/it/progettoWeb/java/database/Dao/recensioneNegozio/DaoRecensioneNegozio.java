/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.recensioneNegozio;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella RecensioneNegozio
 * @author mattia
 */

import it.progettoWeb.java.database.Dao.Negozio.DaoNegozio;
import it.progettoWeb.java.database.Dao.Utente.DaoUtente;
import it.progettoWeb.java.database.Dao.immagineRecensione.DaoImmagineRecensione;
import it.progettoWeb.java.database.Model.Negozio.ModelloNegozio;
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import it.progettoWeb.java.database.Model.immagineRecensione.ModelloListeImmagineRecensione;
import it.progettoWeb.java.database.Model.recensioneNegozio.ModelloRecensioneNegozio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;
import it.progettoWeb.java.database.query.generics.genericsQuery;
import it.progettoWeb.java.database.query.marketsSellers.marketsSellersQuery;
import it.progettoWeb.java.database.query.users.usersQuery;
import it.progettoWeb.java.utility.pair.pair;
import it.progettoWeb.java.utility.tris.tris;

public class DaoRecensioneNegozio {

    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String ID="id";
    private static final String IDNEGOZIO="idNegozio";
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
    public DaoRecensioneNegozio() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    public static ModelloRecensioneNegozio getModelloFromRs(ResultSet rs)
    {
        ModelloRecensioneNegozio RecensioneNegozio = new ModelloRecensioneNegozio();
        
        try{
            RecensioneNegozio.setId(rs.getInt(ID));
            RecensioneNegozio.setIdNegozio(rs.getInt(IDNEGOZIO));
            RecensioneNegozio.setIdUtente(rs.getInt(IDUTENTE));
            RecensioneNegozio.setTesto(rs.getString(TESTO));
            RecensioneNegozio.setValutazione(rs.getInt(VALUTAZIONE));
            RecensioneNegozio.setData(rs.getDate(DATA));
            RecensioneNegozio.setUtilita(rs.getInt(UTILITA));
        } catch (SQLException e) {
        }
        
        return RecensioneNegozio;
    }
    
    /**
     * @author Mattia
     * Ottenere le proprie recensioni di negozi
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloRecensioneNegozio> lista di recensioni
     */
    public List<ModelloRecensioneNegozio> selectReviewsStores(int idU) {
        List<ModelloRecensioneNegozio> recensioni = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectReviewsStores(idU));
            while (rs.next()) {
                recensioni.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return recensioni;
    }
    
    /**
     * @author andrea
     * Ottenere una specifica recensioni dati i parametri identificativi
     * @param idN Un intero che rappresenta il negozio della recensione da ricercare
     * @param idU Un intero che rappresenta l'utente della recensione da ricercare
     * @param testo Una stringa che rappresenta il testo della recensione da ricercare
     * @param valutazione Un intero che rappresenta la valutazione della recensione da ricercare
     * @return ModelloRecensioneOggetto
     */
    public ModelloRecensioneNegozio selectReviewsByData(int idN, int idU, String txt, int val) {
        ModelloRecensioneNegozio recensione = new ModelloRecensioneNegozio();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(genericsQuery.selectReviewsByDataN(idN, idU, txt, val));
            while (rs.next()) {
                recensione = (ModelloRecensioneNegozio) getModelloFromRs(rs);
            }
        } catch (SQLException e) { }

        return recensione;
    }
    
    /**
     * @author andrea
     * Ottenere trio Recensione, Immagini, Negozio
     * @param idN Un intero che rappresenta l'identificativo del negozio preso in considerazione
     * @return pair<List<ModelloRecensioneNegozio>,List<ModelloUtente>> elenco recensioni e negozio
     */
    public pair<List<ModelloRecensioneNegozio>,List<ModelloUtente>> selectReviewImagesUserByStore(int idN) {
        pair<List<ModelloRecensioneNegozio>,List<ModelloUtente>> res;
        List<ModelloRecensioneNegozio> recensioni = new ArrayList<>();
        List<ModelloUtente> utenti = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.selectReviewUserByStore(idN));
            
            while (rs.next()) {
                recensioni.add(DaoRecensioneNegozio.getModelloFromRs(rs));
                utenti.add(DaoUtente.getModelloFromRs(rs));
            }
        } catch (SQLException e) { }
        
        res = new pair(recensioni, utenti);
        return res;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista delle proprie recensioni Negozio dalla più utile
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloRecensioneNegozio> lista di recensioni
     */
    public List<ModelloRecensioneNegozio> selectStoreReviewsOrderUseful(int idU) {
        List<ModelloRecensioneNegozio> recensioni = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectStoreReviewsOrderUseful(idU));
            while (rs.next()) {
                recensioni.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return recensioni;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista delle proprie recensioni Negozio dalla meno utile
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloRecensioneNegozio> lista di recensioni
     */
    public List<ModelloRecensioneNegozio> selectStoreReviewsOrderUseless(int idU) {
        List<ModelloRecensioneNegozio> recensioni = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectStoreReviewsOrderUseless(idU));
            while (rs.next()) {
                recensioni.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return recensioni;
    }
    
    /**
     * @author Mattia
     * Aggiungi una recensione ad un determinato negozio
     * @param recensione oggetto recensione da inserire
     */
    public void addReviewToStore(ModelloRecensioneNegozio recensione) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(usersQuery.addReviewToStore(recensione.getIdNegozio(), recensione.getIdUtente(), recensione.getTesto(), recensione.getValutazione(), recensione.getUtilita()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }
    
    /**
     * @author Mattia
     * Ottenere un boolean se si ha recensito oppure no un negozio (se il count è 1 vuol dire di si)
     * @param idN Un intero che rappresenta l'identificativo del negozio preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return int booleano indicante se si ha recensito o no un negozio
     */
    public int reviewOrNotStore(int idN, int idU) {
        int numRecensioni = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(usersQuery.reviewOrNotStore(idN, idU));
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                numRecensioni = rs.getInt("counter");
            }
        } catch (SQLException e) {
        }

        return numRecensioni;
    }
    
    /**
     * @author Andrea
     * Ottenere un intero indicante il numero di recensioni ottenuto
     * @param idN Un intero che rappresenta l'identificativo del negozio preso in considerazione
     * @return int indicante il numero di recensioni ottenuto
     */
    public int howManyReviews(int idN) {
        int numRecensioni = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(usersQuery.howManyReviews(idN));
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                numRecensioni = rs.getInt("counter");
            }
        } catch (SQLException e) {
        }

        return numRecensioni;
    }
    
    /**
     * @author andrea
     * Ottenere un boolean se si ha acquistato oppure no da un negozio (se il count è 1 vuol dire di si)
     * @param idN Un intero che rappresenta l'identificativo del negozio preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return int booleano indicante se si ha acquistato o no da un negozio
     */
    public int buyOrNotFromStore(int idN, int idU) {
        int numRecensioni = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(usersQuery.buyOrNotFromStore(idN, idU));
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                numRecensioni = rs.getInt("counter");
            }
        } catch (SQLException e) {
        }

        return numRecensioni;
    }
}
