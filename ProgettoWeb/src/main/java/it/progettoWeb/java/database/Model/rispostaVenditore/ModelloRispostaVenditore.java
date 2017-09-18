/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.rispostaVenditore;

import java.util.Date;

/**
 * classe utilizzata per gestire gli oggetti di tipo Risposta Venditore
 * @author mattia
 */
public class ModelloRispostaVenditore {
    
    /**
     * Variabile utilizzata per identificare la recensione a cui si fa riferimento
     */
    private int idRecensione;
    /**
     * Variabile utilizzata per identificare il testo della risposta alla recensione
     */
    private String testo;
    /**
     * Variabile che identifica la data della risposta
     */
    private Date data;

    /**
     * Funzione utilizzata per ottenre l'id della recensione a cui si fa riferimento
     * @return int idRecensione
     */
    public int getIdRecensione() {
        return idRecensione;
    }

    /**
     * Funzione utilizzata per settare l'id della recensione a cui si fa riferimento
     * @param idRecensione 
     */
    public void setIdRecensione(int idRecensione) {
        this.idRecensione = idRecensione;
    }

    /**
     * Funzione utilizzata per ottenre il testo della recensione a cui si fa riferimento
     * @return String testo
     */
    public String getTesto() {
        return testo;
    }

    /**
     * Funzione utilizzata per settare il testo della recnesione a cui si fa riferimento
     * @param testo 
     */
    public void setTesto(String testo) {
        this.testo = testo;
    }

    /**
     * Funzione utilizzata per otterne la data 
     * @return Date data
     */
    public Date getData() {
        return data;
    }

    /**
     * Funzione utilizzata per settare la data della risposta
     * @param data 
     */
    public void setData(Date data) {
        this.data = data;
    }
}
