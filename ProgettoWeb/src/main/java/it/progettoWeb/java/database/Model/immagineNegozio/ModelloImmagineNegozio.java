/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.immagineNegozio;

/**
 * classe utilizzata per gestire gli oggetti di tipo utente 
 * @author mattia
 */
public class ModelloImmagineNegozio {
    
    /**
     * Variabile utilizzata per identificare il path dell'immagine
     */
    private String src;
    /**
     * Variabile utilizata per identificare il negozio a cui si fa riferimento
     */
    private int idN;

    /**
     * Funzione utilizzata per ottenere il percorso dell'immagine
     * @return String src
     */
    public String getSrc() {
        return src;
    }

    /**
     * Funzione utilizzata per settare il percorso dell'immagine a cui si fa riferimento
     * @param src 
     */
    public void setSrc(String src) {
        this.src = src;
    }

    /**
     * Funzione utilizzata per ottenre l'id del negozio a cui si fa riferimento
     * @return idN
     */
    public int getIdN() {
        return idN;
    }

    /**
     * Funzione utilizzata per settare l'id del negozio a cui si fa riferimento
     * @param idN 
     */
    public void setIdN(int idN) {
        this.idN = idN;
    }
}
