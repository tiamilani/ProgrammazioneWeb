<%--
    Document   : descrizione
    Created on : 5-ott-2017, 17.48.54
    Author     : andreafadi
--%>

<style>
    .item-left {
        float:left;
        display: block;
        font-size: 16px;
    }

    .item-right {
        float:right;
        display: block;
        font-size: 16px;
    }

    .button {
        background-color: #6394F8;
        color: white;
        text-align: center;
        padding: 12px;
        text-decoration: none;
        display: block;
        border-radius: 5px;
        font-size: 16px;
        margin: 25px 0 15px 0;
    }


    .button:hover {
        background-color: #3b7af7;
        color: white;
    }

    a:hover, a:visited, a:link, a:active {
        text-decoration: none;
    }
</style>

<div class="descrizione">
    <div style="width: 100%; margin: 0 auto;">
        <div class="text-center">
            <h1>${oggetto.getNome()}</h1><br>
        </div>
        <div class="row">
            <div class="col-12 col-sm-12 col-md-6 col-lg-9 col-xl-9">
                <div class="text-justify">
                    <h5>${oggetto.getDescrizione()}</h5><br>
                    <h5>Venduto da: 
                        <c:url value="/UserController" var="sellerUrl">
                            <c:param name="action" value="DescrizioneVenditore" />
                            <c:param name="idUtente" value="${venditore.getId()}" />
                        </c:url>
                        <a href="${sellerUrl}">${venditore.getCognome()} ${venditore.getNome()}</a>
                    </h5>
                </div>
            </div>
            <div class="col-12 col-sm-12 col-md-6 col-lg-3 col-xl-3">
                <c:url value="/objectSelectedController" var="addToCartLink">
                    <c:param name="action" value="add" />
                </c:url>
                <form method="POST" id="addCartForm" name="addCartForm" action="${addToCartLink}">
                    <div class="row">
                        <span class="col-4 item-left">Prezzo:</span>
                        <span class="col-8 item-right">${oggetto.getPrezzo()} Euro</span>
                    </div>
                    <p class="lead"/>
                    <c:if test="${oggetto.getSconto() > 0}">
                        <div class="row">
                            <span class="col-4 item-left">Sconto:</span>
                            <span class="col-8 item-right">${oggetto.getSconto()} %</span>
                        </div>
                        <div class="row">
                            <span class="col-12 text-center text-danger">Valido fino al: ${oggetto.getDataFineSconto()}</span>
                        </div>
                    </c:if>
                    <p class="lead"/>
                    <div class="row">
                        <span class="col-4 item-left">Quantità:</span>
                        <c:choose>
                            <c:when test="${oggetto.getDisponibilita() > 0}">
                                <input type="number" id="numNow" name="numNow" class="item-right col-6 form-control" min="1" max="${oggetto.getDisponibilita()}" value="1"/>
                            </c:when>
                            <c:otherwise>
                                <span class="col-8 item-right text-danger" id="numNow" name="numNow">ESAURITO</span>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <c:if test="${oggetto.getDisponibilita() > 0}">
                        <div class="row" style="display: none;">
                            <input type="hidden" id="shipType" name="shipType" value='{"prezzo":"${oggetto.getPrezzo()}","negozio":"${oggetto.getIdNegozio()}","oggetto":"${oggetto.getId()}"}'/>
                        </div>
                        <p class="lead"/>
                        <div class="row" id="totNow">
                            <span class="col-4 item-left">Totale:</span>
                            <span class="col-8 item-right text-primary" id="totNowElem">${oggetto.getPrezzo()} Euro</span>
                            <a href="#" class="button col-12" id="addToCart">Acquista</a>
                        </div>
                    </c:if>
                </form>
                
                <script>                    
                    $('#addToCart').click(function() {
                        $('#addCartForm').submit();
                    });
                    
                    $('#numNow').change(function() {
                        var costoTotale = ${oggetto.getPrezzo()};
                        var sconto = ${oggetto.getSconto()};

                        var numProdotti = parseInt($('#numNow').val());

                        costoTotale *= numProdotti;

                        if(sconto > 0)
                            costoTotale -= (costoTotale * sconto) / 100;
                        
                        $('#totNowElem').text(costoTotale.toFixed(2) + " Euro");
                    });
                </script>
            </div>
        </div>
    </div>
</div>
