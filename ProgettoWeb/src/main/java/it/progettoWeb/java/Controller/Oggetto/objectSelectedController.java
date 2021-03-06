package it.progettoWeb.java.Controller.Oggetto;

import it.progettoWeb.java.database.Dao.Negozio.DaoNegozio;
import it.progettoWeb.java.database.Dao.Oggetto.DaoOggetto;
import it.progettoWeb.java.database.Dao.Ordine.DaoOrdine;
import it.progettoWeb.java.database.Dao.Utente.DaoUtente;
import it.progettoWeb.java.database.Dao.immagineOggetto.DaoImmagineOggetto;
import it.progettoWeb.java.database.Dao.indirizzo.DaoIndirizzo;
import it.progettoWeb.java.database.Dao.recensioneOggetto.DaoRecensioneOggetto;
import it.progettoWeb.java.database.Dao.tipoSpedizione.DaoTipoSpedizione;
import it.progettoWeb.java.database.Model.Negozio.ModelloNegozio;
import it.progettoWeb.java.database.Model.Oggetto.ModelloListeOggetto;
import it.progettoWeb.java.database.Model.Oggetto.ModelloOggetto;
import it.progettoWeb.java.database.Model.Ordine.ModelloListeOrdine;
import it.progettoWeb.java.database.Model.Ordine.ModelloOrdine;
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import it.progettoWeb.java.database.Model.immagineOggetto.ModelloImmagineOggetto;
import it.progettoWeb.java.database.Model.immagineOggetto.ModelloListeImmagineOggetto;
import it.progettoWeb.java.database.Model.immagineRecensione.ModelloListeImmagineRecensione;
import it.progettoWeb.java.database.Model.indirizzo.ModelloIndirizzo;
import it.progettoWeb.java.database.Model.recensioneOggetto.ModelloListeRecensioneOggetto;
import it.progettoWeb.java.database.Model.recensioneOggetto.ModelloRecensioneOggetto;
import it.progettoWeb.java.database.Model.rispostaOggetto.ModelloRispostaOggetto;
import it.progettoWeb.java.utility.pair.pair;
import it.progettoWeb.java.utility.tris.tris;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;  

/**
 *
 * @author mattia
 */
