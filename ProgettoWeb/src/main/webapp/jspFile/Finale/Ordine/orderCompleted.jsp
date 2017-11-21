<%-- 
    Document   : orderCompleted
    Created on : Nov 20, 2017, 10:40:34 AM
    Author     : FBrug
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title>Ordine Completato</title>
    </head>
    
    <script src="http://localhost:8080/ProgettoWeb/jspFile/Finale/JS/Orders.js"></script>
    
    <body>
        <meta http-equiv="refresh" content="10;URL=${pageContext.request.contextPath}/jspFile/Finale/Index/index.jsp">
        <div class="container">
            <%@include file="../Header/NavBar/navBar.jsp" %>
            <hr/>
        </div>
        
        <div class="container-fluid">
            <h2><b>Congratulazioni! Hai completato il tuo ordine!</b></h2>
            <p>Il tuo ordine è stato completato con successo. A breve riceverai un mail di conferma 
            con i dati del tuo ordine.</p>
            
            <p>Attendi, tra poco sarai reindirizzato alla pagina principale...</p>
        </div>
            
        <div class="container">
            <hr>
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>
