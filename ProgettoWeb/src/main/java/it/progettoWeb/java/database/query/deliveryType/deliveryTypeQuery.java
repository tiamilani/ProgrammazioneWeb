/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.query.deliveryType;

import it.progettoWeb.java.database.Model.tipoSpedizione.ModelloTipoSpedizione;

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
     * Ottenere la lista di tutti i tipi di spedizione in base all'ID negozio
     * 
     * @param idN Intero rappresentante l'ID del negozio
     * @return String: lista tipi di spedizione
     */
    public static String selectDeliveryTypeByIdN(int idN)
    {
        return "SELECT * FROM tipospedizione WHERE idN="+idN+"";
    }
    
    /**
     * @author FBrug
     * Ottenere la lista di tutti i tipi di spedizione in base all'ID oggetto
     * @param idO Intero rappresentante l'ID dell'oggetto
     * @return String: lista tipi di spedizione
     */
    public static String selectDeliveryTypeByIdO(String idO)
    {
        return "SELECT tipospedizione.* FROM tipospedizione INNER JOIN spedizioneoggetto ON "
                + "(spedizioneoggetto.idO = '" +idO+ "' AND tipospedizione.idS = spedizioneoggetto.idS)";
    }

    public static String updateSpedizione(ModelloTipoSpedizione spedizione) {
        return "UPDATE tipospedizione SET Nome='"+spedizione.getNome()+"', prezzo="+spedizione.getPrezzo()+", Corriere='"+spedizione.getCorriere()+"', "
                + "tempoRichiesto="+spedizione.getTempoRichiesto()+", numeroMassimo="+spedizione.getNumeroMassimo()+"   WHERE idS="+spedizione.getIdS()+";";
    }

    public static String deleteSpedizione(int idS) {
        return "DELETE FROM tipospedizione WHERE idS=" + idS + ";";
    }

    public static String insertObject(ModelloTipoSpedizione spedizione) {
        return "INSERT INTO tipospedizione (idN,Nome,Prezzo,Corriere,tempoRichiesto,numeroMassimo) "
                + "VALUES ("+spedizione.getIdN()+",'"+spedizione.getNome()+"',"+spedizione.getPrezzo()+",'"+spedizione.getCorriere()+"',"+spedizione.getTempoRichiesto()+","+spedizione.getNumeroMassimo()+");";
    }
    
    public static String addSpedizioneOggetto(int idS, String id) {
        return "insert into spedizioneoggetto values("+idS+",'"+id+"');";
    }

    public static String selectSpedizioneOggetto(String idOggetto) {
        return "select * from spedizioneoggetto where idO='"+idOggetto+"';";
    }

    public static String deleteSpedizioneOggetto(int idS, String id) {
        return "DELETE FROM spedizioneoggetto WHERE idS="+idS+" AND idO='"+id+"';";
    }

    public static String exists(int idS, int idNegozio) {
        return "SELECT * FROM spedizioneoggetto INNER join oggetto on (spedizioneoggetto.idO = oggetto.id) where spedizioneoggetto.idS = "+idS+" AND oggetto.idNegozio = "+idNegozio+";";
    }
}
