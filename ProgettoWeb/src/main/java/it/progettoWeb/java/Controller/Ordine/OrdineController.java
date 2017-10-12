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
        request.setAttribute("userId", userId);
        
        
        
        
        //Seleziona oggetti presenti negli ordini + la loro PRIMA immagine
        List<pair<ModelloOggetto, ModelloImmagineOggetto>> objects = new ArrayList<>();
        String idOggetto;
        
        for (ModelloOrdine order : orders)
        {
            idOggetto = order.getIdOggetto();            
            objects.add(new pair<>(daoOggetto.getObjectById(idOggetto), daoImmagineOggetto.selectFirstPhotoObject(idOggetto)));
        }
        
        request.setAttribute("objects", objects);
        
        System.out.println("-------------------");
        System.out.println(objects.get(0).getL().getPrezzo());
        System.out.println(objects.get(1).getL().getPrezzo());
        System.out.println(orders.get(0).getQuantita());
        System.out.println(orders.get(1).getQuantita());
        
        
        
        
        
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
        processRequest(request, response);
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
