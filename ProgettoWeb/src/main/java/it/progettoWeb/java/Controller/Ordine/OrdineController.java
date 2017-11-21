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
import it.progettoWeb.java.database.Dao.tipoSpedizione.DaoTipoSpedizione;
import it.progettoWeb.java.database.Model.Negozio.ModelloListeNegozio;
import it.progettoWeb.java.database.Model.Oggetto.ModelloOggetto;
import it.progettoWeb.java.database.Model.Ordine.ModelloListeOrdine;
import it.progettoWeb.java.database.Model.Ordine.ModelloOrdine;
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import it.progettoWeb.java.database.Model.immagineOggetto.ModelloImmagineOggetto;
import it.progettoWeb.java.database.Model.indirizzo.ModelloListeIndirizzo;
import it.progettoWeb.java.database.Model.tipoSpedizione.ModelloListeTipoSpedizione;
import it.progettoWeb.java.utility.pair.pair;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    private DaoOrdine daoOrdine;
    private DaoOggetto daoOggetto;
    private DaoImmagineOggetto daoImmagineOggetto;
    private DaoNegozio daoNegozio;
    private DaoTipoSpedizione daoTipoSpedizione;
    private DaoIndirizzo daoIndirizzo;
    
    
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
        
        try
        {
            String action = request.getParameter("action");
            
            /*--- PARTE IN COMUNE (PREPARA IL CARRELLO E LE IMMAGINI DEGLI OGGETTI) ---*/
            ModelloUtente utenteSessione = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            ModelloListeOrdine orders = (ModelloListeOrdine)request.getSession().getAttribute("carrelloSessione");
            int carrelloId = orders.getId();

            if(orders.getSize() > 0)
            {
                orders = new ModelloListeOrdine(daoOrdine.selectOrdersComplete(utenteSessione.getId(), 0));
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
                
                
                
                
                
                if (action.equalsIgnoreCase("delivery")) /*--- PREPARA CARRELLO+INDIRIZZI PER LA SELEZIONE DEL METODO DI SPEDIZIONE ---*/
                {
                    forward = DELIVERY_METHOD;

                    List<ModelloListeTipoSpedizione> tipiSpedizione = new ArrayList<>();

                    for (ModelloOrdine order : orders.getList())
                    {
                        //Recupero TipoSpedizione in base all'ID dell'oggetto
                        ModelloListeTipoSpedizione tipiSpedizioneOggetto = new ModelloListeTipoSpedizione(daoTipoSpedizione.selectDeliveryTypesByIdO(order.getIdOggetto()));

                        tipiSpedizione.add(tipiSpedizioneOggetto);
                    }

                    request.setAttribute("listaTipiSpedizione", tipiSpedizione);
                    request.setAttribute("utenteSessione", utenteSessione);

                    ModelloListeIndirizzo indirizzi = new ModelloListeIndirizzo(daoIndirizzo.selectAddressByUserID(utenteSessione.getId()));
                    request.setAttribute("addrs", indirizzi);
                }
                else if (action.equalsIgnoreCase("payment"))
                {
                    forward = PAYMENT_METHOD;


                }
                else if (action.equalsIgnoreCase("finish"))
                {
                    forward = ORDER_COMPLETED;
                }
            }
            
            if(action.equalsIgnoreCase("listOrders"))
            {
                forward = LIST_ORDERS;
            }
        } catch (NullPointerException e) { System.out.println(e.getMessage()); forward = HOME_PAGE; }
        
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
        
        try
        {
            String action = request.getParameter("action");
            String save = request.getParameter("save");
            
            
            /*--- QUI SI SALVANO LE MODIFICHE FATTE AL CARRELLO (QUANTITA' E RIMOZIONE OGGETTO) ---*/
            if(save.equalsIgnoreCase("1"))
            {
                ModelloUtente utenteSessione = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
                int idUtente = utenteSessione.getId();
                String identificatore;

                //Ricavo il numero di ordini presenti nel carrello
                int nOrders = ((ModelloListeOrdine)request.getSession().getAttribute("carrelloSessione")).getSize();

                for(int i = 0; i < nOrders; i++)
                {
                    //Ricavo idOrdine, idOggetto, nuovaQuantita
                    identificatore = "idOrdine" + Integer.toString(i);
                    int idOrdine = Integer.parseInt(request.getParameter(identificatore));

                    identificatore = "idOggetto" + Integer.toString(i);
                    String idOggetto = request.getParameter(identificatore);

                    identificatore = "quantita" + Integer.toString(i);
                    int newQuantita = Integer.parseInt(request.getParameter(identificatore));

                    if(newQuantita == 0)
                    {
                        //Ricavo l'oggetto corrispondente ai parametri ricevuti
                        ModelloOrdine ord = new ModelloOrdine();
                        ord.setIdOrdine(idOrdine);
                        ord.setIdOggetto(idOggetto);
                        ord.setIdUtente(idUtente);

                        //Elimino l'ordine dalla tabella ORDINE
                        daoOrdine.removeObjectInCart(ord);
                    }
                    else
                    {
                        //Faccio l'update dell'ordine
                        daoOrdine.changeOrderQuantity(idOrdine, idOggetto, idUtente, newQuantita);
                    }
                }
            }
            else if(save.equalsIgnoreCase("2"))
            {
                /*--- QUI SALVO L'INDIRIZZO DI SPEDIZIONE E IL METODO DI SPEDIZIONE ---*/
                
            }
            
            if(action.equalsIgnoreCase("listOrders"))
            {
                forward = "OrdineController?action=listOrders";
            }
            else if (action.equalsIgnoreCase("proceed")) /*--- REINDIRIZZA A deliveryMethod.jsp ---*/
            {
                forward = "OrdineController?action=delivery";
            }
            else if (action.equalsIgnoreCase("payment")) /*--- REINDIRIZZA A paymentMethod.jsp ---*/
            {
                /*--- QUI SALVO L'INDIRIZZO DI SPEDIZIONE E IL METODO DI SPEDIZIONE ---*/
                int idI = Integer.parseInt(request.getParameter("idI"));
                System.out.println("ID ADDRESS = " + idI);
                int nOrders = ((ModelloListeOrdine)request.getSession().getAttribute("carrelloSessione")).getSize();
                String identificatore;
                
                for(int i = 0; i < nOrders; i++)
                {
                    identificatore = "idS" + Integer.toString(i);
                    System.out.println(request.getParameter(identificatore));
                    int idS = Integer.parseInt(request.getParameter(identificatore));
                    
                    identificatore = "idOrdine" + Integer.toString(i);
                    int idO = Integer.parseInt(request.getParameter(identificatore));
                    System.out.println(request.getParameter(identificatore));
                    
                    System.out.println("iterator + ID ORDINE + ID SPEDIZIONE = " + i + " " + idO + " " + idS);
                    
                    //daoOrdine.updateOrderIdS(idO, idS);
                }
                
                forward = "OrdineController?action=payment";
            }
            else if (action.equalsIgnoreCase("finish"))
            {
                forward = "OrdineController?action=finish";
            }
        } catch (NullPointerException e) { System.out.println(e.getMessage()); forward = HOME_PAGE; }
        
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
