<%-- 
    Document   : homePage
    Created on : 30-set-2017, 9.54.06
    Author     : mattia
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<html>
    <jsp:useBean id="listacategoriesessione" class="it.progettoWeb.java.database.Model.Categoria.ModelloListeCategoria" scope="session" />
    <jsp:useBean id="utente" class="it.progettoWeb.java.database.Model.Utente.ModelloUtente" scope="session" />
    <jsp:useBean id="listaImmaginiOggetto" class="it.progettoWeb.java.database.Model.immagineOggetto.ModelloListeImmagineOggetto" scope="request" />
    <head>
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title>ShopEro</title>
    </head>
    
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/navBar.jsp" %>
        </div>
        
        <div class="container-fluid">
            <%@include file="../Components/Carosello/slideShow.jsp" %>
            <div class="row rowListaOggetto">
                <h2>Oggetti che potrebbero piacerti</h2>
            </div>
            <%--<img src="${listaImmaginiOggetto.get(0).getSrc()}" alt="NO IMMAGINE" />--%>
            <c:set var="limitColum" value="${4}" scope="page" />
            <%@include file="../Components/Liste/ListaOggetto/testListaOggetto.jsp" %>
        </div>
        
        <div class="container">
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>