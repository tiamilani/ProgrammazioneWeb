<%--
    Document   : newNavBar
    Created on : 14-ott-2017, 19.22.22
    Author     : damiano
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

    .categs.col-2:hover {
        background-color: lightgray;
    }
</style>

<nav class="navbar fixed-top navbar-default bg-light navbar-expand-lg row no-gutters" role="navigation" style="padding: 0 .5rem 0 .5rem;">
    <a class="navbar-brand" href="http://localhost:8080/ProgettoWeb/jspFile/Finale/Index/index.jsp">
        <img src="http://localhost:8080/ProgettoWeb/jspFile/Finale/Img/shopero.jpg" height=40px style="width: auto;" class="d-inline-block align-top" alt="IMG">
    </a>
    <button class="navbar-toggler navbar-toggler-right" id="visible" type="button" data-toggle="collapse" data-target="#collapse-menu" aria-controls="collapse-menu" aria-expanded="false" aria-label="Toggle navigation">
        <span class="Small material-icons">dehaze</span>
    </button>

    <div class="collapse navbar-collapse row no-gutters" id="collapse-menu">
        <div class="dropdown col-xl-1 col-lg-2 d-none d-sm-none d-md-none d-lg-block">
            <button class="btn nav-button dropdown-toggle" id="category" type="button" data-toggle="dropdown">Categorie</button>
            <div class="dropdown-menu">
                <div class="jumbotron">
                    <div class="container" id="del1">
                        <h1 class="display-3">Categorie</h1>
                        <c:set var="i" value="${0}" />
                        <c:forEach items="${listacategoriesessione.getList()}" var="cat">
                            <c:if test="${i == 0}">
                                <div class="row categs">
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
        <div class="dropdown col-xl-1 col-lg-1 col-md-12 col-sm-12">
            <button class="btn nav-button dropdown-toggle" type="button" data-toggle="dropdown">Filtri</button>
            <div class="dropdown-menu" id="filter-dropdown-menu">
                <div class="bg-light p-4">
                    <h4>Filtri</h4>
                    <%@include file="filtri.jsp" %>
                </div>
            </div>
        </div>
        <form class="navbar-form form-inline col-md-12 col-sm-12 col-xl-7 col-lg-6 row no-gutters" autocomplete="on" id="form1" name="form1" method="GET">
            <div class="form-group input-group">
                <div class="find col-10">
                    <input class="form-control" id="expand" type="text" placeholder="Search" autocomplete="on" name="search" value="${param.search == '' || param.search == null || param.search == undefined ? '' : param.search}">
                    <div style="width: 90%; margin-left: 10%;" id="appendToSearch">  </div>
                </div>

                <button type="submit" class="btn nav-search-button btn-default col-1" id="nopad" onclick="myFunction()">
                    <i class="material-icons">search</i>
                </button>
                <input type='hidden' id='hiddenidCategoria' name='hiddenidCategoria' value='' />
                <input type='hidden' id='hiddennomeVenditore' name='hiddennomeVenditore' value='' />
                <input type='hidden' id='hiddennomeNegozio' name='hiddennomeNegozio' value='' />
                <input type='hidden' id='hiddencheckRitiroInNegozio' name='hiddencheckRitiroInNegozio' value='' />
                <input type='hidden' id='hiddencheckProdottiScontati' name='hiddencheckProdottiScontati' value='' />
                <input type="hidden" id='hiddenPriceRange' name='hiddenPriceRange' value='' />
                <input type='hidden' id='hiddenvalutazioneMinima' name='hiddenvalutazioneMinima' value='' />
                <input type='hidden' id='hiddenRegione' name='hiddenRegione' value='' />
                <input type='hidden' id='hiddenText' name='hiddenText' value='' />
            </div>
        </form>
        <div class="user d-flex justify-content-between row no-gutters col-xl-3 col-lg-3">
            <c:choose>
                <c:when test="${utenteSessione.getId() != -1}">
                    <button type="button" class="btn nav-button col-xl-8 col-lg-9 col-md-12 col-sm-12" onclick="location.href='${pageContext.request.contextPath}/jspFile/Finale/Utente/utente.jsp'">
                        ${utenteSessione.getNome()} ${utenteSessione.getCognome()}
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
                Carrello
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
          <div class="modal-header row no-gutters">
            <h4 class="modal-title col-11">Creare un account</h4>
            <button type="button" class="close col-1" data-dismiss="modal">&times;</button>
          </div>
            <form id="registrationForm" name="registrationForm" onsubmit="registration()" <%--action="${pageContext.request.contextPath}/UserController?action=addUser"--%> method="POST">
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
                    <div class="g-recaptcha" data-sitekey="6Le96jMUAAAAAC5kV0EuyDRuTXUColh5_HReQeCS"></div>
                    <p>Per creare un account devi accettare le nostre politiche sulla privacy, disponibili <a href="http://localhost:8080/ProgettoWeb/jspFile/Finale/Policy/Privacy/privacyPolicy.jsp" target="_blank">qui</a><p>
                    <div>
                        <div class="row">
                            <div class="col-7">
                                <p>Accetto le politiche sulla privacy</p>
                            </div>
                            <div class="col-4">
                                <input type="checkbox" id="privacyCheck" name="privacyCheck" required >
                            </div>
                        </div>
                    </div>
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
            <form id="loginForm" name="loginForm" <%--action="${pageContext.request.contextPath}/UserController?action=selectUser"--%> method="POST">
                <div style="display: none">
                    <input type="text" id="fromPage" name="fromPage" value="${pageContext.request.requestURI}"/>
                </div>
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
              <button type="submit" class="col-12 paddingNav btn btn-outline-primary my-2 my-sm-0" onclick="login()">Login</button>
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


