<%--
    Document   : descrizione
    Created on : 5-ott-2017, 17.48.54
    Author     : andreafadi
--%>

<div class="descrizione">
    <div style="width: 100%; margin: 0 auto;">
        <div class="text-center">
            <h1>${oggetto.getNome()}</h1><br>
        </div>
        <div class="text-justify">
            <h5>${oggetto.getDescrizione()}</h5><br>
            <h5>Venduto da: 
                <c:url value="/UserController" var="sellerUrl">
                    <c:param name="action" value="DescrizioneVenditore" />
                    <c:param name="idUtente" value="${venditore.getId()}" />
                </c:url>
                <a href="${sellerUrl}" target="_blank">${venditore.getCognome()} ${venditore.getNome()}</a>
            </h5>
        </div>
    </div>
</div>
