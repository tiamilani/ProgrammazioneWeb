<%-- 
    Document   : paymentMethod
    Created on : Nov 17, 2017, 12:11:26 PM
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
        <title>Metodo di Pagamento</title>
    </head>
    
    <script src="http://localhost:8080/ProgettoWeb/jspFile/Finale/JS/Orders.js"></script>
    
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
            <hr/>
        </div>
        
        <div class="container-fluid">
            <h2><b>METODO DI PAGAMENTO</b></h2>
            
            <hr/>
            
            <div class="row">
                <div class="col-6"><b>RIEPILOGO ORDINE</b></div>
                <div class="col-6"><b>INDIRIZZO DI CONSEGNA</b></div>
            </div>
            <div class="row">
                <div class="col-6">
                    <p>Totale numero articoli: <c:out value="${nArticoli}"/></p>
                    <p>Prezzo totale: EUR <c:out value="${prezzoTot}"/></p>
                </div>
                <div class="col-3">
                    <p>Nome e cognome:</p>
                    <p>Via:</p>
                    <p>N° Civico:</p>
                    <p>Citta':</p>
                    <p>Provincia:</p>
                    <p>Stato:</p>
                </div>
                <div class="3">
                    <p><c:out value="${utenteSessione.getNome()}"/> <c:out value="${utenteSessione.getCognome()}"/></p>
                    <p><c:out value="${address.getVia()}"/></p>
                    <p><c:out value="${address.getnCivico()}"/></p>
                    <p><c:out value="${address.getCitta()}"/></p>
                    <p><c:out value="${address.getProvincia()}"/></p>
                    <p><c:out value="${address.getStato()}"/></p>
                </div>
            </div>
                
            <hr/>
            
            <h4><b>Seleziona il metodo di pagamento:</b></h4>
            <form method="POST" id="formPayment" name="formPayment">
                <div class="row">
                    <div class="col-12">
                        <p>
                            <input type="radio" id="rbtnCard" name="rbtnPay" checked="checked"><b> Carta di credito </b>
                            <img src="http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/creditCarts.png" alt="imgCreditCards">
                        </p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-6">
                        <p>Nome titolare:</p>
                        <p>Cognome titolare:</p>
                        <p>Numero carta:</p>
                        <p>Data di scadenza:</p>
                        <p>Codice di controllo</p>
                    </div>
                    <div class="col-6">
                        <p><input type="tetx" id="nameCard" name="nameTitCard"/></p>
                        <p><input type="tetx" id="surnameCard" name="surnameCard"/></p>
                        <p><input type="tetx" id="numCard" name="numCard" type="tel" maxlength="19" onkeypress="checkInputText(event, this)"/></p>
                        <p>
                            <select id="expCard_Month" name="expCard_Month">
                                <option value="">mm</option>
                                <option value="01">01</option>
                                <option value="02">02</option>
                                <option value="03">03</option>
                                <option value="04">04</option>
                                <option value="05">05</option>
                                <option value="06">06</option>
                                <option value="07">07</option>
                                <option value="08">08</option>
                                <option value="09">09</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                            </select>
                            <select id="expCard_Year" name="expCard_Year">
                                <option value="">yyyy</option>
                                <option value="17">2017</option>
                                <option value="18">2018</option>
                                <option value="19">2019</option>
                                <option value="20">2020</option>
                                <option value="21">2021</option>
                                <option value="22">2022</option>
                                <option value="23">2023</option>
                                <option value="24">2024</option>
                                <option value="25">2025</option>
                                <option value="26">2026</option>
                                <option value="27">2027</option>
                                <option value="28">2028</option>
                                <option value="29">2029</option>
                                <option value="30">2030</option>
                                <option value="31">2031</option>
                            </select>
                        </p>
                        <p><input type="tetx" id="checkCard" name="checkCard" onkeypress="checkInputText(event, this)"/> <img src="http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/cvv.png" alt="imgCVV"></p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <p>
                            <input type="radio" id="rbtnPaypal" name="rbtnPay" checked="checked" type="tel" maxlength="4"><b> PayPal </b>
                            <img src="http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/PayPal.png" alt="imgPayPal">
                        </p>
                    </div>
                </div>
            </form>
            
            <button class="btn btn-outline-primary buttonSpace" type="button" onclick="saveChanges(3)">Paga adesso</button>
        </div>
        
        <%@include file="../Footer/footer.jsp" %>
    </body>
</html>