/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.Controller.InserisciRecensione;

import it.progettoWeb.java.database.Dao.recensioneNegozio.DaoRecensioneNegozio;
import it.progettoWeb.java.database.Dao.recensioneOggetto.DaoRecensioneOggetto;
import it.progettoWeb.java.database.Dao.recensioneVenditore.DaoRecensioneVenditore;
import it.progettoWeb.java.database.Model.Oggetto.ModelloOggetto;
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import it.progettoWeb.java.database.Model.recensioneNegozio.ModelloRecensioneNegozio;
import it.progettoWeb.java.database.Model.recensioneOggetto.ModelloRecensioneOggetto;
import it.progettoWeb.java.database.Model.recensioneVenditore.ModelloRecensioneVenditore;
import java.io.Console;
import java.io.IOException;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mattia
 */
public class InserisciRecensioneController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DaoRecensioneOggetto daoRecensioneOggetto;
    private DaoRecensioneNegozio daoRecensioneNegozio;
    private DaoRecensioneVenditore daoRecensioneVenditore;
    
    public InserisciRecensioneController(){
        super();
        daoRecensioneOggetto = new DaoRecensioneOggetto();
        daoRecensioneNegozio = new DaoRecensioneNegozio();
        daoRecensioneVenditore = new DaoRecensioneVenditore();
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
        String forward="";
        String action = request.getParameter("action");
        
        if(action.equals("Oggetto")) {
            ModelloRecensioneOggetto recensioneOggetto = new ModelloRecensioneOggetto();
            recensioneOggetto.setIdUtente(Integer.parseInt(request.getParameter("utenteReview")));
            recensioneOggetto.setIdOggetto(request.getParameter("oggettoReview"));
            recensioneOggetto.setTesto(request.getParameter("testoReview"));
            recensioneOggetto.setUtilita(0);
            recensioneOggetto.setValutazione(Integer.parseInt(request.getParameter("valutazioneReview")));
            daoRecensioneOggetto.addReviewToObject(recensioneOggetto);
            
            forward = "/jspFile/Finale/Index/index.jsp";
        }
        else if(action.equals("Negozio")) {
            ModelloRecensioneNegozio recensioneNegozio = new ModelloRecensioneNegozio();
            recensioneNegozio.setIdUtente(Integer.parseInt(request.getParameter("utenteReview")));
            recensioneNegozio.setIdNegozio(Integer.parseInt(request.getParameter("negozioReview")));
            recensioneNegozio.setTesto(request.getParameter("testoReview"));
            recensioneNegozio.setUtilita(0);
            recensioneNegozio.setValutazione(Integer.parseInt(request.getParameter("valutazioneReview")));
            daoRecensioneNegozio.addReviewToStore(recensioneNegozio);
            
            forward = "/jspFile/Finale/Index/index.jsp";
        }
        else if(action.equals("Venditore")) {
            ModelloRecensioneVenditore recensioneVenditore = new ModelloRecensioneVenditore();
            recensioneVenditore.setIdUtente(Integer.parseInt(request.getParameter("utenteReview")));
            recensioneVenditore.setIdVenditore(Integer.parseInt(request.getParameter("venditoreReview")));
            recensioneVenditore.setTesto(request.getParameter("testoReview"));
            recensioneVenditore.setUtilita(0);
            recensioneVenditore.setValutazione(Integer.parseInt(request.getParameter("valutazioneReview")));
            daoRecensioneVenditore.addReviewToSeller(recensioneVenditore);
            
            forward = "/jspFile/Finale/Index/index.jsp";
        }
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
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
