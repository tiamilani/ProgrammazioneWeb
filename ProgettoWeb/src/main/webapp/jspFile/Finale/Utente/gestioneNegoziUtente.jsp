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
    </head>
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <h1>Negozi</h1>
                    <div class="row">
                        <div class="col-6">
                            <p>Ordina per: </p>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-6">
                            <form id="formOptionOrderStore" name="formOptionOrderStore" action="${pageContext.request.contextPath}/UserController" method="GET">
                                <input type="hidden" id="action" name="action" value="gestisciNegozi">
                                <input type="hidden" id="order" name="order" value="${order}">
                                <div class="row">
                                    <c:choose>
                                        <c:when test="${orderStore == 'dataup'}">
                                            <select id="orderStore" name="orderStore" class="form-control" id="exampleFormControlSelect1" onchange="this.form.submit()">
                                                <option value="dataup"selected="selected">Per data di apertura più recente</option>
                                                <option value="datadown">Per data di apertura meno recente</option>
                                                <option value="nameup">Per nome a->z</option>
                                                <option value="namedown">Per nome z->a</option>
                                                <option value="prodottivendutiup">Vendite maggiori</option>
                                                <option value="prodottivendutidown">Vendite minori</option>
                                            </select>
                                        </c:when>
                                        <c:when test="${orderStore == 'datadown'}">
                                            <select id="orderStore" name="orderStore" class="form-control" id="exampleFormControlSelect1" onchange="this.form.submit()">
                                                <option value="dataup">Per data di apertura più recente</option>
                                                <option value="datadown" selected="selected">Per data di apertura meno recente</option>
                                                <option value="nameup">Per nome a->z</option>
                                                <option value="namedown">Per nome z->a</option>
                                                <option value="prodottivendutiup">Vendite maggiori</option>
                                                <option value="prodottivendutidown">Vendite minori</option>
                                            </select>
                                        </c:when>
                                        <c:when test="${orderStore == 'nameup'}">
                                            <select id="orderStore" name="orderStore" class="form-control" id="exampleFormControlSelect1" onchange="this.form.submit()">
                                                <option value="dataup" >Per data di apertura più recente</option>
                                                <option value="datadown">Per data di apertura meno recente</option>
                                                <option value="nameup" selected="selected">Per nome a->z</option>
                                                <option value="namedown">Per nome z->a</option>
                                                <option value="prodottivendutiup">Vendite maggiori</option>
                                                <option value="prodottivendutidown">Vendite minori</option>
                                            </select>
                                        </c:when>
                                        <c:when test="${orderStore == 'namedown'}">
                                            <select id="orderStore" name="orderStore" class="form-control" id="exampleFormControlSelect1" onchange="this.form.submit()">
                                                <option value="dataup">Per data di apertura più recente</option>
                                                <option value="datadown">Per data di apertura meno recente</option>
                                                <option value="nameup">Per nome a->z</option>
                                                <option value="namedown" selected="selected">Per nome z->a</option>
                                                <option value="prodottivendutiup">Vendite maggiori</option>
                                                <option value="prodottivendutidown">Vendite minori</option>
                                            </select>
                                        </c:when>
                                        <c:when test="${orderStore == 'prodottivendutiup'}">
                                            <select id="orderStore" name="orderStore" class="form-control" id="exampleFormControlSelect1" onchange="this.form.submit()">
                                                <option value="dataup">Per data di apertura più recente</option>
                                                <option value="datadown">Per data di apertura meno recente</option>
                                                <option value="nameup">Per nome a->z</option>
                                                <option value="namedown">Per nome z->a</option>
                                                <option value="prodottivendutiup" selected="selected">Vendite maggiori</option>
                                                <option value="prodottivendutidown">Vendite minori</option>
                                            </select>
                                        </c:when>
                                        <c:when test="${orderStore == 'prodottivendutidown'}">
                                            <select id="orderStore" name="orderStore" class="form-control" id="exampleFormControlSelect1" onchange="this.form.submit()">
                                                <option value="dataup">Per data di apertura più recente</option>
                                                <option value="datadown">Per data di apertura meno recente</option>
                                                <option value="nameup">Per nome a->z</option>
                                                <option value="namedown">Per nome z->a</option>
                                                <option value="prodottivendutiup">Vendite maggiori</option>
                                                <option value="prodottivendutidown" selected="selected">Vendite minori</option>
                                            </select>
                                        </c:when>
                                        <c:otherwise>
                                            <h2>No store selected</h2>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </form>
                        </div>
                    </div>
                    <hr>
                    <c:choose>
                        <c:when test="${orderStore == 'prodottivendutiup' || orderStore == 'prodottivendutidown'}">
                            <h2><p>I tuoi negozi</p></h2>
                            <c:set var="i" scope="page" value="${0}" />
                            <c:forEach items="${listanegozi.getList()}" var="negozio" >
                                    <div class="row">
                                        <div class="col-2">
                                            <p>Negozio:<br></p>
                                            <c:out value="${negozio.getNomeNegozio()}" />
                                        </div>
                                        <div class="col-2">
                                            <p>Aperto il:</p>
                                            <c:out value="${negozio.getDataApertura()}" />
                                        </div>
                                        <div class="col-2">
                                            <p>Ordini totali:</p>
                                            <c:out value="${numeroOrdiniNegozi.get(i)}" />
                                        </div>
                                        <div class="col-2">
                                            <p>Stato attuale:</p>
                                            <c:choose>
                                                <c:when test="${negozio.getAttivo() == 1}">
                                                    Attivo
                                                </c:when>
                                                <c:otherwise>
                                                    Chiuso
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <c:choose>
                                            <c:when test="${negozio.getAttivo() == 1}">
                                                <div class="col-2">
                                                    <form action="${pageContext.request.contextPath}/NegozioController" id="formGestisciNegozio" name="formGestisciNegozio" method="POST">
                                                        <input type="hidden" name="action" value="gestisciNegozio">
                                                        <input type="hidden" name="id" value="${negozio.getId()}">
                                                        <button type="submit" value="submit" class="btn btn-outline-primary buttonSpace btn-block"><i class="Small material-icons">build</i></button>
                                                    </form>
                                                </div>
                                                <div class="col-2">
                                                    <form action="${pageContext.request.contextPath}/NegozioController" id="formCancNegozio" name="formCancNegozio" method="POST">
                                                        <input type="hidden" name="action" value="cancNegozio">
                                                        <input type="hidden" name="id" value="${negozio.getId()}">
                                                        <button type="submit" value="submit" class="btn btn-outline-danger buttonSpace btn-block"><i class="Small material-icons">close</i></button>
                                                    </form>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="col-4">
                                                    <form action="${pageContext.request.contextPath}/NegozioController" id="formCancNegozio" name="formCancNegozio" method="POST">
                                                        <input type="hidden" name="action" value="apriNegozio">
                                                        <input type="hidden" name="id" value="${negozio.getId()}">
                                                        <button type="submit" value="submit" class="btn btn-outline-success buttonSpace btn-block"><i class="Small material-icons">lock_open</i> Riapri</button>
                                                    </form>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <hr>
                                <c:set var="i" scope="page" value="${i+1}" />
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <h2><p>I tuoi negozi</p></h2>
                            <c:set var="i" scope="page" value="${0}" />
                            <c:forEach items="${listanegozi.getList()}" var="negozio" >
                                    <div class="row">
                                        <div class="col-2">
                                            <p>Negozio:<br></p>
                                            <c:out value="${negozio.getNomeNegozio()}" />
                                        </div>
                                        <div class="col-2">
                                            <p>Aperto il:</p>
                                            <c:out value="${negozio.getDataApertura()}" />
                                        </div>
                                        <div class="col-2">
                                            <p>Ordini ricevuti:</p>
                                            <c:out value="${numeroOrdiniNegozi.get(i)}" />
                                        </div>
                                        <div class="col-2">
                                            <p>Stato attuale:</p>
                                            <c:choose>
                                                <c:when test="${negozio.getAttivo() == 1}">
                                                    Attivo
                                                </c:when>
                                                <c:otherwise>
                                                    Chiuso
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <c:choose>
                                            <c:when test="${negozio.getAttivo() == 1}">
                                                <div class="col-2">
                                                    <form action="${pageContext.request.contextPath}/NegozioController" id="formGestisciNegozio" name="formGestisciNegozio" method="POST">
                                                        <input type="hidden" name="action" value="gestisciNegozio">
                                                        <input type="hidden" name="id" value="${negozio.getId()}">
                                                        <button type="submit" value="submit" class="btn btn-outline-primary buttonSpace btn-block"><i class="Small material-icons">build</i></button>
                                                    </form>
                                                </div>
                                                <div class="col-2">
                                                    <form action="${pageContext.request.contextPath}/NegozioController" id="formCancNegozio" name="formCancNegozio" method="POST">
                                                        <input type="hidden" name="action" value="cancNegozio">
                                                        <input type="hidden" name="id" value="${negozio.getId()}">
                                                        <button type="submit" value="submit" class="btn btn-outline-danger buttonSpace btn-block"><i class="Small material-icons">close</i></button>
                                                    </form>
                                                </div>
                                            </c:when>
                                            <c:otherwise>
                                                <div class="col-4">
                                                    <form action="${pageContext.request.contextPath}/NegozioController" id="formCancNegozio" name="formCancNegozio" method="POST">
                                                        <input type="hidden" name="action" value="apriNegozio">
                                                        <input type="hidden" name="id" value="${negozio.getId()}">
                                                        <button type="submit" value="submit" class="btn btn-outline-success buttonSpace btn-block"><i class="Small material-icons">lock_open</i> Riapri</button>
                                                    </form>
                                                </div>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <hr>
                                <c:set var="i" scope="page" value="${i+1}" />
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    <div class="col-3">
                        <a href="jspFile/Finale/Utente/aggiungiNegozio.jsp" method="GET" class="btn btn-outline-primary buttonSpace btn-block"><i class="Small material-icons">add</i></a>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../Footer/footer.jsp" %>
    </body>
</html>
