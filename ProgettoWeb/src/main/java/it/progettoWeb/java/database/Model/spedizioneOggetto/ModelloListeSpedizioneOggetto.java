/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.spedizioneOggetto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mattia
 */
public class ModelloListeSpedizioneOggetto {
    private List<ModelloSpedizioneOggetto> lsitaOrdine;
    private int idListaOrdine;
    
    public boolean present(int id){
        for(int i=0;i<lsitaOrdine.size();i++){
            if(lsitaOrdine.get(i).getIdS() == id)
                return true;
        }
        return false;
    }
    
    public ModelloListeSpedizioneOggetto(){
        lsitaOrdine = new ArrayList<>();
    }
    
    public ModelloListeSpedizioneOggetto(List<ModelloSpedizioneOggetto> existingList){
        lsitaOrdine = existingList;
    }
    
    public void add(ModelloSpedizioneOggetto categoria){
        lsitaOrdine.add(categoria);
    }
    
    public ModelloSpedizioneOggetto get(int index){
        return lsitaOrdine.get(index);
    }
    
    public List<ModelloSpedizioneOggetto> getList(){
        return lsitaOrdine;
    }
    
    public int getId()
    {
        return idListaOrdine;
    }
    public void setId(int id)
    {
        idListaOrdine = id;
    }
    public int getSize()
    {
        return lsitaOrdine.size();
    }
}
