/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.indirizzo;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella Indirizzo
 * @author mattia
 */

import it.progettoWeb.java.database.Model.indirizzo.ModelloIndirizzo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;

public class DaoIndirizzo {

    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String IDI="idI";
    private static final String STATO="stato";
    private static final String REGIONE="regione";
    private static final String PROVINCIA="provincia";
    private static final String CITTA="citta";
    private static final String VIA="via";
    private static final String NCIVICO="nCivico";
    private static final String INTERNO="interno";
    private static final String LATITUDINE="latitudine";
    private static final String LONGITUDINE="longitudine";
    
    /**
     * Variabile che gestisce la connessione con il db
     */
    private Connection connection;
    
    /**
     * Costruttore della classe, utilizzato per instaurare la connessione con il db
     */
    public DaoIndirizzo() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    private ModelloIndirizzo getModelloFromRs(ResultSet rs)
    {
        ModelloIndirizzo Indirizzo = new ModelloIndirizzo();
        
        try{
            Indirizzo.setIdI(rs.getInt(IDI));
            Indirizzo.setStato(rs.getString(STATO));
            Indirizzo.setRegione(rs.getString(REGIONE));
            Indirizzo.setProvincia(rs.getString(PROVINCIA));
            Indirizzo.setCitta(rs.getString(CITTA));
            Indirizzo.setVia(rs.getString(VIA));
            Indirizzo.setnCivico(rs.getInt(NCIVICO));
            Indirizzo.setInterno(rs.getInt(INTERNO));
            Indirizzo.setLatitudine(rs.getDouble(LATITUDINE));
            Indirizzo.setLongitudine(rs.getDouble(LONGITUDINE));
        } catch (SQLException e) {
        }
        
        return Indirizzo;
    }
}