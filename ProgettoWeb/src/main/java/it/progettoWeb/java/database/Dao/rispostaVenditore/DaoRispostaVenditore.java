/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.rispostaVenditore;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella RispostaVenditore
 * @author mattia
 */

import it.progettoWeb.java.database.Model.rispostaVenditore.ModelloRispostaVenditore;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;
import it.progettoWeb.java.database.query.users.usersQuery;

public class DaoRispostaVenditore {

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
    public DaoRispostaVenditore() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    private ModelloRispostaVenditore getModelloFromRs(ResultSet rs)
    {
        ModelloRispostaVenditore RispostaVenditore = new ModelloRispostaVenditore();
        
        try{
            RispostaVenditore.setIdRecensione(rs.getInt(IDRECENSIONE));
            RispostaVenditore.setTesto(rs.getString(TESTO));
            RispostaVenditore.setData(rs.getDate(DATA));
        } catch (SQLException e) {
        }
        
        return RispostaVenditore;
    } 
    
    /**
     * @author Mattia
     * Ottenere le risposte alle proprie recensioni di venditori
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloRispostaVenditore> lista di risposte
     */
    public List<ModelloRispostaVenditore> selectAnswerReviewsSellers(int idU) {
        List<ModelloRispostaVenditore> risposte = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectAnswerReviewsSellers(idU));
            while (rs.next()) {
                risposte.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return risposte;
    }
}
