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
    private List<ModelloOrdine> lsitaOrdine;
    private int idListaOrdine;
    
    public ModelloListeOrdine(){
        lsitaOrdine = new ArrayList<>();
    }
    
    public ModelloListeOrdine(List<ModelloOrdine> existingList){
        lsitaOrdine = existingList;
    }
    
    public void add(ModelloOrdine categoria){
        lsitaOrdine.add(categoria);
    }
    
    public ModelloOrdine get(int index){
        return lsitaOrdine.get(index);
    }
    
    public List<ModelloOrdine> getList(){
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
