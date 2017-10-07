<%-- 
    Document   : sviluppatori.jsp
    Created on : 4-ott-2017, 15.32.58
    Author     : mattia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title>ShopEro Developer</title>
        <link rel="stylesheet" href="sviluppatori.css"
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="../Header/NavBar/navBar.jsp" %>
            <hr>
            <div class="row">
                <div class="col-12 description">
                    <h1>Ciao</h1>
                    <p>
                        Siamo un gruppo di studenti di Informatica all'Università degli Studi di Trento.<br>
                        Benvenuto nel nostro nuovo sito di e-commerce
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col-6">
                    <div class="card">
                        <img src="../Img/Andrea_Fadi.jpg" alt="Andrea Fadi">
                    </div>
                </div>
                <div class="col-6">
                    <h4> Andrea Fadi </h4>
                    <h6> Full Stack Developer e Web Designer</h6>
                </div>
            </div>
        </div>
                 
        
            <%@include file="../Footer/footer.jsp" %>
    </body>
</html>
