/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.Controller.HomeController;

import io.jsonwebtoken.Claims;
import it.progettoWeb.java.database.Dao.Utente.DaoUtente;
import it.progettoWeb.java.utility.javaMail.SendEmail;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import org.apache.commons.codec.binary.Hex;

/**
 *
 * @author Damiano
 */
public class PasswordReset extends HttpServlet {
    
    private static String HOME_PAGE = "/jspFile/Finale/Index/index.jsp";
    private int tokenExpiration = 2;
    private DaoUtente daoUtente;
    
    public PasswordReset(){
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
        
        String action = request.getParameter("action");
        if(action != null && action.equalsIgnoreCase("confirm")){
            String email = request.getParameter("changed-email");
            String password = request.getParameter("changed-password");
            String pswdConfirm = request.getParameter("changed-confirmPassword");
            
            System.out.println("Mail: " + email + ";\nPassword: " + password);
            
            if(password.equals(pswdConfirm)){
                ModelloUtente utente = daoUtente.selectUserByEmail(email);
                String hexPassword = "";
                try {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    byte[] newPassword = md.digest(password.getBytes());
                    password = newPassword.toString();
                    hexPassword = new String(Hex.encodeHex(newPassword));
                    
                    System.out.println("Stringa in esadecimale " + hexPassword);
                } catch (NoSuchAlgorithmException ex) {
                }
                
                if(hexPassword.length() != 0){
                    utente.setPassword(hexPassword);
                    daoUtente.updateUserPasswordByUserID(utente);
                    System.out.println("Conferma, modifica password avvenuta");
                    request.setAttribute("changedPassword", true);
                    RequestDispatcher view = request.getRequestDispatcher(HOME_PAGE);
                    view.forward(request, response);
                }else{
                    System.out.println("Errore: problema durante la conversione della password in esadecimale");
                    String basePage = request.getHeader("referer");
                    response.sendRedirect(basePage);
                }
            }else{
                System.out.println("Errore: le due password non corrispondono");
                String basePage = request.getHeader("referer");
                response.sendRedirect(basePage);
            }
        }else{
            String basePage = request.getHeader("referer");

            String email = request.getParameter("resetting-email");
            String link = "http://localhost:8080/ProgettoWeb/PasswordResetRequest?token=";

            Key key = MacProvider.generateKey();

            Claims claims = Jwts.claims().setSubject(email);
            claims.put("mailID", email);
            Date expire = new Date();
            expire.setTime(expire.getTime() + (tokenExpiration * 60 * 60 * 1000));
            String token = Jwts.builder().setClaims(claims).setExpiration(expire).signWith(SignatureAlgorithm.HS512, key).compact();
            link = link + token;
            Date scadenza = new Date(expire.getTime());

            String encodedKey = Base64.getEncoder().encodeToString(key.getEncoded());
            daoUtente.setKey(token, encodedKey);


            SendEmail.passwordForget(email, link);

            /*try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet PasswordReset</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet PasswordReset at " + request.getContextPath() + "</h1>");
                out.println("<p>L'email a cui resettare la password è: " + email + "<p>");
                out.println("<p>Il token che ho generato è: " + token + "<p>");
                out.println("<p>Il link che invierò è: " + link + "<p>");
                out.println("<p>Il link scadrà il: " + scadenza + "<p>");
                out.println("</body>");
                out.println("</html>");
            }*/

            response.sendRedirect(basePage);
        }
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
