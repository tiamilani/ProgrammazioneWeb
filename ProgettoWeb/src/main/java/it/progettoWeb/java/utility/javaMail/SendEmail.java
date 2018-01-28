package it.progettoWeb.java.utility.javaMail;

import it.progettoWeb.java.database.Dao.Utente.DaoUtente;
import it.progettoWeb.java.database.Model.Oggetto.ModelloOggetto;
import it.progettoWeb.java.database.Model.Ordine.ModelloOrdine;
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail
{
    private static final DaoUtente daoUtente = new DaoUtente();
    private static final String shopEroMail = "shoperoweb@gmail.com";
    private static final String shopEroPass = "u!l937Ik00[.";

    /**
     * Preparazione Properties e Sessione utilizzate per inviare la mail
     * @param userEmail: email dell'utente a cui inviare la mail
     * @return Message: parte in comune del messaggio (mittente e destinatario)
     */
    private static Message preProcessing(String userEmail)
    {
        try
        {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(shopEroMail, shopEroPass);
                        }
                    });

            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
            message.setFrom(new InternetAddress(shopEroMail));

            return message;
        }
        catch (MessagingException ex)
        {
            System.out.println("Errore nella funzione preProcessing");
            System.out.println(ex.toString());
            sendError(userEmail, ex.toString());
            return null;
        }
    }

    /**
     * Invia una mail a shoperoweb@gmail.com contenente il messaggio di errore provocato
     * durante l'invio di una mail all'utente
     * @param userEmail: email dell'utente a cui si sarebbe dovuto inviare la mail
     * @param exceptionMessage: messaggio d'errore generato nel processo da un'eccezione
     */
    private static void sendError(String userEmail, String exceptionMessage)
    {
        try
        {
            Message messageError = preProcessing(shopEroMail);
            messageError.setSubject("Errore nell'invio di email");
            messageError.setText(
                    "C'è stato un errore nell'invio di una mail relativa a:\n"
                    + "USER MAIL = " + userEmail + ";\n"
                    + "EXEPTION MESSAGE = " + exceptionMessage + ";\n\n"
                    + "Now have fun with that ;)");

            Transport.send(messageError);
            System.out.println("Messaggio d'errore inviato");
            System.out.println(exceptionMessage);
        }
        catch (MessagingException ex)
        {
            System.out.println("Errore nella funzione sendError");
            System.out.println("ERROR MAIL: " + ex.toString());
            System.out.println(ex.toString());
        }
    }

    /**
     * Invia l'email all'utente specificato
     * @param userEmail: email dell'utente a cui inviare la mail
     * @param message: messaggio dell'email
     */
    private static void sendEmail(String userEmail, Message message)
    {
        try
        {
            Transport.send(message);
            System.out.println("Messaggio inviato");
        }
        catch (MessagingException ex)
        {
            System.out.println("Errore nella funzione sendEmail");
            System.out.println(ex.toString());
            System.out.println(ex.toString());
            sendError(userEmail, ex.toString());
        }
    }

    /**
     * Invia un'email al nuovo utente appena registrato
     * @param userEmail: email dell'utente a cui inviare la mail
     */
    public static void addUser(String userEmail)
    {
        String linkVerifica = "http://localhost:8080/ProgettoWeb/UserController?action=validateUser&identification=";
        String original = userEmail;
        String converted = "";
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(original.getBytes());
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                    sb.append(String.format("%02x", b & 0xff));
            }
            converted = sb.toString();
        } catch(NoSuchAlgorithmException e){

        }
        linkVerifica += converted;
        
        try
        {
            ModelloUtente user = daoUtente.selectUserByEmail(userEmail);
            Message message = preProcessing(userEmail);

            if(message != null)
            {
                message.setSubject("Registrazione su ShopEro");
                message.setText(
                        "Salve " + user.getNome() + ",\n"
                        + "Grazie per la tua registrazione su ShopEro.\n"
                        + "Il tuo account è stato creato, ma per attivarlo devi clickare il link qui sotto.\n"
                        + ""+linkVerifica+"\n"
                        + "Potrai accedere utilizzando le tue credenziali.\n\n"
                        + "BUONO SHOPPING!");

                sendEmail(userEmail, message);
            }
        }
        catch (MessagingException ex)
        {
            System.out.println(ex.toString());
            sendError(userEmail, ex.toString());
        }
    }

    /**
     * Invia un'email all'utente che ha cambiato il proprio indirizzo email
     * @param oldEmail: vecchia email dell'utente
     * @param newEmail: nuova email dell'utente
     */
    public static void updateEmail(String oldEmail, String newEmail)
    {
        try
        {
            ModelloUtente user = daoUtente.selectUserByEmail(newEmail);
            Message message = preProcessing(newEmail);

            if(message != null)
            {
                message.setSubject("Modifica indirizzo Email");
                message.setText(
                        "Salve " + user.getNome() + ",\n"
                        + "La modifica del tuo indirizzo email per ShopEro è andata a buon fine.\n"
                        + "La tua VECCHIA email era " + oldEmail + ", la tua NUOVA email è " + newEmail + "\n\n"
                        + "Se non sei stato tu a modificare la tua email, "
                        + "ti suggeriamo di contattare gli sviluppatori http://localhost:8080/ProgettoWeb/jspFile/Finale/Footer/sviluppatori.jsp#findUs \n\n"
                        + "Buona giornata!");

                sendEmail(newEmail, message);
            }

            message = preProcessing(oldEmail);

            if(message != null)
            {
                message.setSubject("Modifica indirizzo Email");
                message.setText(
                        "Salve " + user.getNome() + ",\n"
                        + "La modifica del tuo indirizzo email per ShopEro è andata a buon fine.\n"
                        + "La tua VECCHIA email era " + oldEmail + ", la tua NUOVA email è " + newEmail + "\n\n"
                        + "Se non sei stato tu a modificare la tua email, "
                        + "ti suggeriamo di contattare gli sviluppatori http://localhost:8080/ProgettoWeb/jspFile/Finale/Footer/sviluppatori.jsp#findUs \n\n"
                        + "Buona giornata!");

                sendEmail(oldEmail, message);
            }
        }
        catch (MessagingException ex)
        {
            System.out.println(ex.toString());
            sendError(newEmail, ex.toString());
        }
    }

    /**
     * Invia un'email all'utente che ha effettuato una richiesta di "password dimenticata"
     * @param userEmail: email dell'utente a cui inviare la mail
     * @param link: link da inserire nella mail
     */
    public static void passwordForget(String userEmail, String link)
    {
        try
        {
            ModelloUtente user = daoUtente.selectUserByEmail(userEmail);
            Message message = preProcessing(userEmail);
            System.out.println(user.getNome());

            if(message != null && user.getNome() != null)
            {
                message.setSubject("Reimposta la password di ShopEro");
                message.setText(
                        "Salve " + user.getNome() + ",\n"
                        + "Vogliamo aiutarti a reimpostare la tua password\n"
                        + "Clicca il link seguente per reimpostare la password. "
                        + "Sarà valido solo per 2 ore.\n"
                        + link + "\n\n"
                        + "Se non desideri reimpostare la password o se non hai richiesto tale modifica, puoi ignorare questa email. "
                        + "Nessun altro utente può modificare la tua password."
                        + "Buona giornata!");

                sendEmail(userEmail, message);
            }else{
                System.out.println("Indirizzo email non registrato nel sito -> email non inviata");
            }
        }
        catch (MessagingException ex)
        {
            System.out.println("Errore nella funzione passwordForget");
            System.out.println(ex.toString());
            sendError(userEmail, ex.toString());
        }
    }

    /**
     * Invia un'email all'utente che ha appena modificato la sua passord
     * @param userEmail: email dell'utente a cui inviare la mail
     */
    public static void updatePassword(String userEmail)
    {
        try
        {
            ModelloUtente user = daoUtente.selectUserByEmail(userEmail);
            Message message = preProcessing(userEmail);

            if(message != null)
            {
                message.setSubject("Hai reimpostato la passowrd");
                message.setText(
                        "Salve " + user.getNome() + ",\n"
                        + "La tua password per ShopEro è stata appena modificata.\n"
                        + "Se non sei stato tu a modificare la tua password, "
                        + "ti suggeriamo di contattare gli sviluppatori http://localhost:8080/ProgettoWeb/jspFile/Finale/Footer/sviluppatori.jsp#findUs \n\n"
                        + "Buona giornata!");

                sendEmail(userEmail, message);
            }
        }
        catch (MessagingException ex)
        {
            System.out.println(ex.toString());
            sendError(userEmail, ex.toString());
        }
    }

    /**
     * Invia un'email all'utente che è appena diventato un venditore
     * @param userEmail: email dell'utente a cui inviare la mail
     */
    public static void becomeSeller(String userEmail)
    {
        try
        {
            ModelloUtente user = daoUtente.selectUserByEmail(userEmail);
            Message message = preProcessing(userEmail);

            if(message != null)
            {
                message.setSubject("Benvenuto nel mondo dei venditori");
                message.setText(
                        "Salve " + user.getNome() + ",\n"
                        + "Congratulazioni per esserti unito al fantastico mondo dei venditori di ShopEro!\n\n"
                        + "Ora potrai vendere i tuoi prodotti agli utenti di ShopEro, ti basterà entrare nella tua sezione personale "
                        + "e inserire i tuoi oggetti in modo da esporli al pubblico.\n\n"
                        + "Questo e altro ancora ti aspettano in questa nuova avventura!\n\n"
                        + "BUONA VENDITA!");

                sendEmail(userEmail, message);
            }
        }
        catch (MessagingException ex)
        {
            System.out.println(ex.toString());
            sendError(userEmail, ex.toString());
        }
    }

    /**
     * Invia un'email all'utente che ha appena completato un ordine
     * @param userEmail: email dell'utente a cui inviare la mail
     * @param indexOrder: identificativo dell'ordine appena completato
     */
    public static void orderCompleted(String userEmail, int indexOrder)
    {
        try
        {
            ModelloUtente user = daoUtente.selectUserByEmail(userEmail);
            Message message = preProcessing(userEmail);

            if(message != null)
            {
                message.setSubject("ShopEro - Informazioni dell'ordine [" + indexOrder + "]");
                message.setText(
                        "Salve " + user.getNome() + ",\n"
                        + "Congratulazione per aver completato il tuo ordine.\n"
                        + "Il codice identificativo del tuo ordine è: " + indexOrder + ".\n"
                        + "Potrai visionare il tuo ordine nella sezione apposita del tuo account.\n"
                        + "Inoltre, appena il venditore effettuerà la spedizione, ti sarà possibile tracciare "
                        + "il tuo ordine tramite codice tracking fornito dal venditore stesso.");

                sendEmail(userEmail, message);
            }
        }
        catch (MessagingException ex)
        {
            System.out.println(ex.toString());
            sendError(userEmail, ex.toString());
        }
    }

    public static void ordineInLavorazione(String userEmail, ModelloOrdine ordine, ModelloOggetto oggetto) {
        try
        {
            ModelloUtente user = daoUtente.selectUserByEmail(userEmail);
            Message message = preProcessing(userEmail);

            if(message != null)
            {
                message.setSubject("ShopEro - Ordine in lavorazione :D [" + ordine.getIdOrdine()+ "]");
                message.setText(
                        "Salve " + user.getNome() + ",\n"
                        + "Ci teniamo a comunicarti che il tuo ordine è ora in lavorazione.\n"
                        + "L'ordine in questione è il seguente: \n"
                        + "idOrdine: " + ordine.getIdOrdine()+ ".\n"
                        + "L'idOggeto: "+ordine.getIdOggetto()+"\n"
                        + "Oggetto: "+oggetto.getNome()+"\n"
                        + "Acquistato il: "+ordine.getDataOrdine().toString()+"\n"
                        + "Potrai visionare il tuo ordine nella sezione apposita del tuo account.\n"
                        + "Inoltre, appena il venditore effettuerà la spedizione, ti sarà possibile tracciare "
                        + "il tuo ordine tramite codice tracking fornito dal venditore stesso.\n"
                        + "Per qualsiasi necessita non esitare a rivolgerti al servizio di assistenza");

                sendEmail(userEmail, message);
            }
        }
        catch (MessagingException ex)
        {
            System.out.println(ex.toString());
            sendError(userEmail, ex.toString());
        }
    }
    
    public static void ordineSpedito(String userEmail, ModelloOrdine ordine, ModelloOggetto oggetto) {
        try
        {
            ModelloUtente user = daoUtente.selectUserByEmail(userEmail);
            Message message = preProcessing(userEmail);

            if(message != null)
            {
                message.setSubject("ShopEro - Ordine spedito :D [" + ordine.getIdOrdine() + "]");
                message.setText(
                        "Salve " + user.getNome() + ",\n"
                        + "Ci teniamo a comunicarti che il tuo ordine è stato spedito.\n"
                        + "L'ordine in questione è il seguente: \n"
                        + "idOrdine: " + ordine.getIdOrdine()+ ".\n"
                        + "L'idOggeto: "+ordine.getIdOggetto()+"\n"
                        + "Oggetto: "+oggetto.getNome()+"\n"
                        + "Acquistato il: "+ordine.getDataOrdine().toString()+"\n"
                        + "Codice di tracking fornito dal venditore: "+ordine.getCodiceTracking()+""
                        + "Data di arrivo presunta: "+ordine.getDataArrivoPresunta().toString()+""
                        + "Potrai visionare il tuo ordine nella sezione apposita del tuo account.\n"
                        + "Inoltre, appena il venditore effettuerà la spedizione, ti sarà possibile tracciare "
                        + "il tuo ordine tramite codice tracking fornito dal venditore stesso.\n"
                        + "Per qualsiasi necessita non esitare a rivolgerti al servizio di assistenza");

                sendEmail(userEmail, message);
            }
        }
        catch (MessagingException ex)
        {
            System.out.println(ex.toString());
            sendError(userEmail, ex.toString());
        }
    }
    
    
    /**
     * Invia una mail all'utente venditore per informarlo che è stato citato in una richiesta di assistenza
     * Invia una mail all'utente amministratore per informarlo che è stato scelto per risolvere una richiesta di assistenza
     * @param mailVenditore: email dell'utente venditore citato nella richiesta di assistenza
     * @param mailAmministratore: email dell'utente amministratore incaricato di risolvere la richiesta di assistenza
     * @param nomeUtenteRichiedente: nome e cognome dell'utente che ha richiesta l'assistenza
     * @param idOrdine: intero rappresentante l'id dell'ordine contestato
     * @param idAssistenza: intero rappresentate l'id dell'assistenza in questione
     */
    public static void nuovaRichiestaDiAssistenza(String mailVenditore, String mailAmministratore, String nomeUtenteRichiedente, int idOrdine, int idAssistenza)
    {
        try
        {
            ModelloUtente user = daoUtente.selectUserByEmail(mailVenditore);
            Message message = preProcessing(mailVenditore);

            if(message != null)
            {
                message.setSubject("Richiesta di assistenza per l'ordine [" + idOrdine + "]");
                message.setText(
                        "Salve " + user.getNome() + ",\n"
                        + "È stata creata una richiesta di assistenza (N° " + idAssistenza + ") da parte di " + nomeUtenteRichiedente + "\n"
                        + " in merito all'ordine " + idOrdine + ".\n"
                        + "Ti consigliamo di visualizzare maggiori dettagli nella sezione \"Richieste di assistenza\" \n"
                        + "nella pagina del tuo account.\n"
                        + "Buona giornata!");

                sendEmail(mailVenditore, message);
            }

            user = daoUtente.selectUserByEmail(mailAmministratore);
            message = preProcessing(mailAmministratore);

            if(message != null)
            {
                message.setSubject("Richiesta di assistenza per l'ordine [" + idOrdine + "]");
                message.setText(
                        "Salve " + user.getNome() + ",\n"
                        + "È stata creata una richiesta di assistenza (N° " + idAssistenza + ") da parte di " + nomeUtenteRichiedente + "\n"
                        + " in merito all'ordine " + idOrdine + " ed è stata assegnata a te.\n"
                        + "Ti consigliamo di visualizzare maggiori dettagli nella sezione \"Gestisci richieste di assistenza\" \n"
                        + "nella pagina del tuo account.\n"
                        + "Buona giornata!");

                sendEmail(mailAmministratore, message);
            }
        }
        catch (MessagingException ex)
        {
            System.out.println(ex.toString());
            sendError(mailVenditore, ex.toString());
        }
    }
    
    /**
     * Invia una mail all'utente venditore per informarlo che una richiesta di assistenza in cui era stato citato si è conclusa
     * Invia una mail all'utente richiedente per informarlo che una sua richiesta di assistenza si è conclusa
     * @param mailVenditore: email dell'utente venditore citato nella richiesta di assistenza
     * @param mailUtenteRichiedente: email dell'utente che ha richiesto l'assistenza
     * @param idOrdine: intero rappresentante l'id dell'ordine contestato
     * @param idAssistenza: intero rappresentate l'id dell'assistenza in questione
     */
    public static void chiusuraRichiestaDiAssistenza(String mailVenditore, String mailUtenteRichiedente, int idOrdine, int idAssistenza)
    {
        try
        {
            ModelloUtente user = daoUtente.selectUserByEmail(mailVenditore);
            Message message = preProcessing(mailVenditore);

            if(message != null)
            {
                message.setSubject("Richiesta di assistenza N° " + idAssistenza + " conclusa");
                message.setText(
                        "Salve " + user.getNome() + ",\n"
                        + "La richiesta di assistenza N° " + idAssistenza + " riguardante l'ordine " + idOrdine + " è stata chiusa con successo.\n"
                        + "Ti consigliamo di visualizzare maggiori dettagli nella sezione \"Richieste di assistenza\" \n"
                        + "nella pagina del tuo account.\n"
                        + "Buona giornata!");

                sendEmail(mailVenditore, message);
            }

            user = daoUtente.selectUserByEmail(mailUtenteRichiedente);
            message = preProcessing(mailUtenteRichiedente);

            if(message != null)
            {
                message.setSubject("Richiesta di assistenza N° " + idAssistenza + " conclusa");
                message.setText(
                        "Salve " + user.getNome() + ",\n"
                        + "La richiesta di assistenza N° " + idAssistenza + " riguardante l'ordine " + idOrdine + " è stata chiusa con successo.\n"
                        + "Ti consigliamo di visualizzare maggiori dettagli nella sezione \"Richieste di assistenza\" \n"
                        + "nella pagina del tuo account.\n"
                        + "Buona giornata!");

                sendEmail(mailUtenteRichiedente, message);
            }
        }
        catch (MessagingException ex)
        {
            System.out.println(ex.toString());
            sendError(mailVenditore, ex.toString());
        }
    }
}
