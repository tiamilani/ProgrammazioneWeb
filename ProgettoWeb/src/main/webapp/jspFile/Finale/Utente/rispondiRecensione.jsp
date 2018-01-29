<%-- 
    Document   : rispondiRecensione
    Created on : 29-gen-2018, 11.49.19
    Author     : andreafadi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="listaRecensioniV" class="it.progettoWeb.java.database.Model.recensioneVenditore.ModelloListeRecensioneVenditore" scope="request" />
<jsp:useBean id="listaRecensioniO" class="it.progettoWeb.java.database.Model.recensioneOggetto.ModelloListeRecensioneOggetto" scope="request" />
<jsp:useBean id="listaRecensioniN" class="it.progettoWeb.java.database.Model.recensioneNegozio.ModelloListeRecensioneNegozio" scope="request" />
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <title>Recensioni Personali</title>
        <script>
            $(document).ready(function() {
                $('#tabellaRecensioni').DataTable({
                    responsive: true,
                    colReorder: true
                });
            });
            
            function setIDN(idR) {
                $('#idRecensioneN').val(idR);
            }
            
            function setIDO(idO) {
                $('#idRecensioneO').val(idO);
            }
            
            function setIDV(idV) {
                $('#idRecensioneV').val(idV);
            }
        </script>
    </head>
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
        </div>
        
        <div class="container-fluid">
            <c:if test="${listaRecensioniV.getList().size() > 0}">
                <h2>Venditore</h2>
                <div class="row">
                    <div class="col-12">
                        <table id="tabellaRecensioni" class="table table-striped table-bordered" width="100%" cellspacing="0">
                             <thead>
                                <tr>
                                    <th>Data</th>
                                    <th>Valutazione</th>
                                    <th>Testo</th>
                                    <th>Aggiungi</th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <th>Data</th>
                                    <th>Valutazione</th>
                                    <th>Testo</th>
                                    <th>Aggiungi</th>
                                </tr>
                            </tfoot>
                            <tbody>
                                <c:forEach var="i" begin="${0}" end="${listaRecensioniV.getList().size() -1}">
                                    <tr>
                                        <td>
                                            <c:out value="${listaRecensioniV.get(i).getData()}" />
                                        </td>
                                        <td>
                                            <c:out value="${listaRecensioniV.get(i).getValutazione()}" />
                                        </td>
                                        <td>
                                            <c:out value="${listaRecensioniV.get(i).getTesto()}" />
                                        </td>
                                        <td>
                                            <button action="submit" type="submit" class="btn btn-outline-primary buttonSpace" data-toggle="modal" onclick="setIDV(${listaRecensioniV.get(i).getId()})" data-target="#addReviewModalV"><i class="Small material-icons">add</i></button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
            <c:if test="${listaRecensioniO.getList().size() > 0}">
                <h2>Oggetti</h2>
                <div class="row">
                    <div class="col-12">
                        <table id="tabellaRecensioni" class="table table-striped table-bordered" width="100%" cellspacing="0">
                             <thead>
                                <tr>
                                    <th>Data</th>
                                    <th>Valutazione</th>
                                    <th>Testo</th>
                                    <th>Aggiungi</th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <th>Data</th>
                                    <th>Valutazione</th>
                                    <th>Testo</th>
                                    <th>Aggiungi</th>
                                </tr>
                            </tfoot>
                            <tbody>
                                <c:forEach var="i" begin="${0}" end="${listaRecensioniO.getList().size() -1}">
                                    <tr>
                                        <td>
                                            <c:out value="${listaRecensioniO.get(i).getData()}" />
                                        </td>
                                        <td>
                                            <c:out value="${listaRecensioniO.get(i).getValutazione()}" />
                                        </td>
                                        <td>
                                            <c:out value="${listaRecensioniO.get(i).getTesto()}" />
                                        </td>
                                        <td>
                                            <button action="submit" type="submit" class="btn btn-outline-primary buttonSpace" data-toggle="modal" onclick="setIDO(${listaRecensioniO.get(i).getId()})" data-target="#addReviewModalO"><i class="Small material-icons">add</i></button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
            <c:if test="${listaRecensioniN.getList().size() > 0}">
                <h2>Negozi</h2>
                <div class="row">
                    <div class="col-12">
                        <table id="tabellaRecensioni" class="table table-striped table-bordered" width="100%" cellspacing="0">
                             <thead>
                                <tr>
                                    <th>Data</th>
                                    <th>Valutazione</th>
                                    <th>Testo</th>
                                    <th>Aggiungi</th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <th>Data</th>
                                    <th>Valutazione</th>
                                    <th>Testo</th>
                                    <th>Aggiungi</th>
                                </tr>
                            </tfoot>
                            <tbody>
                                <c:forEach var="i" begin="${0}" end="${listaRecensioniN.getList().size() -1}">
                                    <tr>
                                        <td>
                                            <c:out value="${listaRecensioniN.get(i).getData()}" />
                                        </td>
                                        <td>
                                            <c:out value="${listaRecensioniN.get(i).getValutazione()}" />
                                        </td>
                                        <td>
                                            <c:out value="${listaRecensioniN.get(i).getTesto()}" />
                                        </td>
                                        <td>
                                            <button action="submit" type="submit" class="btn btn-outline-primary buttonSpace" data-toggle="modal" onclick="setIDN(${listaRecensioniN.get(i).getId()})" data-target="#addReviewModalN"><i class="Small material-icons">add</i></button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
        </div>
        <%@include file="../Footer/footer.jsp" %>
    </body>
