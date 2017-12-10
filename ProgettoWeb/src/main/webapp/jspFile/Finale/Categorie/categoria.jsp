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
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title>${categoria.getNome()}</title>
    </head>
    
    <body>
        <!-- Bean per la categoria e la lista oggettiCategoria -->
        <jsp:useBean id="categoria" class="it.progettoWeb.java.database.Model.Categoria.ModelloCategoria" scope="request" />
        <jsp:useBean id="ListaOggetti" class="it.progettoWeb.java.database.Model.Oggetto.ModelloListeOggetto" scope="request" />
        
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
        </div>
        
        <div class="container-fluid" style="margin-top: 1rem;">
            <!-- Stampo nome e descrizione categoria selezionata -->
            <h1>${categoria.getNome()}</h1>
            <p><c:out value="${categoria.getDescrizione()}"/></p>  
            <%@include file="../Components/Liste/ListaOggetto/testListaOggetto.jsp" %>
        </div>
        
        <div class="container">
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>