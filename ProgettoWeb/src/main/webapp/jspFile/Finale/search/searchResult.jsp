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
    </body>
</html>
