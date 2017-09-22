/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.Oggetto;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella Oggetto
 * la gestione dell'inserimento del nome in minuscolo è trasparente all'utente che insersce un oggetto
 * viene gestita da un trigger nella tabella oggetto
 * 
 * E' presente un evento che gestisce la fine degli sconti, li fa terminare e riporta il prezzo alla normalità
 * questa modifica si ripercuote a cascata su oggetti->ordini->carrello
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
import it.progettoWeb.java.database.Model.Oggetto.ModelloOggetto;
import it.progettoWeb.java.database.query.objectSellers.objectSellersQuery;
import it.progettoWeb.java.database.query.users.usersQuery;

public class DaoOggetto {
    
    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String ID="id";
    private static final String IDNEGOZIO="idNegozio";
    private static final String NOME="nome";
    private static final String NOMEDOWNCASE="nomeDownCase";
    private static final String PREZZO="prezzo";
    private static final String DESCRIZIONE="descrizione";
    private static final String RITIROINNEGOZIO="ritiroInNegozio";
    private static final String DISPONIBILITA="disponibilita";
    private static final String STATODISPONIBILITA="statoDisponibilita";
    private static final String SCONTO="sconto";
    private static final String DATAFINESCONTO="dataFineSconto";
    private static final String CATEGORIA="categoria";

    /**
     * Variabile utilizzata per la connessione al database
     */
    private Connection connection;
    
    /**
     * Costruttore del Dao, instaura la connessione
     */
    public DaoOggetto() {
        connection = DbUtil.getConnection();
    }    
    
    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli Oggetto da un result set
     * @param rs un resultset da cui ricavare un modello Oggetto
     * @return il modello Oggetto presente nel resultset
     */
    private ModelloOggetto getModelloFromRs(ResultSet rs)
    {
        ModelloOggetto Object = new ModelloOggetto();
        
        try{
            Object.setId(rs.getString(ID));
            Object.setIdNegozio(rs.getInt(IDNEGOZIO));
            Object.setNome(rs.getString(NOME));
            Object.setNomeDownCase(rs.getString(NOMEDOWNCASE));
            Object.setPrezzo(rs.getDouble(PREZZO));
            Object.setDescrizione(rs.getString(DESCRIZIONE));
            Object.setRitiroInNegozio(rs.getInt(RITIROINNEGOZIO));
            Object.setDisponibilita(rs.getInt(DISPONIBILITA));
            Object.setStatoDisponibilita(rs.getInt(STATODISPONIBILITA));
            Object.setSconto(rs.getDouble(SCONTO));
            Object.setDataFineSconto(rs.getDate(DATAFINESCONTO));
            Object.setCategoria(rs.getInt(CATEGORIA));
        } catch (SQLException e) {
        }
        
        return Object;
    }
    
