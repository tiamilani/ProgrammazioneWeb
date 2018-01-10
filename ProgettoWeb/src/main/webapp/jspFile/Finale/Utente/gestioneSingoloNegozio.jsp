<%-- 
    Document   : gestioneSingoloNegozio
    Created on : 30-dic-2017, 10.46.30
    Author     : mattia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/jspFile/Finale/CSS/form.css">
        <title><c:out value="${utenteSessione.getNome()}" /> Gestione Negozio</title>
        <script type="text/javascript">
            function elimina() {
                document.modificaOggetto.action.value = "deleteObject";
                modificaOggetto.submit();
            }
        </script>
    </head>
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-2">
                    <img id="imgNegozio" class="d-block w-100" src="${immagine.getSrc()}" alt="IMAGE NOT LOADED" style="width: auto; max-height: 500px; object-fit: cover;">
                </div>
                <div class="col-10">
                    <h1><c:out value="${negozio.getNomeNegozio()}" /></h1>
                    <p>Da qui puoi gestire tutto ciò che riguarda il tuo negozio, a partire dagli ordini ricevuti all'aggiunta di oggetti al catalogo</p>
                    <div class="text-justify">
                        <h4><a href="${negozio.getLinkSito()}">${negozio.getLinkSito()}</a></h4><br/>
                        <h5>${indirizzo.getVia()} ${indirizzo.getnCivico()}, ${indirizzo.getCitta()}, ${indirizzo.getProvincia()}, ${indirizzo.getRegione()}, ${indirizzo.getStato()}</h5><br/>
                    </div>
                </div>
            </div>
            <hr>
            <div class="row">
                <div class="col-12">
                    <h2>Lista degli oggetti attualmente nel catalogo</h2>
                    <c:choose>
                        <c:when test="${oggettoInserito == 0}">
                            <div class="alert alert-success">
                                <strong>Successo!</strong> oggetto inserito con successo.
                            </div>
                        </c:when>
                        <c:when test="${oggettoInserito == 1}">
                            <div class="alert alert-danger">
                                <strong>Attenzione!</strong> oggetto non inserito.
                            </div>
                        </c:when>
                        <c:when test="${oggettoModificato == 0}">
                            <div class="alert alert-success">
                                <strong>Successo!</strong> modifica del catalogo terminata con successo.
                            </div>
                        </c:when>
                        <c:when test="${oggettoModificato == 1}">
                            <div class="alert alert-danger">
                                <strong>Attenzione!</strong> modifica al catalogo non apportata a causa di un errore.
                            </div>
                        </c:when>
                        <c:when test="${oggettoModificato == 2}">
                            <div class="alert alert-warning">
                                <strong>Attenzione!</strong> Devi prima selezionare un oggetto da modificare tra quelli disponibili.
                            </div>
                        </c:when>
                        <c:when test="${oggettoModificato == 3}">
                            <div class="alert alert-warning">
                                <strong>Attenzione!</strong> Devi scrivere il nome dell'oggetto he vuoi eliminare nella textbox specifica
                            </div>
                        </c:when>
                        <c:when test="${oggettoModificato == 4}">
                            <div class="alert alert-danger">
                                <strong>Errore!</strong> Il nome da te inserito non corrisponde a quello dell'oggetto
                            </div>
                        </c:when>
                        <c:when test="${oggettoModificato == 5}">
                            <div class="alert alert-success">
                                <strong>Successo!</strong> Operazione di eliminazione portata a termine con successo
                            </div>
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                </div>
                <hr>
                <c:forEach items="${listaOggeti.getList()}" var="oggetto" >
                    <div class="col-12">
                        <div class="row">
                            <div class="col-3">
                                <p>
                                    Nome<br>
                                    <c:out value="${oggetto.getNome()}" /><br>
                                    <c:out value="${oggetto.getId()}" />
                                </p>
                            </div>
                            <div class="col-2">
                                <p>
                                    Prezzo<br>
                                    <c:out value="${oggetto.getPrezzo()}" />&euro;
                                </p>
                            </div>
                            <div class="col-2">
                                <p>
                                    Disponibilit&aacute;<br>
                                    <c:out value="${oggetto.getDisponibilita()}" />
                                </p>
                            </div>
                            <div class="col-1">
                                <p>
                                    Sconto<br>
                                    <c:out value="${oggetto.getSconto()}" />
                                </p>
                            </div>
                            <div class="col-2">
                                <a href="${pageContext.request.contextPath}/NegozioController?action=richiestaPaginaDiModificaOggetto&id=<c:out value="${oggetto.getId()}" />" method="GET" class="btn btn-outline-primary buttonSpace btn-block"><i class="Small material-icons">build</i></a>
                            </div>
                            <div class="col-2">
                                <button type="button" class="btn btn-outline-danger buttonSpace btn-block" data-toggle="modal" data-target="#deleteItemModal<c:out value="${oggetto.getId()}" />">
                                    <i class="Small material-icons">close</i>
                                </button>
                                <div id="deleteItemModal<c:out value="${oggetto.getId()}" />" class="modal fade" role="dialog">
                                    <div class="modal-dialog">

                                        <!-- Modal content-->
                                        <div class="modal-content">
                                          <div class="modal-header">
                                            <h4 class="modal-title">Eliminazione oggetto</h4>
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                          </div>
                                            <div class="row">
                                                <div class="col-2"></div>
                                                <div class="col-8">
                                                    <p>Per eliminare l'oggetto inserisci il nome dell'oggetto che vuoi eliminare per confermare la tua volontà di eliminarlo</p>
                                                </div>
                                                <div class="col-2"></div>
                                            </div>
                                            <form action="${pageContext.request.contextPath}/NegozioController" method="GET">
                                                <input type="hidden" name="action" value="deleteObject">
                                                <input type="hidden" name="idNegozio" value="${negozio.getId()}">
                                                <input type="hidden" name="modifyObject" value="<c:out value="${oggetto.getId()}" />">
                                                <div class="modal-body">
                                                    <div>
                                                        <input class="col-10 modal-input" type="text" id="modifyDelete" name="modifyDelete" required>
                                                        <label for="modifyDelete">Nome oggetto da eliminare</label>
                                                    </div>

                                                </div>
                                                <div class="modal-footer">
                                                    <button action="submit" type="submit" class="col-4 paddingNav btn btn-outline-danger btn-block my-2 my-sm-0" id="btnDelete">Elimina oggetto</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <hr>
                    </div>
                </c:forEach>
                <div class="col-3">
                    <a href="${pageContext.request.contextPath}/NegozioController?action=richiestaPaginaDiAggiuntaOggetto" method="GET" class="btn btn-outline-primary buttonSpace btn-block"><i class="Small material-icons">add</i></a>
                </div>
            </div>
            <hr>
        </div>
        <div class="container">
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>

