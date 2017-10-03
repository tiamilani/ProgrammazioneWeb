/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.immagineRecensione;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mattia
 */
public class ModelloListeImmagineRecensione {
    private List<ModelloImmagineRecensione> listaImmagineRecensione;
    
    public ModelloListeImmagineRecensione(){
        listaImmagineRecensione = new ArrayList<>();
    }
    
    public ModelloListeImmagineRecensione(List<ModelloImmagineRecensione> existingList){
        listaImmagineRecensione = existingList;
    }
    
    public void add(ModelloImmagineRecensione categoria){
        listaImmagineRecensione.add(categoria);
    }
    
    public ModelloImmagineRecensione get(int index){
        return listaImmagineRecensione.get(index);
    }
    
    public List<ModelloImmagineRecensione> getList(){
        return listaImmagineRecensione;
    }
}
