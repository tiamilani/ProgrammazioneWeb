<%-- 
    Document   : gestioneNegoziUtente
    Created on : 30-ott-2017, 15.46.26
    Author     : mattia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../Header/Head/GestioneNegoziHeader/gestioneNegozioHeader.jsp" %>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css">
        <title>Negozi <c:out value="${utenteSessione.getNome()}" /></title>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#tabellaNegozi').DataTable({
                    responsive: true,
                    colReorder: true
                });
            } );
        </script>
        <style>
            table.dataTable > tbody > tr.child ul.dtr-details{
                width: 100%;
            }
        </style>
    </head>
    <body style="padding-bottom: 0rem;">
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <c:choose>
                        <c:when test="${impossibileChiudereNegozio == 1}">
                            <div class="alert alert-danger alert-dismissable ">
                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                <strong>Attenzione!</strong> impossibile chiudere il negozio.
                            </div>
                        </c:when>
                        <c:when test="${impossibileAprireNegozio == 1}">
                            <div class="alert alert-danger alert-dismissable ">
                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                <strong>Attenzione!</strong> impossibile chiudere il negozio.
                            </div>
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                    <h1>Negozi</h1>
                    <p>
                        <c:out value="Questa è la lista dei tuoi negozi attuali." /><br>
                        <c:out value="Da qui puoi aggiungere negozi premendo sul pulsante sottostante, oppure modificare quelli attuali." /><br>
                        <c:out value="Puoi anche chiudere un negozio, ma non verrà eliminato per permettere di mantere lo storico degli acuisti e permetterà a noi amministratori di controllare eventuali irregolarita." />
                    </p>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <table id="tabellaNegozi" class="table table-striped table-bordered" width="100%" cellspacing="0">
                         <thead>
                            <tr>
                                <th>Nome negozio</th>
                                <th>Data apertura</th>
                                <th>Ordini ricevuti</th>
                                <th>Stato</th>
                                <th>Modifica</th>
                                <th>Elimina</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <th>Nome negozio</th>
                                <th>Data apertura</th>
                                <th>Ordini ricevuti</th>
                                <th>Stato</th>
                                <th>Modifica</th>
                                <th>Elimina</th>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:set var="i" scope="page" value="${0}" />
                            <c:forEach items="${listanegozi.getList()}" var="negozio" >
                                <tr>
                                    <td>
                                        <c:out value="${negozio.getNomeNegozio()}" />
                                    </td>
                                    <td>
                                        <c:out value="${negozio.getDataApertura()}" />
                                    </td>
                                    <td>
                                        <c:out value="${numeroOrdiniNegozi.get(i)}" />
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${negozio.getAttivo() == 1}">
                                                Attivo
                                            </c:when>
                                            <c:otherwise>
                                                Chiuso
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <c:choose>
                                        <c:when test="${negozio.getAttivo() == 1}">
                                            <td>
                                                <form action="${pageContext.request.contextPath}/NegozioController" id="formGestisciNegozio" name="formGestisciNegozio" method="POST">
                                                    <input type="hidden" name="action" value="gestisciNegozio">
                                                    <input type="hidden" name="id" value="${negozio.getId()}">
                                                    <button type="submit" value="submit" class="btn btn-outline-primary buttonSpace"><i class="Small material-icons">build</i></button>
                                                </form>
                                            </td>
                                            <td>
                                                <form action="${pageContext.request.contextPath}/NegozioController" id="formCancNegozio" name="formCancNegozio" method="POST">
                                                    <input type="hidden" name="action" value="cancNegozio">
                                                    <input type="hidden" name="id" value="${negozio.getId()}">
                                                    <button type="submit" value="submit" class="btn btn-outline-danger buttonSpace"><i class="Small material-icons">close</i></button>
                                                </form>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>
                                                <form action="${pageContext.request.contextPath}/NegozioController" id="formCancNegozio" name="formCancNegozio" method="POST">
                                                    <input type="hidden" name="action" value="apriNegozio">
                                                    <input type="hidden" name="id" value="${negozio.getId()}">
                                                    <button type="submit" value="submit" class="btn btn-outline-success buttonSpace"><i class="Small material-icons">lock_open</i> Riapri</button>
                                                </form>
                                            </td>
                                            <td></td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                                <c:set var="i" scope="page" value="${i+1}" />
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="col-3">
                        <a href="jspFile/Finale/Utente/aggiungiNegozio.jsp" method="GET" class="btn btn-outline-primary buttonSpace btn-block"><i class="Small material-icons">add</i></a>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../Footer/footer.jsp" %>
    </body>
</html>
