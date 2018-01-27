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
        return "SELECT * FROM utente";
    }

    //Seleziono un utente dall'id
    public static String selectUserById(int id){
        return "SELECT * FROM utente WHERE id='"+id+"'";
    }


    // ottenere la lista dei negozi di un venditore specificando l'id del venditore
    public static String listaNegoziVenditore(String idV){
        return "SELECT * FROM negozio WHERE idVenditore = '"+idV+"';";
    }
    // ottenere la lista dei negozi di un venditore avendo nome e cognome del venditore

    public static String listaNegoziVenditore(String nome,String cognome) {
        return "SELECT negozio.* FROM negozio INNER JOIN utente ON (utente.nome = '"+nome+"' AND utente.cognome = '"+cognome+"' AND utente.UtenteType = 1 AND negozio.idVenditore = utente.id);";
    }

    // ottenre la lista dei negozi di un venditore avendo l'id e l'immagine del negozio

    public static String listaNegoziVenditorePlusImage(String idV) {
        return "SELECT negozio.*, imagenegozio.src FROM negozio INNER JOIN imagenegozio ON (negozio.idVenditore = '"+idV+"' AND negozio.id = imagenegozio.idN);";
    }

    // ottenere la lista dei negozi di un venditore avendo nome e cognome e la prima immagine del negozio

    public static String listaNegoziVenditorePlusImage(String nome,String cognome) {
        return "SELECT negozio.*, imagenegozio.src FROM negozio INNER JOIN utente ON (utente.nome = '"+nome+"' AND utente.cognome = '"+cognome+"' AND utente.UtenteType = 1 AND negozio.idVenditore = utente.id)LEFT JOIN imagenegozio ON (negozio.id = imagenegozio.idN);";
    }

    public static String inserisciUtente(String nome,String cognome,String mail,String password,String avatar,double valutazione,int UtenteType,boolean emailConfermata){
        return "insert into utente(nome, cognome, mail, password, avatar, valutazione, UtenteType, emailConfermata) values ("+
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
        return "UPDATE utente SET valutazione=" + value + " WHERE id=" + idUtente + ";";
    }

    public static String eliminaUtente(int id){
        return "DELETE FROM utente WHERE id="+id+";";
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
        return "SELECT * FROM ordine WHERE ordine.idUtente ="+idU+" ORDER BY ordine.dataOrdine DESC;";
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
        return "SELECT negozio.* FROM negozio INNER JOIN ordine ON "
                + "(ordine.idUtente ="+idU+" AND negozio.id = ordine.idNegozio "
                + "AND ordine.stato <> 0 AND ordine.stato <> 5) GROUP BY negozio.id;";
    }

    /**
     * @author Andrea
     * Ottenere la lista dei negozi da cui ho acquistato e la loro prima foto
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di negozi
     */
    public static String selectStoresUsedAndPhoto(int idU){
        return "SELECT negozio.*, imagenegozio.src FROM negozio INNER JOIN ordine ON "
                + "(ordine.idUtente ="+idU+" AND negozio.id = ordine.idNegozio "
                + "AND ordine.stato <> 0 AND ordine.stato <> 5) LEFT JOIN "
                + "imagenegozio ON (negozio.id = imagenegozio.idN) GROUP BY negozio.id;";
    }

    /**
     * @author Andrea
     * Ottenere la lista dei negozi da cui ho acquistato con i dati dell'oggetto acquistato, la prima foto del negozio e la prima dell'oggetto
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di negozi
     */
    public static String selectStoresUsedAndDataPhotoSPhotoO(int idU){
        return "SELECT negozio.*, imagenegozio.src AS imgNeg, oggetto.id AS idOgg, "
                + "oggetto.nome AS nomeOgg, oggetto.descrizione, imageOggetto.src AS "
                + "imgOgg FROM negozio INNER JOIN ordine ON (ordine.idUtente ="+idU+" "
                + "AND negozio.id = ordine.idNegozio AND ordine.stato <> 0 AND "
                + "ordine.stato <> 5) LEFT JOIN imagenegozio ON (negozio.id = "
                + "imagenegozio.idN) LEFT JOIN oggetto ON (ordine.idOggetto = "
                + "oggetto.id) LEFT JOIN imageOggetto ON (oggetto.id = "
                + "imageOggetto.idO) GROUP BY oggetto.id;";
    }

    /**
     * @author Andrea
     * Ottenere il carrello (La lista degli ordini che sono nel carrello)
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di ordini
     */
    public static String selectOrdersCart(int idU){
        return "SELECT ordine.*, carrello.subtotale FROM carrello, ordine WHERE "
                + "carrello.idUtente ="+idU+" AND ordine.idOrdine = carrello.idOrdine;";
    }

    /**
     * @author Andrea
     * Ottenere le proprie recensioni di oggetti
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di recensioni
     */
    public static String selectReviewsObjects(int idU){
        return "SELECT * FROM recensioneOggetto WHERE idUtente ="+idU+";";
    }

    /**
     * @author Andrea
     * Ottenere le proprie recensioni di negozi
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di recensioni
     */
    public static String selectReviewsStores(int idU){
        return "SELECT * FROM recensioneNegozio WHERE idUtente ="+idU+";";
    }

    /**
     * @author Andrea
     * Ottenere le proprie recensioni di venditori
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di recensioni
     */
    public static String selectReviewsSellers(int idU){
        return "SELECT * FROM recensioneVenditore WHERE idUtente ="+idU+";";
    }

    /**
     * @author Andrea
     * Ottenere le risposte alle proprie recensioni di oggetti
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di risposte alle recensioni
     */
    public static String selectAnswerReviewsObjects(int idU){
        return "SELECT rispostaOggetto.* FROM rispostaOggetto, recensioneOggetto "
                + "WHERE recensioneOggetto.idUtente ="+idU+" AND "
                + "rispostaOggetto.idRecensione = recensioneOggetto.id;";
    }

    /**
     * @author Andrea
     * Ottenere le risposte alle proprie recensioni di negozi
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di risposte alle recensioni
     */
    public static String selectAnswerReviewsStores(int idU){
        return "SELECT Rispostanegozio.* FROM Rispostanegozio, recensioneNegozio "
                + "WHERE recensioneNegozio.idUtente ="+idU+" AND "
                + "Rispostanegozio.idRecensione = recensioneNegozio.id;";
    }

    /**
     * @author Andrea
     * Ottenere le risposte alle proprie recensioni di venditori
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di risposte alle recensioni
     */
    public static String selectAnswerReviewsSellers(int idU){
        return "SELECT rispostaVenditore.* FROM rispostaVenditore, recensioneVenditore "
                + "WHERE recensioneVenditore.idUtente ="+idU+" AND "
                + "rispostaVenditore.idRecensione = recensioneVenditore.id;";
    }

    /**
     * @author Andrea
     * Ottenere tutte le recensioni di un oggetto
     * @param idO Un intero che rappresenta l'identificativo dell'oggetto preso in considerazione
     * @return String: lista di recensioni
     */
    public static String selectAllReviewsObjects(int idO){
        return "SELECT * FROM recensioneOggetto WHERE idOggetto ="+idO+";";
    }

    /**
     * @author Andrea
     * Ottenere tutte le recensioni di un negozio
     * @param idN Un intero che rappresenta l'identificativo del negozio preso in considerazione
     * @return String: lista di recensioni
     */
    public static String selectAllReviewsStores(int idN){
        return "SELECT * FROM recensioneNegozio WHERE idNegozio ="+idN+";";
    }

    /**
     * @author Andrea
     * Ottenere tutte le recensioni di un venditore
     * @param idV Un intero che rappresenta l'identificativo del venditore preso in considerazione
     * @return String: lista di recensioni
     */
    public static String selectAllReviewsSellers(int idV){
        return "SELECT * FROM recensioneVenditore WHERE idVenditore ="+idV+";";
    }

    /**
     * @author Andrea
     * Ottenere la lista delle richieste di assistenza fatte da uno specifico utente
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di richieste di assistenza
     */
    public static String selectAskSupport(int idU){
        return "SELECT * FROM assistenza WHERE assistenza.idUtente ="+idU+";";
    }

    /**
     * @author Andrea
     * Ottenere i dettagli di una determinata richiesta di assistenza
     * @param idA Un intero che rappresenta l'identificativo della richiesta di assistenza presa in considerazione
     * @return String: informazioni di una richiesta di assistenza
     */
    public static String selectSpecifiedInfoSupport(int idA){
        return "SELECT * FROM assistenza WHERE assistenza.id ="+idA+";";
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
        return "SELECT * FROM assistenza WHERE assistenza.stato=" + stato + ";";
    }

    /**
     * @author fbrug
     * Ottenere le richieste di assistenza in base all'ID dell'amministratore a cui sono state assegnate
     * @param idAdmin Intero rappresentante l'ID dell'amministratore di cui si vogliono le richieste di assistenza assegnate
     * @return String: lista di richieste di assistenza
     */
    public static String selectAssistanceByAdminId(int idAdmin)
    {
        return "SELECT * FROM assistenza WHERE assistenza.idAmministratore=" + idAdmin + ";";
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
        return "SELECT * FROM assistenza WHERE assistenza.idAmministratore=" + idAdmin + " AND assistenza.stato=" + stato + ";";
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
        return "UPDATE progettoweb.assistenza SET soluzione ='"+solution+"' WHERE "
                + "assistenza.id ="+idA+";";
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
        return "UPDATE progettoweb.assistenza SET dataChiusura ='"+date+"' WHERE "
                + "assistenza.id ="+idA+";";
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
        return "UPDATE progettoweb.assistenza SET stato ="+state+" WHERE "
                + "assistenza.id ="+idA+";";
    }

    /**
     * @author fbrug
     * Update della richiesta di assistenza
     * @param assistance ModelloAssistenza rappresentante la richiesta da modificare
     * @return String: conferma avvenuta operazione
     */
    public static String updateAssistance(ModelloAssistenza assistance)
    {
        return "UPDATE progettoweb.assistenza SET "
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
        return "Create OR REPLACE View IDUInteressi_"+idU+" as SELECT oggetto.categoria, "
                + "MAX(oggetto.prezzo) AS prezzoMassimo, MIN(oggetto.prezzo) AS "
                + "prezzoMinimo FROM oggetto INNER JOIN ordine ON "
                + "(ordine.idOggetto = oggetto.id) WHERE ordine.idUtente ="+idU+" "
                + "AND ordine.stato <> 0 AND ordine.stato <> 5 GROUP BY oggetto.categoria;"
                + "SELECT oggetto.* FROM oggetto, IDUInteressi_"+idU+" WHERE oggetto.categoria = "
                + "IDUInteressi_"+idU+".categoria AND (oggetto.prezzo - "
                + "(oggetto.prezzo*oggetto.sconto)/100) BETWEEN "
                + "IDUInteressi_"+idU+".prezzoMassimo AND IDUInteressi_"+idU+".prezzoMinimo;";
    }

    /**
     * @author Andrea
     * Ottenere la lista di assistenze che hanno a che fare con un ordine
     * @param idO Un intero che rappresenta l'identificativo dell'ordine preso in considerazione
     * @return String: lista di assistenze
     */
    public static String selectSupportOfOrder(int idO){
        return "SELECT * FROM assistenza WHERE assistenza.idOrdine ="+idO+";";
    }

    /**
     * @author Andrea
     * Ottenere la lista delle proprie recensioni Oggetti dalla più utile
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di recensioni
     */
    public static String selectObjectReviewsOrderUseful(int idU){
        return "SELECT * FROM recensioneOggetto WHERE "
                + "recensioneOggetto.idUtente ="+idU+" ORDER BY "
                + "recensioneOggetto.utilita DESC;";
    }

    /**
     * @author Andrea
     * Ottenere la lista delle proprie recensioni Oggetti dalla meno utile
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di recensioni
     */
    public static String selectObjectReviewsOrderUseless(int idU){
        return "SELECT * FROM recensioneOggetto WHERE "
                + "recensioneOggetto.idUtente ="+idU+" ORDER BY "
                + "recensioneOggetto.utilita ASC;";
    }

    /**
     * @author Andrea
     * Ottenere la lista delle proprie recensioni negozio dalla più utile
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di recensioni
     */
    public static String selectStoreReviewsOrderUseful(int idU){
        return "SELECT * FROM recensioneNegozio WHERE "
                + "recensioneNegozio.idUtente ="+idU+" ORDER BY "
                + "recensioneNegozio.utilita DESC;";
    }

    /**
     * @author Andrea
     * Ottenere la lista delle proprie recensioni negozio dalla meno utile
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di recensioni
     */
    public static String selectStoreReviewsOrderUseless(int idU){
        return "SELECT * FROM recensioneNegozio WHERE "
                + "recensioneNegozio.idUtente ="+idU+" ORDER BY "
                + "recensioneNegozio.utilita ASC;";
    }

    /**
     * @author Andrea
     * Ottenere la lista delle proprie recensioni Venditore dalla più utile
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di recensioni
     */
    public static String selectSellerReviewsOrderUseful(int idU){
        return "SELECT * FROM recensioneVenditore WHERE "
                + "recensioneVenditore.idUtente ="+idU+" ORDER BY "
                + "recensioneVenditore.utilita DESC;";
    }

    /**
     * @author Andrea
     * Ottenere la lista delle proprie recensioni Venditore dalla meno utile
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: lista di recensioni
     */
    public static String selectSellerReviewsOrderUseless(int idU){
        return "SELECT * FROM recensioneVenditore WHERE "
                + "recensioneVenditore.idUtente ="+idU+" ORDER BY "
                + "recensioneVenditore.utilita ASC;";
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
        return "SELECT * FROM imagenegozio WHERE imagenegozio.idN ="+idN+";";
    }

    /**
     * @author Andrea
     * Ottenere solo la prima immagine di un negozio
     * @param idN Un intero che rappresenta l'identificativo del negozio preso in considerazione
     * @return String: lista di foto
     */
    public static String selectFirstPhotoStore(int idN){
        return "SELECT * FROM imagenegozio WHERE imagenegozio.idN ="+idN+" LIMIT 1;";
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
        return "INSERT INTO progettoweb.ordine (idOrdine, idOggetto, idNegozio, "
            + "idUtente, stato, quantita, codiceTracking, dataArrivoPresunta, "
            + "dataOrdine, prezzoDiAcquisto) SELECT "+idOr+", oggetto.id , "
            + "oggetto.idNegozio , "+idU+", 0, "+quant+", NULL, NULL, CURRENT_TIMESTAMP, "
            + "(oggetto.prezzo - (oggetto.prezzo*oggetto.sconto)/100) FROM "
            + "oggetto WHERE oggetto.id ='"+idO+"';";
    }

    /**
     * @author Andrea
     * Rimuovere un ordine (oggetto) dal carrello
     * @param idOr Un intero che rappresenta l'identificativo dell'ordine preso in considerazione
     * @param idOg Una stringa che rappresenta l'identificativo dell'oggetto preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     */
    public static String removeObjectInCart(int idOr, String idOg, int idU){
        return "DELETE FROM ordine WHERE idOrdine ="+idOr+" AND "
                + "idOggetto ='"+idOg+"' AND IDUTENTE ="+idU+";";
    }

    /**
     * @author Andrea
     * Cambia lo stato degli ordini da from a to
     * @param from Un intero che rappresenta lo stato degli ordini da selezionare
     * @param to Un intero che rappresenta lo stato da impostare agli ordini selezionati
     */
    public static String changeOrderStatus(ModelloOrdine ordine, int from, int to){
        return "UPDATE progettoweb.ordine SET stato ="+to+" WHERE "
                + "ordine.stato ="+from+" AND ordine.idOrdine ="+ordine.getIdOrdine()+" AND ordine.idOggetto = '"+ordine.getIdOggetto()+"';";
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
        return "UPDATE progettoweb.ordine SET quantita = " + newQuantity + " WHERE "
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
        return "INSERT INTO progettoweb.ordine (idOrdine, idOggetto, idNegozio, "
                + "idUtente, stato, quantita, codiceTracking, dataArrivoPresunta, "
                + "dataOrdine, prezzoDiAcquisto) SELECT "+idOr+", oggetto.id , "
                + "oggetto.idNegozio , "+idU+", 5, "+quant+", NULL, NULL, CURRENT_TIMESTAMP, "
                + "(oggetto.prezzo - (oggetto.prezzo*oggetto.sconto)/100) FROM "
                + "oggetto WHERE oggetto.id ='"+idO+"';";
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
        return "INSERT INTO recensioneVenditore (id, idVenditore, idUtente, testo, "
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
        return "INSERT INTO recensioneNegozio (id, idNegozio, idUtente, testo, "
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
        String tmp = "INSERT INTO recensioneOggetto (id, idOggetto, idUtente, testo, "
                + "valutazione, data, utilita) VALUES (NULL, '" + idO + "', " +
                idU + ", '" + txt + "', " + val + ", CURRENT_TIMESTAMP, " + star + ");";
        
        System.out.println(tmp);
        return tmp;
    }

    /**
     * @author Andrea
     * Ottenere un boolean se si ha recensito oppure no un venditore (se il count è 1 vuol dire di si)
     * @param idV Un intero che rappresenta l'identificativo del venditore preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: booleano indicante se si ha recensito o no un venditore
     */
    public static String reviewOrNotSeller(int idV, int idU){
        return "SELECT COUNT(idVenditore) AS counter FROM recensioneVenditore "
                + "WHERE recensioneVenditore.idVenditore ="+idV+" AND "
                + "recensioneVenditore.idUtente ="+idU+";";
    }
    
    /**
     * @author Andrea
     * Ottenere un boolean se si ha acquistato oppure no da un venditore (se il count è 1 vuol dire di si)
     * @param idV Un intero che rappresenta l'identificativo del venditore preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: booleano indicante se si ha acquistato o no da un venditore
     */
    public static String buyOrNotFromSeller(int idV, int idU){
        return "SELECT COUNT(*) as counter FROM negozio WHERE idVenditore="+idV+" AND id IN (SELECT idNegozio FROM ordine "
                + "WHERE ordine.idUtente ="+idU+" AND ordine.stato=4)";
    }

    /**
     * @author Andrea
     * Ottenere un boolean se si ha recensito oppure no un negozio (se il count è 1 vuol dire di si)
     * @param idN Un intero che rappresenta l'identificativo del negozio preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: booleano indicante se si ha recensito o no un negozio
     */
    public static String reviewOrNotStore(int idN, int idU){
        return "SELECT COUNT(idNegozio) AS counter FROM recensioneNegozio "
                + "WHERE recensioneNegozio.idNegozio ="+idN+" AND "
                + "recensioneNegozio.idUtente ="+idU+";";
    }

    /**
     * @author Andrea
     * Ottenere un boolean se si ha recensito oppure no un oggetto (se il count è 1 vuol dire di si)
     * @param idO Una Stringa che rappresenta l'identificativo dell'oggetto preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: booleano indicante se si ha recensito o no un oggetto
     */
    public static String reviewOrNotObject(String idO, int idU){
        return "SELECT COUNT(idOggetto) AS counter FROM recensioneOggetto WHERE "
                + "recensioneOggetto.idOggetto ='"+idO+"' AND "
                + "recensioneOggetto.idUtente ="+idU+";";
    }
    
    /**
     * @author Andrea
     * Ottenere un boolean se si ha acquistato oppure no un oggetto (se il count è 1 vuol dire di si)
     * @param idO Una Stringa che rappresenta l'identificativo dell'oggetto preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: booleano indicante se si ha recensito o no un oggetto
     */
    public static String buyOrNotObject(String idO, int idU){
        return "SELECT COUNT(idOggetto) AS counter FROM ordine WHERE "
                + "ordine.idOggetto ='"+idO+"' AND "
                + "ordine.idUtente ="+idU+" AND ordine.stato=4;";
    }
    
    /**
     * @author Andrea
     * Ottenere un boolean se si ha acquistato oppure no da un negozio (se il count è 1 vuol dire di si)
     * @param idN Un intero che rappresenta l'identificativo del negozio preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @return String: booleano indicante se si ha acquistato o no da un negozio
     */
    public static String buyOrNotFromStore(int idN, int idU){
        return "SELECT COUNT(idNegozio) AS counter FROM ordine "
                + "WHERE ordine.idNegozio ="+idN+" AND "
                + "ordine.idUtente ="+idU+" AND ordine.stato=4;";
    }
    
    /**
     * @author Andrea
     * Ottenere un intero indicante il numero di recensioni ottenuto
     * @param idN Un intero che rappresenta l'identificativo del negozio preso in considerazione
     * @return Integer: intero indicante il numero di recensioni ottenuto
     */
    public static String howManyReviews(int idN){
        return "SELECT COUNT(*) AS counter FROM recensioneNegozio "
                + "WHERE recensioneNegozio.idNegozio ="+idN+";";
    }
    
    public static String howManyReviewV(int idV){
        return "SELECT COUNT(*) AS counter FROM recensioneVenditore "
                + "WHERE recensioneVenditore.idVenditore ="+idV+";";
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
        return "INSERT INTO progettoweb.indirizzo (idI, stato, regione, provincia, "
                + "citta, via, nCivico, interno, latitudine, longitudine) VALUES "
                + "(NULL, '"+stato+"', '"+regione+"', '"+provincia+"', '"+citta+"', '"+via+"', "+nCivico+", "+interno+", "+lat+", "+lon+"); "
                + "SET @IDI = 1; "
                + "SELECT @IDI:=idI FROM indirizzo WHERE stato ='"+stato+"' AND "
                + "regione = '"+regione+"' AND provincia = '"+provincia+"' AND "
                + "citta = '"+citta+"' AND via = '"+via+"' AND nCivico = "+nCivico+" AND interno = "+interno+"; "
                + "INSERT INTO progettoweb.indirizzoUtente (idI, idU) "
                + "VALUES (@IDI, "+idU+");";
    }

    public static String insertAddress1(String stato, String regione, String provincia, String citta, String via, int nCivico, int interno, double lat, double lon){
        return "INSERT INTO progettoweb.indirizzo (idI, stato, regione, provincia, "
                + "citta, via, nCivico, interno, latitudine, longitudine) VALUES "
                + "(NULL, '"+stato+"', '"+regione+"', '"+provincia+"', '"+citta+"', '"+via+"', "+nCivico+", "+interno+", "+lat+", "+lon+"); ";
    }

    public static String insertAddress2(){
        return "SET @IDI = 1; ";
    }

    public static String insertAddress3(String stato, String regione, String provincia, String citta, String via, int nCivico, int interno, double lat, double lon){
        return "SELECT @IDI:=idI FROM indirizzo WHERE stato ='"+stato+"' AND "
                + "regione = '"+regione+"' AND provincia = '"+provincia+"' AND "
                + "citta = '"+citta+"' AND via = '"+via+"' AND nCivico = "+nCivico+" AND interno = "+interno+"; ";
    }

    public static String insertAddress4(int idI,int idU){
        return "INSERT INTO progettoweb.indirizzoUtente (idI, idU) "
                + "VALUES ("+idI+", "+idU+");";
    }

    /**
     * @author Andrea
     * Eliminaree un proprio indirizzo
     * @param idI Un intero che rappresenta l'identificativo dell'indirizzo preso in considerazione
     */
    public static String deleteAddress(int idI){
        return "DELETE FROM indirizzo WHERE idI ="+idI+";";
    }

    /**
     * @author Andrea
     * Eliminaree un proprio indirizzo utente
     * @param idI Un intero che rappresenta l'identificativo dell'indirizzo preso in considerazione
     * @param idU id utente
     */
    public static String deleteAddressUtente(int idI, int idU){
        return "DELETE FROM indirizzo WHERE idI ="+idI+" AND idU ="+idU+";";
    }
}
