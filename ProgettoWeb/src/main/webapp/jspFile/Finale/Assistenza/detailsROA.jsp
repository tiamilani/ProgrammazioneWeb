<%-- 
    Document   : detailsROA
    Created on : Dec 30, 2017, 7:20:46 PM
    Author     : FBrug
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/jspFile/Finale/CSS/textareaAssistance.css">
        <title>Assistance Management</title>
    </head>
    
    <script src="${pageContext.request.contextPath}/jspFile/Finale/JS/fixFooter.js"></script>
    
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
            <hr/>
        </div>
            
        <div class="container-fluid">
            <h2><b>RICHIESTA DI ASSISTENZA N° <c:out value="${assistenza.getId()}"/></b></h2>
            
            <div>
                <div class="row">
                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                        <span>Data di apertura</span>
                    </div>
                    <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                        <span><c:out value="${assistenza.getDataApertura()}"/></span>
                    </div>
                </div>
                
                <br>
                
                <div class="row">
                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                        <span>Data di chiusura</span>
                    </div>
                    <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                        <span><c:out value="${assistenza.getDataChiusura()}"/></span>
                    </div>
                </div>
                
                <br>
                
                <c:if test="${isVenditore == 0}">
                    <div class="row">
                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                            <span>Venditore contestato</span>
                        </div>
                        <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                            <span>
                                <c:out value="${venditoreContestato.getCognome()}"/> 
                                <c:out value="${venditoreContestato.getNome()}"/>
                            </span>
                        </div>
                    </div>
                </c:if>
                
                <c:if test="${isVenditore == 1}">
                    <div class="row">
                        <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                            <span>Utente contestatore</span>
                        </div>
                        <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                            <span>
                                <c:out value="${utenteContestatore.getCognome()}"/> 
                                <c:out value="${utenteContestatore.getNome()}"/>
                            </span>
                        </div>
                    </div>
                </c:if>
                
                <br>
                
                <div class="row">
                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                        <span>Oggetto contestato</span>
                    </div>
                    <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                        <span>
                            <c:out value="${oggettoContestato.getNome()}"/>
                        </span>
                    </div>
                </div>
                
                <br>
                
                <div class="row">
                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                        <span>ID Ordine contestato</span>
                    </div>
                    <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                        <span><c:out value="${ordineContestato}"/></span>
                    </div>
                </div>
                
                <br>
                
                <div class="row">
                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                        <span>Testo della richiesta</span>
                    </div>
                    <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                        <span><c:out value="${assistenza.getRichiesta()}"/></span>
                    </div>
                </div>
                
                <br>
                
                <div class="row">
                    <div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
                        <span>Testo della soluzione</span>
                    </div>
                    <div class="col-xs-9 col-sm-9 col-md-9 col-lg-9">
                        <textarea cols="100" rows="5" disabled><c:out value="${assistenza.getSoluzione()}"/></textarea>
                    </div>
                </div>
                
                <br>
                
                <hr/>
                
                <div>
                    <button class="btn btn-outline-primary buttonSpace" type="button" onclick="window.history.back();">INDIETRO</button>
                </div>
            </div>
        </div>
        
        <%@include file="../Footer/footer.jsp" %>
</html>