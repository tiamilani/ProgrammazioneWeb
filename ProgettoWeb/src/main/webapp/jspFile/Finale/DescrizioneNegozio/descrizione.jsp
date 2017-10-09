<%--
    Document   : descrizione
    Created on : 5-ott-2017, 17.48.54
    Author     : andreafadi
--%>

<div class="descrizione" id="descrizione">
    <div style="width: 100%; margin: 0 auto;">
        <div class="text-center">
            <h1>${negozio.getNomeNegozio()}</h1><br>
            <img id="imgNegozio" class="d-block w-100" src="${immagine.getSrc()}" alt="IMAGE NOT LOADED" style="width: auto; max-height: 500px; object-fit: cover;">
        </div>
        <div class="text-justify">
            <h3>${negozio.getNomeNegozio()}</h3><br/>
            <h4>${negozio.getLinkSito()}</h4><br/>
            <h5>${indirizzo.getVia()} ${indirizzo.getnCivico()}, ${indirizzo.getCitta()}, ${indirizzo.getProvincia()}, ${indirizzo.getRegione()}, ${indirizzo.getStato()}</h5><br/>
            <h6>${negozio.getOrarioNegozio()}</h6>
        </div>
    </div>
</div>
