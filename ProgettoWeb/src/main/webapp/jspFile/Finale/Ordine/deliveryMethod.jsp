<%-- 
    Document   : deliveryMethod
    Created on : Nov 9, 2017, 9:50:47 AM
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
        <title>Metodo di Spedizione</title>
    </head>
    
    <script src="http://localhost:8080/ProgettoWeb/jspFile/Finale/JS/Orders.js"></script>
    
    <body onload="setFirstDescription(<c:out value="${carrelloSessione.getSize()}"/>)">
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
            <hr/>
        </div>
            
        <div class="container-fluid">
            <div style="display: none">
                <form method="POST" id="formSaveChanges" name="formSaveChanges">
                    <c:set var="iterator" value="0"/>
                    <c:forEach items="${carrelloSessione.getList()}" var="order">
                        <input type="tetx" id="idOrdine${iterator}" name="idOrdine${iterator}" value="${order.idOrdine}"/>
                        <input type="tetx" id="idOggetto${iterator}" name="idOggetto${iterator}" value="${order.idOggetto}"/>
                        <input type="tetx" id="quantita${iterator}" name="quantita${iterator}" value="${order.getQuantita()}"/>
                        <input type="text" id="idS${iterator}" name="idS${iterator}" value=""/>
                        <c:set var="iterator" value="${iterator + 1}"/>
                    </c:forEach>
                    
                    <input type="text" id="idI" name="idI" value=""/>
                </form>
            </div>
            
            <c:set var="iterator" value="0"/>
            
            <h2><b>INDIRIZZO DI SPEDIZIONE</b></h2>
            <table width="100%" cellpadding="5">
                <!-- Elenco indirizzi - si seleziona tramite un radio button -->
                <tr>
                    <td></td>
                    <td>Nome e Cognome</td>
                    <td>Via</td>
                    <td>Numero Civico</td>
                    <td>Citta'</td>
                    <td>Provincia</td>
                    <td>Stato</td>
                </tr>
                
                <c:forEach items="${addrs.getList()}" var="address">
                    <tr>
                        <td><input type="radio" id="rbtnAddress${iterator}" name="rbtnAddress" value="${address.getIdI()}" onchange="setAddress(this)"></td>
                        
                        
                        <td><c:out value="${utenteSessione.getNome()}"/> <c:out value="${utenteSessione.getCognome()}"/></td>
                        <td><c:out value="${address.getVia()}"/></td>
                        <td><c:out value="${address.getnCivico()}"/></td>
                        <td><c:out value="${address.getCitta()}"/></td>
                        <td><c:out value="${address.getProvincia()}"/></td>
                        <td><c:out value="${address.getStato()}"/></td>
                        <td><a href="${pageContext.request.contextPath}/UserController?action=infoCurrentUser" class="btn btn-outline-primary buttonSpace">Modifica</a></td>
                    </tr>
                    <c:set var="iterator" value="${iterator + 1}"/>
                </c:forEach>
            </table>
            
            <a href="${pageContext.request.contextPath}/UserController?action=infoCurrentUser" class="btn btn-outline-primary buttonSpace">Aggiungi indirizzo</a>
            
            <hr/>
            
            
            
            
            <c:set var="iterator" value="0"/>
            
            
            <h2><b>METODO DI SPEDIZIONE</b></h2>
            
            <div>
                <c:forEach items="${carrelloSessione.getList()}" var="order">
                    <c:forEach items="${objects}" var="object">
                        <c:if test="${order.getIdOggetto() eq object.getL().getId()}">
                            <div>
                                <c:set var="sconto" value="${object.getL().getSconto()}"/>
                                <c:set var="prezzo" value="${object.getL().getPrezzo()}"/>
                                <c:set var="quantita" value="${order.getQuantita()}"/>
                                <c:set var="spedizioniOggetto" value="${listaTipiSpedizione.get(iterator).getList()}"/>
                                <c:set var="prezzoScontato" value="${prezzo - (prezzo * sconto / 100)}"/>
                                
                                <hr size="3" width="100%" align="left"/>
                                
                                <!-- Nome negozio da cui viene spedito il prodotto -->
                                <div class="row">
                                    <span>Spedito da <c:out value="${shops.get(iterator).getNomeNegozio()}"/></span>
                                </div>
                                <!-- Data di consegna prevista del prodotto -->
                                <div class="row">
                                    <h6 id="DCP${iterator}"/>
                                </div>
                                
                                <div class="row">
                                    <!-- Immagine prodotto -->
                                    <div class="col-2">
                                        <img src="<c:out value="${object.getR().getSrc()}"/>" alt="http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/square.png">
                                    </div>
                                    
                                    <div class="col-4">
                                        <!-- Nome prodotto -->
                                        <div class="row">
                                            <c:out value="${object.getL().getNome()}" />
                                        </div>
                                        <!-- Prezzo del prodotto -->
                                        <div class="row">
                                            <span>EUR <fmt:formatNumber type = "number"  minFractionDigits="2"  maxFractionDigits = "2" value = "${prezzoScontato}" /></span>
                                        </div>
                                        <!-- Quantita' prodotto nel carrello -->
                                        <div class="row">
                                            <span>Quantita': <c:out value="${quantita}"/></span>
                                        </div>
                                        <div class="row">
                                            <button class="btn btn-outline-primary buttonSpace" type="button" onclick="removeObject(this)" data-idInput="${iterator}" data-pageId="01">Rimuovi</button>
                                        </div>
                                    </div>
                                    
                                    <!-- Selezione metodo di spedizione -->
                                    <div class="col-4">
                                        <select id="${iterator}" onchange="changeDescription(this.id)">
                                            <c:set var="iterator2" value="0"/>
                                            <c:forEach items="${spedizioniOggetto}" var="spedizione">
                                                <option id="option${iterator}${iterator2}" value="${iterator2}" data-idS="${spedizione.getIdS()}" data-nome="${spedizione.getNome()}" data-prezzoOgg="${prezzoScontato}" data-quantitaOgg="${quantita}" data-Prezzo="${spedizione.getPrezzo()}" data-Corriere="${spedizione.getCorriere()}" data-tempo="${spedizione.getTempoRichiesto()}" data-nMax="${spedizione.getNumeroMassimo()}">
                                                    <c:out value="${spedizione.getNome()}"/>
                                                </option>
                                                <c:set var="iterator2" value="${iterator2 + 1}"/>
                                            </c:forEach>
                                            <c:if test="${object.getL().getRitiroInNegozio() == 1}">
                                                <option id="option${iterator}${iterator2}" value="${iterator2}" data-idS="-1" data-nome="RitiroInNegozio" data-prezzoOgg="${prezzoScontato}" data-quantitaOgg="${quantita}">
                                                    Ritiro in negozio
                                                </option>
                                            </c:if>
                                        </select>
                                    </div>
                                    
                                    <!-- Descrizione metodo spedizione -->
                                    <div class="col-2">
                                        <p id="pDesc${iterator}"/>
                                    </div>
                                </div>
                                   
                                <!-- Prezzo del subtotale: PrezzoProdotto*Quantita + PrezzoSpedizione -->
                                <div class="row">
                                    <p id="pSubTot${iterator}" data-oldSubTot="0"/>
                                </div>
                                
                                <c:set var="iterator" value="${iterator + 1}"/>
                            </div>
                        </c:if>
                    </c:forEach>
                </c:forEach>
            </div>
            
            <p id="pTot" data-oldTot="0"></p>
            <button class="btn btn-outline-primary buttonSpace" type="button" onclick="saveChanges(2)">Procedi col pagamento</button>
        </div>
            
        <%@include file="../Footer/footer.jsp" %>
    </body>
</html>