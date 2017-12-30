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
import it.progettoWeb.java.database.query.admin.adminQuery;
import it.progettoWeb.java.database.query.users.usersQuery;
import java.sql.Date;


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
    private static final String RICHIESTA="richiesta";
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
     * Funzione utilizzata per facilitare l'ottenimento dei modelli da un result set
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
            Assistenza.setRichiesta(rs.getString(RICHIESTA));
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
  
    /**
     * @author Mattia
     * Metodo che ritorna la stringa che rappresenta la query per contare il numero di richieste di assistenza di un amministratore
     * @return int numRichieste
     */
    public int numberRequestOfAssistance(int id) {
        int numRichieste=0;
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(adminQuery.numberRequestOfAssistance(id));
            numRichieste=rs.getInt("numRichieste");
        } catch (SQLException e) {
        }
        
        return numRichieste;
    }
    
    /**
     * @author Mattia
     * Metodo che ritorna il numero di richieste di assistenza in un determinato stato
     * @param stato variabile che identifica lo stato da cercare
     * @return int numRichieste
     */
    public int numberRequestOfAssistanceInAState(int stato) {
        int numRichieste=0;
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(adminQuery.numberRequestOfAssistanceInAState(stato));
            numRichieste=rs.getInt("numRichieste");
        } catch (SQLException e) {
        }
        
        return numRichieste;
    }
    
    /**
     * @author Mattia
     * Metodo che ritorna il numero di richieste di assistenza in un determinato stato di un determinato ammimnistratore
     * @param stato variabile che identifica lo stato da cercare
     * @param id variabile che identifica l'amministratore di cui si vogliono ottenre i dati
     * @return int numRichieste
     */
    public int numberRequestOfAssistanceInAStateOfSpecificAdministrator(int stato, int id) {
        int numRichieste=0;
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(adminQuery.numberRequestOfAssistanceInAStateOfSpecificAdministrator(stato,id));
            numRichieste=rs.getInt("numRichieste");
        } catch (SQLException e) {
        }
        
        return numRichieste;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista delle richieste di assistenza fatte da uno specifico utente
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloAssistenza> lista di richieste di assistenza
     */
    public List<ModelloAssistenza> selectUserAssistances(int idU) {
        List<ModelloAssistenza> richieste = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectAskSupport(idU));
            while (rs.next()) {
                richieste.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return richieste;
    }
    
    /**
     * @author Mattia
     * Ottenere i dettagli di una determinata richiesta di assistenza
     * @param idA Un intero che rappresenta l'identificativo della richiesta di assistenza presa in considerazione
     * @return ModelloAssistenza info richiesta di assistenza
     */
    public ModelloAssistenza selectSpecifiedInfoSupport(int idA) {
        ModelloAssistenza richiesta = new ModelloAssistenza();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectSpecifiedInfoSupport(idA));
            if (rs.next()) {
                richiesta = getModelloFromRs(rs);
            }
        } catch (SQLException e) {
        }

        return richiesta;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di assistenze che hanno a che fare con un ordine
     * @param idO Un intero che rappresenta l'identificativo dell'ordine preso in considerazione
     * @return List<ModelloAssistenza> lista di richieste di assistenza
     */
    public List<ModelloAssistenza> selectSupportOfOrder(int idO) {
        List<ModelloAssistenza> richieste = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectSupportOfOrder(idO));
            while (rs.next()) {
                richieste.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return richieste;
    }
    
    
    /*---2017-12-24---*/
    
    /**
     * @author fbrug
     * Ottenere le richieste di assistenza in un determinato stato (0 = in corso, 1 = chiuse)
     * @param stato Intero rappresentante lo stato della richiesta di assistenza
     * @return String: lista di richieste di assistenza
     */
    public List<ModelloAssistenza> selectAssistanceByState(int stato) {
        List<ModelloAssistenza> richieste = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectAssistanceByState(stato));
            while (rs.next()) {
                richieste.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return richieste;
    }
    
    /**
     * @author fbrug
     * Ottenere le richieste di assistenza in base all'ID dell'amministratore a cui sono state assegnate
     * @param idAdmin Intero rappresentante l'ID dell'amministratore di cui si vogliono le richieste di assistenza assegnate
     * @return String: lista di richieste di assistenza
     */
    public List<ModelloAssistenza> selectAssistanceByAdminId(int idAdmin) {
        List<ModelloAssistenza> richieste = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectAssistanceByAdminId(idAdmin));
            while (rs.next()) {
                richieste.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return richieste;
    }
    
    /**
     * @author fbrug
     * Ottenere le richieste di assistenza in base all'ID dell'amministratore a cui sono state assegnate e in un determinato stato (0 = in corso, 1 = chiuse)
     * @param idAdmin Intero rappresentante l'ID dell'amministratore di cui si vogliono le richieste di assistenza assegnate
     * @param stato Intero rappresentante lo stato della richiesta di assistenza
     * @return String: lista di richieste di assistenza
     */
    public List<ModelloAssistenza> selectAssistanceByAdminIdAndState(int idAdmin, int stato) {
        List<ModelloAssistenza> richieste = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectAssistanceByAdminIdAndState(idAdmin, stato));
            while (rs.next()) {
                richieste.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return richieste;
    }
    
    /*2017-12-25*/
    
    /**
     * @author fbrug
     * Update della soluzione adottata per la richiesta di assistenza selezionata
     * @param idA Intero rappresentate l'ID della richiesta di assistenza selezionata
     * @param solution String rappresentante la soluzione adottata per questa richiesta
     */
    public void updateAssistanceSolution(int idA, String solution)
    {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(usersQuery.updateAssistanceSolution(idA, solution));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    /**
     * @author fbrug
     * Update della data di chiusura della richiesta di assistenza selezionata
     * @param idA Intero rappresentate l'ID della richiesta di assistenza selezionata
     * @param date String rappresentante la data di chiusura della richiesta
     */
    public void updateAssistanceCloseDate(int idA, String date)
    {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(usersQuery.updateAssistanceCloseDate(idA, date));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    /**
     * @author fbrug
     * Update della data di chiusura della richiesta di assistenza selezionata
     * @param idA Intero rappresentate l'ID della richiesta di assistenza selezionata
     * @param state Intero che indica se la richiesta è aperta (0) o chiusa (1)
     */
    public void updateAssistanceState(int idA, int state)
    {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(usersQuery.updateAssistanceState(idA, state));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    /**
     * @author fbrug
     * Update della richiesta di assistenza
     * @param assistance ModelloAssistenza rappresentante la richiesta da modificare
     */
    public void updateAssistance(ModelloAssistenza assistance)
    {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(usersQuery.updateAssistance(assistance));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    /*2017-12-25*/
    
    /**
     * @author fbrug
     * Aggiunta di una richiesta di assistenza
     * @param assistance ModelloAssistenza rappresentante la richiesta di assistenza da aggiungere
     */
    public void insertAssistance(ModelloAssistenza assistance)
    {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(usersQuery.insertAssistance(assistance));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
