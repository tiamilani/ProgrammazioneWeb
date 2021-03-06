/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.Controller;

import it.progettoWeb.java.database.Dao.Negozio.DaoNegozio;
import it.progettoWeb.java.database.Dao.Oggetto.DaoOggetto;
import it.progettoWeb.java.database.Dao.Ordine.DaoOrdine;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.progettoWeb.java.database.Dao.Utente.DaoUtente;
import it.progettoWeb.java.database.Dao.immagineNegozio.DaoImmagineNegozio;
import it.progettoWeb.java.database.Dao.immagineOggetto.DaoImmagineOggetto;
import it.progettoWeb.java.database.Dao.indirizzo.DaoIndirizzo;
import it.progettoWeb.java.database.Dao.ordiniRicevuti.DaoOrdiniRicevuti;
import it.progettoWeb.java.database.Dao.recensioneNegozio.DaoRecensioneNegozio;
import it.progettoWeb.java.database.Dao.recensioneOggetto.DaoRecensioneOggetto;
import it.progettoWeb.java.database.Dao.recensioneVenditore.DaoRecensioneVenditore;
import it.progettoWeb.java.database.Dao.rispostaVenditore.DaoRispostaVenditore;
import it.progettoWeb.java.database.Model.Negozio.ModelloListeNegozio;
import it.progettoWeb.java.database.Model.Negozio.ModelloNegozio;
import it.progettoWeb.java.database.Model.Oggetto.ModelloListeOggetto;
import it.progettoWeb.java.database.Model.Oggetto.ModelloOggetto;
import it.progettoWeb.java.database.Model.Ordine.ModelloListeOrdine;
import it.progettoWeb.java.database.Model.Ordine.ModelloOrdine;
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import it.progettoWeb.java.database.Model.immagineNegozio.ModelloImmagineNegozio;
import it.progettoWeb.java.database.Model.immagineNegozio.ModelloListeImmagineNegozio;
import it.progettoWeb.java.database.Model.immagineOggetto.ModelloImmagineOggetto;
import it.progettoWeb.java.database.Model.immagineOggetto.ModelloListeImmagineOggetto;
import it.progettoWeb.java.database.Model.indirizzo.ModelloIndirizzo;
import it.progettoWeb.java.database.Model.indirizzo.ModelloListeIndirizzo;
import it.progettoWeb.java.database.Model.recensioneNegozio.ModelloListeRecensioneNegozio;
import it.progettoWeb.java.database.Model.recensioneNegozio.ModelloRecensioneNegozio;
import it.progettoWeb.java.database.Model.recensioneOggetto.ModelloListeRecensioneOggetto;
import it.progettoWeb.java.database.Model.recensioneOggetto.ModelloRecensioneOggetto;
import it.progettoWeb.java.database.Model.recensioneVenditore.ModelloListeRecensioneVenditore;
import it.progettoWeb.java.database.Model.recensioneVenditore.ModelloRecensioneVenditore;
import it.progettoWeb.java.database.Model.rispostaNegozio.ModelloRispostaNegozio;
import it.progettoWeb.java.database.Model.rispostaVenditore.ModelloRispostaVenditore;
import it.progettoWeb.java.utility.pair.pair;
import it.progettoWeb.java.utility.tris.tris;
import java.util.List;
import javax.servlet.RequestDispatcher;
import it.progettoWeb.java.utility.VerifyRecaptcha;
import it.progettoWeb.java.utility.javaMail.SendEmail;
import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author mattia
 */
