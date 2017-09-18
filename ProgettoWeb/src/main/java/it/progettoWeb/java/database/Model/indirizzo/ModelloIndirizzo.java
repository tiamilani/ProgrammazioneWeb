/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.indirizzo;

/**
 * classe utilizzata per gestire gli oggetti del modello Indirizzo
 * @author mattia
 */
public class ModelloIndirizzo {
    
    /**
     * Variabile che identifica l'indirizzo
     */
    private int idI;
    /**
     * Variabile per memorizzare lo stato
     */
    private String stato;
    /**
     * Variabile per memorizzare la regione
     */
    private String regione;
    /**
     * Variabile per memorizzare la provincia
     */
    private String provincia;
    /**
     * Variabile per memorizzare la città
     */
    private String citta;
    /**
     * Variabile per memorizzare la via
     */
    private String via;
    /**
     * Variabile per memorizzare il numero civico
     */
    private int nCivico;
    /**
     * Variabile per memorizzare l'interno
     */
    private int interno;
    /**
     * Variabile per memorizzare la latitudine
     */
    private double latitudine;
    /**
     * Variabile per memorizzare la longitudine
     */
    private double longitudine;

    /**
     * Funzione utilizzata per ottenre l'id dell'indirizzo
     * @return 
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
     * Funzione utilizzata per otterne lo stato
     * @return String stato
     */
    public String getStato() {
        return stato;
    }

    /**
     * Funzione utilizzata per settare lo stato
     * @param stato 
     */
    public void setStato(String stato) {
        this.stato = stato;
    }

    /**
     * Funzione utilizzata per otterne la regione
     * @return String regione
     */
    public String getRegione() {
        return regione;
    }

    /**
     * Funzione utilizzata per settare la regione
     * @param regione 
     */
    public void setRegione(String regione) {
        this.regione = regione;
    }

    /**
     * Funzione utilizzata per otterne la provincia
     * @return String provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Funzione utilizzata per settare la provincia
     * @param provincia 
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Funzione utilizzata per ottenre la città
     * @return String citta
     */
    public String getCitta() {
        return citta;
    }

    /**
     * Funzione utilizzata per settare la città
     * @param citta 
     */
    public void setCitta(String citta) {
        this.citta = citta;
    }

    /**
     * Funzione utilizzata per ottenre la via
     * @return String via
     */
    public String getVia() {
        return via;
    }

    /**
     * Funzione utilizzata per settare la via
     * @param via 
     */
    public void setVia(String via) {
        this.via = via;
    }

    /**
     * Funzione utilizzata per ottenre il numero civico
     * @return 
     */
    public int getnCivico() {
        return nCivico;
    }

    /**
     * Funzione utilizzata per settare il numero civico
     * @param nCivico 
     */
    public void setnCivico(int nCivico) {
        this.nCivico = nCivico;
    }

    /**
     * Funzione utilizzata per ottenre l'interno
     * @return int interno
     */
    public int getInterno() {
        return interno;
    }

    /**
     * Funzione utilizzata per settare l'interno
     * @param interno 
     */
    public void setInterno(int interno) {
        this.interno = interno;
    }

    /**
     * Funzione utilizzata per otterne la latitudine
     * @return double latitudine
     */
    public double getLatitudine() {
        return latitudine;
    }

    /**
     * Funzione utilizzata per settare la latitudine
     * @param latitudine 
     */
    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    /**
     * Funzione utilizzata per otterne la longitudine
     * @return double longitudine
     */
    public double getLongitudine() {
        return longitudine;
    }

    /**
     * Funzione utilizzata per settare la longitudine
     * @param longitudine 
     */
    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }
}
