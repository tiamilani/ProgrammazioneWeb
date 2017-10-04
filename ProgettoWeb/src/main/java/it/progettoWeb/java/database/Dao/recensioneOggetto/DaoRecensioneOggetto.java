/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.recensioneOggetto;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella RecensioneOggetto
 * @author mattia
 */

import it.progettoWeb.java.database.Model.recensioneOggetto.ModelloRecensioneOggetto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;
import it.progettoWeb.java.database.query.generics.genericsQuery;
import it.progettoWeb.java.database.query.users.usersQuery;

public class DaoRecensioneOggetto {

    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String ID="id";
    private static final String IDOGGETTO="idOggetto";
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
    public DaoRecensioneOggetto() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    private ModelloRecensioneOggetto getModelloFromRs(ResultSet rs)
    {
        ModelloRecensioneOggetto RecensioneOggetto = new ModelloRecensioneOggetto();
        
        try{
            RecensioneOggetto.setId(rs.getInt(ID));
            RecensioneOggetto.setIdOggetto(rs.getString(IDOGGETTO));
            RecensioneOggetto.setIdUtente(rs.getInt(IDUTENTE));
            RecensioneOggetto.setTesto(rs.getString(TESTO));
            RecensioneOggetto.setValutazione(rs.getInt(VALUTAZIONE));
            RecensioneOggetto.setData(rs.getDate(DATA));
            RecensioneOggetto.setUtilita(rs.getInt(UTILITA));
        } catch (SQLException e) {
        }
        
        return RecensioneOggetto;
    }
    
    /**
     * @author Mattia
     * Ottenere le recensioni di un oggetto
     * @param idU Un intero che rappresenta l'identificativo dell'oggetto preso in considerazione
     * @return List<ModelloRecensioneOggetto> lista di recensioni
     */
    public List<ModelloRecensioneOggetto> selectReviewsObjects(String idO) {
        List<ModelloRecensioneOggetto> recensioni = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(genericsQuery.selectReviewsObjects(idO));
            while (rs.next()) {
                recensioni.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return recensioni;
    }
    
    /**
     * @author Mattia
     * Ottenere le proprie recensioni di oggetti
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloRecensioneOggetto> lista di recensioni
     */
    public List<ModelloRecensioneOggetto> selectReviewsObjects(int idU) {
        List<ModelloRecensioneOggetto> recensioni = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectReviewsObjects(idU));
            while (rs.next()) {
                recensioni.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return recensioni;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista delle proprie recensioni Oggetti dalla più utile
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloRecensioneOggetto> lista di recensioni
     */
    public List<ModelloRecensioneOggetto> selectObjectReviewsOrderUseful(int idU) {
        List<ModelloRecensioneOggetto> recensioni = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectObjectReviewsOrderUseful(idU));
            while (rs.next()) {
                recensioni.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return recensioni;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista delle proprie recensioni Oggetti dalla meno utile
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloRecensioneOggetto> lista di recensioni
     */
    public List<ModelloRecensioneOggetto> selectObjectReviewsOrderUseless(int idU) {
        List<ModelloRecensioneOggetto> recensioni = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectObjectReviewsOrderUseless(idU));
            while (rs.next()) {
                recensioni.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return recensioni;
    }
    
    /**
     * @author Mattia
     * Aggiungi una recensione ad un determinato oggetto
     * @param recensione oggetto recensione da inserire
     */
    public void addReviewToObject(ModelloRecensioneOggetto recensione) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(usersQuery.addReviewToObject(recensione.getIdOggetto(), recensione.getIdUtente(), recensione.getTesto(), recensione.getValutazione(), recensione.getData(), recensione.getUtilita()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }
    
    /**
     * @author Mattia
     * Ottenere un boolean se si ha recensito oppure no un venditore (se il count è 1 vuol dire di si)
     * @param recensione oggetto recensione da inserire
     * @return int NumRecensioni
     */
    public int reviewOrNotObject(ModelloRecensioneOggetto recensione) {
        int numRecensioni = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(usersQuery.reviewOrNotObject(recensione.getIdOggetto(),recensione.getIdUtente()));
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                numRecensioni = rs.getInt("counter");
            }
        } catch (SQLException e) {
        }

        return numRecensioni;
    }
}
