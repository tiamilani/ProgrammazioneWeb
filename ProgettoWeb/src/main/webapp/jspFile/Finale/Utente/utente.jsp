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
            <%@include file="../Header/NavBar/navBar.jsp" %>
        </div>
        
        <div class="container-fluid">
            <h2>Ciao <c:out value="${utenteSessione.getNome()}" /></h2>
            <p>Benvenuto nella tua pagina di configuarazione</p>
            <a  href="${pageContext.request.contextPath}/UserController?action=logout" method="POST" class="btn btn-outline-primary buttonSpace"><i class="Small material-icons">person</i> Loggout</a>
        </div>
        
        <div class="container">
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>
