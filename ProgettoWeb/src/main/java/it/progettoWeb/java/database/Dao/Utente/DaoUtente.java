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

import it.progettoWeb.java.database.Dao.Negozio.DaoNegozio;
import it.progettoWeb.java.database.Dao.immagineNegozio.DaoImmagineNegozio;
import it.progettoWeb.java.database.Dao.immagineUtente.DaoImmagineUtente;
import it.progettoWeb.java.database.Dao.indirizzo.DaoIndirizzo;
import it.progettoWeb.java.database.Model.Negozio.ModelloNegozio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import it.progettoWeb.java.database.Model.immagineNegozio.ModelloImmagineNegozio;
import it.progettoWeb.java.database.Model.immagineUtente.ModelloImmagineUtente;
import it.progettoWeb.java.database.Model.indirizzo.ModelloIndirizzo;
import it.progettoWeb.java.database.query.generics.genericsQuery;
import it.progettoWeb.java.database.query.users.usersQuery;
import it.progettoWeb.java.utility.pair.pair;
import it.progettoWeb.java.utility.tris.tris;

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
        }
    }

    public void deleteUser(int userId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(usersQuery.eliminaUtente(userId));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }

    public void updateUser(ModelloUtente user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(usersQuery.updateUtente(user.getId(), user.getNome(), user.getCognome(), user.getMail(), user.getPassword(), user.getAvatar(), user.getValutazione(), user.getUtenteType(), user.isEmailConfermata()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
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
        }

        return users;
    }

    public ModelloUtente getUserById(int userId) {
        ModelloUtente user = new ModelloUtente();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(usersQuery.selectUserById(userId));
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user = getModelloFromRs(rs);
            }
        } catch (SQLException e) {
        }

        return user;
    }
    
    /**
     * @author Mattia
     * Selezionare tutti gli utenti in base al loro tipo: 0=normale, 1=venditore, 2=amministratore
     * @param utenteType
     * @return List<ModelloUtente> elenco utenti
     */
    public List<ModelloUtente> selectAllUsersByType(int utenteType) {
        List<ModelloUtente> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(genericsQuery.selectAllUsersByType(utenteType));
            while (rs.next()) {
                users.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return users;
    }
    
    /**
     * @author Mattia
     * Selezionare utente in base a mail & password
     * @param mail
     * @param password
     * @return ModelloUtente utente
     */
    public ModelloUtente selectUserByEmailAndPassword(String mail, String password) {
        ModelloUtente user = new ModelloUtente();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(genericsQuery.selectUserByEmailAndPassword(mail,password));
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user = getModelloFromRs(rs);
            }
        } catch (SQLException e) {
        }

        return user;
    }
    
    public ModelloUtente selectUserByEmail(String mail) {
        ModelloUtente user = new ModelloUtente();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(genericsQuery.selectUserByEmail(mail));
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user = getModelloFromRs(rs);
            }
        } catch (SQLException e) {
        }

        return user;
    }
    
    /**
     * @author Mattia
     * Selezionare tutti gli utenti con un certo nome
     * @param nome
     * @return List<ModelloUtente> elenco utenti
     */
    public List<ModelloUtente> selectAllUsersByName(String nome) {
        List<ModelloUtente> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(genericsQuery.selectAllUsersByName(nome));
            while (rs.next()) {
                users.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return users;
    }
    
    /**
     * @author Mattia
     * Selezionare tutti gli utenti con un certo nome & cognome
     * @param nome
     * @return List<ModelloUtente> elenco utenti
     */
    public List<ModelloUtente> selectAllUsersByNameAndSurname(String nome, String cognome) {
        List<ModelloUtente> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(genericsQuery.selectAllUsersByNameAndSurname(nome,cognome));
            while (rs.next()) {
                users.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return users;
    }
    
    /**
     * @author Mattia
     * Ottenere i dati di un utente in base all'ID utente
     * @param idUtente
     * @return ModelloUtente utente
     */
    public ModelloUtente selectUserByID(int idUtente) {
        ModelloUtente user = new ModelloUtente();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(genericsQuery.selectUserByID(idUtente));
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                user = getModelloFromRs(rs);
            }
        } catch (SQLException e) {
        }

        return user;
    }
    
    /**
     * @author Mattia
     * Ottenere i dati di un utente e l'indirizzo avendo l'ID utente
     * @param idUtente
     * @return List<pair<ModelloUtente,ModelloIndirizzo>> elenco utenti ed indirizzi
     */
    public List<pair<ModelloUtente,ModelloIndirizzo>> selectUserAndAddressByUserID(int idUtente) {
        List<pair<ModelloUtente,ModelloIndirizzo>> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(genericsQuery.selectUserAndAddressByUserID(idUtente));
            while (rs.next()) {
                pair<ModelloUtente,ModelloIndirizzo> pr = new pair(getModelloFromRs(rs),DaoIndirizzo.getModelloFromRs(rs));
                users.add(pr);
            }
        } catch (SQLException e) {
        }

        return users;
    }
    
    /**
     * @author andrea
     * Ottenere coppia Negozio, Indirizzo
     * @param idUtente
     * @return pair<List<ModelloNegozio>,List<ModelloIndirizzo>> elenco negozi ed indirizzi
     */
    public pair<List<ModelloNegozio>,List<ModelloIndirizzo>> selectStoreAndAddressByUser(int idUtente) {
        pair<List<ModelloNegozio>,List<ModelloIndirizzo>> res;
        List<ModelloNegozio> negozio = new ArrayList<>();
        List<ModelloIndirizzo> indirizzo = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(genericsQuery.selectStoreAndAddressByUser(idUtente));
            
            while (rs.next()) {
                negozio.add(DaoNegozio.getModelloFromRs(rs));
                indirizzo.add(DaoIndirizzo.getModelloFromRs(rs));
            }
        } catch (SQLException e) { }
        
        res = new pair(negozio, indirizzo);
        return res;
    }
    
    /**
     * @author andrea
     * Ottenere trio Negozio, Indirizzo, Immagine
     * @param idUtente
     * @return tris<List<ModelloNegozio>,List<ModelloIndirizzo>, List<ModelloImmagineNegzio>> elenco negozi con indirizzi e immagine
     */
    public tris<List<ModelloNegozio>,List<ModelloIndirizzo>, List<ModelloImmagineNegozio>> selectStoreAndAddressImageByUser(int idUtente) {
        tris<List<ModelloNegozio>,List<ModelloIndirizzo>, List<ModelloImmagineNegozio>> res;
        List<ModelloNegozio> negozi = new ArrayList<>();
        List<ModelloIndirizzo> indirizzi = new ArrayList<>();
        List<ModelloImmagineNegozio> immagini = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(genericsQuery.selectStoreAndAddressImageByUser(idUtente));
            
            while (rs.next()) {
                negozi.add(DaoNegozio.getModelloFromRs(rs));
                indirizzi.add(DaoIndirizzo.getModelloFromRs(rs));
                immagini.add(DaoImmagineNegozio.getModelloFromRs(rs));
            }
        } catch (SQLException e) { }
        
        res = new tris(negozi, indirizzi, immagini);
        return res;
    }
    
    /**
     * @author Mattia
     * Ottenere i dati di un utente, l'indirizzo ed l'immagine a cui fa riferimento
     * @param mail
     * @param password
     * @return triple<ModelloUtente,ModelloIndirizzo,ModelloImmagineUtente> elenco utenti ed indirizzi
     */
    public tris<ModelloUtente,ModelloIndirizzo,ModelloImmagineUtente> selectUserAndAddressAndImageByEmailAndPassword(String mail, String password) {
         tris<ModelloUtente,ModelloIndirizzo,ModelloImmagineUtente> user = new tris(null,null,null);
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(genericsQuery.selectUserAndAddressAndImageByEmailAndPassword(mail,password));
            
            if (rs.next()) {
                user.setL(getModelloFromRs(rs));
                user.setC(DaoIndirizzo.getModelloFromRs(rs));
                user.setR(DaoImmagineUtente.getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return user;
    }
    
    /**
     * @author Mattia
     * Ottenere gli utenti amministratori ordinati per numero di richieste
     * @return List<ModelloUtente> elenco utenti
     */
    public List<ModelloUtente> selectAdministratorByNumerOfRequests() {
        List<ModelloUtente> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(genericsQuery.selectAdministratorByNumerOfRequests());
            while (rs.next()) {
                users.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return users;
    }
    
    /**
     * @author Mattia
     * Modificare mail utente
     * @param ModelloUtente user
     */
    public void updateUserEmailByUserID(ModelloUtente user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(genericsQuery.updateUserEmailByUserID(user.getId(),user.getMail()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }
    
    /**
     * @author Mattia
     * Modificare password utente
     * @param ModelloUtente user
     */
    public void updateUserPasswordByUserID(ModelloUtente user) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement(genericsQuery.updateUserPasswordByUserID(user.getId(),user.getPassword()));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
        }
    }
    
    
}
