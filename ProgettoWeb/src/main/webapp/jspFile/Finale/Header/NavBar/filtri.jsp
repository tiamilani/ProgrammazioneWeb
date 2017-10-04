<%-- 
    Document   : filtri
    Created on : 3-ott-2017, 15.37.04
    Author     : mattia
--%>

<span class="filterRow">
    
    <div class="dropdown col-1">
        <a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown" class="dropdown-toggle" href="#">
          <span id="selected">Categorie</span><span class="caret"></span></a>
      <ul class="dropdown-menu">
          <li><a href="#">Nessuna categoria</a></li>
          <c:forEach items="${listacategoriesessione.getList()}" var="cat">
              <li><a href="#"><c:out value="${cat.getNome()}"/></a></li>
          </c:forEach>
      </ul>
    </div>

    <div class="col-2">
        <label class="sr-only" for="inlineFormInputGroup">Prezzo minimo</label>
        <div class="input-group mb-2 mr-sm-2 mb-sm-0">
          <div class="input-group-addon">@</div>
          <input type="text" class="form-control" id="minPrice" placeholder="Prezzo minimo">
        </div>    
    </div>
</span>

<script>
 $('.dropdown-menu a').click(function(){
    $('#selected').text($(this).text());
 });
</script>