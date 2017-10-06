<%-- 
    Document   : categoria
    Created on : 06-oct-2017, 9.54.06
    Author     : fbrug
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!--<d%@include file="/ProgettoWeb/jspFile/Finale/Header/Head/HomeHead/homeHead.jsp" %>-->
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title>Oggetti Categoria</title>
    </head>
    
    <body>
        <!-- Bean per la categoria e la lista oggettiCategoria -->
        <jsp:useBean id="categoria" class="it.progettoWeb.java.database.Model.Categoria.ModelloCategoria" scope="request" />
        <jsp:useBean id="ListaOggetti" class="it.progettoWeb.java.database.Model.Oggetto.ModelloListeOggetto" scope="request" />
        
        <div class="container">
            <%@include file="../Header/NavBar/navBar.jsp" %>
        </div>
        <div class="container-fluid" style="text-align: center">
            <h1>CATEGORIA</h1>

            <!-- Stampo nome e descrizione categoria selezionata -->
            <h2><c:out value="${categoria.getNome()}"/></h2>
            <p><c:out value="${categoria.getDescrizione()}"/></p>
            
            <%@include file="../Components/Liste/ListaOggetto/testListaOggetto.jsp" %>
            <hr>
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>