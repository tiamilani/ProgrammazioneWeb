<%-- 
    Document   : infoOrdiniUtente
    Created on : 17-ott-2017, 14.26.15
    Author     : mattia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/jspFile/Finale/CSS/textareaAssistance.css">
        <title>Ordini <c:out value="${utenteSessione.getNome()}" /></title>
    </head>
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
        </div>
        <div class="container-fluid">
            <h1>Ordini ricevuti</h1>
            <form id="formOptionOrder" name="formOptionOrder" action="${pageContext.request.contextPath}/UserController" method="GET">
                <input type="hidden" id="action" name="action" value="orderList">
                <div class="row">
                    <div class="col-xl-3 col-lg-5 col-sm-12 col-xs-12">
                        <p>Ordine: </p>
                        <c:choose>
                            <c:when test="${order == 'data'}">
                                <select id="order" name="order" class="form-control" id="exampleFormControlSelect1" onchange="this.form.submit()">
                                    <option value="data" selected="selected">Per data</option>
                                    <option value="tipo">Per tipo</option>
                                    <option value="lavorazione">In lavorazione</option>
                                    <option value="spediti">Spediti</option>
                                </select>
                            </c:when>
                            <c:when test="${order == 'tipo'}">
                                <select id="order" name="order" class="form-control" id="exampleFormControlSelect1" onchange="this.form.submit()">
                                    <option value="data">Per data</option>
                                    <option value="tipo" selected="selected">Per tipo</option>
                                    <option value="lavorazione">In lavorazione</option>
                                    <option value="spediti">Spediti</option>
                                </select>
                            </c:when>
                            <c:when test="${order == 'lavorazione'}">
                                <select id="order" name="order" class="form-control" id="exampleFormControlSelect1" onchange="this.form.submit()">
                                    <option value="data">Per data</option>
                                    <option value="tipo">Per tipo</option>
                                    <option value="lavorazione"  selected="selected">In lavorazione</option>
                                    <option value="spediti">Spediti</option>
                                </select>
                            </c:when>
                            <c:when test="${order == 'spediti'}">
                                <select id="order" name="order" class="form-control" id="exampleFormControlSelect1" onchange="this.form.submit()">
                                    <option value="data">Per data</option>
                                    <option value="tipo">Per tipo</option>
                                    <option value="lavorazione">In lavorazione</option>
                                    <option value="spediti" selected="selected">Spediti</option>
                                </select>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </form>
            <hr>
            <c:choose>
                <c:when test="${order == 'data'}">
                    <h2>Ordini per data</h2>
                    <c:choose>
                        <c:when test="${listaOrdini.getList().size() == 0}">
                            <div class="row">
                                <div class="col-12">
                                    <p>
                                        Mi spiace ma non ho trovato nessun ordine
                                    </p>
                                </div>
                                <div class="col-12">
                                    <h1>:(</h1>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <p>Ho trovato <c:out value="${listaOrdini.getList().size()}" /> ordini</p>
                            <c:set var="i" scope="page" value="${0}" />
                            <c:forEach items="${listaOrdini.getList()}" var="ordine" >
                                    <c:if test="${ordine.getStato() != 0 && ordine.getStato() != 5}">
                                    <div class="row">
                                        <div class="col-lg-1 col-md-1 col-sm-2 col-xs-3">
                                            <img src="${listaImmagini.get(i).getSrc()}" alt="IMAGE NOT LOADED" style="width: 50px; height: 50px; object-fit: cover;">
                                        </div>
                                        <div class="col-lg-3 col-md-6 col-sm-10 col-xs-9">
                                            <c:url value="/objectSelectedController" var="objUrl" >
                                                <c:param name="idOggetto" value="${listaOggetti.get(i).getId()}" />
                                            </c:url>
                                            <a href="${objUrl}" class="linkOggetto"><h2><c:out value="${listaOggetti.get(i).getNome()}" /></h2></a>
                                            <p>quantit&aacute;: <c:out value="${ordine.getQuantita()}"/></p>
                                        </div>
                                        <div class="col-lg-2 col-md-5 col-sm-6 col-xs-12">
                                            <p>Comprato da: </p>
                                            <c:url value="/UserController" var="storeUrl">
                                                <c:param name="action" value="DescrizioneNegozio" />
                                                <c:param name="idNegozio" value="${listaNegozi.get(i).getId()}" />
                                            </c:url>
                                            <p><a href="${storeUrl}"><c:out value="${listaNegozi.get(i).getNomeNegozio()}" /></a></p>
                                            <c:url value="/UserController" var="sellerUrl">
                                                <c:param name="action" value="DescrizioneVenditore" />
                                                <c:param name="idUtente" value="${listaVenditori.get(i).getId()}" />
                                            </c:url>
                                            <p><a href="${sellerUrl}"><c:out value="${listaVenditori.get(i).getCognome()}" /> <c:out value="${listaVenditori.get(i).getNome()}" /></a></p>
                                        </div>
                                        <div class="col-lg-2 col-md-4 col-sm-6 col-xs-12">
                                            <p>Comprato il:</p>
                                            <p><c:out value="${ordine.getDataOrdine()}" /></p>
                                        </div>
                                        <div class="col-lg-2 col-md-4 col-sm-6 col-xs-12">
                                            <p>Prezzo:</p>
                                            <h2><c:out value="${ordine.getPrezzoDiAcquistoRounded()}" /> &euro;</h2>
                                        </div>
                                        <div class="col-lg-2 col-md-4 col-sm-6 col-xs-12">
                                            <p>Stato attuale:</p>
                                            <c:choose>
                                                <c:when test="${ordine.getStato() == 1}">
                                                    <p>Pagamento ricevuto</p>
                                                </c:when>
                                                <c:when test="${ordine.getStato() == 2}">
                                                    <p>Ordine in lavorazione</p>
                                                </c:when>
                                                <c:when test="${ordine.getStato() == 3}">
                                                    <p><b>Ordine spedito</b></p>
                                                    <p>Tracking: <c:out value="${ordine.getCodiceTracking()}" /></p>
                                                </c:when>
                                                <c:when test="${ordine.getStato() == 4}">
                                                    <p>Ordine ricevuto</p>
                                                </c:when>
                                            </c:choose>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-5 col-sm-12 d-none d-sm-block mx-auto">
                                            <form id="richiestaAssistenza" name="richiestaAssistenza" action="${pageContext.request.contextPath}/AssistenzaController?action=createAssistance" method="POST">
                                                <input type="hidden" id="idOrdine" name="idOrdine" value="${ordine.getIdOrdine()}">
                                                <input type="hidden" id="idOggetto" name="idOggetto" value="${ordine.getIdOggetto()}">
                                                <input type="hidden" id="idNegozio" name="idNegozio" value="${ordine.getIdNegozio()}">
                                                <button type="button" class="btn btn-outline-primary btn-block" data-toggle="modal" dclass="btn btn-outline-primary btn-block" data-target="#callAssistance">
                                                    Richiedi assistenza per questo oggetto
                                                </button>
                                            </form>
                                        </div>
                                        <div class="col-xs-12 d-block d-sm-none mx-auto">
                                            <form id="richiestaAssistenza" name="richiestaAssistenza" action="${pageContext.request.contextPath}/AssistenzaController?action=createAssistance" method="POST">
                                                <input type="hidden" id="idOrdine" name="idOrdine" value="${ordine.getIdOrdine()}">
                                                <input type="hidden" id="idOggetto" name="idOggetto" value="${ordine.getIdOggetto()}">
                                                <input type="hidden" id="idNegozio" name="idNegozio" value="${ordine.getIdNegozio()}">
                                                <button type="button" class="btn btn-outline-primary btn-block" data-toggle="modal" dclass="btn btn-outline-primary btn-block" data-target="#callAssistance">
                                                    Richiedi assistenza
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                    <hr>
                                </c:if>
                                <c:set var="i" scope="page" value="${i+1}" />
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </c:when>
                    <c:when test="${order == 'tipo'}">
                        <h2>Ordini per tipo</h2>
                        <c:choose>
                            <c:when test="${listaOrdini.getList().size() == 0}">
                                <div class="row">
                                    <div class="col-12">
                                        <p>
                                            Mi spiace ma non ho trovato nessun ordine
                                        </p>
                                    </div>
                                    <div class="col-12">
                                        <h1>:(</h1>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:set var="i" scope="page" value="${0}" />
                                <c:forEach items="${listaOrdini.getList()}" var="ordine" >
                                    <c:if test="${ordine.getStato() != 0 && ordine.getStato() != 5}">
                                        <c:set var="orderType" scope="page" value="${0}" />
                                        <c:choose>
                                            <c:when test="${orderType != ordine.getStato() && ordine.getStato() == 1}">
                                                <hr>
                                                <h3>Pagamento ricevuto</h3>
                                                <c:set var="orderType" scope="page" value="${ordine.getStato()}" />
                                            </c:when>
                                            <c:when test="${orderType != ordine.getStato() && ordine.getStato() == 2}">
                                                <hr>
                                                <h3>In lavorazione</h3>
                                                <c:set var="orderType" scope="page" value="${ordine.getStato()}" />
                                            </c:when>
                                            <c:when test="${orderType != ordine.getStato() && ordine.getStato() == 3}">
                                                <hr>
                                                <h3>Spedito</h3>
                                                <c:set var="orderType" scope="page" value="${ordine.getStato()}" />
                                            </c:when>
                                            <c:when test="${orderType != ordine.getStato() && ordine.getStato() == 4}">
                                                <hr>
                                                <h3>Consegnato</h3>
                                                <c:set var="orderType" scope="page" value="${ordine.getStato()}" />
                                            </c:when>
                                        </c:choose>
                                        <div class="row">
                                            <div class="col-lg-1 col-md-1 col-sm-2 col-xs-3">
                                                <img src="${listaImmagini.get(i).getSrc()}" alt="IMAGE NOT LOADED" style="width: 50px; height: 50px; object-fit: cover;">
                                            </div>
                                            <div class="col-lg-3 col-md-6 col-sm-10 col-xs-9">
                                                <c:url value="/objectSelectedController" var="objUrl" >
                                                    <c:param name="idOggetto" value="${listaOggetti.get(i).getId()}" />
                                                </c:url>
                                                <a href="${objUrl}" class="linkOggetto"><h2><c:out value="${listaOggetti.get(i).getNome()}" /></h2></a>
                                                <p>quantit&aacute;: <c:out value="${ordine.getQuantita()}getPrezzoDiAcquistoRounded"/></p>
                                            </div>
                                            <div class="col-lg-2 col-md-5 col-sm-6 col-xs-12">
                                                <p>Comprato da: </p>
                                                <c:url value="/UserController" var="storeUrl">
                                                    <c:param name="action" value="DescrizioneNegozio" />
                                                    <c:param name="idNegozio" value="${listaNegozi.get(i).getId()}" />
                                                </c:url>
                                                <p><a href="${storeUrl}"><c:out value="${listaNegozi.get(i).getNomeNegozio()}" /></a></p>
                                                <c:url value="/UserController" var="sellerUrl">
                                                    <c:param name="action" value="DescrizioneVenditore" />
                                                    <c:param name="idUtente" value="${listaVenditori.get(i).getId()}" />
                                                </c:url>
                                                <p><a href="${sellerUrl}"><c:out value="${listaVenditori.get(i).getCognome()}" /> <c:out value="${listaVenditori.get(i).getNome()}" /></a></p>
                                            </div>
                                            <div class="col-lg-2 col-md-4 col-sm-6 col-xs-12">
                                                <p>Comprato il:</p>
                                                <p><c:out value="${ordine.getDataOrdine()}" /></p>
                                            </div>
                                            <div class="col-lg-2 col-md-4 col-sm-6 col-xs-12">
                                                <p>Prezzo:</p>
                                                <h2><c:out value="${ordine.getPrezzoDiAcquistoRounded()}" /> &euro;</h2>
                                            </div>
                                            <div class="col-lg-2 col-md-4 col-sm-6 col-xs-12">
                                                <p>Stato attuale:</p>
                                                <c:choose>
                                                    <c:when test="${ordine.getStato() == 1}">
                                                        <p>Ordine ricevuto</p>
                                                    </c:when>
                                                    <c:when test="${ordine.getStato() == 2}">
                                                        <p>Ordine in lavorazione</p>
                                                    </c:when>
                                                    <c:when test="${ordine.getStato() == 3}">
                                                        <p><b>Ordine spedito</b></p>
                                                        <p>Tracking: <c:out value="${ordine.getCodiceTracking()}" /></p>
                                                    </c:when>
                                                    <c:when test="${ordine.getStato() == 4}">
                                                        <p>Ordine ricevuto</p>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                        </div>
                                        <div class="row">
                                        <div class="col-lg-5 col-sm-12 d-none d-sm-block mx-auto">
                                            <form id="richiestaAssistenza" name="richiestaAssistenza" action="${pageContext.request.contextPath}/AssistenzaController?action=createAssistance" method="POST">
                                                <input type="hidden" id="idOrdine" name="idOrdine" value="${ordine.getIdOrdine()}">
                                                <input type="hidden" id="idOggetto" name="idOggetto" value="${ordine.getIdOggetto()}">
                                                <input type="hidden" id="idNegozio" name="idNegozio" value="${ordine.getIdNegozio()}">
                                                <button type="button" class="btn btn-outline-primary btn-block" data-toggle="modal" dclass="btn btn-outline-primary btn-block" data-target="#callAssistance">
                                                    Richiedi assistenza per questo oggetto
                                                </button>
                                            </form>
                                        </div>
                                        <div class="col-xs-12 d-block d-sm-none mx-auto">
                                            <form id="richiestaAssistenza" name="richiestaAssistenza" action="${pageContext.request.contextPath}/AssistenzaController?action=createAssistance" method="POST">
                                                <input type="hidden" id="idOrdine" name="idOrdine" value="${ordine.getIdOrdine()}">
                                                <input type="hidden" id="idOggetto" name="idOggetto" value="${ordine.getIdOggetto()}">
                                                <input type="hidden" id="idNegozio" name="idNegozio" value="${ordine.getIdNegozio()}">
                                                <button type="button" class="btn btn-outline-primary btn-block" data-toggle="modal" dclass="btn btn-outline-primary btn-block" data-target="#callAssistance">
                                                    Richiedi assistenza
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                    <hr>
                                    </c:if>
                                    <c:set var="i" scope="page" value="${i+1}" />
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:when test="${order == 'lavorazione'}">
                        <h2>Ordini attualmente in lavorazione</h2>
                        <c:choose>
                            <c:when test="${listaOrdini.getList().size() == 0}">
                                <div class="row">
                                    <div class="col-12">
                                        <p>
                                            Mi spiace ma non ho trovato nessun ordine
                                        </p>
                                    </div>
                                    <div class="col-12">
                                        <h1>:(</h1>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:set var="i" scope="page" value="${0}" />
                                <c:forEach items="${listaOrdini.getList()}" var="ordine" >
                                    <c:if test="${ordine.getStato() != 0 && ordine.getStato() != 5}">
                                        <div class="row">
                                            <div class="col-lg-1 col-md-1 col-sm-2 col-xs-3">
                                                <img src="${listaImmagini.get(i).getSrc()}" alt="IMAGE NOT LOADED" style="width: 50px; height: 50px; object-fit: cover;">
                                            </div>
                                            <div class="col-lg-3 col-md-6 col-sm-10 col-xs-9">
                                                <c:url value="/objectSelectedController" var="objUrl" >
                                                    <c:param name="idOggetto" value="${listaOggetti.get(i).getId()}" />
                                                </c:url>
                                                <a href="${objUrl}" class="linkOggetto"><h2><c:out value="${listaOggetti.get(i).getNome()}" /></h2></a>
                                                <p>quantit&aacute;: <c:out value="${ordine.getQuantita()}"/></p>
                                            </div>
                                            <div class="col-lg-2 col-md-5 col-sm-6 col-xs-12">
                                                <p>Comprato da: </p>
                                                <c:url value="/UserController" var="storeUrl">
                                                    <c:param name="action" value="DescrizioneNegozio" />
                                                    <c:param name="idNegozio" value="${listaNegozi.get(i).getId()}" />
                                                </c:url>
                                                <p><a href="${storeUrl}"><c:out value="${listaNegozi.get(i).getNomeNegozio()}" /></a></p>
                                                <c:url value="/UserController" var="sellerUrl">
                                                    <c:param name="action" value="DescrizioneVenditore" />
                                                    <c:param name="idUtente" value="${listaVenditori.get(i).getId()}" />
                                                </c:url>
                                                <p><a href="${sellerUrl}"><c:out value="${listaVenditori.get(i).getCognome()}" /> <c:out value="${listaVenditori.get(i).getNome()}" /></a></p>
                                            </div>
                                            <div class="col-lg-2 col-md-4 col-sm-6 col-xs-12">
                                                <p>Comprato il:</p>
                                                <p><c:out value="${ordine.getDataOrdine()}" /></p>
                                            </div>
                                            <div class="col-lg-2 col-md-4 col-sm-6 col-xs-12">
                                                <h2><c:out value="${ordine.getPrezzoDiAcquistoRounded()}" /> &euro;</h2>
                                            </div>
                                            <div class="col-lg-2 col-md-4 col-sm-6 col-xs-12">
                                                <p>Stato attuale:</p>
                                                <c:choose>
                                                    <c:when test="${ordine.getStato() == 1}">
                                                        <p>Pagamento ricevuto</p>
                                                    </c:when>
                                                    <c:when test="${ordine.getStato() == 2}">
                                                        <p>Ordine in lavorazione</p>
                                                    </c:when>
                                                    <c:when test="${ordine.getStato() == 3}">
                                                        <p><b>Ordine spedito</b></p>
                                                        <p>Tracking: <c:out value="${ordine.getCodiceTracking()}" /></p>
                                                    </c:when>
                                                    <c:when test="${ordine.getStato() == 4}">
                                                        <p>Ordine ricevuto</p>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                        </div>
                                        <div class="row">
                                        <div class="col-lg-5 col-sm-12 d-none d-sm-block mx-auto">
                                            <form id="richiestaAssistenza" name="richiestaAssistenza" action="${pageContext.request.contextPath}/AssistenzaController?action=createAssistance" method="POST">
                                                <input type="hidden" id="idOrdine" name="idOrdine" value="${ordine.getIdOrdine()}">
                                                <input type="hidden" id="idOggetto" name="idOggetto" value="${ordine.getIdOggetto()}">
                                                <input type="hidden" id="idNegozio" name="idNegozio" value="${ordine.getIdNegozio()}">
                                                <button type="button" class="btn btn-outline-primary btn-block" data-toggle="modal" dclass="btn btn-outline-primary btn-block" data-target="#callAssistance">
                                                    Richiedi assistenza per questo oggetto
                                                </button>
                                            </form>
                                        </div>
                                        <div class="col-xs-12 d-block d-sm-none mx-auto">
                                            <form id="richiestaAssistenza" name="richiestaAssistenza" action="${pageContext.request.contextPath}/AssistenzaController?action=createAssistance" method="POST">
                                                <input type="hidden" id="idOrdine" name="idOrdine" value="${ordine.getIdOrdine()}">
                                                <input type="hidden" id="idOggetto" name="idOggetto" value="${ordine.getIdOggetto()}">
                                                <input type="hidden" id="idNegozio" name="idNegozio" value="${ordine.getIdNegozio()}">
                                                <button type="button" class="btn btn-outline-primary btn-block" data-toggle="modal" dclass="btn btn-outline-primary btn-block" data-target="#callAssistance">
                                                    Richiedi assistenza
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                    <hr>
                                    </c:if>
                                    <c:set var="i" scope="page" value="${i+1}" />
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:when test="${order == 'spediti'}">
                        <h2>Ordini spediti</h2>
                        <c:choose>
                            <c:when test="${listaOrdini.getList().size() == 0}">
                                <div class="row">
                                    <div class="col-12">
                                        <p>
                                            Mi spiace ma non ho trovato nessun ordine
                                        </p>
                                    </div>
                                    <div class="col-12">
                                        <h1>:(</h1>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:set var="i" scope="page" value="${0}" />
                                <c:forEach items="${listaOrdini.getList()}" var="ordine" >
                                    <c:if test="${ordine.getStato() != 0 && ordine.getStato() != 5}">
                                        <div class="row">
                                            <div class="col-lg-1 col-md-1 col-sm-2 col-xs-3">
                                                <img src="${listaImmagini.get(i).getSrc()}" alt="IMAGE NOT LOADED" style="width: 50px; height: 50px; object-fit: cover;">
                                            </div>
                                            <div class="col-lg-3 col-md-6 col-sm-10 col-xs-9">
                                                <c:url value="/objectSelectedController" var="objUrl" >
                                                    <c:param name="idOggetto" value="${listaOggetti.get(i).getId()}" />
                                                </c:url>
                                                <a href="${objUrl}" class="linkOggetto"><h2><c:out value="${listaOggetti.get(i).getNome()}" /></h2></a>
                                                <p>quantit&aacute;: <c:out value="${ordine.getQuantita()}"/></p>
                                            </div>
                                            <div class="col-lg-2 col-md-5 col-sm-6 col-xs-12">
                                                <p>Comprato da: </p>
                                                <c:url value="/UserController" var="storeUrl">
                                                    <c:param name="action" value="DescrizioneNegozio" />
                                                    <c:param name="idNegozio" value="${listaNegozi.get(i).getId()}" />
                                                </c:url>
                                                <p><a href="${storeUrl}"><c:out value="${listaNegozi.get(i).getNomeNegozio()}" /></a></p>
                                                <c:url value="/UserController" var="sellerUrl">
                                                    <c:param name="action" value="DescrizioneVenditore" />
                                                    <c:param name="idUtente" value="${listaVenditori.get(i).getId()}" />
                                                </c:url>
                                                <p><a href="${sellerUrl}"><c:out value="${listaVenditori.get(i).getCognome()}" /> <c:out value="${listaVenditori.get(i).getNome()}" /></a></p>
                                            </div>
                                            <div class="col-lg-2 col-md-4 col-sm-6 col-xs-12">
                                                <p>Comprato il:</p>
                                                <p><c:out value="${ordine.getDataOrdine()}" /></p>
                                            </div>
                                            <div class="col-lg-2 col-md-4 col-sm-6 col-xs-12">
                                                <h2><c:out value="${ordine.getPrezzoDiAcquisto()}" /> &euro;</h2>
                                            </div>
                                            <div class="col-lg-2 col-md-4 col-sm-6 col-xs-12">
                                                <p>Stato attuale:</p>
                                                <c:choose>
                                                    <c:when test="${ordine.getStato() == 1}">
                                                        <p>Pagamento ricevuto</p>
                                                    </c:when>
                                                    <c:when test="${ordine.getStato() == 2}">
                                                        <p>Ordine in lavorazione</p>
                                                    </c:when>
                                                    <c:when test="${ordine.getStato() == 3}">
                                                        <p><b>Ordine spedito</b></p>
                                                        <p>Tracking: <c:out value="${ordine.getCodiceTracking()}" /></p>
                                                    </c:when>
                                                    <c:when test="${ordine.getStato() == 4}">
                                                        <p>Ordine ricevuto</p>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                        </div>
                                        <div class="row">
                                        <div class="col-lg-5 col-sm-12 col-xs-12 mx-auto">
                                            <form id="richiestaAssistenza" name="richiestaAssistenza" action="${pageContext.request.contextPath}/AssistenzaController?action=createAssistance" method="POST">
                                                <input type="hidden" id="idOrdine" name="idOrdine" value="${ordine.getIdOrdine()}">
                                                <input type="hidden" id="idOggetto" name="idOggetto" value="${ordine.getIdOggetto()}">
                                                <input type="hidden" id="idNegozio" name="idNegozio" value="${ordine.getIdNegozio()}">
                                                <button type="button" class="btn btn-outline-primary btn-block" data-toggle="modal" dclass="btn btn-outline-primary btn-block" data-target="#callAssistance">
                                                    Richiedi assistenza per questo oggetto
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                    <hr>
                                    </c:if>
                                    <c:set var="i" scope="page" value="${i+1}" />
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
            </c:choose>
        </div>

        <%@include file="../Footer/footer.jsp" %>
    </body>
</html>


<div id="callAssistance" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <span class="modal-title"><b>ASSISTENZA</b></span>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <p>Scrivi la tua richiesta di assistenza (max 2000 caratteri)</p>
                <textarea cols="50" rows="5" maxlength="2000" form="richiestaAssistenza" id="testoAssistenza" name="testoAssistenza" required></textarea>
            </div>
            <div class="modal-footer">
                <button form="richiestaAssistenza" type="submit" class="col-5 paddingNav btn btn-outline-primary my-2 my-sm-0">INVIA RICHIESTA</button>
            </div>
        </div>
    </div>
</div>
