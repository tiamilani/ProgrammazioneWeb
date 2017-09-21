/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.Utente;

/**
 * Classe utilizzata per gestire gli accessi al database per la tabella utente
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
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import it.progettoWeb.java.database.query.users.usersQuery;

public class DaoUtente {
    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String ID="id";
    private static final String NOME="nome";
    private static final String COGNOME="cognome";
    private static final String MAIL="mail";
    private static final String PASSWORD="password";
    private static final String AVATAR="avatar";
    private static final String VALUTAZIONE="valutazione";
    private static final String UTENTETYPE="UtenteType";
    private static final String EMAILCONFERMATA="emailConfermata";
    
    /**
     * Variabile che gestisce la connessione con il db
     */
    private Connection connection;
    
    /**
     * Costruttore della classe, utilizzato per instaurare la connessione con il db
     */
    public DaoUtente() {
        connection = DbUtil.getConnection();
    }

    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    private ModelloUtente getModelloFromRs(ResultSet rs)
    {
        ModelloUtente User = new ModelloUtente();
        
        try{
            User.setId(rs.getInt(ID));
            User.setNome(rs.getString(NOME));
            User.setCognome(rs.getString(COGNOME));
            User.setMail(rs.getString(MAIL));
            User.setPassword(rs.getString(PASSWORD));
            User.setAvatar(rs.getString(AVATAR));
            User.setValutazione(rs.getDouble(VALUTAZIONE));
            User.setUtenteType(rs.getInt(UTENTETYPE));
            User.setEmailConfermata(rs.getBoolean(EMAILCONFERMATA));
        } catch (SQLException e) {
        }
        
        return User;
    }
    
    
    public void addUser(ModelloUtente user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(usersQuery.inserisciUtente(user.getNome(), user.getCognome(), user.getMail(), user.getPassword(), user.getAvatar(), user.getValutazione(), user.getUtenteType(), user.isEmailConfermata()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(usersQuery.eliminaUtente(userId));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(ModelloUtente user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(usersQuery.updateUtente(user.getId(), user.getNome(), user.getCognome(), user.getMail(), user.getPassword(), user.getAvatar(), user.getValutazione(), user.getUtenteType(), user.isEmailConfermata()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ModelloUtente> getAllUsers() {
        List<ModelloUtente> users = new ArrayList<ModelloUtente>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectAllUsers());
            while (rs.next()) {
                users.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public ModelloUtente getUserById(int userId) {
        ModelloUtente user = new ModelloUtente();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement(usersQuery.selectUserById(userId));
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user = getModelloFromRs(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    
}
