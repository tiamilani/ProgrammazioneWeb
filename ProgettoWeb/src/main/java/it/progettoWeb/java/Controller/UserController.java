/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.progettoWeb.java.database.Dao.Utente.DaoUtente;
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import it.progettoWeb.java.database.query.generics.genericsQuery;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author mattia
 */
public class UserController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/jspFile/DaoTest/userJsp.jsp";
    private static String LIST_USER = "/jspFile/DaoTest/listUser.jsp";
    private static String HOME_PAGE = "/jspFile/Finale/Index/homePage.jsp";
    private static String ERROR_PAGE = "/jspFile/Finale/Error/ricercaErrata.jsp";
    private DaoUtente dao;

    public UserController() {
        super();
        dao = new DaoUtente();
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

        if (action.equalsIgnoreCase("delete")){
            int userId = Integer.parseInt(request.getParameter("userId"));
            dao.deleteUser(userId);
            forward = LIST_USER;
            request.setAttribute("users", dao.getAllUsers());    
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(request.getParameter("userId"));
            ModelloUtente user = dao.getUserById(userId);
            request.setAttribute("user", user);
        } else if (action.equalsIgnoreCase("listUser")){
            forward = LIST_USER;
            request.setAttribute("users", dao.getAllUsers());
        } else {
            forward = INSERT_OR_EDIT;
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

        if (action.equalsIgnoreCase("selectUser")){
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            ModelloUtente utente = dao.selectUserByEmailAndPassword(email, password);
            request.getSession().removeAttribute("utente");
            request.getSession().setAttribute("utente", utente);
            forward = HOME_PAGE;
            
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        } 
        if(action.equalsIgnoreCase("addUser")){
            forward = HOME_PAGE;
            RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
            
            ModelloUtente utente = new ModelloUtente();
            utente.setNome(request.getParameter("nome"));
            utente.setCognome(request.getParameter("cognome"));
            utente.setMail(request.getParameter("email"));
            utente.setPassword(request.getParameter("password"));
            String confirmPassword = request.getParameter("confirmPassword");
            
            if(!utente.getPassword().equals(confirmPassword)){
                forward=ERROR_PAGE;
                request.setAttribute("errore", "La conferma della password non è uguale alla password");
                //RequestDispatcher view = request.getRequestDispatcher(forward);
                view.forward(request, response);
            }
            
            ModelloUtente userAlreadyExists = dao.selectUserByEmail(utente.getMail());
            if(userAlreadyExists.getId()>0){
                forward=ERROR_PAGE;
                request.setAttribute("errore", "Esiste già un utente con questa email");
                //RequestDispatcher view = request.getRequestDispatcher(forward);
                view.forward(request, response);
            }
            
            utente.setUtenteType(0);
            dao.addUser(utente);
            
            forward = HOME_PAGE;
            //RequestDispatcher view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        }
        
        forward = HOME_PAGE;
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
