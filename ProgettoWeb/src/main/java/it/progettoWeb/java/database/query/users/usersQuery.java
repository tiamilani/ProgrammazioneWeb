/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.database.query.users;
    
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
                + "WHERE id="+id+";";
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
        return "SELECT * FROM Ordine WHERE Ordine.idUtente = "+idU+" AND Ordine.stato = "+orderStatus+" ORDER BY Ordine.dataOrdine DESC;";
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
    public static String selectPhotoObject(int idO){
        return "SELECT * FROM imageOggetto WHERE imageOggetto.idO ="+idO+";";
    }
    
    /**
     * @author Andrea
     * Ottenere solo la prima immagine di un oggetto
     * @param idO Un intero che rappresenta l'identificativo dell'oggetto preso in considerazione
     * @return String: lista di foto
     */
    public static String selectFirstPhotoObject(int idO){
        return "SELECT * FROM imageOggetto WHERE imageOggetto.idO ="+idO+" LIMIT 1;";
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
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param from Un intero che rappresenta lo stato degli ordini da selezionare
     * @param to Un intero che rappresenta lo stato da impostare agli ordini selezionati
     */
    public static String changeOrderStatus(int idU, int from, int to){
        return "UPDATE progettoweb.Ordine SET stato ="+to+" WHERE "
                + "Ordine.stato ="+from+" AND Ordine.idUtente ="+idU+";";
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
     * @param data Un Datetime indicante la data di creazione della recensione
     * @param star Un intero indicante l'utilità della recensione
     */
    public static String addReviewToSeller(int idV, int idU, String txt, int val, Date data, int star){
        return "INSERT INTO progettoweb.RecensioneVenditore (id, idVenditore, "
                + "idUtente, testo, valutazione, data, utilita) VALUES "
                + "(NULL, "+idV+", "+idU+", '"+txt+"', "+val+", "+data+", "+star+");";
    }
    
    /**
     * @author Andrea
     * Aggiungi una recensione ad un determinato negozio
     * @param idN Un intero che rappresenta l'identificativo del negozio preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param txt Una stringa contenente il testo della recensione
     * @param val Un intero indicante la valutazione della recensione
     * @param data Un Datetime indicante la data di creazione della recensione
     * @param star Un intero indicante l'utilità della recensione
     */
    public static String addReviewToStore(int idN, int idU, String txt, int val, Date data, int star){
        return "INSERT INTO progettoweb.RecensioneNegozio (id, idNegozio, "
                + "idUtente, testo, valutazione, data, utilita) VALUES "
                + "(NULL, "+idN+", "+idU+", '"+txt+"', "+val+", "+data+", "+star+");";
    }
    
    /**
     * @author Andrea
     * Aggiungi una recensione ad un determinato oggetto
     * @param idO Una stringa che rappresenta l'identificativo dell'oggetto preso in considerazione
     * @param idU Un intero che rappresenta l'identificativo del soggetto preso in considerazione
     * @param txt Una stringa contenente il testo della recensione
     * @param val Un intero indicante la valutazione della recensione
     * @param data Un Datetime indicante la data di creazione della recensione
     * @param star Un intero indicante l'utilità della recensione
     */
    public static String addReviewToObject(String idO, int idU, String txt, int val, Date data, int star){
        return "INSERT INTO progettoweb.RecensioneOggetto (id, idOggetto, "
                + "idUtente, testo, valutazione, data, utilita) VALUES "
                + "(NULL, '"+idO+"', "+idU+", '"+txt+"', "+val+", "+data+", "+star+");";
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
    
    /**
     * @author Andrea
     * Eliminaree un proprio indirizzo
     * @param idI Un intero che rappresenta l'identificativo dell'indirizzo preso in considerazione
     */
    public static String deleteAddress(int idI){
        return "DELETE FROM Indirizzo WHERE idI ="+idI+";";
    }
}
