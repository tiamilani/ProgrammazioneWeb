<%-- 
    Document   : listUser
    Created on : 12-set-2017, 16.57.43
    Author     : mattia
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border=1>
        <thead>
            <tr>
                <th>Id</th>
                <th>idOggetto</th>
                <th>idUtente</th>
                <th>testo</th>
                <th>valutazione</th>
                <th>data</th>
                <th>utilita</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${recensioni}" var="recensione">
                <tr>
                    <td><c:out value="${recensione.id}" /></td>
                    <td><c:out value="${recensione.idOggetto}" /></td>
                    <td><c:out value="${recensione.idUtente}" /></td>
                    <td><c:out value="${recensione.testo}" /></td>
                    <td><c:out value="${recensione.valutazione}" /></td>
                    <td><c:out value="${recensione.data}" /></td>
                    <td><c:out value="${recensione.utilita}" /></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    </body>
</html>