public class UserController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/jspFile/Finale/Utente/modificaDatiUtente.jsp";
    private static String DESCRIZIONEVENDITORE = "/jspFile/Finale/DescrizioneVenditore/descrizioneVenditore.jsp";
    private static String DESCRIZIONENEGOZIO = "/jspFile/Finale/DescrizioneNegozio/descrizioneNegozio.jsp";
    private static String GESTIONEUTENTE ="/jspFile/Finale/Utente/impostazioneUtente.jsp";
    private static String USERPAGE = "/jspFile/Finale/Utente/utente.jsp";
    private static String HOME_PAGE = "/jspFile/Finale/Index/index.jsp";
    private static String INFOORDERDONE= "/jspFile/Finale/Utente/infoOrdiniUtente.jsp";
    private static String ERROR_PAGE = "/jspFile/Finale/Error/ricercaErrata.jsp";
    private static String INFONEGOZI = "/jspFile/Finale/Utente/gestioneNegoziUtente.jsp";
    private static String RISPONDIRECENSIONI = "/jspFile/Finale/Utente/rispondiRecensione.jsp";

    private DaoUtente daoUtente;
    private DaoRecensioneVenditore daoRecensioneV;
    private DaoRecensioneNegozio daoRecensioneN;
    private DaoRecensioneOggetto daoRecensioneO;
    private DaoNegozio daoNegozio;
    private DaoOggetto daoOggetto;
    private DaoIndirizzo daoIndirizzo;
    private DaoOrdine daoOrdine;
    private DaoImmagineOggetto daoImgOggetto;
    private DaoOrdiniRicevuti daoOrdiniRicevuti;
    private DaoImmagineNegozio daoImmagineNegozio;
    private DaoRispostaVenditore daoRispostaVenditore;

    public UserController() {
        super();
        daoIndirizzo = new DaoIndirizzo();
        daoUtente = new DaoUtente();
        daoRecensioneV = new DaoRecensioneVenditore();
        daoRecensioneN = new DaoRecensioneNegozio();
        daoRecensioneO = new DaoRecensioneOggetto();
        daoNegozio = new DaoNegozio();
        daoOggetto = new DaoOggetto();
        daoOrdine = new DaoOrdine();
        daoImgOggetto = new DaoImmagineOggetto();
        daoOrdiniRicevuti = new DaoOrdiniRicevuti();
        daoImmagineNegozio = new DaoImmagineNegozio();
        daoRispostaVenditore = new DaoRispostaVenditore();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String forward="";
        String action = request.getParameter("action");


        if (action.equalsIgnoreCase("edit"))
        {
            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(request.getParameter("userId"));
            ModelloUtente user = daoUtente.getUserById(userId);
            ModelloListeIndirizzo lInd = new ModelloListeIndirizzo(daoIndirizzo.selectAddressByUserID(userId));
            request.setAttribute("user", user);
            request.setAttribute("listaIndirizzi", lInd);
        }
        else if (action.equalsIgnoreCase("listUser")){
            request.setAttribute("users", daoUtente.getAllUsers());
        }
        else if(action.equals("DescrizioneVenditore")){
            int idUtente = Integer.parseInt(request.getParameter("idUtente"));
            ModelloUtente venditore = daoUtente.selectUserByID(idUtente);
            ModelloListeRecensioneVenditore recensioni = new ModelloListeRecensioneVenditore(daoRecensioneV.selectSellerReview(idUtente));
            tris<List<ModelloNegozio>, List<ModelloIndirizzo>, List<ModelloImmagineNegozio>> listaNegoziIndirizziImmagini = daoUtente.selectStoreAndAddressImageByUser(idUtente);
            ModelloListeNegozio listaNegozi = new ModelloListeNegozio(listaNegoziIndirizziImmagini.getL());
            ModelloListeIndirizzo listaIndirizzi = new ModelloListeIndirizzo(listaNegoziIndirizziImmagini.getC());
            ModelloListeImmagineNegozio listaImmagini = new ModelloListeImmagineNegozio(listaNegoziIndirizziImmagini.getR());

            pair<List<ModelloRecensioneVenditore>,List<ModelloUtente>> recensioniVenditori;
            recensioniVenditori = daoRecensioneV.selectReviewUserBySeller(idUtente);
            
            pair<List<ModelloRispostaVenditore>,List<ModelloUtente>> risposteVenditori;
            risposteVenditori = daoRecensioneV.selectAnswerUserBySeller(idUtente);

            try {
                ModelloUtente utenteSessione = (ModelloUtente)request.getSession().getAttribute("utenteSessione");

                if(utenteSessione.getId() != -1)
                {
                    if(daoRecensioneV.buyOrNotFromSeller(idUtente, utenteSessione.getId()) > 0)
                        request.setAttribute("canReviewsN", true);
                    else
                        request.setAttribute("canReviewsN", false);
                }
            } catch (NullPointerException e) {}

            request.setAttribute("canUploadImages", false);
            request.setAttribute("venditore", venditore);
            request.setAttribute("recensioni", recensioni);
            request.setAttribute("listaNegozi", listaNegozi);
            request.setAttribute("listaIndirizzi", listaIndirizzi);
            request.setAttribute("listaImmagini", listaImmagini);
            request.setAttribute("recensioniVenditori", recensioniVenditori);
            request.setAttribute("risposteVenditori", risposteVenditori);
            forward = DESCRIZIONEVENDITORE;
        }
        else if(action.equals("infoCurrentUser")){
            ModelloUtente utente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            ModelloListeIndirizzo listaIndirizzi = new ModelloListeIndirizzo(daoIndirizzo.selectAddressByUserID(utente.getId()));

            request.setAttribute("listaIndirizzi", listaIndirizzi);
            forward = GESTIONEUTENTE;
        }
        else if(action.equals("infoPersonalReviews")) {
            ModelloUtente utente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            
            List<ModelloRecensioneVenditore> listaRecensioniVTMP = daoRecensioneV.selectReviewsSeller(utente.getId());
            ModelloListeRecensioneVenditore listaRecensioniV = new ModelloListeRecensioneVenditore(listaRecensioniVTMP);           
            
            List<ModelloNegozio> negoziVenditore = daoNegozio.selectSellerStore(utente.getId());
            List<ModelloRecensioneNegozio> listaRecensioniNTMP = new ArrayList<ModelloRecensioneNegozio>();
            for(ModelloNegozio store : negoziVenditore) {
                listaRecensioniNTMP.addAll(daoRecensioneN.selectReviewStores(store.getId()));
            }
            ModelloListeRecensioneNegozio listaRecensioniN = new ModelloListeRecensioneNegozio(listaRecensioniNTMP);
            
            List<ModelloOggetto> oggettiVenditore = daoOggetto.selectSellerObjects(utente.getId());
            List<ModelloRecensioneOggetto> listaRecensioniOTMP = new ArrayList<ModelloRecensioneOggetto>();
            for(ModelloOggetto object : oggettiVenditore) {
                listaRecensioniOTMP.addAll(daoRecensioneO.selectReviewsObjects(object.getId()));
            }
            ModelloListeRecensioneOggetto listaRecensioniO = new ModelloListeRecensioneOggetto(listaRecensioniOTMP);
            
            request.setAttribute("listaRecensioniV", listaRecensioniV);
            request.setAttribute("listaRecensioniN", listaRecensioniN);
            request.setAttribute("listaRecensioniO", listaRecensioniO);
            forward = RISPONDIRECENSIONI;
        }
        else if(action.equals("updateMail")){
            ModelloUtente utente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            String newEmail = (String)request.getParameter("changeEmail").replace("\'", " ");

            if(utente.getMail().equalsIgnoreCase(newEmail)){
                request.setAttribute("aggiornamentoEmail", 1);
            }
            else{
                String oldMail = utente.getMail();
                utente.setMail(newEmail);

                daoUtente.updateUserEmailByUserID(utente);
                SendEmail.updateEmail(oldMail, newEmail);
                request.setAttribute("aggiornamentoEmail", 0);
            }
            ModelloListeIndirizzo listaIndirizzi = new ModelloListeIndirizzo(daoIndirizzo.selectAddressByUserID(utente.getId()));

            request.setAttribute("listaIndirizzi", listaIndirizzi);
            forward = GESTIONEUTENTE;
        }
        else if(action.equals("DescrizioneNegozio")){
            int idNegozio = Integer.parseInt(request.getParameter("idNegozio"));
            tris<ModelloNegozio, ModelloIndirizzo, ModelloImmagineNegozio> negozioIndirizzoImmagine = daoNegozio.selectStoreAddressImageByStoreID(idNegozio);
            ModelloNegozio negozio = negozioIndirizzoImmagine.getL();
            ModelloImmagineNegozio immagine = negozioIndirizzoImmagine.getR();
            ModelloIndirizzo indirizzo = negozioIndirizzoImmagine.getC();
            
            ModelloUtente venditore = new ModelloUtente();
            venditore = daoUtente.getUserById(negozio.getIdVenditore());

            pair<List<ModelloRecensioneNegozio>, List<ModelloUtente>> recensioniNegozi;
            recensioniNegozi = daoRecensioneN.selectReviewImagesUserByStore(idNegozio);
            
            pair<List<ModelloRispostaNegozio>,List<ModelloUtente>> risposteNegozi;
            risposteNegozi = daoRecensioneN.selectAnswerUserByStore(idNegozio);

            ModelloListeImmagineNegozio immagini = new ModelloListeImmagineNegozio(daoImmagineNegozio.selectPhotoStore(idNegozio));
            pair<List<ModelloOggetto>, List<ModelloImmagineOggetto>> listaOggettiImmagini = daoOggetto.selectObjectsImageSelledByStoreID(idNegozio);
            ModelloListeOggetto listaOggetti = new ModelloListeOggetto(listaOggettiImmagini.getL());
            ModelloListeImmagineOggetto listaImmaginiOggetto = new ModelloListeImmagineOggetto(listaOggettiImmagini.getR());

            try {
                ModelloUtente utenteSessione = (ModelloUtente)request.getSession().getAttribute("utenteSessione");

                if(utenteSessione.getId() != -1)
                {
                    if(daoRecensioneN.buyOrNotFromStore(idNegozio, utenteSessione.getId()) > 0)
                        request.setAttribute("canReviewsS", true);
                    else
                        request.setAttribute("canReviewsS", false);
                }
            } catch (NullPointerException e) {}

            request.setAttribute("canUploadImages", false);
            request.setAttribute("negozio", negozio);
            request.setAttribute("venditore", venditore);
            request.setAttribute("listaImmagini", immagini);
            request.setAttribute("immagine", immagine);
            request.setAttribute("indirizzo", indirizzo);
            request.setAttribute("listaOggetti", listaOggetti);
            request.setAttribute("listaImmaginiOggetto", listaImmaginiOggetto);
            request.setAttribute("recensioniNegozi", recensioniNegozi);
            request.setAttribute("risposteNegozi", risposteNegozi);
            forward = DESCRIZIONENEGOZIO;
        }
        else if(action.equalsIgnoreCase("orderList")){
            ModelloUtente utente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            String order = request.getParameter("order");
            ModelloListeOrdine listaordini = new ModelloListeOrdine();

            if(order.equalsIgnoreCase("data")){
                listaordini = new ModelloListeOrdine(daoOrdine.selectOrders(utente.getId()));

                for(int i=0; i<listaordini.getSize(); i++){
                    if(listaordini.get(i).getStato() == 0){
                        listaordini.getList().remove(i);
                        i--;
                    }
                }
            } else if(order.equalsIgnoreCase("tipo")){
                ModelloListeOrdine listaOrdiniPagati = new ModelloListeOrdine(daoOrdine.selectOrdersComplete(utente.getId(), 1));
                ModelloListeOrdine listaOrdiniInLavorazione = new ModelloListeOrdine(daoOrdine.selectOrdersComplete(utente.getId(), 2));
                ModelloListeOrdine listaOrdiniSpedito = new ModelloListeOrdine(daoOrdine.selectOrdersComplete(utente.getId(), 3));
                ModelloListeOrdine listaOrdiniConsegnato = new ModelloListeOrdine(daoOrdine.selectOrdersComplete(utente.getId(), 4));

                listaordini = new ModelloListeOrdine();
                for(int i = 0; i < listaOrdiniPagati.getList().size(); i++)
                    listaordini.add(listaOrdiniPagati.get(i));
                for(int i = 0; i < listaOrdiniInLavorazione.getList().size(); i++)
                    listaordini.add(listaOrdiniInLavorazione.get(i));
                for(int i = 0; i < listaOrdiniSpedito.getList().size(); i++)
                    listaordini.add(listaOrdiniSpedito.get(i));
                for(int i = 0; i < listaOrdiniConsegnato.getList().size(); i++)
                    listaordini.add(listaOrdiniConsegnato.get(i));
            } else if(order.equalsIgnoreCase("lavorazione")){
                listaordini = new ModelloListeOrdine(daoOrdine.selectOrdersComplete(utente.getId(), 2));
            } else if(order.equalsIgnoreCase("spediti")){
                listaordini = new ModelloListeOrdine(daoOrdine.selectOrdersComplete(utente.getId(), 3));
            }

            ModelloListeImmagineOggetto immaginiOggetti = new ModelloListeImmagineOggetto();
            ModelloListeOggetto oggetti = new ModelloListeOggetto();
            ModelloListeNegozio negozi = new ModelloListeNegozio();
            List<ModelloUtente> venditori = new ArrayList<>();

            for(int i=0; i<listaordini.getList().size(); i++){
                ModelloOggetto oggetto = daoOggetto.getObjectById(listaordini.get(i).getIdOggetto());
                ModelloImmagineOggetto immagine = new ModelloImmagineOggetto();
                immagine = daoImgOggetto.selectFirstPhotoObject(oggetto.getId());
                ModelloNegozio negozio = daoNegozio.getStoreById(oggetto.getIdNegozio());
                ModelloUtente venditore = daoUtente.getUserById(negozio.getIdVenditore());

                immaginiOggetti.add(immagine);
                oggetti.add(oggetto);
                negozi.add(negozio);
                venditori.add(venditore);
            }

            request.setAttribute("listaImmagini", immaginiOggetti);
            request.setAttribute("listaOggetti", oggetti);
            request.setAttribute("listaNegozi", negozi);
            request.setAttribute("listaVenditori", venditori);
            request.setAttribute("listaOrdini", listaordini);
            request.setAttribute("order", order);
            forward = INFOORDERDONE;
        }
        else if(action.equalsIgnoreCase("gestisciNegozi")){
            ModelloUtente utente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            String order = request.getParameter("order");
            String orderStore = request.getParameter("orderStore");
            ModelloListeOrdine listaordini = new ModelloListeOrdine();
            ModelloListeNegozio listanegozi = new ModelloListeNegozio();
            List<Integer> numeroOrdiniNegozi = new ArrayList<>();

            if(order.equalsIgnoreCase("data")){
                listaordini = new ModelloListeOrdine();
            }

            if(orderStore.equalsIgnoreCase("dataup")){

                listanegozi = new ModelloListeNegozio(daoNegozio.selectShopByOpenDate(utente.getId()));

            } else if (orderStore.equalsIgnoreCase("datadown")){

                List<ModelloNegozio> list;
                list = daoNegozio.selectShopByOpenDate(utente.getId());
                Collections.reverse(list);
                listanegozi = new ModelloListeNegozio(list);

            } else if (orderStore.equalsIgnoreCase("nameup")){

                listanegozi = new ModelloListeNegozio(daoNegozio.selectShopByNameup(utente.getId()));

            } else if (orderStore.equalsIgnoreCase("namedown")){

                listanegozi = new ModelloListeNegozio(daoNegozio.selectShopByNameDown(utente.getId()));

            } else if (orderStore.equalsIgnoreCase("prodottivendutiup")){

                pair<List<ModelloNegozio>,List<Integer>> p = daoNegozio.selectShopWithHigherSalesBySellerID(utente.getId());
                listanegozi = new ModelloListeNegozio(p.getL());
                numeroOrdiniNegozi = p.getR();

            } else if (orderStore.equalsIgnoreCase("prodottivendutidown")){

                pair<List<ModelloNegozio>,List<Integer>> p = daoNegozio.selectShopWithLowestSalesBySellerID(utente.getId());
                listanegozi = new ModelloListeNegozio(p.getL());
                numeroOrdiniNegozi = p.getR();

            }
            if(!orderStore.equalsIgnoreCase("prodottivendutiup") && !orderStore.equalsIgnoreCase("prodottivendutidown"))
                for(int i = 0; i<listanegozi.getList().size(); i++){
                    numeroOrdiniNegozi.add(daoOrdiniRicevuti.selectNumberOfOrderForStore(utente.getId(), listanegozi.get(i).getId()));
            }

            request.setAttribute("order", order);
            request.setAttribute("orderStore", orderStore);
            request.setAttribute("listaOrdini", listaordini);
            request.setAttribute("listanegozi", listanegozi);
            request.setAttribute("numeroOrdiniNegozi", numeroOrdiniNegozi);
            forward = INFONEGOZI;
        }
        else if(action.equalsIgnoreCase("logout")){
            request.getSession().invalidate();

            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals("user")) {
                        cookie.setValue("-1");
                        response.addCookie(cookie);
                    }
                }
            }

            //Cookie ck=new Cookie("user","");//creating cookie object
            //ck.setValue("-1");
            //ck.setMaxAge(0);
            //response.addCookie(ck);//adding cookie in the response


            forward = HOME_PAGE;
        } else if(action.equalsIgnoreCase("validateUser")){
            String emailMD5 = request.getParameter("identification");
            
            boolean validato = daoUtente.validateUser(emailMD5);
            
            if(validato){
                request.setAttribute("accountVerificato", 0);
            } else {
                request.setAttribute("accountVerificato", 1);
            }
            
            forward = HOME_PAGE;
        }
        else
        {
            forward = ERROR_PAGE;
            request.setAttribute("errore", "Comando non trovato");
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String forward = HOME_PAGE;
        boolean redirect = false;
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("selectUser")){
            log("SELECT USER");
            String email = request.getParameter("email");
            email = email.replace("\'", " ");
            String password = request.getParameter("password");
            ModelloUtente utente = daoUtente.selectUserByEmailAndPassword(email, password);
            log("Utente trovato: " + utente.getId());
            if(utente.getId() > 0){
                if(utente.isEmailConfermata()) {
                    request.getSession().removeAttribute("utenteSessione");
                    request.getSession().setAttribute("utenteSessione", utente);

                    Cookie ck=new Cookie("user",String.valueOf(utente.getId()));//creating cookie object
                    ck.setMaxAge(-1);
                    response.addCookie(ck);//adding cookie in the response

                    forward = request.getHeader("referer");
                    log("forward: " + forward);
                    if(forward.equals("http://localhost:8080/ProgettoWeb/PasswordReset?action=confirm") || forward.equals("http://localhost:8080/ProgettoWeb/UserController?action=addUser") || forward.equals("http://localhost:8080/ProgettoWeb/UserController?action=logout"))
                    {
                        redirect = false;
                        forward = HOME_PAGE;
                        log("Il forword è a home page: " + forward);
                    }
                    else
                    {
                        redirect = true;
                    }
                    log("forward: " + forward);

                    try
                    {
                        log("sono nel ty catch");
                        ModelloListeOrdine carrelloInSessione = (ModelloListeOrdine)request.getSession().getAttribute("carrelloSessione");
                        ModelloListeOrdine carrelloInDB = new ModelloListeOrdine(daoOrdine.selectOrdersComplete(utente.getId(), 0));
                        log("Varibili inizializzate");

                        for(ModelloOrdine ordineInCU : carrelloInDB.getList())
                            for(Iterator<ModelloOrdine> iterator = carrelloInSessione.getList().iterator(); iterator.hasNext();)
                            {
                                ModelloOrdine ordineInCIS = iterator.next();
                                if(ordineInCIS.getIdOggetto().equalsIgnoreCase(ordineInCU.getIdOggetto()))
                                {
                                    daoOrdine.changeOrderQuantity(
                                            ordineInCU.getIdOrdine(),
                                            ordineInCU.getIdOggetto(),
                                            ordineInCU.getIdUtente(),
                                            (ordineInCU.getQuantita() + ordineInCIS.getQuantita()));

                                    iterator.remove();
                                }
                            }
                        log("Inserimento eventuali nuove quantità degli oggetti in sessione");
                        int idUtente = utente.getId();
                        int idOrdine;
                        if(carrelloInDB.getSize() > 0){
                            idOrdine = carrelloInDB.get(0).getIdOrdine();
                        }
                        else
                        {
                            if(carrelloInSessione.getSize() > 0){
                                ModelloOrdine primoOggetto = carrelloInSessione.get(0);
                                primoOggetto.setIdUtente(idUtente);
                                daoOrdine.insertObjectInCartFirstTime(primoOggetto);

                                primoOggetto = daoOrdine.selectOrdersComplete(utente.getId(), 0).get(0);
                                idOrdine = primoOggetto.getIdOrdine();
                                List<ModelloOrdine> carrelloSenzaIlPrimoOggetto = carrelloInSessione.getList();
                                carrelloSenzaIlPrimoOggetto.remove(0);
                                carrelloInSessione = new ModelloListeOrdine(carrelloSenzaIlPrimoOggetto);
                            } else {
                                idOrdine = 0; //Il for più sotto non verrà eseguito neanche una volta
                            }
                        }

                        for(ModelloOrdine ordineInCIS : carrelloInSessione.getList())
                        {
                            ordineInCIS.setIdUtente(idUtente);
                            ordineInCIS.setIdOrdine(idOrdine);
                            daoOrdine.insertObjectInCart(ordineInCIS);
                        }

                        log("Aggiungo nuovi oggetti che erano nel carrello in sessione");

                        carrelloInSessione = new ModelloListeOrdine(daoOrdine.selectOrdersComplete(utente.getId(), 0));
                        request.getSession().removeAttribute("carrelloSessione");
                        request.getSession().setAttribute("carrelloSessione", carrelloInSessione);
                    }
                    catch (Exception e) {
                        log("ERRORE: " + e.toString());
                        System.out.println("error message = " + e.toString());
                        forward = ERROR_PAGE;
                        redirect = false;
                        request.setAttribute("errore", "404 Pagina non trovata");
                    }
                }
                else {
                    request.setAttribute("utenteLoginError", 2);
                }
            } else {
                    log("Utente non trovato");
                request.setAttribute("utenteLoginError", 1);
            }
        }
        else if(action.equalsIgnoreCase("addUser")){

            String remoteAddr = request.getRemoteAddr();
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

            if (VerifyRecaptcha.verify(gRecaptchaResponse)) {
                ModelloUtente utente = new ModelloUtente();
                utente.setNome(request.getParameter("nome").replace("\'", " "));
                utente.setCognome(request.getParameter("cognome").replace("\'", " "));
                utente.setMail(request.getParameter("email").replace("\'", " "));
                utente.setPassword(request.getParameter("password"));
                utente.setAvatar("http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/userImage.png");
                String confirmPassword = request.getParameter("confirmPassword");
                utente.setEmailConfermata(false);

                if(!utente.getPassword().equals(confirmPassword)){
                    forward = HOME_PAGE;
                    request.setAttribute("addUser", 3);
                }
                else
                {
                    ModelloUtente userAlreadyExists = daoUtente.selectUserByEmail(utente.getMail());
                    if(userAlreadyExists.getId()>0){
                        forward = HOME_PAGE;
                        request.setAttribute("addUser", 2);
                    }
                    else
                    {
                        utente.setUtenteType(0);
                        daoUtente.addUser(utente);
                        SendEmail.addUser(utente.getMail());

                        forward = HOME_PAGE;
                        request.setAttribute("addUser", 0);
                    }
                }
            } else {
                forward = HOME_PAGE;
                request.setAttribute("addUser", 1);
            }
        }
        else if(action.equalsIgnoreCase("updatePassword")){
            String newPassword = request.getParameter("newPassword");
            String newConfirmedPassword = request.getParameter("newConfirmPassword");

            ModelloUtente utente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");

            if(!newPassword.equals(newConfirmedPassword)){
                forward=GESTIONEUTENTE;
                request.setAttribute("resetPassword", 1);
            }
            else {
                if(utente.getPassword().equals(newPassword)){
                    forward=GESTIONEUTENTE;
                    request.setAttribute("resetPassword", 1);
                }
                else {
                    utente.setPassword(newPassword);
                    daoUtente.updateUserPasswordByUserID(utente);
                    SendEmail.updatePassword(utente.getMail());

                    forward = GESTIONEUTENTE;

                    request.setAttribute("resetPassword", 0);
                }
            }

            ModelloListeIndirizzo listaIndirizzi = new ModelloListeIndirizzo(daoIndirizzo.selectAddressByUserID(utente.getId()));

            request.setAttribute("listaIndirizzi", listaIndirizzi);
        }
        else if(action.equalsIgnoreCase("becomeSeller"))
        {
            ModelloUtente utente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            utente.setUtenteType(1);
            daoUtente.updateUser(utente);

            SendEmail.becomeSeller(utente.getMail());

            forward = USERPAGE;
        }

        if(!redirect){
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        }
        else
            response.sendRedirect(forward);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
