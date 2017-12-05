<%-- 
    Document   : ottieniListaCategorie
    Created on : 28-set-2017, 18.24.51
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
<p>ciao</p>
<br>
<c:forEach items="${categorie}" var="categoria">
    <p>ciao</p><br>
    <p><c:out value="${categoria.getNome()}" /></p><br>
</c:forEach>
</body>
</html>