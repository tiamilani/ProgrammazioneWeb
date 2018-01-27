/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.query.objectSellers;
/**
 *
 * @author andrea
 */
public class objectSellersQuery {
    public static String hello() {
        return "Hello from" + objectSellersQuery.class.toString();
    }
    
    // QUERY OGGETTI DI VENDITORI
    
    /**
     * @author Andrea
     * Ottenere la lista di negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di negozi
     */
    public static String selectSellerStores(int id){
        return "SELECT * FROM negozio WHERE idVenditore='"+id+"';";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti di negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di oggetti
     */
    public static String selectSellerObjects(int id){
        return "SELECT oggetto.* FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) WHERE negozio.idVenditore='"+id+"';";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti che contengono una stringa nel nome nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsWithString(int id, String pattern){
        return "SELECT oggetto.* FROM oggetto INNER JOIN negozio ON (oggetto.idNegozio = negozio.id) "
                + "WHERE negozio.idVenditore='"+id+"' AND oggetto.nomeDownCase LIKE '%"+pattern+"%';";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti di una categoria nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerAndCategory(int id, int cat){
        return "SELECT oggetto.* FROM oggetto INNER JOIN negozio ON "
                + "(oggetto.idNegozio = negozio.id) WHERE negozio.idVenditore='"+id+"' AND categoria='"+cat+"';";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti di una categoria con una certa stringa nel nome,nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerAndName(int id, int cat, String pattern){
        return "SELECT oggetto.* FROM oggetto INNER JOIN negozio ON "
                + "(oggetto.idNegozio = negozio.id) WHERE negozio.idVenditore='"+id+"' AND "
                + "categoria='"+cat+"' AND oggetto.nomeDownCase LIKE '%"+pattern+"%';";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo dei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerMinPrice(int id, int priceMin){
        return "SELECT oggetto.* FROM oggetto INNER JOIN negozio ON "
                + "(oggetto.idNegozio = negozio.id) WHERE negozio.idVenditore='"+id+"' "
                + "AND oggetto.prezzo >= "+priceMin+";";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerMaxPrice(int id, int priceMax){
        return "SELECT oggetto.* FROM oggetto INNER JOIN negozio ON "
                + "(oggetto.idNegozio = negozio.id) WHERE negozio.idVenditore='"+id+"' "
                + "AND oggetto.prezzo <= "+priceMax+";";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerMinMaxPrice(int id, int priceMin, int priceMax){
        return "SELECT oggetto.* FROM oggetto INNER JOIN negozio ON "
                + "(oggetto.idNegozio = negozio.id) WHERE negozio.idVenditore='"+id+"' "
                + "AND oggetto.prezzo BETWEEN "+priceMin+" AND "+priceMax+";";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerMinPriceAndCategory(int id, int priceMin, int cat){
        return "SELECT oggetto.* FROM oggetto INNER JOIN negozio ON "
                + "(oggetto.idNegozio = negozio.id) WHERE negozio.idVenditore='"+id+"' "
                + "AND categoria='"+cat+"' AND oggetto.prezzo >= "+priceMin+";";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerMaxPriceAndCategory(int id, int priceMax, int cat){
        return "SELECT oggetto.* FROM oggetto INNER JOIN negozio ON "
                + "(oggetto.idNegozio = negozio.id) WHERE negozio.idVenditore='"+id+"' "
                + "AND categoria='"+cat+"' AND oggetto.prezzo <= "+priceMax+";";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerMinMaxPriceAndCategory(int id, int priceMin, int priceMax, int cat){
        return "SELECT oggetto.* FROM oggetto INNER JOIN negozio ON "
                + "(oggetto.idNegozio = negozio.id) WHERE negozio.idVenditore='"+id+"' "
                + "AND categoria='"+cat+"' AND oggetto.prezzo BETWEEN "+priceMin+" AND "+priceMax+";";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerMinPriceAndName(int id, int priceMin, String pattern){
        return "SELECT oggetto.* FROM oggetto INNER JOIN negozio ON "
                + "(oggetto.idNegozio = negozio.id) WHERE negozio.idVenditore='"+id+"' AND "
                + "oggetto.prezzo >= "+priceMin+" AND oggetto.nomeDownCase LIKE '%"+pattern+"%';";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerMaxPriceAndName(int id, int priceMax, String pattern){
        return "SELECT oggetto.* FROM oggetto INNER JOIN negozio ON "
                + "(oggetto.idNegozio = negozio.id) WHERE negozio.idVenditore='"+id+"' AND "
                + "oggetto.prezzo <= "+priceMax+" AND oggetto.nomeDownCase LIKE '%"+pattern+"%';";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerMinMaxPriceAndName(int id, int priceMin, int priceMax, String pattern){
        return "SELECT oggetto.* FROM oggetto INNER JOIN negozio ON "
                + "(oggetto.idNegozio = negozio.id) WHERE negozio.idVenditore='"+id+"' AND "
                + "oggetto.prezzo BETWEEN "+priceMin+" AND "+priceMax+" AND oggetto.nomeDownCase LIKE '%"+pattern+"%';";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerMinPriceAndNameAndCategory(int id, int priceMin, int cat, String pattern){
        return "SELECT oggetto.* FROM oggetto INNER JOIN negozio ON "
                + "(oggetto.idNegozio = negozio.id) WHERE negozio.idVenditore='"+id+"' AND "
                + "categoria='"+cat+"' AND oggetto.prezzo >= "+priceMin+" AND "
                + "oggetto.nomeDownCase LIKE '%"+pattern+"%';";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerMaxPriceAndNameAndCategory(int id, int priceMax, int cat, String pattern){
        return "SELECT oggetto.* FROM oggetto INNER JOIN negozio ON "
                + "(oggetto.idNegozio = negozio.id) WHERE negozio.idVenditore='"+id+"' AND "
                + "categoria='"+cat+"' AND oggetto.prezzo <= "+priceMax+" AND "
                + "oggetto.nomeDownCase LIKE '%"+pattern+"%';";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerMinMaxPriceAndNameAndCategory(int id, int priceMin, int priceMax, int cat, String pattern){
        return "SELECT oggetto.* FROM oggetto INNER JOIN negozio ON "
                + "(oggetto.idNegozio = negozio.id) WHERE negozio.idVenditore='"+id+"' AND "
                + "categoria='"+cat+"' AND oggetto.prezzo BETWEEN "+priceMin+" AND "+priceMax+" AND "
                + "oggetto.nomeDownCase LIKE '%"+pattern+"%';";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @return String: lista di negozi
     */
    public static String selectSellerSpecifiedSellerLatLonRad(int id, double lat, double lon, double rad){
        return "SELECT negozio.* FROM negozio INNER JOIN indirizzo ON "
                + "(indirizzo.idI = negozio.idI) WHERE "+rad+" >= 111.111 * "
                + "DEGREES(ACOS(COS(RADIANS("+lat+")) * COS(RADIANS(indirizzo.latitudine)) "
                + "* COS(RADIANS("+lon+" - indirizzo.longitudine)) + "
                + "SIN(RADIANS("+lat+")) * SIN(RADIANS(indirizzo.latitudine)))) "
                + "AND negozio.idVenditore = '"+id+"';";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti di negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerLatLonRad(int idU, int id, double lat, double lon, double rad){
        return "Create OR REPLACE View NegoziDistanza_"+idU+" as SELECT negozio.id FROM "
                + "negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) WHERE "
                + rad+" >= 111.111 * DEGREES(ACOS(COS(RADIANS("+lat+")) * "
                + "COS(RADIANS(indirizzo.latitudine)) * COS(RADIANS("+lon+" - "
                + "indirizzo.longitudine)) + SIN(RADIANS("+lat+")) * "
                + "SIN(RADIANS(indirizzo.latitudine)))) AND negozio.idVenditore ='"+id+"'; "
                + "SELECT oggetto.* FROM oggetto INNER JOIN NegoziDistanza_"+idU+" ON "
                + "(oggetto.idNegozio = NegoziDistanza_"+idU+".id);";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti che contengono una stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerLatLonRadAndName(int idU, int id, double lat, double lon, double rad, String pattern){
        return "Create OR REPLACE View NegoziDistanza_"+idU+" as SELECT negozio.id FROM "
                + "negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) WHERE "
                + rad+" >= 111.111 * DEGREES(ACOS(COS(RADIANS("+lat+")) * "
                + "COS(RADIANS(indirizzo.latitudine)) * COS(RADIANS("+lon+" - "
                + "indirizzo.longitudine)) + SIN(RADIANS("+lat+")) * "
                + "SIN(RADIANS(indirizzo.latitudine)))) AND negozio.idVenditore ='"+id+"'; "
                + "SELECT oggetto.* FROM oggetto INNER JOIN NegoziDistanza_"+idU+" ON "
                + "(oggetto.idNegozio = NegoziDistanza_"+idU+".id) "
                + "WHERE oggetto.nomeDownCase LIKE '%"+pattern+"%';";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti di una categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerLatLonRadAndCategory(int idU, int id, double lat, double lon, double rad, int cat){
        return "Create OR REPLACE View NegoziDistanza_"+idU+" as SELECT negozio.id FROM "
                + "negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) WHERE "
                + rad+" >= 111.111 * DEGREES(ACOS(COS(RADIANS("+lat+")) * "
                + "COS(RADIANS(indirizzo.latitudine)) * COS(RADIANS("+lon+" - "
                + "indirizzo.longitudine)) + SIN(RADIANS("+lat+")) * "
                + "SIN(RADIANS(indirizzo.latitudine)))) AND negozio.idVenditore ='"+id+"'; "
                + "SELECT oggetto.* FROM oggetto INNER JOIN NegoziDistanza_"+idU+" ON "
                + "(oggetto.idNegozio = NegoziDistanza_"+idU+".id) "
                + "WHERE oggetto.categoria='"+cat+"';";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti di una categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerLatLonRadAndNameCategory(int idU, int id, double lat, double lon, double rad, int cat, String pattern){
        return "Create OR REPLACE View NegoziDistanza_"+idU+" as SELECT negozio.id FROM "
                + "negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) WHERE "
                + rad+" >= 111.111 * DEGREES(ACOS(COS(RADIANS("+lat+")) * "
                + "COS(RADIANS(indirizzo.latitudine)) * COS(RADIANS("+lon+" - "
                + "indirizzo.longitudine)) + SIN(RADIANS("+lat+")) * "
                + "SIN(RADIANS(indirizzo.latitudine)))) AND negozio.idVenditore ='"+id+"'; "
                + "SELECT oggetto.* FROM oggetto INNER JOIN NegoziDistanza_"+idU+" ON "
                + "(oggetto.idNegozio = NegoziDistanza_"+idU+".id) "
                + "WHERE oggetto.categoria='"+cat+"' AND oggetto.nomeDownCase LIKE '%"+pattern+"%';";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerLatLonRadAndMinPrice(int idU, int id, double lat, double lon, double rad, int priceMin){
        return "Create OR REPLACE View NegoziDistanza_"+idU+" as SELECT negozio.id FROM "
                + "negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) WHERE "
                + rad+" >= 111.111 * DEGREES(ACOS(COS(RADIANS("+lat+")) * "
                + "COS(RADIANS(indirizzo.latitudine)) * COS(RADIANS("+lon+" - "
                + "indirizzo.longitudine)) + SIN(RADIANS("+lat+")) * "
                + "SIN(RADIANS(indirizzo.latitudine)))) AND negozio.idVenditore ='"+id+"'; "
                + "SELECT oggetto.* FROM oggetto INNER JOIN NegoziDistanza_"+idU+" ON "
                + "(oggetto.idNegozio = NegoziDistanza_"+idU+".id) "
                + "WHERE oggetto.prezzo >= "+priceMin+";";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerLatLonRadAndMaxPrice(int idU, int id, double lat, double lon, double rad, int priceMax){
        return "Create OR REPLACE View NegoziDistanza_"+idU+" as SELECT negozio.id FROM "
                + "negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) WHERE "
                + rad+" >= 111.111 * DEGREES(ACOS(COS(RADIANS("+lat+")) * "
                + "COS(RADIANS(indirizzo.latitudine)) * COS(RADIANS("+lon+" - "
                + "indirizzo.longitudine)) + SIN(RADIANS("+lat+")) * "
                + "SIN(RADIANS(indirizzo.latitudine)))) AND negozio.idVenditore ='"+id+"'; "
                + "SELECT oggetto.* FROM oggetto INNER JOIN NegoziDistanza_"+idU+" ON "
                + "(oggetto.idNegozio = NegoziDistanza_"+idU+".id) "
                + "WHERE oggetto.prezzo <= "+priceMax+";";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerLatLonRadAndMinMaxPrice(int idU, int id, double lat, double lon, double rad, int priceMin, int priceMax){
        return "Create OR REPLACE View NegoziDistanza_"+idU+" as SELECT negozio.id FROM "
                + "negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) WHERE "
                + rad+" >= 111.111 * DEGREES(ACOS(COS(RADIANS("+lat+")) * "
                + "COS(RADIANS(indirizzo.latitudine)) * COS(RADIANS("+lon+" - "
                + "indirizzo.longitudine)) + SIN(RADIANS("+lat+")) * "
                + "SIN(RADIANS(indirizzo.latitudine)))) AND negozio.idVenditore ='"+id+"'; "
                + "SELECT oggetto.* FROM oggetto INNER JOIN NegoziDistanza_"+idU+" ON "
                + "(oggetto.idNegozio = NegoziDistanza_"+idU+".id) "
                + "WHERE oggetto.prezzo BETWEEN "+priceMin+" AND "+priceMax+";";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerLatLonRadAndMinPriceCategory(int idU, int id, double lat, double lon, double rad, int priceMin, int cat){
        return "Create OR REPLACE View NegoziDistanza_"+idU+" as SELECT negozio.id FROM "
                + "negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) WHERE "
                + rad+" >= 111.111 * DEGREES(ACOS(COS(RADIANS("+lat+")) * "
                + "COS(RADIANS(indirizzo.latitudine)) * COS(RADIANS("+lon+" - "
                + "indirizzo.longitudine)) + SIN(RADIANS("+lat+")) * "
                + "SIN(RADIANS(indirizzo.latitudine)))) AND negozio.idVenditore ='"+id+"'; "
                + "SELECT oggetto.* FROM oggetto INNER JOIN NegoziDistanza_"+idU+" ON "
                + "(oggetto.idNegozio = NegoziDistanza_"+idU+".id) "
                + "WHERE oggetto.prezzo >= "+priceMin+" AND oggetto.categoria='"+cat+"';";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerLatLonRadAndMaxPriceCategory(int idU, int id, double lat, double lon, double rad, int priceMax, int cat){
        return "Create OR REPLACE View NegoziDistanza_"+idU+" as SELECT negozio.id FROM "
                + "negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) WHERE "
                + rad+" >= 111.111 * DEGREES(ACOS(COS(RADIANS("+lat+")) * "
                + "COS(RADIANS(indirizzo.latitudine)) * COS(RADIANS("+lon+" - "
                + "indirizzo.longitudine)) + SIN(RADIANS("+lat+")) * "
                + "SIN(RADIANS(indirizzo.latitudine)))) AND negozio.idVenditore ='"+id+"'; "
                + "SELECT oggetto.* FROM oggetto INNER JOIN NegoziDistanza_"+idU+" ON "
                + "(oggetto.idNegozio = NegoziDistanza_"+idU+".id) "
                + "WHERE oggetto.prezzo <= "+priceMax+" AND oggetto.categoria='"+cat+"';";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerLatLonRadAndMinMaxPriceCategory(int idU, int id, double lat, double lon, double rad, int priceMin, int priceMax, int cat){
        return "Create OR REPLACE View NegoziDistanza_"+idU+" as SELECT negozio.id FROM "
                + "negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) WHERE "
                + rad+" >= 111.111 * DEGREES(ACOS(COS(RADIANS("+lat+")) * "
                + "COS(RADIANS(indirizzo.latitudine)) * COS(RADIANS("+lon+" - "
                + "indirizzo.longitudine)) + SIN(RADIANS("+lat+")) * "
                + "SIN(RADIANS(indirizzo.latitudine)))) AND negozio.idVenditore ='"+id+"'; "
                + "SELECT oggetto.* FROM oggetto INNER JOIN NegoziDistanza_"+idU+" ON "
                + "(oggetto.idNegozio = NegoziDistanza_"+idU+".id) "
                + "WHERE oggetto.prezzo BETWEEN "+priceMin+" AND "+priceMax+" AND oggetto.categoria='"+cat+"';";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerLatLonRadAndMinPriceName(int idU, int id, double lat, double lon, double rad, int priceMin, String pattern){
        return "Create OR REPLACE View NegoziDistanza_"+idU+" as SELECT negozio.id FROM "
                + "negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) WHERE "
                + rad+" >= 111.111 * DEGREES(ACOS(COS(RADIANS("+lat+")) * "
                + "COS(RADIANS(indirizzo.latitudine)) * COS(RADIANS("+lon+" - "
                + "indirizzo.longitudine)) + SIN(RADIANS("+lat+")) * "
                + "SIN(RADIANS(indirizzo.latitudine)))) AND negozio.idVenditore ='"+id+"'; "
                + "SELECT oggetto.* FROM oggetto INNER JOIN NegoziDistanza_"+idU+" ON "
                + "(oggetto.idNegozio = NegoziDistanza_"+idU+".id) "
                + "WHERE oggetto.prezzo >= "+priceMin+" AND oggetto.nomeDownCase LIKE '%"+pattern+"%';";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerLatLonRadAndMaxPriceName(int idU, int id, double lat, double lon, double rad, int priceMax, String pattern){
        return "Create OR REPLACE View NegoziDistanza_"+idU+" as SELECT negozio.id FROM "
                + "negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) WHERE "
                + rad+" >= 111.111 * DEGREES(ACOS(COS(RADIANS("+lat+")) * "
                + "COS(RADIANS(indirizzo.latitudine)) * COS(RADIANS("+lon+" - "
                + "indirizzo.longitudine)) + SIN(RADIANS("+lat+")) * "
                + "SIN(RADIANS(indirizzo.latitudine)))) AND negozio.idVenditore ='"+id+"'; "
                + "SELECT oggetto.* FROM oggetto INNER JOIN NegoziDistanza_"+idU+" ON "
                + "(oggetto.idNegozio = NegoziDistanza_"+idU+".id) "
                + "WHERE oggetto.prezzo >= "+priceMax+" AND oggetto.nomeDownCase LIKE '%"+pattern+"%';";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo ed un certo prezzo massimo con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerLatLonRadAndMinMaxPriceName(int idU, int id, double lat, double lon, double rad, int priceMin, int priceMax, String pattern){
        return "Create OR REPLACE View NegoziDistanza_"+idU+" as SELECT negozio.id FROM "
                + "negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) WHERE "
                + rad+" >= 111.111 * DEGREES(ACOS(COS(RADIANS("+lat+")) * "
                + "COS(RADIANS(indirizzo.latitudine)) * COS(RADIANS("+lon+" - "
                + "indirizzo.longitudine)) + SIN(RADIANS("+lat+")) * "
                + "SIN(RADIANS(indirizzo.latitudine)))) AND negozio.idVenditore ='"+id+"'; "
                + "SELECT oggetto.* FROM oggetto INNER JOIN NegoziDistanza_"+idU+" ON "
                + "(oggetto.idNegozio = NegoziDistanza_"+idU+".id) "
                + "WHERE oggetto.prezzo BETWEEN "+priceMin+" AND "+priceMax+" AND oggetto.nomeDownCase LIKE '%"+pattern+"%';";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti con un certo prezzo minimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMin Un intero utilizzato per delimitare il prezzo minimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerLatLonRadAndMinPriceCategoryName(int idU, int id, double lat, double lon, double rad, int priceMin, int cat, String pattern){
        return "Create OR REPLACE View NegoziDistanza_"+idU+" as SELECT negozio.id FROM "
                + "negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) WHERE "
                + rad+" >= 111.111 * DEGREES(ACOS(COS(RADIANS("+lat+")) * "
                + "COS(RADIANS(indirizzo.latitudine)) * COS(RADIANS("+lon+" - "
                + "indirizzo.longitudine)) + SIN(RADIANS("+lat+")) * "
                + "SIN(RADIANS(indirizzo.latitudine)))) AND negozio.idVenditore ='"+id+"'; "
                + "SELECT oggetto.* FROM oggetto INNER JOIN NegoziDistanza_"+idU+" ON "
                + "(oggetto.idNegozio = NegoziDistanza_"+idU+".id) "
                + "WHERE oggetto.prezzo >= "+priceMin+" AND oggetto.categoria='"+cat+"' AND oggetto.nomeDownCase LIKE '%"+pattern+"%';";
    }
    
    /**
     * @author Andrea
     * Ottenere la lista di oggetti con un certo prezzo massimo in una certa categoria con una certa stringa nel nome nei negozi di un determinato venditore data una determinata longitudine, latitudine ed un raggio di ricerca
     * @param idU Un intero che rappresenta l'identificativo dell'utente. Viene utilizzato per assegnare un nome univoco alla view
     * @param id Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param lat Un double utilizzato per definire la latitudine desiderata
     * @param lon Un double utilizzato per definire la longitudine desiderata
     * @param rad Un double utilizzato per definire il raggio desiderata
     * @param priceMax Un intero utilizzato per delimitare il prezzo massimo desiderato
     * @param cat Un intero utilizzato per identificare la categoria di appartenenza
     * @param pattern Una stringa utilizzata per una ricerca basata sul confronto
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerLatLonRadAndMaxPriceCategoryName(int idU, int id, double lat, double lon, double rad, int priceMax, int cat, String pattern){
        return "Create OR REPLACE View NegoziDistanza_"+idU+" as SELECT negozio.id FROM "
                + "negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) WHERE "
                + rad+" >= 111.111 * DEGREES(ACOS(COS(RADIANS("+lat+")) * "
                + "COS(RADIANS(indirizzo.latitudine)) * COS(RADIANS("+lon+" - "
                + "indirizzo.longitudine)) + SIN(RADIANS("+lat+")) * "
                + "SIN(RADIANS(indirizzo.latitudine)))) AND negozio.idVenditore ='"+id+"'; "
                + "SELECT oggetto.* FROM oggetto INNER JOIN NegoziDistanza_"+idU+" ON "
                + "(oggetto.idNegozio = NegoziDistanza_"+idU+".id) "
                + "WHERE oggetto.prezzo <= "+priceMax+" AND oggetto.categoria='"+cat+"' AND oggetto.nomeDownCase LIKE '%"+pattern+"%';";
    }
    
    /**
     * @author Andrea
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
     * @return String: lista di oggetti
     */
    public static String selectSellerObjectsSpecifiedSellerLatLonRadAndMinMaxPriceCategoryName(int idU, int id, double lat, double lon, double rad, int priceMin, int priceMax, int cat, String pattern){
        return "Create OR REPLACE View NegoziDistanza_"+idU+" as SELECT negozio.id FROM "
                + "negozio INNER JOIN indirizzo ON (indirizzo.idI = negozio.idI) WHERE "
                + rad+" >= 111.111 * DEGREES(ACOS(COS(RADIANS("+lat+")) * "
                + "COS(RADIANS(indirizzo.latitudine)) * COS(RADIANS("+lon+" - "
                + "indirizzo.longitudine)) + SIN(RADIANS("+lat+")) * "
                + "SIN(RADIANS(indirizzo.latitudine)))) AND negozio.idVenditore ='"+id+"'; "
                + "SELECT oggetto.* FROM oggetto INNER JOIN NegoziDistanza_"+idU+" ON "
                + "(oggetto.idNegozio = NegoziDistanza_"+idU+".id) "
                + "WHERE oggetto.prezzo BETWEEN "+priceMin+" AND "+priceMax+" AND "
                + "oggetto.categoria='"+cat+"' AND oggetto.nomeDownCase LIKE '%"+pattern+"%';";
    }
}
