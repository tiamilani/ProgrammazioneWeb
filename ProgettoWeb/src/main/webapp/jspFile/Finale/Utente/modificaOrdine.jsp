<%-- 
    Document   : modificaOrdine
    Created on : 11-gen-2018, 10.45.32
    Author     : mattia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/jspFile/Finale/CSS/form.css">
        <title><c:out value="${utenteSessione.getNome()}" /> Modifica ordine</title>
    </head>
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <h2>Modifica ordine</h2>
                    <p>Da qui puoi modificare un ordine che è arrivato al tuo negozio, vedrai i dati relativi all'ordine e lo stato attuale che potrai modificare per comunicare all'utente che il suo ordine sta procedendo</p>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-lg-4 col-md-5 col-sm-12 col-xs-12">
                    <p>
                        Codice identificativo dell'ordine:<br>
                        <c:out value="${ordine.getIdOrdine()}" />
                    </p>
                </div>
                <div class="col-lg-5 col-md-6 col-sm-12 col-xs-12">
                    <p>
                        Id e nome dell'oggetto dell'ordine:<br>
                        <c:out value="${oggetto.getId()}" /> - <c:out value="${oggetto.getNome()}" />
                    </p>
                </div>
                <div class="col-lg-3 col-sm-12 col-xs-12">
                    <p>
                        Stato attuale dell'ordine:<br>
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
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-2 col-md-4 col-sm-12 col-xs-12">
                    <p>
                        Prezzo di acquisto:<br>
                        <c:out value="${ordine.getPrezzoDiAcquisto()}" />&euro;
                    </p>
                </div>
                <div class="col-xl-1 col-lg-2 col-md-4 col-sm-12 col-xs-12">
                    <p>
                        Quantit&aacute;:<br>
                        <c:out value="${ordine.getQuantita()}" />
                    </p>
                </div>
                <div class="col-lg-2 col-md-4 col-sm-12 col-xs-12">
                    <p>
                        Data di acquisto:<br>
                        <c:out value="${ordine.getDataOrdine()}" />
                    </p>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
                    <p>
                        Modalit&aacute; di spedizione:<br>
                        <c:choose>
                            <c:when test="${ordine.getIdS() == 0}">
                                Ritiro in negozio
                            </c:when>
                            <c:otherwise>
                                <c:out value="${tipoSpedizione.getNome()}" />, Corriere <c:out value="${tipoSpedizione.getCorriere()}" />
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>
                <div class="col-xl-3 col-md-6 col-sm-12 col-xs-12">
                    <p>
                        Data di arrivo presunta:<br>
                        <c:choose>
                            <c:when test="${ordine.getIdS() == 0}">
                                Ritiro in negozio
                            </c:when>
                            <c:otherwise>
                                <c:out value="${ordine.getDataArrivoPresunta()}" />
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <p>
                        Indirizzo di spedizione da utilizzare: 
                        <c:choose>
                            <c:when test="${ordine.getIdS() == 0}">
                                Attendi l'utente in negozio
                            </c:when>
                            <c:otherwise>
                                <c:out value="${indirizzo.getVia()}" /> <c:out value="${indirizzo.getnCivico()}" />, <c:out value="${indirizzo.getCitta()}" />, <c:out value="${indirizzo.getProvincia()}" />, <c:out value="${indirizzo.getRegione()}" />, <c:out value="${indirizzo.getStato()}" />
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>
            </div>
            <div class="row">
                <c:choose>
                    <c:when test="${ordine.getIdS() == null}">
                        <p>
                            Quest'ordine non va spedito, dovrai aspettare che l'utente venga a ritirare il prodotto. <br>
                        </p>
                        <div class="col-xl-4 col-sm-12 col-xs-12">
                            <a href="${pageContext.request.contextPath}/NegozioController?action=articoloRitirato&idOrdine=<c:out value="${ordine.getIdOrdine()}" />&idOggetto=<c:out value="${ordine.getIdOggetto()}" />" method="GET" class="btn btn-outline-primary buttonSpace btn-block">Ordine ritirato dall'utente</a>
                        </div>
                    </c:when>
                    <c:when test="${ordine.getStato() == 0}">
                    </c:when>
                    <c:when test="${ordine.getStato() == 5}">
                    </c:when>
                    <c:when test="${ordine.getStato() == 1}">
                        <div class="col-xl-4 col-sm-12 col-xs-12">
                            <a href="${pageContext.request.contextPath}/NegozioController?action=portaOrdineInLavorazione&idOrdine=<c:out value="${ordine.getIdOrdine()}" />&idOggetto=<c:out value="${ordine.getIdOggetto()}" />" method="GET" class="btn btn-outline-primary buttonSpace btn-block"><i class="Small material-icons">build</i> Porta L'ordine in lavorazione</a>
                        </div>
                    </c:when>
                    <c:when test="${ordine.getStato() == 2}">
                        <form class="col-12" id="formOrdineInSpedizione" name="formOrdineInSpedizione" method="GET" action="${pageContext.request.contextPath}/NegozioController">
                            <input type="hidden" name="action" value="portaOrdineInSpedizione">
                            <input type="hidden" id="idOrdine" name="idOrdine" value="${ordine.getIdOrdine()}">
                            <input type="hidden" id="idOggetto" name="idOggetto" value="${oggetto.getId()}">
                            <div class="row">
                                <div class="col-lg-8 col-sm-12 col-xs-12">
                                    <p>
                                        Inserisci il codice di tracking se noto, altrimenti lascia vuoto, non sarà più modificabile
                                    </p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-8 col-sm-12 col-xs-12">
                                    <input type="text" class="form-control" id="codiceTracking" name="codiceTracking" maxlength="40" placeholder="Track this">
                                </div>
                                <div class="col-lg-4 col-sm-12 col-xs-12">
                                    <button action="submit" type="submit" class="btn btn-outline-primary btn-block" id="btnModify">L'ordine è stato spedito</button>
                                </div>
                            </div>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <div class="col-4">
                            <p>L'ordine risulta gi&aacute; spedito, perciò non è più modificabile</p>
                        </div>
                        <form class="col-8">
                            <div class="row">
                                <div class="col-8">
                                    <p>
                                        <c:choose>
                                            <c:when test="${ordine.getCodiceTracking() == null}">
                                                <input type="text" class="form-control" id="codiceTracking" name="codiceTracking" value="Codice tracking non specificato" readonly>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="text" class="form-control" id="codiceTracking" name="codiceTracking" value="${ordine.getCodiceTracking()}" readonly>
                                            </c:otherwise>
                                        </c:choose>
                                    </p>
                                </div>
                            </div>
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
            <%@include file="../Footer/footer.jsp" %>
    </body>
</html>
