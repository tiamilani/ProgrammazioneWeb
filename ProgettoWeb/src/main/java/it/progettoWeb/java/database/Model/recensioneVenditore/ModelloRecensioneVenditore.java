/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.recensioneVenditore;

import java.util.Date;

/**
 *classe utilizzata per gestire gli oggetti di tipo RecensioneVenditore
 * @author mattia
 */
public class ModelloRecensioneVenditore {
    
    /**
     * Variabile utilizzata per l'id della recensione 
     */
    private int id;
    /**
     * Variabile utilizzata per l'id del venditore a cui è riferita la recensione
     */
    private int idVenditore;
    /**
     * Variabile utilizzata per l'id dell'utente che ha scritto la recensione
     */
    private int idUtente;
    /**
     * Variabile utilizzata per memorizzate il testo della recensione
     */
    private String testo;
    /**
     * Variabile utilizzata per memorizzare la valutazione presente nella recensione
     */
    private int valutazione;
    /**
     * Variabile utilizzata per memorizzare la data in cui è stata scritta la recensione
     */
    private Date data;
    /**
     * Variabile utilizzata per memorizzare l'utilità della recensione
     */
    private int utilita;

    /**
     * Funzione utilizzata per otterne l'id della recensione
     * @return int id
     */
    public int getId() {
        return id;
    }

    /**
     * Funzione utilizzata per settare l'id della recensione
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Funzione utilizzata per ottenre l'id del venditore
     * @return int idVenditore
     */
    public int getIdVenditore() {
        return idVenditore;
    }

    /**
     * Funzione utilizzata per settare l'id del venditore
     * @param idVenditore 
     */
    public void setIdVenditore(int idVenditore) {
        this.idVenditore = idVenditore;
    }

    /**
     * Funzione utilizzata per ottenre l'id dell'utente che ha scritto la recensione
     * @return int idUtente
     */
    public int getIdUtente() {
        return idUtente;
    }

    /**
     * Funzione utilizzata per settare l'id dell'utente che ha scritto la recensione
     * @param idUtente 
     */
    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    /**
     * Funzione utilizzata per ottenre il testo della recensione
     * @return String testo
     */
    public String getTesto() {
        return testo;
    }

    /**
     * Funzione utilizzata per settare il testo della recensione
     * @param testo 
     */
    public void setTesto(String testo) {
        this.testo = testo;
    }

    /**
     * Funzione utilizzata per otterne la valutazione data nella recensione
     * @return int valutazione
     */
    public int getValutazione() {
        return valutazione;
    }

    /**
     * Funzione utilizzata per settare la valutazione data nella recensione
     * @param valutazione 
     */
    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
    }

    /**
     * Funzione utilizzata per otterne la data della recensione
     * @return Date data
     */
    public Date getData() {
        return data;
    }

    /**
     * Funzione utilizzata per settare la data della recensione
     * @param data 
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Funzione utilizzata per ottenre l'utilità della recensione
     * @return int utilita
     */
    public int getUtilita() {
        return utilita;
    }

    /**
     * Funzione utilizzata per settare l'utilità della recensione
     * @param utilita 
     */
    public void setUtilita(int utilita) {
        this.utilita = utilita;
    }
}
