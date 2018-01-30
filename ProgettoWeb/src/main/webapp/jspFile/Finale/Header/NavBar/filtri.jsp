<%--
    Document   : filtri
    Created on : 3-ott-2017, 15.37.04
    Author     : Damiano
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/jspFile/Finale/CSS/bootstrap-slider.css">
<script src="${pageContext.request.contextPath}/jspFile/Finale/JS/bootstrap-slider.js"></script>

<style>
    div.stars {
        width: auto;
        display: inline-block;
        margin: auto;
    }
    input.star {display: none; }
    label { top: 0; margin: 0; padding: 0;}
    label.star {
        float: right;
        padding: 10px;
        font-size: 50px;
        color: #444;
        transition: all .2s;
    }
    input.star:checked ~ label.star:before {
        content: '\f005';
        color: #FD4;
        transition: all .25s;
    }
    input.star-5:checked ~ label.star:before {
        color: #FE7;
        text-shadow: 0 0 20px #952;
    }
    input.star-1:checked ~ label.star:before { color: #F62; }
    label.star:hover { transform: rotate(-15deg) scale(1.3); }
    label.star:before {
        content: '\f006';
        font-family: FontAwesome;
    }

    @media screen and (max-width: 480px) {
        label.star {
            padding: 2px;
            font-size: 36px;
        }
    }

    .form-control-file, .form-control-range {
        display: inline-block;
    }

    input {
        margin: 0;
    }

    .distance {
        margin-bottom: 10px;
        display: block;
    }

    .checkbox label:after,
    .radio label:after {
        content: '';
        display: table;
        clear: both;
    }

    .checkbox .cr,
    .radio .cr {
        position: relative;
        display: inline-block;
        border: 1px solid #a9a9a9;
        border-radius: .25em;
        width: 1.3em;
        height: 1.3em;
        float: left;
        margin-right: .5em;
    }

    .radio .cr {
        border-radius: 50%;
    }

    .checkbox .cr .cr-icon,
    .radio .cr .cr-icon {
        position: absolute;
        font-size: .8em;
        line-height: 0;
        top: 50%;
        left: 20%;
    }

    .radio .cr .cr-icon {
        margin-left: 0.04em;
    }

    .checkbox label input[type="checkbox"],
    .radio label input[type="radio"] {
        display: none;
    }

    .checkbox label input[type="checkbox"] + .cr > .cr-icon,
    .radio label input[type="radio"] + .cr > .cr-icon {
        transform: scale(3) rotateZ(-20deg);
        opacity: 0;
        transition: all .3s ease-in;
    }

    .checkbox label input[type="checkbox"]:checked + .cr > .cr-icon,
    .radio label input[type="radio"]:checked + .cr > .cr-icon {
        transform: scale(1) rotateZ(0deg);
        opacity: 1;
    }

    .checkbox label input[type="checkbox"]:disabled + .cr,
    .radio label input[type="radio"]:disabled + .cr {
        opacity: .5;
    }

    #star-block{
        background: inherit;
        border: none;
    }
</style>


