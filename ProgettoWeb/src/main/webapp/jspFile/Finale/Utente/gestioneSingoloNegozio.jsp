<%-- 
    Document   : gestioneSingoloNegozio
    Created on : 30-dic-2017, 10.46.30
    Author     : mattia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/jspFile/Finale/CSS/form.css">
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title><c:out value="${utenteSessione.getNome()}" /> Gestione Negozio</title>
        <script type="text/javascript">
            function elimina() {
                document.modificaOggetto.action.value = "deleteObject";
                modificaOggetto.submit();
            }
            
            $(document).ready(function() {
                $('#tabellaOggetti').DataTable({
                    responsive: true,
                    colReorder: true
                });
                $('#tabellaOrdini').DataTable({
                    responsive: true,
                    colReorder: true
                });
                $('#tabellaSpedizioni').DataTable({
                    responsive: true,
                    colReorder: true
                });
            } );
        </script>
        <style>
            label{
                position: static;
            }
            
            table.dataTable > tbody > tr.child ul.dtr-details{
                width: 100%;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-2 col-xs-12">
                    <img id="imgNegozio" class="d-block w-100" src="${immagine.getSrc()}" alt="IMAGE NOT LOADED" style="width: auto; max-height: 500px; object-fit: cover;">
                </div>
                <div class="col-sm-10 col-xs-12">
                    <h1><c:out value="${negozio.getNomeNegozio()}" /></h1>
                    <p>Da qui puoi gestire tutto ciò che riguarda il tuo negozio, a partire dagli ordini ricevuti all'aggiunta di oggetti al catalogo</p>
                    <div class="text-justify">
                        <h4><a href="${negozio.getLinkSito()}">${negozio.getLinkSito()}</a></h4><br/>
                        <h5>${indirizzo.getVia()} ${indirizzo.getnCivico()}, ${indirizzo.getCitta()}, ${indirizzo.getProvincia()}, ${indirizzo.getRegione()}, ${indirizzo.getStato()}</h5><br/>
                    </div>
                </div>
            </div>
            <hr>
            <%@include file="../alert/alertOrdineModificato.jsp" %>
            <%@include file="../alert/alertSpedizioneModificata.jsp" %>
            <%@include file="../alert/alertSpedizioneAggiunta.jsp" %>
            <div class="row">
                <div class="col-12">
                    <h2>Lista degli oggetti attualmente nel catalogo</h2>
                    <%@include file="../alert/alertOggettoInserito.jsp" %>
                    <%@include file="../alert/alertOggettoModificato.jsp" %>
                </div>
                <hr>
                <div class="col-12">
                    <table id="tabellaOggetti" class="table table-striped table-bordered" width="100%" cellspacing="0">
                         <thead>
                            <tr>
                                <th>Nome</th>
                                <th>Prezzo</th>
                                <th>Disponibilit&aacute;</th>
                                <th>Sconto</th>
                                <th>Modifica</th>
                                <th>Elimina</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <th>Nome</th>
                                <th>Prezzo</th>
                                <th>Disponibilit&aacute;</th>
                                <th>Sconto</th>
                                <th>Modifica</th>
                                <th>Elimina</th>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach items="${listaOggeti.getList()}" var="oggetto" >
                                <tr>
                                    <td>
                                        <c:out value="${oggetto.getNome()}" /><br><c:out value="${oggetto.getId()}" />
                                    </td>
                                    <td>
                                        <c:out value="${oggetto.getPrezzo()}" />&euro;
                                    </td>
                                    <td>
                                        <c:out value="${oggetto.getDisponibilita()}" />
                                    </td>
                                    <td>
                                        <c:out value="${oggetto.getSconto()}" /> %
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/NegozioController?action=richiestaPaginaDiModificaOggetto&id=<c:out value="${oggetto.getId()}" />" method="GET" class="btn btn-outline-primary buttonSpace"><i class="Small material-icons">build</i></a>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-outline-danger buttonSpace" data-toggle="modal" data-target="#deleteItemModal<c:out value="${oggetto.getId()}" />">
                                            <i class="Small material-icons">close</i>
                                        </button>
                                        <div id="deleteItemModal<c:out value="${oggetto.getId()}" />" class="modal fade" role="dialog">
                                            <div class="modal-dialog">

                                                <!-- Modal content-->
                                                <div class="modal-content">
                                                  <div class="modal-header">
                                                    <h4 class="modal-title">Eliminazione oggetto</h4>
                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                  </div>
                                                    <div class="row">
                                                        <div class="col-1"></div>
                                                        <div class="col-8">
                                                            <p>Per eliminare l'oggetto inserisci il nome dell'oggetto che vuoi eliminare per confermare la tua volontà di eliminarlo</p>
                                                        </div>
                                                        <div class="col-2"></div>
                                                    </div>
                                                    <form action="${pageContext.request.contextPath}/NegozioController" method="GET">
                                                        <input type="hidden" name="action" value="deleteObject">
                                                        <input type="hidden" name="idNegozio" value="${negozio.getId()}">
                                                        <input type="hidden" name="modifyObject" value="<c:out value="${oggetto.getId()}" />">
                                                        <div class="modal-body">
                                                            <div>
                                                                <input class="col-10 modal-input" type="text" id="modifyDelete" name="modifyDelete" placeholder="Nome oggetto da eliminare" required>
                                                            </div>

                                                        </div>
                                                        <div class="modal-footer">
                                                            <button action="submit" type="submit" class="col-4 paddingNav btn btn-outline-danger btn-block my-2 my-sm-0" id="btnDelete">Elimina oggetto</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-3 col-sm-12 col-xs-12">
                    <a href="${pageContext.request.contextPath}/NegozioController?action=richiestaPaginaDiAggiuntaOggetto" method="GET" class="btn btn-outline-primary buttonSpace btn-block"><i class="Small material-icons">add</i> Aggiungi</a>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-12">
                    <h2>Ordini ricevuti</h2>
                    <p>Qui trovi la lista degli ordini che hai ricevuto fino ad ora in questo negozio, puoi modificare lo stato dell'ordine in modo tale che un utente possa essere aggionrato</p>
                </div>
                <hr>
                <div class="col-12">
                    <table id="tabellaOrdini" class="display cell-border hover order-column row-border stripe" width="100%" cellspacing="0">
                         <thead>
                            <tr>
                                <th>Id ordine</th>
                                <th>Oggetto ordinato</th>
                                <th>Quantit&aacute;</th>
                                <th>Prezzo di acquisto</th>
                                <th>Data di acquisto</th>
                                <th>Stato</th>
                                <th>Modifica ordine</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <th>Id ordine</th>
                                <th>Oggetto ordinato</th>
                                <th>Quantit&aacute;</th>
                                <th>Prezzo di acquisto</th>
                                <th>Data di acquisto</th>
                                <th>Stato</th>
                                <th>Modifica ordine</th>
                            </tr>
                        </tfoot>
                        <tbody>
                        <c:forEach items="${listaOrdini.getList()}" var="ordine" >
                            <tr>
                                <td>
                                    <c:out value="${ordine.getIdOrdine()}" />
                                </td>
                                <td>
                                    <c:out value="${ordine.getIdOggetto()}" />
                                </td>
                                <td>
                                    <c:out value="${ordine.getQuantita()}" />
                                </td>
                                <td>
                                    <c:out value="${ordine.getPrezzoDiAcquisto()}" />
                                </td>
                                <td>
                                    <c:out value="${ordine.getDataOrdine()}" />
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${ordine.getStato() == 1}">
                                            Ordine ricevuto e pagato
                                        </c:when>
                                        <c:when test="${ordine.getStato() == 2}">
                                            Ordine in lavorazione
                                        </c:when>
                                        <c:when test="${ordine.getStato() == 3}">
                                            Ordine spedito
                                        </c:when>
                                        <c:when test="${ordine.getStato() == 4}">
                                            Ordine consegnato
                                        </c:when>
                                        <c:otherwise>
                                            Ordine nel carrello
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/NegozioController?action=richiestaPaginaDiModificaOrdine&idOrdine=<c:out value="${ordine.getIdOrdine()}" />&idOggetto=<c:out value="${ordine.getIdOggetto()}" />" method="GET" class="btn btn-outline-primary buttonSpace"><i class="Small material-icons">build</i></a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-12">
                    <h2>Lista dei metodi di spedizione</h2>
                    <p>Questi sono i metodi di spedizione attualmente disponibili per questo negozio, da qui puoi aggiungerne o rimuoverne</p>
                    <p>Ricordati di inserire almeno un metodo di spedizione, altrimenti per gli utenti non sar&aacute; possibile portare a termine nessun acquisto</p>
                </div>
                <hr>
                <div class="col-12">
                    <table id="tabellaSpedizioni" class="display cell-border hover order-column row-border stripe" width="100%" cellspacing="0">
                         <thead>
                            <tr>
                                <th>Nome e corriere</th>
                                <th>Prezzo</th>
                                <th>Tempo richiesto</th>
                                <th>Numero oggeti per pacco</th>
                                <th>Modifica</th>
                                <th>Elimina</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <th>Nome e corriere</th>
                                <th>Prezzo</th>
                                <th>Tempo richiesto</th>
                                <th>Numero oggeti per pacco</th>
                                <th>Modifica</th>
                                <th>Elimina</th>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach items="${listaTipiSpedizione.getList()}" var="spedizione" >
                                <tr>
                                    <td>
                                        <c:out value="${spedizione.getNome()}" /><br>
                                        <c:out value="${spedizione.getCorriere()}" />
                                    </td>
                                    <td>
                                        <c:out value="${spedizione.getPrezzo()}" />&euro;
                                    </td>
                                    <td>
                                        <c:out value="${spedizione.getTempoRichiesto()}" /> Giorni
                                    </td>
                                    <td>
                                        <c:out value="${spedizione.getNumeroMassimo()}" />
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/NegozioController?action=richiestaPaginaDiModificaTipoSpedizione&id=<c:out value="${spedizione.getIdS()}" />" method="GET" class="btn btn-outline-primary buttonSpace "><i class="Small material-icons">build</i></a>
                                    </td>
                                    <td>
                                        <button type="button" class="btn btn-outline-danger buttonSpace" data-toggle="modal" data-target="#deleteSpedizioneModal<c:out value="${spedizione.getIdS()}" />">
                                            <i class="Small material-icons">close</i>
                                        </button>
                                        <div id="deleteSpedizioneModal<c:out value="${spedizione.getIdS()}" />" class="modal fade" role="dialog">
                                            <div class="modal-dialog">

                                                <!-- Modal content-->
                                                <div class="modal-content">
                                                  <div class="modal-header">
                                                    <h4 class="modal-title">Eliminazione spedizione</h4>
                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                  </div>
                                                    <div class="row">
                                                        <div class="col-1"></div>
                                                        <div class="col-8">
                                                            <p>Per eliminare questa spedizione inserisci il suo nome come conferma della tua volontà, se vi sono ancora ordini in corso che richiedono questo metodo di spedizione esso non verrà eliminato</p>
                                                        </div>
                                                    </div>
                                                    <form action="${pageContext.request.contextPath}/NegozioController" method="GET">
                                                        <input type="hidden" name="action" value="deleteSpedizione">
                                                        <input type="hidden" name="idNegozio" value="${negozio.getId()}">
                                                        <input type="hidden" name="modifySpedizione" value="<c:out value="${spedizione.getIdS()}" />">
                                                        <div class="modal-body">
                                                            <div>
                                                                <input class="col-10 modal-input" type="text" id="modifyDelete" name="modifyDelete" placeholder="Nome spedizione da eliminare" required>
                                                            </div>

                                                        </div>
                                                        <div class="modal-footer">
                                                            <button action="submit" type="submit" class="col-4 paddingNav btn btn-outline-danger btn-block my-2 my-sm-0" id="btnDelete">Elimina spedizione</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-3 col-sm-12 col-xs-12">
                    <a href="jspFile/Finale/Utente/aggiungiTipoSpedizione.jsp" method="GET" class="btn btn-outline-primary buttonSpace btn-block"><i class="Small material-icons">add</i> Aggiungi</a>
                </div>
            </div>
        </div>
            <%@include file="../Footer/footer.jsp" %>
    </body>
</html>

