/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.Assistenza;

/**
 * Classe utilizzata per gestire gli elementi di tipo assistenza
 * @author mattia
 */

import java.util.Date;

public class ModelloAssistenza {
    
    //Nel caso vi siano problemi con le date
    //https://danielniko.wordpress.com/2012/04/17/simple-crud-using-jsp-servlet-and-mysql/
    
    /**
     * Variabile intera utilizzata per identificare la richiesta di assitenza
     */
    private int id;
    /**
     * Variabile intera utilizzata per identificare l'id dell'utente che partecipa alla richiesta di assistenza
     */
    private int idUtente;
    /**
     * Variabile intera utilizzata per identificare l'id dell'utente venditore che partecipa alla richiesta di assistenza
     */
    private int idVenditore;
    /**
     * Variabile intera utilizzata per identificare l'id dell'utente amminestratore a cui è la richiesta di assistenza
     */
    private int idAmministratore; 
    /**
     * Variabile intera utilizzata per identificare l'ordine a cui fa riferimento l'ordine
     */
    private int idOrdine;
    /**
     * Varibile intera utilizzata per identificare l'oggetto a appartenente all'ordine identificato in precedenza
     */
    private String idOggetto;
    /**
     * Variabile intera utilizzata per identificare lo stato in cui si trova la richiesta di assistenza
     */
    private int stato;
    /**
     * Variabile utilizzata per identificare la decisione che è stata presa dall'amministratore che ha gestito la richiesta
     */
    private String soluzione;
    /**
     * Variabile utilizzata per identificare la data di apertura della Richiesta di assistenza
     */
    private Date dataApertura;
    /**
     * Variabile utilizzata per identificare la data di chiusura della richiesta di assistenza
     */
    private Date dataChiusura;
    
    /**
     * Funzione che ritorna l'id della richiesta di assistenza
     * @return int identificativo della richesta di assistenza
     */
    public int getId() {
        return id;
    }

    /**
     * Funzione che permette di settare l'id dela richiesta di assistenza 
     * @param id Parametro che verrà settato
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Funzione che permette di ottenere l'id dell'utente che è all'interno della richiesta di assistenza che si sta trattando
     * @return int idUtente
     */
    public int getIdUtente() {
        return idUtente;
    }

    /**
     * Funzione che permette di settare l'id dell'utente a cui si riferisce la richiesta di assistenza
     * @param idUtente Parametro che verrà settato
     */
    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    /**
     * Funzioen che ritorna l'id del venditore che partecipa alla richiestadi assistenza
     * @return int idVenditore
     */
    public int getIdVenditore() {
        return idVenditore;
    }

    /**
     * Funzione che permette di settare l'id dell'utente venditore a cui si riferisce la richiesta di assistenza
     * @param idVenditore 
     */
    public void setIdVenditore(int idVenditore) {
        this.idVenditore = idVenditore;
    }

    /**
     * Funzione che ritorna l'id dell'amministratore che gestisce la richiesta di assistenza
     * @return int idAmministratore
     */
    public int getIdAmministratore() {
        return idAmministratore;
    }

    /**
     * Funzione che permette di settare l'id dell'utente amministratore a cui si riferisce la richiesta di assistenza
     * @param idAmministratore 
     */
    public void setIdAmministratore(int idAmministratore) {
        this.idAmministratore = idAmministratore;
    }

    /**
     * Funzione che permette di ottenre l'id dellordine a cui si riferisce la richiesta di assistenza
     * @return int idOrdine
     */
    public int getIdOrdine() {
        return idOrdine;
    }

    /**
     * Funzione che permette di settare l'id dell'ordine a cui si riferisce la richiesta di assistenza
     * @param idOrdine 
     */
    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    /**
     * Funzione che permette di ottenre l'id del'ogetto presente nell'ordine a cui si riferisce la richeista di assistenza
     * @return int idOggetto
     */
    public String getIdOggetto() {
        return idOggetto;
    }

    /**
     * Funzione che permette di settare l'id delloggetto che si riferisce a questa richiesta di assistenza
     * @param idOggetto 
     */
    public void setIdOggetto(String idOggetto) {
        this.idOggetto = idOggetto;
    }
    
    /**
     * Funzione che ritorna lo stato attuale della richiesta di assistenza
     * @return int stato
     */
    public int getStato() {
        return stato;
    }

    /**
     * Funzione che permette di settare lo stato attuale della richiesta di assistenza
     * @param stato 
     */
    public void setStato(int stato) {
        this.stato = stato;
    }

    /**
     * Funzione che permette di ottenre la soluzione che è stata presa dall'amministratore
     * @return String soluzione
     */
    public String getSoluzione() {
        return soluzione;
    }

    /**
     * Funzione che permette di settare la soluzione per la richiesta di assistenza presa in considerazione
     * @param soluzione 
     */
    public void setSoluzione(String soluzione) {
        this.soluzione = soluzione;
    }

    /**
     * Funzione che permette di ottenre la data di apertura della richiesta di assistenza
     * @return Date data apertura
     */
    public Date getDataApertura() {
        return dataApertura;
    }

    /**
     * Funzione che permette di settare la data di apertura di una richiesta di assistenza
     * @param dataApertura 
     */
    public void setDataApertura(Date dataApertura) {
        this.dataApertura = dataApertura;
    }

    /**
     * Funzione che permette di ottenre la data di chiusura della richiesta di assistenza
     * @return Date dataChiusra
     */
    public Date getDataChiusura() {
        return dataChiusura;
    }

    /**
     * Funzione che permette di settare la data di chiusura di una richiesta di assistenza
     * @param dataChiusura 
     */
    public void setDataChiusura(Date dataChiusura) {
        this.dataChiusura = dataChiusura;
    }
}
