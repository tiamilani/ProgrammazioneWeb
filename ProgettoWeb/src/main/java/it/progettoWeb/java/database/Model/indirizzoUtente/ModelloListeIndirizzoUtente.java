/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.indirizzoUtente;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mattia
 */
public class ModelloListeIndirizzoUtente {
    private List<ModelloIndirizzoUtente> listaIndirizziUtente;
    
    public ModelloListeIndirizzoUtente(){
        listaIndirizziUtente = new ArrayList<>();
    }
    
    public ModelloListeIndirizzoUtente(List<ModelloIndirizzoUtente> existingList){
        listaIndirizziUtente = existingList;
    }
    
    public void add(ModelloIndirizzoUtente categoria){
        listaIndirizziUtente.add(categoria);
    }
    
    public ModelloIndirizzoUtente get(int index){
        return listaIndirizziUtente.get(index);
    }
    
    public List<ModelloIndirizzoUtente> getList(){
        return listaIndirizziUtente;
    }
}
