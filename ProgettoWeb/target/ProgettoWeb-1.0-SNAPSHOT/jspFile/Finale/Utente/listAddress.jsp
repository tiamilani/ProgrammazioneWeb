<%-- 
    Document   : listAddress
    Created on : Oct 8, 2017, 4:34:12 PM
    Author     : FBrug
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Indirizzi</title>
    </head>
    <body>
        <table border=1>
        <thead>
            <tr>
                <th>Id</th>
                <th>Stato</th>
                <th>Regione</th>
                <th>Provincia</th>
                <th>Citta'</th>
                <th>Via</th>
                <th>nCivico</th>
                <th>Interno</th>
                <th>Latitudine</th>
                <th>Longitudine</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${address}" var="address">
                <tr>
                    <td><c:out value="${address.idI}" /></td>
                    <td><c:out value="${address.stato}" /></td>
                    <td><c:out value="${address.regione}" /></td>
                    <td><c:out value="${address.provincia}" /></td>
                    <td><c:out value="${address.citta}" /></td>
                    <td><c:out value="${address.via}" /></td>
                    <td><c:out value="${address.nCivico}" /></td>
                    <td><c:out value="${address.interno}" /></td>
                    <td><c:out value="${address.latitudine}" /></td>
                    <td><c:out value="${address.longitudine}" /></td>

                    <td><a href="<c:out value="${pageContext.request.contextPath}"/>/IndirizzoController?action=edit&addrId=<c:out value="${address.getIdI()}"/>&userId=<c:out value="${userId}"/>">Update</a></td>
                    <td><a href="<c:out value="${pageContext.request.contextPath}"/>/IndirizzoController?action=delete&addrId=<c:out value="${address.getIdI()}"/>&userId=${userId}">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="<c:out value="${pageContext.request.contextPath}"/>/IndirizzoController?action=insert&userId=${userId}">Add Address</a></p>
    </body>
</html>
