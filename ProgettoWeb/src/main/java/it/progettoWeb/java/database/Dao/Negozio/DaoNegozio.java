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
}
