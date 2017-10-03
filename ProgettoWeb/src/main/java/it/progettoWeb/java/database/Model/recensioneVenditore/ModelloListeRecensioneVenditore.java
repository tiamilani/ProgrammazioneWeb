/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.recensioneVenditore;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mattia
 */
public class ModelloListeRecensioneVenditore {
    private List<ModelloRecensioneVenditore> listaRecensioneVenditore;
    
    public ModelloListeRecensioneVenditore(){
        listaRecensioneVenditore = new ArrayList<>();
    }
    
    public ModelloListeRecensioneVenditore(List<ModelloRecensioneVenditore> existingList){
        listaRecensioneVenditore = existingList;
    }
    
    public void add(ModelloRecensioneVenditore categoria){
        listaRecensioneVenditore.add(categoria);
    }
    
    public ModelloRecensioneVenditore get(int index){
        return listaRecensioneVenditore.get(index);
    }
    
    public List<ModelloRecensioneVenditore> getList(){
        return listaRecensioneVenditore;
    }
}
