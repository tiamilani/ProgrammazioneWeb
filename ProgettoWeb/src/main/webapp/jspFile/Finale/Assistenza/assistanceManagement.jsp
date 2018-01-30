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
    <script>
        $(document).ready(function() {
                $('#tabellaAssistenzeAperte').DataTable({
                    responsive: true,
                    colReorder: true
                });
            } );
        $(document).ready(function() {
                $('#tabellaAssistenzeChiuse').DataTable({
                    responsive: true,
                    colReorder: true
                });
            } );
    </script>
    
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
                    
                    <table id="tabellaAssistenzeAperte" class="table table-striped table-bordered" width="100%" cellspacing="0">
                        <thead>
                            <tr><b>Data apertura</b></tr>
                            <tr><b>Richiedente</b></tr>
                            <tr><b>Anteprima richiesta</b></tr>
                            <td><b>Risolvi</b></td>
                        </thead>
                        <tfoot>
                            <tr><b>Data apertura</b></tr>
                            <tr><b>Richiedente</b></tr>
                            <tr><b>Anteprima richiesta</b></tr>
                            <td><b>Risolvi</b></td>
                        </tfoot>
                        
                        <tbody>
                            <c:forEach items="${assistenzeAperte}" var="assAperta">
                                <tr>
                                    <td><p><c:out value="${assAperta.getL().getDataApertura()}"/></p></td>
                                    <td><p><c:out value="${assAperta.getR()}"/></p></td>
                                    <td><p><c:out value="${fn:substring(assAperta.getL().getRichiesta(), 0, 200)}"/> ...</p></td>
                                    <td><button class="btn btn-outline-primary buttonSpace" type="button" data-idA="${assAperta.getL().getId()}" onclick="openAssistance(this)">RISOLVI</button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                
                <hr/>
                
                <h3><b>Assistenze concluse</b></h3>
                
                <c:if test="${assistenzeChiuse.size() == 0}">
                    <p><b>Nessun assistenza conclusa.</b></p>
                </c:if>
                    
                    
                <c:if test="${assistenzeChiuse.size() > 0}">  
                    <table id="tabellaAssistenzeChiuse" class="table table-striped table-bordered" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <td><b>Data apertura</b></td>
                                <td><b>Data chiusura</b></td>
                                <td><b>Richiedente</b></td>
                                <td><b>Anteprima richiesta</b></td>
                                <td><b>Anteprima soluzione</b></td>
                                <td><b>Visualizza</b></td>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <td><b>Data apertura</b></td>
                                <td><b>Data chiusura</b></td>
                                <td><b>Richiedente</b></td>
                                <td><b>Anteprima richiesta</b></td>
                                <td><b>Anteprima soluzione</b></td>
                                <td><b>Visualizza</b></td>
                            </tr>
                        </tfoot>
                    
                        <tbody>
                            <c:forEach items="${assistenzeChiuse}" var="assChiusa">
                                <tr>
                                    <td><p><c:out value="${assChiusa.getL().getDataApertura()}"/></p></td>
                                    <td><p><c:out value="${assChiusa.getL().getDataChiusura()}"/></p></td>
                                    <td><p><c:out value="${assChiusa.getR()}"/></p></td>
                                    <td><p><c:out value="${fn:substring(assChiusa.getL().getRichiesta(), 0, 20)}"/> ...</p></td>
                                    <td><p><c:out value="${fn:substring(assChiusa.getL().getSoluzione(), 0, 20)}"/> ...</p></td>
                                    <td><button class="btn btn-outline-primary buttonSpace" type="button" data-idA="${assChiusa.getL().getId()}" onclick="openAssistance(this)">VISUALIZZA</button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
        </div>
            
        <%@include file="../Footer/footer.jsp" %>
    </body>
</html>