</html>

<div id="addReviewModalN" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Aggiungi una Risposta</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <form id="formNegozio" name="formNegozio" method="POST" action="${pageContext.request.contextPath}/RispondiRecensioneController">
                <input type="hidden" name="action" value="Negozio"/>
                <div class="modal-body">
                    <input type="hidden" name="idRecensioneN" id="idRecensioneN" value="">
                    <div>
                        <p>Testo</p>
                        <i class="large material-icons">person_outline</i>
                        <input class="col-10 modal-input" type="text" id="testoN" name="testoN" required>
                    </div>
                    <div id="html_element"></div>
                </div>
                <div class="modal-footer">
                    <button action="submit" type="submit" class="col paddingNav btn btn-outline-primary">Aggiungi Risposta</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="addReviewModalO" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Aggiungi una Risposta</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <form id="formOggetto" name="formOggetto" method="POST" action="${pageContext.request.contextPath}/RispondiRecensioneController">
                <input type="hidden" name="action" value="Oggetto"/>
                <div class="modal-body">
                    <input type="hidden" name="idRecensioneO" id="idRecensioneO" value="">
                    <div>
                        <p>Testo</p>
                        <i class="large material-icons">person_outline</i>
                        <input class="col-10 modal-input" type="text" id="testoO" name="testoO" required>
                    </div>
                    <div id="html_element"></div>
                </div>
                <div class="modal-footer">
                    <button action="submit" type="submit" class="col paddingNav btn btn-outline-primary">Aggiungi Risposta</button>
                </div>
            </form>
        </div>
    </div>
</div>

<div id="addReviewModalV" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Aggiungi una Risposta</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <form id="formVenditore" name="formVenditore" method="POST" action="${pageContext.request.contextPath}/RispondiRecensioneController">
                <input type="hidden" name="action" value="Venditore"/>
                <div class="modal-body">
                    <input type="hidden" name="idRecensioneV" id="idRecensioneV" value="">
                    <div>
                        <p>Testo</p>
                        <i class="large material-icons">person_outline</i>
                        <input class="col-10 modal-input" type="text" name="testoV" id="testoV" required>
                    </div>
                    <div id="html_element"></div>
                </div>
                <div class="modal-footer">
                    <button action="submit" type="submit" class="col paddingNav btn btn-outline-primary">Aggiungi Risposta</button>
                </div>
            </form>
        </div>
    </div>
</div>