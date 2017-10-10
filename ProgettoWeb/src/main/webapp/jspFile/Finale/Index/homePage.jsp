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
        <c:set value="3" var="userToCheck"/>
        <br/><br/><br/><br/><br/>
        <a href="/ProgettoWeb/OrdineController?userId=<c:out value="${userToCheck}"/>">ordine utente <c:out value="${userToCheck}"/></a>
        
        
        <jsp:useBean id="listacategoriesessione" class="it.progettoWeb.java.database.Model.Categoria.ModelloListeCategoria" scope="session" />
        <%--<jsp:useBean id="ListaOggetti" class="it.progettoWeb.java.database.Model.Oggetto.ModelloListeOggetto" scope="request" />--%>
        <jsp:useBean id="utente" class="it.progettoWeb.java.database.Model.Utente.ModelloUtente" scope="session" />
        
        <div class="container">
            <%@include file="../Header/NavBar/navBar.jsp" %>
        </div>
        <div class="container-fluid">
            <%@include file="../Components/Carosello/slideShow.jsp" %>
            <div class="row rowListaOggetto">
                <h2>Oggetti che potrebbero piacerti</h2>
            </div>
            <c:set var ="iterator" scope="page" value="${0}"/>
            <%@include file="../Components/Liste/ListaOggetto/testListaOggetto.jsp" %>
            <hr>
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>