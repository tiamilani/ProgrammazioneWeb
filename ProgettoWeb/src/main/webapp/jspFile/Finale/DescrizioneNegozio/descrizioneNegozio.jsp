<%-- 
    Document   : descrizioneOggetto
    Created on : 3-ott-2017, 8.55.49
    Author     : andreafadi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="negozio" class="it.progettoWeb.java.database.Model.Negozio.ModelloNegozio" scope="request" />
<jsp:useBean id="listaImmagini" class="it.progettoWeb.java.database.Model.immagineNegozio.ModelloListeImmagineNegozio" scope="request" />
<jsp:useBean id="indirizzo" class="it.progettoWeb.java.database.Model.indirizzo.ModelloIndirizzo" scope="request" />
<jsp:useBean id="listaOggetti" class="it.progettoWeb.java.database.Model.Oggetto.ModelloListeOggetto" scope="request" />
<jsp:useBean id="listaImmaginiOggetto" class="it.progettoWeb.java.database.Model.immagineOggetto.ModelloListeImmagineOggetto" scope="request" />

<!DOCTYPE html>
<html>
    <head>
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title>${negozio.getNomeNegozio()}</title>
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
            <%@include file="fotoNegozio.jsp" %>
            <%@include file="descrizione.jsp" %>
            <%@include file="mapNegozio.jsp" %>
            <%@include file="oggettiPresenti.jsp" %>
            <c:if test="${canReviewsS == true}">
                <%@include file="insertReview.jsp" %>
            </c:if>
            <c:if test="${recensioniNegozi.getL().size() > 0}">
                <%@include file="recensioni.jsp" %>
            </c:if>
        </div>
        <%@include file="../Footer/footer.jsp" %>
    </body>
</html>
