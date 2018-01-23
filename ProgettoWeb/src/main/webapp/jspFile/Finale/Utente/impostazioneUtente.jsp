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
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&key=AIzaSyB7LZyOZzvwxSWSLr6cwFw9CpTPT2iIFiw" async defer></script>
        <script type="text/javascript">
           function showResult(result) {
                document.addAddrForm.latitudine.value = result.geometry.location.lat();
                document.addAddrForm.longitudine.value = result.geometry.location.lng();
                addAddrForm.action = "${pageContext.request.contextPath}/IndirizzoController";
                addAddrForm.submit();
            }

            function getLatitudeLongitude(callback, address) {
                // If adress is not supplied, use default value 'Ferrol, Galicia, Spain'
                address = address || 'Ferrol, Galicia, Spain';
                // Initialize the Geocoder
                geocoder = new google.maps.Geocoder();
                if (geocoder) {
                    geocoder.geocode({
                        'address': address
                    }, function (results, status) {
                        if (status === google.maps.GeocoderStatus.OK) {
                            callback(results[0]);
                        }
                    });
                }
            }

            function localizza(){
                var address = document.getElementById('nCivico').value + ', ' + document.getElementById('via').value + ', ' + document.getElementById('citta').value + ', ' + document.getElementById('provincia').value + ', ' + document.getElementById('regione').value;
                getLatitudeLongitude(showResult, address);
            }
            
        </script>
    </head>
    <body>
        <%--<jsp:useBean id="indirizzi" class="it.progettoWeb.java.database.Model.indirizzo.ModelloListeIndirizzo" scope="request" />--%>
        
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
        </div>
        
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <c:choose>
                        <c:when test="${aggiornamentoEmail == 0}">
                            <div class="alert alert-success alert-dismissable ">
                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                <strong>Successo!</strong> La tua mail è stata aggiornata con successo
                            </div>
                        </c:when>
                        <c:when test="${aggiornamentoEmail == 1}">
                            <div class="alert alert-warning alert-dismissable">
                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                <strong>Attenzione!</strong> Devi inserire una mail differente dalla precedente per aggiornarla
                            </div>
                        </c:when>
                        <c:when test="${resetPassword == 0}">
                            <div class="alert alert-success alert-dismissable ">
                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                <strong>Successo!</strong> Password aggiornata con successo
                            </div>
                        </c:when>
                        <c:when test="${resetPassword == 1}">
                            <div class="alert alert-warning alert-dismissable">
                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                <strong>Attenzione!</strong> La conferma della password non è uguale alla password
                            </div>
                        </c:when>
                        <c:when test="${resetPassword == 2}">
                            <div class="alert alert-warning alert-dismissable">
                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                <strong>Attenzione!</strong> La nuova password non può essere uguale a quella vecchia
                            </div>
                        </c:when>
                        <c:when test="${aggiuntaIndirizzo == 0}">
                            <div class="alert alert-success alert-dismissable ">
                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                <strong>Successo!</strong> Indirizzo aggiunto correttamente
                            </div>
                        </c:when>
                        <c:when test="${aggiuntaIndirizzo == 1}">
                            <div class="alert alert-warning alert-dismissable">
                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                <strong>Attenzione!</strong> Non puoi inserire due indirizzi uguali
                            </div>
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <form>
                <div class="row">
                    <p>Aggiorna Email <c:out value="${utenteSessione.getMail()}" /></p>
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
                            <p><c:out value="${indirizzo.getVia()}" /> <c:out value="${indirizzo.getnCivico()}" />, <c:out value="${indirizzo.getCitta()}" />, <c:out value="${indirizzo.getProvincia()}" />, <c:out value="${indirizzo.getRegione()}" /></p>
                        </div>
                    </div>
                    <div class="col-2">
                        <form action="${pageContext.request.contextPath}/IndirizzoController" id="formPassword" name="formIndirizzo" method="GET">
                            <input type="hidden" name="action" value="cancIndirizzo">
                            <input type="hidden" name="id" value="${indirizzo.getIdI()}">
                            <button class="btn btn-outline-danger buttonSpace btn-block" type="submit" value="submit"><i class="Small material-icons">close</i></button>
                        </form>
                    </div>
                </div>
                <hr>
            </c:forEach>
            <div class="row">
                <div class="col-2">
                    <button class="btn btn-outline-primary buttonSpace btn-block" type="submit" value="submit" data-toggle="modal" data-target="#addAddrModal"><i class="Small material-icons">add</i></button>
                </div>
            </div>
        </div>
        <%@include file="../Footer/footer.jsp" %>
    </body>
</html>

<div id="addAddrModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Aggiungi un indirizzo</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <form id="addAddrForm" name="addAddrForm" <%--action="${pageContext.request.contextPath}/IndirizzoController"--%> method="POST">
                <input type="hidden" name="action" value="addAddr">
                <div class="modal-body">
                    <div>
                        <p>regione</p>
                        <i class="large material-icons">person_outline</i>
                        <input class="col-10 modal-input" type="text" id="regione" name="regione" maxlength="255" required>
                    </div>
                    <div>
                        <p>Provincia</p>
                        <i class="large material-icons">person_outline</i>
                        <input class="col-10 modal-input" type="text" id="provincia" name="provincia" maxlength="255" required>
                    </div>
                    <div>
                        <p>Citt&agrave;</p>
                        <i class="large material-icons">person_outline</i>
                        <input class="col-10 modal-input" type="text" id="citta" name="citta" maxlength="255" required>
                    </div>
                    <div>
                        <p>Via</p>
                        <i class="large material-icons">person_outline</i>
                        <input class="col-10 modal-input" type="text" id="via" name="via" maxlength="255" required>
                    </div>
                    <div>
                        <p>N. Civico</p>
                        <i class="large material-icons">person_outline</i>
                        <input class="col-10 modal-input" type="number" min="1" id="nCivico" name="nCivico" required>
                    </div>
                    <div>
                        <p>Interno</p>
                        <i class="large material-icons">person_outline</i>
                        <input class="col-10 modal-input" type="number" min="1" id="interno" name="interno" required>
                    </div>
                    <div style="display: none;">
                        <input class="col-10 modal-input" type="text" id="latitudine" name="latitudine" required>
                        <p>Latitudine</p>
                    </div>
                    <div style="display: none;">
                        <input class="col-10 modal-input" type="text" id="longitudine" name="longitudine" required>
                        <p>longitudine</p>
                    </div>
                    <%--<button class="col paddingNav btn btn-outline-primary" onclick="localizza()">Calcola</button>--%>
                    <p>Gli indirizzi vengono considerati automaticamente in Italia<p>
                    <div id="html_element"></div>
                </div>
                <div class="modal-footer">
                    <button action="submit" type="submit" class="col paddingNav btn btn-outline-primary" id="btn" onclick="localizza()">Aggiungi indirizzo</button>
                </div>
            </form>
        </div>
    </div>
</div>