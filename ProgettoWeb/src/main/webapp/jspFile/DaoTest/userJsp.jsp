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
        <title>Add new user</title>
    </head>
    <body>
         <form method="POST" action='<c:out value="${pageContext.request.contextPath}"/>/UserController' name="frmAddUser">
        Id (Lasciare vuoto per nuovi utenti) : <input
            type="text" name="userid"
            value="<c:out value="${user.id}" />" /> <br /> 
        First Name : <input
            type="text" name="nome"
            value="<c:out value="${user.nome}" />" /> <br /> 
        Last Name : <input
            type="text" name="cognome"
            value="<c:out value="${user.cognome}" />" /> <br /> 
        Email : <input type="text" name="email"
            value="<c:out value="${user.mail}" />" /> <br /> 
        Password: <input type="text" name="password"
            value="<c:out value="${user.password}" />" /> <br />
        Tipo Utente: <input type="text" name="UserType"
            value="<c:out value="${user.getUtenteType()}" />" /> <br />
        <input type="submit" value="Submit" />
    </form>
    </body>
</html>
