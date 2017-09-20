/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.immagineUtente;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella ImmagineUtente
 * @author mattia
 */

import it.progettoWeb.java.database.Model.immagineUtente.ModelloImmagineUtente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;

public class DaoImmagineUtente {
   
    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String SRC="src";
    private static final String IDU="idU";
    
    /**
     * Variabile che gestisce la connessione con il db
     */
    private Connection connection;
    
    /**
     * Costruttore della classe, utilizzato per instaurare la connessione con il db
     */
    public DaoImmagineUtente() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    private ModelloImmagineUtente getModelloFromRs(ResultSet rs)
    {
        ModelloImmagineUtente ImmagineUtente = new ModelloImmagineUtente();
        
        try{
            ImmagineUtente.setSrc(rs.getString(SRC));
            ImmagineUtente.setIdU(rs.getInt(IDU));
        } catch (SQLException e) {
        }
        
        return ImmagineUtente;
    }
}
