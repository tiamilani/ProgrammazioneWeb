/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.Oggetto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mattia
 */
public class ModelloListeOggetto {
    private List<ModelloOggetto> listaOggetto;
    
    public ModelloListeOggetto(){
        listaOggetto = new ArrayList<>();
    }
    
    public ModelloListeOggetto(List<ModelloOggetto> existingList){
        listaOggetto = existingList;
    }
    
    public void add(ModelloOggetto categoria){
        listaOggetto.add(categoria);
    }
    
    public ModelloOggetto get(int index){
        return listaOggetto.get(index);
    }
    
    public List<ModelloOggetto> getList(){
        return listaOggetto;
    }
}
