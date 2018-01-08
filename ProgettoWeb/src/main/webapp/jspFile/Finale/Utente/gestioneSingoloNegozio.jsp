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
            <c:choose>
                <c:when test="${oggettoInserito == 0}">
                    <div class="alert alert-success">
                        <strong>Successo!</strong> oggetto inserito con successo.
                    </div>
                </c:when>
                <c:when test="${oggettoInserito == 1}">
                    <div class="alert alert-danger">
                        <strong>Attenzione!</strong> oggetto non inserito.
                    </div>
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>
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
                            <button action="submit" type="submit" class="btn btn-outline-primary btn-block" id="btn">Aggiungi oggetto</button>
                        </div>
                    </div>
                </form>
            </div>
            <hr>
            <c:choose>
                <c:when test="${oggettoModificato == 0}">
                    <div class="alert alert-success">
                        <strong>Successo!</strong> modifica del catalogo terminata con successo.
                    </div>
                </c:when>
                <c:when test="${oggettoModificato == 1}">
                    <div class="alert alert-danger">
                        <strong>Attenzione!</strong> modifica al catalogo non apportata a causa di un errore.
                    </div>
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>
            <div class="row">
                <div class="col-12">
                    <h2>Modifica/eliminazione oggetto</h2>
                    <p>Qui puoi modificare un oggetto in ogni sua parte, apportare uno sconto ad un determinato oggetto, eliminare un oggetto, ma per poter eliminare l'oggetto dovrai inserire il nome dell'oggetto nuovamente per confermare la tua volontà ad eliminarlo</p>
                    <p>Non serva tu inserisca tutte le parti di un oggetto, puoi modificare anche solo una parte delle informazioni dell'oggetto, le altre verranno mantenute invariate</p>
                </div>
            </div>
            <div class="row">
                <form class="col-12" id="modificaOggetto" name="modificaOggetto" method="POST" action="${pageContext.request.contextPath}/NegozioController">
                    <input type="hidden" name="action" value="modifyObject">
                    <input type="hidden" name="idNegozio" value="${negozio.getId()}">
                    <div class="row">
                        <div class="col-4">
                            <p>Seleziona l'oggetto che vuoi modificare</p>
                        </div>
                        <div class="col-8">
                            <select class="form-control" id="modifyObject" name="modifyObject">
                                <option value="0" selected>Non modificare nessun oggetto</option>
                                <c:forEach items="${listaOggeti.getList()}" var="oggetto" >
                                    <option value="${oggetto.getId()}"><c:out value="${oggetto.getId()}" /> - <c:out value="${oggetto.getNome()}" /></option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-8">
                            <div class="row">
                                <div class="col-10">
                                    <p>Nuovo nome per l'oggetto</p>
                                </div>
                                <div class="col-10">
                                    <input type="text" class="form-control" id="mdifynomeOggetto" name="mdifynomeOggetto" placeholder="Nome">
                                </div>
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="row">
                                <div class="col-12">
                                    <p>Seleziona la categoria</p>
                                </div>
                                <div class="col-12">
                                    <select class="form-control" id="modifyCategoria" name="modifyCategoria">
                                        <option value="0" selected>Non cambiare categoria</option>
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
                            <textarea class="form-control" id="modifyDescrizione" name="modifyDescrizione" rows="3"></textarea>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-1">
                            Prezzo 
                        </div>
                        <div class="col-3">
                            <input type="text" class="form-control" id="modifyPrezzo" name="modifyPrezzo" placeholder="299$">
                        </div>
                        <div class="col-4">
                            <input type="checkbox" class="form-check-input" id="modifyRitironegozio" name="modifyRitironegozio">
                            Prodotto con ritiro in negozio
                        </div>
                        <div class="col-1">
                            Disponibilit&aacute;:
                        </div>
                        <div class="col-1">
                            <input class="form-control" type="number" id="modifyDisponibilita" name="modifyDisponibilita" value="-1">
                        </div>
                        <div class="col-2">
                            <select class="form-control" id="modifySelectDisponibilita" name="modifySelectDisponibilita">
                                <option value="-1">Non modificare disponibilità</option>
                                <option value="0">Disponibile</option>
                                <option value="1">In arrivo</option>
                                <option value="2">Esaurito</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <p>Se vuoi puoi mettere in sconto il prodotto</p>
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
                            <input class="form-control" type="date" placeholder="2011-08-19" value="AAAA-MM-GG" id="modifyDataFineSconto" name="modifyDataFineSconto">
                        </div>
                        <div class="col-4">
                            <input class="form-control" type="number" id="modifyPercentualeSconto" name="modifyPercentualeSconto" value="10">
                        </div>
                        <div class="col-4">
                            <input type="checkbox" class="form-check-input" id="modifyScontoAttivo" name="modifyScontoAttivo">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <button action="submit" type="submit" class="btn btn-outline-primary btn-block" id="btnModify">Modifica oggetto</button>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <p>Per eliminare l'oggetto che hai selzionato inseriscine il nome qui sotto come conferma della tua volontà di eliminarlo</p>
                        </div>
                        <div class="col-8">
                            <input type="text" class="form-control" id="modifyDelete" name="modifyDelete" placeholder="Nome oggetto">
                        </div>
                        <div class="col-4">
                            <button action="submit" type="submit" class="btn btn-outline-danger btn-block" id="btnDelete">Elimina oggetto</button>
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

