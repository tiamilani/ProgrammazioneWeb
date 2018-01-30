<%--
    Document   : searchResult
    Created on : 5-ott-2017, 16.37.56
    Author     : mattia
--%>

<%@page import="it.progettoWeb.java.database.Model.Oggetto.ModelloListeOggetto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
    <title>ShopEro</title>
        <!-- Latest compiled and minified CSS -->
    <!--link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css"> -->

    <!-- Latest compiled and minified JavaScript -->
    <!--script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script> -->

    <!-- (Optional) Latest compiled and minified JavaScript translation files -->
    <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/i18n/defaults-*.min.js"></script> -->
        <title>Risultati</title>
    </head>
    <body>
        <jsp:useBean id="listaOggetti" class="it.progettoWeb.java.database.Model.Oggetto.ModelloListeOggetto" scope="request" />
        <jsp:useBean id="listaImmaginiOggetto" class="it.progettoWeb.java.database.Model.immagineOggetto.ModelloListeImmagineOggetto" scope="request" />
        <c:set var="searchedCategory" value="${hiddenidCategoria}" scope="page" />
        <%@include file="../Header/NavBar/newNavBar.jsp" %>

        <div class="container-fluid">
            
            <div class="summary">
                <%-- <p>Risultati in per: <%=  request.getParameter("search")%></p>
                <p>Categoria selezionata: <%= request.getParameter("hiddenidCategoria")%></p>
                <p>Nome venditore: <%= request.getParameter("hiddennomeVenditore")%></p>
                <p>Nome negozio: <%= request.getParameter("hiddennomeNegozio")%></p>
                <p>Ritiro in negozio: <%= request.getParameter("hiddencheckRitiroInNegozio")%></p>
                <p>Prodotti scontati: <%= request.getParameter("hiddencheckProdottiScontati")%></p>
                <p>Range di prezzo: <%= request.getParameter("hiddenPriceRange")%></p>
                <p>Valutazione minima: <%= request.getParameter("hiddenvalutazioneMinima")%></p>
                <p>Regione: <%= request.getParameter("hiddenRegione")%></p> --%>
                
                <c:choose>
                    <c:when test="${listaOggetti.getList().size() == 0}">
                        <h3>Siamo spiacenti, la ricerca non ha prodotto alcun risultato</h3>
                    </c:when>
                    <c:when test="${listaOggetti.getList().size() == 1}">
                        <c:choose>
                            <c:when test="${param.search == ''}">
                                <h3>La ricerca ha prodotto <c:out value="${listaOggetti.getList().size()}" /> risultato</h3>
                            </c:when>
                            <c:otherwise>
                                <h3>La ricerca per ${param.search} ha prodotto <c:out value="${listaOggetti.getList().size()}" /> risultato</h3>
                            </c:otherwise>
                        </c:choose>
                    </c:when>
                    <c:otherwise>
                         <c:choose>
                            <c:when test="${param.search == ''}">
                                <h3>La ricerca ha prodotto <c:out value="${listaOggetti.getList().size()}" /> risultati</h3>
                            </c:when>
                            <c:otherwise>
                                <h3>La ricerca per ${param.search} ha prodotto <c:out value="${listaOggetti.getList().size()}" /> risultati</h3>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
                
                <%-- <c:out value="${listaOggetti.getList().size()}" />  --%>
                
                <c:set var="limitColum" value="${4}" scope="page" />
                <%@include file="../Components/Liste/ListaOggetto/testListaOggetto.jsp" %> 
            </div>
        </div>
        <%@include file="../Footer/footer.jsp" %>
    </body>
</html>
