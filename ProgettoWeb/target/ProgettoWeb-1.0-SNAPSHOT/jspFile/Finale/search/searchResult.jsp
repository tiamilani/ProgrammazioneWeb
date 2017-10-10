<%-- 
    Document   : searchResult
    Created on : 5-ott-2017, 16.37.56
    Author     : mattia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>

    <!-- (Optional) Latest compiled and minified JavaScript translation files -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/i18n/defaults-*.min.js"></script>
        <title>Pagina di test</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>Parola cercata:<%=  request.getParameter("search") %></p>
        <p>Categoria cercata:<%=  request.getParameter("hiddenidCategoria") %></p>
        <%--<p>min price cercata:<%=  Integer.parseInt((String)request.getAttribute("hiddenminPrice")) %></p>
        <p>max price cercata:<%=  Integer.parseInt((String)request.getAttribute("hiddenmaxPrice")) %></p>
        <p>nome venditore cercata:<%=  request.getParameter("hiddennomeVenditore") %></p>
        <p>nome negozio cercata:<%=  request.getParameter("hiddennomeNegozio") %></p>
        <p>ritiro in negozio cercata:<%= Boolean.getBoolean((String)request.getAttribute("hiddencheckRitiroInNegozio")) %></p>
        <p>oggetti in sconto cercata:<%= Boolean.getBoolean((String)request.getAttribute("hiddencheckProdottiScontati")) %></p>
        <p>latitudine cercata:<%=  Double.parseDouble((String)request.getAttribute("hiddenlatitudine")) %></p>
        <p>longitudine cercata:<%=  Double.parseDouble((String)request.getAttribute("hiddenlongitudine")) %></p>
        <p>raggio cercata:<%=  Double.parseDouble((String)request.getAttribute("hiddenraggio")) %></p>
        <p>valutazione minima cercata:<%=  Integer.parseInt((String)request.getAttribute("hiddenvalutazioneMinima")) %></p>--%>
    </body>
</html>
