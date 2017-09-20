/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.Ordine;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella Ordine
 * nella tabella ordini la modifica del prezzo di un oggetto è automatica, se il prezzo varia allora varia anche nell'ordine che a sua volta farà variare il carrello
 * @author mattia
 */

import it.progettoWeb.java.database.Model.Ordine.ModelloOrdine;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;

public class DaoOrdine {

    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String IDORDINE="idOrdine";
    private static final String IDOGGETTO="idOggetto";
    private static final String IDNEGOZIO="idNegozio";
    private static final String IDUTENTE="idUtente";
    private static final String STATO="stato";
    private static final String QUANTITA="quantita";
    private static final String CODICETRACKING="codiceTracking";
    private static final String DATAARRIVOPRESUNTA="dataArrivoPresunta";
    private static final String DATAORDINE="dataOrdine";
    private static final String PREZZODIACQUISTO="prezzoDiAcquisto";
    private static final String IDS="idS";
    
    /**
     * Variabile che gestisce la connessione con il db
     */
    private Connection connection;
    
    /**
     * Costruttore della classe, utilizzato per instaurare la connessione con il db
     */
    public DaoOrdine() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    private ModelloOrdine getModelloFromRs(ResultSet rs)
    {
        ModelloOrdine Ordine = new ModelloOrdine();
        
        try{
            Ordine.setIdOrdine(rs.getInt(IDORDINE));
            Ordine.setIdOggetto(rs.getString(IDOGGETTO));
            Ordine.setIdNegozio(rs.getInt(IDNEGOZIO));
            Ordine.setIdUtente(rs.getInt(IDUTENTE));
            Ordine.setStato(rs.getInt(STATO));
            Ordine.setQuantita(rs.getInt(QUANTITA));
            Ordine.setCodiceTracking(rs.getString(CODICETRACKING));
            Ordine.setDataArrivoPresunta(rs.getDate(DATAARRIVOPRESUNTA));
            Ordine.setDataOrdine(rs.getTimestamp(DATAORDINE));
            Ordine.setPrezzoDiAcquisto(rs.getDouble(PREZZODIACQUISTO));
            Ordine.setIdS(rs.getInt(IDS));
        } catch (SQLException e) {
        }
        
        return Ordine;
    }  
}
