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
    
    <script src="${pageContext.request.contextPath}/jspFile/Finale/JS/Orders.js"></script>
    <script src="${pageContext.request.contextPath}/jspFile/Finale/JS/fixFooter.js"></script>
    
    <body>
        <c:set var="iterator" value="0"/>
        <c:set var="prezzoTot" value="0"/>
        <c:set var="nOggetti" value="0"/>
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
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
        </div>
        
        <div class="container-fluid">
            <%@include file="../alert/alertOverQuantity.jsp" %>
            <c:set var="iterator" value="0"/>
            
            <h2><b>CARRELLO</b></h2>
            
            <c:if test="${carrelloSessione.getSize() == 0}">
                <p><b>Nessun articolo nel carrello.</b></p>
            </c:if>
            <c:if test="${carrelloSessione.getSize() > 0}">
                <div>
                    <c:forEach items="${carrelloSessione.getList()}" var="order">
                        <c:forEach items="${objects}" var="object">
                            <c:if test="${order.idOggetto eq object.getL().getId()}">
                                <div class="row" style="margin-left: 0px;">
                                    <c:set var="disp" value="${object.getL().getDisponibilita()}"/>
                                    <c:set var="sconto" value="${object.getL().getSconto()}"/>
                                    <c:set var="prezzo" value="${object.getL().getPrezzo()}"/>
                                    <c:set var="quantita" value="${order.getQuantita()}"/>
                                    <c:set var="nOggetti" value="${nOggetti + quantita}"/>

                                    <hr size="3" width="100%" align="left"/>
                                    <!-- Immagine prodotto -->
                                    <div class="col-xs-2 col-sm-4 col-md-2 col-lg-2">
                                        <img style="width: 100px; height: 100px; object-fit: scale-down;" src="<c:out value="${object.getR().getSrc()}"/>" alt="img">
                                    </div>

                                    <!-- Descrizione prodotto -->
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <!-- Nome prodotto -->
                                        <div class="row">
                                            <a href="/ProgettoWeb/objectSelectedController?idOggetto=${object.getL().getId()}">
                                                <p><c:out value="${object.getL().getNome()}" /></p>
                                            </a>
                                        </div>
                                        <!-- Disponibilita' prodotto -->
                                        <div class="row" id="lblDisponibilita${iterator}" data-disponibilita="${disp}">
                                            <c:if test="${disp == 0}">
                                                <p>Non disponibile</p>
                                            </c:if>
                                            <c:if test="${disp > 0 && disp <= 10}">
                                                <p><c:out value="${disp}"/> disponibili</p>
                                            </c:if>
                                            <c:if test="${disp > 10}">
                                                <p>Disponibilit&agrave; immediata</p>
                                            </c:if>
                                        </div>
                                    </div>

                                    <!-- Prezzo prodotto + sconto prodotto --> 
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <c:if test="${sconto > 0}">
                                            <c:set var="prezzoScontato" value="${prezzo - (prezzo * sconto / 100)}"/>
                                            <p><del>&euro; <fmt:formatNumber groupingUsed = "false" type = "number"  minFractionDigits="2"  maxFractionDigits = "2" value = "${prezzo}" /></del></p>
                                            &euro; <span id="lblPrezzoScontato${iterator}"><fmt:formatNumber groupingUsed = "false" type = "number"  minFractionDigits="2"  maxFractionDigits = "2" value = "${prezzoScontato}" /></span>
                                            <p>(Applicato sconto del <c:out value="${sconto}"/> %)</p>

                                            <c:set var="prezzoTot" value="${prezzoTot + (prezzoScontato * quantita)}"/>
                                        </c:if>
                                        <c:if test="${sconto == 0}">
                                            &euro; <span id="lblPrezzo${iterator}"><fmt:formatNumber groupingUsed = "false" type = "number" minFractionDigits="2"  maxFractionDigits = "2" value = "${prezzo}" /></span>

                                            <c:set var="prezzoTot" value="${prezzoTot + (prezzo * quantita)}"/>
                                        </c:if>
                                    </div>

                                    <!-- Quantita' prodotto nel carrello -->
                                    <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                                        <div class="row">
                                            <p style="text-align: left">Quantit&agrave;: 
                                                <input id="${iterator}" type="number" min="1" max="${disp}" style="width: 3em; text-align: right" data-oldvalueQuantita="${quantita}"  value="${quantita}" onkeypress="checkInputText(event, this)" onchange="changeQuantity(this)" />
                                            </p>
                                        </div>
                                        <div class="row" style="margin: 0 auto;">
                                            <button class="btn btn-outline-primary buttonSpace" type="button" onclick="removeObject(this)" data-idInput="${iterator}"  data-pageId="00">Rimuovi</button>
                                        </div>
                                    </div>
                                </div>
                                
                                <c:set var="iterator" value="${iterator + 1}"/>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                    
                </div>
                
                <hr size="3" width="100%" align="left"/>
                <p id="lblResultCart" style="text-align: left" data-oldvaluePrezzo="${prezzoTot}" data-oldvalueOggetti="${nOggetti}"><b>Prezzo provvisorio (<c:out value="${nOggetti}"/> articoli): &euro; <fmt:formatNumber groupingUsed = "false" type = "number" minFractionDigits="2"  maxFractionDigits = "2" value = "${prezzoTot}"/></b></p>
                
                <button class="btn btn-outline-primary buttonSpace" type="button" onclick="saveChanges(0)">Salva modifiche al carrello</button>
                <c:if test="${utenteSessioneID == -1}">
                    <p>Devi essere registrato per poter procedere all'acquisto.</p>
                </c:if>
                <c:if test="${utenteSessioneID != -1}">
                    <button class="btn btn-outline-primary buttonSpace" type="button" onclick="saveChanges(1)">Procedi con l'ordine</button>
                </c:if>
            </c:if>
        </div>
        
        <%@include file="../Footer/footer.jsp" %>
    </body>
</html>