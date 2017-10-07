<%-- 
    Document   : userJsp
    Created on : 07-oct-2017, 16.32.00
    Author     : FBrug
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">-->
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
        <%@include file="../Finale/Header/Head/HomeHead/homeHead.jsp" %>
        <title>Add new user</title>
    </head>
    <body>
        <div class="container">
            <%@include file="../Finale/Header/NavBar/navBar.jsp" %>
            <hr>
        </div>
            
        <div class="container" style="text-align: center">
            <form method="POST" action='<c:out value="${pageContext.request.contextPath}"/>/UserController' name="frmAddUser">
                
                <!--
                <div class="row">
    <div class="col-sm-3" style="background-color:lavender;">.col-sm-4<br/><hr>ciao</div>
    <div class="col-sm-1" style="background-color:lavender;">.col-sm-1</div>
    <div class="col-sm-4" style="background-color:lavenderblush;">.col-sm-4</div>
    <div class="col-sm-4" style="background-color:lavender;">.col-sm-4</div>
  </div>
                -->
                
                
                
                
                
                
                
                
                <div class="container" style="text-align: left">
                    Id (Lasciare vuoto per nuovi utenti):
                        <span style="padding-left: 2em">
                            <input style="padding-left: 2em" type="text" name="userid" value="<c:out value="${user.id}" />"/>
                        </span><hr>
                    Nome:
                        <span style="padding-left: 2em">
                            <input type="text" name="nome" value="<c:out value="${user.nome}" />" />
                        </span><hr>
                    Cognome:
                        <span style="padding-left: 2em">
                            <input type="text" name="cognome" value="<c:out value="${user.cognome}" />" />
                        </span><hr>
                    Email:
                        <span style="padding-left: 2em">
                            <input type="email" name="email" value="<c:out value="${user.mail}" />" />
                        </span><hr>
                    Password:
                        <span style="padding-left: 2em">
                            <input type="password" name="password" value="<c:out value="${user.password}" />" />
                        </span><hr>
                    Tipo Utente:
                        <span style="padding-left: 2em">
                            <input type="text" name="UserType" value="<c:out value="${user.getUtenteType()}" />" />
                        </span><hr>
                        
                    <input type="submit" value="SAVE CHANGES" />
                </div>
            </form>
        </div>
                
        <div class="container">
            <hr>
            <%@include file="../Finale/Footer/footer.jsp" %>
        </div>
    </body>
</html>







<!--
<%-- 
    Document   : userJsp
    Created on : 12-set-2017, 17.11.59
    Author     : mattia
--%>
<-%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<-%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<-%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
        <title>Add new user</title>
    </head>
    <body>
         <form method="POST" action='<-c:out value="${pageContext.request.contextPath}"/>/UserController' name="frmAddUser">
        Id (Lasciare vuoto per nuovi utenti) : <input
            type="text" name="userid"
            value="<-c:out value="${user.id}" />" /> <br /> 
        First Name : <input
            type="text" name="nome"
            value="<-c:out value="${user.nome}" />" /> <br /> 
        Last Name : <input
            type="text" name="cognome"
            value="<-c:out value="${user.cognome}" />" /> <br /> 
        Email : <input type="text" name="email"
            value="<-c:out value="${user.mail}" />" /> <br /> 
        Password: <input type="text" name="password"
            value="<-c:out value="${user.password}" />" /> <br />
        Tipo Utente: <input type="text" name="UserType"
            value="<-c:out value="${user.getUtenteType()}" />" /> <br />
        <input type="submit" value="Submit" />
    </form>
    </body>
</html>
-->