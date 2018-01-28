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
        <jsp:useBean id="listaOggetti" class="it.progettoWeb.java.database.Model.Oggetto.ModelloListeOggetto" scope="request" />
        <jsp:useBean id="listaImmaginiOggetto" class="it.progettoWeb.java.database.Model.immagineOggetto.ModelloListeImmagineOggetto" scope="request" />
        
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
        </div>
        
        <div class="container-fluid" style="margin-top: 1rem;">
            <!-- Stampo nome e descrizione categoria selezionata -->
            
            <c:choose>
                <c:when test="${listaOggetti.getList().size() == 0}">
                    <h3>Siamo spiacenti, la ricerca non ha prodotto alcun risultato</h3>
                </c:when>
                <c:when test="${listaOggetti.getList().size() == 1}">
                    <h3><c:out value="${listaOggetti.getList().size()}" /> risultato per la ricerca nella categoria ${categoria.getNome()}</h3>
                    <p><c:out value="${categoria.getDescrizione()}"/></p>
                </c:when>
                <c:otherwise>
                    <h3><c:out value="${listaOggetti.getList().size()}" /> risultati per la ricerca nella categoria ${categoria.getNome()}</h3>
                    <p><c:out value="${categoria.getDescrizione()}"/></p>
                </c:otherwise>
            </c:choose>
            
            
            
            <c:set var="limitColum" value="${4}" scope="page" />
            <%@include file="../Components/Liste/ListaOggetto/testListaOggetto.jsp" %>
        </div>
        
        <%@include file="../Footer/footer.jsp" %>
    </body>
</html>