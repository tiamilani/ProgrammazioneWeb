<%--
    Document   : navBar
    Created on : 27-set-2017, 20.48.38
    Author     : mattia
--%>
<head>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
</head>

<script>
  $(document).ready(function(){
    $('#registerModal input').focus(function(){
        $(this).next('label').addClass('moveUp');
    });
  });

  $(document).ready(function(){
    $('#registerModal input').on('focusout', function() {
  	if(!$(this).val()) $(this).next('label').removeClass('moveUp');
    });
  });
</script>

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
          <form action="index.jsp">
            <div class="modal-body">
                <div>
                    <i class="large material-icons">person_outline</i>
                    <input class="col-10 modal-input" type="text" id="customerName" required>
                    <label for="customerName">Nome</label>
                </div>
                <div>
                    <i class="large material-icons">group</i>
                    <input class="col-10 modal-input" type="text" id="customerSurname" required>
                    <label for="customerSurname">Cognome</label>
                </div>
                <div>
                    <i class="large material-icons">email</i>
                    <input class="col-10 modal-input" type="text" id="customerEmail" required>
                    <label for="customerEmail">E-mail</label>
                </div>
                <div>
                    <i class="large material-icons">lock_outline</i>
                    <input class="col-10 modal-input" type="password" id="customerPassword" required>
                    <label for="customerPassword">Password</label>
                </div>
                <div>
                    <i class="large material-icons">lock_outline</i>
                    <input class="col-10 modal-input" type="password" id="customerConfirmPassword" required>
                    <label for="customerConfirmPassword">Ripeti la password</label>
                </div>
                    <p>Creando il tuo accont, accetti le nostro condizioni sulla privacy<p>
              
            </div>
            <div class="modal-footer">
              <button type="submit" class="col-6 btn btn-default">Crea il tuo account</button>
            </div>
          </form>
        </div>

    </div>
</div>
