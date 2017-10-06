/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.Controller.SearchObject;

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
public class searchObjectController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private static String SEARCH_PAGE = "/jspFile/Finale/search/searchResult.jsp";
    private static String ERROR_PAGE = "/jspFile/Finale/Error/ricercaErrata.jsp";
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
        String forward=SEARCH_PAGE;
        String error="";
        
        String search="", categoria="", nomeVenditore="", nomeNegozio="";
        int minPrice=0, maxPrice=0, valutazioneMinima=0;
        boolean checkRitiroInNegozio=false, checkProdottiScontati=false;
        double latitudine=0, longitudine=0, raggio=0;
        
        search = request.getParameter("search");
        categoria = request.getParameter("hiddenidCategoria");
        minPrice = ((request.getParameter("hiddenminPrice") == null || "".equals(request.getParameter("hiddenminPrice"))) ? 0 : Integer.parseInt(request.getParameter("hiddenminPrice")));
        maxPrice = ((request.getParameter("hiddenmaxPrice") == null || "".equals(request.getParameter("hiddenmaxPrice"))) ? 0 : Integer.parseInt(request.getParameter("hiddenmaxPrice")));
        nomeVenditore = ((request.getParameter("hiddennomeVenditore") == null || "".equals(request.getParameter("hiddennomeVenditore"))) ? "" : request.getParameter("hiddennomeVenditore"));
        nomeNegozio = ((request.getParameter("hiddennomeNegozio") == null || "".equals(request.getParameter("hiddennomeNegozio"))) ? "" : request.getParameter("hiddennomeNegozio"));
        checkRitiroInNegozio =  ((request.getParameter("hiddencheckRitiroInNegozio") == null) ? false : Boolean.getBoolean(request.getParameter("hiddencheckRitiroInNegozio")));
        checkProdottiScontati = ((request.getParameter("hiddencheckProdottiScontati") == null) ? false : Boolean.getBoolean(request.getParameter("hiddencheckProdottiScontati")));
        latitudine = ((request.getParameter("hiddenlatitudine") == null || "".equals(request.getParameter("hiddenlatitudine"))) ? 0 : Double.parseDouble(request.getParameter("hiddenlatitudine")));
        longitudine = ((request.getParameter("hiddenlongitudine") == null || "".equals(request.getParameter("hiddenlongitudine"))) ? 0 : Double.parseDouble(request.getParameter("hiddenlongitudine")));
        raggio = ((request.getParameter("hiddenraggio") == null || "".equals(request.getParameter("hiddenraggio"))) ? 0 : Double.parseDouble(request.getParameter("hiddenraggio")));
        valutazioneMinima = ((request.getParameter("hiddenvalutazioneMinima") == null || "".equals(request.getParameter("hiddenvalutazioneMinima")) || "Choose...".equals(request.getParameter("hiddenvalutazioneMinima"))) ? 0 : Integer.parseInt(request.getParameter("hiddenvalutazioneMinima")));
        
        search = search.trim().replaceAll(" +", " ");
        
        if(search.length() < 3) {
            forward=ERROR_PAGE;
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        }
        /*
        String test = search;
        if(test == null || test.length() < 10){
            forward=ERROR_PAGE;
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        }*/
        
        request.setAttribute("search",search);
        request.setAttribute("hiddenidCategoria", categoria);
        request.setAttribute("hiddenminPrice", minPrice);
        request.setAttribute("hiddenmaxPrice", maxPrice);
        request.setAttribute("hiddennomeVenditore", nomeVenditore);
        request.setAttribute("hiddennomeNegozio", nomeNegozio);
        request.setAttribute("hiddencheckRitiroInNegozio", checkRitiroInNegozio);
        request.setAttribute("hiddencheckProdottiScontati", checkProdottiScontati);
        request.setAttribute("hiddenlatitudine", latitudine);
        request.setAttribute("hiddenlongitudine", longitudine);
        request.setAttribute("hiddenraggio", raggio);
        request.setAttribute("hiddenvalutazioneMinima", valutazioneMinima);
        
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