<form class="form-inline" name="filterForm">
    <div class="form-group row filterRow">
        <div class="col-lg-4 col-md-4 col-sm-12 category-filter">
            <select class="form-control" style="height: 100%; width: 100%" name="filtroCategoria">
                <c:choose>
                    <c:when test="${param.hiddenidCategoria != 'Categoria' && param.hiddenidCategoria > 0}">
                        <option>Categoria</option>
                        <c:forEach items="${listacategoriesessione.getList()}" var="cat">
                            <option value="${cat.getId()}" ${cat.getId() == param.hiddenidCategoria ? 'selected' : ''}><c:out value="${cat.getNome()}" /></option>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <option selected>Categoria</option>
                        <c:forEach items="${listacategoriesessione.getList()}" var="cat">
                            <option value="${cat.getId()}"><c:out value="${cat.getNome()}" /></option>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </select>
        </div>

        <div class="col-xl-4 col-lg-4 col-md-4 d-none d-sm-none d-md-block">
            <i class="large material-icons">person_outline</i>
            <input class="col-10 text-input" type="text" id="venditore" name="filtroNomeVenditore" placeholder="Nome venditore" value="${param.hiddennomeVenditore != null ? param.hiddennomeVenditore : ''}">
        </div>

        <div class="col-xl-4 col-lg-4 col-md-4 d-none d-sm-none d-md-block">
            <i class="large material-icons">shop</i>
            <input class="col-10 text-input" type="text" id="negozio" name="filtroNomeNegozio" placeholder="Nome negozio" value="${param.hiddennomeNegozio != null ? param.hiddennomeNegozio : ''}">
        </div>
    </div>

    <div class="form-group row filterRow">
        <div class="form-check col-xl-4 col-lg-6 col-md-6 d-none d-sm-none d-md-block">
            <div style="width: 100%">
                <center>
                    <input class="form-check-input" name="filtroRitiroInNegozio" type="checkbox" ${param.hiddencheckRitiroInNegozio ? 'checked' : ''}>Ritiro in negozio
                </center>
            </div>
            <div style="width: 100%">
                <center>
                    <input class="form-check-input" name="filtroProdottiScontati" type="checkbox" ${param.hiddencheckProdottiScontati ? 'checked' : ''}>Prodotti scontati
                </center>
            </div>
        </div>

        <div class="col-xl-4 col-lg-6 col-md-6 col-sm-12 col-xs-12">
            <center style="margin: .3rem 0;">
                <b class="price-label" id="minSliderValue"> 0&euro; </b><input name="range-slider" id="double-slider" type="number" range="true" value="" data-provide="slider" data-slider-min="0" data-slider-max="${sessionScope.massimoPrezzoAttuale}" data-slider-step="5" data-slider-value="[${param.hiddenPriceRange != null ? param.hiddenPriceRange : sessionScope.massimoRangeAttuale}]"><b class="price-label" id="maxSliderValue"> ${sessionScope.massimoPrezzoAttuale}&euro;</b>
            </center>
        </div>

        <div class="form-control col-xl-4 col-lg-12 col-md-12 col-sm-12 col-xs-12" id="star-block">
            <div class="form-group">
                <div class="stars">
                    <input class="star star-5" id="star-5" type="radio" name="valutazioneReview" value="5" ${param.hiddenvalutazioneMinima != null && param.hiddenvalutazioneMinima == 5 ? 'checked' : ''}/>
                    <label class="star star-5" for="star-5"></label>
                    <input class="star star-4" id="star-4" type="radio" name="valutazioneReview" value="4" ${param.hiddenvalutazioneMinima != null && param.hiddenvalutazioneMinima == 4 ? 'checked' : ''}/>
                    <label class="star star-4" for="star-4"></label>
                    <input class="star star-3" id="star-3" type="radio" name="valutazioneReview" value="3" ${param.hiddenvalutazioneMinima != null && param.hiddenvalutazioneMinima == 3 ? 'checked' : ''}/>
                    <label class="star star-3" for="star-3"></label>
                    <input class="star star-2" id="star-2" type="radio" name="valutazioneReview" value="2" ${param.hiddenvalutazioneMinima != null && param.hiddenvalutazioneMinima == 2 ? 'checked' : ''}/>
                    <label class="star star-2" for="star-2"></label>
                    <input class="star star-1" id="star-1" type="radio" name="valutazioneReview" value="1" ${param.hiddenvalutazioneMinima != null && param.hiddenvalutazioneMinima == 1 ? 'checked' : ''}/>
                    <label class="star star-1" for="star-1"></label>
                </div>
            </div>
        </div>
                    
        <div class="col-lg-4 col-md-4 col-sm-12 category-filter">
            <select class="form-control" style="height: 100%; width: 100%" name="filtroRegione" id='regionFilter'>
                <c:choose>
                    <c:when test="${param.hiddenRegione != 'Regione' && param.hiddenRegione != null}">
                        <option>Regione</option>
                        <c:forEach items="${sessionScope.listaRegioni}" var="reg">
                            <option value="${reg}" ${reg == param.hiddenRegione ? 'selected' : ''}><c:out value="${reg}" /></option>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <option selected>Regione</option>
                        <c:forEach items="${sessionScope.listaRegioni}" var="reg">
                            <option value="${reg}"><c:out value="${reg}" /></option>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </select>
        </div>
                    
        <div class="col-lg-4 col-md-4 col-sm-12">
            
        </div>
        <div class="col-lg-4 col-md-4 col-sm-12 row no-gutters justify-content-center">
            <!--<button class="btn btn-outline-primary btn-block col-5" style="margin: 0 .1rem 0 .1rem; padding: 1rem;" onclick="myFunction()">Applica</button> -->
            <button id="noAction" class="btn btn-outline-primary btn-block col-6" style="margin: 0 .1rem 0 .1rem; padding: 1rem;" onclick="resetFilter()">Reset</button>
        </div>                 
    </div>
