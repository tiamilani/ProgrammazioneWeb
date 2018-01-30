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
                <div class="col-lg-3 col-sm-4 col-xs-12">
                    <img src="${utenteSessione.getAvatar()}" alt="Errore nel caricamento dell'immagine dell'utente" style="width: 90%; height: 90%; max-height: 200px; max-width: 200px;"/>
                </div>
                <div class="col-xl-6 col-lg-5 col-sm-8 col-xs-12">
                    <h2>Ciao <c:out value="${utenteSessione.getNome()}" /></h2>
                    <p>Benvenuto nella tua pagina personale, i tuoi dati sono: </p>
                    <p>E-Mail: ${utenteSessione.getMail()}</p>
                    <c:if test="${utenteSessione.getUtenteType() == 1}">
                        <p>La tua valutazione: ${utenteSessione.getValutazione()}</p>
                    </c:if>
                </div>
                <div class="col-xl-3 col-lg-4 col-sm-12">
                    <a href="${pageContext.request.contextPath}/UserController?action=infoCurrentUser" method="GET" class="btn btn-outline-primary buttonSpace btn-block">Impostazione account</a>
                    <a href="${pageContext.request.contextPath}/UserController?action=orderList&order=data" method="GET" class="btn btn-outline-primary buttonSpace btn-block">I miei ordini</a>
                    <c:if test="${utenteSessione.getUtenteType() == 1}" >
                        <a href="${pageContext.request.contextPath}/UserController?action=gestisciNegozi&order=data&orderStore=dataup" method="GET" class="btn btn-outline-primary buttonSpace btn-block">Gestisci negozi</a>
                        <a href="${pageContext.request.contextPath}/UserController?action=infoPersonalReviews" method="GET"  class="btn btn-outline-primary buttonSpace btn-block">Recensioni Personali</a>
                    </c:if>
                    <c:if test="${utenteSessione.getUtenteType() == 0}" >
                        <a href="${pageContext.request.contextPath}/jspFile/Finale/Utente/diventaVenditore.jsp" class="btn btn-outline-primary buttonSpace btn-block">Diventa un venditore</a>
                    </c:if>
                    <c:if test="${utenteSessione.getUtenteType() == 2}" >
                        <a href="${pageContext.request.contextPath}/AssistenzaController?action=listAssistances" method="GET" class="btn btn-outline-primary buttonSpace btn-block">Gestisci richieste di assistenza</a>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/AssistenzaController?action=showAssistances" method="GET" class="btn btn-outline-primary buttonSpace btn-block">Richieste di assistenza</a>
                    <a  href="${pageContext.request.contextPath}/UserController?action=logout" method="POST" class="btn btn-outline-primary buttonSpace btn-block"><i class="Small material-icons">person</i> Logout</a>
                </div>
            </div>
        </div>
        <%@include file="../Footer/footer.jsp" %>
    </body>
</html>