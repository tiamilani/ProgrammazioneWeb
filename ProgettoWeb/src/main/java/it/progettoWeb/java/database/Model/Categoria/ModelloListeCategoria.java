/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.Categoria;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mattia
 */
public class ModelloListeCategoria {
    private List<ModelloCategoria> listaCategorie;
    
    public ModelloListeCategoria(){
        listaCategorie = new ArrayList<>();
    }
    
    public ModelloListeCategoria(List<ModelloCategoria> existingList){
        listaCategorie = existingList;
    }
    
    public void add(ModelloCategoria categoria){
        listaCategorie.add(categoria);
    }
    
    public ModelloCategoria get(int index){
        return listaCategorie.get(index);
    }
    
    public List<ModelloCategoria> getList(){
        return listaCategorie;
    }
}
