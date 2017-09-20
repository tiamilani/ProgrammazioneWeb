/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.Carrello;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella Carrello
 * il carrello si autogestisce quando viene inserito un ordine nel carrello
 * aumenta o diminuisce in automatico il subtotale
 * crea un carrello quando un utente inserisce per la prima volta un oggetto tra gli ordini
 * aumenta o diminuisce il totale in automatico quando viene inserito o termina uno sconto
 * se il prezzo di un oggetto varia viene variato anche il prezzo del carrello
 * i triggers che gestiscono il carrello sono contenuti nella tabella ordine
 * @author mattia
 */

import it.progettoWeb.java.database.Model.Carrello.ModelloCarrello;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;

public class DaoCarrello {
    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String IDUTENTE="idUtente";
    private static final String IDORDINE="idOrdine";
    private static final String SUBTOTAL="subTotal";
    
    /**
     * Variabile che gestisce la connessione con il db
     */
    private Connection connection;
    
    /**
     * Costruttore della classe, utilizzato per instaurare la connessione con il db
     */
    public DaoCarrello() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    private ModelloCarrello getModelloFromRs(ResultSet rs)
    {
        ModelloCarrello Carrello = new ModelloCarrello();
        
        try{
            Carrello.setIdUtente(rs.getInt(IDUTENTE));
            Carrello.setIdOrdine(rs.getInt(IDORDINE));
            Carrello.setSubTotal(rs.getDouble(SUBTOTAL));
        } catch (SQLException e) {
        }
        
        return Carrello;
    }
}
