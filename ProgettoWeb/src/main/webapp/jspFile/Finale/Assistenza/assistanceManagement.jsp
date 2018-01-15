<%-- 
    Document   : assistanceManagement
    Created on : Dec 24, 2017, 10:31:00 PM
    Author     : FBrug
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
            <h2><b>GESTIONE ASSISTENZE</b></h2>
            
            <div>
                <h3><b>Assistenze in attesa di risposta</b></h3>
                
                <c:if test="${assistenzeAperte.size() == 0}">
                    <span><b>Nessun assistenza in corso.</b></span>
                </c:if>
                    
                <c:if test="${assistenzeAperte.size() > 0}">
                    <div class="row">
                        <div class="col-2">
                            <span><b>Data apertura</b></span>
                        </div>
                        <div class="col-2">
                            <span><b>Richiedente</b></span>
                        </div>
                        <div class="col-6">
                            <span><b>Anteprima richiesta</b></span>
                        </div>
                    </div>
                    <c:forEach items="${assistenzeAperte}" var="assAperta">
                        <div class="row">
                            <div class="col-2">
                                <c:out value="${assAperta.getL().getDataApertura()}"/>
                            </div>
                            <div class="col-2">
                                <c:out value="${assAperta.getR()}"/>
                            </div>
                            <div class="col-6">
                                <c:out value="${fn:substring(assAperta.getL().getRichiesta(), 0, 200)}"/> ...
                            </div>
                            <div class="col-2">
                                <button class="btn btn-outline-primary buttonSpace" type="button" data-idA="${assAperta.getL().getId()}" onclick="openAssistance(this)">RISOLVI</button>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                
                <hr/>
                
                <h3><b>Assistenze concluse</b></h3>
                
                <c:if test="${assistenzeChiuse.size() == 0}">
                    <span><b>Nessun assistenza conclusa.</b></span>
                </c:if>
                
                <c:if test="${assistenzeChiuse.size() > 0}">                    
                    <div class="row">
                        <div class="col-2">
                            <span><b>Data apertura</b></span>
                        </div>
                        <div class="col-2">
                            <span><b>Data chiusura</b></span>
                        </div>
                        <div class="col-2">
                            <span><b>Richiedente</b></span>
                        </div>
                        <div class="col-2">
                            <span><b>Anteprima richiesta</b></span>
                        </div>
                        <div class="col-2">
                            <span><b>Anteprima soluzione</b></span>
                        </div>
                    </div>
                    <c:forEach items="${assistenzeChiuse}" var="assChiusa">
                        <div class="row">
                            <div class="col-2">
                                <c:out value="${assChiusa.getL().getDataApertura()}"/>
                            </div>
                            <div class="col-2">
                                <c:out value="${assChiusa.getL().getDataChiusura()}"/>
                            </div>
                            <div class="col-2">
                                <c:out value="${assChiusa.getR()}"/>
                            </div>
                            <div class="col-2">
                                <c:out value="${fn:substring(assChiusa.getL().getRichiesta(), 0, 20)}"/> ...
                            </div>
                            <div class="col-2">
                                <c:out value="${fn:substring(assChiusa.getL().getSoluzione(), 0, 20)}"/> ...
                            </div>
                            <div class="col-2">
                                <button class="btn btn-outline-primary buttonSpace" type="button" data-idA="${assChiusa.getL().getId()}" onclick="openAssistance(this)">VISUALIZZA</button>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
            
        <div class="container">
            <hr>
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>
