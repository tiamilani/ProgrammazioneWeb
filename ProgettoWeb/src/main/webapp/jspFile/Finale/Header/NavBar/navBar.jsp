<%--
    Document   : navBar
    Created on : 27-set-2017, 20.48.38
    Author     : mattia
--%>
<div class="pos-f-t">
  <div class="collapse" id="navbarToggleFiltri">
        <div class="bg-light p-4">
          <h4>Filtri</h4>
          <%@include file="filtri.jsp" %>
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
                <button  type="button" class="col-2 paddingNav btn btn-outline-success my-2 my-sm-0" data-toggle="modal"
                         data-target="#loginModal">Login</button>
                <button  type="button" class="col-2 paddingNav btn btn-outline-primary my-2 my-sm-0" data-toggle="modal"
                         data-target="#registerModal">Registrati</button>
            </form>
        </div>
    </nav>
</div>
            

<%-- messo esternamente al resto in modo da non influenzare il suo autofocus da proprietÓ di posizionamento prima definite--%>
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
              <button type="submit" class="col-2 paddingNav btn btn-outline-primary my-2 my-sm-0">Crea il tuo account</button>
            </div>
          </form>
        </div>

    </div>
</div>

<div id="loginModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title">Login</h4>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
          </div>
          <form action="index.jsp">
            <div class="modal-body">
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
                    <a href="#">Password dimenticata</a>
              
            </div>
            <div class="modal-footer">
              <button type="submit" class="col-2 paddingNav btn btn-outline-primary my-2 my-sm-0">Login</button>
            </div>
          </form>
        </div>

    </div>
</div>