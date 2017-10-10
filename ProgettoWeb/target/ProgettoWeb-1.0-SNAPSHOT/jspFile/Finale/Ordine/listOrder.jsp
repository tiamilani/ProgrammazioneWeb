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
                <!-- ordine --> <th>idOrdine</th>
                <!-- ordine --> <th>idOggetto</th>
                <!-- ordine --> <th>idNegozio</th>
                <!-- oggetto --> <th>immagineOggetto</th>
                <!-- oggetto --> <th>nomeOggetto</th>
                <!-- oggetto --> <th>prezzoOggetto</th>
                <!-- oggetto --> <th>scontoOggetto</th>
                <!-- ordine --> <th>quantita</th>
                <!-- ordine --> <th>dataOrdine</th> <!-- Data nella quale viene creato l'ordine -->
                <!-- ordine --> <th>dataArrivoPresunta</th>
                <!-- ordine --> <th>prezzoDiAcquisto</th> <!-- Prezzo totale dell'ordine -->
                <!-- ordine --> <th>codiceTracking</th>
                <!--<th colspan=2>Action</th>-->
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${cart}" var="order">
                <!-- SETTA CONDIZIONE PER TROVARE OGGETTO RELATIVO ALL'ORDINE -->
                <c:forEach items="${objects}" var="object">
                    <c:if test="${order.idOggetto eq object.getL().getId()}">
                        <tr>
                            <td><c:out value="${order.idOrdine}" /></td>
                            <td><c:out value="${order.idOggetto}" /></td>
                            <td><c:out value="${order.idNegozio}" /></td>
                            <td><c:out value="${object.getR().getSrc()}" /></td>
                            <td><c:out value="${object.getL().getNome()}" /></td>
                            <td><c:out value="${object.getL().getPrezzo()}" /></td>
                            <td><c:out value="${object.getL().getSconto()}" />%</td>
                            <td><c:out value="${order.quantita}" /></td>
                            <td><c:out value="${order.dataOrdine}" /></td>
                            <td><c:out value="${order.dataArrivoPresunta}" /></td>
                            <td><c:out value="${order.prezzoDiAcquisto}" /></td>
                            <td><c:out value="${order.codiceTracking}" /></td>

                            <!--<td><a href="<c:out value="${pageContext.request.contextPath}"/>/IndirizzoController?action=edit&addrId=<c:out value="${address.getIdI()}"/>&userId=<c:out value="${userId}"/>">Update</a></td>
                            <td><a href="<c:out value="${pageContext.request.contextPath}"/>/IndirizzoController?action=delete&addrId=<c:out value="${address.getIdI()}"/>&userId=${userId}">Delete</a></td>-->
                        </tr>
                    </c:if>
                </c:forEach>
                
                
                
            </c:forEach>
        </tbody>
    </table>
    <!--<p><a href="<c:out value="${pageContext.request.contextPath}"/>/IndirizzoController?action=insert&userId=${userId}">Add Address</a></p>-->
    </body>
</html>