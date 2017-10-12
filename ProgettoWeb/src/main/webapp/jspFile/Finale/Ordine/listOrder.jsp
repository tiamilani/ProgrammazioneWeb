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
        <div class="container">
            <%@include file="../Header/NavBar/navBar.jsp" %>
            <hr/>
            
            <h2><b>CARRELLO</b></h2>
        </div>
        
        <div class="container">
            <table width="80%" cellpadding="5">
                <c:forEach items="${cart}" var="order">
                    <c:forEach items="${objects}" var="object">
                        <c:if test="${order.idOggetto eq object.getL().getId()}">
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
                                                <c:set var="disp" value="${object.getL().getDisponibilita()}"/>

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
                                        <c:set var="sconto" value="${object.getL().getSconto()}"/>
                                        <c:set var="prezzo" value="${object.getL().getPrezzo()}"/>

                                        <c:if test="${sconto > 0}">
                                            <td>
                                                <fmt:setLocale value = "it_IT"/>
                                                <del><fmt:formatNumber value = "${prezzo}" type = "currency"/></del>
                                                <fmt:formatNumber value = "${prezzo - (prezzo * sconto / 100)}" type = "currency"/>
                                                <br/>
                                                <p>(Applicato sconto del <c:out value="${sconto}"/> %)</p>
                                            </td>
                                        </c:if>
                                        <c:if test="${sconto == 0}">
                                            <td>
                                                <fmt:setLocale value = "it_IT"/>
                                                <fmt:formatNumber value = "${prezzo}" type = "currency"/>
                                            </td>
                                        </c:if>
                                    </table>
                                </td>
                                <!-- Quantita' prodotto nel carrello -->
                                <td>
                                    <p style="text-align: left">Quantita': <input type="number" min="1" style="width: 3em;"  value="${order.quantita}" onkeypress='return event.charCode >= 48 && event.charCode <= 57' /></p>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </table>
        </div>      
        
        <div class="container">
            <hr>
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>



<!--
<h2>Phone numbers</h2>
        <div class="rTable">
            <div class="rTableRow">
                <div class="rTableHead"><strong>Name</strong></div>
                <div class="rTableHead"><span style="font-weight: bold;">Telephone</span></div>
                <div class="rTableHead">&nbsp;</div>
            </div>
            <div class="rTableRow">
                <div class="rTableCell">John</div>
                <div class="rTableCell"><a href="tel:0123456785">0123 456 785</a></div>
                <div class="rTableCell"><img src="images/check.gif" alt="checked" /></div>
            </div>
                <div class="rTableRow">
                <div class="rTableCell">Cassie</div>
                <div class="rTableCell"><a href="tel:9876532432">9876 532 432</a></div>
                <div class="rTableCell"><img src="images/check.gif" alt="checked" /></div>
            </div>
        </div>
-->



<!--
<div class="container">
            <h2>Carrello</h2>
            <div class="rTable">
                <div class="rTableColumn">
                    colonna1
                </div>
                <div class="rTableColumn">
                    colonna2
                </div>
                <div class="rTableColumn">
                    <div class="rTableRow">
                    <div class="rTableCell"> <!-- Immagine --
                        Immagine
                    </div>
                    <div class="rTableCell"> <!-- Descrizione --
                        <div class="rTableRow">
                            Nome
                        </div>
                        <div class="rTableRow">
                            ? Negozio ?
                        </div>
                    </div>
                    </div>
                </div>
                
                <div class="rTableRow">
                    <div class="rTableColumn"><b>Immagine</b></div>
                    <div class="rTableHead"><b>Descrizione</b></div>
                    <div class="rTableHead"><b>Prezzo</b></div>
                </div>
                <div class="rTableRow">
                    <div class="rTableCell"> <!-- Immagine --
                        Immagine
                    </div>
                    <div class="rTableCell"> <!-- Descrizione --
                        <div class="rTableRow">
                            Nome
                        </div>
                        <div class="rTableRow">
                            ? Negozio ?
                        </div>
                    </div>
                    <div class="rTableCell"> <!-- Prezzo --
                        <div class="rTableRow">
                            Prezzo
                        </div>
                        <div class="rTableRow">
                            Sconto
                        </div>
                        <div class="rTableRow">
                            Quantita'
                        </div>
                    </div>
                </div>

                <div class="rTableRow">
                    <div class="rTableCell">John</div>
                    <div class="rTableCell"><a href="tel:0123456785">0123 456 785</a></div>
                    <div class="rTableCell"><img src="images/check.gif" alt="checked" /></div>
                </div>
                    <div class="rTableRow">
                    <div class="rTableCell">Cassie</div>
                    <div class="rTableCell"><a href="tel:9876532432">9876 532 432</a></div>
                    <div class="rTableCell"><img src="images/check.gif" alt="checked" /></div>
                </div>
            </div>
        </div>
        -->
        
        
        
        
        
