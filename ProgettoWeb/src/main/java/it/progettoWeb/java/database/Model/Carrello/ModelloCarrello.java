/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.Carrello;

/**
 * Classe utilizzata per gestire gli elementi dei carrelli
 * @author mattia
 */
public class ModelloCarrello {
    
    /**
     * Variabile intera utilizzata per identificare l'id dell'utente a cui il carrello fa riferimento
     */
    private int idUtente;
    /**
     * Variabile intera utilizzata per identificare l'id dell'ordine che si tiene nel carrello dell'utente
     */
    private int idOrdine;
    /**
     * Variabile che mantiene il subtotale del carrello
     */
    private double subTotal;

    /**
     * Funione utilizzata per ottenre l'id dell'utente a cui si riferisece il carrello
     * @return int idUtente
     */
    public int getIdUtente() {
        return idUtente;
    }

    /**
     * Funzione utlizzata per settare l'id dell'utente a cui fa riferimento il carrello
     * @param idUtente 
     */
    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    /**
     * Funzione utilizzata per ottenere l'id dell'ordine a cui fa riferimento il carrello
     * @return int idOrdine
     */
    public int getIdOrdine() {
        return idOrdine;
    }

    /**
     * Funzione utilizzata per settare l'id  dell'ordine a cui fa riferimento il carrello
     * @param idOrdine 
     */
    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    /**
     * Funzione utilizzata per ottenere il subTotale del carrello
     * @return double subTotal
     */
    public double getSubTotal() {
        return subTotal;
    }

    /**
     * Funzione utilizzata per settare il subtotale del carrello in considerazione
     * @param subTotal 
     */
    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}
