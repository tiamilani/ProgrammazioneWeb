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
          <span class="text-muted">Toggle che mostra le categorie</span>
        </div>
      </div>
    <nav class="navbar navbar-light bg-white fixed-top navbar-expand-lg">
      <a class="navbar-brand" href="http://localhost:8084/ProgettoWeb/jspFile/Finale/Index/index.jsp">
        <img src="http://localhost:8084/ProgettoWeb/jspFile/Finale/Img/square.png" width="30" height="30" class="d-inline-block align-top" alt="IMG">
        ShopEro
      </a>
        <div class="navbar-nav">
            <button class=" col-8 paddingNav btn btn-link my-2 my-sm-0" type="button" data-toggle="collapse" data-target="#navbarToggleCategorie" aria-controls="navbarToggleCategorie" aria-expanded="false" aria-label="Toggle navigation">
                    Categorie
            </button>
            <button class="col-4 paddingNav btn btn-link my-2 my-sm-0" type="button" data-toggle="collapse" data-target="#navbarToggleFiltri" aria-controls="navbarToggleFiltri" aria-expanded="false" aria-label="Toggle navigation">
                    Filtri
            </button>
        </div>
        <div class="row2">
            <form class="form-inline">
                <input class="form-control mr-sm-2" id="expand" type="text" name="search" placeholder="Search..."/>
                <button  class="col-2 btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form></div>

        <div class="row3">
            <form class="form-inline">
                <input class="col-3 form-control mr-sm-2" type="email" name="email" placeholder="email"/>
                <input class="col-3 form-control mr-sm-2" type="password" name="password" placeholder="password"/>
                <button  class="col-2 paddingNav btn btn-outline-success my-2 my-sm-0" type="submit">Login</button>
                <button  type="button" class="col-2 paddingNav btn btn-outline-primary my-2 my-sm-0" data-toggle="modal"
                         data-target="#registerModal">Registrati</button>
            </form>
        </div>
    </nav>
</div>

<%-- messo esternamente al resto in modo da non influenzare il suo autofocus da proprietà di posizionamento prima definite--%>
<div id="registerModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title">Creare un account</h4>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
          </div>
          <div class="modal-body">
              <label for="customerName">Nome</label>
              <input class="col-6" type="text" id="customerName">
              <label for="customerSurname">Cognome</label>
              <input class="col-6" type="text" id="customerSurname">
              <label for="customerEmail">E-mail</label>
              <input class="col-6" type="text" id="customerEmail">
              <label for="customerPassword">Password</label>
              <input class="col-6" type="password" id="customerPassword">
              <label for="customerConfirmPassword">Digita nuovamente la password</label>
              <input class="col-6" type="password" id="customerConfirmPassword">
          </div>
          <div class="modal-footer">
            <button type="button" class="col-6 btn btn-default" data-dismiss="modal" type="submit ">Crea il tuo account</button>
          </div>
        </div>

    </div>
</div>