<%--
    Document   : descrizione
    Created on : 5-ott-2017, 17.48.54
    Author     : andreafadi
--%>

<div class="descrizione" id="descrizione">
    <div style="width: 100%; margin: 0 auto;">
        <div class="text-center">
            <h1>${negozio.getNomeNegozio()}</h1><br>
        </div>
        <div class="row">
            <div class="col-12 col-sm-12 col-md-6 col-lg-9 col-xl-9">
                <div class="text-justify">
                    <h4>Sito web: ${negozio.getLinkSito()}</h4><br/>
                    <h4>Popolarita': ${negozio.getValutazione()} / 5</h4><br/>
                    <h5>Indirizzo: ${indirizzo.getVia()} ${indirizzo.getnCivico()}, ${indirizzo.getCitta()}, ${indirizzo.getProvincia()}, ${indirizzo.getRegione()}, ${indirizzo.getStato()}</h5><br/>
                    <h6>${negozio.getOrarioNegozio()}</h6><br/><br/>
                    <h5>Proprietario:
                    <c:url value="/UserController" var="sellerUrl">
                        <c:param name="action" value="DescrizioneVenditore" />
                        <c:param name="idUtente" value="${venditore.getId()}" />
                    </c:url>
                    <a href="${sellerUrl}">${venditore.getCognome()} ${venditore.getNome()}</a><br/>
                </div>
            </div>
        </div>
    </div>
</div>