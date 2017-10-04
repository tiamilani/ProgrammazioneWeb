<%-- 
    Document   : homePage
    Created on : 30-set-2017, 9.54.06
    Author     : mattia
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title>ShopEro</title>
    </head>
    
    <body>
        <jsp:useBean id="listacategoriesessione" class="it.progettoWeb.java.database.Model.Categoria.ModelloListeCategoria" scope="session" />
        <jsp:useBean id="LsitaOggetti" class="it.progettoWeb.java.database.Model.Oggetto.ModelloListeOggetto" scope="session" />
        
        <div class="container-fluid">
            <%@include file="../Header/NavBar/navBar.jsp" %>
            <%@include file="../Components/Carosello/slideShow.jsp" %>
            <%@include file="../Components/Liste/ListaOggetto/testListaOggetto.jsp" %>
            <hr>
            <%@include file="../Footer/footer.jsp" %>
            <%--<a href="test.jsp">test</a>--%>
        </div>
    </body>
</html>