<!-- WORKS
<div class="container">
            <table border=1>
                <thead>
                    <!--
                        STATO DELL'ORDINE:
                            (0 = nel carrello, 1 = pagato, 2 = in lavorazione, 3 = spedito, 4 = consegnato, 5 = nella lista desideri)
                    --
                    <tr>
                        <!-- ordine --|> <th>idOrdine</th>
                        <!-- ordine --|> <th>idOggetto</th>
                        <!-- ordine --|> <th>idNegozio</th>
                        <!-- oggetto -- <th>immagineOggetto</th>
                        <!-- oggetto -- <th>nomeOggetto</th>
                        <!-- oggetto -> <th>prezzoOggetto</th>
                        <!-- oggetto -> <th>scontoOggetto</th>
                        <!-- ordine -- <th>quantita</th>
                        <!-- ordine -- <th>dataOrdine</th> <!-- Data nella quale viene creato l'ordine --
                        <!-- ordine -- <th>dataArrivoPresunta</th>
                        <!-- ordine -- <th>prezzoDiAcquisto</th> <!-- Prezzo totale dell'ordine --
                        <!-- ordine -- <th>codiceTracking</th>
                        <!--<th colspan=2>Action</th>--
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${cart}" var="order">
                        <c:forEach items="${objects}" var="object">
                            <c:if test="${order.idOggetto eq object.getL().getId()}">
                                <tr>
                                    <!--<td><c:out value="${order.idOrdine}" /></td>--
                                    <!--<td><c:out value="${order.idOggetto}" /></td>--
                                    <!--<td><c:out value="${order.idNegozio}" /></td>--
                                    <td><c:out value="${object.getR().getSrc()}" /></td>
                                    <td><c:out value="${object.getL().getNome()}" /></td>
                                    <td><c:out value="${object.getL().getPrezzo()}" /></td>
                                    <td><c:out value="${object.getL().getSconto()}" />%</td>
                                    <td><c:out value="${order.quantita}" /></td>
                                    <td><c:out value="${order.dataOrdine}" /></td>
                                    <td><c:out value="${order.dataArrivoPresunta}" /></td>
                                    <td><c:out value="${order.prezzoDiAcquisto}" /></td>
                                    <td><c:out value="${order.codiceTracking}" /></td>

                                    <!--<td><a href="<c:out value="${pageContext.request.contextPath}"/>/IndirizzoController?action=edit&addrId=<c:out value="${address.getIdI()}"/>&userId=<c:out value="${userId}"/>">Update</a></td>
                                    <td><a href="<c:out value="${pageContext.request.contextPath}"/>/IndirizzoController?action=delete&addrId=<c:out value="${address.getIdI()}"/>&userId=${userId}">Delete</a></td>--
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </tbody>
            </table>
            <!--<p><a href="<c:out value="${pageContext.request.contextPath}"/>/IndirizzoController?action=insert&userId=${userId}">Add Address</a></p>--
        </div>
-->


<!--
<div class="container">
            <table width="100%" border="0" cellpadding="5">
                <tbody>
                    <c:forEach items="${cart}" var="order">
                        <c:forEach items="${objects}" var="object">
                            <c:if test="${order.idOggetto eq object.getL().getId()}">
                                <tr>
                                    
                                    <td>
                                        <table>
                                            <th style="text-align: center">IMMAGINE</th>
                                            <tr><td style="text-align: center"><c:out value="${object.getR().getSrc()}" /></td></tr>
                                        </table>
                                    </td>
                                    
                                    <td>
                                        <table>
                                            <th style="text-align: center">DESCRIZIONE</th>
                                            <tr>
                                                
                                                <td style="text-align: center"><c:out value="${object.getL().getNome()}" /></td>
                                            </tr>
                                            <tr>
                                                
                                                <td style="text-align: center">
                                                    <c:set var="disp" value="${object.getL().getDisponibilita()}"/>
                                                    
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
                                            <th style="text-align: center">PREZZO</th>
                                            
                                            <c:set var="sconto" value="${object.getL().getSconto()}"/>
                                            <c:set var="prezzo" value="${object.getL().getPrezzo()}"/>
                                            
                                            <c:if test="${sconto > 0}">
                                                <td style="text-align: center">
                                                    <c:out value="${prezzo - (prezzo * sconto / 100)}" />
                                                    <br/>
                                                    <p style="text-align: center">Scontato del <c:out value="${sconto}"/> %</p>
                                                </td>
                                            </c:if>
                                            <c:if test="${sconto == 0}">
                                                <td style="text-align: center"><p><c:out value="${prezzo}" /></p></td>
                                            </c:if>
                                        </table>
                                    </td>
                                    <th style="text-align: center">QUANTITA'</th>
                                    
                                    <td style="text-align: center">
                                        <input type="number" min="1" value="${order.quantita}"/>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        -->