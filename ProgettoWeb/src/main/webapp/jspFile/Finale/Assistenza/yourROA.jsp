<%-- 
    Document   : yourROA
    Created on : Dec 30, 2017, 6:39:21 PM
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
    
    <script>
        function details(elem)
        {
            var index = elem.getAttribute("data-idA");
            document.location.href='/ProgettoWeb/AssistenzaController?action=details&id=' + index;
        };
        
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
        $(document).ready(function() {
                $('#assistenzeAperteVenditore').DataTable({
                    responsive: true,
                    colReorder: true
                });
            } );
        $(document).ready(function() {
                $('#assistenzeChiuseVenditore').DataTable({
                    responsive: true,
                    colReorder: true
                });
            } );
    </script>
    <script src="${pageContext.request.contextPath}/jspFile/Finale/JS/fixFooter.js"></script>
    
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
            <hr/>
        </div>
            
        <div class="container-fluid">
            <h2><b>LE TUE RICHIESTE DI ASSISTENZA</b></h2>
            
            <div>
                <h3><b>Assistenze in attesa di risposta</b></h3>
                
                <c:if test="${assistenzeAperte.size() == 0}">
                    <span><b>Nessun assistenza in corso.</b></span>
                </c:if>
                    
                <c:if test="${assistenzeAperte.size() > 0}">
                    <table id="tabellaAssistenzeAperte" class="table table-striped table-bordered" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <td><b>Data apertura</b></td>
                                <td><b>Oggetto</b></td>
                                <td><b>Anteprima richiesta</b></td>
                                <td><b>Dettagli</b></td>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <td><b>Data apertura</b></td>
                                <td><b>Oggetto</b></td>
                                <td><b>Anteprima richiesta</b></td>
                                <td><b>Dettagli</b></td>
                            </tr>
                        </tfoot>
                    
                        <tbody>
                            <c:forEach items="${assistenzeAperte}" var="assAperta">
                                <tr>
                                    <td><c:out value="${assAperta.getL().getDataApertura()}"/></td>
                                    <td><c:out value="${assAperta.getR()}"/></td>
                                    <td><c:out value="${fn:substring(assAperta.getL().getRichiesta(), 0, 200)}"/> ...</td>
                                    <td><button class="btn btn-outline-primary buttonSpace" type="button" data-idA="${assAperta.getL().getId()}" onclick="details(this)">DETTAGLI</button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                
                <hr/>
                
                <h3><b>Assistenze concluse</b></h3>
                
                <c:if test="${assistenzeChiuse.size() == 0}">
                    <span><b>Nessun assistenza conclusa.</b></span>
                </c:if>
                
                <c:if test="${assistenzeChiuse.size() > 0}">  
                    <table id="tabellaAssistenzeChiuse" class="table table-striped table-bordered" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <td><b>Data apertura</b></td>
                                <td><b>Data chiusura</b></td>
                                <td><b>Oggetto</b></td>
                                <td><b>Anteprima richiesta</b></td>
                                <td><b>Anteprima soluzione</b></td>
                                <td><b>Dettagli</b></td>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <td><b>Data apertura</b></td>
                                <td><b>Data chiusura</b></td>
                                <td><b>Oggetto</b></td>
                                <td><b>Anteprima richiesta</b></td>
                                <td><b>Anteprima soluzione</b></td>
                                <td><b>Dettagli</b></td>
                            </tr>
                        </tfoot>
                    
                        <tbody>
                            <c:forEach items="${assistenzeChiuse}" var="assChiusa">
                                <tr>
                                    <td><c:out value="${assChiusa.getL().getDataApertura()}"/></td>
                                    <td><c:out value="${assChiusa.getL().getDataChiusura()}"/></td>
                                    <td><c:out value="${assChiusa.getR()}"/></td>
                                    <td><c:out value="${fn:substring(assChiusa.getL().getRichiesta(), 0, 200)}"/> ...</td>
                                    <td><c:out value="${fn:substring(assChiusa.getL().getSoluzione(), 0, 20)}"/> ...</td>
                                    <td><button class="btn btn-outline-primary buttonSpace" type="button" data-idA="${assChiusa.getL().getId()}" onclick="details(this)">DETTAGLI</button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
            
            <hr/>
            
            <c:if test="${isVenditore == 1}">
            
                <h2><b>RICHIESTE DI ASSISTENZA IN CUI SEI STATO CITATO</b></h2>

                <div>
                    <h3><b>Assistenze in attesa di risposta</b></h3>

                    <c:if test="${assistenzeAperteVenditore.size() == 0}">
                        <span><b>Nessun assistenza in corso.</b></span>
                    </c:if>

                    <c:if test="${assistenzeAperteVenditore.size() > 0}">
                        <table id="assistenzeAperteVenditore" class="table table-striped table-bordered" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <td><b>Data apertura</b></td>
                                    <td><b>Oggetto</b></td>
                                    <td><b>Anteprima richiesta</b></td>
                                    <td><b>Dettagli</b></td>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <td><b>Data apertura</b></td>
                                    <td><b>Oggetto</b></td>
                                    <td><b>Anteprima richiesta</b></td>
                                    <td><b>Dettagli</b></td>
                                </tr>
                            </tfoot>

                            <tbody>
                                <c:forEach items="${assistenzeAperteVenditore}" var="assAperta">
                                    <tr>
                                        <td><c:out value="${assAperta.getL().getDataApertura()}"/></td>
                                        <td><c:out value="${assAperta.getR()}"/></td>
                                        <td><c:out value="${fn:substring(assAperta.getL().getRichiesta(), 0, 200)}"/> ...</td>
                                        <td><button class="btn btn-outline-primary buttonSpace" type="button" data-idA="${assAperta.getL().getId()}" onclick="details(this)">DETTAGLI</button></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>

                    <hr/>

                    <h3><b>Assistenze concluse</b></h3>

                    <c:if test="${assistenzeChiuseVenditore.size() == 0}">
                        <span><b>Nessun assistenza conclusa.</b></span>
                    </c:if>

                    <c:if test="${assistenzeChiuseVenditore.size() > 0}"> 
                        <table id="assistenzeChiuseVenditore" class="table table-striped table-bordered" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <td><b>Data apertura</b></td>
                                    <td><b>Data chiusura</b></td>
                                    <td><b>Oggetto</b></td>
                                    <td><b>Anteprima richiesta</b></td>
                                    <td><b>Anteprima soluzione</b></td>
                                    <td><b>Dettagli</b></td>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <td><b>Data apertura</b></td>
                                    <td><b>Data chiusura</b></td>
                                    <td><b>Oggetto</b></td>
                                    <td><b>Anteprima richiesta</b></td>
                                    <td><b>Anteprima soluzione</b></td>
                                    <td><b>Dettagli</b></td>
                                </tr>
                            </tfoot>

                            <tbody>
                                <c:forEach items="${assistenzeChiuseVenditore}" var="assChiusa">
                                    <tr>
                                        <td><c:out value="${assChiusa.getL().getDataApertura()}"/></td>
                                        <td><c:out value="${assChiusa.getL().getDataChiusura()}"/></td>
                                        <td><c:out value="${assChiusa.getR()}"/></td>
                                        <td><c:out value="${fn:substring(assChiusa.getL().getRichiesta(), 0, 200)}"/> ...</td>
                                        <td><c:out value="${fn:substring(assChiusa.getL().getSoluzione(), 0, 20)}"/> ...</td>
                                        <td><button class="btn btn-outline-primary buttonSpace" type="button" data-idA="${assChiusa.getL().getId()}" onclick="details(this)">DETTAGLI</button></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>
            </c:if>
        </div>
            
        <%@include file="../Footer/footer.jsp" %>
    </body>
</html>

