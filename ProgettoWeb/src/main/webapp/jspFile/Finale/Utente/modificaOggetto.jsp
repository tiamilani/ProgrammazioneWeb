<%-- 
    Document   : modificaOggetto
    Created on : 10-gen-2018, 22.13.46
    Author     : mattia
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/jspFile/Finale/CSS/form.css">
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title><c:out value="${utenteSessione.getNome()}" /> Modifica oggetto</title>
    </head>
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <h2>Modifica/eliminazione oggetto</h2>
                    <p>Qui puoi modificare un oggetto in ogni sua parte, apportare uno sconto ad un determinato oggetto, eliminare un oggetto, ma per poter eliminare l'oggetto dovrai inserire il nome dell'oggetto nuovamente per confermare la tua volontà ad eliminarlo</p>
                    <p>Ricorda che se viene attivato un periodo di sconto non è cancellabile</p>
                </div>
            </div>
            <div class="row">
                <form class="col-12" id="modificaOggetto" name="modificaOggetto" method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/NegozioController">
                    <input type="hidden" name="action" value="modifyObject">
                    <input type="hidden" name="idNegozio" value="${negozio.getId()}">
                    <input type="hidden" id="modifyObject" name="modifyObject" value="${oggetto.getId()}">
                    <div class="row">
                        <div class="col-lg-8 col-sm-12 col-xs-12">
                            <p>Nome del nuovo oggetto</p>
                        </div>
                        <div class="col-lg-4 d-block d-lg-block">
                            <p>Seleziona la categoria a cui appartiene</p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-8 col-sm-12 col-xs-12">
                            <input type="text" class="form-control" id="mdifynomeOggetto" name="mdifynomeOggetto" maxlength="500" value="${oggetto.getNome()}" required>
                        </div>
                        <div class="col-sm-12 col-xs-12 d-block d-sm-block d-md-block d-lg-none">
                            <p>Seleziona la categoria a cui appartiene</p>
                        </div>
                        <div class="col-lg-4 col-sm-12 col-xs-12">
                            <select class="form-control" id="modifyCategoria" name="modifyCategoria" required>
                                <c:forEach items="${categorie.getList()}" var="categoria" >
                                    <c:choose>
                                        <c:when test="${categoria.getId() == oggetto.getCategoria()}">
                                            <option value="${categoria.getId()}" selected>${categoria.getNome()}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${categoria.getId()}">${categoria.getNome()}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <p>Inserisci una descrizione dell'oggetto, sarà poi visualizzata dagli utenti quindi attento a ciò che scrivi</p>
                        </div>
                        <div class="col-12">
                            <textarea class="form-control" id="modifyDescrizione" name="modifyDescrizione" maxlength="2500" rows="3" required>${oggetto.getDescrizione()}</textarea>
                        </div>
                    </div>
                    <div class="row">
                        <div class="d-none d-md-block d-lg-none col-md-3">
                            Prezzo
                        </div>
                        <div class="d-none d-md-block d-lg-none col-md-3">
                        </div>
                        <div class="d-none d-md-block d-lg-none col-sm-3">
                            Disponibilit&aacute;:
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xl-1 col-lg-3 d-md-none d-lg-block">
                            Prezzo 
                        </div>
                        <div class="col-xl-2 col-lg-3 col-md-2">
                            <input type="text" class="form-control" id="modifyPrezzo" name="modifyPrezzo" value="${oggetto.getPrezzo()}" required>
                        </div>
                        <div class="col-xl-2 col-lg-6 col-md-4">
                            <select class="form-control" id="modifyRitiroInNegozio" name="modifyRitiroInNegozio">
                                <c:choose>
                                    <c:when test="${0 == oggetto.getRitiroInNegozio()}">
                                        <option value="0" selected>Ritiro in negozio NON disponibile</option>
                                        <option value="1">Ritiro in negozio Disponibile</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="0">Ritiro in negozio NON disponibile</option>
                                        <option value="1" selected>Ritiro in negozio Disponibile</option>
                                    </c:otherwise>
                                </c:choose>
                            </select>
                        </div>
                        <div class="col-xl-2 col-lg-3 d-md-none d-lg-block">
                            Disponibilit&aacute;:
                        </div>
                        <div class="col-xl-3 col-lg-3 col-md-3">
                            <input class="form-control" type="number" id="modifyDisponibilita" name="modifyDisponibilita" value="${oggetto.getDisponibilita()}" required>
                        </div>
                        <div class="col-xl-2 col-lg-6 col-md-3">
                            <select class="form-control" id="modifySelectDisponibilita" name="modifySelectDisponibilita">
                                <c:choose>
                                    <c:when test="${0 == oggetto.getStatoDisponibilita()}">
                                        <option value="0" selected>Disponibile</option>
                                        <option value="1">In arrivo</option>
                                        <option value="2">Esaurito</option>
                                    </c:when>
                                    <c:when test="${1 == oggetto.getStatoDisponibilita()}">
                                        <option value="0">Disponibile</option>
                                        <option value="1" selected>In arrivo</option>
                                        <option value="2">Esaurito</option>
                                    </c:when>
                                    <c:when test="${2 == oggetto.getStatoDisponibilita()}">
                                        <option value="0">Disponibile</option>
                                        <option value="1">In arrivo</option>
                                        <option value="2" selected>Esaurito</option>
                                    </c:when>
                                </c:choose>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <c:choose>
                            <c:when test="${null == oggetto.getDataFineSconto()}">
                                <div class="col-12">
                                    <p>Se vuoi puoi mettere in sconto il prodotto</p>
                                </div>
                                <div class="col-md-4 col-sm-12 col-xs-12">
                                    <p>Data di fine sconto</p>
                                </div>
                                <div class="col-md-4 d-none d-xs-none d-sm-none d-md-block">
                                    <p>Percentuale di sconto</p>
                                </div>
                                <div class="col-md-4 d-none d-xs-none d-sm-none d-md-block">
                                    <p>Attiva sconto</p>
                                </div>
                                <div class="col-md-4 col-sm-12 col-xs-12">
                                    <input class="form-control" type="date" placeholder="2011-08-19" value="AAAA-MM-GG" id="modifyDataFineSconto" name="modifyDataFineSconto">
                                </div>
                                <div class="d-none d-sm-block d-md-none col-sm-12">
                                    <p>Percentuale di sconto</p>
                                </div>
                                <div class="d-block d-sm-none col-xs-12">
                                    <p>Percentuale di sconto</p>
                                </div>
                                <div class="col-md-4 col-sm-12 col-xs-12">
                                    <input class="form-control" type="number" id=<div class="d-none d-sm-block d-md-none col-sm-12">
                                </div>
                                <div class="d-none d-sm-block d-md-none col-sm-6">
                                    <p>Attiva sconto</p>
                                </div>
                                <div class="d-block d-sm-none col-xs-6">
                                    <p>Attiva sconto</p>
                                </div>
                                <div class="col-md-4 col-sm-6 col-xs-2">
                                    <input type="checkbox" class="form-check-input" id="modifyScontoAttivo" name="modifyScontoAttivo">
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="col-12">
                                    <p>Il prodotto &egrave; gi&aacute; scontato, prima di poterlo mettere nuovamente in sconto dovrai aspettare che questo termini</p>
                                </div>
                                <div class="col-md-4 col-sm-12 col-xs-12">
                                    <p>Data di fine sconto</p>
                                </div>
                                <div class="col-md-4 d-none d-xs-none d-sm-none d-md-block">
                                    <p>Percentuale di sconto</p>
                                </div>
                                <div class="col-md-4 d-none d-xs-none d-sm-none d-md-block">
                                    <p>Attiva sconto</p>
                                </div>
                                <div class="col-md-4 col-sm-12 col-xs-12">
                                    <input class="form-control" type="date" value="${oggetto.getDataFineSconto()}" id="modifyDataFineSconto" name="modifyDataFineSconto">
                                </div>
                                <div class="d-none d-sm-block d-md-none col-sm-12">
                                    <p>Percentuale di sconto</p>
                                </div>
                                <div class="d-block d-sm-none col-xs-12">
                                    <p>Percentuale di sconto</p>
                                </div>
                                <div class="col-md-4 col-sm-12 col-xs-12">
                                    <input class="form-control" type="number" id="modifyPercentualeSconto" name="modifyPercentualeSconto" value="${oggetto.getSconto()}">
                                </div>
                                <div class="d-none d-sm-block d-md-none col-sm-6">
                                    <p>Attiva sconto</p>
                                </div>
                                <div class="d-block d-sm-none col-xs-6">
                                    <p>Attiva sconto</p>
                                </div>
                                <div class="col-md-4 col-sm-6 col-xs-2">
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <h2>Metodi di spedizione</h2>
                            <p>Questi sono i metodi di spedizione Scelti per questo oggetto, ricordati di lasciarne almeno uno attivo, nel caso in cui tu li disattivassi tutti verrà attivato il primo</p>
                        </div>
                        <c:forEach items="${listaSpedizioni.getList()}" var="spedizione" >
                            <div class="col-lg-3 col-md-6 col-sm-12 col-xs-12">
                                <c:choose>
                                    <c:when test="${listaSpedizioniOggetto.present(spedizione.getIdS())}">
                                        <input type="checkbox" class="form-check-input" id="checkbox-${spedizione.getIdS()}" name="checkbox-${spedizione.getIdS()}" checked>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="checkbox" class="form-check-input" id="checkbox-${spedizione.getIdS()}" name="checkbox-${spedizione.getIdS()}">
                                    </c:otherwise>
                                </c:choose>
                                ${spedizione.getCorriere()} - ${spedizione.getNome()}
                            </div>
                        </c:forEach>
                    </div>
                    <div class="row">
                        <div class="col-12 form-group">
                            <label style="margin-bottom: 10px; display: block;" for="file">Carica delle immagini per una recensione migliore!</label>
                            <input type="file" style="margin: 0; display: inline-block" id="file" name="file" accept="image/*" multiple>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <button action="submit" type="submit" class="btn btn-outline-primary btn-block" id="btnModify">Modifica oggetto</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
            <%@include file="../Footer/footer.jsp" %>
    </body>
</html>

