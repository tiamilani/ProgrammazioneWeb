/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.ordiniRicevuti;

import java.util.Date;

/**
 * Classe utilizzata per gestire gli oggetti di tipo ordini ricevuti
 * @author mattia
 */
public class ModelloOrdiniRicevuti {
    
    /**
     * Variabile utilizzata per identificare l'ordine
     */
    private int idO; //Ordine
    /**
     * Variabile utilizzata per identificare il venditore
     */
    private int idV;
    /**
     * Variabile utilizzata per identificare la data in cui è stato creato l'ordine
     */
    private Date data;

    /**
     * Funzione utilizzata per ottenre l'id dell'ordine a cui si fa riferimento
     * @return int idO
     */
    public int getIdO() {
        return idO;
    }
  
    /**
     * Funzione utilizzata per settare l'id dell'ordine a cui si fa riferimento
     * @param idO 
     */
    public void setIdO(int idO) {
        this.idO = idO;
    }

    /**
     * Funzione utilizzata per ottenre l'id del venditore che deve gestire l'ordine
     * @return int idV
     */
    public int getIdV() {
        return idV;
    }

    /**
     * Funzione utilizzata per settare l'id del venditore che deve gestire l'ordine
     * @param idV 
     */
    public void setIdV(int idV) {
        this.idV = idV;
    }

    /**
     * Funzione utilizzata per ottenere la data in cui è stato effettuato l'ordine
     * @return Date data
     */
    public Date getData() {
        return data;
    }

    /**
     * Funzione utilizzata per settare la data in cui è stato effettuato l'ordine
     * @param data 
     */
    public void setData(Date data) {
        this.data = data;
    }
}