    /**
     * @author Mattia
     * Funzione utilizzata per ottenre la lista degli oggetti di un utente venditore partendo dall'id
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloOggetto> lista di modelli Oggetto che risultano dalla query
     */
    public List<ModelloOggetto> selectSellerObjects(int id) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjects(id));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti che contengono una stringa nel nome nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsWithString(int id, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsWithString(id,pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo minimo dei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @return String: lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerMinPrice(int id, int priceMin) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerMinPrice(id,priceMin));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo massimo nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerMaxPrice(int id, int priceMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerMaxPrice(id,priceMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerMinMaxPrice(int id, int priceMin, int priceMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerMinMaxPrice(id,priceMin,priceMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerMinPriceAndCategory(int id, int priceMin, int cat) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerMinPriceAndCategory(id,priceMin,cat));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerMaxPriceAndCategory(int id, int priceMax, int cat) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerMaxPriceAndCategory(id,priceMax,cat));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerMinMaxPriceAndCategory(int id, int priceMin, int priceMax, int cat) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerMinMaxPriceAndCategory(id,priceMin,priceMax,cat));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerMinPriceAndName(int id, int priceMin, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerMinPriceAndName(id,priceMin,pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerMaxPriceAndName(int id, int priceMax, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerMaxPriceAndName(id,priceMax,pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerMinMaxPriceAndName(int id, int priceMin, int priceMax, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerMinMaxPriceAndName(id,priceMin,priceMax,pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerMinPriceAndNameAndCategory(int id, int priceMin, int cat, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerMinPriceAndNameAndCategory(id,priceMin,cat,pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerMaxPriceAndNameAndCategory(int id, int priceMax, int cat, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerMaxPriceAndNameAndCategory(id,priceMax,cat,pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerMinMaxPriceAndNameAndCategory(int id, int priceMin, int priceMax, int cat, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerMinMaxPriceAndNameAndCategory(id,priceMin,priceMax,cat,pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerLatLonRad(int id,int idU, double lat, double lon, double rad) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerLatLonRad(idU,id,lat,lon,rad));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti che contengono una stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerLatLonRadAndName(int idU,int id, double lat, double lon, double rad, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerLatLonRadAndName(idU,id,lat,lon,rad,pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti di una categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerLatLonRadAndCategory(int idU,int id, double lat, double lon, double rad, int cat) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerLatLonRadAndCategory(idU,id,lat,lon,rad,cat));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti di una categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerLatLonRadAndNameCategory(int idU,int id, double lat, double lon, double rad, int cat, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerLatLonRadAndNameCategory(idU,id,lat,lon,rad,cat,pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo minimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerLatLonRadAndMinPrice(int idU,int id, double lat, double lon, double rad, int priceMin) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerLatLonRadAndMinPrice(idU,id,lat,lon,rad,priceMin));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo massimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerLatLonRadAndMaxPrice(int idU,int id, double lat, double lon, double rad, int priceMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerLatLonRadAndMaxPrice(idU,id,lat,lon,rad,priceMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerLatLonRadAndMinMaxPrice(int idU,int id, double lat, double lon, double rad, int priceMin, int priceMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerLatLonRadAndMinMaxPrice(idU,id,lat,lon,rad,priceMin,priceMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerLatLonRadAndMinPriceCategory(int idU, int id, double lat, double lon, double rad, int priceMin, int cat) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerLatLonRadAndMinPriceCategory(idU,id,lat,lon,rad,priceMin,cat));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerLatLonRadAndMaxPriceCategory(int idU, int id, double lat, double lon, double rad, int priceMax, int cat) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerLatLonRadAndMaxPriceCategory(idU,id,lat,lon,rad,priceMax,cat));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerLatLonRadAndMinMaxPriceCategory(int idU, int id, double lat, double lon, double rad, int priceMin, int priceMax, int cat) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerLatLonRadAndMinMaxPriceCategory(idU,id,lat,lon,rad,priceMin,priceMax,cat));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerLatLonRadAndMinPriceName(int idU, int id, double lat, double lon, double rad, int priceMin, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerLatLonRadAndMinPriceName(idU,id,lat,lon,rad,priceMin,pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerLatLonRadAndMaxPriceName(int idU, int id, double lat, double lon, double rad, int priceMax, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerLatLonRadAndMaxPriceName(idU,id,lat,lon,rad,priceMax,pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerLatLonRadAndMinMaxPriceName(int idU, int id, double lat, double lon, double rad, int priceMin, int priceMax, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerLatLonRadAndMinMaxPriceName(idU,id,lat,lon,rad,priceMin,priceMax,pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerLatLonRadAndMinPriceCategoryName(int idU, int id, double lat, double lon, double rad, int priceMin, int cat, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerLatLonRadAndMinPriceCategoryName(idU,id,lat,lon,rad,priceMin,cat,pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerLatLonRadAndMaxPriceCategoryName(int idU, int id, double lat, double lon, double rad, int priceMax, int cat, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerLatLonRadAndMaxPriceCategoryName(idU,id,lat,lon,rad,priceMax,cat,pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerLatLonRadAndMinMaxPriceCategoryName(int idU, int id, double lat, double lon, double rad, int priceMin, int priceMax, int cat, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerLatLonRadAndMinMaxPriceCategoryName(idU,id,lat,lon,rad,priceMin,priceMax,cat,pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista dei prodotti nella stessa fascia di prezzo e categoria di quelli già acquistati
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectProductsSamePriceCategoryAlreadyBought(int idU) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectProductsSamePriceCategoryAlreadyBought(idU));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Objects;
    }
}
