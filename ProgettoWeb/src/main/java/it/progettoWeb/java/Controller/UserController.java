/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.Controller;

import it.progettoWeb.java.database.Dao.Negozio.DaoNegozio;
import it.progettoWeb.java.database.Dao.Oggetto.DaoOggetto;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.progettoWeb.java.database.Dao.Utente.DaoUtente;
import it.progettoWeb.java.database.Dao.indirizzo.DaoIndirizzo;
import it.progettoWeb.java.database.Dao.recensioneVenditore.DaoRecensioneVenditore;
import it.progettoWeb.java.database.Model.Negozio.ModelloListeNegozio;
import it.progettoWeb.java.database.Model.Negozio.ModelloNegozio;
import it.progettoWeb.java.database.Model.Oggetto.ModelloListeOggetto;
import it.progettoWeb.java.database.Model.Oggetto.ModelloOggetto;
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import it.progettoWeb.java.database.Model.immagineNegozio.ModelloImmagineNegozio;
import it.progettoWeb.java.database.Model.immagineNegozio.ModelloListeImmagineNegozio;
import it.progettoWeb.java.database.Model.immagineOggetto.ModelloImmagineOggetto;
import it.progettoWeb.java.database.Model.immagineOggetto.ModelloListeImmagineOggetto;
import it.progettoWeb.java.database.Model.indirizzo.ModelloIndirizzo;
import it.progettoWeb.java.database.Model.indirizzo.ModelloListeIndirizzo;
import it.progettoWeb.java.database.Model.recensioneVenditore.ModelloListeRecensioneVenditore;
import it.progettoWeb.java.database.query.generics.genericsQuery;
import it.progettoWeb.java.utility.pair.pair;
import it.progettoWeb.java.utility.tris.tris;
import java.util.List;
import javax.servlet.RequestDispatcher;
import it.progettoWeb.java.utility.VerifyRecaptcha;
import javax.servlet.http.Cookie;

/**
 *
 * @author mattia
 */
