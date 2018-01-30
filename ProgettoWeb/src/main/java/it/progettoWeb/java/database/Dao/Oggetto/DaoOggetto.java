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

import info.debatty.java.stringsimilarity.JaroWinkler;
import it.progettoWeb.java.database.Dao.immagineOggetto.DaoImmagineOggetto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;
import it.progettoWeb.java.database.Model.Oggetto.ModelloOggetto;
import it.progettoWeb.java.database.Model.immagineOggetto.ModelloImmagineOggetto;
import it.progettoWeb.java.database.query.generics.genericsQuery;
import it.progettoWeb.java.database.query.marketsSellers.marketsSellersQuery;
import it.progettoWeb.java.database.query.objectSellers.objectSellersQuery;
import it.progettoWeb.java.database.query.objects.objectsQuery;
import it.progettoWeb.java.database.query.sellers.sellersQuery;
import java.sql.Date;
import it.progettoWeb.java.database.query.objectsMarkets.objectMarketsQuery;
import it.progettoWeb.java.database.query.users.usersQuery;
import it.progettoWeb.java.utility.pair.pair;
import java.sql.PreparedStatement;
import java.util.Comparator;
import java.util.PriorityQueue;

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
    private static final String VALUTAZIONE="valutazione";
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
    public static ModelloOggetto getModelloFromRs(ResultSet rs)
    {
        ModelloOggetto Object = new ModelloOggetto();

        try{
            Object.setId(rs.getString(ID));
            Object.setIdNegozio(rs.getInt(IDNEGOZIO));
            Object.setNome(rs.getString(NOME));
            Object.setNomeDownCase(rs.getString(NOMEDOWNCASE));
            Object.setPrezzo(rs.getDouble(PREZZO));
            Object.setDescrizione(rs.getString(DESCRIZIONE));
            Object.setValutazione(rs.getDouble(VALUTAZIONE));
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
     * @param idV Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloOggetto> lista di modelli Oggetto che risultano dalla query
     */
    public List<ModelloOggetto> selectSellerObjects(int idV) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjects(idV));
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

    public List<ModelloOggetto> selectRandomObjects(int limit) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(genericsQuery.selectRandomObjects(limit));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author andrea
     * Ottenere coppia Oggetto, Immagine
     * @param limit Quanti oggetti ricavare
     * @return pair<List<ModelloOggetto>,List<ModelloImmagineOggetto>> elenco oggetti ed immagine
     */
    public pair<List<ModelloOggetto>, List<ModelloImmagineOggetto>> selectRandomObjectsAndImage(int limit) {
        pair<List<ModelloOggetto>,List<ModelloImmagineOggetto>> res;
        List<ModelloOggetto> oggetti = new ArrayList<>();
        List<ModelloImmagineOggetto> immagini = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(genericsQuery.selectRandomObjectsAndImages(limit));
            while (rs.next()) {
                oggetti.add(DaoOggetto.getModelloFromRs(rs));
                immagini.add(DaoImmagineOggetto.getModelloFromRs(rs));
            }
        } catch (SQLException e) { }

        res = new pair(oggetti, immagini);
        return res;
    }

    /**
     * @author andrea
     * Ottenere coppia Oggetto, Immagine
     * @param idNegozio Il negozio che vende determinati oggetti
     * @return pair<List<ModelloOggetto>,List<ModelloImmagineOggetto>> elenco oggetti ed immagine
     */
    public pair<List<ModelloOggetto>, List<ModelloImmagineOggetto>> selectObjectsImageSelledByStoreID(int idNegozio) {
        pair<List<ModelloOggetto>,List<ModelloImmagineOggetto>> res;
        List<ModelloOggetto> oggetti = new ArrayList<>();
        List<ModelloImmagineOggetto> immagini = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(genericsQuery.selectObjectsImageSelledByStoreID(idNegozio));
            while (rs.next()) {
                oggetti.add(DaoOggetto.getModelloFromRs(rs));
                immagini.add(DaoImmagineOggetto.getModelloFromRs(rs));
            }
        } catch (SQLException e) { }

        res = new pair(oggetti, immagini);
        return res;
    }

    public ModelloOggetto getObjectById(String objectId) {
        ModelloOggetto oggetto = new ModelloOggetto();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(objectsQuery.selectObjectById(objectId));
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                oggetto = getModelloFromRs(rs);
            }
        } catch (SQLException e) {
        }

        return oggetto;
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
        } catch (SQLException e) {}
        return Objects;
    }

    /**
     * @author andrea
     * Ottenere la lista di oggetti di una categoria
     * @param idCategoria: Intero indicante l'id della categoria
     * @return List<ModelloOggetto> lista di oggetti
     */
    public pair<List<ModelloOggetto>, List<ModelloImmagineOggetto>> selectObjectByCategory(int idCategoria) {
        pair<List<ModelloOggetto>, List<ModelloImmagineOggetto>> res;
        List<ModelloOggetto> Objects = new ArrayList<>();
        List<ModelloImmagineOggetto> immagini = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectObjectByCategory(idCategoria));
            while (rs.next()) {
                Objects.add(DaoOggetto.getModelloFromRs(rs));
                immagini.add(DaoImmagineOggetto.getModelloFromRs(rs));
            }
        } catch (SQLException e) {}


        res = new pair(Objects, immagini);
        return res;
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



    /**
     * @author fbrug
     * Ottenere la lista dei prodotti venduti raggruppati per categoria e negozio
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista dei prodotti
     */
    public List<ModelloOggetto> selectObjectSaledGroupByCategoryAndShop(int idVenditore)
    {
        List<ModelloOggetto> oggetti = new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectObjectSaledGroupByCategoryAndShop(idVenditore));

            while(rs.next())
                oggetti.add(getModelloFromRs(rs));
        } catch(SQLException e) {}

        return oggetti;
    }

    /**
     * @author fbrug
     * Ottenere la lista dei prodotti venduti in una determinata categoria raggruppati per negozio
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @param nomeCategoria: rappresenta il nome della categoria da ricercare
     * @return String: lista dei prodotti
     */
    public List<ModelloOggetto> selectObjectSaledGroupByShop(int idVenditore, String nomeCategoria)
    {
        List<ModelloOggetto> oggetti = new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectObjectSaledGroupByShop(idVenditore, nomeCategoria));

            while(rs.next())
                oggetti.add(getModelloFromRs(rs));
        } catch(SQLException e) {}

        return oggetti;
    }

    /**
     * @author fbrug
     * Ottenere la lista dei prodotti venduti ordinati per valutazioni
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista dei prodotti
     */
    public List<ModelloOggetto> selectObjectSaledOrderedByRating(int idVenditore)
    {
        List<ModelloOggetto> oggetti = new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectObjectSaledOrderedByRating(idVenditore));

            while(rs.next())
                oggetti.add(getModelloFromRs(rs));
        } catch(SQLException e) {}

        return oggetti;
    }

    /**
     * @author fbrug
     * Ottenere la lista dei proprio prodotti in sconto raggruppati per categoria e negozio
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista dei prodotti
     */
    public List<ModelloOggetto> selectObjectDiscountedGroupByShopAndCategory(int idVenditore)
    {
        List<ModelloOggetto> oggetti = new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectObjectDiscountedGroupByShopAndCategory(idVenditore));

            while(rs.next())
                oggetti.add(getModelloFromRs(rs));
        } catch(SQLException e) {}

        return oggetti;
    }

    /**
     * @author fbrug
     * Ottenere la lista dei proprio prodotti in sconto raggruppati per categoria e negozio ordinati per scadenza più vicina dello sconto
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista dei prodotti
     */
    public List<ModelloOggetto> selectObjectsDiscountedGrupByShopAndCategoryOrderedByDeadline(int idVenditore)
    {
        List<ModelloOggetto> oggetti = new ArrayList<>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectObjectsDiscountedGrupByShopAndCategoryOrderedByDeadline(idVenditore));

            while(rs.next())
                oggetti.add(getModelloFromRs(rs));
        } catch(SQLException e) {}

        return oggetti;
    }

    /**
     * @author fbrug
     * Aggiungere un prodotto (OGGETTO) ad un proprio negozio
     * @param idNegozio: intero rapprensentante l'ID del negozio in cui inserire il nuovo oggetto
     * @param nomeOggetto: nome del nuovo oggetto da inserire
     * @param prezzoOggetto: prezzo del nuovo oggetto da inserire
     * @param descrizioneOggetto: descrizione del nuovo oggetto da inserire
     * @param ritiroInNegozio: disponibilità o meno del ritiro in negozio per l'oggetto (0 = no, 1 = si)
     * @param disponibilita: rappresenta la quantità dell'oggetto disponibile in negozio
     * @param statoDisponibilita: indica lo stato della disponibilità
     * @param sconto: indica lo sconto applicato all'oggetto
     * @param dataFineSconto: indica la data in cui terminerà lo sconto applicato all'oggetto
     * @param idCategoria: intero rappresentante l'ID della categoria di cui l'oggetto fa parte
     */
    public boolean insertObject(String id,int idNegozio, String nomeOggetto,String nomeDownCase, double prezzoOggetto,
            String descrizioneOggetto, int ritiroInNegozio, int disponibilita, int statoDisponibilita,
            double sconto, Date dataFineSconto, int idCategoria)
    {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sellersQuery.insertObject(id,idNegozio, nomeOggetto,nomeDownCase, prezzoOggetto, descrizioneOggetto,
                    ritiroInNegozio, disponibilita, statoDisponibilita, sconto, dataFineSconto, idCategoria));
        } catch(SQLException e) {
            return false;
        }
        return true;
    }

    /**
     * @author fbrug
     * Rimuovere un oggetto da un proprio negozio
     * @return String: conferma avvenuta operazione
     */
    public boolean deleteObject(String idOggetto)
    {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sellersQuery.deleteObject(idOggetto));
        }
        catch (SQLException e) {
            return false;
        }
        return true;
    }

    public boolean updateObjectStars(String idOggetto, double value)
    {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sellersQuery.updateObjectStars(idOggetto, value));
        }
        catch (SQLException e) {
            return false;
        }
        return true;
    }

    /**
     * @author fbrug
     * Modificare il prezzo di un oggetto di un proprio negozio
     * @param idOggetto: stringa rappresentante l'ID dell'oggetto a cui modificare il prezzo
     * @param prezzoOggetto: il nuovo prezzo dell'oggetto
     */
    public void updateObjectPrice(String idOggetto, double prezzoOggetto)
    {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sellersQuery.updateObjectPrice(idOggetto, prezzoOggetto));
        }
        catch (SQLException e) {}
    }

    /**
     * @author fbrug
     * Modificare lo sconto di un oggetto
     * @param idOggetto: stringa rappresentante l'ID dell'oggetto a cui modificare il prezzo
     * @param sconto: il nuovo sconto applicato all'oggetto
     */
    public void updateObjectDiscount(String idOggetto, double sconto)
    {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sellersQuery.updateObjectDiscount(idOggetto, sconto));
        }
        catch (SQLException e) {}
    }

    /**
     * @author fbrug
     * Aggiungere una immagine del profilo di un utente
     * @param idOggetto: stringa rappresentante l'ID dell'oggetto a cui inserire l'immagine
     * @param imagePath: il path della nuova immagine
     */
    public void insertObjectImage(String idOggetto, String imagePath)
    {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sellersQuery.insertObjectImage(idOggetto, imagePath));
        }
        catch (SQLException e) {}
    }

    /**
     * @author fbrug
     * Modificare l'immagine del profilo di un oggetto con un determinato ID oggetto
     * @param idOggetto: stringa rappresentante l'ID dell'oggetto a cui cambiare l'immagine
     * @param oldImagePath: il path dell'immagine da modificare
     * @param newImagePath: il path della nuova immagine
     */
    public void updateObjectImage(String idOggetto, String oldImagePath, String newImagePath)
    {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sellersQuery.updateObjectImage(idOggetto, oldImagePath, newImagePath));
        }
        catch (SQLException e) {}
    }

    /**
     * @author fbrug
     * Modificare la quantita' di un oggetto
     * @param idOggetto: stringa rappresentante l'ID dell'oggetto a cui modificare il prezzo
     * @param newQuantity: intero rappresentante la nuova quantita' disponibile per l'oggetto in questione
     */
    public void updateObjectQuantity(String idOggetto, int newQuantity)
    {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sellersQuery.updateObjectQuantity(idOggetto, newQuantity));
        }
        catch (SQLException e) {}
    }

    /**
     * @author fbrug
     * Modificare la quantita' di un oggetto
     * @param idOggetto: stringa rappresentante l'ID dell'oggetto a cui modificare il prezzo
     * @param newState: intero rappresentante il nuovo stato di disponibilita' per l'oggetto in questione
     */
    public void updateObjectStatus(String idOggetto, int newState)
    {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sellersQuery.updateObjectStatus(idOggetto, newState));
        }
        catch (SQLException e) {}
    }

    /**
     * @author fbrug
     * Rimuovere l'immagine di un determinato oggetto
     * @param idOggetto: string rappresentante l'ID dell'oggetto a cui rimuovere l'immagine
     * @param imagePath: il path dell'immagine da rimuovere
     */
    public void deleteObjectImage(String idOggetto, String imagePath)
    {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sellersQuery.deleteObjectImage(idOggetto, imagePath));
        }
        catch (SQLException e) {}
    }

    /**
     * @author Damiano
     * Funzione utilizzata per ottenre la lista degli oggetti di un utente venditore partendo dall'id
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di modelli Oggetto che risultano dalla query
     */
    public List<ModelloOggetto> itemsInShopBySellerSpecific(int id, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInShopBySellerSpecific(id, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Funzione utilizzata per ottenre la lista degli oggetti in sconto di un utente venditore partendo dall'id
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloOggetto> lista di modelli Oggetto che risultano dalla query
     */
    public List<ModelloOggetto> itemsInShopBySellerOnDiscount(int id) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInShopBySellerOnDiscount(id));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Funzione utilizzata per ottenre la lista degli oggetti in sconto di un utente venditore partendo dall'id
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di modelli Oggetto che risultano dalla query
     */
    public List<ModelloOggetto> itemsInShopBySellerSpecificOnDiscount(int id, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInShopBySellerSpecificOnDiscount(id, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti che contengono una stringa nel nome nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithStringInShopBySellerSpecific(int id, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithStringInShopBySellerSpecific(id, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto che contengono una stringa nel nome nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithStringInShopBySellerOnDiscount(int id, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithStringInShopBySellerOnDiscount(id, pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto che contengono una stringa nel nome nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno     * @param ritiro
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithStringInShopBySellerSpecificOnDiscount(int id, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithStringInShopBySellerSpecificOnDiscount(id, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti di una categoria nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerAndCategory(int id, int cat) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerAndCategory(id, cat));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti di una categoria nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryInShopBySellerSpecific(int id, int cat, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryInShopBySellerSpecific(id, cat, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto di una categoria nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryInShopBySellerOnDiscount(int id, int cat) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryInShopBySellerOnDiscount(id, cat));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto di una categoria nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryInShopBySellerSpecificOnDiscount(int id, int cat, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryInShopBySellerSpecificOnDiscount(id, cat, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti di una categoria nei negozi di un determinato venditore con una stringa nel nome
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithStringInShopBySeller(int id, int cat, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithStringInShopBySeller(id, cat, pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti di una categoria nei negozi di un determinato venditore con una stringa nel nome e
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithStringInShopBySellerSpecific(int id, int cat, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithStringInShopBySellerSpecific(id, cat, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto di una categoria nei negozi di un determinato venditore con una stringa nel nome
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithStringInShopBySellerOnDiscount(int id, int cat, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithStringInShopBySellerOnDiscount(id, cat, pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto di una categoria nei negozi di un determinato venditore con una stringa nel nome e
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa che deve essere presente nel nome dell'oggetto cercato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithStringInShopBySellerSpecificOnDiscount(int id, int cat, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithStringInShopBySellerSpecificOnDiscount(id, cat, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo massimo nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato* @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno* @param ritiro
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceHigherThanBySellerSpecific(int id, int priceMin, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceHigherThanBySellerSpecific(id, priceMin, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo massimo nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato* @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceHigherThanBySellerOnDiscount(int id, int priceMin) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceHigherThanBySellerOnDiscount(id, priceMin));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo massimo nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato* @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno* @param ritiro
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceHigherThanBySellerSpecificOnDiscount(int id, int priceMin, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceHigherThanBySellerSpecificOnDiscount(id, priceMin, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo massimo nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceLowerThanBySellerSpecific(int id, int priceMax, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceLowerThanBySellerSpecific(id, priceMax, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo massimo nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceLowerThanBySellerOnDiscount(int id, int priceMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceLowerThanBySellerOnDiscount(id, priceMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo massimo nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceLowerThanBySellerSpecificOnDiscount(int id, int priceMax, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceLowerThanBySellerSpecificOnDiscount(id, priceMax, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno* @param ritiro
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceBetweenRangeBySellerSpecific(int id, int priceMin, int priceMax, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceBetweenRangeBySellerSpecific(id, priceMin, priceMax, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo ed un certo prezzo massimo nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceBetweenRangeBySellerOnDiscount(int id, int priceMin, int priceMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceBetweenRangeBySellerOnDiscount(id, priceMin, priceMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo ed un certo prezzo massimo nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno* @param ritiro
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceBetweenRangeBySellerSpecificOnDiscount(int id, int priceMin, int priceMax, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceBetweenRangeBySellerSpecificOnDiscount(id, priceMin, priceMax, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceHigherThanBySellerSpecific(int id, int priceMin, int cat, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceHigherThanBySellerSpecific(id, priceMin, cat, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo in una certa categoria nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceHigherThanBySellerOnDiscount(int id, int priceMin, int cat) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceHigherThanBySellerOnDiscount(id, priceMin, cat));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo in una certa categoria nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceHigherThanBySellerSpecificOnDiscount(int id, int priceMin, int cat, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceHigherThanBySellerSpecificOnDiscount(id, priceMin, cat, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceLowerThanBySellerSpecific(int id, int priceMax, int cat, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceLowerThanBySellerSpecific(id, priceMax, cat, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceLowerThanBySellerOnDiscount(int id, int priceMax, int cat) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceLowerThanBySellerOnDiscount(id,priceMax,cat));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceLowerThanBySellerSpecificOnDiscount(int id, int priceMax, int cat, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceLowerThanBySellerSpecificOnDiscount(id, priceMax, cat, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceBetweenRangeBySellerSpecific(int id, int priceMin, int priceMax, int cat, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceBetweenRangeBySellerSpecific(id, priceMin, priceMax, cat, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

        /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceBetweenRangeBySellerOnDiscount(int id, int priceMin, int priceMax, int cat) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceBetweenRangeBySellerOnDiscount(id, priceMin, priceMax, cat));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceBetweenRangeBySellerSpecificOnDiscount(int id, int priceMin, int priceMax, int cat, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceBetweenRangeBySellerSpecificOnDiscount(id, priceMin, priceMax, cat, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceHigherThanWithStringBySellerSpecific(int id, int priceMin, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceHigherThanWithStringBySellerSpecific(id, priceMin, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo con una certa stringa nel nome nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceHigherThanWithStringBySellerOnDiscount(int id, int priceMin, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceHigherThanWithStringBySellerOnDiscount(id, priceMin, pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo con una certa stringa nel nome nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceHigherThanWithStringBySellerSpecificOnDiscount(int id, int priceMin, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceHigherThanWithStringBySellerSpecificOnDiscount(id, priceMin, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }


    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceLowerThanWithStringBySellerSpecific(int id, int priceMax, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceLowerThanWithStringBySellerSpecific(id, priceMax, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceLowerThanWithStringBySellerOnDiscount(int id, int priceMax, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceLowerThanWithStringBySellerOnDiscount(id, priceMax, pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceLowerThanWithStringBySellerSpecificOnDiscount(int id, int priceMax, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceLowerThanWithStringBySellerSpecificOnDiscount(id, priceMax, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceBetweenRangeWithStringBySellerSpecific(int id, int priceMin, int priceMax, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceBetweenRangeWithStringBySellerSpecific(id, priceMin, priceMax, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceBetweenRangeWithStringBySellerOnDiscount(int id, int priceMin, int priceMax, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceBetweenRangeWithStringBySellerOnDiscount(id, priceMin, priceMax, pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceBetweenRangeWithStringBySellerSpecificOnDiscount(int id, int priceMin, int priceMax, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceBetweenRangeWithStringBySellerSpecificOnDiscount(id, priceMin, priceMax, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceHigherThanWithStringbySellerSpecific(int id, int priceMin, int cat, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceHigherThanWithStringbySellerSpecific(id, priceMin, cat, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceHigherThanWithStringbySellerOnDiscount(int id, int priceMin, int cat, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceHigherThanWithStringbySellerOnDiscount(id, priceMin, cat, pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceHigherThanWithStringbySellerSpecificOnDiscount(int id, int priceMin, int cat, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceHigherThanWithStringbySellerSpecificOnDiscount(id, priceMin, cat, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceLowerThanWithStringbySellerSpecific(int id, int priceMax, int cat, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceLowerThanWithStringbySellerSpecific(id, priceMax, cat, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceLowerThanWithStringbySellerOnDiscount(int id, int priceMax, int cat, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceLowerThanWithStringbySellerOnDiscount(id, priceMax, cat, pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceLowerThanWithStringbySellerSpecificOnDiscount(int id, int priceMax, int cat, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceLowerThanWithStringbySellerSpecificOnDiscount(id, priceMax, cat, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }


    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceBetweenRangeWithStringbySellerSpecific(int id, int priceMin, int priceMax, int cat, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceBetweenRangeWithStringbySellerSpecific(id, cat, priceMin, priceMax, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }


    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceBetweenRangeWithStringbySellerOnDiscount(int id, int priceMin, int priceMax, int cat, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceBetweenRangeWithStringbySellerOnDiscount(id, cat, priceMin, priceMax, pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }


    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
     * con una determinata modalità di ritiro
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceBetweenRangeWithStringbySellerSpecificOnDiscount(int id, int priceMin, int priceMax, int cat, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceBetweenRangeWithStringbySellerSpecificOnDiscount(id, cat, priceMin, priceMax, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }


    /**
     * @author Damiano
     * Ottenere la lista di oggetti di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsbySellerNearbySpecific(int idU, int id, double lat, double lon, double rad, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsbySellerNearbySpecific(idU, lat, lon, rad, id, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsbySellerNearbyOnDiscount(int idU, int id, double lat, double lon, double rad) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsbySellerNearbyOnDiscount(idU, lat, lon, rad, id));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsbySellerNearbySpecificOnDiscount(int idU, int id, double lat, double lon, double rad, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsbySellerNearbySpecificOnDiscount(idU, lat, lon, rad, id, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }


    /**
     * @author Damiano
     * Ottenere la lista di oggetti che contengono una stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithStringbySellerNearbySpecific(int idU, int id, double lat, double lon, double rad, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithStringbySellerNearbySpecific(idU, lat, lon, rad, id, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto che contengono una stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithStringbySellerNearbyOnDiscount(int idU, int id, double lat, double lon, double rad, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithStringbySellerNearbyOnDiscount(idU, lat, lon, rad, id, pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto che contengono una stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithStringbySellerNearbySpecificOnDiscount(int idU, int id, double lat, double lon, double rad, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithStringbySellerNearbySpecificOnDiscount(idU, lat, lon, rad, id, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }


    /**
     * @author Damiano
     * Ottenere la lista di oggetti di una categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategorybySellerNearbySpecific(int idU, int id, double lat, double lon, double rad, int cat, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategorybySellerNearbySpecific(idU, lat, lon, rad, id, cat, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto di una categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategorybySellerNearbyOnDiscount(int idU, int id, double lat, double lon, double rad, int cat) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategorybySellerNearbyOnDiscount(idU, lat, lon, rad, id, cat));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto di una categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param ritiro Un intero che indica se l'ogetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategorybySellerNearbySpecificOnDiscount(int idU, int id, double lat, double lon, double rad, int cat, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategorybySellerNearbySpecificOnDiscount(idU, lat, lon, rad, id, cat, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti di una categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithStringbySellerNearbySpecific(int idU, int id, double lat, double lon, double rad, int cat, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithStringbySellerNearbySpecific(idU, lat, lon, rad, id, cat, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto di una categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithStringbySellerNearbyOnDiscount(int idU, int id, double lat, double lon, double rad, int cat, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithStringbySellerNearbyOnDiscount(idU, lat, lon, rad, id, cat, pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto di una categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithStringbySellerNearbySpecificOnDiscount(int idU, int id, double lat, double lon, double rad, int cat, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithStringbySellerNearbySpecificOnDiscount(idU, lat, lon, rad, id, cat, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }


    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo minimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceHigherThanbySellerNearbySpecific(int idU, int id, double lat, double lon, double rad, int priceMin, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceHigherThanbySellerNearbySpecific(idU, lat, lon, rad, id, priceMin, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceHigherThanbySellerNearbyOnDiscount(int idU, int id, double lat, double lon, double rad, int priceMin) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceHigherThanbySellerNearbyOnDiscount(idU, lat, lon, rad, id, priceMin));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param ritiro Un interno che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceHigherThanbySellerNearbySpecificOnDiscount(int idU, int id, double lat, double lon, double rad, int priceMin, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceHigherThanbySellerNearbySpecificOnDiscount(idU, lat, lon, rad, id, priceMin, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }


    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo massimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceLowerThanbySellerNearbySpecific(int idU, int id, double lat, double lon, double rad, int priceMax, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceLowerThanbySellerNearbySpecific(idU, lat, lon, rad, id, priceMax, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo massimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceLowerThanbySellerNearbyOnDiscount(int idU, int id, double lat, double lon, double rad, int priceMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceLowerThanbySellerNearbyOnDiscount(idU, lat, lon, rad, id, priceMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo massimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceLowerThanbySellerNearbySpecificOnDiscount(int idU, int id, double lat, double lon, double rad, int priceMax, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceLowerThanbySellerNearbySpecificOnDiscount(idU, lat, lon, rad, id, priceMax, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }


    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceBetweenRangebySellerNearbySpecific(int idU, int id, double lat, double lon, double rad, int priceMin, int priceMax, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceBetweenRangebySellerNearbySpecific(idU, lat, lon, rad, id, priceMin, priceMax, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo ed un certo prezzo massimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceBetweenRangebySellerNearbyOnDiscount(int idU, int id, double lat, double lon, double rad, int priceMin, int priceMax) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceBetweenRangebySellerNearbyOnDiscount(idU, lat, lon, rad, id, priceMin, priceMax));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo ed un certo prezzo massimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceBetweenRangebySellerNearbySpecificOnDiscount(int idU, int id, double lat, double lon, double rad, int priceMin, int priceMax, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceBetweenRangebySellerNearbySpecificOnDiscount(idU, lat, lon, rad, id, priceMin, priceMax, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceHigherThanbySellerNearbySpecific(int idU, int id, double lat, double lon, double rad, int priceMin, int cat, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceHigherThanbySellerNearbySpecific(idU, lat, lon, rad, id, priceMin, cat, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceHigherThanbySellerNearbyOnDiscount(int idU, int id, double lat, double lon, double rad, int priceMin, int cat) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceHigherThanbySellerNearbyOnDiscount(idU, lat, lon, rad, id, priceMin, cat));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceHigherThanbySellerNearbySpecificOnDiscount(int idU, int id, double lat, double lon, double rad, int priceMin, int cat, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceHigherThanbySellerNearbySpecificOnDiscount(idU, lat, lon, rad, id, priceMin, cat, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceLowerThanbySellerNearbySpecific(int idU, int id, double lat, double lon, double rad, int priceMax, int cat, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceLowerThanbySellerNearbySpecific(idU, lat, lon, rad, id, priceMax, cat, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceLowerThanbySellerNearbyOnDiscount(int idU, int id, double lat, double lon, double rad, int priceMax, int cat) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceLowerThanbySellerNearbyOnDiscount(idU, lat, lon, rad, id, priceMax,cat));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceLowerThanbySellerNearbySpecificOnDiscount(int idU, int id, double lat, double lon, double rad, int priceMax, int cat, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceLowerThanbySellerNearbySpecificOnDiscount(idU, lat, lon, rad, id, priceMax, cat, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }


    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceBetweenRangebySellerNearbySpecific(int idU, int id, double lat, double lon, double rad, int priceMin, int priceMax, int cat, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceBetweenRangebySellerNearbySpecific(idU, lat, lon, rad, id, priceMin, priceMax, cat, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
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
    public List<ModelloOggetto> itemsInCategoryWithPriceBetweenRangebySellerNearbyOnDiscount(int idU, int id, double lat, double lon, double rad, int priceMin, int priceMax, int cat) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceBetweenRangebySellerNearbyOnDiscount(idU, lat, lon, rad, id, priceMin, priceMax, cat));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceBetweenRangebySellerNearbySpecificOnDiscount(int idU, int id, double lat, double lon, double rad, int priceMin, int priceMax, int cat, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceBetweenRangebySellerNearbySpecificOnDiscount(idU, lat, lon, rad, id, priceMin, priceMax, cat, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceHigherThanWithStringbySellerNearbySpecific(int idU, int id, double lat, double lon, double rad, int priceMin, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceHigherThanWithStringbySellerNearbySpecific(idU, lat, lon, rad, id, priceMin, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceHigherThanWithStringbySellerNearbyOnDiscount(int idU, int id, double lat, double lon, double rad, int priceMin, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceHigherThanWithStringbySellerNearbyOnDiscount(idU, lat, lon, rad, id, priceMin, pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceHigherThanWithStringbySellerNearbySpecificOnDiscount(int idU, int id, double lat, double lon, double rad, int priceMin, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceHigherThanWithStringbySellerNearbySpecificOnDiscount(idU, lat, lon, rad, id, priceMin, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }


    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceLowerThanWithStringbySellerNearbySpecific(int idU, int id, double lat, double lon, double rad, int priceMax, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceLowerThanWithStringbySellerNearbySpecific(idU, lat, lon, rad, id, priceMax, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceLowerThanWithStringbySellerNearbyOnDiscount(int idU, int id, double lat, double lon, double rad, int priceMax, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceLowerThanWithStringbySellerNearbyOnDiscount(idU, lat, lon, rad, id, priceMax, pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceLowerThanWithStringbySellerNearbySpecificOnDiscount(int idU, int id, double lat, double lon, double rad, int priceMax, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceLowerThanWithStringbySellerNearbySpecificOnDiscount(idU, lat, lon, rad, id, priceMax, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }


    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceBetweenRangeWithStringbySellerNearbySpecific(int idU, int id, double lat, double lon, double rad, int priceMin, int priceMax, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceBetweenRangeWithStringbySellerNearbySpecific(idU, lat, lon, rad, id, priceMin, priceMax, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
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
    public List<ModelloOggetto> itemsWithPriceBetweenRangeWithStringbySellerNearbyOnDiscount(int idU, int id, double lat, double lon, double rad, int priceMin, int priceMax, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceBetweenRangeWithStringbySellerNearbyOnDiscount(idU, lat, lon, rad, id, priceMin, priceMax, pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti  in sconto con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsWithPriceBetweenRangeWithStringbySellerNearbySpecificOnDiscount(int idU, int id, double lat, double lon, double rad, int priceMin, int priceMax, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsWithPriceBetweenRangeWithStringbySellerNearbySpecificOnDiscount(idU, lat, lon, rad, id, priceMin, priceMax, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }


    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceHigherThanWithStringbySellerNearbySpecific(int idU, int id, double lat, double lon, double rad, int priceMin, int cat, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceHigherThanWithStringbySellerNearbySpecific(idU, lat, lon, rad, id, cat, priceMin, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
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
    public List<ModelloOggetto> itemsInCategoryWithPriceHigherThanWithStringbySellerNearbyOnDiscount(int idU, int id, double lat, double lon, double rad, int priceMin, int cat, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceHigherThanWithStringbySellerNearbyOnDiscount(idU, lat, lon, rad, id, cat, priceMin, pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceHigherThanWithStringbySellerNearbySpecificOnDiscount(int idU, int id, double lat, double lon, double rad, int priceMin, int cat, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceHigherThanWithStringbySellerNearbySpecificOnDiscount(idU, lat, lon, rad, id, cat, priceMin, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }


    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
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
    public List<ModelloOggetto> itemsInCategoryWithPriceLowerThanWithStringbySellerNearbySpecific(int idU, int id, double lat, double lon, double rad, int priceMax, int cat, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceLowerThanWithStringbySellerNearbySpecific(idU, lat, lon, rad, id, cat, priceMax, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
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
    public List<ModelloOggetto> itemsInCategoryWithPriceLowerThanWithStringbySellerNearbyOnDiscount(int idU, int id, double lat, double lon, double rad, int priceMax, int cat, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceLowerThanWithStringbySellerNearbyOnDiscount(idU, lat, lon, rad, id, cat, priceMax, pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
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
    public List<ModelloOggetto> itemsInCategoryWithPriceLowerThanWithStringbySellerNearbySpecificOnDiscount(int idU, int id, double lat, double lon, double rad, int priceMax, int cat, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceLowerThanWithStringbySellerNearbySpecificOnDiscount(idU, lat, lon, rad, id, cat, priceMax, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }


    /**
     * @author Damiano
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceBetweenRangeWithStringbySellerNearbySpecific(int idU, int id, double lat, double lon, double rad, int priceMin, int priceMax, int cat, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceBetweenRangeWithStringbySellerNearbySpecific(idU, lat, lon, rad, id, cat, priceMin, priceMax, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
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
    public List<ModelloOggetto> itemsInCategoryWithPriceBetweenRangeWithStringbySellerNearbyOnDiscount(int idU, int id, double lat, double lon, double rad, int priceMin, int priceMax, int cat, String pattern) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceBetweenRangeWithStringbySellerNearbyOnDiscount(idU, lat, lon, rad, id, cat, priceMin, priceMax, pattern));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti in sconto con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * con una determinata modalità di ritiro
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @param ritiro Un intero che indica se l'oggetto è ritirabile in negozio o meno
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> itemsInCategoryWithPriceBetweenRangeWithStringbySellerNearbySpecificOnDiscount(int idU, int id, double lat, double lon, double rad, int priceMin, int priceMax, int cat, String pattern, int ritiro) {
        List<ModelloOggetto> Objects = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(marketsSellersQuery.itemsInCategoryWithPriceBetweenRangeWithStringbySellerNearbySpecificOnDiscount(idU, lat, lon, rad, id, cat, priceMin, priceMax, pattern, ritiro));
            while (rs.next()) {
                Objects.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }

        return Objects;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti di una ricerca
     * @param name
     * @return List<ModelloOggetto> lista di oggetti ottenuti dalla ricerca
     */
    public pair<List<ModelloOggetto>, List<ModelloImmagineOggetto>> selectObjectByQuery(String name, String categoria, String venditore, String negozio, double minPrice, double maxPrice, boolean sconto, boolean ritiro, int minRating, double limitPrice, String regione, boolean limitResult) {
        pair<List<ModelloOggetto>,List<ModelloImmagineOggetto>> res;
        List<ModelloOggetto> oggetti = new ArrayList<>();
        List<ModelloImmagineOggetto> immagini = new ArrayList<>();
        
        String lista = "";
        
        try {
            
            if(name != null && name.length() != 0){
                PriorityQueue<pair<Double,String>> queue = new PriorityQueue<>(new Comparator(){
                @Override
                    public int compare(Object first, Object second) {
                        return Double.compare(((pair<Double, String>)first).getL(),((pair<Double, String>)second).getL());
                    }
                });
                
                Statement stmt = connection.createStatement();
                ResultSet resultSet = stmt.executeQuery(objectsQuery.selectAllObjectNames());

                JaroWinkler jw1 = new JaroWinkler();

                List<String> similarNames = new ArrayList<>();

                while(resultSet.next()){
                    System.out.println("Distanza di "  + resultSet.getString(1) + ": " + jw1.distance(resultSet.getString(1), name));
                    if(jw1.distance(resultSet.getString(1), name) <= 0.3) {
                        //queue.add(new pair(jw1.distance(resultSet.getString(1), name), resultSet.getString(1)));
                        similarNames.add("'" + resultSet.getString(1) + "'");
                    }
                }

                /*for(pair<Double, String> elem : queue){
                    similarNames.add("'" + elem.getR() + "'");
                }*/

                lista = "(" + similarNames.toString().substring(similarNames.toString().indexOf("[") + 1, similarNames.toString().indexOf("]")) + ")";
                System.out.println(lista);
            }
            //System.out.println(toReturn);
            //System.out.println("\n");
            
            boolean shop = false;
            boolean seller = false;
            boolean place = false;
            boolean before = false;
            String query = "SELECT Oggetto.*, imageOggetto.* FROM Oggetto INNER JOIN imageOggetto ON (Oggetto.id = imageOggetto.idO)";

            if(negozio != null && negozio.length() > 0){
                query = query + " INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id)";
                shop = true;
            }
            if(venditore != null && venditore.length() > 0 && !shop){
                query = query + " INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) INNER JOIN Utente ON (Negozio.idVenditore = Utente.id)";
                seller = true;
            }else if(venditore != null && venditore.length() > 0){
                query = query + " INNER JOIN Utente ON (Negozio.idVenditore = Utente.id)";
            }

            if(!regione.equals("Regione")){
                if(shop || seller){
                    query = query + " INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)";
                }
                else{
                    query = query + " INNER JOIN Negozio ON (Oggetto.idNegozio = Negozio.id) INNER JOIN Indirizzo ON (Indirizzo.idI = Negozio.idI)";
                }
            }

            if(shop){
                query = query + " WHERE Negozio.nomeNegozio LIKE '%" + negozio + "%'";
            }
            if(seller){
                if(shop){
                    query = query + " AND Utente.nome LIKE '%" + venditore + "%'";
                }else{
                    query = query + " WHERE Utente.nome LIKE '%" + venditore + "%'";
                    seller = true;
                }
            }
            
            if(name != null && name.length() != 0){
                if(shop || seller){
                    query = query + " AND Oggetto.nome IN " + lista + "";
                }else{
                    query = query + " WHERE Oggetto.nome IN " + lista + "";
                    before = true;
                }
            }

            // se sono state aggiunte cose prima scrivo AND, altrimenti WHERE
            /*if(shop || seller){
                query = query + " AND Oggetto.nomeDownCase LIKE '%" + name + "%'";
            }else{
                query = query + " WHERE Oggetto.nomeDownCase LIKE '%" + name + "%'";
            }*/
            if(!categoria.equals("Categoria") && (shop || seller || before)){
                query = query + " AND Oggetto.categoria = " + categoria;
            }else if (!categoria.equals("Categoria")){
                query = query + " WHERE Oggetto.categoria = " + categoria;
                before = true;
            }

            if(shop || seller || before){
                if(minPrice > 0 && maxPrice < 1000){
                    query = query + " AND Oggetto.prezzo BETWEEN " + minPrice + " AND " + maxPrice;
                }else if(minPrice > 0){
                    query = query + " AND Oggetto.prezzo >= " + minPrice;
                }else if(maxPrice < limitPrice){
                    query = query + " AND Oggetto.prezzo <= " + maxPrice;
                }
            }else{
                if(minPrice > 0 && maxPrice < 1000){
                    query = query + " WHERE Oggetto.prezzo BETWEEN " + minPrice + " AND " + maxPrice;
                }else if(minPrice > 0){
                    query = query + " WHERE Oggetto.prezzo > " + minPrice;
                }else if(maxPrice < 1000){
                    query = query + " WHERE Oggetto.prezzo < " + maxPrice;
                }
                before = true;
            }

            if(sconto && (shop || seller || before)){
                query = query + " AND Oggetto.sconto <> 0";
            }else if(sconto){
                query = query + " WHERE Oggetto.sconto <> 0";
                before = true;
            }

            if(ritiro && (shop || seller || before)){
                query = query + " AND Oggetto.ritiroInNegozio = 1";
            }else if(ritiro){
                query = query + " WHERE Oggetto.ritiroInNegozio = 1";
                before = true;
            }

            if(shop || seller || before){
                query = query + " AND Oggetto.valutazione >= " + minRating;
            }else{
                query = query + " WHERE Oggetto.valutazione >= " + minRating;
                before = true;
            }

            if(!regione.equals("Regione")){
                if(shop || seller || before){
                    query = query + " AND Indirizzo.regione LIKE '" + regione + "'";
                }else{
                    query = query + " WHERE Indirizzo.regione LIKE '" + regione + "'";
                }
            }

            query = query + " ORDER BY Oggetto.prezzo";
            
            if(limitResult){
                query = query + " LIMIT 200";
            }
            
            query = query + ";";

            System.out.println(query);

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);

            
            while (rs.next()) {
                oggetti.add(DaoOggetto.getModelloFromRs(rs));
                immagini.add(DaoImmagineOggetto.getModelloFromRs(rs));
            }
            
            /*if(name.length() != 0){
                JaroWinkler jw = new JaroWinkler();

                while (rs.next()) {

                    //System.out.println(rs.getString(4));
                    //if(jw.distance(rs.getString(4), name) <= 0.3) {
                        oggetti.add(DaoOggetto.getModelloFromRs(rs));
                        immagini.add(DaoImmagineOggetto.getModelloFromRs(rs));
                        //System.out.println("Ho aggiunto " + rs.getString(4)  + " perché " + jw.distance(rs.getString(4), name));
                    //}
                    oggetti.add(DaoOggetto.getModelloFromRs(rs));
                    immagini.add(DaoImmagineOggetto.getModelloFromRs(rs));
                }
            }else{
                while (rs.next()) {
                    oggetti.add(DaoOggetto.getModelloFromRs(rs));
                    immagini.add(DaoImmagineOggetto.getModelloFromRs(rs));
                }
            }*/

        } catch (SQLException e) {}

        res = new pair(oggetti, immagini);
        return res;
    }

    public boolean updateObject(ModelloOggetto object, String previusId) {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sellersQuery.updateObject(object, previusId));
        } catch(SQLException e) {
            return false;
        }
        return true;
    }

    /**
     * @author Damiano
     * Ottenere la lista di oggetti che contengono una stringa nel nome
     * @param nomeDownCase: Stringa contenente il nome dell'oggetto in minuscolo, per facilitare alcune operazioni
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<String> selectByStringSimilarity(String searched) {
        PriorityQueue <pair<Double, String>> queue = new PriorityQueue<>(new Comparator(){
            @Override
            public int compare(Object first, Object second) {
                return Double.compare(((pair<Double, String>)first).getL(),((pair<Double, String>)second).getL());
            }

        });

        JaroWinkler jw = new JaroWinkler();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectsQuery.selectAllObjectNames());
            int i = 0;
            while (rs.next()) {
                queue.add(new pair(jw.distance(searched.toLowerCase(), rs.getString(1).toLowerCase()), rs.getString(1)));
            }

            /*System.out.println("Oggetti trovati: " + queue.size());
            System.out.println("Distanza delle stringhe da " + nomeDownCase);*/

        } catch (SQLException e) {}

        List<String> result = new ArrayList<>();
        //System.out.println("Elementi nella queue di dimensione " + queue.size());
        int i = 1;
        while(queue.size() > 0 && i <= 10){
            pair<Double, String> elem = queue.poll();
            //System.out.println("Elemento " + (i++) + ") " + elem.getR() + " - " + elem.getL());
            i++;
            result.add(elem.getR());
        }

        return result;
    }

    public double getMaxPrice() {
        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(genericsQuery.getMaxPrice());
            if(rs.next()){
                return Math.ceil(rs.getDouble(1));
            }
            else return (0);
        } catch (SQLException ex) {
        }
        return 0;
    }

}
