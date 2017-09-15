/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.Negozio;

/**
 * Classe utilizzata per gestire il modello negozio
 * @author mattia
 */

import java.util.Date;

public class ModelloNegozio {
    
    /**
     * Variabile intera utilizzata per identificare il negozio
     */
    private int id;
    /**
     * Variabile intera utilizata per identificare il venditore proprietario del negozio
     */
    private int idVenditore;
    /**
     * Variabile String utilizzata per identificare il nome del negozio
     */
    private String nomeNegozio;
    /**
     * Variabile che permette di rappresentare la valutazione complessiva del negozio
     */
    private double valutazione;
    /**
     * Variabile intera che indica se il negozio è ancora attivo oppure no 1->attivo 0->chiuso
     */
    private int attivo;
    /**
     * Variabile utilizzata per identificare l'indirizzo del negozio
     */
    private int idI;
    /**
     * Variabile che rappresenta la data in cui è stato aperto il negozio
     */
    private Date dataApertura;
    /**
     * Variabile che identifica tramite una stringa il link al sito del negozio
     */
    private String linkSito;
    /**
     * Stringa utilizzata per identificare gli orari del negozio
     */
    private String orarioNegozio;

    /**
     * Funzione utilizzata per ottenere l'id del negozio a cui si sta facendo riferimento
     * @return int id
     */
    public int getId() {
        return id;
    }

    /**
     * Funzione utilizzata per settare l'id del negozio
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Funzione utilizzata per otenere l'id del venditore proprietario del negozio
     * @return int idVenditore
     */
    public int getIdVenditore() {
        return idVenditore;
    }

    /**
     * Funzione utilizzata per settare l'id del venditore proprietario del negozio
     * @param idVenditore 
     */
    public void setIdVenditore(int idVenditore) {
        this.idVenditore = idVenditore;
    }

    /**
     * Funzione utilizzata per ottenre il nome del negozio a cui ci si riferisce
     * @return String nomeNegozio
     */
    public String getNomeNegozio() {
        return nomeNegozio;
    }

    /**
     * Funzione utilizzata per settare il nome del negozio a cui si fa riferimento
     * @param nomeNegozio 
     */
    public void setNomeNegozio(String nomeNegozio) {
        this.nomeNegozio = nomeNegozio;
    }

    /**
     * Funzione per ottenre la valutazione attuale del negozio
     * @return double Valutazione
     */
    public double getValutazione() {
        return valutazione;
    }

    /**
     * Funzione utilizzata per settare una valutazione del negozio
     * @param valutazione 
     */
    public void setValutazione(double valutazione) {
        this.valutazione = valutazione;
    }

    /**
     * Funzione per ottenere se il negozio è attivo oppure no
     * @return int attivo
     */
    public int getAttivo() {
        return attivo;
    }

    /**
     * Funzione utilizzata per settare lo stato del negozio se attivo o chiuso
     * @param attivo 
     */
    public void setAttivo(int attivo) {
        this.attivo = attivo;
    }

    /**
     * Funzione utilizzata per ottenre l'id dell'indirizzo del negozio
     * @return int idI
     */
    public int getIdI() {
        return idI;
    }

    /**
     * Funzione utlizzata per settare l'id dell'indirizzo in cui si trova il negozio
     * @param idI 
     */
    public void setIdI(int idI) {
        this.idI = idI;
    }

    /**
     * Funzione utilizzata per ottenere la data di apertura del negozio
     * @return Date dataApertura
     */
    public Date getDataApertura() {
        return dataApertura;
    }

    /**
     * Funzione utilizzata per settare la data di apertura del negozio
     * @param dataApertura 
     */
    public void setDataApertura(Date dataApertura) {
        this.dataApertura = dataApertura;
    }

    /**
     * Funzione utilizzata per ottenre il link al sito del negozio
     * @return String linkSito
     */
    public String getLinkSito() {
        return linkSito;
    }

    /**
     * Funzione utilizzata per settare il link del sito di riferimento per il negozio in esame
     * @param linkSito 
     */
    public void setLinkSito(String linkSito) {
        this.linkSito = linkSito;
    }

    /**
     * Funzione utilizzata per ottenere la stringa con gli orari di apertura e chiusura del negozio
     * @return String orarioNegozio
     */
    public String getOrarioNegozio() {
        return orarioNegozio;
    }

    /**
     * Funzione utilizzata per settare l'orario di apertura del negozio
     * @param orarioNegozio 
     */
    public void setOrarioNegozio(String orarioNegozio) {
        this.orarioNegozio = orarioNegozio;
    }
}
