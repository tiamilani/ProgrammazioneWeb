/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.Controller.InserisciRecensione;

import it.progettoWeb.java.database.Dao.immagineRecensione.DaoImmagineRecensione;
import it.progettoWeb.java.database.Dao.recensioneNegozio.DaoRecensioneNegozio;
import it.progettoWeb.java.database.Dao.recensioneOggetto.DaoRecensioneOggetto;
import it.progettoWeb.java.database.Dao.recensioneVenditore.DaoRecensioneVenditore;
import it.progettoWeb.java.database.Model.immagineRecensione.ModelloImmagineRecensione;
import it.progettoWeb.java.database.Model.recensioneNegozio.ModelloRecensioneNegozio;
import it.progettoWeb.java.database.Model.recensioneOggetto.ModelloRecensioneOggetto;
import it.progettoWeb.java.database.Model.recensioneVenditore.ModelloRecensioneVenditore;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/InserisciRecensioneController")
@MultipartConfig(fileSizeThreshold = 1024*1024*2, // 2MB
                 maxFileSize = 1024*1024*10,      // 10MB
                 maxRequestSize = 1024*1024*50)   // 50MB

/**
 *
 * @author andreafadi
 */
public class InserisciRecensioneController extends HttpServlet {
    /**
     * Name of the directory where uploaded files will be saved, relative to
     * the web application directory.
     */
    private static final String SAVE_DIR = "uploadFiles";
    private static final long serialVersionUID = 1L;
    private DaoRecensioneOggetto daoRecensioneOggetto;
    private DaoRecensioneNegozio daoRecensioneNegozio;
    private DaoRecensioneVenditore daoRecensioneVenditore;
    private DaoImmagineRecensione daoImmagineRecensione;
    private List<String> imageSrcs = new ArrayList<String>();
    
    public InserisciRecensioneController(){
        super();
        daoRecensioneOggetto = new DaoRecensioneOggetto();
        daoRecensioneNegozio = new DaoRecensioneNegozio();
        daoRecensioneVenditore = new DaoRecensioneVenditore();
        daoImmagineRecensione = new DaoImmagineRecensione();
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String forward = "/jspFile/Finale/Index/index.jsp";
        String action = request.getParameter("action");
        
        if(action.equals("Oggetto")) {
            int idUtente = Integer.parseInt(request.getParameter("utenteReview"));
            String idOggetto = request.getParameter("oggettoReview");
            String testoRecensione = request.getParameter("testoReview");
            int valutazioneRecensione = Integer.parseInt(request.getParameter("valutazioneReview"));
            
            ModelloRecensioneOggetto recensioneOggetto = new ModelloRecensioneOggetto();
            recensioneOggetto.setIdUtente(idUtente);
            recensioneOggetto.setIdOggetto(idOggetto);
            recensioneOggetto.setTesto(testoRecensione);
            recensioneOggetto.setUtilita(0);
            recensioneOggetto.setValutazione(valutazioneRecensione);
            daoRecensioneOggetto.addReviewToObject(recensioneOggetto);
            
            if(getImages(request, response)) {
                for (String src : imageSrcs) {
                    ModelloImmagineRecensione immagineRecensione = new ModelloImmagineRecensione();
                    immagineRecensione.setIdR(daoRecensioneOggetto.selectReviewsByData(idOggetto, idUtente, testoRecensione, valutazioneRecensione).getId());
                    immagineRecensione.setSrc(src);
                    daoImmagineRecensione.addImageReviewSet(immagineRecensione);
                }
            }
        }
        else if(action.equals("Negozio")) {
            int idUtente = Integer.parseInt(request.getParameter("utenteReview"));
            int idNegozio = Integer.parseInt(request.getParameter("negozioReview"));
            String testoRecensione = request.getParameter("testoReview");
            int valutazioneRecensione = Integer.parseInt(request.getParameter("valutazioneReview"));
            
            ModelloRecensioneNegozio recensioneNegozio = new ModelloRecensioneNegozio();
            recensioneNegozio.setIdUtente(idUtente);
            recensioneNegozio.setIdNegozio(idNegozio);
            recensioneNegozio.setTesto(testoRecensione);
            recensioneNegozio.setUtilita(0);
            recensioneNegozio.setValutazione(valutazioneRecensione);
            daoRecensioneNegozio.addReviewToStore(recensioneNegozio);
        }
        else if(action.equals("Venditore")) {
            int idUtente = Integer.parseInt(request.getParameter("utenteReview"));
            int idVenditore = Integer.parseInt(request.getParameter("venditoreReview"));
            String testoRecensione = request.getParameter("testoReview");
            int valutazioneRecensione = Integer.parseInt(request.getParameter("valutazioneReview"));
            
            ModelloRecensioneVenditore recensioneVenditore = new ModelloRecensioneVenditore();
            recensioneVenditore.setIdUtente(idUtente);
            recensioneVenditore.setIdVenditore(idVenditore);
            recensioneVenditore.setTesto(testoRecensione);
            recensioneVenditore.setUtilita(0);
            recensioneVenditore.setValutazione(valutazioneRecensione);
            daoRecensioneVenditore.addReviewToSeller(recensioneVenditore);
        }
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
    
    /**
     * Get uploaded images
     */
    private boolean getImages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String appPath = request.getServletContext().getRealPath("");
        appPath = appPath.substring(0, (appPath.indexOf("Tomcat") + 6)) + File.separator;
        String savePath = appPath + SAVE_DIR;
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        
        boolean imageSaved = false;
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            if(!fileName.isEmpty()) {
                fileName = new File(fileName).getName();
                System.out.println(savePath + File.separator + fileName);
                part.write(savePath + File.separator + fileName);
                imageSrcs.add(savePath + File.separator + fileName);
                imageSaved = true;
            }
        }
        
        return imageSaved;
    }
    
    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
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
