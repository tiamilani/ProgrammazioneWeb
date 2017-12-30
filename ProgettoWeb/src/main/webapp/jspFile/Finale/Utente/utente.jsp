<%-- 
    Document   : utente.jsp
    Created on : 10-ott-2017, 23.35.36
    Author     : mattia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title><c:out value="${utenteSessione.getNome()}" /></title>
    </head>
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-3">
                    <img src="${utenteSessione.getAvatar()}" alt="Immagine utente" style="max-height: 200px; max-width: 200px;"/>
                </div>
                <div class="col-6">
                    <h2>Ciao <c:out value="${utenteSessione.getNome()}" /></h2>
                    <p>Benvenuto nella tua pagina personale, i tuoi dati sono: </p>
                    <p>E-Mail: ${utenteSessione.getMail()}</p>
                    <c:if test="${utenteSessione.getUtenteType() == 1}">
                        <p>La tua valutazione: ${utenteSessione.getValutazione()}</p>
                    </c:if>
                    <p>Altre info...</p>
                </div>
                <div class="col-3">
                    <a href="${pageContext.request.contextPath}/UserController?action=infoCurrentUser" method="GET" class="btn btn-outline-primary buttonSpace btn-block">Impostazione account</a>
                    <a href="${pageContext.request.contextPath}/UserController?action=orderList&order=data" method="GET" class="btn btn-outline-primary buttonSpace btn-block">I miei ordini</a>
                    <c:if test="${utenteSessione.getUtenteType() == 1}" >
                        <a href="${pageContext.request.contextPath}/UserController?action=gestisciNegozi&order=data&orderStore=dataup" method="GET" class="btn btn-outline-primary buttonSpace btn-block">Gestisci negozi</a>
                    </c:if>
                    <c:if test="${utenteSessione.getUtenteType() == 0}" >
                        <a href="${pageContext.request.contextPath}/jspFile/Finale/Utente/diventaVenditore.jsp" class="btn btn-outline-primary buttonSpace btn-block">Diventa un venditore</a>
                    </c:if>
                    <c:if test="${utenteSessione.getUtenteType() == 2}" >
                        <a href="${pageContext.request.contextPath}/AssistenzaController?action=listAssistances" method="GET" class="btn btn-outline-primary buttonSpace btn-block">Gestisci richieste di assistenza</a>
                    </c:if>
                    <a href="#" method="GET" class="btn btn-outline-primary buttonSpace btn-block">Assistenza</a>
                    <a  href="${pageContext.request.contextPath}/UserController?action=logout" method="POST" class="btn btn-outline-primary buttonSpace btn-block"><i class="Small material-icons">person</i> Logout</a>
                </div>
        </div>
        
        <%@include file="../Footer/footer.jsp" %>
    </body>
</html>