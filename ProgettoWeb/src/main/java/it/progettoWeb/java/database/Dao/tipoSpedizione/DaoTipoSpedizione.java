/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.tipoSpedizione;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella TipoSpedizione
 * @author mattia
 */

import it.progettoWeb.java.database.Model.tipoSpedizione.ModelloTipoSpedizione;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;

public class DaoTipoSpedizione {

    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String IDS="idS";
    private static final String IDU="idU";
    private static final String NOME="Nome";
    private static final String PREZZO="Prezzo";
    private static final String CORRIERE="Corriere";
    private static final String TEMPORICHIESTO="tempoRichiesto";
    
    /**
     * Variabile che gestisce la connessione con il db
     */
    private Connection connection;
    
    /**
     * Costruttore della classe, utilizzato per instaurare la connessione con il db
     */
    public DaoTipoSpedizione() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    private ModelloTipoSpedizione getModelloFromRs(ResultSet rs)
    {
        ModelloTipoSpedizione TipoSpedizione = new ModelloTipoSpedizione();
        
        try{
            TipoSpedizione.setIdS(rs.getInt(IDS));
            TipoSpedizione.setIdU(rs.getInt(IDU));
            TipoSpedizione.setNome(rs.getString(NOME));
            TipoSpedizione.setPrezzo(rs.getDouble(PREZZO));
            TipoSpedizione.setCorriere(rs.getString(CORRIERE));
            TipoSpedizione.setTempoRichiesto(rs.getInt(TEMPORICHIESTO));
        } catch (SQLException e) {
        }
        
        return TipoSpedizione;
    }   
}
