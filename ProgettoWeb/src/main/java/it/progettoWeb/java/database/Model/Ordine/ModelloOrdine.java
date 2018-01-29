/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.Ordine;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Classe utilizzata per gestire gli oggetti di tipo modello ordine
 * @author mattia
 */
public class ModelloOrdine {
    
    /**
     * Variabile utilizzata per identificare l'ordine
     */
    private int idOrdine;
    /**
     * Variabile utilizzata per identificare l'id delloggetto nell'ordine
     */
    private String idOggetto;
    /**
     * Variabile utilizzata per l'Id del negozio che vende l'oggetto
     */
    private int idNegozio;
    /**
     * Variabile utilizzata per identificare l'id dell'utente che ha questo oggetto tra i suoi ordini
     */
    private int idUtente;
    /**
     * Variabile utilizzata per identificare lo stato dell'ordine
     */
    private int stato;
    /**
     * Variabile utilzzata per identificare la quantità di oggetti con questo id appartenenti allo stesso ordine
     */
    private int quantita;
    /**
     * Variabile utilizzata per identificare il codice di tracking
     */
    private String codiceTracking;
    /**
     * Variabile utilizzata per identificare la data di arrivo presunta
     */
    private Date dataArrivoPresunta;
    /**
     * Variabile utilizzata per identificare la data in cui è stato effettuato l'ordine
     */ 
    private Timestamp dataOrdine;
    /**
     * Variabile utilizzata per identificare il prezzo di acquisto relativo a questo oggetto di questo ordine
     */
    private double prezzoDiAcquisto;
    /**
     * Variabile utilizzata per identificare l'id della spedizione scelta per l'ordine
     */
    private int idS;
    /**
     * Variabile utilizzata per identificare l'id dell'indirizzo scelto per l'ordine
     */
    private int idI;

    /**
     * Funzione utilizzata per ottenere l'id dell'ordine 
     * @return int idOrdine
     */
    public int getIdOrdine() {
        return idOrdine;
    }

    /**
     * Funzione utilizzata per settare l'id dell'ordine
     * @param idOrdine 
     */
    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    /**
     * Funzione utilizzata per ottenere l'id dell'oggetto appartente a questo ordine
     * @return String idOggetto
     */
    public String getIdOggetto() {
        return idOggetto;
    }

    /**
     * Funzione utilizzata per settare l'id dell'oggetto appartenete a questo ordine
     * @param idOggetto 
     */
    public void setIdOggetto(String idOggetto) {
        this.idOggetto = idOggetto;
    }

    /**
     * Funzione utilizzata per ottenere l'id del negozio che vende questo oggetto
     * @return int idNegozio
     */
    public int getIdNegozio() {
        return idNegozio;
    }

    /**
     * Funzione utilizzata per settare l'id del negozio che vende questo oggetto
     * @param idNegozio 
     */
    public void setIdNegozio(int idNegozio) {
        this.idNegozio = idNegozio;
    }

    /**
     * Funzione utilizzata per ottenere l'id dell'utente che sta effettuando l'ordine
     * @return int idUtente
     */
    public int getIdUtente() {
        return idUtente;
    }

    /**
     * Funzione utilizzata per settare l'id dell'utente che sta effettuando l'ordine
     * @param idUtente 
     */
    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    /**
     * Funzione utilizzata per ottenere lo stato dell'ordine
     * @return int stato
     */
    public int getStato() {
        return stato;
    }

    /**
     * Funzione utilizzata per settare lo stato dell'ordine
     * @param stato 
     */
    public void setStato(int stato) {
        this.stato = stato;
    }

    /**
     * Funzione utilizzata per ottenere la quantità di oggetti nell'ordine
     * @return int quantita
     */
    public int getQuantita() {
        return quantita;
    }

    /**
     * Funzione utilizzata per settare la quantita di oggetti presenti nell'ordine
     * @param quantita 
     */
    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    /**
     * Funzione utilizzata per ottenere il codice di tracking
     * @return String codiceTracking
     */
    public String getCodiceTracking() {
        return codiceTracking;
    }

    /**
     * Funzione utilizzata per settare il codice di tracking dell'ordine
     * @param codiceTracking 
     */
    public void setCodiceTracking(String codiceTracking) {
        this.codiceTracking = codiceTracking;
    }

    /**
     * Funzione utilizzata per ottenere la data di arrivo presunta, calcolata sui giorni della spedizione scelta
     * @return Date dataArrivoPresunta
     */
    public Date getDataArrivoPresunta() {
        return dataArrivoPresunta;
    }

    /**
     * Funzione utilizzata per settare la data di arrivo presunta
     * @param dataArrivoPresunta 
     */
    public void setDataArrivoPresunta(Date dataArrivoPresunta) {
        this.dataArrivoPresunta = dataArrivoPresunta;
    }

    /**
     * Funzione utilizzata per ottenere la data in cui è stato fatto l'ordine
     * @return Timestamp dataOrdine
     */
    public Timestamp getDataOrdine() {
        return dataOrdine;
    }

    /**
     * Funzione utilizzata per settare la data in cui è stato fatto l'ordinde
     * @param dataOrdine 
     */
    public void setDataOrdine(Timestamp dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    /**
     * Funzione utilizzata per ottenere il prezzo a cui è stato acquistato l'oggetto
     * @return double prezzoDiAcquisto
     */
    public double getPrezzoDiAcquisto() {
        return prezzoDiAcquisto;
    }

    /**
     * Funzione utilizzata per settare il prezzo a cui è stato acquistato l'oggetto
     * @param prezzoDiAcquisto 
     */
    public void setPrezzoDiAcquisto(double prezzoDiAcquisto) {
        this.prezzoDiAcquisto = prezzoDiAcquisto;
    }

    /**
     * Funzione utilizzata per ottenre l'id della spedizione a cui si fa riferimento
     * @return int id tipo spedizione
     */
    public int getIdS() {
        return idS;
    }

    /**
     * Funzione utilizzata per settaer l'id della spedizione a cui si fa riferimento
     * @param idS 
     */
    public void setIdS(int idS) {
        this.idS = idS;
    }
    
    /**
     * Funzione utilizzata per ottenere l'id dell'indirizzo a cui si fa riferimento
     * @return int id indirizzo spedizione
     */
    public int getIdI() {
        return idI;
    }
    
    /**
     * Funzione utilizzata per settare l'id dell'indirizzo a cui si fa riferimento
     * @param idI 
     */
    public void setIdI(int idI) {
        this.idI = idI;
    }
    
    public double getPrezzoDiAcquistoRounded(){
        return new BigDecimal(prezzoDiAcquisto).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
