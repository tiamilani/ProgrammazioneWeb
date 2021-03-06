<%--
    Document   : oggettiCorrelati
    Created on : 3-ott-2017, 22.32.00
    Author     : andreafadi
--%>

<div class="carouselSpacing">
    <div id="oggettiCorrelati" class="carousel slide" data-ride="carousel">
        <c:if test="${listaOggetti.getList().size() > 1}">
            <ol class="carousel-indicators">
                <c:forEach var="i" begin="${0}" end="${listaOggetti.getList().size() -1}" step="2">
                    <c:choose>
                        <c:when test="${i == 0}">
                            <li data-target="#fotoOggetto" data-slide-to="${i}" class="active"></li>
                        </c:when>
                        <c:otherwise>
                            <li data-target="#fotoOggetto" data-slide-to="${i}"></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ol>
        </c:if>
        <div class="carousel-inner">
            <c:forEach var="i" begin="${0}" end="${listaOggetti.getList().size() -1}" step="2">
                <c:choose>
                    <c:when test="${i == 0}">
                        <div class="carousel-item active" style="height: auto; margin: 0 auto;">
                    </c:when>
                    <c:otherwise>
                        <div class="carousel-item" style="height: auto; margin: 0 auto;">
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${(listaOggetti.getList().size() % 2) == 0}">
                        <div class="row">
                            <div class="col-6">
                                <c:url value="/objectSelectedController" var="objUrl" >
                                    <c:param name="idOggetto" value="${listaOggetti.get(i).getId()}" />
                                </c:url>
                                <a href="${objUrl}" class="card cardSmall" style="box-shadow: none; width: 70%; margin-right: 10%; margin-left: auto;">
                                    <img class="imgCard" src="${listaImmaginiOggetto.get(i).getSrc()}" alt="IMAGE NOT LOADED" style="width: auto; height: 300px; object-fit: scale-down;">
                                    <div class="container">
                                        <h4><b><c:out value="${listaOggetti.get(i).getNome()}" /></b></h4>
                                        <p>Prezzo: <c:out value="${listaOggetti.get(i).getPrezzo()}" />&euro;</p>
                                    </div>
                                </a>
                            </div>
                            <div class="col-6">
                                <c:url value="/objectSelectedController" var="objUrl" >
                                    <c:param name="idOggetto" value="${listaOggetti.get(i+1).getId()}" />
                                </c:url>
                                <a href="${objUrl}" class="card cardSmall" style="box-shadow: none; width: 70%; margin-right: auto; margin-left: 10%;">
                                    <img class="imgCard" src="${listaImmaginiOggetto.get(i+1).getSrc()}" alt="IMAGE NOT LOADED" style="width: auto; height: 300px; object-fit: scale-down;">
                                    <div class="container">
                                        <h4><b><c:out value="${listaOggetti.get(i+1).getNome()}" /></b></h4>
                                        <p>Prezzo: <c:out value="${listaOggetti.get(i+1).getPrezzo()}" />&euro;</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:choose>
                            <c:when test="${i == (listaOggetti.getList().size() - 1)}">
                                <div class="row">
                                    <div class="col-12">
                                        <c:url value="/objectSelectedController" var="objUrl" >
                                            <c:param name="idOggetto" value="${listaOggetti.get(i).getId()}" />
                                        </c:url>
                                        <a href="${objUrl}" class="card cardSmall" style="box-shadow: none; width: 70%; margin-right: auto; margin-left: auto;">
                                            <img class="imgCard" src="${listaImmaginiOggetto.get(i).getSrc()}" alt="IMAGE NOT LOADED" style="width: auto; height: 300px; object-fit: scale-down;">
                                            <div class="container">
                                                <h4><b><c:out value="${listaOggetti.get(i).getNome()}" /></b></h4>
                                                <p>Prezzo: <c:out value="${listaOggetti.get(i).getPrezzo()}" />&euro;</p>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="row">
                                    <div class="col-6">
                                        <c:url value="/objectSelectedController" var="objUrl" >
                                            <c:param name="idOggetto" value="${listaOggetti.get(i).getId()}" />
                                        </c:url>
                                        <a href="${objUrl}" class="card cardSmall" style="box-shadow: none; width: 70%; margin-right: 10%; margin-left: auto;">
                                            <img class="imgCard" src="${listaImmaginiOggetto.get(i).getSrc()}" alt="IMAGE NOT LOADED" style="width: auto; height: 300px; object-fit: scale-down;">
                                            <div class="container">
                                                <h4><b><c:out value="${listaOggetti.get(i).getNome()}" /></b></h4>
                                                <p>Prezzo: <c:out value="${listaOggetti.get(i).getPrezzo()}" />&euro;</p>
                                            </div>
                                        </a>
                                    </div>
                                    <div class="col-6">
                                        <c:url value="/objectSelectedController" var="objUrl" >
                                            <c:param name="idOggetto" value="${listaOggetti.get(i+1).getId()}" />
                                        </c:url>
                                        <a href="${objUrl}" class="card cardSmall" style="box-shadow: none; width: 70%; margin-right: auto; margin-left: 10%;">
                                            <img class="imgCard" src="${listaImmaginiOggetto.get(i+1).getSrc()}" alt="IMAGE NOT LOADED" style="width: auto; height: 300px; object-fit: scale-down;">
                                            <div class="container">
                                                <h4><b><c:out value="${listaOggetti.get(i+1).getNome()}" /></b></h4>
                                                <p>Prezzo: <c:out value="${listaOggetti.get(i+1).getPrezzo()}" />&euro;</p>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
            </div>
            </c:forEach>
        </div>
        <c:if test="${listaOggetti.getList().size() > 1}">
            <a class="carousel-control-prev" href="#oggettiCorrelati" role="button" data-slide="prev">
                <i class="fa fa-chevron-left" aria-hidden="true" style="color: rgb(225, 100, 50); font-size: 200%;"></i>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#oggettiCorrelati" role="button" data-slide="next">
                <i class="fa fa-chevron-right" aria-hidden="true" style="color: rgb(225, 100, 50); font-size: 200%;"></i>
                <span class="sr-only">Next</span>
            </a>
        </c:if>
    </div>
</div>