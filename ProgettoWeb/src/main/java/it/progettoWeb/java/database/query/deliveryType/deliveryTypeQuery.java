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
        return "SELECT * FROM tipoSpedizione";
    }
    
    /**
     * @author FBrug
     * Ottenere la lista di tutti i tipi di spedizione in base all'ID Spedizione
     * @param idS Intero rappresentante l'ID della spedizione
     * @return String: lista tipi di spedizione
     */
    public static String selectDeliveryTypeByIdS(int idS)
    {
        return "SELECT * FROM tipoSpedizione WHERE idS="+idS+"";
    }
    
    /**
     * @author FBrug
     * Ottenere la lista di tutti i tipi di spedizione in base all'ID Negozio
     * @param idN Intero rappresentante l'ID del negozio
     * @return String: lista tipi di spedizione
     */
    public static String selectDeliveryTypeByIdN(int idN)
    {
        return "SELECT * FROM tipoSpedizione WHERE idN="+idN+"";
    }
    
    /**
     * @author FBrug
     * Ottenere la lista di tutti i tipi di spedizione in base all'ID Oggetto
     * @param idO Intero rappresentante l'ID dell'oggetto
     * @return String: lista tipi di spedizione
     */
    public static String selectDeliveryTypeByIdO(String idO)
    {
        return "SELECT tipoSpedizione.* FROM tipoSpedizione INNER JOIN spedizioneoggetto ON "
                + "(spedizioneoggetto.idO = '" +idO+ "' AND tipoSpedizione.idS = spedizioneoggetto.idS)";
    }

    public static String updateSpedizione(ModelloTipoSpedizione spedizione) {
        return "UPDATE tipoSpedizione SET Nome='"+spedizione.getNome()+"', prezzo="+spedizione.getPrezzo()+", Corriere='"+spedizione.getCorriere()+"', "
                + "tempoRichiesto="+spedizione.getTempoRichiesto()+", numeroMassimo="+spedizione.getNumeroMassimo()+"   WHERE idS="+spedizione.getIdS()+";";
    }

    public static String deleteSpedizione(int idS) {
        return "DELETE FROM tipoSpedizione WHERE idS=" + idS + ";";
    }

    public static String insertObject(ModelloTipoSpedizione spedizione) {
        return "INSERT INTO tipoSpedizione (idN,Nome,Prezzo,Corriere,tempoRichiesto,numeroMassimo) "
                + "VALUES ("+spedizione.getIdN()+",'"+spedizione.getNome()+"',"+spedizione.getPrezzo()+",'"+spedizione.getCorriere()+"',"+spedizione.getTempoRichiesto()+","+spedizione.getNumeroMassimo()+");";
    }

    public static String addSpedizioneOggetto(int idS, String id) {
        return "insert into spedizioneOggetto values("+idS+",'"+id+"');";
    }

    public static String selectSpedizioneOggetto(String idOggetto) {
        return "select * from spedizioneOggetto where idO='"+idOggetto+"';";
    }

    public static String deleteSpedizioneOggetto(int idS, String id) {
        return "DELETE FROM spedizioneOggetto WHERE idS="+idS+" AND idO='"+id+"';";
    }
}
