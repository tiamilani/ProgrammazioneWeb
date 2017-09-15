/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.query.generics;
/**
 *
 * @author mattia
 */
public class genericsQuery {
    public static String hello() {
        return "Hello from" + genericsQuery.class.toString();
    }
    
    
    /*--- LAST UPDATE -> 2017-09-14 ---*/
    
    /**
     * @author Brugix
     * Selezionare tutti gli utenti in base al loro tipo: 0=normale, 1=venditore, 2=amministratore
     * @param type
     * @return String: elenco utenti
     */
    public static String selectAllUsersByType(int type)
    {
        return "SELECT * FROM Utente WHERE UtenteType = " + type + ";";
    }
    
    /**
     * @author Brugix
     * Selezionare utente in base a email & password
     * @param email
     * @param password
     * @return String: elenco utenti
     */
    public static String selectUserByEmailAndPassword(String email, String password)
    {
        return "SELECT * FROM Utente WHERE mail = " + email + " AND password = " + password + ";";
    }
    
    /**
     * @author Brugix
     * Selezionare tutti gli utenti con un certo nome
     * @param name
     * @return String: elenco utenti
     */
    public static String selectAllUsersByName(String name)
    {
        return "SELECT * FROM Utente WHERE nome = " + name + ";";
    }
    
    /**
     * @author Brugix
     * Selezionare utente in base a nome & cognome
     * @param name
     * @param surname
     * @return String: elenco utenti
     */
    public static String selectAllUsersByNameAndSurname(String name, String surname)
    {
        return "SELECT * FROM Utente WHERE nome = " + name + " AND cognome = " + surname + ";";
    }
    
    /**
     * @author Brugix
     * Ottenere i dati di un utente in base all'ID utente
     * @param userId
     * @return String: elenco utenti
     */
    public static String selectUserByID(int userId)
    {
        return "SELECT * FROM Utente WHERE id = " + userId + ";";
    }
    
    /**
     * @author Brugix
     * Ottenere indirizzi di un utente avendo l'ID utente
     * @param userId
     * @return String: elenco indirizzi
     */
    public static String selectAddressByUserID(int userId)
    {
        return "SELECT Indirizzo.* "
               + "FROM Indirizzo INNER JOIN IndirizzoUtente ON "
               + "(Indirizzo.idI = IndirizzoUtente.idI AND IndirizzoUtente.idU = " + userId + ");";
    }
    
    /**
     * @author Brugix
     * Ottenere i dati di un utente e l'indirizzo avendo email e password
     * @param email
     * @param password
     * @return String: elenco utenti & indirizzi
     */
    public static String selectUserAndAddressByEmailAndPassword(String email, String password)
    {
        return "SELECT Utente.*, Indirizzo.* "
               + "FROM Utente INNER JOIN IndirizzoUtente INNER JOIN Indirizzo "
               + "ON (Utente.mail = " + email + " AND Utente.password = " + password + " AND Indirizzo.idI = IndirizzoUtente.idI AND IndirizzoUtente.idU = Utente.id);";
    }
    
    /**
     * @author Brugix
     * Ottenere i dati di un utente e l'indirizzo avendo l'ID utente
     * @param userId
     * @return String: elenco utenti & indirizzi
     */
    public static String selectUserAndAddressByUserID(int userId)
    {
        return "SELECT Utente.*, Indirizzo.* "
               + "FROM Utente INNER JOIN IndirizzoUtente INNER JOIN Indirizzo "
               + "ON (Utente.id = " + userId + " AND Indirizzo.idI = IndirizzoUtente.idI AND IndirizzoUtente.idU = Utente.id);";
    }
    
    /**
     * @author Brugix
     * Ottenere l'immagine di un utente
     * @param userId
     * @return String: elenco immagini utente
     */
    public static String selectUserImageByUserID(int userId)
    {
        return "SELECT src FROM imageUtente WHERE idU = " + userId + ";";
    }
    
