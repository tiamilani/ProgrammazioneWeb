/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.recensioneOggetto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mattia
 */
public class ModelloListeRecensioneOggetto {
    private List<ModelloRecensioneOggetto> listaRecensioneOggetto;
    
    public ModelloListeRecensioneOggetto(){
        listaRecensioneOggetto = new ArrayList<>();
    }
    
    public ModelloListeRecensioneOggetto(List<ModelloRecensioneOggetto> existingList){
        listaRecensioneOggetto = existingList;
    }
    
    public void add(ModelloRecensioneOggetto categoria){
        listaRecensioneOggetto.add(categoria);
    }
    
    public ModelloRecensioneOggetto get(int index){
        return listaRecensioneOggetto.get(index);
    }
    
    public List<ModelloRecensioneOggetto> getList(){
        return listaRecensioneOggetto;
    }
}
