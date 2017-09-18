/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.immagineUtente;

/**
 * classe utilizzata per gestire gli oggetti di tipo ModelloImmagineUtente
 * @author mattia
 */
public class ModelloImmagineUtente {
    
    /**
     * Variabile utilizzata per identificare il percorso dell'immagine
     */
    private String src;
    /**
     * Variabile utilizzata per identificare l'utente a cui si fa riferimento
     */
    private int idU;

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
     * Funzione utilizzata per ottenre l'id dell'utente a cui si fa riferimento
     * @return int idU
     */
    public int getIdU() {
        return idU;
    }

    /**
     * Funzione utilizzata per settare l'id dell'utente a cui si fa riferimento
     * @param idU 
     */
    public void setIdU(int idU) {
        this.idU = idU;
    }
}
