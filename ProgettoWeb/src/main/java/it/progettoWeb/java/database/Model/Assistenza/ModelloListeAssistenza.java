/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.Assistenza;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mattia
 */
public class ModelloListeAssistenza {
    private List<ModelloAssistenza> listaAssistenza;
    
    public ModelloListeAssistenza(){
        listaAssistenza = new ArrayList<>();
    }
    
    public ModelloListeAssistenza(List<ModelloAssistenza> existingList){
        listaAssistenza = existingList;
    }
    
    public void add(ModelloAssistenza categoria){
        listaAssistenza.add(categoria);
    }
    
    public ModelloAssistenza get(int index){
        return listaAssistenza.get(index);
    }
    
    public List<ModelloAssistenza> getList(){
        return listaAssistenza;
    }
}
