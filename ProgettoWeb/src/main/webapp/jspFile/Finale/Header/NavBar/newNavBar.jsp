<%-- 
    Document   : newNavBar
    Created on : 14-ott-2017, 19.22.22
    Author     : damiano
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
function myFunction() {
    var idCategoria = document.forms["filterForm"].elements[0].value;
    var nomeVenditore = document.forms["filterForm"].elements[1].value;
    var nomeNegozio = document.forms["filterForm"].elements[2].value;
    var checkRitiroInNegozio = document.forms["filterForm"].elements[3].checked;
    var checkProdottiScontati = document.forms["filterForm"].elements[4].checked;
    var rangeValue = $('#double-slider').attr('value');
    var valutazioneMinima = calcStar();
    console.log(valutazioneMinima);
        
    
    
    /*
    var latitudine = document.forms["filterForm"].elements[7].value;
    var longitudine = document.forms["filterForm"].elements[8].value;
    var raggio = document.forms["filterForm"].elements[9].value;
    */

    document.form1.hiddenidCategoria.value = String(idCategoria);
    document.form1.hiddennomeVenditore.value = String(nomeVenditore);
    document.form1.hiddennomeNegozio.value = String(nomeNegozio);
    document.form1.hiddencheckRitiroInNegozio.value = String(checkRitiroInNegozio);
    document.form1.hiddencheckProdottiScontati.value = String(checkProdottiScontati);
    document.form1.hiddenPriceRange.value = String(rangeValue);
    document.form1.hiddenvalutazioneMinima.value = String(valutazioneMinima);
        /*
    document.form1.hiddenlatitudine.value = String(latitudine);
    document.form1.hiddenlongitudine.value = String(longitudine);
    document.form1.hiddenraggio.value = String(raggio);
    */

    //alert('idCategoria: '+ idCategoria +' minPrice: '+ minPrice +' maxPrice: '+ maxPrice +' nomeVenditore: '+ nomeVenditore +' nomeNegozio: '+ nomeNegozio +' checkRitiroInNegozio: '+ checkRitiroInNegozio +' checkProdottiScontati: '+ checkProdottiScontati +' latitudine: '+ latitudine +' longitudine: '+ longitudine +' raggio: '+ raggio +' valutazioneMinima: ' + valutazioneMinima + '')

    form1.action = "${pageContext.request.contextPath}/searchObjectController";
    form1.submit();
}

    function calcStar(){
        if(document.getElementById('star-5').checked){
            return "5";
        }else if(document.getElementById('star-4').checked){
            return "4";
        }else if(document.getElementById('star-3').checked){
            return "3";
        }else if(document.getElementById('star-2').checked){
            return "2";
        }else if(document.getElementById('star-1').checked){
            return "1";
        }else return 0;
    };
    
    $(window).resize(function(){
        if($("nav").width() > 942){
            console.log($("nav").width());
            $("#visible").attr("aria-expanded", "false");
            $("#visible").addClass("collapsed");
            $("#collapse-menu").removeClass("show");
        }
    });
</script>

<nav class="navbar fixed-top navbar-default bg-light navbar-expand-lg row no-gutters" role="navigation" style="padding: 0 .5rem 0 .5rem;">
    <a class="navbar-brand" href="http://localhost:8080/ProgettoWeb/jspFile/Finale/Index/index.jsp">
        <img src="http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/shopero_small.jpg" height=40px class="d-inline-block align-top" alt="IMG">
    </a>
    <button class="navbar-toggler navbar-toggler-right" id="visible" type="button" data-toggle="collapse" data-target="#collapse-menu" aria-controls="collapse-menu" aria-expanded="false" aria-label="Toggle navigation">
        <span class="Small material-icons">dehaze</span>
    </button>
    
    <div class="collapse navbar-collapse row no-gutters" id="collapse-menu">
        <div class="dropdown col-xl-1 col-lg-2 col-md-12 col-sm-12">
            <button class="btn nav-button dropdown-toggle" id="category" type="button" data-toggle="dropdown">Categorie</button>
            <div class="dropdown-menu">
                <h4>Categorie</h4>
                <c:set var="i" value="${0}" />
                <c:forEach items="${listacategoriesessione.getList()}" var="cat">
                    <c:if test="${i == 0}">
                        <div class="row">
                    </c:if>
                            <div class="dropdown-item col-xl-2 col-lg-2 col-md-4 col-sm-6">
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
        <div class="dropdown col-xl-1 col-lg-1 col-md-12 col-sm-12">
            <button class="btn nav-button dropdown-toggle" type="button" data-toggle="dropdown">Filtri</button>
            <div class="dropdown-menu">
                <div class="bg-light p-4">
                    <h4>Filtri</h4>
                    <%@include file="filtri.jsp" %>
                </div>
            </div>
        </div>
        <form class="navbar-form form-inline col-md-12 col-sm-12 col-xl-7 col-lg-6 row no-gutters" autocomplete="on" id="form1" name="form1" method="GET">
            <div class="form-group">
                <div class="find col-10">
                    <input class="form-control" id="expand" type="text" placeholder="Search" autocomplete="on" name="search"> 
                </div>
                <button type="submit" class="btn btn-default col-1" id="nopad" onclick="myFunction()">
                    <i class="material-icons">search</i>
                </button>
                <input type='hidden' id = 'hiddenidCategoria' name = 'hiddenidCategoria' value='' />
                <input type='hidden' id = 'hiddennomeVenditore' name = 'hiddennomeVenditore' value='' />
                <input type='hidden' id = 'hiddennomeNegozio' name = 'hiddennomeNegozio' value='' />
                <input type='hidden' id = 'hiddencheckRitiroInNegozio' name = 'hiddencheckRitiroInNegozio' value='' />
                <input type='hidden' id = 'hiddencheckProdottiScontati' name = 'hiddencheckProdottiScontati' value='' />
                <input type="hidden" id = 'hiddenPriceRange' name = 'hiddenPriceRange' value='' />
                <input type='hidden' id = 'hiddenvalutazioneMinima' name = 'hiddenvalutazioneMinima' value='' />
            </div>
        </form>
        <div class="user d-flex justify-content-between row no-gutters col-xl-3 col-lg-3">
            <c:choose>
                <c:when test="${utenteSessione.getId() != -1}">
                    <button type="button" class="btn nav-button col-xl-4 col-lg-9 col-md-12 col-sm-12"
                            onclick="location.href='${pageContext.request.contextPath}/jspFile/Finale/Utente/utente.jsp'">
                        Account
                    </button>
                </c:when>
                <c:otherwise>
                    <button type="button" class="btn nav-button col-xl-3 col-lg-4 col-md-12 col-sm-12" data-toggle="modal" data-target="#loginModal">
                        Log In
                    </button>
                    <button type="button" class="btn nav-button col-xl-4 col-lg-5 col-md-12 col-sm-12" data-toggle="modal" data-target="#registerModal">
                        Registrati
                    </button>
                </c:otherwise>
            </c:choose>    
            <button type="button" class="btn nav-button col-xl-4 col-lg-3 col-md-12 col-sm-12" onclick="location.href='${pageContext.request.contextPath}/OrdineController?action=listOrders'">
                Carello
            </button>
        </div>
    </div>
