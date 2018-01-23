<%-- 
    Document   : descrizioneOggetto
    Created on : 3-ott-2017, 8.55.49
    Author     : andreafadi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="negozio" class="it.progettoWeb.java.database.Model.Negozio.ModelloNegozio" scope="request" />
<jsp:useBean id="immagine" class="it.progettoWeb.java.database.Model.immagineNegozio.ModelloImmagineNegozio" scope="request" />
<jsp:useBean id="indirizzo" class="it.progettoWeb.java.database.Model.indirizzo.ModelloIndirizzo" scope="request" />
<jsp:useBean id="listaOggetti" class="it.progettoWeb.java.database.Model.Oggetto.ModelloListeOggetto" scope="request" />
<jsp:useBean id="listaImmaginiOggetto" class="it.progettoWeb.java.database.Model.immagineOggetto.ModelloListeImmagineOggetto" scope="request" />

<!DOCTYPE html>
<html>
    <head>
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title>${negozio.getNomeNegozio()}</title>
    </head>
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
        </div>
        <div class="container-fluid">
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
