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
}
