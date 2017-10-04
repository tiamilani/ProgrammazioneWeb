/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.Categoria;

/**
 * Classe utilizzata per gestire il tipo ModelloCategoria
 * @author mattia
 */
public class ModelloCategoria {
    private int id;
    private String nome;
    private String sottoCategoria;
    private String descrizione;
    private int oggettiPresenti;

    public ModelloCategoria(){
        //No arguments
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSottoCategoria() {
        return sottoCategoria;
    }

    public void setSottoCategoria(String sottoCategoria) {
        this.sottoCategoria = sottoCategoria;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getOggettiPresenti() {
        return oggettiPresenti;
    }

    public void setOggettiPresenti(int oggettiPresenti) {
        this.oggettiPresenti = oggettiPresenti;
    }
}
