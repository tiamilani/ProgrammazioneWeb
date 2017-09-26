/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.immagineNegozio;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella ImmagineNegozio
 * @author mattia
 */

import it.progettoWeb.java.database.Model.immagineNegozio.ModelloImmagineNegozio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;
import it.progettoWeb.java.database.query.users.usersQuery;

public class DaoImmagineNegozio {
    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String SRC="src";
    private static final String IDN="idN";
    
    /**
     * Variabile che gestisce la connessione con il db
     */
    private Connection connection;
    
    /**
     * Costruttore della classe, utilizzato per instaurare la connessione con il db
     */
    public DaoImmagineNegozio() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    private ModelloImmagineNegozio getModelloFromRs(ResultSet rs)
    {
        ModelloImmagineNegozio ImmagineNegozio = new ModelloImmagineNegozio();
        
        try{
            ImmagineNegozio.setSrc(rs.getString(SRC));
            ImmagineNegozio.setIdN(rs.getInt(IDN));
        } catch (SQLException e) {
        }
        
        return ImmagineNegozio;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista delle immagini di un negozio
     * @param idN Un intero che rappresenta l'identificativo del negozio preso in considerazione
     * @return List<ModelloImmagineNegozio> lista di immagini
     */
    public List<ModelloImmagineNegozio> selectPhotoStore(int idN) {
        List<ModelloImmagineNegozio> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectPhotoStore(idN));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere solo la prima immagine di un negozio
     * @param idN Un intero che rappresenta l'identificativo del negozio preso in considerazione
     * @return ModelloImmagineNegozio prima immagine di un oggetto
     */
    public ModelloImmagineNegozio selectFirstPhotoStore(int idN) {
        ModelloImmagineNegozio img = new ModelloImmagineNegozio();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectFirstPhotoStore(idN));
            if (rs.next()) {
                img = getModelloFromRs(rs);
            }
        } catch (SQLException e) {
        }
        
        return img;
    }
}
