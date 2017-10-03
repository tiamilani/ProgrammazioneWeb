/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.recensioneNegozio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mattia
 */
public class ModelloListeRecensioneNegozio {
    private List<ModelloRecensioneNegozio> listaRecensioniNegozio;
    
    public ModelloListeRecensioneNegozio(){
        listaRecensioniNegozio = new ArrayList<>();
    }
    
    public ModelloListeRecensioneNegozio(List<ModelloRecensioneNegozio> existingList){
        listaRecensioniNegozio = existingList;
    }
    
    public void add(ModelloRecensioneNegozio categoria){
        listaRecensioniNegozio.add(categoria);
    }
    
    public ModelloRecensioneNegozio get(int index){
        return listaRecensioniNegozio.get(index);
    }
    
    public List<ModelloRecensioneNegozio> getList(){
        return listaRecensioniNegozio;
    }
}
