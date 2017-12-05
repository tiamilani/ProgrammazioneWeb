<%-- 
    Document   : fixedMenu
    Created on : 3-ott-2017, 22.23.11
    Author     : andreafadi
--%>

<div class="sidenav">
    Prezzo: ${oggetto.getPrezzo()} Euro<br>
    <c:if test="${oggetto.getSconto() > 0}">
        Sconto: ${oggetto.getSconto()} %<br>
        Valido fino al: ${oggetto.getDataFineSconto()}<br>
    </c:if>
    Disponibilita': ${oggetto.getDisponibilita()}<br><br>
    
    <div class="text-center">
        <a href="#" class="btn btn-info btn-lg text-center">
            <span class="fa fa-shopping-cart"></span>Acquista
        </a>
    </div>
</div>
