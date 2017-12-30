/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.Controller.NegozioController;

import it.progettoWeb.java.database.Dao.Negozio.DaoNegozio;
import it.progettoWeb.java.database.Dao.Oggetto.DaoOggetto;
import it.progettoWeb.java.database.Dao.Utente.DaoUtente;
import it.progettoWeb.java.database.Dao.immagineNegozio.DaoImmagineNegozio;
import it.progettoWeb.java.database.Dao.indirizzo.DaoIndirizzo;
import it.progettoWeb.java.database.Dao.ordiniRicevuti.DaoOrdiniRicevuti;
import it.progettoWeb.java.database.Model.Negozio.ModelloNegozio;
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import it.progettoWeb.java.database.Model.immagineNegozio.ModelloImmagineNegozio;
import it.progettoWeb.java.database.Model.indirizzo.ModelloIndirizzo;
import it.progettoWeb.java.utility.tris.tris;
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
    private static String SHOPPAGE = "/jspFile/Finale/Utente/gestioneSingoloNegozio.jsp";
    private static String ERROR_PAGE = "/jspFile/Finale/Error/ricercaErrata.jsp";
    
    private DaoNegozio daoNegozio;
    private DaoIndirizzo daoIndirizzo;
    
    public NegozioController() {
        super();
        daoIndirizzo = new DaoIndirizzo();
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
        
        if (action.equalsIgnoreCase("addNegozio")) {
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
            
            List<ModelloIndirizzo> tuttiNegozi = daoIndirizzo.selectAddressLatLng(indirizzo.getLatitudine(), indirizzo.getLongitudine());
            
            indirizzo.setIdI(tuttiNegozi.get(tuttiNegozi.size() - 1).getIdI());
            
            for(int i = 0 ; i < tuttiNegozi.size(); i++){
                log("IdI possibile: " + tuttiNegozi.get(i).getIdI());
            }
            
            log("IDI INDIRIZZO: " + indirizzo.getIdI());
            
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
            orario += ", Martedì: " + ((request.getParameter("chiusoMartedi") == null) ? request.getParameter("orarioAperturaNegozioMartedi") + " - " + request.getParameter("orarioChiusuraNegozioMartedi") : "Chiuso") + " ";
            orario += ", Mercoledì: " + ((request.getParameter("chiusoMercoledi") == null) ? request.getParameter("orarioAperturaNegozioMercoledi") + " - " + request.getParameter("orarioChiusuraNegozioMercoledi") : "Chiuso") + " ";
            orario += ", Giovedì: " + ((request.getParameter("chiusoGiovedi") == null) ? request.getParameter("orarioAperturaNegozioGiovedi") + " - " + request.getParameter("orarioChiusuraNegozioGiovedi") : "Chiuso") + " ";
            orario += ", Venerdì: " + ((request.getParameter("chiusoVenerdi") == null) ? request.getParameter("orarioAperturaNegozioVenerdi") + " - " + request.getParameter("orarioChiusuraNegozioVenerdi") : "Chiuso") + " ";
            orario += ", Sabato: " + ((request.getParameter("chiusoSabato") == null) ? request.getParameter("orarioAperturaNegozioSabato") + " - " + request.getParameter("orarioChiusuraNegozioSabato") : "Chiuso") + " ";
            orario += ", Domenica: " + ((request.getParameter("chiusoDomenica") == null) ? request.getParameter("orarioAperturaNegozioDomenica") + " - " + request.getParameter("orarioChiusuraNegozioDomenica") : "Chiuso") + " ";
            
            negozio.setOrarioNegozio(orario);
            
            daoNegozio.insertShop(negozio);
            
            log("SHOP INSERITO");
            
            List<ModelloNegozio> listaNegozi = daoNegozio.selectShopByOpenDate(utente.getId());
            negozio.setId(listaNegozi.get(0).getId());
            
            log("LISTA DI NEGOZIO TROVATA, id: " + negozio.getId());
            
            daoNegozio.insertShopImage(negozio.getId(), "http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/square.png");
            
            log("IMMAGINE SHOP INSERITA");
            
            forward = USERPAGE;
        }
        else if(action.equalsIgnoreCase("cancNegozio")){
            int idNegozio = Integer.parseInt(request.getParameter("id"));
            daoNegozio.updateShopStatus(idNegozio,0);
            forward = USERPAGE;
        }
        else if(action.equalsIgnoreCase("apriNegozio")){
            int idNegozio = Integer.parseInt(request.getParameter("id"));
            daoNegozio.updateShopStatus(idNegozio,1);
            forward = USERPAGE;
        }
        else if(action.equalsIgnoreCase("gestisciNegozio")){
            int idNegozio = Integer.parseInt(request.getParameter("id"));
            tris<ModelloNegozio, ModelloIndirizzo, ModelloImmagineNegozio> trisNegozioIndirizzoImmagine = daoNegozio.selectStoreAddressImageByStoreID(idNegozio);
            
            request.setAttribute("negozio", trisNegozioIndirizzoImmagine.getL());
            request.setAttribute("indirizzo", trisNegozioIndirizzoImmagine.getC());
            request.setAttribute("immagine", trisNegozioIndirizzoImmagine.getR());
            forward = SHOPPAGE;
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
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
