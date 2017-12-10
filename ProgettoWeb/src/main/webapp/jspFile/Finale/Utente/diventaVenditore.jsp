<%-- 
    Document   : diventaVenditore
    Created on : Oct 20, 2017, 4:48:08 PM
    Author     : FBrug
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title>Diventa Venditore</title>
    </head>
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
            <hr/>
        </div>
            
        <div class="container-fluid" style="text-align: center">
            <h2><b>Diventa Venditore</b></h2>
            <hr/>
            
            <div class="card-group">
                <div class="card cardSmall" style="box-shadow: none; align-items: center">
                    <img class="imgCard" src="https://images-na.ssl-images-amazon.com/images/G/30/amazonservices/2015/assets/images/icons/benefits/svg/easier.svg" alt="IMAGE NOT LOADED" style="width: 50%; height: 50%;">
                    <div class="container"><p>I tuoi prodotti saranno più facili da trovare e acquistare</p></div>
                </div>
                <div class="card cardSmall" style="box-shadow: none; align-items: center">
                    <img class="imgCard" src="https://images-na.ssl-images-amazon.com/images/G/30/amazonservices/2015/assets/images/icons/benefits/svg/millions.svg" alt="IMAGE NOT LOADED" style="width: 50%; height: 50%;">
                    <div class="container"><p>Il tuo marchio e i tuoi negozi saranno visibili a milioni di clienti</p></div>
                </div>
                <div class="card cardSmall" style="box-shadow: none; align-items: center">
                    <img class="imgCard" src="https://images-na.ssl-images-amazon.com/images/G/30/amazonservices/2015/assets/images/icons/benefits/svg/all5.svg" alt="IMAGE NOT LOADED" style="width: 50%; height: 50%;">
                    <div class="container"><p>Potrai aggiungere i tuoi negozi e i tuoi prodotti all'interno della tua pagina utente</p></div>
                </div>
            </div>
            <div class="card-group">
                <div class="card cardSmall" style="box-shadow: none; align-items: center">
                    <img class="imgCard" src="https://images-na.ssl-images-amazon.com/images/G/30/amazonservices/2015/assets/images/icons/benefits/svg/categories.svg" alt="IMAGE NOT LOADED" style="width: 50%; height: 50%;">
                    <div class="container"><p>Potrai vendere i tuoi prodotti all'interno di 30 categorie diverse</p></div>
                </div>
                <div class="card cardSmall" style="box-shadow: none; align-items: center">
                    <img class="imgCard" src="https://images-na.ssl-images-amazon.com/images/G/30/amazonservices/2015/assets/images/icons/benefits/svg/eligible.svg" alt="IMAGE NOT LOADED" style="width: 50%; height: 50%;">
                    <div class="container"><p>Ovviamente potrai continuare ad acquistare prodotti come privato</p></div>
                </div>
                <div class="card cardSmall" style="box-shadow: none; align-items: center">
                    <img class="imgCard" src="https://images-na.ssl-images-amazon.com/images/G/30/amazonservices/2015/assets/images/icons/benefits/svg/trusted.svg" alt="IMAGE NOT LOADED" style="width: 50%; height: 50%;">
                    <div class="container"><p>Questo e molto altro ti aspetta se deciderai di diventare venditore in ShopEro</p></div>
                </div>
            </div>
            
            <hr/>
            <div class="container">
                <button type="button" class="btn btn-outline-primary buttonSpace" data-toggle="modal" data-target="#becomeSellerConfirm">
                    <i class="Small material-icons">attach_money</i><b> DIVENTA VENDITORE</b>
                </button>
            </div>
        </div>
            
        <div class="container">
            <hr>
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>


<div id="becomeSellerConfirm" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header">
              <span class="modal-title"><b>Confermi di voler diventare venditore?</b></span>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
          </div>
            <form action="<c:out value="${pageContext.request.contextPath}"/>/UserController?action=becomeSeller" method="POST">
            
            <div class="modal-footer">
              <button type="submit" class="col-2 paddingNav btn btn-outline-primary my-2 my-sm-0">SI</button>
            </div>
          </form>
        </div>

    </div>
</div>