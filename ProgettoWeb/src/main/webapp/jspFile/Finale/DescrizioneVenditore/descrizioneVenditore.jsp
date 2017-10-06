<%-- 
    Document   : descrizioneOggetto
    Created on : 3-ott-2017, 8.55.49
    Author     : andreafadi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="venditore" class="it.progettoWeb.java.database.Model.Utente.ModelloUtente" scope="request" />

<!DOCTYPE html>
<html>
    <head>
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title>${venditore.getNome()}</title>
    </head>
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/navBar.jsp" %>
            <%@include file="../Components/Menu/fixedMenu.jsp" %>
        </div>
        <div class="container-fluid">
            <%@include file="../Components/Carosello/fotoOggetto.jsp" %>
            <%@include file="descrizione.jsp" %>
            <%@include file="../Components/Google/mapOggetto.jsp" %>
            <%@include file="../Components/Carosello/oggettiCorrelati.jsp" %>
            <%@include file="recensioni.jsp" %>
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>
