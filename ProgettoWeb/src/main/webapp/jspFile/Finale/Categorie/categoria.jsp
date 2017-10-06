<%-- 
    Document   : categoria
    Created on : 30-set-2017, 17.26.16
    Author     : mattia
--%>

<%@page import="it.progettoWeb.java.database.Model.Categoria.ModelloCategoria"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="it.progettoWeb.java.database.Model.Oggetto.ModelloOggetto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title>Oggetti Categoria</title>
    </head>
    <body>
        <jsp:useBean id="listacategoriesessione" class="it.progettoWeb.java.database.Model.Categoria.ModelloListeCategoria" scope="session" />
        <jsp:useBean id="ListaOggetti" class="it.progettoWeb.java.database.Model.Oggetto.ModelloListeOggetto" scope="session" />
        
        <div class="container">
            <%@include file="../Header/NavBar/navBar.jsp" %>
            
            <div class="container-fluid" style="text-align: center">


                <h1>CATEGORIA</h1>
                
                
                <c:forEach items="${listacategoriesessione.getList()}" var="cat">
                    <c:url value="http://localhost:8080/ProgettoWeb/jspFile/Finale/Categorie/categoria.jsp" var="catUrl">
                        <c:param name="id" value="${cat.getId()}" />
                    </c:url>
                    <a class="nav-link" href="${catUrl}"><c:out value="${cat.getNome()}"/></a>
                </c:forEach>
                
                <!--
                <h2><%= request.getParameter("id") %></h2>
                <p><%= request.getParameter("nome") %></p>
                <p><%= request.getParameter("categoria") %></p>
                -->

                <!-- LISTA OGGETTI DELLA CATEGORIA -->
                <%@include file="../Components/Liste/ListaOggetto/testListaOggetto.jsp" %>

            </div>
                
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>