</nav>
            
<%-- messo esternamente al resto in modo da non influenzare il suo autofocus da propriet� di posizionamento prima definite--%>
<div id="registerModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header row no-gutters">
            <h4 class="modal-title col-11">Creare un account</h4>
            <button type="button" class="close col-1" data-dismiss="modal">&times;</button>
          </div>
          <form id="registrationForm" action="${pageContext.request.contextPath}/UserController?action=addUser" method="POST">
            <div class="modal-body">
                <div>
                    <i class="large material-icons">person_outline</i>
                    <input class="col-10 modal-input" type="text" id="customerName" name="nome" required>
                    <label class="modal-label" for="customerName">Nome</label>
                </div>
                <div>
                    <i class="large material-icons">group</i>
                    <input class="col-10 modal-input" type="text" id="customerSurname" name="cognome" required>
                    <label class="modal-label" for="customerSurname">Cognome</label>
                </div>
                <div>
                    <i class="large material-icons">email</i>
                    <input class="col-10 modal-input" type="email" id="customerEmail" name="email" required>
                    <label class="modal-label" for="customerEmail">E-mail</label>
                </div>
                <div>
                    <i class="large material-icons">lock_outline</i>
                    <input class="col-10 modal-input" type="password" id="customerPassword" name="password" required>
                    <label class="modal-label" for="customerPassword">Password</label>
                </div>
                <div>
                    <i class="large material-icons">lock_outline</i>
                    <input class="col-10 modal-input" type="password" id="customerConfirmPassword" name="confirmPassword" required>
                    <label class="modal-label" for="customerConfirmPassword">Ripeti la password</label>
                </div>
                    <p>Creando il tuo accont, accetti le nostro condizioni sulla privacy<p>

            </div>
            <div class="modal-footer">
              <button type="submit" class="col-12 paddingNav btn btn-outline-primary">Crea il tuo account</button>
            </div>
          </form>
        </div>

    </div>
</div>

<div id="loginModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header row no-gutters">
            <h4 class="modal-title col-11">Login</h4>
            <button type="button" class="close col-1" data-dismiss="modal">&times;</button>
          </div>
            <form action="${pageContext.request.contextPath}/UserController?action=selectUser" method="POST">
            <div class="modal-body">
                <div>
                    <i class="large material-icons">email</i>
                    <input class="col-10 modal-input" type="email" id="email" name="email" required>
                    <label class="modal-label" for="customerEmail">E-mail</label>
                </div>
                <div>
                    <i class="large material-icons">lock_outline</i>
                    <input class="col-10 modal-input" type="password" id="password" name="password" required>
                    <label class="modal-label" for="customerPassword">Password</label>
                </div>
                <center>    
                    <a href="" data-toggle="modal" data-target="#passwordModal" data-dismiss="modal">Hai dimenticato la password?</a>
                </center>

            </div>
            <div class="modal-footer">
              <button type="submit" class="col-12 paddingNav btn btn-outline-primary my-2 my-sm-0">Login</button>
            </div>
          </form>
        </div>

    </div>
</div>
                
<div id="passwordModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header row no-gutters">
            <h4 class="modal-title col-11">Recupero Password</h4>
            <button type="button" class="close col-1" data-dismiss="modal">&times;</button>
          </div>
            <form action="${pageContext.request.contextPath}/PasswordReset" method="POST">
            <div class="modal-body">
                <div>
                    <i class="large material-icons">email</i>
                    <input class="col-10 modal-input" type="text" id="resetting-email" name="resetting-email" required>
                    <label class="modal-label" for="customerEmail">E-mail</label>
                </div>

            </div>
            <div class="modal-footer">
              <button type="submit" class="col-12 paddingNav btn btn-outline-primary my-2 my-sm-0">Recupera password</button>
            </div>
          </form>
        </div>

    </div>
</div>
