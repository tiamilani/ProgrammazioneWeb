/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.immagineRecensione;

/**
 * classe utilizzata per gesire gli oggetti di tipo immagineRecensione
 * @author mattia
 */
public class ModelloImmagineRecensione {
    
    /**
     * Variabile utilizzata per identificare il percorso dell'immagine
     */
    private String src;
    /**
     * Variabile utilizzata per identificare la recensione a cui si fa riferimento
     */
    private int idR;

    /**
     * Funzioene utilizzata per ottenre il percorso all'immagine
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
     * Funzione utilizzata per ottenre l'id della recensione a cui si fa riferimento
     * @return int idR
     */
    public int getIdR() {
        return idR;
    }

    /**
     * Funzione utilizzata per settare l'id della recensione a cui si fa riferimento
     * @param idR 
     */
    public void setIdR(int idR) {
        this.idR = idR;
    }
}
