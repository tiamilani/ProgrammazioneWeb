/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.rispostaNegozio;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella RispostaNegozio
 * @author mattia
 */

import it.progettoWeb.java.database.Model.rispostaNegozio.ModelloRispostaNegozio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;
import it.progettoWeb.java.database.query.users.usersQuery;

public class DaoRispostaNegozio {

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
    public DaoRispostaNegozio() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    public static ModelloRispostaNegozio getModelloFromRs(ResultSet rs)
    {
        ModelloRispostaNegozio RispostaNegozio = new ModelloRispostaNegozio();
        
        try{
            RispostaNegozio.setIdRecensione(rs.getInt(IDRECENSIONE));
            RispostaNegozio.setTesto(rs.getString(TESTO));
            RispostaNegozio.setData(rs.getDate(DATA));
        } catch (SQLException e) {
        }
        
        return RispostaNegozio;
    }   
    
    /**
     * @author Mattia
     * Ottenere le risposte alle proprie recensioni di negozi
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloRispostaNegozio> lista di risposte
     */
    public List<ModelloRispostaNegozio> selectAnswerReviewsStores(int idU) {
        List<ModelloRispostaNegozio> risposte = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectAnswerReviewsStores(idU));
            while (rs.next()) {
                risposte.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return risposte;
    }
    
    public boolean insertAnswerToStore(ModelloRispostaNegozio rispostaNegozio) {
        
       try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(usersQuery.insertAnswerToStore(rispostaNegozio.getIdRecensione(), rispostaNegozio.getTesto()));
        }
        catch (SQLException e) {
            return false;
        }
        return true;
    }
}
