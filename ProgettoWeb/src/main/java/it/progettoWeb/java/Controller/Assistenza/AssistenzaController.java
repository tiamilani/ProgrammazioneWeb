package it.progettoWeb.java.Controller.Assistenza;

import it.progettoWeb.java.database.Dao.Assistenza.DaoAssistenza;
import it.progettoWeb.java.database.Dao.Negozio.DaoNegozio;
import it.progettoWeb.java.database.Dao.Oggetto.DaoOggetto;
import it.progettoWeb.java.database.Dao.Ordine.DaoOrdine;
import it.progettoWeb.java.database.Dao.Utente.DaoUtente;
import it.progettoWeb.java.database.Model.Assistenza.ModelloAssistenza;
import it.progettoWeb.java.database.Model.Assistenza.ModelloListeAssistenza;
import it.progettoWeb.java.database.Model.Utente.ModelloUtente;
import it.progettoWeb.java.utility.javaMail.SendEmail;
import it.progettoWeb.java.utility.pair.pair;
import java.io.IOException;
import java.sql.Timestamp;
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
    private static String YOUR_ROA = "/jspFile/Finale/Assistenza/yourROA.jsp";
    private static String DETAILS_ROA = "/jspFile/Finale/Assistenza/detailsROA.jsp";
    private DaoAssistenza daoAssistenza;
    private DaoUtente daoUtente;
    private DaoOggetto daoOggetto;
    private DaoOrdine daoOrdine;
    private DaoNegozio daoNegozio;
    
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
        daoNegozio = new DaoNegozio();
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
        request.setCharacterEncoding("UTF-8");
        String forward;
        forward = ERROR_PAGE; request.setAttribute("errore", "404 Pagina non trovata");
        
        try
        {
            String action = request.getParameter("action");
            ModelloUtente utenteSessione = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
            
            //Se l'utente loggato esiste
            if(utenteSessione.getId() > 0)
            {
                if((utenteSessione.getUtenteType() == 2) && (action.equalsIgnoreCase("listAssistances")))  //Solo se l'utente è amministratore
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
                else if((utenteSessione.getUtenteType() == 2) && (action.equalsIgnoreCase("resolve")))  //Solo se l'utente è amministratore
                {
                    forward = RESOLVE_ASSITANCE;
                    int index = Integer.parseInt(request.getParameter("id"));

                    ModelloAssistenza assistenza = daoAssistenza.selectSpecifiedInfoSupport(index);
                    ModelloUtente utenteRichiedente = daoUtente.selectUserByID(assistenza.getIdUtente());
                    request.setAttribute("assistenza", assistenza);
                    request.setAttribute("utenteRichiedente", utenteRichiedente);
                    request.setAttribute("venditoreContestato", daoUtente.selectUserByID(assistenza.getIdVenditore()));
                    request.setAttribute("oggettoContestato", daoOggetto.getObjectById(assistenza.getIdOggetto()));
                    request.setAttribute("ordineContestato", (daoOrdine.selectOrdersByIdOrder(assistenza.getIdOrdine()).get(0)).getIdOrdine());
                }
                else if(action.equalsIgnoreCase("showAssistances"))
                {
                    forward = YOUR_ROA;

                    ModelloListeAssistenza listaAssistenze = new ModelloListeAssistenza(
                            daoAssistenza.selectUserAssistances(utenteSessione.getId()));

                    List<pair<ModelloAssistenza, String>> assistenzeAperte = new ArrayList<>();
                    List<pair<ModelloAssistenza, String>> assistenzeChiuse = new ArrayList<>();

                    for(ModelloAssistenza assistenza : listaAssistenze.getList())
                    {
                        String nomeOggettoContestato = (daoOggetto.getObjectById(assistenza.getIdOggetto())).getNome();

                        if(assistenza.getStato() == 0)
                            assistenzeAperte.add(new pair<>(assistenza, nomeOggettoContestato));
                        else
                            assistenzeChiuse.add(new pair<>(assistenza, nomeOggettoContestato));
                    }

                    request.setAttribute("assistenzeAperte", assistenzeAperte);
                    request.setAttribute("assistenzeChiuse", assistenzeChiuse);


                    //Se l'utente è un venditore, mostro le richieste di assistenza in cui è stato citato
                    if(utenteSessione.getUtenteType() == 1)
                    {
                        ModelloListeAssistenza listaAssistenzeVenditore = new ModelloListeAssistenza(
                                daoAssistenza.selectServiceRequestBySellerID(utenteSessione.getId()));

                        List<pair<ModelloAssistenza, String>> assistenzeAperteVenditore = new ArrayList<>();
                        List<pair<ModelloAssistenza, String>> assistenzeChiuseVenditore = new ArrayList<>();

                        for(ModelloAssistenza assistenza : listaAssistenzeVenditore.getList())
                        {
                            String nomeOggettoContestato = (daoOggetto.getObjectById(assistenza.getIdOggetto())).getNome();

                            if(assistenza.getStato() == 0)
                                assistenzeAperteVenditore.add(new pair<>(assistenza, nomeOggettoContestato));
                            else
                                assistenzeChiuseVenditore.add(new pair<>(assistenza, nomeOggettoContestato));
                        }

                        request.setAttribute("assistenzeAperteVenditore", assistenzeAperteVenditore);
                        request.setAttribute("assistenzeChiuseVenditore", assistenzeChiuseVenditore);
                        request.setAttribute("isVenditore", 1);
                    }
                    else
                        request.setAttribute("isVenditore", 0);
                }
                else if(action.equalsIgnoreCase("details"))
                {
                    forward = DETAILS_ROA;
                    int index = Integer.parseInt(request.getParameter("id"));

                    ModelloAssistenza assistenza = daoAssistenza.selectSpecifiedInfoSupport(index);
                    request.setAttribute("assistenza", assistenza);
                    
                    if(utenteSessione.getUtenteType() == 1)
                    {
                        request.setAttribute("utenteContestatore", daoUtente.selectUserByID(assistenza.getIdUtente()));
                        request.setAttribute("isVenditore", 1);
                    }
                    else
                    {
                        request.setAttribute("venditoreContestato", daoUtente.selectUserByID(assistenza.getIdVenditore()));
                        request.setAttribute("isVenditore", 0);
                    }

                    request.setAttribute("oggettoContestato", daoOggetto.getObjectById(assistenza.getIdOggetto()));
                    request.setAttribute("ordineContestato", (daoOrdine.selectOrdersByIdOrder(assistenza.getIdOrdine()).get(0)).getIdOrdine());
                }
            }
        }
        catch (Exception e) 
        {
            System.out.println(getServletName() + " error message = " + e.toString());
            forward = ERROR_PAGE;
            request.setAttribute("ECCEZIONE", e.toString());
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
        request.setCharacterEncoding("UTF-8");
        String forward;
        forward = ERROR_PAGE; request.setAttribute("errore", "404 Pagina non trovata");
        try
        {
            String action = request.getParameter("action");
            
            if(action.equalsIgnoreCase("save"))
            {
                int idA = Integer.parseInt(request.getParameter("idA"));
                String solution = request.getParameter("solution");
                
                if(solution.length() > 2000)
                    solution = solution.substring(0, 2000);
                solution = solution.replace('\'', ' ');
                
                daoAssistenza.updateAssistanceSolution(idA, solution);
                
                forward = "AssistenzaController?action=listAssistances";
            }
            else if(action.equalsIgnoreCase("saveAndClose"))
            {
                int idA = Integer.parseInt(request.getParameter("idA"));
                String solution = request.getParameter("solution");
                
                if(solution.length() > 2000)
                    solution = solution.substring(0, 2000);
                solution = solution.replace('\'', ' ');
                
                ModelloAssistenza ass = daoAssistenza.selectSpecifiedInfoSupport(idA);
                ass.setDataChiusura(new Timestamp(System.currentTimeMillis()));
                ass.setSoluzione(solution);
                ass.setStato(1);
                
                daoAssistenza.updateAssistance(ass);
                
                SendEmail.chiusuraRichiestaDiAssistenza(
                        (daoUtente.getUserById(ass.getIdVenditore())).getMail(),
                        (daoUtente.getUserById(ass.getIdUtente())).getMail(), 
                        ass.getIdOrdine(), 
                        ass.getId());
                
                forward = "AssistenzaController?action=listAssistances";
            }
            else if(action.equalsIgnoreCase("createAssistance"))
            {
                ModelloUtente utenteRichiedente = (ModelloUtente)request.getSession().getAttribute("utenteSessione");
                int idUtenteRichiedente = utenteRichiedente.getId();
                int idOrdine = Integer.parseInt(request.getParameter("idOrdine"));
                int idNegozio = Integer.parseInt(request.getParameter("idNegozio"));
                int idVenditore = (daoNegozio.getStoreById(idNegozio)).getIdVenditore();
                String idOggetto = request.getParameter("idOggetto");
                String testo = request.getParameter("testoAssistenza");
                
                if(testo.length() > 2000)
                    testo = testo.substring(0, 2000);
                testo = testo.replace('\'', ' ');
                
                ModelloAssistenza assistance = new ModelloAssistenza();
                assistance.setIdUtente(idUtenteRichiedente);
                assistance.setIdOrdine(idOrdine);
                assistance.setIdVenditore(idVenditore);
                assistance.setIdOggetto(idOggetto);
                assistance.setRichiesta(testo);
                assistance.setIdAmministratore(getAdminWithMinimumPendingRequests(idUtenteRichiedente));
                
                daoAssistenza.insertAssistance(assistance);
                
                SendEmail.nuovaRichiestaDiAssistenza(
                        (daoUtente.getUserById(idVenditore)).getMail(), 
                        (daoUtente.getUserById(assistance.getIdAmministratore())).getMail(), 
                        (utenteRichiedente.getCognome() + " " + utenteRichiedente.getNome()), 
                        idOrdine, 
                        (daoAssistenza.selectAssistance(idUtenteRichiedente, idVenditore, assistance.getIdAmministratore(), idOrdine, idOggetto)).get(0).getId());
                
                forward = "AssistenzaController?action=showAssistances";
            }
        }
        catch (Exception e) 
        {
            System.out.println(getServletName() + " error message = " + e.toString());
            forward = ERROR_PAGE;
            request.setAttribute("ECCEZIONE", e.toString());
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

    
    private int getAdminWithMinimumPendingRequests(int idUtenteRichiedente)
    {
        int min = Integer.MAX_VALUE;
        List<ModelloUtente> listAdministrators = daoUtente.selectAllUsersByType(2);
        
        int idAmministratore = listAdministrators.get(0).getId();
        for(ModelloUtente adm : listAdministrators)
        {
            if(adm.getId() != idUtenteRichiedente)
            {
                int requests = daoAssistenza.numberRequestOfAssistanceInAStateOfSpecificAdministrator(0, adm.getId());
                if(min > requests)
                {
                    min = requests;
                    idAmministratore = adm.getId();

                    if(requests == 0)
                    break;
                }
            }
        }
        
        return idAmministratore;
    }
}
