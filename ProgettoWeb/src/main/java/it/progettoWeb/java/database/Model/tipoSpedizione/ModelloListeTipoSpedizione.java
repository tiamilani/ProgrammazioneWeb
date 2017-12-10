/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.Model.tipoSpedizione;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mattia
 */
public class ModelloListeTipoSpedizione 
{
    private List<ModelloTipoSpedizione> listaTipoSpedizione;
    
    public ModelloListeTipoSpedizione()
    {
        listaTipoSpedizione = new ArrayList<>();
    }
    
    public ModelloListeTipoSpedizione(List<ModelloTipoSpedizione> existingList)
    {
        listaTipoSpedizione = existingList;
    }
    
    public void add(ModelloTipoSpedizione tipoSpedizione)
    {
        listaTipoSpedizione.add(tipoSpedizione);
    }
    
    public ModelloTipoSpedizione get(int index)
    {
        return listaTipoSpedizione.get(index);
    }
    
    public List<ModelloTipoSpedizione> getList()
    {
        return listaTipoSpedizione;
    }
}
