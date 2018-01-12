/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.progettoWeb.java.Controller.Ordine;

import it.progettoWeb.java.database.Dao.Negozio.DaoNegozio;
import it.progettoWeb.java.database.Dao.Oggetto.DaoOggetto;
import it.progettoWeb.java.database.Dao.Ordine.DaoOrdine;
import it.progettoWeb.java.database.Dao.immagineOggetto.DaoImmagineOggetto;
import it.progettoWeb.java.database.Dao.indirizzo.DaoIndirizzo;
import it.progettoWeb.java.database.Dao.ordiniRicevuti.DaoOrdiniRicevuti;
import it.progettoWeb.java.database.Dao.tipoSpedizione.DaoTipoSpedizione;
import it.progettoWeb.java.database.Model.Negozio.ModelloListeNegozio;
import it.progettoWeb.java.database.Model.Oggetto.ModelloOggetto;
import it.progettoWeb.java.database.Model.Ordine.ModelloListeOrdine;
import it.progettoWeb.java.database.Model.Ordine.ModelloOrdine;
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import it.progettoWeb.java.database.Model.immagineOggetto.ModelloImmagineOggetto;
import it.progettoWeb.java.database.Model.indirizzo.ModelloIndirizzo;
import it.progettoWeb.java.database.Model.indirizzo.ModelloListeIndirizzo;
import it.progettoWeb.java.database.Model.tipoSpedizione.ModelloListeTipoSpedizione;
import it.progettoWeb.java.database.Model.tipoSpedizione.ModelloTipoSpedizione;
import it.progettoWeb.java.utility.javaMail.SendEmail;
import it.progettoWeb.java.utility.pair.pair;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 2017-10-10 14:50
 * @author FBrug
 */
