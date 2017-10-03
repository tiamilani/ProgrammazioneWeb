/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.immagineOggetto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mattia
 */
public class ModelloListeImmagineOggetto {
    private List<ModelloImmagineOggetto> listaImmagineOggetto;
    
    public ModelloListeImmagineOggetto(){
        listaImmagineOggetto = new ArrayList<>();
    }
    
    public ModelloListeImmagineOggetto(List<ModelloImmagineOggetto> existingList){
        listaImmagineOggetto = existingList;
    }
    
    public void add(ModelloImmagineOggetto categoria){
        listaImmagineOggetto.add(categoria);
    }
    
    public ModelloImmagineOggetto get(int index){
        return listaImmagineOggetto.get(index);
    }
    
    public List<ModelloImmagineOggetto> getList(){
        return listaImmagineOggetto;
    }
}
