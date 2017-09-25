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
import it.progettoWeb.java.database.query.marketsSellers.marketsSellersQuery;
import it.progettoWeb.java.database.query.objectSellers.objectSellersQuery;

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
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato* @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerMinPrice(int id, int priceMin) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerMinPrice(id, priceMin));
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
     * @author Mattia
     * Ottenere la lista di oggetti di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerLatLonRad(int idU, int id, double lat, double lon, double rad) {
        List<ModelloOggetto> Objects = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerObjectsSpecifiedSellerLatLonRad(idU, id, lat, lon, rad));
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
     * @author Mattia
     * Ottenere la lista di oggetti che contengono una stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerLatLonRadAndName(int idU, int id, double lat, double lon, double rad, String pattern) {
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
     * @author Mattia
     * Ottenere la lista di oggetti di una categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerLatLonRadAndCategory(int idU, int id, double lat, double lon, double rad, int cat) {
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
     * @author Mattia
     * Ottenere la lista di oggetti di una categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerLatLonRadAndNameCategory(int idU, int id, double lat, double lon, double rad, int cat, String pattern) {
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
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo minimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerLatLonRadAndMinPrice(int idU, int id, double lat, double lon, double rad, int priceMin) {
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
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo massimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerLatLonRadAndMaxPrice(int idU, int id, double lat, double lon, double rad, int priceMax) {
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
     * @author Mattia
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @return List<ModelloOggetto> lista di oggetti
     */
    public List<ModelloOggetto> selectSellerObjectsSpecifiedSellerLatLonRadAndMinMaxPrice(int idU, int id, double lat, double lon, double rad, int priceMin, int priceMax) {
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
    
}