public class OrdineController extends HttpServlet {
    private static String HOME_PAGE = "/jspFile/Finale/Index/index.jsp";
    private static String LIST_ORDERS = "/jspFile/Finale/Ordine/listOrders.jsp";
    private static String DELIVERY_METHOD = "/jspFile/Finale/Ordine/deliveryMethod.jsp";
    private static String PAYMENT_METHOD = "/jspFile/Finale/Ordine/paymentMethod.jsp";
    private static String ORDER_COMPLETED = "/jspFile/Finale/Ordine/orderCompleted.jsp";
    private static String ERROR_PAGE = "/jspFile/Finale/Error/ricercaErrata.jsp";
    private DaoOrdine daoOrdine;
    private DaoOggetto daoOggetto;
    private DaoImmagineOggetto daoImmagineOggetto;
    private DaoNegozio daoNegozio;
    private DaoTipoSpedizione daoTipoSpedizione;
    private DaoIndirizzo daoIndirizzo;
    private DaoOrdiniRicevuti daoOrdiniRicevuti;
    
    
    /**
     * Costruttore della classe OrdineController
    */
    public OrdineController()
    {
        super();
        daoOrdine = new DaoOrdine();
        daoOggetto = new DaoOggetto();
        daoImmagineOggetto = new DaoImmagineOggetto();
        daoNegozio = new DaoNegozio();
        daoTipoSpedizione = new DaoTipoSpedizione();
        daoIndirizzo = new DaoIndirizzo();
        daoOrdiniRicevuti = new DaoOrdiniRicevuti();
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
            throws ServletException, IOException
    {
        String forward = HOME_PAGE;
        forward = ERROR_PAGE; request.setAttribute("errore", "404 Pagina non trovata");
        
        try
        {
            String action = request.getParameter("action");
            
            /*--- PARTE IN COMUNE (PREPARA IL CARRELLO E LE IMMAGINI DEGLI OGGETTI) ---*/
            ModelloUtente utenteSessione = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            ModelloListeOrdine orders = (ModelloListeOrdine)request.getSession().getAttribute("carrelloSessione");
            int utenteSessioneID = utenteSessione.getId();
            
            if(orders.getSize() > 0)
            {
                if(utenteSessioneID != -1)
                {
                    orders = new ModelloListeOrdine(daoOrdine.selectOrdersComplete(utenteSessioneID, 0));
                    request.getSession().setAttribute("utenteSessione", utenteSessione);
                }
                
                request.getSession().removeAttribute("carrelloSessione");
                request.getSession().setAttribute("carrelloSessione", orders);

                //Seleziona oggetti presenti negli ordini + la loro PRIMA immagine
                List<pair<ModelloOggetto, ModelloImmagineOggetto>> objects = new ArrayList<>();
                String idOggetto;
                
                //Recupero NOME del Negozio relativo all'oggetto nell'ordine
                ModelloListeNegozio negozi = new ModelloListeNegozio();

                for (ModelloOrdine order : orders.getList())
                {
                    idOggetto = order.getIdOggetto();
                    objects.add(new pair<>(daoOggetto.getObjectById(idOggetto), daoImmagineOggetto.selectFirstPhotoObject(idOggetto)));
                    
                    negozi.add(daoNegozio.getStoreById(order.getIdNegozio()));
                }

                request.setAttribute("objects", objects);
                request.setAttribute("shops", negozi);
                request.setAttribute("utenteSessioneID", utenteSessioneID);
                
                if(utenteSessioneID != -1)
                {
                    if (action.equalsIgnoreCase("delivery"))
                    {
                        /*--- PREPARA INDIRIZZI+SPEDIZIONI PER LA SELEZIONE DEL METODO DI SPEDIZIONE ---*/
                        forward = DELIVERY_METHOD;

                        List<ModelloListeTipoSpedizione> tipiSpedizione = new ArrayList<>();

                        for (ModelloOrdine order : orders.getList())
                        {
                            //Recupero TipoSpedizione in base all'ID dell'oggetto
                            ModelloListeTipoSpedizione tipiSpedizioneOggetto = new ModelloListeTipoSpedizione(daoTipoSpedizione.selectDeliveryTypesByIdO(order.getIdOggetto()));

                            tipiSpedizione.add(tipiSpedizioneOggetto);
                        }

                        request.setAttribute("listaTipiSpedizione", tipiSpedizione);

                        ModelloListeIndirizzo indirizzi = new ModelloListeIndirizzo(daoIndirizzo.selectAddressByUserID(utenteSessione.getId()));
                        request.setAttribute("addrs", indirizzi);
                    }
                    else if (action.equalsIgnoreCase("payment"))
                    {
                        /*--- PREPARA INDIRIZZO PER LA SELEZIONE DEL METODO DI PAGAMENTO ---*/
                        forward = PAYMENT_METHOD;

                        ModelloIndirizzo indirizzo = daoIndirizzo.selectAddressByIdAddress(orders.get(0).getIdI());
                        request.setAttribute("address", indirizzo);

                        /*---Calcolo prezzo totale (prezzoOgg * quantitaOgg + prezzoSped)---*/
                        double prezzoTot = 0;
                        int nArticoli = 0;
                        for (ModelloOrdine order : orders.getList())
                        {                       
                            prezzoTot += (order.getPrezzoDiAcquisto() * order.getQuantita());
                            if(order.getIdS() != 0)
                            {
                                ModelloTipoSpedizione ts = daoTipoSpedizione.selectDeliveryTypesByIdS(order.getIdS()).get(0);
                                prezzoTot += Math.ceil((double)order.getQuantita() / (double)ts.getNumeroMassimo()) * ts.getPrezzo();
                            }

                            nArticoli += order.getQuantita();
                        }

                        request.setAttribute("prezzoTot", (Math.round(prezzoTot * 100.0) / 100.0));
                        request.setAttribute("nArticoli", nArticoli);
                    }
                    else if (action.equalsIgnoreCase("finish"))
                    {
                        forward = ORDER_COMPLETED;
                    }
                }
            }
            
            if(action.equalsIgnoreCase("listOrders"))
            {
                forward = LIST_ORDERS;
            }
        } catch (Exception e) { System.out.println("error message = " + e.toString()); forward = ERROR_PAGE; request.setAttribute("errore", "404 Pagina non trovata"); }
        
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
            throws ServletException, IOException
    {
        String forward = HOME_PAGE;
        forward = ERROR_PAGE; request.setAttribute("errore", "404 Pagina non trovata");
        
        try
        {
            System.out.println("---------");
            String action = request.getParameter("action");
            String save = request.getParameter("save");
            
            System.out.println("action = " + action);
            System.out.println("save = " + save);
            
            if(save.equalsIgnoreCase("1"))
            {
                /*--- QUI SI SALVANO LE MODIFICHE FATTE AL CARRELLO (QUANTITA' E RIMOZIONE OGGETTO) ---*/
                ModelloUtente utenteSessione = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
                int idUtente = utenteSessione.getId();
                String identificatore;
                
                System.out.println("idU = " + idUtente);

                //Ricavo il numero di ordini presenti nel carrello
                ModelloListeOrdine carrelloSessione = (ModelloListeOrdine)request.getSession().getAttribute("carrelloSessione");

                for(int i = 0; i < carrelloSessione.getList().size(); i++)
                {
                    //Ricavo idOrdine, idOggetto, nuovaQuantita
                    identificatore = "idOrdine" + Integer.toString(i);
                    int idOrdine = Integer.parseInt(request.getParameter(identificatore));
                    System.out.println(idOrdine);

                    identificatore = "idOggetto" + Integer.toString(i);
                    String idOggetto = request.getParameter(identificatore);
                    System.out.println(idOggetto);
                    
                    identificatore = "quantita" + Integer.toString(i);
                    int newQuantita = Integer.parseInt(request.getParameter(identificatore));
                    System.out.println(newQuantita);
                    
                    if(newQuantita == 0)
                    {
                        //Ricavo l'oggetto corrispondente ai parametri ricevuti
                        ModelloOrdine ord = new ModelloOrdine();
                        ord.setIdOrdine(idOrdine);
                        ord.setIdOggetto(idOggetto);
                        ord.setIdUtente(idUtente);
                        
                        if(idUtente != -1) //Elimino l'ordine dalla tabella ORDINE
                            daoOrdine.removeObjectInCart(ord);
                        else
                            carrelloSessione.getList().remove(ord);
                        
                        System.out.println("rimosso");
                    }
                    else
                    {
                        if(idUtente != -1) //Faccio l'update dell'ordine
                            daoOrdine.changeOrderQuantity(idOrdine, idOggetto, idUtente, newQuantita);
                        else
                        {
                            ModelloOrdine ord = carrelloSessione.get(i);
                            ord.setQuantita(newQuantita);
                            carrelloSessione.getList().remove(ord);
                            carrelloSessione.getList().add(ord);
                        }
                        
                        System.out.println("cambio quantita");
                    }
                }
            }
            else if(save.equalsIgnoreCase("2"))
            {
                /*--- QUI SALVO L'INDIRIZZO DI SPEDIZIONE E IL METODO DI SPEDIZIONE ---*/
                int nOrders = ((ModelloListeOrdine)request.getSession().getAttribute("carrelloSessione")).getSize();
                String identificatore;
                
                int idI = Integer.parseInt(request.getParameter("idI"));
                
                for(int i = 0; i < nOrders; i++)
                {
                    identificatore = "idS" + Integer.toString(i);
                    int idS = Integer.parseInt(request.getParameter(identificatore));
                    
                    identificatore = "idOrdine" + Integer.toString(i);
                    int idO = Integer.parseInt(request.getParameter(identificatore));
                    
                    daoOrdine.updateOrderIdI(idO, idI);
                    
                    if(idS == -1)
                        daoOrdine.updateOrderIdS(idO, -1);
                    else
                        daoOrdine.updateOrderIdS(idO, idS);
                }
            }
            else if(save.equalsIgnoreCase("3"))
            {
                //Ricavo il numero di ordini presenti nel carrello
                ModelloListeOrdine carrelloSessione = (ModelloListeOrdine)request.getSession().getAttribute("carrelloSessione");
                
                
                System.out.println("dentro finish");
                /*--- QUI CAMBIO LO STATO DEGLI ORDINI NEL CARRELLO IN "PAGATI" E INVIO LA MAIL DI CONFERMA DELL'ORDINE---*/
                daoOrdine.changeOrderStatus(((ModelloUtente)request.getSession().getAttribute("utenteSessione")).getId(), 0, 1);
                SendEmail.orderCompleted(
                        ((ModelloUtente)request.getSession().getAttribute("utenteSessione")).getMail(),                         
                        (carrelloSessione.get(0)).getIdOrdine());
                System.out.println("fatto finish");
                
                
                /*---2018-01-12---*/
                Set<Integer> idVenditori = new LinkedHashSet<>();
                
                
                //Diminuisco la disponibilità di ciascun prodotto
                //nel mentre salvo la lista dei venditori ai quali ho comprato uno o più oggetti
                for(ModelloOrdine order : carrelloSessione.getList())
                {
                    idVenditori.add(daoNegozio.getStoreById(order.getIdNegozio()).getIdVenditore());
                    
                    daoOggetto.updateObjectQuantity(order.getIdOggetto(), ((daoOggetto.getObjectById(order.getIdOggetto())).getDisponibilita() - order.getQuantita()));
                }
                
                
                //Aggiungo righe alla tabella ordiniRicevuti
                for(int idV : idVenditori)
                {
                    daoOrdiniRicevuti.addOrdineRicevuto(carrelloSessione.get(0).getIdOrdine(), idV);
                }
            }
            
            if(action.equalsIgnoreCase("listOrders"))
            {
                forward = "OrdineController?action=listOrders";
            }
            else if (action.equalsIgnoreCase("delivery")) /*--- REINDIRIZZA A deliveryMethod.jsp ---*/
            {
                forward = "OrdineController?action=delivery";
            }
            else if (action.equalsIgnoreCase("payment")) /*--- REINDIRIZZA A paymentMethod.jsp ---*/
            {   
                forward = "OrdineController?action=payment";
            }
            else if (action.equalsIgnoreCase("finish")) /*--- REINDIRIZZA A orderCompleted.jsp ---*/
            {
                forward = "OrdineController?action=finish";                
            }
        } catch (NullPointerException e) { System.out.println(e.toString()); forward = ERROR_PAGE; request.setAttribute("errore", "Si è verificato un problema interno al sistema."); }
        
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
    }// </editor-fold>

}
