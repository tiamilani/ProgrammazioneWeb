/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.query.generics;

import java.security.Key;
import it.progettoWeb.java.database.Model.indirizzo.ModelloIndirizzo;

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
    
    public static String howManyReviewsO(String idO){
        return "SELECT COUNT(*) AS counter FROM RecensioneOggetto "
                + "WHERE RecensioneOggetto.idOggetto ='"+idO+"';";
    }

    public static String selectRandomObjectsAndImages(int limit)
    {
        return "SELECT Oggetto.*, imageOggetto.* FROM Oggetto JOIN imageOggetto ON Oggetto.id=imageOggetto.idO GROUP BY Oggetto.id,imageOggetto.src ORDER BY RAND() LIMIT " + limit + ";";
    }

    public static String selectObjectsImageSelledByStoreID(int idNegozio)
    {
        return "SELECT Oggetto.*, imageOggetto.* FROM Oggetto JOIN imageOggetto ON Oggetto.id=imageOggetto.idO WHERE Oggetto.idNegozio = " + idNegozio + " GROUP BY Oggetto.id ORDER BY RAND();";
    }

    public static String selectAddressByIdAddress(int idAddress)
    {
        return "SELECT * FROM Indirizzo WHERE idI = " + idAddress + ";";
    }


    /**
     * @author andreafadi
     * Inserire una nuova associazione Recensione-Immagine
     * @param idR Identificativo della recensione da collegare
     * @param src Identificativo del percorso dell'immagine da collegare
     * @return String: conferma avvenuta operazione
     */
    public static String addImageReviewSet(int idR, String src)
    {
        return "INSERT INTO imageRecensione (src,idR) values ('" + src + "'," + idR + ");";
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
     * @param utenteType Indica il tipo di utente (0=normale, 1=venditore, 2=amministratore)
     * @return String: elenco utenti
     */
    public static String selectAllUsersByType(int utenteType)
    {
        return "SELECT * FROM Utente WHERE UtenteType = " + utenteType + ";";
    }

    public static String selectUserByEmail(String email)
    {
        return "SELECT * FROM Utente WHERE mail = '" + email + "';";
    }

    /**
     * @author fbrug
     * Selezionare utente in base a mail & password
     * @param mail Email dell'utente da ricercare
     * @param password Password dell'utente da ricercare
     * @return String: un utente
     */
    public static String selectUserByEmailAndPassword(String mail, String password)
    {
        return "SELECT * FROM Utente WHERE mail = '" + mail + "' AND password = '" + password + "';";
    }

    /**
     * @author fbrug
     * Selezionare tutti gli utenti con un certo nome
     * @param nome Nome dell'utente da ricercare
     * @return String: elenco utenti
     */
    public static String selectAllUsersByName(String nome)
    {
        return "SELECT * FROM Utente WHERE nome = '" + nome + "';";
    }

    /**
     * @author fbrug
     * Selezionare utente in base a nome & cognome
     * @param nome Nome dell'utente a ricercare
     * @param cognome Cognome dell'utente da ricercare
     * @return String: elenco utenti
     */
    public static String selectAllUsersByNameAndSurname(String nome, String cognome)
    {
        return "SELECT * FROM Utente WHERE nome = '" + nome + "' AND cognome = '" + cognome + "';";
    }

    /**
     * @author fbrug
     * Ottenere i dati di un utente in base all'ID utente
     * @param idUtente Intero rappresentante l'ID dell'utente da ricercare
     * @return String: un utente
     */
    public static String selectUserByID(int idUtente)
    {
        return "SELECT * FROM Utente WHERE id = " + idUtente + ";";
    }

    /**
     * @author fbrug
     * Ottenere i dati di un negozio in base all'ID negozio
     * @param idStore Intero rappresentante l'ID del negozio
     * @return String: Informazioni sul negozio
     */
    public static String selectStoreById(int idStore)
    {
        return "SELECT * FROM Negozio WHERE id = " + idStore + ";";
    }

    /**
     * @author fbrug
     * Ottenere indirizzi di un utente avendo l'ID utente
     * @param idUtente Intero rappresentante l'ID dell'utente
     * @return String: elenco indirizzi
     */
    public static String selectAddressByUserID(int idUtente)
    {
        return "SELECT Indirizzo.* "
               + "FROM Indirizzo INNER JOIN IndirizzoUtente ON "
               + "(Indirizzo.idI = IndirizzoUtente.idI AND IndirizzoUtente.idU = " + idUtente + ");";
    }

    public static String selectAddressLatLng(double lat, double lng)
    {
        return "SELECT * "
               + "FROM Indirizzo "
               + "WHERE latitudine="+lat+" and longitudine="+lng+";";
    }

    /**
     * @author fbrug
     * Ottenere i dati di un utente e l'indirizzo avendo mail e password
     * @param mail Email dell'utente da ricercare
     * @param password Password dell'utente da ricercare
     * @return String: elenco utenti & indirizzi
     */
    public static String selectUserAndAddressByEmailAndPassword(String mail, String password)
    {
        return "SELECT Utente.*, Indirizzo.* "
               + "FROM Utente INNER JOIN IndirizzoUtente INNER JOIN Indirizzo "
               + "ON (Utente.mail = '" + mail + "' AND Utente.password = '" + password + "' AND Indirizzo.idI = IndirizzoUtente.idI AND IndirizzoUtente.idU = Utente.id);";
    }

    /**
     * @author fbrug
     * Ottenere i dati di un utente e l'indirizzo avendo l'ID utente
     * @param idUtente Intero rappresentante l'ID dell'utente da ricercare
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
     * @param idUtente Intero rappresentante l'ID dell'utente da ricercare
     * @return String: elenco immagini utente
     */
    public static String selectUserImageByUserID(int idUtente)
    {
        return "SELECT src FROM imageUtente WHERE idU = " + idUtente + ";";
    }

    /**
     * @author andrea
     * Ottenere coppia Negozio, Indirizzo
     * @param idUtente
     * @return Negozio.*, Indirizzo.*
     */
    public static String selectStoreAndAddressByUser(int idUtente)
    {
        return "SELECT Negozio.*, Indirizzo.* FROM Negozio JOIN Indirizzo ON Negozio.idI=Indirizzo.idI WHERE Negozio.idVenditore=" + idUtente + ";";
    }

    /**
     * @author andrea
     * Ottenere tris Negozio, Indirizzo, Immagine
     * @param idUtente
     * @return Negozio.*, Indirizzo.*, ImmagineNegozio.*
     */
    public static String selectStoreAndAddressImageByUser(int idUtente)
    {
        return "SELECT Negozio.*, Indirizzo.*, imageNegozio.* FROM Negozio JOIN Indirizzo ON Negozio.idI=Indirizzo.idI JOIN imageNegozio ON Negozio.id=imageNegozio.idN WHERE Negozio.idVenditore=" + idUtente + " GROUP BY Negozio.id;";
    }

    /**
     * @author andrea
     * Ottenere tris Negozio, Indirizzo, Immagine
     * @param idNegozio Id del negozio di cui si vogliono le informazioni
     * @return Negozio.*, Indirizzo.*, ImmagineNegozio.*
     */
    public static String selectStoreAddressImageByStoreID(int idNegozio)
    {
        //Update, rimosso group by Negozio.id, visto che la ricerca restituirà un risultato univoco dato dall'id del negozio non serve effettuare il group by che altrimenti dava errore sql
        return "SELECT Negozio.*, Indirizzo.*, imageNegozio.* FROM Negozio JOIN Indirizzo ON Negozio.idI=Indirizzo.idI JOIN imageNegozio ON Negozio.id=imageNegozio.idN WHERE Negozio.id=" + idNegozio + ";";
    }

    /**
     * @author fbrug
     * Ottenere i dati di un utente, l'indirizzo ed l'immagine a cui fa riferimento
     * @param mail Email dell'utente da ricercare
     * @param password Password dell'utente da ricercare
     * @return String: elenco utenti & indirizzi & immagini
     */
    public static String selectUserAndAddressAndImageByEmailAndPassword(String mail, String password)
    {
        return "SELECT Utente.*, Indirizzo.*, imageUtente.src "
               + "FROM Utente INNER JOIN IndirizzoUtente INNER JOIN Indirizzo ON (Utente.mail = '" + mail + "' AND Utente.password = '" + password + "' AND Indirizzo.idI = IndirizzoUtente.idI AND IndirizzoUtente.idU = Utente.id) "
               + "LEFT JOIN imageUtente ON (imageUtente.idU = Utente.id);";
    }

    /**
     * @author fbrug
     * Ottenere gli utenti amministratori ordinati per numero di richieste (decrescente)
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
     * Ottenere gli utenti amministratori ordinati per numero di richieste in corso (decrescente)
     * @return String: elenco utenti amministratori
     */
    public static String selectAdministratorByNumerOfPendingRequests()
    {
        return "SELECT DISTINCT COUNT(A2.idAmministratore) as contatore, A1.idAmministratore as id "
               + "FROM Assistenza A1 "
               + "LEFT JOIN Assistenza A2 ON (A1.idAmministratore = A2.idAmministratore) "
               + "WHERE A1.stato=0 "
               + "GROUP BY A1.id "
               + "ORDER BY contatore DESC;";
    }

    /**
     * @author fbrug
     * Modificare l'immagine del profilo di un utente con un determinato ID utente
     * @param idUtente Intero rappresentante l'ID dell'utente da ricercare
     * @param imagePath Rappresenta il path dell'immagine
     * @return String: conferma avvenuta operazione
     */
    public static String updateUserImageByUserID(int idUtente, String imagePath)
    {
        return "UPDATE imageUtente SET imageUtente.src = '" + imagePath + "' WHERE imageUtente.idU = " + idUtente + ";";
    }

    /**
     * @author fbrug
     * Modificare mail utente
     * @param idUtente Intero rappresentante l'ID dell'utente da ricercare
     * @param mail Email dell'utente da ricercare
     * @return String: conferma avvenuta operazione
     */
    public static String updateUserEmailByUserID(int idUtente, String mail)
    {
        return "UPDATE Utente SET Utente.mail = '" + mail + "' WHERE Utente.id = " + idUtente + ";";
    }

    /**
     * @author fbrug
     * Modificare password utente
     * @param idUtente Intero rappresentante l'ID dell'utente da ricercare
     * @param password Password dell'utente da ricercare
     * @return String: conferma avvenuta operazione
     */
    public static String updateUserPasswordByUserID(int idUtente, String password)
    {
        return "UPDATE Utente SET Utente.password = '" + password + "' WHERE Utente.id = " + idUtente + ";";
    }

    /**
     * @author fbrug
     * Modificare indirizzo utente
     * (Suppongo l'utente abbia scelto un indirizzo dalla lista dei propri indirizzi quindi avrò l'ID dell'indirizzo)
     * @param addrID Intero rappresentante l'ID dell'indirizzo da modificare
     * @param citta Citta' dell'indirizzo
     * @param interno Intero rappresentante l'interno dell'edificio
     * @param latitudine Latitudine dell'indirizzo
     * @param longitudine Longitudine dell'indirizzo
     * @param nCivico Intero rappresentante il numero civico
     * @param provincia Provincia dell'indirizzo
     * @param regione Regione dell'indirizzo
     * @param stato Stato dell'indirizzo
     * @param via Via dell'indirizzo
     * @return String: conferma avvenuta operazione
     */
    public static String updateUserAddressByAddressID(int addrID, String stato, String regione, String provincia, String citta, String via,
                                                      int nCivico, int interno, double latitudine, double longitudine)
    {
        return "UPDATE Indirizzo"
               + " SET Indirizzo.citta = '" + citta
               + "', Indirizzo.interno = " + interno
               + ", Indirizzo.latitudine = " + latitudine
               + ", Indirizzo.longitudine = " + longitudine
               + ", Indirizzo.nCivico = " + nCivico
               + ", Indirizzo.provincia = '" + provincia
               + "', Indirizzo.regione = '" + regione
               + "', Indirizzo.stato = '" + stato
               + "', Indirizzo.via = '" + via
               + "' WHERE Indirizzo.idI = " + addrID + ";";
    }

    /**
     * @author fbrug
     * Rimuovere l'immagine del profilo di un determinato utente
     * @param idUtente Intero rappresentante l'ID dell'utente da ricercare
     * @return String: conferma avvenuta operazione
     */
    public static String deleteUserImageByUserID(int idUtente)
    {
        return "DELETE FROM imageUtente WHERE imageUtente.idU = " + idUtente + ";";
    }

    /**
     * @author fbrug    //---CONTROLLA---
     * Aggiungere una immagine del profilo di un utente
     * @param idUtente Intero rappresentante l'ID dell'utente da ricercare
     * @param imagePath Rappresenta il path dell'immagine
     * @return String: conferma avvenuta operazione
     */
    public static String insertUserImage(int idUtente, String imagePath)
    {
        return "INSERT INTO imageUtente (src,idU) "
               + "SELECT '" + imagePath + "', Utente.id FROM Utente WHERE Utente.id = " + idUtente + ";";
    }

    /**
     * Ottenere informazioni sulle recensioni relative ad un oggetto
     * @param idO Stringa rappresentante l'ID dell'oggetto
     * @return String: Informazioni sulle recensioni dell'oggetto ricercato
     */
    public static String selectReviewsObjects(String idO) {
        return "SELECT * FROM RecensioneOggetto WHERE idOggetto = '" + idO + "';";
    }

    /**
     * @author Damiano
     * @param token: il token inviato al'utente
     * @return la query che permette di ottenere la chiave di cifratura del token
     */
    public static String getPasswordAuthenticationKey(String token){
        return "SELECT chiave FROM RecuperoPassword WHERE token = '" + token + "';";
    }

    /**
     * @author Damiano
     * @param token: il token inviato al'utente
     * @param key: la chiave utilizzata per cifrare il token assegnato all'utente
     * @return la query che permette di ottenere la chiave di cifratura del token
     */
    public static String setPasswordAuthenticationKey(String token, String key){
        return "INSERT INTO RecuperoPassword(token, chiave) VALUES ('" + token + "', '" + key + "');";
    }

    /**
     * Ottenere una specifica recensioni dati i parametri identificativi
     * @param idO Una stringa che rappresenta l'oggetto della recensione da ricercare
     * @param idU Un intero che rappresenta l'utente della recensione da ricercare
     * @param testo Una stringa che rappresenta il testo della recensione da ricercare
     * @param valutazione Un intero che rappresenta la valutazione della recensione da ricercare
     * @return ModelloRecensioneOggetto
     */
    public static String selectReviewsByDataO(String idO, int idUtente, String testo, int valutazione) {
        return "SELECT * FROM RecensioneOggetto WHERE idOggetto = '" + idO + "' AND "
                + "idUtente=" + idUtente + " AND testo='" + testo + "' AND valutazione=" + valutazione + ";";
    }

    /**
     * Ottenere una specifica recensioni dati i parametri identificativi
     * @param idN Un intero che rappresenta il negozio della recensione da ricercare
     * @param idU Un intero che rappresenta l'utente della recensione da ricercare
     * @param testo Una stringa che rappresenta il testo della recensione da ricercare
     * @param valutazione Un intero che rappresenta la valutazione della recensione da ricercare
     * @return ModelloRecensioneOggetto
     */
    public static String selectReviewsByDataN(int idN, int idUtente, String testo, int valutazione) {
        return "SELECT * FROM RecensioneNegozio WHERE idNegozio = '" + idN + "' AND "
                + "idUtente=" + idUtente + " AND testo='" + testo + "' AND valutazione=" + valutazione + ";";
    }

    /**
     * Ottenere una specifica recensioni dati i parametri identificativi
     * @param idV Un intero che rappresenta il venditore della recensione da ricercare
     * @param idU Un intero che rappresenta l'utente della recensione da ricercare
     * @param testo Una stringa che rappresenta il testo della recensione da ricercare
     * @param valutazione Un intero che rappresenta la valutazione della recensione da ricercare
     * @return ModelloRecensioneOggetto
     */
    public static String selectReviewsByDataV(int idV, int idUtente, String testo, int valutazione) {
        return "SELECT * FROM RecensioneVenditore WHERE idVenditore = '" + idV + "' AND "
                + "idUtente=" + idUtente + " AND testo='" + testo + "' AND valutazione=" + valutazione + ";";
    }

    public static String selectNumberOfAddress(ModelloIndirizzo indirizzo, int idU) {
        return "select COUNT(idI) AS numIndirizzi "
                + "from Indirizzo NATURAL JOIN IndirizzoUtente "
                + "WHERE stato='"+indirizzo.getStato()+"' AND "
                + "regione='"+indirizzo.getRegione()+"' AND "
                + "provincia='"+indirizzo.getProvincia()+"' AND "
                + "citta='"+indirizzo.getCitta()+"' AND "
                + "via='"+indirizzo.getVia()+"' AND "
                + "nCivico="+indirizzo.getnCivico()+" AND "
                + "idU="+idU+";";
    }
}
