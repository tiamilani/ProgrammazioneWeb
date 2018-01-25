<%-- 
    Document   : assistanceResolution
    Created on : Dec 25, 2017, 4:59:16 PM
    Author     : FBrug
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title>Assistance Management</title>
    </head>
    
    <script src="http://localhost:8080/ProgettoWeb/jspFile/Finale/JS/Assistances.js"></script>
    
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
            <hr/>
        </div>
            
        <div class="container-fluid">
            <div style="display: none">
                <form method="POST" id="formSaveChanges" name="formSaveChanges">
                    <input id="idA" name="idA" type="text" value="${assistenza.getId()}"/>
                </form>
            </div>
            
            <h2><b>RICHIESTA DI ASSISTENZA N° <c:out value="${assistenza.getId()}"/></b></h2>
            
            <div>
                <div class="row">
                    <div class="col-3">
                        <span>Data di apertura</span>
                    </div>
                    <div class="col-9">
                        <span><c:out value="${assistenza.getDataApertura()}"/></span>
                    </div>
                </div>
                
                <br>
                
                <div class="row">
                    <div class="col-3">
                        <span>Data di chiusura</span>
                    </div>
                    <div class="col-9">
                        <span><c:out value="${assistenza.getDataChiusura()}"/></span>
                    </div>
                </div>
                
                <br>
                
                <div class="row">
                    <div class="col-3">
                        <span>Utente richiedente</span>
                    </div>
                    <div class="col-9">
                        <span>
                            <c:out value="${utenteRichiedente.getCognome()}"/> 
                            <c:out value="${utenteRichiedente.getNome()}"/> , 
                            <c:out value="${utenteRichiedente.getId()}"/>
                        </span>
                    </div>
                </div>
                
                <br>
                
                <div class="row">
                    <div class="col-3">
                        <span>Venditore contestato</span>
                    </div>
                    <div class="col-9">
                        <span>
                            <c:out value="${venditoreContestato.getCognome()}"/> 
                            <c:out value="${venditoreContestato.getNome()}"/> , 
                            <c:out value="${venditoreContestato.getId()}"/>
                        </span>
                    </div>
                </div>
                
                <br>
                
                <div class="row">
                    <div class="col-3">
                        <span>Oggetto contestato</span>
                    </div>
                    <div class="col-9">
                        <span>
                            <c:out value="${oggettoContestato.getNome()}"/> , 
                            <c:out value="${oggettoContestato.getId()}"/>
                        </span>
                    </div>
                </div>
                
                <br>
                
                <div class="row">
                    <div class="col-3">
                        <span>ID Ordine contestato</span>
                    </div>
                    <div class="col-9">
                        <span><c:out value="${ordineContestato}"/></span>
                    </div>
                </div>
                
                <br>
                
                <div class="row">
                    <div class="col-3">
                        <span>Testo della richiesta</span>
                    </div>
                    <div class="col-9">
                        <span><c:out value="${assistenza.getRichiesta()}"/></span>
                    </div>
                </div>
                
                <br>
                
                <div class="row">
                    <div class="col-3">
                        <span>Testo della soluzione (max 2500 caratteri)</span>
                    </div>
                    <div class="col-9">
                        <c:if test="${assistenza.getStato() == 0}">
                            <textarea cols="100" rows="5" maxlength="2500" name="solution" form="formSaveChanges"><c:out value="${assistenza.getSoluzione()}"/></textarea>
                        </c:if>
                        <c:if test="${assistenza.getStato() == 1}">
                            <textarea cols="100" rows="5" disabled><c:out value="${assistenza.getSoluzione()}"/></textarea>
                        </c:if>
                    </div>
                </div>
                
                <br>
                
                <hr/>
                
                <div>
                    <c:if test="${assistenza.getStato() == 0}">
                        <button class="btn btn-outline-primary buttonSpace" type="button" onclick="saveChangesAssistance(0)">SALVA</button>
                        <button class="btn btn-outline-primary buttonSpace" type="button" onclick="saveChangesAssistance(1)">SALVA E CHIUDI</button>
                    </c:if>
                    <c:if test="${assistenza.getStato() == 1}">
                        <button class="btn btn-outline-primary buttonSpace" type="button" onclick="window.history.back();">INDIETRO</button>
                    </c:if>
                </div>
            </div>
        </div>
            
        <div class="container">
            <hr>
            <%@include file="../Footer/footer.jsp" %>
        </div>
</html>
