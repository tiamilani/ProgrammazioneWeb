/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.Negozio;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella Negozio
 * @author mattia
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;
import it.progettoWeb.java.database.query.objectSellers.objectSellersQuery;
import it.progettoWeb.java.database.Model.Negozio.ModelloNegozio;
import it.progettoWeb.java.database.query.objectsMarkets.objectMarketsQuery;
import it.progettoWeb.java.database.query.users.usersQuery;

public class DaoNegozio {
    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String ID="id";
    private static final String IDVENDITORE="idVenditore";
    private static final String NOMENEGOZIO="nomeNegozio";
    private static final String VALUTAZIONE="valutazione";
    private static final String ATTIVO="attivo";
    private static final String IDI="idI";
    private static final String DATAAPERTURA="dataApertura";
    private static final String LINKSITO="linkSito";
    private static final String ORARIONEGOZIO="orarioNegozio";
    
    /**
     * Variabile che gestisce la connessione con il db
     */
    private Connection connection;
    
    /**
     * Costruttore della classe, utilizzato per instaurare la connessione con il db
     */
    public DaoNegozio() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    private ModelloNegozio getModelloFromRs(ResultSet rs)
    {
        ModelloNegozio Store = new ModelloNegozio();
        
        try{
            Store.setId(rs.getInt(ID));
            Store.setIdVenditore(rs.getInt(IDVENDITORE));
            Store.setNomeNegozio(rs.getString(NOMENEGOZIO));
            Store.setValutazione(rs.getDouble(VALUTAZIONE));
            Store.setAttivo(rs.getInt(ATTIVO));
            Store.setIdI(rs.getInt(IDI));
            Store.setDataApertura(rs.getTimestamp(DATAAPERTURA));
            Store.setLinkSito(rs.getString(LINKSITO));
            Store.setOrarioNegozio(rs.getString(ORARIONEGOZIO));
        } catch (SQLException e) {
        }
        
        return Store;
    }
    
    /**
     * @author Mattia
     * Funzione utilizzata per ottenre la lista dei negozio di un utente venditore partendo dall'id
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloNegozio> lista di modelli negozio che risultano dalla query
     */
    public List<ModelloNegozio> selectSellerStore(int id) {
        List<ModelloNegozio> Stores = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerStores(id));
            while (rs.next()) {
                Stores.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Stores;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @return List<ModelloNegozio> lista di negozi
     */
    public List<ModelloNegozio> selectSellerSpecifiedSellerLatLonRad(int id, double lat, double lon, double rad) {
        List<ModelloNegozio> Stores = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerSpecifiedSellerLatLonRad(id,lat,lon,rad));
            while (rs.next()) {
                Stores.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Stores;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista dei negozi da cui ho acquistato
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloNegozio> lista di negozi
     */
    public List<ModelloNegozio> selectStoresUsed(int idU) {
        List<ModelloNegozio> Stores = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectStoresUsed(idU));
            while (rs.next()) {
                Stores.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Stores;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista dei negozi da cui ho acquistato e la loro prima foto
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloNegozio> lista di negozi
     */
    public List<ModelloNegozio> selectStoresUsedAndPhoto(int idU) {
        List<ModelloNegozio> Stores = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectStoresUsedAndPhoto(idU));
            while (rs.next()) {
                Stores.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Stores;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista dei negozi da cui ho acquistato con i dati dell'oggetto acquistato, la prima foto del negozio e la prima dell'oggetto
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloNegozio> lista di negozi
     */
    public List<ModelloNegozio> selectStoresUsedAndDataPhotoSPhotoO(int idU) {
        List<ModelloNegozio> Stores = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectStoresUsedAndDataPhotoSPhotoO(idU));
            while (rs.next()) {
                Stores.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Stores;
    }
    
    /**
     * @author Mattia
     * ottenere la lista di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @return List<ModelloNegozio> lista di negozi
     */
    public List<ModelloNegozio> selectShopWithlatitudeAndLongitude(double raggio, double longitudine, double latitudine) {
        List<ModelloNegozio> Stores = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectShopWithlatitudeAndLongitude(raggio,longitudine,latitudine));
            while (rs.next()) {
                Stores.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Stores;
    }
}
