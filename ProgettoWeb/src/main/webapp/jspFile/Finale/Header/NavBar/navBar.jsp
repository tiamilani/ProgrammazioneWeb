<%--
    Document   : navBar
    Created on : 27-set-2017, 20.48.38
    Author     : mattia
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
            <c:set var="i" value="${0}" />
            <c:forEach items="${listacategoriesessione.getList()}" var="cat">
                <c:if test="${i == 0}">
                    <div class="row">
                </c:if>
                <div class="col-2">
                    <c:url value="/CategoriaController" var="catUrl">
                        <c:param name="id" value="${cat.getId()}" />
                    </c:url>
                    <a class="nav-link" href="${catUrl}"><c:out value="${cat.getNome()}"/></a>
                </div>
                <c:set var="i" value="${i+1}" />
                <c:if test="${i == 6}">
                    <c:set var="i" value="${0}" />
                    </div>
                </c:if>
            </c:forEach>
            <c:if test="${i != 0}">
                </div>
            </c:if>
        </div>
    </div>
    <nav id="navbarFiltri" class="navbar navbar-light bg-white fixed-top navbar-expand-lg">
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
                        <i class="Small material-icons">arrow_drop_down</i>    
                        Categorie
                    </button>
                    <button class="paddingNav btn btn-outline-primary" type="button" data-toggle="collapse" data-target="#navbarToggleFiltri" aria-controls="navbarToggleFiltri" aria-expanded="false" aria-label="Toggle navigation">
                        <i class="Small material-icons">arrow_drop_down</i>
                        Filtri
                    </button>
                </div>
            </div>
        </div>

        <div class="col-9 floatRight">
            <div class="row">
                <div class="col-8 float-right">
                    <form class="form-inline float-right col-12" <%--action="${pageContext.request.contextPath}/searchObjectController"--%> id="form1" name="form1" method="GET">
                            <div class="col-11">
                                    <input class="form-control" id="expand" type="text" name="search" placeholder="Search..."/>
                            </div>
                            <div class="col-1">
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
                                <button class="btn btn-outline-primary" type="submit" value="submit" onclick="myFunction()"><i class="Small material-icons">search</i></button>
                            </div>
                    </form>
                </div>
                <div class="col-4">
                    <c:choose>
                        <c:when test="${utente.getId() != -1}">
                            <button  type="button" class="btn btn-outline-primary buttonSpace" data-toggle="modal"
                                 data-target="#loginModal"><i class="Small material-icons">person</i> Account</button>
                        </c:when>
                        <c:otherwise>
                            <button  type="button" class="btn btn-outline-primary buttonSpace" data-toggle="modal"
                                 data-target="#loginModal"><i class="Small material-icons">person</i> Login</button>
                            <button  type="button" class="btn btn-outline-success buttonSpace" data-toggle="modal"
                                     data-target="#registerModal"><i class="Small material-icons">person_add</i> Registrati</button>
                        </c:otherwise>
                    </c:choose>
                        <button  type="button" class="btn btn-outline-primary buttonSpace"><i class="Small material-icons">shopping_basket</i> carrello</button>
                </div>
            </div>
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
          <form action="${pageContext.request.contextPath}/UserController?action=addUser" method="POST">
            <div class="modal-body">
                <div>
                    <i class="large material-icons">person_outline</i>
                    <input class="col-10 modal-input" type="text" id="customerName" name="nome" required>
                    <label for="customerName">Nome</label>
                </div>
                <div>
                    <i class="large material-icons">group</i>
                    <input class="col-10 modal-input" type="text" id="customerSurname" name="cognome" required>
                    <label for="customerSurname">Cognome</label>
                </div>
                <div>
                    <i class="large material-icons">email</i>
                    <input class="col-10 modal-input" type="text" id="customerEmail" name="email" required>
                    <label for="customerEmail">E-mail</label>
                </div>
                <div>
                    <i class="large material-icons">lock_outline</i>
                    <input class="col-10 modal-input" type="password" id="customerPassword" name="password" required>
                    <label for="customerPassword">Password</label>
                </div>
                <div>
                    <i class="large material-icons">lock_outline</i>
                    <input class="col-10 modal-input" type="password" id="customerConfirmPassword" name="confirmPassword" required>
                    <label for="customerConfirmPassword">Ripeti la password</label>
                </div>
                    <p>Creando il tuo accont, accetti le nostro condizioni sulla privacy<p>
              
            </div>
            <div class="modal-footer">
              <button type="submit" class="col-2 paddingNav btn btn-outline-primary">Crea il tuo account</button>
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
            <form action="${pageContext.request.contextPath}/UserController?action=selectUser" method="POST">
            <div class="modal-body">
                <div>
                    <i class="large material-icons">email</i>
                    <input class="col-10 modal-input" type="text" id="email" name="email" required>
                    <label for="customerEmail">E-mail</label>
                </div>
                <div>
                    <i class="large material-icons">lock_outline</i>
                    <input class="col-10 modal-input" type="password" id="password" name="password" required>
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