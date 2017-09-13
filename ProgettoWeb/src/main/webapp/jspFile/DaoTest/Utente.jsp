<%-- 
    Document   : Utente.jsp
    Created on : 12-set-2017, 16.55.39
    Author     : mattia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:forward page="/UserController?action=listUser" />
    </body>
</html>
