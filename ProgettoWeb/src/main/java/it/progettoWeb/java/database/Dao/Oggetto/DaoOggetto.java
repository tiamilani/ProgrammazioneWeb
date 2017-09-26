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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;
import it.progettoWeb.java.database.Model.Oggetto.ModelloOggetto;
import it.progettoWeb.java.database.query.objectSellers.objectSellersQuery;
import it.progettoWeb.java.database.query.objects.objectsQuery;

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
     * @author andrea
     * Ottenere la lista di oggetti che contengono una stringa nel nome
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByName(String nomeDownCase) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByName(nomeDownCase));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti di una categoria
     * @param idCategoria: Intero indicante l'id della categoria
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategory(int idCategoria) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategory(idCategoria));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti di una categoria con una certa stringa nel nome
     * @param idCategoria: Intero indicante l'id della categoria
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndName(int idCategoria, String nomeDownCase) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndName(idCategoria, nomeDownCase));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectHigherThanPrice(double prezzoMin) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectHigherThanPrice(prezzoMin));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectLowerThanPrice(double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectLowerThanPrice(prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectBetweenPrices(double prezzoMin, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectBetweenPrices(prezzoMin, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria
     * @param idCategoria: Intero indicante l'id della categoria
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndHigherThanPrice(int idCategoria, double prezzoMin) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndHigherThanPrice(idCategoria, prezzoMin));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria
     * @param idCategoria: Intero indicante l'id della categoria
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndLowerThanPrice(int idCategoria, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndLowerThanPrice(idCategoria, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria
     * @param idCategoria: Intero indicante l'id della categoria
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndBetweenPrices(int idCategoria, double prezzoMin, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndBetweenPrices(idCategoria, prezzoMin, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByNameAndHigherThanPrice(String nomeDownCase, double prezzoMin) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByNameAndHigherThanPrice(nomeDownCase, prezzoMin));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByNameAndLowerThanPrice(String nomeDownCase, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByNameAndLowerThanPrice(nomeDownCase, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByNameAndBetweenPrices(String nomeDownCase, double prezzoMin, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByNameAndBetweenPrices(nomeDownCase, prezzoMin, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome
     * @param idCategoria: Intero indicante l'id della categoria
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndNameAndHigherThanPrice(int idCategoria, String nomeDownCase, double prezzoMin) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndNameAndHigherThanPrice(idCategoria, nomeDownCase, prezzoMin));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome
     * @param idCategoria: Intero indicante l'id della categoria
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndNameAndLowerThanPrice(int idCategoria, String nomeDownCase, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndNameAndLowerThanPrice(idCategoria, nomeDownCase, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome
     * @param idCategoria: Intero indicante l'id della categoria
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndNameAndBetweenPrices(int idCategoria, String nomeDownCase, double prezzoMin, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndNameAndBetweenPrices(idCategoria, nomeDownCase, prezzoMin, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti di un determinato negozio
     * @param idNegozio: Intero indicante l'identificativo del negozio desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByShop(int idNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByShop(idNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti che contengono una stringa nel nome di un determinato negozio
     * @param idNegozio: Intero indicante l'identificativo del negozio desiderato
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByShopAndName(int idNegozio, String nomeDownCase) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByShopAndName(idNegozio, nomeDownCase));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti di una categoria di un determinato negozio
     * @param idCategoria: Intero indicante l'id della categoria
     * @param idNegozio: Intero indicante l'identificativo del negozio desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndShop(int idCategoria, int idNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndShop(idCategoria, idNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti di una categoria con una certa stringa nel nome di un determinato negozio
     * @param idCategoria: Intero indicante l'id della categoria
     * @param idNegozio: Intero indicante l'identificativo del negozio desiderato
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndShopAndName(int idCategoria, int idNegozio, String nomeDownCase) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndShopAndName(idCategoria, idNegozio, nomeDownCase));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo di un determinato negozio
     * @param idNegozio: Intero indicante l'identificativo del negozio desiderato
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByShopAndHigherThanPrice(int idNegozio, double prezzoMin) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByShopAndHigherThanPrice(idNegozio, prezzoMin));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo di un determinato negozio
     * @param idNegozio: Intero indicante l'identificativo del negozio desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByShopAndLowerThanPrice(int idNegozio, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByShopAndLowerThanPrice(idNegozio, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di un determinato negozio
     * @param idNegozio: Intero indicante l'identificativo del negozio desiderato
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByShopAndBetweenPrices(int idNegozio, double prezzoMin, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByShopAndBetweenPrices(idNegozio, prezzoMin, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di un determinato negozio
     * @param idCategoria: Intero indicante l'id della categoria
     * @param idNegozio: Intero indicante l'identificativo del negozio desiderato
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndShopAndHigherThanPrice(int idCategoria, int idNegozio, double prezzoMin) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndShopAndHigherThanPrice(idCategoria, idNegozio, prezzoMin));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di un determinato negozio
     * @param idCategoria: Intero indicante l'id della categoria
     * @param idNegozio: Intero indicante l'identificativo del negozio desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndShopAndLowerThanPrice(int idCategoria, int idNegozio, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndShopAndLowerThanPrice(idCategoria, idNegozio, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di un determinato negozio
     * @param idCategoria: Intero indicante l'id della categoria
     * @param idNegozio: Intero indicante l'identificativo del negozio desiderato
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndShopAndBetweenPrices(int idCategoria, int idNegozio, double prezzoMin, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndShopAndBetweenPrices(idCategoria, idNegozio, prezzoMin, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome di un determinato negozio
     * @param idNegozio: Intero indicante l'identificativo del negozio desiderato
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByShopAndNameAndHigherThanPrice(int idNegozio, String nomeDownCase, double prezzoMin) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByShopAndNameAndHigherThanPrice(idNegozio, nomeDownCase, prezzoMin));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
     * @param idNegozio: Intero indicante l'identificativo del negozio desiderato
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByShopAndNameAndLowerThanPrice(int idNegozio, String nomeDownCase, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByShopAndNameAndLowerThanPrice(idNegozio, nomeDownCase, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome di un determinato negozio
     * @param idNegozio: Intero indicante l'identificativo del negozio desiderato
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByShopAndNameAndBetweenPrices(int idNegozio, String nomeDownCase, double prezzoMin, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByShopAndNameAndBetweenPrices(idNegozio, nomeDownCase, prezzoMin, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     * @param idNegozio: Intero indicante l'identificativo del negozio desiderato
     * @param idCategoria: Intero indicante l'id della categoria
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndShopAndNameAndHigherThanPrice(int idNegozio, int idCategoria, String nomeDownCase, double prezzoMin) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndShopAndNameAndHigherThanPrice(idNegozio, idCategoria, nomeDownCase, prezzoMin));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     * @param idNegozio: Intero indicante l'identificativo del negozio desiderato
     * @param idCategoria: Intero indicante l'id della categoria
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndShopAndNameAndLowerThanPrice(int idNegozio, int idCategoria, String nomeDownCase, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndShopAndNameAndLowerThanPrice(idNegozio, idCategoria, nomeDownCase, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di un determinato negozio
     * @param idNegozio: Intero indicante l'identificativo del negozio desiderato
     * @param idCategoria: Intero indicante l'id della categoria
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndShopAndNameAndBetweenPrices(int idNegozio, int idCategoria, String nomeDownCase, double prezzoMin, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndShopAndNameAndBetweenPrices(idNegozio, idCategoria, nomeDownCase, prezzoMin, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti in negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente: Intero indicante l'identificativo del soggetto desiderato
     * @param lat: Double contenente il valore della latitudine
     * @param lon: Double contenente il valore della longitudine
     * @param r: Double contenente il valore della raggio di ricerca
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByLLR(int idUtente, double lat, double lon, double r) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByLLR(idUtente, lat, lon, r));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti che contengono una stringa nel nome, nei negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente: Intero indicante l'identificativo del soggetto desiderato
     * @param lat: Double contenente il valore della latitudine
     * @param lon: Double contenente il valore della longitudine
     * @param r: Double contenente il valore della raggio di ricerca
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByNameAndLLR(int idUtente, double lat, double lon, double r, String nomeDownCase) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByNameAndLLR(idUtente, lat, lon, r, nomeDownCase));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti di una categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente: Intero indicante l'identificativo del soggetto desiderato
     * @param lat: Double contenente il valore della latitudine
     * @param lon: Double contenente il valore della longitudine
     * @param r: Double contenente il valore della raggio di ricerca
     * @param idCategoria: Intero indicante l'id della categoria
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndLLR(int idUtente, double lat, double lon, double r, int idCategoria) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndLLR(idUtente, lat, lon, r, idCategoria));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti di una categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente: Intero indicante l'identificativo del soggetto desiderato
     * @param lat: Double contenente il valore della latitudine
     * @param lon: Double contenente il valore della longitudine
     * @param r: Double contenente il valore della raggio di ricerca
     * @param idCategoria: Intero indicante l'id della categoria
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndNameAndLLR(int idUtente, double lat, double lon, double r, int idCategoria, String nomeDownCase) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndNameAndLLR(idUtente, lat, lon, r, idCategoria, nomeDownCase));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente: Intero indicante l'identificativo del soggetto desiderato
     * @param lat: Double contenente il valore della latitudine
     * @param lon: Double contenente il valore della longitudine
     * @param r: Double contenente il valore della raggio di ricerca
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByLLRAndHigherThanPrice(int idUtente, double lat, double lon, double r, double prezzoMin) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByLLRAndHigherThanPrice(idUtente, lat, lon, r, prezzoMin));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente: Intero indicante l'identificativo del soggetto desiderato
     * @param lat: Double contenente il valore della latitudine
     * @param lon: Double contenente il valore della longitudine
     * @param r: Double contenente il valore della raggio di ricerca
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByLLRAndLowerThanPrice(int idUtente, double lat, double lon, double r, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByLLRAndLowerThanPrice(idUtente, lat, lon, r, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente: Intero indicante l'identificativo del soggetto desiderato
     * @param lat: Double contenente il valore della latitudine
     * @param lon: Double contenente il valore della longitudine
     * @param r: Double contenente il valore della raggio di ricerca
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByLLRAndBetweenPrices(int idUtente, double lat, double lon, double r, double prezzoMin, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByLLRAndBetweenPrices(idUtente, lat, lon, r, prezzoMin, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente: Intero indicante l'identificativo del soggetto desiderato
     * @param lat: Double contenente il valore della latitudine
     * @param lon: Double contenente il valore della longitudine
     * @param r: Double contenente il valore della raggio di ricerca
     * @param idCategoria: Intero indicante l'id della categoria
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndLLRAndHigherThanPrice(int idUtente, double lat, double lon, double r, int idCategoria, double prezzoMin) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndLLRAndHigherThanPrice(idUtente, lat, lon, r, idCategoria, prezzoMin));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente: Intero indicante l'identificativo del soggetto desiderato
     * @param lat: Double contenente il valore della latitudine
     * @param lon: Double contenente il valore della longitudine
     * @param r: Double contenente il valore della raggio di ricerca
     * @param idCategoria: Intero indicante l'id della categoria
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndLLRLowerThanPrice(int idUtente, double lat, double lon, double r, int idCategoria, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndLLRLowerThanPrice(idUtente, lat, lon, r, idCategoria, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente: Intero indicante l'identificativo del soggetto desiderato
     * @param lat: Double contenente il valore della latitudine
     * @param lon: Double contenente il valore della longitudine
     * @param r: Double contenente il valore della raggio di ricerca
     * @param idCategoria: Intero indicante l'id della categoria
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndLLRAndBetweenPrices(int idUtente, double lat, double lon, double r, int idCategoria, double prezzoMin, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndLLRAndBetweenPrices(idUtente, lat, lon, r, idCategoria, prezzoMin, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente: Intero indicante l'identificativo del soggetto desiderato
     * @param lat: Double contenente il valore della latitudine
     * @param lon: Double contenente il valore della longitudine
     * @param r: Double contenente il valore della raggio di ricerca
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByNameAndLLRAndHigherThanPrice(int idUtente, double lat, double lon, double r, double prezzoMin, String nomeDownCase) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByNameAndLLRAndHigherThanPrice(idUtente, lat, lon, r, prezzoMin, nomeDownCase));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente: Intero indicante l'identificativo del soggetto desiderato
     * @param lat: Double contenente il valore della latitudine
     * @param lon: Double contenente il valore della longitudine
     * @param r: Double contenente il valore della raggio di ricerca
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByNameAndLLRLowerThanPrice(int idUtente, double lat, double lon, double r, double prezzoMax, String nomeDownCase) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByNameAndLLRLowerThanPrice(idUtente, lat, lon, r, prezzoMax, nomeDownCase));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente: Intero indicante l'identificativo del soggetto desiderato
     * @param lat: Double contenente il valore della latitudine
     * @param lon: Double contenente il valore della longitudine
     * @param r: Double contenente il valore della raggio di ricerca
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByNameAndLLRAndBetweenPrices(int idUtente, double lat, double lon, double r, double prezzoMin, double prezzoMax, String nomeDownCase) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByNameAndLLRAndBetweenPrices(idUtente, lat, lon, r, prezzoMin, prezzoMax, nomeDownCase));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente: Intero indicante l'identificativo del soggetto desiderato
     * @param lat: Double contenente il valore della latitudine
     * @param lon: Double contenente il valore della longitudine
     * @param r: Double contenente il valore della raggio di ricerca
     * @param idCategoria: Intero indicante l'id della categoria
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndNameAndLLRAndHigherThanPrice(int idUtente, double lat, double lon, double r, int idCategoria, double prezzoMin, String nomeDownCase) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndNameAndLLRAndHigherThanPrice(idUtente, lat, lon, r, idCategoria, prezzoMin, nomeDownCase));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente: Intero indicante l'identificativo del soggetto desiderato
     * @param lat: Double contenente il valore della latitudine
     * @param lon: Double contenente il valore della longitudine
     * @param r: Double contenente il valore della raggio di ricerca
     * @param idCategoria: Intero indicante l'id della categoria
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndNameAndLLRLowerThanPrice(int idUtente, double lat, double lon, double r, int idCategoria, double prezzoMax, String nomeDownCase) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndNameAndLLRLowerThanPrice(idUtente, lat, lon, r, idCategoria, prezzoMax, nomeDownCase));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente: Intero indicante l'identificativo del soggetto desiderato
     * @param lat: Double contenente il valore della latitudine
     * @param lon: Double contenente il valore della longitudine
     * @param r: Double contenente il valore della raggio di ricerca
     * @param idCategoria: Intero indicante l'id della categoria
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndNameAndLLRAndBetweenPrices(int idUtente, double lat, double lon, double r, int idCategoria, double prezzoMin, double prezzoMax, String nomeDownCase) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndNameAndLLRAndBetweenPrices(idUtente, lat, lon, r, idCategoria, prezzoMin, prezzoMax, nomeDownCase));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti che contengono una stringa nel nome
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByNameAndPIS(String nomeDownCase, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByNameAndPIS(nomeDownCase, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti di una categoria
     * @param idCategoria: Intero indicante l'id della categoria
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndPIS(int idCategoria, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndPIS(idCategoria, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti di una categoria con una certa stringa nel nome
     * @param idCategoria: Intero indicante l'id della categoria
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndNameAndPIS(int idCategoria, String nomeDownCase, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndNameAndPIS(idCategoria, nomeDownCase, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectHigherThanPriceAndPIS(double prezzoMin, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectHigherThanPriceAndPIS(prezzoMin, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectLowerThanPriceAndPIS(double prezzoMax, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectLowerThanPriceAndPIS(prezzoMax, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectBetweenPricesAndPIS(double prezzoMin, double prezzoMax, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectBetweenPricesAndPIS(prezzoMin, prezzoMax, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria
     * @param idCategoria: Intero indicante l'id della categoria
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndHigherThanPriceAndPIS(int idCategoria, double prezzoMin, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndHigherThanPriceAndPIS(idCategoria, prezzoMin, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria
     * @param idCategoria: Intero indicante l'id della categoria
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndLowerThanPriceAndPIS(int idCategoria, double prezzoMax, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndLowerThanPriceAndPIS(idCategoria, prezzoMax, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria
     * @param idCategoria: Intero indicante l'id della categoria
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndBetweenPricesAndPIS(int idCategoria, double prezzoMin, double prezzoMax, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndBetweenPricesAndPIS(idCategoria, prezzoMin, prezzoMax, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByNameAndHigherThanPriceAndPIS(String nomeDownCase, double prezzoMin, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByNameAndHigherThanPriceAndPIS(nomeDownCase, prezzoMin, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByNameAndLowerThanPriceAndPIS(String nomeDownCase, double prezzoMax, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByNameAndLowerThanPriceAndPIS(nomeDownCase, prezzoMax, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByNameAndBetweenPricesAndPIS(String nomeDownCase, double prezzoMin, double prezzoMax, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByNameAndBetweenPricesAndPIS(nomeDownCase, prezzoMin, prezzoMax, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome
     * @param idCategoria: Intero indicante l'id della categoria
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndNameAndHigherThanPriceAndPIS(int idCategoria, String nomeDownCase, double prezzoMin, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndNameAndHigherThanPriceAndPIS(idCategoria, nomeDownCase, prezzoMin, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome
     * @param idCategoria: Intero indicante l'id della categoria
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndNameAndLowerThanPriceAndPIS(int idCategoria, String nomeDownCase, double prezzoMax, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndNameAndLowerThanPriceAndPIS(idCategoria, nomeDownCase, prezzoMax, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome
     * @param idCategoria: Intero indicante l'id della categoria
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndNameAndBetweenPricesAndPIS(int idCategoria, String nomeDownCase, double prezzoMin, double prezzoMax, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndNameAndBetweenPricesAndPIS(idCategoria, nomeDownCase, prezzoMin, prezzoMax, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti che contengono una stringa nel nome
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByNameAndEndDiscount(String nomeDownCase) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByNameAndEndDiscount(nomeDownCase));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti di una categoria
     * @param idCategoria: Intero indicante l'id della categoria
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndEndDiscount(int idCategoria) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndEndDiscount(idCategoria));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti di una categoria con una certa stringa nel nome
     * @param idCategoria: Intero indicante l'id della categoria
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndNameAndEndDiscount(int idCategoria, String nomeDownCase) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndNameAndEndDiscount(idCategoria, nomeDownCase));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectHigherThanPriceAndEndDiscount(double prezzoMin) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectHigherThanPriceAndEndDiscount(prezzoMin));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectLowerThanPriceAndEndDiscount(double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectLowerThanPriceAndEndDiscount(prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectBetweenPricesAndEndDiscount(double prezzoMin, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectBetweenPricesAndEndDiscount(prezzoMin, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria
     * @param idCategoria: Intero indicante l'id della categoria
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndHigherThanPriceAndEndDiscount(int idCategoria, double prezzoMin) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndHigherThanPriceAndEndDiscount(idCategoria, prezzoMin));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria
     * @param idCategoria: Intero indicante l'id della categoria
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndLowerThanPriceAndEndDiscount(int idCategoria, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndLowerThanPriceAndEndDiscount(idCategoria, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria
     * @param idCategoria: Intero indicante l'id della categoria
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndBetweenPricesAndEndDiscount(int idCategoria, double prezzoMin, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndBetweenPricesAndEndDiscount(idCategoria, prezzoMin, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByNameAndHigherThanPriceAndEndDiscount(String nomeDownCase, double prezzoMin) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByNameAndHigherThanPriceAndEndDiscount(nomeDownCase, prezzoMin));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByNameAndLowerThanPriceAndEndDiscount(String nomeDownCase, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByNameAndLowerThanPriceAndEndDiscount(nomeDownCase, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByNameAndBetweenPricesAndEndDiscount(String nomeDownCase, double prezzoMin, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByNameAndBetweenPricesAndEndDiscount(nomeDownCase, prezzoMin, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome
     * @param idCategoria: Intero indicante l'id della categoria
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndNameAndHigherThanPriceAndEndDiscount(int idCategoria, String nomeDownCase, double prezzoMin) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndNameAndHigherThanPriceAndEndDiscount(idCategoria, nomeDownCase, prezzoMin));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome
     * @param idCategoria: Intero indicante l'id della categoria
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndNameAndLowerThanPriceAndEndDiscount(int idCategoria, String nomeDownCase, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndNameAndLowerThanPriceAndEndDiscount(idCategoria, nomeDownCase, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome
     * @param idCategoria: Intero indicante l'id della categoria
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndNameAndBetweenPricesAndEndDiscount(int idCategoria, String nomeDownCase, double prezzoMin, double prezzoMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndNameAndBetweenPricesAndEndDiscount(idCategoria, nomeDownCase, prezzoMin, prezzoMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti che contengono una stringa nel nome
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByNameAndPISAndEndDiscount(String nomeDownCase, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByNameAndPISAndEndDiscount(nomeDownCase, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti di una categoria
     * @param idCategoria: Intero indicante l'id della categoria
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndPISAndEndDiscount(int idCategoria, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndPISAndEndDiscount(idCategoria, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti di una categoria con una certa stringa nel nome
     * @param idCategoria: Intero indicante l'id della categoria
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndNameAndPISAndEndDiscount(int idCategoria, String nomeDownCase, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndNameAndPISAndEndDiscount(idCategoria, nomeDownCase, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectHigherThanPriceAndPISAndEndDiscount(double prezzoMin, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectHigherThanPriceAndPISAndEndDiscount(prezzoMin, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectLowerThanPriceAndPISAndEndDiscount(double prezzoMax, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectLowerThanPriceAndPISAndEndDiscount(prezzoMax, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectBetweenPricesAndPISAndEndDiscount(double prezzoMin, double prezzoMax, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectBetweenPricesAndPISAndEndDiscount(prezzoMin, prezzoMax, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria
     * @param idCategoria: Intero indicante l'id della categoria
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndHigherThanPriceAndPISAndEndDiscount(int idCategoria, double prezzoMin, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndHigherThanPriceAndPISAndEndDiscount(idCategoria, prezzoMin, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria
     * @param idCategoria: Intero indicante l'id della categoria
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndLowerThanPriceAndPISAndEndDiscount(int idCategoria, double prezzoMax, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndLowerThanPriceAndPISAndEndDiscount(idCategoria, prezzoMax, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria
     * @param idCategoria: Intero indicante l'id della categoria
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndBetweenPricesAndPISAndEndDiscount(int idCategoria, double prezzoMin, double prezzoMax, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndBetweenPricesAndPISAndEndDiscount(idCategoria, prezzoMin, prezzoMax, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByNameAndHigherThanPriceAndPISAndEndDiscount(String nomeDownCase, double prezzoMin, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByNameAndHigherThanPriceAndPISAndEndDiscount(nomeDownCase, prezzoMin, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByNameAndLowerThanPriceAndPISAndEndDiscount(String nomeDownCase, double prezzoMax, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByNameAndLowerThanPriceAndPISAndEndDiscount(nomeDownCase, prezzoMax, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByNameAndBetweenPricesAndPISAndEndDiscount(String nomeDownCase, double prezzoMin, double prezzoMax, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByNameAndBetweenPricesAndPISAndEndDiscount(nomeDownCase, prezzoMin, prezzoMax, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome
     * @param idCategoria: Intero indicante l'id della categoria
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndNameAndHigherThanPriceAndPISAndEndDiscount(int idCategoria, String nomeDownCase, double prezzoMin, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndNameAndHigherThanPriceAndPISAndEndDiscount(idCategoria, nomeDownCase, prezzoMin, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome
     * @param idCategoria: Intero indicante l'id della categoria
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndNameAndLowerThanPriceAndPISAndEndDiscount(int idCategoria, String nomeDownCase, double prezzoMax, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndNameAndLowerThanPriceAndPISAndEndDiscount(idCategoria, nomeDownCase, prezzoMax, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome
     * @param idCategoria: Intero indicante l'id della categoria
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @param prezzoMin: Double indicante il prezzo minimo desiderato
     * @param prezzoMax: Double indicante il prezzo massimo desiderato
     * @param ritiroInNegozio: Boolean indicante se l'opzione ritira in negozio è consentita
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectObjectByCategoryAndNameAndBetweenPricesAndPISAndEndDiscount(int idCategoria, String nomeDownCase, double prezzoMin, double prezzoMax, int ritiroInNegozio) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategoryAndNameAndBetweenPricesAndPISAndEndDiscount(idCategoria, nomeDownCase, prezzoMin, prezzoMax, ritiroInNegozio));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}

        return Objects;
    }
}
