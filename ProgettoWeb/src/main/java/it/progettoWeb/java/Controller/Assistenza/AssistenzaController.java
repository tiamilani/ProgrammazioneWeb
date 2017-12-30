package it.progettoWeb.java.Controller.Assistenza;

import it.progettoWeb.java.database.Dao.Assistenza.DaoAssistenza;
import it.progettoWeb.java.database.Dao.Oggetto.DaoOggetto;
import it.progettoWeb.java.database.Dao.Ordine.DaoOrdine;
import it.progettoWeb.java.database.Dao.Utente.DaoUtente;
import it.progettoWeb.java.database.Model.Assistenza.ModelloAssistenza;
import it.progettoWeb.java.database.Model.Assistenza.ModelloListeAssistenza;
import it.progettoWeb.java.database.Model.Oggetto.ModelloOggetto;
import it.progettoWeb.java.database.Model.Ordine.ModelloOrdine;
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import it.progettoWeb.java.utility.pair.pair;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 2017-12-24 23:04
 * @author FBrug
 */
public class AssistenzaController extends HttpServlet
{    
    private static String ERROR_PAGE = "/jspFile/Finale/Error/ricercaErrata.jsp";
    private static String SHOW_ASSISTANCES = "/jspFile/Finale/Assistenza/assistanceManagement.jsp";
    private static String RESOLVE_ASSITANCE = "/jspFile/Finale/Assistenza/assistanceResolution.jsp";
    private DaoAssistenza daoAssistenza;
    private DaoUtente daoUtente;
    private DaoOggetto daoOggetto;
    private DaoOrdine daoOrdine;
    
    /**
     * Costruttore della classe AssistenzaController
    */
    public AssistenzaController()
    {
        super();
        daoAssistenza = new DaoAssistenza();
        daoUtente = new DaoUtente();
        daoOggetto = new DaoOggetto();
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
            throws ServletException, IOException
    {
        String forward;
        forward = ERROR_PAGE; request.setAttribute("errore", "404 Pagina non trovata");
        
        try
        {
            String action = request.getParameter("action");
            ModelloUtente utenteSessione = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            
            //Se l'utente loggato esiste ed è amministatore
            if(utenteSessione.getId() > 0 && utenteSessione.getUtenteType() == 2)
            {
                if(action.equalsIgnoreCase("listAssistances"))
                {
                    forward = SHOW_ASSISTANCES;
                    
                    ModelloListeAssistenza listaAssistenze = new ModelloListeAssistenza(
                            daoAssistenza.selectAssistanceByAdminId(utenteSessione.getId()));
                    
                    List<pair<ModelloAssistenza, String>> assistenzeAperte = new ArrayList<>();
                    List<pair<ModelloAssistenza, String>> assistenzeChiuse = new ArrayList<>();
                    
                    for(ModelloAssistenza assistenza : listaAssistenze.getList())
                    {
                        ModelloUtente utenteRichiedente = daoUtente.getUserById(assistenza.getIdUtente());
                        String nomeUtenteRichiedente = utenteRichiedente.getCognome() + " " + utenteRichiedente.getNome();

                        if(assistenza.getStato() == 0)
                            assistenzeAperte.add(new pair<>(assistenza, nomeUtenteRichiedente));
                        else
                            assistenzeChiuse.add(new pair<>(assistenza, nomeUtenteRichiedente));
                    }
                    
                    request.setAttribute("assistenzeAperte", assistenzeAperte);
                    request.setAttribute("assistenzeChiuse", assistenzeChiuse);
                }
                else if(action.equalsIgnoreCase("resolve"))
                {
                    
                    forward = RESOLVE_ASSITANCE;
                    int index = Integer.parseInt(request.getParameter("id"));
                    
                    ModelloAssistenza assistenza = daoAssistenza.selectSpecifiedInfoSupport(index);
                    ModelloUtente utenteRichiedente = daoUtente.selectUserByID(assistenza.getIdUtente());
                    request.setAttribute("assistenza", assistenza);
                    request.setAttribute("utenteRichiedente", utenteRichiedente);
                    
                    if(assistenza.getIdVenditore() > 0)
                    {
                        ModelloUtente venditoreContestato = daoUtente.selectUserByID(assistenza.getIdVenditore());
                        request.setAttribute("venditoreContestato", venditoreContestato);
                    }
                    if(assistenza.getIdOggetto() !=  null || assistenza.getIdOggetto() != "")
                    {
                        ModelloOggetto oggettoContestato = daoOggetto.getObjectById(assistenza.getIdOggetto());
                        request.setAttribute("oggettoContestato", oggettoContestato);
                    }
                    if(assistenza.getIdOrdine() > 0)
                    {
                        int ordineContestato = daoOrdine.selectOrdersByIdOrder(assistenza.getIdOrdine()).get(0).getIdOrdine();
                        request.setAttribute("ordineContestato", ordineContestato);
                    }
                }
            }
        }
        catch (Exception e) 
        {
            System.out.println("error message = " + e.getMessage());
            forward = ERROR_PAGE;
            request.setAttribute("ECCEZIONE", e.getMessage());
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
            throws ServletException, IOException
    {
        String forward;
        forward = ERROR_PAGE; request.setAttribute("errore", "404 Pagina non trovata");
        try
        {
            String action = request.getParameter("action");
            int idA = Integer.parseInt(request.getParameter("idA"));
            String solution = request.getParameter("solution");
            
            if(action.equalsIgnoreCase("save"))
            {
                daoAssistenza.updateAssistanceSolution(idA, solution);
                
                forward = "AssistenzaController?action=listAssistances";
            }
            else if(action.equalsIgnoreCase("saveAndClose"))
            {
                ModelloAssistenza ass = daoAssistenza.selectSpecifiedInfoSupport(idA);
                ass.setDataChiusura(new Timestamp(System.currentTimeMillis()));
                ass.setSoluzione(solution);
                ass.setStato(1);
                
                daoAssistenza.updateAssistance(ass);
                
                forward = "AssistenzaController?action=listAssistances";
            }
        }
        catch (Exception e) 
        {
            System.out.println("error message = " + e.getMessage());
            forward = ERROR_PAGE;
            request.setAttribute("ECCEZIONE", e.getMessage());
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