<script>
    $(document).ready(function(){

        //$('#expand').autocomplete().disable();
        $('#expand').on('focus', function(){
            $('#expand').autocomplete({
                serviceUrl: '${pageContext.request.contextPath}/AutocompleteSearchController',
                type: 'POST',
                dataType: 'json',
                paramName: 'research',
                maxHeight: '290',
                orientation: 'auto',
                width: $('#appendToSearch').width(),
                showNoSuggestionNotice: 'true',
                appendTo: $('#appendToSearch'),
                noSuggestionNotice: 'Nessun risultato trovato'
                /*beforeRender: function(container){
                    console.log($('#appendToSearch').width());
                    console.log($('#expand').width());
                    //if($('#expand').width() < hiddenAutoComplete)
                }*/
                /*onSelect: function(suggestion){
                    alert('You selected: ' + suggestion.value);
                }*/
            });
        });

        $(window).scroll(function(){
            // console.log("you are scrolling");
            // $('.autocomplete-suggestions').css('display', 'none');
            if(typeof $('#expand').autocomplete() !== 'undefined'){
                $('#expand').autocomplete().hide();
            }
        });

        $(window).resize(function(){
            //console.log("you are scrolling");
            // $('.autocomplete-suggestions').css('display', 'none');

            $("#visible").attr("aria-expanded", "false");
            $("#visible").addClass("collapsed");
            $("#collapse-menu").removeClass("show");
            //console.log("resizing");
            if(typeof $('#expand').autocomplete() !== 'undefined'){
                $('#expand').autocomplete().hide();
            }
        });

    });

    function resetFilter(){
        document.forms["filterForm"].elements[0].value = 'Categoria';
        if(document.forms["filterForm"].elements[1].value !== ''){
            document.forms["filterForm"].elements[1].value = '';
        }
        document.forms["filterForm"].elements[2].value = '';
        $('input[name=filtroRitiroInNegozio]').prop('checked', false);
        $('input[name=filtroProdottiScontati]').prop('checked', false);
        var minRange = $('#double-slider').attr('data-slider-min');
        var maxRange = $('#double-slider').attr('data-slider-max');
        var rangeValue = minRange + ',' + maxRange;
        console.log(rangeValue);
        // document.getElementById("double-slider").setAttribute('data-slider-value', rangeValue);
        
        document.getElementById("double-slider").setAttribute("value", rangeValue);
        document.getElementById("double-slider").setAttribute("data-value", rangeValue);
        //$('#double-slider').attr('value');
        document.getElementById("regionFilter").value = 'Regione';
        
        $('input[name=valutazioneReview]').prop('checked', false);

    }
    
    document.getElementById("noAction").addEventListener("click", function(event){
        event.preventDefault();
    });


    function myFunction() {
        var idCategoria = document.forms["filterForm"].elements[0].value;
        var nomeVenditore = document.forms["filterForm"].elements[1].value;
        var nomeNegozio = document.forms["filterForm"].elements[2].value;
        var checkRitiroInNegozio = document.forms["filterForm"].elements[3].checked;
        var checkProdottiScontati = document.forms["filterForm"].elements[4].checked;
        var rangeValue = $('#double-slider').attr('value');
        var nomeRegione = $('#regionFilter').val();
        //var testo = $('#expand').val();
        var valutazioneMinima = calcStar();
        console.log(valutazioneMinima);

        console.log("Ho appena assegnato il tutto");

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
        document.form1.hiddenRegione.value = String(nomeRegione);
        //document.form1.hiddenText.value = String(testo + " ");
        
            /*
        document.form1.hiddenlatitudine.value = String(latitudine);
        document.form1.hiddenlongitudine.value = String(longitudine);
        document.form1.hiddenraggio.value = String(raggio);
        */

        //alert('idCategoria: '+ idCategoria +' minPrice: '+ minPrice +' maxPrice: '+ maxPrice +' nomeVenditore: '+ nomeVenditore +' nomeNegozio: '+ nomeNegozio +' checkRitiroInNegozio: '+ checkRitiroInNegozio +' checkProdottiScontati: '+ checkProdottiScontati +' latitudine: '+ latitudine +' longitudine: '+ longitudine +' raggio: '+ raggio +' valutazioneMinima: ' + valutazioneMinima + '')

        form1.action = "${pageContext.request.contextPath}/searchObjectController";
        form1.submit();
    }

    function login() {
        var password = document.loginForm.password.value;

        document.loginForm.password.value = md5(password);

        loginForm.action = "${pageContext.request.contextPath}/UserController?action=selectUser";
        loginForm.submit();
    }

    function registration() {
        var password = document.registrationForm.password.value;
        var confirmPassword = document.registrationForm.confirmPassword.value;

        document.registrationForm.password.value = md5(password);
        document.registrationForm.confirmPassword.value = md5(confirmPassword);

        registrationForm.action = "${pageContext.request.contextPath}/UserController?action=addUser";
        registrationForm.submit();
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

    /*$(window).resize(function(){
        if($("nav").width() > 942){
            // console.log($("nav").width());
            $("#visible").attr("aria-expanded", "false");
            $("#visible").addClass("collapsed");
            $("#collapse-menu").removeClass("show");
        }
    });*/
</script>
