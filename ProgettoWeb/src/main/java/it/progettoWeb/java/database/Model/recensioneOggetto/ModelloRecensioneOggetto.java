/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.recensioneOggetto;

import java.sql.Date;

/**
 * classe utilizzata per gestire gli oggetti di tipo RecensioneOggetto
 * @author mattia
 */
public class ModelloRecensioneOggetto {
    
    /**
     * Variabile utilizzata per riconoscere la recensione del negozio
     */
    private int id;
    /**
     * Variabile utilizzata per identificare l'id dell'oggetto
     */
    private String idOggetto;
    /**
     * Variabile utilizzata per identificare l'utente che ha fatto la recensione
     */
    private int idUtente;
    /**
     * Variabile utilizzata per identificare il testo della recensione
     */
    private String testo;
    /**
     * Variabile utilizzata per identificare la valutazione data
     */
    private int valutazione;
    /**
     * Varaibile utilizzata per identificare la data della recensione
     */
    private Date data;
    /**
     * Varaibile utilizzata per identificare l'utilità della recensione
     */
    private int utilita;

    /**
     * Funzione utilizzata per ottenre l'id della recensione
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
     * Funzione utilizzata per ottenre l'id dell'oggetto a cui è riferita la recensione
     * @return String idOggetto
     */
    public String getIdOggetto() {
        return idOggetto;
    }

    /**
     * Funzione utilizzata per settare l'id dell'oggetto a cui è riferita la recensione
     * @param idOggetto 
     */
    public void setIdOggetto(String idOggetto) {
        this.idOggetto = idOggetto;
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
     * Funzione utilizzata per ottenre la valutazione assegnata ad una recensione
     * @return int valutazione 
     */
    public int getValutazione() {
        return valutazione;
    }

    /**
     * Funzione utilizzata per settare la valutazione assegnata ad una recensione
     * @param valutazione 
     */
    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
    }

    /**
     * Funzione utilizzata per otterne la data di una recensione
     * @return Date data
     */
    public Date getData() {
        return data;
    }

    /**
     * Funzione utilizzata per settare la data di una recensione
     * @param data 
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Funzione utilizzata per ottenre l'utilità di un recensione
     * @return int utilita
     */
    public int getUtilita() {
        return utilita;
    }

    /**
     * Funzione utilizzata per settare l'utilità di un recensione
     * @param utilita 
     */
    public void setUtilita(int utilita) {
        this.utilita = utilita;
    }
}
