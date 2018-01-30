/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.Controller.Indirizzo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.progettoWeb.java.database.Dao.Utente.DaoUtente;
import it.progettoWeb.java.database.Dao.indirizzo.DaoIndirizzo;
import it.progettoWeb.java.database.Dao.indirizzoUtente.DaoIndirizzoUtente;
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import it.progettoWeb.java.database.Model.indirizzo.ModelloIndirizzo;
import it.progettoWeb.java.database.Model.indirizzo.ModelloListeIndirizzo;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author mattia
 */
public class IndirizzoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String LIST_ADDRESS = "/jspFile/Finale/Utente/listAddress.jsp";
    private static String INSERT_OR_EDIT = "/jspFile/Finale/Utente/modificaDatiIndirizzo.jsp";
    private static String ERROR_PAGE = "/jspFile/Finale/Error/ricercaErrata.jsp";
    private static String GESTIONEUTENTE ="/jspFile/Finale/Utente/impostazioneUtente.jsp";
    
    private DaoUtente daoU;
    private DaoIndirizzo daoI;
    private DaoIndirizzoUtente daoIndirizzoUtente;

    public IndirizzoController() {
        super();
        daoU = new DaoUtente();
        daoI = new DaoIndirizzo();
        daoIndirizzoUtente = new DaoIndirizzoUtente();
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
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("cancIndirizzo"))
        {
            int addrId = Integer.parseInt(request.getParameter("id"));

            daoI.deleteAddress(addrId);

            response.sendRedirect("UserController?action=infoCurrentUser");
            return;
        }
        else if (action.equalsIgnoreCase("edit"))
        {
            int addrId = Integer.parseInt(request.getParameter("addrId"));
            ModelloIndirizzo addr = daoI.selectAddressByIdAddress(addrId);

            int userId = Integer.parseInt(request.getParameter("userId"));
            request.setAttribute("userId", userId);

            forward = INSERT_OR_EDIT;
            request.setAttribute("addr", addr);
        }
        else if (action.equalsIgnoreCase("listAddress"))
        {
            int userId = Integer.parseInt(request.getParameter("userId"));

            forward = LIST_ADDRESS;
            request.setAttribute("address", daoI.selectAddressByUserID(userId));
            request.setAttribute("userId", userId);
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
        request.setCharacterEncoding("UTF-8");
        String forward="";
        String action = request.getParameter("action");

        if(action.equalsIgnoreCase("addAddr")){
            ModelloIndirizzo indirizzo = new ModelloIndirizzo();
            ModelloUtente utente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");

            indirizzo.setStato("Italia");
            indirizzo.setRegione(request.getParameter("regione"));
            indirizzo.setProvincia(request.getParameter("provincia"));
            indirizzo.setCitta(request.getParameter("citta"));
            indirizzo.setVia(request.getParameter("via"));
            indirizzo.setnCivico(Integer.parseInt(request.getParameter("nCivico")));
            indirizzo.setInterno(Integer.parseInt(request.getParameter("interno")));

            indirizzo.setLatitudine(Double.parseDouble(request.getParameter("latitudine")));
            indirizzo.setLongitudine(Double.parseDouble(request.getParameter("longitudine")));

            boolean indirizzoEsistente = daoI.addressExists(indirizzo,utente.getId());
            
            if(!indirizzoEsistente){
                daoI.insertAddress(indirizzo,utente.getId());
                request.setAttribute("aggiuntaIndirizzo", 0);
            } else {
                request.setAttribute("aggiuntaIndirizzo", 1);
            }

            forward = GESTIONEUTENTE;
            
            ModelloListeIndirizzo listaIndirizzi = new ModelloListeIndirizzo(daoI.selectAddressByUserID(utente.getId()));

            request.setAttribute("listaIndirizzi", listaIndirizzi);
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
    }
}
