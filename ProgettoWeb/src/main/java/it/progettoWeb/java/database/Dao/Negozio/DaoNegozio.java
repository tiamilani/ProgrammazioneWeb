/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Dao.Negozio;

/**
 * Classe utilizzta per gestire gli accessi al database della tabella Negozio
 * @author mattia
 */

import it.progettoWeb.java.database.Dao.immagineNegozio.DaoImmagineNegozio;
import it.progettoWeb.java.database.Dao.indirizzo.DaoIndirizzo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.progettoWeb.java.database.Util.DbUtil;
import it.progettoWeb.java.database.query.objectSellers.objectSellersQuery;
import it.progettoWeb.java.database.Model.Negozio.ModelloNegozio;
import it.progettoWeb.java.database.Model.immagineNegozio.ModelloImmagineNegozio;
import it.progettoWeb.java.database.Model.indirizzo.ModelloIndirizzo;
import it.progettoWeb.java.database.query.generics.genericsQuery;
import it.progettoWeb.java.database.query.objects.objectsQuery;
import it.progettoWeb.java.database.query.sellers.sellersQuery;
import it.progettoWeb.java.database.query.objectsMarkets.objectMarketsQuery;
import it.progettoWeb.java.database.query.users.usersQuery;
import it.progettoWeb.java.utility.pair.pair;
import it.progettoWeb.java.utility.tris.tris;

public class DaoNegozio {
    /**
     * Costatnti che indicano i nomi delle colonne da poter riutilizzare all'interno del file
     */
    private static final String ID="id";
    private static final String IDVENDITORE="idVenditore";
    private static final String NOMENEGOZIO="nomeNegozio";
    private static final String VALUTAZIONE="valutazione";
    private static final String ATTIVO="attivo";
    private static final String IDI="idI";
    private static final String DATAAPERTURA="dataApertura";
    private static final String LINKSITO="linkSito";
    private static final String ORARIONEGOZIO="orarioNegozio";
    
    /**
     * Variabile che gestisce la connessione con il db
     */
    private Connection connection;
    
    /**
     * Costruttore della classe, utilizzato per instaurare la connessione con il db
     */
    public DaoNegozio() {
        connection = DbUtil.getConnection();
    }
    
    /**
     * @author Mattia
     * Funzione utilizzata per facilitare l'ottenimento dei modelli negozio da un result set
     * @param rs un resultset da cui ricavare un modello negozio
     * @return il modello negozio presente nel resultset
     */
    public static ModelloNegozio getModelloFromRs(ResultSet rs)
    {
        ModelloNegozio Store = new ModelloNegozio();
        
        try{
            Store.setId(rs.getInt(ID));
            Store.setIdVenditore(rs.getInt(IDVENDITORE));
            Store.setNomeNegozio(rs.getString(NOMENEGOZIO));
            Store.setValutazione(rs.getDouble(VALUTAZIONE));
            Store.setAttivo(rs.getInt(ATTIVO));
            Store.setIdI(rs.getInt(IDI));
            Store.setDataApertura(rs.getTimestamp(DATAAPERTURA));
            Store.setLinkSito(rs.getString(LINKSITO));
            Store.setOrarioNegozio(rs.getString(ORARIONEGOZIO));
        } catch (SQLException e) {
        }
        
        return Store;
    }
    
    /**
     * @author Mattia
     * Funzione utilizzata per ottenre la lista dei negozio di un utente venditore partendo dall'id
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloNegozio> lista di modelli negozio che risultano dalla query
     */
    public List<ModelloNegozio> selectSellerStore(int id) {
        List<ModelloNegozio> Stores = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerStores(id));
            while (rs.next()) {
                Stores.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Stores;
    }
    
