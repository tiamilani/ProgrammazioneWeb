/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.Categoria;


import it.progettoWeb.java.database.Model.Categoria.ModelloCategoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;
import it.progettoWeb.java.database.query.generics.genericsQuery;
import it.progettoWeb.java.database.query.sellers.sellersQuery;

/**
 *
 * @author mattia
 */
public class DaoCategoria {
    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    
    private int id;
    private String nome;
    private String sottoCategoria;
    private String descrizione;
    private int oggettiPresenti;
    
    private static final String ID="id";
    private static final String NAME="nome";
    private static final String SOTTOCATEGORIA="sottoCategoria";
    private static final String DESCRIZIONE="descrizione";
    private static final String OGGETTIPRESENTI="oggettiPresenti";
    
    /**
     * Variabile che gestisce la connessione con il db
     */
    private Connection connection;
    
    /**
     * Costruttore della classe, utilizzato per instaurare la connessione con il db
     */
    public DaoCategoria() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    private ModelloCategoria getModelloFromRs(ResultSet rs)
    {
        ModelloCategoria category = new ModelloCategoria();
        
        try{
            category.setId(rs.getInt(ID));
            category.setNome(rs.getString(NAME));
            category.setSottoCategoria(rs.getString(SOTTOCATEGORIA));
            category.setDescrizione(rs.getString(DESCRIZIONE));
            category.setOggettiPresenti(rs.getInt(OGGETTIPRESENTI));
        } catch (SQLException e) {
        }
        
        return category;
    }
    
    /**
     * @author Mattia
     * @return List<ModelloCategoria>
     */
    public List<ModelloCategoria> selectAllCategory() {
        List<ModelloCategoria> categorie = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(genericsQuery.selectAllCategory());
            while (rs.next()) {
                categorie.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return categorie;
    }
    
    /**
     * Permette di selezionare un oggetto Categoria avendone l'id
     * @param catId
     * @return 
     */
    public ModelloCategoria selectCategoryById(int catId) {
        ModelloCategoria categoria = new ModelloCategoria();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(genericsQuery.selectCategoryById(catId));
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                categoria = getModelloFromRs(rs);
            }
        } catch (SQLException e) {
        }

        return categoria;
    }

    public void increaseCategory(int categoria) {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sellersQuery.increaseCategory(categoria));
        } catch(SQLException e) {}
    }

    public void decraseCategory(int categoria) {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sellersQuery.decraseCategory(categoria));
        } catch(SQLException e) {}
    }
}