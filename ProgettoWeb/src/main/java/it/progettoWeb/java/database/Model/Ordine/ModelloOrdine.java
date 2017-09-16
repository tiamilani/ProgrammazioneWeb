/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.Ordine;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author mattia
 */
public class ModelloOrdine {
    private int idOrdine;
    private String idOggetto;
    private int idNegozio;
    private int idUtente;
    private int stato;
    private int quantita;
    private String codiceTracking;
    private Date dataArrivoPresunta; 
    private Timestamp dataOrdine;
    private double prezzoDiAcquisto;
    private int idS;
}
