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
        <title>Ricerca non consentita</title>
        <meta http-equiv="refresh" content="10;URL=${pageContext.request.contextPath}/jspFile/Finale/Index/index.jsp">
    </head>
    <body>
        <h1><c:out value="${errore}" /></h1>
        <p>ora ti riporto alla homePage</p>
    </body>
</html>