public class UserController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/jspFile/Finale/Utente/modificaDatiUtente.jsp";
    private static String DESCRIZIONEVENDITORE = "/jspFile/Finale/DescrizioneVenditore/descrizioneVenditore.jsp";
    private static String DESCRIZIONENEGOZIO = "/jspFile/Finale/DescrizioneNegozio/descrizioneNegozio.jsp";
    private static String GESTIONEUTENTE ="/jspFile/Finale/Utente/impostazioneUtente.jsp";
    private static String USERPAGE = "/jspFile/Finale/Utente/utente.jsp";
    private static String HOME_PAGE = "/jspFile/Finale/Index/index.jsp";
    private static String ERROR_PAGE = "/jspFile/Finale/Error/ricercaErrata.jsp";
    private DaoUtente daoUtente;
    private DaoRecensioneVenditore daoRecensione;
    private DaoNegozio daoNegozio;
    private DaoOggetto daoOggetto;
    private DaoIndirizzo daoIndirizzo;

    public UserController() {
        super();
        daoIndirizzo = new DaoIndirizzo();
        daoUtente = new DaoUtente();
        daoRecensione = new DaoRecensioneVenditore();
        daoNegozio = new DaoNegozio();
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


        if (action.equalsIgnoreCase("edit"))
        {
            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(request.getParameter("userId"));
            ModelloUtente user = daoUtente.getUserById(userId);
            ModelloListeIndirizzo lInd = new ModelloListeIndirizzo(daoIndirizzo.selectAddressByUserID(userId));
            request.setAttribute("user", user);
            request.setAttribute("listaIndirizzi", lInd);
        } 
        else if (action.equalsIgnoreCase("listUser")){
            request.setAttribute("users", daoUtente.getAllUsers());
        } 
        else if(action.equals("DescrizioneVenditore")){
            int idUtente = Integer.parseInt(request.getParameter("idUtente"));
            ModelloUtente venditore = daoUtente.selectUserByID(idUtente);
            ModelloListeRecensioneVenditore recensioni = new ModelloListeRecensioneVenditore(daoRecensione.selectSellerReview(idUtente));
            tris<List<ModelloNegozio>, List<ModelloIndirizzo>, List<ModelloImmagineNegozio>> listaNegoziIndirizziImmagini = daoUtente.selectStoreAndAddressImageByUser(idUtente);
            ModelloListeNegozio listaNegozi = new ModelloListeNegozio(listaNegoziIndirizziImmagini.getL());
            ModelloListeIndirizzo listaIndirizzi = new ModelloListeIndirizzo(listaNegoziIndirizziImmagini.getC());
            ModelloListeImmagineNegozio listaImmagini = new ModelloListeImmagineNegozio(listaNegoziIndirizziImmagini.getR());

            request.setAttribute("venditore", venditore);
            request.setAttribute("recensioni", recensioni);
            request.setAttribute("listaNegozi", listaNegozi);
            request.setAttribute("listaIndirizzi", listaIndirizzi);
            request.setAttribute("listaImmagini", listaImmagini);
            forward = DESCRIZIONEVENDITORE;
        } 
        else if(action.equals("infoCurrentUser")){
            ModelloUtente utente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            ModelloListeIndirizzo listaIndirizzi = new ModelloListeIndirizzo(daoIndirizzo.selectAddressByUserID(utente.getId()));
            
            request.setAttribute("listaIndirizzi", listaIndirizzi);
            forward = GESTIONEUTENTE;
        }
        else if(action.equals("updateMail")){
            ModelloUtente utente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            String newEmail = (String)request.getParameter("changeEmail");
            
            if(utente.getMail().equalsIgnoreCase(newEmail)){
                forward=ERROR_PAGE;
                request.setAttribute("errore", "La mail deve differire da quella precedente");
            }
            else{
                utente.setMail(newEmail);

                daoUtente.updateUserEmailByUserID(utente);
            }
            response.sendRedirect("UserController?action=infoCurrentUser");
            return;
        }
        else if(action.equals("DescrizioneNegozio")){
            int idNegozio = Integer.parseInt(request.getParameter("idNegozio"));
            tris<ModelloNegozio, ModelloIndirizzo, ModelloImmagineNegozio> negozioIndirizzoImmagine = daoNegozio.selectStoreAddressImageByStoreID(idNegozio);
            ModelloNegozio negozio = negozioIndirizzoImmagine.getL();
            ModelloImmagineNegozio immagine = negozioIndirizzoImmagine.getR();
            ModelloIndirizzo indirizzo = negozioIndirizzoImmagine.getC();
            pair<List<ModelloOggetto>, List<ModelloImmagineOggetto>> listaOggettiImmagini = daoOggetto.selectObjectsImageSelledByStoreID(idNegozio);
            ModelloListeOggetto listaOggetti = new ModelloListeOggetto(listaOggettiImmagini.getL());
            ModelloListeImmagineOggetto listaImmaginiOggetto = new ModelloListeImmagineOggetto(listaOggettiImmagini.getR());

            request.setAttribute("negozio", negozio);
            request.setAttribute("immagine", immagine);
            request.setAttribute("indirizzo", indirizzo);
            request.setAttribute("listaOggetti", listaOggetti);
            request.setAttribute("listaImmaginiOggetto", listaImmaginiOggetto);
            forward = DESCRIZIONENEGOZIO;
        }
        else if(action.equalsIgnoreCase("logout")){
            request.getSession().invalidate();
            
            Cookie ck=new Cookie("user","");//creating cookie object  
            ck.setMaxAge(0);
            response.addCookie(ck);//adding cookie in the response 
            
            forward = HOME_PAGE;
        }
        else
        {
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


        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("selectUser")){
            
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            ModelloUtente utente = daoUtente.selectUserByEmailAndPassword(email, password);
            
            if(utente.getId() > 0){
                request.getSession().removeAttribute("utenteSessione");
                request.getSession().setAttribute("utenteSessione", utente);

                Cookie ck=new Cookie("user",String.valueOf(utente.getId()));//creating cookie object  
                ck.setMaxAge(-1);
                response.addCookie(ck);//adding cookie in the response  
            }
            forward = HOME_PAGE;
        }
        else if(action.equalsIgnoreCase("addUser")){
            
            String remoteAddr = request.getRemoteAddr();
            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");

            if (VerifyRecaptcha.verify(gRecaptchaResponse)) {
                ModelloUtente utente = new ModelloUtente();
                utente.setNome(request.getParameter("nome"));
                utente.setCognome(request.getParameter("cognome"));
                utente.setMail(request.getParameter("email"));
                utente.setPassword(request.getParameter("password"));
                String confirmPassword = request.getParameter("confirmPassword");

                if(!utente.getPassword().equals(confirmPassword)){
                    forward=ERROR_PAGE;
                    request.setAttribute("errore", "La conferma della password non è uguale alla password");
                }
                else 
                {
                    ModelloUtente userAlreadyExists = daoUtente.selectUserByEmail(utente.getMail());
                    if(userAlreadyExists.getId()>0){
                        forward=ERROR_PAGE;
                        request.setAttribute("errore", "Esiste già un utente con questa email");
                    }
                    else
                    {
                        utente.setUtenteType(0);
                        daoUtente.addUser(utente);

                        forward = HOME_PAGE;
                    }
                }
            } else {
                forward=ERROR_PAGE;
                request.setAttribute("errore", "Captcha errato");
            }
        }
        else if(action.equalsIgnoreCase("updatePassword")){
            String newPassword = request.getParameter("newPassword");
            String newConfirmedPassword = request.getParameter("newConfirmPassword");
            
            ModelloUtente utente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            
            if(!newPassword.equals(newConfirmedPassword)){
                forward=ERROR_PAGE;
                request.setAttribute("errore", "La conferma della password non è uguale alla password");
            }
            else {
                if(utente.getPassword().equals(newPassword)){
                    forward=ERROR_PAGE;
                    request.setAttribute("errore", "La nuova password non può essere uguale a quella vecchia");
                }
                else {
                    utente.setPassword(newPassword);
                    daoUtente.updateUserPasswordByUserID(utente);
                    response.sendRedirect("UserController?action=infoCurrentUser");
                    return;
                }
            }
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
