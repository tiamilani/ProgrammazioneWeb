/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.ordiniRicevuti;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mattia
 */
public class ModelloListeOrdiniRicevuti {
    private List<ModelloOrdiniRicevuti> listaOrdiniRicevuti;
    
    public ModelloListeOrdiniRicevuti(){
        listaOrdiniRicevuti = new ArrayList<>();
    }
    
    public ModelloListeOrdiniRicevuti(List<ModelloOrdiniRicevuti> existingList){
        listaOrdiniRicevuti = existingList;
    }
    
    public void add(ModelloOrdiniRicevuti categoria){
        listaOrdiniRicevuti.add(categoria);
    }
    
    public ModelloOrdiniRicevuti get(int index){
        return listaOrdiniRicevuti.get(index);
    }
    
    public List<ModelloOrdiniRicevuti> getList(){
        return listaOrdiniRicevuti;
    }
}
