/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.Controller.HomeController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import it.progettoWeb.java.database.Dao.Utente.DaoUtente;
import java.io.IOException;
import java.util.Base64;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DAS
 */
public class PasswordResetRequest extends HttpServlet {

    private static String CHANGE_PAGE = "/jspFile/Finale/Index/reset.jsp";
    private int tokenExpiration = 2;
    private DaoUtente daoUtente;
    
    public PasswordResetRequest(){
        super();
        daoUtente = new DaoUtente();
        
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
        response.setContentType("text/html;charset=UTF-8");
        
        System.out.println("Se sei arrivato qui è perché hai bisogno di resettare la password");
        
        String token = request.getParameter("token");
        System.out.println(token);
        String encodedKey = daoUtente.getKey(token);
        System.out.println("La chiave è: " + encodedKey);
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        
        Jws<Claims> parseClaimsJws = Jwts.parser().setSigningKey(decodedKey).parseClaimsJws(token);
        String userEmail = parseClaimsJws.getBody().getSubject();

        
        /*try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ResetRequest</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PasswordResetRequest at " + request.getContextPath() + "</h1>");
            out.println("<p>L'utente che ha richiesto questa modifica è: " + userEmail + "<p>");
            out.println("<p>Il token è: " + token + "<p>");
            out.println("</body>");
            out.println("</html>");
        }*/
        
        request.setAttribute("resetEmail", userEmail);
        System.out.println("Email da resettare: " + request.getAttribute("resetEmail"));
        RequestDispatcher view = request.getRequestDispatcher(CHANGE_PAGE);
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
