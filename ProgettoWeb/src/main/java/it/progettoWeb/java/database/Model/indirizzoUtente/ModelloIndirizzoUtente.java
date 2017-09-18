/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.indirizzoUtente;

/**
 * classe utilizzata per gestire gli oggetti della classe ModelloIndirizzoUtente
 * @author mattia
 */
public class ModelloIndirizzoUtente {
    
    /**
     * Variabile utilizzata per identificare l'indirizzo
     */
    private int idI;
    /**
     * Variabile utilizzata per identificare l'utente a cui fa riferimento
     */
    private int idU;

    /**
     * Funzione utilizzata per ottenere l'id dell'indirizzo
     * @return int idI
     */
    public int getIdI() {
        return idI;
    }

    /**
     * Funzione utilizzata per settare l'id dell'indirizzo
     * @param idI 
     */
    public void setIdI(int idI) {
        this.idI = idI;
    }

    /**
     * Funzione utilizzata per ottenere l'id dell'Utente
     * @return int IdU
     */
    public int getIdU() {
        return idU;
    }

    /**
     * Funzione utilizzata per settare l'id dell'Utente
     * @param idU 
     */
    public void setIdU(int idU) {
        this.idU = idU;
    }
}
