/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.immagineOggetto;

/**
 * classe utilizzata per gestire gli oggetti di tipo ImmagineOggetto
 * @author mattia
 */
public class ModelloImmagineOggetto {
    
    /**
     * Variabile utilizzata per identificare il percorso dell'immagine
     */
    private String src;
    /**
     * Variabile utilizzata per identificare l'oggetto a cui si fa riferimento
     */
    private String idO; //O sta per oggetto

    /**
     * Fuzione utilizzata per ottenre il percorso dell'immagine
     * @return String src
     */
    public String getSrc() {
        return src;
    }

    /**
     * Funzione utilizzata per settare il percorso all'immagine
     * @param src 
     */
    public void setSrc(String src) {
        this.src = src;
    }

    /**
     * Funzioen utilizzata per ottenre l'id dell'oggetto a cui si fa riferimento
     * @return 
     */
    public String getIdO() {
        return idO;
    }

    /**
     * Funzioen utilizzata per settare l'id dell'oggetto a cui si fa riferimento
     * @param idO 
     */
    public void setIdO(String idO) {
        this.idO = idO;
    }
}
