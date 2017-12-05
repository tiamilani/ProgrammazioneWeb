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
import it.progettoWeb.java.database.query.users.usersQuery;
import java.io.InputStream;
import java.net.URL;
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

            /*String[] LatLon = GoogleGeoCode(
                indirizzo.getnCivico(),
                indirizzo.getVia(),
                indirizzo.getCitta(),
                indirizzo.getProvincia());

            if(LatLon[0]!=null){
                indirizzo.setLatitudine(Double.parseDouble(LatLon[0]));
            } else {
                indirizzo.setLatitudine(0.0);
            }

            if(LatLon[1]!=null){
                indirizzo.setLongitudine(Double.parseDouble(LatLon[1]));
            } else {
                indirizzo.setLongitudine(0.0);
            }*/

            //indirizzo.setLatitudine(5.0);
            //indirizzo.setLongitudine(5.0);

            daoI.insertAddress(indirizzo,utente.getId());

            //forward = ERROR_PAGE;
            //request.setAttribute("errore", usersQuery.insertAddress(indirizzo.getStato(),indirizzo.getRegione(),indirizzo.getProvincia(),indirizzo.getCitta(),indirizzo.getVia(),indirizzo.getnCivico(),indirizzo.getInterno(),indirizzo.getLatitudine(),indirizzo.getLongitudine(),utente.getId()));

            response.sendRedirect("UserController?action=infoCurrentUser");
            return;
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

/*
    private static final String GEO_CODE_SERVER = "http://maps.googleapis.com/maps/api/geocode/json?";

    private String[] GoogleGeoCode(int nCivico, String via, String citta, String provincia)
    {
        String code = "33, Via Passeggiata Archeologica, Agrigento, AG";
        //String code = nCivico + "," + via + "," + citta + "," + provincia;

        String response = getLocation(code);

        String[] result = parseLocation(response);

        //System.out.println("Latitude: " + result[0]);
        //System.out.println("Longitude: " + result[1]);

        return result;
    }

    private static String getLocation(String code)
    {
        String address = buildUrl(code);

        String content = null;

        try
        {
            URL url = new URL(address);

            InputStream stream = url.openStream();

            try
            {
                int available = stream.available();

                byte[] bytes = new byte[available];

                stream.read(bytes);

                content = new String(bytes);
            }
            finally
            {
                stream.close();
            }

            return (String) content.toString();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static String buildUrl(String code)
    {
        StringBuilder builder = new StringBuilder();

        builder.append(GEO_CODE_SERVER);

        builder.append("address=");
        builder.append(code.replaceAll(" ", "+"));
        builder.append("&sensor=false");

        return builder.toString();
    }

    private static String[] parseLocation(String response)
    {
        // Look for location using brute force.
        // There are much nicer ways to do this, e.g. with Google's JSON library: Gson
        //     https://sites.google.com/site/gson/gson-user-guide

        String[] lines = response.split("\n");

        String lat = null;
        String lng = null;

        for (int i = 0; i < lines.length; i++)
        {
            if ("\"location\" : {".equals(lines[i].trim()))
            {
                lat = getOrdinate(lines[i+1]);
                lng = getOrdinate(lines[i+2]);
                break;
            }
        }

        return new String[] {lat, lng};
    }

    private static String getOrdinate(String s)
    {
        String[] split = s.trim().split(" ");

        if (split.length < 1)
        {
            return null;
        }

        String ord = split[split.length - 1];

        if (ord.endsWith(","))
        {
            ord = ord.substring(0, ord.length() - 1);
        }

        // Check that the result is a valid double
        Double.parseDouble(ord);

        return ord;
    } */
}
