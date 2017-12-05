/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.recensioneOggetto;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella RecensioneOggetto
 * @author mattia
 */

import it.progettoWeb.java.database.Dao.Utente.DaoUtente;
import it.progettoWeb.java.database.Dao.immagineRecensione.DaoImmagineRecensione;
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import it.progettoWeb.java.database.Model.immagineOggetto.ModelloListeImmagineOggetto;
import it.progettoWeb.java.database.Model.immagineRecensione.ModelloImmagineRecensione;
import it.progettoWeb.java.database.Model.immagineRecensione.ModelloListeImmagineRecensione;
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

public class DaoRecensioneOggetto {

    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String ID="id";
    private static final String IDOGGETTO="idOggetto";
    private static final String IDUTENTE="idUtente";
    private static final String TESTO="testo";
    private static final String VALUTAZIONE="valutazione";
    private static final String DATA="data";
    private static final String UTILITA="utilita";
    
    /**
     * Variabile che gestisce la connessione con il db
     */
    private Connection connection;
    
    /**
     * Costruttore della classe, utilizzato per instaurare la connessione con il db
     */
    public DaoRecensioneOggetto() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    public static ModelloRecensioneOggetto getModelloFromRs(ResultSet rs)
    {
        ModelloRecensioneOggetto RecensioneOggetto = new ModelloRecensioneOggetto();
        
        try{
            RecensioneOggetto.setId(rs.getInt(ID));
            RecensioneOggetto.setIdOggetto(rs.getString(IDOGGETTO));
            RecensioneOggetto.setIdUtente(rs.getInt(IDUTENTE));
            RecensioneOggetto.setTesto(rs.getString(TESTO));
            RecensioneOggetto.setValutazione(rs.getInt(VALUTAZIONE));
            RecensioneOggetto.setData(rs.getDate(DATA));
            RecensioneOggetto.setUtilita(rs.getInt(UTILITA));
        } catch (SQLException e) {
        }
        
        return RecensioneOggetto;
    }
    
    /**
     * @author Mattia
     * Ottenere le recensioni di un oggetto
     * @param idO Un intero che rappresenta l'identificativo dell'oggetto preso in considerazione
     * @return List<ModelloRecensioneOggetto> lista di recensioni
     */
    public List<ModelloRecensioneOggetto> selectReviewsObjects(String idO) {
        List<ModelloRecensioneOggetto> recensioni = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(genericsQuery.selectReviewsObjects(idO));
            while (rs.next()) {
                recensioni.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return recensioni;
    }
    
    /**
     * @author andrea
     * Ottenere trio Recensione, Immagini, Utente
     * @param idO Una stringa che rappresenta l'identificativo dell'oggetto preso in considerazione
     * @return tris<List<ModelloListeImmagineRecensione>,List<ModelloListeImmagini>, List<ModelloUtente>> elenco recensioni, immagini e utente
     */
    public tris<List<ModelloRecensioneOggetto>,List<ModelloListeImmagineRecensione>, List<ModelloUtente>> selectReviewImagesUserByObject(String idO) {
        tris<List<ModelloRecensioneOggetto>,List<ModelloListeImmagineRecensione>, List<ModelloUtente>> res;
        List<ModelloRecensioneOggetto> recensioni = new ArrayList<>();
        List<ModelloListeImmagineRecensione> immagini = new ArrayList<>();
        List<ModelloUtente> utenti = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectReviewUserByObject(idO));
            
            while (rs.next()) {
                recensioni.add(DaoRecensioneOggetto.getModelloFromRs(rs));
                utenti.add(DaoUtente.getModelloFromRs(rs));
            }
        } catch (SQLException e) { }
        
        try {
            Statement statement = connection.createStatement();
            ModelloListeImmagineRecensione immaginiSingleReview;
            
            for(int i = 0; i < recensioni.size(); i++)
            {
                ResultSet rs = statement.executeQuery(objectsQuery.selectImagesByObject(recensioni.get(i).getId()));
                
                immaginiSingleReview = new ModelloListeImmagineRecensione();
                while (rs.next()) {
                    immaginiSingleReview.add(DaoImmagineRecensione.getModelloFromRs(rs));
                }
                
                immagini.add(immaginiSingleReview);
            }
        } catch (SQLException e) { }
        
        res = new tris(recensioni, immagini, utenti);
        return res;
    }
    
    /**
     * @author Mattia
     * Ottenere le proprie recensioni di oggetti
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloRecensioneOggetto> lista di recensioni
     */
    public List<ModelloRecensioneOggetto> selectReviewsObjects(int idU) {
        List<ModelloRecensioneOggetto> recensioni = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectReviewsObjects(idU));
            while (rs.next()) {
                recensioni.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return recensioni;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista delle proprie recensioni Oggetti dalla più utile
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloRecensioneOggetto> lista di recensioni
     */
    public List<ModelloRecensioneOggetto> selectObjectReviewsOrderUseful(int idU) {
        List<ModelloRecensioneOggetto> recensioni = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectObjectReviewsOrderUseful(idU));
            while (rs.next()) {
                recensioni.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return recensioni;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista delle proprie recensioni Oggetti dalla meno utile
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloRecensioneOggetto> lista di recensioni
     */
    public List<ModelloRecensioneOggetto> selectObjectReviewsOrderUseless(int idU) {
        List<ModelloRecensioneOggetto> recensioni = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectObjectReviewsOrderUseless(idU));
            while (rs.next()) {
                recensioni.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return recensioni;
    }
    
    /**
     * @author Mattia
     * Aggiungi una recensione ad un determinato oggetto
     * @param recensione oggetto recensione da inserire
     */
    public void addReviewToObject(ModelloRecensioneOggetto recensione) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(usersQuery.addReviewToObject(recensione.getIdOggetto(), recensione.getIdUtente(), recensione.getTesto(), recensione.getValutazione(), recensione.getUtilita()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }
    
    /**
     * @author Mattia
     * Ottenere un boolean se si ha recensito oppure no un venditore (se il count è 1 vuol dire di si)
     * @param idOggetto oggetto di cui si vuole fare la ricerca
     * @param idUtente utente di cui si vuole fare la ricerca
     * @return int NumRecensioni
     */
    public int reviewOrNotObject(String idOggetto, int idUtente) {
        int numRecensioni = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(usersQuery.reviewOrNotObject(idOggetto, idUtente));
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                numRecensioni = rs.getInt("counter");
            }
        } catch (SQLException e) {
        }

        return numRecensioni;
    }
}
