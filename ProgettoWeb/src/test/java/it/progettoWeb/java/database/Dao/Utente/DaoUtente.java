/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.Utente;

/**
 *
 * @author mattia
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import it.progettoWeb.java.database.Util.DbUtil;
import it.progettoWeb.java.database.query.users.usersQuery;

public class DaoUtente {
    private Connection connection;
    
    public DaoUtente() {
        connection = DbUtil.getConnection();
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
                ModelloUtente user = new ModelloUtente();
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setCognome(rs.getString("cognome"));
                user.setMail(rs.getString("mail"));
                user.setPassword(rs.getString("password"));
                user.setAvatar(rs.getInt("avatar"));
                user.setValutazione(rs.getDouble("valutazione"));
                user.setUtenteType(rs.getInt("UtenteType"));
                user.setEmailConfermata(rs.getBoolean("emailConfermata"));
                users.add(user);
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
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setCognome(rs.getString("cognome"));
                user.setMail(rs.getString("mail"));
                user.setPassword(rs.getString("password"));
                user.setAvatar(rs.getInt("avatar"));
                user.setValutazione(rs.getDouble("valutazione"));
                user.setUtenteType(rs.getInt("UtenteType"));
                user.setEmailConfermata(rs.getBoolean("emailConfermata"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
