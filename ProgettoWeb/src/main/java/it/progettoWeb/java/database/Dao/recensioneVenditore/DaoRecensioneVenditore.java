/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.recensioneVenditore;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella RecensioneVenditore
 * @author mattia
 */

import it.progettoWeb.java.database.Model.recensioneVenditore.ModelloRecensioneVenditore;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;
import it.progettoWeb.java.database.query.sellers.sellersQuery;

public class DaoRecensioneVenditore {

    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String ID="id";
    private static final String IDVENDITORE="idVenditore";
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
    public DaoRecensioneVenditore() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    private ModelloRecensioneVenditore getModelloFromRs(ResultSet rs)
    {
        ModelloRecensioneVenditore RecensioneVenditore = new ModelloRecensioneVenditore();
        
        try{
            RecensioneVenditore.setId(rs.getInt(ID));
            RecensioneVenditore.setIdVenditore(rs.getInt(IDVENDITORE));
            RecensioneVenditore.setIdUtente(rs.getInt(IDUTENTE));
            RecensioneVenditore.setTesto(rs.getString(TESTO));
            RecensioneVenditore.setValutazione(rs.getInt(VALUTAZIONE));
            RecensioneVenditore.setData(rs.getDate(DATA));
            RecensioneVenditore.setUtilita(rs.getInt(UTILITA));
        } catch (SQLException e) {
        }
        
        return RecensioneVenditore;
    }
    
    /**
     * @author fbrug
     * Ottenere la lista delle recensioni ricevute
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista delle recensioni
     */
    public List<ModelloRecensioneVenditore> selectSellerReview(int idVenditore)
    {
        List<ModelloRecensioneVenditore> recensioniV = new ArrayList<>();
        
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectSellerReview(idVenditore));
            
            while(rs.next())
                recensioniV.add(getModelloFromRs(rs));
        } catch(SQLException e) {}
        
        return recensioniV;
    }
}
