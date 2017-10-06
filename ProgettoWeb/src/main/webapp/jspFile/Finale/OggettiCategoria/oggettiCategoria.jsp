<%-- 
    Document   : oggettiCategoria
    Created on : Oct 6, 2017, 3:42:30 PM
    Author     : FBrug
--%>

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
        
        <%
            ModelloOggetto o = new ModelloOggetto();
            o.setId("01");
            String s = o.getId();
        %>
        
        
        
        
        <% out.print(o.getId());%>
        
        
        
        
        
        
        
        
        <h1>Hello World!</h1>
        <div class="container-fluid">
            <%@include file="../Header/NavBar/navBar.jsp" %>
           
            <div style="text-align:center">
                <h1>CATEGORIA</h1>
                <h2>nomeCategoria</h2>
            </div>
            
            
            <!-- LISTA OGGETTI DELLA CATEGORIA -->
            <div class="rowListaOggetto">
                <%@include file="../Components/Liste/ListaOggetto/testListaOggetto.jsp" %>
            </div>

            
            
            
            
            
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>
