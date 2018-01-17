<%-- 
    Document   : aggiungiNegozio
    Created on : 10-dic-2017, 11.21.44
    Author     : mattia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../Header/Head/HomeHead/homeHead.jsp" %>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/jspFile/Finale/CSS/form.css">
        <title><c:out value="${utenteSessione.getNome()}" /> Nuovo Negozio</title>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&key=AIzaSyB7LZyOZzvwxSWSLr6cwFw9CpTPT2iIFiw" async defer></script>
        <script type="text/javascript">
            function showResult(result) {
                document.nuovoNegozio.latitudine.value = result.geometry.location.lat();
                document.nuovoNegozio.longitudine.value = result.geometry.location.lng();
                nuovoNegozio.action = "${pageContext.request.contextPath}/NegozioController";
                nuovoNegozio.submit();
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
                return false;
            }
            
        </script>
    </head>
    <body>
        <div class="container">
            <%@include file="../Header/NavBar/newNavBar.jsp" %>
        </div>
        <div class="container-fluid">
            <div class="row">
                <div class="col-12">
                    <h1>Aggiungi un negozio</h1>
                    <p>Aggiungendo un negozio migliori le tue possbilità di vendita, ma attento, i tuoi utenti e noi di shopero ci aspettiamo che tu sia sempre leale corretto e sincero</p>
                    <p>Una volta aggiunto il tuo negozio potrai gestirlo ed aggiungervi gli oggetti che sono in vendita dalla pagina "Gestisci negozi" presente nel tuo account</p> 
                </div>
                <div class="col-12">
                <hr>
                    <h2>Dati principali del negozio</h2>
                    <p>Qui dovrai inserire i dati principali del tuo negozio, questi dati saranno quelli che lo renderanno disponibile agli utenti</p>
                    <p>Attenzione: i dati non saranno modificabili</p>
                </div>
            </div>
            <c:choose>
                <c:when test="${negozioInserito == 0}">
                    <div class="alert alert-success">
                        <strong>Successo!</strong> negozio inserito con successo.
                    </div>
                </c:when>
                <c:when test="${negozioInserito == 1}">
                    <div class="alert alert-danger">
                        <strong>Attenzione!</strong> negozio non inserito.
                    </div>
                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>
            <div class="row">
                <form class="col-12" id="nuovoNegozio" name="nuovoNegozio" onsubmit="return localizza()" method="POST">
                    <input type="hidden" name="action" value="addNegozio">
                    <div class="col-12">
                        <p>Nome del nuovo negozio:</p>
                    </div>
                    <div class="col-12">
                        <input type="text" class="form-control" id="nomeNegozio" name="nomeNegozio" placeholder="Nome" required>
                    </div>
                    <div class="col-12">
                        <p>Inserisci i dati relativi all'inidirizzo:</p>
                    </div>
                    <div class="row">
                        <div class="col-4">
                            <input class="form-control" type="text" id="regione" name="regione" placeholder="Regione" required>
                        </div>
                        <div class="col-4">
                            <input class="form-control" type="text" id="provincia" name="provincia" placeholder="Provincia" required>
                        </div>
                        <div class="col-4">
                            <input class="form-control" type="text" id="citta" name="citta" placeholder="Città" required>
                        </div>
                        <div class="col-8">
                            <input class="form-control" type="text" id="via" name="via" placeholder="Via" required>
                        </div>
                        <div class="col-1">
                            <p>N. civico</p>
                        </div>
                        <div class="col-3">
                            <input class="form-control" type="number" id="nCivico" name="nCivico" value="12" required>
                        </div>
                        <input class="form-control" type="hidden" id="interno" name="interno" value="-1" required>
                        <input class="form-control" type="hidden" id="latitudine" name="latitudine" required>
                        <input class="form-control" type="hidden" id="longitudine" name="longitudine" required>
                    </div>
                    <div class="col-12">
                        <p>Se possiedi il sito del negozio inserisci pure il link qui sotto, verrà mostrato agli utenti che visiteranno la pagina del negozio</p>
                    </div>
                    <div class="col-12">
                        <input type="text" class="form-control" id="linkNegozio" name="linkNegozio" placeholder="http://www.link.com">
                    </div>
                    <div class="col-12">
                        <p>Ora gentilmente inserisci gli orari ed i giorni di apertura del tuo negozio, in modo che gli utenti che vorranno venire a ritirare gli oggetti in negozio possano conoscerli</p>
                    </div>
                    <div class="row">
                        <div class="col-1">
                            <p>Giorno</p>
                        </div>
                        <div class="col-4">
                            <p>Orario apertura</p>
                        </div>
                        <div class="col-4">
                            <p>Orario chiusura</p>
                        </div>
                        <div class="col-3">
                            <p>Giorno di chiusura</p>
                        </div>
                        
                        
                        <div class="col-1">
                            <p>Luned&iacute;</p>
                        </div>
                        <div class="col-4">
                            <input type="tyme" class="form-control" id="orarioAperturaNegozioLunedi" name="orarioAperturaNegozioLunedi" placeholder="00:00:00">
                        </div>
                        <div class="col-4">
                            <input type="tyme" class="form-control" id="orarioChiusuraNegozioLunedi" name="orarioChiusuraNegozioLunedi" placeholder="00:00:00">
                        </div>
                        <div class="col-3">
                            <input type="checkbox" class="form-check-input" id="chiusoLunedi" name="chiusoLunedi">
                            Chiuso
                        </div>
                        <div class="col-1">
                            <p>Marted&iacute;</p>
                        </div>
                        <div class="col-4">
                            <input type="tyme" class="form-control" id="orarioAperturaNegozioMartedi" name="orarioAperturaNegozioMartedi" placeholder="00:00:00">
                        </div>
                        <div class="col-4">
                            <input type="tyme" class="form-control" id="orarioChiusuraNegozioMartedi" name="orarioChiusuraNegozioMartedi" placeholder="00:00:00">
                        </div>
                        <div class="col-3">
                            <input type="checkbox" class="form-check-input" id="chiusoMartedi" name="chiusoMartedi">
                            Chiuso
                        </div>
                        <div class="col-1">
                            <p>Mercoled&iacute;</p>
                        </div>
                        <div class="col-4">
                            <input type="tyme" class="form-control" id="orarioAperturaNegozioMercoledi" name="orarioAperturaNegozioMercoledi" placeholder="00:00:00">
                        </div>
                        <div class="col-4">
                            <input type="tyme" class="form-control" id="orarioChiusuraNegozioMercoledi" name="orarioChiusuraNegozioMercoledi" placeholder="00:00:00">
                        </div>
                        <div class="col-3">
                            <input type="checkbox" class="form-check-input" id="chiusoMercoledi" name="chiusoMercoledi">
                            Chiuso
                        </div>
                        <div class="col-1">
                            <p>Gioed&iacute;</p>
                        </div>
                        <div class="col-4">
                            <input type="tyme" class="form-control" id="orarioAperturaNegozioGiovedi" name="orarioAperturaNegozioGiovedi" placeholder="00:00:00">
                        </div>
                        <div class="col-4">
                            <input type="tyme" class="form-control" id="orarioChiusuraNegozioGiovedi" name="orarioChiusuraNegozioGiovedi" placeholder="00:00:00">
                        </div>
                        <div class="col-3">
                            <input type="checkbox" class="form-check-input" id="chiusoGiovedi" name="chiusoGiovedi">
                            Chiuso
                        </div>
                        <div class="col-1">
                            <p>Venerd&iacute;</p>
                        </div>
                        <div class="col-4">
                            <input type="tyme" class="form-control" id="orarioAperturaNegozioVenerdi" name="orarioAperturaNegozioVenerdi" placeholder="00:00:00">
                        </div>
                        <div class="col-4">
                            <input type="tyme" class="form-control" id="orarioChiusuraNegozioVenerdi" name="orarioChiusuraNegozioVenerdi" placeholder="00:00:00">
                        </div>
                        <div class="col-3">
                            <input type="checkbox" class="form-check-input" id="chiusoVenerdi" name="chiusoVenerdi">
                            Chiuso
                        </div>
                        <div class="col-1">
                            <p>Sabato</p>
                        </div>
                        <div class="col-4">
                            <input type="tyme" class="form-control" id="orarioAperturaNegozioSabato" name="orarioAperturaNegozioSabato" placeholder="00:00:00">
                        </div>
                        <div class="col-4">
                            <input type="tyme" class="form-control" id="orarioChiusuraNegozioSabato" name="orarioChiusuraNegozioSabato" placeholder="00:00:00">
                        </div>
                        <div class="col-3">
                            <input type="checkbox" class="form-check-input" id="chiusoSabato" name="chiusoSabato">
                            Chiuso
                        </div>
                        <div class="col-1">
                            <p>Domenica</p>
                        </div>
                        <div class="col-4">
                            <input type="tyme" class="form-control" id="orarioAperturaNegozioDomenica" name="orarioAperturaNegozioDomenica" placeholder="00:00:00">
                        </div>
                        <div class="col-4">
                            <input type="tyme" class="form-control" id="orarioChiusuraNegozioDomenica" name="orarioChiusuraNegozioDomenica" placeholder="00:00:00">
                        </div>
                        <div class="col-3">
                            <input type="checkbox" class="form-check-input" id="chiusoDomenica" name="chiusoDomenica">
                            Chiuso
                        </div>
                    </div>
                    
                    <div class="row">
                        <div class="col-12">
                            <button action="submit" type="submit" class="btn btn-outline-primary btn-block" id="btn">Aggiungi negozio</button>
                        </div>
                    </div>
                </form>
            </div>
            <hr>
        </div>
        <%@include file="../Footer/footer.jsp" %>
    </body>
</html>
