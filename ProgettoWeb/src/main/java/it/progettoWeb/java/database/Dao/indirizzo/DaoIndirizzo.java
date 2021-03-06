/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.indirizzo;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella Indirizzo
 * @author mattia
 */

import it.progettoWeb.java.database.Model.indirizzo.ModelloIndirizzo;
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

public class DaoIndirizzo {

    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String IDI="idI";
    private static final String STATO="stato";
    private static final String REGIONE="regione";
    private static final String PROVINCIA="provincia";
    private static final String CITTA="citta";
    private static final String VIA="via";
    private static final String NCIVICO="nCivico";
    private static final String INTERNO="interno";
    private static final String LATITUDINE="latitudine";
    private static final String LONGITUDINE="longitudine";
    
    /**
     * Variabile che gestisce la connessione con il db
     */
    private Connection connection;
    
    /**
     * Costruttore della classe, utilizzato per instaurare la connessione con il db
     */
    public DaoIndirizzo() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    public static ModelloIndirizzo getModelloFromRs(ResultSet rs)
    {
        ModelloIndirizzo Indirizzo = new ModelloIndirizzo();
        
        try{
            Indirizzo.setIdI(rs.getInt(IDI));
            Indirizzo.setStato(rs.getString(STATO));
            Indirizzo.setRegione(rs.getString(REGIONE));
            Indirizzo.setProvincia(rs.getString(PROVINCIA));
            Indirizzo.setCitta(rs.getString(CITTA));
            Indirizzo.setVia(rs.getString(VIA));
            Indirizzo.setnCivico(rs.getInt(NCIVICO));
            Indirizzo.setInterno(rs.getInt(INTERNO));
            Indirizzo.setLatitudine(rs.getDouble(LATITUDINE));
            Indirizzo.setLongitudine(rs.getDouble(LONGITUDINE));
        } catch (SQLException e) {
        }
        
        return Indirizzo;
    }
    
    /**
     * @author Mattia
     * Ottenere indirizzi di un utente avendo l'ID utente
     * @param idUtente
     * @return List<ModelloIndirizzo> elenco indirizzi
     */
    public List<ModelloIndirizzo> selectAddressByUserID(int idUtente) {
        List<ModelloIndirizzo> addreses = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(genericsQuery.selectAddressByUserID(idUtente));
            while (rs.next()) {
                addreses.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return addreses;
    }
    
    public List<ModelloIndirizzo> selectAddressLatLng(double lat, double lng) {
        List<ModelloIndirizzo> addreses = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(genericsQuery.selectAddressLatLng(lat,lng));
            while (rs.next()) {
                addreses.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return addreses;
    }
    
    public ModelloIndirizzo selectAddressByIdAddress(int addressId) {
        ModelloIndirizzo address = new ModelloIndirizzo();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(genericsQuery.selectAddressByIdAddress(addressId));
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                address = getModelloFromRs(rs);
            }
        } catch (SQLException e) {
        }

        return address;
    }
    
    /**
     * @author Mattia
     * Modificare indirizzo utente
     * @param ModelloIndirizzo user
     */
    public String updateUserAddressByAddressID(ModelloIndirizzo address) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(genericsQuery.updateUserAddressByAddressID(address.getIdI(),address.getStato(),address.getRegione(),address.getProvincia(),address.getCitta(),address.getVia(),address.getnCivico(),address.getInterno(),address.getLatitudine(),address.getLongitudine()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            return e.getMessage();
        }
        return "ok";
    }
    
    public boolean insertAddress(ModelloIndirizzo address){
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(usersQuery.insertAddress1(address.getStato(),address.getRegione(),address.getProvincia(),address.getCitta(),address.getVia(),address.getnCivico(),address.getInterno(),address.getLatitudine(),address.getLongitudine()));
            preparedStatement.executeUpdate();
            
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    
    /**
     * @author Mattia
     * aggiungere indirizzo utente
     * @param ModelloIndirizzo user
     */
    public void insertAddress(ModelloIndirizzo address, int idU) {
        try {
            //PreparedStatement preparedStatement = connection
              //      .prepareStatement(usersQuery.insertAddress(address.getStato(),address.getRegione(),address.getProvincia(),address.getCitta(),address.getVia(),address.getnCivico(),address.getInterno(),address.getLatitudine(),address.getLongitudine(),idU));
            //preparedStatement.executeUpdate();
            
            Statement stat = connection.createStatement();
            stat.addBatch(usersQuery.insertAddress1(address.getStato(),address.getRegione(),address.getProvincia(),address.getCitta(),address.getVia(),address.getnCivico(),address.getInterno(),address.getLatitudine(),address.getLongitudine()));
            stat.addBatch(usersQuery.insertAddress2());
            stat.executeBatch();
            
            PreparedStatement preparedStatement = connection.prepareStatement(usersQuery.insertAddress3(address.getStato(),address.getRegione(),address.getProvincia(),address.getCitta(),address.getVia(),address.getnCivico(),address.getInterno(),address.getLatitudine(),address.getLongitudine()));
            ResultSet rs = preparedStatement.executeQuery();
            int idI=1;
            if (rs.next()) {
                idI = rs.getInt("@IDI:=idI");
            }
            
            stat.clearBatch();
            stat.addBatch(usersQuery.insertAddress4(idI,idU));
            stat.executeBatch();
            //stat.addBatch(usersQuery.insertAddress3(address.getStato(),address.getRegione(),address.getProvincia(),address.getCitta(),address.getVia(),address.getnCivico(),address.getInterno(),address.getLatitudine(),address.getLongitudine()));
            //stat.addBatch(usersQuery.insertAddress4(idU));
            
        } catch (SQLException e) {
        }
    }
    
    /**
     * @author Mattia
     * elimina indirizzo utente
     * @param ModelloIndirizzo user
     */
    public void deleteAddress(int idI) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(usersQuery.deleteAddress(idI));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }

    public boolean addressExists(ModelloIndirizzo indirizzo, int idU) {
        int indirizzi = 0;
        
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(genericsQuery.selectNumberOfAddress(indirizzo,idU));
            
            while(rs.next())
                indirizzi = rs.getInt("numIndirizzi");
        } catch(SQLException e) {}
        
        if(indirizzi == 0)
            return false;
        else
            return true;
    }
    
    public List<String> getAllRegions() {
        List<String> regioni = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(genericsQuery.getAllRegions());
            while (rs.next()) {
                regioni.add(rs.getString(1));
            }
        } catch (SQLException e) {
        }

        return regioni;
    }
}
