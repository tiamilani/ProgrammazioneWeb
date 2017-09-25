/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.Assistenza;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella Assistenza
 * @author mattia
 */

import it.progettoWeb.java.database.Model.Assistenza.ModelloAssistenza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;
import it.progettoWeb.java.database.query.sellers.sellersQuery;

public class DaoAssistenza {
    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String ID="id";
    private static final String IDUTENTE="idUtente";
    private static final String IDVENDITORE="idVenditore";
    private static final String IDAMMINISTRATORE="idAmministratore";
    private static final String IDORDINE="idOrdine";
    private static final String IDOGGETTO="idOggetto";
    private static final String STATO="stato";
    private static final String SOLUZIONE="soluzione";
    private static final String DATAAPERTURA="dataApertura";
    private static final String DATACHIUSURA="dataChiusura";
    
    /**
     * Variabile che gestisce la connessione con il db
     */
    private Connection connection;
    
    /**
     * Costruttore della classe, utilizzato per instaurare la connessione con il db
     */
    public DaoAssistenza() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    private ModelloAssistenza getModelloFromRs(ResultSet rs)
    {
        ModelloAssistenza Assistenza = new ModelloAssistenza();
        
        try{
            Assistenza.setId(rs.getInt(ID));
            Assistenza.setIdUtente(rs.getInt(IDUTENTE));
            Assistenza.setIdVenditore(rs.getInt(IDVENDITORE));
            Assistenza.setIdAmministratore(rs.getInt(IDAMMINISTRATORE));
            Assistenza.setIdOrdine(rs.getInt(IDORDINE));
            Assistenza.setIdOggetto(rs.getString(IDOGGETTO));
            Assistenza.setStato(rs.getInt(STATO));
            Assistenza.setSoluzione(rs.getString(SOLUZIONE));
            Assistenza.setDataApertura(rs.getDate(DATAAPERTURA));
            Assistenza.setDataChiusura(rs.getDate(DATACHIUSURA));
        } catch (SQLException e) {
        }
        
        return Assistenza;
    }
    
    
    
    /**
     * @author fbrug
     * Ottenere le richieste di assistenza in cui si è stati citati
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista delle richieste di assistenza
     */
    public List<ModelloAssistenza> selectServiceRequestBySellerID(int idVenditore)
    {
        List<ModelloAssistenza> assistenze = new ArrayList<>();
        
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectServiceRequestBySellerID(idVenditore));
            
            while(rs.next())
                assistenze.add(getModelloFromRs(rs));
        } catch(SQLException e) {}
        
        return assistenze;
    }
}
