/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.tipoSpedizione;

/**
 * Classe utilizzata per gestire gli oggetti di tipo Tipo Spedizione
 * @author mattia
 */
public class ModelloTipoSpedizione {
    
    /**
     * Variabile utilizata per identificare la spedizione
     */
    private int idS;
    /**
     * Variabile utilizzata per identificare il negozio che permette questo tipo di spedizione
     * E a cui appartiene
     */
    private int idN;
    /**
     * Nome della spedizione
     */
    private String Nome;
    /**
     * Variabile utilizzata per identificare il costo della spedizione
     */
    private double Prezzo;
    /**
     * Variabile utilizzata per identificare il corriere scelto
     */
    private String Corriere;
    /**
     * Variabile usata per indicare il tempo richiesto in giorni lavorativi
     */
    private int tempoRichiesto;

    /**
     * Funzione utilizzata per ottenere l'id della spedizione
     * @return int idS
     */
    public int getIdS() {
        return idS;
    }

    /**
     * Funzione utilizzata per settare l'id della spedizione
     * @param idS 
     */
    public void setIdS(int idS) {
        this.idS = idS;
    }

    /**
     * Funzione utilizzata per ottenere l'id del negozio che utilizza quel tipo di spedizione
     * @return int idN
     */
    public int getIdN() {
        return idN;
    }

    /**
     * Funzione per settare l'id del negozio che utilizza questo tipo di spedizione
     * @param idN 
     */
    public void setIdN(int idN) {
        this.idN = idN;
    }
    
    /**
     * Funzione utilizzata per ottenre il nome della spedizione (semi descrizione)
     * @return String Nome
     */
    public String getNome() {
        return Nome;
    }

    /**
     * Funzione utilizzata per settare il nome della spedizione
     * @param Nome 
     */
    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    /**
     * Funzone utilizzata per ottenre il prezzo della spedizione
     * @return double Prezzo
     */
    public double getPrezzo() {
        return Prezzo;
    }

    /**
     * Funzioen utilizzata per settare il prezzo della spedizione
     * @param Prezzo 
     */
    public void setPrezzo(double Prezzo) {
        this.Prezzo = Prezzo;
    }

    /**
     * Funzione utilizzata per settare il nome del corriere che si occuperà della spedizione
     * @return String Corriere
     */
    public String getCorriere() {
        return Corriere;
    }

    /**
     * Funzione utilizzata per settare il nome del correre che si occuperà della spedizione
     * @param Corriere 
     */
    public void setCorriere(String Corriere) {
        this.Corriere = Corriere;
    }

    /**
     * Funzione utilizzata per ottenre il tempo richiesto in giorni lavorativi per la spedizione
     * @return int tempoRichiesto
     */
    public int getTempoRichiesto() {
        return tempoRichiesto;
    }

    /**
     * Funzione utilizzata per settare il tempo richiesto 
     * @param tempoRichiesto 
     */
    public void setTempoRichiesto(int tempoRichiesto) {
        this.tempoRichiesto = tempoRichiesto;
    }
}
