/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.Controller.SearchObject;

import it.progettoWeb.java.database.Dao.Oggetto.DaoOggetto;
import it.progettoWeb.java.database.Model.Oggetto.ModelloOggetto;
import java.io.IOException;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DAS
 */
public class AutocompleteSearchController extends HttpServlet {

    private DaoOggetto daoOggetto;
    
    public AutocompleteSearchController(){
        super();
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
        response.setContentType("application/json");
        
        /*System.out.println(request.getParameter("research"));
        System.out.println("Arrivato nella servlet");*/
        
        /*JsonObject jsonObject = Json.createObjectBuilder()
                .add("suggestions",
                        Json.createArrayBuilder().add("iPhone SE")
                                              .add("Motosega")
                                              .add("Diocane")
                                              .add("Huawei")
                                              .add("HTC U11")
                                              .build()
                ).build();*/
        
        //List<ModelloOggetto> research = daoOggetto.selectObjectByName(request.getParameter("research"));
        //System.out.println("La ricerca per " + request.getParameter("research") + " ha fornito " + research.size() + " risultati");
        List<String> similar = daoOggetto.selectByStringSimilarity(request.getParameter("research"));
        //System.out.println("Oggetti trovati nel DB: " + similar.size());
        
        JsonArrayBuilder values = Json.createArrayBuilder();
        for (String entry : similar) {
            values.add(entry);
        }
        
        JsonArray listaValori = values.build();
        
        JsonObject jsonObject = Json.createObjectBuilder().
                add("suggestions", listaValori).build();
        
        String suggestions = jsonObject.toString();
        //System.out.println(suggestions);

        response.getWriter().write(suggestions);
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
