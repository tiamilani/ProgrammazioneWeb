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
                                            <td><c:out value="${object.getL().getNome()}" /></td>
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
                                                
                                                <del><fmt:formatNumber value = "${prezzo}" type = "currency"/></del>
                                                <fmt:formatNumber value = "${prezzoScontato}" type = "currency"/>
                                                <br/>
                                                <p>(Applicato sconto del <c:out value="${sconto}"/> %)</p>
                                                
                                                <c:set var="prezzoTot" value="${prezzoTot + (prezzoScontato * quantita)}"/>
                                                
                                            </td>
                                        </c:if>
                                        <c:if test="${sconto == 0}">
                                            <td>
                                                <fmt:formatNumber value = "${prezzo}" type = "currency"/>
                                                
                                                <c:set var="prezzoTot" value="${prezzoTot + (prezzo * quantita)}"/>
                                            </td>
                                        </c:if>
                                    </table>
                                </td>
                                <!-- Quantita' prodotto nel carrello -->
                                <td>
                                    <p style="text-align: left">Quantita': 
                                        <input id="${iterator}" type="number" min="1" max="${disp}" style="width: 3em; text-align: right"  value="${quantita}" onkeypress='return event.charCode >= 48 && event.charCode <= 57' onchange="checkInput(this)"/>
                                    </p>
                                    
                                    
                                    <script>
                                        function checkInput(elem)
                                        {
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
                                            
                                            alert("nuovo prezzo = " + nuovoPrezzoTotale);
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
            <p id="lblResultCart" style="text-align: left"><b>Prezzo provvisorio (<c:out value="${nOggetti}"/> articoli): <fmt:formatNumber value = "${prezzoTot}" type = "currency"/></b></p>
        </div>
        
        <div class="container">
            <hr>
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>