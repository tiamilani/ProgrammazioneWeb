/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.Controller.NegozioController;

import it.progettoWeb.java.database.Dao.Categoria.DaoCategoria;
import it.progettoWeb.java.database.Dao.Negozio.DaoNegozio;
import it.progettoWeb.java.database.Dao.Oggetto.DaoOggetto;
import it.progettoWeb.java.database.Dao.Ordine.DaoOrdine;
import it.progettoWeb.java.database.Dao.Utente.DaoUtente;
import it.progettoWeb.java.database.Dao.immagineNegozio.DaoImmagineNegozio;
import it.progettoWeb.java.database.Dao.immagineOggetto.DaoImmagineOggetto;
import it.progettoWeb.java.database.Dao.indirizzo.DaoIndirizzo;
import it.progettoWeb.java.database.Dao.ordiniRicevuti.DaoOrdiniRicevuti;
import it.progettoWeb.java.database.Dao.spedizioneOggetto.DaoSpedizioneOggetto;
import it.progettoWeb.java.database.Dao.tipoSpedizione.DaoTipoSpedizione;
import it.progettoWeb.java.database.Model.Categoria.ModelloListeCategoria;
import it.progettoWeb.java.database.Model.Negozio.ModelloListeNegozio;
import it.progettoWeb.java.database.Model.Negozio.ModelloNegozio;
import it.progettoWeb.java.database.Model.Oggetto.ModelloListeOggetto;
import it.progettoWeb.java.database.Model.Oggetto.ModelloOggetto;
import it.progettoWeb.java.database.Model.Ordine.ModelloListeOrdine;
import it.progettoWeb.java.database.Model.Ordine.ModelloOrdine;
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import it.progettoWeb.java.database.Model.immagineNegozio.ModelloImmagineNegozio;
import it.progettoWeb.java.database.Model.immagineOggetto.ModelloImmagineOggetto;
import it.progettoWeb.java.database.Model.immagineOggetto.ModelloListeImmagineOggetto;
import it.progettoWeb.java.database.Model.indirizzo.ModelloIndirizzo;
import it.progettoWeb.java.database.Model.ordiniRicevuti.ModelloListeOrdiniRicevuti;
import it.progettoWeb.java.database.Model.spedizioneOggetto.ModelloListeSpedizioneOggetto;
import it.progettoWeb.java.database.Model.tipoSpedizione.ModelloListeTipoSpedizione;
import it.progettoWeb.java.database.Model.tipoSpedizione.ModelloTipoSpedizione;
import it.progettoWeb.java.utility.javaMail.SendEmail;
import it.progettoWeb.java.utility.pair.pair;
import it.progettoWeb.java.utility.tris.tris;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/NegozioController")
@MultipartConfig(fileSizeThreshold = 1024*1024*2, // 2MB
                 maxFileSize = 1024*1024*10,      // 10MB
                 maxRequestSize = 1024*1024*50)   // 50MB

/**
 *
 * @author mattia
 */
public class NegozioController extends HttpServlet {
    
    private static final String SAVE_DIR = "uploadFiles";
    private static String USERPAGE = "/jspFile/Finale/Utente/utente.jsp";
    private static String SHOPPAGE = "/jspFile/Finale/Utente/gestioneSingoloNegozio.jsp";
    private static String ERROR_PAGE = "/jspFile/Finale/Error/ricercaErrata.jsp";
    private static String ADDSHOPPAGE = "/jspFile/Finale/Utente/aggiungiNegozio.jsp";
    private static String ADDOBJECT = "jspFile/Finale/Utente/aggiungiOggetto.jsp";
    private static String MODFICAOGGETTO = "jspFile/Finale/Utente/modificaOggetto.jsp";
    private static String MODIFICAORDINE = "jspFile/Finale/Utente/modificaOrdine.jsp";
    private static String MODIFYTIPOSPEDIZIONE = "jspFile/Finale/Utente/modificaSpedizione.jsp";
    private static String INFONEGOZI = "/jspFile/Finale/Utente/gestioneNegoziUtente.jsp";

    private DaoNegozio daoNegozio;
    private DaoIndirizzo daoIndirizzo;
    private DaoCategoria daoCategoria;
    private DaoOggetto daoOggetto;
    private DaoImmagineOggetto daoImmagineOggetto;
    private DaoOrdine daoOrdine;
    private DaoOrdiniRicevuti daoOrdiniRicevuti;
    private DaoTipoSpedizione daoTipoSpedizione;
    private DaoSpedizioneOggetto daoSpedizioneOggetto;
    
    private List<String> imageSrcs = new ArrayList<String>();
    private List<ModelloImmagineOggetto> immaginiOggetto = new ArrayList<ModelloImmagineOggetto>();
    
