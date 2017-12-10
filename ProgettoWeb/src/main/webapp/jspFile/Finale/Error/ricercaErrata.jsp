<%-- 
    Document   : ricercaErrata
    Created on : 6-ott-2017, 16.27.16
    Author     : mattia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title>Ricerca non consentita</title>
        <meta http-equiv="refresh" content="5;URL=${pageContext.request.contextPath}/jspFile/Finale/Index/index.jsp">
    </head>
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
        </div>
        
        <div class="container-fluid" style="text-align: center">
            <h1><b><c:out value="${errore}" /></b></h1>
            <h1><b>La pagina richiesta non è presente in questo sito</b></h1>
            <p>Resta calmo, tra pochi secondi sarai reindirizzato alla pagina principale.</p>
            <img src="http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/404.png" alt="404 - Pagina non trovata">
        </div>
        
        <div class="container">
            <hr/>
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>