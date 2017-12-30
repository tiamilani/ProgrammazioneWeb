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
                        <h6>${negozio.getOrarioNegozio()}</h6>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>

