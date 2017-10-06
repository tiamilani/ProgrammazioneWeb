<%-- 
    Document   : descrizione
    Created on : 5-ott-2017, 17.48.54
    Author     : andreafadi
--%>

<div class="descrizione">
    <div style="width: 100%; margin: 0 auto;">
        <div class="text-center">
            <h1>${oggetto.getNome()}</h1><br/>
        </div>
        <div class="text-justify">
            <h5>
                Prezzo: ${oggetto.getPrezzo()} Euro<br/>
                <c:if test="${oggetto.getSconto() > 0}">
                    Sconto: ${oggetto.getSconto()} %<br/>
                    Valido fino al: ${oggetto.getDataFineSconto()}<br/>
                </c:if>
                Disponibilità: ${oggetto.getDisponibilita()}
            </h5><br/>
            <h5>${oggetto.getDescrizione()}</h5><br/>
        </div>
        <div class="text-center">
            <h3>Puoi ritirare il prodotto in questo punto vendita</h3>
        </div>
    </div>
</div>
