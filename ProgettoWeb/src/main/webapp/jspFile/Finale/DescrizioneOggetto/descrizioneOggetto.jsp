<%-- 
    Document   : descrizioneOggetto
    Created on : 3-ott-2017, 8.55.49
    Author     : andreafadi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %> <!-- meta & script, no title, check -->
        <title>TO DO - Bean.NomeOggetto</title>
    </head>
    <body>
        <jsp:useBean id="oggetto" class="it.progettoWeb.java.database.Model.Oggetto.ModelloOggetto" scope="request" />
        
        <div class="container-fluid">
            <%@include file="../Header/NavBar/navBar.jsp" %>
            <%@include file="../Components/Menu/fixedMenu.jsp" %>
            <%@include file="../Components/Carosello/slideShow.jsp" %>
            <span>${oggetto.getDescrizione()}</span>
            <% if (oggetto.getRitiroInNegozio() == 1); %>
                <%@include file="../Components/Google/mapOggetto.jsp" %>
            <%@include file="../Components/Carosello/oggettiCorrelati.jsp" %>
            <!-- Include JSP recensioniOggetto -->
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>
