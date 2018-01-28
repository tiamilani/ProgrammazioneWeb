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
<%--<jsp:useBean id="utenteSessione" class="it.progettoWeb.java.database.Model.Utente.ModelloUtente" scope="request" />--%>
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
        <style>
            .carousel-indicators li {
                background-color: rgba(225, 150, 100, 0.5);
            }

            .carousel-indicators .active {
                background-color: rgba(255, 50, 50, 0.7);
            }

            .carousel-indicators {
                top: 105%;
            }
        </style>    
    </head>
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
        </div>
        <div class="container-fluid">
            <%@include file="fotoOggetto.jsp" %>
            <%@include file="descrizione.jsp" %>
            <c:if test="${oggetto.getRitiroInNegozio() == 1}">
                <%@include file="mapOggetto.jsp" %>
            </c:if>
            <%@include file="oggettiCorrelati.jsp" %>
            <c:if test="${canReviewsO == true}">
                <%@include file="insertReview.jsp" %>
            </c:if>
            <c:if test="${recensioniUtenteImmagini.getL().size() > 0}">
                <%@include file="recensioni.jsp" %>
            </c:if>
        </div>
        <%@include file="../Footer/footer.jsp" %>
    </body>
</html>
