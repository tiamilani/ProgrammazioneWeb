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
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import it.progettoWeb.java.database.Model.indirizzo.ModelloIndirizzo;
import it.progettoWeb.java.database.Model.indirizzo.ModelloListeIndirizzo;
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
    private DaoUtente daoU;
    private DaoIndirizzo daoI;

    public IndirizzoController() {
        super();
        daoU = new DaoUtente();
        daoI = new DaoIndirizzo();
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
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete"))
        {
            int addrId = Integer.parseInt(request.getParameter("addrId"));
            daoI.deleteAddress(addrId);
            
            forward = LIST_ADDRESS;
            int userId = Integer.parseInt(request.getParameter("userId"));
            request.setAttribute("address", daoI.selectAddressByUserID(userId));
            request.setAttribute("userId", userId);
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
        else 
        {
            int userId = Integer.parseInt(request.getParameter("userId"));
            
            forward = INSERT_OR_EDIT;
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
        
        
        System.out.println("---------------------------------------------------------");
        
        ModelloIndirizzo addr = new ModelloIndirizzo();
        int userId = Integer.parseInt(request.getParameter("userId"));
        
        System.out.println("userId = "+userId);
        
        addr.setStato(request.getParameter("stato"));System.out.println("stato = "+request.getParameter("stato"));
        addr.setRegione(request.getParameter("regione"));System.out.println("regione = "+request.getParameter("regione"));
        addr.setProvincia(request.getParameter("provincia"));System.out.println("provincia = "+request.getParameter("provincia"));
        addr.setCitta(request.getParameter("citta"));System.out.println("citta = "+request.getParameter("citta"));
        addr.setVia(request.getParameter("via"));System.out.println("via = "+request.getParameter("via"));
        addr.setnCivico(Integer.parseInt(request.getParameter("nCivico")));System.out.println("nCivico = "+Integer.parseInt(request.getParameter("nCivico")));
        addr.setInterno(Integer.parseInt(request.getParameter("interno")));System.out.println("interno = "+Integer.parseInt(request.getParameter("interno")));
        
        String[] LatLon = GoogleGeoCode(
                Integer.parseInt(request.getParameter("nCivico")),
                request.getParameter("via"),
                request.getParameter("citta"),
                request.getParameter("provincia"));
        
        addr.setLatitudine(Double.parseDouble(LatLon[0]));System.out.println("stato = "+Double.parseDouble(LatLon[0]));
        addr.setLongitudine(Double.parseDouble(LatLon[1]));System.out.println("stato = "+Double.parseDouble(LatLon[1]));
        
        
        String addrId = request.getParameter("addrid");System.out.println("addrid = "+request.getParameter("addrid"));
        if(addrId == null || addrId.isEmpty())
        {
            String x =daoI.insertAddress(addr,userId); 
            System.out.println("RESULT INSERT = "+x);
        }
        else
        {
            addr.setIdI(Integer.parseInt(addrId));
            String x =daoI.updateUserAddressByAddressID(addr);
            System.out.println("RESULT UPDATE = "+x);
        }
        
        RequestDispatcher view = request.getRequestDispatcher(LIST_ADDRESS);
        request.setAttribute("address", daoI.selectAddressByUserID(userId));
        request.setAttribute("userId", userId);
        view.forward(request, response);
        
        /*
        ModelloUtente user = new ModelloUtente();
        
        user.setNome(request.getParameter("nome"));
        user.setCognome(request.getParameter("cognome"));
        user.setMail(request.getParameter("mail"));
        user.setPassword(request.getParameter("password"));
        user.setAvatar("0");
        user.setValutazione(0);
        user.setUtenteType(Integer.parseInt(request.getParameter("UserType")));
        user.setEmailConfermata(false);
        
        String userid = request.getParameter("userid");
        if(userid == null || userid.isEmpty())
        {
            daoU.addUser(user);
        }
        else
        {
            user.setId(Integer.parseInt(userid));
            daoU.updateUser(user);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_ADDRESS);
        request.setAttribute("users", daoU.getAllUsers());
        view.forward(request, response);*/
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

    
    private static final String GEO_CODE_SERVER = "http://maps.googleapis.com/maps/api/geocode/json?";

    private String[] GoogleGeoCode(int nCivico, String via, String citta, String provincia)
    {
        //String code = "33, Via Passeggiata Archeologica, Agrigento, AG";
        String code = nCivico + "," + via + "," + citta + "," + provincia;

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
    }
}
