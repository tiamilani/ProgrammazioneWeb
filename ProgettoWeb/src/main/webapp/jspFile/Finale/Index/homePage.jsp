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
    <%--<jsp:useBean id="utenteSessione" class="it.progettoWeb.java.database.Model.Utente.ModelloUtente" scope="session" />--%>
    <jsp:useBean id="listaImmaginiOggetto" class="it.progettoWeb.java.database.Model.immagineOggetto.ModelloListeImmagineOggetto" scope="request" />
    <head>
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title>ShopEro</title>
    </head>
    
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
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
        
        <!--- 2017-11-08 --->
        <script type="text/javascript">
            var nibirumail_advice_text = 'Questo sito usa i cookie per migliorare i servizi e analizzare il traffico. Navigando all\'interno del sito accetti l\'utilizzo dei cookie.\n\
                Maggiori informazioni in Privacy Policy.\n\
                <button type="button" onclick="location.href=\'http://localhost:8080/ProgettoWeb/jspFile/Finale/Policy/Privacy/privacyPolicy.jsp\'">Privacy Policy</button>\n\
                <button type="button" href="javascript:;" class="nibirumail_agreement">ACCETTO</button>';
                </script>
        <script type="text/javascript" src="https://nibirumail.com/docs/scripts/nibirumail.cookie.min.js"></script>
        
        <!--
        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    
                    <a class="btn__close_c" href="javascript:r$('cookies').fadeOut('slow');"><img src="//d1qsjop7tycsfb.cloudfront.net/fileadmin/Img/cookies/btn_close_cookies.png"></a>
                    <div class="text__cont">
                        <p>Questo sito usa cookie, anche di terzi, per migliorare l’esperienza di navigazione e adeguare la pubblicità alle tue preferenze. Chiudendo questo banner o proseguendo nella navigazione acconsenti all’utilizzo di cookie.
                            <a href="/banner-cookie/" class="btn">
                                Informazioni e gestione consenso cookie.
                            </a>
                        </p>
                    </div>
                </div>
                -->
    </body>
</html>