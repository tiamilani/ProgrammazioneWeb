/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.query.users;

import it.progettoWeb.java.database.Model.Ordine.ModelloOrdine;
import it.progettoWeb.java.database.Model.Assistenza.ModelloAssistenza;
import java.sql.Date;

/**
 *
 * @author Andrea
 * TO DO: Cambia lo stato di un ordine da nella lista dei desideri al carrello (Se c'è già lo stesso oggetto anche nel carrello semplicmente ne aumento la quantità)
 */
public class usersQuery {
    public static String hello() {
        return "Hello from " + usersQuery.class.toString();
    }

    // OTHER QUERY
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
                "'"+avatar+"',"+
                ""+valutazione+","+
                ""+UtenteType+","+
                ""+emailConfermata+");";
    }
    
    public static String updateUserStars(int idUtente, double value)
    {
        return "UPDATE Utente SET valutazione=" + value + " WHERE id=" + idUtente + ";";
    }

    public static String eliminaUtente(int id){
        return "DELETE FROM Utente WHERE id="+id+";";
    }

    public static String updateUtente(int id,String nome,String cognome,String mail,String password,String avatar,double valutazione,int UtenteType,boolean emailConfermata){
        return "update utente set "+
                "nome='"+nome+"',"+
                "cognome='"+cognome+"',"+
                "mail='"+mail+"',"+
                "password='"+password+"',"+
                "avatar='"+avatar+"',"+
                "valutazione="+valutazione+","+
                "UtenteType="+UtenteType+","+
                "emailConfermata="+emailConfermata+""
                + " WHERE id="+id+";";
    }

    // QUERY UTENTE
    /**
     * @author Andrea
     * Ottenere la lista degli ordini
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di ordini
     */
    public static String selectOrders(int idU){
        return "SELECT * FROM Ordine WHERE Ordine.idUtente ="+idU+" ORDER BY Ordine.dataOrdine DESC;";
    }

    /**
     * @author Andrea
     * Ottenere la lista degli ordini con richieste specifiche per l'ordine
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param orderStatus Un intero che rappresenta l'identificativo dello stato dell'ordine
     * @return String: lista di ordini
     */
    public static String selectOrdersComplete(int idU, int orderStatus){
        return "SELECT * FROM ordine WHERE ordine.idUtente = "+idU+" AND ordine.stato = "+orderStatus+" ORDER BY ordine.dataOrdine DESC;";
    }

    /**
     * @author Andrea
     * Ottenere la lista dei negozi da cui ho acquistato
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di negozi
     */
    public static String selectStoresUsed(int idU){
        return "SELECT Negozio.* FROM Negozio INNER JOIN Ordine ON "
                + "(Ordine.idUtente ="+idU+" AND Negozio.id = Ordine.idNegozio "
                + "AND Ordine.stato <> 0 AND Ordine.stato <> 5) GROUP BY Negozio.id;";
    }

    /**
     * @author Andrea
     * Ottenere la lista dei negozi da cui ho acquistato e la loro prima foto
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di negozi
     */
    public static String selectStoresUsedAndPhoto(int idU){
        return "SELECT Negozio.*, imageNegozio.src FROM Negozio INNER JOIN Ordine ON "
                + "(Ordine.idUtente ="+idU+" AND Negozio.id = Ordine.idNegozio "
                + "AND Ordine.stato <> 0 AND Ordine.stato <> 5) LEFT JOIN "
                + "imageNegozio ON (Negozio.id = imageNegozio.idN) GROUP BY Negozio.id;";
    }

    /**
     * @author Andrea
     * Ottenere la lista dei negozi da cui ho acquistato con i dati dell'oggetto acquistato, la prima foto del negozio e la prima dell'oggetto
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di negozi
     */
    public static String selectStoresUsedAndDataPhotoSPhotoO(int idU){
        return "SELECT Negozio.*, imageNegozio.src AS imgNeg, Oggetto.id AS idOgg, "
                + "Oggetto.nome AS nomeOgg, Oggetto.descrizione, imageOggetto.src AS "
                + "imgOgg FROM Negozio INNER JOIN Ordine ON (Ordine.idUtente ="+idU+" "
                + "AND Negozio.id = Ordine.idNegozio AND Ordine.stato <> 0 AND "
                + "Ordine.stato <> 5) LEFT JOIN imageNegozio ON (Negozio.id = "
                + "imageNegozio.idN) LEFT JOIN Oggetto ON (Ordine.idOggetto = "
                + "Oggetto.id) LEFT JOIN imageOggetto ON (Oggetto.id = "
                + "imageOggetto.idO) GROUP BY Oggetto.id;";
    }

    /**
     * @author Andrea
     * Ottenere il carrello (La lista degli ordini che sono nel carrello)
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di ordini
     */
    public static String selectOrdersCart(int idU){
        return "SELECT Ordine.*, Carrello.subtotale FROM Carrello, Ordine WHERE "
                + "Carrello.idUtente ="+idU+" AND Ordine.idOrdine = Carrello.idOrdine;";
    }

    /**
     * @author Andrea
     * Ottenere le proprie recensioni di oggetti
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di recensioni
     */
    public static String selectReviewsObjects(int idU){
        return "SELECT * FROM RecensioneOggetto WHERE idUtente ="+idU+";";
    }

    /**
     * @author Andrea
     * Ottenere le proprie recensioni di negozi
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di recensioni
     */
    public static String selectReviewsStores(int idU){
        return "SELECT * FROM RecensioneNegozio WHERE idUtente ="+idU+";";
    }

    /**
     * @author Andrea
     * Ottenere le proprie recensioni di venditori
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di recensioni
     */
    public static String selectReviewsSellers(int idU){
        return "SELECT * FROM RecensioneVenditore WHERE idUtente ="+idU+";";
    }

    /**
     * @author Andrea
     * Ottenere le risposte alle proprie recensioni di oggetti
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di risposte alle recensioni
     */
    public static String selectAnswerReviewsObjects(int idU){
        return "SELECT RispostaOggetto.* FROM RispostaOggetto, RecensioneOggetto "
                + "WHERE RecensioneOggetto.idUtente ="+idU+" AND "
                + "RispostaOggetto.idRecensione = RecensioneOggetto.id;";
    }

    /**
     * @author Andrea
     * Ottenere le risposte alle proprie recensioni di negozi
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di risposte alle recensioni
     */
    public static String selectAnswerReviewsStores(int idU){
        return "SELECT RispostaNegozio.* FROM RispostaNegozio, RecensioneNegozio "
                + "WHERE RecensioneNegozio.idUtente ="+idU+" AND "
                + "RispostaNegozio.idRecensione = RecensioneNegozio.id;";
    }

    /**
     * @author Andrea
     * Ottenere le risposte alle proprie recensioni di venditori
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di risposte alle recensioni
     */
    public static String selectAnswerReviewsSellers(int idU){
        return "SELECT RispostaVenditore.* FROM RispostaVenditore, RecensioneVenditore "
                + "WHERE RecensioneVenditore.idUtente ="+idU+" AND "
                + "RispostaVenditore.idRecensione = RecensioneVenditore.id;";
    }

    /**
     * @author Andrea
     * Ottenere tutte le recensioni di un oggetto
     * @param idO Un intero che rappresenta l'identificativo dell'oggetto preso in considerazione
     * @return String: lista di recensioni
     */
    public static String selectAllReviewsObjects(int idO){
        return "SELECT * FROM RecensioneOggetto WHERE idOggetto ="+idO+";";
    }

    /**
     * @author Andrea
     * Ottenere tutte le recensioni di un negozio
     * @param idN Un intero che rappresenta l'identificativo del negozio preso in considerazione
     * @return String: lista di recensioni
     */
    public static String selectAllReviewsStores(int idN){
        return "SELECT * FROM RecensioneNegozio WHERE idNegozio ="+idN+";";
    }

    /**
     * @author Andrea
     * Ottenere tutte le recensioni di un venditore
     * @param idV Un intero che rappresenta l'identificativo del venditore preso in considerazione
     * @return String: lista di recensioni
     */
    public static String selectAllReviewsSellers(int idV){
        return "SELECT * FROM RecensioneVenditore WHERE idVenditore ="+idV+";";
    }

    /**
     * @author Andrea
     * Ottenere la lista delle richieste di assistenza fatte da uno specifico utente
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di richieste di assistenza
     */
    public static String selectAskSupport(int idU){
        return "SELECT * FROM Assistenza WHERE Assistenza.idUtente ="+idU+";";
    }

    /**
     * @author Andrea
     * Ottenere i dettagli di una determinata richiesta di assistenza
     * @param idA Un intero che rappresenta l'identificativo della richiesta di assistenza presa in considerazione
     * @return String: informazioni di una richiesta di assistenza
     */
    public static String selectSpecifiedInfoSupport(int idA){
        return "SELECT * FROM Assistenza WHERE Assistenza.id ="+idA+";";
    }



    /*---2017-12-24---*/
    /**
     * @author fbrug
     * Ottenere le richieste di assistenza in un determinato stato (0 = in corso, 1 = chiuse)
     * @param stato Intero rappresentante lo stato della richiesta di assistenza
     * @return String: lista di richieste di assistenza
     */
    public static String selectAssistanceByState(int stato)
    {
        return "SELECT * FROM Assistenza WHERE Assistenza.stato=" + stato + ";";
    }

    /**
     * @author fbrug
     * Ottenere le richieste di assistenza in base all'ID dell'amministratore a cui sono state assegnate
     * @param idAdmin Intero rappresentante l'ID dell'amministratore di cui si vogliono le richieste di assistenza assegnate
     * @return String: lista di richieste di assistenza
     */
    public static String selectAssistanceByAdminId(int idAdmin)
    {
        return "SELECT * FROM Assistenza WHERE Assistenza.idAmministratore=" + idAdmin + ";";
    }

    /**
     * @author fbrug
     * Ottenere le richieste di assistenza in base all'ID dell'amministratore a cui sono state assegnate e in un determinato stato (0 = in corso, 1 = chiuse)
     * @param idAdmin Intero rappresentante l'ID dell'amministratore di cui si vogliono le richieste di assistenza assegnate
     * @param stato Intero rappresentante lo stato della richiesta di assistenza
     * @return String: lista di richieste di assistenza
     */
    public static String selectAssistanceByAdminIdAndState(int idAdmin, int stato)
    {
        return "SELECT * FROM Assistenza WHERE Assistenza.idAmministratore=" + idAdmin + " AND Assistenza.stato=" + stato + ";";
    }
    
    /**
     * @author fbrug
     * Ottenere le richieste di assistenza in base ai parametri specificati
     * @param idUtente Intero rappresentante l'ID dell'utente che ha richiesto l'assistenza
     * @param idVenditore Intero rappresentante l'ID del venditore citato nella richiesta di assistenza
     * @param idAmministratore Intero rappresentante l'ID dell'amministratore incaricato di risolvere la richiesta di assistenza
     * @param idOrdine Intero rappresentante l'ID dell'ordine citato nella richiesta di assistenza
     * @param idOggetto Stringa rappresentate l'ID dell'oggetto citato nella richiesta di assistenza
     * @return String: lista di richieste di assistenza
     */
    public static String selectAssistance(int idUtente, int idVenditore, int idAmministratore, int idOrdine, String idOggetto)
    {
        return "SELECT * FROM assistenza WHERE "
                + "assistenza.idUtente=" + idUtente + " AND "
                + "assistenza.idVenditore=" + idVenditore + " AND "
                + "assistenza.idAmministratore=" + idAmministratore + " AND "
                + "assistenza.idOrdine=" + idOrdine + " AND "
                + "assistenza.idOggetto='" + idOggetto + "';";
    }
    

    /*2017-12-25*/

    /**
     * @author fbrug
     * Update della soluzione adottata per la richiesta di assistenza selezionata
     * @param idA Intero rappresentate l'ID della richiesta di assistenza selezionata
     * @param solution String rappresentante la soluzione adottata per questa richiesta
     * @return String: conferma avvenuta operazione
     */
    public static String updateAssistanceSolution(int idA, String solution)
    {
        return "UPDATE progettoweb.Assistenza SET soluzione ='"+solution+"' WHERE "
                + "Assistenza.id ="+idA+";";
    }

    /**
     * @author fbrug
     * Update della data di chiusura della richiesta di assistenza selezionata
     * @param idA Intero rappresentate l'ID della richiesta di assistenza selezionata
     * @param date String rappresentante la data di chiusura della richiesta
     * @return String: conferma avvenuta operazione
     */
    public static String updateAssistanceCloseDate(int idA, String date)
    {
        return "UPDATE progettoweb.Assistenza SET dataChiusura ='"+date+"' WHERE "
                + "Assistenza.id ="+idA+";";
    }

    /**
     * @author fbrug
     * Update della data di chiusura della richiesta di assistenza selezionata
     * @param idA Intero rappresentate l'ID della richiesta di assistenza selezionata
     * @param state Intero che indica se la richiesta è aperta (0) o chiusa (1)
     * @return String: conferma avvenuta operazione
     */
    public static String updateAssistanceState(int idA, int state)
    {
        return "UPDATE progettoweb.Assistenza SET stato ="+state+" WHERE "
                + "Assistenza.id ="+idA+";";
    }

    /**
     * @author fbrug
     * Update della richiesta di assistenza
     * @param assistance ModelloAssistenza rappresentante la richiesta da modificare
     * @return String: conferma avvenuta operazione
     */
    public static String updateAssistance(ModelloAssistenza assistance)
    {
        return "UPDATE progettoweb.Assistenza SET "
                + "idUtente="+assistance.getIdUtente()+","
                + "idVenditore="+assistance.getIdVenditore()+","
                + "idAmministratore="+assistance.getIdAmministratore()+","
                + "idOrdine="+assistance.getIdOrdine()+","
                + "idOggetto='"+assistance.getIdOggetto()+"',"
                + "stato="+assistance.getStato()+","
                + "richiesta='"+assistance.getRichiesta()+"',"
                + "soluzione='"+assistance.getSoluzione()+"',"
                + "dataApertura='"+assistance.getDataApertura()+"',"
                + "dataChiusura='"+assistance.getDataChiusura()+"' "
                + "WHERE id="+assistance.getId()+";";
    }

    /*2017-12-30*/

    /**
     * @author fbrug
     * Aggiunta di una richiesta di assistenza
     * @param assistance ModelloAssistenza rappresentante la richiesta di assistenza da aggiungere
     * @return String: conferma avvenuta operazione
     */
    public static String insertAssistance(ModelloAssistenza assistance)
    {
        return "INSERT INTO assistenza "
                + "(idUtente, idVenditore, idAmministratore, idOrdine, idOggetto, richiesta) "
                + "VALUES ("
                + assistance.getIdUtente() + ", "
                + assistance.getIdVenditore()+ ", "
                + assistance.getIdAmministratore()+ ", "
                + assistance.getIdOrdine()+ ", '"
                + assistance.getIdOggetto()+ "', '"
                + assistance.getRichiesta()+ "')";
    }
    /*---*/


    /**
     * @author Andrea
     * Ottenere la lista dei prodotti nella stessa fascia di prezzo e categoria di quelli già acquistati
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: informazioni di un venditore
     */
    public static String selectProductsSamePriceCategoryAlreadyBought(int idU){
        return "Create OR REPLACE View IDUInteressi_"+idU+" as SELECT Oggetto.categoria, "
                + "MAX(Oggetto.prezzo) AS prezzoMassimo, MIN(Oggetto.prezzo) AS "
                + "prezzoMinimo FROM Oggetto INNER JOIN Ordine ON "
                + "(Ordine.idOggetto = Oggetto.id) WHERE Ordine.idUtente ="+idU+" "
                + "AND Ordine.stato <> 0 AND Ordine.stato <> 5 GROUP BY Oggetto.categoria;"
                + "SELECT Oggetto.* FROM Oggetto, IDUInteressi_"+idU+" WHERE Oggetto.categoria = "
                + "IDUInteressi_"+idU+".categoria AND (Oggetto.prezzo - "
                + "(Oggetto.prezzo*Oggetto.sconto)/100) BETWEEN "
                + "IDUInteressi_"+idU+".prezzoMassimo AND IDUInteressi_"+idU+".prezzoMinimo;";
    }

    /**
     * @author Andrea
     * Ottenere la lista di assistenze che hanno a che fare con un ordine
     * @param idO Un intero che rappresenta l'identificativo dell'ordine preso in considerazione
     * @return String: lista di assistenze
     */
    public static String selectSupportOfOrder(int idO){
        return "SELECT * FROM Assistenza WHERE Assistenza.idOrdine ="+idO+";";
    }

    /**
     * @author Andrea
     * Ottenere la lista delle proprie recensioni Oggetti dalla più utile
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di recensioni
     */
    public static String selectObjectReviewsOrderUseful(int idU){
        return "SELECT * FROM RecensioneOggetto WHERE "
                + "RecensioneOggetto.idUtente ="+idU+" ORDER BY "
                + "RecensioneOggetto.utilita DESC;";
    }

    /**
     * @author Andrea
     * Ottenere la lista delle proprie recensioni Oggetti dalla meno utile
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di recensioni
     */
    public static String selectObjectReviewsOrderUseless(int idU){
        return "SELECT * FROM RecensioneOggetto WHERE "
                + "RecensioneOggetto.idUtente ="+idU+" ORDER BY "
                + "RecensioneOggetto.utilita ASC;";
    }

    /**
     * @author Andrea
     * Ottenere la lista delle proprie recensioni Negozio dalla più utile
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di recensioni
     */
    public static String selectStoreReviewsOrderUseful(int idU){
        return "SELECT * FROM RecensioneNegozio WHERE "
                + "RecensioneNegozio.idUtente ="+idU+" ORDER BY "
                + "RecensioneNegozio.utilita DESC;";
    }

    /**
     * @author Andrea
     * Ottenere la lista delle proprie recensioni Negozio dalla meno utile
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di recensioni
     */
    public static String selectStoreReviewsOrderUseless(int idU){
        return "SELECT * FROM RecensioneNegozio WHERE "
                + "RecensioneNegozio.idUtente ="+idU+" ORDER BY "
                + "RecensioneNegozio.utilita ASC;";
    }

    /**
     * @author Andrea
     * Ottenere la lista delle proprie recensioni Venditore dalla più utile
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di recensioni
     */
    public static String selectSellerReviewsOrderUseful(int idU){
        return "SELECT * FROM RecensioneVenditore WHERE "
                + "RecensioneVenditore.idUtente ="+idU+" ORDER BY "
                + "RecensioneVenditore.utilita DESC;";
    }

    /**
     * @author Andrea
     * Ottenere la lista delle proprie recensioni Venditore dalla meno utile
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di recensioni
     */
    public static String selectSellerReviewsOrderUseless(int idU){
        return "SELECT * FROM RecensioneVenditore WHERE "
                + "RecensioneVenditore.idUtente ="+idU+" ORDER BY "
                + "RecensioneVenditore.utilita ASC;";
    }

    /**
     * @author Andrea
     * Ottenere la lista delle immagini di un oggetto
     * @param idO Un intero che rappresenta l'identificativo dell'oggetto preso in considerazione
     * @return String: lista di foto
     */
    public static String selectPhotoObject(String idO){
        return "SELECT * FROM imageOggetto WHERE imageOggetto.idO ='"+idO+"';";
    }

    /**
     * @author Andrea
     * Ottenere solo la prima immagine di un oggetto
     * @param idO Un intero che rappresenta l'identificativo dell'oggetto preso in considerazione
     * @return String: lista di foto
     */
    public static String selectFirstPhotoObject(String idO){
        return "SELECT * FROM imageOggetto WHERE imageOggetto.idO ='"+idO+"' LIMIT 1;";
    }

    /**
     * @author Andrea
     * Ottenere la lista delle immagini di un negozio
     * @param idN Un intero che rappresenta l'identificativo del negozio preso in considerazione
     * @return String: lista di foto
     */
    public static String selectPhotoStore(int idN){
        return "SELECT * FROM imageNegozio WHERE imageNegozio.idN ="+idN+";";
    }

    /**
     * @author Andrea
     * Ottenere solo la prima immagine di un negozio
     * @param idN Un intero che rappresenta l'identificativo del negozio preso in considerazione
     * @return String: lista di foto
     */
    public static String selectFirstPhotoStore(int idN){
        return "SELECT * FROM imageNegozio WHERE imageNegozio.idN ="+idN+" LIMIT 1;";
    }

    /**
     * @author Andrea
     * Ottenere la lista delle immagini di una recensione
     * @param idR Un intero che rappresenta l'identificativo della recensione presa in considerazione
     * @return String: lista di foto
     */
    public static String selectPhotoReview(int idR){
        return "SELECT * FROM imageRecensione WHERE imageRecensione.idR ="+idR+";";
    }

    /**
     * @author Andrea
     * Ottenere solo la prima immagine di una recensione
     * @param idR Un intero che rappresenta l'identificativo della recensione presa in considerazione
     * @return String: lista di foto
     */
    public static String selectFirstPhotoReview(int idR){
        return "SELECT * FROM imageRecensione WHERE imageRecensione.idR ="+idR+" LIMIT 1;";
    }

    /**
     * @author Andrea
     * Ottenere la lista delle immagini di un utente
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di foto
     */
    public static String selectPhotoUser(int idU){
        return "SELECT * FROM imageUtente WHERE imageUtente.idU ="+idU+";";
    }

    /**
     * @author Andrea
     * Ottenere solo la prima immagine di un utente
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di foto
     */
    public static String selectFirstPhotoUser(int idU){
        return "SELECT * FROM imageUtente WHERE imageUtente.idU ="+idU+" LIMIT 1;";
    }

    /**
     * @author Andrea
     * Aggiungi un oggetto agli ordini nel carrello
     * @param idOr Un intero che rappresenta l'identificativo dell'ordine preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param quant Un intero che rappresenta la quantità del prodotto da inserire nel carrello
     * @param idO Una stringa che rappresenta l'identificativo dell'oggetto preso in considerazione
     */
    public static String insertObjectInCart(int idOr, int idU, int quant, String idO){
        return "INSERT INTO progettoweb.Ordine (idOrdine, idOggetto, idNegozio, "
            + "idUtente, stato, quantita, codiceTracking, dataArrivoPresunta, "
            + "dataOrdine, prezzoDiAcquisto) SELECT "+idOr+", Oggetto.id , "
            + "Oggetto.idNegozio , "+idU+", 0, "+quant+", NULL, NULL, CURRENT_TIMESTAMP, "
            + "(Oggetto.prezzo - (Oggetto.prezzo*Oggetto.sconto)/100) FROM "
            + "Oggetto WHERE Oggetto.id ='"+idO+"';";
    }

    /**
     * @author Andrea
     * Rimuovere un ordine (oggetto) dal carrello
     * @param idOr Un intero che rappresenta l'identificativo dell'ordine preso in considerazione
     * @param idOg Una stringa che rappresenta l'identificativo dell'oggetto preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     */
    public static String removeObjectInCart(int idOr, String idOg, int idU){
        return "DELETE FROM Ordine WHERE idOrdine ="+idOr+" AND "
                + "idOggetto ='"+idOg+"' AND IDUTENTE ="+idU+";";
    }

    /**
     * @author Andrea
     * Cambia lo stato degli ordini da from a to
     * @param from Un intero che rappresenta lo stato degli ordini da selezionare
     * @param to Un intero che rappresenta lo stato da impostare agli ordini selezionati
     */
    public static String changeOrderStatus(ModelloOrdine ordine, int from, int to){
        return "UPDATE progettoweb.Ordine SET stato ="+to+" WHERE "
                + "Ordine.stato ="+from+" AND Ordine.idOrdine ="+ordine.getIdOrdine()+" AND Ordine.idOggetto = '"+ordine.getIdOggetto()+"';";
    }

    /**
     * @author fbrug
     * Cambia la quantità di un prodotto nell'ordine
     * @param idOr Un intero che rappresenta l'identificativo dell'ordine preso in considerazione
     * @param idOg Una stringa che rappresenta l'identificativo dell'oggetto preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param newQuantity Un intero rappresentate la nuova quantità dell'oggetto nell'ordine selezionato
     */
    public static String changeOrderQuantity(int idOr, String idOg, int idU, int newQuantity)
    {
        return "UPDATE progettoweb.Ordine SET quantita = " + newQuantity + " WHERE "
                + "idOrdine = " + idOr + " AND idOggetto = '" + idOg + "' AND IDUTENTE = " + idU + ";";
    }

    /**
     * @author Andrea
     * Aggiungi un oggetto agli ordini nella lista dei desideri
     * @param idOr Un intero che rappresenta l'identificativo dell'ordine preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param quant Un intero che rappresenta la quantità del prodotto da inserire nel carrello
     * @param idO Una stringa che rappresenta l'identificativo dell'oggetto preso in considerazione
     */
    public static String insertObjectInWislist(int idOr, int idU, int quant, String idO){
        return "INSERT INTO progettoweb.Ordine (idOrdine, idOggetto, idNegozio, "
                + "idUtente, stato, quantita, codiceTracking, dataArrivoPresunta, "
                + "dataOrdine, prezzoDiAcquisto) SELECT "+idOr+", Oggetto.id , "
                + "Oggetto.idNegozio , "+idU+", 5, "+quant+", NULL, NULL, CURRENT_TIMESTAMP, "
                + "(Oggetto.prezzo - (Oggetto.prezzo*Oggetto.sconto)/100) FROM "
                + "Oggetto WHERE Oggetto.id ='"+idO+"';";
    }

    /**
     * @author Andrea
     * Aggiungi una recensione ad un determinato venditore
     * @param idV Un intero che rappresenta l'identificativo del venditore preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param txt Una stringa contenente il testo della recensione
     * @param val Un intero indicante la valutazione della recensione
     * @param star Un intero indicante l'utilità della recensione
     */
    public static String addReviewToSeller(int idV, int idU, String txt, int val, int star){
        return "INSERT INTO RecensioneVenditore (id, idVenditore, idUtente, testo, "
                + "valutazione, data, utilita) VALUES (NULL, '" + idV + "', " +
                idU + ", '" + txt + "', " + val + ", CURRENT_TIMESTAMP, " + star + ");";
    }

    /**
     * @author Andrea
     * Aggiungi una recensione ad un determinato negozio
     * @param idN Un intero che rappresenta l'identificativo del negozio preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param txt Una stringa contenente il testo della recensione
     * @param val Un intero indicante la valutazione della recensione
     * @param star Un intero indicante l'utilità della recensione
     */
    public static String addReviewToStore(int idN, int idU, String txt, int val, int star){
        return "INSERT INTO RecensioneNegozio (id, idNegozio, idUtente, testo, "
                + "valutazione, data, utilita) VALUES (NULL, '" + idN + "', " +
                idU + ", '" + txt + "', " + val + ", CURRENT_TIMESTAMP, " + star + ");";
    }

    /**
     * @author Andrea
     * Aggiungi una recensione ad un determinato oggetto
     * @param idO Una stringa che rappresenta l'identificativo dell'oggetto preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param txt Una stringa contenente il testo della recensione
     * @param val Un intero indicante la valutazione della recensione
     * @param star Un intero indicante l'utilità della recensione
     */
    public static String addReviewToObject(String idO, int idU, String txt, int val, int star) {
        return "INSERT INTO RecensioneOggetto (id, idOggetto, idUtente, testo, "
                + "valutazione, data, utilita) VALUES (NULL, '" + idO + "', " +
                idU + ", '" + txt + "', " + val + ", CURRENT_TIMESTAMP, " + star + ");";        
    }

    /**
     * @author Andrea
     * Ottenere un boolean se si ha recensito oppure no un venditore (se il count è 1 vuol dire di si)
     * @param idV Un intero che rappresenta l'identificativo del venditore preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: booleano indicante se si ha recensito o no un venditore
     */
    public static String reviewOrNotSeller(int idV, int idU){
        return "SELECT COUNT(idVenditore) AS counter FROM RecensioneVenditore "
                + "WHERE RecensioneVenditore.idVenditore ="+idV+" AND "
                + "RecensioneVenditore.idUtente ="+idU+";";
    }
    
    /**
     * @author Andrea
     * Ottenere un boolean se si ha acquistato oppure no da un venditore (se il count è 1 vuol dire di si)
     * @param idV Un intero che rappresenta l'identificativo del venditore preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: booleano indicante se si ha acquistato o no da un venditore
     */
    public static String buyOrNotFromSeller(int idV, int idU){
        return "SELECT COUNT(*) as counter FROM Negozio WHERE idVenditore="+idV+" AND id IN (SELECT idNegozio FROM Ordine "
                + "WHERE Ordine.idUtente ="+idU+" AND Ordine.stato=4)";
    }

    /**
     * @author Andrea
     * Ottenere un boolean se si ha recensito oppure no un negozio (se il count è 1 vuol dire di si)
     * @param idN Un intero che rappresenta l'identificativo del negozio preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: booleano indicante se si ha recensito o no un negozio
     */
    public static String reviewOrNotStore(int idN, int idU){
        return "SELECT COUNT(idNegozio) AS counter FROM RecensioneNegozio "
                + "WHERE RecensioneNegozio.idNegozio ="+idN+" AND "
                + "RecensioneNegozio.idUtente ="+idU+";";
    }

    /**
     * @author Andrea
     * Ottenere un boolean se si ha recensito oppure no un oggetto (se il count è 1 vuol dire di si)
     * @param idO Una Stringa che rappresenta l'identificativo dell'oggetto preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: booleano indicante se si ha recensito o no un oggetto
     */
    public static String reviewOrNotObject(String idO, int idU){
        return "SELECT COUNT(idOggetto) AS counter FROM RecensioneOggetto WHERE "
                + "RecensioneOggetto.idOggetto ='"+idO+"' AND "
                + "RecensioneOggetto.idUtente ="+idU+";";
    }
    
    /**
     * @author Andrea
     * Ottenere un boolean se si ha acquistato oppure no un oggetto (se il count è 1 vuol dire di si)
     * @param idO Una Stringa che rappresenta l'identificativo dell'oggetto preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: booleano indicante se si ha recensito o no un oggetto
     */
    public static String buyOrNotObject(String idO, int idU){
        return "SELECT COUNT(idOggetto) AS counter FROM Ordine WHERE "
                + "Ordine.idOggetto ='"+idO+"' AND "
                + "Ordine.idUtente ="+idU+" AND Ordine.stato=4;";
    }
    
    /**
     * @author Andrea
     * Ottenere un boolean se si ha acquistato oppure no da un negozio (se il count è 1 vuol dire di si)
     * @param idN Un intero che rappresenta l'identificativo del negozio preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: booleano indicante se si ha acquistato o no da un negozio
     */
    public static String buyOrNotFromStore(int idN, int idU){
        return "SELECT COUNT(idNegozio) AS counter FROM Ordine "
                + "WHERE Ordine.idNegozio ="+idN+" AND "
                + "Ordine.idUtente ="+idU+" AND Ordine.stato=4;";
    }
    
    /**
     * @author Andrea
     * Ottenere un intero indicante il numero di recensioni ottenuto
     * @param idN Un intero che rappresenta l'identificativo del negozio preso in considerazione
     * @return Integer: intero indicante il numero di recensioni ottenuto
     */
    public static String howManyReviews(int idN){
        return "SELECT COUNT(*) AS counter FROM RecensioneNegozio "
                + "WHERE RecensioneNegozio.idNegozio ="+idN+";";
    }
    
    public static String howManyReviewV(int idV){
        return "SELECT COUNT(*) AS counter FROM RecensioneVenditore "
                + "WHERE RecensioneVenditore.idVenditore ="+idV+";";
    }

    /**
     * @author Andrea
     * Aggiungere un proprio indirizzo
     * @param stato Stringa rappresentate lo Stato in considerazione
     * @param regione Stringa rappresentate la regione in considerazione
     * @param provincia Stringa rappresentate la provincia in considerazione
     * @param citta Stringa rappresentate la città in considerazione
     * @param via Stringa rappresentate la via in considerazione
     * @param nCivico Intero rappresentate il numero civico in considerazione
     * @param interno Intero rappresentate il numero di interno in considerazione
     * @param lat Double rappresentate la latitudine in considerazione
     * @param lon Double rappresentate la longitudine in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     */
    public static String insertAddress(String stato, String regione, String provincia, String citta, String via, int nCivico, int interno, double lat, double lon, int idU){
        return "INSERT INTO progettoweb.Indirizzo (idI, stato, regione, provincia, "
                + "citta, via, nCivico, interno, latitudine, longitudine) VALUES "
                + "(NULL, '"+stato+"', '"+regione+"', '"+provincia+"', '"+citta+"', '"+via+"', "+nCivico+", "+interno+", "+lat+", "+lon+"); "
                + "SET @IDI = 1; "
                + "SELECT @IDI:=idI FROM Indirizzo WHERE stato ='"+stato+"' AND "
                + "regione = '"+regione+"' AND provincia = '"+provincia+"' AND "
                + "citta = '"+citta+"' AND via = '"+via+"' AND nCivico = "+nCivico+" AND interno = "+interno+"; "
                + "INSERT INTO progettoweb.IndirizzoUtente (idI, idU) "
                + "VALUES (@IDI, "+idU+");";
    }

    public static String insertAddress1(String stato, String regione, String provincia, String citta, String via, int nCivico, int interno, double lat, double lon){
        return "INSERT INTO progettoweb.Indirizzo (idI, stato, regione, provincia, "
                + "citta, via, nCivico, interno, latitudine, longitudine) VALUES "
                + "(NULL, '"+stato+"', '"+regione+"', '"+provincia+"', '"+citta+"', '"+via+"', "+nCivico+", "+interno+", "+lat+", "+lon+"); ";
    }

    public static String insertAddress2(){
        return "SET @IDI = 1; ";
    }

    public static String insertAddress3(String stato, String regione, String provincia, String citta, String via, int nCivico, int interno, double lat, double lon){
        return "SELECT @IDI:=idI FROM Indirizzo WHERE stato ='"+stato+"' AND "
                + "regione = '"+regione+"' AND provincia = '"+provincia+"' AND "
                + "citta = '"+citta+"' AND via = '"+via+"' AND nCivico = "+nCivico+" AND interno = "+interno+"; ";
    }

    public static String insertAddress4(int idI,int idU){
        return "INSERT INTO progettoweb.IndirizzoUtente (idI, idU) "
                + "VALUES ("+idI+", "+idU+");";
    }

    /**
     * @author Andrea
     * Eliminaree un proprio indirizzo
     * @param idI Un intero che rappresenta l'identificativo dell'indirizzo preso in considerazione
     */
    public static String deleteAddress(int idI){
        return "DELETE FROM Indirizzo WHERE idI ="+idI+";";
    }

    /**
     * @author Andrea
     * Eliminaree un proprio indirizzo utente
     * @param idI Un intero che rappresenta l'identificativo dell'indirizzo preso in considerazione
     * @param idU id utente
     */
    public static String deleteAddressUtente(int idI, int idU){
        return "DELETE FROM Indirizzo WHERE idI ="+idI+" AND idU ="+idU+";";
    }
}
