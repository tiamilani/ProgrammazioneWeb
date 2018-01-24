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
import it.progettoWeb.java.database.query.deliveryType.deliveryTypeQuery;

public class DaoTipoSpedizione {

    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String IDS="idS";
    private static final String IDN="idN";
    private static final String NOME="Nome";
    private static final String PREZZO="Prezzo";
    private static final String CORRIERE="Corriere";
    private static final String TEMPORICHIESTO="tempoRichiesto";
    private static final String NUMEROMASSIMO="numeroMassimo";
    
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
     * Funzione utilizzata per facilitare l'ottenimento dei modelli tipoSpedizione da un result set
     * @param rs un resultset da cui ricavare un modello tipoSpedizione
     * @return il modello tipoSpedizione presente nel resultset
     */
    private ModelloTipoSpedizione getModelloFromRs(ResultSet rs)
    {
        ModelloTipoSpedizione TipoSpedizione = new ModelloTipoSpedizione();
        
        try{
            TipoSpedizione.setIdS(rs.getInt(IDS));
            TipoSpedizione.setIdN(rs.getInt(IDN));
            TipoSpedizione.setNome(rs.getString(NOME));
            TipoSpedizione.setPrezzo(rs.getDouble(PREZZO));
            TipoSpedizione.setCorriere(rs.getString(CORRIERE));
            TipoSpedizione.setTempoRichiesto(rs.getInt(TEMPORICHIESTO));
            TipoSpedizione.setNumeroMassimo(rs.getInt(NUMEROMASSIMO));
        } catch (SQLException e) {
        }
        
        return TipoSpedizione;
    }
    
    /**
     * @author FBrug
     * Ottenere la lista di tutti i tipi di spedizione in base all'ID Spedizione
     * @param idS Intero rappresentante l'ID della spedizione
     * @return String: lista tipi di spedizione
     */
    public List<ModelloTipoSpedizione> selectDeliveryTypesByIdS(int idS)
    {
        List<ModelloTipoSpedizione> types = new ArrayList<>();
        
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(deliveryTypeQuery.selectDeliveryTypeByIdS(idS));
            while(rs.next())
                types.add(getModelloFromRs(rs));
        }
        catch (SQLException e) { System.out.println(e.toString()); }
        
        return types;
    }
    
    /**
     * @author FBrug
     * Ottenere la lista di tutti i tipi di spedizione in base all'ID Negozio
     * @param idN Intero rappresentante l'ID del negozio
     * @return String: lista tipi di spedizione
     */
    public List<ModelloTipoSpedizione> selectDeliveryTypesByIdN(int idN)
    {
        List<ModelloTipoSpedizione> types = new ArrayList<>();
        
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(deliveryTypeQuery.selectDeliveryTypeByIdN(idN));
            while(rs.next())
                types.add(getModelloFromRs(rs));
        }
        catch (SQLException e) { System.out.println(e.toString()); }
        
        return types;
    }
    
    /**
     * @author FBrug
     * Ottenere la lista di tutti i tipi di spedizione in base all'ID Oggetto
     * @param idO Intero rappresentante l'ID dell'oggetto
     * @return String: lista tipi di spedizione
     */
    public List<ModelloTipoSpedizione> selectDeliveryTypesByIdO(String idO)
    {
        List<ModelloTipoSpedizione> types = new ArrayList<>();
        
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(deliveryTypeQuery.selectDeliveryTypeByIdO(idO));
            while(rs.next())
                types.add(getModelloFromRs(rs));
        }
        catch (SQLException e) { System.out.println(e.toString()); }
        
        return types;
    }

    public boolean updateSpedizione(ModelloTipoSpedizione spedizione) {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(deliveryTypeQuery.updateSpedizione(spedizione));
        } catch(SQLException e) {
            return false;
        }
        return true;
    }

    public boolean deleteSpedizione(int idS) {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(deliveryTypeQuery.deleteSpedizione(idS));
        }
        catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean addSpedizione(ModelloTipoSpedizione spedizione) {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(deliveryTypeQuery.insertObject(spedizione));
        } catch(SQLException e) {
            return false;
        }
        return true;
    }
}
