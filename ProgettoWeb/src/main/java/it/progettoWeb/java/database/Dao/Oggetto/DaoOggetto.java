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
import it.progettoWeb.java.database.query.objectsMarkets.objectMarketsQuery;
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

    /**
     * @author Mattia
     * ottenere la lista di oggetti di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopId(int idS, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopId(idS,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificName(int idS, String name, int ritiroInNegozio ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificName(idS,name,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti di una categoria di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategory(int idS, int category, int ritiroInNegozio ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategory(idS,category,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificName(int idS, int category, String name, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificName(idS,category,name,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificMinPrice(int idS, double min, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificMinPrice(idS,min,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificMaxPrice(int idS, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificMaxPrice(idS,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificBetweenPrice(int idS, double min, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificBetweenPrice(idS,min,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificMinPrice(int idS,int category, double min, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificMinPrice(idS,category,min,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificMaxPrice(int idS,int category, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificMaxPrice(idS,category,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificBetweenPrice(int idS,int category, double min, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificBetweenPrice(idS,category,min,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificNameAndSpecificMinPrice(int idS,String name, double min, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificNameAndSpecificMinPrice(idS,name,min,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificNameAndSpecificMaxPrice(int idS,String name, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificNameAndSpecificMaxPrice(idS,name,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificNameAndSpecificBetweenPrice(int idS,String name, double min, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificNameAndSpecificBetweenPrice(idS,name,min,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMinPrice(int idS, int category, String name, double min, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMinPrice(idS,category,name,min,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMaxPrice(int idS, int category, String name, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMaxPrice(idS,category,name,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificBetweenPrice(int idS, int category, String name, double min, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificBetweenPrice(idS,category,name,min,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti in negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU,  int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopWithLatitudeAndLongitude(raggio,longitudine,latitudine,idU,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti che contengono una stringa nel nome, nei negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificNameWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU, String name, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificNameWithLatitudeAndLongitude(raggio,longitudine,latitudine,idU,name,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti di una categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU, int category, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryWithLatitudeAndLongitude(raggio,longitudine,latitudine,idU,category,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti di una categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificNameWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU, int category, String name, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificNameWithLatitudeAndLongitude(raggio,longitudine,latitudine,idU,category,name,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificMinPriceWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU, double min, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificMinPriceWithLatitudeAndLongitude(raggio,longitudine,latitudine,idU,min,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo vdi negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificMaxPriceWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificMaxPriceWithLatitudeAndLongitude(raggio,longitudine,latitudine,idU,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificBetweenPriceWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU, double min, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificBetweenPriceWithLatitudeAndLongitude(raggio,longitudine,latitudine,idU,min,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificMinPriceWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU, int category, double min, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificMinPriceWithLatitudeAndLongitude(raggio,longitudine,latitudine,idU,category,min,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificMaxPriceWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU, int category, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificMaxPriceWithLatitudeAndLongitude(raggio,longitudine,latitudine,idU,category,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificBetweenPriceWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU, int category, double min, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificBetweenPriceWithLatitudeAndLongitude(raggio,longitudine,latitudine,idU,category,min,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
    * ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificNameAndSpecificMinPriceWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU, String name, double min, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificNameAndSpecificMinPriceWithLatitudeAndLongitude(raggio,longitudine,latitudine,idU,name,min,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificNameAndSpecificMaxPriceWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU, String name, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificNameAndSpecificMaxPriceWithLatitudeAndLongitude(raggio,longitudine,latitudine,idU,name,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificNameAndSpecificBetweenPriceWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU, String name, double min, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificNameAndSpecificBetweenPriceWithLatitudeAndLongitude(raggio,longitudine,latitudine,idU,name,min,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMinPriceWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU, int category, String name, double min, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMinPriceWithLatitudeAndLongitude(raggio,longitudine,latitudine,idU,category,name,min,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMaxPriceWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU, int category, String name, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMaxPriceWithLatitudeAndLongitude(raggio,longitudine,latitudine,idU,category,name,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificBetweenPriceWithLatitudeAndLongitude(double raggio, double longitudine, double latitudine, int idU, int category, String name, double min, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificBetweenPriceWithLatitudeAndLongitude(raggio,longitudine,latitudine,idU,category,name,min,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

	/**
     * @author Mattia
     * ottenere la lista di oggetti di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdWithDiscount(int idS ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdWithDiscount(idS ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificNameWithDiscount(int idS, String name  ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificNameWithDiscount(idS,name ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti di una categoria di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryWithDiscount(int idS, int category  ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryWithDiscount(idS,category ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificNameWithDiscount(int idS, int category, String name ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificNameWithDiscount(idS,category,name ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificMinPriceWithDiscount(int idS, double min ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificMinPriceWithDiscount(idS,min ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificMaxPriceWithDiscount(int idS, double max ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificMaxPriceWithDiscount(idS,max ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificBetweenPriceWithDiscount(int idS, double min, double max ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificBetweenPriceWithDiscount(idS,min,max ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificMinPriceWithDiscount(int idS,int category, double min ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificMinPriceWithDiscount(idS,category,min ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificMaxPriceWithDiscount(int idS,int category, double max ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificMaxPriceWithDiscount(idS,category,max ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificBetweenPriceWithDiscount(int idS,int category, double min, double max ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificBetweenPriceWithDiscount(idS,category,min,max ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificNameAndSpecificMinPriceWithDiscount(int idS,String name, double min ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificNameAndSpecificMinPriceWithDiscount(idS,name,min ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificNameAndSpecificMaxPriceWithDiscount(int idS,String name, double max ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificNameAndSpecificMaxPriceWithDiscount(idS,name,max ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificNameAndSpecificBetweenPriceWithDiscount(int idS,String name, double min, double max ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificNameAndSpecificBetweenPriceWithDiscount(idS,name,min,max ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMinPriceWithDiscount(int idS, int category, String name, double min ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMinPriceWithDiscount(idS,category,name,min ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMaxPriceWithDiscount(int idS, int category, String name, double max ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMaxPriceWithDiscount(idS,category,name,max ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificBetweenPriceWithDiscount(int idS, int category, String name, double min, double max ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificBetweenPriceWithDiscount(idS,category,name,min,max ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti in negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU,  int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopWithLatitudeAndLongitudeWithDiscount(raggio,longitudine,latitudine,idU ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti che contengono una stringa nel nome, nei negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificNameWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU, String name ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificNameWithLatitudeAndLongitudeWithDiscount(raggio,longitudine,latitudine,idU,name ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti di una categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU, int category ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryWithLatitudeAndLongitudeWithDiscount(raggio,longitudine,latitudine,idU,category ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti di una categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificNameWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU, int category, String name ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificNameWithLatitudeAndLongitudeWithDiscount(raggio,longitudine,latitudine,idU,category,name ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificMinPriceWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU, double min ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificMinPriceWithLatitudeAndLongitudeWithDiscount(raggio,longitudine,latitudine,idU,min ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo vdi negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificMaxPriceWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU, double max ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificMaxPriceWithLatitudeAndLongitudeWithDiscount(raggio,longitudine,latitudine,idU,max ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU, double min, double max ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscount(raggio,longitudine,latitudine,idU,min,max ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificMinPriceWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU, int category, double min ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificMinPriceWithLatitudeAndLongitudeWithDiscount(raggio,longitudine,latitudine,idU,category,min ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificMaxPriceWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU, int category, double max ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificMaxPriceWithLatitudeAndLongitudeWithDiscount(raggio,longitudine,latitudine,idU,category,max));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU, int category, double min, double max ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscount(raggio,longitudine,latitudine,idU,category,min,max ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
    * ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificNameAndSpecificMinPriceWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU, String name, double min ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificNameAndSpecificMinPriceWithLatitudeAndLongitudeWithDiscount(raggio,longitudine,latitudine,idU,name,min ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificNameAndSpecificMaxPriceWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU, String name, double max ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificNameAndSpecificMaxPriceWithLatitudeAndLongitudeWithDiscount(raggio,longitudine,latitudine,idU,name,max ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificNameAndSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU, String name, double min, double max ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificNameAndSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscount(raggio,longitudine,latitudine,idU,name,min,max ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMinPriceWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU, int category, String name, double min ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMinPriceWithLatitudeAndLongitudeWithDiscount(raggio,longitudine,latitudine,idU,category,name,min ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMaxPriceWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU, int category, String name, double max ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMaxPriceWithLatitudeAndLongitudeWithDiscount(raggio,longitudine,latitudine,idU,category,name,max ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     *
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscount(double raggio, double longitudine, double latitudine, int idU, int category, String name, double min, double max ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscount(raggio,longitudine,latitudine,idU,category,name,min,max ));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

	/**
     * @author Mattia
     * ottenere la lista di oggetti di un determinato negozio
     *
  * @param idS variabile contenente l'id dello shop
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdWithDiscountAndPickupInStore(int idS, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdWithDiscountAndPickupInStore(idS,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio
     *
  * @param idS variabile contenente l'id dello shop
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificNameWithDiscountAndPickupInStore(int idS, String name, int ritiroInNegozio ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificNameWithDiscountAndPickupInStore(idS,name,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti di una categoria di un determinato negozio
     *
  * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryWithDiscountAndPickupInStore(int idS, int category, int ritiroInNegozio ) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryWithDiscountAndPickupInStore(idS,category,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio
     *
  * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificNameWithDiscountAndPickupInStore(int idS, int category, String name, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificNameWithDiscountAndPickupInStore(idS,category,name,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio
     *
  * @param idS variabile contenente l'id dello shop
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificMinPriceWithDiscountAndPickupInStore(int idS, double min, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificMinPriceWithDiscountAndPickupInStore(idS,min,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio
     *
  * @param idS variabile contenente l'id dello shop
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificMaxPriceWithDiscountAndPickupInStore(int idS, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificMaxPriceWithDiscountAndPickupInStore(idS,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio
     *
  * @param idS variabile contenente l'id dello shop
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificBetweenPriceWithDiscountAndPickupInStore(int idS, double min, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificBetweenPriceWithDiscountAndPickupInStore(idS,min,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio
     *
  * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificMinPriceWithDiscountAndPickupInStore(int idS,int category, double min, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificMinPriceWithDiscountAndPickupInStore(idS,category,min,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio
     *
  * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificMaxPriceWithDiscountAndPickupInStore(int idS,int category, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificMaxPriceWithDiscountAndPickupInStore(idS,category,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio
     *
  * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificBetweenPriceWithDiscountAndPickupInStore(int idS,int category, double min, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificBetweenPriceWithDiscountAndPickupInStore(idS,category,min,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio
     *
  * @param idS variabile contenente l'id dello shop
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificNameAndSpecificMinPriceWithDiscountAndPickupInStore(int idS,String name, double min, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificNameAndSpecificMinPriceWithDiscountAndPickupInStore(idS,name,min,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
     *
  * @param idS variabile contenente l'id dello shop
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificNameAndSpecificMaxPriceWithDiscountAndPickupInStore(int idS,String name, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificNameAndSpecificMaxPriceWithDiscountAndPickupInStore(idS,name,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
     *
  * @param idS variabile contenente l'id dello shop
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificNameAndSpecificBetweenPriceWithDiscountAndPickupInStore(int idS,String name, double min, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificNameAndSpecificBetweenPriceWithDiscountAndPickupInStore(idS,name,min,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     *
  * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMinPriceWithDiscountAndPickupInStore(int idS, int category, String name, double min, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMinPriceWithDiscountAndPickupInStore(idS,category,name,min,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     *
  * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMaxPriceWithDiscountAndPickupInStore(int idS, int category, String name, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMaxPriceWithDiscountAndPickupInStore(idS,category,name,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     *
  * @param idS variabile contenente l'id dello shop
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificBetweenPriceWithDiscountAndPickupInStore(int idS, int category, String name, double min, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificBetweenPriceWithDiscountAndPickupInStore(idS,category,name,min,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti in negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
 *
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double longitudine, double latitudine, int idU,  int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopWithLatitudeAndLongitudeWithDiscountAndPickupInStore(raggio,longitudine,latitudine,idU,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti che contengono una stringa nel nome, nei negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
 *
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificNameWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double longitudine, double latitudine, int idU, String name, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificNameWithLatitudeAndLongitudeWithDiscountAndPickupInStore(raggio,longitudine,latitudine,idU,name,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti di una categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
 *
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double longitudine, double latitudine, int idU, int category, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryWithLatitudeAndLongitudeWithDiscountAndPickupInStore(raggio,longitudine,latitudine,idU,category,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti di una categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
 *
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificNameWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double longitudine, double latitudine, int idU, int category, String name, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificNameWithLatitudeAndLongitudeWithDiscountAndPickupInStore(raggio,longitudine,latitudine,idU,category,name,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
 *
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificMinPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double longitudine, double latitudine, int idU, double min, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificMinPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(raggio,longitudine,latitudine,idU,min,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo vdi negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
 *
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificMaxPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double longitudine, double latitudine, int idU, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificMaxPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(raggio,longitudine,latitudine,idU,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
 *
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double longitudine, double latitudine, int idU, double min, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(raggio,longitudine,latitudine,idU,min,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
 *
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificMinPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double longitudine, double latitudine, int idU, int category, double min, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificMinPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(raggio,longitudine,latitudine,idU,category,min,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
 *
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificMaxPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double longitudine, double latitudine, int idU, int category, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificMaxPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(raggio,longitudine,latitudine,idU,category,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
 *
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double longitudine, double latitudine, int idU, int category, double min, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(raggio,longitudine,latitudine,idU,category,min,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
    * ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
 *
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificNameAndSpecificMinPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double longitudine, double latitudine, int idU, String name, double min, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificNameAndSpecificMinPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(raggio,longitudine,latitudine,idU,name,min,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
 *
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificNameAndSpecificMaxPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double longitudine, double latitudine, int idU, String name, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificNameAndSpecificMaxPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(raggio,longitudine,latitudine,idU,name,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
 *
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificNameAndSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double longitudine, double latitudine, int idU, String name, double min, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificNameAndSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(raggio,longitudine,latitudine,idU,name,min,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
 *
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMinPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double longitudine, double latitudine, int idU, int category, String name, double min, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMinPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(raggio,longitudine,latitudine,idU,category,name,min,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
 *
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMaxPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double longitudine, double latitudine, int idU, int category, String name, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificMaxPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(raggio,longitudine,latitudine,idU,category,name,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Mattia
     * ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @param idU variabile utilizzata per specificare l'id dell'utente che fa la richiesta
 *
     * @param category variabile che serve a specificare la categoria in cui cercare
     * @param name variabile utilizzata per passare una stringa per la ricerca tra i nomi degli oggetti
     * @param min variabile utilizzata per specificare il valore di prezzo minimo da prendere in considerazione
     * @param max variabile utilizzata per specificare il valore di prezzo massimo da prendere in considerazione
     * @param ritiroInNegozio variabile che specifica l'opzione di ritiro in negozio (utilizzare solo 0 o 1)
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(double raggio, double longitudine, double latitudine, int idU, int category, String name, double min, double max, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectObjectShopIdSpecificCategoryAndSpecificNameAndSpecificBetweenPriceWithLatitudeAndLongitudeWithDiscountAndPickupInStore(raggio,longitudine,latitudine,idU,category,name,min,max,ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }
}