public class objectSelectedController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static String HOME_PAGE = "/jspFile/Finale/DescrizioneOggetto/descrizioneOggetto.jsp";
    private static String ERROR_PAGE = "/ProgettoWeb/jspFile/Finale/Error/ricercaErrata.jsp";
    private DaoNegozio daoNegozio;
    private DaoUtente daoUtente;
    private DaoRecensioneOggetto daoRecensione;
    private DaoOggetto daoOggetto;
    private DaoIndirizzo daoIndirizzo;
    private DaoImmagineOggetto daoImmagineOggetto;
    private DaoRecensioneOggetto daoRecensioneOggetto;
    private DaoTipoSpedizione daoTipoSpedizione;
    private DaoOrdine daoOrdine;
    
    public objectSelectedController() {
        super();
        daoNegozio = new DaoNegozio();
        daoUtente = new DaoUtente();
        daoRecensione = new DaoRecensioneOggetto();
        daoOggetto = new DaoOggetto();
        daoIndirizzo = new DaoIndirizzo();
        daoImmagineOggetto = new DaoImmagineOggetto();
        daoRecensioneOggetto = new DaoRecensioneOggetto();
        daoTipoSpedizione = new DaoTipoSpedizione();
        daoOrdine = new DaoOrdine();
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
        request.setCharacterEncoding("UTF-8");
        String forward = HOME_PAGE;
        String idOggetto = request.getParameter("idOggetto");
        
        ModelloOggetto oggetto = daoOggetto.getObjectById(idOggetto);
        ModelloNegozio negozio = daoNegozio.getStoreById(oggetto.getIdNegozio());
        ModelloUtente venditore = daoUtente.getUserById(negozio.getIdVenditore());
        tris<List<ModelloRecensioneOggetto>, List<ModelloListeImmagineRecensione>, List<ModelloUtente>> recensioniUtenteImmagini;
        recensioniUtenteImmagini = daoRecensioneOggetto.selectReviewImagesUserByObject(idOggetto);
        
        pair<List<ModelloRispostaOggetto>,List<ModelloUtente>> risposteOggetto;
        risposteOggetto = daoRecensioneOggetto.selectAnswerUserByObject(idOggetto);
        
        ModelloListeRecensioneOggetto recensioni = new ModelloListeRecensioneOggetto(daoRecensione.selectReviewsObjects(idOggetto));
        ModelloIndirizzo indirizzo = daoIndirizzo.selectAddressByIdAddress(negozio.getIdI());
        
        ModelloListeImmagineOggetto immagini = new ModelloListeImmagineOggetto(daoImmagineOggetto.selectPhotoObject(idOggetto));
        pair<List<ModelloOggetto>, List<ModelloImmagineOggetto>> listaOggettiImmagini = daoOggetto.selectRandomObjectsAndImage(12);
        ModelloListeOggetto listaOggetti = new ModelloListeOggetto(listaOggettiImmagini.getL());
        ModelloListeImmagineOggetto listaImmaginiOggetto = new ModelloListeImmagineOggetto(listaOggettiImmagini.getR());
        
        try {
            ModelloUtente utenteSessione = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            
            if(utenteSessione.getId() != -1)
            {
                if(daoRecensioneOggetto.buyOrNotObject(idOggetto, utenteSessione.getId()) > 0) {
                    request.setAttribute("canReviewsO", true);
                    request.setAttribute("canUploadImages", true);
                }
                else {
                    request.setAttribute("canReviewsO", false);
                    request.setAttribute("canUploadImages", false);
                }
            }
        } catch (NullPointerException e) {}
        
        request.setAttribute("oggetto", oggetto);
        request.setAttribute("negozio", negozio);
        request.setAttribute("venditore", venditore);
        request.setAttribute("recensioni", recensioni);
        request.setAttribute("indirizzo", indirizzo);
        request.setAttribute("listaImmagini", immagini);
        request.setAttribute("listaOggetti", listaOggetti);
        request.setAttribute("listaImmaginiOggetto", listaImmaginiOggetto);
        request.setAttribute("recensioniUtenteImmagini", recensioniUtenteImmagini);
        request.setAttribute("risposteOggetto", risposteOggetto);
        
        RequestDispatcher view = request.getRequestDispatcher(forward);
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
        request.setCharacterEncoding("UTF-8");
        String forward = "/ProgettoWeb/objectSelectedController?idOggetto=";
        
        try
        {
            String action = request.getParameter("action");
            
            ModelloUtente utenteSessione = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            ModelloListeOrdine carrello = (ModelloListeOrdine)request.getSession().getAttribute("carrelloSessione");
            int idUtente = utenteSessione.getId();
        
            if (action.equals("add")) {
                
                String jsonString = request.getParameter("shipType");
                
                Object obj = JSONValue.parse(jsonString);  
                JSONObject jsonObject = (JSONObject) obj;  

                int quantita = Integer.parseInt(request.getParameter("numNow"));
                double prezzo = Double.parseDouble(jsonObject.get("prezzo").toString());
                int negozio = Integer.parseInt(jsonObject.get("negozio").toString());
                String oggetto = (String) jsonObject.get("oggetto");
                
                forward += oggetto;
                
                boolean alreadyInCart = false;
                for(ModelloOrdine ordine : carrello.getList())
                    if(ordine.getIdOggetto().equalsIgnoreCase(oggetto))
                    {
                        if(idUtente != -1)
                            daoOrdine.changeOrderQuantity(ordine.getIdOrdine(), oggetto, ordine.getIdUtente(), (ordine.getQuantita() + quantita));
                        
                        carrello.getList().remove(ordine);
                        ordine.setQuantita(ordine.getQuantita() + quantita);
                        carrello.add(ordine);
                        alreadyInCart = true;
                        break;
                    }
                
                if(!alreadyInCart)
                {                    
                    ModelloOrdine addElemento = new ModelloOrdine();
                    
                    addElemento.setStato(0);
                    addElemento.setDataOrdine(new Timestamp(System.currentTimeMillis()));
                    addElemento.setIdNegozio(negozio);
                    addElemento.setIdOggetto(oggetto);
                    addElemento.setIdUtente(idUtente);
                    addElemento.setPrezzoDiAcquisto(prezzo);
                    addElemento.setQuantita(quantita);
                    
                    if(carrello.getSize() > 0)
                        addElemento.setIdOrdine(carrello.get(0).getIdOrdine());
                    
                    carrello.add(addElemento);
                    
                    if(idUtente != -1) {
                        ModelloListeOrdine carrelloInDB = new ModelloListeOrdine(daoOrdine.selectOrdersComplete(idUtente, 0));
                        if(carrelloInDB.getSize() > 0){
                            addElemento.setIdOrdine(carrelloInDB.get(0).getIdOrdine());
                            daoOrdine.insertObjectInCart(addElemento);
                        }
                        else
                        {
                            if(carrello.getSize() > 0){
                                ModelloOrdine primoOggetto = carrello.get(0);
                                primoOggetto.setIdUtente(idUtente);
                                daoOrdine.insertObjectInCartFirstTime(primoOggetto);
                            }
                        }
                    }
                }
                
                request.getSession().removeAttribute("carrelloSessione");
                request.setAttribute("carrelloSessione", carrello);
                request.getSession().setAttribute("carrelloSessione", carrello);
            }
        } catch (NullPointerException e) {
            forward = ERROR_PAGE;
        }
        
        response.sendRedirect(forward);
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