    /**
     * Permette di selezionare un oggetto negozio avendone l'id
     * @param storeId
     * @return 
     */
    public ModelloNegozio getStoreById(int storeId) {
        ModelloNegozio negozio = new ModelloNegozio();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(genericsQuery.selectStoreById(storeId));
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                negozio = getModelloFromRs(rs);
            }
        } catch (SQLException e) {
        }

        return negozio;
    }
    
    /**
     * @author andrea
     * Ottenere trio Negozio, Indirizzo, Immagine
     * @param idNegozio
     * @return tris<ModelloNegozio, ModelloIndirizzo, ModelloImmagineNegzio> Negozio con indirizzo e immagine
     */
    public tris<ModelloNegozio, ModelloIndirizzo, ModelloImmagineNegozio> selectStoreAddressImageByStoreID(int idNegozio) {
        tris<ModelloNegozio, ModelloIndirizzo, ModelloImmagineNegozio> res;
        ModelloNegozio negozio = new ModelloNegozio();
        ModelloIndirizzo indirizzo = new ModelloIndirizzo();
        ModelloImmagineNegozio immagine = new ModelloImmagineNegozio();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(genericsQuery.selectStoreAddressImageByStoreID(idNegozio));
            
            while (rs.next()) {
                negozio = DaoNegozio.getModelloFromRs(rs);
                indirizzo = DaoIndirizzo.getModelloFromRs(rs);
                immagine = DaoImmagineNegozio.getModelloFromRs(rs);
            }
        } catch (SQLException e) { }
        
        res = new tris(negozio, indirizzo, immagine);
        return res;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @return List<ModelloNegozio> lista di negozi
     */
    public List<ModelloNegozio> selectSellerSpecifiedSellerLatLonRad(int id, double lat, double lon, double rad) {
        List<ModelloNegozio> Stores = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectSellersQuery.selectSellerSpecifiedSellerLatLonRad(id,lat,lon,rad));
            while (rs.next()) {
                Stores.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Stores;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista dei negozi da cui ho acquistato
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloNegozio> lista di negozi
     */
    public List<ModelloNegozio> selectStoresUsed(int idU) {
        List<ModelloNegozio> Stores = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectStoresUsed(idU));
            while (rs.next()) {
                Stores.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Stores;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista dei negozi da cui ho acquistato e la loro prima foto
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloNegozio> lista di negozi
     */
    public List<ModelloNegozio> selectStoresUsedAndPhoto(int idU) {
        List<ModelloNegozio> Stores = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectStoresUsedAndPhoto(idU));
            while (rs.next()) {
                Stores.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Stores;
    }
    
    /**
     * @author Mattia
     * Ottenere la lista dei negozi da cui ho acquistato con i dati dell'oggetto acquistato, la prima foto del negozio e la prima dell'oggetto
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return List<ModelloNegozio> lista di negozi
     */
    public List<ModelloNegozio> selectStoresUsedAndDataPhotoSPhotoO(int idU) {
        List<ModelloNegozio> Stores = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(usersQuery.selectStoresUsedAndDataPhotoSPhotoO(idU));
            while (rs.next()) {
                Stores.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Stores;
    }
    
    /**
     * @author fbrug
     * Ottenere la lista dei propri negozi
     * @param idVenditore: intero rappresentante l'ID del venditore a cui appartiene il negozio
     * @return String: lista dei negozi
     */
    public List<ModelloNegozio> selectShopsBySellerID(int idVenditore)
    {
        List<ModelloNegozio> stores = new ArrayList<>();
        
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectShopsBySellerID(idVenditore));
            
            while(rs.next())
            {
                stores.add(getModelloFromRs(rs));
            }
        }
        catch (SQLException e) {}
        
        return stores;
    }
    
    /**
     * @author fbrug
     * Ottenere la lista dei propri negozi con anche il numero di vendite
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista dei negozi
     */
    public List<ModelloNegozio> selectShopAndSalesNumberBySellerID(int idVenditore)
    {
        List<ModelloNegozio> stores = new ArrayList<>();
        
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectShopAndSalesNumberBySellerID(idVenditore));
            
            while(rs.next())
            {
                stores.add(getModelloFromRs(rs));
            }
        }
        catch (SQLException e) {}
        
        return stores;
    }
    
    /**
     * @author fbrug
     * Ottenere la lista dei propri negozi ordinati per vendite maggiori
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista dei negozi
     */
    public pair<List<ModelloNegozio>,List<Integer>> selectShopWithHigherSalesBySellerID(int idVenditore)
    {
        pair<List<ModelloNegozio>,List<Integer>> result;
        List<ModelloNegozio> stores = new ArrayList<>();
        List<Integer> ordini = new ArrayList<>();
        
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectShopWithHigherSalesBySellerID(idVenditore));
            
            while(rs.next())
            {
                stores.add(getModelloFromRs(rs));
                ordini.add(rs.getInt("numeroVendite"));
            }
        }
        catch (SQLException e) {}
        
        result = new pair<>(stores, ordini);
        return result;
    }
    
    /**
     * @author fbrug
     * Ottenere la lista dei propri negozi per vendite minori
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista dei negozi
     */
    public pair<List<ModelloNegozio>,List<Integer>> selectShopWithLowestSalesBySellerID(int idVenditore)
    {
        pair<List<ModelloNegozio>,List<Integer>> result;
        List<ModelloNegozio> stores = new ArrayList<>();
        List<Integer> ordini = new ArrayList<>();
        
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectShopWithLowestSalesBySellerID(idVenditore));
            
            while(rs.next())
            {
                stores.add(getModelloFromRs(rs));
                ordini.add(rs.getInt("numeroVendite"));
            }
        }
        catch (SQLException e) {}
        
        result = new pair<>(stores, ordini);
        return result;
    }
    
    /**
     * @author fbrug
     * Ottenere la lista dei propri negozi con vendite inferiori ad un certo valore
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @param valore: intero rappresentante il numero di vendite del negozio
     * @return String: lista dei negozi
     */
    public List<ModelloNegozio> selectShopWithLowerSalesThanBySellerID(int idVenditore, int valore)
    {
        List<ModelloNegozio> stores = new ArrayList<>();
        
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectShopWithLowerSalesThanBySellerID(idVenditore, valore));
            
            while(rs.next())
            {
                stores.add(getModelloFromRs(rs));
            }
        }
        catch (SQLException e) {}
        
        return stores;
    }
    
    /**
     * @author fbrug
     * Ottenere la lista dei propri negozi con vendite superiori ad un certo valore
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @param valore: intero rappresentante il numero di vendite del negozio
     * @return String: lista dei negozi
     */
    public List<ModelloNegozio> selectShopWithHigherSalesThanBySellerID(int idVenditore, int valore)
    {
        List<ModelloNegozio> stores = new ArrayList<>();
        
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectShopWithHigherSalesThanBySellerID(idVenditore, valore));
            
            while(rs.next())
            {
                stores.add(getModelloFromRs(rs));
            }
        }
        catch (SQLException e) {}
        
        return stores;
    }
    
    /**
     * @author fbrug
     * Ottenere la lista dei negozi che vendono prodotti di una certa categoria
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @param attivo: intero rappresentante la situazione del negozio (0 = chiuso, 1 = in attività)
     * @param idCategoria: intero rappresentante l'ID della categoria
     * @return String: lista dei negozi
     */
    public List<ModelloNegozio> selectShopByCategory(int idVenditore, int attivo, int idCategoria)
    {
        List<ModelloNegozio> stores = new ArrayList<>();
        
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectShopByCategory(idVenditore, attivo, idCategoria));
            
            while(rs.next())
            {
                stores.add(getModelloFromRs(rs));
            }
        }
        catch (SQLException e) {}
        
        return stores;
    }
    
    /**
     * @author fbrug
     * Ottenere la lista dei negozi che vendono prodotti di una certa categoria ordinate da quello con più vendite
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @param attivo: intero rappresentante la situazione del negozio (0 = chiuso, 1 = in attività)
     * @param idCategoria: intero rappresentante l'ID della categoria
     * @param categoriaOggetto: intero rappresentante la cetegoria dell'oggetto ricercato
     * @return String: lista dei negozi
     */
    public List<ModelloNegozio> selectShopWithHigherSalesByCategory(int idVenditore, int attivo, int idCategoria, int categoriaOggetto)
    {
        List<ModelloNegozio> stores = new ArrayList<>();
        
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectShopWithHigherSalesByCategory(idVenditore, attivo, idCategoria, categoriaOggetto));
            
            while(rs.next())
            {
                stores.add(getModelloFromRs(rs));
            }
        }
        catch (SQLException e) {}
        
        return stores;
    }
    
    /**
     * @author fbrug
     * Ottenere la lista dei negozi che vendono prodotti di una certa categoria ordinate da quello con meno vendite
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @param attivo: intero rappresentante la situazione del negozio (0 = chiuso, 1 = in attività)
     * @param idCategoria: intero rappresentante l'ID della categoria
     * @param categoriaOggetto: intero rappresentante la cetegoria dell'oggetto ricercato
     * @return String: lista dei negozi
     */
    public List<ModelloNegozio> selectShopWithLowerSalesByCategory(int idVenditore, int attivo, int idCategoria, int categoriaOggetto)
    {
        List<ModelloNegozio> stores = new ArrayList<>();
        
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectShopWithLowerSalesByCategory(idVenditore, attivo, idCategoria, categoriaOggetto));
            
            while(rs.next())
            {
                stores.add(getModelloFromRs(rs));
            }
        }
        catch (SQLException e) {}
        
        return stores;
    }
    
    /**
     * @author fbrug
     * Ottenere la lista dei propri negozi ordinati per data di apertura
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista dei negozi
     */
    public List<ModelloNegozio> selectShopByOpenDate(int idVenditore)
    {
        List<ModelloNegozio> stores = new ArrayList<>();
        
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectShopByOpenDate(idVenditore));
            
            while(rs.next())
            {
                stores.add(getModelloFromRs(rs));
            }
        }
        catch (SQLException e) {}
        
        return stores;
    }
    
    public List<ModelloNegozio> selectShopByNameup(int idVenditore)
    {
        List<ModelloNegozio> stores = new ArrayList<>();
        
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectShopByNameup(idVenditore));
            
            while(rs.next())
            {
                stores.add(getModelloFromRs(rs));
            }
        }
        catch (SQLException e) {}
        
        return stores;
    }
    
    public List<ModelloNegozio> selectShopByNameDown(int idVenditore)
    {
        List<ModelloNegozio> stores = new ArrayList<>();
        
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectShopByNameDown(idVenditore));
            
            while(rs.next())
            {
                stores.add(getModelloFromRs(rs));
            }
        }
        catch (SQLException e) {}
        
        return stores;
    }
    
    /**
     * @author fbrug
     * Ottenere la lista dei propri negozi ordinati per fatturato
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista dei negozi
     */
    public List<ModelloNegozio> selectShopByRevenue(int idVenditore)
    {
        List<ModelloNegozio> stores = new ArrayList<>();
        
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectShopByRevenue(idVenditore));
            
            while(rs.next())
            {
                stores.add(getModelloFromRs(rs));
            }
        }
        catch (SQLException e) {}
        
        return stores;
    }
    
    /**
     * @author fbrug
     * Ottenere i dati di un negozio
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @param idNegozio: intero rappresentante l'ID del negozio
     * @return String: lista dei negozi
     */
    public List<ModelloNegozio> selectShopBySellerIDAndShopID(int idVenditore, int idNegozio)
    {
        List<ModelloNegozio> stores = new ArrayList<>();
        
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectShopBySellerIDAndShopID(idVenditore, idNegozio));
            
            while(rs.next())
            {
                stores.add(getModelloFromRs(rs));
            }
        }
        catch (SQLException e) {}
        
        return stores;
    }
    
    /**
     * @author fbrug
     * Ottenere la lista dei propri negozi ordinati per recensioni
     * @param idVenditore: intero rappresentante l'ID del venditore
     * @return String: lista dei negozi
     */
    public List<ModelloNegozio> selectShopOrderedByRating(int idVenditore)
    {
        List<ModelloNegozio> stores = new ArrayList<>();
        
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sellersQuery.selectShopOrderedByRating(idVenditore));
            
            while(rs.next())
            {
                stores.add(getModelloFromRs(rs));
            }
        }
        catch (SQLException e) {}
        
        return stores;
    }
    
    /**
     * @author fbrug
     * Aggiungere un proprio negozio
     * @param negozio: negozio da inserire
     */
    public boolean insertShop(ModelloNegozio negozio)
    {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sellersQuery.insertShop(negozio));
        }
        catch (SQLException e) {
            return false;
        }
        return true;
    }
    
    /**
     * @author fbrug
     * Chiudere un proprio negozio (cambiare il suo stato)
     * @param idNegozio: intero rappresentante l'ID del negozio a cui cambiare lo stato
     * @param attivo: nuovo stato del negozio (0 = chiuso, 1 = in attività)
     */
    public boolean updateShopStatus(int idNegozio, int attivo)
    {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sellersQuery.updateShopStatus(idNegozio, attivo));
        }
        catch (SQLException e) {
            return false;
        }
        return true;
    }
    
    public boolean updateShopStars(int idNegozio, double value)
    {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sellersQuery.updateShopStars(idNegozio, value));
        }
        catch (SQLException e) {
            return false;
        }
        return true;
    }
    
   /**
     * @author fbrug
     * Aggiungere una immagine del profilo di un negozio
     * @param idNegozio: intero rappresentante l'ID del negozio a cui inserire l'immagine
     * @param imagePath: il path della nuova immagine
     */
    public void insertShopImage(int idNegozio, String imagePath)
    {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sellersQuery.insertShopImage(idNegozio, imagePath));
        }
        catch (SQLException e) {}
    }
    
    /**
     * @author fbrug
     * Modificare l'immagine del profilo di un negozio con un determinato ID negozio
     * @param idNegozio: intero rappresentante l'ID del negozio a cui cambiare l'immagine
     * @param oldImagePath: il path dell'immagine da modificare
     * @param newImagePath: il path della nuova immagine
     */
    public void updateShopImage(int idNegozio, String oldImagePath, String newImagePath)
    {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sellersQuery.updateShopImage(idNegozio, oldImagePath, newImagePath));
        }
        catch (SQLException e) {}
    }
    
    /**
     * @author fbrug
     * Rimuovere l'immagine di un determinato negozio
     * @param idNegozio: intero rappresentante l'ID del negozio a cui rimuovere l'immagine
     * @param imagePath: il path dell'immagine da rimuovere
     */
    public void deleteShopImage(int idNegozio, String imagePath)
    {
        try
        {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sellersQuery.deleteShopImage(idNegozio, imagePath));
        }
        catch (SQLException e) {}
    }
  
  /**
     * @author andrea
     * Ottenere la lista di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idUtente: Intero indicante l'identificativo del soggetto desiderato
     * @param lat: Double contenente il valore della latitudine
     * @param lon: Double contenente il valore della longitudine
     * @param r: Double contenente il valore della raggio di ricerca
     * @return List<ModelloNegozio> lista di negozi
     */
    public List<ModelloNegozio> LLR(int idUtente, double lat, double lon, double r) {
      List<ModelloNegozio> Stores = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
          ResultSet rs = statement.executeQuery(objectsQuery.selectShopByLLR(idUtente, lat, lon, r));
            while (rs.next()) {
                Stores.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {}
        
        return Stores;
    }
  
    /**
     * @author Mattia
     * ottenere la lista di negozi data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param raggio variabile utilizzata per specificare il raggio di ricerca
     * @param longitudine variabile utilizzata per specificare la longitudine dal punto in cui effettuare la ricerca
     * @param latitudine variabile utilizzata per specificare la latituidine dal punto in cui effettuare la ricerca
     * @return List<ModelloNegozio> lista di negozi
     */
    public List<ModelloNegozio> selectShopWithlatitudeAndLongitude(double raggio, double longitudine, double latitudine) {
        List<ModelloNegozio> Stores = new ArrayList<>();
        
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(objectMarketsQuery.selectShopWithlatitudeAndLongitude(raggio,longitudine,latitudine));
            while (rs.next()) {
                Stores.add(getModelloFromRs(rs));
            }
        } catch (SQLException e) {
        }
        
        return Stores;
    }
}
