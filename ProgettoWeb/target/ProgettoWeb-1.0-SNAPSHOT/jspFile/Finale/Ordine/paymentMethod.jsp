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
            <%@include file="../Header/NavBar/navBar.jsp" %>
            <hr/>
        </div>
        
        <div class="container-fluid">
            <h2><b>METODO DI PAGAMENTO</b></h2>
            
            <!-- TRY 1
            <table>
                <tr><td><b>Riepilogo ordine</b></td></tr>
                <tr><td>Totale numero articoli: <c:out value="${carrelloSessione.getSize()}"/></tr>
                <tr><td>Prezzo totale: </td></tr>
                
                <tr><td colspan="9"><hr size="3" width="100%" align="left"/></td></tr>
                
                <!-- Seleziona il metodo di pagamento (carta - PayPal) --|>
                <tr><td><b>Seleziona il metodo di pagamento:</b></td></tr>
                <tr><td>
                <table>
                    <tr><td><input type="radio" id="rbtnCard" name="rbtnCard" checked="checked"></td></tr>
                    <tr><td>
                        <form method="POST" id="formCreditCard" name="formCreditCard">
                            <span><b>Dati della carta</b></span>
                            <span>Nome titolare</span><input type="tetx" id="nameTitCarta" name="nameTitCarta"/>
                            <span>Cognome titolare</span><input type="tetx" id="surnameTitCarta" name="surnameTitCarta"/>
                            <span>Numero carta</span><input type="tetx" id="numCarta" name="numCarta"/>
                            <span>Scadenza carta</span><input type="tetx" id="expCarta" name="expCarta"/>
                            <span>Codice di controllo</span><input type="tetx" id="checkCarta" name="checkCarta"/>
                        </form>
                    </td></tr>
                    <tr><td><input type="radio" id="rbtnPaypal" name="rbtnPaypal" value="PayPal"></td></tr>
                </table></td></tr>
            </table>-->
            
            <span><b>Riepilogo ordine</b></span>
            <div class="row">
                <div class="col-3">
                    <span>Totale numero articoli: <c:out value="${carrelloSessione.getSize()}"/></span>
                    <span>Prezzo totale: </span>
                </div>
                <div class="col-3">
                    <span>Indirizzo di consegna:</span>
                    Nome e cognome: <c:out value="${utenteSessione.getNome()}"/> <c:out value="${utenteSessione.getCognome()}"/>
                    Via: <c:out value="${address.getVia()}"/>
                    N° Civico: <c:out value="${address.getnCivico()}"/>
                    Citta': <c:out value="${address.getCitta()}"/>
                    Provincia: <c:out value="${address.getProvincia()}"/>
                    Stato: <c:out value="${address.getStato()}"/>
                </div>
            </div>
            <hr/>
            <span><b>Seleziona il metodo di pagamento:</b></span><br>
            <input type="radio" id="rbtnCard" name="rbtnPay" checked="checked"><b>Carta di credito</b><br>
            <form method="POST" id="formCreditCard" name="formCreditCard">
                <p><b>Dati della carta</b></p>
                <p>Nome titolare<input type="tetx" id="nameTitCarta" name="nameTitCarta"/></p>
                <p>Cognome titolare<input type="tetx" id="surnameTitCarta" name="surnameTitCarta"/></p>
                <p>Numero carta<input type="tetx" id="numCarta" name="numCarta"/></p>
                <p>Scadenza carta<input type="tetx" id="expCarta" name="expCarta"/></p>
                <p>Codice di controllo<input type="tetx" id="checkCarta" name="checkCarta"/></p>
            </form>
            <input type="radio" id="rbtnPaypal" name="rbtnPay" value="PayPal"><b>PayPal</b><br>
            
            <button class="btn btn-outline-primary buttonSpace" type="button" onclick="saveChanges(3)">Paga adesso</button>
        </div>
        
        <div class="container">
            <hr>
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>


<!--
### Pagina di pagamento
- la pagina di pagamento dovrà riepilogare ciò che l'utente ha slezionato (ora non più modificabile)
    ed il subtotale
- una seconda parte della pagina dovrà mostrare le opzioni per il pagamento (num carta ecc ecc)
- usa form (guarda pdf spiegazione)
- pulsante paga che porterà ad una pagina con scritto 
    "Ordine effettuato, potrai controllare l'ordine dalla pagina di gestione degli ordini nel tuo profilo"
- La gestione dell’acquisto, attraverso pagina fittizia di pagamento con carta di credito 
    oppure ritiro diretto presso il negozio, per i soli oggetti con tale possibilitàa’ di fruizione
-->