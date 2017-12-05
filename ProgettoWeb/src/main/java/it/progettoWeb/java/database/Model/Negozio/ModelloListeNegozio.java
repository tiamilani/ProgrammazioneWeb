/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.Negozio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mattia
 */
public class ModelloListeNegozio {
    private List<ModelloNegozio> listaNegozio;
    
    public ModelloListeNegozio(){
        listaNegozio = new ArrayList<>();
    }
    
    public ModelloListeNegozio(List<ModelloNegozio> existingList){
        listaNegozio = existingList;
    }
    
    public void add(ModelloNegozio categoria){
        listaNegozio.add(categoria);
    }
    
    public ModelloNegozio get(int index){
        return listaNegozio.get(index);
    }
    
    public List<ModelloNegozio> getList(){
        return listaNegozio;
    }
}
