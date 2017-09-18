/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.spedizioneOggetto;

/**
 * classe utilizzata per gestire gli oggetti di tipo Spedizione Oggetto
 * @author mattia
 */
public class ModelloSpedizioneOggetto {
    
    /**
     * Variabile utilizzata per identificare la spedizione associata
     */
    private int idS;
    /**
     * Variabile utilizzata per idetificare l'oggetto associato alla spedizione
     */
    private String idO;

    /**
     * Funzione utilizzata per ottenre l'id della spedizione
     * @return int idS
     */
    public int getIdS() {
        return idS;
    }

    /**
     * Funzione utilizzata per settare l'id della spedizione
     * @param idS 
     */
    public void setIdS(int idS) {
        this.idS = idS;
    }

    /**
     * Funzione utilizzata per ottenre l'id dell'oggetto
     * @return int idO
     */
    public String getIdO() {
        return idO;
    }

    /**
     * Funzione utilizzata per settare l'id dell'oggetto
     * @param idO 
     */
    public void setIdO(String idO) {
        this.idO = idO;
    }
}
