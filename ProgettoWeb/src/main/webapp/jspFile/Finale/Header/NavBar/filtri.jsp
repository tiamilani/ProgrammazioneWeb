<%-- 
    Document   : filtri
    Created on : 3-ott-2017, 15.37.04
    Author     : mattia
--%>

<form class="form-inline" name="filterForm">
    <div class="form-group row filterRow">
        <div class="col-lg-3 col-md-4">
            <select>
                <option selected>Categoria</option>
                <c:forEach items="${listacategoriesessione.getList()}" var="cat">
                  <option value="${cat.getId()}"><c:out value="${cat.getNome()}"/></option>
                </c:forEach>
            </select>
        </div>

        <div class="col-lg-3 col-md-4">
            <input type="range" min="0" max="1000" value="500" class="slider">
        </div>

        <div class="col-lg-3 col-md-4">
            <input type="text" name="filtroNomeVenditore" placeholder="Venditore">
        </div>

        <div class="col-lg-3 col-md-4">
            <input type="text" name="filtroNomeNegozio" placeholder="Negozio">
        </div>
    </div>
                
    <div class="form-group row filterRow">
        <div class="form-check col-lg-4 col-md-4">
            <input class="form-check-input" name="filtroRitiroInNegozio" type="checkbox">Ritiro in negozio
        </div>
        
        <div class="form-check col-lg-4 col-md-4">
            <input class="form-check-input" name="filtroProdottiScontati" type="checkbox">Prodotti scontati
        </div>
        
        <div class="form-control" col-lg-4 col-md-4>
            <div class="rating">
                <span class="fa fa-star checked"></span>
                <span class="fa fa-star checked"></span>
                <span class="fa fa-star checked"></span>
                <span class="fa fa-star"></span>
                <span class="fa fa-star"></span>
            </div>
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
</script>