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
        
        <c:set var="iterator" value="0"/>
        <c:set var="prezzoTot" value="0"/>
        <c:set var="nOggetti" value="0"/>
        <fmt:setLocale value = "it_IT"/>
        
        <div class="container">
            <%@include file="../Header/NavBar/navBar.jsp" %>
            <hr/>
        </div>
        
        <div class="container-fluid">
            <h2><b>CARRELLO</b></h2>
            <table width="100%" cellpadding="5">
                <c:forEach items="${cart}" var="order">
                    <c:forEach items="${objects}" var="object">
                        <c:if test="${order.idOggetto eq object.getL().getId()}">
                            <p><c:out value="${objects.get(iterator).getL().getNome()}"/></p>
                            
                            <c:set var="disp" value="${object.getL().getDisponibilita()}"/>
                            <c:set var="sconto" value="${object.getL().getSconto()}"/>
                            <c:set var="prezzo" value="${object.getL().getPrezzo()}"/>
                            <c:set var="quantita" value="${order.getQuantita()}"/>
                            <c:set var="nOggetti" value="${nOggetti + quantita}"/>

                            
                            <tr><td colspan="9"><hr size="3" width="100%" align="left"/></td></tr>
                            <tr>
                                
                                <!-- Immagine prodotto -->
                                <td style="text-align: center"><c:out value="${object.getR().getSrc()}" /></td>

                                <!-- Descrizione prodotto -->
                                <td>
                                    <table>
                                        <tr>
                                            <!-- Nome prodotto -->
                                            <td><c:out value="${object.getL().getNome()}" /></p></td>
                                        </tr>
                                        <tr>
                                            <!-- Disponibilita' prodotto -->
                                            <td>
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
                                                
                                                <!-- OLD    <del><fmt:formatNumber value = "${prezzo}" type = "currency"/></del>-->
                                                <!-- OLD    <span id="lblPrezzoScontato${iterator}"><fmt:formatNumber value = "${prezzoScontato}" type = "currency"/></span>-->
                                                
                                                <del>EUR <fmt:formatNumber type = "number"  minFractionDigits="2"  maxFractionDigits = "2" value = "${prezzo}" /></del>
                                                EUR <span id="lblPrezzoScontato${iterator}"><fmt:formatNumber type = "number"  minFractionDigits="2"  maxFractionDigits = "2" value = "${prezzoScontato}" /></span>
                                                <br/>
                                                <p>(Applicato sconto del <c:out value="${sconto}"/> %)</p>
                                                
                                                <c:set var="prezzoTot" value="${prezzoTot + (prezzoScontato * quantita)}"/>
                                                
                                            </td>
                                        </c:if>
                                        <c:if test="${sconto == 0}">
                                            <td>
                                                <!-- OLD    <span id="lblPrezzo${iterator}"><fmt:formatNumber value = "${prezzo}" type = "currency"/></span>-->
                                                
                                                EUR <span id="lblPrezzo${iterator}"><fmt:formatNumber type = "number" minFractionDigits="2"  maxFractionDigits = "2" value = "${prezzo}" /></span>
                                                
                                                <c:set var="prezzoTot" value="${prezzoTot + (prezzo * quantita)}"/>
                                            </td>
                                        </c:if>
                                    </table>
                                </td>
                                <!-- Quantita' prodotto nel carrello -->
                                <td>
                                    <p style="text-align: left">Quantita': 
                                        <input id="${iterator}" type="number" min="1" max="${disp}" style="width: 3em; text-align: right" data-oldvalueQuantita="${quantita}"  value="${quantita}" onkeypress='return event.charCode >= 48 && event.charCode <= 57' onchange="checkInput(this)"/>
                                    </p>
                                    
                                    
                                    <script>
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

                                            //Ottengo il prezzo dell'oggetto
                                            if(document.getElementById("lblPrezzo"+elem.id) == null)
                                                prezzoOggetto = (parseFloat(document.getElementById("lblPrezzoScontato"+elem.id).innerHTML.replace(',', '.'))).toFixed(2);
                                            else
                                                prezzoOggetto = (parseFloat(document.getElementById("lblPrezzo"+elem.id).innerHTML.replace(',', '.'))).toFixed(2);
                                           
                                           
                                           //TEST 2 -> FAILED
                                           /*
                                            * Vorrei assegnare alla variabile prezzoTot (variabile JSP) il valore della variabile nuovoPrezzoTotale (variabile javascript).
                                            * Questo non è possibile, poiché JSP is executed on the server and JavaScript on the browser.
                                            * Quindi si può leggere il valore di una variabile JSP, ma non si può modificare (dentro lo script).
                                            * L'unico sistema è cambiare il testo di un elemento
                                            
                                           //<-c:set var="prezzoTot" value="${nuovoPrezzoTotale}"/>;
                                           //alert(${prezzoTot});*/
                                           
                                           
                                           
                                           //TEST 3 -> FUNZIA!!!
                                           
                                           //Ottengo il prezzo dell'oggetto e il vecchio prezzo totale
                                           prezzoOggetto = parseFloat(prezzoOggetto);
                                           oldPrezzoTotale = parseFloat(oldPrezzoTotale);
                                           
                                           
                                           
                                           //alert("oldQuantita = " + (oldQuantita));
                                           //alert("newQuantita = " + (newQuantita));
                                           //alert("prezzoOggetto = " + (prezzoOggetto));
                                           //alert("oldPrezzoTotale = " + (oldPrezzoTotale));
                                           //alert("oldOggetti = " + (oldNumeroArticoli));
                                           
                                           
                                           
                                           
                                           //Calcolo il nuovo prezzo totale e il nuovo numero di articoli
                                           newPrezzoTotale = (oldPrezzoTotale + prezzoOggetto * (newQuantita - oldQuantita));
                                           newNumeroArticoli = oldNumeroArticoli + (newQuantita - oldQuantita);
                                           
                                           //alert("newPrezzoTotale = " + newPrezzoTotale);
                                           //alert("newNumeroArticoli = " + newNumeroArticoli);
                                           
                                           
                                           //Stampo il nuovo numero di articoli e il nuovo prezzo totale
                                           var newText = "<b>Prezzo provvisorio (" + newNumeroArticoli + " articoli): EUR " + newPrezzoTotale.toFixed(2);
                                           document.getElementById("lblResultCart").innerHTML = newText;
                                           
                                           //Setto i vari attriuti (dei vari elementi utilizzati nel processo) con i nuovi valori
                                           document.getElementById("lblResultCart").setAttribute("data-oldvaluePrezzo", newPrezzoTotale.toString());
                                           document.getElementById("lblResultCart").setAttribute("data-oldvalueOggetti", newNumeroArticoli.toString());
                                           elem.setAttribute("data-oldvalueQuantita", newQuantita.toString());
    
    
                                           
                                           
                                           
                                           
                                           //TEST 1 -> FUNZIA (ma non salva il prezzoTot globale)
                                           /*
                                           //Calcolo il nuovo prezzo totale provvisorio
                                           //nuovoPrezzoTotale = prezzoTot + prezzoOggetto * (newQuantita - oldQuantita)
                                           nuovoPrezzoTotale = (${prezzoTot} + prezzoOggetto * (newQuantita - oldQuantita)).toFixed(2);
                                           
                                           
                                           //Calcolo il numero di articoli totali provvisori
                                           nuovoNumeroArticoli = ${nOggetti} + (newQuantita - oldQuantita);
                                           
                                           alert("oldQuantita = " + oldQuantita);
                                           alert("prezzoOggetto = " + prezzoOggetto);
                                           alert("nuovoPrezzoTotale = " + nuovoPrezzoTotale);
                                           alert("nuovoNumeroArticoli = " + nuovoNumeroArticoli);
                                           
                                           
                                           //Scrivo i valori di nuovoNumeroArticoli e nuovoPrezzoTotale
                                           var newText = "<b>Prezzo provvisorio (" + nuovoNumeroArticoli + " articoli): EUR " + nuovoPrezzoTotale;
                                           alert(newText);
                                           
                                           document.getElementById("lblResultCart").innerHTML = newText;
                                           
                                           
                                           
                                           //Cambio i valori delle variabili globali --> NON SI PUO' FARE
                                           //<-c:set var="prezzoTot" value="${prezzoTot + nuovoPrezzoTotale}"/>;
                                           //<-c:set var="nOggetti" value="${nOggetti + nuovoNumeroArticoli}"/>;
                                           
                                           */
                                           
                                           
                                            /*
                                             * TEST 0
                                             * PROBLEMA
                                             * Lo script viene caricato solo la prima volta, e le variabili vengono istanziate SOLO con i dati del primo elemento
                                             * NON E' DINAMICO!
                                             
                                            
                                            //Ricevo l'evento 'onchange' da <InpuNumber>
                                            
                                            //Calcolo nuovoNumeroArticoli e nuovoPrezzoTotale basandomi sull'ID di <InputNumber>
                                            var index = parseInt(elem.id);
                                            var prezzoOggetto = ${objects.get(index).getL().getPrezzo() - (objects.get(index).getL().getPrezzo() * objects.get(index).getL().getSconto() / 100)};
                                            var nuovaQuantita = elem.value;
                                            var nuovoNumeroArticoli = 0;
                                            var nuovoPrezzoTotale = 0;
                                            
                                            alert("InputNumber.id = " + index);
                                            alert("vecchiaQuantita = " + ${cart.get(index).getQuantita()});
                                            alert("nuovaQuantita = " + nuovaQuantita);
                                            alert("prezzo - (prezzo * sconto / 100) = " + ${objects.get(index).getL().getPrezzo()} + " - (" + ${objects.get(index).getL().getPrezzo()} + " * " + ${objects.get(index).getL().getSconto()} + " / 100");
                                            alert("prezzoOggetto = " + prezzoOggetto);
                                            
                                            
                                            alert(${prezzoTot} + " + " + prezzoOggetto + " * (" + nuovaQuantita + " - " + ${cart.get(index).getQuantita()});
                                            nuovoPrezzoTotale = ${prezzoTot} + prezzoOggetto * (nuovaQuantita - ${cart.get(index).getQuantita()});
                                            
                                            alert("nuovo prezzo = " + nuovoPrezzoTotale);*/
                                        }
                                    </script>
                                </td>
                            </tr>
                            
                            <c:set var="iterator" value="${iterator + 1}"/>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </table>
            <hr size="3" width="100%" align="left"/>
            <!-- OLD    <p id="lblResultCart" style="text-align: left"><b>Prezzo provvisorio (<c:out value="${nOggetti}"/> articoli): <fmt:formatNumber value = "${prezzoTot}" type = "currency"/></b></p>-->
            <p id="lblResultCart" style="text-align: left" data-oldvaluePrezzo="${prezzoTot}" data-oldvalueOggetti="${nOggetti}"><b>Prezzo provvisorio (<c:out value="${nOggetti}"/> articoli): <fmt:formatNumber type = "number" minFractionDigits="2"  maxFractionDigits = "2" value = "${prezzoTot}"/></b></p>
        </div>
        
        <div class="container">
            <hr>
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>