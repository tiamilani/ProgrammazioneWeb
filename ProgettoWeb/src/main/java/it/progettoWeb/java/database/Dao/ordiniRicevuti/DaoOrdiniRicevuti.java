/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.ordiniRicevuti;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella OrdiniRicevuti
 * @author mattia
 */

import it.progettoWeb.java.database.Model.Ordine.ModelloOrdine;
import it.progettoWeb.java.database.Model.ordiniRicevuti.ModelloOrdiniRicevuti;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;
import it.progettoWeb.java.database.query.sellers.sellersQuery;

public class DaoOrdiniRicevuti {

    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String IDO="idO";
    private static final String IDV="idV";
    private static final String DATA="data";
    
    /**
     * Variabile che gestisce la connessione con il db
     */
    private Connection connection;
    
    /**
     * Costruttore della classe, utilizzato per instaurare la connessione con il db
     */
    public DaoOrdiniRicevuti() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    private ModelloOrdiniRicevuti getModelloFromRs(ResultSet rs)
    {
        ModelloOrdiniRicevuti OrdineRicevuto = new ModelloOrdiniRicevuti();
        
        try{
            OrdineRicevuto.setIdO(rs.getInt(IDO));
            OrdineRicevuto.setIdV(rs.getInt(IDV));
            OrdineRicevuto.setData(rs.getDate(DATA));
        } catch (SQLException e) {
        }
        
        return OrdineRicevuto;
    }
    
    public int selectNumberOfOrderForStore(int idVenditore, int idNegozio)
    {
        int ordini = 0;
        
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectNumberOfOrderForStore(idVenditore, idNegozio));
            
            while(rs.next())
                ordini = rs.getInt("ordini");
        } catch(SQLException e) {}
        
        return ordini;
    }
}
