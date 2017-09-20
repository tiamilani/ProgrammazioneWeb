/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.immagineOggetto;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella ImmagineOggetto
 * @author mattia
 */

import it.progettoWeb.java.database.Model.immagineOggetto.ModelloImmagineOggetto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;

public class DaoImmagineOggetto {

    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String SRC="id";
    private static final String IDO="idOggeto";
    
    /**
     * Variabile che gestisce la connessione con il db
     */
    private Connection connection;
    
    /**
     * Costruttore della classe, utilizzato per instaurare la connessione con il db
     */
    public DaoImmagineOggetto() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    private ModelloImmagineOggetto getModelloFromRs(ResultSet rs)
    {
        ModelloImmagineOggetto ImmagineOggetto = new ModelloImmagineOggetto();
        
        try{
            ImmagineOggetto.setSrc(rs.getString(SRC));
            ImmagineOggetto.setIdO(rs.getString(IDO));
        } catch (SQLException e) {
        }
        
        return ImmagineOggetto;
    }
}
