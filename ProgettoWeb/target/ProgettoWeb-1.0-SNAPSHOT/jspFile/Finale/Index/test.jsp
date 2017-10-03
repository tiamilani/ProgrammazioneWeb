<%-- 
    Document   : test.jsp
    Created on : 28-set-2017, 18.32.47
    Author     : mattia
--%>
<<<<<<< HEAD
<%@page import="it.progettoWeb.java.database.Model.Categoria.ModelloListeCategoria"%>
<%@page import="java.util.List"%>
=======
>>>>>>> master
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
<<<<<<< HEAD
        <title>Pagina di test</title>
    </head>
    <body>
        
        <jsp:useBean id="listacategoriesessione" class="it.progettoWeb.java.database.Model.Categoria.ModelloListeCategoria" scope="session" />
        <h4>Categorie</h4>
        <c:forEach items="${listacategoriesessione.getList()}" var="cat">
            <a class="nav-link" href="#"><c:out value="${cat.getNome()}"/></a>
        </c:forEach>
    </body>
</html>
=======
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <jsp:forward page="/CategoriaController?action=listCategory" />
    </body>
</html>
>>>>>>> master