    public NegozioController() {
        super();
        daoIndirizzo = new DaoIndirizzo();
        daoNegozio = new DaoNegozio();
        daoCategoria = new DaoCategoria();
        daoOggetto = new DaoOggetto();
        daoImmagineOggetto = new DaoImmagineOggetto();
        daoOrdine = new DaoOrdine();
        daoOrdiniRicevuti = new DaoOrdiniRicevuti();
        daoTipoSpedizione = new DaoTipoSpedizione();
        daoSpedizioneOggetto = new DaoSpedizioneOggetto();
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
        
        if (action.equalsIgnoreCase("richiestaPaginaDiAggiuntaOggetto")) {
            int idNegozio = ((ModelloNegozio)request.getSession().getAttribute("negozio")).getId();
            ModelloListeCategoria listaCategorie = new ModelloListeCategoria(daoCategoria.selectAllCategory());
            ModelloListeTipoSpedizione listaSpedizioni = new ModelloListeTipoSpedizione(daoTipoSpedizione.selectDeliveryTypesByIdN(idNegozio));
            request.setAttribute("categorie", listaCategorie);
            request.setAttribute("listaSpedizioni", listaSpedizioni);
            forward = ADDOBJECT;
        }
        else if(action.equals("richiestaPaginaDiModificaOggetto")){
            ModelloListeCategoria listaCategorie = new ModelloListeCategoria(daoCategoria.selectAllCategory());
            request.setAttribute("categorie", listaCategorie);
            String idOggetto = request.getParameter("id");
            int idNegozio = ((ModelloNegozio)request.getSession().getAttribute("negozio")).getId();
            ModelloListeTipoSpedizione listaSpedizioni = new ModelloListeTipoSpedizione(daoTipoSpedizione.selectDeliveryTypesByIdN(idNegozio));
            
            ModelloListeSpedizioneOggetto listaSpedizioniOggetto = new ModelloListeSpedizioneOggetto(daoSpedizioneOggetto.getListSpedizioniOggetto(idOggetto));
            
            ModelloOggetto object = object = daoOggetto.getObjectById(idOggetto);
            request.setAttribute("oggetto", object);
            request.setAttribute("listaSpedizioni", listaSpedizioni);
            request.setAttribute("listaSpedizioniOggetto", listaSpedizioniOggetto);
            forward = MODFICAOGGETTO;
        }
        else if(action.equalsIgnoreCase("deleteObject")){
            int idNegozio = ((ModelloNegozio)request.getSession().getAttribute("negozio")).getId();
            ModelloUtente utente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            ModelloOggetto object = new ModelloOggetto();
            object.setId(request.getParameter("modifyObject"));

            object = daoOggetto.getObjectById(object.getId());
            log("Id dell'oggetto selezionato: " + object.getId() + " Nome dell'oggetto selezionato: " + object.getNome());

            String nomeScritto = request.getParameter("modifyDelete");
            if(!nomeScritto.isEmpty()){
                if(nomeScritto.equals(object.getNome())){
                    daoOggetto.deleteObjectImage(object.getId(), "http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/objectImage.png");
                    boolean eliminaOggetto = daoOggetto.deleteObject(object.getId());
                    if(!eliminaOggetto)
                        request.setAttribute("oggettoModificato", 6); 
                    else
                        request.setAttribute("oggettoModificato", 5); 
                } else {
                    log("Nome oggetto: " + object.getNome() + " Nome inserito: " + nomeScritto);
                    request.setAttribute("oggettoModificato", 4); 
                }
            } else {
                request.setAttribute("oggettoModificato", 3); 
            }
            
            tris<ModelloNegozio, ModelloIndirizzo, ModelloImmagineNegozio> trisNegozioIndirizzoImmagine = daoNegozio.selectStoreAddressImageByStoreID(idNegozio);
            ModelloListeCategoria listaCategorie = new ModelloListeCategoria(daoCategoria.selectAllCategory());
            ModelloListeOggetto listaOggetti = new ModelloListeOggetto(daoOggetto.selectObjectByShop(trisNegozioIndirizzoImmagine.getL().getId()));
            ModelloListeOrdine listaOrdini = new ModelloListeOrdine(daoOrdine.selectOrderRecivedBySellerIdShopIDNewstToOldes(utente.getId(),idNegozio));
            ModelloListeTipoSpedizione listaTipiSpedizione = new ModelloListeTipoSpedizione(daoTipoSpedizione.selectDeliveryTypesByIdN(idNegozio));
            
            request.setAttribute("negozio", trisNegozioIndirizzoImmagine.getL());
            request.setAttribute("indirizzo", trisNegozioIndirizzoImmagine.getC());
            request.setAttribute("immagine", trisNegozioIndirizzoImmagine.getR());
            request.setAttribute("categorie", listaCategorie);
            request.setAttribute("listaOggeti", listaOggetti);
            request.setAttribute("listaOrdini", listaOrdini);
            request.setAttribute("listaTipiSpedizione", listaTipiSpedizione);
            forward = SHOPPAGE;
        }
        else if(action.equalsIgnoreCase("deleteSpedizione")){
            int idNegozio = ((ModelloNegozio)request.getSession().getAttribute("negozio")).getId();
            ModelloUtente utente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            ModelloTipoSpedizione spedizione = new ModelloTipoSpedizione();
            spedizione.setIdS(Integer.parseInt(request.getParameter("modifySpedizione")));
            
            int numeroSpedizioniRimaste = daoTipoSpedizione.selectDeliveryTypesByIdN(idNegozio).size();
            
            if(numeroSpedizioniRimaste > 1){
                spedizione = daoTipoSpedizione.selectDeliveryTypesByIdS(spedizione.getIdS()).get(0);

                String nomeScritto = request.getParameter("modifyDelete");
                if(!nomeScritto.isEmpty()){
                    if(nomeScritto.equals(spedizione.getNome())){
                        boolean oggettoSpedizioneAssegnata = daoSpedizioneOggetto.exist(spedizione.getIdS(),idNegozio);
                        if(!oggettoSpedizioneAssegnata){
                            boolean cancellaTipoSpedizione = daoTipoSpedizione.deleteSpedizione(spedizione.getIdS());
                            if(!cancellaTipoSpedizione)
                                request.setAttribute("spedizioneModificata", 4); 
                            else
                                request.setAttribute("spedizioneModificata", 3); 
                        } else {
                            request.setAttribute("spedizioneModificata", 6);
                        }
                    } else {
                        request.setAttribute("spedizioneModificata", 2); 
                    }
                } else {
                    request.setAttribute("spedizioneModificata", 1); 
                }
            } else {
                request.setAttribute("spedizioneModificata", 5); 
            }
            
            tris<ModelloNegozio, ModelloIndirizzo, ModelloImmagineNegozio> trisNegozioIndirizzoImmagine = daoNegozio.selectStoreAddressImageByStoreID(idNegozio);
            ModelloListeCategoria listaCategorie = new ModelloListeCategoria(daoCategoria.selectAllCategory());
            ModelloListeOggetto listaOggetti = new ModelloListeOggetto(daoOggetto.selectObjectByShop(trisNegozioIndirizzoImmagine.getL().getId()));
            ModelloListeOrdine listaOrdini = new ModelloListeOrdine(daoOrdine.selectOrderRecivedBySellerIdShopIDNewstToOldes(utente.getId(),idNegozio));
            ModelloListeTipoSpedizione listaTipiSpedizione = new ModelloListeTipoSpedizione(daoTipoSpedizione.selectDeliveryTypesByIdN(idNegozio));
            
            request.setAttribute("negozio", trisNegozioIndirizzoImmagine.getL());
            request.setAttribute("indirizzo", trisNegozioIndirizzoImmagine.getC());
            request.setAttribute("immagine", trisNegozioIndirizzoImmagine.getR());
            request.setAttribute("categorie", listaCategorie);
            request.setAttribute("listaOggeti", listaOggetti);
            request.setAttribute("listaOrdini", listaOrdini);
            request.setAttribute("listaTipiSpedizione", listaTipiSpedizione);
            forward = SHOPPAGE;
        }
        else if(action.equalsIgnoreCase("richiestaPaginaDiModificaOrdine")){
            String idOrdine = request.getParameter("idOrdine");
            String idOggetto = request.getParameter("idOggetto");
            ModelloOrdine ordine = daoOrdine.selectOrdersByIdOrderIdOggetto(idOrdine,idOggetto);
            ModelloOggetto oggetto = daoOggetto.getObjectById(idOggetto);
            if(ordine.getIdS() > 0){
                ModelloTipoSpedizione tipoSpedizione = daoTipoSpedizione.selectDeliveryTypesByIdS(ordine.getIdS()).get(0);
                ModelloIndirizzo indirizzo = daoIndirizzo.selectAddressByIdAddress(ordine.getIdI());
                request.setAttribute("ordine", ordine);
                request.setAttribute("oggetto", oggetto);
                request.setAttribute("tipoSpedizione", tipoSpedizione);
                request.setAttribute("indirizzo", indirizzo);
            } else {
                request.setAttribute("ordine", ordine);
                request.setAttribute("oggetto", oggetto);
            }
            forward=MODIFICAORDINE;
        }
        else if(action.equalsIgnoreCase("articoloRitirato")){
            String idOrdine = request.getParameter("idOrdine");
            String idOggetto = request.getParameter("idOggetto");
            ModelloOrdine ordine = daoOrdine.selectOrdersByIdOrderIdOggetto(idOrdine,idOggetto);
            ModelloOggetto oggetto = daoOggetto.getObjectById(idOggetto);
            ModelloUtente utente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");

            //daoOrdine.updateOrderDataArrivoPresunta(ordine,dt);
            daoOrdine.changeOrderStatus(ordine, ordine.getStato(), 4);
            
            int idNegozio = ((ModelloNegozio)request.getSession().getAttribute("negozio")).getId();
            
            tris<ModelloNegozio, ModelloIndirizzo, ModelloImmagineNegozio> trisNegozioIndirizzoImmagine = daoNegozio.selectStoreAddressImageByStoreID(idNegozio);
            ModelloListeCategoria listaCategorie = new ModelloListeCategoria(daoCategoria.selectAllCategory());
            ModelloListeOggetto listaOggetti = new ModelloListeOggetto(daoOggetto.selectObjectByShop(trisNegozioIndirizzoImmagine.getL().getId()));
            ModelloListeOrdine listaOrdini = new ModelloListeOrdine(daoOrdine.selectOrderRecivedBySellerIdShopIDNewstToOldes(utente.getId(),idNegozio));
            ModelloListeTipoSpedizione listaTipiSpedizione = new ModelloListeTipoSpedizione(daoTipoSpedizione.selectDeliveryTypesByIdN(idNegozio));
            
            request.setAttribute("negozio", trisNegozioIndirizzoImmagine.getL());
            request.setAttribute("indirizzo", trisNegozioIndirizzoImmagine.getC());
            request.setAttribute("immagine", trisNegozioIndirizzoImmagine.getR());
            request.setAttribute("categorie", listaCategorie);
            request.setAttribute("listaOggeti", listaOggetti);
            request.setAttribute("ordineModificato", 0);
            request.setAttribute("listaOrdini", listaOrdini);
            request.setAttribute("listaTipiSpedizione", listaTipiSpedizione);
            forward = SHOPPAGE;
        }
        else if(action.equalsIgnoreCase("portaOrdineInLavorazione")){
            String idOrdine = request.getParameter("idOrdine");
            String idOggetto = request.getParameter("idOggetto");
            ModelloOrdine ordine = daoOrdine.selectOrdersByIdOrderIdOggetto(idOrdine,idOggetto);
            ModelloOggetto oggetto = daoOggetto.getObjectById(idOggetto);
            ModelloTipoSpedizione tipoSpedizione = daoTipoSpedizione.selectDeliveryTypesByIdS(ordine.getIdS()).get(0);
            ModelloUtente utente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, tipoSpedizione.getTempoRichiesto());  // number of days to add
            String dt = sdf.format(c.getTime());  // dt is now the new date
            ordine.setDataArrivoPresunta(c.getTime());
            
            //daoOrdine.updateOrderDataArrivoPresunta(ordine,dt);
            daoOrdine.changeOrderStatus(ordine, ordine.getStato(), 2);
            SendEmail.ordineInLavorazione(utente.getMail(),ordine,oggetto);
            
            int idNegozio = ((ModelloNegozio)request.getSession().getAttribute("negozio")).getId();
            
            tris<ModelloNegozio, ModelloIndirizzo, ModelloImmagineNegozio> trisNegozioIndirizzoImmagine = daoNegozio.selectStoreAddressImageByStoreID(idNegozio);
            ModelloListeCategoria listaCategorie = new ModelloListeCategoria(daoCategoria.selectAllCategory());
            ModelloListeOggetto listaOggetti = new ModelloListeOggetto(daoOggetto.selectObjectByShop(trisNegozioIndirizzoImmagine.getL().getId()));
            ModelloListeOrdine listaOrdini = new ModelloListeOrdine(daoOrdine.selectOrderRecivedBySellerIdShopIDNewstToOldes(utente.getId(),idNegozio));
            ModelloListeTipoSpedizione listaTipiSpedizione = new ModelloListeTipoSpedizione(daoTipoSpedizione.selectDeliveryTypesByIdN(idNegozio));
            
            request.setAttribute("negozio", trisNegozioIndirizzoImmagine.getL());
            request.setAttribute("indirizzo", trisNegozioIndirizzoImmagine.getC());
            request.setAttribute("immagine", trisNegozioIndirizzoImmagine.getR());
            request.setAttribute("categorie", listaCategorie);
            request.setAttribute("listaOggeti", listaOggetti);
            request.setAttribute("ordineModificato", 0);
            request.setAttribute("listaOrdini", listaOrdini);
            request.setAttribute("listaTipiSpedizione", listaTipiSpedizione);
            forward = SHOPPAGE;
        }
        else if(action.equalsIgnoreCase("portaOrdineInSpedizione")){
            String idOrdine = request.getParameter("idOrdine");
            String idOggetto = request.getParameter("idOggetto");
            ModelloOrdine ordine = daoOrdine.selectOrdersByIdOrderIdOggetto(idOrdine,idOggetto);
            ModelloOggetto oggetto = daoOggetto.getObjectById(idOggetto);
            ModelloTipoSpedizione tipoSpedizione = daoTipoSpedizione.selectDeliveryTypesByIdS(ordine.getIdS()).get(0);
            daoOrdine.changeOrderStatus(ordine, ordine.getStato(), 3);
            ModelloUtente utente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            
            String tracking = request.getParameter("codiceTracking");
            if(!tracking.isEmpty()){
                daoOrdine.updateOrderTracking(ordine, tracking);
            }
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, tipoSpedizione.getTempoRichiesto());  // number of days to add
            String dt = sdf.format(c.getTime());  // dt is now the new date
            ordine.setDataArrivoPresunta(c.getTime());
            
