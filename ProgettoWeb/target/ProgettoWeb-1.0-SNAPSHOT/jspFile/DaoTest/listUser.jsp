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
                <th>Nome</th>
                <th>Cognome</th>
                <th>Email</th>
                <th>Passowrd</th>
                <th>Avatar</th>
                <th>Valutazione</th>
                <th>UtenteType</th>
                <th>EmailConfermata</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.id}" /></td>
                    <td><c:out value="${user.nome}" /></td>
                    <td><c:out value="${user.cognome}" /></td>
                    <td><c:out value="${user.mail}" /></td>
                    <td><c:out value="${user.password}" /></td>
                    <td><c:out value="${user.avatar}" /></td>
                    <td><c:out value="${user.valutazione}" /></td>
                    <td><c:out value="${user.getUtenteType()}" /></td>
                    <td><c:out value="${user.emailConfermata}" /></td>

                    <td><a href="<c:out value="${pageContext.request.contextPath}"/>/UserController?action=edit&userId=<c:out value="${user.getId()}"/>">Update</a></td>
                    <td><a href="<c:out value="${pageContext.request.contextPath}"/>/UserController?action=delete&userId=<c:out value="${user.getId()}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="<c:out value="${pageContext.request.contextPath}"/>/UserController?action=insert">Add User</a></p>
    </body>
</html>
