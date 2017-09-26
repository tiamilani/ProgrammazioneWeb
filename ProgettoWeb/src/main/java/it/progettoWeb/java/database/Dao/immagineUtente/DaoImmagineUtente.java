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
import it.progettoWeb.java.database.query.generics.genericsQuery;
import it.progettoWeb.java.database.query.users.usersQuery;

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
     * Funzione utilizzata per facilitare l'ottenimento dei modelli da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    public static ModelloImmagineUtente getModelloFromRs(ResultSet rs)
    {
        ModelloImmagineUtente ImmagineUtente = new ModelloImmagineUtente();
        
        try{
            ImmagineUtente.setSrc(rs.getString(SRC));
            ImmagineUtente.setIdU(rs.getInt(IDU));
        } catch (SQLException e) {
        }
        
        return ImmagineUtente;
    }
    
    /**
     * @author Mattia
     * Ottenere l'immagine di un utente
     * @param idUtente
     * @return ModelloImmagine, immagine del profilo
     */
    public ModelloImmagineUtente selectUserImageByUserID(int idUtente) {
        ModelloImmagineUtente img = new ModelloImmagineUtente();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(genericsQuery.selectAddressByUserID(idUtente));
            
            if (rs.next()) {
                img = getModelloFromRs(rs);
            }
            
        } catch (SQLException e) {
        }

        return img;
    }
    
    /**
     * @author Mattia
     * Modificare l'immagine del profilo di un utente con un determinato ID utente
     * @param ModelloImmagineUtente img
     */
    public void updateUserImageByUserID(ModelloImmagineUtente img) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(genericsQuery.updateUserImageByUserID(img.getIdU(),img.getSrc()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }
    
    /**
     * @author Mattia
     * Rimuovere l'immagine del profilo di un determinato utente
     * @param idUtente
     */
    public void deleteUserImageByUserID(int userId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(genericsQuery.deleteUserImageByUserID(userId));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }
    
    /**
     * @author Mattia
     * Aggiungere una immagine del profilo di un utente
     * @param ModelloImmagine user
     */
    public void insertUserImage(ModelloImmagineUtente img) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(genericsQuery.insertUserImage(img.getIdU(),img.getSrc()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }
    
    /**
     * @author Mattia
     * Ottenere la lista delle immagini di un utente
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloImmagineUtente> lista di immagini
     */
    public List<ModelloImmagineUtente> selectPhotoUser(int idU) {
        List<ModelloImmagineUtente> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectPhotoUser(idU));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere solo la prima immagine di un utente
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return ModelloImmagineUtente prima immagine di un utente
     */
    public ModelloImmagineUtente selectFirstPhotoUser(int idU) {
        ModelloImmagineUtente img = new ModelloImmagineUtente();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectFirstPhotoUser(idU));
            if (rs.next()) {
                img = getModelloFromRs(rs);
            }
        } catch (SQLException e) {
        }
        
        return img;
    }
}
