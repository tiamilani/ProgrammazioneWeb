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
    
    <script src="${pageContext.request.contextPath}/jspFile/Finale/JS/Assistances.js"></script>
    <script src="${pageContext.request.contextPath}/jspFile/Finale/JS/fixFooter.js"></script>
    
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
                    <p><b>Nessun assistenza in corso.</b></p>
                </c:if>
                    
                <c:if test="${assistenzeAperte.size() > 0}">
                    <div class="row">
                        <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                            <p><b>Data apertura</b></p>
                        </div>
                        <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                            <p><b>Richiedente</b></p>
                        </div>
                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                            <p><b>Anteprima richiesta</b></p>
                        </div>
                    </div>
                    <c:forEach items="${assistenzeAperte}" var="assAperta">
                        <div class="row">
                            <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                                <p><c:out value="${assAperta.getL().getDataApertura()}"/></p>
                            </div>
                            <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                                <p><c:out value="${assAperta.getR()}"/></p>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                                <p><c:out value="${fn:substring(assAperta.getL().getRichiesta(), 0, 200)}"/> ...</p>
                            </div>
                            <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                                <button class="btn btn-outline-primary buttonSpace" type="button" data-idA="${assAperta.getL().getId()}" onclick="openAssistance(this)">RISOLVI</button>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                
                <hr/>
                
                <h3><b>Assistenze concluse</b></h3>
                
                <c:if test="${assistenzeChiuse.size() == 0}">
                    <p><b>Nessun assistenza conclusa.</b></p>
                </c:if>
                
                <c:if test="${assistenzeChiuse.size() > 0}">                    
                    <div class="row">
                        <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                            <p><b>Data apertura</b></p>
                        </div>
                        <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                            <p><b>Data chiusura</b></p>
                        </div>
                        <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                            <p><b>Richiedente</b></p>
                        </div>
                        <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                            <p><b>Anteprima richiesta</b></p>
                        </div>
                        <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                            <p><b>Anteprima soluzione</b></p>
                        </div>
                    </div>
                    <c:forEach items="${assistenzeChiuse}" var="assChiusa">
                        <div class="row">
                            <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                                <p><c:out value="${assChiusa.getL().getDataApertura()}"/></p>
                            </div>
                            <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                                <p><c:out value="${assChiusa.getL().getDataChiusura()}"/></p>
                            </div>
                            <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                                <p><c:out value="${assChiusa.getR()}"/></p>
                            </div>
                            <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                                <p><c:out value="${fn:substring(assChiusa.getL().getRichiesta(), 0, 20)}"/> ...</p>
                            </div>
                            <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                                <p><c:out value="${fn:substring(assChiusa.getL().getSoluzione(), 0, 20)}"/> ...</p>
                            </div>
                            <div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
                                <button class="btn btn-outline-primary buttonSpace" type="button" data-idA="${assChiusa.getL().getId()}" onclick="openAssistance(this)">VISUALIZZA</button>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
            
        <%@include file="../Footer/footer.jsp" %>
    </body>
</html>
