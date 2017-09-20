/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.spedizioneOggetto;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella SpedizioneOggetto
 * @author mattia
 */

import it.progettoWeb.java.database.Model.spedizioneOggetto.ModelloSpedizioneOggetto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;

public class DaoSpedizioneOggetto {
    
    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String IDS="idS";
    private static final String IDO="idO";
    
    /**
     * Variabile che gestisce la connessione con il db
     */
    private Connection connection;
    
    /**
     * Costruttore della classe, utilizzato per instaurare la connessione con il db
     */
    public DaoSpedizioneOggetto() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    private ModelloSpedizioneOggetto getModelloFromRs(ResultSet rs)
    {
        ModelloSpedizioneOggetto SpedizioneOggetto = new ModelloSpedizioneOggetto();
        
        try{
            SpedizioneOggetto.setIdS(rs.getInt(IDS));
            SpedizioneOggetto.setIdO(rs.getString(IDO));
        } catch (SQLException e) {
        }
        
        return SpedizioneOggetto;
    }
}
