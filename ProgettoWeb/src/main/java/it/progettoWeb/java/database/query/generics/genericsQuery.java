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
    
    
    /*--- LAST UPDATE -> 2017-09-17 ---*/
    
    /**
     * @author Mattia
     * @return String: elenco categorie
     */
    public static String selectAllCategory()
    {
        return "SELECT * FROM Categoria;";
    }
    
    public static String selectRandomObjects(int limit)
    {
        return "SELECT * FROM Oggetto ORDER BY RAND() LIMIT " + limit + ";";
    }
    
    public static String selectAddressByIdAddress(int idAddress)
    {
        return "SELECT * FROM Indirizzo WHERE idI = " + idAddress + ";";
    }
    
    /**
     * @author Mattia
     * @return String: categoria singola
     */
    public static String selectCategoryById(int id)
    {
        return "SELECT * FROM Categoria WHERE id="+id+";";
    }
    
    /**
     * @author fbrug
     * Selezionare tutti gli utenti in base al loro tipo: 0=normale, 1=venditore, 2=amministratore
     * @param utenteType
     * @return String: elenco utenti
     */
    public static String selectAllUsersByType(int utenteType)
    {
        return "SELECT * FROM Utente WHERE UtenteType = " + utenteType + ";";
    }
    
    /**
     * @author fbrug
     * Selezionare utente in base a mail & password
     * @param mail
     * @param password
     * @return String: un utente
     */
    public static String selectUserByEmailAndPassword(String mail, String password)
    {
        return "SELECT * FROM Utente WHERE mail = " + mail + " AND password = " + password + ";";
    }
    
    /**
     * @author fbrug
     * Selezionare tutti gli utenti con un certo nome
     * @param nome
     * @return String: elenco utenti
     */
    public static String selectAllUsersByName(String nome)
    {
        return "SELECT * FROM Utente WHERE nome = " + nome + ";";
    }
    
    /**
     * @author fbrug
     * Selezionare utente in base a nome & cognome
     * @param nome
     * @param cognome
     * @return String: elenco utenti
     */
    public static String selectAllUsersByNameAndSurname(String nome, String cognome)
    {
        return "SELECT * FROM Utente WHERE nome = " + nome + " AND cognome = " + cognome + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere i dati di un utente in base all'ID utente
     * @param idUtente
     * @return String: un utente
     */
    public static String selectUserByID(int idUtente)
    {
        return "SELECT * FROM Utente WHERE id = " + idUtente + ";";
    }
    
    public static String selectStoreById(int idStore)
    {
        return "SELECT * FROM Negozio WHERE id = " + idStore + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere indirizzi di un utente avendo l'ID utente
     * @param idUtente
     * @return String: elenco indirizzi
     */
    public static String selectAddressByUserID(int idUtente)
    {
        return "SELECT Indirizzo.* "
               + "FROM Indirizzo INNER JOIN IndirizzoUtente ON "
               + "(Indirizzo.idI = IndirizzoUtente.idI AND IndirizzoUtente.idU = " + idUtente + ");";
    }
    
    /**
     * @author fbrug
     * Ottenere i dati di un utente e l'indirizzo avendo mail e password
     * @param mail
     * @param password
     * @return String: elenco utenti & indirizzi
     */
    public static String selectUserAndAddressByEmailAndPassword(String mail, String password)
    {
        return "SELECT Utente.*, Indirizzo.* "
               + "FROM Utente INNER JOIN IndirizzoUtente INNER JOIN Indirizzo "
               + "ON (Utente.mail = " + mail + " AND Utente.password = " + password + " AND Indirizzo.idI = IndirizzoUtente.idI AND IndirizzoUtente.idU = Utente.id);";
    }
    
    /**
     * @author fbrug
     * Ottenere i dati di un utente e l'indirizzo avendo l'ID utente
     * @param idUtente
     * @return String: elenco utenti & indirizzi
     */
    public static String selectUserAndAddressByUserID(int idUtente)
    {
        return "SELECT Utente.*, Indirizzo.* "
               + "FROM Utente INNER JOIN IndirizzoUtente INNER JOIN Indirizzo "
               + "ON (Utente.id = " + idUtente + " AND Indirizzo.idI = IndirizzoUtente.idI AND IndirizzoUtente.idU = Utente.id);";
    }
    
    /**
     * @author fbrug
     * Ottenere l'immagine di un utente
     * @param idUtente
     * @return String: elenco immagini utente
     */
    public static String selectUserImageByUserID(int idUtente)
    {
        return "SELECT src FROM imageUtente WHERE idU = " + idUtente + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere i dati di un utente, l'indirizzo ed l'immagine a cui fa riferimento
     * @param mail
     * @param password
     * @return String: elenco utenti & indirizzi & immagini
     */
    public static String selectUserAndAddressAndImageByEmailAndPassword(String mail, String password)
    {
        return "SELECT Utente.*, Indirizzo.*, imageUtente.src "
               + "FROM Utente INNER JOIN IndirizzoUtente INNER JOIN Indirizzo ON (Utente.mail = " + mail + " AND Utente.password = " + password + " AND Indirizzo.idI = IndirizzoUtente.idI AND IndirizzoUtente.idU = Utente.id) "
               + "LEFT JOIN imageUtente ON (imageUtente.idU = Utente.id);";
    }
    
    /**
     * @author fbrug
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
     * @author fbrug
     * Modificare l'immagine del profilo di un utente con un determinato ID utente
     * @param idUtente
     * @param imagePath
     * @return String: conferma avvenuta operazione
     */
    public static String updateUserImageByUserID(int idUtente, String imagePath)
    {
        return "UPDATE imageUtente SET imageUtente.src = " + imagePath + " WHERE imageUtente.idU = " + idUtente + ";";
    }
    
    /**
     * @author fbrug
     * Modificare mail utente
     * @param idUtente
     * @param mail
     * @return String: conferma avvenuta operazione
     */
    public static String updateUserEmailByUserID(int idUtente, String mail)
    {
        return "UPDATE Utente SET Utente.mail = " + mail + " WHERE Utente.id = " + idUtente + ";";
    }
    
    /**
     * @author fbrug
     * Modificare password utente
     * @param idUtente
     * @param password
     * @return String: conferma avvenuta operazione
     */
    public static String updateUserPasswordByUserID(int idUtente, String password)
    {
        return "UPDATE Utente SET Utente.password = " + password + " WHERE Utente.id = " + idUtente + ";";
    }
    
    /**
     * @author fbrug
     * Modificare indirizzo utente
     * (Suppongo l'utente abbia scelto un indirizzo dalla lista dei propri indirizzi quindi avrò l'ID dell'indirizzo)
     * @param addrID
     * @param citta
     * @param interno
     * @param latitudine
     * @param longitudine
     * @param nCivico
     * @param provincia
     * @param regione
     * @param stato
     * @param via
     * @return String: conferma avvenuta operazione
     */
    public static String updateUserAddressByAddressID(int addrID, String stato, String regione, String provincia, String citta, String via,
                                                      int nCivico, int interno, double latitudine, double longitudine)
    {
        return "UPDATE Indirizzo"
               + " SET Indirizzo.citta = " + citta
               + " Indirizzo.interno = " + interno
               + " Indirizzo.latitudine = " + latitudine
               + " Indirizzo.longitudine = " + longitudine
               + " Indirizzo.nCivico = " + nCivico
               + " Indirizzo.provincia = " + provincia
               + " Indirizzo.regione = " + regione
               + " Indirizzo.stato = " + stato
               + " Indirizzo.via = " + via
               + " WHERE Indirizzo.idI = " + addrID + ";";
    }
    
    /**
     * @author fbrug
     * Rimuovere l'immagine del profilo di un determinato utente
     * @param idUtente
     * @return String: conferma avvenuta operazione
     */
    public static String deleteUserImageByUserID(int idUtente)
    {
        return "DELETE FROM imageUtente WHERE imageUtente.idU = " + idUtente + ";";
    }
    
    /**
     * @author fbrug
     * Aggiungere una immagine del profilo di un utente
     * @param idUtente
     * @param imagePath
     * @return String: conferma avvenuta operazione
     */
    public static String insertUserImage(int idUtente, String imagePath)
    {
        return "INSERT INTO imageUtente (src,idU) "
               + "SELECT " + imagePath + ", Utente.id FROM Utente WHERE Utente.id = " + idUtente + ";";
    }

    public static String selectReviewsObjects(String idO) {
        return "SELECT * FROM RecensioneOggetto WHERE idOggetto = '" + idO + "';";
    }
}
