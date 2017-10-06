<%-- 
    Document   : categoria
    Created on : 30-set-2017, 17.26.16
    Author     : mattia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="categoria" class="it.progettoWeb.java.database.Model.Categoria.ModelloCategoria" scope="request" />
        <h1>CIAO</h1>
        <p>Io sono qui per mostrare le informazioni e gli oggetti della categoria: <c:out value="${categoria.getNome()}"/></p>
        
    </body>
</html>
