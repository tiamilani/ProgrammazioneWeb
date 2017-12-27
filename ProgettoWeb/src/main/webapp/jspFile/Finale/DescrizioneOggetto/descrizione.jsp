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
                        <input type="number" id="numNow" name="numNow" class="item-right col-6 form-control" min="1" max="${oggetto.getDisponibilita()}" value="1"/>
                    </div>
                    <p class="lead"/>
                    <div class="row">
                        <select class="form-control col-12" id="shipType" name="shipType">
                            <option disabled selected value="false"> -- spedizione -- </option>
                            <c:forEach items="${listaTipiSpedizione.getList()}" var="ship">
                                <option value='{"prezzo":"${ship.getPrezzo()}","negozio":"${oggetto.getIdNegozio()}","oggetto":"${oggetto.getId()}"}' data-value='{"nome":"${ship.getNome()}","prezzo":"${ship.getPrezzo()}","corriere":"${ship.getCorriere()}","tempo":"${ship.getTempoRichiesto()}"}'>
                                    ${ship.getNome()}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <p class="lead"/>
                    <div class="row" id="totNow">
                        <span class="col-4 item-left">Totale:</span>
                        <span class="col-8 item-right text-primary" id="totNowElem"></span>
                        <span class="col-4 item-left">Corriere:</span>
                        <span class="col-8 item-right" id="shipCar"></span>
                        <span class="col-4 item-left">Tempo:</span>
                        <span class="col-8 item-right" id="shipTime"></span>
                        <a href="#" class="button col-12" id="addToCart">Acquista</a>
                    </div>
                </form>
                
                <script>
                    $(window).ready(function() {
                        $('#totNow').hide();
                    });
                    
                    $('#addToCart').click(function() {
                        $('#addCartForm').submit();
                    });

                    $('#shipType').change(function() {
                        var costoTotale = ${oggetto.getPrezzo()};
                        var sconto = ${oggetto.getSconto()};
                        
                        var numProdotti = parseInt($('#numNow').val());
                        
                        //var tipoSpedizione = $(this).find(":selected").data("value").nome;
                        var costoSpedizione = $(this).find(":selected").data("value").prezzo;
                        var corriereSpedizione = $(this).find(":selected").data("value").corriere;
                        var tempoSpedizione = $(this).find(":selected").data("value").tempo;
                        
                        costoTotale *= numProdotti;
                        
                        if(sconto > 0)
                            costoTotale -= (costoTotale * sconto) / 100;
                        
                        costoTotale += parseInt(costoSpedizione);
                        
                        $("#totNow").show();
                        $('#totNowElem').text(costoTotale + " Euro");
                        $('#shipCar').text(corriereSpedizione);
                        $('#shipTime').text(tempoSpedizione + " giorni");
                    });
                    
                    $('#numNow').change(function() {
                        if($('#shipType').val() != "false") {
                            var costoTotale = ${oggetto.getPrezzo()};
                            var sconto = ${oggetto.getSconto()};

                            var numProdotti = parseInt($('#numNow').val());
                            
                            //var tipoSpedizione = $(this).find(":selected").data("value").nome;
                            var costoSpedizione = $('#shipType').find(":selected").data("value").prezzo;
                            var corriereSpedizione = $('#shipType').find(":selected").data("value").corriere;
                            var tempoSpedizione = $('#shipType').find(":selected").data("value").tempo;
                            
                            costoTotale *= numProdotti;
                            
                            if(sconto > 0)
                                costoTotale -= (costoTotale * sconto) / 100;

                            costoTotale += parseInt(costoSpedizione);

                            $("#totNow").show();
                            $('#totNowElem').text(costoTotale + " Euro");
                            $('#shipCar').text(corriereSpedizione);
                            $('#shipTime').text(tempoSpedizione + " giorni");
                        }
                    });
                </script>
            </div>
        </div>
    </div>
</div>
