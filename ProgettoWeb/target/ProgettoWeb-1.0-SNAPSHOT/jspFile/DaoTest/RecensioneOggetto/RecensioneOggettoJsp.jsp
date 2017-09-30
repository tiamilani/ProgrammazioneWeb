<%-- 
    Document   : userJsp
    Created on : 12-set-2017, 17.11.59
    Author     : mattia
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
        <title>Add new recensione</title>
    </head>
    <body>
         <form method="POST" action='<c:out value="${pageContext.request.contextPath}"/>/RecensioneOggettoController' name="frmAddRecensione">
        Id (Lasciare vuoto per nuove recensioni) : <input
            type="text" name="id"
            value="<c:out value="${recensione.id}" />" /> <br /> 
        Id Oggetto : <input
            type="text" name="idOggetto"
            value="<c:out value="${recensione.idOggetto}" />" /> <br /> 
        Id Utente : <input
            type="text" name="idUtente"
            value="<c:out value="${recensione.idUtente}" />" /> <br /> 
        Testo recensione : <input type="text" name="testo"
            value="<c:out value="${recensione.testo}" />" /> <br /> 
        valutazione: <input type="text" name="valutazione"
            value="<c:out value="${recensione.valutazione}" />" /> <br />
        Data di acquisto: <input type="text" name="data"
            value="<c:out value="${recensione.data}" />" /> <br />
        Utilità: <input type="text" name="utilita"
            value="<c:out value="${recensione.utilita}" />" /> <br />
        <input type="submit" value="Submit" />
    </form>
    </body>
</html>
