/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.rispostaOggetto;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella RispostaOggetto
 * @author mattia
 */

import it.progettoWeb.java.database.Model.rispostaOggetto.ModelloRispostaOggetto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;
import it.progettoWeb.java.database.query.users.usersQuery;

public class DaoRispostaOggetto {

    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String IDRECENSIONE="idRecensione";
    private static final String TESTO="testo";
    private static final String DATA="data";
    
    /**
     * Variabile che gestisce la connessione con il db
     */
    private Connection connection;
    
    /**
     * Costruttore della classe, utilizzato per instaurare la connessione con il db
     */
    public DaoRispostaOggetto() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    public static ModelloRispostaOggetto getModelloFromRs(ResultSet rs)
    {
        ModelloRispostaOggetto RispostaOggetto = new ModelloRispostaOggetto();
        
        try{
            RispostaOggetto.setIdRecensione(rs.getInt(IDRECENSIONE));
            RispostaOggetto.setTesto(rs.getString(TESTO));
            RispostaOggetto.setData(rs.getDate(DATA));
        } catch (SQLException e) {
        }
        
        return RispostaOggetto;
    }    
    
    
    /**
     * @author Mattia
     * Ottenere le risposte alle proprie recensioni di oggetti
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloRispostaOggetto> lista di risposte
     */
    public List<ModelloRispostaOggetto> selectAnswerReviewsObjects(int idU) {
        List<ModelloRispostaOggetto> risposte = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectAnswerReviewsObjects(idU));
            while (rs.next()) {
                risposte.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return risposte;
    }
    
    public boolean insertAnswerToObject(ModelloRispostaOggetto rispostaOggetto) {
        
       try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(usersQuery.insertAnswerToObject(rispostaOggetto.getIdRecensione(), rispostaOggetto.getTesto()));
        }
        catch (SQLException e) {
            return false;
        }
        return true;
    }
}
