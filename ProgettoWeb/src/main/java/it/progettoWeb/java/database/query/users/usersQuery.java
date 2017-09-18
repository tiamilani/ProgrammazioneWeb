/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.query.users;
    
/**
 *
 * @author mattia
 */
public class usersQuery {
    public static String hello() {
        return "Hello from " + usersQuery.class.toString();
    }
    
    // QUERY UTENTI
    
    //Slezionare tutti gli utenti senza distinzioni
    public static String selectAllUsers(){
        return "SELECT * FROM Utente";
    }
    
    //Seleziono un utente dall'id
    public static String selectUserById(int id){
        return "SELECT * FROM Utente WHERE id='"+id+"'";
    }
    
    
    // ottenere la lista dei negozi di un venditore specificando l'id del venditore
    public static String listaNegoziVenditore(String idV){
        return "SELECT * FROM Negozio WHERE idVenditore = '"+idV+"';";
    }
    // ottenere la lista dei negozi di un venditore avendo nome e cognome del venditore

    public static String listaNegoziVenditore(String nome,String cognome) {
        return "SELECT Negozio.* FROM Negozio INNER JOIN Utente ON (Utente.nome = '"+nome+"' AND Utente.cognome = '"+cognome+"' AND Utente.UtenteType = 1 AND Negozio.idVenditore = Utente.id);";
    }

    // ottenre la lista dei negozi di un venditore avendo l'id e l'immagine del negozio

    public static String listaNegoziVenditorePlusImage(String idV) {
        return "SELECT Negozio.*, imageNegozio.src FROM Negozio INNER JOIN imageNegozio ON (Negozio.idVenditore = '"+idV+"' AND Negozio.id = imageNegozio.idN);";
    }

    // ottenere la lista dei negozi di un venditore avendo nome e cognome e la prima immagine del negozio

    public static String listaNegoziVenditorePlusImage(String nome,String cognome) {
        return "SELECT Negozio.*, imageNegozio.src FROM Negozio INNER JOIN Utente ON (Utente.nome = '"+nome+"' AND Utente.cognome = '"+cognome+"' AND Utente.UtenteType = 1 AND Negozio.idVenditore = Utente.id)LEFT JOIN imageNegozio ON (Negozio.id = imageNegozio.idN);";
    }
    
    public static String inserisciUtente(String nome,String cognome,String mail,String password,String avatar,double valutazione,int UtenteType,boolean emailConfermata){
        return "insert into Utente(nome, cognome, mail, password, avatar, valutazione, UtenteType, emailConfermata) values ("+
                "'"+nome+"',"+
                "'"+cognome+"',"+
                "'"+mail+"',"+
                "'"+password+"',"+
                ""+avatar+","+
                ""+valutazione+","+
                ""+UtenteType+","+
                ""+emailConfermata+");";
    }
    
    public static String eliminaUtente(int id){
        return "DELETE FROM Utente WHERE id="+id+";";
    }
    
    public static String updateUtente(int id,String nome,String cognome,String mail,String password,String avatar,double valutazione,int UtenteType,boolean emailConfermata){
        return "update Utente set "+
                "nome='"+nome+"',"+
                "cognome='"+cognome+"',"+
                "mail='"+mail+"',"+
                "password='"+password+"',"+
                "avatar="+avatar+","+
                "valutazione="+valutazione+","+
                "UtenteType="+UtenteType+","+
                "emailConfermata="+emailConfermata+""
                + "WHERE id="+id+"";
    }
}
