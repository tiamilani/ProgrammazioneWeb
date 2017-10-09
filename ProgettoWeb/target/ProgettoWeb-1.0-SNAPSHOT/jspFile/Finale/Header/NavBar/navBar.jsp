<%--
    Document   : navBar
    Created on : 27-set-2017, 20.48.38
    Author     : mattia
--%>
<script>
function myFunction() {
    var idCategoria = document.forms["filterForm"].elements[0].value;
    var minPrice = document.forms["filterForm"].elements[1].value;
    var maxPrice = document.forms["filterForm"].elements[2].value;
    var nomeVenditore = document.forms["filterForm"].elements[3].value;
    var nomeNegozio = document.forms["filterForm"].elements[4].value;
    var checkRitiroInNegozio = document.forms["filterForm"].elements[5].checked;
    var checkProdottiScontati = document.forms["filterForm"].elements[6].checked;
    var latitudine = document.forms["filterForm"].elements[7].value;
    var longitudine = document.forms["filterForm"].elements[8].value;
    var raggio = document.forms["filterForm"].elements[9].value;
    var valutazioneMinima = document.forms["filterForm"].elements[10].value;
    
    document.form1.hiddenidCategoria.value = String(idCategoria);
    document.form1.hiddenminPrice.value = String(minPrice);
    document.form1.hiddenmaxPrice.value = String(maxPrice);
    document.form1.hiddennomeVenditore.value = String(nomeVenditore);
    document.form1.hiddennomeNegozio.value = String(nomeNegozio);
    document.form1.hiddencheckRitiroInNegozio.value = String(checkRitiroInNegozio);
    document.form1.hiddencheckProdottiScontati.value = String(checkProdottiScontati);
    document.form1.hiddenlatitudine.value = String(latitudine);
    document.form1.hiddenlongitudine.value = String(longitudine);
    document.form1.hiddenraggio.value = String(raggio);
    document.form1.hiddenvalutazioneMinima.value = String(valutazioneMinima);
    
    //alert('idCategoria: '+ idCategoria +' minPrice: '+ minPrice +' maxPrice: '+ maxPrice +' nomeVenditore: '+ nomeVenditore +' nomeNegozio: '+ nomeNegozio +' checkRitiroInNegozio: '+ checkRitiroInNegozio +' checkProdottiScontati: '+ checkProdottiScontati +' latitudine: '+ latitudine +' longitudine: '+ longitudine +' raggio: '+ raggio +' valutazioneMinima: ' + valutazioneMinima + '')
    form1.action = "${pageContext.request.contextPath}/searchObjectController";
    form1.submit();
}
</script>
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
                <c:url value="/CategoriaController" var="catUrl">
                    <c:param name="id" value="${cat.getId()}" />
                </c:url>
                <a class="nav-link" href="${catUrl}"><c:out value="${cat.getNome()}"/></a>
            </c:forEach>
        </div>
    </div>
    <nav class="navbar navbar-light bg-white fixed-top navbar-expand-lg">
        <div class="col-1">
            <a class="navbar-brand" href="http://localhost:8080/ProgettoWeb/jspFile/Finale/Index/index.jsp">
              <img src="http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/square.png" width="30" height="30" class="d-inline-block align-top" alt="IMG">
              ShopEro
            </a>
        </div>
        <div class="col-2">
            <div class="row">
                <div class="navbar-nav">
                    <button class="paddingNav btn btn-outline-primary" type="button" data-toggle="collapse" data-target="#navbarToggleCategorie" aria-controls="navbarToggleCategorie" aria-expanded="false" aria-label="Toggle navigation">
                            Categorie
                    </button>
                    <button class="paddingNav btn btn-outline-primary" type="button" data-toggle="collapse" data-target="#navbarToggleFiltri" aria-controls="navbarToggleFiltri" aria-expanded="false" aria-label="Toggle navigation">
                            Filtri
                    </button>
                </div>
            </div>
        </div>

        <div class="col-9 floatRight">
            <div class="row">
                <div class="col-10">
                    <form class="form-inline" <%--action="${pageContext.request.contextPath}/searchObjectController"--%> id="form1" name="form1" method="GET">
                    <input class="form-control" id="expand" type="text" name="search" placeholder="Search..."/>
                    <input type='hidden' id= 'hiddenidCategoria' name = 'hiddenidCategoria' value='' />
                    <input type='hidden' id= 'hiddenminPrice' name = 'hiddenminPrice' value='' />
                    <input type='hidden' id= 'hiddenmaxPrice' name = 'hiddenmaxPrice' value='' />
                    <input type='hidden' id= 'hiddennomeVenditore' name = 'hiddennomeVenditore' value='' />
                    <input type='hidden' id= 'hiddennomeNegozio' name = 'hiddennomeNegozio' value='' />
                    <input type='hidden' id= 'hiddencheckRitiroInNegozio' name = 'hiddencheckRitiroInNegozio' value='' />
                    <input type='hidden' id= 'hiddencheckProdottiScontati' name = 'hiddencheckProdottiScontati' value='' />
                    <input type='hidden' id= 'hiddenlatitudine' name = 'hiddenlatitudine' value='' />
                    <input type='hidden' id= 'hiddenlongitudine' name = 'hiddenlongitudine' value='' />
                    <input type='hidden' id= 'hiddenraggio' name = 'hiddenraggio' value='' />
                    <input type='hidden' id= 'hiddenvalutazioneMinima' name = 'hiddenvalutazioneMinima' value='' />
                    <button class="btn btn-outline-success" type="submit" value="submit" onclick="myFunction()">Search</button>
                </form>
                </div>
                <div class="col-2">
                    <form class="form-inline">
                        <button  type="button" class="btn btn-outline-success" data-toggle="modal"
                                 data-target="#loginModal">Login</button>
                        <button  type="button" class="btn btn-outline-primary" data-toggle="modal"
                                 data-target="#registerModal">Registrati</button>
                    </form>
                </div>
            </div>
        </div>
    </nav>
</div>
            

<%-- messo esternamente al resto in modo da non influenzare il suo autofocus da propriet� di posizionamento prima definite--%>
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