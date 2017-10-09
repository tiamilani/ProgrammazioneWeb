/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.Controller.Oggetto;

import it.progettoWeb.java.database.Dao.Negozio.DaoNegozio;
import it.progettoWeb.java.database.Dao.Oggetto.DaoOggetto;
import it.progettoWeb.java.database.Dao.Utente.DaoUtente;
import it.progettoWeb.java.database.Dao.immagineOggetto.DaoImmagineOggetto;
import it.progettoWeb.java.database.Dao.indirizzo.DaoIndirizzo;
import it.progettoWeb.java.database.Dao.recensioneOggetto.DaoRecensioneOggetto;
import it.progettoWeb.java.database.Model.Negozio.ModelloNegozio;
import it.progettoWeb.java.database.Model.Oggetto.ModelloListeOggetto;
import it.progettoWeb.java.database.Model.Oggetto.ModelloOggetto;
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import it.progettoWeb.java.database.Model.immagineOggetto.ModelloListeImmagineOggetto;
import it.progettoWeb.java.database.Model.indirizzo.ModelloIndirizzo;
import it.progettoWeb.java.database.Model.recensioneOggetto.ModelloListeRecensioneOggetto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mattia
 */
public class objectSelectedController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String HOME_PAGE = "/jspFile/Finale/DescrizioneOggetto/descrizioneOggetto.jsp";
    private DaoNegozio daoNegozio;
    private DaoUtente daoUtente;
    private DaoRecensioneOggetto daoRecensione;
    private DaoOggetto daoOggetto;
    private DaoIndirizzo daoIndirizzo;
    private DaoImmagineOggetto daoImmagineOggetto;
    
    public objectSelectedController() {
        super();
        daoNegozio = new DaoNegozio();
        daoUtente = new DaoUtente();
        daoRecensione = new DaoRecensioneOggetto();
        daoOggetto = new DaoOggetto();
        daoIndirizzo = new DaoIndirizzo();
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
        
        String forward=HOME_PAGE;
        String idOggetto = request.getParameter("idOggetto");
        
        ModelloOggetto oggetto = daoOggetto.getObjectById(idOggetto);
        ModelloNegozio negozio = daoNegozio.getStoreById(oggetto.getIdNegozio());
        ModelloUtente venditore = daoUtente.getUserById(negozio.getIdVenditore());
        ModelloListeRecensioneOggetto recensioni = new ModelloListeRecensioneOggetto(daoRecensione.selectReviewsObjects(idOggetto));
        ModelloIndirizzo indirizzo = daoIndirizzo.selectAddressByIdAddress(negozio.getIdI());
        ModelloListeImmagineOggetto immagini = new ModelloListeImmagineOggetto(daoImmagineOggetto.selectPhotoObject(idOggetto));
        ModelloListeOggetto randomOggetti = new ModelloListeOggetto(daoOggetto.selectRandomObjects(12));
        
        request.setAttribute("oggetto", oggetto);
        request.setAttribute("negozio", negozio);
        request.setAttribute("venditore", venditore);
        request.setAttribute("recensioni", recensioni);
        request.setAttribute("indirizzo", indirizzo);
        request.setAttribute("listaImmagini", immagini);
        request.setAttribute("randomOggetti", randomOggetti);
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
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
    }// </editor-fold>

}