    /**
     * @author Brugix
     * Ottenere i dati di un utente, l'indirizzo ed l'immagine a cui fa riferimento
     * @param email
     * @param password
     * @return String: elenco utenti & indirizzi & immagini
     */
    public static String selectUserAndAddressAndImageByEmailAndPassword(String email, String password)
    {
        return "SELECT Utente.*, Indirizzo.*, imageUtente.src "
               + "FROM Utente INNER JOIN IndirizzoUtente INNER JOIN Indirizzo ON (Utente.mail = " + email + " AND Utente.password = " + password + " AND Indirizzo.idI = IndirizzoUtente.idI AND IndirizzoUtente.idU = Utente.id) "
               + "LEFT JOIN imageUtente ON (imageUtente.idU = Utente.id);";
    }
    
    /**
     * @author Brugix
     * Ottenere gli utenti amministratori ordinati per numero di richieste
     * @return String: elenco utenti amministratori
     */
    public static String selectAdministratorByNumerOfRequests()
    {
        return "SELECT DISTINCT COUNT(A2.idAmministratore) as contatore, A1.idAmministratore as id "
               + "FROM Assistenza A1 "
               + "LEFT JOIN Assistenza A2 ON (A1.idAmministratore = A2.idAmministratore) "
               + "GROUP BY A1.id "
               + "ORDER BY contatore DESC;";
    }
    
    /**
     * @author Brugix
     * Modificare l'immagine del profilo di un utente con un determinato ID utente
     * @param userId
     * @param imagePath
     * @return String: conferma avvenuta operazione
     */
    public static String updateUserImageByUserID(int userId, String imagePath)
    {
        return "UPDATE imageUtente SET imageUtente.src = " + imagePath + " WHERE imageUtente.idU = " + userId + ";";
    }
    
    /**
     * @author Brugix
     * Modificare email utente
     * @param userId
     * @param email
     * @return String: conferma avvenuta operazione
     */
    public static String updateUserEmailByUserID(int userId, String email)
    {
        return "UPDATE Utente SET Utente.mail = " + email + " WHERE Utente.id = " + userId + ";";
    }
    
    /**
     * @author Brugix
     * Modificare password utente
     * @param userId
     * @param password
     * @return String: conferma avvenuta operazione
     */
    public static String updateUserPasswordByUserID(int userId, String password)
    {
        return "UPDATE Utente SET Utente.password = " + password + " WHERE Utente.id = " + userId + ";";
    }
    
    /**
     * @author Brugix
     * Modificare indirizzo utente
     * (Suppongo l'utente abbia scelto un indirizzo dalla lista dei propri indirizzi quindi avrò l'ID dell'indirizzo)
     * @param addrId
     * @param city
     * @param interno
     * @param latitude
     * @param longitude
     * @param civicNumber
     * @param province
     * @param region
     * @param country
     * @param via
     * @return String: conferma avvenuta operazione
     */
    public static String updateUserAddressByAddressID(int addrId, String city, String interno, String latitude, String longitude, String civicNumber, String province, String region, String country, String via)
    {
        return "UPDATE Indirizzo"
               + " SET Indirizzo.citta = " + city
               + " Indirizzo.interno = " + interno
               + " Indirizzo.latitudine = " + latitude
               + " Indirizzo.longitudine = " + longitude
               + " Indirizzo.nCivico = " + civicNumber
               + " Indirizzo.provincia = " + province
               + " Indirizzo.regione = " + region
               + " Indirizzo.stato = " + country
               + " Indirizzo.via = " + via
               + " WHERE Indirizzo.idI = " + addrId + ";";
    }
    
    /**
     * @author Brugix
     * Rimuovere l'immagine del profilo di un determinato utente
     * @param userId
     * @return String: conferma avvenuta operazione
     */
    public static String deleteUserImageByUserID(int userId)
    {
        return "DELETE FROM imageUtente WHERE imageUtente.idU = " + userId + ";";
    }
    
    /**
     * @author Brugix
     * Aggiungere una immagine del profilo di un utente
     * @param userId
     * @param imagePath
     * @return String: conferma avvenuta operazione
     */
    public static String insertUserImage(int userId, String imagePath)
    {
        return "INSERT INTO image (src,idU) "
               + "SELECT " + imagePath + ", Utente.id FROM Utente WHERE Utente.id = " + userId + ";";
    }
}
