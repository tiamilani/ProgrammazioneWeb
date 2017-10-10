/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.Controller.HomeController;

import it.progettoWeb.java.database.Dao.Categoria.DaoCategoria;
import it.progettoWeb.java.database.Dao.Oggetto.DaoOggetto;
import it.progettoWeb.java.database.Model.Categoria.ModelloCategoria;
import it.progettoWeb.java.database.Model.Categoria.ModelloListeCategoria;
import it.progettoWeb.java.database.Model.Oggetto.ModelloListeOggetto;
import it.progettoWeb.java.database.Model.Oggetto.ModelloOggetto;
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import it.progettoWeb.java.database.Model.immagineOggetto.ModelloImmagineOggetto;
import it.progettoWeb.java.database.Model.immagineOggetto.ModelloListeImmagineOggetto;
import it.progettoWeb.java.utility.pair.pair;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mattia
 */
public class HomeController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String HOME_PAGE = "/jspFile/Finale/Index/homePage.jsp";
    private DaoCategoria daoCat;
    private DaoOggetto daoOggetto;

    public HomeController() {
        super();
        daoCat = new DaoCategoria();
        daoOggetto = new DaoOggetto();
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
        request.getSession().invalidate();

        if (action.equalsIgnoreCase("Inizializzazione")){
            forward = HOME_PAGE;
            ModelloUtente utente = new ModelloUtente();
            utente.setId(-1);
            ModelloListeCategoria listaCategorie = new ModelloListeCategoria(daoCat.selectAllCategory());
            request.getSession().setAttribute("utente", utente);
            request.getSession().setAttribute("listacategoriesessione", listaCategorie);

            //Richiedo oggetti per riempire la home page
            pair<List<ModelloOggetto>, List<ModelloImmagineOggetto>> listaOggettiImmagini = daoOggetto.selectRandomObjectsAndImage(12);
            ModelloListeOggetto listaOggetti = new ModelloListeOggetto(listaOggettiImmagini.getL());
            ModelloListeImmagineOggetto listaImmaginiOggetto = new ModelloListeImmagineOggetto(listaOggettiImmagini.getR());
            request.setAttribute("ListaOggetti", listaOggetti);
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
