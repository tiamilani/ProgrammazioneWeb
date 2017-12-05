/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.immagineNegozio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mattia
 */
public class ModelloListeImmagineNegozio {
    private List<ModelloImmagineNegozio> listaImmagineNegozio;
    
    public ModelloListeImmagineNegozio(){
        listaImmagineNegozio = new ArrayList<>();
    }
    
    public ModelloListeImmagineNegozio(List<ModelloImmagineNegozio> existingList){
        listaImmagineNegozio = existingList;
    }
    
    public void add(ModelloImmagineNegozio categoria){
        listaImmagineNegozio.add(categoria);
    }
    
    public ModelloImmagineNegozio get(int index){
        return listaImmagineNegozio.get(index);
    }
    
    public List<ModelloImmagineNegozio> getList(){
        return listaImmagineNegozio;
    }
}
