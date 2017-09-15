/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.Oggetto;

import java.util.Date;

/**
 * Classe utilizzata per gestire il modello Oggetto
 * @author mattia
 */
public class ModelloOggetto {
    private String id;
    private int idNegozio;
    private String nome;
    private String nomeDownCase;
    private double prezzo;
    private String descrizione;
    private int ritiroInNegozio;
    private int disponibilita;
    private int statoDisponibilita;
    private double sconto;
    private Date dataFineSconto;
    private int categoria;
}
