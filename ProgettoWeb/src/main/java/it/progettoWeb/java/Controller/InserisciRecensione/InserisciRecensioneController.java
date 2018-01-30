/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.progettoWeb.java.Controller.InserisciRecensione;

import it.progettoWeb.java.database.Dao.Negozio.DaoNegozio;
import it.progettoWeb.java.database.Dao.Oggetto.DaoOggetto;
import it.progettoWeb.java.database.Dao.Utente.DaoUtente;
import it.progettoWeb.java.database.Dao.immagineRecensione.DaoImmagineRecensione;
import it.progettoWeb.java.database.Dao.recensioneNegozio.DaoRecensioneNegozio;
import it.progettoWeb.java.database.Dao.recensioneOggetto.DaoRecensioneOggetto;
import it.progettoWeb.java.database.Dao.recensioneVenditore.DaoRecensioneVenditore;
import it.progettoWeb.java.database.Model.Negozio.ModelloNegozio;
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
    private DaoNegozio daoNegozio;
    private DaoUtente daoUtente;
    private DaoOggetto daoOggetto;
    private List<String> imageSrcs = new ArrayList<String>();
    
    public InserisciRecensioneController(){
        super();
        daoRecensioneOggetto = new DaoRecensioneOggetto();
        daoRecensioneNegozio = new DaoRecensioneNegozio();
        daoRecensioneVenditore = new DaoRecensioneVenditore();
        daoImmagineRecensione = new DaoImmagineRecensione();
        daoNegozio = new DaoNegozio();
        daoUtente = new DaoUtente();
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
        request.setCharacterEncoding("UTF-8");
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
        request.setCharacterEncoding("UTF-8");
        String forward = "";
        String action = request.getParameter("action");
        
        if(action.equals("Oggetto")) {
            int idUtente = Integer.parseInt(request.getParameter("utenteReview"));
            String idOggetto = request.getParameter("oggettoReview");
            String testoRecensione = request.getParameter("testoReview").replace('\'', ' ');
            int valutazioneRecensione = Integer.parseInt(request.getParameter("valutazioneReview"));
            
            forward = "/objectSelectedController?idOggetto=" + idOggetto;
            
            ModelloRecensioneOggetto recensioneOggetto = new ModelloRecensioneOggetto();
            recensioneOggetto.setIdUtente(idUtente);
            recensioneOggetto.setIdOggetto(idOggetto);
            recensioneOggetto.setTesto(testoRecensione);
            recensioneOggetto.setUtilita(0);
            recensioneOggetto.setValutazione(valutazioneRecensione);
            daoRecensioneOggetto.addReviewToObject(recensioneOggetto);
            
            imageSrcs.clear();
            if(getImages(request, response)) {
                for (String src : imageSrcs) {
                    ModelloImmagineRecensione immagineRecensione = new ModelloImmagineRecensione();
                    immagineRecensione.setIdR(daoRecensioneOggetto.selectReviewsByData(idOggetto, idUtente, testoRecensione, valutazioneRecensione).getId());
                    immagineRecensione.setSrc(src);
                    daoImmagineRecensione.addImageReviewSet(immagineRecensione);
                }
            }
            
            int numReviews = daoRecensioneOggetto.howManyReviewsO(idOggetto);
            double newMedia = ((daoOggetto.getObjectById(idOggetto).getValutazione() * numReviews + valutazioneRecensione) / (numReviews + valutazioneRecensione));
            daoOggetto.updateObjectStars(idOggetto, newMedia);
        }
        else if(action.equals("Negozio")) {
            int idUtente = Integer.parseInt(request.getParameter("utenteReview"));
            int idNegozio = Integer.parseInt(request.getParameter("negozioReview"));
            String testoRecensione = request.getParameter("testoReview").replace('\'', ' ');
            int valutazioneRecensione = Integer.parseInt(request.getParameter("valutazioneReview"));
            
            forward = "/UserController?action=DescrizioneNegozio&idNegozio=" + idNegozio;
            
            ModelloRecensioneNegozio recensioneNegozio = new ModelloRecensioneNegozio();
            recensioneNegozio.setIdUtente(idUtente);
            recensioneNegozio.setIdNegozio(idNegozio);
            recensioneNegozio.setTesto(testoRecensione);
            recensioneNegozio.setUtilita(0);
            recensioneNegozio.setValutazione(valutazioneRecensione);
            daoRecensioneNegozio.addReviewToStore(recensioneNegozio);
            
            int numReviews = daoRecensioneNegozio.howManyReviews(idNegozio);
            double newMedia = ((daoNegozio.getStoreById(idNegozio).getValutazione() * numReviews + valutazioneRecensione) / (numReviews + valutazioneRecensione));
            daoNegozio.updateShopStars(idNegozio, newMedia);
        }
        else if(action.equals("Venditore")) {
            int idUtente = Integer.parseInt(request.getParameter("utenteReview"));
            int idVenditore = Integer.parseInt(request.getParameter("venditoreReview"));
            String testoRecensione = request.getParameter("testoReview").replace('\'', ' ');
            int valutazioneRecensione = Integer.parseInt(request.getParameter("valutazioneReview"));
            
            forward = "/UserController?action=DescrizioneVenditore&idUtente=" + idVenditore;
            
            ModelloRecensioneVenditore recensioneVenditore = new ModelloRecensioneVenditore();
            recensioneVenditore.setIdUtente(idUtente);
            recensioneVenditore.setIdVenditore(idVenditore);
            recensioneVenditore.setTesto(testoRecensione);
            recensioneVenditore.setUtilita(0);
            recensioneVenditore.setValutazione(valutazioneRecensione);
            daoRecensioneVenditore.addReviewToSeller(recensioneVenditore);
            
            int numReviews = daoRecensioneVenditore.howManyReviews(idVenditore);
            double newMedia = ((daoUtente.getUserById(idVenditore).getValutazione() * numReviews + valutazioneRecensione) / (numReviews + valutazioneRecensione));
            daoUtente.updateUserStars(idVenditore, newMedia);
        }
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
    
    /**
     * Get uploaded images
     */
    private boolean getImages(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String appPath = request.getServletContext().getRealPath("");
        String savePath = appPath + SAVE_DIR;
        String savePathFake = request.getContextPath() + "/" + SAVE_DIR;
        
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        
        boolean imageSaved = false;
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            if(!fileName.isEmpty()) {
                fileName = new File(fileName).getName();
                part.write(savePath + "/" + fileName);
                imageSrcs.add(savePathFake + "/" + fileName);
                imageSaved = true;
                System.out.println(savePath + "/" + fileName);
                System.out.println(savePathFake + "/" + fileName);
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
