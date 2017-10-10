<%-- 
    Document   : searchResult
    Created on : 5-ott-2017, 16.37.56
    Author     : mattia
--%>

<%@page import="it.progettoWeb.java.database.Model.Oggetto.ModelloListeOggetto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
    <title>ShopEro</title>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>

    <!-- (Optional) Latest compiled and minified JavaScript translation files -->
    <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/i18n/defaults-*.min.js"></script> -->
        <title>Risultati</title>
    </head>
    <body>
        <jsp:useBean id="ListaOggetti" class="it.progettoWeb.java.database.Model.Oggetto.ModelloListeOggetto" scope="request" />
        <%@include file="../Header/NavBar/navBar.jsp" %>
        
        <div class="container-fluid"> 
            
            <div class="summary">
                <p>Risultati in <%=  request.getParameter("hiddenidCategoria")%> per: <%=  request.getParameter("search")%><p>
                <p>Nome venditore:<%=  request.getAttribute("ListaOggetti") %></p>
        
                <%
                    ModelloListeOggetto trovati = (ModelloListeOggetto)request.getAttribute("ListaOggetti");
                    System.out.println(trovati.getList().size());
                %>

                <%@include file="../Components/Liste/ListaOggetto/testListaOggetto.jsp" %>
                <%@include file="../Footer/footer.jsp" %>
            </div>
        </div>
    </body>
</html>