            daoOrdine.updateOrderDataArrivoPresunta(ordine,dt);
            SendEmail.ordineSpedito(utente.getMail(),ordine,oggetto);
            
            int idNegozio = ((ModelloNegozio)request.getSession().getAttribute("negozio")).getId();
            
            tris<ModelloNegozio, ModelloIndirizzo, ModelloImmagineNegozio> trisNegozioIndirizzoImmagine = daoNegozio.selectStoreAddressImageByStoreID(idNegozio);
            ModelloListeCategoria listaCategorie = new ModelloListeCategoria(daoCategoria.selectAllCategory());
            ModelloListeOggetto listaOggetti = new ModelloListeOggetto(daoOggetto.selectObjectByShop(trisNegozioIndirizzoImmagine.getL().getId()));
            ModelloListeOrdine listaOrdini = new ModelloListeOrdine(daoOrdine.selectOrderRecivedBySellerIdShopIDNewstToOldes(utente.getId(),idNegozio));
            ModelloListeTipoSpedizione listaTipiSpedizione = new ModelloListeTipoSpedizione(daoTipoSpedizione.selectDeliveryTypesByIdN(idNegozio));
            
            request.setAttribute("negozio", trisNegozioIndirizzoImmagine.getL());
            request.setAttribute("indirizzo", trisNegozioIndirizzoImmagine.getC());
            request.setAttribute("immagine", trisNegozioIndirizzoImmagine.getR());
            request.setAttribute("categorie", listaCategorie);
            request.setAttribute("listaOggeti", listaOggetti);
            request.setAttribute("ordineModificato", 0);
            request.setAttribute("listaOrdini", listaOrdini);
            request.setAttribute("listaTipiSpedizione", listaTipiSpedizione);
            forward = SHOPPAGE;
        }
        else if(action.equalsIgnoreCase("richiestaPaginaDiModificaTipoSpedizione")){
            int idS = Integer.parseInt(request.getParameter("id"));
            ModelloTipoSpedizione tipoSpedizione = daoTipoSpedizione.selectDeliveryTypesByIdS(idS).get(0);
            request.setAttribute("spedizione", tipoSpedizione);
            forward = MODIFYTIPOSPEDIZIONE;
        }
        else {
            log("ACTION NON TROVATA");
            
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
        String forward="";
        String action = request.getParameter("action");
        request.setCharacterEncoding("UTF-8");
        
        if (action.equalsIgnoreCase("addNegozio")) {
            ModelloUtente utente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            ModelloIndirizzo indirizzo = new ModelloIndirizzo();
            
            indirizzo.setStato("Italia");
            indirizzo.setRegione(request.getParameter("regione"));
            indirizzo.setProvincia(request.getParameter("provincia"));
            indirizzo.setCitta(request.getParameter("citta"));
            indirizzo.setVia(request.getParameter("via"));
            indirizzo.setnCivico(Integer.parseInt(request.getParameter("nCivico")));
            indirizzo.setInterno(Integer.parseInt(request.getParameter("interno")));

            indirizzo.setLatitudine(Double.parseDouble(request.getParameter("latitudine")));
            indirizzo.setLongitudine(Double.parseDouble(request.getParameter("longitudine")));
            
            boolean indirizzoInserito = daoIndirizzo.insertAddress(indirizzo);
            
            if(!indirizzoInserito){
                request.setAttribute("negozioInserito", 1);
            } else {
                List<ModelloIndirizzo> tuttiNegozi = daoIndirizzo.selectAddressLatLng(indirizzo.getLatitudine(), indirizzo.getLongitudine());

                indirizzo.setIdI(tuttiNegozi.get(tuttiNegozi.size() - 1).getIdI());

                for(int i = 0 ; i < tuttiNegozi.size(); i++){
                    log("IdI possibile: " + tuttiNegozi.get(i).getIdI());
                }

                ModelloNegozio negozio = new ModelloNegozio();
                negozio.setIdVenditore(utente.getId());
                negozio.setNomeNegozio(request.getParameter("nomeNegozio"));
                negozio.setAttivo(1);
                negozio.setIdI(indirizzo.getIdI());
                Timestamp timestampNow = new Timestamp(System.currentTimeMillis());
                negozio.setDataApertura(timestampNow);
                negozio.setLinkSito(request.getParameter("linkNegozio"));

                String orario = "";
                
                
                if(request.getParameter("orarioAperturaNegozioLunedi").isEmpty() || request.getParameter("orarioChiusuraNegozioLunedi").isEmpty()){
                    orario += "Lunedì: chiuso";
                } else {
                    orario += "Lunedì: " + ((request.getParameter("chiusoLunedi") == null) ? request.getParameter("orarioAperturaNegozioLunedi") + " - " + request.getParameter("orarioChiusuraNegozioLunedi") : "Chiuso") + "";
                }
                
                if(request.getParameter("orarioAperturaNegozioMartedi").isEmpty() || request.getParameter("orarioChiusuraNegozioMartedi").isEmpty()){
                    orario += ", Martedì: chiuso";
                } else {
                    orario += ", Martedì: " + ((request.getParameter("chiusoMartedi") == null) ? request.getParameter("orarioAperturaNegozioMartedi") + " - " + request.getParameter("orarioChiusuraNegozioMartedi") : "Chiuso") + "";
                }
                if(request.getParameter("orarioAperturaNegozioMercoledi").isEmpty() || request.getParameter("orarioChiusuraNegozioMercoledi").isEmpty()){
                    orario += ", Mercoledì: chiuso";
                } else {
                    orario += ", Mercoledì: " + ((request.getParameter("chiusoMercoledi") == null) ? request.getParameter("orarioAperturaNegozioMercoledi") + " - " + request.getParameter("orarioChiusuraNegozioMercoledi") : "Chiuso") + "";
                }
                if(request.getParameter("orarioAperturaNegozioGiovedi").isEmpty() || request.getParameter("orarioChiusuraNegozioGiovedi").isEmpty()){
                    orario += ", Giovedì: chiuso";
                } else {
                    orario += ", Giovedì: " + ((request.getParameter("chiusoGiovedi") == null) ? request.getParameter("orarioAperturaNegozioGiovedi") + " - " + request.getParameter("orarioChiusuraNegozioGiovedi") : "Chiuso") + "";
                }
                if(request.getParameter("orarioAperturaNegozioVenerdi").isEmpty() || request.getParameter("orarioChiusuraNegozioVenerdi").isEmpty()){
                    orario += ", Venerdì: chiuso";
                } else {
                    orario += ", Venerdì: " + ((request.getParameter("chiusoVenerdi") == null) ? request.getParameter("orarioAperturaNegozioVenerdi") + " - " + request.getParameter("orarioChiusuraNegozioVenerdi") : "Chiuso") + "";
                }
                if(request.getParameter("orarioAperturaNegozioSabato").isEmpty() || request.getParameter("orarioChiusuraNegozioSabato").isEmpty()){
                    orario += ", Sabato: chiuso";
                } else {
                    orario += ", Sabato: " + ((request.getParameter("chiusoSabato") == null) ? request.getParameter("orarioAperturaNegozioSabato") + " - " + request.getParameter("orarioChiusuraNegozioSabato") : "Chiuso") + "";
                }
                if(request.getParameter("orarioAperturaNegozioDomenica").isEmpty() || request.getParameter("orarioChiusuraNegozioDomenica").isEmpty()){
                    orario += ", Domenica: chiuso";
                } else {
                    orario += ", Domenica: " + ((request.getParameter("chiusoDomenica") == null) ? request.getParameter("orarioAperturaNegozioDomenica") + " - " + request.getParameter("orarioChiusuraNegozioDomenica") : "Chiuso") + "";
                }

                negozio.setOrarioNegozio(orario);

                boolean negozioInserito =daoNegozio.insertShop(negozio);
                if(negozioInserito){
                    List<ModelloNegozio> listaNegozi = daoNegozio.selectShopByOpenDate(utente.getId());
                    negozio.setId(listaNegozi.get(0).getId());

                    daoNegozio.insertShopImage(negozio.getId(), "http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/imageNegozio.png");
                    request.setAttribute("negozioInserito", 0);
                    
                    ModelloTipoSpedizione spedizione = new ModelloTipoSpedizione();
                    int idNegozio = negozio.getId();

                    spedizione.setIdN(idNegozio);

                    request.setAttribute("aggiungiSepdizione", 0);

                    String nome = request.getParameter("nomeSpedizione");
                    if(!nome.isEmpty()){
                        spedizione.setNome(request.getParameter("nomeSpedizione"));
                    } else {
                        request.setAttribute("aggiungiSepdizione", 1);
                    }

                    String prezzo = request.getParameter("prezzoSpedizione");
                    if(!prezzo.isEmpty()){
                        spedizione.setPrezzo(Double.parseDouble(request.getParameter("prezzoSpedizione")));
                    } else {
                        request.setAttribute("aggiungiSepdizione", 1);
                    }

                    String corriere = request.getParameter("corriereSpedizione");
                    if(!corriere.isEmpty()){
                        spedizione.setCorriere(request.getParameter("corriereSpedizione"));
                    } else {
                        request.setAttribute("aggiungiSepdizione", 1);
                    }

                    int tempoRichiesto = (Integer.parseInt(request.getParameter("giorniConsegna")));
                    if(tempoRichiesto>0){
                        spedizione.setTempoRichiesto(tempoRichiesto);
                    } else {
                        request.setAttribute("aggiungiSepdizione", 1);
                    }

                    int numeroMassimo = (Integer.parseInt(request.getParameter("numeroMassimo")));
                    if(numeroMassimo>0){
                        spedizione.setNumeroMassimo(numeroMassimo);
                    } else {
                        request.setAttribute("aggiungiSepdizione", 1);
                    }

                    if((int)request.getAttribute("aggiungiSepdizione") == 0){
                        boolean aggiungiSpedizione = daoTipoSpedizione.addSpedizione(spedizione);
                        if(!aggiungiSpedizione)
                            request.setAttribute("aggiungiSepdizione", 1);
                    }
                    
                }
                else {
                    request.setAttribute("negozioInserito", 1);
                }
            }
            forward = ADDSHOPPAGE;
        }
        else if(action.equalsIgnoreCase("cancNegozio")){
            int idNegozio = Integer.parseInt(request.getParameter("id"));
            ModelloUtente utente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            
            boolean negozioChiuso = daoNegozio.updateShopStatus(idNegozio,0);
            
            if(!negozioChiuso){
                request.setAttribute("impossibileChiudereNegozio", 1);
            }
            
            ModelloListeNegozio listanegozi = new ModelloListeNegozio();
            List<Integer> numeroOrdiniNegozi = new ArrayList<>();
            
            listanegozi = new ModelloListeNegozio(daoNegozio.selectShopByOpenDate(utente.getId()));
            for(int i = 0; i<listanegozi.getList().size(); i++){
                    numeroOrdiniNegozi.add(daoOrdiniRicevuti.selectNumberOfOrderForStore(utente.getId(), listanegozi.get(i).getId()));
            }
            
            request.setAttribute("listanegozi", listanegozi);
            request.setAttribute("numeroOrdiniNegozi", numeroOrdiniNegozi);
            forward = INFONEGOZI;
        }
        else if(action.equalsIgnoreCase("apriNegozio")){
            int idNegozio = Integer.parseInt(request.getParameter("id"));
            ModelloUtente utente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            
            boolean negozioAperto = daoNegozio.updateShopStatus(idNegozio,1);
            
            if(!negozioAperto){
                request.setAttribute("impossibileAprireNegozio", 1);
            }
            
            ModelloListeNegozio listanegozi = new ModelloListeNegozio();
            List<Integer> numeroOrdiniNegozi = new ArrayList<>();
            
            listanegozi = new ModelloListeNegozio(daoNegozio.selectShopByOpenDate(utente.getId()));
            for(int i = 0; i<listanegozi.getList().size(); i++){
                    numeroOrdiniNegozi.add(daoOrdiniRicevuti.selectNumberOfOrderForStore(utente.getId(), listanegozi.get(i).getId()));
            }
            
            request.setAttribute("listanegozi", listanegozi);
            request.setAttribute("numeroOrdiniNegozi", numeroOrdiniNegozi);
            forward = INFONEGOZI;
        }
        else if(action.equalsIgnoreCase("gestisciNegozio")){
            ModelloUtente utente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            int idNegozio = Integer.parseInt(request.getParameter("id"));
            tris<ModelloNegozio, ModelloIndirizzo, ModelloImmagineNegozio> trisNegozioIndirizzoImmagine = daoNegozio.selectStoreAddressImageByStoreID(idNegozio);
            ModelloListeCategoria listaCategorie = new ModelloListeCategoria(daoCategoria.selectAllCategory());
            ModelloListeOggetto listaOggetti = new ModelloListeOggetto(daoOggetto.selectObjectByShop(trisNegozioIndirizzoImmagine.getL().getId()));
            ModelloListeOrdine listaOrdini = new ModelloListeOrdine(daoOrdine.selectOrderRecivedBySellerIdShopIDNewstToOldes(utente.getId(),idNegozio));
            ModelloListeTipoSpedizione listaTipiSpedizione = new ModelloListeTipoSpedizione(daoTipoSpedizione.selectDeliveryTypesByIdN(idNegozio));
            
            request.getSession().removeAttribute("negozio");
            request.getSession().setAttribute("negozio", trisNegozioIndirizzoImmagine.getL());
            request.setAttribute("indirizzo", trisNegozioIndirizzoImmagine.getC());
            request.setAttribute("immagine", trisNegozioIndirizzoImmagine.getR());
            request.setAttribute("categorie", listaCategorie);
            request.setAttribute("listaOggeti", listaOggetti);
            request.setAttribute("listaOrdini", listaOrdini);
            request.setAttribute("listaTipiSpedizione", listaTipiSpedizione);
            forward = SHOPPAGE;
        }
        else if(action.equalsIgnoreCase("addObject")){
            
            ModelloUtente utente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            ModelloOggetto newObject = new ModelloOggetto();
            
            newObject.setCategoria(Integer.parseInt(request.getParameter("selectCategoria")));
            newObject.setDisponibilita(Integer.parseInt(request.getParameter("disponibilita")));
            newObject.setStatoDisponibilita(Integer.parseInt(request.getParameter("selectDisponibilita")));
            newObject.setDescrizione(request.getParameter("descrizione"));
            newObject.setIdNegozio(Integer.parseInt(request.getParameter("idNegozio")));
            newObject.setNome(request.getParameter("nomeOggetto"));
            newObject.setNomeDownCase(newObject.getNome().toLowerCase());
            newObject.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
            newObject.setRitiroInNegozio((request.getParameter("ritironegozio") == null) ? 0 : 1);
            if(request.getParameter("scontoAttivo") != null){
                newObject.setSconto(Integer.parseInt(request.getParameter("percentualeSconto")));
                newObject.setDataFineSconto(Date.valueOf(request.getParameter("dataFineSconto")));
            } else {
                newObject.setSconto(0);
                newObject.setDateToNull();
            }
            
            String original = newObject.getNome() + String.valueOf(newObject.getIdNegozio());
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

            newObject.setId(converted);
            ModelloListeTipoSpedizione listaTipiSpedizione = new ModelloListeTipoSpedizione(daoTipoSpedizione.selectDeliveryTypesByIdN(Integer.parseInt(request.getParameter("idNegozio"))));
            
            boolean inserimentoAvvenuto = daoOggetto.insertObject(newObject.getId(),newObject.getIdNegozio(), newObject.getNome(),newObject.getNomeDownCase(), newObject.getPrezzo(), newObject.getDescrizione(), newObject.getRitiroInNegozio(), newObject.getDisponibilita(), newObject.getStatoDisponibilita(), newObject.getSconto(), newObject.getDataFineSconto(), newObject.getCategoria());
            if(inserimentoAvvenuto){
                immaginiOggetto.clear();
                imageSrcs.clear();
                if(getImages(request, response)) {
                    for (String src : imageSrcs) {
                        ModelloImmagineOggetto immagineOggettoNew = new ModelloImmagineOggetto();
                        immagineOggettoNew.setIdO(newObject.getId());
                        immagineOggettoNew.setSrc(src);
                        daoImmagineOggetto.addImageToObject(immagineOggettoNew);
                    }
                }
                
                daoCategoria.increaseCategory(newObject.getCategoria());
                
                int k=0;
                for(int i=0;i<listaTipiSpedizione.getList().size();i++){
                    if(request.getParameter("checkbox-"+listaTipiSpedizione.get(i).getIdS()) != null){
                        daoSpedizioneOggetto.addSpedizioneOggetto(listaTipiSpedizione.get(i).getIdS(),newObject.getId());
                        k++;
                    }
                }
                
                if(k==0){
                    daoSpedizioneOggetto.addSpedizioneOggetto(listaTipiSpedizione.get(0).getIdS(),newObject.getId());
                }
                
                request.setAttribute("oggettoInserito", 0);
            } else {
                request.setAttribute("oggettoInserito", 1);
            }
            
            tris<ModelloNegozio, ModelloIndirizzo, ModelloImmagineNegozio> trisNegozioIndirizzoImmagine = daoNegozio.selectStoreAddressImageByStoreID(Integer.parseInt(request.getParameter("idNegozio")));
            ModelloListeCategoria listaCategorie = new ModelloListeCategoria(daoCategoria.selectAllCategory());
            ModelloListeOggetto listaOggetti = new ModelloListeOggetto(daoOggetto.selectObjectByShop(trisNegozioIndirizzoImmagine.getL().getId()));
            ModelloListeOrdine listaOrdini = new ModelloListeOrdine(daoOrdine.selectOrderRecivedBySellerIdShopIDNewstToOldes(utente.getId(),Integer.parseInt(request.getParameter("idNegozio"))));
            
            request.setAttribute("negozio", trisNegozioIndirizzoImmagine.getL());
            request.setAttribute("indirizzo", trisNegozioIndirizzoImmagine.getC());
            request.setAttribute("immagine", trisNegozioIndirizzoImmagine.getR());
            request.setAttribute("categorie", listaCategorie);
            request.setAttribute("listaOggeti", listaOggetti);
            request.setAttribute("listaOrdini", listaOrdini);
            request.setAttribute("listaTipiSpedizione", listaTipiSpedizione);
            forward = SHOPPAGE;
        }
        else if(action.equalsIgnoreCase("modifyObject")){
            ModelloUtente utente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            ModelloOggetto object = new ModelloOggetto();
            int idNegozio = ((ModelloNegozio)request.getSession().getAttribute("negozio")).getId();
            
            object.setId(request.getParameter("modifyObject"));
            ModelloListeTipoSpedizione listaTipiSpedizione = new ModelloListeTipoSpedizione(daoTipoSpedizione.selectDeliveryTypesByIdN(idNegozio));
            if(object.getId().equals("0")){
                request.setAttribute("oggettoModificato", 2);
            }
            else {
                
                object = daoOggetto.getObjectById(object.getId());
                if(Integer.parseInt(request.getParameter("modifyCategoria")) != 0){
                   if(Integer.parseInt(request.getParameter("modifyCategoria")) != object.getCategoria()){
                       daoCategoria.decraseCategory(object.getCategoria());
                       object.setCategoria(Integer.parseInt(request.getParameter("modifyCategoria")));
                       daoCategoria.increaseCategory(object.getCategoria());
                   } 
                }
                
                if(Integer.parseInt(request.getParameter("modifyDisponibilita")) != -1){
                    if(Integer.parseInt(request.getParameter("modifyDisponibilita")) >= 0){
                        object.setDisponibilita(Integer.parseInt(request.getParameter("modifyDisponibilita")));
                    } else {
                        object.setDisponibilita(0);
                    }
                }
                
                if(Integer.parseInt(request.getParameter("modifySelectDisponibilita")) != -1) {
                    object.setStatoDisponibilita(Integer.parseInt(request.getParameter("modifySelectDisponibilita")));
                }
                
                String descrizione = request.getParameter("modifyDescrizione");
                if(!descrizione.isEmpty()){
                    object.setDescrizione(request.getParameter("modifyDescrizione"));
                }
                
                String nome = request.getParameter("mdifynomeOggetto");
                if(!nome.isEmpty()){
                    object.setNome(request.getParameter("mdifynomeOggetto"));
                    object.setNomeDownCase(object.getNome().toLowerCase());
                }
                
                String prezzo = request.getParameter("modifyPrezzo");
                if(!prezzo.isEmpty()){
                    object.setPrezzo(Double.parseDouble(request.getParameter("modifyPrezzo")));
                }
                
                if(Integer.parseInt(request.getParameter("modifyRitiroInNegozio")) != -1){
                    object.setRitiroInNegozio((request.getParameter("modifyRitiroInNegozio") == null) ? 0 : 1);
                }
                
                if(object.getSconto() == 0){
                    if(request.getParameter("modifyScontoAttivo") != null){
                        object.setSconto(Integer.parseInt(request.getParameter("modifyPercentualeSconto")));
                        object.setDataFineSconto(Date.valueOf(request.getParameter("modifyDataFineSconto")));
                    } else {
                        object.setSconto(0);
                        object.setDateToNull();
                    }
                }

                String original = object.getNome() + String.valueOf(object.getIdNegozio());
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

                String previusId = object.getId();
                object.setId(converted);
                
                boolean modificaOggetto = daoOggetto.updateObject(object,previusId);
                
                if(modificaOggetto) {
                    immaginiOggetto.clear();
                    immaginiOggetto = daoImmagineOggetto.selectPhotoObject(object.getId());
                    for (ModelloImmagineOggetto immagineOggetto : immaginiOggetto) {
                        daoImmagineOggetto.remImageToObject(immagineOggetto);
                        if(!immagineOggetto.getSrc().contains("objectImage.png")) {
                            if(tempAvailable(immagineOggetto.getSrc(), request)) {
                                immagineOggetto.setIdO(object.getId());
                                immagineOggetto.setSrc(immagineOggetto.getSrc());
                                daoImmagineOggetto.addImageToObject(immagineOggetto);
                            }
                        }
                    }
                    
                    imageSrcs.clear();
                    if(getImages(request, response)) {
                        for (String src : imageSrcs) {
                            ModelloImmagineOggetto immagineOggettoNew = new ModelloImmagineOggetto();
                            immagineOggettoNew.setIdO(object.getId());
                            immagineOggettoNew.setSrc(src);
                            daoImmagineOggetto.addImageToObject(immagineOggettoNew);
                        }
                    }

                    int k=0;
                    for(int i=0;i<listaTipiSpedizione.getList().size();i++){
                        if(request.getParameter("checkbox-"+listaTipiSpedizione.get(i).getIdS()) != null){
                            daoSpedizioneOggetto.addSpedizioneOggetto(listaTipiSpedizione.get(i).getIdS(),object.getId());
                            k++;
                        } else {
                            daoSpedizioneOggetto.deleteSpedizioneOggetto(listaTipiSpedizione.get(i).getIdS(),object.getId());
                        }
                    }

                    if(k==0){
                        daoSpedizioneOggetto.addSpedizioneOggetto(listaTipiSpedizione.get(0).getIdS(),object.getId());
                    }
                    
                    request.setAttribute("oggettoModificato", 0);
                } else {
                    request.setAttribute("oggettoModificato", 1);
                }
            }
            
            tris<ModelloNegozio, ModelloIndirizzo, ModelloImmagineNegozio> trisNegozioIndirizzoImmagine = daoNegozio.selectStoreAddressImageByStoreID(idNegozio);
            ModelloListeCategoria listaCategorie = new ModelloListeCategoria(daoCategoria.selectAllCategory());
            ModelloListeOggetto listaOggetti = new ModelloListeOggetto(daoOggetto.selectObjectByShop(trisNegozioIndirizzoImmagine.getL().getId()));
            ModelloListeOrdine listaOrdini = new ModelloListeOrdine(daoOrdine.selectOrderRecivedBySellerIdShopIDNewstToOldes(utente.getId(),idNegozio));
            
            
            request.setAttribute("negozio", trisNegozioIndirizzoImmagine.getL());
            request.setAttribute("indirizzo", trisNegozioIndirizzoImmagine.getC());
            request.setAttribute("immagine", trisNegozioIndirizzoImmagine.getR());
            request.setAttribute("categorie", listaCategorie);
            request.setAttribute("listaOggeti", listaOggetti);
            request.setAttribute("listaOrdini", listaOrdini);
            request.setAttribute("listaTipiSpedizione", listaTipiSpedizione);
            forward = SHOPPAGE;
        }
        else if(action.equalsIgnoreCase("modifyTipoSpedizione")){
            ModelloUtente utente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            ModelloTipoSpedizione spedizione = new ModelloTipoSpedizione();
            int idNegozio = ((ModelloNegozio)request.getSession().getAttribute("negozio")).getId();
            
            spedizione.setIdS(Integer.parseInt(request.getParameter("modifyTipoSpedizione")));
            
            spedizione = daoTipoSpedizione.selectDeliveryTypesByIdS(spedizione.getIdS()).get(0);
            
            String nome = request.getParameter("mdifyNomeSpedizione");
            if(!nome.isEmpty()){
                spedizione.setNome(request.getParameter("mdifyNomeSpedizione"));
            }
            
            String prezzo = request.getParameter("mdifyPrezzoSpedizione");
            if(!prezzo.isEmpty()){
                spedizione.setPrezzo(Double.parseDouble(request.getParameter("mdifyPrezzoSpedizione")));
            }
            
            String corriere = request.getParameter("mdifyCorriereSpedizione");
            if(!corriere.isEmpty()){
                spedizione.setCorriere(request.getParameter("mdifyCorriereSpedizione"));
            }
            
            int tempoRichiesto = (Integer.parseInt(request.getParameter("modifyGiorniConsegna")));
            if(tempoRichiesto>0){
                spedizione.setTempoRichiesto(tempoRichiesto);
            }
            
            int numeroMassimo = (Integer.parseInt(request.getParameter("modifyNumeroMassimo")));
            if(numeroMassimo>0){
                spedizione.setNumeroMassimo(numeroMassimo);
            }
            
            boolean modificaSpedizione = daoTipoSpedizione.updateSpedizione(spedizione);
            
            if(modificaSpedizione)
                request.setAttribute("spedizioneModificata", 0);
            else
                request.setAttribute("spedizioneModificata", 4);
            
            tris<ModelloNegozio, ModelloIndirizzo, ModelloImmagineNegozio> trisNegozioIndirizzoImmagine = daoNegozio.selectStoreAddressImageByStoreID(idNegozio);
            ModelloListeCategoria listaCategorie = new ModelloListeCategoria(daoCategoria.selectAllCategory());
            ModelloListeOggetto listaOggetti = new ModelloListeOggetto(daoOggetto.selectObjectByShop(trisNegozioIndirizzoImmagine.getL().getId()));
            ModelloListeOrdine listaOrdini = new ModelloListeOrdine(daoOrdine.selectOrderRecivedBySellerIdShopIDNewstToOldes(utente.getId(),idNegozio));
            ModelloListeTipoSpedizione listaTipiSpedizione = new ModelloListeTipoSpedizione(daoTipoSpedizione.selectDeliveryTypesByIdN(idNegozio));
            
            request.setAttribute("negozio", trisNegozioIndirizzoImmagine.getL());
            request.setAttribute("indirizzo", trisNegozioIndirizzoImmagine.getC());
            request.setAttribute("immagine", trisNegozioIndirizzoImmagine.getR());
            request.setAttribute("categorie", listaCategorie);
            request.setAttribute("listaOggeti", listaOggetti);
            request.setAttribute("listaOrdini", listaOrdini);
            request.setAttribute("listaTipiSpedizione", listaTipiSpedizione);
            forward = SHOPPAGE;
        }
        else if(action.equalsIgnoreCase("addTipoSpedizione")){
            ModelloUtente utente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            ModelloTipoSpedizione spedizione = new ModelloTipoSpedizione();
            int idNegozio = ((ModelloNegozio)request.getSession().getAttribute("negozio")).getId();
            
            spedizione.setIdN(idNegozio);
            
            request.setAttribute("aggiungiSepdizione", 0);
            
            String nome = request.getParameter("nomeSpedizione");
            if(!nome.isEmpty()){
                spedizione.setNome(request.getParameter("nomeSpedizione"));
            } else {
                request.setAttribute("aggiungiSepdizione", 1);
            }
            
            String prezzo = request.getParameter("prezzoSpedizione");
            if(!prezzo.isEmpty()){
                spedizione.setPrezzo(Double.parseDouble(request.getParameter("prezzoSpedizione")));
            } else {
                request.setAttribute("aggiungiSepdizione", 1);
            }
            
            String corriere = request.getParameter("corriereSpedizione");
            if(!corriere.isEmpty()){
                spedizione.setCorriere(request.getParameter("corriereSpedizione"));
            } else {
                request.setAttribute("aggiungiSepdizione", 1);
            }
            
            int tempoRichiesto = (Integer.parseInt(request.getParameter("giorniConsegna")));
            if(tempoRichiesto>0){
                spedizione.setTempoRichiesto(tempoRichiesto);
            } else {
                request.setAttribute("aggiungiSepdizione", 1);
            }
            
            int numeroMassimo = (Integer.parseInt(request.getParameter("numeroMassimo")));
            if(numeroMassimo>0){
                spedizione.setNumeroMassimo(numeroMassimo);
            } else {
                request.setAttribute("aggiungiSepdizione", 1);
            }
            
            if((int)request.getAttribute("aggiungiSepdizione") == 0){
                boolean aggiungiSpedizione = daoTipoSpedizione.addSpedizione(spedizione);
                if(!aggiungiSpedizione)
                    request.setAttribute("aggiungiSepdizione", 1);
            }
            
            tris<ModelloNegozio, ModelloIndirizzo, ModelloImmagineNegozio> trisNegozioIndirizzoImmagine = daoNegozio.selectStoreAddressImageByStoreID(idNegozio);
            ModelloListeCategoria listaCategorie = new ModelloListeCategoria(daoCategoria.selectAllCategory());
            ModelloListeOggetto listaOggetti = new ModelloListeOggetto(daoOggetto.selectObjectByShop(trisNegozioIndirizzoImmagine.getL().getId()));
            ModelloListeOrdine listaOrdini = new ModelloListeOrdine(daoOrdine.selectOrderRecivedBySellerIdShopIDNewstToOldes(utente.getId(),idNegozio));
            ModelloListeTipoSpedizione listaTipiSpedizione = new ModelloListeTipoSpedizione(daoTipoSpedizione.selectDeliveryTypesByIdN(idNegozio));
            
            request.setAttribute("negozio", trisNegozioIndirizzoImmagine.getL());
            request.setAttribute("indirizzo", trisNegozioIndirizzoImmagine.getC());
            request.setAttribute("immagine", trisNegozioIndirizzoImmagine.getR());
            request.setAttribute("categorie", listaCategorie);
            request.setAttribute("listaOggeti", listaOggetti);
            request.setAttribute("listaOrdini", listaOrdini);
            request.setAttribute("listaTipiSpedizione", listaTipiSpedizione);
            forward = SHOPPAGE;
        }
        else {
            log("ACTION NON TROVATA");
            
            forward = ERROR_PAGE;
            request.setAttribute("errore", "Comando non trovato");
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
    
    /**
     * Get uploaded images
     */
    private boolean getImages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + SAVE_DIR;
        String savePathFake = request.getContextPath() + File.separator + SAVE_DIR;
        
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        
        boolean imageSaved = false;
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            if(!fileName.isEmpty()) {
                fileName = new File(fileName).getName();
                part.write(savePath + File.separator + fileName);
                imageSrcs.add(savePathFake + File.separator + fileName);
                imageSaved = true;
            }
        }
        
        return imageSaved;
    }
    
    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
    
    private boolean tempAvailable(String src, HttpServletRequest request) {
        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + SAVE_DIR;
        String fileName = getFileName(src);
        savePath += fileName;
        
        File fileSaveAvailable = new File(savePath);
        if (fileSaveAvailable.exists()) {
            return true;
        }
        
        return false;
    }
    
    private String getFileName (String src) {
        return src.substring(src.indexOf(SAVE_DIR) + SAVE_DIR.length(), src.length());
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
