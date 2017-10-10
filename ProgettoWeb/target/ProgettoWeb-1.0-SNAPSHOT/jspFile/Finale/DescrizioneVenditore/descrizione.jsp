<%--
    Document   : descrizione
    Created on : 5-ott-2017, 17.48.54
    Author     : andreafadi
--%>

<div class="descrizione" id="descrizione">
    <div style="width: 100%; margin: 0 auto;">
        <div class="text-center">
            <h1>${venditore.getCognome()} ${venditore.getNome()}</h1><br>
            <img class="d-block w-100" src="${venditore.getAvatar()}" alt="IMAGE NOT LOADED" style="width: 100px; height: 100px; object-fit: contain;">
        </div>
        <div class="text-justify">
            <h5>
                Email: ${venditore.getMail()}<br>
                Valutazione: ${venditore.getValutazione()} / 5
            </h5>
        </div>
    </div>
</div>