</form>

<%--
<form class="form-inline" name="filterForm">
    <div class="row filterRow ">
        <div class="col-auto dropdown">
            <select class="custom-select mb-2 mr-sm-2 mb-sm-0" name="filtroCategoria">
              <option selected>Categoria</option>
              <c:forEach items="${listacategoriesessione.getList()}" var="cat">
                  <option value="${cat.getId()}"><c:out value="${cat.getNome()}"/></option>
              </c:forEach>
            </select>
          </div>

        <div class="col-3">
            <p>Prezzo minimo e massimo (0 per non considerarlo)</p>
            <div class="row">
                <div class="col-6 labelSpacing">
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                      <div class="input-group-addon"><i class="large material-icons">person_outline</i></div>
                      <input name="filtroMinPrice" type="text" class="form-control" id="minPrice" placeholder="0">
                    </div>
                </div>

                <div class="col-6 labelSpacing">
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                    <div class="input-group-addon"><i class="large material-icons">person_outline</i></div>
                    <input name="filtroMaxPrice" type="text" class="form-control" id="minPrice" placeholder="0">
                </div>
                </div>
            </div>
        </div>

        <div class="col-2">
            <p>Nome venditore</p>
            <input name="filtroNomeVenditore" class="col-11 labelSpacing form-control mr-sm-2" type="text" name="venditore" placeholder="Venditore"/>
        </div>

        <div class="col-2">
            <p>Nome Negozio</p>
            <input name="filtroNomeNegozio" class="col-11 labelSpacing form-control mr-sm-2" type="text" name="negozio" placeholder="Negozio"/>
        </div>

        <div class="col-2">
            <label class="checkboxMargin form-check-label">
                <input name="filtroRitiroInNegozio" type="checkbox" class="form-check-input" name="checkboxRitiroInNegozio" value = "false">
                Solo prodotti con ritiro in negozio
            </label>
            <label class="checkboxMargin form-check-label">
                <input name="filtroProdottiScontati" type="checkbox" class="form-check-input" name="checkboxProdottiScontati" value = "false">
                Solo prodotti scontati
            </label>
        </div>
    </div>
    <div class="row filterRow ">
        <div class="col-3 spazziatuaOggetti">
            <p>Latitudine</p>
            <input name="filtroLatitudine" class="labelSpacing form-control mr-sm-2" type="text" name="latitudine" placeholder="Lat"/>

            <p>Longitudine</p>
            <input name="filtroLongitudine" class="labelSpacing form-control mr-sm-2" type="text" name="Longitudine" placeholder="Long"/>

            <p>Raggio</p>
            <input name="filtroRaggio" class="labelSpacing form-control mr-sm-2" type="text" name="raggio" placeholder="Raggio"/>
        </div>

        <div class="col-3 spazziatuaOggetti">
            <h1>Spazio riservato alla mappa</h1>
        </div>

        <div class="col-3">
            <p>Valutazione minima</p>
            <select name="filtroValutazioneMinima" class="custom-select mb-2 mr-sm-2 mb-sm-0" id="selettoreValutazione">
                <option selected>Choose...</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
            </select>
        </div>
    </div>
</form>
--%>

<script>
 $('.dropdown-menu a').click(function(){
    $('#selected').text($(this).text());
 });

 $(document).ready(function(){
    var valore = $('#double-slider').attr('value');
    var estremi = valore.split(',');
    $('#minSliderValue').html(estremi[0] + "&euro;");
    $('#maxSliderValue').html(estremi[1] + "&euro;");
 });

 $(document).ready(function(){
    $('#double-slider').on('change', function(){
        var newValore = $(this).attr('value');
        var estremi = newValore.split(',');
        $('#minSliderValue').html(estremi[0] + "&euro;");
        $('#maxSliderValue').html(estremi[1] + "&euro;");
    });
 });
</script>
