<%-- 
    Document   : aggiungiOggetto
    Created on : 10-gen-2018, 21.38.26
    Author     : mattia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/jspFile/Finale/CSS/form.css">
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title><c:out value="${utenteSessione.getNome()}" /> Aggiunta oggetto</title>
    </head>
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <h2>Aggiungi oggetto</h2>
                    <p>Qui puoi aggiungere un oggetto a quelli già in vendita nel tuo negozio</p>
                    <p>Ricorda che se viene attivato un periodo di sconto non è cancellabile</p>
                </div>
            </div>
            <div class="row">
                <form class="col-12" id="nuovoOggetto" name="nuovoOggetto" method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/NegozioController">
                    <input type="hidden" name="action" value="addObject">
                    <input type="hidden" name="idNegozio" value="${negozio.getId()}">
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
                            <input type="text" class="form-control" id="nomeOggetto" name="nomeOggetto" maxlength="500" placeholder="Nome" style="margin-top: 5px;" required>
                        </div>
                        <div class="col-sm-12 col-xs-12 d-block d-sm-block d-md-block d-lg-none">
                            <p>Seleziona la categoria a cui appartiene</p>
                        </div>
                        <div class="col-lg-4 col-sm-12 col-xs-12">
                            <select class="form-control" id="selectCategoria" name="selectCategoria" required style="margin-top: 5px;">
                                <c:forEach items="${categorie.getList()}" var="categoria" >
                                    <option value="${categoria.getId()}">${categoria.getNome()}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <p>Inserisci una descrizione dell'oggetto, sarà poi visualizzata dagli utenti quindi attento a ciò che scrivi</p>
                        </div>
                        <div class="col-12">
                            <textarea class="form-control" id="descrizione" name="descrizione" maxlength="2500" rows="3"></textarea>
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
                        <div class="col-xl-2 col-lg-3 col-md-3">
                            <input type="text" class="form-control" id="prezzo" name="prezzo" placeholder="299$" required>
                        </div>
                        <div class="col-xl-2 col-lg-6 d-md-none d-lg-block">
                            <input type="checkbox" class="form-check-input" id="ritironegozio" name="ritironegozio">
                            Prodotto con ritiro in negozio
                        </div>
                        <div class="d-none d-md-block d-lg-none col-md-3">
                            <input type="checkbox" class="form-check-input" id="ritironegozio" name="ritironegozio">
                            Ritiro in negozio
                        </div>
                        <div class="col-xl-2 col-lg-3 d-md-none d-lg-block">
                            Disponibilit&aacute;:
                        </div>
                        <div class="col-xl-3 col-lg-3 col-md-3">
                            <input class="form-control" type="number" id="disponibilita" name="disponibilita" value="9" min="0" required>
                        </div>
                        <div class="col-xl-2 col-lg-6 col-md-3">
                            <select class="form-control" id="selectDisponibilita" name="selectDisponibilita" required>
                                    <option value="0">Disponibile</option>
                                    <option value="1">In arrivo</option>
                                    <option value="2">Esaurito</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <p>Se vuoi puoi già mettere in sconto il prodotto</p>
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
                            <input class="form-control" type="date" placeholder="2011-08-19" value="AAAA-MM-GG" id="dataFineSconto" name="dataFineSconto">
                        </div>
                        <div class="d-none d-sm-block d-md-none col-sm-12">
                            <p>Percentuale di sconto</p>
                        </div>
                        <div class="d-block d-sm-none col-xs-12">
                            <p>Percentuale di sconto</p>
                        </div>
                        <div class="col-md-4 col-sm-12 col-xs-12">
                            <input class="form-control" type="number" id="percentualeSconto" name="percentualeSconto" value="10">
                        </div>
                        <div class="d-none d-sm-block d-md-none col-sm-6">
                            <p>Attiva sconto</p>
                        </div>
                        <div class="d-block d-sm-none col-xs-6">
                            <p>Attiva sconto</p>
                        </div>
                        <div class="col-md-4 col-sm-6 col-xs-2">
                            <input type="checkbox" class="form-check-input" id="scontoAttivo" name="scontoAttivo">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <h2>Metodi di spedizione</h2>
                            <p>Seleziona almeno un metodo di spedizione per questo oggetto</p>
                        </div>
                        <c:forEach items="${listaSpedizioni.getList()}" var="spedizione" >
                            <div class="col-lg-3 col-md-6 col-sm-12 col-xs-12">
                                <input type="checkbox" class="form-check-input" id="checkbox-${spedizione.getIdS()}" name="checkbox-${spedizione.getIdS()}">
                                ${spedizione.getCorriere()} - ${spedizione.getNome()}
                            </div>
                        </c:forEach>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-12">
                            <h2>Immagini per l'oggetto</h2>
                        </div>
                        <div class="col-12 form-group">
                            <label style="margin-bottom: 10px; display: block;" for="file">Carica delle immagini per una recensione migliore!</label>
                            <input type="file" style="margin: 0; display: inline-block" id="file" name="file" accept="image/*" multiple>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <button action="submit" type="submit" class="btn btn-outline-primary btn-block" id="btn">Aggiungi oggetto</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
            <%@include file="../Footer/footer.jsp" %>
    </body>
</html>
