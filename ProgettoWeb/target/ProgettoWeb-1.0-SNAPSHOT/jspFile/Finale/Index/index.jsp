<%--
    Document   : index
    Created on : 26-set-2017, 21.05.16
    Author     : mattia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
        <link rel='stylesheet' href="http://getbootstrap.com/docs/4.0/examples/carousel/carousel.css">
		<link rel="stylesheet" href="../CSS/navBar.css">
                <link rel="stylesheet" href="../CSS/slideShow.css">
                <link rel="stylesheet" href="../CSS/oggetto.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container-fluid">
        <%@include file="../Header/NavBar/navBar.jsp" %>
        <%@include file="../Components/Carosello/slideShow.jsp" %>
        <%@include file="../Components/Liste/ListaOggetto/testListaOggetto.jsp" %>
        <a href="test.jsp">test</a>
        </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>

    </body>
</html>
