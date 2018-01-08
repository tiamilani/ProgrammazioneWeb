/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.Oggetto;

import java.sql.Date;

/**
 * Classe utilizzata per gestire il modello Oggetto
 * @author mattia
 */
public class ModelloOggetto {
    
    /**
     * Variabile utilizzata per identificare univocamente un oggetto, sarà un hash md5 tra il nome del'oggetto ed l'id del negozio, in modo che un negozio non possa avere prodotti con lo stesso nome
     */
    private String id;
    /**
     * Varibile utilizzata per identificare il negozio di appartenenza di un oggetto
     */
    private int idNegozio;
    /**
     * Variabile che contiene il nome dell'oggetto in vendita
     */
    private String nome;
    /**
     * Nome in minuscolo, per facilitare la ricerca
     */
    private String nomeDownCase;
    /**
     * Prezzo dell'oggetto
     */
    private double prezzo;
    /**
     * Variabile utilizzata per la descrizione dell'oggetto da parte del venditore
     */
    private String descrizione;
    /**
     * Variabile che indica o meno la presenza del ritiro in negozio
     */
    private int ritiroInNegozio;
    /**
     * Variabile che indica la disponibilità in singoli pezzi dell'oggetto 
     */
    private int disponibilita;
    /**
     * Variabile che indica lo stato della disponibilità
     */
    private int statoDisponibilita;
    /**
     * Variabile che indica la percentuale di sconto a cui è sottoposto l'oggetto
     */
    private double sconto;
    /**
     * Data in cui terminerà lo sconto
     */
    private Date dataFineSconto;
    /**
     * Variabile che indica la categoria di appartenenza dell'oggetto
     */
    private int categoria;

    public void setDateToNull(){
        dataFineSconto = null;
    }
    
    /**
     * Funzione utilizzata per ottenre l'id dell'oggetto
     * @return String id
     */
    public String getId() {
        return id;
    }

    /**
     * Funzione utilizzata per settare l'id dell'oggetto
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Funzione utilizzata per otteren l'id del negozio che vende quell'oggetto
     * @return Int idNegozio
     */
    public int getIdNegozio() {
        return idNegozio;
    }

    /**
     * Funzione utilizzata per settare l'id del negozio che vende l'oggetto in questione
     * @param idNegozio 
     */
    public void setIdNegozio(int idNegozio) {
        this.idNegozio = idNegozio;
    }

    /**
     * Funzione utilizzata per ottenere il nome dell'oggetto
     * @return String nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Funzione utilizzata per settare il nome dell'oggetto
     * @param nome 
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Funzione utilizzata per ottenere il nome in minuscolo
     * @return String nomeDownCase
     */
    public String getNomeDownCase() {
        return nomeDownCase;
    }

    /**
     * Funzione utilizzata per settare il nome in maiuscolo
     * @param nomeDownCase 
     */
    public void setNomeDownCase(String nomeDownCase) {
        this.nomeDownCase = nomeDownCase;
    }

    /**
     * Funzione utilizzata per otterne il prezzo dell'oggetto
     * @return double prezzo
     */
    public double getPrezzo() {
        return prezzo;
    }

    /**
     * Funzione utilizzata per settare il prezzo dell'oggetto
     * @param prezzo 
     */
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    /**
     * Funzione utilizzata ottenere la descrizione dell'oggetto
     * @return String descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Funzione utilzzata per settare la descrizione dell'oggetto
     * @param descrizione 
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Funzione utilizzata per ottenre il ritiro in negozio
     * @return int ritiroInNegozio
     */
    public int getRitiroInNegozio() {
        return ritiroInNegozio;
    }

    /**
     * Funzione utilzzata per settare il valore del ritiroInNegozio
     * @param ritiroInNegozio 
     */
    public void setRitiroInNegozio(int ritiroInNegozio) {
        this.ritiroInNegozio = ritiroInNegozio;
    }

    /**
     * Funzione utilizzata per ottenre la disponibilità attuale dell'ogetto
     * @return int disponibilita
     */
    public int getDisponibilita() {
        return disponibilita;
    }

    /**
     * Funzione utilizzata per settare la disponibilità dell'oggetto
     * @param disponibilita 
     */
    public void setDisponibilita(int disponibilita) {
        this.disponibilita = disponibilita;
    }

    /**
     * Funzione utilizzata per ottenre lo stato della disponibilità
     * @return int statoDisponibilità
     */
    public int getStatoDisponibilita() {
        return statoDisponibilita;
    }

    /**
     * Funzione Utilizzata per settare lo stato della disponibilità
     * @param statoDisponibilita 
     */
    public void setStatoDisponibilita(int statoDisponibilita) {
        this.statoDisponibilita = statoDisponibilita;
    }

    /**
     * Funzione che ottiene la percentuale di sconto settata per l'oggetto
     * @return double sconto
     */
    public double getSconto() {
        return sconto;
    }

    /**
     * Funione utilizzata per settare il valore dello sconto da applicare all'oggetto
     * @param sconto 
     */
    public void setSconto(double sconto) {
        this.sconto = sconto;
    }

    /**
     * Funzione utilizzata per ottenre la data in cui finirà lo sconto
     * @return Date dataFineSconto
     */
    public Date getDataFineSconto() {
        return dataFineSconto;
    }

    /**
     * Funzione utilizzata per settare la data di fine sconto
     * @param dataFineSconto 
     */
    public void setDataFineSconto(Date dataFineSconto) {
        this.dataFineSconto = dataFineSconto;
    }

    /**
     * Funzione utilizzata per ottenre la categoria dell'oggetto in questione
     * @return int categoria
     */
    public int getCategoria() {
        return categoria;
    }

    /**
     * Funzione utilizzata per settare la categoria dell'oggetto in questione
     * @param categoria 
     */
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
}
