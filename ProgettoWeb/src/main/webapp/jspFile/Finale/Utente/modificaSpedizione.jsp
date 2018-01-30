<%-- 
    Document   : modificaOggetto
    Created on : 10-gen-2018, 22.13.46
    Author     : mattia
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/jspFile/Finale/CSS/form.css">
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>        
        <title><c:out value="${utenteSessione.getNome()}" /> Modifica spedizione</title>
    </head>
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <h2>Modifica Tipo di spedizione</h2>
                    <p>Qui puoi modificare le informazioni della spedizione in ogni sua parte, dal nome al prezzo, agli oggetti massimi che contiene un singolo pacco
                </div>
            </div>
            <hr>
            <div class="row">
                <form class="col-12" id="modificaTipoSpedizione" name="modificaTipoSpedizione" method="POST" action="${pageContext.request.contextPath}/NegozioController">
                    <input type="hidden" name="action" value="modifyTipoSpedizione">
                    <input type="hidden" name="idNegozio" value="${negozio.getId()}">
                    <input type="hidden" id="modifyTipoSpedizione" name="modifyTipoSpedizione" value="${spedizione.getIdS()}">
                    <div class="row">
                        <div class="col-4">
                            <p>Modifca il nome attuale della spedizione</p>
                            <input type="text" class="form-control" id="mdifyNomeSpedizione" name="mdifyNomeSpedizione" maxlength="50" value="${spedizione.getNome()}" required>
                        </div>
                        <div class="col-4">
                            <p>Modifica il prezzo attuale della spedizione</p>
                            <input type="text" class="form-control" id="mdifyPrezzoSpedizione" name="mdifyPrezzoSpedizione" value="${spedizione.getPrezzo()}" required>
                        </div>
                        <div class="col-4">
                            <p>Modifca il nome del corriere che effettuer&aacute; la consegna</p>
                            <input type="text" class="form-control" id="mdifyCorriereSpedizione" name="mdifyCorriereSpedizione" maxlength="50" value="${spedizione.getCorriere()}" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-4">
                            <p>Giorni lavorativi per la consegna</p>
                            <input class="form-control" type="number" id="modifyGiorniConsegna" name="modifyGiorniConsegna" value="${spedizione.getTempoRichiesto()}" required>
                        </div>
                        <div class="col-8">
                            <p>Numero massimo di oggetti presenti per singola spedizione, oltre questa soglia verranno applicate nuovamente le spese di spedizione all'ordine</p>
                            <input class="form-control" type="number" id="modifyNumeroMassimo" name="modifyNumeroMassimo" value="${spedizione.getNumeroMassimo()}" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <button action="submit" type="submit" class="btn btn-outline-primary btn-block" id="btnModify">Aggiorna spedizione</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="container">
            <%@include file="../Footer/footer.jsp" %>
        </div>
    </body>
</html>

