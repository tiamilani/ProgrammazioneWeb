<%-- 
    Document   : filtri
    Created on : 3-ott-2017, 15.37.04
    Author     : mattia
--%>

<form class="form-inline">
    <div class="row filterRow ">
        <div class="col-auto dropdown">
            <select class="custom-select mb-2 mr-sm-2 mb-sm-0" id="selettoreCategoria">
              <option selected>Categoria</option>
              <c:forEach items="${listacategoriesessione.getList()}" var="cat">
                  <option value="1"><c:out value="${cat.getNome()}"/></option>
              </c:forEach>
            </select>
          </div>

        <div class="col-3">
            <p>Prezzo minimo e massimo (0 per non considerarlo)</p>
            <div class="row">
                <div class="col-6 labelSpacing">
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                      <div class="input-group-addon"><i class="large material-icons">person_outline</i></div>
                      <input type="text" class="form-control" id="minPrice" placeholder="0">
                    </div>    
                </div>

                <div class="col-6 labelSpacing">
                    <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                    <div class="input-group-addon"><i class="large material-icons">person_outline</i></div>
                    <input type="text" class="form-control" id="minPrice" placeholder="0">
                </div>  
                </div>  
            </div>
        </div>

        <div class="col-2">
            <p>Nome venditore</p>
            <input class="col-11 labelSpacing form-control mr-sm-2" type="text" name="venditore" placeholder="Venditore"/>
        </div>
              
        <div class="col-2">
            <p>Nome Negozio</p>
            <input class="col-11 labelSpacing form-control mr-sm-2" type="text" name="negozio" placeholder="Negozio"/>
        </div>
        
        <div class="col-2">
            <label class="checkboxMargin form-check-label">
                <input type="checkbox" class="form-check-input" name="checkboxRitiroInNegozio">
                Solo prodotti con ritiro in negozio
            </label>
            <label class="checkboxMargin form-check-label">
                <input type="checkbox" class="form-check-input" name="checkboxProdottiScontati">
                Solo prodotti scontati
            </label>
        </div>
    </div>
    <div class="row filterRow ">
        <div class="col-3 spazziatuaOggetti">
            <p>Latitudine</p>
            <input class="labelSpacing form-control mr-sm-2" type="text" name="latitudine" placeholder="Lat"/>
            
            <p>Longitudine</p>
            <input class="labelSpacing form-control mr-sm-2" type="text" name="Longitudine" placeholder="Long"/>
            
            <p>Raggio</p>
            <input class="labelSpacing form-control mr-sm-2" type="text" name="raggio" placeholder="Raggio"/>
        </div>
        
        <div class="col-3 spazziatuaOggetti">
            <h1>Spazio riservato alla mappa</h1>
        </div>
        
        <div class="col-3">
            <p>Valutazione minima</p>
            <select class="custom-select mb-2 mr-sm-2 mb-sm-0" id="selettoreValutazione">
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

<script>
 $('.dropdown-menu a').click(function(){
    $('#selected').text($(this).text());
 });
</script>