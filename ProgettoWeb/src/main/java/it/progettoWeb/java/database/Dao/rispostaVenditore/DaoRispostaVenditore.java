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
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
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
}