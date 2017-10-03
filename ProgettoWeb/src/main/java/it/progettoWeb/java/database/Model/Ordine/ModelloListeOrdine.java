/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.Ordine;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mattia
 */
public class ModelloListeOrdine {
    private List<ModelloOrdine> listaCategorie;
    
    public ModelloListeOrdine(){
        listaCategorie = new ArrayList<>();
    }
    
    public ModelloListeOrdine(List<ModelloOrdine> existingList){
        listaCategorie = existingList;
    }
    
    public void add(ModelloOrdine categoria){
        listaCategorie.add(categoria);
    }
    
    public ModelloOrdine get(int index){
        return listaCategorie.get(index);
    }
    
    public List<ModelloOrdine> getList(){
        return listaCategorie;
    }
}
