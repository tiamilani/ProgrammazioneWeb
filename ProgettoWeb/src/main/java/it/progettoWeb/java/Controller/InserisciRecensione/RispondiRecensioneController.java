/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.Controller.InserisciRecensione;

import it.progettoWeb.java.database.Dao.rispostaNegozio.DaoRispostaNegozio;
import it.progettoWeb.java.database.Dao.rispostaOggetto.DaoRispostaOggetto;
import it.progettoWeb.java.database.Dao.rispostaVenditore.DaoRispostaVenditore;
import it.progettoWeb.java.database.Model.rispostaNegozio.ModelloRispostaNegozio;
import it.progettoWeb.java.database.Model.rispostaOggetto.ModelloRispostaOggetto;
import it.progettoWeb.java.database.Model.rispostaVenditore.ModelloRispostaVenditore;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andreafadi
 */
public class RispondiRecensioneController extends HttpServlet {
    
    private DaoRispostaNegozio daoRispostaNegozio;
    private DaoRispostaOggetto daoRispostaOggetto;
    private DaoRispostaVenditore daoRispostaVenditore;
    
    public RispondiRecensioneController() {
        daoRispostaNegozio = new DaoRispostaNegozio();
        daoRispostaOggetto = new DaoRispostaOggetto();
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
        request.setCharacterEncoding("UTF-8");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String forward = "/jspFile/Finale/Utente/utente.jsp";
        String action = request.getParameter("action");
        
        if(action.equals("Oggetto")) {
            int idRecensione = Integer.parseInt(request.getParameter("idRecensioneO"));
            String testoRecensione = request.getParameter("testoO").replace('\'', ' ');
            
            ModelloRispostaOggetto rispostaOggetto = new ModelloRispostaOggetto();
            rispostaOggetto.setIdRecensione(idRecensione);
            rispostaOggetto.setTesto(testoRecensione);
            daoRispostaOggetto.insertAnswerToObject(rispostaOggetto);
        }
        else if(action.equals("Negozio")) {
            int idRecensione = Integer.parseInt(request.getParameter("idRecensioneN"));
            String testoRecensione = request.getParameter("testoN").replace('\'', ' ');
            
            ModelloRispostaNegozio rispostaNegozio = new ModelloRispostaNegozio();
            rispostaNegozio.setIdRecensione(idRecensione);
            rispostaNegozio.setTesto(testoRecensione);
            daoRispostaNegozio.insertAnswerToStore(rispostaNegozio);
        }
        else if(action.equals("Venditore")) {
            int idRecensione = Integer.parseInt(request.getParameter("idRecensioneV"));
            String testoRecensione = request.getParameter("testoV").replace('\'', ' ');
            
            ModelloRispostaVenditore rispostaVenditore = new ModelloRispostaVenditore();
            rispostaVenditore.setIdRecensione(idRecensione);
            rispostaVenditore.setTesto(testoRecensione);
            daoRispostaVenditore.insertAnswerToSeller(rispostaVenditore);
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
