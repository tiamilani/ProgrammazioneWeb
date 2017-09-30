<%--
    Document   : navBar
    Created on : 27-set-2017, 20.48.38
    Author     : mattia
--%>
<div class="pos-f-t">
  <div class="collapse" id="navbarToggleFiltri">
        <div class="bg-light p-4">
          <h4>Filtri</h4>
          <span class="text-muted">Toggle che mostra i filtri</span>
        </div>
      </div>
   <div class="collapse" id="navbarToggleCategorie">
        <div class="bg-light p-4">
            <h4>Categorie</h4>
            <c:forEach items="${listacategoriesessione.getList()}" var="cat">
                <c:url value="http://localhost:8084/ProgettoWeb/jspFile/Finale/Categorie/categoria.jsp" var="catUrl">
                    <c:param name="id" value="${cat.getId()}" />
                </c:url>
                <a class="nav-link" href="${catUrl}"><c:out value="${cat.getNome()}"/></a>
            </c:forEach>
        </div>
      </div>
    <nav class="navbar navbar-light bg-white fixed-top navbar-expand-lg">
      <a class="navbar-brand" href="http://localhost:8084/ProgettoWeb/jspFile/Finale/Index/index.jsp">
        <img src="http://localhost:8084/ProgettoWeb/jspFile/Finale/Img/square.png" width="30" height="30" class="d-inline-block align-top" alt="IMG">
        ShopEro
      </a>
        <div class="navbar-nav">
            <button class=" col-8 paddingNav btn btn-outline-success my-2 my-sm-0" type="button" data-toggle="collapse" data-target="#navbarToggleCategorie" aria-controls="navbarToggleCategorie" aria-expanded="false" aria-label="Toggle navigation">
                    Categorie
            </button>
            <button class="col-4 paddingNav btn btn-outline-success my-2 my-sm-0" type="button" data-toggle="collapse" data-target="#navbarToggleFiltri" aria-controls="navbarToggleFiltri" aria-expanded="false" aria-label="Toggle navigation">
                    Filtri
            </button>
        </div>
        <div class="row2">
            <form class="form-inline">
                <input class="col-9 form-control mr-sm-2" type="text" name="search" placeholder="search"/>
                <button  class="col-2 btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form></div>

        <div class="row3">
            <form class="form-inline">
                <input class="col-3 form-control mr-sm-2" type="email" name="email" placeholder="email"/>
                <input class="col-3 form-control mr-sm-2" type="password" name="password" placeholder="password"/>
                <button  class="col-2 paddingNav btn btn-outline-success my-2 my-sm-0" type="submit">Login</button>
                <button  class="col-2 paddingNav btn btn-outline-primary my-2 my-sm-0" type="submit">Registrati</button>
            </form>
        </div>
    </nav>
</div>