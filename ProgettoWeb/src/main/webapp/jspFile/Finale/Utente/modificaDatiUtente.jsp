<%-- 
    Document   : modificaDatiUtente
    Created on : Oct 7, 2017, 3:06:30 PM
    Author     : FBrug
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
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title>Modifica Dati Utente</title>
    </head>
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/navBar.jsp" %>
        </div>
        
        <div class="container" style="text-align: center">
            <h1>DATI ACCOUNT</h1>
            
            
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
            
            
            <!--
            <div class="container-fluid" style="text-align: center">
                <form action="index.jsp">
                    <div>
                        <i class="large material-icons">person_outline</i>
                        <input class="col-10 modal-input" type="text" id="customerName" required>
                        <label for="customerName">Nome</label>
                    </div>
                    <div>
                        <i class="large material-icons">group</i>
                        <input class="col-10 modal-input" type="text" id="customerSurname" required>
                        <label for="customerSurname">Cognome</label>
                    </div>
                    <div>
                        <i class="large material-icons">email</i>
                        <input class="col-10 modal-input" type="text" id="customerEmail" required>
                        <label for="customerEmail">E-mail</label>
                    </div>
                    <div>
                        <i class="large material-icons">lock_outline</i>
                        <input class="col-10 modal-input" type="password" id="customerPassword" required>
                        <label for="customerPassword">Password</label>
                    </div>
                    <div>
                        <i class="large material-icons">lock_outline</i>
                        <input class="col-10 modal-input" type="password" id="customerConfirmPassword" required>
                        <label for="customerConfirmPassword">Ripeti la password</label>
                    </div>
                        <p>Creando il tuo accont, accetti le nostro condizioni sulla privacy<p>

            </div>-->
        </div>
        
        <div class="container">
            <hr>
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>
