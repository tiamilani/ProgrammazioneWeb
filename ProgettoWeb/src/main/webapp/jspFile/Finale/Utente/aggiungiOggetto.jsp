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
                        <div class="col-8">
                            <div class="row">
                                <div class="col-10">
                                    <p>Nome del nuovo oggetto</p>
                                </div>
                                <div class="col-10">
                                    <input type="text" class="form-control" id="nomeOggetto" name="nomeOggetto" maxlength="500" placeholder="Nome" required>
                                </div>
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="row">
                                <div class="col-12">
                                    <p>Seleziona la categoria a cui appartiene</p>
                                </div>
                                <div class="col-12">
                                    <select class="form-control" id="selectCategoria" name="selectCategoria" required>
                                        <c:forEach items="${categorie.getList()}" var="categoria" >
                                            <option value="${categoria.getId()}">${categoria.getNome()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
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
                        <div class="col-1">
                            Prezzo 
                        </div>
                        <div class="col-3">
                            <input type="text" class="form-control" id="prezzo" name="prezzo" placeholder="299$" required>
                        </div>
                        <div class="col-4">
                            <input type="checkbox" class="form-check-input" id="ritironegozio" name="ritironegozio">
                            Prodotto con ritiro in negozio
                        </div>
                        <div class="col-1">
                            Disponibilit&aacute;:
                        </div>
                        <div class="col-1">
                            <input class="form-control" type="number" id="disponibilita" name="disponibilita" value="9" required>
                        </div>
                        <div class="col-2">
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
                        <div class="col-4">
                            <p>Data di fine sconto</p>
                        </div>
                        <div class="col-4">
                            <p>Percentuale di sconto</p>
                        </div>
                        <div class="col-4">
                            <p>Attiva sconto</p>
                        </div>
                        <div class="col-4">
                            <input class="form-control" type="date" placeholder="2011-08-19" value="AAAA-MM-GG" id="dataFineSconto" name="dataFineSconto">
                        </div>
                        <div class="col-4">
                            <input class="form-control" type="number" id="percentualeSconto" name="percentualeSconto" value="10">
                        </div>
                        <div class="col-4">
                            <input type="checkbox" class="form-check-input" id="scontoAttivo" name="scontoAttivo">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <h2>Metodi di spedizione</h2>
                            <p>Seleziona almeno un metodo di spedizione per questo oggetto</p>
                        </div>
                        <c:forEach items="${listaSpedizioni.getList()}" var="spedizione" >
                            <div class="col-3">
                                <input type="checkbox" class="form-check-input" id="checkbox-${spedizione.getIdS()}" name="checkbox-${spedizione.getIdS()}">
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
                            <button action="submit" type="submit" class="btn btn-outline-primary btn-block" id="btn">Aggiungi oggetto</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="container">
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>
