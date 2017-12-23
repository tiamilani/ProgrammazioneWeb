/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.Controller.NegozioController;

import it.progettoWeb.java.database.Dao.Negozio.DaoNegozio;
import it.progettoWeb.java.database.Dao.Oggetto.DaoOggetto;
import it.progettoWeb.java.database.Dao.Utente.DaoUtente;
import it.progettoWeb.java.database.Dao.indirizzo.DaoIndirizzo;
import it.progettoWeb.java.database.Dao.ordiniRicevuti.DaoOrdiniRicevuti;
import it.progettoWeb.java.database.Model.Negozio.ModelloNegozio;
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import it.progettoWeb.java.database.Model.indirizzo.ModelloIndirizzo;
import java.io.IOException;
import java.sql.Timestamp;
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
public class NegozioController extends HttpServlet {
    
    private static String USERPAGE = "/jspFile/Finale/Utente/utente.jsp";
    private static String ERROR_PAGE = "/jspFile/Finale/Error/ricercaErrata.jsp";
    
    private DaoUtente daoUtente;
    private DaoOggetto daoOggetto;
    private DaoIndirizzo daoIndirizzo;
    private DaoOrdiniRicevuti daoOrdiniRicevuti;
    private DaoNegozio daoNegozio;
    
    public NegozioController() {
        super();
        daoIndirizzo = new DaoIndirizzo();
        daoUtente = new DaoUtente();
        daoOggetto = new DaoOggetto();
        daoOrdiniRicevuti = new DaoOrdiniRicevuti();
        daoNegozio = new DaoNegozio();
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
        log("SONO STATO TROVATO");
        String forward="";
        String action = request.getParameter("action");
        
        if (action.equalsIgnoreCase("addNegozio")) {
            log("ACTION TROVATA");
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
            
            daoIndirizzo.insertAddress(indirizzo);
            
            List<ModelloIndirizzo> tuttiNegozi = daoIndirizzo.selectAddressByUserID(utente.getId());
            
            indirizzo.setIdI(tuttiNegozi.get(tuttiNegozi.size() - 1).getIdI());
            
            ModelloNegozio negozio = new ModelloNegozio();
            negozio.setIdVenditore(utente.getId());
            negozio.setNomeNegozio(request.getParameter("nomeNegozio"));
            negozio.setAttivo(1);
            negozio.setIdI(indirizzo.getIdI());
            Timestamp timestampNow = new Timestamp(System.currentTimeMillis());
            negozio.setDataApertura(timestampNow);
            negozio.setLinkSito(request.getParameter("linkNegozio"));
            
            String orario = "";
            
            orario += "Lunedì: " + ((request.getParameter("chiusoLunedi") == null) ? request.getParameter("orarioAperturaNegozioLunedi") + " - " + request.getParameter("orarioChiusuraNegozioLunedi") : "Chiuso") + " ";
            orario += "Martedì: " + ((request.getParameter("chiusoMartedi") == null) ? request.getParameter("orarioAperturaNegozioMartedi") + " - " + request.getParameter("orarioChiusuraNegozioMartedi") : "Chiuso") + " ";
            orario += "Mercoledì: " + ((request.getParameter("chiusoMercoledi") == null) ? request.getParameter("orarioAperturaNegozioMercoledi") + " - " + request.getParameter("orarioChiusuraNegozioMercoledi") : "Chiuso") + " ";
            orario += "Giovedì: " + ((request.getParameter("chiusoGiovedi") == null) ? request.getParameter("orarioAperturaNegozioGiovedi") + " - " + request.getParameter("orarioChiusuraNegozioGiovedi") : "Chiuso") + " ";
            orario += "Venerdì: " + ((request.getParameter("chiusoVenerdi") == null) ? request.getParameter("orarioAperturaNegozioVenerdi") + " - " + request.getParameter("orarioChiusuraNegozioVenerdi") : "Chiuso") + " ";
            orario += "Sabato: " + ((request.getParameter("chiusoSabato") == null) ? request.getParameter("orarioAperturaNegozioSabato") + " - " + request.getParameter("orarioChiusuraNegozioSabato") : "Chiuso") + " ";
            orario += "Domenica: " + ((request.getParameter("chiusoDomenica") == null) ? request.getParameter("orarioAperturaNegozioDomenica") + " - " + request.getParameter("orarioChiusuraNegozioDomenica") : "Chiuso") + " ";
            
            negozio.setOrarioNegozio(orario);
            
            log(daoNegozio.insertShop(negozio));
           
            forward = USERPAGE;
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
