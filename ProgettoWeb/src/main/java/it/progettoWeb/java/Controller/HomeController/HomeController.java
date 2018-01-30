/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.Controller.HomeController;

import it.progettoWeb.java.database.Dao.Categoria.DaoCategoria;
import it.progettoWeb.java.database.Dao.Oggetto.DaoOggetto;
import it.progettoWeb.java.database.Dao.Ordine.DaoOrdine;
import it.progettoWeb.java.database.Dao.Utente.DaoUtente;
import it.progettoWeb.java.database.Dao.indirizzo.DaoIndirizzo;
import it.progettoWeb.java.database.Model.Categoria.ModelloListeCategoria;
import it.progettoWeb.java.database.Model.Oggetto.ModelloListeOggetto;
import it.progettoWeb.java.database.Model.Oggetto.ModelloOggetto;
import it.progettoWeb.java.database.Model.Ordine.ModelloListeOrdine;
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import it.progettoWeb.java.database.Model.immagineOggetto.ModelloImmagineOggetto;
import it.progettoWeb.java.database.Model.immagineOggetto.ModelloListeImmagineOggetto;
import it.progettoWeb.java.utility.pair.pair;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mattia
 */
public class HomeController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String HOME_PAGE = "/jspFile/Finale/Index/homePage.jsp";
    private DaoCategoria daoCat;
    private DaoOggetto daoOggetto;
    private DaoUtente daoUtente;
    private DaoOrdine daoOrdine;
    private DaoIndirizzo daoIndirizzo;

    public HomeController() {
        super();
        daoCat = new DaoCategoria();
        daoOggetto = new DaoOggetto();
        daoUtente = new DaoUtente();
        daoOrdine = new DaoOrdine();
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

        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("Inizializzazione")){ 
            forward = HOME_PAGE;
            ModelloUtente utente = new ModelloUtente();
            utente.setId(-1);
            
            HttpSession session = request.getSession(false);
            
            Cookie[] cookies = request.getCookies();
            
            if(session != null) {
                log("La sessione non è nulla");
                
                System.out.println("Il prezzo massimo del database è " + daoOggetto.getMaxPrice());
                request.getSession().setAttribute("massimoPrezzoAttuale", (int)daoOggetto.getMaxPrice());
                System.out.println("Range massimo di prezzo: " + "0," + (int)(daoOggetto.getMaxPrice()) + "");
                request.getSession().setAttribute("massimoRangeAttuale", "0," + (int)(daoOggetto.getMaxPrice()) + "");
                
                ModelloUtente utenteInSessione = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
                if(utenteInSessione == null || utenteInSessione.getId() == -1){
                    log("Utente nullo o -1");
                    if ((cookies != null) && (cookies.length > 0)) {
                        log("Il cookie esiste");
                        int idUtente=-1;

                        for (Cookie cookie : cookies) {
                            switch (cookie.getName()) {
                                case "user":
                                    if(cookie.getMaxAge() != 0)
                                        idUtente = Integer.parseInt(cookie.getValue());
                                        log("Il valore nel cookie è: " +idUtente+ "");
                                    break;
                            }
                        }
                        
                        if(idUtente == -1){
                            log("Il valore trovato è -1");
                            request.getSession().setAttribute("utenteSessione", utente);
                        }
                        else {
                            log("Il valore trovato è diverso da -1 setto l'utente corretto");
                            ModelloUtente utenteSessione = daoUtente.getUserById(idUtente);
                            request.getSession().setAttribute("utenteSessione", utenteSessione);
                        }
                    } 
                    else {
                        log("Cookie non trovato");
                        request.getSession().setAttribute("utenteSessione", utente);
                    }
                }
                else {
                    log("Utente non nullo o -1");
                }
            } else {
                log("La sessione è nulla");
                if ((cookies != null) && (cookies.length > 0)) {
                    int idUtente=-1;

                    for (Cookie cookie : cookies) {
                        switch (cookie.getName()) {
                            case "user":
                                if(cookie.getMaxAge() != 0)
                                    idUtente = Integer.parseInt(cookie.getValue());
                                break;
                        }
                    }
                    if(idUtente == -1){
                        request.getSession().setAttribute("utenteSessione", utente);       
                    }
                    else {
                        ModelloUtente utenteSessione = daoUtente.getUserById(idUtente);
                        request.getSession().setAttribute("utenteSessione", utenteSessione);
                    }
                } 
                else {
                     request.getSession().setAttribute("utenteSessione", utente);
                }
            }
            
            /*--- 2017-11-06 */
            ModelloListeOrdine carrelloInSessione = (ModelloListeOrdine)request.getSession().getAttribute("carrelloSessione");
            if(carrelloInSessione == null || carrelloInSessione.getId() == -1)
            {
                ModelloUtente utenteInSessione = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
                if(utenteInSessione.getId() == -1)
                {
                    request.getSession().setAttribute("carrelloSessione", new ModelloListeOrdine());
                }
                else
                {
                    ModelloListeOrdine carrello = new ModelloListeOrdine(daoOrdine.selectOrdersComplete(utenteInSessione.getId(), 0));
                    carrello.setId(carrello.hashCode());
                    request.getSession().removeAttribute("carrelloSessione");
                    request.getSession().setAttribute("carrelloSessione", carrello);
                }
            }
            /*---*/
            
            ModelloListeCategoria listaCategorie = new ModelloListeCategoria(daoCat.selectAllCategory());
            request.getSession().setAttribute("listacategoriesessione", listaCategorie);
            List<String> regioni = daoIndirizzo.getAllRegions();
            request.getSession().setAttribute("listaRegioni", regioni);

            //Richiedo oggetti per riempire la home page
            pair<List<ModelloOggetto>, List<ModelloImmagineOggetto>> listaOggettiImmagini = daoOggetto.selectRandomObjectsAndImage(12);
            ModelloListeOggetto listaOggetti = new ModelloListeOggetto(listaOggettiImmagini.getL());
            ModelloListeImmagineOggetto listaImmaginiOggetto = new ModelloListeImmagineOggetto(listaOggettiImmagini.getR());
            request.setAttribute("listaOggetti", listaOggetti);
            request.setAttribute("listaImmaginiOggetto", listaImmaginiOggetto);
        }
        else {
             //Qui va mostrata una pagina di errore

            //forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);

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
        processRequest(request, response);
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
    }

}
