/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.immagineOggetto;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella ImmagineOggetto
 * @author mattia
 */

import it.progettoWeb.java.database.Model.immagineOggetto.ModelloImmagineOggetto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;
import it.progettoWeb.java.database.query.users.usersQuery;
import java.sql.PreparedStatement;

public class DaoImmagineOggetto {

    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String SRC="src";
    private static final String IDO="idO";
    
    /**
     * Variabile che gestisce la connessione con il db
     */
    private Connection connection;
    
    /**
     * Costruttore della classe, utilizzato per instaurare la connessione con il db
     */
    public DaoImmagineOggetto() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    public static ModelloImmagineOggetto getModelloFromRs(ResultSet rs)
    {
        ModelloImmagineOggetto ImmagineOggetto = new ModelloImmagineOggetto();
        
        try{
            ImmagineOggetto.setSrc(rs.getString(SRC));
            ImmagineOggetto.setIdO(rs.getString(IDO));
        } catch (SQLException e) {
        }
        
        return ImmagineOggetto;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista delle immagini di un oggetto
     * @param idO Una Stringa che rappresenta l'identificativo dell'oggetto preso in considerazione
     * @return List<ModelloImmagineOggetto> lista di immagini
     */
    public List<ModelloImmagineOggetto> selectPhotoObject(String idO) {
        List<ModelloImmagineOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectPhotoObject(idO));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere solo la prima immagine di un oggetto
     * @param idO Una stringa che rappresenta l'identificativo dell'oggetto preso in considerazione
     * @return ModelloImmagineOggetto prima immagine di un oggetto
     */
    public ModelloImmagineOggetto selectFirstPhotoObject(String idO) {
        ModelloImmagineOggetto img = new ModelloImmagineOggetto();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectFirstPhotoObject(idO));
            if (rs.next()) {
                img = getModelloFromRs(rs);
            }
        } catch (SQLException e) {   
        } 
        return img;
    }
    
    public void addImageToObject(ModelloImmagineOggetto imageObject) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(usersQuery.addImageToObject(imageObject.getIdO(), imageObject.getSrc()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) { System.out.println(e); }
    }
    
    public void remImageToObject(ModelloImmagineOggetto imageObject) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(usersQuery.remImageToObject(imageObject.getIdO(), imageObject.getSrc()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) { System.out.println(e); }
    }
}
