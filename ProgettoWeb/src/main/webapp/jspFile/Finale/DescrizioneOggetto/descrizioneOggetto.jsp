<%-- 
    Document   : descrizioneOggetto
    Created on : 3-ott-2017, 8.55.49
    Author     : andreafadi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="oggetto" class="it.progettoWeb.java.database.Model.Oggetto.ModelloOggetto" scope="request" />
<jsp:useBean id="negozio" class="it.progettoWeb.java.database.Model.Negozio.ModelloNegozio" scope="request" />
<jsp:useBean id="venditore" class="it.progettoWeb.java.database.Model.Utente.ModelloUtente" scope="request" />
<jsp:useBean id="utenteSessione" class="it.progettoWeb.java.database.Model.Utente.ModelloUtente" scope="request" />
<jsp:useBean id="recensioni" class="it.progettoWeb.java.database.Model.recensioneOggetto.ModelloListeRecensioneOggetto" scope="request" />
<jsp:useBean id="indirizzo" class="it.progettoWeb.java.database.Model.indirizzo.ModelloIndirizzo" scope="request" />
<jsp:useBean id="listaImmagini" class="it.progettoWeb.java.database.Model.immagineOggetto.ModelloListeImmagineOggetto" scope="request" />
<jsp:useBean id="listaOggetti" class="it.progettoWeb.java.database.Model.Oggetto.ModelloListeOggetto" scope="request" />
<jsp:useBean id="listaImmaginiOggetto" class="it.progettoWeb.java.database.Model.immagineOggetto.ModelloListeImmagineOggetto" scope="request" />

<!DOCTYPE html>
<html>
    <head>
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title>${oggetto.getNome()}</title>
    </head>
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
            <%@include file="../Components/Menu/fixedMenu.jsp" %>
        </div>
        <div class="container-fluid">
            <%@include file="fotoOggetto.jsp" %>
            <%@include file="descrizione.jsp" %>
            <c:if test="${oggetto.getRitiroInNegozio() == 1}">
                <%@include file="mapOggetto.jsp" %>
            </c:if>
            <%@include file="oggettiCorrelati.jsp" %>
            <c:if test="${utenteSessione.getId() > 0}">
                <%@include file="insertReview.jsp" %>
            </c:if>
            <%@include file="recensioni.jsp" %>
        </div>
        <div class="container">
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>
