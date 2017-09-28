<%--
    Document   : navBar
    Created on : 27-set-2017, 20.48.38
    Author     : mattia
--%>

<nav class="navbar navbar-light bg-white fixed-top navbar-expand-lg">
  <a class="navbar-brand" href="http://localhost:8084/ProgettoWeb/jspFile/Finale/Index/index.jsp">
    <img src="http://localhost:8084/ProgettoWeb/jspFile/Finale/Img/square.png" width="30" height="30" class="d-inline-block align-top" alt="IMG">
    ShopEro
  </a>
    <div class="navbar-nav">
        <a class="nav-item nav-link" href="#">Categorie</a>
        <a class="nav-item nav-link" href="#">Filtri</a>
    </div>
    <div class="row1">
        <form class="form-inline">
            <input class="col-9 form-control mr-sm-2" type="text" name="search" placeholder="search"/>
            <button  class="col-2 btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
    </div>

    <div class="row2">
        <form class="form-inline">
            <input class="col-3 form-control mr-sm-2" type="email" name="email" placeholder="email"/>
            <input class="col-3 form-control mr-sm-2" type="password" name="password" placeholder="password"/>
            <button  class="col-2 paddingNav btn btn-outline-success my-2 my-sm-0" type="submit">Login</button>
            <button  class="col-2 paddingNav btn btn-outline-primary my-2 my-sm-0" type="submit">Registrati</button>
        </form>
    </div>
</nav>

<div class="pos-f-t">
  <div class="collapse" id="navbarToggleExternalContent">
    <div class="bg-dark p-4">
      <h4 class="text-white">Collapsed content</h4>
      <span class="text-muted">Toggleable via the navbar brand.</span>
    </div>
  </div>
  <nav class="navbar navbar-dark bg-dark">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
  </nav>
</div>