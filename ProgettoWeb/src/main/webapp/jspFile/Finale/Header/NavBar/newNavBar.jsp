<%-- 
    Document   : newNavBar
    Created on : 14-ott-2017, 19.22.22
    Author     : damiano
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

    
    
    $(window).resize(function(){
        if($("nav").width() > 942){
            $("#visible").attr("aria-expanded", "false");
            $("#visible").addClass("collapsed");
            $("#collapse-menu").removeClass("show");
        }
    });
</script>
<style>
    .dropdown-menu.show {
        width: 100%;
        display: inline;
        margin: 0;
        padding: 0;
    }
    
    .jumbotron {
        margin: 0;
        height: 100%;
    }
    
    #del1 {
        padding: 0;
        margin: 0;
    }
    
    .col-2:hover {
        background-color: lightgray;
    }
</style>

<nav class="navbar fixed-top navbar-default bg-light navbar-expand-lg" role="navigation">
    <a class="navbar-brand" href="http://localhost:8080/ProgettoWeb/jspFile/Finale/Index/index.jsp">
        <img src="http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/square.png" width=30px height=30px class="d-inline-block align-top" alt="IMG">
        ShopEro
    </a>
    <button class="navbar-toggler navbar-toggler-right" id="visible" type="button" data-toggle="collapse" data-target="#collapse-menu" aria-controls="collapse-menu" aria-expanded="false" aria-label="Toggle navigation">
        <span class="Small material-icons">dehaze</span>
    </button>
    <div class="navbar-collapse collapse" id="collapse-menu">
        <div class="dropdown">
            <button class="btn btn-primary dropdown-toggle" id="category" type="button" data-toggle="dropdown">Categorie</button>
            <div class="dropdown-menu">
                <div class="jumbotron">
                    <div class="container" id="del1">
                        <h1 class="display-3">Categorie</h1>
                        <c:set var="i" value="${0}" />
                        <c:forEach items="${listacategoriesessione.getList()}" var="cat">
                            <c:if test="${i == 0}">
                                <div class="row">
                            </c:if>
                                    <div class="col-2" style="border-radius: 1rem;">
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
            </div>
        </div>
        <div class="dropdown">
            <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Filtri</button>
            <div class="dropdown-menu">
                <div class="bg-light p-4">
                    <h4>Filtri</h4>
                    <%@include file="filtri.jsp" %>
                </div>
            </div>
        </div>
        <form class="navbar-form form-inline mx-auto">
            <div class="form-group">
                <div class="find">
                    <input class="form-control" id="expand" type="text" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default btn-lg" id="nopad">
                    <i class="material-icons">search</i>
                </button>
            </div>
        </form>
        <div class="user">
            <c:choose>
                <c:when test="${utenteSessione.getId() != -1}">
                    <button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/jspFile/Finale/Utente/utente.jsp'">
                        ${utenteSessione.getNome()} ${utenteSessione.getCognome()}
                    </button>
                </c:when>
                <c:otherwise>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#loginModal">
                        Log In
                    </button>
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#registerModal">
                        Registrati
                    </button>
                </c:otherwise>
            </c:choose>    
            <button type="button" class="btn btn-primary" onclick="location.href='${pageContext.request.contextPath}/OrdineController?action=listOrders'">
                Carello
                <span class="badge badge-light">${carrelloSessione.getSize()}</span>
            </button>
        </div>
    </div>
</nav>
            
<%-- messo esternamente al resto in modo da non influenzare il suo autofocus da propriet� di posizionamento prima definite--%>
<div id="registerModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title">Creare un account</h4>
            <button type="button" class="close" data-dismiss="modal">&times;</button>
          </div>
          <form id="registrationForm" action="${pageContext.request.contextPath}/UserController?action=addUser" method="POST">
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
                <div style="display: none">
                    <input type="text" id="fromPage" name="fromPage" value="${pageContext.request.requestURI}"/>
                </div>
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
