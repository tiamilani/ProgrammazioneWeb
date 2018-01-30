<%-- 
    Document   : homePage
    Created on : 30-set-2017, 9.54.06
    Author     : mattia
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<html>
    <head>
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title>ShopHero</title>
    </head>
    
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
        </div>
        
        <div>
            <h1>
                Recupero Password
            </h1>
            <p>
                Gentile utente, abbiamo ricevuto la tua richiesta per il recupero della password.
                Inserisci la password nel form sottostante!
            </p>
            <center>
                
            <form action="${pageContext.request.contextPath}/PasswordReset?action=confirm" method="POST" id="resetForm">
                <div>
                    <i class="large material-icons">email</i>
                    <input class="col-8 change-input" type="email" value="${resetEmail}" name="changed-email" required readonly="readonly">
                </div>
                <div>
                    <i class="large material-icons">lock_outline</i>
                    <input class="col-8 change-input" type="password" id="customerPassword" name="changed-password" required placeholder="Inserire la password">
                </div>
                <div>
                    <i class="large material-icons">lock_outline</i>
                    <input class="col-8 change-input" type="password" id="customerConfirmPassword" name="changed-confirmPassword" required placeholder="Ripetere la password">
                </div>
                    
                <div class="reset-footer">
                  <button type="submit" class="col-10 btn btn-outline-primary my-2 my-sm-0">Modifica password</button>
                </div>
            </form>
                    
            </center>
        </div>
        
        <div class="container">
            <%@include file="../Footer/footer.jsp" %>
        </div>
        
        <!--- 2017-11-08 --->
        <script type="text/javascript">
            var nibirumail_advice_text = 'Questo sito usa i cookie per migliorare i servizi e analizzare il traffico. Navigando all\'interno del sito accetti l\'utilizzo dei cookie.\n\
                Maggiori informazioni in Privacy Policy.\n\
                <button type="button" onclick="location.href=\'http://localhost:8080/ProgettoWeb/jspFile/Finale/Policy/Privacy/privacyPolicy.jsp\'">Privacy Policy</button>\n\
                <button type="button" href="javascript:;" class="nibirumail_agreement">ACCETTO</button>';
                </script>
        <script type="text/javascript" src="https://nibirumail.com/docs/scripts/nibirumail.cookie.min.js"></script>
    </body>
</html>