/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.immagineRecensione;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella immagineRecensione
 * @author mattia
 */

import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import it.progettoWeb.java.database.Model.immagineOggetto.ModelloListeImmagineOggetto;
import it.progettoWeb.java.database.Model.immagineRecensione.ModelloImmagineRecensione;
import it.progettoWeb.java.database.Model.recensioneOggetto.ModelloRecensioneOggetto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;
import it.progettoWeb.java.database.query.generics.genericsQuery;
import it.progettoWeb.java.database.query.objects.objectsQuery;
import it.progettoWeb.java.database.query.users.usersQuery;
import it.progettoWeb.java.utility.tris.tris;

public class DaoImmagineRecensione {

    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String SRC="src";
    private static final String IDR="idR";
    
    /**
     * Variabile che gestisce la connessione con il db
     */
    private Connection connection;
    
    /**
     * Costruttore della classe, utilizzato per instaurare la connessione con il db
     */
    public DaoImmagineRecensione() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    public static ModelloImmagineRecensione getModelloFromRs(ResultSet rs)
    {
        ModelloImmagineRecensione ImmagineRecensione = new ModelloImmagineRecensione();
        
        try{
            ImmagineRecensione.setSrc(rs.getString(SRC));
            ImmagineRecensione.setIdR(rs.getInt(IDR));
        } catch (SQLException e) {
        }
        
        return ImmagineRecensione;
    }
    
    /**
     * @author andreafadi
     * Inserire un nuovo set immagine-recensione
     * @param setImageReview Un set di Immagine-Recensione
     */
    public void addImageReviewSet(ModelloImmagineRecensione setImageReview) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(genericsQuery.addImageReviewSet(setImageReview.getIdR(), setImageReview.getSrc()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) { System.out.println(e); }
    }
    
    /**
     * @author Mattia
     * Ottenere la lista delle immagini di una recensione
     * @param idR Un intero che rappresenta l'identificativo della recensione presa in considerazione
     * @return List<ModelloImmagineRecensione> lista di immagini
     */
    public List<ModelloImmagineRecensione> selectPhotoReview(int idR) {
        List<ModelloImmagineRecensione> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectPhotoReview(idR));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere solo la prima immagine di una recensione
     * @param idR Un intero che rappresenta l'identificativo della recensione presa in considerazione
     * @return ModelloImmagineRecensione prima immagine di un oggetto
     */
    public ModelloImmagineRecensione selectFirstPhotoReview(int idR) {
        ModelloImmagineRecensione img = new ModelloImmagineRecensione();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectFirstPhotoReview(idR));
            if (rs.next()) {
                img = getModelloFromRs(rs);
            }
        } catch (SQLException e) {
        }
        
        return img;
    }
}
