/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.query.deliveryType;

/**
 *
 * @author FBrug
 */
public class deliveryTypeQuery
{
    public static String hello()
    {
        return "Hello from " + deliveryTypeQuery.class.toString();
    }
    
    /**
     * @author FBrug
     * Ottenere la lista di tutti i tipi di spedizione
     * @return String: lista tipi di spedizione
     */
    public static String selectAllDeliveryTypes()
    {
        return "SELECT * FROM tipospedizione";
    }
    
    /**
     * @author FBrug
     * Ottenere la lista di tutti i tipi di spedizione in base all'ID Spedizione
     * @param idS Intero rappresentante l'ID della spedizione
     * @return String: lista tipi di spedizione
     */
    public static String selectDeliveryTypeByIdS(int idS)
    {
        return "SELECT * FROM tipospedizione WHERE idS="+idS+"";
    }
    
    /**
     * @author FBrug
     * Ottenere la lista di tutti i tipi di spedizione in base all'ID Negozio
     * @param idN Intero rappresentante l'ID del negozio
     * @return String: lista tipi di spedizione
     */
    public static String selectDeliveryTypeByIdN(int idN)
    {
        return "SELECT * FROM tipospedizione WHERE idN="+idN+"";
    }
    
    /**
     * @author FBrug
     * Ottenere la lista di tutti i tipi di spedizione in base all'ID Oggetto
     * @param idO Intero rappresentante l'ID dell'oggetto
     * @return String: lista tipi di spedizione
     */
    public static String selectDeliveryTypeByIdO(String idO)
    {
        return "SELECT tipospedizione.* FROM tipospedizione INNER JOIN spedizioneoggetto ON "
                + "(spedizioneoggetto.idO = '" +idO+ "' AND tipospedizione.idS = spedizioneoggetto.idS)";
    }
}
