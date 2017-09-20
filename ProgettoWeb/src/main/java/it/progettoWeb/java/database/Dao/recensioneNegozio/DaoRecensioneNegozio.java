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

import it.progettoWeb.java.database.Model.recensioneNegozio.ModelloRecensioneNegozio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;

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
    private ModelloRecensioneNegozio getModelloFromRs(ResultSet rs)
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
}
