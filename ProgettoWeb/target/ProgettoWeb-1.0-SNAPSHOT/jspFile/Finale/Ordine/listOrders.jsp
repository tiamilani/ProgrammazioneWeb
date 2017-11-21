<%-- 
    Document   : listOrders
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
    
    <script src="http://localhost:8080/ProgettoWeb/jspFile/Finale/JS/Orders.js"></script>
    <script>
        window.onbeforeunload = function(event)
        {
            saveChanges(0);
            event.returnValue = true;
        };
    </script>
    
    <body>
        <c:set var="iterator" value="0"/>
        <c:set var="prezzoTot" value="0"/>
        <c:set var="nOggetti" value="0"/>
        <c:set var="userId_request" value="${utenteSessione.getId()}"/>
        <fmt:setLocale value = "it_IT"/>
        
        
        <div style="display: none">
            <form method="POST" id="formSaveChanges" name="formSaveChanges">
                <c:forEach items="${carrelloSessione.getList()}" var="order">
                    <input type="tetx" id="idOrdine${iterator}" name="idOrdine${iterator}" value="${order.idOrdine}"/>
                    <input type="tetx" id="idOggetto${iterator}" name="idOggetto${iterator}" value="${order.idOggetto}"/>
                    <input type="tetx" id="quantita${iterator}" name="quantita${iterator}" value="${order.getQuantita()}"/>
                           
                    <c:set var="iterator" value="${iterator + 1}"/>
                </c:forEach>
            </form>
        </div>

        <div class="container">
            <%@include file="../Header/NavBar/navBar.jsp" %>
            <hr/>
        </div>
        
        <div class="container-fluid">
            
            <c:set var="iterator" value="0"/>
            
            <h2><b>CARRELLO</b></h2>
            <c:if test="${carrelloSessione.getSize() == 0}">
                <span><b>Nessun articolo nel carrello.</b></span>
            </c:if>
            <c:if test="${carrelloSessione.getSize() > 0}">
                <table width="100%" cellpadding="5">
                    <c:forEach items="${carrelloSessione.getList()}" var="order">
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
                                    <td style="text-align: center"><img src="http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/square.png" alt="<c:out value="${object.getR().getSrc()}"/>"></td>

                                    <!-- Descrizione prodotto -->
                                    <td>
                                        <table>
                                            <tr>
                                                <!-- Nome prodotto -->
                                                <td><c:out value="${object.getL().getNome()}" /></td>
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
                                            <input id="${iterator}" type="number" min="1" max="${disp}" style="width: 3em; text-align: right" data-oldvalueQuantita="${quantita}"  value="${quantita}" onkeypress='checkInputText(event, this)' onchange="changeQuantity(this)" />
                                        </p>
                                        <button class="btn btn-outline-primary buttonSpace" type="button" onclick="removeObject(this)" data-idInput="${iterator}"  data-pageId="00">Rimuovi</button>
                                    </td>
                                </tr>

                                <c:set var="iterator" value="${iterator + 1}"/>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </table>
                <hr size="3" width="100%" align="left"/>
                <p id="lblResultCart" style="text-align: left" data-oldvaluePrezzo="${prezzoTot}" data-oldvalueOggetti="${nOggetti}"><b>Prezzo provvisorio (<c:out value="${nOggetti}"/> articoli): EUR <fmt:formatNumber type = "number" minFractionDigits="2"  maxFractionDigits = "2" value = "${prezzoTot}"/></b></p>
                <button class="btn btn-outline-primary buttonSpace" type="button" onclick="saveChanges(1)">Procedi con l'ordine</button>
            </c:if>
        </div>
        
        <div class="container">
            <hr>
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>