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
                    <h4>${negozio.getLinkSito()}</h4><br/>
                    <h4>${negozio.getValutazione()} / 5</h4><br/>
                    <h5>${indirizzo.getVia()} ${indirizzo.getnCivico()}, ${indirizzo.getCitta()}, ${indirizzo.getProvincia()}, ${indirizzo.getRegione()}, ${indirizzo.getStato()}</h5><br/>
                    <h6>${negozio.getOrarioNegozio()}</h6>
                </div>
            </div>
        </div>
    </div>
</div>