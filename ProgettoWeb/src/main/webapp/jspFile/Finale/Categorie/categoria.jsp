<%-- 
    Document   : categoria
    Created on : 30-set-2017, 17.26.16
    Author     : mattia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="listacategoriesessione" class="it.progettoWeb.java.database.Model.Categoria.ModelloListeCategoria" scope="session" />
        <h1>CIAO</h1>
        <p>Io sono qui per mostrare le informazioni e gli oggetti della categoria: <%=  request.getParameter("id") %></p>
        
    </body>
</html>
