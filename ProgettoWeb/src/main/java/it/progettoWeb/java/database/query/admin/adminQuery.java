/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.query.admin;
/**
 *
 * @author mattia
 */
public class adminQuery {
    public static String hello() {
        return "Hello from" + adminQuery.class.toString();
    }
    
    
    /**
     * @author Damiano
     * Metodo che ritorna la stringa che rappresenta la query per contare il numero di richieste di assistenza di un amministratore
     * @return La stringa che rappresenta la query
     */
    public static String numberRequestOfAssistance(int id){
        return "SELECT count(idAmministratore) AS numRichieste FROM progettoweb.Assistenza where idAmministratore="+id+";";
     }
    
    /**
     * @author Damiano
     * Metodo che ritorna il numero di richieste di assistenza in un determinato stato
     * @param stato variabile che identifica lo stato da cercare
     * @return La stringa che rappresenta la query
     */
    public static String numberRequestOfAssistanceInAState(int stato){
        return "SELECT COUNT (id) AS numRichieste FROM Assistenza WHERE stato = "+stato+";";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna il numero di richieste di assistenza in un determinato stato di un determinato ammimnistratore
     * @param stato variabile che identifica lo stato da cercare
     * @param id variabile che identifica l'amministratore di cui si vogliono ottenre i dati
     * @return La stringa che rappresenta la query
     */
    public static String numberRequestOfAssistanceInAStateOfSpecificAdministrator(int stato,int id){
        return "SELECT COUNT (id) AS numRichieste FROM Assistenza WHERE stato = "+stato+" AND idAmministratore="+id+";";
    }
}
