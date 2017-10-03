/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.immagineUtente;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mattia
 */
public class ModelloListeImmagineUtente {
    private List<ModelloImmagineUtente> listaImmagineRecensione;
    
    public ModelloListeImmagineUtente(){
        listaImmagineRecensione = new ArrayList<>();
    }
    
    public ModelloListeImmagineUtente(List<ModelloImmagineUtente> existingList){
        listaImmagineRecensione = existingList;
    }
    
    public void add(ModelloImmagineUtente categoria){
        listaImmagineRecensione.add(categoria);
    }
    
    public ModelloImmagineUtente get(int index){
        return listaImmagineRecensione.get(index);
    }
    
    public List<ModelloImmagineUtente> getList(){
        return listaImmagineRecensione;
    }
}
