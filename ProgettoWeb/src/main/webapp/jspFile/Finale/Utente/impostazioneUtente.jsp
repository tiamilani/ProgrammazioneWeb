<%-- 
    Document   : impostazioneUtente
    Created on : 12-ott-2017, 15.18.34
    Author     : mattia
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title><c:out value="${utenteSessione.getNome()}" /></title>
    </head>
    <body>
        <%--<jsp:useBean id="indirizzi" class="it.progettoWeb.java.database.Model.indirizzo.ModelloListeIndirizzo" scope="request" />--%>
        
        <div class="container">
            <%@include file="../Header/NavBar/navBar.jsp" %>
        </div>
        
        <div class="container-fluid">
            <form>
                <div class="row">
                    <div class="float-sm-left">Aggiorna Email</div>
                    <div class="float-sm-right"><c:out value="${utenteSessione.getMail()}" /></div>
                </div>
            <div class="row">
                <form action="${pageContext.request.contextPath}/UserController" id="formMail" name="formMail" method="GET">
                    <input type="hidden" name="action" value="updateMail">
                    <div class="col-10">
                        <input type="email" class="form-control" id="changeEmail" name="changeEmail" placeholder="New Email">
                    </div>
                    <div class="col-2">
                        <button class="btn btn-outline-primary buttonSpace btn-block" type="submit" value="submit">Salva email</button>
                    </div>
                </form>
            </div>
            </form>
            <p>Modifica password</p>
            <form action="${pageContext.request.contextPath}/UserController" id="formPassword" name="formPassword" method="POST">
                <input type="hidden" name="action" value="updatePassword">
                <div class="row">
                    <div class="col-5">
                        <input type="password" class="form-control" id="inputPassword" name="newPassword" placeholder="New Password">
                    </div>
                    <div class="col-5">
                        <input type="password" class="form-control" id="inputPassword" name="newConfirmPassword" placeholder="Confirm New Password">
                    </div>
                    <div class="col-2">
                        <button class="btn btn-outline-primary buttonSpace btn-block" type="submit" value="submit">Salva password</button>
                    </div>
                </div>
            </form>
            <hr>
            <h2>Indirizzi</h2>
            <c:forEach items="${listaIndirizzi.getList()}" var="indirizzo" >
                <div class="row">
                    <div class="col-10">
                        <div class="row">
                            <h3><c:out value="${indirizzo.getCitta()}" /></h3>
                        </div>
                        <div class="row">
                            <p><c:out value="${indirizzo.getVia()}" /> <c:out value="${indirizzo.getnCivico()}" />, <c:out value="${indirizzo.getCitta()}" />, <c:out value="${indirizzo.getProvincia()}" /></p>
                        </div>
                    </div>
                    <div class="col-2">
                        <form action="${pageContext.request.contextPath}/IndirizzoController" id="formPassword" name="formIndirizzo" method="GET">
                            <input type="hidden" name="action" value="cancIndirizzo">
                            <input type="hidden" name="id" value="${indirizzo.getIdI()}">
                            <button class="btn btn-outline-primary buttonSpace btn-block" type="submit" value="submit">Canc</button>
                        </form>
                    </div>
                </div>
                <hr>
            </c:forEach>
            <button class="btn btn-outline-primary buttonSpace" type="submit" value="submit" data-toggle="modal"
                             data-target="#addAddrModal">addIndirizzo</button>
            <div id="addAddrModal" class="modal fade" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                      <div class="modal-header">
                        <h4 class="modal-title">Aggiungi un indirizzo</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                      </div>
                      <form id="addAddrForm" action="${pageContext.request.contextPath}/IndirizzoController" method="POST">
                          <input type="hidden" name="action" value="addAddr">
                        <div class="modal-body">
                            <div>
                                <input class="col-10 modal-input" type="text" id="regione" name="regione" required>
                                <label for="regione">regione</label>
                            </div>
                            <div>
                                <input class="col-10 modal-input" type="text" id="provincia" name="provincia" required>
                                <label for="provincia">Provincia</label>
                            </div>
                            <div>
                                <input class="col-10 modal-input" type="text" id="citta" name="citta" required>
                                <label for="citta">Citt&agrave;</label>
                            </div>
                            <div>
                                <input class="col-10 modal-input" type="text" id="via" name="via" required>
                                <label for="via">Via</label>
                            </div>
                            <div>
                                <input class="col-10 modal-input" type="text" id="nCivico" name="nCivico" required>
                                <label for="nCivico">N. Civico</label>
                            </div>
                            <div>
                                <input class="col-10 modal-input" type="text" id="interno" name="interno" required>
                                <label for="nCivico">Interno</label>
                            </div>
                            
                            <p>Gli indirizzi vengono considerati automaticamente in Italia<p>
                            <div id="html_element"></div>
                        </div>
                        <div class="modal-footer">
                          <button type="submit" class="col paddingNav btn btn-outline-primary">Aggiungi indirizzo</button>
                        </div>
                      </form>
                    </div>

                </div>
            </div>
            <br>
        </div>
        
        <div class="container">
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>