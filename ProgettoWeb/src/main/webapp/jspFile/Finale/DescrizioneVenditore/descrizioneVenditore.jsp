<%-- 
    Document   : descrizioneOggetto
    Created on : 3-ott-2017, 8.55.49
    Author     : andreafadi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="venditore" class="it.progettoWeb.java.database.Model.Utente.ModelloUtente" scope="request" />
<jsp:useBean id="recensioni" class="it.progettoWeb.java.database.Model.recensioneVenditore.ModelloListeRecensioneVenditore" scope="request" />
<jsp:useBean id="listaNegozi" class="it.progettoWeb.java.database.Model.Negozio.ModelloListeNegozio" scope="request" />
<jsp:useBean id="listaIndirizzi" class="it.progettoWeb.java.database.Model.indirizzo.ModelloListeIndirizzo" scope="request" />
<jsp:useBean id="listaImmagini" class="it.progettoWeb.java.database.Model.immagineNegozio.ModelloListeImmagineNegozio" scope="request" />

<!DOCTYPE html>
<html>
    <head>
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title>${venditore.getCognome()} ${venditore.getNome()}</title>
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
            <%@include file="descrizione.jsp" %>
            <c:if test="${listaIndirizzi.getList().size() > 0}">
                <%@include file="mapVenditore.jsp" %>
            </c:if>
            <%@include file="negoziCorrelati.jsp" %>
            <c:if test="${canReviewsN == true}">
                <%@include file="insertReview.jsp" %>
            </c:if>
            <c:if test="${recensioniVenditori.getL().size() > 0}">
                <%@include file="recensioni.jsp" %>
            </c:if>
        </div>
        <%@include file="../Footer/footer.jsp" %>
    </body>
</html>
