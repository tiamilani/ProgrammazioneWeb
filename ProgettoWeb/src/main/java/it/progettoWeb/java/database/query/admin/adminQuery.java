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
     * Metodo che ritorna la stringa che rappresenta la query per contare il numero di richieste di assistenza in sospeso
     * @return La stringa che rappresenta la query
     */
    public static String numberSuspendedRequestOfAssistance(){
        return "SELECT COUNT (id) FROM Assistenza WHERE stato = 0;";
    }
    
    /**
     * @author Damiano
     * Metodo che ritorna la stringa che rappresenta la query per contare il numero di richieste di assistenza in completate
     * @return La stringa che rappresenta la query
     */
    public static String numberCompletedRequestOfAssistance(){
        return "SELECT COUNT (id) FROM Assistenza WHERE stato = 1;";
    }
}
