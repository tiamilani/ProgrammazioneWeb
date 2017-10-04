/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.indirizzo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mattia
 */
public class ModelloListeIndirizzo {
    private List<ModelloIndirizzo> listaIndirizzo;
    
    public ModelloListeIndirizzo(){
        listaIndirizzo = new ArrayList<>();
    }
    
    public ModelloListeIndirizzo(List<ModelloIndirizzo> existingList){
        listaIndirizzo = existingList;
    }
    
    public void add(ModelloIndirizzo categoria){
        listaIndirizzo.add(categoria);
    }
    
    public ModelloIndirizzo get(int index){
        return listaIndirizzo.get(index);
    }
    
    public List<ModelloIndirizzo> getList(){
        return listaIndirizzo;
    }
}
