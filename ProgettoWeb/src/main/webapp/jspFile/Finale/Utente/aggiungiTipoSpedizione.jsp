<%-- 
    Document   : aggiungiTipoSpedizione
    Created on : 14-gen-2018, 12.33.10
    Author     : mattia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/jspFile/Finale/CSS/form.css">
        <title><c:out value="${utenteSessione.getNome()}" /> Aggiungi spedizione</title>
    </head>
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <h2>Aggiunta Tipo di spedizione</h2>
                    <p>Aggiungendo un metodo di spedizione permetterai ai tuoi utenti che ordinano un oggetto di avere una consegna più veloce pagnado un extra oppure una consegna su appuntamento</p>
                </div>
            </div>
            <hr>
            <div class="row">
                <form class="col-12" id="aggiungiSpedizione" name="aggiungiSpedizione" method="POST" action="${pageContext.request.contextPath}/NegozioController">
                    <input type="hidden" name="action" value="addTipoSpedizione">
                    <input type="hidden" name="idNegozio" value="${negozio.getId()}">
                    <div class="row">
                        <div class="col-4">
                            <p>Nome della spedizione</p>
                            <input type="text" class="form-control" id="nomeSpedizione" name="nomeSpedizione" maxlength="50" placeholder="Spedizione standard" required>
                        </div>
                        <div class="col-4">
                            <p>Prezzo della spedizione</p>
                            <input type="text" class="form-control" id="prezzoSpedizione" name="prezzoSpedizione" placeholder="10.0" required>
                        </div>
                        <div class="col-4">
                            <p>Corriere che effettuer&aacute; la consegna</p>
                            <input type="text" class="form-control" id="corriereSpedizione" name="corriereSpedizione" maxlength="50" placeholder="Corriere" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-4">
                            <p>Giorni lavorativi per la consegna</p>
                            <input class="form-control" type="number" id="giorniConsegna" name="giorniConsegna" value="5" required>
                        </div>
                        <div class="col-8">
                            <p>Numero massimo di oggetti presenti per singola spedizione, oltre questa soglia verranno applicate nuovamente le spese di spedizione all'ordine</p>
                            <input class="form-control" type="number" id="numeroMassimo" name="numeroMassimo" value="5" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <button action="submit" type="submit" class="btn btn-outline-primary btn-block" id="btnModify">Aggiungi spedizione</button>
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


