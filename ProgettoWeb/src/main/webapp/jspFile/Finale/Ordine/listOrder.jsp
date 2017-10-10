<%-- 
    Document   : listOrder
    Created on : Oct 10, 2017, 3:03:37 PM
    Author     : FBrug
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Carrello</title>
    </head>
    <body>
        <table border=1>
        <thead>
            <!--
                STATO DELL'ORDINE:
                    (0 = nel carrello, 1 = pagato, 2 = in lavorazione, 3 = spedito, 4 = consegnato, 5 = nella lista desideri)
            -->
            <tr>
                <th>idOrdine</th>
                <th>idOggetto</th>
                <th>idNegozio</th>
                <th>immagineOggetto</th>
                <th>nomeOggetto</th>
                <th>prezzoOggetto</th>
                <th>quantita</th>
                <th>dataOrdine</th> <!-- Data nella quale viene creato l'ordine -->
                <th>dataArrivoPrevisto</th>
                <th>prezzoAcquisto</th> <!-- Prezzo totale dell'ordine -->
                <!--<th colspan=2>Action</th>-->
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${cart}" var="order">
                <tr>
                    <td><c:out value="${order.idO}" /></td>
                    <td><c:out value="${order.idO}" /></td>
                    <td><c:out value="${order.stato}" /></td>
                    <td><c:out value="${order.regione}" /></td>
                    <td><c:out value="${order.provincia}" /></td>
                    <td><c:out value="${order.citta}" /></td>
                    <td><c:out value="${order.via}" /></td>
                    <td><c:out value="${order.nCivico}" /></td>
                    <td><c:out value="${order.interno}" /></td>
                    <td><c:out value="${order.latitudine}" /></td>
                    <td><c:out value="${order.longitudine}" /></td>

                    <!--<td><a href="<c:out value="${pageContext.request.contextPath}"/>/IndirizzoController?action=edit&addrId=<c:out value="${address.getIdI()}"/>&userId=<c:out value="${userId}"/>">Update</a></td>
                    <td><a href="<c:out value="${pageContext.request.contextPath}"/>/IndirizzoController?action=delete&addrId=<c:out value="${address.getIdI()}"/>&userId=${userId}">Delete</a></td>-->
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="<c:out value="${pageContext.request.contextPath}"/>/IndirizzoController?action=insert&userId=${userId}">Add Address</a></p>
    </body>
</html>