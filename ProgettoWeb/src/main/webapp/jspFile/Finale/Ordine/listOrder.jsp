<%-- 
    Document   : listOrder
    Created on : Oct 10, 2017, 3:03:37 PM
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
        <title>Carrello</title>
    </head>
    <body>
        <jsp:useBean id="cart" class="it.progettoWeb.java.database.Model.Ordine.ModelloListeOrdine" scope="session" />
        
        <c:set var="iterator" value="0"/>
        <c:set var="prezzoTot" value="0"/>
        <c:set var="nOggetti" value="0"/>
        <c:set var="userId_request" value="${userId_request}"/>
        <fmt:setLocale value = "it_IT"/>
        
        
        <div style="display: none">
            <form method="POST" id="formSaveChanges" name="formSaveChanges">
                <input type="text" id="toDo" name="toDo" value="saveChanges"/>
                <input type="text" id="idUtente" name="idUtente" value="${userId_request}"/>
                <input type="text" id="nOrders" name="nOrders" value="${cart.size()}"/>
                
                <c:forEach items="${cart}" var="order">
                    <input type="tetx" id="idOrdine${iterator}" name="idOrdine${iterator}" value="${order.idOrdine}"/>
                    <input type="tetx" id="idOggetto${iterator}" name="idOggetto${iterator}" value="${order.idOggetto}"/>
                    <input type="tetx" id="quantita${iterator}" name="quantita${iterator}" value="${order.getQuantita()}"/>
                           
                    <c:set var="iterator" value="${iterator + 1}"/>
                </c:forEach>
            </form>
        </div>

        
        <script>
            window.onbeforeunload = function(event)
            {
                //NON VA CON IL REFRESH DELLA PAGINA!!!
                saveChanges();
                //event.returnValue = "Write something clever here..";
                
                //evemt.returnValue = true;
                //event.returnValue = true;
                //return true
            };
            
            
            
            function saveChanges()
            {
                document.formSaveChanges.action="${pageContext.request.contextPath}/OrdineController";
                document.formSaveChanges.submit();
            };
        </script>
        
        
        
        <div class="container">
            <%@include file="../Header/NavBar/navBar.jsp" %>
            <hr/>
        </div>
        
        <div class="container-fluid">
            
            <c:set var="iterator" value="0"/>
            
            <h2><b>CARRELLO</b></h2>
            <table width="100%" cellpadding="5">
                <c:forEach items="${cart}" var="order">
                    <c:forEach items="${objects}" var="object">
                        <c:if test="${order.idOggetto eq object.getL().getId()}">
                            <c:set var="disp" value="${object.getL().getDisponibilita()}"/>
                            <c:set var="sconto" value="${object.getL().getSconto()}"/>
                            <c:set var="prezzo" value="${object.getL().getPrezzo()}"/>
                            <c:set var="quantita" value="${order.getQuantita()}"/>
                            <c:set var="nOggetti" value="${nOggetti + quantita}"/>

                            
                            <tr><td colspan="9"><hr size="3" width="100%" align="left"/></td></tr>
                            <tr>
                                
                                <!-- Immagine prodotto -->
                                <td style="text-align: center"><img src="http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/square.png" alt="<c:out value="${object.getR().getSrc()}"/>"/></td>

                                <!-- Descrizione prodotto -->
                                <td>
                                    <table>
                                        <tr>
                                            <!-- Nome prodotto -->
                                            <td><c:out value="${object.getL().getNome()}" /></p></td>
                                        </tr>
                                        <tr>
                                            <!-- Disponibilita' prodotto -->
                                            <td id="lblDisponibilita${iterator}" data-disponibilita="${disp}">
                                                <c:if test="${disp == 0}">
                                                    <p>Non disponibile</p>
                                                </c:if>
                                                <c:if test="${disp > 0 && disp <= 10}">
                                                    <p><c:out value="${disp}"/> disponibili</p>
                                                </c:if>
                                                <c:if test="${disp > 10}">
                                                    <p>Disponibilita' immediata</p>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                                <td>
                                    <table>
                                        <!-- Prezzo prodotto + sconto prodotto -->
                                        
                                        <c:if test="${sconto > 0}">
                                            <td>
                                                <c:set var="prezzoScontato" value="${prezzo - (prezzo * sconto / 100)}"/>
                                                <del>EUR <fmt:formatNumber type = "number"  minFractionDigits="2"  maxFractionDigits = "2" value = "${prezzo}" /></del>
                                                EUR <span id="lblPrezzoScontato${iterator}"><fmt:formatNumber type = "number"  minFractionDigits="2"  maxFractionDigits = "2" value = "${prezzoScontato}" /></span>
                                                <br/>
                                                <p>(Applicato sconto del <c:out value="${sconto}"/> %)</p>
                                                
                                                <c:set var="prezzoTot" value="${prezzoTot + (prezzoScontato * quantita)}"/>
                                                
                                            </td>
                                        </c:if>
                                        <c:if test="${sconto == 0}">
                                            <td>
                                                EUR <span id="lblPrezzo${iterator}"><fmt:formatNumber type = "number" minFractionDigits="2"  maxFractionDigits = "2" value = "${prezzo}" /></span>
                                                
                                                <c:set var="prezzoTot" value="${prezzoTot + (prezzo * quantita)}"/>
                                            </td>
                                        </c:if>
                                    </table>
                                </td>
                                <!-- Quantita' prodotto nel carrello -->
                                <td>
                                    <p style="text-align: left">Quantita': 
                                        <input id="${iterator}" type="number" min="1" max="${disp}" style="width: 3em; text-align: right" data-oldvalueQuantita="${quantita}"  value="${quantita}" onkeypress='checkInputText(event, this)' onchange="checkInput(this)" onclick="checkInput(this)"/>
                                    </p>
                                    
                                    <button type="button" onclick="removeObject(this)" data-idInput="${iterator}">Rimuovi</button>
                                    
                                    
                                    <script>
                                        function removeObject(elem)
                                        {
                                            /*
                                             * Setto il valore dell'<intput> quantita relativo all'oggetto selezionato a 0
                                             * Dopo chiamo la servlet con parametro toDo=saveChanges (quindi uso solo la funzione
                                             * saveChanges) e salvo i parametri cambiati.
                                             * Se la quantita' e' 0, elimino la riga corrispondente dalla tabella ORDINE
                                            */
                                            var iterator = parseInt(elem.getAttribute("data-idInput"));
                                            var id = "quantita" + iterator.toString();
                                            
                                            document.getElementById(id).value = 0;
                                            
                                            saveChanges();
                                        };
                                        
                                        
                                        
                                        
                                        function checkInputText(e, elem)
                                        {
                                            if(e.keyCode == 13) // invio
                                            {
                                                checkInput(elem);
                                                return true;
                                            }
                                            else if(e.keyCode >= 48 && e.keyCode <= 57) // tra 0 e 9 
                                            {
                                                return true;
                                            }
                                            
                                            e.returnValue = false;
                                        };
                                    
                                        
                                        function checkInput(elem)
                                        {
                                            //Per il momento salvo la quantita' vecchia in un attributo dell'elemento (trova metodo alternativo, in modo che l'utente non possa modificarlo)
                                            var oldQuantita = parseInt(elem.getAttribute("data-oldvalueQuantita"));
                                            var newQuantita = parseInt(elem.value);
                                            var prezzoOggetto = 0;
                                            var oldPrezzoTotale = (parseFloat(document.getElementById("lblResultCart").getAttribute("data-oldvaluePrezzo"))).toFixed(2);
                                            var oldNumeroArticoli = parseFloat(document.getElementById("lblResultCart").getAttribute("data-oldvalueOggetti"));
                                            var newPrezzoTotale = 0;
                                            var newNumeroArticoli = 0;
                                            var disponibilitaProdotto = parseInt(document.getElementById("lblDisponibilita"+elem.id).getAttribute("data-disponibilita"));
                                            
                                            
                                            //Se (newQuantita > disponibilitaProdotto) allora setto (newQuantita = disponibilitaProdotto)
                                            if(newQuantita > disponibilitaProdotto)
                                            {
                                                newQuantita = disponibilitaProdotto;
                                                elem.value = disponibilitaProdotto.toString();
                                            }
                                                

                                            //Ottengo il prezzo dell'oggetto
                                            if(document.getElementById("lblPrezzo"+elem.id) == null)
                                                prezzoOggetto = (parseFloat(document.getElementById("lblPrezzoScontato"+elem.id).innerHTML.replace(',', '.'))).toFixed(2);
                                            else
                                                prezzoOggetto = (parseFloat(document.getElementById("lblPrezzo"+elem.id).innerHTML.replace(',', '.'))).toFixed(2);
                                           
                                           //Ottengo il prezzo dell'oggetto e il vecchio prezzo totale
                                           prezzoOggetto = parseFloat(prezzoOggetto);
                                           oldPrezzoTotale = parseFloat(oldPrezzoTotale);
                                           
                                           //Calcolo il nuovo prezzo totale e il nuovo numero di articoli
                                           newPrezzoTotale = (oldPrezzoTotale + prezzoOggetto * (newQuantita - oldQuantita));
                                           newNumeroArticoli = oldNumeroArticoli + (newQuantita - oldQuantita);
                                           
                                           //Stampo il nuovo numero di articoli e il nuovo prezzo totale
                                           var newText = "<b>Prezzo provvisorio (" + newNumeroArticoli + " articoli): EUR " + newPrezzoTotale.toFixed(2);
                                           document.getElementById("lblResultCart").innerHTML = newText;
                                           
                                           //Setto i vari attriuti (dei vari elementi utilizzati nel processo) con i nuovi valori
                                           document.getElementById("lblResultCart").setAttribute("data-oldvaluePrezzo", newPrezzoTotale.toString());
                                           document.getElementById("lblResultCart").setAttribute("data-oldvalueOggetti", newNumeroArticoli.toString());
                                           elem.setAttribute("data-oldvalueQuantita", newQuantita.toString());
                                           
                                           document.getElementById("quantita"+elem.id).value = newQuantita.toString();
                                        };
                                    </script>
                                </td>
                            </tr>
                            
                            <c:set var="iterator" value="${iterator + 1}"/>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </table>
            <hr size="3" width="100%" align="left"/>
            <p id="lblResultCart" style="text-align: left" data-oldvaluePrezzo="${prezzoTot}" data-oldvalueOggetti="${nOggetti}"><b>Prezzo provvisorio (<c:out value="${nOggetti}"/> articoli): EUR <fmt:formatNumber type = "number" minFractionDigits="2"  maxFractionDigits = "2" value = "${prezzoTot}"/></b></p>
        </div>
        
        <div class="container">
            <hr>
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>

<!-- PROBLEMI
DA FA' -> 1) l'utente deve fare il login e accedere alla pagina del carrello dalla navbar, e l'id dell'utente c'è l'hai già in sessione e sicuramente per motivi di sicurezza non andrebbe passato in get
DONE -> 2)Ho inserito del testo nella quantità, dopo di che ho rimesso un numero ma continuava a dare errore
FUNZIA -> 3)Ho modificato la quantità, sono tornato alla home e la quantità è andata persa
FUNZIA -> 4)Quando imposto quantità 0 l'oggetto andrebbe rimosso
FUNZIA -> 5)Sarebbe carino mostrare anche un'immagine dell'oggetto affianco al nome
FUNZIA -> 6)Premo su rimuovi ma non rimuove niente, ricarica la pagina e c'è ancora l'oggetto
DONE -> 7)Non dovrei poter ordinare più oggetti di quanti ne ha messi a disposizione il venditore, ma questo si può modificare nella servlet quando si premerà su procedi
-->