/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package it.progettoWeb.java.Controller.Ordine;

import it.progettoWeb.java.database.Dao.Oggetto.DaoOggetto;
import it.progettoWeb.java.database.Dao.Ordine.DaoOrdine;
import it.progettoWeb.java.database.Dao.immagineOggetto.DaoImmagineOggetto;
import it.progettoWeb.java.database.Model.Oggetto.ModelloOggetto;
import it.progettoWeb.java.database.Model.Ordine.ModelloOrdine;
import it.progettoWeb.java.database.Model.immagineOggetto.ModelloImmagineOggetto;
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
    
    //VIENE PASSATO L'ID UTENTE DAL BOTTONE "CARRELLO"
    //LISTORDERS
    //QUERY PER VISUALIZZARE OGGETTI NEL CARRELLO
    //SOLO ORDINI CON STATO = 0 (NEL CARRELLO)
    
    
    private static String LIST_ORDERS = "/jspFile/Finale/Ordine/listOrder.jsp";
    private DaoOrdine daoOrdine;
    private DaoOggetto daoOggetto;
    private DaoImmagineOggetto daoImmagineOggetto;
    
    
    /**
     * Costruttore della classe OrdineController
     */
    public OrdineController()
    {
        super();
        daoOrdine = new DaoOrdine();
        daoOggetto = new DaoOggetto();
        daoImmagineOggetto = new DaoImmagineOggetto();
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
        //Richiede l'ID dell'utente di cui mostrare gli ordini
        int userId = Integer.parseInt(request.getParameter("userId"));
        
        List<ModelloOrdine> orders = new ArrayList<>();
        orders = daoOrdine.selectOrdersComplete(userId, 0);
        
        //Richiama la pagina di visualizzazione degli ordini (la pagina del carrello)
        String forward = LIST_ORDERS;
        request.setAttribute("cart", orders);
        request.setAttribute("userId_request", userId);
        
        //Seleziona oggetti presenti negli ordini + la loro PRIMA immagine
        List<pair<ModelloOggetto, ModelloImmagineOggetto>> objects = new ArrayList<>();
        String idOggetto;
        
        for (ModelloOrdine order : orders)
        {
            idOggetto = order.getIdOggetto();            
            objects.add(new pair<>(daoOggetto.getObjectById(idOggetto), daoImmagineOggetto.selectFirstPhotoObject(idOggetto)));
        }
        
        request.setAttribute("objects", objects);
        
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
        System.out.println("-----------------------------------------------------------");
        
        
        
        String toDo = request.getParameter("toDo");System.out.println(toDo);
        int idUtente = Integer.parseInt(request.getParameter("idUtente"));
        
        //if (toDo.equalsIgnoreCase("saveChanges"))
        //{
            System.out.println("DENTRO onbeforeunload");
            
            
            
            
            String identificatore;
            
            //Ricavo il numero di ordini presenti nel carrello
            int nOrders =  Integer.parseInt(request.getParameter("nOrders"));
            
            for(int i = 0; i < nOrders; i++)
            {
                //Ricavo idOrdine, idOggetto, nuovaQuantita
                identificatore = "idOrdine" + Integer.toString(i);
                int idOrdine = Integer.parseInt(request.getParameter(identificatore));
                
                identificatore = "idOggetto" + Integer.toString(i);
                String idOggetto = request.getParameter(identificatore);
                
                identificatore = "quantita" + Integer.toString(i);
                int newQuantita = Integer.parseInt(request.getParameter(identificatore));
                
                
                
                /*
                System.out.println("idOrdine = " + idOrdine);
                System.out.println("idOggetto = " + idOggetto);
                System.out.println("idUtente = " + idUtente);
                System.out.println("quantita = " + newQuantita);
                */
                
                if(newQuantita == 0)
                {
                    //Ricavo l'oggetto corrispondente ai parametri ricevuti
                    ModelloOrdine ord = new ModelloOrdine();
                    ord.setIdOrdine(idOrdine);
                    ord.setIdOggetto(idOggetto);
                    ord.setIdUtente(idUtente);

                    //Elimino l'ordine dalla tabella ORDINE
                    //System.out.println("REMOVE oggetto idOrdine = " + idOrdine + " idOggetto = " + idOggetto
                    //+ " idUtente = " + idUtente + " newQuantita = " + newQuantita);
                    daoOrdine.removeObjectInCart(ord);
                }
                else
                {
                    //System.out.println("UPDATE oggetto idOrdine = " + idOrdine + " idOggetto = " + idOggetto
                    //+ " idUtente = " + idUtente + " newQuantita = " + newQuantita);
                    daoOrdine.changeOrderQuantity(idOrdine, idOggetto, idUtente, newQuantita);
                }
            }
            
            
            
            
            
        //}
        /*
        OLD
        else if (toDo.equalsIgnoreCase("remove"))
        {
            //Ricavo i parametri
            int idOrdine = Integer.parseInt(request.getParameter("idOrdine"));
            String idOggetto = request.getParameter("idOggetto");
            idUtente = Integer.parseInt(request.getParameter("idUtente"));
            
            
            System.out.println("idOrdine = " + idOrdine);
            System.out.println("idOggetto = " + idOggetto);
            System.out.println("idUtente = " + idUtente);
            

            /*
            Bottone che chiama la servlet e:
                - salva le nuove quantità degli oggetti
                - rimuove l'oggetto selezionato (recuperare l'ID dell'oggetto; salvalo come dato nel bottone)
            La servlet richiamerà se stessa e ricaricherà la pagina con i nuovi dati


            cambia valori nella tabella ordine + carrello

            *-/


            //Ricavo l'oggetto corrispondente ai parametri ricevuti
            ModelloOrdine ord = new ModelloOrdine();
            ord.setIdOrdine(idOrdine);
            ord.setIdOggetto(idOggetto);
            ord.setIdUtente(idUtente);

            //Elimino l'ordine dalla tabella ORDINE
            //daoOrdine.removeObjectInCart(ord);
        }*/
        
        //Ricarica la pagina con il carrello modificato
        String forward = "OrdineController?userId=" + idUtente;
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
