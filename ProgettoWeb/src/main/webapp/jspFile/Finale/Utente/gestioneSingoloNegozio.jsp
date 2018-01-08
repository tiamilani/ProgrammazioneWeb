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
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/jspFile/Finale/CSS/form.css">
        <title><c:out value="${utenteSessione.getNome()}" /> Gestione Negozio</title>
    </head>
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-2">
                    <img id="imgNegozio" class="d-block w-100" src="${immagine.getSrc()}" alt="IMAGE NOT LOADED" style="width: auto; max-height: 500px; object-fit: cover;">
                </div>
                <div class="col-10">
                    <h1><c:out value="${negozio.getNomeNegozio()}" /></h1>
                    <p>Da qui puoi gestire tutto ciò che riguarda il tuo negozio, a partire dagli ordini ricevuti all'aggiunta di oggetti al catalogo</p>
                    <div class="text-justify">
                        <h4><a href="${negozio.getLinkSito()}">${negozio.getLinkSito()}</a></h4><br/>
                        <h5>${indirizzo.getVia()} ${indirizzo.getnCivico()}, ${indirizzo.getCitta()}, ${indirizzo.getProvincia()}, ${indirizzo.getRegione()}, ${indirizzo.getStato()}</h5><br/>
                    </div>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-12">
                    <h2>Aggiungi oggetto</h2>
                    <p>Qui puoi aggiungere un oggetto a quelli già in vendita nel tuo negozio</p>
                </div>
            </div>
            <div class="row">
                <form class="col-12" id="nuovoOggetto" name="nuovoOggetto" method="POST" action="${pageContext.request.contextPath}/NegozioController">
                    <input type="hidden" name="action" value="addObject">
                    <input type="hidden" name="idNegozio" value="${negozio.getId()}">
                    <div class="row">
                        <div class="col-8">
                            <div class="row">
                                <div class="col-10">
                                    <p>Nome del nuovo oggetto</p>
                                </div>
                                <div class="col-10">
                                    <input type="text" class="form-control" id="nomeOggetto" name="nomeOggetto" placeholder="Nome" required>
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
                            <textarea class="form-control" id="descrizione" name="descrizione" rows="3"></textarea>
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
                        <div class="col-3">
                            <input class="form-control" type="number" id="disponibilita" name="disponibilita" value="9" required>
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
                            <button action="submit" type="submit" class="btn btn-outline-primary btn-block" id="btn">Aggiungi oggetto</button>
                        </div>
                    </div>
                </form>
            </div>
            <hr>
        </div>
        <div class="container">
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>

